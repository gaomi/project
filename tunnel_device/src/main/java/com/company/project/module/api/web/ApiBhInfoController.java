package com.company.project.module.api.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.controller.BaseController;
import com.company.project.module.api.model.BhInfo;
import com.company.project.module.api.service.ApiBhInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by paodingsoft.chen on 2019/07/08.
 */
@RestController
@RequestMapping("/module/bh/info")
@Api(description = "病害信息管理")
public class ApiBhInfoController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ApiBhInfoService apiBhInfoService;

    @PostMapping("/list")
    @ApiOperation(value = "列表", notes = "病害信息列表")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<BhInfo> list = apiBhInfoService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/detail")
    @ApiOperation(value = "明细", notes = "病害信息详细数据")
    public Result detail(@RequestParam String id) {
        BhInfo bhInfo = apiBhInfoService.findById(id);
        return ResultGenerator.genSuccessResult(bhInfo);
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增", notes = "病害信息新增")
    public Result add(BhInfo bhInfo) {
        try {
            apiBhInfoService.save(bhInfo);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("新增病害信息信息失败", e);
            return ResultGenerator.genFailResult("新增病害信息信息失败！");
        }
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "病害信息删除")
    public Result delete(@RequestParam String id) {
        try {
            apiBhInfoService.deleteById(id);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("删除病害信息信息失败", e);
            return ResultGenerator.genFailResult("删除病害信息信息失败！");
        }
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改", notes = "病害信息修改")
    public Result update(BhInfo bhInfo) {
        try {
            apiBhInfoService.update(bhInfo);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("修改病害信息信息失败", e);
            return ResultGenerator.genFailResult("修改病害信息信息失败！");
        }

    }


}
