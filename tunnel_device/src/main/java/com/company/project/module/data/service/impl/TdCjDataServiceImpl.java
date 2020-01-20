package com.company.project.module.data.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.core.exception.ServiceException;
import com.company.project.module.data.dao.TdCjDataMapper;
import com.company.project.module.data.model.TdCjData;
import com.company.project.module.data.service.TdCjDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by paodingsoft.chen on 2019/07/30.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TdCjDataServiceImpl extends AbstractService<TdCjData> implements TdCjDataService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private TdCjDataMapper metroCjMapper;

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void saveAll(List<TdCjData> list) {
        try {
            int batchCount = 199;//每批commit的个数
            int batchLastIndex = batchCount - 1;//每批最后一个的下标
            for (int index = 0; index < list.size() - 1; ) {
                if (batchLastIndex > list.size() - 1) {
                    batchLastIndex = list.size();
                    metroCjMapper.saveAll(list.subList(index, batchLastIndex));
                    logger.info("insert :" + index + " to " + batchLastIndex);
                    logger.info("insertBatch success : " + list.size() + " data inserted ;");
                    break;//数据插入完成,退出循环
                } else {
                    metroCjMapper.saveAll(list.subList(index, batchLastIndex));
                    logger.info("insert :" + index + " to " + batchLastIndex);
                    index = batchLastIndex;//设置下一批下标
                    batchLastIndex = index + (batchCount - 1);
                    if (index == list.size() - 1) {
                        metroCjMapper.saveAll(list.subList(index, index + 1));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("插入数据失败！", e);
        }
    }

    @Override
    public List<Map> selectSegment() {
        return metroCjMapper.selectSegment();
    }

    @Override
    public int updateCj(Map map) {
        return metroCjMapper.updateCj(map);
    }

    @Override
    public List<Map> getDateByTime(Map map) {
        return metroCjMapper.getDateByTime(map);
    }

    @Override
    public List<Map> getCjDate(Map map) {
        return metroCjMapper.getCjDate(map);
    }

    @Override
    public List<Map> getPointData(Map map) {
        return metroCjMapper.getPointData(map);
    }

    @Override
    public List<Map> getQlXdate(Map map) {
        return metroCjMapper.getQlXdate(map);
    }

    @Override
    public List<Map> getQlByTime(Map map) {
        return metroCjMapper.getQlByTime(map);
    }

    @Override
    public List<Map> getCjPtd(Map map) {
        return metroCjMapper.getCjPtd(map);
    }

    @Override
    public List<Map> getQLPtd(Map params) {
        return metroCjMapper.getQLPtd(params);
    }

    @Override
    public List<Map> getCjXDate(Map map) {
        return metroCjMapper.getCjXDate(map);
    }

    @Override
    public List<Map> getPtdCjXDate(Map params) {
        return metroCjMapper.initPtdCjXData(params);
    }

    @Override
    public List<Map> getPtdCjData(Map params) {
        return metroCjMapper.getPtdCjData(params);
    }

    @Override
    public List<Map> getMapDateByTime(Map map) {
        return null;
    }

    @Override
    public Double getCjMark(Map param) {
        return metroCjMapper.getCjMark(param);
    }


}
