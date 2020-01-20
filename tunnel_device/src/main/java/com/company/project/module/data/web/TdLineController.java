package com.company.project.module.data.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.controller.BaseController;
import com.company.project.core.model.QueryRequest;
import com.company.project.module.data.model.TdLine;
import com.company.project.module.data.service.TdLineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Map;
import java.util.UUID;

/**
 * Created by paodingsoft.chen on 2019/08/07.
 */
@RestController
@RequestMapping("/module/data/line")
@Api(description = "地铁线路信息管理")
public class TdLineController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private TdLineService tdLineService;

    @GetMapping
    @RequiresPermissions("line:list")
    @ApiOperation(value = "访问路径", notes = "线路查询请求地址")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("module/data/line/list");
        return mv;
    }

    @PostMapping("/list")
    @ApiOperation(value = "列表", notes = "地铁线路信息列表")
    public Result list(@RequestBody QueryRequest request, TdLine line) {
        Example example = new Example(TdLine.class);
        if ((null != request.getParams()) && StringUtils.isNotBlank(request.getParams().get("keyWord").toString())) {
            String keyWord = request.getParams().get("keyWord").toString();
            example.createCriteria().andLike("lineName", "%" + keyWord + "%").orLike("lineCode", "%" + keyWord + "%");
        }
        example.setOrderByClause("line_code");
        Map<String, Object> listData = super.selectByPageNumSize(request, () -> this.tdLineService.findByExample(example));
        return ResultGenerator.genSuccessResult(listData);
    }

    @GetMapping("/toEdit")
    @ApiOperation(value = "线路编辑页面", notes = "获取线路编辑页面")
    public ModelAndView toEdit(){
        ModelAndView mv = new ModelAndView("module/data/line/edit");
        return mv;
    }

    @PostMapping("/detail")
    @ApiOperation(value = "明细", notes = "地铁线路信息详细数据")
    public Result detail(@RequestParam String id) {
        TdLine tdLine = tdLineService.findById(id);
        return ResultGenerator.genSuccessResult(tdLine);
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增", notes = "地铁线路信息新增")
    public Result add(TdLine tdLine) {
        try {
            tdLine.setUuid(UUID.randomUUID().toString());
            tdLineService.save(tdLine);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("新增地铁线路信息信息失败", e);
            return ResultGenerator.genFailResult("新增地铁线路信息信息失败！");
        }
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "地铁线路信息删除")
    public Result delete(@RequestParam String id) {
        try {
            tdLineService.deleteById(id);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("删除地铁线路信息信息失败", e);
            return ResultGenerator.genFailResult("删除地铁线路信息信息失败！");
        }
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改", notes = "地铁线路信息修改")
    public Result update(TdLine tdLine) {
        try {
            tdLineService.update(tdLine);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("修改地铁线路信息信息失败", e);
            return ResultGenerator.genFailResult("修改地铁线路信息信息失败！");
        }

    }


}
