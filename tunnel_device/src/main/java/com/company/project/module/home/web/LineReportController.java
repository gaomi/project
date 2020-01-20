package com.company.project.module.home.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.controller.BaseController;
import com.company.project.core.model.QueryRequest;
import com.company.project.module.home.service.LineReportService;
import com.company.project.module.home.service.VisualService;
import com.company.project.module.home.util.ExportWordUtile;
import com.deepoove.poi.XWPFTemplate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * @author Chen
 * @created 2019-11-27-18:42.
 */
@RestController
@RequestMapping("/module/report")
@Api(description = "统计报表")
public class LineReportController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LineReportService lineReportService;
    @Autowired
    private VisualService visualService;
    @Resource
    private ExportWordUtile exportWordUtile;


    @PostMapping("/getFaultAnalyze")
    @ApiOperation(value = "获取病害统计", notes = "获取（周、月、年）病害统计")
    public Result getFaultAnalyze(@RequestBody QueryRequest request) {
        Map data = lineReportService.getDiseDesc(request.getParams());
        return ResultGenerator.genSuccessResult(data);
    }

    @GetMapping("/healthMarck")
    @ApiOperation(value = "健康度数据", notes = "获取线路健康度数据")
    public Result healthMarck() {
        try {
            List<Map> result = visualService.getHealthMarck();
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            logger.error("获取健康度数据失败", e);
            return ResultGenerator.genFailResult("获取健康度数据失败！");
        }
    }

    @PostMapping("/exportWord")
    @ApiOperation(value = "导出周报报表", notes = "导出周报报表")
    public void exportWord(@RequestBody QueryRequest params, HttpServletRequest request, HttpServletResponse response) throws Exception {
        XWPFTemplate xwpfTemplate = exportWordUtile.creatWeekReport(params.getParams());
        response.reset();
        response.setHeader("Content-Disposition", "attachment");
        response.setHeader("Connection", "close");
        response.setHeader("Content-Type", "application/octet-stream");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/OCTET-STREAM");
        try {
            ByteArrayOutputStream bOut = new ByteArrayOutputStream();
            xwpfTemplate.write(bOut);
            xwpfTemplate.close();
            ByteArrayInputStream swapStream = new ByteArrayInputStream(bOut.toByteArray());

            BufferedInputStream bis = null;
            try {
                OutputStream out = response.getOutputStream();
                request.setCharacterEncoding("UTF-8");
                int BUFFER = 1024 * 10;
                byte data[] = new byte[BUFFER];
                //获取文件输入流
//                fis = conn.getInputStream();
                int read;
                bis = new BufferedInputStream(swapStream, BUFFER);
                while ((read = bis.read(data)) != -1) {
                    out.write(data, 0, read);
                }
            } catch (IOException e) {
                System.out.println("文件下载异常" + e.fillInStackTrace());
            } finally {
                swapStream.close();
                bis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("抛出异常！！");
        }
    }

}
