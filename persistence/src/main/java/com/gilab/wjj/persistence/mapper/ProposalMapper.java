package com.gilab.wjj.persistence.mapper;

import com.gilab.wjj.persistence.model.Proposal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yuankui on 12/17/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
@Mapper
public interface ProposalMapper {
    Proposal selectProposal(@Param("id") long proposalId);

    List<Proposal> selectProposalWithFilter();

    void insertProposal(Proposal proposal);

    void updateProposal(Proposal proposal);

    void deleteProposal(@Param("id") long proposalId);
}
