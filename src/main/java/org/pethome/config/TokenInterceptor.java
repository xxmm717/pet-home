package org.pethome.config;


import io.jsonwebtoken.Claims;
import org.pethome.pojo.User;
import org.pethome.utils.JwtUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //判断请求方式，排除OPTIONS请求
        if(request.getMethod().toUpperCase().equals("OPTIONS")){
            return true;//通过所有OPTION请求
        }

        //1、获取请求头
        String token = request.getHeader("Authorization");
        //2、使用工具类，判断token是否有效
        boolean verifyToken = JwtUtils.verifyToken(token);
        //3、如果token失效，返回状态码401，拦截
        if (!verifyToken) {
            response.setStatus(401);
            return false;
        }
        //4、如果token正常可用，放行
        //解析token，获取id，用户名，密码
        Claims claims = JwtUtils.getClaims(token);
        Integer id =(Integer)claims.get("id");
        String username = (String) claims.get("username");
        String password = (String) claims.get("password");

        //构造User对象，存入Threadlocal
        User user = new User();
        user.setUserId(Long.valueOf(id));
        user.setUsername(username);
        user.setPassword(password);

        org.pethome.config.UserHolder.set(user);
        return true;

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        org.pethome.config.UserHolder.remove();
    }
}