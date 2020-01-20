package com.company.project.module.api.service;

import com.company.project.core.Service;
import com.company.project.module.api.model.AqbhqProject;
import com.company.project.module.api.model.OperParam;

import java.util.List;
import java.util.Map;


/**
 * Created by paodingsoft.chen on 2019/05/27.
 */
public interface ApiAqbhqService extends Service<AqbhqProject> {
    /**
     * 项目信息接口
     *
     * @return
     */
    boolean getProjectInfo();

    /**
     * 获取项目工况列表信息
     *
     * @param id
     * @return
     */

    Map getProjectInCondition(String id);

    /**
     * 人员信息接口
     *
     * @return
     */
    List<Map> getAllPersonInfo(Map map);

    /**
     * 人员巡查轨迹接口
     *
     * @return
     */
    List<Map> getProwlerLocus();

    /**
     * 项目信息更新接收接口
     *
     * @return
     */
    void updateByAqbhq(AqbhqProject aqbhqProject);

    AqbhqProject getProjectInfoById(String id);

    List<Map> selectAll(Map param);

    /**
     * 获取人员定位信息接口
     *
     * @return
     */
    List<Map> getPersonsLocation();

    List<Map> getProjectIdBySegment(String[] id);


    /**
     * 操作项目
     *
     * @param operParam
     */
    void operationByAqbhq(OperParam operParam);


    void operationByAqbhq(AqbhqProject aqbhq, String state);

    List<Map> GetPatrollerByTime(Map map);

    /**
     * 更新token
     *
     * @param code
     */
    void changeToken(String code);

    int getAqbhqMatk(Map map);

    /***
     *添加所有项目坐标
     */
    boolean insertAllCoordinates();

    /***
     * 获取项目区间
     * @param id
     * @return
     */
    List<AqbhqProject> getProjectBySegment(String[] id);

    /***
     * 获取全部安全保护区病害
     */
    List<Map> selectAqbhqInfo();

    /***
     * 更新全部巡检人员
     * @return
     */
    boolean updateAllPersonInfo();

    Map getDictCheck();
}
