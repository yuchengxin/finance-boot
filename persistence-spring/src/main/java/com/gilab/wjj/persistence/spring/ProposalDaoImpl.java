package com.gilab.wjj.persistence.spring;

import com.gilab.wjj.persistence.dao.ProposalDao;
import com.gilab.wjj.persistence.mapper.ProposalMapper;
import com.gilab.wjj.persistence.model.Proposal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yuankui on 12/17/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
@Repository("ProposalDao")
public class ProposalDaoImpl implements ProposalDao {
    @Autowired
    private ProposalMapper mapperr;

    @Override
    public Proposal getProposal(long proposalId) {
        return mapperr.selectProposal(proposalId);
    }

    @Override
    public List<Proposal> getProposalWithFilter() {
        return mapperr.selectProposalWithFilter();
    }

    @Override
    public long createProposal(Proposal proposal) {
        mapperr.insertProposal(proposal);
        return proposal.getId();
    }

    @Override
    public void updateProposal(Proposal proposal) {
        mapperr.updateProposal(proposal);
    }

    @Override
    public void deleteProposal(long proposalId) {
        mapperr.deleteProposal(proposalId);
    }
}
