package com.company.project.module.inspect.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.company.project.core.ProjectConstant;
import com.company.project.core.annotation.VerifyRedis;
import com.company.project.core.util.redis.RedisUtil;
import com.company.project.module.data.dao.DeviceLevelMapper;
import com.company.project.module.data.dao.MetroDeviceMapper;
import com.company.project.module.inspect.dao.DeviceFaultTypeMapper;
import com.company.project.module.inspect.service.DeviceService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Chen
 * @created 2019-11-18-14:02.
 */
@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceFaultTypeMapper deviceFaultTypeMapper;
    @Autowired
    private DeviceLevelMapper deviceLevelMapper;

    @Autowired
    private MetroDeviceMapper metroDeviceMapper;

    @Value("${fault-image.path}")
    private String checkResPath;
    @Resource
    private RedisUtil redisUtil;

    @Override
    public List findAllDeviceFaultType(Map params) {
        List<Map> result = deviceFaultTypeMapper.findDeviceFaultTypeByV(params);
        return result;
    }

    @Override
    @VerifyRedis(ProjectConstant.TD_DEVICE_FAULT_TYPE_KEY)
    public Map<String, Object> findDeviceFaultTypeDict() {
        /**
         *  {
         *      level3+level4:[{name:原型隧道level3+level4,id:device_level_uuid,code:device_level_code,pid:level1+level2},{},{}]
         *      deviceType:[{name:DEVICE_TYPE,id:device_level_code+DEVICE_TYPE_CODE,pid:level3+level4},{},{}]
         *      FAULT_TYPE1:[{name:FAULT_TYPE1,id:device_level_code+DEVICE_TYPE_CODE+FAULT_TYPE1_code,pid:deviceType},{},{}]
         *      FAULT_TYPE2:[{name:FAULT_TYPE2,id:device_level_code+DEVICE_TYPE_CODE+FAULT_TYPE2_code,pid:FAULT_TYPE1},{},{}]
         *  }
         */
        HashMap<String, Object> dictMap = Maps.newHashMap();
        List<Map> result = deviceFaultTypeMapper.findDeviceFaultTypeByV(Maps.newHashMap());
        result.forEach(type -> {
            String deviceLevelCode = String.valueOf(type.get("DEVICE_LEVEL_CODE"));
            String deviceLevelUuid = String.valueOf(type.get("DEVICE_LEVEL_UUID"));
            String level1 = String.valueOf(type.get("LEVEL1"));
            String level2 = String.valueOf(type.get("LEVEL2"));
            String level3 = String.valueOf(type.get("LEVEL3"));
            String level4 = String.valueOf(type.get("LEVEL4"));
            String deviceTypeCode = String.valueOf(type.get("DEVICE_TYPE_CODE"));
            String deviceType = String.valueOf(type.get("DEVICE_TYPE"));
            String faultType1 = String.valueOf(type.get("FAULT_TYPE1"));
            String faultType2 = String.valueOf(type.get("FAULT_TYPE2"));
            String faultType1Code = String.valueOf(type.get("FAULT_TYPE1_CODE"));
            String faultType2Code = String.valueOf(type.get("FAULT_TYPE2_CODE"));
            String deviceTypeKey = deviceLevelCode + deviceTypeCode;
            String faultType1Key = deviceTypeKey + faultType1Code;
            String faultType2Key = faultType1Key + faultType2Code;
            String level2Key = deviceLevelCode.substring(0, 4);
            String level3Key = deviceLevelCode.substring(0, 6);
            /**
             * 设备树
             */
            if (!dictMap.containsKey("level")) {
                HashMap<String, Object> map = loadDeviceLevelCodeMap(level4 + "(" + level3 + ")", deviceLevelCode, deviceLevelCode, level3Key);
                dictMap.put("level", Lists.newArrayList(map));
            } else {
                List deviceLevelList = (List) dictMap.get("level");
                HashMap<String, Object> map = loadDeviceLevelCodeMap(level4 + "(" + level3 + ")", deviceLevelCode, deviceLevelCode, level3Key);
                deviceLevelList.add(map);
            }
            /**
             * 设备类型字典
             */
            createDictData(dictMap, "device", deviceType, deviceTypeKey, deviceTypeKey, deviceLevelCode);
            /**
             * 故障类型1字典
             */
            createDictData(dictMap, "fault1", faultType1, faultType1Key, faultType1Key, deviceTypeKey);
            /**
             * 故障类型2字典
             */
            createDictData(dictMap, "fault2", faultType2, faultType2Key, faultType2Key, faultType1Key);


            createDictData(dictMap, "level3", level3, level3Key, level3Key, level2Key);
            createDictData(dictMap, "level4", level4, deviceLevelCode, deviceLevelCode, level3Key);

        });
        dictMap.replace("level", getList((List<Map>) dictMap.get("level")));
        filterList(dictMap, "device");
        filterList(dictMap, "fault1");
        filterList(dictMap, "fault2");
        filterList(dictMap, "level3");
        filterList(dictMap, "level4");
        redisUtil.set(ProjectConstant.TD_DEVICE_FAULT_TYPE_KEY, JSONObject.toJSONString(dictMap), 24 * 60 * 60);
        return dictMap;
    }

    @Override
    public List findAllDeviceLevel(Map params) {
        return deviceLevelMapper.selectAllDeviceLevel();
    }

    @Override
    public List findAllDevice(Map params) {
        return metroDeviceMapper.findEamDevice(params);
    }

    @Override
    public List findCheckDevice(Map params) {
        return metroDeviceMapper.findCheckDevice(params);
    }

    @Override
    public Map findcheckItem(Map params) {
        Map result =Maps.newHashMap();
        List<Map> mapsList = metroDeviceMapper.findcheckItem(params);
        for(Map map : mapsList ){
            String key = map.get("DICT_VALUE").toString();
            if(result.get(key) == null){
                List itemList = Lists.newArrayList();
                if(map.get("IMG_URL") != null){
                    itemList.add(checkResPath+map.get("IMG_URL").toString());
                }
                result.put(key,itemList);
            }else{
                List itemList = (List)result.get(key);
                if(map.get("IMG_URL") != null){
                    itemList.add(checkResPath+map.get("IMG_URL").toString());
                }
                result.put(key,itemList);
            }
        }
             return result;
    }

    @Override
    public Map<String, Object> getDict(Map params) {
        Map result = Maps.newHashMap();
        List<String> yearList = metroDeviceMapper.getResYear(params);
        result.put("year",yearList);
        return result;
    }

    /**
     * 去重子list
     *
     * @param dictMap
     * @param key
     */
    private void filterList(Map dictMap, String key) {
        Map map = (Map) dictMap.get(key);
        map.forEach((k, v) -> {
            map.replace(k, getList((List<Map>) v));
        });
    }

    /**
     * 构建字典子选项
     *
     * @param dictMap
     * @param key
     * @param name
     * @param id
     * @param code
     * @param pid
     */
    private void createDictData(HashMap<String, Object> dictMap, String key, String name, String id, String code, String pid) {
        if (!dictMap.containsKey(key)) {
            HashMap<String, Object> item = loadDeviceLevelCodeMap(name, id, code, pid);
            HashMap<String, Object> map = Maps.newHashMap();
            map.put(pid, Lists.newArrayList(item));
            dictMap.put(key, map);
        } else {
            Map map = (Map) dictMap.get(key);
            HashMap<String, Object> item = loadDeviceLevelCodeMap(name, id, code, pid);
            if (!map.containsKey(pid)) {
                map.put(pid, Lists.newArrayList(item));
            } else {
                List list = (List) map.get(pid);
                list.add(item);
            }
        }
    }

    //设备树字典
    private HashMap<String, Object> loadDeviceLevelCodeMap(String name, String id, String code, String pid) {
        HashMap<String, Object> map = Maps.newHashMap();
        //文字内容
        map.put("name", name);
        //id
        map.put("id", id);
        //code
        map.put("code", code);
        //父级
        map.put("pid", pid);
        //父类型
        map.put("type", pid);
        return map;
    }

    /**
     * 根据id去重
     *
     * @param list
     * @return
     */
    private List getList(List<Map> list) {
        Set<Map> treeSet = new TreeSet<Map>(new Comparator<Map>() {
            @Override
            public int compare(Map o1, Map o2) {
                int compareTo = o1.get("id").toString().compareTo(o2.get("id").toString());
                return compareTo;
            }
        });
        treeSet.addAll(list);
        return Lists.newArrayList(treeSet);
    }
}
