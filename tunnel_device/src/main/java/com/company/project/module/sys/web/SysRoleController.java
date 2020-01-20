package com.company.project.module.sys.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.annotation.Log;
import com.company.project.core.controller.BaseController;
import com.company.project.core.model.QueryRequest;
import com.company.project.core.model.Tree;
import com.company.project.module.sys.model.SysMenu;
import com.company.project.module.sys.model.SysRole;
import com.company.project.module.sys.model.SysUser;
import com.company.project.module.sys.model.dto.SysRoleMenuDto;
import com.company.project.module.sys.service.SysRoleService;
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
@RequestMapping("/module/sys/role")
@Api(description = "角色管理")
public class SysRoleController extends BaseController {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Resource
    private SysRoleService sysRoleService;

    @Log("获取角色信息")
    @GetMapping
    @RequiresPermissions("role:list")
    @ApiOperation(value = "访问路径", notes = "角色管理请求地址")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("module/sys/role/list");
        return mv;
    }

    @GetMapping("/toEdit")
    @RequiresPermissions("role:edit")
    @ApiOperation(value = "访问路径", notes = "角色信息编辑页面请求地址")
    public ModelAndView editPage() {
        ModelAndView mv = new ModelAndView("module/sys/role/edit");
        return mv;
    }

    @GetMapping("/toPermEdit")
    @RequiresPermissions("role:edit")
    @ApiOperation(value = "访问路径", notes = "角色权限编辑页面请求地址")
    public ModelAndView editPermPage() {
        ModelAndView mv = new ModelAndView("module/sys/role/permEdit");
        return mv;
    }

    @PostMapping("/list")
    @RequiresPermissions("role:list")
    @ApiOperation(value = "列表", notes = "角色列表")
    public Result list(@RequestBody QueryRequest request, SysRole role) {
        try {
            ////如果是-1，则查所有
            //String queryKey = "-1";
            //if ((null!=request.getParams().get("keyWord") )&& ) {
            //    queryKey
            //}
            //if (StringUtils.equals(role.getId(), queryKey)) {
            //    List<SysRole> all = this.sysRoleService.findAll();
            //    return ResultGenerator.genSuccessResult(all);
            //} else {
            //
            //}
            if ((null != request.getParams()) && StringUtils.isNotBlank(request.getParams().get("keyWord").toString())) {
                role.setName(request.getParams().get("keyWord").toString());
            }
            Map<String, Object> listData = super.selectByPageNumSize(request, () -> this.sysRoleService.findAllRole(role));
            return ResultGenerator.genSuccessResult(listData);
        } catch (Exception e) {
            log.error("获取角色列表失败", e);
            return ResultGenerator.genFailResult("获取角色列表失败！");
        }
    }

    @PostMapping("/detail")
    @ApiOperation(value = "明细", notes = "角色详细数据")
    public Result detail(@RequestParam String id) {
        try {
            SysRoleMenuDto dto = this.sysRoleService.findRoleWithMenus(id);
            return ResultGenerator.genSuccessResult(dto);
        } catch (Exception e) {
            log.error("获取角色信息失败", e);
            return ResultGenerator.genFailResult("获取角色信息失败！");
        }
    }

    @Log("新增角色")
    @RequiresPermissions("role:add")
    @PostMapping("/add")
    @ApiOperation(value = "新增", notes = "角色新增")
    public Result add(SysRole role, String[] menuId) {
        try {
            sysRoleService.addRole(role,menuId);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            log.error("新增角色失败", e);
            return ResultGenerator.genFailResult("新增角色失败！");
        }
    }

    @Log("删除角色")
    @RequiresPermissions("role:delete")
    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "角色删除")
    public Result delete(@RequestParam String ids) {
        try {
            sysRoleService.deleteRoles(ids);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            log.error("删除角色失败", e);
            return ResultGenerator.genFailResult("删除角色失败！");
        }

    }

    @Log("修改角色")
    @RequiresPermissions("role:update")
    @PostMapping("/update")
    @ApiOperation(value = "修改", notes = "角色修改")
    public Result update(SysRole role, String[] menuId) {
        try {
            sysRoleService.updateRole(role, menuId);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            log.error("修改角色失败", e);
            return ResultGenerator.genFailResult("修改角色失败！");
        }

    }

    @PostMapping("/getRoleTree")
    @ApiOperation(value = "检查名称", notes = "角色名称检查")
    public Result getRoleTree(String id) {
        try {
            SysUser currentUser = this.getCurrentUser();
            List<Tree<SysMenu>> tree = this.sysRoleService.getRoleMenuTree(id, currentUser);
            return ResultGenerator.genSuccessResult(tree);
        } catch (Exception e) {
            log.error("获取菜单树失败", e);
            return ResultGenerator.genFailResult("获取菜单数据失败！");
        }
    }

    @PostMapping("/checkRoleName")
    @ApiOperation(value = "检查名称", notes = "角色名称检查")
    public boolean checkRoleName(String roleName, String oldRoleName) {
        if (StringUtils.isNotBlank(oldRoleName) && roleName.equalsIgnoreCase(oldRoleName)) {
            return true;
        }
        SysRole result = sysRoleService.findByName(roleName);
        return result == null;
    }


}
