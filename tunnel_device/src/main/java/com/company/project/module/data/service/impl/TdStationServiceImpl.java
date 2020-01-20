package com.company.project.module.data.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.module.data.dao.TdStationMapper;
import com.company.project.module.data.model.TdStation;
import com.company.project.module.data.service.TdStationService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by paodingsoft.chen on 2019/08/06.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TdStationServiceImpl extends AbstractService<TdStation> implements TdStationService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private TdStationMapper tdStationMapper;

    @Override
    public List findAllStations(Map params) {
        Example example = new Example(TdStation.class);
        Example.Criteria criteria = example.createCriteria();
        if (!params.isEmpty()) {
            String keyWord = String.valueOf(params.get("keyWord"));
            String lineId = String.valueOf(params.get("line"));
            String classType = String.valueOf(params.get("classType"));

            if (StringUtils.isNotBlank(lineId)&& !StringUtils.equals(lineId.trim(),"null")) {
                criteria.andEqualTo("lineUuid", lineId);
            }
            if (StringUtils.isNotBlank(classType)&& !StringUtils.equals(classType.trim(),"null")) {
                criteria.andEqualTo("classType", classType);
            }
            if (StringUtils.isNotBlank(keyWord)) {
                criteria.andLike("stationName", "%" + keyWord + "%").orLike("lineCode", "%" + keyWord + "%");
            }
        }
        example.setOrderByClause("line_code,station_code");
        return tdStationMapper.selectByExample(example);
    }
}
