package com.company.project.module.data.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.module.data.dao.MetroFaultMapper;
import com.company.project.module.data.model.MetroFault;
import com.company.project.module.data.service.MetroFaultService;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by paodingsoft.chen on 2019/07/19.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class MetroFaultServiceImpl extends AbstractService<MetroFault> implements MetroFaultService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private MetroFaultMapper metroFaultMapper;

    @Value("${fault-image.path}")
    private String ServicePath;

    @Override
    public void createNewFault(List<Map> siData) {

    }

    @Override
    public List<Map> selectAll(Map params) {
//        Condition condition = new Condition(MetroFault.class);
//        Example.Criteria criteria = condition.createCriteria();
//        for (Object key : params.keySet()) {
//            if(params.get(key) == null){
//                continue;
//            }
//            if(((String) key).equals("duct")){
//                criteria.andCondition("to_number(trim(DUCT_CODE)) >=",params.get(key) ).andCondition("to_number(trim(DUCT_CODE2)) <=",params.get(key));
//            }else if(((String) key).equals("startDuct")){
//                criteria.andCondition("to_number(trim(DUCT_CODE)) >=",params.get(key) );
//            }else if(((String) key).equals("endDuct")){
//                criteria.andCondition("to_number(trim(DUCT_CODE2)) <=",params.get(key));
//            }else{
//                criteria.andLike((String) key, params.get(key) + "%");
//            }
//        }
//        condition.orderBy("recCreateTime");
//        return metroFaultMapper.selectByCondition(condition);
        return metroFaultMapper.selectByParam(params);
    }

    @Override
    public Map getDiseTop(String ctx, String time) {
        Map map = Maps.newHashMap();
        Map params = Maps.newHashMap();
        params.put("level", "A");
        params.put("time", time);
        List<Map> listA = metroFaultMapper.getDiseTop(params);
        for (Map item : listA) {
            if (item.get("IMAGE_URL") != null) {
                item.put("IMAGE_URL", ServicePath + item.get("LINE_ID") + "/" + item.get("IMAGE_URL"));
            }
        }
        map.put("A", listA);
        params.put("level", "AA");
        List<Map> listAA = metroFaultMapper.getDiseTop(params);
        for (Map item : listAA) {
            if (item.get("IMAGE_URL") != null) {
                item.put("IMAGE_URL", ServicePath + item.get("LINE_ID") + "/" + item.get("IMAGE_URL"));
            }
        }
        map.put("AA", listAA);
        return map;
    }

    @Override
    public List<Map> findDiseDuct(String line) {
        return metroFaultMapper.findDiseDuct(line);
    }

    @Override
    public List<Map> getDiseStatis(String data) {
        return metroFaultMapper.getDiseStatis(data);
    }

    @Override
    public List<Map> getSegmentStatis(Map params) {
        return metroFaultMapper.getSegmentStatis(params);
    }

    @Override
    public List<Map> getDiseAnalyze(Map params) {
        return metroFaultMapper.getDiseAnalyze(params);
    }

    @Override
    public List<Map> newFault(Map params) {
        return metroFaultMapper.newFault(params);
    }

    @Override
    public List<Map> selectTest() {
        return metroFaultMapper.selectTest();
    }

    @Override
    public Integer updateTest(Map params) {
        return metroFaultMapper.updateTest(params);
    }

    @Override
    public List<String> getFaultLoc() {
        return metroFaultMapper.getFaultLoc();
    }

    @Override
    public List<Map> getFaultMark(Map param) {
        return metroFaultMapper.getFaultMark(param);
    }


    @Override
    public List<Map> getFaultByStaus(Map params) {
        return metroFaultMapper.getFaultByStaus(params);
    }

}
