package com.example.demo.register;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/event")
public class UserEventController {

    @Resource
    private UserService userService;

    @GetMapping("/register")
    public String register(String username) {
        userService.register(username);
        return "注册成功!";
    }

    @GetMapping("/logout")
    public String logout(String username) {
        userService.logout(username);
        return "登出成功!";
    }
}
