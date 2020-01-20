package com.company.project.module.job.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.annotation.Log;
import com.company.project.core.controller.BaseController;
import com.company.project.core.model.QueryRequest;
import com.company.project.module.job.model.JobInfo;
import com.company.project.module.job.service.JobInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.quartz.CronExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by paodingsoft.chen on 2019/05/28.
 */
@CrossOrigin
@RestController
@RequestMapping("/module/job/info")
@Api(description = "定时任务管理")
public class JobInfoController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private JobInfoService jobInfoService;

    @Log("获取定时任务信息")
    @GetMapping
    @RequiresPermissions("job:list")
    @ApiOperation(value = "访问路径", notes = "定时任务管理请求地址")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("module/job/info");
        return mv;
    }

    @GetMapping("/toEdit")
    @RequiresPermissions("job:edit")
    @ApiOperation(value = "访问路径", notes = "定时任务编辑页面请求地址")
    public ModelAndView editPage() {
        ModelAndView mv = new ModelAndView("module/job/edit");
        return mv;
    }

    @PostMapping("/list")
    @RequiresPermissions("job:list")
    @ApiOperation(value = "列表", notes = "定时任务列表")
    public Result list(@RequestBody QueryRequest request) {
        try {
            Condition con = new Condition(JobInfo.class);
            if(request.getParams().get("keyWord") != null && !request.getParams().get("keyWord").equals("")){
                String keyWord = request.getParams().get("keyWord").toString();
                con.createCriteria().andLike("remark","%"+keyWord+"%").orLike("methodName","%"+keyWord+"%");
            }
            Map<String, Object> result = super.selectByPageNumSize(request, () -> this.jobInfoService.findByCondition(con));
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            logger.error("获取定时任务列表失败", e);
            return ResultGenerator.genFailResult("获取定时任务列表失败！");
        }
    }
     /*public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page,size);
    List<JobInfo> list = jobInfoService.findAll();
    PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
}*/

    @PostMapping("/detail")
    @ApiOperation(value = "明细", notes = "定时任务详细数据")
    public Result detail(@RequestParam String id) {
        try {
            JobInfo jobInfo = this.jobInfoService.findById(id);
            return ResultGenerator.genSuccessResult(jobInfo);
        } catch (Exception e) {
            logger.error("获取定时任务信息失败", e);
            return ResultGenerator.genFailResult("获取定时任务信息失败！");
        }
    }

    @Log("新增任务 ")
    @PostMapping("/add")
    @RequiresPermissions("job:add")
    @ApiOperation(value = "新增", notes = "定时任务新增")
    public Result add(JobInfo jobInfo) {
        try {
            jobInfo.setJobId(UUID.randomUUID().toString());
            this.jobInfoService.addJob(jobInfo);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("新增定时任务信息失败", e);
            return ResultGenerator.genFailResult("新增定时任务信息失败！");
        }
    }

    @Log("删除任务")
    @PostMapping("/delete")
    @RequiresPermissions("job:delete")
    @ApiOperation(value = "删除", notes = "定时任务删除")
    public Result delete(@RequestParam String id) {
        try {
            this.jobInfoService.deleteBatch(id);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("删除定时任务信息失败", e);
            return ResultGenerator.genFailResult("删除定时任务信息失败！");
        }
    }

    @Log("修改任务 ")
    @PostMapping("/update")
    @RequiresPermissions("job:update")
    @ApiOperation(value = "修改", notes = "定时任务修改")
    public Result update(JobInfo jobInfo) {
        try {
            this.jobInfoService.updateJob(jobInfo);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("修改定时任务信息失败", e);
            return ResultGenerator.genFailResult("修改定时任务信息失败！");
        }

    }

    @PostMapping("/checkCron")
    @ApiOperation(value = "检查表达式", notes = "检查表达式")
    public boolean checkCron(String cron) {
        try {
            return CronExpression.isValidExpression(cron);
        } catch (Exception e) {
            return false;
        }
    }

    @Log("执行任务")
    @RequiresPermissions("job:run")
    @PostMapping("/run")
    @ApiOperation(value = "执行任务", notes = "执行任务")
    public Result runJob(String jobIds) {
        try {
            this.jobInfoService.run(jobIds);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("执行任务失败", e);
            return ResultGenerator.genFailResult("执行任务失败！");
        }
    }

    @Log("暂停任务")
    @RequiresPermissions("job:pause")
    @PostMapping("/pause")
    @ApiOperation(value = "暂停任务", notes = "暂停任务")
    public Result pauseJob(String jobIds) {
        try {
            this.jobInfoService.pause(jobIds);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("暂停任务失败", e);
            return ResultGenerator.genFailResult("暂停任务失败！");
        }
    }

    @Log("恢复任务")
    @RequiresPermissions("job:resume")
    @PostMapping("/resume")
    @ApiOperation(value = "恢复任务", notes = "恢复任务")
    public Result resumeJob(String jobIds) {
        try {
            this.jobInfoService.resume(jobIds);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("恢复任务失败", e);
            return ResultGenerator.genFailResult("恢复任务失败！");
        }
    }


    /**
     * @param job 定时任务
     * @return ResponseBo
     */
    @PostMapping("/getSysCronClazz")
    @ApiOperation(value = "系统任务", notes = "系统任务")
    public Result getSysCronClazz(JobInfo job) {
        List<JobInfo> sysCronClazz = this.jobInfoService.getSysCronClazz(job);
        return ResultGenerator.genSuccessResult(sysCronClazz);
    }

}
