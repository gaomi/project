package com.company.project.module.sys.web;

import com.company.project.core.controller.BaseController;
import com.company.project.module.sys.model.SysUser;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class ViewController extends BaseController {

    @GetMapping("/echarts")
    public String echarts() {
        return "template/echarts";
    }

    @GetMapping("/line")
    public String line() {
        return "template/line";
    }

    @GetMapping("/gis")
    public String gis() {
        return "template/gis";
    }


    @GetMapping("/test")
    public String test() {
        return "template/test";
    }

    @CrossOrigin
    @GetMapping("/aqbhq")
    public String aqbhq() {
        return "module/home/aqbhq/list";
    }


    /**
     * 首页访问
     */
    @RequestMapping("/")
    public ModelAndView index() {
        return new ModelAndView("redirect:/sys");
    }

    /**
     * 登录访问
     */
    @RequestMapping("/login")
    public ModelAndView home() {
        return new ModelAndView("login");
    }

    /**
     * 首页欢迎页面
     *
     * @return 跳转到welcome.do
     */
    @RequestMapping("/welcome")
    public ModelAndView welcome() {
        return new ModelAndView("welcome");
    }

    /**
     * 系统管理页
     */
    @RequestMapping("/sys")
    public ModelAndView sysIndex(Model model) {
        getUserData(model);
        return new ModelAndView("module/sys/index");
    }

    /**
     * 结构巡检管理页
     */
    @RequestMapping("/inspect")
    public ModelAndView inspectIndex(Model model) {
        getUserData(model);
        return new ModelAndView("module/inspect/index");
    }

    /**
     * 数据处理管理页
     */
    @RequestMapping("/data")
    public ModelAndView dataIndex(Model model) {
        getUserData(model);
        return new ModelAndView("module/data/index");
    }


    /**
     * 区间设备详情管理页
     */
    @RequestMapping("/segment")
    public ModelAndView deviceIndex(Model model) {
        getUserData(model);
        return new ModelAndView("module/segment/index");
    }

    @GetMapping("/report")
    @ApiOperation(value = "统计报表", notes = "统计报表")
    public ModelAndView reportIndex(Model model, String lineCode) {
        getUserData(model);
        ModelAndView mv = new ModelAndView("module/report/index");
        mv.addObject("lineCode", lineCode);
        return mv;
    }


    private void getUserData(Model model) {
        SysUser user = super.getCurrentUser();
        String sysTime = super.getSysTime();
        model.addAttribute("user", user);
        model.addAttribute("sysTime", sysTime);
    }
}
