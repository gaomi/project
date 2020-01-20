package com.company.project.module.sys.dao;

import com.company.project.core.Mapper;
import com.company.project.module.sys.model.SysMenu;
import com.company.project.module.sys.model.SysRoleMenu;

import java.util.List;

public interface SysRoleMenuMapper extends Mapper<SysRoleMenu> {
    /**
     * 根据角色id，找对应菜单
     *
     * @param id
     * @return
     */
    List<SysMenu> findRoleMenu(String id);
}