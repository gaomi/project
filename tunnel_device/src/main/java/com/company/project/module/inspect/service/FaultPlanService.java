package com.company.project.module.inspect.service;

import com.company.project.core.model.QueryRequest;
import com.company.project.module.inspect.model.FaultPlan;
import com.company.project.core.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by paodingsoft.chen on 2019/09/20.
 */
public interface FaultPlanService extends Service<FaultPlan> {
    /**
     * 根据条件查找施工单位
     *
     * @param request
     * @return
     */
    List findCompany(QueryRequest request);

    /**
     * 获取表单需要的基本字典
     *
     * @return
     */
    Map<String, List> finddict();

    /**
     * 查询施工计划
     *
     * @return
     */
    List findAllPlan();

    /**
     * 根据任务查询所有病害
     *
     * @param plan
     * @return
     */
    Map<String, List> findFaultByPlan(FaultPlan plan);

    /**
     * 根据任务上传数据
     * @param id
     * @param map
     */
    void uploadDataByPlan(String id, Map map);

    /**
     * 根据任务查询可选设备及人员
     * @param plan
     * @return
     */
    Map<String, List> findRefDetailByPlan(FaultPlan plan);

    /**
     * 更新任务的人员和设备
     * @param assignData
     */
    void updateRefByPlan(Map assignData);

    Map findDictForApp(String module);
}
