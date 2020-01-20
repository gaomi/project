package com.company.project.module.data.web;


import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.model.QueryRequest;
import com.company.project.module.data.service.BaseDataService;
import com.company.project.module.data.service.TdSegmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/module/data/base")
@Api(description = "线、站、区间基本数据公用接口")
public class BaseDataController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BaseDataService baseDataService;

    @Autowired
    private TdSegmentService tdSegmentService;

    @PostMapping("/getBaseInfo")
    @ApiOperation(value = "地铁基本数据", notes = "地铁基本数据，线路、站、设备")
    public Result getBaseInfo(@RequestBody QueryRequest request){
        try {
            Map result = baseDataService.findBaseInfoForApp(request.getParams());
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            logger.error("获取基本数据失败", e);
            return ResultGenerator.genFailResult("获取基本数据失败！");
        }
    }

    @PostMapping("/getSegmentInfo")
    @ApiOperation(value = "地铁基本数据", notes = "地铁基本数据，线路、区间")
    public Result getSegmentInfo(@RequestBody QueryRequest request){
        try {
            List<Map> result = baseDataService.findSegmentInfoForApp(request.getParams());
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            logger.error("获取基本数据失败", e);
            return ResultGenerator.genFailResult("获取基本数据失败！");
        }
    }

    @PostMapping("/getStationInfo")
    @ApiOperation(value = "地铁基本数据", notes = "地铁基本数据，线路、站")
    public Result getStationInfo(@RequestBody QueryRequest request){
        try {
            Map result = baseDataService.findStationInfoForWeb(request.getParams());
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            logger.error("获取基本数据失败", e);
            return ResultGenerator.genFailResult("获取基本数据失败！");
        }
    }

    @PostMapping("/getDataDict")
    @ApiOperation(value = "数据管理页面字典", notes = "数据管理页面字典")
    public Result getDataDict(@RequestBody QueryRequest request){
        try {
            Map result = baseDataService.findDataDictForWeb(request.getParams());
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            logger.error("获取字典数据失败", e);
            return ResultGenerator.genFailResult("获取字典数据失败！");
        }
    }
}
