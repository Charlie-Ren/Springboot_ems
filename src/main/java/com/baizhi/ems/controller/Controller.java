package com.baizhi.ems.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class Controller { //测试用的
   @GetMapping("test")
    public String test(){
        System.out.println("test method invoke");
        return "test method ok";//环境成功
    }
}
