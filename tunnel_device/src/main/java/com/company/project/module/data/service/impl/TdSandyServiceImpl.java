package com.company.project.module.data.service.impl;

import com.company.project.core.exception.ServiceException;
import com.company.project.core.util.poi.pojo.ExcelSet;
import com.company.project.core.util.poi.pojo.ExcelSheet;
import com.company.project.module.data.dao.TdDictMapper;
import com.company.project.module.data.dao.TdSandyMapper;
import com.company.project.module.data.model.TdDict;
import com.company.project.module.data.model.TdSandy;
import com.company.project.module.data.service.TdSandyService;
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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Created by paodingsoft.chen on 2019/08/07.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TdSandyServiceImpl extends AbstractService<TdSandy> implements TdSandyService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private TdSandyMapper tdSandyMapper;
    @Resource
    private TdDictMapper tdDictMapper;

    @Override
    public void saveData(List<Map<String, Object>> list) {
        insertBatch(list);
    }

    @Override
    public List findbyParams(Map params) {
        //String lineCode = (String) params.get("lineCode");
        //String segment = (String) params.get("segment");
        //Example example = new Example(TdSandy.class);
        //example.createCriteria().andEqualTo("lineCode", lineCode).andEqualTo("segmentUuid", segment);

        List<Map> list = tdSandyMapper.selectBySegment(params);
        return list;
    }

    @Override
    public Map getSandyDict() {
        Map result = Maps.newHashMap();
        Condition con = new Condition(TdDict.class);
        con.createCriteria().andEqualTo("pid","4");
        List<TdDict> local = tdDictMapper.selectByCondition(con);
        Condition con1 = new Condition(TdDict.class);
        con1.createCriteria().andEqualTo("pid","3");
        List<TdDict> candyCode = tdDictMapper.selectByCondition(con1);
        result.put("local",local);
        result.put("code",candyCode);
        return result;
    }

    @Override
    public void saveSandy(TdSandy tdSandy) {
        tdSandyMapper.saveSandy(tdSandy);
    }

    @Override
    public List<Map> getSandySelect() {
        List<Map> emphasisSelect = tdSandyMapper.getSandySelectSegment();
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

    /**
     * 批量插入
     *
     * @param detailList
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void insertBatch(List detailList) {
        try {
            int batchCount = 199;//每批commit的个数
            int batchLastIndex = batchCount - 1;//每批最后一个的下标
            for (int index = 0; index < detailList.size() - 1; ) {
                if (batchLastIndex > detailList.size() - 1) {
                    batchLastIndex = detailList.size();
                    tdSandyMapper.batchSaveSandyByImport(detailList.subList(index, batchLastIndex));

                    logger.info("insert :" + index + " to " + batchLastIndex);
                    logger.info("insertBatch success : " + detailList.size() + " data inserted ;");
                    break;//数据插入完成,退出循环
                } else {
                    tdSandyMapper.batchSaveSandyByImport(detailList.subList(index, batchLastIndex));

                    logger.info("insert :" + index + " to " + batchLastIndex);
                    index = batchLastIndex;//设置下一批下标
                    batchLastIndex = index + (batchCount - 1);
                    if (index == detailList.size() - 1) {
                        tdSandyMapper.batchSaveSandyByImport(detailList.subList(index, index + 1));

                    }
                }
            }
        } catch (Exception e) {
            throw new ServiceException("插入数据失败！", e);
        }
    }
}
