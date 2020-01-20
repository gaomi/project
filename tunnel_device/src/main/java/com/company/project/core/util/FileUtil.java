package com.company.project.core.util;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.exception.ServiceException;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class FileUtil {

    private static Logger log = LoggerFactory.getLogger(FileUtil.class);
    @Value("${exportFile.path}")
    private static String exportPath;

    private FileUtil() {

    }

    /**
     * 文件名加UUID
     *
     * @param filename 文件名
     * @return UUID_文件名
     */
    private static String makeFileName(String filename) {
        return UUID.randomUUID().toString() + "_" + filename;
    }

    /**
     * 文件名特殊字符过滤
     *
     * @param fileName 文件名
     * @return 过滤后的文件名
     * @throws PatternSyntaxException 正则异常
     */
    public static String stringFilter(String fileName) {
        String regEx = "[`~!@#$%^&*+=|{}':; ',//[//]<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(fileName);
        return m.replaceAll("").trim();
    }

    /**
     * 生成Excel文件
     *
     * @param filename 文件名称
     * @param list     文件内容List
     * @param clazz    List中的对象类型
     * @return ResponseBo
     */
    public static Result createExcelByPOIKit(String filename, List<?> list, Class<?> clazz) {

        if (list.isEmpty()) {
            return ResultGenerator.genFailResult("导出数据为空！");
        } else {
            boolean operateSign = false;
            String fileName = filename + ".xlsx";
            fileName = makeFileName(fileName);
            try {
                File fileDir = new File(exportPath);
                if (!fileDir.exists())
                    fileDir.mkdir();
                String path = "file/" + fileName;
                FileOutputStream fos;
                fos = new FileOutputStream(path);
                operateSign = ExcelUtils.builder(clazz)
                    // 设置每个sheet的最大记录数,默认为10000,可不设置
                    // .setMaxSheetRecords(10000)
                    .toExcel(list, "查询结果", fos);
            } catch (FileNotFoundException e) {
                log.error("文件未找到", e);
            }
            if (operateSign) {
                return ResultGenerator.genSuccessResult(fileName);
            } else {
                return ResultGenerator.genFailResult("导出Excel失败！");
            }
        }
    }

    /**
     * 生成Csv文件
     *
     * @param filename 文件名称
     * @param list     文件内容List
     * @param clazz    List中的对象类型
     * @return ResponseBo
     */
    public static Result createCsv(String filename, List<?> list, Class<?> clazz) {

        if (list.isEmpty()) {
            return ResultGenerator.genFailResult("导出数据为空！");
        } else {
            boolean operateSign;
            String fileName = filename + ".csv";
            fileName = makeFileName(fileName);

            File fileDir = new File(exportPath);
            if (!fileDir.exists())
                fileDir.mkdir();
            String path = "file/" + fileName;
            operateSign = ExcelUtils.builder(clazz)
                .toCsv(list, path);
            if (operateSign) {
                return ResultGenerator.genSuccessResult(fileName);
            } else {
                return ResultGenerator.genFailResult("导出Csv失败！");
            }
        }
    }


    /**
     * 获取本地测试文件
     *
     * @return
     */
    public static String getDataByLocal(String fileName) {
        ClassPathResource resource = new ClassPathResource(fileName);
        File filePath = null;
        String input = null;
        try {
            filePath = resource.getFile();
            input = FileUtils.readFileToString(filePath, "UTF-8");
            return input;
        } catch (IOException e) {
            throw new ServiceException("文件获取失败！", e);
        }
    }

    /**
     * 获取根目录测试文件
     *
     * @return
     */
    public static String getRootDataByLocal(String fileName) {
        File filePath = null;
        String input = null;
        try {
            String property = System.getProperty("user.dir");
            String absolutePath = new File("/").getAbsolutePath();
            System.out.println(absolutePath);
            property.replaceAll("bin", "webapps/");
            filePath = new File(property + "/src/main/webapp" + fileName);
            input = FileUtils.readFileToString(filePath, "UTF-8");
            return input;
        } catch (IOException e) {
            throw new ServiceException("文件获取失败！", e);
        }
    }

    public static void main(String[] args) {
        String result = FileUtil.getRootDataByLocal("/static/js/mock/eamData.json");
        System.out.println(result);
    }
}
