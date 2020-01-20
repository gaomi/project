package com.company.project.module.sys.dao;

import com.company.project.core.Mapper;
import com.company.project.module.sys.model.SysRole;
import com.company.project.module.sys.model.dto.SysRoleMenuDto;

import java.util.List;
import java.util.Set;

public interface SysRoleMapper extends Mapper<SysRole> {
    List<SysRole> findUserRole(String userName);

    List<SysRoleMenuDto> findDtoById(String id);

    Set<String> findRoleByUserId(String userId);
}