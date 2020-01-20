package com.company.project.module.home.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.company.project.core.ProjectConstant;
import com.company.project.core.annotation.VerifyRedis;
import com.company.project.core.util.redis.RedisUtil;
import com.company.project.module.data.dao.TdCjDataMapper;
import com.company.project.module.data.dao.TdSlDataMapper;
import com.company.project.module.data.service.TdSegmentService;
import com.company.project.module.home.service.VisualService;
import com.company.project.module.home.util.HealthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Chen
 * @created 2019-11-28-14:13.
 */
@Service
public class VisualServiceImpl implements VisualService {
    @Resource
    private TdSegmentService tdSegmentService;
    @Autowired
    private TdSlDataMapper tdSlDataMapper;
    @Autowired
    private TdCjDataMapper tdCjDataMapper;
    @Resource
    private RedisUtil redisUtil;

    @Override
    public Map getDiseDesc(Map params) {
        return null;
    }

    @Override
    @VerifyRedis(ProjectConstant.LINE_HEALTH_KEY)
    public List<Map> getHealthMarck() {
        List<Map> segmentInfoForApp = tdSegmentService.findSegmentInfoForApp(null);
        List<Map> result = new HealthUtil().initHealth(segmentInfoForApp);
        //List<Map> result = new HealthUtil(tdSlDataService, metroCjService, metroFaultService, tdDictService).initHealth(segmentInfoForApp);
        redisUtil.set(ProjectConstant.LINE_HEALTH_KEY, JSONObject.toJSONString(result), 24 * 7 * 60 * 60);
        return result;
    }

    @Override
    public Map getZdjcYjData() {
        Map resultMap = new HashMap();
        //沉降
        List settWarning = tdCjDataMapper.getZdjcCjYjData();
        //收敛
        List astrWarning = tdSlDataMapper.getZdjcSlYjData();

        Map warning = new HashMap();
        warning.put("settWarning", settWarning);
        warning.put("astrWarning", astrWarning);
        resultMap.put("warning", warning);
        return resultMap;
    }

}
