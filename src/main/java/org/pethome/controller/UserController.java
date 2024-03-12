package org.pethome.controller;

import org.pethome.utils.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("/login")
    public Map login(@RequestParam String username,@RequestParam String password) {
        System.out.println(username);
        System.out.println(password);
        return R.ok("登录成功");
    }
}
