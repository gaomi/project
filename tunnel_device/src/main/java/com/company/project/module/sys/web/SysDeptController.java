package com.company.project.module.sys.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.annotation.Log;
import com.company.project.core.controller.BaseController;
import com.company.project.core.model.QueryRequest;
import com.company.project.core.model.Tree;
import com.company.project.module.sys.model.SysDept;
import com.company.project.module.sys.service.SysDeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by chen on 2019/05/21.
 */
@RestController
@RequestMapping("/module/sys/dept")
@Api(description = "部门管理")
public class SysDeptController extends BaseController {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Resource
    private SysDeptService sysDeptService;

    @Log("获取部门信息")
    @GetMapping
    @RequiresPermissions("dept:list")
    @ApiOperation(value = "访问路径", notes = "部门管理请求地址")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("module/sys/dept/list");
        return mv;
    }

    @GetMapping("/toEdit")
    @RequiresPermissions("dept:edit")
    @ApiOperation(value = "访问路径", notes = "部门编辑页面请求地址")
    public ModelAndView editPage() {
        ModelAndView mv = new ModelAndView("module/sys/dept/edit");
        return mv;
    }

    @PostMapping("/list")
    @RequiresPermissions("dept:list")
    @ApiOperation(value = "列表", notes = "部门列表")
    public Result list(QueryRequest request, SysDept dept) {
        Map<String, Object> listData= super.selectByPageNumSize(request, () -> sysDeptService.findAllDepts(dept));
        return ResultGenerator.genSuccessResult(listData);
    }

    @PostMapping("/tree")
    @RequiresPermissions("dept:list")
    @ApiOperation(value = "部门树", notes = "部门列表")
    public Result getDeptTree() {
        try {
            List<Tree<SysDept>> tree = this.sysDeptService.getDeptTree();
            return ResultGenerator.genSuccessResult(tree);
        } catch (Exception e) {
            log.error("获取部门树失败", e);
            return ResultGenerator.genFailResult("获取部门树失败！");
        }
    }

    @PostMapping("/detail")
    @ApiOperation(value = "明细", notes = "部门详细数据")
    public Result detail(@RequestParam String id) {
        try {
            SysDept sysDept = sysDeptService.findById(id);
            return ResultGenerator.genSuccessResult(sysDept);
        } catch (Exception e) {
            log.error("获取部门信息失败", e);
            return ResultGenerator.genFailResult("获取部门信息失败！");
        }
    }

    @Log("新增部门 ")
    @RequiresPermissions("dept:add")
    @PostMapping("/add")
    @ApiOperation(value = "新增", notes = "部门新增")
    public Result add(SysDept sysDept) {
        try {
            sysDeptService.addDept(sysDept);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            log.error("新增部门失败", e);
            return ResultGenerator.genFailResult("新增部门失败！");
        }

    }

    @Log("删除部门")
    @RequiresPermissions("dept:delete")
    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "部门删除")
    public Result deleteDepts(String ids) {
        try {
            sysDeptService.deleteDepts(ids);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            log.error("删除部门失败", e);
            return ResultGenerator.genFailResult("删除部门失败！");
        }
    }


    @Log("修改部门 ")
    @RequiresPermissions("dept:update")
    @ApiOperation(value = "修改", notes = "部门修改")
    @PostMapping("/update")
    public Result update(SysDept sysDept) {
        try {
            sysDeptService.update(sysDept);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            log.error("修改部门失败", e);
            return ResultGenerator.genFailResult("修改部门失败！");
        }
    }


    @PostMapping("/checkDeptName")
    @ApiOperation(value = "检查名称", notes = "部门名称检查")
    public boolean checkDeptName(String deptName, String oldDeptName) {
        if (StringUtils.isNotBlank(oldDeptName) && deptName.equalsIgnoreCase(oldDeptName)) {
            return true;
        }
        SysDept result = sysDeptService.findByName(deptName);
        return result == null;
    }
}
