package com.company.project.module.inspect.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.module.data.dao.TdDictMapper;
import com.company.project.module.data.model.TdDict;
import com.company.project.module.inspect.dao.SiFaultDataMapper;
import com.company.project.module.inspect.model.SiFaultData;
import com.company.project.module.inspect.service.EamWorkOrderService;
import com.company.project.module.inspect.service.SiFaultDataService;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author Chen
 * @created 2019-12-17-15:11.
 */
@Service
public class SiFaultDataServiceImpl extends AbstractService<SiFaultData> implements SiFaultDataService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SiFaultDataMapper siFaultDataMapper;
    @Autowired
    private TdDictMapper tdDictMapper;
    @Autowired
    private EamWorkOrderService eamWorkOrderService;
    @Value("${fault-image.path}")
    private String ServicePath;

    @Override
    public List<Map> getFaultByWorkOrder(String uuid, String lineCode) {
        List<Map> result = siFaultDataMapper.getFaultByWorkOrder(uuid);
        toDealImage(result, lineCode);
        return result;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void commitCheckFaultByWorkOrder(String orderId, List list, String personNo) throws Exception {
        Date commitDate = new Date();
        for (int i = 0; i < list.size(); i++) {
            Map temp = (Map) list.get(i);
            String uid = UUID.randomUUID().toString();
            temp.put("uuid", uid);
            temp.put("operatorNo", personNo);
            temp.put("insertDate", commitDate);
        }
        //保存病害
        this.batchInsertByMethod(list, "AllSiFault");
        //TODO 提交到EAM关闭工单
        eamWorkOrderService.closeEamOrder(orderId, commitDate,personNo);

    }

    @Override
    public List<SiFaultData> findRecord(String code) {
        return siFaultDataMapper.findByInternalCode(code);
    }

    @Override
    public List<TdDict> getFaultStatus() {
        Condition con = new Condition(TdDict.class);
        con.createCriteria().andEqualTo("pid" , "1");
        con.orderBy("dictKey");
        return tdDictMapper.selectByCondition(con);
    }

    /**
     * 给图加路径
     *
     * @param list
     * @param lineCode
     */
    private void toDealImage(List<Map> list, String lineCode) {
        list.stream().forEach(m -> {
            List<String> imageList = (List<String>) m.get("imageList");
            List<String> tempList = Lists.newArrayList();
            for (int i = 0; i < imageList.size(); i++) {
                tempList.add(ServicePath + lineCode + "/" + imageList.get(i));
            }
            m.put("imageList", tempList);
        });
    }
}
