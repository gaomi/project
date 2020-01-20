package com.company.project.module.data.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.core.exception.ServiceException;
import com.company.project.module.data.dao.TdFaultImgMapper;
import com.company.project.module.data.model.TdFaultImg;
import com.company.project.module.data.service.TdFaultImgService;
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
 * Created by paodingsoft.chen on 2019/08/20.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TdFaultImgServiceImpl extends AbstractService<TdFaultImg> implements TdFaultImgService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private TdFaultImgMapper tdFaultImgMapper;

    @Value("${fault-image.path}")
    private String ServicePath;

    @Override
    public List<Map> getImageById(String lineCode, String ctx, Map ids) {
        List<Map> fileName = tdFaultImgMapper.getImageById(ids);
        for (Map img : fileName) {
            String imgPath = ServicePath + lineCode + "/" + (String) img.get("IMAGE_URL");
            img.put("IMAGE_URL", imgPath);
        }
        return fileName;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void saveAll(List<TdFaultImg> list) {
        try {
            int batchCount = 199;//每批commit的个数
            int batchLastIndex = batchCount - 1;//每批最后一个的下标
            for (int index = 0; index < list.size() - 1; ) {
                if (batchLastIndex > list.size() - 1) {
                    batchLastIndex = list.size();
                    tdFaultImgMapper.saveAll(list.subList(index, batchLastIndex));
                    logger.info("insert :" + index + " to " + batchLastIndex);
                    logger.info("insertBatch success : " + list.size() + " data inserted ;");
                    break;//数据插入完成,退出循环
                } else {
                    tdFaultImgMapper.saveAll(list.subList(index, batchLastIndex));
                    logger.info("insert :" + index + " to " + batchLastIndex);
                    index = batchLastIndex;//设置下一批下标
                    batchLastIndex = index + (batchCount - 1);
                    if (index == list.size() - 1) {
                        tdFaultImgMapper.saveAll(list.subList(index, index + 1));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("插入数据失败！", e);
        }
    }

}
