package com.company.project.module.sys.service;

import com.company.project.core.Service;
import com.company.project.core.model.Tree;
import com.company.project.module.sys.model.SysMenu;
import com.company.project.module.sys.model.SysRole;
import com.company.project.module.sys.model.SysUser;
import com.company.project.module.sys.model.dto.SysRoleMenuDto;

import java.util.List;
import java.util.Set;


/**
 * Created by chen on 2019/05/21.
 */
public interface SysRoleService extends Service<SysRole> {
    List<SysRole> findUserRole(String userName);

    List<SysRole> findAllRole(SysRole role);

    /**
     * 查找角色对应的菜单
     *
     * @param id
     * @return
     */
    SysRoleMenuDto findRoleWithMenus(String id);

    SysRole findByName(String roleName);

    void deleteRoles(String roleIds);

    Set<String> findRoleByUserId(String userId);


    /**
     * 根据角色id查询菜单
     *
     * @param id
     * @param user
     * @return
     */
    List<Tree<SysMenu>> getRoleMenuTree(String id, SysUser user);

    /**
     * 新增角色
     * @param role
     * @param menuId
     */
    void addRole(SysRole role, String[] menuId);


    /**
     * 修改角色
     *
     * @param role
     * @param menuId
     */
    void updateRole(SysRole role, String[] menuId);
}
