package com.company.project.module.job.task;

import com.company.project.core.annotation.CronTag;
import com.company.project.module.api.service.ApiAqbhqService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Chen
 * @created 2019-05-29-11:03.
 */
@CronTag("testTask2")
public class TestTask2 {

  /*  @Autowired
    private ApiAqbhqService aqbhqService;
*/
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void mytest(String params) {
        logger.info("我是带参数的mytest方法，正在被执行，参数为：{}", params);
    }

}