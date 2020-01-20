package com.company.project.module.sys.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.module.sys.model.SysRoleMenu;
import com.company.project.module.sys.service.SysRoleMenuService;
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
@RequestMapping("/module/sys/roleMenu")
@Api(description = "角色菜单管理")
public class SysRoleMenuController {
    @Resource
    private SysRoleMenuService sysRoleMenuService;

    @PostMapping("/add")
    public Result add(SysRoleMenu sysRoleMenu) {
        sysRoleMenuService.save(sysRoleMenu);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam String id) {
        sysRoleMenuService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SysRoleMenu sysRoleMenu) {
        sysRoleMenuService.update(sysRoleMenu);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam String id) {
        SysRoleMenu sysRoleMenu = sysRoleMenuService.findById(id);
        return ResultGenerator.genSuccessResult(sysRoleMenu);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SysRoleMenu> list = sysRoleMenuService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
