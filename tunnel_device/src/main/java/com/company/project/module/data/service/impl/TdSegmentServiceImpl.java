package com.company.project.module.data.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.company.project.core.AbstractService;
import com.company.project.core.ProjectConstant;
import com.company.project.core.annotation.VerifyRedis;
import com.company.project.core.util.redis.RedisUtil;
import com.company.project.module.data.dao.TdSegmentMapper;
import com.company.project.module.data.model.TdSegment;
import com.company.project.module.data.service.TdDuctService;
import com.company.project.module.data.service.TdSegmentService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by paodingsoft.chen on 2019/08/06.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TdSegmentServiceImpl extends AbstractService<TdSegment> implements TdSegmentService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private TdSegmentMapper tdSegmentMapper;
    @Resource
    private TdDuctService tdDuctService;

    @Resource
    private RedisUtil redisUtil;

    @Override
    public List findNoGroupSegmentV(Map<String, String> map) {
        return tdSegmentMapper.findNoGroupSegmentV(map);
    }

    @Override
    public List findBaseDataByV(Map map) {

        return tdSegmentMapper.findBaseDataByV(map);
    }

    @Override
    public List findAllSegment(Map params) {

        return tdSegmentMapper.findAllSegmentByV(params);
    }

    @Override
    @VerifyRedis(ProjectConstant.LINE_SEGMENT_KEY)
    public List<Map> findSegmentInfoForApp(Map params) {
        List<Map> result = getDuplicateSegmentList(tdSegmentMapper.findSegmentInfoForApp(params));
        redisUtil.set(ProjectConstant.LINE_SEGMENT_KEY, JSONObject.toJSONString(result), 24 * 60 * 60);
        return result;
    }

    @Override
    @Transactional
    public void updateSegmentMileage(TdSegment tdSegment) {
        TdSegment tdSegment1 = tdSegmentMapper.selectByPrimaryKey(tdSegment.getUuid());
            tdSegmentMapper.updateSegmentFleage(tdSegment);
        if(!tdSegment.getStartMileageCode().equals(tdSegment1.getStartMileageCode())
            || !tdSegment.getEndMileageCode().equals(tdSegment1.getEndMileageCode())){
            tdSegment.setStartDuctCode(tdSegment1.getStartDuctCode());
            tdSegment.setEndDuctCode(tdSegment1.getEndDuctCode());
            tdDuctService.updateMileageBySegment(tdSegment);
        }
    }

    @Override
    public List<Map> getGroupSegment(Map params) {
        return tdSegmentMapper.getGroupSegment(params);
    }

    @Override
    public List<Map> getSegmentByGroup(Map params) {
        return tdSegmentMapper.getSegmentByGroup(params);
    }

    @Override
    public void updateSegment(List<Map> param) {
        tdSegmentMapper.updateSegment(param);
    }

    /**
     * 整合线路及站，区间
     *
     * @return list2
     */
    public List<Map> getDuplicateSegmentList(List<Map> list2) {
        Map<String, Object> segment = Maps.newHashMap();
        list2.forEach(map -> {
//            String uuidList = ToStringUtil.getIds((List)map.get("UUID_LIST"));
//            map.put("UUID_LIST",uuidList);
            if (segment.containsKey(map.get("LINE_UUID"))) {
                Map itemLine = (Map) segment.get(map.get("LINE_UUID"));
                if (itemLine.containsKey(map.get("UPDOWN"))) {
                    List itemList = (List) itemLine.get(map.get("UPDOWN"));
                    itemList.add(map);
                } else {
                    List mapList = Lists.newArrayList();
                    mapList.add(map);
                    itemLine.put(map.get("UPDOWN"), mapList);
                }
            } else {
                Map itemLine = Maps.newHashMap();
                List mapList = Lists.newArrayList();
                mapList.add(map);
                itemLine.put(map.get("UPDOWN"), mapList);
                itemLine.put("LINE_NAME", map.get("LINE_NAME"));
                itemLine.put("LINE_UUID", map.get("LINE_UUID"));
                itemLine.put("UUID_LIST", map.get("UUID_LIST"));
                itemLine.put("LINE_CODE", map.get("LINE_CODE"));
                itemLine.put("LINE_COLOR", map.get("LINE_COLOR"));
                segment.put((String) map.get("LINE_UUID"), itemLine);
            }
        });
        List<Map> result = Lists.newArrayList();
        for (String key : segment.keySet()) {
            result.add((Map) segment.get(key));
        }
        return result;
    }
}
