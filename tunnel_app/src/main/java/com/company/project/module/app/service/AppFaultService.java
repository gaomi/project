package com.company.project.module.app.service;

import com.company.project.core.Service;
import com.company.project.module.inspect.model.FaultPlan;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;


/**
 * Created by paodingsoft.chen on 2019/09/20.
 */
public interface AppFaultService extends Service<FaultPlan> {
    /**
     * 手机端查询施工计划
     *
     * @param map
     * @param employeeId
     * @return
     */
    List findAllPlanForApp(Map map, String employeeId);

    /**
     * 手机端查询员工下发施工计划
     *
     * @return
     */
    List findXfPlanForApp(String employeeNo) ;

    /**
     * 手机端根据工单id查询所有病害
     *
     * @param id
     * @return
     */
    Map<String, Object> findDetailByPlanForApp(String id);

    /**
     * 修改任务状态
     * @param params
     */
    void changePlanStatus(Map params) throws Exception;

    /**
     * 任务上传
     * @param params
     * @return
     */
    String uploadPlanData(Map params, String employeeNo);
}
