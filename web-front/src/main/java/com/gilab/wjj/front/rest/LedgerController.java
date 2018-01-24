package com.gilab.wjj.front.rest;

import com.gilab.wjj.core.BasicLedgerAgent;
import com.gilab.wjj.core.ContractAgent;
import com.gilab.wjj.exception.FinanceErrMsg;
import com.gilab.wjj.front.utils.RestUtils;
import com.gilab.wjj.persistence.dao.ContractDao;
import com.gilab.wjj.persistence.ext.AjaxErrorMessage;
import com.gilab.wjj.persistence.model.*;
import com.gilab.wjj.util.excel.ExcelDataFormatter;
import com.gilab.wjj.util.excel.ExcelUtils;
import com.gilab.wjj.util.logback.LoggerFactory;
import com.google.common.net.HttpHeaders;
import io.swagger.annotations.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by che on 18/1/24.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
@Api(value = "API - ContractController", description = "台账API详情")
@RestController
@RequestMapping("/api/v1")
public class LedgerController {
    private static final Logger logger = LoggerFactory.getLogger(LedgerController.class);

    @Autowired
    private BasicLedgerAgent basicLedgerMgr;

    @ApiOperation(value = "查询台账", notes = "根据ID查询台账", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "contractId", value = "contractId", dataType = "Long", paramType = "query"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 400, message = "错误请求"),
            @ApiResponse(code = 401, message = "用户未授权"),
            @ApiResponse(code = 403, message = "用户被禁止"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    @ResponseBody
    @RequestMapping(value = "/ledgers/{contractId}", method = { RequestMethod.GET }, produces = "application/json")
    public List<BasicLedger> getLedger(final HttpServletResponse response,
                                    @PathVariable long contractId) throws IOException {
        //TODO
        //登录判断

        //TODO
        //权限判断
        return basicLedgerMgr.getLedger(contractId);
    }


    @ApiOperation(value = "批量导入台账", notes = "批量导入台账支付信息", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ledgers", value = "execl文件", required = true, dataType = "MultipartFile", paramType = "body")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 400, message = "错误请求"),
            @ApiResponse(code = 401, message = "用户未授权"),
            @ApiResponse(code = 403, message = "用户被禁止"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    @ResponseBody
    @RequestMapping(value = "/upload-ledgers", method = { RequestMethod.POST }, produces = "application/json")
    public Map uploadContracts(final HttpServletResponse response,
                                           @RequestParam("ledgers")MultipartFile ledgers) throws Exception {
        //TODO
        //登录判断

        //TODO
        //权限判断
        File f=File.createTempFile("tmp", null);
        ledgers.transferTo(f);
        f.deleteOnExit();
        System.out.println(f.getPath());
        ExcelDataFormatter edf = new ExcelDataFormatter();

        List<BasicLedgerInfo> basicLedgerInfos = new ExcelUtils<>(new BasicLedgerInfo()).readFromFile(null, f);
        basicLedgerMgr.batchUpdateLedgers(basicLedgerInfos);
        HashMap resMap = new HashMap();
        resMap.put("SUCCESS",true);
        return resMap;
//        return RestUtils.getOrSendError(response, contractMgr.batchCreateContracts(basicRentInfos));
    }
}
