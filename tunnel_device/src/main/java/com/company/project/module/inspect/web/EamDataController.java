package com.company.project.module.inspect.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.annotation.Log;
import com.company.project.core.controller.BaseController;
import com.company.project.core.model.QueryRequest;
import com.company.project.module.inspect.service.EamPersonService;
import com.company.project.module.inspect.service.EamWorkOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by paodingsoft.chen on 2019/05/27.
 */
@RestController
@RequestMapping("/module/eam")
@Api(description = "EAM工单系统操作")
public class EamDataController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EamPersonService eamPersonService;
    @Autowired
    private EamWorkOrderService eamWorkOrderService;

    @GetMapping("/person")
    @ApiOperation(value = "EAM人员页面", notes = "EAM人员页面跳转")
    public ModelAndView getPerson() {
        ModelAndView mv = new ModelAndView("module/inspect/eam/person");
        return mv;
    }

    @GetMapping("/workOrder")
    @ApiOperation(value = "EAM工单页面", notes = "EAM工单页面跳转")
    public ModelAndView getWorkOrder() {
        ModelAndView mv = new ModelAndView("module/inspect/eam/workOrder/list");
        return mv;
    }


    @PostMapping("/person/list")
    @ApiOperation(value = "获取人员", notes = "获取人员")
    public Result getPersonList(@RequestBody QueryRequest request) {
        try {
            Map<String, Object> result = super.selectByPageNumSize(request, () -> this.eamPersonService.selectPersonData(request.getParams()));
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            logger.error("查询人员数据信息失败", e);
            return ResultGenerator.genFailResult("查询人员数据信息失败！");
        }
    }

    @PostMapping("/person/getAllFromEam")
    @ApiOperation(value = "更新全部", notes = "调用接口更新全部人员")
    public Result getAllFromEam() {
        try {
            eamPersonService.getAllFromEam();
            return ResultGenerator.genSuccessResult("更新成功");
        } catch (Exception e) {
            logger.error("更新人员数据信息失败", e);
            return ResultGenerator.genFailResult("更新人员数据信息失败");
        }
    }

    @Log("重置eam人员密码")
    @PostMapping("/person/resetPassWord")
    @ApiOperation(value = "重置密码", notes = "重置人员密码")
    public Result updatePassWord(@RequestParam String id) {
        try {
            eamPersonService.resetPassWordById(id);
            return ResultGenerator.genSuccessResult("重置成功");
        } catch (Exception e) {
            logger.error("重置人员密码失败", e);
            return ResultGenerator.genFailResult("重置人员密码失败");
        }
    }


    @PostMapping("/workOrder/list")
    @ApiOperation(value = "获取工单", notes = "获取工单列表")
    public Result getWorkOrderList(@RequestBody QueryRequest request) {
        try {
            Map<String, Object> result = super.selectByPageNumSize(request, () -> this.eamWorkOrderService.selectWorkOrderList(request.getParams()));
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            logger.error("查询工单数据信息失败", e);
            return ResultGenerator.genFailResult("查询工单数据信息失败！");
        }
    }

    @GetMapping("/workOrder/toWorkOrderCheck")
    @ApiOperation(value = "EAM工单审核弹窗", notes = "EAM工单审核弹窗")
    public ModelAndView toWorkOrderCheckDialog() {
        ModelAndView mv = new ModelAndView("module/inspect/eam/workOrder/checkDialog");
        return mv;
    }

}
