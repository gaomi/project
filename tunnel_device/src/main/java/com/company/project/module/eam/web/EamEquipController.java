package com.company.project.module.eam.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.controller.BaseController;
import com.company.project.core.model.QueryRequest;
import com.company.project.module.eam.model.EamEquip;
import com.company.project.module.eam.service.EamEquipService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
* Created by paodingsoft.chen on 2020/01/09.
*/
@RestController
@RequestMapping("/module/eam/equip")
@Api(description = "EAM设备管理")
public class EamEquipController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private EamEquipService eamEquipService;

    @PostMapping("/list")
    @ApiOperation(value = "列表", notes = "EAM设备列表")
    public Result list(@RequestBody QueryRequest request) {
        try {
            Map<String, Object> result = super.selectByPageNumSize(request, () -> this.eamEquipService.findAll());
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            logger.error("获取EAM设备列表失败", e);
            return ResultGenerator.genFailResult("获取EAM设备列表失败！");
        }
    }

    @PostMapping("/detail")
    @ApiOperation(value = "明细", notes = "EAM设备详细数据")
    public Result detail(@RequestParam String id) {
        EamEquip eamEquip = eamEquipService.findById(id);
        return ResultGenerator.genSuccessResult(eamEquip);
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增", notes = "EAM设备新增")
    public Result add(EamEquip eamEquip) {
        try {
            eamEquipService.save(eamEquip);
            return ResultGenerator.genSuccessResult();
          } catch (Exception e) {
            logger.error("新增EAM设备信息失败", e);
            return ResultGenerator.genFailResult("新增EAM设备信息失败，请联系网站管理员！");
        }
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "EAM设备删除")
    public Result delete(@RequestParam String id) {
        try {
            eamEquipService.deleteById(id);
            return ResultGenerator.genSuccessResult();
          } catch (Exception e) {
            logger.error("删除EAM设备信息失败", e);
            return ResultGenerator.genFailResult("删除EAM设备信息失败，请联系网站管理员！");
        }
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改", notes = "EAM设备修改")
    public Result update(EamEquip eamEquip) {
        try {
            eamEquipService.update(eamEquip);
            return ResultGenerator.genSuccessResult();
          } catch (Exception e) {
            logger.error("修改EAM设备信息失败", e);
            return ResultGenerator.genFailResult("修改EAM设备信息失败，请联系网站管理员！");
        }

    }


}
