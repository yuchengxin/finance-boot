package com.gilab.wjj.persistence.dao;

import com.gilab.wjj.persistence.model.Merchant;
import com.gilab.wjj.persistence.model.Proposal;

import java.util.List;

/**
 * Created by yuankui on 12/17/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
public interface ProposalDao {

    Proposal getProposal(long proposalId);

    List<Proposal> getProposalWithFilter();

    long createProposal(Proposal proposal);

    void updateProposal(Proposal proposal);

    void deleteProposal(long proposalId);

}
