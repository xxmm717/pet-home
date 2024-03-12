package org.pethome.service;

import java.util.Map;

public interface UserService {
    //登录
    Map login(String username, String password);
    //注册
    Map register(String username, String password);
}
