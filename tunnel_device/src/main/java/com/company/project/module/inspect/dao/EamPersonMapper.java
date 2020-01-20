package com.company.project.module.inspect.dao;

import com.company.project.core.Mapper;
import com.company.project.module.inspect.model.EamPerson;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EamPersonMapper extends Mapper<EamPerson> {
    /***
     * 修改密码
     * @param params
     */
    void updatePassWord(Map params);

    /***
     * 批量添加人员信息
     * @param subList
     */
    void batchSaveEamPerson(@Param("list") List<EamPerson> subList);

    /***
     * 更新人员信息
     * @param subList
     */
    void batchUpdateEamPerson(@Param("list") List<EamPerson> subList);

    /***
     * 获取所有人员信息
     * @param map
     * @return
     */
    List<EamPerson> selectPersonAll(Map map);
}