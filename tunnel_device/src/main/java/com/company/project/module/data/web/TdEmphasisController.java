package com.company.project.module.data.web;


import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.controller.BaseController;
import com.company.project.core.model.QueryRequest;
import com.company.project.module.data.model.TdEmphasis;
import com.company.project.module.data.service.TdEmphasisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@RequestMapping("/module/data")
@Api(description = "大修信息管理")
@RestController
public class TdEmphasisController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private TdEmphasisService tdEmphasisService;

    @GetMapping("/repair")
    @ApiOperation(value = "访问路径", notes = "大修请求地址")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("module/data/emphasis/list");
        return mv;
    }


    @GetMapping("/emphasis/toEdit")
    @ApiOperation(value = "访问路径", notes = "大修添加修改页面地址")
    public ModelAndView toEdit() {
        ModelAndView mv = new ModelAndView("module/data/emphasis/edit");
        return mv;
    }


    @PostMapping("/emphasis/list")
    @ApiOperation(value = "列表", notes = "大修信息列表")
    public Result list(@RequestBody QueryRequest request) {
        try {
            Map result = super.selectByPageNumSize(request, () -> tdEmphasisService.getEmphasisList(request.getParams()));
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("大修数据获取失败", e);
            return ResultGenerator.genFailResult("大修数据获取失败！");
        }
    }

    @PostMapping("/emphasis/getGroupSegment")
    @ApiOperation(value = "获取风井区间", notes = "获取风井区间信息列表")
    public Result getGroupSegment(@RequestBody QueryRequest request) {
        try {
            List<Map> result = tdEmphasisService.getGroupSegment(request.getParams());
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("大修数据获取失败", e);
            return ResultGenerator.genFailResult("大修数据获取失败！");
        }
    }

    @PostMapping("/emphasis/getSegmentByGroup")
    @ApiOperation(value = "获取风井区间", notes = "获取风井区间信息列表")
    public Result getSegmentByGroup(@RequestBody QueryRequest request) {
        try {
            List<Map> result = tdEmphasisService.getSegmentByGroup(request.getParams());
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("大修数据获取失败", e);
            return ResultGenerator.genFailResult("大修数据获取失败！");
        }
    }

    @GetMapping("/emphasis/getEmphasisSelect")
    @ApiOperation(value = "获取大修选择信息", notes = "获取大修选择信息")
    public Result getEmphasisSelect() {
        try {
            List<Map> result = tdEmphasisService.getEmphasisSelect();
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取大修选择信息", e);
            return ResultGenerator.genFailResult("获取大修选择信息！");
        }
    }


    @PostMapping("/emphasis/detail")
    @ApiOperation(value = "明细", notes = "大修线路信息详细数据")
    public Result detail(@RequestParam String id) {
        TdEmphasis tdLine = tdEmphasisService.findById(id);
        return ResultGenerator.genSuccessResult(tdLine);
    }

    @PostMapping("/emphasis/add")
    @ApiOperation(value = "新增", notes = "大修线路信息新增")
    public Result add(TdEmphasis tdEmphasis) {
        try {
            tdEmphasis.setUuid(UUID.randomUUID().toString());
            tdEmphasisService.save(tdEmphasis);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("新增大修线路信息信息失败", e);
            return ResultGenerator.genFailResult("新增大修线路信息信息失败！");
        }
    }

    @PostMapping("/emphasis/delete")
    @ApiOperation(value = "删除", notes = "大修线路信息删除")
    public Result delete(@RequestParam String id) {
        try {
            tdEmphasisService.deleteByIds(id);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("删除大修线路信息信息失败", e);
            return ResultGenerator.genFailResult("删除大修线路信息信息失败！");
        }
    }

    @PostMapping("/emphasis/update")
    @ApiOperation(value = "修改", notes = "大修线路信息修改")
    public Result update(TdEmphasis tdLine) {
        try {
            tdEmphasisService.update(tdLine);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("修改大修线路信息信息失败", e);
            return ResultGenerator.genFailResult("修改大修线路信息信息失败！");
        }

    }


}
