package com.company.project.module.inspect.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.controller.BaseController;
import com.company.project.core.model.QueryRequest;
import com.company.project.module.inspect.service.EamWorkOrderService;
import com.company.project.module.inspect.service.SiFaultDataService;
import com.company.project.module.sys.model.SysUser;
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

/**
 * @author Chen
 * @created 2019-11-18-14:02.
 */
@RestController
@RequestMapping("/module/fault/check")
@Api(description = "病害审核")
public class FaultCheckController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    //@Autowired
    //private FaultCheckService faultCheckService;
    //
    //@GetMapping
    //@ApiOperation(value = "访问路径", notes = "病害记录管理请求地址")
    //public ModelAndView typeIndex() {
    //    ModelAndView mv = new ModelAndView("module/inspect/fault/check/list");
    //    return mv;
    //}
    //
    //@PostMapping("/list")
    //@ApiOperation(value = "列表", notes = "病害记录列表")
    //public Result typeList(@RequestBody QueryRequest request) {
    //    Map<String, Object> listData = super.selectByPageNumSize(request, () -> this.faultCheckService.findAllFaultLog(request.getParams()));
    //    return ResultGenerator.genSuccessResult(listData);
    //}

    @Autowired
    private EamWorkOrderService eamWorkOrderService;
    @Autowired
    private SiFaultDataService siFaultDataService;


    @PostMapping("/getFaultByWorkOrder")
    @ApiOperation(value = "获取工单病害", notes = "获取工单病害数据")
    public Result getFaultByWorkOrder(@RequestBody QueryRequest request) {
        try {
            Map params = request.getParams();
            String orderId = String.valueOf(params.get("orderId"));
            String lineCode = String.valueOf(params.get("lineCode"));
            List<Map> result = eamWorkOrderService.getFaultByWorkOrder(orderId, lineCode);
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            logger.error("获取病害失败",e.getMessage());
            return ResultGenerator.genFailResult("获取病害失败");
        }
    }

    @PostMapping("/commitFaultByWorkOrder")
    @ApiOperation(value = "提交审核结果", notes = "提交审核结果请求")
    public Result commitFaultByWorkOrder(@RequestBody QueryRequest request) {
        try {
            SysUser currentUser = super.getCurrentUser();
            List faultList = (List) request.getParams().get("faultList");
            String orderId = String.valueOf(request.getParams().get("orderId"));
            siFaultDataService.commitCheckFaultByWorkOrder(orderId, faultList, currentUser.getEmployeeNo());
            return ResultGenerator.genSuccessResult("提交成功");
        } catch (Exception e) {
            logger.error("提交审核结果失败",e.getMessage());
            return ResultGenerator.genFailResult("提交审核结果失败");
        }
    }
}
