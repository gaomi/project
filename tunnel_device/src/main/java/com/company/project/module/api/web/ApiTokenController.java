package com.company.project.module.api.web;

import com.company.project.configurer.token.TokenManager;
import com.company.project.core.Result;
import com.company.project.core.ResultCode;
import com.company.project.core.ResultGenerator;
import com.company.project.core.annotation.Authorization;
import com.company.project.core.annotation.CurrentUserId;
import com.company.project.core.annotation.Log;
import com.company.project.module.api.model.TokenModel;
import com.company.project.module.sys.model.SysUser;
import com.company.project.module.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * token相关接口
 *
 * @author Chen
 * @created 2019-05-28-12:10.
 */
@RestController
@RequestMapping("/api/v1/tokens")
@Api(description = "api的token相关接口")
public class ApiTokenController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private TokenManager tokenManager;

    @PostMapping("/")
    @Log("获取token")
    @ApiOperation(value = "获取token", notes = "通过用户名密码获取token接口")
    public ResponseEntity<Result> getToken(String userName, String passWord) {

        if (null == userName || null == passWord) {
            return new ResponseEntity<>(ResultGenerator.genFailResult(ResultCode.DATA_NOT_NULL), HttpStatus.BAD_REQUEST);
        }

        SysUser user = this.sysUserService.findBy("userName", userName);
        //TODO 整合完成后换成shiro的md5 String strByMD5 = ShiroUtils.getStrByMD5(passwordStr);
        String pwd = DigestUtils.md5DigestAsHex(passWord.getBytes());
        if (null == user || !user.getPassword().equals(pwd)) {
            return new ResponseEntity<>(ResultGenerator.genFailResult(ResultCode.USERNAME_OR_PASSWORD_ERROR), HttpStatus.NOT_FOUND);
        }

        //TODO 正式的时候需要换成别的字段，user表中id是uuid字符串
        TokenModel token = this.tokenManager.createToken(user.getId());
        return new ResponseEntity<>(ResultGenerator.genSuccessResult(token), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @Log("清除token")
    @Authorization
    public Result logout(@CurrentUserId String userId) {
        this.tokenManager.deleteToken(userId);
        return ResultGenerator.genSuccessResult();
    }
}
