package com.company.project.module.app.service.impl;

import com.company.project.module.app.service.AppSysService;
import com.company.project.module.sys.dao.SysDeptMapper;
import com.company.project.module.sys.dao.SysUserMapper;
import com.company.project.module.sys.model.SysDept;
import com.company.project.module.sys.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;
import java.util.Map;

/**
 * @author Chen
 * @created 2019-11-20-19:52.
 */
@Service
public class AppSysServiceImpl implements AppSysService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Override
    public List<SysUser> isLogin(String userName, String passWord) {
        Condition userCondition = new Condition(SysUser.class);
        String pwd = DigestUtils.md5DigestAsHex(passWord.getBytes());
        userCondition.createCriteria().andEqualTo("userName", userName).andEqualTo("password", pwd);
        return sysUserMapper.selectByCondition(userCondition);
    }

    @Override
    public SysDept findDeptById(String deptId) {
        return sysDeptMapper.selectByPrimaryKey(deptId);
    }

    @Override
    public void updateUserPassword(Map param) {
        sysUserMapper.updateUserPassword(param);
    }
}
