package com.company.project.core.util;

import java.util.Map;

/**
 * @author Chen
 * @created 2019-09-22-21:09.
 */
public class PaodingUtils {
    /**
     * map转对象
     *
     * @param obj
     * @return
     */
    public static Map<?, ?> objectToMap(Object obj) {
        if (obj == null) {
            return null;
        }
        return new org.apache.commons.beanutils.BeanMap(obj);
    }
}
