package com.weixin.controller;

import com.weixin.utils.AuthUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by liyintao on 2018/10/16.
 */
@RestController
@RequestMapping(value = "/WxAuth")
public class LoginController {

//    @PostMapping("/WxAuth/wxLogin")
    @GetMapping("/wxLogin")
    public String hello(HttpServletResponse resp){
        try {
            System.out.println("开始调用微信接口");
            // 传给微信的回调地址http://10.105.0.28:8080/
            // 陶承波的域名地址
//    	String backUrl = "http://wx.yogapay.com/WxAuth/wxCallBack";
            // 本地映射到公网的域名+路径 http://zyxnj3.natappfree.cc/WxAuth/wxCallBack
            String backUrl = "http://wutcc4.natappfree.cc/WxAuth/wxCallBack";
            // 访问的作用域范围
            String scope = "snsapi_userinfo"; // snsapi_base
            String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
                    + AuthUtil.APPID
                    + "&redirect_uri=" + URLEncoder.encode(backUrl)
                    + "&response_type=code"
                    + "&scope=" + scope
                    + "&state=STATE#wechat_redirect";
            // 重定向
            resp.sendRedirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "李银涛测试springBoot启动成功！！！" ;
    }
}
