package com.company.project.module.data.util;

import com.company.project.module.api.enums.JhjcEnum;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

public class CommonUtil {


    public static Object getQvlv(List<Map> list) {
        List result = Lists.newArrayList();
        result.add(null);
        for (int i = 1; i < list.size() - 1; i++) {
            Map map = list.get(i);
            String value = "0";

            try {
                value = CalcCenterAndRadius(
                        ((BigDecimal) list.get(i - 1).get("POINTSMILEAGE")).doubleValue(),
                        ((BigDecimal) list.get(i - 1).get("CJ_VALUE")).doubleValue(),
                        ((BigDecimal) list.get(i).get("POINTSMILEAGE")).doubleValue(),
                        ((BigDecimal) list.get(i).get("CJ_VALUE")).doubleValue(),
                        ((BigDecimal) list.get(i + 1).get("POINTSMILEAGE")).doubleValue(),
                        ((BigDecimal) list.get(i + 1).get("CJ_VALUE")).doubleValue());
                BigDecimal value1 = new BigDecimal(value);
                BigDecimal value12 = value1.setScale(3, RoundingMode.HALF_UP);
                Double value3 = value12.doubleValue()/1000;

                result.add(value3);
            } catch (Exception e) {
                result.add(null);
            }
        }
        result.add(null);
        return result;
    }

    public static Object getSuLv(List<Map> list) {
        List result = Lists.newArrayList();
        result.add(null);
        for (int i = 1; i < list.size() - 1; i++) {
            try{
                double  popM =  ((BigDecimal) list.get(i - 1).get("POINTSMILEAGE")).doubleValue();
                double  popV =   ((BigDecimal) list.get(i - 1).get("CJ_VALUE")).doubleValue()*1000000;
                double nextM =    ((BigDecimal) list.get(i).get("POINTSMILEAGE")).doubleValue();
                double nextV = ((BigDecimal) list.get(i).get("CJ_VALUE")).doubleValue()*1000000;
                BigDecimal value1 = new BigDecimal((nextV-popV)/(nextM-popM));
                double re_value2 = value1.setScale(3, RoundingMode.HALF_UP).doubleValue();
                result.add(re_value2);
            }catch (Exception e){
                result.add(null);
            }
        }
        return result;
    }

    /// <summary> 计算半径 </summary>
    /// <param name="dXS">前一个点里程</param>
    /// <param name="dYS">前一个点累计沉降</param>
    /// <param name="dXM">当前点里程</param>
    /// <param name="dYM">当前点累计沉降</param>
    /// <param name="dXE">后一个点里程</param>
    /// <param name="dYE">后一个点累计沉降</param>
    /// <returns>曲率半径</returns>
    public static String CalcCenterAndRadius(double dXS, double dYS, double dXM, double dYM, double dXE, double dYE) {
        double xielv1 = (dYM - dYS) / (dXM - dXS);
        double xielv2 = (dYE - dYM) / (dXE - dXM);
        if (xielv1 == xielv2)//斜率相同 三点在同一直线上时不可计算曲率半径
        {
            return "99999999.99";
        }
        double number = 0.0000001;
        //通过弧上三点计算弧半径和圆心的子程序
        double[] cen = {0.0, 0.0, 0.0};
        Map<String, Double> map = Maps.newHashMap();
        map.put("a1", 0.0);
        map.put("b1", 0.0);
        map.put("a2", 0.0);
        map.put("b2", 0.0);
        if (Math.abs(dXS - dXM) <= number || Math.abs(dXE - dXM) <= number || Math.abs(dYS - dYM) <= number || Math.abs(dYE - dYM) <= number) {
            if (Math.abs(dXS - dXM) <= number) {
                if (Math.abs(dYE - dYM) <= number) {
                    cen[0] = (dXM + dXE) / 2.0;
                    cen[1] = (dYM + dYS) / 2.0;
                } else {
//                    jisuan1(dXM, dYM, dXE, dYE,   a2,    b2);
                    jisuan1(dXM, dYM, dXE, dYE, "a2", "b2", map);
                    cen[1] = (dYM + dYS) / 2.0;
                    cen[0] = (cen[1] - map.get("b2")) / map.get("a2");
                }
            }

            if (Math.abs(dXE - dXM) <= number) {
                if (Math.abs(dYS - dYM) <= number) {
                    cen[0] = (dXM + dXS) / 2.0;
                    cen[1] = (dYM + dYE) / 2.0;
                } else {
                    jisuan1(dXM, dYM, dXS, dYS, "a2", "b2", map);
                    cen[1] = (dYM + dYE) / 2.0;
                    cen[0] = (cen[1] - map.get("b2")) / map.get("a2");
                }
            }

            if (Math.abs(dXS - dXM) <= number && Math.abs(dXE - dXM) <= number) {
                cen[0] = dXM;
                cen[1] = dYM;
            }

            if (Math.abs(dYS - dYM) <= number) {
                if (Math.abs(dXE - dXM) <= number) {
                    cen[0] = (dXM + dXS) / 2.0;
                    cen[1] = (dYM + dYE) / 2.0;
                } else {
                    jisuan1(dXM, dYM, dXE, dYE, "a2", "b2", map);
                    cen[0] = (dXM + dXS) / 2.0;
                    cen[1] = map.get("a2") * cen[0] + map.get("b2");
                }
            }

            if (Math.abs(dYE - dYM) <= number) {
                if (Math.abs(dXS - dXM) <= number) {
                    cen[0] = (dXM + dXE) / 2.0;
                    cen[1] = (dYM + dYS) / 2.0;
                } else {
                    jisuan1(dXM, dYM, dXS, dYS, "a2", "b2", map);
                    cen[0] = (dXM + dXE) / 2.0;
                    cen[1] = map.get("a2") * cen[0] + map.get("b2");
                }
            }

            if (Math.abs(dYS - dYM) <= number && Math.abs(dYE - dYM) <= number) {
                cen[0] = dXM;
                cen[1] = dYM;
            }
        } else {
            jisuan1(dXS, dYS, dXM, dYM, "a1", "b1", map);
            jisuan1(dXM, dYM, dXE, dYE, "a2", "b2", map);
            if (map.get("a2") == map.get("a1"))
                map.put("a1", map.get("a1") + 0.001);
            cen[0] = -(map.get("b2") - map.get("b1")) / (map.get("a2") - map.get("a1"));
            cen[1] = map.get("a1") * cen[0] + map.get("b1");
            cen[2] = 0.0;
        }
        return Math.sqrt((cen[0] - dXS) * (cen[0] - dXS) + (cen[1] - dYS) * (cen[1] - dYS)) + "";
    }

    private static void jisuan1(double x1, double y1, double x2, double y2, double a, double b) {
        //计算法线方程系数
        double a1;
        if (x1 == x2) {
            x1 = x1 + 0.0000001;
        }
        if (y2 == y1) {
            y1 = y1 + 0.0000001;
        }

        a1 = (y2 - y1) / (x2 - x1);
        a = -1 / a1;
        b = (y1 + y2) / 2 - a * ((x1 + x2) / 2);
    }

    private static void jisuan1(double x1, double y1, double x2, double y2, String a, String b, Map<String, Double> map) {
        //计算法线方程系数
        double a1;
        if (x1 == x2) {
            x1 = x1 + 0.0000001;
        }
        if (y2 == y1) {
            y1 = y1 + 0.0000001;
        }

        a1 = (y2 - y1) / (x2 - x1);
        map.put(a, -1 / a1);
        map.put(b, (y1 + y2) / 2 - (-1 / a1) * ((x1 + x2) / 2));
    }

    public static Object getSllXaxis(List<Map> list) {
        List result = Lists.newArrayList();
        for (Map m : list) {
            List temp = Lists.newArrayList();
            temp.add(m.get("DUCT_CODE"));
            temp.add(m.get("MILEAGE_CODE"));
            result.add(temp);
        }
        return result;
    }


    /**
     * 收敛获取单个年份
     *
     * @param listEntiy
     * @return
     */
    public static Object getSeriesItem(List<Map> listEntiy) {
        if(listEntiy.size()<1){
            return  null;
        }
        Map map = Maps.newHashMap();
        List<BigDecimal> list = Lists.newArrayList();
        for (Map m : listEntiy) {
            list.add((BigDecimal)m.get("RECODE_VALUE"));
        }
        map.put("name", listEntiy.get(0).get("RECODE_DATE") + JhjcEnum.SLY.getName());
        map.put("data", list);
        map.put("yAxisIndex", 1);
        map.put("xAxisIndex", 0);
        return map;
    }


    public static Object getSeriesCj(List<Map> cj_list,String endStr) {
        Map map = Maps.newHashMap();
        List<Object> list = Lists.newArrayList();
        for (Map m : cj_list) {
            list.add(m.get("CJ_VALUE"));
        }
        map.put("name", cj_list.get(0).get("DTIME") + endStr);
        map.put("data", list);
        map.put("yAxisIndex", 0);
        map.put("xAxisIndex", 1);
        return map;
    }

    public static Object getXaxis(List<Map> cj_list,String oneInfex,String twoIndex) {
        List result = Lists.newArrayList();
        for (Map m : cj_list) {
            List temp = Lists.newArrayList();
            temp.add(m.get(oneInfex));
            temp.add(m.get(twoIndex));
            result.add(temp);
        }
        return result;
    }

    public static String[] getXaxis(List<Map> cj_list,String oneInfex) {
        String[] result = new String[cj_list.size()];
        for(int i=0;i<cj_list.size();i++){
            Map m = cj_list.get(i);
            result[1]=(String)m.get(oneInfex);
        }
        return result;
    }


   public static Object setCjYear(List<Map> list){
        for(int j=0;j<list.size();j++){
            Map<String,String> itemMap = list.get(j);
            //参数年
            int yearA = Integer.parseInt((itemMap.get("SHOWKEY").split("-"))[0]);
            //参数月
            int ma = Integer.parseInt((itemMap.get("SHOWKEY").split("-"))[1]);
            int yearB = 0, mb = 0;
            int test = 999, mTest = 0;
            String ret = "";
            for (int i = j+1; i < list.size(); i++) {
                Map<String,String> item=list.get(i);
                yearB = Integer.parseInt((item.get("SHOWKEY").split("-"))[0]);
                mb = Integer.parseInt((item.get("SHOWKEY").split("-"))[1]);
                mTest = Math.abs(((yearA - yearB) * 12 + ma) - mb - 12);

                if (mTest >= test) {
                    ret = (String)list.get(i - 1).get("SHOWKEY");
                    break;
                } else {
                    test = mTest;
                }
                if (i == list.size() - 1) {
                    ret = (String)list.get(i).get("SHOWKEY");
                }
            }
            itemMap.put("CJY",ret);
        }
        return list;
   }

}
