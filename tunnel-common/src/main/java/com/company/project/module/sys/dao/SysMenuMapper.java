package com.company.project.module.sys.dao;

import com.company.project.core.Mapper;
import com.company.project.module.sys.model.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface SysMenuMapper extends Mapper<SysMenu> {

    List<SysMenu> findUserPermissions(String userName);

    // 删除父节点，子节点变成顶级节点（根据实际业务调整）
    void changeToTop(List<String> menuIds);

    /**
     * 查询id最大值
     * @return
     */
    Integer selectMaxId();

    Set<String> findPermissionByUserId(String userId);

    /**
     * 根据用户Id找菜单
     *
     * @param id
     * @return
     */
    List<SysMenu> findUserMenuByUserId(String id);

    /***
     * 获取页面头部菜单
     * @return
     */
    List<SysMenu> getHeadMenu(@Param("id") String id);

    /***
     * 获取侧边栏菜单
     * @param map
     * @return
     */
    List<Map> getAsideMenu(Map map);
}