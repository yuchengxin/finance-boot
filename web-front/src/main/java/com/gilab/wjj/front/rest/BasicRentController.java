package com.gilab.wjj.front.rest;

import com.gilab.wjj.core.BasicRentAgent;
import com.gilab.wjj.core.ContractAgent;
import com.gilab.wjj.front.utils.RestUtils;
import com.gilab.wjj.persistence.model.BasicRentMonthResult;
import com.gilab.wjj.persistence.model.BasicRentPeriodResult;
import com.gilab.wjj.persistence.model.BasicRentResult;
import com.gilab.wjj.persistence.model.BasicRentYearResult;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yuankui on 12/22/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
@Api(value = "API - BasicRentController", description = "基本租金API详情")
@RestController
@RequestMapping("/api/v1")
public class BasicRentController {
    @Autowired
    private BasicRentAgent basicRentMgr;

    @Autowired
    private ContractAgent contractMgr;

    @ApiOperation(value = "计算基本租金", notes = "基本返租租金明细计算", produces = "application/json")
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
    @RequestMapping(value = "/cal_basic_rent/{contractId}", method = { RequestMethod.GET }, produces = "application/json")
    public BasicRentResult calBasicRentDetails(final HttpServletResponse response,
                                          @PathVariable long contractId) throws IOException {
        //TODO
        //登录判断

        //TODO
        //权限判断
        return RestUtils.getOrSendError(response, basicRentMgr.calBasicRentDetail(contractId));
    }

    @ApiOperation(value = "计算某一期基本租金", notes = "某一期基本返租租金明细的计算", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "contractId", value = "合同id", required = true, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "period", value = "某一期", required = true, dataType = "int", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 400, message = "错误请求"),
            @ApiResponse(code = 401, message = "用户未授权"),
            @ApiResponse(code = 403, message = "用户被禁止"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    @ResponseBody
    @RequestMapping(value = "/cal_period_basic_rent", method = { RequestMethod.GET }, produces = "application/json")
    public BasicRentPeriodResult calPeriodBasicRent(final HttpServletResponse response,
                                                    @RequestParam final long contractId,
                                                    @RequestParam final int period) throws IOException {
        //TODO
        //登录判断

        //TODO
        //权限判断
        return RestUtils.getOrSendError(response, basicRentMgr.calBasicRentPeriod(contractId, period));
    }

    @ApiOperation(value = "计算某个月基本租金", notes = "某个月基本返租租金明细的计算", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "contractId", value = "合同id", required = true, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "date", value = "日期,精确到月", required = true, dataType = "long", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 400, message = "错误请求"),
            @ApiResponse(code = 401, message = "用户未授权"),
            @ApiResponse(code = 403, message = "用户被禁止"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    @ResponseBody
    @RequestMapping(value = "/cal_month_basic_rent", method = { RequestMethod.GET }, produces = "application/json")
    public BasicRentMonthResult calMonthBasicRent(final HttpServletResponse response,
                                                  @RequestParam final long contractId,
                                                  @RequestParam final long date) throws IOException {
        //TODO
        //登录判断

        //TODO
        //权限判断
        return RestUtils.getOrSendError(response, basicRentMgr.calBasicRentMonth(contractId, date));
    }

    @ApiOperation(value = "计算某一年基本租金", notes = "某一年基本返租租金明细的计算", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "contractId", value = "合同id", required = true, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "year", value = "年份", required = true, dataType = "int", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 400, message = "错误请求"),
            @ApiResponse(code = 401, message = "用户未授权"),
            @ApiResponse(code = 403, message = "用户被禁止"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    @ResponseBody
    @RequestMapping(value = "/cal_year_basic_rent", method = { RequestMethod.GET }, produces = "application/json")
    public BasicRentYearResult calMonthBasicRent(final HttpServletResponse response,
                                                 @RequestParam final long contractId,
                                                 @RequestParam final int year) throws IOException {
        //TODO
        //登录判断

        //TODO
        //权限判断
        return RestUtils.getOrSendError(response, basicRentMgr.calBasicRentYear(contractId, year));
    }

    @ApiOperation(value = "计算器", notes = "输入回款时间,返租基价和方案来进行预计算", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "paybackDate", value = "回款时间", required = true, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "leasebackPrice", value = "返租基价", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "proposalId", value = "返租基价", required = true, dataType = "long", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 400, message = "错误请求"),
            @ApiResponse(code = 401, message = "用户未授权"),
            @ApiResponse(code = 403, message = "用户被禁止"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    @ResponseBody
    @RequestMapping(value = "/pre_cal_basic_rent", method = { RequestMethod.GET }, produces = "application/json")
    public BasicRentResult preCalBasicRentDetail(final HttpServletResponse response,
                                             @RequestParam final long paybackDate,
                                             @RequestParam final int leasebackPrice,
                                             @RequestParam final long proposalId) throws IOException {
        //TODO
        //登录判断

        //TODO
        //权限判断
        return RestUtils.getOrSendError(response, basicRentMgr.preCalBasicRentDetail(paybackDate, leasebackPrice, proposalId));
    }
}
