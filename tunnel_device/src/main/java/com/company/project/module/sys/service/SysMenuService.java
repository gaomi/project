package com.company.project.module.sys.service;

import com.company.project.core.Service;
import com.company.project.core.model.QueryRequest;
import com.company.project.core.model.Tree;
import com.company.project.module.sys.model.SysMenu;
import com.company.project.module.sys.model.SysUser;

import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Created by chen on 2019/05/21.
 */
public interface SysMenuService extends Service<SysMenu> {

    List<SysMenu> findUserPermissions(String userName);

    SysMenu findByNameAndType(String menuName, String type);

    List<SysMenu> findAllMenus(SysMenu menu);

    void addMenu(SysMenu menu);

    void deleteMeuns(String ids);

    void updateMenu(SysMenu menu);

    Set<String> findPermissionByUserId(String userId);

    /**
     * 菜单数据
     * @return
     */
    List<Tree<SysMenu>> getMenuTree(SysUser user);

    /***
     * 获取页面头部菜单
     * @return
     */
    List<SysMenu> getHeadMenu(String userId);

    /***
     * 获取侧边栏菜单
     * @param map
     * @return
     */
    List<Map> getAsideMenu(Map map);
}
