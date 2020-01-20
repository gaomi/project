package com.company.project.module.map.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.controller.BaseController;
import com.company.project.core.model.QueryRequest;
import com.company.project.module.map.service.MapService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.rmi.ServerException;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/map")
@Api(description = "地图页面")
public class MapController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MapService mapService;

    @GetMapping("/index")
    @ApiOperation(value = "地图首页", notes = "地图首页")
    public ModelAndView getMapIndex(Model model) {
        model.addAttribute("user", super.getCurrentUser());
        ModelAndView mv = new ModelAndView("module/map/index");
        return mv;
    }

    @GetMapping("/map")
    @ApiOperation(value = "地图页面", notes = "地图页面")
    public ModelAndView getMapPage() {
        ModelAndView mv = new ModelAndView("module/map/map");
        return mv;
    }


    @GetMapping("/result")
    @ApiOperation(value = "地图查询结果", notes = "地图查询结果页面")
    public ModelAndView getMapTabPage() {
        ModelAndView mv = new ModelAndView("module/map/result/index");
        return mv;
    }

    //@GetMapping("/proxy")
    //@ApiOperation(value = "代理", notes = "代理地址")
    //public ModelAndView getProxy(HttpServletRequest request) {
    //
    //    //return mv;
    //    SysUser user = (SysUser) request.getSession().getAttribute(ProjectConstant.SESSION_KEY);
    //    String url = request.getServerName() + ":9080/tunnel_api/proxy.jsp?td_uid=";
    //    ModelAndView mv = new ModelAndView("redirect:http://" + url + user.getId());
    //    return mv;
    //}

    @PostMapping("/getAqbhqDataForMap")
    @ApiOperation(value = "根据条件查询违规项目数据", notes = "根据条件查询违规项目数据接口")
    public Result getAqbhqDataForMap(@RequestBody QueryRequest request) {
        try {
            List<Map> listMap = mapService.getAqbhqDataForMap(request.getParams());
            return ResultGenerator.genSuccessResult(listMap);
        } catch (Exception e) {
            logger.error("查询违规项目失败", e);
            return ResultGenerator.genFailResult("查询违规项目失败！");
        }
    }

    @PostMapping("/getJhjcDataForMap")
    @ApiOperation(value = "根据条件查询监护监测项目数据", notes = "根据条件查询监护监测项目数据接口")
    public Result getJhjcDataForMap(@RequestBody QueryRequest request) {
        try {
            List<Map> listMap = mapService.getJhjcDataForMap(request.getParams());
            return ResultGenerator.genSuccessResult(listMap);
        } catch (Exception e) {
            logger.error("查询监护监测项目失败", e);
            return ResultGenerator.genFailResult("查询项监护监测目失败！");
        }
    }
}
