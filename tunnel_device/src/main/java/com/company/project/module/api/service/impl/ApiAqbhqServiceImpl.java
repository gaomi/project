package com.company.project.module.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.company.project.core.AbstractService;
import com.company.project.core.ProjectConstant;
import com.company.project.core.Result;
import com.company.project.core.annotation.Log;
import com.company.project.core.annotation.VerifyRedis;
import com.company.project.core.exception.ServiceException;
import com.company.project.core.util.HttpClientUtil;
import com.company.project.core.util.StringUtils;
import com.company.project.core.util.redis.RedisUtil;
import com.company.project.module.api.dao.AqbhqMapper;
import com.company.project.module.api.dao.AqbhqPersonMapper;
import com.company.project.module.api.enums.ActionEnum;
import com.company.project.module.api.model.AqbhqPerson;
import com.company.project.module.api.model.AqbhqProject;
import com.company.project.module.api.model.OperParam;
import com.company.project.module.api.service.ApiAqbhqService;
import com.company.project.module.data.dao.TdDictMapper;
import com.company.project.module.data.dao.TdSegmentMapper;
import com.company.project.module.data.dao.TdStationMapper;
import com.company.project.module.data.model.TdDict;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.util.*;


/**
 * Created by paodingsoft.chen on 2019/05/27.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ApiAqbhqServiceImpl extends AbstractService<AqbhqProject> implements ApiAqbhqService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String apiUrl = ProjectConstant.AQBHQ_WEBAPI_URL;
    @Resource
    private AqbhqMapper aqbhqMapper;
    @Resource
    private AqbhqPersonMapper aqbhqPersonMapper;
    @Resource
    private HttpClientUtil httpClientUtil;
    @Resource
    private TdSegmentMapper tdSegmentMapper;

    @Resource
    private RedisUtil redisUtil;
    @Resource
    private TdStationMapper tdStationMapper;
    @Resource
    private TdDictMapper tdDictMapper;

    @Override
    public boolean getProjectInfo() {
        boolean result = false;
        for (int a = 0; a < 3; a++) {
            try {
                //远程url
                Result ss = httpClientUtil.post(apiUrl + "/GetProjectInfo", ProjectConstant.AQBHQ_KEY);
                //本地mock
                //Result ss = httpClientUtil.get("http://localhost:9089/metro_api/static/js/mock/aqbhqProjectInfo.json");
                List<AqbhqProject> aqbhqProjectInCondition  = (List<AqbhqProject>) StringUtils.parseArray((byte[])ss.getData(),AqbhqProject.class);
                Map<String, Object> segmentsMap = getSegmentUuid();
                //填充区间uuid
                aqbhqProjectInCondition.forEach(project -> {
                    String metrostationnamestart = project.getMetrostationnamestart().endsWith("站") ? project.getMetrostationnamestart() : project.getMetrostationnamestart() + "站";
                    String metrostationnameend = project.getMetrostationnameend().endsWith("站") ? project.getMetrostationnameend() : project.getMetrostationnameend() + "站";
                    String segKey = project.getMetrolinename() + "_" + metrostationnamestart + "_" + metrostationnameend;
                    if (segmentsMap.containsKey(segKey)) {
                        Object uuid = segmentsMap.get(segKey);
                        if ("" != uuid && null != uuid) {
                            project.setNoUdUuid(uuid.toString());
                        }
                    }
                });
                insertBatchForProject(aqbhqProjectInCondition);
                result = true;
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;

    }




    /***
     * 查询所有项目坐标并入库
     */
    @Override
    public boolean insertAllCoordinates() {
        boolean result = false;
        for (int a = 0; a < 3; a++) {
            //远程url
            try {
                Result ss = httpClientUtil.post(apiUrl + "/GetCoordinatesByProjectId", ProjectConstant.AQBHQ_KEY);
                List<Map> coordinatesList = (List<Map>)JSON.parse((byte[])ss.getData());
                insertBatchForCoordinates(coordinatesList);
                result = true;
                break;
            } catch (Exception e) {
                logger.info("项目坐标更新失败，第"+a+"次尝试");
                e.printStackTrace();
            }
        }
        return result;
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void insertBatchForCoordinates(List detailList) {
            //先删除
            aqbhqMapper.deleteAllCoordinates();
            int batchCount = 199;//每批commit的个数
            int batchLastIndex = batchCount - 1;//每批最后一个的下标
            for (int index = 0; index < detailList.size() - 1; ) {
                if (batchLastIndex > detailList.size() - 1) {
                    batchLastIndex = detailList.size();
                    aqbhqMapper.batchSaveCoordinates(detailList.subList(index, batchLastIndex));
                    logger.info("insert :" + index + " to " + batchLastIndex);
                    logger.info("insertBatch success : " + detailList.size() + " data inserted ;");
                    break;//数据插入完成,退出循环
                } else {
                    aqbhqMapper.batchSaveCoordinates(detailList.subList(index, batchLastIndex));
                    logger.info("insert :" + index + " to " + batchLastIndex);
                    index = batchLastIndex;//设置下一批下标
                    batchLastIndex = index + (batchCount - 1);
                    if (index == detailList.size() - 1) {
                        aqbhqMapper.batchSaveCoordinates(detailList.subList(index, index + 1));
                    }
                }
            }
    }

    /**
     * 获取区间的no_ud_uuid
     *
     * @return
     */

    @VerifyRedis(ProjectConstant.NO_UD_UUID_KEY)
    public Map<String, Object> getSegmentUuid() {
        List<Map> noUDIdByV = tdSegmentMapper.findNoUDIdByV();
        HashMap<String, Object> segmentsMap = Maps.newHashMap();
        noUDIdByV.forEach(map -> {
            String segKey1 = map.get("LINE_NAME").toString() + "_" + map.get("START_STATION_NAME") + "_" + map.get("END_STATION_NAME");
            String segKey2 = map.get("LINE_NAME").toString() + "_" + map.get("END_STATION_NAME") + "_" + map.get("START_STATION_NAME");
            Object noUDId = map.get("NO_UD_UUID");
            //if ("" != map.get("NO_UD_UUID") && null != map.get("NO_UD_UUID")) {
            //    String noUDId =   map.get("NO_UD_UUID").toString();
            //
            //} else {
            //    continue;
            //}
            segmentsMap.put(segKey1, noUDId);
            segmentsMap.put(segKey2, noUDId);

        });
        redisUtil.set(ProjectConstant.NO_UD_UUID_KEY, JSONObject.toJSONString(segmentsMap), 24 * 60 * 60);
        return segmentsMap;
    }

    @Log("根据id查询安全保区项目信息")
    @Override
    public Map getProjectInCondition(String id) {
        String result = null;
        try {
       /*     //远程url
            // result = HttpUtils.sendPost(apiUrl + "/GetProjectInCondition?ProjectID=", id);
            //本地mock
            result = HttpUtils.sendGet("http://localhost/tunnel_device/static/js/mock/aqbhqProjectInCondition.json", "");
            //本地测试
            //result = FileUtil.getDataByLocal("/static/js/mock/aqbhqProjectInCondition.json");
            JSONObject obj = JSONObject.parseObject(result);
            return ResultGenerator.genSuccessResult(obj);*/
            Map map = new HashMap();
            map.put("ProjectID", id);
            //远程url
            Result post1 = httpClientUtil.post(apiUrl + "/GetProjectInCondition", map, ProjectConstant.AQBHQ_KEY);
            Map aqbhqProjectInCondition = (Map)JSON.parse((byte[]) post1.getData());
            return aqbhqProjectInCondition;
        } catch (Exception e) {
            logger.error("api获取工况数据失败！");
            throw new ServiceException("api获取工况数据失败！", e);
        }
    }

    @Override
    public List<Map> getAllPersonInfo(Map param) {
        return aqbhqMapper.getAllPersonInfo(param);
    }

    @Override
    public boolean updateAllPersonInfo() {
        boolean result = false;
        try {
           /* //远程url
            // result = HttpUtils.sendPost(apiUrl + "/GetAllPersonInfo", "");
            //本地mock
            result = HttpUtils.sendGet("http://localhost/tunnel_device/static/js/mock/aqbhqAllPersonInfo.json", "");
            //本地测试
            //result = FileUtil.getDataByLocal("/static/js/mock/aqbhqAllPersonInfo.json");
            List aqbhqProjectInCondition = JSON.parseObject(result, new TypeReference<ArrayList<Map>>() {
            });
            return aqbhqProjectInCondition;*/
            //本地mock
            Result ss = httpClientUtil.post(apiUrl + "/GetAllPersonInfo", ProjectConstant.AQBHQ_KEY);
            // Result ss = httpClientUtil.get("http://localhost:9089/metro_api/static/js/mock/aqbhqAllPersonInfo.json");
            List<AqbhqPerson> aqbhqProjectInCondition = (List<AqbhqPerson>)StringUtils.parseArray((byte[]) ss.getData(), AqbhqPerson.class);

            this.insertBatchForPerson(aqbhqProjectInCondition);
            result = true;
        } catch (Exception e) {
            logger.error("api更新人员信息数据失败！");
        }
        return result;
    }

    @Override
    public Map getDictCheck() {
        Map result = Maps.newHashMap();
        Condition con = new Condition(TdDict.class);
        con.createCriteria().andEqualTo("pid","174").andEqualTo("status","1");
        List<TdDict> status = tdDictMapper.selectByCondition(con);
        Condition conLevel = new Condition(TdDict.class);
        conLevel.createCriteria().andEqualTo("pid","177").andEqualTo("status","1");
        List<TdDict> level = tdDictMapper.selectByCondition(conLevel);
        result.put("status",status);
        result.put("level",level);
        return result;
    }


    @Override
    public List<Map> getProwlerLocus() {
        try {
            //远程url
            Result ss = httpClientUtil.post(apiUrl + "/GetProwlerLocus", ProjectConstant.AQBHQ_KEY);
            //本地mock
            //Result ss  = httpClientUtil.get("http://localhost:9088/project/static/js/mock/aqbhqProwlerLocus.json");
            List aqbhqProjectInCondition = (List)JSON.parse((byte[]) ss.getData());
            return aqbhqProjectInCondition;
        } catch (Exception e) {
            logger.error("api获取人员巡查轨迹数据失败！");
            throw new ServiceException("api获取人员巡查轨迹数据失败！", e);
        }
    }

    @Override
    public void updateByAqbhq(AqbhqProject aqbhqProject) {
        try {
            AqbhqProject project = this.aqbhqMapper.selectByPrimaryKey(aqbhqProject);
            if (project != null) {
                this.aqbhqMapper.updateByApi(aqbhqProject);
            }
        } catch (Exception e) {
            throw new ServiceException("更新项目信息失败！", e);
        }
    }

    @Override
    public AqbhqProject getProjectInfoById(String id) {
//        AqbhqProject aqbhqProject = new AqbhqProject();
//        aqbhqProject.setProjectid(id);
//        return this.aqbhqMapper.selectOne(aqbhqProject);
        return this.aqbhqMapper.getProjectInfoById(id);
    }

    @Override
    public List<Map> selectAll(Map param) {
      /*  //return aqbhqMapper.getLineSegment(param);

        Condition condition = new Condition(AqbhqProject.class);
        Example.Criteria criteria = condition.createCriteria();
        for (Object key : param.keySet()) {
//            criteria.andEqualTo();
            criteria.andLike((String) key, param.get(key) + "%");
        }
        return this.aqbhqMapper.selectByCondition(condition);*/
        /*根据区间名查询*/
        return this.aqbhqMapper.selectBySegmentName(param);
    }


    @Override
    public List<Map> getPersonsLocation() {
        try {

            //1.获取位置信息数据
            Result locusStr = httpClientUtil.post(apiUrl + "/GetProwlerLocus", ProjectConstant.AQBHQ_KEY);
            List locusResult = (List)JSON.parse((byte[]) locusStr.getData());
            Map<String, Map> prowlerLocusMap = Maps.newHashMap();
            for (Object locus : locusResult) {
                Map locus1 = (Map) locus;
                String prowlerPersonID = (String) locus1.get("ProwlerPersonID");
                prowlerLocusMap.put(prowlerPersonID, locus1);
            }

            //2,从数据库获取人员信息
            List<AqbhqPerson> personResult = aqbhqMapper.findAllPerson();
            Map<String, AqbhqPerson> personsMap = Maps.newHashMap();
            for (AqbhqPerson person : personResult) {
                String personID = person.getPersonid();
                personsMap.put(personID, person);
            }

            //3.整合
            List<Map> infoList = Lists.newArrayList();
            for (Map.Entry<String, Map> map : prowlerLocusMap.entrySet()) {
                AqbhqPerson person = personsMap.get(map.getKey());
                Map locus = map.getValue();
                Map<Object, Object> resMap = Maps.newHashMap();
                locus.put("PersonName", person.getPersonname());
                locus.put("PersonSex", person.getPersonsex());
                locus.put("PersonContact", person.getPersoncontact());
                locus.put("PersonDescription", person.getPersondescription());
                locus.put("MetroLines", person.getMetrolines());
                locus.remove("ProwlerLocusBaiduCoordinatesY");
                locus.remove("ProwlerLocusBaiduCoordinatesX");
                resMap.put("person", locus);
                infoList.add(resMap);
            }
            return infoList;
        } catch (Exception e) {
            throw new ServiceException("获取信息失败！", e);
        }
    }

    @Override
    public List<Map> getProjectIdBySegment(String[] id) {
        List<Map> result = Lists.newArrayList();
        for (String item : id) {
            List<Map> itemList = aqbhqMapper.getProjectIdBySegment(item);
            result.addAll(itemList);
        }
        return result;
    }


    @Override
    public void operationByAqbhq(OperParam operParam) {
        if (operParam.checkAction(ActionEnum.INSERT)) {
            this.aqbhqMapper.insert((AqbhqProject) operParam.getData());
        } else if (operParam.checkAction(ActionEnum.UPDATE)) {
            this.aqbhqMapper.updateByApi((AqbhqProject) operParam.getData());
        }
    }

    @Override
    public void operationByAqbhq(AqbhqProject aqbhq, String state) {
        if (state.equalsIgnoreCase(ActionEnum.INSERT.getCode())) {
            this.aqbhqMapper.insert(aqbhq);
        } else if (state.equalsIgnoreCase(ActionEnum.UPDATE.getCode())) {
            this.aqbhqMapper.updateByApi(aqbhq);
        }
    }

    @Log("根据时间查询项目工况信息")
    @Override
    public List<Map> GetPatrollerByTime(Map map) {
        String result = null;
        try {
            //远程url
            Result post1 = httpClientUtil.post(apiUrl + "/GetPatrollerByTime", map, ProjectConstant.AQBHQ_KEY);
            List aqbhqProjectInCondition = (List)JSON.parse((byte[]) post1.getData());
            return aqbhqProjectInCondition;
        } catch (Exception e) {
            logger.error("api获取工况数据失败！");
            throw new ServiceException("api获取工况数据失败！", e);
        }
    }

    @Override
    public void changeToken(String code) {
        httpClientUtil.changeToken(code);
    }

    /**
     * 批量插入
     *
     * @param detailList
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void insertBatchForProject(List<AqbhqProject> detailList) {
            //先删除
            aqbhqMapper.deleteAllProject();
            int batchCount = 199;//每批commit的个数
            int batchLastIndex = batchCount - 1;//每批最后一个的下标
            for (int index = 0; index < detailList.size() - 1; ) {
                if (batchLastIndex > detailList.size() - 1) {
                    batchLastIndex = detailList.size();
                    aqbhqMapper.batchSaveProject(detailList.subList(index, batchLastIndex));
                    logger.info("insert :" + index + " to " + batchLastIndex);
                    logger.info("insertBatch success : " + detailList.size() + " data inserted ;");
                    break;//数据插入完成,退出循环
                } else {
                    aqbhqMapper.batchSaveProject(detailList.subList(index, batchLastIndex));
                    logger.info("insert :" + index + " to " + batchLastIndex);
                    index = batchLastIndex;//设置下一批下标
                    batchLastIndex = index + (batchCount - 1);
                    if (index == detailList.size() - 1) {
                        aqbhqMapper.batchSaveProject(detailList.subList(index, index + 1));
                    }
                }
            }
    }

    /**
     * 批量插入
     *
     * @param detailList
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void insertBatchForPerson(List<AqbhqPerson> detailList) {
            aqbhqMapper.deleteAllPerson();
            int batchCount = 199;//每批commit的个数
            int batchLastIndex = batchCount - 1;//每批最后一个的下标
            for (int index = 0; index < detailList.size() - 1; ) {
                if (batchLastIndex > detailList.size() - 1) {
                    batchLastIndex = detailList.size();
                    aqbhqMapper.saveAllPerson(detailList.subList(index, batchLastIndex));
                    logger.info("insert :" + index + " to " + batchLastIndex);
                    logger.info("insertBatch success : " + detailList.size() + " data inserted ;");
                    break;//数据插入完成,退出循环
                } else {
                    aqbhqMapper.saveAllPerson(detailList.subList(index, batchLastIndex));
                    logger.info("insert :" + index + " to " + batchLastIndex);
                    index = batchLastIndex;//设置下一批下标
                    batchLastIndex = index + (batchCount - 1);
                    if (index == detailList.size() - 1) {
                        aqbhqMapper.saveAllPerson(detailList.subList(index, index + 1));
                    }
                }
            }
    }

    @Override
    public List<AqbhqProject> getProjectBySegment(String[] id) {
        List<AqbhqProject> result = Lists.newArrayList();
        for (String item : id) {
            List<Map> listMap = tdStationMapper.selectStation(item);
            List<AqbhqProject> itemList = aqbhqMapper.getProjectBySegment(listMap.get(0));
            result.addAll(itemList);
        }
        return result;
    }

    @Override
    public List<Map> selectAqbhqInfo() {
        return aqbhqMapper.selectAqbhqInfo();
    }

    @Override
    public int getAqbhqMatk(Map map) {
        return aqbhqMapper.getAqbhqMatk(map);
    }


}
