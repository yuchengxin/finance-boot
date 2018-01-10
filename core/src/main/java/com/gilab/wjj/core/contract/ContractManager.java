package com.gilab.wjj.core.contract;

import com.gilab.wjj.core.ContractAgent;
import com.gilab.wjj.exception.FinanceErrMsg;
import com.gilab.wjj.exception.FinanceRuntimeException;
import com.gilab.wjj.persistence.dao.ContractDao;
import com.gilab.wjj.persistence.dao.MerchantDao;
import com.gilab.wjj.persistence.dao.ProposalDao;
import com.gilab.wjj.persistence.model.*;
import com.gilab.wjj.util.DateUtils;
import com.gilab.wjj.util.logback.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yuankui on 12/20/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
@Service("contractAgent")
public class ContractManager implements ContractAgent {

    private static final Logger logger = LoggerFactory.getLogger(ContractManager.class);

    @Autowired
    private ContractDao contractDao;

    @Autowired
    private MerchantDao merchantDao;

    @Autowired
    private ProposalDao proposalDao;

    @Override
    public ReqResult<Contract> getContract(long contractId) {
        Contract contract = contractDao.getContract(contractId);
        if (contract == null) {
            return ReqResult.fail("Cannot find contract[%d]", contractId);
        } else {
            return ReqResult.success(contract, "contract found.");
        }
    }

    @Override
    public List<Contract> getContractWithFilter(Long filterStartTime, Long filterEndTime, String contractVersion, Double buildingStartSize,
                                                Double buildingEndSize, SigningMode signMode, ContractStatus contractStatus) {
        return contractDao.getContractWithFilter(filterStartTime, filterEndTime, contractVersion, buildingStartSize, buildingEndSize,
                signMode, contractStatus);
    }

    @Override
    public ReqResult<Contract> createContract(Contract contract) {
        ReqResult<Contract> preCheckResult = preCheckCreate(contract);
        if (!preCheckResult.isSuccess()) {
            return preCheckResult;
        }
        long id = contractDao.createContract(contract);
        Contract newContract = contract.clone();
        newContract.setId(id);
        return ReqResult.success(newContract, "contract[%d] created.", id);
    }

    @Override
    public SimpleReqResult updateContract(Contract contract) {
        return null;
    }

    @Override
    public SimpleReqResult deleteContract(long contractId) {
        return null;
    }

    @Override
    @Transactional
    public ReqResultMap batchCreateContracts(List<BasicRentInfo> basicRentInfos) {
        List<Contract> contracts = new ArrayList<>();

        Map<BasicRentInfo, String> resultFailed = new HashMap<>();
        Map<BasicRentInfo, String> resultSucceed = new HashMap<>();
        for(BasicRentInfo basicRentInfo : basicRentInfos){
            StringBuilder resultStr = new StringBuilder();
            if(basicRentInfo.getSigningDate() == null){
                resultStr.append("签约日期不能为空;");
            } else if(basicRentInfo.getBuildingInfo() == null){
                resultStr.append("楼层/房号不能为空;");
            }
            if(!resultStr.toString().isEmpty()){
                resultFailed.put(basicRentInfo, resultStr.toString());
                continue;
            }
            basicRentInfo.setContractNo(DateUtils.datetimeString(basicRentInfo.getSigningDate().getTime(), "yyMMdd") + basicRentInfo.getBuildingInfo());
            if(contractDao.getContractWithNo(basicRentInfo.getContractNo()) != null){
                resultFailed.put(basicRentInfo, "数据库中已经有合同编号为"+basicRentInfo.getContractNo()+"的合同了");
                continue;
            }
            Contract contract = basicRentInfo2Contract(basicRentInfo);
            if(contract.getContractVersion() == null){
                contract.setContractVersion("1.0");
            }
            System.out.println(contract.getContractVersion());
            switch (contract.getContractVersion()){
                case "1.0" :
                case "1" :
                    contract.setProposalId(1L);
                    break;
                case "2" :
                case "2.0" :
                    contract.setProposalId(2L);
                    break;
                case "3" :
                case "3.0" :
                    contract.setProposalId(1L);
                    break;
                case "4" :
                case "4.0" :
                    contract.setProposalId(1L);
                    break;
                default:
                    contract.setContractStatus(ContractStatus.UNSTARTED);
            }
            if(contract.getBeneficiary() == null || contract.getBeneficiary().getMerchantName() == null
                    || contract.getBeneficiary().getBankInfo() == null || contract.getBeneficiary().getBankAccount() == null
                    || contract.getBeneficiary().getMerchantIdNo() == null || contract.getPaybackDate() == null
                    || contract.getLeasebackPrice() == null || contract.getProposalId() == null){
                contract.setContractStatus(ContractStatus.UNSTARTED);
            } else {
                ContractStatus status;
                Proposal proposal = proposalDao.getProposal(contract.getProposalId());
                if(proposal == null){
                    logger.error("can't find proposal[d%]", contract.getProposalId());
                    throw new FinanceRuntimeException(FinanceErrMsg.NAMED_RESOURCE_NOT_CAPABLE, "proposal isn't exist");
                }
                long payStartTime = DateUtils.convertJodaTime(basicRentInfo.getPaybackDate()).plusYears(proposal.getMarketCulLife()).plusDays(-1).getMillis();
                long contractTerTime = DateUtils.convertJodaTime(payStartTime).plusYears(proposal.getLeasebackLife()).getMillis();
                long currentTime = System.currentTimeMillis();
                if(currentTime < payStartTime && currentTime > basicRentInfo.getPaybackDate().getTime()){
                    status = ContractStatus.PENDINGRENTAL;
                }else if(currentTime >= payStartTime && currentTime < contractTerTime){
                    status = ContractStatus.RENTAL;
                }else if(currentTime >= contractTerTime){
                    status = ContractStatus.NORMALEND;
                } else
                    status = ContractStatus.UNSTARTED;

                contract.setPayStartDate(payStartTime);
                contract.setContractTerDate(contractTerTime);
                contract.setContractStatus(status);
            }
            contracts.add(contract);
            resultSucceed.put(basicRentInfo, "成功");
        }
        if(resultFailed.size() != 0){
            for (BasicRentInfo b : resultFailed.keySet()){
                System.out.println(b);
                System.out.println(resultFailed.get(b));
            }
            return ReqResultMap.create(false, resultFailed, "部分数据有问题，请确认之后重新导入");
        }
        contractDao.batchCreateContracts(contracts);
        return ReqResultMap.success(resultSucceed, "导入成功");
    }

    private Contract basicRentInfo2Contract(BasicRentInfo basicRentInfo){
        List<Merchant> signer = string2Merchant(basicRentInfo.getSigner(), basicRentInfo.getPhone(), basicRentInfo.getMerchantIdNo(), null, null, null);
        System.out.println(basicRentInfo.getPhone());
        if(signer != null && signer.size() != 0)
            for(Merchant merchant : signer){
                if(merchantDao.getMerchantWithCheck(merchant.getMerchantName(), merchant.getMerchantPhone(), merchant.getMerchantIdNo()) == null){
                    merchantDao.createMerchant(merchant);
                }
            }
        List<Merchant>  beneficiary = string2Merchant(basicRentInfo.getBeneficiary(), null, basicRentInfo.getBeneficiaryIdNo(), basicRentInfo.getBankInfo(), basicRentInfo.getBankAccount(), basicRentInfo.getBeneficiaryAddress());
        if(beneficiary != null && beneficiary.size() != 0)
            for(Merchant merchant : beneficiary){
                if(merchantDao.getMerchantWithCheck(merchant.getMerchantName(), merchant.getMerchantPhone(), merchant.getMerchantIdNo()) == null){
                    merchantDao.createMerchant(merchant);
                }
            }

        return new Contract.Builder()
                .signer(signer)
                .signingMode(SigningMode.strLookup(basicRentInfo.getSigningMode()))
                .signingDate(basicRentInfo.getSigningDate().getTime())
                .signTotalPrice(basicRentInfo.getSignTotalPrice())
                .subscriptionDate(basicRentInfo.getSubscriptionDate() == null ? null : basicRentInfo.getSubscriptionDate().getTime())
                .leasebackPrice(basicRentInfo.getLeasebackPrice())
                .paybackDate(basicRentInfo.getPaybackDate() == null ? null : basicRentInfo.getPaybackDate().getTime())
                .buildingInfo(basicRentInfo.getBuildingInfo())
                .buildingSize(basicRentInfo.getBuildingSize())
                .backPremium(basicRentInfo.getBackPremium())
                .beneficiary(beneficiary == null ? null : beneficiary.get(0))
                .contractVersion(basicRentInfo.getContractVersion())
                .contractNo(basicRentInfo.getContractNo())
                .logs("...")
                .originalPrice(basicRentInfo.getOriginalPrice())
                .region(basicRentInfo.getRegion())
                .totalPrice(basicRentInfo.getTotalPrice())
                .signingStatus(SigningStatus.strLookup(basicRentInfo.getSigningStatus()))
                .build();
    }

    private List<Merchant> string2Merchant(String nameStr, String phoneStr, String IDStr, String bankStr, String accountStr, String addressStr){
        String[] name = null;
        String[] phone = null;
        String[] ID = null;
        String[] bank = null;
        String[] account = null;
        String[] address = null;
        int nameLength = 0;
        int phoneLength = 0;
        int IDLength = 0;
        int bankLength = 0;
        int accountLength = 0;
        int addressLength = 0;
        if(nameStr != null){
            name = nameStr.split(",");
            nameLength = name.length;
        }
        if(phoneStr != null) {
            phone = phoneStr.split(",");
            phoneLength = phone.length;
        }
        if(IDStr != null){
            ID = IDStr.split(",");
            IDLength = ID.length;
        }
        if(bankStr != null){
            bank = bankStr.split(",");
            bankLength = bank.length;
        }
        if(accountStr != null){
            account = accountStr.split(",");
            accountLength = account.length;
        }
        if(addressStr != null){
            address = addressStr.split(",");
            addressLength = address.length;
        }
        if(nameLength == 0)return null;
        List<Merchant> merchants = new ArrayList<>();
        if((phoneLength == 0 || phoneLength == nameLength) && (IDLength == 0 || bankLength == nameLength) &&
                (accountLength == 0 || accountLength == nameLength) && (addressLength == 0 || addressLength == nameLength)){
            for(int i = 0; i < nameLength; i++){
                Merchant merchant = new Merchant();
                merchant.setMerchantName(name[i]);
                if(phone != null)
                    merchant.setMerchantPhone(phone[i]);
                if(ID != null)
                    merchant.setMerchantIdNo(ID[i]);
                if(bank != null)
                    merchant.setBankInfo(bank[i]);
                if(account != null)
                    merchant.setBankAccount(account[i]);
                if(address != null)
                    merchant.setMerchantAddress(address[i].replaceAll("[\\t\\n\\r]", ""));
                merchants.add(merchant);
            }
        }else{
            Merchant merchant = new Merchant.Builder()
                    .merchantName(nameStr)
                    .merchantPhone(phoneStr)
                    .merchantIdNo(IDStr)
                    .bankInfo(bankStr)
                    .bankAccount(accountStr)
                    .merchantAddress(addressStr)
                    .build();
            merchants.add(merchant);
        }
        return merchants;
    }

    private ReqResult<Contract> preCheckCreate(Contract contract){
        if(contract.getContractNo() == null){
            return ReqResult.fail("合同编号不能为空");
        } else if(contractDao.getContractWithNo(contract.getContractNo()) != null){
            return ReqResult.fail("合同编号不允许相同");
        } else if(contract.getPaybackDate() == null || contract.getPaybackDate() <= 0){
            return ReqResult.fail("回款日期不能为空");
        } else if(contract.getLeasebackPrice() == null || contract.getLeasebackPrice() <= 0) {
            return ReqResult.fail("返租基价不能为空");
        } else if(contract.getProposalId() <= 0 || proposalDao.getProposal(contract.getProposalId()) == null){
            return ReqResult.fail("方案不存在");
        } else if(contract.getBeneficiary() == null || contract.getBeneficiary().getMerchantName() == null
                || contract.getBeneficiary().getMerchantIdNo() == null || contract.getBeneficiary().getBankInfo() == null
                || contract.getBeneficiary().getBankAccount() == null){
            return ReqResult.fail("受益人信息不完整");
        } else {
            Proposal proposal = proposalDao.getProposal(contract.getProposalId());
            long payStartTime = DateUtils.convertJodaTime(contract.getPaybackDate()).plusYears(proposal.getMarketCulLife()).plusDays(-1).getMillis();
            long contractTerTime = DateUtils.convertJodaTime(payStartTime).plusYears(proposal.getLeasebackLife()).getMillis();
            contract.setPayStartDate(payStartTime);
            contract.setContractTerDate(contractTerTime);
            for(Merchant m : contract.getSigner()){
                if(merchantDao.getMerchant(m.getId()) == null){
                    merchantDao.createMerchant(m);
                }
            }
            if(merchantDao.getMerchant(contract.getBeneficiary().getId()) == null){
                merchantDao.createMerchant(contract.getBeneficiary());
            }
            long currentTime = System.currentTimeMillis();
            if(currentTime < payStartTime && currentTime > contract.getPaybackDate()){
                contract.setContractStatus(ContractStatus.PENDINGRENTAL);
            }else if(currentTime >= payStartTime && currentTime < contract.getContractTerDate()){
                contract.setContractStatus(ContractStatus.RENTAL);
            }else if(currentTime >= contract.getContractTerDate()){
                contract.setContractStatus(ContractStatus.NORMALEND);
            } else
                contract.setContractStatus(ContractStatus.UNSTARTED);
            return ReqResult.emptySuccess();
        }
    }
}
