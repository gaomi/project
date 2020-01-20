package com.company.project.module.data.service;
import com.company.project.module.data.model.TdDuct;
import com.company.project.core.Service;
import com.company.project.module.data.model.TdSegment;

import java.util.List;
import java.util.Map;


/**
 * Created by paodingsoft.chen on 2019/08/22.
 */
public interface TdDuctService extends Service<TdDuct> {

    void updateMileageBySegment(TdSegment tdSegment);

    /***
     * 查询环号
     * @param params
     * @return
     */
    List<Map> selectDuct(Map params);
}
