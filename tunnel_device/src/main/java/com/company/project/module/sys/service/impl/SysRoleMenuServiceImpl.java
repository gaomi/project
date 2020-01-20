package com.company.project.module.sys.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.module.sys.dao.SysRoleMenuMapper;
import com.company.project.module.sys.model.SysRoleMenu;
import com.company.project.module.sys.service.SysRoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by chen on 2019/05/21.
 */
@Service
@Transactional
public class SysRoleMenuServiceImpl extends AbstractService<SysRoleMenu> implements SysRoleMenuService {
    @Resource
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void deleteRoleMenusByRoleId(String roleIds) {
        this.batchDelete(roleIds, "roleId", SysRoleMenu.class);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void deleteRoleMenusByMenuId(String menuIds) {
        this.batchDelete(menuIds, "menuId", SysRoleMenu.class);
    }
}
