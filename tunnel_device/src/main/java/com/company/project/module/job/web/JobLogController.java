package com.company.project.module.job.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.annotation.Log;
import com.company.project.core.controller.BaseController;
import com.company.project.core.model.QueryRequest;
import com.company.project.core.util.FileUtil;
import com.company.project.module.job.model.JobLog;
import com.company.project.module.job.service.JobLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by paodingsoft.chen on 2019/05/28.
 */
@CrossOrigin
@RestController
@RequestMapping("/module/job/log")
@Api(description = "定时任务日志管理")
public class JobLogController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private JobLogService jobLogService;

    @Log("获取定时任务日志信息")
    @GetMapping
    @RequiresPermissions("jobLog:list")
    @ApiOperation(value = "访问路径", notes = "定时任务日志管理请求地址")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("module/job/log");
        return mv;
    }

    @PostMapping("/list")
    @RequiresPermissions("job:list")
    @ApiOperation(value = "列表", notes = "定时任务日志列表")
    public Result jobLogList(@RequestBody QueryRequest request) {
        try {
            Map<String, Object> result = super.selectByPageNumSize(request, () -> this.jobLogService.selectJobLog(request.getParams()));
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            logger.error("定时任务日志列表失败", e);
            return ResultGenerator.genFailResult("定时任务日志列表失败！");
        }
    }
    /*public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<JobLog> list = jobLogService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }*/

    @PostMapping("/detail")
    @ApiOperation(value = "明细", notes = "定时任务日志详细数据")
    public Result detail(@RequestParam String id) {
        JobLog jobLog = jobLogService.findById(id);
        return ResultGenerator.genSuccessResult(jobLog);
    }

   /* @PostMapping("/add")
    @ApiOperation(value = "新增", notes = "定时任务日志新增")
    public Result add(JobLog jobLog) {
        try {
            jobLogService.save(jobLog);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("新增定时任务日志信息失败", e);
            return ResultGenerator.genFailResult("新增定时任务日志信息失败！");
        }
    }*/

    @Log("删除调度日志")
    @PostMapping("/delete")
    @RequiresPermissions("jobLog:delete")
    @ApiOperation(value = "删除", notes = "定时任务日志删除")
    public Result delete(@RequestParam String ids) {
        try {
            this.jobLogService.deleteBatch(ids);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("删除定时任务日志信息失败", e);
            return ResultGenerator.genFailResult("删除定时任务日志信息失败！");
        }
    }

    @Log("清空调度日志")
    @PostMapping("/deleteJobAllLogs")
    @RequiresPermissions("jobLog:delete")
    @ApiOperation(value = "清空调度日志", notes = "定时任务日志清空")
    public Result deleteJobAllLogs() {
        try {
            this.jobLogService.deleteJobAllLogs();
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("清空定时任务日志信息失败", e);
            return ResultGenerator.genFailResult("清空定时任务日志信息失败！");
        }
    }

    /*@PostMapping("/update")
    @ApiOperation(value = "修改", notes = "定时任务日志修改")
    public Result update(JobLog jobLog) {
        try {
            jobLogService.update(jobLog);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("修改定时任务日志信息失败", e);
            return ResultGenerator.genFailResult("修改定时任务日志信息失败！");
        }

    }*/

    @PostMapping("/excel")
    @ApiOperation(value = "excel导出", notes = "日志excel导出")
    public Result jobLogExcel(JobLog jobLog) {
        try {
            List<JobLog> list = this.jobLogService.findAllJobLogs(jobLog);
            return FileUtil.createExcelByPOIKit("调度日志表", list, JobLog.class);
        } catch (Exception e) {
            logger.error("导出调度日志信息Excel失败", e);
            return ResultGenerator.genFailResult("导出Excel失败！");
        }
    }

    @PostMapping("/csv")
    @ApiOperation(value = "csv导出", notes = "日志csv导出")
    public Result jobLogCsv(JobLog jobLog) {
        try {
            List<JobLog> list = this.jobLogService.findAllJobLogs(jobLog);
            return FileUtil.createCsv("调度日志表", list, JobLog.class);
        } catch (Exception e) {
            logger.error("导出调度日志信息Csv失败", e);
            return ResultGenerator.genFailResult("导出Csv失败！");
        }
    }
}
