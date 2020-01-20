package com.company.project.module.api.web;


import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.annotation.Authorization;
import com.company.project.core.annotation.CurrentUserId;
import com.company.project.core.annotation.Log;
import com.company.project.module.api.model.AqbhqProject;
import com.company.project.module.api.model.OperParam;
import com.company.project.module.api.service.ApiAqbhqService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by Gao on 2019/07/09.
 */
@RestController
@RequestMapping("/api")
@Api(description = "安全保护区更新接口")
public class ApiOperController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ApiAqbhqService aqbhqApiService;


    @PostMapping("/aqbhq/updateAqbhq")
    @Log("操作项目信息")
    @ApiOperation(value = "操作项目信息", notes = "操作项目信息")
    @Authorization
    public Result updateAqbhq(@CurrentUserId String userId, OperParam<AqbhqProject> operParam) {
        try {
            //TODO operParam改用requestbody
            this.aqbhqApiService.operationByAqbhq(operParam);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("更新项目信息失败", e);
            return ResultGenerator.genFailResult("更新项目信息失败！");
        }
    }


}
