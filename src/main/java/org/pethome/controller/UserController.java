package org.pethome.controller;

import org.pethome.service.UserService;
import org.pethome.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    //登录
    @PostMapping("/login")
    public Map login(@RequestParam String username,@RequestParam String password) {

        return userService.login(username,password);
    }
    //注册
    @PostMapping("/register")
    public Map register(@RequestParam String username,@RequestParam String password) {
        return userService.register(username,password);
    }

}
