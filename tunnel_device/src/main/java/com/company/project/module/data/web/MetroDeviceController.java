package com.company.project.module.data.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.controller.BaseController;
import com.company.project.core.model.QueryRequest;
import com.company.project.module.data.model.MetroDevice;
import com.company.project.module.data.service.MetroDeviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by paodingsoft.chen on 2019/09/22.
 */
@RestController
@RequestMapping("/module/data/device")
@Api(description = "地铁设备管理")
public class MetroDeviceController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private MetroDeviceService metroDeviceService;

    @GetMapping
    @RequiresPermissions("device:list")
    @ApiOperation(value = "访问路径", notes = "设备查询请求地址")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("module/data/device/list");
        return mv;
    }

    @PostMapping("/list")
    @ApiOperation(value = "列表", notes = "地铁设备列表")
    public Result list(@RequestBody QueryRequest request) {
        Map<String, Object> listData = super.selectByPageNumSize(request, () -> this.metroDeviceService.findAllDevice(request.getParams()));
        return ResultGenerator.genSuccessResult(listData);
    }

    @GetMapping("/toEdit")
    @ApiOperation(value = "设备编辑页面", notes = "获取设备编辑页面")
    public ModelAndView toEdit(){
        ModelAndView mv = new ModelAndView("module/data/device/edit");
        return mv;
    }

    @PostMapping("/detail")
    @ApiOperation(value = "明细", notes = "地铁设备详细数据")
    public Result detail(@RequestParam String id) {
        MetroDevice metroDevice = metroDeviceService.findById(id);
        return ResultGenerator.genSuccessResult(metroDevice);
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增", notes = "地铁设备新增")
    public Result add(MetroDevice metroDevice) {
        try {
            metroDeviceService.save(metroDevice);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("新增地铁设备信息失败", e);
            return ResultGenerator.genFailResult("新增地铁设备信息失败！");
        }
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "地铁设备删除")
    public Result delete(@RequestParam String id) {
        try {
            metroDeviceService.deleteById(id);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("删除地铁设备信息失败", e);
            return ResultGenerator.genFailResult("删除地铁设备信息失败！");
        }
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改", notes = "地铁设备修改")
    public Result update(MetroDevice metroDevice) {
        try {
            metroDeviceService.update(metroDevice);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("修改地铁设备信息失败", e);
            return ResultGenerator.genFailResult("修改地铁设备信息失败！");
        }

    }


}
