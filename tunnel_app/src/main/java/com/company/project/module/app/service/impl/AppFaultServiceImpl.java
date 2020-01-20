package com.company.project.module.app.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.company.project.core.AbstractService;
import com.company.project.core.util.FileUtil;
import com.company.project.module.app.service.AppFaultService;
import com.company.project.module.app.util.ImageUtils;
import com.company.project.module.inspect.dao.FaultPlanMapper;
import com.company.project.module.inspect.dao.SiFaultDataMapper;
import com.company.project.module.inspect.model.FaultPlan;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.rmi.ServerException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class AppFaultServiceImpl extends AbstractService<FaultPlan> implements AppFaultService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private FaultPlanMapper faultPlanMapper;
    @Resource
    private SiFaultDataMapper siFaultDataMapper;

    @Value("${inspect-res.path}")
    private String saveResPath;

    @Override
    public List findXfPlanForApp(String employeeNo) {
        //TODO 调用eam工单接口。
        return faultPlanMapper.findXfPlanForApp(employeeNo);
    }

    @Override
    public List findAllPlanForApp(Map map, String employeeId) {
        if (map.isEmpty()) {
            map = Maps.newHashMap();
            map.put("employeeId", employeeId);
        } else {
            map.put("employeeId", employeeId);
        }
        return faultPlanMapper.findAllPlanForApp(map);
    }

    /***
     * 排序
     */
    private class DeviceComparable implements Comparator {
        @Override
        public int compare(Object o1, Object o2) {
            int befor = Integer.parseInt((String) ((Map) o1).get("START_MILEAGE"));
            int end = Integer.parseInt((String) ((Map) o2).get("START_MILEAGE"));
            return befor - end;
        }
    }

    @Override
    public Map<String, Object> findDetailByPlanForApp(String uuid) {
        /*20191212修改*/

        /*
        *  //查任务
        Map planDetail = faultPlanMapper.findVPlanDetailByIdForApp(uuid);
        //设备及检查项
        List<Map> deviceList = faultPlanMapper.findDeviceListByPlanForApp(uuid);
        List duplicateMap = getDuplicateMap(deviceList);
        //病害
        List<Map> faultList = faultPlanMapper.findFaultByPlanForApp(uuid);
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("plan", planDetail);
        map.put("devices", duplicateMap);
        map.put("faults", faultList);
        */

        //查任务明细
        Map planDetail = faultPlanMapper.findVPlanDetailByIdForApp(uuid);
        //List<Map> taskList = getTaskData(uuid);
        //设备及检查项
        List<Map> taskList = faultPlanMapper.findDeviceListByPlanID(uuid);
//        List duplicateMap = getDuplicateMap(deviceList);
        //Todo 取门字典
        List doorType = Arrays.asList("01030501", "01030502", "01030503");
        Map duplicateMap = getDeviceListMap(taskList, doorType);
        //病害
        List<Map> faultListForApp = faultPlanMapper.getFaultListForApp(uuid);
        //门
        Map params = Maps.newHashMap();
        params.put("doorType", doorType);
        params.put("uuid", uuid);
        List<Map> doorDevice = faultPlanMapper.getDoorListForApp(params);
        faultListForApp.addAll(doorDevice);
        Collections.sort(faultListForApp, new MileageComparator());
        //任务数据集合
        HashMap<String, Object> map = Maps.newHashMap();
        HashMap<String, Object> doorFaultsResult = Maps.newHashMap();
        map.put("task", taskList);
        map.put("plan", planDetail);
        map.put("devices", duplicateMap);
        map.put("faults", getFaultFormatter(faultListForApp, doorFaultsResult, doorType));
        map.put("doorFaults", doorFaultsResult);
        List stationResult = faultPlanMapper.findPlanStationByPlanID(uuid);
        map.put("stations", stationResult);
        return map;
    }


    /***
     *
     * @param faultListForApp
     * @return
     */
    public Map getFaultFormatter(List<Map> faultListForApp, Map doorFaultsResult, List<String> doorType) {
        Map<String, List<Map>> result = Maps.newHashMap();
        Map doorTemp = Maps.newHashMap();
        for (Map map : faultListForApp) {
//            try {
            if (map.get("DEVICE_UUID") == null || map.get("DEVICE_TYPE") == null) {
                if (result.get((String) map.get("SEGMENT")) == null) {
                    List<Map> list = Lists.newArrayList();
                    result.put((String) map.get("SEGMENT"), list);
                }
                continue;
            }
            String doorNumber = (String) map.get("DEVICE_UUID");
//判断是否是门病害
            if (map.get("TYPE_F") == null && doorType.contains(((String) map.get("DEVICE_TYPE")).substring(0, 8))) {

                String doorCheckItem = (String) map.get("DEVICE_TYPE");
                if (doorFaultsResult.get(doorNumber + "_" + doorCheckItem) == null) {
                    List doorList = Lists.newArrayList();
                    doorList.add(map);
                    doorFaultsResult.put(doorNumber + "_" + doorCheckItem, doorList);
                } else {
                    List doorList = (List) doorFaultsResult.get(doorNumber + "_" + doorCheckItem);
                    doorList.add(map);
                }
                continue;
            }
            //处理普通病害与门设备
            if (result.get(map.get("SEGMENT")) == null) {

                List<Map> list = Lists.newArrayList();
                if (map.get("TYPE_F") == null) {
                    map.put("TYPE_F", "is_fault");
                }
                list.add(map);
                result.put((String) map.get("SEGMENT"), list);

            } else {
                List<Map> segment = result.get(map.get("SEGMENT"));

                if (map.get("TYPE_F") == null) {
                    map.put("TYPE_F", "is_fault");
                }
                segment.add(map);

            }
        }
        return result;
    }


    private Map getDeviceListMap(List<Map> deviceList, List<String> doorList) {
        Map device = Maps.newHashMap();
        for (Map itemp : deviceList) {
            List<Map> itemDevice = (List<Map>) itemp.get("DEVICE_LIST");
            for (Map itemMap : itemDevice) {
                String deviceId = itemMap.get("DEVICE_UUID").toString();
                if (!doorList.contains(deviceId.substring(deviceId.length() - 8))) {
                    itemMap.put("DEVICE_NAME", itemMap.get("LEVEL_NAME").toString() + "(" + itemMap.get("DEVICE_NAME") + ")");
                }
                itemMap.remove("LEVEL_NAME");
            }
            device.put(itemp.get("taskId"), itemDevice);
            itemp.remove("DEVICE_LIST");
        }
        return device;
    }

    /**
     * 根据工单uuid查病害
     *
     * @param uuid
     * @return
     */
    private Map<String, Object> getFaultsMap(String uuid) {
        List<Map> faultList = faultPlanMapper.findFaultByPlanForApp(uuid);
        HashMap<String, Object> map = Maps.newHashMap();
        faultList.stream().forEach(m -> {
            String taskId = String.valueOf(m.get("TASK_ID"));
            if (map.containsKey(taskId)) {
                List<Map> list = (List) map.get(taskId);
                list.add(m);
            } else {
                ArrayList<Map> list = Lists.newArrayList(m);
                map.put(taskId, list);
            }
        });
        return map;
    }

    /**
     * 根据工单id,查任务分类
     *
     * @param uuid
     * @return
     */
    private List<Map> getTaskData(String uuid) {
        List<Map> taskList = Lists.newArrayList();
        HashMap<String, String> map1 = Maps.newHashMap();
        map1.put("taskName", "杨高南路站-锦绣路站 上行");
        map1.put("workOrderId", uuid);
        map1.put("upDown", "0");
        map1.put("taskId", "0729300103");
        HashMap<String, Object> map2 = Maps.newHashMap();
        map2.put("taskName", "锦绣路站-芳华路站 上行");
        map2.put("workOrderId", uuid);
        map2.put("upDown", "0");
        map2.put("taskId", "0730310103");
        HashMap<String, Object> map3 = Maps.newHashMap();
        map3.put("taskName", "锦绣路站-杨高南路站 下行");
        map3.put("workOrderId", uuid);
        map3.put("upDown", "1");
        map3.put("taskId", "0730290103");
        HashMap<String, Object> map4 = Maps.newHashMap();
        map4.put("taskName", "芳华路站-锦绣路站 下行");
        map4.put("workOrderId", uuid);
        map4.put("upDown", "1");
        map4.put("taskId", "0731300103");
        taskList.add(map1);
        taskList.add(map2);
        taskList.add(map3);
        taskList.add(map4);
        return taskList;
    }

    @Override
    public void changePlanStatus(Map params) throws Exception {
        if (params.isEmpty()) {
            throw new ServerException("任务编号为空");
        } else {
            if (null == params.get("PLAN_UUID")) {
                throw new ServerException("任务编号为空");
            } else if (StringUtils.isBlank(params.get("PLAN_UUID").toString().trim())) {
                throw new ServerException("任务编号为空");
            }
            String plan_uuid = String.valueOf(params.get("PLAN_UUID"));
            //TODO 改任务状态
            System.out.println(plan_uuid);
            //appFaultMapper.changePlanStatus(plan_uuid);

        }


    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public String uploadPlanData(Map params, String employeeNo) {
//        FileUtil.saveStringFile(JSONObject.toJSONString(params), "D:\\log\\request.json");
        JSONArray planId = (JSONArray) params.get("planId");
        String planUuid = planId.get(0).toString();
        /*String deviceLists = (String) params.get("deviceList");
        List<Map> deviceList = JSON.parseObject(deviceLists, new TypeReference<List<Map>>() {
        });*/
        Map param = Maps.newHashMap();
        //为病害添加工单号和操作人ID
        param.put("planUuid", planUuid);
        param.put("operatorNo", employeeNo);
        //保存新增编辑病害
        if (params.containsKey("addList")) {
            JSONArray ObjectLists = (JSONArray) params.get("addList");
            List<Map> dealLists = ObjectLists.toJavaList(Map.class);
            if (dealLists.size() > 0) {
                //if (item.equals("editList")) {
                //    param.put("FAULT_STATUS", "50");//复检待审核
                //} else {
                //    param.put("FAULT_STATUS", "10");//待审核
                //}
                param.put("list", dealLists);
                siFaultDataMapper.batchSaveNewFault(param);
            }
        }
        //保存病害资源
        for (String item : Arrays.asList("addList_", "editList_")) {
            for (String type : Arrays.asList("img", "video", "audio")) {
                if (!params.containsKey(item + type)) continue;
                List<Map> addRscList = (List) params.get(item + type);
                if (addRscList.size() > 0) {
                    saveFaultRes(addRscList, planUuid, type);
                }
            }
        }
        //保存检查项资源
        Set<Map> deviceIdSet = Sets.newHashSet();
        for (String type : Arrays.asList("img", "video")) {
            if (!params.containsKey("deviceCheckList_" + type)) continue;
            List<Map> addRscList = (List) params.get("deviceCheckList_" + type);
            if (addRscList.size() > 0) {
                deviceIdSet = saveDeviceRes(addRscList, planUuid, type, deviceIdSet);
            }
        }
        if (deviceIdSet.size() > 0) {
            siFaultDataMapper.updateDeviceItem(deviceIdSet);
        }
        Map updateParam = new HashMap() {{
            put("PLAN_UUID", planUuid);
            put("RE_START_DATE", params.get("workStartDate"));
            put("RE_END_DATE", params.get("workEndDate"));
        }};
        siFaultDataMapper.updateFaultPlan(updateParam);
        return planUuid;
    }


    @Transactional
    public Set<Map> saveDeviceRes(List<Map> list, String planUuid, String type, Set<Map> set) {
        type = type.toUpperCase();
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        List<Map> imageList = Lists.newArrayList();
        for (Map item : list) {
            try {
                Map itemMap = Maps.newHashMap();
                String str = "";
                String suffix = "_" + planUuid;
                String resType = "";
                String savePath = "device/" + sdf.format(new Date());
                String deviceType = "";
                switch (type) {
                    case "IMG":
                        str = (String) item.get(type);
                        savePath += "/img/";
                        suffix += ".jpg";
                        deviceType="Img";
                        resType = "1";
                        break;
                    case "VIDEO":
                        str = (String) item.get(type);
                        savePath += "/video/";
                        deviceType="Video";
                        suffix += ".mp4";
                        resType = "2";
                        break;
                }
                String[] resList = str.split("\\|\\|,");
                for (String res : resList) {
//                    if(res.endsWith("||")){
//                        res=res.substring(0,res.length()-2);
//                    }
                    res = res.substring(res.indexOf(',') + 1);
                    String deviceDetailId = (String) item.get("deviceDetail"+deviceType+"Id");
                    String[] arrStr = deviceDetailId.split("\\|\\|");
                    String fileName = UUID.randomUUID().toString().replace("-", "") + suffix;
                    ImageUtils.base64ToImg(saveResPath + savePath, res, fileName);
                    itemMap.put("DEVICE_UUID", arrStr[1]);
                    itemMap.put("RES_NAME", fileName);
                    itemMap.put("PLAN_UUID", planUuid);
                    itemMap.put("RES_TYPE", resType);
                    itemMap.put("DEVICE_CHECK", arrStr[2]);
                    itemMap.put("RES_PATH", savePath);
                    Map param = new HashMap() {{
                        put("PLAN_UUID", planUuid);
                        put("DEVICE_CHECK", arrStr[2]);
                    }};
                    imageList.add(itemMap);
                    set.add(param);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        siFaultDataMapper.saveDeviceRes(imageList);
        return set;
    }

    @Transactional
    public void saveFaultRes(List<Map> list, String planUuid, String type) {
        type = type.toUpperCase();
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        List<Map> imageList = Lists.newArrayList();
        for (Map item : list) {
            try {
                Map itemMap = Maps.newHashMap();
                String str = "";
                String suffix = "_" + planUuid;
                String resType = "";
                String savePath = "fault/" + sdf.format(new Date());
                switch (type) {
                    case "IMG":
                        str = (String) item.get(type);
                        savePath += "/img/";
                        suffix += ".jpg";
                        resType = "1";
                        break;
                    case "VIDEO":
                        str = (String) item.get(type);
                        savePath += "/video/";
                        suffix += ".mp4";
                        resType = "2";
                        break;
                    case "AUDIO":
                        str = (String) item.get(type);
                        savePath += "/audio/";
                        suffix += ".amr";
                        resType = "3";
                        break;

                }
                String[] arrStr = str.split("\\|\\|,");
                for (String strTemp : arrStr) {
//                    if(strTemp.endsWith("||")){
//                        strTemp=strTemp.substring(0,strTemp.length()-2);
//                    }
                    strTemp = strTemp.substring(strTemp.indexOf(',') + 1);
                    String INTERNAL_CODE = (String) item.get("INTERNAL_CODE");
                    String fileName = UUID.randomUUID().toString().replace("-", "") + suffix;
                    ImageUtils.base64ToImg(saveResPath + savePath, strTemp, fileName);
                    itemMap.put("INTERNAL_CODE", INTERNAL_CODE);
                    itemMap.put("RES_NAME", fileName);
                    itemMap.put("PLAN_UUID", planUuid);
                    itemMap.put("RES_TYPE", resType);
                    itemMap.put("RES_PATH", savePath);
                    imageList.add(itemMap);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        siFaultDataMapper.saveResData(imageList);
    }

    /**
     * 整合设备及检查项
     *
     * @param list
     * @return
     */
    public List getDuplicateMap(List<Map> list) {
        ArrayList<Map> result = Lists.newArrayList();
        Map<String, Map> map = Maps.newHashMap();
        for (Map m : list) {
            String device_uuid = m.get("DEVICE_UUID").toString();
            String deviceName = m.get("DEVICE_NAME").toString();
            String check_item = m.get("CHECK_ITEM").toString();
            String deviceCheckCode = m.get("DEVICE_CHECK_CODE").toString();
            String lineUuid = m.get("LINE_UUID").toString();
            String startStationUuid = m.get("START_STATION_UUID").toString();
            String endStationUuid = m.get("END_STATION_UUID").toString();
            String highLow = m.get("HIGH_LOW").toString();
            String isDoor = m.get("IS_DOOR").toString();
            if (map.containsKey(device_uuid)) {
                Map<String, String> checkItemsMap = createCheckItemMap(device_uuid, deviceName, check_item, deviceCheckCode, lineUuid, startStationUuid, endStationUuid, highLow);
                Map map1 = map.get(device_uuid);
                List checkItemsList = (List) map1.get("CHECK_ITEMS");
                checkItemsList.add(checkItemsMap);
            } else {
                HashMap<String, Object> map1 = Maps.newHashMap();
                map1.put("DEVICE_UUID", device_uuid);
                map1.put("DEVICE_NAME", deviceName);
                map1.put("IS_DOOR", isDoor);
                Map<String, String> checkItemsMap = createCheckItemMap(device_uuid, deviceName, check_item, deviceCheckCode, lineUuid, startStationUuid, endStationUuid, highLow);
                map1.put("CHECK_ITEMS", Lists.newArrayList(checkItemsMap));
                map.put(device_uuid, map1);
            }
        }
        for (Map.Entry<String, Map> entry : map.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }

    private Map<String, String> createCheckItemMap(String device_uuid, String deviceName, String check_item, String deviceCheckCode, String lineUuid, String startStationUuid, String endStationUuid, String highLow) {
        Map<String, String> checkItemsMap = Maps.newHashMap();
        checkItemsMap.put("CHECK_ITEM_NAME", check_item);
        checkItemsMap.put("CHECK_ITEM_ID", device_uuid + "_" + deviceCheckCode);
        checkItemsMap.put("DEVICE_NAME", deviceName);
        checkItemsMap.put("LINE_UUID", lineUuid);
        checkItemsMap.put("START_STATION_UUID", startStationUuid);
        checkItemsMap.put("END_STATION_UUID", endStationUuid);
        checkItemsMap.put("HIGH_LOW", highLow);
        return checkItemsMap;
    }


    private class MileageComparator implements Comparator {

        @Override
        public int compare(Object o1, Object o2) {
            try {
                int ki = ((BigDecimal) ((Map) o1).get("START_KI_MILEAGE")).intValue() - ((BigDecimal) ((Map) o2).get("START_KI_MILEAGE")).intValue();
                if (ki == 0) {
                    int hum = ((BigDecimal) ((Map) o1).get("START_HUN_MILEAGE")).intValue() - ((BigDecimal) ((Map) o2).get("START_HUN_MILEAGE")).intValue();
                    return hum;
                } else {
                    return ki;
                }

            } catch (Exception e) {
            }
            return -1;
        }
    }

}
