package com.company.project.module.app.service;

import com.company.project.module.sys.model.SysDept;
import com.company.project.module.sys.model.SysUser;

import java.util.List;
import java.util.Map;

public interface AppSysService {
    List<SysUser> isLogin(String userName, String passWord);

    SysDept findDeptById(String deptId);

    void updateUserPassword(Map param);
}
