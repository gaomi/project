package com.company.project.module.inspect.service;

import java.util.List;
import java.util.Map;

public interface EamPersonService {

    /**
     * 获取人员数据
     *
     * @param params
     * @return
     */
    List selectPersonData(Map params);

    /**
     * 从获取eam接口人员数据
     */
    void getAllFromEam();

    /**
     * 根据id重置密码
     */
    void resetPassWordById(String id);


}
