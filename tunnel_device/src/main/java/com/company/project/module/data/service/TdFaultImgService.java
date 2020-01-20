package com.company.project.module.data.service;

import com.company.project.core.Service;
import com.company.project.module.data.model.TdFaultImg;

import java.util.List;
import java.util.Map;


/**
 * Created by paodingsoft.chen on 2019/08/20.
 */
public interface TdFaultImgService extends Service<TdFaultImg> {

    List<Map> getImageById(String lineCode, String ctx, Map ids);

    void saveAll(List<TdFaultImg> list);
}
