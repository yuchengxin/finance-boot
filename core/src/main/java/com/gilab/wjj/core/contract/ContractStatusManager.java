package com.gilab.wjj.core.contract;

import com.gilab.wjj.exception.FinanceErrMsg;
import com.gilab.wjj.exception.FinanceRuntimeException;
import com.gilab.wjj.persistence.dao.ContractDao;
import com.gilab.wjj.persistence.dao.ProposalDao;
import com.gilab.wjj.persistence.model.Contract;
import com.gilab.wjj.persistence.model.ContractStatus;
import com.gilab.wjj.persistence.model.Proposal;
import com.gilab.wjj.util.DateUtils;
import com.gilab.wjj.util.logback.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuankui on 12/29/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
@Service
public class ContractStatusManager implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(ContractStatusManager.class);

    @Autowired
    private ContractDao contractDao;

    @Autowired
    private ProposalDao proposalDao;

    @Override
    public void afterPropertiesSet() throws Exception {
//        calibrationStatus();
    }

    @Transactional
    @Scheduled(cron = "0 0 0 * * ?")
    private void calibrationStatus(){
        logger.info("start update status of contracts");
        List<Contract> contracts = contractDao.getContractWithFilter(null, null, null, null, null, null);
        if(contracts == null || contracts.size() == 0){
            logger.warn("there are no contracts");
            return;
        }
        List<Contract> needUpdate = new ArrayList<>();
        for(Contract contract : contracts){
            contract.setContractStatus(calCurrentStatus(contract));
            needUpdate.add(contract);
        }
        contractDao.batchUpdateContractStatus(needUpdate);
        logger.info("end update status of contracts");
    }

    private ContractStatus calCurrentStatus(Contract contract){
        if(contract.getContractNo() == null || contract.getPaybackDate() == null || contract.getPaybackDate() <= 0
                || contract.getLeasebackPrice() == null || contract.getLeasebackPrice() <= 0
                || contract.getProposalId() == null || contract.getProposalId() <= 0 || proposalDao.getProposal(contract.getProposalId()) == null
                || contract.getBeneficiary() == null || contract.getBeneficiary().getMerchantName() == null
                || contract.getBeneficiary().getMerchantIdNo() == null || contract.getBeneficiary().getBankInfo() == null
                || contract.getBeneficiary().getBankAccount() == null){
            return ContractStatus.UNSTARTED;
        }
        Proposal proposal = proposalDao.getProposal(contract.getProposalId());
        if(proposal == null){
            logger.error("can't find proposal[d%]", contract.getProposalId());
            throw new FinanceRuntimeException(FinanceErrMsg.NAMED_RESOURCE_NOT_CAPABLE, "proposal isn't exist");
        }
        long payStartTime = DateUtils.convertJodaTime(contract.getPaybackDate()).plusYears(proposal.getMarketCulLife()).plusDays(1).getMillis();
        long currentTime = System.currentTimeMillis();
        if(contract.getContractStatus() != ContractStatus.UNSTARTED
                && contract.getContractStatus() != ContractStatus.NORMALEND
                && contract.getContractStatus() != ContractStatus.ABNORMALEND){
            if(currentTime < payStartTime && currentTime > contract.getPaybackDate()){
                return ContractStatus.PENDINGRENTAL;
            }else if(currentTime >= payStartTime && currentTime < contract.getContractTerDate()){
                return ContractStatus.RENTAL;
            }else if(currentTime >= contract.getContractTerDate()){
                return ContractStatus.NORMALEND;
            }
        }
        return contract.getContractStatus();
    }


}
