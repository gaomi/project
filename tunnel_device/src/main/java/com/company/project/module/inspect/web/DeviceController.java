package com.company.project.module.inspect.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.controller.BaseController;
import com.company.project.core.model.QueryRequest;
import com.company.project.module.inspect.service.DeviceService;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @author Chen
 * @created 2019-11-18-14:02.
 */
@RestController
@RequestMapping("/module/device")
@Api(description = "设备管理")
public class DeviceController extends BaseController {

    @Autowired
    private DeviceService deviceService;


    @GetMapping("/faultType")
    @ApiOperation(value = "访问路径", notes = "设备故障分类管理请求地址")
    public ModelAndView typeIndex() {
        ModelAndView mv = new ModelAndView("module/inspect/device/faultType/list");
        return mv;
    }

    @GetMapping("/level")
    @ApiOperation(value = "访问路径", notes = "设备层级管理页面请求地址")
    public ModelAndView levelIndex() {
        ModelAndView mv = new ModelAndView("module/inspect/device/level/list");
        return mv;
    }

    @GetMapping("/list")
    @ApiOperation(value = "设备列表", notes = "设备列表页面跳转")
    public ModelAndView getIndex() {
        ModelAndView mv = new ModelAndView("module/inspect/device/list");
        return mv;
    }

    @GetMapping("/toEdit")
    @ApiOperation(value = "设备管理编辑页面", notes = "获取设备管理编辑页面")
    public ModelAndView toEdit(){
        ModelAndView mv = new ModelAndView("module/inspect/device/detail");
        return mv;
    }

    @PostMapping("/faultType/list")
    @ApiOperation(value = "列表", notes = "设备故障分类列表")
    public Result typeList(@RequestBody QueryRequest request) {
        Map<String, Object> listData = super.selectByPageNumSize(request, () -> this.deviceService.findAllDeviceFaultType(request.getParams()));
        return ResultGenerator.genSuccessResult(listData);
    }

    @PostMapping("/level/treelist")
    @ApiOperation(value = "列表", notes = "设备分类列表")
    public Result levelList(@RequestBody QueryRequest request) {
        List listData = this.deviceService.findAllDeviceLevel(request.getParams());
        return ResultGenerator.genSuccessResult(listData);
    }

    @PostMapping("/dict")
    @ApiOperation(value = "字典", notes = "设备故障分类字典")
    public Result dict() {
        Map<String, Object> listData = deviceService.findDeviceFaultTypeDict();
        return ResultGenerator.genSuccessResult(listData);
    }

    @PostMapping("/detail")
    @ApiOperation(value = "查看设备详情", notes = "查看设备详情")
    public Result detail(String id) {
        Map param = Maps.newHashMap();
        param.put("equipNo",id);
        List allDevice = deviceService.findAllDevice(param);
        if(allDevice.size() > 0){
            return ResultGenerator.genSuccessResult(allDevice.get(0));
        }else{
            return ResultGenerator.genSuccessResult("查询失败");
        }

    }

    @PostMapping("/list")
    @ApiOperation(value = "设备数据", notes = "设备列表数据")
    public Result list(@RequestBody QueryRequest request) {
        Map<String, Object> listData = super.selectByPageNumSize(request, () -> this.deviceService.findAllDevice(request.getParams()));
        return ResultGenerator.genSuccessResult(listData);
    }


}
