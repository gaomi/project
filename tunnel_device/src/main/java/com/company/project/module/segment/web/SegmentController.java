package com.company.project.module.segment.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.annotation.Authorization;
import com.company.project.core.annotation.Log;
import com.company.project.core.controller.BaseController;
import com.company.project.core.model.QueryRequest;
import com.company.project.core.util.ToStringUtil;
import com.company.project.module.api.enums.JhjcEnum;
import com.company.project.module.api.service.ApiAqbhqService;
import com.company.project.module.api.service.ApiJhjcService;
import com.company.project.module.data.model.MetroFaultLl;
import com.company.project.module.data.service.*;
import com.company.project.module.data.util.CommonUtil;
import com.company.project.module.segment.service.SegmentService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by paodingsoft.chen on 2019/05/27.
 */
@RestController
@RequestMapping("/module/segment")
@Api(description = "区间数据选项卡")
public class SegmentController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ApiAqbhqService aqbhqService;
    @Resource
    private SegmentService segmentService;
    @Resource
    private ApiJhjcService jhjcProjectService;
    @Resource
    private TdPtdService tdPtdService;
    @Resource
    private TdSlDataService metroSlService;
    @Resource
    private MetroFaultService metroFaultService;
    @Resource
    private TdCjDataService metroCjService;
    @Resource
    private TdFaultImgService tdFaultImgService;
    @Resource
    private TdLineService tdLineService;
    @Resource
    private MetroFaultLlService metroFaultLlService;
    @Resource
    private MetroDeviceService metroDeviceService;
    @Resource
    private TdMaintainInfoService tdMaintainInfoService;

    @GetMapping("/page/{name}")
    @ApiOperation(value = "设备管理统配路径", notes = "设备管理统配路径")
    public ModelAndView getPages(@PathVariable("name") String name) {
        ModelAndView mv = new ModelAndView("module/segment/tabs/" + name);
        return mv;
    }

    @GetMapping("/tabs")
    @ApiOperation(value = "根据ID安全保护区项目信息", notes = "根据ID获取安全保护区项目信息")
    public Result tabs(Map map) {
        try {
            Result result = segmentService.getTabInfoById(map);
            return result;
        } catch (Exception e) {
            logger.error("根据ID安全保护区项目信息失败", e);
            return ResultGenerator.genFailResult("根据ID安全保护区项目信息失败！");
        }
    }

    @PostMapping(value = "/jhjc/list")
    @ApiOperation(value = "监护监测项目列表", notes = "监护监测项目信息列表")
    public Result jhjcList(@RequestBody QueryRequest request) {
        try {
            Map<String, Object> result;
            result = super.selectByPageNumSize(request, () -> this.jhjcProjectService.selectAll(request.getParams()));
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("监护监测项目列表失败", e);
            return ResultGenerator.genFailResult("监护监测项目列表失败！");
        }
    }

    @PostMapping(value = "/ptd/list")
    @ApiOperation(value = "旁通道列表", notes = "旁通道列表")
    public Result getPtdList(@RequestBody QueryRequest request) {
        try {
            Map<String, Object> result = super.selectByPageNumSize(request, () -> this.tdPtdService.listAll(request.getParams()));
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("旁通道列表列表失败", e);
            return ResultGenerator.genFailResult("旁通道列表列表失败！");
        }
    }


    @PostMapping("/aqbhq/list")
    @ApiOperation(value = "获取安全保护区项目列表", notes = "获取安全保护区项目列表数据")
    //public Result getAqbhqList(@RequestParam(defaultValue = "0") Integer pageSize, @RequestParam(defaultValue = "0") Integer pageNum) {
    public Result getAqbhqList(@RequestBody QueryRequest request) {
        //  QueryRequest request = new QueryRequest(pageSize, pageNum);
        try {
            Map<String, Object> result;
            if (request.getParams().size() > 0) {
                result = super.selectByPageNumSize(request, () -> this.aqbhqService.selectAll(request.getParams()));
            } else {
                result = super.selectByPageNumSize(request, () -> this.aqbhqService.findAll());
            }
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            logger.error("获取安全保护区项目列表失败", e);
            return ResultGenerator.genFailResult("获取安全保护区项目列表失败！");
        }
    }


    @PostMapping("/getProjectInfoById")
    @ApiOperation(value = "根据Id获取项目信息", notes = "根据Id获取项目信息接口")
    @Authorization
    public Result getProjectInfoById(@RequestParam String id) {
        try {
            return ResultGenerator.genSuccessResult(aqbhqService.getProjectInfoById(id));
        } catch (Exception e) {
            logger.error("获取项目信息失败", e);
            return ResultGenerator.genFailResult("获取项目信息失败！");
        }
    }

    @PostMapping("/getBhInfo")
    @ApiOperation(value = "获取病害信息", notes = "获取病害信息接口")
    public Result getBhInfo(@RequestBody QueryRequest request, HttpServletRequest res ) {
        try {
            Map<String, Object> result;
            if (request.getParams().size() > 0) {
                result = super.selectByPageNumSize(request, () -> this.metroFaultService.selectAll(request.getParams()));
            } else {
                result = super.selectByPageNumSize(request, () -> this.metroFaultService.findAll());
            }
            List<Map> list = (List<Map>) result.get("rows");
            List<Map> imageList = Lists.newArrayList();
            if (list.size() > 0) {
                List<String> idList = Lists.newArrayList();
                for (Map item : list) {
                    idList.add((String) item.get("INTERNAL_CODE"));
                }
                Map ids = Maps.newHashMap();
                ids.put("ids", idList);
                imageList = tdFaultImgService.getImageById((String) request.getParams().get("lineCode"), "", ids);
            }
            result.put("image", imageList);
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            logger.error("获取病害信息失败", e);
            return ResultGenerator.genFailResult("获取病害信息失败！");
        }
    }

    @PostMapping("/getBhLvli")
    @ApiOperation(value = "获取项目病害信息", notes = "获取项目病害信息接口")
    public Result getBhLvli(@RequestBody QueryRequest request, HttpServletRequest res) {
        try {
            List<MetroFaultLl> llList = metroFaultLlService.getBhLvli(request.getParams());
            return ResultGenerator.genSuccessResult(llList);
        } catch (Exception e) {
            logger.error("获取项目病害信息失败", e);
            return ResultGenerator.genFailResult("获取项目病害信息失败！");
        }
    }


    @PostMapping("/getSectionInfo")
    @ApiOperation(value = "获取区间详情信息", notes = "获取区间详情信息接口")
    public Result getSectionInfo(@RequestBody QueryRequest request) {
        try {
            Map resultMap = segmentService.getSectionInfo(request.getParams());
            return ResultGenerator.genSuccessResult(resultMap);
        } catch (Exception e) {
            logger.error("获取区间详情信息失败", e);
            return ResultGenerator.genFailResult("获取区间详情信息失败！");
        }
    }

    @PostMapping("/getMainTain")
    @ApiOperation(value = "获取区间详情信息", notes = "获取区间详情信息接口")
    public Result getMainTain(@RequestBody QueryRequest request) {
        try {
            Map resultMap = Maps.newHashMap();
            if (request.getParams().get("deviceId") != null) {
                resultMap = super.selectByPageNumSize(request, () -> this.tdMaintainInfoService.selectByDevice(request.getParams()));
            } else {
                resultMap = super.selectByPageNumSize(request, () -> this.tdMaintainInfoService.selectByCon(request.getParams()));
            }
            return ResultGenerator.genSuccessResult(resultMap);
        } catch (Exception e) {
            logger.error("获取区间详情信息失败", e);
            return ResultGenerator.genFailResult("获取区间详情信息失败！");
        }
    }

    @PostMapping("/getDeviceInfo")
    @ApiOperation(value = "获取区间详情信息", notes = "获取区间详情信息接口")
    public Result getDeviceInfo(@RequestBody QueryRequest request) {
        try {
            Map resultMap = super.selectByPageNumSize(request, () -> this.metroDeviceService.selectByCon(request.getParams()));
            return ResultGenerator.genSuccessResult(resultMap);
        } catch (Exception e) {
            logger.error("获取区间详情信息失败", e);
            return ResultGenerator.genFailResult("获取区间详情信息失败！");
        }
    }

    @PostMapping(value = "/getSLInitInfo", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "获取初始收敛沉降区间信息", notes = "获取初始收敛沉降信息接口")
    public Result getSLInitInfo(@RequestBody QueryRequest request) {
        try {
            Map resultMap = segmentService.getSLInitInfo(request.getParams());

            return ResultGenerator.genSuccessResult(resultMap);
        } catch (Exception e) {
            logger.error("收敛沉降初始化失败", e);
            return ResultGenerator.genFailResult("收敛沉降初始化失败！");
        }
    }

    @PostMapping(value = "/getPtdDataByName", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "获取初始收敛沉降区间信息", notes = "获取初始收敛沉降信息接口")
    public Result getPtdDataByName(@RequestBody QueryRequest request) {

        try {
            Object resultMap = segmentService.getPtdDataByName(request.getParams());

            return ResultGenerator.genSuccessResult(resultMap);
        } catch (Exception e) {
            logger.error("收敛沉降初始化失败", e);
            return ResultGenerator.genFailResult("收敛沉降初始化失败！");
        }
    }

    @PostMapping(value = "/getPtdInitInfo", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "获取初始收敛沉降区间信息", notes = "获取初始收敛沉降信息接口")
    public Result getPtdInitInfo(@RequestBody QueryRequest request) {
        try {
            Map resultMap = segmentService.getPtdInitInfo(request.getParams());

            return ResultGenerator.genSuccessResult(resultMap);
        } catch (Exception e) {
            logger.error("收敛沉降初始化失败", e);
            return ResultGenerator.genFailResult("收敛沉降初始化失败！");
        }
    }


    @PostMapping(value = "/getQLInitInfo")
    @ApiOperation(value = "获取初始曲率X轴信息", notes = "获取初始曲率X轴信息接口")
    public Result getQLInitInfo(@RequestBody QueryRequest request) {
        try {
            Map result = Maps.newHashMap();
            List<Map> list = metroCjService.getQlXdate(request.getParams());
            result.put("xAxis", CommonUtil.getXaxis(list, "POINTSNAME", "POINTSMILEAGE"));
            List<Map> cjPtd = metroCjService.getQLPtd(request.getParams());
            result.put("CjPtd", CommonUtil.getXaxis(cjPtd, "POINTSNAME", "POINTSMILEAGE"));
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            logger.error("曲率初始化失败", e);
            return ResultGenerator.genFailResult("获取曲率数据失败");
        }
    }

    @PostMapping(value = "/getPtdQlInit")
    @ApiOperation(value = "旁通道获取初始曲率X轴信息", notes = "旁通道曲率初始化失败")
    public Result getPtdQlInit(@RequestBody QueryRequest request) {
        try {
            Map result = Maps.newHashMap();
            List<Map> list = metroCjService.getPtdCjXDate(request.getParams());
            result.put("xAxis", CommonUtil.getXaxis(list, "POINTSNAME", "POINTSMILEAGE"));
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            logger.error("旁通道曲率初始化失败", e);
            return ResultGenerator.genFailResult("旁通道曲率初始化失败");
        }
    }

    @PostMapping("/getQlByTime")
    @ApiOperation(value = "根据年份获取沉降曲率数据", notes = "根据年份获取曲率沉降数据")
    public Result getQlByTime(@RequestBody QueryRequest request) {
        Map result;
        try {
            List<Map> list = metroCjService.getQlByTime(request.getParams());
            result = Maps.newHashMap();
            result.put("Qvlv", CommonUtil.getQvlv(list));
            result.put("Sulv", CommonUtil.getSuLv(list));
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            logger.error("获取区间信息失败", e);
            return ResultGenerator.genFailResult("获取区间信息失败！");
        }
    }

    @PostMapping("/getStatis")
    @ApiOperation(value = "根据年份获取沉降曲率数据", notes = "根据年份获取曲率沉降数据")
    public Result getStatis(@RequestBody QueryRequest request) {
        Map result;
        try {
            result = segmentService.getSegmentStatis(request.getParams());
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            logger.error("获取区间信息失败", e);
            return ResultGenerator.genFailResult("获取区间信息失败！");
        }
    }

    @PostMapping("/getDict")
    @ApiOperation(value = "获取数据字典数据", notes = "获取初始化数据字典数据")
    public Result getDict(@RequestBody QueryRequest request) {
        try {
            Map result = segmentService.getDict(request.getParams());
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            logger.error("获取数据字典数据", e);
            return ResultGenerator.genFailResult("获取数据字典数据");
        }
    }

    @PostMapping("/getStatus")
    @ApiOperation(value = "获取区间统计数据", notes = "获取区间统计数据失败")
    public Result getStatus(@RequestBody QueryRequest request) {
        try {
            Map result = segmentService.getStatus(request.getParams());
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            logger.error("获取区间统计数据失败", e);
            return ResultGenerator.genFailResult("获取区间统计数据失败");
        }
    }

    @PostMapping("/getSlByName")
    @ApiOperation(value = "根据年份获取测量数据", notes = "根据年份获取测量数据")
    public Result getMonitorByName(@RequestBody QueryRequest request) {
        Object result = new Object();
        try {
            result = segmentService.getSlByName(request.getParams());
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            return ResultGenerator.genFailResult("查询失败");
        }
    }

    @PostMapping("/getPointDetails")
    @ApiOperation(value = "根据点号获取详细数据", notes = "根据点号获取详细数据")
    public Result getPointDetails(@RequestBody QueryRequest request) {
        Map param = request.getParams();
        Object result = new Object();
        try {
            if (param.get("type").toString().equals(JhjcEnum.CJN.getCode())) {
                result = metroCjService.getPointData(param);
            } else if (param.get("type").toString().equals(JhjcEnum.SLY.getCode())) {
                result = metroSlService.getDuctDate(param);
            }
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            return ResultGenerator.genFailResult("点号信息查询失败");
        }
    }


    @PostMapping("/getSegmentByLine")
    @ApiOperation(value = "获取线路区间", notes = "获取线路区间接口")
    public Result getSegmentByLine(@RequestBody QueryRequest request) {
        try {
            List result = Lists.newArrayList();
            result = segmentService.getSegmentByLine(request.getParams());
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            logger.error("获取线路区间信息失败", e);
            return ResultGenerator.genFailResult("获取线路区间信息失败！");
        }
    }

    @PostMapping("/getLineList")
    @ApiOperation(value = "获取线路", notes = "获取线路接口")
    public Result getLineList() {
        try {
            List result = Lists.newArrayList();
            result = tdLineService.getLineList();
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            logger.error("获取线路信息失败", e);
            return ResultGenerator.genFailResult("获取线路信息失败！");
        }
    }

    @PostMapping("/getMeterBySegment")
    @ApiOperation(value = "获取里程数", notes = "获取里程数接口")
    public Result getMeterBySegment(String id) {
        try {
            List result = Lists.newArrayList();
            result = segmentService.getMeterBySegment(id);
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            logger.error("获取里程数失败", e);
            return ResultGenerator.genFailResult("获取里程数失败！");
        }
    }

    @PostMapping("/getDuctBySegment")
    @ApiOperation(value = "获取环号数", notes = "获取环号接口")
    public Result getDuctBySegment(String id) {
        try {
            List result = Lists.newArrayList();
            result = segmentService.getDuctBySegment(id);
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            logger.error("获取环号失败", e);
            return ResultGenerator.genFailResult("获取环号失败！");
        }
    }

    @PostMapping("/getMainCharDate")
    @ApiOperation(value = "获取环号详情", notes = "获取环号详情数据")
    public Result getDimDate(@RequestBody QueryRequest request) {
        try {
            Map result = segmentService.getDimDate(request.getParams());
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            logger.error("获取环号详情", e);
            return ResultGenerator.genFailResult("获取环号详情数据");
        }
    }

    @PostMapping("/emphasis/list")
    @ApiOperation(value = "大修数据", notes = "大修数据")
    public Result getEmphasisList(@RequestBody QueryRequest request) {
        try {
            Map result = super.selectByPageNumSize(request, () -> segmentService.getEmphasisList(request.getParams()));
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("大修数据获取失败", e);
            return ResultGenerator.genFailResult("大修数据获取失败！");
        }
    }

    @PostMapping("/getXiangQing")
    @ApiOperation(value = "获取管片详情", notes = "获取管片详情接口")
    public Result getXiangQing(@RequestBody QueryRequest request) {
        try {
            Map result = segmentService.getXiangQing(request.getParams());
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            logger.error("获取里程数失败", e);
            return ResultGenerator.genFailResult("获取里程数失败！");
        }
    }

    @PostMapping("/getCharInitTime")
    @ApiOperation(value = "获取折线图时间初始化", notes = "获取折线图时间初始化数据")
    public Result getCharInitTime(@RequestBody QueryRequest request) {
        try {
            Map result = segmentService.getCharInitTime(request.getParams());
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            logger.error("获取折线图时间", e);
            return ResultGenerator.genFailResult("获取折线图时间失败");
        }
    }

    public Object getColor(int bound, List arr, Integer lenth) {
        String[] colorList = {"#e74c3c", "#ff7e00", "#f9c922", "#2d9a2a"};
        List color = Lists.newArrayList();
        for (int i = 0; i < lenth; i++) {
            color.add(colorList[bound - (Integer) arr.get(i) - 1]);
        }
        return color;
    }

    public Object getNum(Integer bound, Integer lenth) {
        List arr = Lists.newArrayList();
        Random ra = new Random();
        for (int i = 0; i < lenth; i++) {
            int temp = ra.nextInt(bound);
            arr.add(temp);
        }
        return arr;
    }

}
