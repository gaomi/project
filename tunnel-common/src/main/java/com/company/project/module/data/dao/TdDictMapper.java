package com.company.project.module.data.dao;

import com.company.project.core.Mapper;
import com.company.project.module.data.model.TdDict;

import java.util.List;
import java.util.Map;

public interface TdDictMapper extends Mapper<TdDict> {
    List<Map> getDictColor();

    void insertApp(Map param);

    List<Map> getHealthMarck();

    List<Map> getHealthWeight();

    /**
     * 根据表格名称查找字典数据
     *
     * @param param
     * @return
     */
    List<TdDict> findDictDataByModule(Map param);

    /**
     * 下拉框字典
     *
     * @return
     */
    List<Map> findFaultTypeDict();

    /**
     * 手机端病害类型分类
     *
     * @return
     */
    List<Map> findFaultTypeDictForApp();

    /**
     * 手机端病害类型分类
     *
     * @return
     */
    List<Map> findFaultTypeDictForAppN();

    /**
     * 手机端字典数据，pid为0和20000，for_module='app_fault'
     *
     * @return
     */
    List findDictForApp();

    /**
     * 手机端3种门的检查项字典
     *
     * @return
     */
    List findDoorCheckItemDictForApp();

    /***
     * 字典一、二级
     * @return
     */
    List<TdDict> findTreeDicts();

    /***
     * 根据pid查询数据字典
     * @param map
     * @return
     */
    List<TdDict> findDictByPid(Map map);

    /***
     * 删除数据字典
     * @param id
     */
    void deleteDictById(String id);

    /***
     * 更新字典
     * @param tdDict
     */
    void updateDict(TdDict tdDict);
}