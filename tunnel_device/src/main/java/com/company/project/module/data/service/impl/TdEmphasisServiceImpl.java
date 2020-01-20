package com.company.project.module.data.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.module.data.dao.TdEmphasisMapper;
import com.company.project.module.data.dao.TdLineMapper;
import com.company.project.module.data.model.TdEmphasis;
import com.company.project.module.data.model.TdLine;
import com.company.project.module.data.service.TdEmphasisService;
import com.company.project.module.data.service.TdLineService;
import com.company.project.module.data.service.TdSegmentService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Created by paodingsoft.chen on 2019/08/07.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TdEmphasisServiceImpl extends AbstractService<TdEmphasis> implements TdEmphasisService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private TdEmphasisMapper tdEmphasisMapper;
    @Resource
    private TdSegmentService tdSegmentService;

    @Override
    public List getEmphasisList(Map params) {
        Example example = new Example(TdEmphasis.class);
        Example.Criteria criteria = example.createCriteria();
        if (!params.isEmpty()) {
            if (params.get("duct") != null && !params.get("duct").equals("")) {
                criteria.andEqualTo("ductCode", params.get("duct"));
            }
            if (params.get("startDuct") != null) {
                criteria.andGreaterThanOrEqualTo("ductCode", params.get("startDuct"));
                criteria.andCondition("DUCT_CODE <= ", params.get("endDuct"));
            }
            if (params.get("group_segment") != null && !params.get("group_segment").equals("")) {
                criteria.andEqualTo("segmentUuid", params.get("group_segment"));
            }
            if (params.get("lineUuid") != null && !params.get("lineUuid").equals("")) {
                criteria.andEqualTo("lineUuid", params.get("lineUuid"));
            }
            if (params.get("updown") != null && !params.get("updown").equals("")) {
                criteria.andEqualTo("updown", params.get("updown"));
            }
        }
        example.setOrderByClause("duct_code desc");
        return tdEmphasisMapper.selectByExample(example);
    }

    @Override
    public List<Map> getEmphasisForMainCharts(List group_segment) {
        return tdEmphasisMapper.getEmphasisForMainCharts(group_segment);
    }

    @Override
    public List<Map> getGroupSegment(Map params) {
        return  tdSegmentService.getGroupSegment(params);
    }

    @Override
    public List<Map> getSegmentByGroup(Map params) {
        return tdSegmentService.getSegmentByGroup(params);
    }

    @Override
    public List<Map> getEmphasisSelect() {
        List<Map> emphasisSelect = tdEmphasisMapper.getEmphasisSelect();
        List<Map> result = Lists.newArrayList();
        for(Map map : emphasisSelect){
            List<Map> segment = (List<Map>)map.get("SEGMENT");
            Map<Object, List<Map>> updown = segment.stream().collect(Collectors.groupingBy(m -> m.get("UPDOWN")));
            map.remove("SEGMENT");
            updown.putAll(map);
            result.add(updown);
        }
        return result;
    }
}
