package com.company.project.module.sys.service.impl;

import com.company.project.configurer.shiro.ShiroUtils;
import com.company.project.core.AbstractService;
import com.company.project.core.exception.ServiceException;
import com.company.project.core.model.QueryRequest;
import com.company.project.module.sys.dao.SysRoleMapper;
import com.company.project.module.sys.dao.SysUserMapper;
import com.company.project.module.sys.dao.SysUserRoleMapper;
import com.company.project.module.sys.model.SysUser;
import com.company.project.module.sys.model.SysUserRole;
import com.company.project.module.sys.model.dto.SysUserRoleDto;
import com.company.project.module.sys.service.SysUserService;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by chen on 2019/05/21.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class SysUserServiceImpl extends AbstractService<SysUser> implements SysUserService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public List<SysUser> findUserWithDept(SysUser user, QueryRequest request) {
        try {
            Map params = request.getParams();
            return this.sysUserMapper.findUserWithDept(params);
        } catch (Exception e) {
            logger.error("error", e);
            return Lists.newArrayList();
        }
    }

    @Override
    public List<SysUser> isLogin(String userName, String passWord) {
        Condition userCondition = new Condition(SysUser.class);
        String pwd = DigestUtils.md5DigestAsHex(passWord.getBytes());
        userCondition.createCriteria().andEqualTo("userName", userName).andEqualTo("password", pwd);
        return this.findByCondition(userCondition);
    }


    @Override
    @Transactional
    public void registUser(SysUser user) {

    }

    @Override
    public SysUser findByName(String username) {
        SysUser user = new SysUser();
        user.setUserName(username);
        return sysUserMapper.selectOne(user);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void addUser(SysUser user, String[] roles) {
        SysUser user1 = this.findByName(user.getUserName());
        if (user1 != null) {
            throw new ServiceException("用户已存在");
        }
        user.setId(UUID.randomUUID().toString());
        user.setCreateTime(new Date());
        user.setTheme(SysUser.DEFAULT_THEME);
        user.setAvatar(SysUser.DEFAULT_AVATAR);
        //user.setPassword(MD5Utils.encrypt(user.getUserName(), user.getPassword()));
        String strByMD5 = "";

        if (StringUtils.isNotBlank(user.getPassword())) {
            strByMD5 = ShiroUtils.getStrByMD5(user.getPassword());
        } else {
            //默认6个1
            strByMD5 = ShiroUtils.getStrByMD5("111111");
        }
        user.setPageSize(Long.parseLong("10"));
        user.setPassword(strByMD5);
        this.save(user);
        //setUserRoles(user, roles);
    }

    @Override
    public void updateTheme(String theme, String userName) {

    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void deleteUsers(String ids) {
        this.batchDelete(ids, "id", SysUser.class);

        //this.userRoleService.deleteUserRolesByUserId(ids);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void updateUser(SysUser user, String[] rolesSelect) {
        user.setPassword(null);
        user.setUserName(null);
        user.setCreateTime(null);
        this.updateNotNull(user);
        Example example = new Example(SysUserRole.class);
        example.createCriteria().andCondition("user_id =", user.getId());
        this.sysUserRoleMapper.deleteByExample(example);
        setUserRoles(user, rolesSelect);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Boolean updateDisabled(String id, boolean b) {
        SysUser adminUser = new SysUser();
        adminUser.setId(id);
        adminUser.setStatus(SysUser.STATUS_LOCK);
        return sysUserMapper.updateDisabled(adminUser);
    }

    @Override
    public SysUserRoleDto findById(String id) {
        List<SysUserRoleDto> list = this.sysUserMapper.findUserWithRole(id);
        List<String> roleList = list.stream().map(SysUserRoleDto::getRoleId).collect(Collectors.toList());
        if (list.isEmpty()) {
            return null;
        }
        SysUserRoleDto userWithRole = list.get(0);
        userWithRole.setRoleIds(roleList);
        return userWithRole;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void updatePwd(SysUser principal, String password) {
        String passwordStr = password.trim();
        if (!StringUtils.equals(principal.getPassword(), passwordStr)) {
            String strByMD5 = ShiroUtils.getStrByMD5(passwordStr);
            principal.setPassword(strByMD5);
            SysUser user = new SysUser();
            user.setId(principal.getId());
            user.setPassword(strByMD5);
            this.update(user);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void updateConfig(SysUser principal, String pageSize) {
        principal.setPageSize(Long.parseLong(pageSize));
        SysUser user = new SysUser();
        user.setId(principal.getId());
        user.setPageSize(Long.parseLong(pageSize));
        this.update(user);
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    private void setUserRoles(SysUser user, String[] roles) {
        Arrays.stream(roles).forEach(roleId -> {
            SysUserRole ur = new SysUserRole();
            ur.setId(UUID.randomUUID().toString());
            ur.setUserId(user.getId());
            ur.setRoleId(roleId);
            this.sysUserRoleMapper.insert(ur);
        });
    }
}
