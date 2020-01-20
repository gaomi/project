package com.company.project.module.sys.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.annotation.Log;
import com.company.project.core.controller.BaseController;
import com.company.project.core.model.QueryRequest;
import com.company.project.core.model.Tree;
import com.company.project.module.sys.model.SysMenu;
import com.company.project.module.sys.model.SysUser;
import com.company.project.module.sys.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by chen on 2019/05/21.
 */
@RestController
@RequestMapping("/module/sys/menus")
@Api(description = "菜单管理")
public class SysMenusController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private SysMenuService sysMenuService;

    @GetMapping
    @RequiresPermissions("menu:list")
    @ApiOperation(value = "访问路径", notes = "菜单管理请求地址")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("module/sys/menus/list");
        return mv;
    }

    @GetMapping("/toEdit")
    @RequiresPermissions("menu:edit")
    @ApiOperation(value = "访问路径", notes = "菜单编辑页面请求地址")
    public ModelAndView editPage() {
        ModelAndView mv = new ModelAndView("module/sys/menus/edit");
        return mv;
    }

    @GetMapping("/getHeadMenu")
    @ApiOperation(value = "获取头部菜单", notes = "获取头部菜单")
    public Result getHeadMenu() {
        SysUser currentUser = super.getCurrentUser();
       List<SysMenu> menuList = sysMenuService.getHeadMenu(currentUser.getId());
        return ResultGenerator.genSuccessResult(menuList);
    }

    @PostMapping("/getAsideMenu")
    @ApiOperation(value = "获取头部菜单", notes = "获取头部菜单")
    public Result getAsideMenu(@RequestBody QueryRequest request) {
        SysUser currentUser = super.getCurrentUser();
        Map params = request.getParams();
        params.put("id",currentUser.getId());
        List<Map> menus  = sysMenuService.getAsideMenu(params);
        return ResultGenerator.genSuccessResult(menus);
    }

    @PostMapping("/treelist")
    @RequiresPermissions("menu:list")
    @ApiOperation(value = "列表", notes = "菜单列表")
    public Result list(@RequestBody QueryRequest request, SysMenu menu) {
        try {
            if ((null != request.getParams()) && StringUtils.isNotBlank(request.getParams().get("keyWord").toString())) {
                menu.setName(request.getParams().get("keyWord").toString());
            }
            List<SysMenu> allMenus = sysMenuService.findAllMenus(menu);
            return ResultGenerator.genSuccessResult(allMenus);
        } catch (Exception e) {
            logger.error("获取菜单集合失败", e);
            return ResultGenerator.genFailResult("获取菜单信息失败！");
        }
    }

    @PostMapping("/detail")
    @ApiOperation(value = "明细", notes = "菜单/按钮详细数据")
    public Result detail(@RequestParam String id) {
        try {
            SysMenu sysMenu = sysMenuService.findById(id);
            return ResultGenerator.genSuccessResult(sysMenu);
        } catch (Exception e) {
            logger.error("获取菜单信息失败", e);
            return ResultGenerator.genFailResult("获取菜单/按钮信息失败！");
        }

    }

    @Log("新增菜单/按钮")
    @RequiresPermissions("menu:add")
    @PostMapping("/add")
    @ApiOperation(value = "新增", notes = "菜单/按钮新增")
    public Result add(SysMenu menu) {
        String name;
        if (SysMenu.TYPE_MENU.equals(menu.getType())) {
            name = "按钮";
        } else {
            name = "菜单";
        }
        try {
            sysMenuService.addMenu(menu);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("新增{}失败", name, e);
            return ResultGenerator.genFailResult("新增" + name + "失败！");
        }
    }

    @PostMapping("/delete")
    @Log("删除菜单")
    @RequiresPermissions("menu:delete")
    @ApiOperation(value = "删除", notes = "菜单/按钮删除")
    public Result delete(@RequestParam String ids) {
        try {
            sysMenuService.deleteMeuns(ids);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("删除菜单失败", e);
            return ResultGenerator.genFailResult("删除失败！" + e.getMessage());
        }
    }

    @Log("修改菜单/按钮")
    @RequiresPermissions("menu:update")
    @PostMapping("/update")
    @ApiOperation(value = "修改", notes = "菜单/按钮修改")
    public Result update(SysMenu menu) {
        String name;
        if (SysMenu.TYPE_MENU.equals(menu.getType())) {
            name = "按钮";
        } else {
            name = "菜单";
        }
        try {
            sysMenuService.updateMenu(menu);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("更新{}失败", name, e);
            return ResultGenerator.genFailResult("更新" + name + "失败！");
        }
    }


    @PostMapping("/checkMenuName")
    @ApiOperation(value = "检查名称", notes = "菜单名称检查")
    public boolean checkMenuName(String menuName, String type, String oldMenuName) {
        if (StringUtils.isNotBlank(oldMenuName) && menuName.equalsIgnoreCase(oldMenuName)) {
            return true;
        }
        SysMenu result = sysMenuService.findByNameAndType(menuName, type);
        return result == null;
    }

    @PostMapping("/tree")
    @RequiresPermissions("menu:list")
    @ApiOperation(value = "菜单树", notes = "菜单列表")
    public Result getMenuTree() {
        try {
            SysUser currentUser = this.getCurrentUser();
            List<Tree<SysMenu>> tree = this.sysMenuService.getMenuTree(currentUser);
            return ResultGenerator.genSuccessResult(tree);
        } catch (Exception e) {
            logger.error("获取菜单树失败", e);
            return ResultGenerator.genFailResult("获取菜单数据失败！");
        }
    }

}
