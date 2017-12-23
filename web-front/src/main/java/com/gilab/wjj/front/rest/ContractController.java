package com.gilab.wjj.front.rest;

import com.gilab.wjj.core.ContractAgent;
import com.gilab.wjj.front.utils.RestUtils;
import com.gilab.wjj.persistence.model.Contract;
import com.gilab.wjj.util.logback.LoggerFactory;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yuankui on 12/21/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
@Api(value = "API - ContractController", description = "合同API详情")
@RestController
@RequestMapping("/api/v1")
public class ContractController {
    private static final Logger logger = LoggerFactory.getLogger(ContractController.class);

    @Autowired
    private ContractAgent contractMgr;

    @ApiOperation(value = "创建合同", notes = "创建单个合同", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "contract", value = "合同实例", required = true, dataType = "Contract", paramType = "body")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 400, message = "错误请求"),
            @ApiResponse(code = 401, message = "用户未授权"),
            @ApiResponse(code = 403, message = "用户被禁止"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    @ResponseBody
    @RequestMapping(value = "/contracts", method = { RequestMethod.PUT }, produces = "application/json")
    public Contract createContract(final HttpServletResponse response,
                               @RequestBody Contract contract) throws IOException {
        //TODO
        //登录判断

        //TODO
        //权限判断
        return RestUtils.getOrSendError(response, contractMgr.createContract(contract));
    }

    @ApiOperation(value = "查询合同", notes = "查询单个合同", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "contractId", value = "合同id", required = true, dataType = "long", paramType = "path")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 400, message = "错误请求"),
            @ApiResponse(code = 401, message = "用户未授权"),
            @ApiResponse(code = 403, message = "用户被禁止"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    @ResponseBody
    @RequestMapping(value = "/contracts/{contractId}", method = { RequestMethod.GET }, produces = "application/json")
    public Contract getContract(final HttpServletResponse response,
                               @PathVariable long contractId) throws IOException {
        //TODO
        //登录判断

        //TODO
        //权限判断
        return RestUtils.getOrSendError(response, contractMgr.getContract(contractId));
    }
}
