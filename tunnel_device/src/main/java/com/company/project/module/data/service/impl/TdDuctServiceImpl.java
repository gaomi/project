package com.company.project.module.data.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.company.project.core.exception.ServiceException;
import com.company.project.module.data.dao.TdDuctMapper;
import com.company.project.module.data.model.TdDuct;
import com.company.project.module.data.model.TdSegment;
import com.company.project.module.data.service.TdDuctService;
import com.company.project.core.AbstractService;
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
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;


/**
 * Created by paodingsoft.chen on 2019/08/22.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TdDuctServiceImpl extends AbstractService<TdDuct> implements TdDuctService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private TdDuctMapper tdDuctMapper;

    @Override
    public void updateMileageBySegment(TdSegment tdSegment) {
        int startCode = tdSegment.getStartDuctCode();
        int endCode = tdSegment.getEndDuctCode();
        int suff = startCode < endCode ? 1 :  -1;
        int ductCode = Math.abs(endCode-startCode);
        double startMileage = tdSegment.getStartMileageCode().doubleValue();
        double ductMileage = tdSegment.getEndMileageCode().doubleValue() - startMileage;
        double ductWith = ductMileage / ductCode;
        List list = Lists.newArrayList();
        DecimalFormat df =new DecimalFormat(".00");
        for(int i = 0;i<=ductCode;i++){
            Map item = Maps.newHashMap();
            item.put("ductCode",startCode+i*suff);
            item.put("mileage",df.format((i*ductWith)+startMileage));
            list.add(item);
        }
        Map param = Maps.newHashMap();
        param.put("segment",tdSegment.getUuid());
        param.put("list",list);
        batchUpdate(list,tdSegment.getUuid());

    }

    @Override
    public List<Map> selectDuct(Map params) {
        return tdDuctMapper.selectDuct(params);
    }

    private void batchUpdate(List list,String uuid) {
            Map param = Maps.newHashMap();
            param.put("segment",uuid);
            int batchCount = 199;//每批commit的个数
            int batchLastIndex = batchCount - 1;//每批最后一个的下标
            for (int index = 0; index < list.size() - 1; ) {
                if (batchLastIndex > list.size() - 1) {
                    batchLastIndex = list.size();
                    param.put("list",list.subList(index, batchLastIndex));
                    tdDuctMapper.updateMileage(param);
                    logger.info("insert :" + index + " to " + batchLastIndex);
                    logger.info("insertBatch success : " + list.size() + " data inserted ;");
                    break;//数据插入完成,退出循环
                } else {
                    param.put("list",list.subList(index, batchLastIndex));
                    tdDuctMapper.updateMileage(param);
                    logger.info("insert :" + index + " to " + batchLastIndex);
                    index = batchLastIndex;//设置下一批下标
                    batchLastIndex = index + (batchCount - 1);
                    if (index == list.size() - 1) {
                        param.put("list",list.subList(index, batchLastIndex));
                        tdDuctMapper.updateMileage(param);
                    }
                }
            }

    }
}
