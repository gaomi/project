package com.company.project.module.job.task;

import com.company.project.core.annotation.CronTag;
import com.company.project.module.api.service.ApiAqbhqService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Chen
 * @created 2019-05-29-11:03.
 */
@CronTag("testTask")
public class TestTask {


    @Autowired
    private ApiAqbhqService aqbhqService;
    /* @Autowired
    private ApiJhjcService jhjcService;*/

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public String test(String params) {
        logger.info("我是带参数的test方法，正在被执行，参数为：{}", params);
        return null;
    }

    public String test1() {
        logger.info("我是不带参数的test1方法，正在被执行");
        return null;
    }

    public String updateAqbhqInfo() throws Exception{
        logger.info("每天12刷新一次，安全保护区跟新安全保护区项目与项目坐标，正在被执行");
        String result = "";
        boolean projectInfo = aqbhqService.getProjectInfo();
        boolean coordinates = aqbhqService.insertAllCoordinates();
        boolean personInfo = aqbhqService.updateAllPersonInfo();
        if(!projectInfo){
            result+="安全保护区项目更新失败";
        }
        if(!coordinates){
            result+="#安全保护区项坐标目更新失败！！";
        }
        if(!personInfo){
            result+="#巡检人员列表更新失败！！";
        }
       return result;
    }

    public void testjob() {
        logger.info("我是不带参数的testjob方法，正在被执行");
    }

    public void getAqbhqTokenJob(String code) {
        logger.info("每天12刷新一次，安全保护区token，getAqbhqTokenJob，正在被执行");
        //TODO 访问3次，并且sleep
        aqbhqService.changeToken(code);

    }

   /* public void aqbhqProjectJob() {
        logger.info("每天12刷新一次，安全保护区项目接口，aqbhqProjectJob方法，正在被执行");
        this.aqbhqService.getProjectInfo();
    }

    public void aqbhqPersonJob() {
        logger.info("每天12刷新一次，安全保护区人员接口，aqbhqPersonJob方法，正在被执行");
        this.aqbhqService.getAllPersonInfo();
    }

    public void jhjcProjectJob() {
        logger.info("每天12刷新一次，监护监测项目接口，jhjcPersonJob方法，正在被执行");
        this.jhjcService.getProjectInfo();
    }
*/

}