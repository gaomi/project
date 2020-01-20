package com.company.project.module.sys.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.module.sys.dao.SysUserRoleMapper;
import com.company.project.module.sys.model.SysUserRole;
import com.company.project.module.sys.service.SysUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by chen on 2019/05/21.
 */
@Service
@Transactional
public class SysUserRoleServiceImpl extends AbstractService<SysUserRole> implements SysUserRoleService {
    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void deleteUserRolesByRoleId(String roleIds) {
        this.batchDelete(roleIds, "roleId", SysUserRole.class);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void deleteUserRolesByUserId(String userIds) {
        this.batchDelete(userIds, "roleId", SysUserRole.class);
    }
}
