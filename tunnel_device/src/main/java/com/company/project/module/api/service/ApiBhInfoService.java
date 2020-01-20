package com.company.project.module.api.service;

import com.company.project.core.Service;
import com.company.project.module.api.model.BhInfo;

import java.util.List;
import java.util.Map;


/**
 * Created by paodingsoft.chen on 2019/07/08.
 */
public interface ApiBhInfoService extends Service<BhInfo> {

    List<BhInfo> selectAll(Map params);
}
