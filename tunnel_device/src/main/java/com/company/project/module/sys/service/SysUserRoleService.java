package com.company.project.module.sys.service;

import com.company.project.core.Service;
import com.company.project.module.sys.model.SysUserRole;


/**
 * Created by chen on 2019/05/21.
 */
public interface SysUserRoleService extends Service<SysUserRole> {
    /**
     * 根据角色id删除角色关系
     * @param roleIds
     */
    void deleteUserRolesByRoleId(String roleIds);
    /**
     * 根据用户id删除角色关系
     * @param userIds
     */
    void deleteUserRolesByUserId(String userIds);
}
