package com.company.project.module.data.service;

import com.company.project.core.Service;
import com.company.project.core.model.Tree;
import com.company.project.module.data.model.TdDict;

import java.util.List;
import java.util.Map;


/**
 * Created by paodingsoft.chen on 2019/08/15.
 */
public interface TdDictService extends Service<TdDict> {

    List<Map> getDictColor();

    void insertApp(Map param);

    List<Map> getHealthMarck();

    List<Map> getHealthWeight();

    /**
     * 字典第一二级
     */
    List findTreeDicts();

    /**
     * 字典第一二级
     */
    Tree findTreeDicts(String flag);

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
