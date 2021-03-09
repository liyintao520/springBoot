package com.weixin.controller;

import com.weixin.utils.AuthUtil;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RestController
//@RequestMapping(value = "/WxAuth")
public class LoginController {

    /**
     * 微信授权登录
     * @param resp
     * @return
     */
    @GetMapping("/WxAuth/wxLogin")
    public String wxLogin(HttpServletResponse resp){
        try {
            System.out.println("开始调用微信接口....");
            // 传给微信的回调地址http://10.105.0.28:8080/
            // 陶承波的域名地址
//    	String backUrl = "http://wx.yogapay.com/WxAuth/wxCallBack";
            // 本地映射到公网的域名+路径 http://wxzr6z.natappfree.cc/WxAuth/callBack
            String backUrl = "http://gycf3e.natappfree.cc/WxAuth/callBack"; // 回调地址

            // 访问的作用域范围
            // 第一步：用户同意授权，获取code【https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect】
            // 【snsapi_userinfo】弹出用户同意拒绝对话框，可以获取用户基本信息。【snsapi_base】默认直接同意，获取用户的openid
            String scope = "snsapi_userinfo"; // snsapi_base
//            String scope = "snsapi_base"; // snsapi_base
            String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
                    + AuthUtil.APPID
                    + "&redirect_uri=" + URLEncoder.encode(backUrl)
                    + "&response_type=code"
                    + "&scope=" + scope
                    + "&state=STATE#wechat_redirect";
            log.info("url = {}", url);
            // 重定向
            resp.sendRedirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "李银涛测试springBoot启动成功！！！" ;
    }

    /**
     * qq授权登录
     * @param resp
     * @return
     */
    @GetMapping("/qqAuth/qqLogin")
    public String qqLogin(HttpServletResponse resp){

        return "qq授权登录！";
    }




}
