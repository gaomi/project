package com.company.project.module.data.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.annotation.Log;
import com.company.project.core.controller.BaseController;
import com.company.project.core.model.QueryRequest;
import com.company.project.core.model.Tree;
import com.company.project.module.data.model.TdDict;
import com.company.project.module.data.service.TdDictService;
import com.google.common.collect.Maps;
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
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by paodingsoft.chen on 2019/08/12.
 */
@RestController
@RequestMapping("/module/data/dict")
@Api(description = "数据配置管理")
public class TdDictController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private TdDictService tdDictService;

    @GetMapping
    @RequiresPermissions("dict:list")
    @ApiOperation(value = "访问路径", notes = "数据配置管理请求地址")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("module/data/dict/list");
        return mv;
    }

    @GetMapping("/toEdit")
    @RequiresPermissions("dict:edit")
    @ApiOperation(value = "访问路径", notes = "数据配置编辑页面请求地址")
    public ModelAndView editPage() {
        ModelAndView mv = new ModelAndView("module/data/dict/edit");
        return mv;
    }

    @PostMapping("/list")
    @ApiOperation(value = "列表", notes = "TD数据字典列表")
    public Result list(@RequestBody QueryRequest request) {
        try {
            Map params = request.getParams();
            String flag = params.get("flag").toString();
            Map<String, Object> result = Maps.newHashMap();
            if (StringUtils.equals("leaf", flag)) {
                //查找子节点，pid不为空的
                Example example = new Example(TdDict.class);
                Example.Criteria criteria = example.createCriteria();
                criteria.andIsNotNull("pid").andEqualTo("pid",params.get("pid")).andEqualTo("status","1");
                if(params.get("keyWord")!= null && params.get("keyWord")!= ""){
                    criteria.andCondition("DICT_VALUE like ","%"+params.get("keyWord")+"%");
                }
                result = super.selectByPageNumSize(request, () -> this.tdDictService.findByExample(example));
            } else {
                result = super.selectByPageNumSize(request, () -> this.tdDictService.findAll());
            }

            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            logger.error("获取数据字典列表失败", e);
            return ResultGenerator.genFailResult("获取数据字典列表失败！");
        }
    }

    @PostMapping("/tree")
    @RequiresPermissions("dict:list")
    @ApiOperation(value = "树列表", notes = "数据字典第一第二级列表")
    public Result tree() {
        try {
            List listData = this.tdDictService.findTreeDicts();
            return ResultGenerator.genSuccessResult(listData);
        } catch (Exception e) {
            logger.error("查询树列表失败", e);
            return ResultGenerator.genFailResult("查询树列表！");
        }

    }


    @PostMapping("/detail")
    @ApiOperation(value = "明细", notes = "TD数据字典详细数据")
    public Result detail(@RequestParam String id) {
        TdDict tdDict = tdDictService.findById(id);
        return ResultGenerator.genSuccessResult(tdDict);
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增", notes = "TD数据字典新增")
    public Result add(TdDict tdDict) {
        try {
            tdDict.setUuid(UUID.randomUUID().toString().replace("-",""));
            tdDict.setIdParent("0");
            tdDict.setStatus("1");
            tdDictService.save(tdDict);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("新增TD数据字典信息失败", e);
            return ResultGenerator.genFailResult("新增TD数据字典信息失败！");
        }
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "TD数据字典删除")
    public Result delete(@RequestParam String id) {
        try {
            tdDictService.deleteDictById(id);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("删除TD数据字典信息失败", e);
            return ResultGenerator.genFailResult("删除TD数据字典信息失败！");
        }
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改", notes = "TD数据字典修改")
    public Result update(TdDict tdDict) {
        try {
            tdDictService.updateDict(tdDict);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("修改TD数据字典信息失败", e);
            return ResultGenerator.genFailResult("修改TD数据字典信息失败！");
        }

    }


}
