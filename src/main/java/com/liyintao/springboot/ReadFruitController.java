package com.liyintao.springboot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liyintao on 2018/5/10.
 */
@RestController
public class ReadFruitController {

    @Value("${fruit.name}")
    private String name;

    @Value("${fruit.amount}")
    private Integer amount;

    @RequestMapping("/testyml")
    public String readymlResource() {
        return name + ";" + amount;
    }

}