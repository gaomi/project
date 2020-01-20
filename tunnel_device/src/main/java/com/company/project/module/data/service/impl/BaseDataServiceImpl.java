package com.company.project.module.data.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.company.project.core.ProjectConstant;
import com.company.project.core.annotation.VerifyRedis;
import com.company.project.core.util.redis.RedisUtil;
import com.company.project.module.data.dao.TdDictMapper;
import com.company.project.module.data.dao.TdStationMapper;
import com.company.project.module.data.model.TdDict;
import com.company.project.module.data.service.BaseDataService;
import com.company.project.module.data.service.TdSegmentService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author Chen
 * @created 2019-11-17-14:04.
 */
@Service
public class BaseDataServiceImpl implements BaseDataService {
    @Resource
    private TdStationMapper tdStationMapper;
    // @Resource
    // private MetroDeviceInfoMapper metroDeviceInfoMapper;
    @Resource
    private TdSegmentService tdSegmentService;
    @Resource
    private TdDictMapper tdDictMapper;

    @Resource
    private RedisUtil redisUtil;

    @Override
    @VerifyRedis(ProjectConstant.LINE_STATION_DEVICE_KEY)
    public Map findBaseInfoForApp(Map params) {
        List stations = tdStationMapper.findBaseInfoForApp(params);
        List<Map> devices = null;
        //List<Map> devices = metroDeviceInfoMapper.findDeviceInfoForApp();
        HashMap<Object, Object> map = Maps.newHashMap();
        //list深复制
        List stationsInfoList = getDuplicateBaseInfoList(stations);
        ArrayList arrayList = Lists.newArrayList();
        CollectionUtils.addAll(arrayList, new Object[stationsInfoList.size()]);
        Collections.copy(arrayList, stationsInfoList);
        List baseInfoList = getDuplicateDeviceList(arrayList, devices);
        map.put("devices", baseInfoList);
        redisUtil.set(ProjectConstant.LINE_STATION_DEVICE_KEY, JSONObject.toJSONString(map), 24 * 60 * 60);
        return map;
    }

    /**
     * 整合设备
     *
     * @param list
     * @param list2
     * @return *
     * *{
     * *  设备：{
     * *       站id1:[{设备1},{}],
     * *       站id2:[{},{}],
     * * }
     */
    public List getDuplicateDeviceList(List<Map> list, List<Map> list2) {
        list2.forEach(map -> {
            String line_uuid = map.get("LINE_UUID").toString();
            String start_station_uuid = map.get("START_STATION_UUID").toString();
            String end_station_uuid = map.get("END_STATION_UUID").toString();

            list.forEach(lineMap -> {
                String line = lineMap.get("LINE_UUID").toString();
                if (line.equals(line_uuid)) {
                    if (lineMap.containsKey("DEVICES")) {
                        //包含设备map
                        Map stationsMap = (Map) lineMap.get("DEVICES");
                        if (stationsMap.containsKey(start_station_uuid)) {
                            List devicesList = (List) stationsMap.get(start_station_uuid);
                            devicesList.add(map);
                        } else if (stationsMap.containsKey(end_station_uuid)) {
                            List devicesList = (List) stationsMap.get(end_station_uuid);
                            devicesList.add(map);
                        } else {
                            stationsMap.put(start_station_uuid, Lists.newArrayList(map));
                            stationsMap.put(end_station_uuid, Lists.newArrayList(map));
                        }
                    } else {
                        Map stationsMap = Maps.newHashMap();
                        stationsMap.put(start_station_uuid, Lists.newArrayList(map));
                        stationsMap.put(end_station_uuid, Lists.newArrayList(map));
                        lineMap.put("DEVICES", stationsMap);
                    }
                }
            });

        });
        return list;
    }

    @Override
    @VerifyRedis(ProjectConstant.LINE_STATION_KEY)
    public Map findStationInfoForWeb(Map params) {
        List stations = tdStationMapper.findBaseInfoForApp(params);
        HashMap<Object, Object> map = Maps.newHashMap();
        List stationsInfoList = getDuplicateBaseInfoList(stations);
        map.put("stations", stationsInfoList);
        redisUtil.set(ProjectConstant.LINE_STATION_KEY, JSONObject.toJSONString(map), 24 * 60 * 60);
        return map;
    }


    @Override
    public List<Map> findSegmentInfoForApp(Map params) {
        return tdSegmentService.findSegmentInfoForApp(params);
    }

    @Override
    public Map findDataDictForWeb(Map params) {
        List<TdDict> dictDataByModule = tdDictMapper.findDictDataByModule(params);
        HashMap<String, Object> map = Maps.newHashMap();
        dictDataByModule.forEach(dict -> {
            if (StringUtils.equals(dict.getPid(), "0")) {
                map.put(dict.getDictKey(), dict);
                map.put(dict.getUuid(), Lists.newArrayList());
            } else {
                if (map.containsKey(dict.getPid())){
                    List list = (List) map.get(dict.getPid());
                    list.add(dict);
                }else {
                    ArrayList<Object> list = Lists.newArrayList();
                    list.add(dict);
                    map.put(dict.getPid(),list);

                }

            }

        });
        return map;
    }

    /**
     * 整合线路及站，设备
     *
     * @param list
     * @return
     */
    private List getDuplicateBaseInfoList(List<Map> list) {
        ArrayList<Map> result = Lists.newArrayList();
        Map<String, Map> map = Maps.newHashMap();
        for (Map m : list) {
            String line_uuid = m.get("LINE_UUID").toString();
            String line_name = m.get("LINE_NAME").toString();
            String line_code = m.get("LINE_CODE").toString();
            String remark = m.get("REMARK").toString();
            String station_uuid = m.get("STATION_UUID").toString();
            String station_name = m.get("STATION_NAME").toString();
            String station_code = m.get("STATION_CODE").toString();
            String station_tunnel = m.get("IS_TUNNEL").toString();

            if (map.containsKey(line_uuid)) {
                Map<String, String> stationMap = Maps.newHashMap();
                stationMap.put("STATION_UUID", station_uuid);
                stationMap.put("STATION_NAME", station_name);
                stationMap.put("STATION_CODE", station_code);
                stationMap.put("IS_TUNNEL", station_tunnel);
                Map map1 = map.get(line_uuid);
                List stationsList = (List) map1.get("STATIONS");
                stationsList.add(stationMap);
            } else {
                HashMap<String, Object> map1 = Maps.newHashMap();
                map1.put("LINE_UUID", line_uuid);
                map1.put("LINE_NAME", line_name);
                map1.put("LINE_COLOR", remark);
                map1.put("LINE_CODE", line_code);
                Map<String, String> stationMap = Maps.newHashMap();
                stationMap.put("STATION_UUID", station_uuid);
                stationMap.put("STATION_NAME", station_name);
                stationMap.put("STATION_CODE", station_code);
                map1.put("STATIONS", Lists.newArrayList(stationMap));
                map.put(line_uuid, map1);
            }
        }
        for (Map.Entry<String, Map> entry : map.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }


}
