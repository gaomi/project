package com.company.project.module.data.dao;

import com.company.project.core.Mapper;
import com.company.project.module.data.model.MetroFaultLl;

import java.util.List;
import java.util.Map;

public interface MetroFaultLlMapper extends Mapper<MetroFaultLl> {
    List<MetroFaultLl> getBhLvli(Map params);

    List<Map> getBhBiHuan();
}