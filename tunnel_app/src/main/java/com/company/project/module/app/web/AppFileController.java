package com.company.project.module.app.web;


import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/module/app/file/")
@Api(description = "手机端上传下载接口")
public class AppFileController {

    @PostMapping("upload")
    @ApiOperation(value = "文件上传", notes = "手机端文件上传")
    public Result Fileupload(@RequestParam("files") MultipartFile file, String type) {
        String fileName;
        try {
            String dir = "D:\\log\\" + type + File.separator;
            // 如果文件夹没有创建文件夹
            File filesMin = new File(dir);
            if (!filesMin.exists()) {
                filesMin.mkdirs();
            }
            fileName = file.getOriginalFilename();
            // 保存文件  fileName.substring(fileName.lastIndexOf("."))
            String minFile = dir + File.separator + fileName;
            //文件上传
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(minFile));
            return ResultGenerator.genSuccessResult("上传成功！");
        } catch (Exception e) {
            return ResultGenerator.genFailResult("上传失败!");
        }
    }

    @GetMapping(value = "downfile")
    @ApiOperation(value = "文件下载", notes = "手机端测试文件下载")
    public void downFile(@RequestParam String url, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, MalformedURLException {

        //String url = "D:\\log\\1\\123.gif";
        String suffer = url.substring(url.lastIndexOf("\\" + 1));
        String fileName = suffer;
        System.out.println(url);
        response.reset();
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        response.setHeader("Content-Type", "application/octet-stream");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/OCTET-STREAM");
        response.setHeader("Connection", "close");
        try {
            FileInputStream files = new FileInputStream(url);
            InputStream fis = files;

            BufferedInputStream bis = null;
            try {
                OutputStream out = response.getOutputStream();
                request.setCharacterEncoding("UTF-8");
                int BUFFER = 1024 * 10;
                byte data[] = new byte[BUFFER];
                //获取文件输入流
//                fis = conn.getInputStream();
                int read;
                bis = new BufferedInputStream(fis, BUFFER);
                while ((read = bis.read(data)) != -1) {
                    out.write(data, 0, read);
                }
            } catch (IOException e) {
                System.out.println("文件下载异常" + fileName + e.fillInStackTrace());
            } finally {
                fis.close();
                bis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("抛出异常！！");
        }
    }

    @PostMapping(value = "base64Upload")
    @ApiOperation(value = "文件上传", notes = "字符串base64文件上传")
    public Result base64Upload(@RequestBody Map base64ImgData) {
        try {
            String dir = "D:\\log\\" + File.separator;
            // 如果文件夹没有创建文件夹
            System.out.println(base64ImgData);
            return ResultGenerator.genSuccessResult("上传成功！");
        } catch (Exception e) {
            return ResultGenerator.genFailResult("上传失败!");
        }
    }
}
