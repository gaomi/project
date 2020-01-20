package com.conpany.project;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.company.project.module.app.service.AppFaultService;
import com.company.project.module.app.web.AppLoginController;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/***
 * 单元测试类
 */
public class DemoTest extends  Tester{

    @Resource
    private AppFaultService appFaultService;
    @Resource
    private AppLoginController appLoginController;

    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer();
        try {
            FileReader fr =new FileReader("D:\\log\\param1.json");
            BufferedReader br = new BufferedReader(fr);

            String str;
            while((str = br.readLine()) != null){
                sb.append(str);
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map param= (Map)JSONObject.parseObject((sb.toString()));
        System.out.println(param.get("workEndDate"));
        System.out.println(param.get("workStartDate"));

    }

    /***
     * 测试点巡检提交数据
     */
    @Test
    @Rollback(false)
    public void fun1(){
        StringBuffer sb = new StringBuffer();
        try {
            FileReader fr =new FileReader("D:\\log\\request123.json");
            BufferedReader br = new BufferedReader(fr);

            String str;
            while((str = br.readLine()) != null){
                sb.append(str);
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map param= JSONObject.parseObject((sb.toString()));
        String s = appFaultService.uploadPlanData(param,"123");

    }

}
