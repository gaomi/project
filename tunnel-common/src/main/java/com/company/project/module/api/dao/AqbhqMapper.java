package com.company.project.module.api.dao;

import com.company.project.core.Mapper;
import com.company.project.module.api.model.AqbhqPerson;
import com.company.project.module.api.model.AqbhqProject;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface AqbhqMapper extends Mapper<AqbhqProject> {


    int getAqbhqMatk(Map map);

    @Update("delete from J302_API_AQBHQ_PROJECT ")
    void deleteAllProject();

    void batchSaveProject(List<AqbhqProject> list);

    AqbhqProject getProjectInfoById(String id);

    void updateByApi(AqbhqProject aqbhqProject);

    List<Map> getProjectIdBySegment(String id);

    List<AqbhqProject> getProjectBySegment(Map map);

    List<Map> getProjectSegment(Map map);

    /**
     * 根据区间查询数据
     */
    List<Map> selectBySegmentName(Map param);

    /**
     * 人员
     */
    @Update("delete from J302_API_AQBHQ_PERSON")
    void deleteAllPerson();

    Integer saveAllPerson(List<AqbhqPerson> list);

    @Select("SELECT * FROM J302_API_AQBHQ_PERSON")
    List<AqbhqPerson> findAllPerson();

    /**
     * 条件查询点数据
     *
     * @param params
     * @return
     */
    List<Map> findDataForMapByV(Map params);

    /***
     * 删除所有项目坐标
     */
    void deleteAllCoordinates();

    /***
     * 批量添加项目坐标
     * @param subList
     */
    void batchSaveCoordinates(@Param("list") List subList);

    /***
     * 获取安全保护区信息
     */
    List<Map> selectAqbhqInfo();

    /***
     * 获取巡检人员信息
     * @param param
     * @return
     */
    List<Map> getAllPersonInfo(Map param);
}