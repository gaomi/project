package com.company.project.module.data.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.core.exception.ServiceException;
import com.company.project.module.data.dao.MetroDeviceMapper;
import com.company.project.module.data.model.MetroDevice;
import com.company.project.module.data.service.MetroDeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by paodingsoft.chen on 2019/09/22.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class MetroDeviceServiceImpl extends AbstractService<MetroDevice> implements MetroDeviceService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private MetroDeviceMapper metroDeviceMapper;

    @Override
    public void batchSave(List<Map> deviceInfos) {
        insertBatch(deviceInfos);
    }

    @Override
    public List<MetroDevice> selectByCon(Map params) {
        return metroDeviceMapper.selectByCon(params);
    }

    @Override
    public List<Map> getDeviceInfo(Map params) {
        return metroDeviceMapper.getDeviceInfo(params);
    }

    @Override
    public List findAllDevice(Map params) {
        return metroDeviceMapper.findAllDeviceForWeb(params);
    }

    @Override
    public Map findDeviceTypeDict() {
        metroDeviceMapper.findDeviceTypeDictForWeb();
        return null;
    }
    /**
     * 批量插入
     *
     * @param detailList
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void insertBatch(List<Map> detailList) {
        try {
            int batchCount = 199;//每批commit的个数
            int batchLastIndex = batchCount - 1;//每批最后一个的下标
            for (int index = 0; index < detailList.size() - 1; ) {
                if (batchLastIndex > detailList.size() - 1) {
                    batchLastIndex = detailList.size();
                    metroDeviceMapper.batchSaveProject(detailList.subList(index, batchLastIndex));
                    logger.info("insert :" + index + " to " + batchLastIndex);
                    logger.info("insertBatch success : " + detailList.size() + " data inserted ;");
                    break;//数据插入完成,退出循环
                } else {
                    metroDeviceMapper.batchSaveProject(detailList.subList(index, batchLastIndex));
                    logger.info("insert :" + index + " to " + batchLastIndex);
                    index = batchLastIndex;//设置下一批下标
                    batchLastIndex = index + (batchCount - 1);
                    if (index == detailList.size() - 1) {
                        metroDeviceMapper.batchSaveProject(detailList.subList(index, index + 1));
                    }
                }
            }

        } catch (Exception e) {
            throw new ServiceException("插入数据失败！", e);
        }
    }
}
