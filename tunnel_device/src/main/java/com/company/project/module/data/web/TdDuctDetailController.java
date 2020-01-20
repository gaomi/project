package com.company.project.module.data.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.controller.BaseController;
import com.company.project.module.data.model.TdDuctDetail;
import com.company.project.module.data.service.TdDuctDetailService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by paodingsoft.chen on 2019/08/12.
*/
@RestController
@RequestMapping("/module/td/duct/detail")
@Api(description = "区间环号信息表管理")
public class TdDuctDetailController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private TdDuctDetailService tdDuctDetailService;

    @PostMapping("/list")
    @ApiOperation(value = "列表", notes = "区间环号信息表列表")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<TdDuctDetail> list = tdDuctDetailService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/detail")
    @ApiOperation(value = "明细", notes = "区间环号信息表详细数据")
    public Result detail(@RequestParam String id) {
        TdDuctDetail tdDuctDetail = tdDuctDetailService.findById(id);
        return ResultGenerator.genSuccessResult(tdDuctDetail);
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增", notes = "区间环号信息表新增")
    public Result add(TdDuctDetail tdDuctDetail) {
        try {
            tdDuctDetailService.save(tdDuctDetail);
            return ResultGenerator.genSuccessResult();
          } catch (Exception e) {
            logger.error("新增区间环号信息表信息失败", e);
            return ResultGenerator.genFailResult("新增区间环号信息表信息失败！");
        }
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "区间环号信息表删除")
    public Result delete(@RequestParam String id) {
        try {
            tdDuctDetailService.deleteById(id);
            return ResultGenerator.genSuccessResult();
          } catch (Exception e) {
            logger.error("删除区间环号信息表信息失败", e);
            return ResultGenerator.genFailResult("删除区间环号信息表信息失败！");
        }
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改", notes = "区间环号信息表修改")
    public Result update(TdDuctDetail tdDuctDetail) {
        try {
            tdDuctDetailService.update(tdDuctDetail);
            return ResultGenerator.genSuccessResult();
          } catch (Exception e) {
            logger.error("修改区间环号信息表信息失败", e);
            return ResultGenerator.genFailResult("修改区间环号信息表信息失败！");
        }

    }


}
