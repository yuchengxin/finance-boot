package com.gilab.wjj.core;

import com.gilab.wjj.persistence.model.Proposal;
import com.gilab.wjj.persistence.model.ReqResult;
import com.gilab.wjj.persistence.model.SimpleReqResult;

import java.util.List;

/**
 * Created by yuankui on 12/22/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
public interface ProposalAgent extends Agent {

    ReqResult<Proposal> getProposal(long proposalId);

    List<Proposal> getProposalWithFilter();

    ReqResult<Proposal> createProposal(Proposal proposal);

    SimpleReqResult updateProposal(Proposal proposal);

    SimpleReqResult deleteProposal(long proposalId);
}
