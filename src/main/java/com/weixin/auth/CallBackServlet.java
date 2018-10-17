package com.weixin.auth;

import com.alibaba.fastjson.JSONObject;
import com.weixin.utils.AuthUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 回调地址中的业务操作。
 * Created by liyintao on 2018/5/10.
 */
//@WebServlet("/callBack") // 这是web.xml中配置了。不配置就保留
public class CallBackServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//    	微信回调的时候回带过来这个参数-------
//    	第二步：通过code换取网页授权access_token
    	String code = req.getParameter("code");
        System.err.println("回调时响应：code = " + code);
        // 获取code后，请求以下链接获取access_token：  https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + AuthUtil.APPID
                + "&secret=" + AuthUtil.APPSECRET
                + "&code=" + code
                + "&grant_type=authorization_code";
        JSONObject jsonObject = AuthUtil.doGetJson(url);
        String openid = jsonObject.getString("openid");
        String token = jsonObject.getString("access_token");
     // 第三步刷新access_token（如果需要）
        // 第四步：拉取用户信息(需scope为 snsapi_userinfo)
        String infoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + token
                + "&openid=" + openid
                + "&lang=zh_CN";
        JSONObject userInfo = AuthUtil.doGetJson(infoUrl);
        System.err.println("从微信获取到的用户基本信息：" + userInfo);
        
        //1、使用微信用户信息直接登录，无序注册和绑定
        req.setAttribute("info", userInfo);
        req.getRequestDispatcher("/index1.jsp").forward(req, resp);
    }
    
    
}
