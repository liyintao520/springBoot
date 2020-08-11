package com.weixin.controller;

import com.alibaba.fastjson.JSONObject;
import com.weixin.utils.AuthUtil;
import com.weixin.utils.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by liyintao on 2018/10/16.
 */
@Controller
public class CallBackController {

    @RequestMapping("/WxAuth/callBack")
    public String callBack(HttpServletRequest req, HttpServletResponse resp){
//        wutcc4.natappfree.cc
//    	微信回调的时候回带过来这个参数-------
//    	第二步：通过code换取网页授权access_token
        try {
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
            // 可以不写，这样返回页面。
            req.setAttribute("info", userInfo);
//            req.getRequestDispatcher("success").forward(req, resp);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
