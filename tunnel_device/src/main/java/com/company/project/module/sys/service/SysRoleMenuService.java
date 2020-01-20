package com.company.project.module.sys.service;

import com.company.project.core.Service;
import com.company.project.module.sys.model.SysRoleMenu;


/**
 * Created by chen on 2019/05/21.
 */
public interface SysRoleMenuService extends Service<SysRoleMenu> {
    /**
     * 根据角色id删除权限关系
     * @param roleIds
     */
    void deleteRoleMenusByRoleId(String roleIds);
    /**
     * 根据id删除权限关系
     * @param menuIds
     */
    void deleteRoleMenusByMenuId(String menuIds);
}
