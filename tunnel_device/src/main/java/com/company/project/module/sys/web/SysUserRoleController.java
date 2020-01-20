package com.company.project.module.sys.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.module.sys.model.SysUserRole;
import com.company.project.module.sys.service.SysUserRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by chen on 2019/05/21.
*/
@RestController
@RequestMapping("/module/sys/userRole")
@Api(description = "用户角色管理")
public class SysUserRoleController {
    @Resource
    private SysUserRoleService sysUserRoleService;

    @PostMapping("/add")
    public Result add(SysUserRole sysUserRole) {
        sysUserRoleService.save(sysUserRole);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam String id) {
        sysUserRoleService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SysUserRole sysUserRole) {
        sysUserRoleService.update(sysUserRole);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam String id) {
        SysUserRole sysUserRole = sysUserRoleService.findById(id);
        return ResultGenerator.genSuccessResult(sysUserRole);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SysUserRole> list = sysUserRoleService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
