package com.company.project.module.eam.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.controller.BaseController;
import com.company.project.core.model.QueryRequest;
import com.company.project.module.eam.model.EamDepart;
import com.company.project.module.eam.service.EamDepartService;
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
@RequestMapping("/module/eam/depart")
@Api(description = "EAM部门管理")
public class EamDepartController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private EamDepartService eamDepartService;

    @PostMapping("/list")
    @ApiOperation(value = "列表", notes = "EAM部门列表")
    public Result list(@RequestBody QueryRequest request) {
        try {
            Map<String, Object> result = super.selectByPageNumSize(request, () -> this.eamDepartService.findAll());
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            logger.error("获取EAM部门列表失败", e);
            return ResultGenerator.genFailResult("获取EAM部门列表失败！");
        }
    }

    @PostMapping("/detail")
    @ApiOperation(value = "明细", notes = "EAM部门详细数据")
    public Result detail(@RequestParam String id) {
        EamDepart eamDepart = eamDepartService.findById(id);
        return ResultGenerator.genSuccessResult(eamDepart);
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增", notes = "EAM部门新增")
    public Result add(EamDepart eamDepart) {
        try {
            eamDepartService.save(eamDepart);
            return ResultGenerator.genSuccessResult();
          } catch (Exception e) {
            logger.error("新增EAM部门信息失败", e);
            return ResultGenerator.genFailResult("新增EAM部门信息失败，请联系网站管理员！");
        }
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "EAM部门删除")
    public Result delete(@RequestParam String id) {
        try {
            eamDepartService.deleteById(id);
            return ResultGenerator.genSuccessResult();
          } catch (Exception e) {
            logger.error("删除EAM部门信息失败", e);
            return ResultGenerator.genFailResult("删除EAM部门信息失败，请联系网站管理员！");
        }
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改", notes = "EAM部门修改")
    public Result update(EamDepart eamDepart) {
        try {
            eamDepartService.update(eamDepart);
            return ResultGenerator.genSuccessResult();
          } catch (Exception e) {
            logger.error("修改EAM部门信息失败", e);
            return ResultGenerator.genFailResult("修改EAM部门信息失败，请联系网站管理员！");
        }

    }


}
