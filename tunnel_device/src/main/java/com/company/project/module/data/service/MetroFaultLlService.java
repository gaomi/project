package com.company.project.module.data.service;

import com.company.project.core.Service;
import com.company.project.module.data.model.MetroFaultLl;

import java.util.List;
import java.util.Map;


/**
 * Created by paodingsoft.chen on 2019/09/25.
 */
public interface MetroFaultLlService extends Service<MetroFaultLl> {

    List<MetroFaultLl> getBhLvli(Map params);

    List<Map> getBhBiHuan();
}
