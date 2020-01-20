package com.company.project.module.sys.service;

import com.company.project.module.sys.model.SysUserOnline;

import java.util.List;

public interface SysSessionService {
    List<SysUserOnline> list();

    void forceLogout(String id);
}
