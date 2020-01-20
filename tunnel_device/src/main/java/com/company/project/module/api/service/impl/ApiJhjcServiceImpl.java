package com.company.project.module.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import com.company.project.core.AbstractService;
import com.company.project.core.ProjectConstant;
import com.company.project.core.Result;
import com.company.project.core.annotation.Log;
import com.company.project.core.exception.ServiceException;
import com.company.project.core.util.HttpClientUtil;
import com.company.project.module.api.dao.JhjcMapper;
import com.company.project.module.api.model.JhjcProject;
import com.company.project.module.api.service.ApiJhjcService;
import com.company.project.module.data.dao.TdDictMapper;
import com.company.project.module.data.model.TdDict;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by paodingsoft.chen on 2019/06/25.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ApiJhjcServiceImpl extends AbstractService<JhjcProject> implements ApiJhjcService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private JhjcMapper jhjcMapper;
    @Resource
    private HttpClientUtil httpClientUtil;
    @Resource
    private TdDictMapper tdDictMapper;

    private String apiUrl = ProjectConstant.JHJC_WEBAPI_URL;

    @Log("获取所有监护监测项目信息")
    @Override
    public void getProjectInfo() {
        List<JhjcProject> jhjcProjects = null;
        try {
            //远程url
            Result resu = httpClientUtil.get(apiUrl + "/projects");
            //本地mock
            //Result resu = httpClientUtil.get("http://localhost:9089/metro_api/static/js/mock/jhjcProjectInfo.json");
            jhjcProjects = (List)JSON.parse((byte[]) resu.getData());
            jhjcMapper.deleteAll();
            insertBatch(jhjcProjects);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("查询监护监测项目信息失败！", e);
        }

    }

    @Log("根据id获取监护监测项目信息")
    @Override
    public List<Map> getProjectLayers(String id) {
        try {
            //远程url
            Map map = new HashMap();
            map.put("prjId", id);
            Result resu = httpClientUtil.get(apiUrl + "/layers", map);
            //本地mock
//            Result resu = httpClientUtil.get("http://localhost:9089/metro_api/static/js/mock/jhjcProjectLayer.json");
            //本地测试
            List jhjcProjectInCondition = (List)JSON.parse((byte[]) resu.getData());
            return jhjcProjectInCondition;
        } catch (Exception e) {
            logger.error("api获取监护监测项目布点图数据失败！");
            throw new ServiceException("api获取监护监测项目布点图数据失败！", e);
        }
    }

    @Override
    public void updateByJhjc(JhjcProject jhjcProject) {
        try {
            JhjcProject project = this.jhjcMapper.selectOne(jhjcProject);
            if (project != null) {
                this.update(jhjcProject);
            }
        } catch (Exception e) {
            throw new ServiceException("更新监护监测项目信息失败！", e);
        }
    }

    @Override
    public JhjcProject getProjectInfoById(String id) {
        return this.jhjcMapper.getProjectInfoById(id);
    }

    @Override
    public Map<String, Object> selectLimt(Integer num, Integer size, Map param) {
        Map<String, Object> result = new HashMap<String, Object>();
        param.put("num", num);
        param.put("size", size);
        List<JhjcProject> listjhjc = jhjcMapper.selectLimt(param);
        int total = jhjcMapper.getTotal(param);
        result.put("rows", listjhjc);
        result.put("total", total);
        return result;
    }

    @Override
    public List<Map> getProjecStatus(String id) {
        return null;
    }

    @Override
    public List getDetailById(String id) {
        Map map = Maps.newHashMap();
        map.put("prjId", id);
        Result res = httpClientUtil.get(apiUrl + "/status", map);

        List objects = (List)JSON.parse((byte[]) res.getData());
        return objects;
    }

    @Override
    public List<JhjcProject> findByIds(List<String> list) {
        return jhjcMapper.findByIds(list);
    }

    @Override
    public JhjcProject selectById(String s) {
        List list = Lists.newArrayList();
        list.add(s);
        List<JhjcProject> result = findByIds(list);
        return result.get(0);
    }

    @Override
    public List<JhjcProject> selectAll(Map param) {
        return jhjcMapper.selectByKeyword(param);
    }

    @Override
    public List<JhjcProject> queryAll() {
        return jhjcMapper.queryAll();
    }

    @Override
    public List<Map> getProjectState(String id) {
        String result = null;
        try {
            Map map = new HashMap();
            map.put("prjId",id);
            Result resu = httpClientUtil.get(apiUrl + "/status",map);
            List jhjcProjectInCondition = (List)JSON.parse((byte[])resu.getData());

            return jhjcProjectInCondition;
        } catch (Exception e) {
            logger.error("根据id获取监护监测项目工况信息失败！");
            throw new ServiceException("根据id获取监护监测项目工况信息失败！", e);
        }
    }

    @Override
    public List<Map> getProjectPoint(Map map) {
        String result = null;
        try {
            Result resu = httpClientUtil.get(apiUrl + "/points",map);
            List jhjcProjectInCondition = (List)JSON.parse((byte[])resu.getData());
            return jhjcProjectInCondition;
        } catch (Exception e) {
            logger.error("根据id起始时间获取监护监测项目工况测点变形信息失败！");
            throw new ServiceException("根据id起始时间获取监护监测项目工况测点变形信息失败！", e);
        }
    }

    @Override
    public Map getDictCheck() {
        Map result = Maps.newHashMap();
        //获取监护项目状态
        Condition con = new Condition(TdDict.class);
        con.createCriteria().andEqualTo("pid","173").andEqualTo("status","1");
        List<TdDict> status = tdDictMapper.selectByCondition(con);
        //获取监护项目等级
        Condition conLevel = new Condition(TdDict.class);
        conLevel.createCriteria().andEqualTo("pid","176").andEqualTo("status","1");
        List<TdDict> level = tdDictMapper.selectByCondition(conLevel);
        result.put("status",status);
        result.put("level",level);
        return result;
    }

    /**
     * 批量插入
     *
     * @param detailList
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void insertBatch(List<JhjcProject> detailList) {
        try {
            int batchCount = 199;//每批commit的个数
            int batchLastIndex = batchCount - 1;//每批最后一个的下标
            for (int index = 0; index < detailList.size() - 1; ) {
                if (batchLastIndex > detailList.size() - 1) {
                    batchLastIndex = detailList.size();
                    jhjcMapper.batchSaveProject(detailList.subList(index, batchLastIndex));
                    logger.info("insert :" + index + " to " + batchLastIndex);
                    logger.info("insertBatch success : " + detailList.size() + " data inserted ;");
                    break;//数据插入完成,退出循环
                } else {
                    jhjcMapper.batchSaveProject(detailList.subList(index, batchLastIndex));
                    logger.info("insert :" + index + " to " + batchLastIndex);
                    index = batchLastIndex;//设置下一批下标
                    batchLastIndex = index + (batchCount - 1);
                    if (index == detailList.size() - 1) {
                        jhjcMapper.batchSaveProject(detailList.subList(index, index + 1));
                    }
                }
            }

        } catch (Exception e) {
            throw new ServiceException("插入数据失败！", e);
        }
    }


}
