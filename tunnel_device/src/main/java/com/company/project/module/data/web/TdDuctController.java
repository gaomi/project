package com.company.project.module.data.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.controller.BaseController;
import com.company.project.core.model.QueryRequest;
import com.company.project.module.data.model.TdDuct;
import com.company.project.module.data.service.TdDuctService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
* Created by paodingsoft.chen on 2019/08/22.
*/
@RestController
@RequestMapping("/module/data/duct")
@Api(description = "环号信息管理")
public class TdDuctController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private TdDuctService tdDuctService;

    @GetMapping()
    @RequiresPermissions("duct:list")
    @ApiOperation(value = "访问路径", notes = "隧道环页面请求地址")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("module/data/duct/list");
        return mv;
    }

    @PostMapping("/list")
    @ApiOperation(value = "列表", notes = "环号信息列表")
    public Result list(@RequestBody QueryRequest request) {
        try {
            Map result = super.selectByPageNumSize(request, () -> tdDuctService.selectDuct(request.getParams()));
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("大修数据获取失败", e);
            return ResultGenerator.genFailResult("大修数据获取失败！");
        }
    }

    @PostMapping("/detail")
    @ApiOperation(value = "明细", notes = "环号信息详细数据")
    public Result detail(@RequestParam String id) {
        TdDuct tdDuct = tdDuctService.findById(id);
        return ResultGenerator.genSuccessResult(tdDuct);
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增", notes = "环号信息新增")
    public Result add(TdDuct tdDuct) {
        try {
            tdDuctService.save(tdDuct);
            return ResultGenerator.genSuccessResult();
          } catch (Exception e) {
            logger.error("新增环号信息信息失败", e);
            return ResultGenerator.genFailResult("新增环号信息信息失败！");
        }
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "环号信息删除")
    public Result delete(@RequestParam String id) {
        try {
            tdDuctService.deleteById(id);
            return ResultGenerator.genSuccessResult();
          } catch (Exception e) {
            logger.error("删除环号信息信息失败", e);
            return ResultGenerator.genFailResult("删除环号信息信息失败！");
        }
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改", notes = "环号信息修改")
    public Result update(TdDuct tdDuct) {
        try {
            tdDuctService.update(tdDuct);
            return ResultGenerator.genSuccessResult();
          } catch (Exception e) {
            logger.error("修改环号信息信息失败", e);
            return ResultGenerator.genFailResult("修改环号信息信息失败！");
        }

    }


}
