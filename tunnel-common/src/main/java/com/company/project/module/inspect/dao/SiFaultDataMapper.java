package com.company.project.module.inspect.dao;

import com.company.project.core.Mapper;
import com.company.project.module.inspect.model.SiFaultData;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface SiFaultDataMapper extends Mapper<SiFaultData> {
    /**
     * 批量插入病害
     *
     * @param param
     */
    Integer batchSaveNewFault(Map param);

    /**
     * 批量插入病害状态
     *
     * @param subList
     */
    void batchSaveFaultStatus(List<Map> subList);

    /**
     * 查询所有病害记录
     *
     * @param params
     * @return
     */
    List findAllFaultForWebByV(Map params);

    /***
     * 查找审核（入库）病害
     * @param params
     * @return
     */
    List<Map> getCheckFault(Map params);

    /***
     * 保存审核后的病害
     * @param subList
     */
    void saveAllSiFault(List<Map> subList);

    /***
     * 更新操作（添加最新状态）
     * @param subList
     */
    Integer updateFault(List<Map> subList);

    /***
     * 保存图片
     * @param imageList
     */
    void saveResData(List<Map> imageList);

    /***
     *
     * @param imageList
     */
    void saveDeviceRes(List<Map> imageList);

    /***
     * 更新检查项
     * @param deviceIdSet
     */
    void updateDeviceItem(@Param("set") Set deviceIdSet);

    /***
     * 更新工单已完成
     * @param newHashMap
     */
    void updateFaultPlan(Map newHashMap);

    /***
     * 根据工单查找病害
     * @param uuid
     * @return
     */
    List<Map> getFaultByWorkOrder(String uuid);


    /***
     * 批量保存审核后的病害 抽象调用
     * @param subList
     */
    void batchSaveAllSiFault(List<Map> subList);

    /***
     * 根据病害内码获取履历
     * @param code
     * @return
     */
    List<SiFaultData> findByInternalCode(String code);
}

