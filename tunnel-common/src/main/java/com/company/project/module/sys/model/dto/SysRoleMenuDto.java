package com.company.project.module.sys.model.dto;

import com.company.project.module.sys.model.SysRole;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class SysRoleMenuDto extends SysRole {
    private String menuId;

    private List<String> menuIds;
}