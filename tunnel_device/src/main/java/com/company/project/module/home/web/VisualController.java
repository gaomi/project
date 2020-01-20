package com.company.project.module.home.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.controller.BaseController;
import com.company.project.module.data.service.MetroFaultService;
import com.company.project.module.home.model.HomeItem;
import com.company.project.module.home.service.VisualService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by paodingsoft.chen on 2019/05/27.
 */
@RestController
@Api(description = "首页大屏")
public class VisualController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private MetroFaultService metroFaultService;

    @Resource
    private VisualService visualService;

    @GetMapping("/visual")
    @CrossOrigin
    @ApiOperation(value = "页面路径", notes = "大屏路径")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("module/visual/index");
        return mv;
    }

    @GetMapping("/module/visual/dynamicInfo")
    @ApiOperation(value = "动态信息", notes = "动态信息")
    public Result getDynamicInfo(String time, HttpServletRequest res) {
        try {
            Map dise_list = metroFaultService.getDiseTop("", time);
            Map resultMap = new HashMap();
            HomeItem dise1 = new HomeItem("5", "管片", "渗水", "#");
            HomeItem dise2 = new HomeItem("12", "管片", "缝隙", "#");
            HomeItem dise3 = new HomeItem("7", "管片", "渗泥沙", "#");
            HomeItem dise4 = new HomeItem("7", "管片", "渗泥沙", "#");
            HomeItem dise5 = new HomeItem("5", "管片", "渗泥沙", "#");
            HomeItem dise6 = new HomeItem("15", "管片", "渗泥沙", "#");
            HomeItem dise7 = new HomeItem("7", "管片", "渗泥沙", "#");
            HomeItem dise8 = new HomeItem("2", "管片", "渗泥沙", "#");
            HomeItem dise9 = new HomeItem("7", "管片", "渗泥沙", "#");
            HomeItem dise10 = new HomeItem("13", "管片", "渗泥沙", "#");
            HomeItem dise11 = new HomeItem("9", "管片", "渗泥沙", "#");
            HomeItem dise12 = new HomeItem("11", "管片", "渗泥沙", "#");
            HomeItem dise13 = new HomeItem("8", "管片", "渗泥沙", "#");
            HomeItem dise14 = new HomeItem("7", "管片", "渗泥沙", "#");
            List repir = new ArrayList(Arrays.asList(dise8, dise9, dise10, dise11, dise12, dise13, dise14));
            resultMap.put("dise", dise_list);
            resultMap.put("repir", repir);
            return ResultGenerator.genSuccessResult(resultMap);
        } catch (Exception e) {
            logger.error("获取首页动态信息失败", e);
            return ResultGenerator.genFailResult("获取首页动态信息失败！");
        }
    }

    @GetMapping("/module/visual/warningInfo")
    @ApiOperation(value = "预警仪表信息", notes = "预警仪表信息")
    public Result warningInfo() {
        try {
            Map resultMap = visualService.getZdjcYjData();
            return ResultGenerator.genSuccessResult(resultMap);
        } catch (Exception e) {
            logger.error("获取预警仪表信息失败", e);
            return ResultGenerator.genFailResult("获取预警仪表信息失败！");
        }
    }

    @GetMapping("/module/visual/diseInfo")
    @ApiOperation(value = "病害信息", notes = "病害维修则线图信息")
    public Result diseInfo(String data) {
        try {
            Map resultMap = new HashMap();
            List<Map> resdata = Lists.newArrayList();
            List<Map> mapThis = metroFaultService.getDiseStatis(data);
            List<Map> mapSum = metroFaultService.getDiseStatis(null);
            for (int i = 0; i < mapThis.size(); i++) {
                Map item = Maps.newHashMap();
                item.put("line", ((Map) mapThis.get(i)).get("LINE_NAME"));
                item.put("diseSum", ((Map) mapSum.get(i)).get("D_COUNT"));
                item.put("diseThis", ((Map) mapThis.get(i)).get("D_COUNT"));
                item.put("repSum", 150);
                item.put("repThis", 20);
                resdata.add(item);
            }

            HomeItem dise_line1 = new HomeItem("1号线", "36", "7", "500", "53");
            HomeItem dise_line2 = new HomeItem("2号线", "50", "8", "550", "43");
            HomeItem dise_line3 = new HomeItem("3号线", "15", "9", "420", "65");
            HomeItem dise_line4 = new HomeItem("4号线", "33", "15", "540", "48");
            HomeItem dise_line5 = new HomeItem("5号线", "44", "12", "320", "26");
            HomeItem dise_line6 = new HomeItem("6号线", "62", "16", "490", "53");
            HomeItem dise_line7 = new HomeItem("7号线", "36", "8", "520", "68");
            HomeItem dise_line8 = new HomeItem("8号线", "46", "9", "470", "48");
            HomeItem dise_line9 = new HomeItem("9号线", "50", "6", "580", "39");
            HomeItem dise_line10 = new HomeItem("10号线", "30", "9", "400", "53");
            HomeItem dise_line11 = new HomeItem("11号线", "52", "19", "470", "59");
            HomeItem dise_line12 = new HomeItem("12号线", "20", "9", "430", "58");
            HomeItem dise_line13 = new HomeItem("13号线", "12", "3", "580", "43");
            HomeItem dise_line14 = new HomeItem("14号线", "62", "21", "440", "39");
            HomeItem dise_line15 = new HomeItem("15号线", "42", "9", "450", "46");
            HomeItem dise_line16 = new HomeItem("16号线", "39", "12", "580", "49");
            HomeItem dise_line17 = new HomeItem("17号线", "55", "9", "380", "51");
            List diseLine = new ArrayList(Arrays.asList(dise_line1, dise_line2, dise_line3, dise_line4, dise_line5, dise_line6, dise_line7, dise_line8, dise_line9, dise_line10, dise_line11, dise_line12, dise_line13, dise_line14, dise_line15, dise_line16, dise_line17));
            resultMap.put("diseLine", resdata);
            return ResultGenerator.genSuccessResult(resultMap);
        } catch (Exception e) {
            logger.error("获取病害信息失败", e);
            return ResultGenerator.genFailResult("获取病害信息失败！");
        }
    }


    @GetMapping("/module/visual/lineInfo")
    @ApiOperation(value = "最值数据", notes = "最值数据信息，雷达图")
    public Result lineInfo() {
        try {
            Map resultMap = new HashMap();
            Map lineData = new HashMap<>();
            HomeItem indicator2 = new HomeItem("沉 降", "5");
            HomeItem indicator1 = new HomeItem("收 敛", "5");
            HomeItem indicator4 = new HomeItem("病 害", "5");
            HomeItem indicator3 = new HomeItem("违 规 项 目", "5");
            HomeItem indicator5 = new HomeItem("监 护 项 目", "5");
            HomeItem indicator6 = new HomeItem("致 命\n风 险", "5");
            List indicator = new ArrayList(Arrays.asList(indicator2, indicator1, indicator4, indicator3, indicator5, indicator6));
            lineData.put("indicator", indicator);
            List<Map> result = visualService.getHealthMarck();
            List lineSeries = Lists.newArrayList();
            for (Map map : result) {
                try {
                    HomeItem line_item = new HomeItem((String) map.get("LINE_NAME"), ((List<Integer>) map.get("MarkList")).toArray(), (String) map.get("LINE_COLOR"));
                    lineSeries.add(line_item);
                } catch (Exception e) {
                    HomeItem line_item = new HomeItem((String) map.get("LINE_NAME"), new Object[]{0, 0, 0, 0, 0, 0}, (String) map.get("LINE_COLOR"));
                    lineSeries.add(line_item);
//                    logger.info(map.get("LINE_NAME")+"线路健康度不存在");
                }
            }
            lineData.put("lineSeries", lineSeries);
            resultMap.put("lineData", lineData);
            return ResultGenerator.genSuccessResult(resultMap);
        } catch (Exception e) {
            logger.error("获取最值数据失败", e);
            return ResultGenerator.genFailResult("获取最值数据失败！");
        }
    }


}
