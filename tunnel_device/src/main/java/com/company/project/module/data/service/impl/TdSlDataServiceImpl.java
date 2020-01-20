package com.company.project.module.data.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.core.exception.ServiceException;
import com.company.project.module.data.dao.TdSlDataMapper;
import com.company.project.module.data.model.TdSlData;
import com.company.project.module.data.service.TdSlDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by paodingsoft.chen on 2019/09/03.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TdSlDataServiceImpl extends AbstractService<TdSlData> implements TdSlDataService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private TdSlDataMapper tdSlDataMapper;

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void saveAll(List<TdSlData> list) {
        try {
            int batchCount = 199;//每批commit的个数
            int batchLastIndex = batchCount - 1;//每批最后一个的下标
            for (int index = 0; index < list.size() - 1; ) {
                if (batchLastIndex > list.size() - 1) {
                    batchLastIndex = list.size();
                    tdSlDataMapper.saveAll(list.subList(index, batchLastIndex));
                    logger.info("insert :" + index + " to " + batchLastIndex);
                    logger.info("insertBatch success : " + list.size() + " data inserted ;");
                    break;//数据插入完成,退出循环
                } else {
                    tdSlDataMapper.saveAll(list.subList(index, batchLastIndex));
                    logger.info("insert :" + index + " to " + batchLastIndex);
                    index = batchLastIndex;//设置下一批下标
                    batchLastIndex = index + (batchCount - 1);
                    if (index == list.size() - 1) {
                        tdSlDataMapper.saveAll(list.subList(index, index + 1));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("插入数据失败！", e);
        }
    }

    @Override
    public List<Map> initSLXData(Map map) {
        return tdSlDataMapper.initSLXData(map);
    }

    @Override
    public List<Map> getSlByName(Map map) {
        return tdSlDataMapper.getSlByName(map);
    }

    @Override
    public List<Map> getWarning() {
        return tdSlDataMapper.getWarning();
    }

    @Override
    public List<Map> getLegend(String emphasis) {
        return tdSlDataMapper.getLegend(emphasis);
    }

    @Override
    public List<Map> getDiffData(Map map) {
        return tdSlDataMapper.getDiffData(map);
    }

    @Override
    public List<Map> getDuctDate(Map param) {
        return tdSlDataMapper.getDuctDate(param);
    }

    @Override
    public List<Map> initPtdSlXData(Map params) {
        return tdSlDataMapper.initPtdSlXData(params);
    }

    @Override
    public List<Map> getPtdSLTime(Map params) {
        return tdSlDataMapper.getPtdSLTime(params);
    }

    @Override
    public List<Map> getMapSlByName(Map map) {
        return null;
    }

    @Override
    public List<Map> getMapDiffData(Map map) {
        return tdSlDataMapper.getMapDiffData(map);
    }

    @Override
    public List<Double> getSlScore(Map param) {
        return tdSlDataMapper.getSlScore(param);
    }

    @Override
    public int getZMScore(Map param) {
        return tdSlDataMapper.getZMScore(param);
    }

}
