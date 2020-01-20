package com.company.project.module.data.service;

import com.company.project.core.Service;
import com.company.project.module.data.model.TdSandy;

import java.util.List;
import java.util.Map;


/**
 * Created by paodingsoft.chen on 2019/08/07.
 */
public interface TdSandyService extends Service<TdSandy> {
    void saveData(List<Map<String, Object>> list);

    /**
     * 根据条件查找
     * @param params
     * @return
     */
    List findbyParams(Map params);

    /***
     * 获取土层信息字典
     * @return
     */
    Map getSandyDict();

    /**
     * 添加土层信息
     * @param tdSandy
     */
    void saveSandy(TdSandy tdSandy);

    /***
     * 砂性土选项
     * @return
     */
    List<Map> getSandySelect();
}
