package com.company.project.module.sys.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.core.exception.ServiceException;
import com.company.project.core.model.Tree;
import com.company.project.core.util.PaodingUtils;
import com.company.project.core.util.TreeUtils;
import com.company.project.module.sys.dao.SysMenuMapper;
import com.company.project.module.sys.dao.SysRoleMapper;
import com.company.project.module.sys.dao.SysRoleMenuMapper;
import com.company.project.module.sys.model.SysMenu;
import com.company.project.module.sys.model.SysRole;
import com.company.project.module.sys.model.SysRoleMenu;
import com.company.project.module.sys.model.SysUser;
import com.company.project.module.sys.model.dto.SysRoleMenuDto;
import com.company.project.module.sys.service.SysRoleMenuService;
import com.company.project.module.sys.service.SysRoleService;
import com.company.project.module.sys.service.SysUserRoleService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by chen on 2019/05/21.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class SysRoleServiceImpl extends AbstractService<SysRole> implements SysRoleService {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Resource
    private SysRoleMapper sysRoleMapper;

    @Resource
    private SysMenuMapper sysMenuMapper;
    @Resource
    private SysRoleMenuMapper sysRoleMenuMapper;
    @Resource
    private SysRoleMenuService sysRoleMenuService;
    @Resource
    private SysUserRoleService sysUserRoleService;

    @Override
    public List<SysRole> findUserRole(String userName) {
        return this.sysRoleMapper.findUserRole(userName);
    }

    @Override
    public List<SysRole> findAllRole(SysRole role) {
        try {
            Example example = new Example(SysRole.class);
            if (StringUtils.isNotBlank(role.getName())) {
                example.createCriteria().andCondition("name like ", "%" + role.getName() + "%");
            }
            example.setOrderByClause("create_time");
            return this.findByExample(example);
        } catch (Exception e) {
            log.error("获取角色信息失败", e);
            return Lists.newArrayList();
        }
    }

    @Override
    public SysRoleMenuDto findRoleWithMenus(String id) {
        List<SysRoleMenuDto> list = this.sysRoleMapper.findDtoById(id);
        List<String> menuList = list.stream().map(SysRoleMenuDto::getMenuId).collect(Collectors.toList());
        if (list.isEmpty()) {
            return null;
        }
        SysRoleMenuDto roleWithMenu = list.get(0);
        roleWithMenu.setMenuIds(menuList);
        return roleWithMenu;
    }

    @Override
    public SysRole findByName(String roleName) {
        Example example = new Example(SysRole.class);
        example.createCriteria().andCondition("lower(name)=", roleName.toLowerCase());
        List<SysRole> list = this.findByExample(example);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void deleteRoles(String roleIds) {
        this.batchDelete(roleIds, "id", SysRole.class);
        this.sysRoleMenuService.deleteRoleMenusByRoleId(roleIds);
        this.sysUserRoleService.deleteUserRolesByRoleId(roleIds);
    }

    @Override
    public Set<String> findRoleByUserId(String userId) {
        return sysRoleMapper.findRoleByUserId(userId);
    }

    @Override
    public List<Tree<SysMenu>> getRoleMenuTree(String id, SysUser user) {
        List<SysMenu> menus = Lists.newArrayList();
//        if (StringUtils.equals(user.getId(), "1")) {
           //Todo //管理员
            menus = this.sysMenuMapper.selectAll();
//        } else {
//            menus = this.sysMenuMapper.findUserMenuByUserId(user.getId());
//            //menus = this.sysRoleMenuMapper.findRoleMenu(user.getId());
//        }
        HashMap<String, String> map = Maps.newHashMap();
        Example example = new Example(SysRoleMenu.class);
        example.createCriteria().andCondition(" role_id = ", id);
        List<SysRoleMenu> userMenuIds = this.sysRoleMenuMapper.selectByExample(example);
        for (SysRoleMenu roleMenu : userMenuIds) {
            map.put(roleMenu.getMenuId(), roleMenu.toString());
        }
        List<Tree<SysMenu>> trees = new ArrayList<>();
        menus.stream().forEach(menu -> {
            if (menu != null) {
                Tree<SysMenu> tree = new Tree<>();
                tree.setId(menu.getId());
                tree.setParentId(menu.getParentId());
                tree.setText(menu.getName());
                tree.setAttributes((Map<String, Object>) PaodingUtils.objectToMap(menu));
                tree.setChecked(map.containsKey(menu.getId()) ? true : false);
                trees.add(tree);
            }
        });
        return TreeUtils.buildList(trees, "0");
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void addRole(SysRole role, String[] menuId) {
        SysRole role1 = this.findByName(role.getName());
        if (role1 != null) {
            throw new ServiceException("角色已存在");
        }
        String id = UUID.randomUUID().toString();
        role.setId(id);
        role.setCreateTime(new Date());
        setRoleMenus(role, menuId);
        this.save(role);

    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void updateRole(SysRole role, String[] menuId) {
        role.setOperateTime(new Date());
        SysRole role1 = this.findByName(role.getName());
        if (null != role1 && (!StringUtils.equals(role1.getId(), role.getId()))) {
            throw new ServiceException("角色名已存在");
        }
        this.updateNotNull(role);
        setRoleMenus(role, menuId);
    }

    private void setRoleMenus(SysRole role, String[] menuIds) {
        Example example = new Example(SysRoleMenu.class);
        example.createCriteria().andCondition("role_id = ", role.getId());
        this.sysRoleMenuMapper.deleteByExample(example);
        Arrays.stream(menuIds).forEach(menuId -> {
            SysRoleMenu rm = new SysRoleMenu();
            rm.setId(UUID.randomUUID().toString());
            rm.setMenuId(menuId);
            rm.setRoleId(role.getId());
            this.sysRoleMenuMapper.insert(rm);
        });
    }

}
