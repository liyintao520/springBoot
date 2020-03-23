package com.liyintao.springboot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liyintao on 2018/5/3.
 */
@RestController
@RequestMapping(value = "/demo")
public class HelloController {

     @Value("${fruit.name}")
    private String name = "";

//    @RequestMapping(value = "/hello")

    /**
     * http://localhost:8080/demo/hello
     * @return
     */
    @GetMapping("/hello")
    public String hello(){
        return "测试springBoot启动成功！！！" + this.name;
    }
}
