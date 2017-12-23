package com.gilab.wjj.front.rest;

import com.gilab.wjj.core.ContractAgent;
import com.gilab.wjj.core.MerchantAgent;
import com.gilab.wjj.front.utils.RestUtils;
import com.gilab.wjj.persistence.model.Contract;
import com.gilab.wjj.persistence.model.Merchant;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by yuankui on 12/23/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
@Api(value = "API - MerchantController", description = "商户API详情")
@RestController
@RequestMapping("/api/v1")
public class MerchantController {
    @Autowired
    private MerchantAgent merchantMgr;

    @ApiOperation(value = "添加商户", notes = "创建单个商户", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "merchant", value = "商户实例", required = true, dataType = "Merchant", paramType = "body")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 400, message = "错误请求"),
            @ApiResponse(code = 401, message = "用户未授权"),
            @ApiResponse(code = 403, message = "用户被禁止"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    @ResponseBody
    @RequestMapping(value = "/merchants", method = { RequestMethod.PUT }, produces = "application/json")
    public Merchant createMerchant(final HttpServletResponse response,
                                   @RequestBody final Merchant merchant) throws IOException {
        //TODO
        //登录判断

        //TODO
        //权限判断
        return RestUtils.getOrSendError(response, merchantMgr.createMerchant(merchant));
    }

    @ApiOperation(value = "查询商户", notes = "根据id查询单个商户", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "merchantId", value = "商户id", required = true, dataType = "long", paramType = "path")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 400, message = "错误请求"),
            @ApiResponse(code = 401, message = "用户未授权"),
            @ApiResponse(code = 403, message = "用户被禁止"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    @ResponseBody
    @RequestMapping(value = "/merchants{merchantId}", method = { RequestMethod.GET }, produces = "application/json")
    public Merchant getMerchant(final HttpServletResponse response,
                                   @PathVariable final long merchantId) throws IOException {
        //TODO
        //登录判断

        //TODO
        //权限判断
        return RestUtils.getOrSendError(response, merchantMgr.getMerchant(merchantId));
    }

    @ApiOperation(value = "查询商户", notes = "根据商户名，商户电话，身份证，商户银行帐号", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "merchantName", value = "商户姓名", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "merchantPhone", value = "商户电话", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "merchantIdNo", value = "商户身份证号", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "bankAccount", value = "商户银行卡号", dataType = "String", paramType = "query"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 400, message = "错误请求"),
            @ApiResponse(code = 401, message = "用户未授权"),
            @ApiResponse(code = 403, message = "用户被禁止"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    @ResponseBody
    @RequestMapping(value = "/merchants", method = { RequestMethod.GET }, produces = "application/json")
    public List<Merchant> getMerchantWithFilter(final HttpServletResponse response,
                                               @RequestParam final String merchantName,
                                                @RequestParam final String merchantPhone,
                                                @RequestParam final String merchantIdNo,
                                                @RequestParam final String bankAccount) throws IOException {
        //TODO
        //登录判断

        //TODO
        //权限判断
        return merchantMgr.getMerchantWithFilter(merchantName, merchantPhone, merchantIdNo, bankAccount);
    }
}
