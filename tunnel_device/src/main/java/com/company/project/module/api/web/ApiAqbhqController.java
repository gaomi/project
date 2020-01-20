package com.company.project.module.api.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.annotation.Authorization;
import com.company.project.core.annotation.Log;
import com.company.project.core.controller.BaseController;
import com.company.project.core.model.QueryRequest;
import com.company.project.module.api.model.AqbhqProject;
import com.company.project.module.api.model.OperParam;
import com.company.project.module.api.service.ApiAqbhqService;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by paodingsoft.chen on 2019/05/27.
 */
@CrossOrigin
@RestController
@RequestMapping("/module/api/aqbhq")
@Api(description = "安全保护区项目（顺凯信息）接口")
public class ApiAqbhqController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ApiAqbhqService aqbhqApiService;


    @PostMapping("/GetProjectInfo")
    @Log("获取项目信息")
    @ApiOperation(value = "获取项目信息", notes = "获取项目信息接口")
    public Result getProjectInfo() {
        try {
            boolean result = aqbhqApiService.getProjectInfo();
            if (result) {
                return ResultGenerator.genSuccessResult(result);
            } else {
                return ResultGenerator.genFailResult("获取项目信息失败！");
            }
        } catch (Exception e) {
            logger.error("获取项目信息失败", e);
            return ResultGenerator.genFailResult("获取项目信息失败！");
        }
    }

    @GetMapping("/insertAllCoordinates")
    @Log("重导项目坐标信息")
    @ApiOperation(value = "重导项目坐标信息", notes = "重导项目坐标信息接口")
    public Result insertAllCoordinates() {
        try {
            aqbhqApiService.insertAllCoordinates();
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("重导项目坐标信息", e);
            return ResultGenerator.genFailResult("重导项目坐标信息接口！");
        }
    }

    @GetMapping("/selectAqbhqInfo")
    @Log("获取项目和坐标信息")
    @ApiOperation(value = "获取项目和坐标信息", notes = "获取项目和坐标信息接口")
    public Result selectAqbhqInfo() {
        try {
            List<Map> maps = aqbhqApiService.selectAqbhqInfo();
            return ResultGenerator.genSuccessResult(maps);
        } catch (Exception e) {
            logger.error("获取项目和坐标信息失败", e);
            return ResultGenerator.genFailResult("获取项目和坐标信息失败！");
        }
    }

    @PostMapping("/updateAllPersonInfo")
    @Log("更新人员信息信息")
    @ApiOperation(value = "更新人员信息信息", notes = "更新人员信息信息接口")
    public Result updateAllPersonInfo() {

        boolean result = aqbhqApiService.updateAllPersonInfo();
        if (result) {
            return ResultGenerator.genSuccessResult("更新巡检人员成功");
        } else {
            return ResultGenerator.genFailResult("更新巡检人员失败");
        }

    }

    @PostMapping("/initDictCheck")
    @Log("获取安全保护区数据字典")
    @ApiOperation(value = "获取数据字典", notes = "获取安全保护区数据字典")
    public Result getDictCheck() {

        Map result = null;
        try {
            result = aqbhqApiService.getDictCheck();
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultGenerator.genFailResult("获取安全保护区数据字典失败");

    }

    @PostMapping("/getAllPersonInfo")
    @Log("获取人员信息信息")
    @ApiOperation(value = "获取人员信息信息", notes = "获取人员信息信息接口")
    public Result getAllPersonInfo(@RequestBody QueryRequest queryRequest) {
        try {
            Map<String, Object> result;
            result = super.selectByPageNumSize(queryRequest, () -> this.aqbhqApiService.getAllPersonInfo(queryRequest.getParams()));
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            logger.error("获取人员信息信息失败", e);
            return ResultGenerator.genFailResult("获取人员信息信息失败！");
        }
    }

    @PostMapping("/getProwlerLocus")
    @Log("获取人员巡查轨迹")
    @ApiOperation(value = "获取人员巡查轨迹信息", notes = "获取人员巡查轨迹信息接口")
    public Result getProwlerLocus() {
        try {
            List<Map> listMap = aqbhqApiService.getProwlerLocus();
            return ResultGenerator.genSuccessResult(listMap);
        } catch (Exception e) {
            logger.error("获取人员巡查轨迹信息失败", e);
            return ResultGenerator.genFailResult("获取人员巡查轨迹信息失败！");
        }
    }

    @PostMapping("/GetProjectInCondition")
    @Log("根据条件获取项目工况信息")
    @ApiOperation(value = "根据条件获获取项目工况信息", notes = "根据条件获获取项目工况信息接口")
    public Result getProjectInCondition(@RequestParam String id) {

        try {
            Map list = aqbhqApiService.getProjectInCondition(id);
            return ResultGenerator.genSuccessResult(list);
        } catch (Exception e) {
            logger.error("获取项目工况信息失败", e);
            return ResultGenerator.genFailResult("获取项目工况信息失败！");
        }

    }


    @PostMapping("/persons")
    @ApiOperation(value = "巡查人员信息", notes = "巡查人员接口")
    public Result getPersonsLocation() {

        List<Map> list = Lists.newArrayList();
        try {
            list = this.aqbhqApiService.getPersonsLocation();
            return ResultGenerator.genSuccessResult(list);
        } catch (Exception e) {
            logger.error("获取巡查人员信息失败", e);
            return ResultGenerator.genFailResult("获取巡查人员信息失败！");
        }
    }


    @PostMapping("/getProjectIdBySegment")
    @ApiOperation(value = "查询项目信息", notes = "根据区间查询项目信息返回项目Id及坐标")
    public Result getProjectIdBySegment(String[] id) {
        List<Map> list = Lists.newArrayList();
        try {
            list = this.aqbhqApiService.getProjectIdBySegment(id);
            return ResultGenerator.genSuccessResult(list);
        } catch (Exception e) {
            logger.error("查询项目信息失败", e);
            return ResultGenerator.genFailResult("查询项目信息失败！");
        }
    }

    @PostMapping("/getProjectBySegment")
    @ApiOperation(value = "查询项目信息", notes = "根据区间查询项目信息")
    public Result getProjectBySegment(String[] id) {
        List<AqbhqProject> list = Lists.newArrayList();
        try {
            list = this.aqbhqApiService.getProjectBySegment(id);
            return ResultGenerator.genSuccessResult(list);
        } catch (Exception e) {
            logger.error("查询项目信息失败", e);
            return ResultGenerator.genFailResult("查询项目信息失败！");
        }
    }

    @PostMapping("/operAqbhq")
    @Log("操作项目信息")
    @ApiOperation(value = "操作项目信息", notes = "操作项目信息")
    public Result operByAqbhq(AqbhqProject aqbhq, String state) {
        try {
            this.aqbhqApiService.operationByAqbhq(aqbhq, state);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("更新项目信息失败", e);
            return ResultGenerator.genFailResult("更新项目信息失败！");
        }
    }


    @PostMapping("/GetProjectId")
    @Log("获取项目id")
    @ApiOperation(value = "根据条件获获取项目工况信息", notes = "根据条件获获取项目工况信息接口")
    public Result getProjectId(@RequestParam String id) {
        List<Map> list = Lists.newArrayList();
        try {
            AqbhqProject aqbhqProject = aqbhqApiService.getProjectInfoById(id);
            return ResultGenerator.genSuccessResult(aqbhqProject);
        } catch (Exception e) {
            logger.error("获取项目工况信息失败", e);
            return ResultGenerator.genFailResult("获取项目工况信息失败！");
        }

    }

    @PostMapping("/GetPatrollerByTime")
    @Log("获取项目id")
    @ApiOperation(value = "根据条件获获取项目工况信息", notes = "根据条件获获取项目工况信息接口")
    public Result GetPatrollerByTime(String startTime, String endTime) {
        Map map = new HashMap();
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        List<Map> list = Lists.newArrayList();
        try {
            List<Map> listMap = aqbhqApiService.GetPatrollerByTime(map);
            return ResultGenerator.genSuccessResult(listMap);
        } catch (Exception e) {
            logger.error("获取项目工况信息失败", e);
            return ResultGenerator.genFailResult("获取项目工况信息失败！");
        }

    }

    @PostMapping("/changeToken")
    @ApiOperation(value = "更改token", notes = "更改token")
    @Authorization
    public Result changeToken() {
        try {
            aqbhqApiService.changeToken("API_AQBHQ");
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("更改token失败", e);
            return ResultGenerator.genFailResult("更改token失败！");
        }

    }
}
