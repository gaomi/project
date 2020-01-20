package com.company.project.core.util;

import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * 工具类
 *
 * @author Chen
 * @created 2019-07-01-11:46.
 */
public class ToStringUtil {
    /**
     * 把List集合转化成指定格式的String类型
     */
    public String ListToString(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    /**
     * 将集合转成String类型;如"'a','b','c','d'"
     *
     * @param list
     * @return
     */
    public static String getIds(List<String> list) {
        StringBuffer sb = new StringBuffer();
        for (String temp : list) {
            sb.append("'" + temp + "',");
        }
        String result = sb.toString();
        result = result.substring(0, result.lastIndexOf(","));
        return result;
    }

    /**
     * 判断字符串是否为数字
     */
    public static boolean isNumber(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 字符串转map
     *
     * @param mapString
     * @param separator
     * @param pairSeparator
     * @return String dictStr = "全断面=4, ⑦=8, ⑥2=6, 下卧层=6, ⑤2=3, ④2=2, ⑥3t=7, 上层=1, ⑤1j=5, ②3=1, ⑤1a=4, 中层=2, 下层=3, 泵站下部=5, 中间层=7";
     * Map map = ToStringUtil.transStringToMap(dictStr, ", ", "=");
     */
    public static Map<String, Object> transStringToMap(String mapString, String separator, String pairSeparator) {
        Map<String, Object> map = Maps.newHashMap();
        String[] fSplit = mapString.split(separator);
        for (int i = 0; i < fSplit.length; i++) {
            if (fSplit[i] == null || fSplit[i].length() == 0) {
                continue;
            }
            String[] sSplit = fSplit[i].split(pairSeparator);
            String value = fSplit[i].substring(fSplit[i].indexOf('=') + 1, fSplit[i].length());
            map.put(sSplit[0], value);
        }

        return map;
    }

}


