package com.company.project.module.home.util;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HealthMarkUtil {

    private List<Map> scoreList = Lists.newArrayList();

    private Map<Integer, String> cjScore = Maps.newHashMap();

    private Map slScore = Maps.newHashMap();

    //    private Map<Integer, Integer> zmScore = Maps.newHashMap();
    private Map<Integer, String> zmScore = Maps.newHashMap();

    private Map<Integer, String> faultScore = Maps.newHashMap();


    /***
     *
     * @param
     */
    private class SlComparable implements Comparator {
        @Override
        public int compare(Object o1, Object o2) {
            int a1 = (int) ((Map) o1).get("level");
            int a2 = (int) ((Map) o2).get("level");
            return a2 - a1;
        }
    }

    public HealthMarkUtil(List<Map> scoreList) {
        this.scoreList = scoreList;
        initMark();
    }

    private void initMark() {
        slScore.put("treeMap", new HashMap<String, Integer>());
        slScore.put("mainList", new ArrayList<>());
        Set tempSet = Sets.newHashSet();
        for (Map item : scoreList) {
            String code = (String) item.get("FOR_MODULE");
            switch (code) {
                case "COMMON/MARK_CJ":
                    cjScore.put(Integer.parseInt((String) item.get("DICT_VALUE")), (String) item.get("DICT_KEY"));
                    break;
                case "COMMON/MARK_SL":
                    String[] arr = ((String) item.get("DICT_KEY")).split("#");
                    Map tempMap = (Map) slScore.get("treeMap");
                    tempMap.put(arr[0], 0);
                    tempSet.add(arr[0]);
                    //TODO
                    List tempList = (List) slScore.get("mainList");
                    Map<String, Object> itemMap = Maps.newHashMap();
                    if (arr.length > 1) {
                        itemMap.put("mark", Integer.parseInt(arr[1]));
                    } else {
                        itemMap.put("mark", 1);
                    }
                    itemMap.put("class", arr[0]);
                    itemMap.put("level", Integer.parseInt((String) item.get("DICT_VALUE")));
                    tempList.add(itemMap);
                    break;
                case "COMMON/MARK_FAULT":
                    faultScore.put(Integer.parseInt((String) item.get("DICT_VALUE")), (String) item.get("DICT_KEY"));
                    break;
                case "COMMON/MARK_ZMFX":
                    zmScore.put(Integer.parseInt((String) item.get("DICT_VALUE")), (String) item.get("DICT_KEY"));
//                    zmScore.put(Integer.parseInt((String) item.get("DICT_VALUE")), (String) item.get("DICT_KEY"));
                    break;
                default:
                    break;
            }

        }
        Collections.sort((List) slScore.get("mainList"), new SlComparable());
        List<String> itemList = Lists.newArrayList();
        itemList.addAll(tempSet);
        slScore.put("treeList", itemList);
    }

    /***
     * 返回区间沉降等级
     * @param score
     * @return
     */
    public int getCjMark(Double score) {
        for (Integer key : cjScore.keySet()) {
            if (getNumber(cjScore.get(key), score)) {
                return key;
            }
        }
        return 0;
    }


    /***
     * 返回区间收敛等级
     * @param count
     * @return
     */
    public int getSlScore(List<Double> count) {
        String map = JSONObject.toJSONString(slScore.get("treeMap"));
        Map<String, Integer> itemMap = (Map<String, Integer>) JSONObject.parse(map);
        List<Map> mainList = (List<Map>) slScore.get("mainList");
        List<String> itemList = (List<String>) slScore.get("treeList");
        for (Double item : count) {
            for (int i = 0; i < itemList.size(); i++) {
                if (getNumber(itemList.get(i), item)) {
                    itemMap.put(itemList.get(i), itemMap.get(itemList.get(i)) + 1);
                }
            }
        }
        for (int i = 0; i < mainList.size(); i++) {
            if (itemMap.get(mainList.get(i).get("class")) != null && itemMap.get(mainList.get(i).get("class")) >= (int) mainList.get(i).get("mark")) {
                return (int) mainList.get(i).get("level");
            }
        }
        return 0;
    }

    /***
     * 获取致命风险项健康度
     * @param score
     * @return
     */
    public int getZMScore(int score) {
        for (Integer key : zmScore.keySet()) {
            if (getNumber(zmScore.get(key), (double) score)) {
                return key;
            }
        }
        return 0;
    }

    /***
     * 获取数字
     * @param str
     * @return
     */
    public boolean getNumber(String str, Double value) {
        boolean result = false;
        String regex = "([0-9]+)";
        List<Integer> list = Lists.newArrayList();
        if (str == null || str.trim().length() < 1) {
            return false;
        }
        Matcher matcher = Pattern.compile(regex).matcher(str);
        while (matcher.find()) {
            list.add(Integer.parseInt(matcher.group(0)));
        }
        if (list.size() > 1) {
            if (str.startsWith("[") && str.endsWith("]") && value >= list.get(0) && value <= list.get(1)) {
                result = true;
            } else if (str.startsWith("[") && str.endsWith(")") && value >= list.get(0) && value < list.get(1)) {
                result = true;
            } else if (str.startsWith("(") && str.endsWith("]") && value > list.get(0) && value <= list.get(1)) {
                result = true;
            } else if (str.startsWith("(") && str.endsWith(")") && value > list.get(0) && value < list.get(1)) {
                result = true;
            }
        } else if (str.contains("*") && list.size() == 1) {
            if (str.indexOf("*") > str.indexOf(list.get(0) + "")) {
                if (str.startsWith("[") && value >= list.get(0)) {
                    result = true;
                } else if (str.startsWith("(") && value > list.get(0)) {
                    result = true;
                }
            } else if (str.indexOf("*") < str.indexOf(list.get(0) + "")) {
                if (str.startsWith("]") && value <= list.get(0)) {
                    result = true;
                } else if (str.startsWith(")") && value < list.get(0)) {
                    result = true;
                }
            }
        }
        return result;
    }


    /**
     * 返回区间病害等级
     *
     * @param fault
     * @return
     */
    public int[] getFaultMark(List<Map> fault) {
        int[] result = new int[2];
        Set set = Sets.newHashSet();
        for (Map item : fault) {
            try {
                int start = Integer.parseInt((String) item.get("DUCT_CODE"));
                int num = Integer.parseInt((String) item.get("DUCT_NUM"));
//                System.out.println(start+"--"+num);
                for (int i = start; i < start + num; i++) {
                    set.add(start);
                }
            } catch (Exception e) {
                continue;
            }
        }
        result[1] = set.size();
        for (Integer key : faultScore.keySet()) {
            if (getNumber(faultScore.get(key), (double) set.size())) {
                result[0] = key;
            }
        }

        return result;
    }

    /**
     * 返回线路病害等级
     *
     * @param
     * @return
     */
    public int getFaultMark(int value) {

        if (value >= 3000) {
//            System.out.println(set.size() +"===>["+faultScore.get(5)+")==>"+5);
            return 5;
        } else if (value >= 2000 && value < 3000) {
//            System.out.println(set.size() +"===>["+faultScore.get(4)+","+faultScore.get(5)+")==>"+4);
            return 4;
        } else if (value >= 1000 && value < 2000) {
//            System.out.println(set.size() +"===>["+faultScore.get(3)+","+faultScore.get(4)+")==>"+3);
            return 3;
        } else if (value >= 500 && value < 1000) {
//            System.out.println(set.size() +"===>["+faultScore.get(2)+","+faultScore.get(3)+")==>"+2);
            return 2;
        } else if (value >= 100 && value < 500) {
//            System.out.println(set.size() +"===>["+faultScore.get(1)+","+faultScore.get(2)+")==>"+1);
            return 1;
        } else if (value < 100) {
//            System.out.println(set.size() +"===>["+faultScore.get(1)+")==>"+0);
            return 0;
        }
        return 0;
    }
}
