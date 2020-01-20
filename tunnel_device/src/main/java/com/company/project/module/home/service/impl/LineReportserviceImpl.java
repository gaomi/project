package com.company.project.module.home.service.impl;

import com.company.project.module.data.dao.MetroFaultMapper;
import com.company.project.module.home.service.LineReportService;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Chen
 * @created 2019-11-30-13:35.
 */
@Service
public class LineReportserviceImpl implements LineReportService {

    @Resource
    private MetroFaultMapper metroFaultMapper;

    @Override
    public Map getDiseDesc(Map params) {
        Map result = Maps.newHashMap();
        Map param = Maps.newHashMap();
        param.put("faultLeve", "AA");
        param.put("statis", "all");
        param.put("status", "20");
        List<Map> AllAnalyze = metroFaultMapper.getDiseAnalyze(param);
        List<Map> Storage = metroFaultMapper.newFault(param);
        List<String> stringList = Arrays.asList("week", "month", "year");
        for (String item : stringList) {
            Map item_Statis = Maps.newHashMap();
            param.put("statis", item);
            param.put("status", "20");
            List<Map> add = metroFaultMapper.getDiseAnalyze(param);
            List<Map> newFault = metroFaultMapper.newFault(param);
            param.put("status", "60");
            List<Map> delete = metroFaultMapper.getDiseAnalyze(param);
            List<Map> mainTain = metroFaultMapper.newFault(param);
            Map statisMap = Maps.newHashMap();
            statisMap.put("Add", add);
            statisMap.put("Delete", delete);
            statisMap.put("All", AllAnalyze);
            item_Statis.put("statis", statisMap);
            item_Statis.put("newFault", newFault);
            item_Statis.put("Maintain", mainTain);
            item_Statis.put("Storage", Storage);
            result.put(item, item_Statis);
        }
        return result;
    }
}
