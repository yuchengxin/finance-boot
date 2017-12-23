package com.gilab.wjj.core.proposal;

import com.gilab.wjj.core.ProposalAgent;
import com.gilab.wjj.persistence.dao.ProposalDao;
import com.gilab.wjj.persistence.model.Proposal;
import com.gilab.wjj.persistence.model.ReqResult;
import com.gilab.wjj.persistence.model.SimpleReqResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yuankui on 12/22/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
@Service("proposalAgent")
public class ProposalManager implements ProposalAgent {

    @Autowired
    private ProposalDao proposalDao;

    @Override
    public ReqResult<Proposal> getProposal(long proposalId) {
        Proposal proposal = proposalDao.getProposal(proposalId);
        if (proposal == null) {
            return ReqResult.fail("Cannot find proposal[%d]", proposalId);
        } else {
            return ReqResult.success(proposal, "proposal found.");
        }
    }

    @Override
    public List<Proposal> getProposalWithFilter() {
        return null;
    }

    @Override
    public ReqResult<Proposal> createProposal(Proposal proposal) {
        long id = proposalDao.createProposal(proposal);
        Proposal newProposal = proposal.clone();
        newProposal.setId(id);
        return ReqResult.success(newProposal, "Proposal[%d] created.", id);
    }

    @Override
    public SimpleReqResult updateProposal(Proposal proposal) {
        Proposal origProposal = proposalDao.getProposal(proposal.getId());
        if (origProposal == null) return SimpleReqResult.fail("Cannot find Proposal[%d]", proposal.getId());
        proposalDao.updateProposal(proposal);
        return SimpleReqResult.success("Proposal[%d] updated.", proposal.getId());
    }

    @Override
    public SimpleReqResult deleteProposal(long proposalId) {
        Proposal proposal = proposalDao.getProposal(proposalId);
        if (proposal == null) return SimpleReqResult.fail("Cannot find proposal[%d]", proposalId);
        proposalDao.deleteProposal(proposalId);
        return SimpleReqResult.success("proposal[%d] removed.", proposalId);
    }
}
