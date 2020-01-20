package com.company.project.module.inspect.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.controller.BaseController;
import com.company.project.core.model.QueryRequest;
import com.company.project.module.inspect.model.FaultPlan;
import com.company.project.module.inspect.service.FaultPlanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by paodingsoft.chen on 2019/09/20.
 */
@CrossOrigin
@RestController
@RequestMapping("/module/fault/plan")
@Api(description = "施工计划管理")
public class FaultPlanController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private FaultPlanService faultPlanService;

    @GetMapping
    @RequiresPermissions("inspect:list")
    @ApiOperation(value = "访问路径", notes = "施工计划管理请求地址")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("module/inspect/fault/plan/list");
        return mv;
    }

    @GetMapping("/toEdit")
    @RequiresPermissions("inspect:edit")
    @ApiOperation(value = "编辑页面访问路径", notes = "施工计划编辑页面请求地址")
    public ModelAndView editPage() {
        ModelAndView mv = new ModelAndView("module/inspect/fault/plan/edit");
        return mv;
    }

    @GetMapping("/toPalnEdit")
    @RequiresPermissions("inspect:edit")
    @ApiOperation(value = "编辑页面访问路径", notes = "施工计划编辑页面请求地址，进行人员分配，设备分配")
    public ModelAndView planEditPage() {
        ModelAndView mv = new ModelAndView("module/inspect/fault/plan/planEdit");
        return mv;
    }

    @CrossOrigin
    @PostMapping("/list")
    @ApiOperation(value = "列表", notes = "施工计划列表")
    public Result list(@RequestBody QueryRequest request) {
        try {
            Map<String, Object> result = super.selectByPageNumSize(request, () -> this.faultPlanService.findAllPlan());
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            logger.error("获取施工计划列表失败", e);
            return ResultGenerator.genFailResult("获取施工计划列表失败！");
        }
    }

    @PostMapping("/detail")
    @ApiOperation(value = "明细", notes = "施工计划详细数据")
    public Result detail(@RequestParam String id) {
        FaultPlan inspectPlan = faultPlanService.findById(id);
        return ResultGenerator.genSuccessResult(inspectPlan);
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增", notes = "施工计划新增")
    public Result add(FaultPlan inspectPlan) {
        try {
            faultPlanService.save(inspectPlan);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("新增施工计划信息失败", e);
            return ResultGenerator.genFailResult("新增施工计划信息失败！");
        }
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "施工计划删除")
    public Result delete(@RequestParam String id) {
        try {
            faultPlanService.deleteById(id);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("删除施工计划信息失败", e);
            return ResultGenerator.genFailResult("删除施工计划信息失败！");
        }
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改", notes = "施工计划修改")
    public Result update(FaultPlan inspectPlan) {
        try {
            faultPlanService.update(inspectPlan);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("修改施工计划信息失败", e);
            return ResultGenerator.genFailResult("修改施工计划信息失败！");
        }

    }

    @PostMapping("/getCompany")
    @ApiOperation(value = "查询人员", notes = "施工单位查询")
    public Result getCompany(QueryRequest request) {
        try {
            Map<String, Object> result = super.selectByPageNumSize(request, () -> this.faultPlanService.findCompany(request));
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            logger.error("获取施工单位列表失败", e);
            return ResultGenerator.genFailResult("获取施工单位列表失败！");
        }
    }

    @PostMapping("/dict")
    @ApiOperation(value = "查询字典", notes = "表单字典数据查询")
    public Result getDict() {
        try {
            Map<String, List> result = this.faultPlanService.finddict();
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            logger.error("获取字典数据失败", e);
            return ResultGenerator.genFailResult("获取字典数据失败！");
        }
    }

    @CrossOrigin
    @PostMapping("/getFaultByPlan")
    @ApiOperation(value = "查询病害", notes = "根据任务查询所有病害")
    public Result getFaultByPlan(String id) {
        try {
            FaultPlan plan = faultPlanService.findById(id);
            plan.setUuid(id);//F57FE2ACFD6343579ACD462A16795EDF
            plan.setLineUuid("0F8C4BB8E8764B478C3E0F85781B5DD9");
            //plan.setUpdown("1");
            plan.setStartStationUuid("9B155A266E244F1EB8D0D0FE9B840321");
            plan.setEndStationUuid("5FCD25F5F9864E2A8F3A7D0DE93E4885");
            Map<String, List> result = this.faultPlanService.findFaultByPlan(plan);
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            logger.error("查询病害数据失败", e);
            return ResultGenerator.genFailResult("查询病害数据失败！");
        }
    }


    @CrossOrigin
    @PostMapping("/uploadDataByPlan")
    @ApiOperation(value = "上传", notes = "根据任务上传数据")
    public Result uploadDataByPlan(String id, Map map) {
        try {

            FaultPlan plan = faultPlanService.findById(id);
            plan.setUuid(id);//F57FE2ACFD6343579ACD462A16795EDF
            plan.setLineUuid("0F8C4BB8E8764B478C3E0F85781B5DD9");
            //plan.setUpdown("1");
            plan.setStartStationUuid("9B155A266E244F1EB8D0D0FE9B840321");
            plan.setEndStationUuid("5FCD25F5F9864E2A8F3A7D0DE93E4885");
            this.faultPlanService.uploadDataByPlan(plan.getUuid(), map);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("上传数据失败", e);
            return ResultGenerator.genFailResult("上传数据失败！");
        }
    }

    @PostMapping("/getPlanRefDetail")
    @ApiOperation(value = "任务可分配数据", notes = "任务可分配数据，人员，设备")
    public Result getPlanRefDetail(@RequestBody Map plan) {
        try {
            FaultPlan queryPlan = loadPlan(plan);
            Map<String, List> result = faultPlanService.findRefDetailByPlan(queryPlan);
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            logger.error("查询任务数据失败", e);
            return ResultGenerator.genFailResult("查询任务数据失败！");
        }
    }

    @PostMapping("/assignPlan")
    @ApiOperation(value = "任务分配", notes = "任务分配人员、设备")
    public Result assignPlan(@RequestBody Map assignData) {
        try {
            faultPlanService.updateRefByPlan(assignData);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("分配任务失败", e);
            return ResultGenerator.genFailResult("分配任务失败！");
        }
    }
    @PostMapping("/dict1")
    @ApiOperation(value = "字典数据", notes = "手机端字典数据")
    public Result getDict(@RequestBody QueryRequest request) {
        try {
            String module = "FAULT_APP";
            Map result = this.faultPlanService.findDictForApp(module);
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            logger.error("获取字典数据失败", e);
            return ResultGenerator.genFailResult("获取字典数据失败！");
        }
    }

    private FaultPlan loadPlan(Map plan) {
        FaultPlan queryPlan = new FaultPlan();
        queryPlan.setUuid(plan.get("UUID").toString());
        queryPlan.setLineUuid(plan.get("LINE_UUID").toString());
        queryPlan.setStartStationUuid(plan.get("START_STATION_UUID").toString());
        queryPlan.setEndStationUuid(plan.get("END_STATION_UUID").toString());
        queryPlan.setCompanyName(plan.get("COMPANY_NAME").toString());
        if (StringUtils.equals(plan.get("UPDOWN").toString(), "2")) {
            queryPlan.setUpdown("N");
        } else if (StringUtils.equals(plan.get("UPDOWN").toString(), "1")) {
            queryPlan.setUpdown("D");
        } else if (StringUtils.equals(plan.get("UPDOWN").toString(), "0")) {
            queryPlan.setUpdown("U");
        } else {
            queryPlan.setUpdown("");
        }
        return queryPlan;
    }
}
