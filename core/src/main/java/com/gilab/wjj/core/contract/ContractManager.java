package com.gilab.wjj.core.contract;

import com.gilab.wjj.core.BasicRentAgent;
import com.gilab.wjj.core.ContractAgent;
import com.gilab.wjj.exception.FinanceErrMsg;
import com.gilab.wjj.exception.FinanceRuntimeException;
import com.gilab.wjj.persistence.dao.BasicLedgerDao;
import com.gilab.wjj.persistence.dao.ContractDao;
import com.gilab.wjj.persistence.dao.MerchantDao;
import com.gilab.wjj.persistence.dao.ProposalDao;
import com.gilab.wjj.persistence.model.*;
import com.gilab.wjj.util.DateUtils;
import com.gilab.wjj.util.logback.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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

    @Autowired
    private BasicRentAgent basicRentMgr;

    @Autowired
    private BasicLedgerDao basicLedgerDao;

    @Value("${tax.rate_min}")
    private double tax_rate_min;

    @Value("${tax.rate_max}")
    private double tax_rate_max;

    @Value("${tax.line}")
    private int tax_rate_line ;

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
    public List<Contract> getContractWithFilter(Long filterStartTime, Long filterEndTime, String contractVersion, String buildingInfo,
                                                SigningMode signMode, ContractStatus contractStatus) {
        return contractDao.getContractWithFilter(filterStartTime, filterEndTime, contractVersion, buildingInfo,
                signMode, contractStatus);
    }

    @Override
    public List<AllBasicRentResult> getContractAndCalResultWithFilter(String contractNo, Long filterStartTime, Long filterEndTime, String contractVersion, String buildingInfo, SigningMode signMode, ContractStatus contractStatus, String beneficiaryName, String beneficiaryPhone, String beneficiaryIDNO, String beneficiaryAccount) {
        List<AllBasicRentResult> allBasicRentResults = new ArrayList<>();
        List<Contract> contracts = new ArrayList<>();
        if(contractNo != null && !contractNo.isEmpty()){
            Contract contract = contractDao.getContractWithNo(contractNo);
            if(contract != null){
                contracts.add(contract);
            }
        } else if((beneficiaryName != null && !beneficiaryName.isEmpty()) ||
                (beneficiaryPhone != null && !beneficiaryPhone.isEmpty()) ||
                (beneficiaryIDNO != null && !beneficiaryIDNO.isEmpty()) ||
                (beneficiaryAccount != null && !beneficiaryAccount.isEmpty())){
            List<Merchant> merchantList = merchantDao.getMerchantWithFilter(beneficiaryName, beneficiaryPhone, beneficiaryIDNO, beneficiaryAccount);
            if(merchantList != null && !merchantList.isEmpty()){
                List<Long> merchantIdList = new ArrayList<>();
                for (Merchant m : merchantList){
                    merchantIdList.add(m.getId());
                }
                List<Contract> contractWithBeneficiary = contractDao.getContractWithBeneficiaryList(merchantIdList);
                if(contractWithBeneficiary != null && !contractWithBeneficiary.isEmpty()){
                    contracts.addAll(contractWithBeneficiary);
                }
            }
        } else {
            List<Contract> contractWithFilter = contractDao.getContractWithFilter(filterStartTime, filterEndTime, contractVersion, buildingInfo, signMode, contractStatus);
            if(contractWithFilter != null && !contractWithFilter.isEmpty()){
                contracts.addAll(contractWithFilter);
            }
        }
        for(Contract c : contracts){
            List<BasicLedger> basicLedgers = basicLedgerDao.getBasicLedgerWithContract(c.getId());
            AllBasicRentResult calResult = calResultStr(c, basicLedgers);
            calResult.setContract(c);
            allBasicRentResults.add(calResult);
        }

        return allBasicRentResults;
    }

    private AllBasicRentResult calResultStr(Contract contract, List<BasicLedger> basicLedgers){
        if(basicLedgers == null || basicLedgers.isEmpty()){
            return null;
        }
        Proposal proposal = proposalDao.getProposal(contract.getProposalId());
        if(proposal == null){
            logger.error("can't find proposal[{}]", contract.getProposalId());
            throw new FinanceRuntimeException(FinanceErrMsg.NAMED_INPUT_ILLEGAL, "Cannot find contract[" + contract.getId() +"]'s proposal");
        }
        double total = 0;
        long preRentDate = 0;
        double preRentCountPre = 0;
        double preRentCountPost = 0;
        double normalTaxRate = 0;
        int flag = 0;
        AllBasicRentResult result = new AllBasicRentResult();
        List<SpecialMonthBasicRentResult> extendMonthRentResults = new ArrayList<>();
        List<NormalMonthBasicRentResult> normalMonthBasicRentResults = new ArrayList<>();
        for(BasicLedger b : basicLedgers){
            total += b.getPlanPayCountPost();
            if(b.getRentMonthMode() == RentMonthMode.FIRSTMONTH){
                SpecialMonthBasicRentResult firstMonthBasicRentResult = new SpecialMonthBasicRentResult.Builder()
                        .rentMonthMode(RentMonthMode.FIRSTMONTH)
                        .planPayDate(b.getPlanPayDate())
                        .planPayCountPre(b.getPlanPayCountPre())
                        .planPayCountPost(b.getPlanPayCountPost())
                        .calFormula(b.getCalFormula())
                        .taxRate(b.getTaxRate())
                        .build();
                result.setFirstMonthBasicRentResult(firstMonthBasicRentResult);
            }
            if(b.getRentMonthMode() == RentMonthMode.EXTENDMONTH){
                SpecialMonthBasicRentResult extendMonthBasicRentResult = new SpecialMonthBasicRentResult.Builder()
                        .rentMonthMode(RentMonthMode.EXTENDMONTH)
                        .planPayDate(b.getPlanPayDate())
                        .planPayCountPre(b.getPlanPayCountPre())
                        .planPayCountPost(b.getPlanPayCountPost())
                        .calFormula(b.getCalFormula())
                        .taxRate(b.getTaxRate())
                        .build();
                extendMonthRentResults.add(extendMonthBasicRentResult);
            }
            if(b.getRentMonthMode() == RentMonthMode.LASTMONTH){
                SpecialMonthBasicRentResult lastMonthBasicRentResult = new SpecialMonthBasicRentResult.Builder()
                        .rentMonthMode(RentMonthMode.LASTMONTH)
                        .planPayDate(b.getPlanPayDate())
                        .planPayCountPre(b.getPlanPayCountPre())
                        .planPayCountPost(b.getPlanPayCountPost())
                        .calFormula(b.getCalFormula())
                        .taxRate(b.getTaxRate())
                        .build();
                result.setLastMonthBasicRentResult(lastMonthBasicRentResult);
            }

            if(b.getRentMonthMode() == RentMonthMode.NORMALMONTH){
                flag++;
                if(preRentDate == 0){
                    preRentDate = b.getPlanPayDate();
                }
                if(preRentCountPre == 0){
                    preRentCountPre = b.getPlanPayCountPre();
                }
                if(preRentCountPost == 0){
                    preRentCountPost = b.getPlanPayCountPost();
                }
                if(normalTaxRate == 0){
                    normalTaxRate = b.getTaxRate();
                }
            } else if(b.getRentMonthMode() != RentMonthMode.FIRSTMONTH) {
                String normalStr = "从" + DateUtils.datetimeString(preRentDate, "yyyy-MM") + "月到" +
                        DateUtils.datetimeString(DateUtils.convertJodaTime(preRentDate).plusMonths(flag-1).getMillis(), "yyyy-MM") +
                        "月，每月的28号或之后的５个工作日之内返还该月下一个月的基本租金，应返还的基本租金税前为"+preRentCountPre+"元，税率为"+normalTaxRate+
                        "，所以税后应返还金额为"+preRentCountPost+"元;";
                NormalMonthBasicRentResult normalMonthBasicRentResult = new NormalMonthBasicRentResult.Builder()
                        .planPayCountPre(preRentCountPre)
                        .planPayCountPost(preRentCountPost)
                        .startTime(preRentDate)
                        .endTime(DateUtils.convertJodaTime(preRentDate).plusMonths(flag-1).getMillis())
                        .taxRate(normalTaxRate)
                        .calFormula(normalStr)
                        .build();
                normalMonthBasicRentResults.add(normalMonthBasicRentResult);
                flag = 0;
                preRentCountPre = 0;
                preRentCountPost = 0;
                preRentDate = 0;
                normalTaxRate = 0;
            }
        }
        result.setExtendMonthRentResults(extendMonthRentResults);
        result.setNormalMonthBasicRentResults(normalMonthBasicRentResults);
        result.setBasicLedgerDetails(basicLedgers);
        result.setTotal(new BigDecimal(total).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue());
        return result;
    }

    @Override
    @Transactional
    public ReqResult<Contract> createContract(Contract contract) {
        ReqResult<Contract> preCheckResult = preCheckCreate(contract);
        if (!preCheckResult.isSuccess()) {
            return preCheckResult;
        }
        long id = contractDao.createContract(contract);
        Contract newContract = contract.clone();
        newContract.setId(id);
        List<BasicLedger> basicLedgers = basicRentMgr.calBasicRentDetail(id);
        basicLedgerDao.batchCreateBasicLedgers(basicLedgers);
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
//    @Transactional
    @SuppressWarnings("这里应该加声明时事务，以保证出错时能进行回滚，然而加了事务后，只能在一个数据库连接中进行操作，" +
            "但是MerchantHandler和ListMerchant2StringHandler中建立了新的数据库连接，新的连接实际上是查不到值的，所以会出错，" +
            "解决方法是修改Contract类，不要建立Merchant实体与数据库中id的映射.")
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
                contract.setContractVersion("1");
            }
            switch (contract.getContractVersion()){
                case "1.0" :
                case "1" :
                    contract.setContractVersion("1");
                    contract.setProposalId(1L);
                    break;
                case "2" :
                case "2.0" :
                    contract.setContractVersion("2");
                    contract.setProposalId(2L);
                    break;
                case "3" :
                case "3.0" :
                    contract.setContractVersion("3");
                    contract.setProposalId(1L);
                    break;
                case "4" :
                case "4.0" :
                    contract.setContractVersion("4");
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
                    logger.error("can't find proposal[{}]", contract.getProposalId());
                    throw new FinanceRuntimeException(FinanceErrMsg.NAMED_RESOURCE_NOT_CAPABLE, "proposal isn't exist");
                }
                long payStartTime = DateUtils.convertJodaTime(basicRentInfo.getPaybackDate()).plusYears(proposal.getMarketCulLife()).plusDays(1).getMillis();
                long contractTerTime = DateUtils.convertJodaTime(payStartTime).plusYears(proposal.getLeasebackLife() - proposal.getMarketCulLife()).getMillis();
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
            return ReqResultMap.create(false, resultFailed, "部分数据有问题，请确认之后重新导入");
        }
        contractDao.batchCreateContracts(contracts);
        List<BasicLedger> basicLedgerAll = new ArrayList<>();
        for(Contract contract : contracts){
            List<BasicLedger> basicLedgers = basicRentMgr.calBasicRentDetail(contract.getContractNo());
            if(basicLedgers != null && basicLedgers.size() != 0)basicLedgerAll.addAll(basicLedgers);
        }
        basicLedgerDao.batchCreateBasicLedgers(basicLedgerAll);
        return ReqResultMap.success(null, "导入成功");
    }

    private Contract basicRentInfo2Contract(BasicRentInfo basicRentInfo){
        List<Merchant> signer = string2Merchant(basicRentInfo.getSigner(), basicRentInfo.getPhone(), basicRentInfo.getMerchantIdNo(), null, null, null);
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
                .signingMode(SigningMode.desLookup(basicRentInfo.getSigningMode()))
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
                .signingStatus(SigningStatus.desLookup(basicRentInfo.getSigningStatus()))
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
            long payStartTime = DateUtils.convertJodaTime(contract.getPaybackDate()).plusYears(proposal.getMarketCulLife()).plusDays(1).getMillis();
            long contractTerTime = DateUtils.convertJodaTime(payStartTime).plusYears(proposal.getLeasebackLife() - proposal.getMarketCulLife()).getMillis();
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
