package com.weixin.auth;

import com.weixin.utils.AuthUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * 界面入口地址：
 * Created by liyintao on 2018/5/10.
 * 1、引导用户进入授权页面同意授权，获取code
 */
@Slf4j
@WebServlet("/wxLogin") // 访问地址
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
       System.out.println("【LoginServlet】引导用户进入授权页面同意授权，获取code...");
       // 传给微信的回调地址http://10.105.0.28:8080/
       // 陶承波的域名地址
//    	String backUrl = "http://wx.yogapay.com/WxAuth/wxCallBack"; 
    	// 本地映射到公网的域名+路径 http://dnkw2t.natappfree.cc/WxAuth/wxCallBack
    	String backUrl = "http://dnkw2t.natappfree.cc/WxAuth/callBack";   //  回调地址
    	// 访问的作用域范围
        String scope = "snsapi_userinfo"; // snsapi_base
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
                + AuthUtil.APPID
                + "&redirect_uri=" + URLEncoder.encode(backUrl)
                + "&response_type=code"
                + "&scope=" + scope
                + "&state=STATE#wechat_redirect";
        log.info("url = {}", url);
        // 重定向
        resp.sendRedirect(url);
//        super.doGet(req, resp);
    }
}
