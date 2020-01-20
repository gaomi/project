package com.company.project.module.data.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.controller.BaseController;
import com.company.project.core.model.QueryRequest;
import com.company.project.module.data.model.TdSandy;
import com.company.project.module.data.service.TdSandyService;
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
import java.util.UUID;

/**
 * Created by paodingsoft.chen on 2019/08/07.
 */
@RestController
@RequestMapping("/module/data/sandy")
@Api(description = "隧道砂性土信息管理")
public class TdSandyController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private TdSandyService tdSandyService;

    @GetMapping
    @RequiresPermissions("sandy:list")
    @ApiOperation(value = "访问路径", notes = "砂性土层页面请求地址")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("module/data/sandy/list");
        return mv;
    }
    @PostMapping("/list")
    @ApiOperation(value = "列表", notes = "隧道砂性土信息列表")
    public Result list(@RequestBody QueryRequest request) {
        try {
            Map<String, Object> result = super.selectByPageNumSize(request, () -> this.tdSandyService.findbyParams(request.getParams()));
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            logger.error("获取隧道砂性土信息列表失败", e);
            return ResultGenerator.genFailResult("获取隧道砂性土信息列表失败！");
        }
    }

    @GetMapping("/toEdit")
    @ApiOperation(value = "土层信息编辑页面", notes = "获取土层信息编辑页面")
    public ModelAndView toEdit(){
        ModelAndView mv = new ModelAndView("module/data/sandy/edit");
        return mv;
    }

    @PostMapping("/detail")
    @ApiOperation(value = "明细", notes = "隧道砂性土信息详细数据")
    public Result detail(@RequestParam String id) {
        TdSandy tdSandy = tdSandyService.findById(id);
        return ResultGenerator.genSuccessResult(tdSandy);
    }


    @ApiOperation(value = "新增", notes = "隧道砂性土信息新增")
    public Result add(TdSandy tdSandy) {
        try {
            tdSandy.setUuid(UUID.randomUUID().toString());
            tdSandyService.save(tdSandy);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("新增隧道砂性土信息信息失败", e);
            return ResultGenerator.genFailResult("新增隧道砂性土信息信息失败！");
        }
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增", notes = "隧道砂性土信息新增")
    public Result save(TdSandy tdSandy) {
        try {
            tdSandyService.saveSandy(tdSandy);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("新增隧道砂性土信息信息失败", e);
            return ResultGenerator.genFailResult("新增隧道砂性土信息信息失败！");
        }
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "隧道砂性土信息删除")
    public Result delete(@RequestParam String id) {
        try {
            tdSandyService.deleteByIds(id);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("删除隧道砂性土信息信息失败", e);
            return ResultGenerator.genFailResult("删除隧道砂性土信息信息失败！");
        }
    }

    @GetMapping("/getDict")
    @ApiOperation(value = "删除", notes = "隧道砂性土信息删除")
    public Result getDict() {
        try {
            Map result = tdSandyService.getSandyDict();
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            logger.error("删除隧道砂性土信息信息失败", e);
            return ResultGenerator.genFailResult("删除隧道砂性土信息信息失败！");
        }
    }

    @GetMapping("/getSandySelect")
    @ApiOperation(value = "砂性土选项", notes = "砂性土选项")
    public Result getSandySelect() {
        try {
            List<Map> result = tdSandyService.getSandySelect();
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            logger.error("砂性土选项信息信息失败", e);
            return ResultGenerator.genFailResult("砂性土选项信息信息失败！");
        }
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改", notes = "隧道砂性土信息修改")
    public Result update(TdSandy tdSandy) {
        try {
            tdSandyService.update(tdSandy);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("修改隧道砂性土信息信息失败", e);
            return ResultGenerator.genFailResult("修改隧道砂性土信息信息失败！");
        }

    }


}
