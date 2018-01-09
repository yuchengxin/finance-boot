package com.gilab.wjj.front.rest;

import com.gilab.wjj.core.ProposalAgent;
import com.gilab.wjj.front.utils.RestUtils;
import com.gilab.wjj.persistence.model.Proposal;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chejian on 12/23/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
@Api(value = "API - UserController", description = "方案API详情")
@RestController
@RequestMapping("/api/v1")
public class ProposalController {
    @Autowired
    private ProposalAgent proposalMgr;

    @ApiOperation(value = "添加方案", notes = "创建单个方案", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "proposal", value = "方案实例", required = true, dataType = "Proposal", paramType = "body")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 400, message = "错误请求"),
            @ApiResponse(code = 401, message = "用户未授权"),
            @ApiResponse(code = 403, message = "用户被禁止"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    @ResponseBody
    @RequestMapping(value = "/proposal", method = { RequestMethod.PUT }, produces = "application/json")
    public Proposal createProposal(final HttpServletResponse response,
                                   @RequestBody final Proposal proposal) throws IOException {
        return RestUtils.getOrSendError(response, proposalMgr.createProposal(proposal));
    }

    @ApiOperation(value = "删除方案", notes = "根据ID删除单个方案", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户实例", required = true, dataType = "User", paramType = "body")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 400, message = "错误请求"),
            @ApiResponse(code = 401, message = "用户未授权"),
            @ApiResponse(code = 403, message = "用户被禁止"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    @ResponseBody
    @RequestMapping(value = "/deleteProposal", method = { RequestMethod.PUT }, produces = "application/json")
    public Map deleteUser(final HttpServletResponse response,
                          @RequestParam(name = "proposalId", required = true) final long proposalId) throws IOException {
        proposalMgr.deleteProposal(proposalId);
        Map res = new HashMap();
        res.put("SUCCESS",true);
        return res;
    }

    @ApiOperation(value = "查询方案", notes = "根据id查询单个方案", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "proposalId", value = "方案id", required = true, dataType = "long", paramType = "path")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 400, message = "错误请求"),
            @ApiResponse(code = 401, message = "用户未授权"),
            @ApiResponse(code = 403, message = "用户被禁止"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    @ResponseBody
    @RequestMapping(value = "/proposal{proposalId}", method = { RequestMethod.GET }, produces = "application/json")
    public Proposal getProposal(final HttpServletResponse response,
                                @PathVariable final long proposalId) throws IOException {
        //TODO
        //登录判断

        //TODO
        //权限判断
        return RestUtils.getOrSendError(response, proposalMgr.getProposal(proposalId));
    }

    @ApiOperation(value = "查询方案", notes = "根据方案名", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "proposalName", value = "方案名", dataType = "String", paramType = "query"),
//            @ApiImplicitParam(name = "merchantPhone", value = "方案电话", dataType = "String", paramType = "query"),
//            @ApiImplicitParam(name = "merchantIdNo", value = "方案身份证号", dataType = "String", paramType = "query"),
//            @ApiImplicitParam(name = "bankAccount", value = "方案银行卡号", dataType = "String", paramType = "query"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 400, message = "错误请求"),
            @ApiResponse(code = 401, message = "用户未授权"),
            @ApiResponse(code = 403, message = "用户被禁止"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    @ResponseBody
    @RequestMapping(value = "/proposal", method = { RequestMethod.GET }, produces = "application/json")
    public List<Proposal> getProposalWithFilter(final HttpServletResponse response,
                                                @RequestParam(name = "proposalName", required = false) final String proposalName) throws IOException {
        return proposalMgr.getProposalWithFilter();
    }
}
