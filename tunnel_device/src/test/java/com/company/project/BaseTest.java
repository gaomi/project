package com.company.project;


import com.alibaba.fastjson.JSON;
import com.company.project.module.data.model.TdDict;
import com.company.project.module.data.service.TdDictService;
import com.company.project.module.data.service.TdSegmentService;
import com.company.project.module.inspect.ExcelUtil;
import com.company.project.module.inspect.model.SiFaultData;
import com.company.project.module.inspect.service.DeviceService;
import com.company.project.module.inspect.service.SiFaultDataService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import tk.mybatis.mapper.entity.Condition;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Chen
 * @created 2019-08-20-14:19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:spring/spring-shiro.xml", "classpath:spring/spring-quartz.xml", "classpath:spring/spring-mybatis.xml", "classpath:spring/springMvc.xml", "classpath:spring/spring-service.xml", "classpath:spring/spring-redis.xml"})
public class BaseTest {

    @Autowired
    private TdSegmentService tdSegmentService;
    @Autowired
    private SiFaultDataService siFaultDataService;
    @Autowired
    private DeviceService deviceService;

        public static void main(String[] args) {
//            File file = new File(.getClass().getResource("/model/faultSegment.docx").getPath());
//            System.out.println("22");
        }

    @Test
    public void fun4() {
            Map map= Maps.newHashMap();
        Map map1 = deviceService.findcheckItem(map);
        System.out.println(JSON.toJSONString(map1));
    }
    @Test
    public void fun3() {
        ExcelUtil excelUtil = new ExcelUtil();
        //读取excel数据
        ArrayList<Map<String, String>> result = excelUtil.readExcelToObj("D:\\log\\1\\07.xlsx");
        Collections.reverse(result);
        result.remove(0);
        List<Map> param = Lists.newArrayList();
        Map upSegment = Maps.newHashMap();
        upSegment.put("updown", "0");
        Map downSegment = Maps.newHashMap();
        downSegment.put("updown", "1");
        for (int i = 0; i < result.size(); i++) {
            Map itemMap = result.get(i);
            if (i % 2 == 0) {
                if (upSegment.get("start_station") != null) {
                    Map item1 = Maps.newHashMap();
                    Map item2 = Maps.newHashMap();
                    item1.putAll(upSegment);
                    item2.putAll(downSegment);
                    param.add(item1);
                    param.add(item2);
                    System.out.println(item1);
                    System.out.println(item2);
                }
                upSegment.put("start_station", itemMap.get(0 + "") + "站");
                add(upSegment, itemMap, "start", 1, 2, "SK");
                downSegment.put("start_station", itemMap.get(0 + "") + "站");
                add(downSegment, itemMap, "start", 3, 4, "XK");
            } else {
                upSegment.put("end_station", itemMap.get(0 + "") + "站");
                add(upSegment, itemMap, "end", 1, 2, "SK");
                downSegment.put("end_station", itemMap.get(0 + "") + "站");
                add(downSegment, itemMap, "end", 3, 4, "XK");
            }
            if (i == result.size() - 1) {
                Map item1 = Maps.newHashMap();
                Map item2 = Maps.newHashMap();
                item1.putAll(upSegment);
                item2.putAll(downSegment);
                param.add(item1);
                param.add(item2);
                System.out.println(item1);
                System.out.println(item2);
            }
        }
        tdSegmentService.updateSegment(param);
    }

    public static void add(Map map, Map param, String name, int i, int j, String flag) {
        String mil = param.get(j + "").toString();
        if (mil.contains("+") && mil.startsWith("N")) {
            int duct = Integer.parseInt(param.get(i + "").toString());
            map.put(name + "_duct", duct);
            String mileage = mil.substring(3).replace("+", "");
            BigDecimal big = new BigDecimal(mileage);
            map.put(name + "_mileage", big);
            map.put(name + "_falg", mil.substring(0, 3));
        }else if (mil.contains("+") && mil.startsWith("SK") ) {
            int duct = Integer.parseInt(param.get(i + "").toString());
            map.put(name + "_duct", duct);
            String mileage = mil.substring(2).replace("+", "");
            BigDecimal big = new BigDecimal(mileage);
            map.put(name + "_mileage", big);
            map.put(name + "_falg", mil.substring(0, 2));
        }else if (mil.contains("+") && mil.startsWith("X") ) {
            int duct = Integer.parseInt(param.get(i + "").toString());
            map.put(name + "_duct", duct);
            String mileage = mil.substring(1).replace("+", "");
            BigDecimal big = new BigDecimal(mileage);
            map.put(name + "_mileage", big);
            map.put(name + "_falg", mil.substring(0, 1));
        } else {
            int duct = Integer.parseInt(param.get(i + "").toString());
            map.put(name + "_duct", duct);
            BigDecimal big = new BigDecimal(mil);
            big.setScale(2, BigDecimal.ROUND_HALF_UP);
            map.put(name + "_mileage", big);
            map.put(name + "_falg", flag);
        }
    }


    @Autowired
    private TdDictService tdDictService;

    @Test
    public void fun() {
        List<TdDict> list = Lists.newArrayList();
        for (int i = 88888; i < 88895; i++) {
            TdDict tdDict = new TdDict();
            tdDict.setUuid(i + "");
            list.add(tdDict);
        }
    }

    @Test
    public void fun2() {
        Condition con = new Condition(TdDict.class);
        con.createCriteria().andEqualTo("pid", "29");
    }

}
