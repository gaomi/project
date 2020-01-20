package com.company.project.module.eam.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EamMapper {
    /**
     * 批量EAM插入人员
     *
     * @param subList
     */
    void batchSavePerson(List<Map> subList);

    /**
     * 批量插入部门
     *
     * @param subList
     */
    void batchSaveDepart(List<Map> subList);

    /**
     * 批量插入设备
     *
     * @param subList
     */
    void batchSaveEquip(List<Map> subList);

    /**
     * 批量插入病害
     *
     * @param subList
     */
    void batchSaveFault(List<Map> subList);

    /**
     * 批量插入工单
     *
     * @param subList
     */
    void batchSaveWorkOrder(List<Map> subList);

    /**
     * 删除关键字的表数据
     *
     * @param tableName
     */
    @Delete("delete from ${tableName} ")
    void deleteData(@Param("tableName") String tableName);


    /**
     * 单个插入，用作测试
     *
     * @param map
     */
    void insertData(Map map);
}
