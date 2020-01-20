package com.company.project.module.api.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.annotation.Log;
import com.company.project.core.controller.BaseController;
import com.company.project.module.api.service.ApiJhjcService;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by paodingsoft.chen on 2019/06/25.
 */
@RestController
@RequestMapping("/module/api/jhjc")
@Api(description = "监护监测项目信息")
public class ApiJhjcController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ApiJhjcService jhjcProjectService;

    @PostMapping("/projects")
    @Log("获取监护监测项目信息")
    @ApiOperation(value = "获取监护监测项目信息", notes = "获取监护监测项目信息接口")
    public Result getProjectInfo() {
        try {
            jhjcProjectService.getProjectInfo();
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("获取监护监测项目信息失败", e);
            return ResultGenerator.genFailResult("获取监护监测项目信息失败！");
        }
    }

    @PostMapping("/layers")
    @Log("获取监护监测项目布点图")
    @ApiOperation(value = "获取监护监测项目布点图", notes = "获取监护监测项目布点图")
    public Result getProjectLayers(@RequestParam String id) {
        List<Map> list = Lists.newArrayList();
        try {
            list = jhjcProjectService.getProjectLayers(id);
            return ResultGenerator.genSuccessResult(list);
        } catch (Exception e) {
            logger.error("获取监护监测项目布点图失败", e);
            return ResultGenerator.genFailResult("获取监护监测项目布点图失败！");
        }

    }

    @PostMapping("/getProjectState")
    @Log("根据id获取监护监测项目工况信息")
    @ApiOperation(value = "根据id获取监护监测项目工况信息", notes = "根据id获取监护监测项目工况信息接口")
    public Result getProjectState(@RequestParam String id) {
        List<Map> list = Lists.newArrayList();
        try {
            list = jhjcProjectService.getProjectState(id);
            return ResultGenerator.genSuccessResult(list);
        } catch (Exception e) {
            logger.error("获取监护监测项目布点图失败", e);
            return ResultGenerator.genFailResult("根据id获取监护监测项目工况信息失败！");
        }

    }

    @PostMapping("/initDictCheck")
    @Log("获取监护监测数据字典")
    @ApiOperation(value = "获取数据字典", notes = "获取监护监测数据字典")
    public Result getDictCheck() {

        Map result = null;
        try {
            result = jhjcProjectService.getDictCheck();
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultGenerator.genFailResult("获取监护监测数据字典失败");

    }

    @PostMapping("/getProjectPoint")
    @Log("根据id起始时间获取监护监测项目测点变形信息")
    @ApiOperation(value = "根据id起始时间获取测点变形信息", notes = "根据id起始时间获取监护监测项目测点变形信息接口")
    public Result getProjectPoint(Map map) {
        List<Map> list = Lists.newArrayList();
        try {
//            Map map = new HashMap();
//            map.put("prjId","6");
//            map.put("start","2015-01-12 12:00:11");
            map.put("end","2018-12-11 11:33:45");
            list = jhjcProjectService.getProjectPoint(map);
            return ResultGenerator.genSuccessResult(list);
        } catch (Exception e) {
            logger.error("根据id起始时间获取测点变形信息失败", e);
            return ResultGenerator.genFailResult("根据id起始时间获取测点变形信息失败！");
        }

    }
    @PostMapping("/status")
    @Log("获取监护监测项目布点图")
    @ApiOperation(value = "获取监护监测项目布点图", notes = "获取监护监测项目布点图")
    public Result getProjecStatus(@RequestParam String id) {
        List<Map> list = Lists.newArrayList();
        try {
            list = jhjcProjectService.getProjecStatus(id);
            return ResultGenerator.genSuccessResult(list);
        } catch (Exception e) {
            logger.error("获取监护监测项目布点图失败", e);
            return ResultGenerator.genFailResult("获取监护监测项目布点图失败！");
        }

    }

    @PostMapping("/detail")
    @Log("获取监护监测项目详情")
    @ApiOperation(value = "获取监护监测项目详情", notes = "获取监护监测项目详情")
    public Result detail(@RequestParam String id) {
        try {
            List list = jhjcProjectService.getDetailById(id);
            return ResultGenerator.genSuccessResult(list);
        } catch (Exception e) {
            logger.error("获取监护监测项目详情失败", e);
            return ResultGenerator.genFailResult("获取监护监测项目详情失败！");
        }

    }

}
