package com.company.project.core.util;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.exception.ServiceException;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
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

    public static void saveFile(List<String> error,String filepath)  {
        try{
            File file = new File(filepath);
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file,true);
            BufferedWriter bw = new BufferedWriter(fw);
            for(String str : error){
                bw.write(str+"\r\n");
            }
            bw.flush();
            bw.close();
            fw.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void saveStringFile(String toJSONString,String filepath) {
        try{
            File file = new File(filepath);
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file,true);
            BufferedWriter bw = new BufferedWriter(fw);
                bw.write(toJSONString+"\r\n");
            bw.flush();
            bw.close();
            fw.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
