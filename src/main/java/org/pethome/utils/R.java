package org.pethome.utils;

import com.alibaba.druid.util.StringUtils;

import java.util.HashMap;
import java.util.Map;


public class R {

    /**
     * 通用成功状态码
     */
    public static final String SUCCESS_CODE = "001";
    /**
     * 失败状态码
     */
    public static final String FAIL_CODE = "004";
    /**
     * 未登录
     */
    public static final String USER_NO_LOGIN = "401";

    /**
     * 成功
     * @param msg
     * @param key
     * @param data
     * @return
     */
    public static Map ok(String msg,String key,Object data){

        return retMap(SUCCESS_CODE,msg,data,key);
    }

    /**
     * 成功
     * @param key
     * @param data
     * @return
     */
    public static Map ok(String key,Object data){

        return retMap(SUCCESS_CODE,null,data,key);
    }

    /**
     * 成功
     * @return
     */
    public static Map ok(String msg){

        return retMap(SUCCESS_CODE,msg,null,null);
    }


    /**
     * 失败
     * @param msg
     * @param key
     * @param data
     * @return
     */
    public static Map fail(String msg,String key,Object data){

        return retMap(FAIL_CODE,msg,data,key);
    }

    /**
     * 失败
     * @param key
     * @param data
     * @return
     */
    public static Map fail(String key,Object data){

        return retMap(FAIL_CODE,null,data,key);
    }

    /**
     * 失败
     * @return
     */
    public static Map fail(String msg){

        return retMap(FAIL_CODE,msg,null,null);

    }

    public static Map retMap(String code, String msg, Object data, String key){

        Map map = new HashMap();
        map.put("code",code);
        if (!StringUtils.isEmpty(msg)){
            map.put("msg",msg);
        }

        //自定义map数据传入 返回的key和值
        if (data != null && !StringUtils.isEmpty(key)){
            Map dataMap = new HashMap();
            dataMap.put(key,data);
            map.put("data",dataMap);
        }
        return map;
    }


    public static void main(String[] args) {

        Map map = retMap("001", null, "zxm", "user");

        Map ok = ok("user", "zxm");
        Map ok1 = ok("添加成功！");

        System.out.println("map = " + map);


    }



}