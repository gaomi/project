package com.company.project.module.inspect.web;


import com.company.project.core.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by paodingsoft.chen on 2019/09/20.
 */
@RestController
@RequestMapping("/module/inspect")
@Api(description = "结构巡检")
public class InspectController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @GetMapping("/sideMenu")
    @ApiOperation(value = "结构巡检菜单", notes = "结构巡检菜单")
    public ModelAndView getMenu() {
        ModelAndView mv = new ModelAndView("module/inspect/sideMenu");
        return mv;
    }
}
