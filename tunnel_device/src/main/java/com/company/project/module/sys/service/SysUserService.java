package com.company.project.module.sys.service;

import com.company.project.core.Service;
import com.company.project.core.model.QueryRequest;
import com.company.project.module.sys.model.SysUser;
import com.company.project.module.sys.model.dto.SysUserRoleDto;

import java.util.List;


/**
 * Created by chen on 2019/05/21.
 */
public interface SysUserService extends Service<SysUser> {
    List<SysUser> findUserWithDept(SysUser user, QueryRequest request);

    /**
     * 检验是否登录
     *
     * @param userName
     * @param passWord
     * @return
     */
    List<SysUser> isLogin(String userName, String passWord);

    void registUser(SysUser user);

    SysUser findByName(String username);

    void addUser(SysUser user, String[] roles);

    void updateTheme(String theme, String userName);

    void deleteUsers(String ids);

    void updateUser(SysUser user, String[] rolesSelect);

    Boolean updateDisabled(String id, boolean b);

    /**
     * 根据id查找
     * @param id
     * @return
     */
    @Override
    SysUserRoleDto findById(String id);

    /**
     * 修改密码
     * @param principal
     * @param password
     */
    void updatePwd(SysUser principal, String password);

    /**
     * 修改配置
     * @param principal
     * @param pageSize
     */
    void updateConfig(SysUser principal, String pageSize);
}
