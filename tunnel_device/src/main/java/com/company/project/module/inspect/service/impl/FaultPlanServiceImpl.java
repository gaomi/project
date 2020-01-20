package com.company.project.module.inspect.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.core.model.QueryRequest;
import com.company.project.core.model.Tree;
import com.company.project.module.data.model.TdDict;
import com.company.project.module.data.service.TdDictService;
import com.company.project.module.inspect.dao.FaultPlanMapper;
import com.company.project.module.inspect.model.FaultPlan;
import com.company.project.module.inspect.service.FaultPlanService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.*;


/**
 * Created by paodingsoft.chen on 2019/09/20.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class FaultPlanServiceImpl extends AbstractService<FaultPlan> implements FaultPlanService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private FaultPlanMapper faultPlanMapper;
    @Resource
    private TdDictService tdDictService;

    @Override
    public List findCompany(QueryRequest request) {
        Map<Object, Object> map = Maps.newHashMap();
        List company = faultPlanMapper.findCompany(map);
        List<Tree> trees = new ArrayList<>();
        HashMap<String, Tree> map2 = Maps.newHashMap();

        company.forEach(map1 -> {

            Tree tree = new Tree();
            Map map11 = (Map) map1;
            Tree whdw = map2.get(map11.get("WHDW"));
            if (whdw != null) {
                List children = whdw.getChildren();
                tree.setText(map11.get("EMPLOYEE_NO").toString() + "-" + map11.get("NAME").toString());
                tree.setId((String) map11.get("EMPLOYEE_NO"));
                tree.setAttributes(map11);
                children.add(tree);
            } else {
                Tree tree1 = new Tree();
                tree1.setText(map11.get("EMPLOYEE_NO").toString() + "-" + map11.get("NAME").toString());
                tree1.setId((String) map11.get("EMPLOYEE_NO"));
                tree1.setAttributes(map11);
                tree.setText((String) map11.get("SGDW"));
                tree.setId((String) map11.get("WHDW"));

                ArrayList<Tree> children = Lists.newArrayList();
                children.add(tree1);
                tree.setChildren(children);
                map2.put(map11.get("WHDW").toString(), tree);
                trees.add(tree);
            }
        });
        return trees;
    }

    @Override
    public Map<String, List> finddict() {
        String faultStatus = "1";
        String majorStr = "2";
        String planStr = "200004";
        String workStr = "200005";

        ArrayList<String> list = Lists.newArrayList();
        //2:专业类型，200004：工作类型，200005：计划类型,1:计划状态
        list.add(majorStr);
        list.add(planStr);
        list.add(workStr);
        list.add(faultStatus);
        Example example = new Example(TdDict.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("pid", list);
        List<TdDict> byExample = tdDictService.findByExample(example);
        HashMap<String, List> map = Maps.newHashMap();
        map.put("work_type", Lists.newArrayList());
        map.put("plan_type", Lists.newArrayList());
        map.put("major", Lists.newArrayList());
        map.put("faultStatus", Lists.newArrayList());
        for (TdDict tdDict : byExample) {
            if (StringUtils.equals(tdDict.getPid(), majorStr)) {
                List major = map.get("major");
                major.add(tdDict);
            } else if (StringUtils.equals(tdDict.getPid(), planStr)) {
                List plan_type = map.get("plan_type");
                plan_type.add(tdDict);
            } else if (StringUtils.equals(tdDict.getPid(), workStr)) {
                List work_type = map.get("work_type");
                work_type.add(tdDict);
            }
        }
        return map;
    }

    @Override
    public List findAllPlan() {
        return faultPlanMapper.findAllPlan();
    }

    @Override
    public Map<String, List> findFaultByPlan(FaultPlan plan) {

        List<Map> faults = faultPlanMapper.findFaultByPlan(plan);
        HashMap<String, List> map = Maps.newHashMap();
        map.put("faults", faults);
        map.put("plan", Lists.newArrayList(plan));
        //去重
        Set<Map> treeSet = new TreeSet<Map>(new Comparator<Map>() {
            @Override
            public int compare(Map o1, Map o2) {
                int compareTo = o1.get("DEVICE_ID").toString().compareTo(o2.get("DEVICE_ID").toString());
                return compareTo;
            }
        });
        treeSet.addAll(faults);

        List deviceList = Lists.newArrayList(treeSet);
        //TODO 添加设备检查项
        List deviceCheckList = Lists.newArrayList();
        map.put("devices", deviceList);
        map.put("deviceChecks", deviceCheckList);

        return map;
    }

    @Override
    public void uploadDataByPlan(String id, Map map) {
        //1. 添加病害数据
        map.get("devices");
        map.get("faults");
        //批量插入病害数据

        map.get("plan");
        map.get("devices");
        //2.把任务改为完成状态
        FaultPlan plan = FaultPlan.builder().uuid(id).status("50").build();
        faultPlanMapper.updateByPrimaryKeySelective(plan);
    }

    @Override
    public Map<String, List> findRefDetailByPlan(FaultPlan plan) {
      /*  List deviceList= faultPlanMapper.findDeviceByPlan(plan);
        List operatorList= faultPlanMapper.findOperatorByPlan(plan);*/

        List<Map> refDetails = faultPlanMapper.findRefDetailByPlan(plan);
        ArrayList<Map> deviceList = Lists.newArrayList();
        ArrayList<Map> operatorList = Lists.newArrayList();
        for (Map map : refDetails) {
            String d_type = map.get("D_TYPE").toString();
            String optional = map.get("OPTIONAL").toString();
            if (StringUtils.equals("opt", optional) && StringUtils.equals("d", d_type)) {
                deviceList.add(map);
            } else if (StringUtils.equals("opt", optional) && StringUtils.equals("u", d_type)) {
                operatorList.add(map);
            } else if (StringUtils.equals("use", optional) && StringUtils.equals("d", d_type)) {
                deviceList.add(map);
            } else if (StringUtils.equals("use", optional) && StringUtils.equals("u", d_type)) {
                operatorList.add(map);
            }
        }
        List mopDeviceList = getMopList(deviceList, "T_UUID", "OPTIONAL", "opt");
        List mopOperatorList = getMopList(operatorList, "T_UUID", "OPTIONAL", "opt");
        HashMap<String, List> map = Maps.newHashMap();

        map.put("devices", mopDeviceList);
        map.put("operators", mopOperatorList);

        return map;
    }


    @Transactional
    @Override
    public void updateRefByPlan(Map assignData) {


        String uuid = assignData.get("UUID").toString();
        faultPlanMapper.deleteOperatorByPlan(uuid);//先删除操作人记录
        String operatorIds = assignData.get("operatorIds").toString();
        List<String> operators = Arrays.asList(operatorIds.split(","));
        HashMap<Object, Object> operatorMaps = Maps.newHashMap();
        operatorMaps.put("planUuid", uuid);
        operatorMaps.put("operators", operators);

        faultPlanMapper.deleteDeviceByPlan(uuid);//先删除设备记录
        String deviceIds = assignData.get("deviceIds").toString();
        List<String> devices = Arrays.asList(deviceIds.split(","));
        HashMap<Object, Object> deviceMaps = Maps.newHashMap();
        deviceMaps.put("planUuid", uuid);
        deviceMaps.put("devices", devices);


        faultPlanMapper.saveOperatorByPlan(operatorMaps);

        faultPlanMapper.saveDeviceByPlan(deviceMaps);

    }

    /**
     * list根据属性去重
     * @param oldlist
     * @param key
     * @param removeKey
     * @param checkVaule
     * @return
     */
    public List getMopList(List<Map> oldlist, String key, String removeKey, String checkVaule) {
        HashMap<String, Map> map = Maps.newHashMap();
        HashSet<String> set = new HashSet<>();
        for (Map m : oldlist) {
            String t_uuid = String.valueOf(m.get(key));
            if (set.contains(t_uuid) && (!StringUtils.equals(String.valueOf(m.get(removeKey)), checkVaule))) {
                map.remove(t_uuid);
                map.put(t_uuid, m);
            } else {
                set.add(t_uuid);
                map.put(t_uuid, m);
            }
        }
        set.clear();
        ArrayList<Map> list = Lists.newArrayList(map.values());
        return list;
    }
    @Override
    public Map findDictForApp(String module) {
        //todo 改成根据每个pid查
        List<Map> dicts = faultPlanMapper.findDictForApp();
        HashMap<String, List> map = Maps.newHashMap();
        dicts.forEach(m -> {
            String type = m.get("D_TYPE").toString();

            if (type.equals("p")) {
                String dict_key = m.get("DICT_KEY").toString();
                String dict_uuid = m.get("UUID").toString();
                if (CollectionUtils.isEmpty(map)) {
                    map.put(dict_uuid, Lists.newArrayList());
                } else if (CollectionUtils.isEmpty(map.get(dict_uuid))) {
                    map.put(dict_uuid, Lists.newArrayList());
                }
            } else {
                String dict_key = m.get("DICT_KEY").toString();
                String dict_vaule = m.get("DICT_VALUE").toString();
                String dict_uuid = m.get("UUID").toString();
                String dict_pid = m.get("PID").toString();
                if (CollectionUtils.isEmpty(map.get(dict_pid))) {
                    ArrayList<Object> list = Lists.newArrayList();
                    HashMap<String, Object> dictMap = Maps.newHashMap();
                    dictMap.put("UUID", dict_uuid);
                    dictMap.put("DICT_KEY", dict_key);
                    dictMap.put("DICT_VALUE", dict_vaule);
                    dictMap.put("PID", dict_pid);
                    list.add(dictMap);
                    map.put(dict_pid, list);
                } else {
                    List list = map.get(dict_pid);
                    HashMap<String, Object> dictMap = Maps.newHashMap();
                    dictMap.put("UUID", dict_uuid);
                    dictMap.put("DICT_KEY", dict_key);
                    dictMap.put("DICT_VALUE", dict_vaule);
                    dictMap.put("PID", dict_pid);
                    list.add(dictMap);
                    map.put(dict_pid, list);
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
        HashMap<Object, Object> dictMap = Maps.newHashMap();
        map.forEach((k, v) -> {
            switch (k) {
                case "6":
                    //上下行
                    dictMap.put("high_low", v);
                    break;
                case "10":
                    //病害单位
                    dictMap.put("fault_unit", v);
                    break;
                case "2":
                    //工务专业编码
                    dictMap.put("major", v);
                    break;
                case "200001":
                    //病害大类代码
                    dictMap.put("t_big_type_id", v);
                    break;
                case "200002":
                    //病害中类代码
                    dictMap.put("t_middle_type_id", v);
                    break;
                case "200003":
                    //病害等级
                    dictMap.put("falut_grade", v);
                    break;
                case "200004":
                    //工作类型
                    dictMap.put("work_type", v);
                    break;
                case "200005":
                    //计划性质
                    dictMap.put("plan", v);
                    break;
                case "200006":
                    //计划状态
                    dictMap.put("plan_status", v);
                    break;
                case "200007":
                    //是否修复
                    dictMap.put("is_finish", v);
                    break;
                default:
                    //隧道200100
                    //人防门200200
                    //防淹门200300
                    //防火隔断门200400
                    dictMap.put("other_" + k, v);
                    break;
            }
//设备类别--大类代码--中类代码--病害等级
        });
        dictMap.put("t_fault_unit",dictMap.get("fault_unit"));
        //pid=200003

        dictMap.put("t_falut_grade",null);
        //pid=200002
        dictMap.put("t_middle_type_id",dictMap.get("fault_unit"));
        HashMap<String, Object> middleMap = Maps.newHashMap();
        middleMap.put("middle_type_id",dictMap.get("middle_type_id"));

        dictMap.put("big_type_id",middleMap);

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
}
