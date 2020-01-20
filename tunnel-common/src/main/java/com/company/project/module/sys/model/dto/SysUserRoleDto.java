package com.company.project.module.sys.model.dto;

import com.company.project.module.sys.model.SysUser;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class SysUserRoleDto extends SysUser {
    private String roleId;

    private List<String> roleIds;
}