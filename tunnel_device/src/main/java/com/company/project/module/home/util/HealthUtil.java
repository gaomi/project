package com.company.project.module.home.util;

import com.company.project.core.util.SpringContextUtils;
import com.company.project.core.util.ToStringUtil;
import com.company.project.module.data.dao.MetroFaultMapper;
import com.company.project.module.data.dao.TdCjDataMapper;
import com.company.project.module.data.dao.TdDictMapper;
import com.company.project.module.data.dao.TdSlDataMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class HealthUtil {

    private TdSlDataMapper tdSlDataMapper;
    private TdCjDataMapper tdCjDataMapper;
    private MetroFaultMapper metroFaultMapper;
    private TdDictMapper tdDictMapper;
    private HealthMarkUtil markUtil;
    private List<Map> healthWeight = Lists.newArrayList();


    public HealthUtil() {
        tdSlDataMapper = SpringContextUtils.getBean("tdSlDataMapper");
        tdCjDataMapper = SpringContextUtils.getBean("tdCjDataMapper");
        metroFaultMapper = SpringContextUtils.getBean("metroFaultMapper");
        tdDictMapper = SpringContextUtils.getBean("tdDictMapper");
    }

    public List<Map> initHealth(List<Map> segmentInfo) {
        //初始化健康度评测
        List<Map> healthMarck = tdDictMapper.getHealthMarck();
        //获取权重
        healthWeight = tdDictMapper.getHealthWeight();
        markUtil = new HealthMarkUtil(healthMarck);
        for (Map lineMap : segmentInfo) {
            if (lineMap.get("LINE_CODE").equals("07") || lineMap.get("LINE_CODE").equals("17")) {
                groupSegment(lineMap);
                getLineHealth(lineMap);
                System.out.println(lineMap.get("LINE_CODE") + "号线---OK");
            }
        }
//        FileUtil2.saveStringFile(JSONObject.toJSONString(segmentInfo),"D:\\log\\lineHealth.json");
        return segmentInfo;
    }

    /***
     * 为线路设置健康度
     */
    public void getLine(List<Map> param) {
        for (Map lineMap : param) {
            groupSegment(lineMap);
        }

        for (Map lineMap : param) {
            // Todo 获取整条线的健康度
            getLineHealth(lineMap);
        }

    }

    private void getLineHealth(Map lineMap) {
        int count = 0, sumMark = 0;
        List<Integer> lineScore = Lists.newArrayList();
        Map<Integer, Integer> lineScoreMap = Maps.newHashMap();
        lineScoreMap.put(0, 0);
        lineScoreMap.put(1, 0);
        lineScoreMap.put(2, 0);
        lineScoreMap.put(3, 0);
        lineScoreMap.put(4, 0);
        lineScoreMap.put(5, 0);
        int fauleSum = 0;
        List<Double> markValue = Lists.newArrayList();
        for (Map upMap : (List<Map>) lineMap.get("ud")) {
            markValue.add((double) upMap.get("Mark"));
            count++;
            fauleSum += (int) upMap.get("fault_duct");

            List<Integer> markList = (List<Integer>) upMap.get("MarkList");
            for (int i = 0; i < markList.size(); i++) {
                lineScoreMap.put(i, lineScoreMap.get(i) + markList.get(i));
            }
        }
        Collections.sort(markValue);
        int lineMark = 0;
        if (markValue.size() > 0 && markValue.size() % 2 == 0) {
            lineMark = (int) Math.ceil((markValue.get(markValue.size() / 2) + markValue.get((markValue.size() / 2) - 1)) / 2);
        } else if (markValue.size() > 0) {
            lineMark = (int) Math.ceil(markValue.get(markValue.size() / 2));
        }
        if (count != 0) {
            for (int i = 0; i < lineScoreMap.size(); i++) {
                if (i == 2) {
                    //设置总病害环
                    lineScore.add(markUtil.getFaultMark(fauleSum));
                } else {
                    lineScore.add((int) Math.ceil(lineScoreMap.get(i) / (count * 1.0)));
                }

            }
            lineMap.put("Mark", lineMark);
            lineMap.put("MarkList", lineScore);
        }
        System.out.println(count);
    }


    /***
     * 获取区间健康度（无上、下行）
     * @param lineMap
     * @return
     */
    public Map groupSegment(Map lineMap) {
        String line_code = (String) lineMap.get("LINE_CODE");
        String lineCode = line_code.startsWith("0") ? line_code.replace("0", "") : line_code;
        List<Map> groupSegment = Lists.newArrayList();
        for (Map upMap : (List<Map>) lineMap.get("0")) {
            if (upMap.get("IS_TUNNEL").equals("1")) {
                upMap.put("LINE_CODE", lineCode);
                segmentMark(upMap);
            }

        }
        for (Map downMap : (List<Map>) lineMap.get("1")) {
            if (downMap.get("IS_TUNNEL").equals("1")) {
                downMap.put("LINE_CODE", lineCode);
                segmentMark(downMap);
                for (Map itemMap : (List<Map>) lineMap.get("0")) {
                    if (!itemMap.get("IS_TUNNEL").equals("1")) {
                        continue;
                    }
                    Map item = Maps.newHashMap();
                    try {
                        if (downMap.get("NO_UD_UUID").equals(itemMap.get("NO_UD_UUID"))) {
                            item.put("NO_UD_UUID", downMap.get("NO_UD_UUID"));
                            item.put("START_STATION_CODE", downMap.get("START_STATION_CODE"));
                            item.put("fault_duct", (int) downMap.get("fault_duct") + (int) itemMap.get("fault_duct"));
//                            if ((int) itemMap.get("MIDDLE") >= (int) downMap.get("MIDDLE")) {
//                                item.put("Mark", itemMap.get("MIDDLE"));
//                            } else {
//                                item.put("Mark", downMap.get("MIDDLE"));
//                            }
                            List<Integer> markList = Lists.newArrayList();
                            List<Integer> downList = (List<Integer>) downMap.get("MarkList");
                            List<Integer> upList = (List<Integer>) itemMap.get("MarkList");
                            Double segmentMark = 0d;
                            for (int i = 0; i < downList.size(); i++) {
                                double weight = new BigDecimal((String) healthWeight.get(i).get("DICT_KEY")).doubleValue();
                                if (downList.get(i) > upList.get(i)) {
                                    markList.add(downList.get(i));
                                    segmentMark += weight * downList.get(i);
                                } else {
                                    markList.add(upList.get(i));
                                    segmentMark += weight * upList.get(i);
                                }
                            }
                            item.put("Mark", segmentMark);
                            item.put("MarkList", markList);
                            groupSegment.add(item);
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }
        lineMap.put("ud", groupSegment);
        return lineMap;
    }


    /**
     * 获取上、下行健康度
     */
    public Map segmentMark(Map params) {
        params.put("UUID_LIST", params.get("UUID_LIST"));
        List<Integer> mark = getMark(params);
        params.put("MarkList", mark);
        double middle = 0;
        for (int i = 0; i < mark.size(); i++) {
            middle += new BigDecimal((String) healthWeight.get(i).get("DICT_KEY")).doubleValue() * mark.get(i);
        }
        params.put("MIDDLE", (int) Math.ceil(middle));
        return params;
    }

    /***
     * 获取六项得分
     */
    public List<Integer> getMark(Map param) {
        List<Integer> result = Lists.newArrayList();
        //沉降健康度
        result.add(getCjScore(param, getCJTime(param)));
        //收敛健康度
        result.add(getSlScore(param, getSLTime()));
        //病害健康度
        int[] faultInfo = getFaultMark(param);
        result.add(faultInfo[0]);

        result.add(getWGMark(param));
        result.add(0);
        result.add(0);
        param.put("fault_duct", faultInfo[1]);
        return result;
    }


    private int getWGMark(Map param) {
//        int soccer = apiAqbhqService.getAqbhqMatk(param);
        //Todo  获取得分
        return 0;
    }

    /***
     * 病害得分
     * @param param
     * @return
     */
    private int[] getFaultMark(Map param) {
        List<Map> fault = Lists.newArrayList();
        try {
            fault = metroFaultMapper.getFaultMark(param);
        } catch (Exception e) {
            return new int[]{0, 0};
        }
        return markUtil.getFaultMark(fault);
    }

    /**
     * 收敛得分
     *
     * @param param
     * @param map
     * @return
     */
    private int getSlScore(Map param, Map map) {
        param.putAll(map);
        List<Double> count = Lists.newArrayList();
        try {
            count = tdSlDataMapper.getSlScore(param);
        } catch (Exception e) {
            return 0;
        }
        return markUtil.getSlScore(count);
    }

    /**
     * 沉降得分
     *
     * @param param
     * @param map
     * @return
     */
    private int getCjScore(Map param, Map map) {
        if (map.get("CJ_THIS") == null || map.get("CJ_SHOW") == null) {
            return 0;
        }
        param.putAll(map);
        double count = 0d;
        try {
            count = tdCjDataMapper.getCjMark(param);
        } catch (Exception e) {
            return 0;
        }
        return markUtil.getCjMark(count);
    }

    /***
     * 获取沉降时间
     * @param param
     * @return
     */
    public Map getCJTime(Map param) {

        Map result = Maps.newHashMap();
        try {
            SimpleDateFormat cjsdf = new SimpleDateFormat("yyyy-MM");
            long thisTime = new Date().getTime();
            Map params = new HashMap() {{
                put("start_mileage", param.get("START_MILEAGE_CODE"));
                put("end_mileage", param.get("END_MILEAGE_CODE"));
                put("updown", param.get("UPDOWN"));
                put("line", param.get("LINE_CODE"));
            }};
            List<Map> cj_legend = tdCjDataMapper.getCjDate(params);
            Map itemMap = Maps.newHashMap();
            for (Map item : cj_legend) {
                try {
                    String beside = (String) item.get("SHOWKEY");
                    long start = cjsdf.parse(beside).getTime();
                    long offset = thisTime - start;
                    if (offset > 0) {
                        if (itemMap.get("CJ_BESIDE") == null) {
                            itemMap.put("CJ_BESIDE", offset);
                            itemMap.put("CJ_SHOW", beside);
                            itemMap.put("CJ_THIS", (String) item.get("CJN"));
                        } else if (offset < (long) itemMap.get("CJ_BESIDE")) {
                            itemMap.put("CJ_BESIDE", offset);
                            itemMap.put("CJ_SHOW", beside);
                            itemMap.put("CJ_THIS", (String) item.get("CJN"));
                        }
                    }
                } catch (Exception e) {
                    continue;
                }
            }
            result.put("CJ_THIS", itemMap.get("CJ_THIS"));
            result.put("CJ_SHOW", itemMap.get("CJ_SHOW"));
            return result;
        } catch (Exception e) {

        }
        return result;
    }

    /***
     * 获取收敛时间
     * @return
     */
    public Map getSLTime() {
        Map result = Maps.newHashMap();
        try {
            SimpleDateFormat slsdf = new SimpleDateFormat("yyyy");
            long thisTime = new Date().getTime();
            List<Map> sl_legend = tdSlDataMapper.getLegend("0");
            Map itemMap = Maps.newHashMap();

            for (Map item : sl_legend) {
                try {
                    String beside = (String) item.get("SL_YEAR");
                    long start = slsdf.parse(beside).getTime();
                    long offset = thisTime - start;
                    if (offset > 0) {
                        if (itemMap.get("SL_BESIDE") == null) {
                            itemMap.put("SL_BESIDE", offset);
                            itemMap.put("SL_SHOW", beside);
                            itemMap.put("SL_THIS", (String) item.get("YEARN"));
                        } else if (offset < (long) itemMap.get("SL_BESIDE")) {
                            itemMap.put("SL_BESIDE", offset);
                            itemMap.put("SL_SHOW", beside);
                            itemMap.put("SL_THIS", (String) item.get("YEARN"));
                        }
                    }
                } catch (Exception e) {
                    continue;
                }
            }
            result.put("SL_THIS", itemMap.get("SL_THIS"));
            result.put("SL_SHOW", itemMap.get("SL_SHOW"));
        } catch (Exception e) {

        }
        return result;
    }
}


