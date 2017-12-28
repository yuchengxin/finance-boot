package com.gilab.wjj.front.rest;

import com.gilab.wjj.core.ContractAgent;
import com.gilab.wjj.exception.FinanceErrMsg;
import com.gilab.wjj.front.utils.RestUtils;
import com.gilab.wjj.persistence.dao.ContractDao;
import com.gilab.wjj.persistence.ext.AjaxErrorMessage;
import com.gilab.wjj.persistence.model.BasicRentInfo;
import com.gilab.wjj.persistence.model.Contract;
import com.gilab.wjj.persistence.model.ContractStatus;
import com.gilab.wjj.persistence.model.SigningMode;
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

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

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

    @Autowired
    private ContractDao contractDao;

    @Autowired
    private ResourceLoader resourceLoader;

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

    @ApiOperation(value = "查询多个合同", notes = "查询合同", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "filterStartTime", value = "签约时间段的起始时间", dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "filterEndTime", value = "签约时间段的结束时间", dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "contractVersion", value = "合同版本", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "buildingStartSize", value = "建筑最小面积", dataType = "Double", paramType = "query"),
            @ApiImplicitParam(name = "buildingEndSize", value = "建筑最大面积", dataType = "Double", paramType = "query"),
            @ApiImplicitParam(name = "signMode", value = "签约方式", dataType = "SignMode", paramType = "query"),
            @ApiImplicitParam(name = "contractStatus", value = "合同状态", dataType = "ContractStatus", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 400, message = "错误请求"),
            @ApiResponse(code = 401, message = "用户未授权"),
            @ApiResponse(code = 403, message = "用户被禁止"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    @ResponseBody
    @RequestMapping(value = "/contracts", method = { RequestMethod.GET }, produces = "application/json")
    public List<Contract> getContractList(final HttpServletResponse response,
                                    @RequestParam(name = "filterStartTime", required = false) Long filterStartTime,
                                    @RequestParam(name = "filterEndTime", required = false) Long filterEndTime,
                                    @RequestParam(name = "contractVersion", required = false) String contractVersion,
                                    @RequestParam(name = "buildingStartSize", required = false) Double buildingStartSize,
                                    @RequestParam(name = "buildingEndSize", required = false) Double buildingEndSize,
                                    @RequestParam(name = "signMode", required = false) SigningMode signMode,
                                    @RequestParam(name = "contractStatus", required = false) ContractStatus contractStatus) throws IOException {
        //TODO
        //登录判断

        //TODO
        //权限判断
        return contractMgr.getContractWithFilter(filterStartTime, filterEndTime, contractVersion, buildingStartSize,
                buildingEndSize, signMode, contractStatus);
    }

    @ApiOperation(value = "导出返租基础信息表", notes = "导出返租基础信息表", produces = "application/octet-stream")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "filterStartTime", value = "签约时间段的起始时间", dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "filterEndTime", value = "签约时间段的结束时间", dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "contractVersion", value = "合同版本", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "buildingStartSize", value = "建筑最小面积", dataType = "Double", paramType = "query"),
            @ApiImplicitParam(name = "buildingEndSize", value = "建筑最大面积", dataType = "Double", paramType = "query"),
            @ApiImplicitParam(name = "signMode", value = "签约方式", dataType = "SignMode", paramType = "query"),
            @ApiImplicitParam(name = "contractStatus", value = "合同状态", dataType = "ContractStatus", paramType = "query")
    })
    @ResponseBody
    @RequestMapping(value = "/download-contracts", method = { RequestMethod.GET }, produces = "application/octet-stream")
    public ResponseEntity<Resource> downloadContracts(HttpServletResponse response,
                                                     @RequestParam(name = "filterStartTime", required = false) Long filterStartTime,
                                                     @RequestParam(name = "filterEndTime", required = false) Long filterEndTime,
                                                     @RequestParam(name = "contractVersion", required = false) String contractVersion,
                                                     @RequestParam(name = "buildingStartSize", required = false) Double buildingStartSize,
                                                     @RequestParam(name = "buildingEndSize", required = false) Double buildingEndSize,
                                                     @RequestParam(name = "signMode", required = false) SigningMode signMode,
                                                     @RequestParam(name = "contractStatus", required = false) ContractStatus contractStatus) throws Exception {
        // 检查权限
        List<Contract> contracts = contractDao.getContractWithFilter(filterStartTime, filterEndTime, contractVersion,
                buildingStartSize, buildingEndSize, signMode, contractStatus);
        if (contracts == null || contracts.size() == 0) {
            return RestUtils.sendError(response, HttpServletResponse.SC_NOT_FOUND,
                    new AjaxErrorMessage(FinanceErrMsg.INFO_LOOKUP_MISS.getErrCode(), "合同都不存在"));
        }

        //TODO
        //权限判断
        List<BasicRentInfo> rentInfos = new ArrayList<>();

        for(Contract contract : contracts){
            rentInfos.add(contract.contract2BasicRentInfo());
        }

        Workbook wb = ExcelUtils.getWorkBook(rentInfos, null);

        String prefix = "Contract_" + UUID.randomUUID().toString();
        File schemeFile = File.createTempFile(prefix, ".xlsx");
        schemeFile.deleteOnExit();

        FileOutputStream out = new FileOutputStream(schemeFile);
        wb.write(out);
        out.close();
        String sendFileName = prefix + ".xlsx";
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + sendFileName +"\"")
                .body(resourceLoader.getResource("file:" + schemeFile.getAbsolutePath()));
    }



}
