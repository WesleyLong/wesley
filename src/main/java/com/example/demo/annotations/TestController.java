package com.example.demo.annotations;

import lombok.extern.java.Log;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log
@Controller
@RequestMapping("/annotation")
public class TestController {
    @RequestMapping("/test")
    @Wesley(value = "自定义注解-controller")
    public void test(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        ((TestController) AopContext.currentProxy()).test2();
    }

    @Wesley(value = "自定义注解-method")
    public void test2() {
    }
}
