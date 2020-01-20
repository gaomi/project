package com.company.project.module.inspect.dao;

import com.company.project.core.Mapper;
import com.company.project.module.inspect.model.FaultPlan;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface FaultPlanMapper extends Mapper<FaultPlan> {

    /**
     * pc查询任务
     *
     * @return
     */
    List findAllPlan();

    List findAllFaultPlanForWeb(Map map);

    /**
     * 根据任务查询所有病害
     *
     * @param uuid
     * @return
     */
    List findFaultByPlan(String uuid);

    /**
     * 根据id 查询视图
     *
     * @param uuid
     * @return
     */
    Map findVPlanDetailById(String uuid);

    /**
     * 根据id查设备及设备检查项
     *
     * @param uuid
     * @return
     */
    List<Map> findDeviceListByPlan(String uuid);

    /**
     * 根据任务查询可分配设备和人员
     *
     * @param plan
     * @return
     */
    List<Map> findRefDetailByPlan(FaultPlan plan);

    /**
     * 给任务添加人员
     *
     * @param operatorMaps
     */
    void saveOperatorByPlan(HashMap<Object, Object> operatorMaps);

    /**
     * 给任务添加设备
     *
     * @param deviceMaps
     */
    void saveDeviceByPlan(HashMap<Object, Object> deviceMaps);


    /**
     * 删除任务的操作人
     *
     * @param uuid
     */
    void deleteOperatorByPlan(String uuid);

    /**
     * 删除任务的设备
     *
     * @param uuid
     */
    void deleteDeviceByPlan(String uuid);


    /**
     * 手机端查询任务
     *
     * @param map
     * @return
     */
    List findAllPlanForApp(Map map);


    /**
     * 手机端根据id 查询任务明细
     *
     * @param uuid
     * @return
     */
    Map findVPlanDetailByIdForApp(String uuid);

    /**
     * 手机端根据id查设备及设备检查项
     *
     * @param uuid
     * @return
     */
    List<Map> findDeviceListByPlanForApp(String uuid);

    /**
     * 手机端根据任务查询所有病害
     *
     * @param uuid
     * @return
     */
    List findFaultByPlanForApp(String uuid);

    /**
     * 手机端查询员工下发施工计划
     *
     * @param employeeNo
     * @return
     */
    List findXfPlanForApp(String employeeNo);

    /**
     * 手机端字典数据，pid为0和20000，for_module='app_fault'
     *
     * @return
     */
    List findDictForApp();

    List findCompany(Map map);


    /**
     * 根据任务查询所有病害
     *
     * @param plan
     * @return
     */
    List findFaultByPlan(FaultPlan plan);

    /**
     * 根据任务id查可选设备
     *
     * @param plan
     * @return
     */
    List findDeviceByPlan(FaultPlan plan);

    /**
     * 根据任务id查可选人员
     *
     * @param plan
     * @return
     */
    List findOperatorByPlan(FaultPlan plan);

    /**
     * 根据任务添加设备、人员数据
     *
     * @param assignData
     */
    void updateRefByPlan(Map assignData);

    /***
     * 根据工单号获取设备列表
     * @param uuid
     * @return
     */
    List<Map> findDeviceListByPlanID(String uuid);

    /**
     * 根据工单获取病害数据
     *
     * @param uuid
     * @return
     */
    List<Map> getFaultListForApp(String uuid);

    /***
     * 获取门的信息
     * @param params
     * @return
     */
    List<Map> getDoorListForApp(Map params);

    /**
     * 查询任务站列表
     * @param uuid
     * @return
     */
    List findPlanStationByPlanID(String uuid);
}