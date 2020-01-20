package com.conpany.project.app;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.company.project.core.util.FileUtil;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Chen
 * @created 2019-11-01-11:18.
 */
public class Base64Test {
    @Test
    public void audioBase64() throws IOException {
        StringBuffer sb = new StringBuffer();
        try {
            FileReader fr =new FileReader("D:\\log\\par.json");
            BufferedReader br = new BufferedReader(fr);

            String str;
            while((str = br.readLine()) != null){
                sb.append(str);
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject obj = JSONObject.parseObject(sb.toString());
        JSONArray addList_img = obj.getJSONArray("addList_img");
        JSONObject jsonObject = addList_img.getJSONObject(0);
        String imgStr = String.valueOf(jsonObject.get("IMG"));

//        视频
//        JSONObject obj = JSONObject.parseObject(sb.toString());
//        JSONArray addList_img = obj.getJSONArray("addList_video");
//        JSONObject jsonObject = addList_img.getJSONObject(0);
//        String imgStr = String.valueOf(jsonObject.get("VIDEO"));

//        JSONArray addList_img = obj.getJSONArray("addList_audio");
//        JSONObject jsonObject = addList_img.getJSONObject(0);
//        String imgStr = String.valueOf(jsonObject.get("AUDIO"));
//        String imgStr = String.valueOf(jsonObject.get("IMG"));
        //String s = imgStr.replaceAll(" +","+");
        //videoStr.replace(",", "+");
        //InputStream audioIs = new ByteArrayInputStream(Base64.getDecoder().decode(videoStr.getBytes(Charsets.UTF_8)));
        //InputStream imgIs = new ByteArrayInputStream(Base64.getDecoder().decode(imgStr.getBytes(Charsets.UTF_8)));

        String property = System.getProperty("user.dir");

        //FileUtils.copyInputStreamToFile(audioIs, new File(property + "/file/mp4/audio_" + new Date() + ".mp4"));
        //FileUtils.copyInputStreamToFile(imgIs, new File(property + "/file/img/image_" + new Date() + ".jpeg"));
        //if (audioIs != null) {
        //    audioIs.close();
        //}

        // byte[] bytes = ImageBase64Utils.convertBase64ImgStrToBytes(s);
        // FileOutputStream fos = new FileOutputStream(property + "/file/img/image_" + new Date() + ".jpg");
        //fos.write(bytes);
        //fos.flush();
        //fos.close();
        imgStr = imgStr.substring(imgStr.indexOf(',') + 1);
        ImageUtils.base64ToImg("src/test/resources/video/", imgStr, "video_img.jpg");
        //videoStr = videoStr.substring(videoStr.indexOf(',') + 1);
        //ImageUtils.base64ToImg(property + "/file/video/", videoStr, "video_1.mp4");
    }
}
