package com.company.project.module.data.dao;

import com.company.project.core.Mapper;
import com.company.project.module.data.model.TdSandy;

import java.util.List;
import java.util.Map;

public interface TdSandyMapper extends Mapper<TdSandy> {
    void batchSaveSandyByImport(List<Map> list);

    List<Map> selectBySegment(Map params);

    /***
     * 获取区间沙性图环号
     * @param params
     * @return
     */
    List<Map> getProjectSegment(Map params);

    /**
     * 添加土层信息
     * @param tdSandy
     */
    void saveSandy(TdSandy tdSandy);

    /***
     * 查找土层信息的所有区间
     * @return
     */
    List<Map> getSandySelectSegment();
}