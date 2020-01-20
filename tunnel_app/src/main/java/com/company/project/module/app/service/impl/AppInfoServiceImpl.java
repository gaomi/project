package com.company.project.module.app.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.company.project.core.AbstractService;
import com.company.project.core.ProjectConstant;
import com.company.project.core.annotation.VerifyRedis;
import com.company.project.core.util.TreeUtils;
import com.company.project.core.util.redis.RedisUtil;
import com.company.project.module.app.dao.AppVersionMapper;
import com.company.project.module.app.model.AppVersion;
import com.company.project.module.app.service.AppInfoService;
import com.company.project.module.data.dao.MetroDeviceInfoMapper;
import com.company.project.module.data.dao.TdDictMapper;
import com.company.project.module.data.dao.TdStationMapper;
import com.company.project.module.data.model.TdStation;
import com.company.project.module.inspect.dao.FaultPlanMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.*;


@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class AppInfoServiceImpl extends AbstractService<TdStation> implements AppInfoService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TdStationMapper tdStationMapper;
    @Autowired
    private MetroDeviceInfoMapper metroDeviceInfoMapper;

    @Autowired
    private FaultPlanMapper faultPlanMapper;

    @Autowired
    private AppVersionMapper appVersionMapper;

    @Autowired
    private TdDictMapper tdDictMapper;

    @Resource
    private RedisUtil redisUtil;

    @Override
    @VerifyRedis(ProjectConstant.LINE_STATION_DEVICE_KEY)
    public Map findBaseInfoForApp(Map params) {
        List stationsInfoList = getBaseStationInfoForApp(params);
        ArrayList arrayList = copyBaseInfoList(stationsInfoList);
        List<Map> devices = metroDeviceInfoMapper.findDeviceInfoForApp();
        List baseInfoList = getDuplicateDeviceList(arrayList, devices);
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("devices", baseInfoList);
        redisUtil.set(ProjectConstant.LINE_STATION_DEVICE_KEY, JSONObject.toJSONString(map), 24 * 60 * 60);
        return map;
    }

    @Override
    @VerifyRedis(ProjectConstant.LINE_STATION_KEY)
    public Map findStationInfoForApp(Map params) {
        List stations = tdStationMapper.findBaseInfoForApp(params);
        HashMap<Object, Object> map = Maps.newHashMap();
        //list深复制
        List stationsInfoList = getDuplicateBaseInfoList(stations);
        map.put("stations", stationsInfoList);
        redisUtil.set(ProjectConstant.LINE_STATION_KEY, JSONObject.toJSONString(map), 24 * 60 * 60);
        return map;
    }

    @Override
    public AppVersion findVersionForApp(Map params) {
        //手机系统版本
        String appType = params.get("appType").toString();
        Example example = new Example(AppVersion.class);
        example.createCriteria().andEqualTo("status", "1").andEqualTo("appType", appType);
        List<AppVersion> appVersions = appVersionMapper.selectByExample(example);
        return appVersions.get(0);
    }

    @Override
    public Map findDictForApp(String module) {
        HashMap<String, Object> dictMap = getBaseDictData();
        /*dictMap.put("t_fault_unit",dictMap.get("fault_unit"));
        //pid=200003

        dictMap.put("t_falut_grade",null);
        //pid=200002
        dictMap.put("t_middle_type_id",dictMap.get("fault_unit"));
        HashMap<String, Object> middleMap = Maps.newHashMap();
        middleMap.put("middle_type_id",dictMap.get("middle_type_id"));

        dictMap.put("big_type_id",middleMap);*/

      /*   //pid=6

        //pid=200007
        dictMap.put("", null);
        //pid=
        dictMap.put("", null);
        //pid=
        dictMap.put("", null);
        //pid=
        dictMap.put("work_type", null);
        //pid=
        dictMap.put("", null);
        //pid=10
       unitMap.put("fault_unit",null);
        //pid=200003
        gradeMap.put("falut_grade",null);
        //pid=200002
        middleMap.put("middle_type_id",null);*/

        return dictMap;
        //pid=200001
        /* map.put("big_type_id", null);
        List<Tree<TdDict>> trees = new ArrayList<>();
        dicts.forEach(dict -> {
            Tree<TdDict> tree = new Tree<>();
            tree.setId(dict.getUuid());
            tree.setParentId(dict.getPid());
            tree.setText(dict.getDictKey());
            tree.setAttributes((Map<String, Object>) PaodingUtils.objectToMap(dict));
            trees.add(tree);
        });
        return TreeUtils.buildList(trees, "0");*/
    }

    @Override
    public Map findDeviceInfoForApp() {
        //TODO  后期存redis
        List<Map> devices = metroDeviceInfoMapper.findDeviceInfoForApp();

        // List duplicateDeviceList = getDuplicateDeviceList(devices);

        return null;
    }

    @Override
    @VerifyRedis(ProjectConstant.APP_BASE_INFO)
    public Map getBaseInfoDataForApp() {
        try {
            //List stationsInfoList = getBaseStationInfoForApp(Maps.newHashMap());
            //ArrayList arrayList = copyBaseInfoList(stationsInfoList);
            //List<Map> devices = metroDeviceInfoMapper.findDeviceInfoForApp();
            //List deviceInfoList = getDuplicateDeviceList(arrayList, devices);

            HashMap<String, Object> map = Maps.newHashMap();
            //map.put("stations", stationsInfoList);
            //map.put("devices", deviceInfoList);
            HashMap<String, Object> dictInfoMaps = getBaseDictData();
            Map faultTypeMap = getFaultTypeForApp();
            dictInfoMaps.put("fault_type", faultTypeMap);
            map.put("dicts", dictInfoMaps);
            redisUtil.set(ProjectConstant.APP_BASE_INFO, JSONObject.toJSONString(map), 24 * 60 * 60);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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

    /**
     * 获取病害分类,20191212修改备份
     *
     * @return
     */
    public List getFaultTypeForApp_Bak() {
        List<Map> faultTypeDictForApp = tdDictMapper.findFaultTypeDictForApp();
        //封装所有数据到map下的set
        HashMap<String, Object> map = Maps.newHashMap();
        faultTypeDictForApp.forEach(m -> {
            String majorType = String.valueOf(m.get("MAJOR_TYPE"));
            String majorTypeName = String.valueOf(m.get("MAJOR_TYPE_T"));
            String bigType = String.valueOf(m.get("BIG_TYPE"));
            String bigTypeName = String.valueOf(m.get("BIG_TYPE_T"));
            String middleType = String.valueOf(m.get("MIDDLE_TYPE"));
            String middleTypeName = String.valueOf(m.get("MIDDLE_TYPE_T"));
            String smallType = String.valueOf(m.get("SMALL_TYPE"));
            String typeDesc = String.valueOf(m.get("TYPE_DESC"));
            String gradeType = String.valueOf(m.get("GRADE_TYPE"));
            String gradeDesc = String.valueOf(m.get("GRADE_DESC"));

/**
 * fault_type[{
 * 			"name": "隧道",
 * 			"id": "WBGWSD",
 * 			"childrens":[{
 * 			    "id": "WBGWSD0102",
 * 				"name": "矩形衬砌",
 * 			    "childrens":[{
 * 			        "id": "WBGWSD010201",
 * 					"name": "渗水",
 * 			        "childrens":[{
 * 			            "id": "AA",
 * 			            "name": "AA",
 * 			            "desc": "描述AA",
 * 			            "childrens": []
 *                        }]
 *                }]
 *            }]
 *
 * ]
 */
//major最外层
            if (map.containsKey("majorTypeSet")) {
                Set set = (Set) map.get("majorTypeSet");
                TreeUtils.addSetData(set, majorType, majorTypeName, null);
            } else {
                Set set = TreeUtils.addSetData(Sets.newHashSet(), majorType, majorTypeName, null);
                map.put("majorTypeSet", set);
            }
            putSetData(map, bigType, bigTypeName, "bigTypeMaps");
            putSetData(map, middleType, middleTypeName, "middleTypeMaps");

            Map gradeMap = putMapData(gradeType, gradeDesc, Maps.newHashMap());
            gradeMap.put("childrens", null);

            if (map.containsKey("smallTypeMaps")) {
                Map smallTypeMaps = (Map) map.get("smallTypeMaps");
                if (smallTypeMaps.containsKey(smallType)) {
                    Map smallTypeMap = (Map) smallTypeMaps.get(smallType);
                    List childrens = (List) smallTypeMap.get("childrens");
                    childrens.add(gradeMap);
                } else {
                    Map smallTypeMap = Maps.newHashMap();
                    smallTypeMap.put("id", smallType);
                    smallTypeMap.put("name", smallType);
                    smallTypeMap.put("desc", typeDesc);
                    ArrayList<Map> gradeList = Lists.newArrayList();
                    gradeList.add(gradeMap);
                    smallTypeMap.put("childrens", gradeList);
                    smallTypeMaps.put(smallType, smallTypeMap);
                }
            } else {
                Map smallTypeMaps = Maps.newHashMap();
                Map smallTypeMap = Maps.newHashMap();
                smallTypeMap.put("id", smallType);
                smallTypeMap.put("name", smallType);
                smallTypeMap.put("desc", typeDesc);
                ArrayList<Map> gradeList = Lists.newArrayList();
                gradeList.add(gradeMap);
                smallTypeMap.put("childrens", gradeList);
                smallTypeMaps.put(smallType, smallTypeMap);
                map.put("smallTypeMaps", smallTypeMaps);

            }
            //  putData(map, gradeType, gradeDesc, "gradeTypeMaps");
            //putSetData(map, smallType, typeDesc, "smallTypeMaps");

           /*   putSetData(map, middleType, middleTypeName, "middleTypeMaps");

            Map gradeMap = Maps.newHashMap();
            gradeMap.put("id", gradeType);
            gradeMap.put("name", gradeType);
            gradeMap.put("desc", gradeDesc);
            gradeMap.put("childrens", null);
            if (map.containsKey("smallTypeMaps")) {
                Map smallTypeMap = (Map) map.get("smallTypeMaps");
                if (smallTypeMap.containsKey("childrens")) {
                    List childrens = (List) smallTypeMap.get("childrens");
                    childrens.add(gradeMap);
                } else {
                    putMapData(smallType, typeDesc, gradeMap, smallTypeMap);
                }
            } else {
                Map smallTypeMap = Maps.newHashMap();
                putMapData(smallType, typeDesc, gradeMap, smallTypeMap);
                map.put("smallTypeMaps", smallTypeMap);

            }*/
        });


        Set marjorSet = loadTreeData(map);
        return Lists.newArrayList(marjorSet);
    }

    public Map getFaultTypeForApp() {

        List<Map> list = tdDictMapper.findFaultTypeDictForAppN();
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("level3", Sets.newHashSet());
        map.put("level4", Maps.newHashMap());
        map.put("device_type", Maps.newHashMap());
        map.put("fault1", Maps.newHashMap());
        map.put("fault2", Maps.newHashMap());
        List doorType = Arrays.asList("01030501", "01030502", "01030503");
        list.stream().forEach(m -> {
            String level3Code = String.valueOf(m.get("LEVEL3_CODE"));
            String level3Name = String.valueOf(m.get("LEVEL3_NAME"));
            String level4Code = String.valueOf(m.get("LEVEL4_CODE"));
            String level4Name = String.valueOf(m.get("LEVEL4_NAME"));
            String deviceTypeCode = String.valueOf(m.get("DEVICE_TYPE_CODE"));
            String deviceTypeName = String.valueOf(m.get("DEVICE_TYPE_NAME"));
            String fault1Code = String.valueOf(m.get("FAULT1_CODE"));
            String fault1Name = String.valueOf(m.get("FAULT1_NAME"));
            String fault2Code = String.valueOf(m.get("FAULT2_CODE"));
            String fault2Name = String.valueOf(m.get("FAULT2_NAME"));
            String fault3Name = String.valueOf(m.get("FAULT3_NAME"));
            String fault4Name = String.valueOf(m.get("FAULT4_NAME"));

            //第一层
            Set level1Set = (Set) map.get("level3");
            level1Set.add(putMapData(level3Code, level3Name, Maps.newHashMap()));
            //判断LEVEL4是三防门向LEVEL3添加门  List doorType = Arrays.asList("01030501", "01030502", "01030503");
            //第二层
            if (doorType.contains(level4Code)) {
                level1Set.add(putMapData(level4Code, level4Name, Maps.newHashMap()));
                loadSelectChild(map, level4Code, level4Code, level4Name, "level4");
            } else {
                loadSelectChild(map, level3Code, level4Code, level4Name, "level4");
            }
            //第三层
            loadSelectChild(map, level4Code, deviceTypeCode, deviceTypeName, "device_type");
            //第四层
            loadSelectChild(map, deviceTypeCode, fault1Code, fault1Name, "fault1");
            //第五层
            Map fault2TypeMap = (Map) map.get("fault2");
            if (fault2TypeMap.containsKey(fault1Code)) {
                Set deviceTypeSet = (Set) fault2TypeMap.get(fault1Code);
                deviceTypeSet.add(putMapData(fault2Code, fault2Name, fault3Name, fault4Name, Maps.newHashMap()));
            } else {
                HashSet<Map> set = Sets.newHashSet(putMapData(fault2Code, fault2Name, fault3Name, fault4Name, Maps.newHashMap()));
                fault2TypeMap.put(fault1Code, set);
            }

        });
        /*list.stream().forEach(m -> {
            String majorType = String.valueOf(m.get("MAJOR_TYPE"));
            String majorTypeName = String.valueOf(m.get("MAJOR_TYPE_T"));
            String bigType = String.valueOf(m.get("BIG_TYPE"));
            String bigTypeName = String.valueOf(m.get("BIG_TYPE_T"));
            String middleType = String.valueOf(m.get("MIDDLE_TYPE"));
            String middleTypeName = String.valueOf(m.get("MIDDLE_TYPE_T"));
            String typeDesc = String.valueOf(m.get("TYPE_DESC"));
            String smallType = String.valueOf(m.get("SMALL_TYPE"));
            String gradeType = String.valueOf(m.get("GRADE_TYPE"));
            String unitType = String.valueOf(m.get("UNIT_TYPE"));
            //第一层
            if (map.containsKey("majorTypeSet")) {
                Set set = (Set) map.get("majorTypeSet");
                TreeUtils.addSetData(set, majorType, majorTypeName, null);
            } else {
                Set set = TreeUtils.addSetData(Sets.newHashSet(), majorType, majorTypeName, null);
                map.put("majorTypeSet", set);
            }

            //第二层
            putSetData(map, bigType, bigTypeName, "bigTypeMaps");
            //第三层
            putSetData(map, middleType, middleTypeName, "middleTypeMaps");
            //第四层
            Map gradeMap = putMapData(gradeType, typeDesc, unitType, Maps.newHashMap());
            gradeMap.put("childrens", null);

            if (map.containsKey("smallTypeMaps")) {
                Map smallTypeMaps = (Map) map.get("smallTypeMaps");
                if (smallTypeMaps.containsKey(smallType)) {
                    Map smallTypeMap = (Map) smallTypeMaps.get(smallType);
                    List childrens = (List) smallTypeMap.get("childrens");
                    childrens.add(gradeMap);
                } else {
                    Map smallTypeMap = Maps.newHashMap();
                    smallTypeMap.put("id", smallType);
                    smallTypeMap.put("name", smallType);
                    smallTypeMap.put("desc", typeDesc);
                    ArrayList<Map> gradeList = Lists.newArrayList();
                    gradeList.add(gradeMap);
                    smallTypeMap.put("childrens", gradeList);
                    smallTypeMaps.put(smallType, smallTypeMap);
                }
            } else {
                Map smallTypeMaps = Maps.newHashMap();
                Map smallTypeMap = Maps.newHashMap();
                smallTypeMap.put("id", smallType);
                smallTypeMap.put("name", smallType);
                smallTypeMap.put("desc", typeDesc);
                ArrayList<Map> gradeList = Lists.newArrayList();
                gradeList.add(gradeMap);
                smallTypeMap.put("childrens", gradeList);
                smallTypeMaps.put(smallType, smallTypeMap);
                map.put("smallTypeMaps", smallTypeMaps);

            }
        });
        Set marjorSet = loadTreeData(map);*/


        return map;
    }

    private Set loadTreeData(HashMap<String, Object> map) {
        //todo 拼树数据
        Map middleMap = (Map) map.get("middleTypeMaps");
        middleMap.forEach((k, v) -> {
            Set middles = (Set) v;
            middles.forEach(middle -> {
                Map middle1 = (Map) middle;
                String id = String.valueOf(middle1.get("id"));
                Map smallTypeMaps = (Map) map.get("smallTypeMaps");
                smallTypeMaps.forEach((smallk, smallv) -> {
                    String s = smallk.toString();
                    String substringid = s.substring(0, s.length() - 2);
                    if (StringUtils.equals(substringid, id)) {
                        List childrensList = (List) middle1.get("childrens");
                        childrensList.add(smallv);
                    }
                });

            });
        });

        Map bigMap = (Map) map.get("bigTypeMaps");
        bigMap.forEach((k, v) -> {
            Set bigs = (Set) v;
            bigs.forEach(big -> {
                Map big1 = (Map) big;
                String id = String.valueOf(big1.get("id"));
                Map middleTypeMaps = (Map) map.get("middleTypeMaps");
                Set middleSet = (Set) middleTypeMaps.get(id);
                List childrensList = (List) big1.get("childrens");
                childrensList.addAll(Lists.newArrayList(middleSet));
            });
        });
        Set marjorSet = (Set) map.get("majorTypeSet");
        marjorSet.forEach(marjor -> {
            Map marjormap = (Map) marjor;
            String marjorid = (String) marjormap.get("id");
            Map bigTypeMaps = (Map) map.get("bigTypeMaps");
            Set bigsSet = (Set) bigTypeMaps.get(marjorid);
            List childrensList = (List) marjormap.get("childrens");
            childrensList.addAll(Lists.newArrayList(bigsSet));
        });
        return marjorSet;
    }

    private Map putMapData(String type, String desc, Map pMap) {
        pMap.put("id", type);
        pMap.put("name", desc);
        pMap.put("desc", desc);
        return pMap;
    }

    private Map putMapData(String type, String desc, Map map, Map pMap) {
        Map nmap = putMapData(type, desc, pMap);
        ArrayList<Map> list = Lists.newArrayList();
        list.add(map);
        nmap.put("childrens", list);
        return nmap;
    }

    private Map putMapData(String type, String desc, String unit, Map pMap) {
        pMap.put("id", type);
        pMap.put("name", desc);
        pMap.put("desc", desc);
        pMap.put("unit", unit);
        return pMap;
    }

    private void putSetData(HashMap<String, Object> pMap, String type, String name, String key) {
        HashMap<String, Set> map = pMap.containsKey(key) ? (HashMap<String, Set>) pMap.get(key) : Maps.newHashMap();
        String pKey = StringUtils.substring(type, 0, type.length() - 2);
        if (map.containsKey(pKey)) {
            Set set = map.get(pKey);
            TreeUtils.addSetData(set, type, name, null);
        } else {
            Set set = TreeUtils.addSetData(Sets.newHashSet(), type, name, null);
            map.put(pKey, set);
            if (!pMap.containsKey(key)) {
                pMap.put(key, map);
            }
        }
    }

    /**
     * 字典数据包装
     *
     * @return
     */
    private HashMap<String, Object> getBaseDictData() {
        //todo 改成根据每个pid查
        List<Map> dicts = tdDictMapper.findDictForApp();
        HashMap<String, List> map = Maps.newHashMap();
        dicts.forEach(m -> {
            String type = m.get("D_TYPE").toString();
            String dict_key = m.get("DICT_KEY").toString();
            String dict_uuid = m.get("UUID").toString();
            if (type.equals("p")) {
                if (map.isEmpty()) {
                    map.put(dict_uuid, Lists.newArrayList());
                } else if (CollectionUtils.isEmpty(map.get(dict_uuid))) {
                    map.put(dict_uuid, Lists.newArrayList());
                }
            } else {
                String dict_vaule = m.get("DICT_VALUE").toString();
                String dict_pid = m.get("PID").toString();
                String dict_remark = null;
                if (dict_pid.equals("154")) {
                    //隔断门拍照留存选项
                    dict_remark = m.get("REMARK").toString();
                }

                if (CollectionUtils.isEmpty(map.get(dict_pid))) {
                    ArrayList<Object> list = Lists.newArrayList();
                    createDictMap(map, dict_key, dict_vaule, dict_uuid, dict_pid, dict_remark, list);
                } else {
                    List list = map.get(dict_pid);
                    createDictMap(map, dict_key, dict_vaule, dict_uuid, dict_pid, dict_remark, list);
                }

            }


                /*if (CollectionUtils.isEmpty(map.get("major"))){
                    ArrayList<Object> list = Lists.newArrayList();
                    HashMap<String, Object> map1 = Maps.newHashMap();
                    map1.put("uuid",dict.getUuid());
                    map1.put("dict_key",dict.getDictKey());
                    map1.put("dict_vaule",dict.getDictValue());
                    map1.put("pid",dict.getPid());
                    list.add(map1);
                    map.put("major",list);
                }else   {
                    HashMap<String, Object> map1 = Maps.newHashMap();
                    map1.put("uuid",dict.getUuid());
                    map1.put("dict_key",dict.getDictKey());
                    map1.put("dict_vaule",dict.getDictValue());
                    map1.put("pid",dict.getPid());
                    List<Object> list=map.get("major");
                    list.add(map1);
                }*/
            //if (map.get("major"))
            ////pid=2
            //map.put("major",null);

        });
//进行代码分组
        HashMap<String, Object> dictMap = Maps.newHashMap();
        map.forEach((k, v) -> {
            switch (k) {
                case "6"://上下行
                    dictMap.put("high_low", v);
                    break;
                case "2"://专业类型
                    dictMap.put("major", v);
                    break;
                case "29"://位置
                    dictMap.put("detail_loc", v);
                    break;
                case "22"://专业类型
                    dictMap.put("major_type", v);
                    break;
                case "200005":
                    dictMap.put("plan", v);
                    break;
                case "200006"://任务状态
                    dictMap.put("plan_status", v);
                    break;
                case "200004"://工作类型类型
                    dictMap.put("work_type", v);
                    break;
                case "200007"://是否完成
                    dictMap.put("is_finish", v);
                    break;
                case "200001":
                    /*HashMap<String, Object> map1 = Maps.newHashMap();
                    map1.put("middle_type_id", Lists.newArrayList());*/
                    dictMap.put("t_big_type_id", v);
                    break;
                case "200003"://病害等级
                    dictMap.put("falut_grade", v);
                    break;
                case "200002"://中类型
                    dictMap.put("t_middle_type_id", v);
                    break;
                case "10"://病害单位
                    dictMap.put("fault_unit", v);
                    break;
                case "154": //隔断门拍照留存，操作选项
                    dictMap.put("gdm_opt", v);
                    break;
                case "146": //影响设备
                    dictMap.put("affecte_equipment", v);
                    break;
                default:
                    dictMap.put("other_" + k, v);
                    break;
            }

        });
        //三防门检查项
        List<Map> doorCheckItemList = this.tdDictMapper.findDoorCheckItemDictForApp();
        Map doorCheckItemMap = Maps.newHashMap();
        doorCheckItemList.forEach(m -> {
            String deviceLevelCode = String.valueOf(m.get("DEVICE_LEVEL_CODE"));
            if (doorCheckItemMap.containsKey(deviceLevelCode)) {
               /* Map itemMap = (Map) doorCheckItemMap.get(deviceLevelCode);
                itemMap.put(doorCheckItemCode, m);*/
                List list = (List) doorCheckItemMap.get(deviceLevelCode);
                list.add(m);
            } else {
                //内容为map
                /*HashMap<Object, Object> itemMap = Maps.newHashMap();
                itemMap.put(doorCheckItemCode, m);
                doorCheckItemMap.put(deviceLevelCode, itemMap);*/
                //内容为数组
                doorCheckItemMap.put(deviceLevelCode, Lists.newArrayList(m));
            }

        });
        dictMap.put("door_check_item", doorCheckItemMap);
        return dictMap;
    }

    /**
     * 字典对象包装
     *
     * @param map
     * @param dict_key
     * @param dict_vaule
     * @param dict_uuid
     * @param dict_pid
     * @param list
     */
    private void createDictMap(HashMap<String, List> map, String dict_key, String dict_vaule, String dict_uuid, String dict_pid, String dict_remark, List list) {
        HashMap<String, Object> dictMap = Maps.newHashMap();
        dictMap.put("UUID", dict_uuid);
        dictMap.put("DICT_KEY", dict_key);
        dictMap.put("DICT_VALUE", dict_vaule);
        dictMap.put("PID", dict_pid);
        if (StringUtils.isNotBlank(dict_remark)) {
            dictMap.put("REMARK", dict_remark);
        }
        list.add(dictMap);
        map.put(dict_pid, list);
    }

    /**
     * 整合线路及站，设备
     *
     * @param list
     * @return
     */
    public List getDuplicateBaseInfoList(List<Map> list) {
        ArrayList<Map> result = Lists.newArrayList();
        Map<String, Map> map = Maps.newHashMap();
        for (Map m : list) {
            String line_uuid = m.get("LINE_UUID").toString();
            String line_name = m.get("LINE_NAME").toString();
            String remark = m.get("REMARK").toString();
            String station_uuid = m.get("STATION_UUID").toString();
            String station_name = m.get("STATION_NAME").toString();
            String station_code = m.get("STATION_CODE").toString();
            String station_tunnel = m.get("IS_TUNNEL").toString();

            if (map.containsKey(line_uuid)) {
                Map map1 = map.get(line_uuid);
                List stationsList = (List) map1.get("STATIONS");
                Map<String, String> stationMap = loadStationData(station_uuid, station_name, station_code, station_tunnel);

                stationsList.add(stationMap);
            } else {
                HashMap<String, Object> map1 = Maps.newHashMap();
                map1.put("LINE_UUID", line_uuid);
                map1.put("LINE_NAME", line_name);
                map1.put("LINE_COLOR", remark);
                Map<String, String> stationMap = loadStationData(station_uuid, station_name, station_code, station_tunnel);
                map1.put("STATIONS", Lists.newArrayList(stationMap));
                map.put(line_uuid, map1);
            }
        }
        for (Map.Entry<String, Map> entry : map.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }

    /**
     * 车站数据包装
     *
     * @param station_uuid
     * @param station_name
     * @param station_code
     * @param station_tunnel
     * @return
     */
    private Map<String, String> loadStationData(String station_uuid, String station_name, String station_code, String station_tunnel) {
        Map<String, String> stationMap = Maps.newHashMap();
        stationMap.put("STATION_UUID", station_uuid);
        stationMap.put("STATION_NAME", station_name);
        stationMap.put("STATION_CODE", station_code);
        stationMap.put("IS_TUNNEL", station_tunnel);
        return stationMap;
    }

    /**
     * list复制
     *
     * @param stationsInfoList
     * @return
     */
    private ArrayList copyBaseInfoList(List stationsInfoList) {
        ArrayList arrayList = Lists.newArrayList();
        //list深复制
        CollectionUtils.addAll(arrayList, new Object[stationsInfoList.size()]);
        Collections.copy(arrayList, stationsInfoList);
        return arrayList;
    }

    /**
     * 获得原始的车站和线路数据
     *
     * @param params
     * @return
     */
    private List getBaseStationInfoForApp(Map params) {
        List stations = tdStationMapper.findBaseInfoForApp(params);
        return getDuplicateBaseInfoList(stations);
    }

    private void loadSelectChild(HashMap<String, Object> map, String pCode, String code, String name, String fault1) {
        Map fault1TypeMap = (Map) map.get(fault1);
        if (fault1TypeMap.containsKey(pCode)) {
            Set deviceTypeSet = (Set) fault1TypeMap.get(pCode);
            deviceTypeSet.add(putMapData(code, name, Maps.newHashMap()));
        } else {
            HashSet<Map> set = Sets.newHashSet(putMapData(code, name, Maps.newHashMap()));
            fault1TypeMap.put(pCode, set);
        }
    }

    private Map putMapData(String type, String desc, String unit, String grade, Map pMap) {
        pMap.put("id", type);
        pMap.put("name", desc);
        pMap.put("desc", desc);
        pMap.put("unit", unit);
        pMap.put("grade", grade);
        return pMap;
    }

}
