package com.company.project.module.app.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.controller.CommonBaseController;
import com.company.project.core.model.QueryRequest;
import com.company.project.module.app.model.AppVersion;
import com.company.project.module.app.service.AppInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 基本信息接口
 *
 * @author Chen
 * @created 2019-09-19-11:30.
 */
@CrossOrigin
@RestController
@RequestMapping("/app/info")
@Api(description = "手机端基本数据接口")
public class AppInfoController extends CommonBaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private AppInfoService appInfoService;

    @PostMapping("/getBaseInfo")
    @ApiOperation(value = "地铁基本数据", notes = "地铁基本数据，线路、站、设备")
    public Result getBaseInfo(@RequestBody QueryRequest request) {
        try {
            Map result = this.appInfoService.findBaseInfoForApp(request.getParams());
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            logger.error("获取基本数据失败", e);
            return ResultGenerator.genFailResult("获取基本数据失败！");
        }
    }

    @PostMapping("/getDeviceInfo")
    @ApiOperation(value = "设备数据", notes = "手机端设备数据")
    public Result getDeviceInfo() {
        try {
            //Map result = this.appInfoService.findDeviceInfoForApp();
            Map result = this.appInfoService.getBaseInfoDataForApp();
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            logger.error("获取设备数据失败", e);
            return ResultGenerator.genFailResult("获取设备数据失败！");
        }
    }

    @PostMapping("/dict")
    @ApiOperation(value = "字典数据", notes = "手机端字典数据")
    public Result getDict(@RequestBody QueryRequest request) {
        try {
            String module = "FAULT_APP";
            Map result = this.appInfoService.findDictForApp(module);
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            logger.error("获取字典数据失败", e);
            return ResultGenerator.genFailResult("获取字典数据失败！");
        }
    }

    @PostMapping("/getVersion")
    @ApiOperation(value = "app版本", notes = "手机端版本")
    public Result getVersion(@RequestBody QueryRequest request, HttpSession session) {
        try {
            String employeeId = super.verifySession(session);
            AppVersion result = this.appInfoService.findVersionForApp(request.getParams());
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            logger.error("获取字典数据失败", e);
            return ResultGenerator.genFailResult("获取字典数据失败！");
        }
    }
    //TODO app版本的维护，增删改

}
