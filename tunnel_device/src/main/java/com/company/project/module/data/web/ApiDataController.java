package com.company.project.module.data.web;


import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/***
 * 接口数据对应接口
 */
@RestController
@RequestMapping("/module/data")
public class ApiDataController {


    @GetMapping("/wgsg")
    @ApiOperation(value = "违规施工页面", notes = "违规施工页面")
    public ModelAndView getMenu() {
        ModelAndView mv = new ModelAndView("module/data/api/aqbhq");
        return mv;
    }

    @GetMapping("/xcry")
    @ApiOperation(value = "巡查人员页面", notes = "巡查人员页面")
    public ModelAndView getPerson() {
        ModelAndView mv = new ModelAndView("module/data/api/person");
        return mv;
    }
    @GetMapping("/jhjc")
    @ApiOperation(value = "监护监测页面", notes = "监护监测页面")
    public ModelAndView getJhjc() {
        ModelAndView mv = new ModelAndView("module/data/api/jhjc");
        return mv;
    }

}
