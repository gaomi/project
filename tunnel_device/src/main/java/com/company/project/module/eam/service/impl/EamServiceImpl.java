package com.company.project.module.eam.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import com.company.project.core.exception.ServiceException;
import com.company.project.core.util.FileUtil;
import com.company.project.module.eam.dao.EamMapper;
import com.company.project.module.eam.service.EamService;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Chen
 * @created 2019-11-26-11:35.
 */
@Service
public class EamServiceImpl implements EamService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    String userId = "EAMData";
    String password = "EAMGW+!@$";
    String resultKey = "message";

    @Autowired
    private EamMapper eamMapper;

    @Override
    public void saveEamData(String typeKey) {
        //外网接口地址不通时使用本地map
        /*GetDataInterfaceService service = new GetDataInterfaceService_Service().getGetDataInterfaceServicePort();
         String dataStr = service.getDataInfo(userId, password, "get" + typeKey + "Response");
        Map dataMap = JSON.parseObject(dataStr, new TypeReference<Map>() {
        });*/
        //本地map
        HashMap<String, String> map = getData();
        Map dataMap = JSON.parseObject(map.get("dataStr" + typeKey), new TypeReference<Map>() {
        });
        JSONArray jsonData = (JSONArray) dataMap.get(resultKey);
        List<Map> result = jsonData.toJavaList(Map.class);
        // ArrayList<Map> list = Lists.newArrayList();
        //result.stream().forEach(m -> {
        //
        //    Map map = m;
        //    list.add(map);
        //});
        insertBatch(result, typeKey);

    }

    /**
     * 批量插入
     *
     * @param detailList
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void insertBatch(List<Map> detailList, String methodName) {
        try {
            //先删除
            //Method deleteMethod = this.eamMapper.getClass().getDeclaredMethod("delete" + methodName, String.class);
            //deleteMethod.invoke(this.eamMapper, methodName);

            //eamMapper.deleteData("j302_eam_" + methodName.toLowerCase());
            //后插入
            Method saveMethod = this.eamMapper.getClass().getDeclaredMethod("batchSave" + methodName, List.class);
            //每批commit的个数
            int batchCount = 199;
            //每批最后一个的下标
            int batchLastIndex = batchCount - 1;
            for (int index = 0; index < detailList.size() - 1; ) {
                if (batchLastIndex > detailList.size() - 1) {
                    batchLastIndex = detailList.size();
                    saveMethod.invoke(this.eamMapper, detailList.subList(index, batchLastIndex));
                    logger.info("insert :" + index + " to " + batchLastIndex);
                    logger.info("insertBatch success : " + detailList.size() + " data inserted ;");
                    break;//数据插入完成,退出循环
                } else {
                    saveMethod.invoke(this.eamMapper, detailList.subList(index, batchLastIndex));
                    logger.info("insert :" + index + " to " + batchLastIndex);
                    //设置下一批下标
                    index = batchLastIndex;
                    batchLastIndex = index + (batchCount - 1);
                    if (index == detailList.size() - 1) {
                        saveMethod.invoke(this.eamMapper, detailList.subList(index, batchLastIndex));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("插入数据失败！", e);
        }
    }


    /**
     * 批量插入
     */
    /* @Transactional(readOnly = false, rollbackFor = Exception.class)
   public void insertBatchForFault(List<Map> detailList, String methodName) {
        try {

            int batchCount = 199;//每批commit的个数
            int batchLastIndex = batchCount - 1;//每批最后一个的下标
            for (int index = 0; index < detailList.size() - 1; ) {
                if (batchLastIndex > detailList.size() - 1) {
                    batchLastIndex = detailList.size();
                    choceMethod(methodName, detailList.subList(index, batchLastIndex));
                    //method.invoke(this.eamMapper,);
                    logger.info("insert :" + index + " to " + batchLastIndex);
                    logger.info("insertBatch success : " + detailList.size() + " data inserted ;");
                    break;//数据插入完成,退出循环
                } else {
                    choceMethod(methodName, detailList.subList(index, batchLastIndex));
                    logger.info("insert :" + index + " to " + batchLastIndex);
                    index = batchLastIndex;//设置下一批下标
                    batchLastIndex = index + (batchCount - 1);
                    if (index == detailList.size() - 1) {
                        choceMethod(methodName, detailList.subList(index, batchLastIndex));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("插入数据失败！", e);
        }
    }*/

   /* private void choceMethod(String methodName, List list) {
        switch (methodName) {
            case "Person":
                eamMapper.batchSavePerson(list);
                break;
            case "Depart":
                eamMapper.batchSaveDepart(list);
                break;
            case "Equip":
                eamMapper.batchSaveEquip(list);
                break;
            case "Fault":
                eamMapper.batchSaveFault(list);
                break;
            default:
                break;
        }
    }*/
    private HashMap<String, String> getData() {
        String dataStrPerson = "";
        String dataStrDept = "";
        String dataStrEquip = "";
        String dataStrFault = "";
        String dataStrWorkOrder = "";

        //本地测试
        String result = FileUtil.getRootDataByLocal("/static/js/mock/eamData.json");
        Map resultMap = JSON.parseObject(result, new TypeReference<Map>() {
        });
        HashMap<String, String> map = Maps.newHashMap();
        resultMap.forEach((k, v) -> {
            Map m = (Map) v;
            map.put((String) k, (String) m.get("message"));
        });



        /*map.put("dataStrWorkOrder", dataStrWorkOrder);
        map.put("dataStrDept", dataStrDept);
        map.put("dataStrEquip", dataStrEquip);
        map.put("dataStrFault", dataStrFault);*/

        return map;
    }

}
