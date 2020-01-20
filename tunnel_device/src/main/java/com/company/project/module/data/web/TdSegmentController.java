package com.company.project.module.data.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.controller.BaseController;
import com.company.project.core.model.QueryRequest;
import com.company.project.module.data.model.TdSegment;
import com.company.project.module.data.service.TdSegmentService;
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
 * Created by paodingsoft.chen on 2019/08/06.
 */
@RestController
@RequestMapping("/module/data/segment")
@Api(description = "地铁区间信息管理")
public class TdSegmentController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private TdSegmentService tdSegmentService;

    @GetMapping
    @RequiresPermissions("line:list")
    @ApiOperation(value = "访问路径", notes = "区间查询请求地址")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("module/data/segment/list");
        return mv;
    }

    @PostMapping("/list")
    @ApiOperation(value = "列表", notes = "地铁区间信息列表")
    public Result list(@RequestBody QueryRequest request) {
        Map<String, Object> listData = super.selectByPageNumSize(request, () -> this.tdSegmentService.findAllSegment(request.getParams()));
        return ResultGenerator.genSuccessResult(listData);
    }

    @PostMapping("/detail")
    @ApiOperation(value = "明细", notes = "地铁区间信息详细数据")
    public Result detail(@RequestParam String id) {
        TdSegment tdSegment = tdSegmentService.findById(id);
        return ResultGenerator.genSuccessResult(tdSegment);
    }

    @GetMapping("/toEdit")
    @ApiOperation(value = "区间编辑页面", notes = "获取区间编辑页面")
    public ModelAndView toEdit(){
        ModelAndView mv = new ModelAndView("module/data/segment/edit");
        return mv;
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增", notes = "地铁区间信息新增")
    public Result add(TdSegment tdSegment) {
        try {
            tdSegmentService.save(tdSegment);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("新增地铁区间信息信息失败", e);
            return ResultGenerator.genFailResult("新增地铁区间信息信息失败！");
        }
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "地铁区间信息删除")
    public Result delete(@RequestParam String id) {
        try {
            tdSegmentService.deleteById(id);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("删除地铁区间信息信息失败", e);
            return ResultGenerator.genFailResult("删除地铁区间信息信息失败！");
        }
    }



    @PostMapping("/update")
    @ApiOperation(value = "修改", notes = "地铁区间信息修改")
    public Result updateSegmentMileage(TdSegment tdSegment) {
        try {
            tdSegmentService.updateSegmentMileage(tdSegment);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("修改地铁区间信息信息失败", e);
            return ResultGenerator.genFailResult("修改地铁区间信息信息失败！");
        }

    }


}
