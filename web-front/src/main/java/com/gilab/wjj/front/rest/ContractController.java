package com.gilab.wjj.front.rest;

import com.gilab.wjj.core.ContractAgent;
import com.gilab.wjj.exception.FinanceErrMsg;
import com.gilab.wjj.front.utils.RestUtils;
import com.gilab.wjj.persistence.dao.ContractDao;
import com.gilab.wjj.persistence.ext.AjaxErrorMessage;
import com.gilab.wjj.persistence.model.*;
import com.gilab.wjj.util.excel.ExcelUtils;
import com.gilab.wjj.util.logback.LoggerFactory;
import com.google.common.net.HttpHeaders;
import io.swagger.annotations.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

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

    @ApiOperation(value = "批量导入合同", notes = "批量导入合同", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "contracts", value = "execl文件", required = true, dataType = "MultipartFile", paramType = "body")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 400, message = "错误请求"),
            @ApiResponse(code = 401, message = "用户未授权"),
            @ApiResponse(code = 403, message = "用户被禁止"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    @ResponseBody
    @RequestMapping(value = "/upload-contracts", method = { RequestMethod.POST }, produces = "application/json")
    public Map<BasicRentInfo, String> uploadContracts(final HttpServletResponse response,
                                           @RequestParam("contracts")MultipartFile contracts) throws Exception {
        //TODO
        //登录判断

        //TODO
        //权限判断
        File f=File.createTempFile("tmp", null);
        contracts.transferTo(f);
        f.deleteOnExit();
        List<BasicRentInfo> basicRentInfos = new ExcelUtils<>(new BasicRentInfo()).readFromFile(null, f);
        return RestUtils.getOrSendError(response, contractMgr.batchCreateContracts(basicRentInfos));
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

    @ApiOperation(value = "查询多个合同及返租明细", notes = "查询多个合同及返租明细", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "contractNo", value = "合同编号", dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "filterStartTime", value = "签约时间段的起始时间", dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "filterEndTime", value = "签约时间段的结束时间", dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "contractVersion", value = "合同版本", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "buildingInfo", value = "楼房信息", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "signMode", value = "签约方式", dataType = "SignMode", paramType = "query"),
            @ApiImplicitParam(name = "contractStatus", value = "合同状态", dataType = "ContractStatus", paramType = "query"),
            @ApiImplicitParam(name = "beneficiaryName", value = "受益人姓名", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "beneficiaryPhone", value = "受益人电话", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "beneficiaryIDNO", value = "受益人身份证号", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "beneficiaryAccount", value = "受益人银行账户", dataType = "String", paramType = "query")
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
    public List<AllBasicRentResult> getContractList(final HttpServletResponse response,
                                    @RequestParam(name = "contractNo", required = false) String contractNo,
                                    @RequestParam(name = "filterStartTime", required = false) Long filterStartTime,
                                    @RequestParam(name = "filterEndTime", required = false) Long filterEndTime,
                                    @RequestParam(name = "contractVersion", required = false) String contractVersion,
                                    @RequestParam(name = "buildingInfo", required = false) String buildingInfo,
                                    @RequestParam(name = "signMode", required = false) SigningMode signMode,
                                    @RequestParam(name = "contractStatus", required = false) ContractStatus contractStatus,
                                    @RequestParam(name = "beneficiaryName", required = false) String beneficiaryName,
                                    @RequestParam(name = "beneficiaryPhone", required = false) String beneficiaryPhone,
                                    @RequestParam(name = "beneficiaryIDNO", required = false) String beneficiaryIDNO,
                                    @RequestParam(name = "beneficiaryAccount", required = false) String beneficiaryAccount
                                                 ) throws IOException {
        //TODO
        //登录判断

        //TODO
        //权限判断
        return contractMgr.getContractAndCalResultWithFilter(contractNo, filterStartTime, filterEndTime, contractVersion,
                buildingInfo, signMode, contractStatus, beneficiaryName, beneficiaryPhone, beneficiaryIDNO, beneficiaryAccount);
    }

    @ApiOperation(value = "导出返租基础信息表", notes = "导出返租基础信息表", produces = "application/octet-stream")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "filterStartTime", value = "签约时间段的起始时间", dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "filterEndTime", value = "签约时间段的结束时间", dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "contractVersion", value = "合同版本", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "buildingInfo", value = "楼房信息", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "signMode", value = "签约方式", dataType = "SignMode", paramType = "query"),
            @ApiImplicitParam(name = "contractStatus", value = "合同状态", dataType = "ContractStatus", paramType = "query")
    })
    @ResponseBody
    @RequestMapping(value = "/download-contracts", method = { RequestMethod.GET }, produces = "application/octet-stream")
    public ResponseEntity<Resource> downloadContracts(HttpServletResponse response,
                                                     @RequestParam(name = "filterStartTime", required = false) Long filterStartTime,
                                                     @RequestParam(name = "filterEndTime", required = false) Long filterEndTime,
                                                     @RequestParam(name = "contractVersion", required = false) String contractVersion,
                                                     @RequestParam(name = "buildingInfo", required = false) String buildingInfo,
                                                     @RequestParam(name = "signMode", required = false) SigningMode signMode,
                                                     @RequestParam(name = "contractStatus", required = false) ContractStatus contractStatus) throws Exception {
        // 检查权限
        List<Contract> contracts = contractDao.getContractWithFilter(filterStartTime, filterEndTime, contractVersion,
                buildingInfo, signMode, contractStatus);
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
