package com.weixin.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by liyintao on 2018/5/10.
 */
public class AuthUtil {
	// 官方微信提供的测试账号【https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421137522】接口测试号申请
    public static final String APPID = "wx93ab8bc78863eddd";
    public static final String APPSECRET = "f3ba90aa933c1671542f5cc3767a5734";
	// 陶承波提供的
	/*public static final String APPID = "wx77f0316147d3c190";
    public static final String APPSECRET = "2611955ebb2ee9b12d199d5c82c8cbdd";*/
    
    // 我自己本人的APPID、APPSECRET【我是订阅号没有权限，只能用官方提供的测试账号】
    /*public static final String APPID = "wxa167dd9faacbd814";
    public static final String APPSECRET = "35f12d4e18d332b1649d3a82d3f56112";*/
    
    public static JSONObject doGetJson(String url) throws IOException {
        JSONObject jsonObject = null;
        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = client.execute(httpGet);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            String result = EntityUtils.toString(entity, "UTF-8");
            jsonObject = JSONObject.parseObject(result);
        }
        // 释放连接
        httpGet.releaseConnection();
        return jsonObject;
    }
}
