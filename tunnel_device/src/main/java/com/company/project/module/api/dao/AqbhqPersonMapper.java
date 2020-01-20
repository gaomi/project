package com.company.project.module.api.dao;

import com.company.project.core.Mapper;
import com.company.project.module.api.model.AqbhqPerson;

import java.util.List;
import java.util.Map;

public interface AqbhqPersonMapper extends Mapper<AqbhqPerson> {


    List<Map> getAllPersonInfo(Map param);
}