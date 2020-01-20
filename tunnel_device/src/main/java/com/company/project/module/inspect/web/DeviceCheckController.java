package com.company.project.module.inspect.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.controller.BaseController;
import com.company.project.core.model.QueryRequest;
import com.company.project.module.inspect.service.DeviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author Chen
 * @created 2019-11-18-14:02.
 */
@RestController
@RequestMapping("/module/device/check")
@Api(description = "设备必检项审核")
public class DeviceCheckController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DeviceService deviceService;

    @PostMapping("/list")
    @ApiOperation(value = "设备数据", notes = "设备列表数据")
    public Result list(@RequestBody QueryRequest request) {
        Map<String, Object> listData = super.selectByPageNumSize(request, () -> this.deviceService.findCheckDevice(request.getParams()));
        return ResultGenerator.genSuccessResult(listData);
    }

    @GetMapping("/toEdit")
    @ApiOperation(value = "设备检查项页面", notes = "获取设备检查项页面")
    public ModelAndView toEdit(){
        ModelAndView mv = new ModelAndView("module/inspect/device/edit");
        return mv;
    }

    @PostMapping("/checkItem")
    @ApiOperation(value = "设备检查项", notes = "设备检查项数据")
    public Result checkItem(@RequestBody QueryRequest request) {
        Map<String, Object> listData = this.deviceService.findcheckItem(request.getParams());
        return ResultGenerator.genSuccessResult(listData);
    }

    @PostMapping("/getDict")
    @ApiOperation(value = "设备检查项", notes = "设备检查项数据")
    public Result getDict(@RequestBody QueryRequest request) {
        Map<String, Object> listData = this.deviceService.getDict(request.getParams());
        return ResultGenerator.genSuccessResult(listData);
    }

}
