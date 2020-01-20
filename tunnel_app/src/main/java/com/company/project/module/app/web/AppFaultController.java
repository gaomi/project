package com.company.project.module.app.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.controller.CommonBaseController;
import com.company.project.core.model.QueryRequest;
import com.company.project.module.app.service.AppFaultService;
import com.company.project.module.sys.model.SysUser;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by paodingsoft.chen on 2019/09/20.
 */
@CrossOrigin
@RestController
@RequestMapping("/app/fault")
@Api(description = "手机端病害接口")
public class AppFaultController extends CommonBaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private AppFaultService appFaultService;

    @PostMapping("/plan/getXfPlan")
    @ApiOperation(value = "列表", notes = "手机端已下发施工计划列表")
    public Result getXfPlan(@RequestBody QueryRequest request, HttpSession session) {
        try {
            String employeeId = super.verifySession(session);
            Map<String, Object> result = super.selectByPageNumSize(request, () -> this.appFaultService.findXfPlanForApp(employeeId));
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            logger.error("获取施工计划列表失败", e);
            return ResultGenerator.genFailResult("获取施工计划列表失败！");
        }
    }

    @PostMapping("/plan/receivePlanStatus")
    @ApiOperation(value = "手机端施工计划接收", notes = "施工计划状态修改")
    public Result receivePlan(@RequestBody QueryRequest request, HttpSession session) {
        try {

            String employeeId = super.verifySession(session);
            Map params = request.getParams();
            appFaultService.changePlanStatus(request.getParams());
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("施工计划状态修改失败", e);
            return ResultGenerator.genFailResult("施工计划状态修改失败");
        }
    }

    @PostMapping("/plan/list")
    @ApiOperation(value = "列表", notes = "手机端施工计划列表")
    public Result list(@RequestBody QueryRequest request, HttpSession session) {
        try {
            String employeeId = super.verifySession(session);
            Map<String, Object> result = super.selectByPageNumSize(request, () -> this.appFaultService.findAllPlanForApp(request.getParams(), employeeId));
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            logger.error("获取施工计划列表失败", e);
            return ResultGenerator.genFailResult("获取施工计划列表失败！");
        }
    }

    @PostMapping("/plan/getPlanDetailByUuid")
    @ApiOperation(value = "查询病害", notes = "手机端根据工单id查询所有病害")
    public Result getPlanDetailByUuid(@RequestBody QueryRequest request, HttpSession session) {
        try {
            String userId = super.verifySession(session);
            Map params = request.getParams();
            String id = String.valueOf(params.get("UUID")).trim();
            Map<String, Object> result = this.appFaultService.findDetailByPlanForApp(id);
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            logger.error("查询病害数据失败", e);
            return ResultGenerator.genFailResult("查询病害数据失败！");
        }
    }

    @PostMapping("/plan/uploadByPlan")
    @ApiOperation(value = "任务病害上传", notes = "手机端任务病害数据上传接口")
    public Result uploadByPlan(@RequestBody QueryRequest request, HttpSession session) {
        try {
            System.out.println("开始上传--------------------------------");
            String userId = super.verifySession(session);
            SysUser webUser = getWebUser(session);
            //String dir = "D:\\log\\audio" + File.separator;
            //// 如果文件夹没有创建文件夹
            //File filesMin = new File(dir);
            //if (!filesMin.exists()) {
            //    filesMin.mkdirs();
            //}
            //String fileName = "aaa.mp4";
            //// 保存文件  fileName.substring(fileName.lastIndexOf("."))
            //String minFile = dir + File.separator + fileName;
            //文件上传
            //FileUtils.copyInputStreamToFile(file.getInputStream(), new File(minFile));
            //todo 返回任务id

            String planUuid = appFaultService.uploadPlanData(request.getParams(),webUser.getEmployeeNo());
            System.out.println("结束上传--------------------------------");
            return ResultGenerator.genSuccessResult(planUuid);
        } catch (Exception e) {
            logger.error("上传失败!", e);
            return ResultGenerator.genFailResult("上传失败!");
        }
    }

    @PostMapping("/plan/uploadByFault")
    @ApiOperation(value = "离线病害上传", notes = "手机端离线病害数据上传")
    public Result uploadByFault(@RequestBody QueryRequest request, HttpSession session) {
        try {
            String userId = super.verifySession(session);
            Map params = request.getParams();
            JSONArray offline_faultList = (JSONArray) params.get("offline_faultList");
            List<String> faultList = JSONObject.parseArray(offline_faultList.toJSONString(), String.class);
            ArrayList<String> list = Lists.newArrayList();
            faultList.forEach(fault -> {
                Map parse = (Map) JSONObject.parse(fault);
                list.add(parse.get("INTERNAL_CODE").toString());
            });
            /*offline_faultList.toArray();
            Object[] objects = offline_faultList.toArray();
            String s = offline_faultList.toJSONString();
            String s2="[\"{\\\"INTERNAL_CODE\\\":\\\"xao178l1zw\\\",\\\"LINE_ID\\\":\\\"0F8C4BB8E8764B478C3E0F85781B5DD9\\\",\\\"HIGH_LOW\\\":\\\"U\\\",\\\"START_STA\\\":\\\"C8DCAE3F175346D98D27E8BC4A6866D2\\\",\\\"END_STA\\\":\\\"D266788036354A98B057C8014DA4F378\\\",\\\"START_STA_T\\\":\\\"顾村公园站\\\",\\\"END_STA_T\\\":\\\"祁华路站\\\",\\\"DEVICE_ID\\\":\\\"0705060103000000000004\\\",\\\"DEVICE_NAME\\\":\\\"顾村公园至祁华路隧道上行\\\",\\\"DUCT_CODE\\\":\\\"5\\\",\\\"DUCT_CODE2\\\":\\\"1\\\",\\\"STATION_KI_NUM\\\":\\\"1\\\",\\\"STATION_HUN_NUM\\\":\\\"55\\\",\\\"STATION_KI_NUM_END\\\":\\\"0\\\",\\\"STATION_HUN_NUM_END\\\":\\\"0\\\",\\\"DUCT_NUM\\\":\\\"4\\\",\\\"BIG_TYPE_ID\\\":\\\"BGWSD02\\\",\\\"BIG_TYPE_ID_T\\\":\\\"人防门\\\",\\\"SMALL_TYPE_ID\\\":\\\"WBGWSD0206\\\",\\\"SMALL_TYPE_ID_T\\\":\\\"密封条\\\",\\\"FAULT_LEVEL\\\":\\\"AA\\\",\\\"FAULT_LEVEL_T\\\":\\\"AA\\\",\\\"FAULT_TYPE\\\":\\\"WBGWSD020603\\\",\\\"FAULT_TYPE_T\\\":\\\"缺损\\\",\\\"DETAIL_LOC\\\":\\\"item6,item7\\\",\\\"FAULT_QUANTITY\\\":\\\"\\\",\\\"FAULT_QUANTITY_T\\\":\\\"\\\",\\\"FAULT_UNIT\\\":\\\"1-1\\\",\\\"FAULT_UNIT_T\\\":\\\"米\\\",\\\"AFFECTE_EQUIPMENT\\\":\\\"\\\",\\\"FAULT_DESC\\\":\\\"\\\",\\\"IS_FINISH\\\":\\\"1\\\",\\\"IS_FINISH_T\\\":\\\"已修复\\\",\\\"REPAIR_DESC\\\":\\\"\\\",\\\"MATERIAL_CONSUMPTION\\\":\\\"\\\"}\",\"{\\\"INTERNAL_CODE\\\":\\\"35zkn9ace0\\\",\\\"LINE_ID\\\":\\\"3D530A1220204ED5B9087F8B854A7D6F\\\",\\\"HIGH_LOW\\\":\\\"U\\\",\\\"START_STA\\\":\\\"942985FE175C4975811C4C14E230875A\\\",\\\"END_STA\\\":\\\"248333CEE42E48749330726D08FDCC44\\\",\\\"START_STA_T\\\":\\\"鞍山新村站\\\",\\\"END_STA_T\\\":\\\"四平路站\\\",\\\"DEVICE_ID\\\":\\\"0808090103000000000001\\\",\\\"DEVICE_NAME\\\":\\\"鞍山新村至四平路上行隧道\\\",\\\"DUCT_CODE\\\":\\\"1\\\",\\\"DUCT_CODE2\\\":\\\"500\\\",\\\"STATION_KI_NUM\\\":\\\"10\\\",\\\"STATION_HUN_NUM\\\":\\\"500\\\",\\\"STATION_KI_NUM_END\\\":\\\"11\\\",\\\"STATION_HUN_NUM_END\\\":\\\"081\\\",\\\"DUCT_NUM\\\":\\\"499\\\",\\\"BIG_TYPE_ID\\\":\\\"BGWSD01\\\",\\\"BIG_TYPE_ID_T\\\":\\\"隧道\\\",\\\"SMALL_TYPE_ID\\\":\\\"WBGWSD0109\\\",\\\"SMALL_TYPE_ID_T\\\":\\\"疏散平台\\\",\\\"FAULT_LEVEL\\\":\\\"D\\\",\\\"FAULT_LEVEL_T\\\":\\\"D\\\",\\\"FAULT_TYPE\\\":\\\"WBGWSD010903\\\",\\\"FAULT_TYPE_T\\\":\\\"其他\\\",\\\"DETAIL_LOC\\\":\\\"\\\",\\\"FAULT_QUANTITY\\\":\\\"33\\\",\\\"FAULT_QUANTITY_T\\\":\\\"\\\",\\\"FAULT_UNIT\\\":\\\"1-1\\\",\\\"FAULT_UNIT_T\\\":\\\"米\\\",\\\"AFFECTE_EQUIPMENT\\\":\\\"\\\",\\\"FAULT_DESC\\\":\\\"sjsjsj\\\\nsnsns\\\",\\\"IS_FINISH\\\":\\\"1\\\",\\\"IS_FINISH_T\\\":\\\"已修复\\\",\\\"REPAIR_DESC\\\":\\\"dndndj\\\",\\\"MATERIAL_CONSUMPTION\\\":\\\"dbdndh\\\"}\"]";
            String s3="[\"{\\\"INTERNAL_CODE\\\":\\\"xao178l1zw\\\",\\\"LINE_ID\\\":\\\"0F8C4BB8E8764B478C3E0F85781B5DD9\\\",\\\"HIGH_LOW\\\":\\\"U\\\",\\\"START_STA\\\":\\\"C8DCAE3F175346D98D27E8BC4A6866D2\\\",\\\"END_STA\\\":\\\"D266788036354A98B057C8014DA4F378\\\",\\\"START_STA_T\\\":\\\"顾村公园站\\\",\\\"END_STA_T\\\":\\\"祁华路站\\\",\\\"DEVICE_ID\\\":\\\"0705060103000000000004\\\",\\\"DEVICE_NAME\\\":\\\"顾村公园至祁华路隧道上行\\\",\\\"DUCT_CODE\\\":\\\"5\\\",\\\"DUCT_CODE2\\\":\\\"1\\\",\\\"STATION_KI_NUM\\\":\\\"1\\\",\\\"STATION_HUN_NUM\\\":\\\"55\\\",\\\"STATION_KI_NUM_END\\\":\\\"0\\\",\\\"STATION_HUN_NUM_END\\\":\\\"0\\\",\\\"DUCT_NUM\\\":\\\"4\\\",\\\"BIG_TYPE_ID\\\":\\\"BGWSD02\\\",\\\"BIG_TYPE_ID_T\\\":\\\"人防门\\\",\\\"SMALL_TYPE_ID\\\":\\\"WBGWSD0206\\\",\\\"SMALL_TYPE_ID_T\\\":\\\"密封条\\\",\\\"FAULT_LEVEL\\\":\\\"AA\\\",\\\"FAULT_LEVEL_T\\\":\\\"AA\\\",\\\"FAULT_TYPE\\\":\\\"WBGWSD020603\\\",\\\"FAULT_TYPE_T\\\":\\\"缺损\\\",\\\"DETAIL_LOC\\\":\\\"item6,item7\\\",\\\"FAULT_QUANTITY\\\":\\\"\\\",\\\"FAULT_QUANTITY_T\\\":\\\"\\\",\\\"FAULT_UNIT\\\":\\\"1-1\\\",\\\"FAULT_UNIT_T\\\":\\\"米\\\",\\\"AFFECTE_EQUIPMENT\\\":\\\"\\\",\\\"FAULT_DESC\\\":\\\"\\\",\\\"IS_FINISH\\\":\\\"1\\\",\\\"IS_FINISH_T\\\":\\\"已修复\\\",\\\"REPAIR_DESC\\\":\\\"\\\",\\\"MATERIAL_CONSUMPTION\\\":\\\"\\\"}\",\"{\\\"INTERNAL_CODE\\\":\\\"35zkn9ace0\\\",\\\"LINE_ID\\\":\\\"3D530A1220204ED5B9087F8B854A7D6F\\\",\\\"HIGH_LOW\\\":\\\"U\\\",\\\"START_STA\\\":\\\"942985FE175C4975811C4C14E230875A\\\",\\\"END_STA\\\":\\\"248333CEE42E48749330726D08FDCC44\\\",\\\"START_STA_T\\\":\\\"鞍山新村站\\\",\\\"END_STA_T\\\":\\\"四平路站\\\",\\\"DEVICE_ID\\\":\\\"0808090103000000000001\\\",\\\"DEVICE_NAME\\\":\\\"鞍山新村至四平路上行隧道\\\",\\\"DUCT_CODE\\\":\\\"1\\\",\\\"DUCT_CODE2\\\":\\\"500\\\",\\\"STATION_KI_NUM\\\":\\\"10\\\",\\\"STATION_HUN_NUM\\\":\\\"500\\\",\\\"STATION_KI_NUM_END\\\":\\\"11\\\",\\\"STATION_HUN_NUM_END\\\":\\\"081\\\",\\\"DUCT_NUM\\\":\\\"499\\\",\\\"BIG_TYPE_ID\\\":\\\"BGWSD01\\\",\\\"BIG_TYPE_ID_T\\\":\\\"隧道\\\",\\\"SMALL_TYPE_ID\\\":\\\"WBGWSD0109\\\",\\\"SMALL_TYPE_ID_T\\\":\\\"疏散平台\\\",\\\"FAULT_LEVEL\\\":\\\"D\\\",\\\"FAULT_LEVEL_T\\\":\\\"D\\\",\\\"FAULT_TYPE\\\":\\\"WBGWSD010903\\\",\\\"FAULT_TYPE_T\\\":\\\"其他\\\",\\\"DETAIL_LOC\\\":\\\"\\\",\\\"FAULT_QUANTITY\\\":\\\"33\\\",\\\"FAULT_QUANTITY_T\\\":\\\"\\\",\\\"FAULT_UNIT\\\":\\\"1-1\\\",\\\"FAULT_UNIT_T\\\":\\\"米\\\",\\\"AFFECTE_EQUIPMENT\\\":\\\"\\\",\\\"FAULT_DESC\\\":\\\"sjsjsj\\\\nsnsns\\\",\\\"IS_FINISH\\\":\\\"1\\\",\\\"IS_FINISH_T\\\":\\\"已修复\\\",\\\"REPAIR_DESC\\\":\\\"dndndj\\\",\\\"MATERIAL_CONSUMPTION\\\":\\\"dbdndh\\\"}\"]";
            System.out.println(s);
            System.out.println(objects);
            ArrayList<String> list = Lists.newArrayList();*/
            //offline_faultList.forEach(fault ->{
            //    JSONArray array = (JSONArray) fault;
            //    List<Map> list1 = JSONObject.parseArray(array.toJSONString(), Map.class);
            //    System.out.println(map.get("INTERNAL_CODE").toString());
            //    list.add(map.get("INTERNAL_CODE").toString());
            //});
            //List<Map> faultList = JSONObject.parseArray(offline_faultList.toJSONString(), Map.class);
            //
            //
            //faultList.forEach(fault -> {
            //    Map map = (Map) fault;
            //    list.add(map.get("INTERNAL_CODE").toString());
            //});
            //todo 返回病害id
            return ResultGenerator.genSuccessResult(list);
        } catch (Exception e) {
            logger.error("上传失败!", e);
            return ResultGenerator.genFailResult("上传失败!");
        }
    }

    @PostMapping("/plan/oldUploadByFault")
    @ApiOperation(value = "离线病害上传", notes = "手机端离线病害数据上传旧接口")
    public Result oldUploadByFault(@RequestBody QueryRequest request, HttpSession session) {
        try {
            String userId = super.verifySession(session);
            Map params = request.getParams();
            JSONArray offline_faultList = (JSONArray) params.get("offline_faultList");
            List<String> faultList = JSONObject.parseArray(offline_faultList.toJSONString(), String.class);
            ArrayList<String> list = Lists.newArrayList();
            faultList.forEach(fault -> {
                Map parse = (Map) JSONObject.parse(fault);
                list.add(parse.get("INTERNAL_CODE").toString());
            });
            /*offline_faultList.toArray();
            Object[] objects = offline_faultList.toArray();
            String s = offline_faultList.toJSONString();
            String s2="[\"{\\\"INTERNAL_CODE\\\":\\\"xao178l1zw\\\",\\\"LINE_ID\\\":\\\"0F8C4BB8E8764B478C3E0F85781B5DD9\\\",\\\"HIGH_LOW\\\":\\\"U\\\",\\\"START_STA\\\":\\\"C8DCAE3F175346D98D27E8BC4A6866D2\\\",\\\"END_STA\\\":\\\"D266788036354A98B057C8014DA4F378\\\",\\\"START_STA_T\\\":\\\"顾村公园站\\\",\\\"END_STA_T\\\":\\\"祁华路站\\\",\\\"DEVICE_ID\\\":\\\"0705060103000000000004\\\",\\\"DEVICE_NAME\\\":\\\"顾村公园至祁华路隧道上行\\\",\\\"DUCT_CODE\\\":\\\"5\\\",\\\"DUCT_CODE2\\\":\\\"1\\\",\\\"STATION_KI_NUM\\\":\\\"1\\\",\\\"STATION_HUN_NUM\\\":\\\"55\\\",\\\"STATION_KI_NUM_END\\\":\\\"0\\\",\\\"STATION_HUN_NUM_END\\\":\\\"0\\\",\\\"DUCT_NUM\\\":\\\"4\\\",\\\"BIG_TYPE_ID\\\":\\\"BGWSD02\\\",\\\"BIG_TYPE_ID_T\\\":\\\"人防门\\\",\\\"SMALL_TYPE_ID\\\":\\\"WBGWSD0206\\\",\\\"SMALL_TYPE_ID_T\\\":\\\"密封条\\\",\\\"FAULT_LEVEL\\\":\\\"AA\\\",\\\"FAULT_LEVEL_T\\\":\\\"AA\\\",\\\"FAULT_TYPE\\\":\\\"WBGWSD020603\\\",\\\"FAULT_TYPE_T\\\":\\\"缺损\\\",\\\"DETAIL_LOC\\\":\\\"item6,item7\\\",\\\"FAULT_QUANTITY\\\":\\\"\\\",\\\"FAULT_QUANTITY_T\\\":\\\"\\\",\\\"FAULT_UNIT\\\":\\\"1-1\\\",\\\"FAULT_UNIT_T\\\":\\\"米\\\",\\\"AFFECTE_EQUIPMENT\\\":\\\"\\\",\\\"FAULT_DESC\\\":\\\"\\\",\\\"IS_FINISH\\\":\\\"1\\\",\\\"IS_FINISH_T\\\":\\\"已修复\\\",\\\"REPAIR_DESC\\\":\\\"\\\",\\\"MATERIAL_CONSUMPTION\\\":\\\"\\\"}\",\"{\\\"INTERNAL_CODE\\\":\\\"35zkn9ace0\\\",\\\"LINE_ID\\\":\\\"3D530A1220204ED5B9087F8B854A7D6F\\\",\\\"HIGH_LOW\\\":\\\"U\\\",\\\"START_STA\\\":\\\"942985FE175C4975811C4C14E230875A\\\",\\\"END_STA\\\":\\\"248333CEE42E48749330726D08FDCC44\\\",\\\"START_STA_T\\\":\\\"鞍山新村站\\\",\\\"END_STA_T\\\":\\\"四平路站\\\",\\\"DEVICE_ID\\\":\\\"0808090103000000000001\\\",\\\"DEVICE_NAME\\\":\\\"鞍山新村至四平路上行隧道\\\",\\\"DUCT_CODE\\\":\\\"1\\\",\\\"DUCT_CODE2\\\":\\\"500\\\",\\\"STATION_KI_NUM\\\":\\\"10\\\",\\\"STATION_HUN_NUM\\\":\\\"500\\\",\\\"STATION_KI_NUM_END\\\":\\\"11\\\",\\\"STATION_HUN_NUM_END\\\":\\\"081\\\",\\\"DUCT_NUM\\\":\\\"499\\\",\\\"BIG_TYPE_ID\\\":\\\"BGWSD01\\\",\\\"BIG_TYPE_ID_T\\\":\\\"隧道\\\",\\\"SMALL_TYPE_ID\\\":\\\"WBGWSD0109\\\",\\\"SMALL_TYPE_ID_T\\\":\\\"疏散平台\\\",\\\"FAULT_LEVEL\\\":\\\"D\\\",\\\"FAULT_LEVEL_T\\\":\\\"D\\\",\\\"FAULT_TYPE\\\":\\\"WBGWSD010903\\\",\\\"FAULT_TYPE_T\\\":\\\"其他\\\",\\\"DETAIL_LOC\\\":\\\"\\\",\\\"FAULT_QUANTITY\\\":\\\"33\\\",\\\"FAULT_QUANTITY_T\\\":\\\"\\\",\\\"FAULT_UNIT\\\":\\\"1-1\\\",\\\"FAULT_UNIT_T\\\":\\\"米\\\",\\\"AFFECTE_EQUIPMENT\\\":\\\"\\\",\\\"FAULT_DESC\\\":\\\"sjsjsj\\\\nsnsns\\\",\\\"IS_FINISH\\\":\\\"1\\\",\\\"IS_FINISH_T\\\":\\\"已修复\\\",\\\"REPAIR_DESC\\\":\\\"dndndj\\\",\\\"MATERIAL_CONSUMPTION\\\":\\\"dbdndh\\\"}\"]";
            String s3="[\"{\\\"INTERNAL_CODE\\\":\\\"xao178l1zw\\\",\\\"LINE_ID\\\":\\\"0F8C4BB8E8764B478C3E0F85781B5DD9\\\",\\\"HIGH_LOW\\\":\\\"U\\\",\\\"START_STA\\\":\\\"C8DCAE3F175346D98D27E8BC4A6866D2\\\",\\\"END_STA\\\":\\\"D266788036354A98B057C8014DA4F378\\\",\\\"START_STA_T\\\":\\\"顾村公园站\\\",\\\"END_STA_T\\\":\\\"祁华路站\\\",\\\"DEVICE_ID\\\":\\\"0705060103000000000004\\\",\\\"DEVICE_NAME\\\":\\\"顾村公园至祁华路隧道上行\\\",\\\"DUCT_CODE\\\":\\\"5\\\",\\\"DUCT_CODE2\\\":\\\"1\\\",\\\"STATION_KI_NUM\\\":\\\"1\\\",\\\"STATION_HUN_NUM\\\":\\\"55\\\",\\\"STATION_KI_NUM_END\\\":\\\"0\\\",\\\"STATION_HUN_NUM_END\\\":\\\"0\\\",\\\"DUCT_NUM\\\":\\\"4\\\",\\\"BIG_TYPE_ID\\\":\\\"BGWSD02\\\",\\\"BIG_TYPE_ID_T\\\":\\\"人防门\\\",\\\"SMALL_TYPE_ID\\\":\\\"WBGWSD0206\\\",\\\"SMALL_TYPE_ID_T\\\":\\\"密封条\\\",\\\"FAULT_LEVEL\\\":\\\"AA\\\",\\\"FAULT_LEVEL_T\\\":\\\"AA\\\",\\\"FAULT_TYPE\\\":\\\"WBGWSD020603\\\",\\\"FAULT_TYPE_T\\\":\\\"缺损\\\",\\\"DETAIL_LOC\\\":\\\"item6,item7\\\",\\\"FAULT_QUANTITY\\\":\\\"\\\",\\\"FAULT_QUANTITY_T\\\":\\\"\\\",\\\"FAULT_UNIT\\\":\\\"1-1\\\",\\\"FAULT_UNIT_T\\\":\\\"米\\\",\\\"AFFECTE_EQUIPMENT\\\":\\\"\\\",\\\"FAULT_DESC\\\":\\\"\\\",\\\"IS_FINISH\\\":\\\"1\\\",\\\"IS_FINISH_T\\\":\\\"已修复\\\",\\\"REPAIR_DESC\\\":\\\"\\\",\\\"MATERIAL_CONSUMPTION\\\":\\\"\\\"}\",\"{\\\"INTERNAL_CODE\\\":\\\"35zkn9ace0\\\",\\\"LINE_ID\\\":\\\"3D530A1220204ED5B9087F8B854A7D6F\\\",\\\"HIGH_LOW\\\":\\\"U\\\",\\\"START_STA\\\":\\\"942985FE175C4975811C4C14E230875A\\\",\\\"END_STA\\\":\\\"248333CEE42E48749330726D08FDCC44\\\",\\\"START_STA_T\\\":\\\"鞍山新村站\\\",\\\"END_STA_T\\\":\\\"四平路站\\\",\\\"DEVICE_ID\\\":\\\"0808090103000000000001\\\",\\\"DEVICE_NAME\\\":\\\"鞍山新村至四平路上行隧道\\\",\\\"DUCT_CODE\\\":\\\"1\\\",\\\"DUCT_CODE2\\\":\\\"500\\\",\\\"STATION_KI_NUM\\\":\\\"10\\\",\\\"STATION_HUN_NUM\\\":\\\"500\\\",\\\"STATION_KI_NUM_END\\\":\\\"11\\\",\\\"STATION_HUN_NUM_END\\\":\\\"081\\\",\\\"DUCT_NUM\\\":\\\"499\\\",\\\"BIG_TYPE_ID\\\":\\\"BGWSD01\\\",\\\"BIG_TYPE_ID_T\\\":\\\"隧道\\\",\\\"SMALL_TYPE_ID\\\":\\\"WBGWSD0109\\\",\\\"SMALL_TYPE_ID_T\\\":\\\"疏散平台\\\",\\\"FAULT_LEVEL\\\":\\\"D\\\",\\\"FAULT_LEVEL_T\\\":\\\"D\\\",\\\"FAULT_TYPE\\\":\\\"WBGWSD010903\\\",\\\"FAULT_TYPE_T\\\":\\\"其他\\\",\\\"DETAIL_LOC\\\":\\\"\\\",\\\"FAULT_QUANTITY\\\":\\\"33\\\",\\\"FAULT_QUANTITY_T\\\":\\\"\\\",\\\"FAULT_UNIT\\\":\\\"1-1\\\",\\\"FAULT_UNIT_T\\\":\\\"米\\\",\\\"AFFECTE_EQUIPMENT\\\":\\\"\\\",\\\"FAULT_DESC\\\":\\\"sjsjsj\\\\nsnsns\\\",\\\"IS_FINISH\\\":\\\"1\\\",\\\"IS_FINISH_T\\\":\\\"已修复\\\",\\\"REPAIR_DESC\\\":\\\"dndndj\\\",\\\"MATERIAL_CONSUMPTION\\\":\\\"dbdndh\\\"}\"]";
            System.out.println(s);
            System.out.println(objects);
            ArrayList<String> list = Lists.newArrayList();*/
            //offline_faultList.forEach(fault ->{
            //    JSONArray array = (JSONArray) fault;
            //    List<Map> list1 = JSONObject.parseArray(array.toJSONString(), Map.class);
            //    System.out.println(map.get("INTERNAL_CODE").toString());
            //    list.add(map.get("INTERNAL_CODE").toString());
            //});
            //List<Map> faultList = JSONObject.parseArray(offline_faultList.toJSONString(), Map.class);
            //
            //
            //faultList.forEach(fault -> {
            //    Map map = (Map) fault;
            //    list.add(map.get("INTERNAL_CODE").toString());
            //});
            //todo 返回病害id
            return ResultGenerator.genSuccessResult(list);
        } catch (Exception e) {
            logger.error("上传失败!", e);
            return ResultGenerator.genFailResult("上传失败!");
        }
    }

    @PostMapping("/plan/oldUploadByPlan")
    @ApiOperation(value = "任务病害上传", notes = "手机端任务病害数据上传旧接口")
    public Result oldUploadByPlan(@RequestBody QueryRequest request, HttpSession session) {
        try {
            String userId = super.verifySession(session);
            //String dir = "D:\\log\\audio" + File.separator;
            //// 如果文件夹没有创建文件夹
            //File filesMin = new File(dir);
            //if (!filesMin.exists()) {
            //    filesMin.mkdirs();
            //}
            //String fileName = "aaa.mp4";
            //// 保存文件  fileName.substring(fileName.lastIndexOf("."))
            //String minFile = dir + File.separator + fileName;
            //文件上传
            //FileUtils.copyInputStreamToFile(file.getInputStream(), new File(minFile));
            //todo 返回任务id
            Map params = request.getParams();
            JSONArray planId = (JSONArray) params.get("planId");
            String uuid = planId.get(0).toString();
            return ResultGenerator.genSuccessResult(uuid);
        } catch (Exception e) {
            logger.error("上传失败!", e);
            return ResultGenerator.genFailResult("上传失败!");
        }
    }

}
