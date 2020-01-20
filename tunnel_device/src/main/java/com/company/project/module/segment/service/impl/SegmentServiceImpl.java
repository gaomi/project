package com.company.project.module.segment.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.util.ToStringUtil;
import com.company.project.core.util.redis.RedisUtil;
import com.company.project.module.api.dao.AqbhqMapper;
import com.company.project.module.api.dao.JhjcMapper;
import com.company.project.module.api.enums.JhjcEnum;
import com.company.project.module.api.model.AqbhqProject;
import com.company.project.module.api.service.ApiAqbhqService;
import com.company.project.module.data.dao.*;
import com.company.project.module.data.model.TdDict;
import com.company.project.module.data.model.TdEmphasis;
import com.company.project.module.data.service.MetroFaultService;
import com.company.project.module.data.service.TdDictService;
import com.company.project.module.data.service.TdEmphasisService;
import com.company.project.module.data.util.CommonUtil;
import com.company.project.module.home.model.SectionItem;
import com.company.project.module.segment.enums.ProjectEnum;
import com.company.project.module.segment.service.SegmentService;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SegmentServiceImpl implements SegmentService {

    @Resource
    private ApiAqbhqService apiAqbhqService;
    @Resource
    private TdSlDataMapper tdSlDataMapper;
    @Resource
    private MetroDeviceInfoMapper metroDeviceInfoMapper;
    @Resource
    private TdCjDataMapper tdCjDataMapper;
    @Resource
    private TdSegmentMapper tdSegmentMapper;
    @Resource
    private TdDuctDetailMapper tdDuctDetailMapper;
    @Resource
    private TdDuctMapper tdDuctMapper;
    @Resource
    private TdDictService tdDictService;
    @Resource
    private MetroFaultService metroFaultService;
    @Resource
    private MetroFaultMapper metroFaultMapper;
    @Resource
    private TdSandyMapper tdSandyMapper;
    @Resource
    private AqbhqMapper aqbhqMapper;
    @Resource
    private JhjcMapper jhjcMapper;
    @Resource
    private TdEmphasisService tdEmphasisService;
    @Resource
    private RedisUtil redisUtil;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result getTabInfoById(Map map) {
        Map result = new HashMap();
        if (((List<String>) map.get("aqbhqPorjectIds")).size() > 0) {
            List<AqbhqProject> list_aqbhq = apiAqbhqService.findByIds(ToStringUtil.getIds((List<String>) map.get("aqbhqPorjectIds")));
            result.put("aqbhqPorjectIds", list_aqbhq);
        }
        return ResultGenerator.genSuccessResult(result);
    }

    @Override
    public List getSegmentByLine(Map map) {
        List result = tdSegmentMapper.findSegmentByLine2(map);
        return result;
    }

    @Override
    public List getMeterBySegment(String id) {
        return tdCjDataMapper.getMeterBySegment(id);
    }

    @Override
    public List getDuctBySegment(String id) {
        return tdSlDataMapper.getDuctBySegment(id);
    }

    @Override
    public Map getSectionInfo(Map map) {
        long startTime = System.currentTimeMillis();
        Map resultMap = Maps.newHashMap();
        Map sectionDetail = new HashMap();
        Condition con = new Condition(TdDict.class);
        //查询概况颜色
        con.createCriteria().andEqualTo("pid", "165").andEqualTo("status","1");
        List dict = tdDictService.findByCondition(con);
//        List<Map> duct_list =metroDuctService.getAllDuct(map);
        List<Map> dise_info = tdDuctDetailMapper.getDiseBySegment(map);
        SectionItem xAxis = new SectionItem("环号\n里程", CommonUtil.getXaxis(dise_info, "DUCT_CODE", "MILEAGE_CODE"));
        List<Map> colorList = tdDictService.getDictColor();
        List<Map> ptd_list = tdDuctMapper.getPangTongDao(map);
        sectionDetail.put("xPtd", CommonUtil.getXaxis(ptd_list, "DUCT_CODE", "MILEAGE_CODE"));
        sectionDetail.put("xAxis", xAxis);
        sectionDetail.put("data", dise_info);
        resultMap.put("sectionDetail", sectionDetail);
        resultMap.put("colorLevel", dict);
        resultMap.put("itemColorLevel", colorList);
        long times = System.currentTimeMillis() - startTime;
        return resultMap;
    }

    @Override
    public Map getSLInitInfo(Map params) {
        Map resultMap = Maps.newHashMap();
        Map monitor = new HashMap();
        //初始化收敛数据
        List<Map> listEntiy = tdSlDataMapper.initSLXData(params);
        List<Map> cj_list = tdCjDataMapper.getCjXDate(params);
        Map xdate = Maps.newHashMap();
        Object cj_xdata = CommonUtil.getXaxis(cj_list, "POINTSNAME", "POINTSMILEAGE");
        xdate.put("xdateOne", CommonUtil.getXaxis(listEntiy, "DUCT_CODE", "MILEAGE_CODE"));
        xdate.put("xdateTwo", cj_xdata);
        monitor.put("xdate", xdate);
        List<Map> cjPtd = tdCjDataMapper.getCjPtd(params);
        monitor.put("CjPtd", CommonUtil.getXaxis(cjPtd, "POINTSNAME", "POINTSMILEAGE"));
        resultMap.put("monitor", monitor);
        return resultMap;
    }


    @Override
    public Object getPtdDataByName(Map params) {
        Object result = null;
        try {
            if (params.get("type").equals(JhjcEnum.SLY.getName())) {
                List<Map> listEntiy = tdSlDataMapper.getSlByName(params);
                result = CommonUtil.getSeriesItem(listEntiy);
            } else if (params.get("type").equals(JhjcEnum.SLC.getName())) {
                params.put("base_key", 5.5);
                List<Map> listEntiy = tdSlDataMapper.getDiffData(params);
                result = CommonUtil.getSeriesItem(listEntiy);
            } else if (params.get("type").equals(JhjcEnum.SLS.getName())) {
                List<Map> listEntiy = tdSlDataMapper.getSlByName(params);
                result = CommonUtil.getSeriesItem(listEntiy);

            } else if (StringUtils.isEmpty((String) params.get("type"))) {
                List<Map> cj_list = tdCjDataMapper.getPtdCjData(params);
                Map resultMap = Maps.newHashMap();
                resultMap.put("Qvlv", CommonUtil.getQvlv(cj_list));
                resultMap.put("Sulv", CommonUtil.getSuLv(cj_list));
                result = resultMap;
            } else {
                if (params.get("type").equals(JhjcEnum.CJY.getName())) {
                    params.put("base_code", JhjcEnum.CJY.getCode());
                }
                List<Map> cj_list = tdCjDataMapper.getPtdCjData(params);
                result = CommonUtil.getSeriesCj(cj_list, (String) params.get("type"));
            }
            return result;
        } catch (Exception e) {
            return "查询失败";
        }
    }

    @Override
    public Object getSlByName(Map map) {
        Object result = new Object();
        if (!StringUtils.isEmpty((String) map.get("segment"))) {
            if (map.get("type").equals(JhjcEnum.SLY.getName()) || map.get("type").equals(JhjcEnum.SLS.getName())) {
                List<Map> listEntiy = tdSlDataMapper.getSlByName(map);
                result = CommonUtil.getSeriesItem(listEntiy);
            } else if (map.get("type").equals(JhjcEnum.SLC.getName())) {
                map.put("base_key", 5.5);
                List<Map> listEntiy = tdSlDataMapper.getDiffData(map);
                result = CommonUtil.getSeriesItem(listEntiy);
            } else {
                map.put("base_key", map.get("init_date"));
                if (map.get("type").equals(JhjcEnum.CJY.getName())) {
                    map.put("base_code", JhjcEnum.CJY.getCode());
                }
                List<Map> cj_list = tdCjDataMapper.getDateByTime(map);
                result = CommonUtil.getSeriesCj(cj_list, (String) map.get("type"));
            }
        } else {
            if (map.get("type").equals(JhjcEnum.SLY.getName()) || map.get("type").equals(JhjcEnum.SLS.getName())) {
                //List<Map> listEntiy = tdSlDataMapper.getMapSlByName(map);
                //result = CommonUtil.getSeriesItem(listEntiy);
            } else if (map.get("type").equals(JhjcEnum.SLC.getName())) {
                map.put("base_key", 5.5);
                //List<Map> listEntiy = tdSlDataMapper.getMapDiffData(map);
                //result = CommonUtil.getSeriesItem(listEntiy);
            } else {
                map.put("base_key", map.get("init_date"));
                if (map.get("type").equals(JhjcEnum.CJY.getName())) {
                    map.put("base_code", JhjcEnum.CJY.getCode());
                }
                //List<Map> cj_list = tdCjDataMapper.getMapDateByTime(map);
                //result = CommonUtil.getSeriesCj(cj_list, (String) map.get("type"));
            }
        }
        return result;
    }

    @Override
    public Map getSegmentStatis(Map params) {
        Map listMap = Maps.newHashMap();
        List<Map> dise = metroFaultService.getSegmentStatis(params);
        listMap.put(ProjectEnum.BHXX.getCode(), dise);
        return listMap;
    }

    @Override
    public Map getDict(Map params) {
        Map result = Maps.newHashMap();
//        legend.put("emlegend", em_legend);
        Condition aqbhq = new Condition(TdDict.class);
        //获取安全保护区的等级
        aqbhq.createCriteria().andEqualTo("pid", "177").andEqualTo("status","1");
        List aqbhqdict = tdDictService.findByCondition(aqbhq);
        Condition jhjc = new Condition(TdDict.class);
        //获取监护监测的等级
        jhjc.createCriteria().andEqualTo("pid", "176").andEqualTo("status","1");
        List jhjcdict = tdDictService.findByCondition(jhjc);
        Condition jstate = new Condition(TdDict.class);
        //获取监护监测的状态
        jstate.createCriteria().andEqualTo("pid", "173").andEqualTo("status","1");
        List jhjcState = tdDictService.findByCondition(jstate);
        Condition status = new Condition(TdDict.class);
        //获取安全保护区的状态
        status.createCriteria().andEqualTo("pid", "174").andEqualTo("status","1");
        List statusList = tdDictService.findByCondition(status);
        result.put("AQBHQ_LEVEL", aqbhqdict);
        result.put("JHJC_LEVEL", jhjcdict);
        result.put("JHJC_STATE", jhjcState);
        result.put("AQBHQ_STATUS", statusList);
        return result;
    }

    @Override
    public Map getStatus(Map params) {
        Map result = Maps.newHashMap();
        Map listMap = Maps.newHashMap();
        params.put("status", "20");
        List<Map> dise = metroFaultMapper.getSegmentStatis(params);
        List<Map> aqbhqList = aqbhqMapper.getProjectSegment(params);
        List<Map> jhjcList = jhjcMapper.getProjectSegment(params);
        listMap.put(ProjectEnum.BHXX.getCode(), dise);
        listMap.put(ProjectEnum.JHXM.getCode(), jhjcList);
        listMap.put(ProjectEnum.WGSG.getCode(), aqbhqList);
        result.put("statis", listMap);
        List<Map> deviceList = metroDeviceInfoMapper.getDeviceInfo(params);
        result.put("deviceInfo", deviceList);
        return result;
    }

    @Override
    public Map getDimDate(Map params) {
        Map map = Maps.newHashMap();
        Map item = Maps.newHashMap();
        Map xAxis = Maps.newHashMap();
        //获取病害
        StopWatch clock = new StopWatch();
        clock.start("获取项目");
        List<Map> faultList = metroFaultMapper.getProjectSegment(params);
        //获取砂性土
        List<Map> sandyList = tdSandyMapper.getProjectSegment(params);
        List<Map> aqbhqList = aqbhqMapper.getProjectSegment(params);
        List<Map> jhjcList = jhjcMapper.getProjectSegment(params);
        clock.stop();
        clock.start("获取环号");
        List<Map> ductList = getSegmentDuct(params);
        clock.stop();
        //大修环数
        params.get("group_segment");
        clock.start("获取大修和颜色");
        List<Map> emphasisList = tdEmphasisService.getEmphasisForMainCharts((List) params.get("group_segment"));
        List<Map> ptd_list = tdDuctMapper.getPangTongDao(params);
        List<Map> colorList = tdDictService.getDictColor();
        Condition con = new Condition(TdDict.class);
        //获取颜色等级
        con.createCriteria().andEqualTo("pid", "165").andEqualTo("status","1");
        List dict = tdDictService.findByCondition(con);
        clock.stop();
        System.out.println(clock.prettyPrint());
        item.put("faultDate", faultList);
        item.put("sandyDate", sandyList);
        item.put("aqbhqDate", aqbhqList);
        item.put("emphasisDate", emphasisList);
        item.put("jhjcDate", jhjcList);
        item.put("xPtd", CommonUtil.getXaxis(ptd_list, "DUCT_CODE", "MILEAGE_CODE"));
        xAxis.put("data", ductList);
        xAxis.put("name", "环号\n里程");
        item.put("xAxis", xAxis);
        map.put("sectionDetail", item);
        map.put("colorLevel", dict);
        map.put("itemColorLevel", colorList);
        return map;
    }


    @Override
    public Map getPtdInitInfo(Map params) {
        Map resultMap = Maps.newHashMap();
        Map monitor = new HashMap();
        //初始化收敛数据
        List<Map> listEntiy = tdSlDataMapper.initPtdSlXData(params);
        List<Map> cj_list = tdCjDataMapper.initPtdCjXData(params);
        Map xdate = Maps.newHashMap();
        Object cj_xdata = CommonUtil.getXaxis(cj_list, "POINTSNAME", "POINTSMILEAGE");
        xdate.put("xdateOne", CommonUtil.getXaxis(listEntiy, "DUCT_CODE", "MILEAGE_CODE"));
        xdate.put("xdateTwo", cj_xdata);
        monitor.put("xdate", xdate);
        resultMap.put("monitor", monitor);
        return resultMap;
    }

    @Override
    public List getEmphasisList(Map params) {
        return tdEmphasisService.getEmphasisList(params);
    }

    @Override
    public Map getXiangQing(Map params) {
        Map result = Maps.newHashMap();
        List<Map> falutMaps = metroFaultMapper.selectByParam(params);
        List<Map> sandyMaps = tdSandyMapper.selectBySegment(params);
        List<Map> slMap = tdSlDataMapper.selectSLByDuct(params);
        result.put("fault", falutMaps);
        result.put("sandy", sandyMaps);
        if (slMap.size() > 0) {
            result.put("sl", slMap.get(0).get("YAIX"));
        } else {
            result.put("sl", null);
        }
        return result;
    }

    @Override
    public Map getCharInitTime(Map params) {
        Map result = Maps.newHashMap();
        //            //初始化选框
        List<Map> sl_legend = tdSlDataMapper.getLegend("0");
//        List<Map> em_legend = tdSlDataService.getLegend("1");
        List<Map> cj_legend = tdCjDataMapper.getCjDate(params);
        Map legend = new HashMap();
        legend.put("cjleglend", CommonUtil.setCjYear(cj_legend));
        legend.put("slleglend", sl_legend);
        result.put("legend", legend);
        return result;
    }

    private List<Map> getSegmentDuct(Map map) {
        String redisKey = map.get("line").toString() + ":" + map.get("updown").toString() + ":" + map.get("segment").toString();
        List<Map> result = null;
        try {
            result = JSONObject.parseArray((String) redisUtil.get(redisKey), Map.class);
            if (result == null) {
                result = tdDuctMapper.getSegmentDuct(map);
                redisUtil.set(redisKey, JSONObject.toJSONString(result), 7 * 24 * 60 * 60);
            }
        } catch (Exception e) {
            result = tdDuctMapper.getSegmentDuct(map);
        }

        return result;
    }
}
