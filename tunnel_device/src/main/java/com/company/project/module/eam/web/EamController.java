package com.company.project.module.eam.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.controller.BaseController;
import com.company.project.module.eam.service.EamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by paodingsoft.chen on 2019/05/27.
 */
@RestController
@RequestMapping("/webservice/eam")
@Api(description = "EAM工单系统webservice操作")
public class EamController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EamService eamService;

    /**
     * 获取EAM数据
     */
    @PostMapping("/getEamData")
    @ApiOperation(value = "EAM接口获取数据", notes = "获取EAM数据，type取值人员Person，部门Depart，设备Equip，设备、故障树Fault，工单WorkOrder")
    public Result getEamData(String type) {
        try {
            eamService.saveEamData(type);
        /* eamService.saveEamData("Depart");
        eamService.saveEamData("Equip");
        eamService.saveEamData("Fault");
        eamService.saveEamData("WorkOrder");*/
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("获取EAM数据失败！", e);
            return ResultGenerator.genFailResult("获取EAM数据失败！");
        }

    }


    /**
     * 部门
     */
    @PostMapping("/getDepts")
    @ApiOperation(value = "获取部门", notes = "获取部门")
    public Result getDeparts() {
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 设备
     */
    @PostMapping("/getEquips")
    @ApiOperation(value = "获取设备", notes = "获取设备")
    public Result getEquips() {
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 设备、故障树
     */
    @PostMapping("/getFaults")
    @ApiOperation(value = "获取故障", notes = "获取故障")
    public Result getFaults() {
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 工单修改
     */
    @PostMapping("/getWorkOrders")
    @ApiOperation(value = "获取工单", notes = "获取工单")
    public Result getWorkOrders() {
        return ResultGenerator.genSuccessResult();
    }
}
