package com.company.project.module.inspect.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.company.project.core.AbstractService;
import com.company.project.core.Result;
import com.company.project.core.exception.ServiceException;
import com.company.project.core.util.HttpClientUtil;
import com.company.project.module.inspect.dao.EamPersonMapper;
import com.company.project.module.inspect.model.EamPerson;
import com.company.project.module.inspect.service.EamPersonService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Chen
 * @created 2019-12-14-12:15.
 */
@Service
public class EamPersonServiceImpl extends AbstractService<EamPerson> implements EamPersonService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private EamPersonMapper eamPersonMapper;
    @Resource
    private HttpClientUtil httpClientUtil;

    @Override
    public List selectPersonData(Map params) {
        return eamPersonMapper.selectPersonAll(params);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void getAllFromEam() {
        // Result result = httpClientUtil.get("http://localhost/tunnel_device/static/js/mock/eamData.json");
        Result result = httpClientUtil.get("http://localhost:9980/tunnel_tdd/static/js/mock/eamData.json");
        Map data = (Map)JSON.parse((byte[]) result.getData());
        Map data_con = (Map) data.get("dataStrPerson");
        List<EamPerson> newPersonList = JSONObject.parseArray(JSONObject.toJSONString(data_con.get("message")), EamPerson.class);
        Map map = Maps.newHashMap();
        List<EamPerson> eamPeople = eamPersonMapper.selectPersonAll(map);
        Set<EamPerson> newPersonSet = Sets.newHashSet();
        Set<EamPerson> oldPersonSet = Sets.newHashSet();
        newPersonSet.addAll(newPersonList);
        Set<EamPerson> item = CopySet(newPersonSet);
        oldPersonSet.addAll(eamPeople);
        newPersonSet.removeAll(oldPersonSet);
        oldPersonSet.removeAll(item);
        //添加新人员
        if (newPersonSet.size() > 0) {
            List<EamPerson> addPerson = Lists.newArrayList();
            for (EamPerson eamPerson : newPersonSet) {
                eamPerson.setStatus("1");
                eamPerson.setInspectPassword(DigestUtils.md5DigestAsHex(eamPerson.getPersonid().getBytes()));
                addPerson.add(eamPerson);
            }
            insertBatchForEamPerson(addPerson);
        }
        //去除旧人员
        if (oldPersonSet.size() > 0) {
            List<EamPerson> updatePerson = Lists.newArrayList();
            for (EamPerson eamPerson : oldPersonSet) {
                if (eamPerson.getStatus() != "0") {
                    eamPerson.setStatus("0");
                    updatePerson.add(eamPerson);
                }
            }
            updateBatchForEamPerson(updatePerson);
        }
        //TODO 更新旧人员状态

    }

    /***
     * 批量更新
     * @param updatePerson
     */
    private void updateBatchForEamPerson(List<EamPerson> updatePerson) {
        try {
            int batchCount = 199;//每批commit的个数
            int batchLastIndex = batchCount - 1;//每批最后一个的下标
            for (int index = 0; index < updatePerson.size() - 1; ) {
                if (batchLastIndex > updatePerson.size() - 1) {
                    batchLastIndex = updatePerson.size();
                    eamPersonMapper.batchUpdateEamPerson(updatePerson.subList(index, batchLastIndex));
                    logger.info("update :" + index + " to " + batchLastIndex);
                    logger.info("updateBatch success : " + updatePerson.size() + " data update;");
                    break;//数据插入完成,退出循环
                } else {
                    eamPersonMapper.batchUpdateEamPerson(updatePerson.subList(index, batchLastIndex));
                    logger.info("update :" + index + " to " + batchLastIndex);
                    index = batchLastIndex;//设置下一批下标
                    batchLastIndex = index + (batchCount - 1);
                    if (index == updatePerson.size() - 1) {
                        eamPersonMapper.batchUpdateEamPerson(updatePerson.subList(index, index + 1));
                    }
                }
            }
            if (updatePerson.size() == 1) {
                eamPersonMapper.batchUpdateEamPerson(updatePerson);
            }
        } catch (Exception e) {
            throw new ServiceException("插入数据失败！", e);
        }
    }

    private Set<EamPerson> CopySet(Set<EamPerson> a) {
        Set<EamPerson> result = Sets.newHashSet();
        for (EamPerson eamPerson : a) {
            result.add(eamPerson);
        }
        return result;
    }

    @Override
    public void resetPassWordById(String id) {
        EamPerson person = this.findById(id);
        person.setInspectPassword(DigestUtils.md5DigestAsHex(person.getPersonid().getBytes()));
        this.update(person);
    }


    /**
     * 批量插入
     *
     * @param list
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void insertBatchForEamPerson(List<EamPerson> list) {
        if (list.size() == 1) {
            eamPersonMapper.batchSaveEamPerson(list);
        } else {
            batchInsertByMethod(list, "EamPerson");
        }
    }
}
