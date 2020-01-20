package com.company.project.module.inspect.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.controller.BaseController;
import com.company.project.core.model.QueryRequest;
import com.company.project.module.data.model.TdDict;
import com.company.project.module.inspect.model.SiFaultData;
import com.company.project.module.inspect.service.FaultLogService;
import com.company.project.module.inspect.service.SiFaultDataService;
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
@RequestMapping("/module/fault/log")
@Api(description = "病害记录")
public class FaultLogController extends BaseController {

    @Autowired
    private FaultLogService faultLogService;
    @Autowired
    private SiFaultDataService siFaultDataService;

    @GetMapping
    @ApiOperation(value = "访问路径", notes = "病害记录管理请求地址")
    public ModelAndView typeIndex() {
        ModelAndView mv = new ModelAndView("module/inspect/fault/log/list");
        return mv;
    }

    @PostMapping("/list")
    @ApiOperation(value = "列表", notes = "病害记录列表")
    public Result typeList(@RequestBody QueryRequest request) {
        Map<String, Object> listData = super.selectByPageNumSize(request, () -> this.faultLogService.findAllFaultLog(request.getParams()));
        return ResultGenerator.genSuccessResult(listData);
    }
    @GetMapping("/toEdit")
    @ApiOperation(value = "病害管理编辑页面", notes = "获取病害管理编辑页面")
    public ModelAndView toEdit(){
        ModelAndView mv = new ModelAndView("module/inspect/fault/log/edit");
        return mv;
    }

    @GetMapping("/toLvli")
    @ApiOperation(value = "病害管理履历页面", notes = "获取病害管理履历页面")
    public ModelAndView toLvli(){
        ModelAndView mv = new ModelAndView("module/inspect/fault/log/lvli");
        return mv;
    }
    @PostMapping("/detail")
    @ApiOperation(value = "详情", notes = "病害记录详情")
    public Result detail(@RequestParam String id) {
        SiFaultData byId = this.siFaultDataService.findById(id);
        return ResultGenerator.genSuccessResult(byId);
    }

    @GetMapping("/getFaultStatus")
    @ApiOperation(value = "字典", notes = "获取病害状态字典")
    public Result getFaultStatus() {
        List<TdDict> byId = this.siFaultDataService.getFaultStatus();
        return ResultGenerator.genSuccessResult(byId);
    }

    @PostMapping("/record")
    @ApiOperation(value = "履历", notes = "履历记录详情")
    public Result record(@RequestParam String code) {
        List<SiFaultData> byId = this.siFaultDataService.findRecord(code);
        return ResultGenerator.genSuccessResult(byId);
    }


}
