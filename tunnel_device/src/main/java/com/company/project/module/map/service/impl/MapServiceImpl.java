package com.company.project.module.map.service.impl;

import com.company.project.module.api.dao.AqbhqMapper;
import com.company.project.module.api.dao.JhjcMapper;
import com.company.project.module.map.service.MapService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Chen
 * @created 2019-11-13-10:41.
 */
@Service
public class MapServiceImpl implements MapService {
    @Resource
    private AqbhqMapper aqbhqMapper;
    @Resource
    private JhjcMapper jhjcMapper;

    @Override
    public List<Map> getAqbhqDataForMap(Map params) throws Exception {
        if (params.isEmpty()) {
            params.put("LINE_UUID", "");
        }
        List<Map> result = aqbhqMapper.findDataForMapByV(params);
        return result;
    }

    @Override
    public List<Map> getJhjcDataForMap(Map params) throws Exception {
        if (params.isEmpty()) {
            params.put("LINE_UUID", "");
        }
        List<Map> result = jhjcMapper.findDataForMapByV(params);
        return result;
    }
}
