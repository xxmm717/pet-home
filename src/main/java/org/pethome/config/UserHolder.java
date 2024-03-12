package org.pethome.config;

import org.pethome.pojo.User;

/**
 * 实现threadLocal储存数据的方法
 */
public class UserHolder {

    private static ThreadLocal<User> tl = new ThreadLocal<>();
    //将用户对象，存入Threadlocal
    public static void set(User user){
        tl.set(user);
    }
    //从当前线程，获取用户对象
    public static User getUser(){
        return tl.get();
    }
    //从当前线程，获取用户id
    public static Long getUserId(){
        return tl.get().getUserId();
    }
    //从当前线程，获取用户名
    public static String getUsername(){
        return tl.get().getUsername();
    }
    //从当前线程，获取用户密码
    public static String getUserPassword(){
        return tl.get().getPassword();
    }
    //清空
    public static void remove(){
        tl.remove();
    }
}
