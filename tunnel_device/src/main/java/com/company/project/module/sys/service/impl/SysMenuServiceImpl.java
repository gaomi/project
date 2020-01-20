package com.company.project.module.sys.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.core.exception.ServiceException;
import com.company.project.core.model.Tree;
import com.company.project.core.util.PaodingUtils;
import com.company.project.core.util.TreeUtils;
import com.company.project.module.sys.dao.SysMenuMapper;
import com.company.project.module.sys.model.SysMenu;
import com.company.project.module.sys.model.SysUser;
import com.company.project.module.sys.service.SysMenuService;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import javax.annotation.Resource;
import java.util.*;


/**
 * Created by chen on 2019/05/21.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class SysMenuServiceImpl extends AbstractService<SysMenu> implements SysMenuService {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Resource
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> findUserPermissions(String userName) {
        return this.sysMenuMapper.findUserPermissions(userName);
    }

    @Override
    public SysMenu findByNameAndType(String menuName, String type) {
        return null;
    }

    @Override
    public List<SysMenu> findAllMenus(SysMenu menu) {
        try {
            Example example = new Example(SysMenu.class);
            Criteria criteria = example.createCriteria();
            if (StringUtils.isNotBlank(menu.getName())) {
                criteria.andCondition("Name like", "%" + menu.getName() + "%");
            }
            if (menu.getType() != null && StringUtils.isNotBlank(menu.getType().toString())) {
                criteria.andCondition("TYPE =", menu.getType().toString());
            }
            example.setOrderByClause("id");
            List<SysMenu> byExample = this.findByExample(example);
            return byExample;
        } catch (NumberFormatException e) {
            log.error("error", e);
            return new ArrayList<>();
        }
    }

    @Override
    @Transactional
    public void addMenu(SysMenu menu) {
        menu.setOperateTime(new Date());
        if (menu.getParentId() == null || menu.getParentId() == "") {
            menu.setParentId(0 + "");
            menu.setMenuLevel(0 + "");
        } else {
            SysMenu pMenu = new SysMenu();
            pMenu.setId(menu.getParentId());
            SysMenu pMenu1 = this.sysMenuMapper.selectByPrimaryKey(pMenu);
            menu.setMenuLevel(pMenu1.getMenuLevel() + "." + menu.getParentId());
        }

        if (SysMenu.TYPE_BUTTON.equals(menu.getType())) {
            menu.setUrl(null);
            menu.setIcon(null);
        }
        Integer maxId = this.sysMenuMapper.selectMaxId();
        menu.setId((maxId + 1) + "");
        this.save(menu);
    }

    @Override
    @Transactional
    public void deleteMeuns(String ids) {
        Example example = new Example(SysMenu.class);
        example.createCriteria().andEqualTo("parentId", ids);

        List<SysMenu> sysMenus = sysMenuMapper.selectByExample(example);
        if (sysMenus.size()>0){
            throw new ServiceException("包含子菜单,不能删除");
        }
        this.batchDelete(ids, "id", SysMenu.class);
        //sysRoleMenuService.deleteRoleMenusByMenuId(ids);
        List<String> list = Arrays.asList(ids.split(","));
        //sysMenuMapper.changeToTop(list);
    }

    @Override
    @Transactional
    public void updateMenu(SysMenu menu) {
        menu.setOperateTime(new Date());
        if (menu.getParentId() == null || menu.getParentId() == "") {
            menu.setParentId(0 + "");  menu.setMenuLevel(0 + "");
        }else {
            SysMenu pMenu = new SysMenu();
            pMenu.setId(menu.getParentId());
            SysMenu pMenu1 = this.sysMenuMapper.selectByPrimaryKey(pMenu);
            menu.setMenuLevel(pMenu1.getMenuLevel() + "." + menu.getParentId());
        }
        if (SysMenu.TYPE_BUTTON.equals(menu.getType())) {
            menu.setUrl(null);
            menu.setIcon(null);
        }
        this.updateNotNull(menu);
    }

    @Override
    public Set<String> findPermissionByUserId(String userId) {

        return sysMenuMapper.findPermissionByUserId(userId);
    }

    @Override
    public List<Tree<SysMenu>> getMenuTree(SysUser user) {
        List<Tree<SysMenu>> trees = new ArrayList<>();
        List<SysMenu> menus = Lists.newArrayList();
        //todo 改为角色
        if ("1".equals(user.getId())) {
            menus = this.sysMenuMapper.selectAll();
        } else {
            menus = this.sysMenuMapper.findUserMenuByUserId(user.getId());
        }
        menus.forEach(menu -> {
            Tree<SysMenu> tree = new Tree<>();
            tree.setId(menu.getId());
            tree.setParentId(menu.getParentId());
            tree.setText(menu.getName());
            tree.setAttributes((Map<String, Object>) PaodingUtils.objectToMap(menu));
            trees.add(tree);
        });
        return TreeUtils.buildList(trees, "0");
    }

    @Override
    public List<SysMenu> getHeadMenu(String userId) {
        return sysMenuMapper.getHeadMenu(userId);
    }

    @Override
    public List<Map> getAsideMenu(Map map) {
        return sysMenuMapper.getAsideMenu(map);
    }
}
