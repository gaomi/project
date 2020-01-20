package com.company.project.core.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONReader;
import com.alibaba.fastjson.TypeReference;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.util.redis.RedisUtil;
import com.company.project.module.api.model.AqbhqProject;
import com.company.project.module.api.model.TokenModel;
import com.company.project.module.data.model.TdDict;
import com.company.project.module.data.service.TdDictService;
import com.fasterxml.jackson.core.JsonParser;
import com.google.common.collect.Maps;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.jsoup.nodes.Document.OutputSettings.Syntax.html;

@Component
public class HttpClientUtil {

    private static Logger log = LoggerFactory.getLogger(HttpClientUtil.class);

    @Resource
    private RedisUtil redisUtil;
    @Autowired
    private TdDictService tdDictService;

    public Result get(String url, String param) {
        GetMethod contentMethod = null;
        HttpClient hc = new HttpClient(new HttpClientParams(), new SimpleHttpConnectionManager());
        hc.getHttpConnectionManager().getParams().setConnectionTimeout(600000);
        hc.getHttpConnectionManager().getParams().setSoTimeout(600000);
        if (!StringUtils.isEmpty(url)) {
            //链接
            contentMethod = new GetMethod(url + param);
            //添加请求头
//            contentMethod.addRequestHeader("Content-Type","application/json;charset=UTF-8");
            try {
                int statusCode1 = hc.executeMethod(contentMethod);
                byte[] responseBody = contentMethod.getResponseBody();
                if (HttpStatus.SC_OK == statusCode1) {
//                    JSONArray resultList = JSONObject.parseArray(html);
                    return ResultGenerator.genSuccessResult(responseBody);
                } else {
                    return ResultGenerator.genFailResult("失败状态码：" + statusCode1);
                }
            } catch (Exception e) {
                log.error("请求失败");
            } finally {
                contentMethod.releaseConnection();
                hc.getHttpConnectionManager().closeIdleConnections(0);
            }
        } else {
            log.error("url错误");
        }
        return ResultGenerator.genFailResult("请求失败");
    }

    /**
     * @param url url
     * @return
     */
    public Result get(String url) {

        return get(url, "");
    }

    /**
     * @param url url
     * @param map url中的参数
     * @return
     */
    public Result get(String url, Map<String, String> map) {

        String param = "?";
        try {
            for (Map.Entry<String, String> entry : map.entrySet()) {
//                String value =entry.getValue().replaceAll(" ","%20");
                if (!StringUtils.isEmpty(entry.getValue())) {
                    param += entry.getKey() + "=" + URLEncoder.encode(entry.getValue(), "UTF-8") + "&";
                }
            }
        } catch (Exception e) {
            return ResultGenerator.genFailResult("参数异常");
        }

        param = param.substring(0, param.lastIndexOf('&'));

        return get(url, param);
    }

    /**
     * @param url url
     * @return
     */
    public Result post(String url, String token) {
        return post(url, null, token, null);
    }

    /**
     * @param url   url
     * @param param body参数
     * @return
     */
    public Result postToken(String url, String param) {
        return post(url, null, null, param);
    }


    /**
     * @param url   url
     * @param map   url中的参数
     * @param token token
     * @return
     */
    public Result post(String url, Map<String, String> map, String token) {
        return post(url, map, token, null);
    }

    /**
     * @param url   url
     * @param map   url中的参数
     * @param param body参数
     * @return
     */
    public Result post(String url, Map<String, String> map, String TOKEN_CODE, String param) {
        String urlparam = "";
        if (map != null && map.size() > 0) {
            urlparam = "?";
            try {
                for (Map.Entry<String, String> entry : map.entrySet()) {
//                String value =entry.getValue().replaceAll(" ","%20");
                    if (!StringUtils.isEmpty(entry.getValue())) {
                        urlparam += entry.getKey() + "=" + URLEncoder.encode(entry.getValue(), "UTF-8") + "&";
                    }
                }
            } catch (Exception e) {
                return ResultGenerator.genFailResult("参数异常");
            }
            urlparam = urlparam.substring(0, urlparam.lastIndexOf('&'));
        }
        PostMethod contentMethod = null;
        HttpClient hc = new HttpClient(new HttpClientParams(), new SimpleHttpConnectionManager());
        hc.getHttpConnectionManager().getParams().setConnectionTimeout(600000);
        hc.getHttpConnectionManager().getParams().setSoTimeout(600000);
        if (!StringUtils.isEmpty(url)) {
            //链接
            contentMethod = new PostMethod(url + urlparam);
            //添加请求头
            contentMethod.addRequestHeader("Content-Type", "application/json");
            if (TOKEN_CODE != null) {
                TokenModel token = getToken(TOKEN_CODE);
                contentMethod.addRequestHeader(token.getUserId(), token.getToken());
            }
            try {
                if (!StringUtils.isEmpty(param)) {
                    RequestEntity entity = new StringRequestEntity(param, "application/json", "UTF-8");
                    contentMethod.setRequestEntity(entity);
                }
                int statusCode1 = hc.executeMethod(contentMethod);
                byte[] responseBody = contentMethod.getResponseBody();
                if (HttpStatus.SC_OK == statusCode1) {
//                    JSONArray resultList = JSONObject.parseArray(html);
                    return ResultGenerator.genSuccessResult(responseBody);
                } else {
                    return ResultGenerator.genFailResult("失败状态码：" + statusCode1);
                }
            } catch (Exception e) {
                log.error("请求失败");
            } finally {
                contentMethod.releaseConnection();
                hc.getHttpConnectionManager().closeIdleConnections(0);
            }
        } else {
            log.error("url错误");
        }
        return ResultGenerator.genFailResult("请求失败");
    }

    /**
     * 获取token
     */
    public TokenModel getToken(String token_code) {
        Condition dictCondition = new Condition(TdDict.class);
        dictCondition.createCriteria().andEqualTo("forModule", token_code);
        List<TdDict> tdDicts = tdDictService.findByCondition(dictCondition);
        Map<String, String> map = Maps.newHashMap();
        for (TdDict tdDict : tdDicts) {
            map.put(tdDict.getDictKey(), tdDict.getDictValue());
        }

        String token = (String) redisUtil.get("token:" + token_code);
        if (StringUtils.isEmpty(token)) {
            //param:"{\"UserName\":\"admin\",\"Password\":\"test\"}"
            //key:"auth"
            String url = map.get("url");
            String param = "{\"UserName\":\"" + map.get("UserName") + "\",\"Password\":\"" + map.get("Password") + "\"}";
            //Result post = postToken((String) redisUtil.hget(token_code, "url"), (String) redisUtil.hget(token_code, "param"));
            Result post = postToken(url, param);
//            JSONObject obj = JSONReader
            Object obj = null;
            try {
                obj = JSON.parse((byte[]) post.getData());
            } catch (Exception e) {
                e.printStackTrace();
            }
            //String tokenKey = (String) redisUtil.hget(token_code, "key");
            String tokenKey = map.get("header_key");
            String tokenValue = (String) ((Map)obj).get("Token");
            TokenModel tmodel = new TokenModel(tokenKey, tokenValue);
            long expire = (long) Double.parseDouble(map.get("expire"));
            redisUtil.set("token:" + token_code, JSONObject.toJSONString(tmodel), expire * 60 * 60);
            return tmodel;
        }
        TokenModel tokenModel = JSONObject.parseObject(token, TokenModel.class);
        return tokenModel;
    }

    /**
     * 更换token
     */
    public void changeToken(String code) {
        Condition dictCondition = new Condition(TdDict.class);
        dictCondition.createCriteria().andEqualTo("forModule", code);
        List<TdDict> tdDicts = tdDictService.findByCondition(dictCondition);
        Map<String, String> map = Maps.newHashMap();
        for (TdDict tdDict : tdDicts) {
            map.put(tdDict.getDictKey(), tdDict.getDictValue());
        }
        redisUtil.del("token:" + code);
        String url = map.get("url");
        String param = "{\"UserName\":\"" + map.get("UserName") + "\",\"Password\":\"" + map.get("Password") + "\"}";
        Result post = this.postToken(url, param);
        JSONObject obj = JSONObject.parseObject((String) post.getData());
        String tokenKey = map.get("header_key");
        String tokenValue = (String) obj.get("Token");
        TokenModel tmodel = new TokenModel(tokenKey, tokenValue);
        long expire = (long) Double.parseDouble(map.get("expire"));
        redisUtil.set("token:" + code, JSONObject.toJSONString(tmodel), expire * 60 * 60);
    }


    public StringBuilder getResponse(InputStream inputStream) throws Exception {
        StringBuilder sb = new StringBuilder();
        String line;

        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        return sb;
    }


    public static void main(String[] args) {
//        HttpClientUtil.hset(ProjectConstant.AQBHQ_KEY,"url","http://120.25.160.53/shxs/api/Token/GetToken");
//        redisUtil.hset(ProjectConstant.AQBHQ_KEY,"key","auth");
//        redisUtil.hset(ProjectConstant.AQBHQ_KEY,"param","{\"UserName\":\"admin\",\"Password\":\"test\"}");
//        Map map= new HashMap();
//        map.put("ProjectID","9af11949-0a06-4feb-ada6-0007170c5c9e");
//        Result post1=new HttpClientUtil().post((String)redisUtil.hget(ProjectConstant.AQBHQ_KEY,"url"),map,ProjectConstant.AQBHQ_KEY);
//        System.out.println(post1.getData());
        //Post
//        Result post=post("http://120.25.160.53/shxs/api/Token/GetToken","{\"UserName\":\"admin\",\"Password\":\"test\"}");
//        System.out.println(post.getData());
//        JSONObject obj = JSONObject.parseObject((String)post.getData());
//        String tok = (String)obj.get("Token");
//        System.out.println("@@@@@@@@@@@@@@"+tok);

//post
//        Token token = new Token("auth","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJVc2VyTmFtZSI6ImFkbWluIiwiUm9sZXMiOltdLCJJc0FkbWluIjp0cnVlLCJFeHBpcnlEYXRlVGltZSI6IjIwMTktMDctMTNUMTc6MTY6MTkuMDE3NTkwMyswODowMCJ9.a4cAzdvmvXgspbpLir5bMOGMjKCP_wSrKCucVPkrP1E");
//    Map map= new HashMap();
//    map.put("ProjectID","9af11949-0a06-4feb-ada6-0007170c5c9e");
//        Result post1=post("http://120.25.160.53/shxs/Hermes/WebAPI/GetProjectInCondition",map,token);
//        System.out.println(post1.getData());
        //GET
//    Map map = new HashMap();
//    map.put("prjId","6");
//    map.put("start","2015-01-12 12:00:11");
//    map.put("end","2018-12-11 11:33:45");
//       Result aa=get("http://10.0.19.121:5000/points",map);
//        System.out.println(aa.getData());
    }

}
