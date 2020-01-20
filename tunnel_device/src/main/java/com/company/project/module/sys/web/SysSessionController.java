package com.company.project.module.sys.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.annotation.Log;
import com.company.project.module.sys.model.SysUserOnline;
import com.company.project.module.sys.service.SysSessionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 在线用户信息
 *
 * @author Chen
 * @created 2019-05-22-13:55.
 */
@Controller
@RequestMapping("/module/sys/session")
@Api(description = "在线用户管理")
public class SysSessionController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SysSessionService sysSessionService;

    @Log("获取在线用户信息")
    @PostMapping
    @RequiresPermissions("session:list")
    @ApiOperation(value = "访问路径", notes = "在线用户页面")
    public String online() {
        return "module/sys/online";
    }


    @ResponseBody
    @PostMapping("/list")
    @RequiresPermissions("session:list")
    @ApiOperation(value = "列表", notes = "在线用户列表")
    public Result list() {
        List<SysUserOnline> list = sysSessionService.list();
        Map<String, Object> rspData = new HashMap<>();
        rspData.put("rows", list);
        rspData.put("total", CollectionUtils.isNotEmpty(list) ? list.size() : 0);
        return ResultGenerator.genSuccessResult(rspData);
    }

    @ResponseBody
    @RequiresPermissions("user:kickout")
    @PostMapping("/forceLogout")
    @ApiOperation(value = "强制线下", notes = "在线用户强制线下")
    public Result forceLogout(String id) {
        try {
            sysSessionService.forceLogout(id);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            log.error("踢出用户失败", e);
            return ResultGenerator.genFailResult("踢出用户失败");
        }

    }
}
