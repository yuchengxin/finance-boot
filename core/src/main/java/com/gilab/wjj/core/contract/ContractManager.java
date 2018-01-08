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
import java.util.List;

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
    public SimpleReqResult batchCreateContracts(List<BasicRentInfo> basicRentInfos) {
        List<Contract> contracts = new ArrayList<>();
        for(BasicRentInfo basicRentInfo : basicRentInfos){
            Contract contract = basicRentInfo2Contract(basicRentInfo);
            contracts.add(contract);
        }
        contractDao.batchCreateContracts(contracts);
        return SimpleReqResult.success("succeed to batch create contracts");
    }

    private Contract basicRentInfo2Contract(BasicRentInfo basicRentInfo){
        List<Merchant> signer = string2Merchant(basicRentInfo.getSigner(), basicRentInfo.getPhone(), basicRentInfo.getMerchantIdNo(), null, null, null);
        if(signer != null && signer.size() != 0)
            for(Merchant merchant : signer){
                if(merchantDao.getMerchantWithFilter(merchant.getMerchantName(), merchant.getMerchantPhone(), null, null) == null){
                    merchantDao.createMerchant(merchant);
                }
            }
        List<Merchant>  beneficiary = string2Merchant(basicRentInfo.getBeneficiary(), null, basicRentInfo.getBeneficiaryIdNo(), basicRentInfo.getBankInfo(), basicRentInfo.getBankAccount(), basicRentInfo.getBeneficiaryAddress());
        if(beneficiary != null && beneficiary.size() != 0)
            for(Merchant merchant : beneficiary){
                if(merchantDao.getMerchantWithFilter(merchant.getMerchantName(), merchant.getMerchantPhone(), null, null) == null){
                    merchantDao.createMerchant(merchant);
                }
            }
        String version;
        if(basicRentInfo.getContractVersion() == null || basicRentInfo.getContractVersion().isEmpty()){
            version = "1";
        } else {
            version = basicRentInfo.getContractVersion();
        }
        int proposalId = 1;
        switch (version){
            case "1":
                proposalId = 1;
                break;
            case "2":
                proposalId = 2;
                break;
            case "3":
                proposalId = 3;
                break;
            case "4":
                proposalId = 4;
                break;
            default:
                break;
        }
        ContractStatus status = ContractStatus.UNSIGNED;
        if(basicRentInfo.getContractStatus().equals("已签") && basicRentInfo.getPaybackDate() != null && basicRentInfo.getContractTerDate() != null){
            Proposal proposal = proposalDao.getProposal(proposalId);
            if(proposal == null){
                logger.error("can't find proposal[d%]", proposalId);
                throw new FinanceRuntimeException(FinanceErrMsg.NAMED_RESOURCE_NOT_CAPABLE, "proposal isn't exist");
            }
            long payStartTime = DateUtils.convertJodaTime(basicRentInfo.getPaybackDate()).plusYears(proposal.getMarketCulLife()).plusDays(-1).getMillis();
            long currentTime = System.currentTimeMillis();
                if(currentTime < payStartTime && currentTime > basicRentInfo.getPaybackDate().getTime()){
                    status = ContractStatus.PENDINGRENTAL;
                }else if(currentTime >= payStartTime && currentTime < basicRentInfo.getContractTerDate().getTime()){
                    status = ContractStatus.RENTAL;
                }else if(currentTime >= basicRentInfo.getContractTerDate().getTime()){
                    status = ContractStatus.NORMALEND;
                }
        } else if(basicRentInfo.getContractStatus().equals("已结束")){
            status = ContractStatus.NORMALEND;
        } else {
            status = ContractStatus.ABNORMALEND;
        }

        return new Contract.Builder()
                .signer(signer)
                .signingMode(SigningMode.strLookup(basicRentInfo.getSigningMode()))
                .signingDate(basicRentInfo.getSigningDate() == null ? 0 : basicRentInfo.getSigningDate().getTime())
                .signTotalPrice(basicRentInfo.getSignTotalPrice())
                .subscriptionDate(basicRentInfo.getSubscriptionDate() == null ? 0 : basicRentInfo.getSubscriptionDate().getTime())
                .leasebackPrice(basicRentInfo.getLeasebackPrice())
                .paybackDate(basicRentInfo.getPaybackDate() == null ? 0 : basicRentInfo.getPaybackDate().getTime())
                .payStartDate(basicRentInfo.getPayStartDate() == null ? 0 : basicRentInfo.getPayStartDate().getTime() )
                .buildingInfo(basicRentInfo.getBuildingInfo())
                .buildingSize(basicRentInfo.getBuildingSize())
                .backPremium(basicRentInfo.getBackPremium())
                .beneficiary(beneficiary == null ? null : beneficiary.get(0))
                .contractTerDate(basicRentInfo.getContractTerDate() == null ? 0 : basicRentInfo.getContractTerDate().getTime())
                .contractVersion(version)
                .contractNo(basicRentInfo.getContractNo())
                .contractStatus(status)
                .logs("...")
                .originalPrice(basicRentInfo.getOriginalPrice())
                .proposalId(proposalId)
                .region(basicRentInfo.getRegion())
                .totalPrice(basicRentInfo.getTotalPrice())
                .taxAmount(basicRentInfo.getTaxAmount())
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
                    merchant.setMerchantAddress(address[i]);
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
        if(contract.getPaybackDate() <= 0){
            return ReqResult.fail("回款日期不能为空");
        } else if(contract.getLeasebackPrice() <= 0) {
            return ReqResult.fail("返租基价不能为空");
        } else if(contract.getProposalId() <= 0 || proposalDao.getProposal(contract.getProposalId()) == null){
            return ReqResult.fail("方案不存在");
        } else {
            Proposal proposal = proposalDao.getProposal(contract.getProposalId());
            long payStartTime = DateUtils.convertJodaTime(contract.getPaybackDate()).plusYears(proposal.getMarketCulLife()).plusDays(-1).getMillis();
            long contractTerTime = DateUtils.convertJodaTime(payStartTime).plusYears(proposal.getLeasebackLife()).getMillis();
            contract.setPayStartDate(payStartTime);
            contract.setContractTerDate(contractTerTime);
            return ReqResult.emptySuccess();
        }
    }
}
