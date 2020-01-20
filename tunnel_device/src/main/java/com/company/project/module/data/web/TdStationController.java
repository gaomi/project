package com.company.project.module.data.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.controller.BaseController;
import com.company.project.core.model.QueryRequest;
import com.company.project.module.data.model.TdStation;
import com.company.project.module.data.service.TdStationService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Map;
import java.util.UUID;

/**
 * Created by paodingsoft.chen on 2019/08/06.
 */
@RestController
@RequestMapping("/module/data/station")
@Api(description = "地铁站点信息管理")
public class TdStationController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private TdStationService tdStationService;

    @GetMapping
    @RequiresPermissions("data:list")
    @ApiOperation(value = "访问路径", notes = "站点查询请求地址")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("module/data/station/list");
        return mv;
    }

    @PostMapping("/list")
    @ApiOperation(value = "列表", notes = "地铁站点信息列表")
    public Result list(@RequestBody QueryRequest request) {
        Map<String, Object> listData = super.selectByPageNumSize(request, () -> this.tdStationService.findAllStations(request.getParams()));
        return ResultGenerator.genSuccessResult(listData);
    }

    @GetMapping("/toEdit")
    @ApiOperation(value = "站点编辑页面", notes = "获取站点编辑页面")
    public ModelAndView toEdit(){
        ModelAndView mv = new ModelAndView("module/data/station/edit");
        return mv;
    }

    @PostMapping("/detail")
    @ApiOperation(value = "明细", notes = "地铁站点信息详细数据")
    public Result detail(@RequestParam String id) {
        TdStation tdStation = tdStationService.findById(id);
        return ResultGenerator.genSuccessResult(tdStation);
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增", notes = "地铁站点信息新增")
    public Result add(TdStation tdStation) {
        try {
            tdStation.setUuid(UUID.randomUUID().toString());
            tdStationService.save(tdStation);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("新增地铁站点信息信息失败", e);
            return ResultGenerator.genFailResult("新增地铁站点信息信息失败！");
        }
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "地铁站点信息删除")
    public Result delete(@RequestParam String id) {
        try {
            tdStationService.deleteById(id);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("删除地铁站点信息信息失败", e);
            return ResultGenerator.genFailResult("删除地铁站点信息信息失败！");
        }
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改", notes = "地铁站点信息修改")
    public Result update(TdStation tdStation) {
        try {
            tdStationService.update(tdStation);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("修改地铁站点信息信息失败", e);
            return ResultGenerator.genFailResult("修改地铁站点信息信息失败！");
        }

    }


}
