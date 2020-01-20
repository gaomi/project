package com.company.project.module.home.util;

import com.alibaba.fastjson.JSONObject;
import com.company.project.core.util.FileUtil;
import com.company.project.module.api.enums.JhjcEnum;
import com.company.project.module.data.service.TdCjDataService;
import com.company.project.module.data.service.TdSegmentService;
import com.company.project.module.data.service.TdSlDataService;
import com.company.project.module.home.util.model.MergeEnum;
import com.company.project.module.home.util.model.TableMerge;
import com.company.project.module.home.util.poilcy.MyRenderPolicy;
import com.company.project.module.segment.service.SegmentService;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.data.*;
import com.deepoove.poi.data.style.Style;
import com.deepoove.poi.data.style.TableStyle;
import com.deepoove.poi.util.BytePictureUtils;
import com.google.common.collect.Maps;
import org.apache.commons.compress.utils.Lists;
import org.jfree.chart.ChartPanel;
import org.jfree.data.xy.XYDataset;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class ExportWordUtile {
    @Autowired
    private SegmentService tabService;
    @Autowired
    private TdSlDataService tdSlDataService;
    @Autowired
    private TdCjDataService metroCjService;
    @Value("${fault-image.path}")
    private String ServicePath;

    private  Logger logger = LoggerFactory.getLogger(FileUtil.class);

    Map<String, Object> datas = new HashMap<String, Object>();
    private String faultTitle = "%s、关于%s号线%s区间%s%s环%s的情况说明";
    private String oneDesc = "%s凌晨巡检发现，%s号线%s区间%s%s环%s病害，见图1，根据标准定义为%s级病害。巡检人员当即上报调度要求进行相关处置。";
    private String twoDesc = "根据%s年长期沉降报告显示，以该位置为中心外扩50米范围本次沉降最大%smm,最小%smm，累计最大%smm，最小%smm,无明显差异沉降，见图2、3。根据%s年收敛数据，%s环前后5个断面本次变量在%smm,见图4、5，与设计比值最大为%scm,%s超标。";

    Style headTextStyle = new Style();
    TableStyle headStyle = new TableStyle();
    TableStyle rowStyle = new TableStyle();
    Style tableTextStyle = new Style();

    public static void main(String[] args) throws Exception {
        String param ="{\"statis\":[{\"name\":\"AA合计\",\"add\":0,\"Delete\":0,\"All\":1,\"line\":\"网络剩余\"},{\"name\":\"A合计\",\"add\":0,\"Delete\":0,\"All\":1258,\"line\":\"网络剩余\"},{\"name\":\"B合计\",\"add\":0,\"Delete\":0,\"All\":2915,\"line\":\"网络剩余\"},{\"name\":\"C合计\",\"add\":0,\"Delete\":0,\"All\":78,\"line\":\"网络剩余\"},{\"name\":\"D合计\",\"add\":0,\"Delete\":0,\"All\":0,\"line\":\"网络剩余\"},{\"name\":\"以上合计\",\"add\":0,\"Delete\":0,\"All\":4252,\"line\":\"网络剩余\"}],\"weekAdd\":[{\"LINE_ID\":\"07\",\"START_STATIONNAME\":\"肇嘉浜路\",\"END_STATIONNAME\":\"常熟路\",\"UPDOWN\":\"下行\",\"DUCT_CODE\":\"1266\",\"DUCT_CODE2\":\"1267\",\"FAULT_TYPE\":\"渗泥沙\",\"FAULT_LEVEL\":\"AA\"}],\"weekDelete\":[],\"weekAll\":[]}";
        Map params = JSONObject.parseObject(param);
        new ExportWordUtile().creatWeekReport(params);
    }

    /***
     * 创建周报Word模板
     * @param params
     * @return
     * @throws Exception
     */
    public XWPFTemplate creatWeekReport(Map params) throws Exception {
        datas.put("weekTime", params.get("showTime"));
        //1.1
        initTable(datas, (List) params.get("statis"));
        //1.2
        initDescTable(datas,params);
        //1.3
        List FaultList = (List) params.get("weekAdd");
        if(FaultList.size() > 0){
            List<Map> faultSegments = getFaultDesc(FaultList);
            DocxRenderData faultSegment = new DocxRenderData(new File(this.getClass().getResource("/model/faultSegment.docx").getPath()), faultSegments);
            datas.put("faultSegment", faultSegment);
        }

        Configure configure = Configure.newBuilder().build();
        configure.plugin('#',new MyRenderPolicy());
         XWPFTemplate template = XWPFTemplate.compile(this.getClass().getResource("/model/model.docx").getPath(),configure).render(datas);
//        FileOutputStream out = new FileOutputStream("src/main/resources/temp/model.docx");
//        template.write(out);
//        out.flush();
//        out.close();
//        template.close();
        return template;
    }

    /***
     * 初始化病害详情
     * @param weekAdd
     * @return
     */
    private List<Map> getFaultDesc(List<Map> weekAdd) {
        List<Map> faultSegments = Lists.newArrayList();
            for(Map fault : weekAdd){
                Map<String,Object> params=getCharPatam(fault);
                Map item = Maps.newHashMap();
                item.put("oneDesc",String.format(oneDesc,(String)params.get("CREATE_TIME"),fault.get("LINE_ID"),fault.get("LINE_ID")+"-"+fault.get("LINE_ID"),fault.get("UPDOWN"),fault.get("DUCT_CODE")+"-"+fault.get("DUCT_CODE2"),fault.get("SMALL_TYPE_NAME")+""+fault.get("FAULT_TYPE"),fault.get("FAULT_LEVEL")+""));
                item.put("twoDesc",String.format(twoDesc,(String)params.get("CREATE_TIME"),((Map)params.get(JhjcEnum.CJN.getName())).get("max")+"",((Map)params.get(JhjcEnum.CJN.getName())).get("min")+"",((Map)params.get(JhjcEnum.CJS.getName())).get("max")+"",((Map)params.get(JhjcEnum.CJS.getName())).get("min")+"",((Map)params.get(JhjcEnum.SLY.getName())).get("show_key")+"",fault.get("DUCT_CODE")+"-"+fault.get("DUCT_CODE2"),((Map)params.get(JhjcEnum.SLY.getName())).get("max")+"-"+((Map)params.get(JhjcEnum.SLY.getName())).get("min"),((Map)params.get(JhjcEnum.SLC.getName())).get("max"+""),"均未"));
                item.put("threeDesc","对应位置范围内地面暂未发现违规施工，查阅监护项目台账暂无监护项目。");
                item.put("fourDesc","以该AA级病害为中心外扩50米范围，查看病害信息，发未发现周边管片");
                item.put("fiveDesc","建议重点关注范围内管片渗泥沙病害发展，适时进行处置。");
                String url = ServicePath+fault.get("LINE_ID")+File.separator+fault.get("IMAGE_URL");
                try{
                    item.put("picture",new PictureRenderData(100, 100, ".png", BytePictureUtils
                            .getUrlBufferedImage(url)));
                }catch (Exception e){
                    logger.info("获取病害图片失败");
                }

                BufferedImage nowCj = (BufferedImage)(((Map)params.get(JhjcEnum.CJN.getName())).get("BufferImage"));
                item.put("nowCj",new PictureRenderData(500, 170, ".png", nowCj));
                BufferedImage accDj = (BufferedImage)(((Map)params.get(JhjcEnum.CJS.getName())).get("BufferImage"));
                item.put("accCj",new PictureRenderData(500, 170, ".png",accDj));
                BufferedImage nowSl = (BufferedImage)(((Map)params.get(JhjcEnum.SLY.getName())).get("BufferImage"));
                item.put("nowSl",new PictureRenderData(500, 170, ".png", nowSl));
                BufferedImage differ = (BufferedImage)(((Map)params.get(JhjcEnum.SLC.getName())).get("BufferImage"));
                item.put("differ",new PictureRenderData(500, 170, ".png", differ));
                faultSegments.add(item);
            }
        return faultSegments;
    }

    /***
     * 获取画图必备参数（如沉降收敛数值）
     * @param fault
     * @return
     */
    public Map getCharPatam(Map fault){
        Map result = Maps.newHashMap();
        Map  charTime = Maps.newHashMap();
        try{
            SimpleDateFormat sdf  =  new   SimpleDateFormat("yyyyMMddhhmmss");
            String create_time = new  SimpleDateFormat("yyyy年MM月dd日").format(sdf.parse((String)fault.get("REC_CREATE_TIME")));
            //获取参数用到的Time格式
            result.put("CREATE_TIME",create_time);

            Map<String,Object> params =Maps.newHashMap();
            params.put("isDensity","0");

            Double start = new BigDecimal((String)fault.get("STATION_KI_NUM")+(String)fault.get("STATION_HUN_NUM")).doubleValue()-50;
            Double end = new BigDecimal((String)fault.get("STATION_KI_NUM_END")+(String)fault.get("STATION_HUN_NUM_END")).doubleValue()+50;

            String lineCode = (String)fault.get("LINE_ID");
            if(lineCode.startsWith("0")){
                lineCode = lineCode.substring(1);
            }
            String updown ="1";
            if(((String)fault.get("DIRECTION")).equals("U")){
                updown="0";
            }
            params.put("group_segment",Arrays.asList(fault.get("SEGMENT_UUID").toString()));
            params.put("line",lineCode);
            params.put("segment",fault.get("NO_GROUP_UUID"));
            params.put("updown",updown);
            params.put("start_mileage",fault.get("START_MILEAGE"));
            params.put("end_mileage",fault.get("END_MILEAGE"));
            params.put("segment_name",fault.get("START_STATIONNAME")+"~"+fault.get("END_STATIONNAME"));

            Map<String,Map> charList = getQuerTime((String)fault.get("REC_CREATE_TIME"),params);
            Map resultMap = tabService.getSLInitInfo(params);
            Map<String,List> xdata = (Map)((Map)resultMap.get("monitor")).get("xdate");
            List<List> ptdList = (List)((Map)resultMap.get("monitor")).get("CjPtd");
            List<List> cdata = xdata.get("xdateTwo");
            List<List> sdata = xdata.get("xdateOne");
            Map charMap = Maps.newHashMap();
            charMap.put("start",start);
            charMap.put("end",end);
            charMap.put("ptdList",ptdList);

            for(String key : charList.keySet()){
                Map resu_map =Maps.newHashMap();
                try{
                    Map param = (Map)charList.get(key);
                    params.putAll(param);
                    params.put("type",key);
                    Map dataVale= (Map)tabService.getSlByName(params);
                    List<BigDecimal> allvalue = (List<BigDecimal>)dataVale.get("data");

                    if(key.contains(JhjcEnum.CJ.getName())){
                        charMap.put("xdata",cdata);
                        charMap.put("type",key);
                    }else if(key.contains(JhjcEnum.SL.getName())){
                        charMap.put("xdata",sdata);
                        charMap.put("type",key);
                    }
                    charMap.put("allvalue",allvalue);
                    resu_map = getCSChar(param.get("title")+"",param.get("xAxis")+"",param.get("yAxis")+"",charMap);
                    resu_map.put("show_key",param.get("show_key"));
                    result.put(key,resu_map);

                }catch (Exception e){
                    e.printStackTrace();
                    System.out.println(JSONObject.toJSONString(resu_map));
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("起始里程错误");
        }
        return result;
    }

    /***
     * 调用JfreeChar 画图
     * @param title
     * @param yTitle
     * @param xTitle
     * @param params
     * @return
     * @throws Exception
     */
    public Map getCSChar(String title,String yTitle,String xTitle,Map params) throws Exception{
        Map result = Maps.newHashMap();
        Double start =(Double)params.get("start");
        Double end = (Double)params.get("end");
        List<BigDecimal> allvalue = (List<BigDecimal>)params.get("allvalue");
        List<List> ptdList = (List<List>)params.get("ptdList");
        List<List> cdata = (List<List>)params.get("xdata");
        String legend = "沉降量";
        int index=1;
        if(((String)params.get("type")).contains(JhjcEnum.SL.getName())){ index = 0;legend="收敛量"; }
        Double offset =0d;
        Map<Double,Double> all = Maps.newHashMap();
        Map<Double,Double> segment = Maps.newHashMap();
        for(int i = 0; i < cdata.size();i++){

            try{
                Double value = allvalue.get(i).doubleValue();
                if(i == 0){
                    result.put("max",value);
                    result.put("min",value);
                }
                all.put(((BigDecimal)cdata.get(i).get(index)).doubleValue(),value);
                if(((BigDecimal)cdata.get(i).get(1)).doubleValue() >= start && ((BigDecimal)cdata.get(i).get(1)).doubleValue() <=end){
                    segment.put(((BigDecimal)cdata.get(i).get(index)).doubleValue(),value);
                }
                if((Double)result.get("max") < value){
                    result.put("max",value);
                }
                if((Double)result.get("min") > value){
                    result.put("min",value);
                }
            }catch (Exception e){
                System.out.println("===>"+allvalue.get(i));

            }

        }
        Map<Double,List> item_ptd = Maps.newHashMap();
        if(((String)params.get("type")).contains(JhjcEnum.SL.getName())){
            offset = ((Double)result.get("max")-(Double)result.get("min"))/5;
            item_ptd=null;
        }else{
            for(List item : ptdList){
                offset = ((Double)result.get("max")-(Double)result.get("min"))/5;
                item_ptd.put(((BigDecimal)item.get(1)).doubleValue(),Arrays.asList((Double)result.get("min")-offset,(Double)result.get("max")+offset));
            }
        }

        Map datas = CreatXYLineChart.createXYDataset(legend,all,segment,item_ptd);
        XYDataset sataset = (XYDataset)datas.get("dataset");
        Map<Integer,List> param = (Map<Integer,List>)datas.get("param");
        ChartPanel chartPanel = new CreatXYLineChart().createChart(title, xTitle, yTitle,sataset,param,(Double)result.get("min")-offset,(Double)result.get("max")+offset);
        //将图片保存为png格式
        result.put("BufferImage",chartPanel.getChart().createBufferedImage(800, 350, null));
//        System.out.println((String)params.get("type")+"最值："+((Double)result.get("max")+offset)+"最值"+((Double)result.get("min")-offset));
//        System.out.println((String)params.get("type")+"最大值："+((Double)result.get("max"))+"最小值"+((Double)result.get("min")));
        return result;
    }



    /***
     * 根据病害的创建时间获取时间
     * @param creatTime
     * @param param
     * @return
     * @throws Exception
     */
    public Map getQuerTime(String creatTime,Map param) throws Exception{
        String CJTiele = "地铁"+param.get("line")+"号线"+param.get("updown")+"线%s沉降量曲线图（"+param.get("segment_name")+"）";
        String SLTiele = "%s年度"+param.get("line")+"号线"+param.get("segment_name")+param.get("updown")+"线%s曲线图";
//       creatTime= "REC_CREATE_TIME":"20180504010837"
        Map result = Maps.newHashMap();
        SimpleDateFormat sdf  =  new   SimpleDateFormat("yyyyMMddhhmmss");
        SimpleDateFormat  cjsdf  =  new   SimpleDateFormat("yyyy-MM");
        SimpleDateFormat  slsdf  =  new   SimpleDateFormat("yyyy");
        long thisTime=sdf.parse(creatTime).getTime();
        List<Map> sl_legend =tdSlDataService.getLegend("0");
        List<Map> cj_legend = metroCjService.getCjDate(param);
        Map itemMap = Maps.newHashMap();
        for(Map item : cj_legend){
            String beside =(String)item.get("SHOWKEY");
            long start = cjsdf.parse(beside).getTime();
            long offset =  thisTime-start;
            if( offset > 0){
                if(itemMap.get("CJ_BESIDE")==null){
                    itemMap.put("CJ_BESIDE",offset);
                    itemMap.put("CJ_SHOW",beside);
                    itemMap.put("CJ_THIS",(String)item.get("CJN"));
                }else if (offset < (long)itemMap.get("CJ_BESIDE")){
                    itemMap.put("CJ_BESIDE",offset);
                    itemMap.put("CJ_SHOW",beside);
                    itemMap.put("CJ_THIS",(String)item.get("CJN"));
                }
            }
            if(itemMap.get("CJ_MIN") == null){
                itemMap.put("CJ_MIN",start);
            }else if(start < (long)itemMap.get("CJ_MIN")){
                itemMap.put("CJ_MIN",start);
                itemMap.put("CJ_INIT",beside);
            }

        }
        for(Map item : sl_legend){
            String beside =(String)item.get("SL_YEAR");
            long start = slsdf.parse(beside).getTime();
            long offset =  thisTime-start;
            if( offset > 0){
                if(itemMap.get("SL_BESIDE")==null){
                    itemMap.put("SL_BESIDE",offset);
                    itemMap.put("SL_SHOW",beside);
                    itemMap.put("SL_THIS",(String)item.get("YEARN"));
                }else if (offset < (long)itemMap.get("SL_BESIDE")){
                    itemMap.put("SL_BESIDE",offset);
                    itemMap.put("SL_SHOW",beside);
                    itemMap.put("SL_THIS",(String)item.get("YEARN"));
                }
            }
        }
        result.put(JhjcEnum.CJS.getName(),new HashMap(){{
            put("init_date",itemMap.get("CJ_INIT"));
            put("show_key",itemMap.get("CJ_SHOW"));
            put("title",String.format(CJTiele,"累计"));
            put("xAxis","沉降量（mm）");
            put("yAxis","里程（m）");

        }});
        result.put(JhjcEnum.CJN.getName(),new HashMap(){{
            put("init_date",itemMap.get("CJ_THIS"));
            put("show_key",itemMap.get("CJ_SHOW"));
            put("title",String.format(CJTiele,"本次"));
            put("xAxis","沉降量（mm）");
            put("yAxis","里程（m）");
        }});
        result.put(JhjcEnum.SLY.getName(),new HashMap(){{
            put("init_date",itemMap.get("SL_THIS"));
            put("show_key",itemMap.get("SL_SHOW"));
            put("title",String.format(SLTiele,itemMap.get("SL_THIS")+"","本次变化值"));
            put("xAxis","本次变化量（mm）");
            put("yAxis","环号（m）");
        }});
        result.put(JhjcEnum.SLC.getName(),new HashMap(){{
            put("init_date",itemMap.get("SL_THIS"));
            put("show_key",itemMap.get("SL_SHOW"));
            put("title",String.format(SLTiele,itemMap.get("SL_THIS")+"","与设计差"));
            put("xAxis","与设计差值（mm）");
            put("yAxis","环号（m）");
        }});
        return result;
    }

    /***
     * 本周新增，本周消除，本周结存模块
     * @param datas
     * @param params
     */
    private void initDescTable(Map<String, Object> datas, Map params) {
        List<Map> tableSegments = new ArrayList<Map>();
        List<String> tableName = Arrays.asList("weekAdd", "weekDelete", "weekAll");
        List<String> tableTiele = Arrays.asList("1.3.1 本周新增：", "1.3.2 本周消除：", "1.3.3 本周结存：");
        int index = 0;
        for (String item : tableName) {
            Map tableSegment = Maps.newHashMap();
            List<String> places1 = Arrays.asList("线路", "区间/上下行", "病害环号/里程", "病害类型", "病害等级");
            List<Map<String, String>> faultList = (List<Map<String, String>>) params.get(item);
            List<String[]> rowItem = Lists.newArrayList();
            for (Map<String, String> itemMap : faultList) {
                rowItem.add(new String[]{
                        itemMap.get("LINE_ID"),
                        itemMap.get("START_STATIONNAME")+"-"+itemMap.get("END_STATIONNAME")+"/"+itemMap.get("UPDOWN"),
                        itemMap.get("DUCT_CODE")+"-"+itemMap.get("DUCT_CODE2"),
                        itemMap.get("FAULT_TYPE"),
                        itemMap.get("FAULT_LEVEL")
                });
            }
            tableSegment.put("titleCode",tableTiele.get(index));
            tableSegment.put("faultTable", initRowRender(places1, rowItem, null));
            tableSegments.add(tableSegment);
            index++;
        }
        DocxRenderData tabSegment = new DocxRenderData(new File(this.getClass().getResource("/model/tableSegment.docx").getPath()), tableSegments);
        datas.put("tableSegment", tabSegment);


    }

    /****
     * 初始化本周相关表格
     * @param datas
     * @param statis
     */
    private void initTable(Map<String, Object> datas, List<Map<String, Object>> statis) {
        List<String> places = Arrays.asList("线路", "等级", "本周新增数", "本周消除数", "本周结存数");
        List<String[]> arr = Lists.newArrayList();
        for (Map<String, Object> item : statis) {
            String[] itemArr = new String[]{(String)item.get("line"), (String)item.get("name"), item.get("add")+"", item.get("Delete")+"", item.get("All")+""};
            arr.add(itemArr);
        }
        datas.put("statis", initRowRender(places, arr, new TableMerge(MergeEnum.VERTICALLY.getCode(), Arrays.asList(0))));

        //表格头部合并
//            TableMerge itemMerge = new TableMerge(MergeEnum.VERTICALLY.getCode(),Arrays.asList(0));
//            TableMerge tableMerge =new TableMerge(MergeEnum.HORIZONAL.getCode(),null);
//            List<String> placetest = Arrays.asList("线路", "等级", "本周新增数","本周消除数","本周结存数");
//            tableMerge.setHeader(initRow(placetest));
//            tableMerge.setItemMerge(itemMerge);
//            datas.put("statis",initRowRender(places,arr,tableMerge));
    }

    /***
     * 生成表格方法
     * @param headerRow
     * @param rows
     * @param merge
     * @return
     */
    public MiniTableRenderData initRowRender(List<String> headerRow, List<String[]> rows, TableMerge merge) {
        headTextStyle.setFontFamily("Hei");
        headTextStyle.setFontSize(10);

        headTextStyle.setColor("7F7F7F");
        headStyle.setBackgroundColor("F2F2F2");
        headStyle.setAlign(STJc.CENTER);

        rowStyle = new TableStyle();
        rowStyle.setAlign(STJc.CENTER);
        tableTextStyle.setFontSize(12);
        TextRenderData[] textArray = new TextRenderData[headerRow.size()];
        for (int i = 0; i < headerRow.size(); i++) {
            textArray[i] = new TextRenderData(headerRow.get(i), headTextStyle);
        }
        RowRenderData header = RowRenderData.build(textArray);
        header.setRowStyle(headStyle);
        List<RowRenderData> labors = new ArrayList<RowRenderData>();
        for (String[] item : rows) {
            RowRenderData arr = RowRenderData.build(item);
            arr.setRowStyle(rowStyle);
            labors.add(arr);
        }
        MiniTableRenderData miniTableRenderData =new MiniTableRenderData(header, "无对应内容");
        if(rows.size() > 0){
            miniTableRenderData = new MiniTableRenderData(header, labors, JSONObject.toJSONString(merge), MiniTableRenderData.WIDTH_A4_MEDIUM_FULL);
            miniTableRenderData.setStyle(headStyle);
        }
        return miniTableRenderData;
    }


}
