package org.pethome.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.pethome.mapper.UserMapper;
import org.pethome.pojo.User;
import org.pethome.service.UserService;
import org.pethome.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static net.sf.jsqlparser.util.validation.metadata.NamedObject.user;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    //登录
    @Override
    public Map login(String username, String password) {
        //        判断用户是否存在
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUsername,username);
        User userIsExist = userMapper.selectOne(lqw);
        if (userIsExist == null){
            return R.fail("用户不存在");
        }
//        查询出该用户的密码
        LambdaQueryWrapper<User> lqw2 = new LambdaQueryWrapper<>();
        lqw2.eq(User::getUsername,username);
        User userSelected = userMapper.selectOne(lqw2);
        String oldPassword = userSelected.getPassword();
//        登录成功
        if (oldPassword.equals(password)) {
            userSelected.setPassword(null);

            //生成token
            Map<String, Object> map = new HashMap<>();
            map.put("id", userSelected.getUserId());
            map.put("username", username);
            map.put("password", password);
            long now = System.currentTimeMillis();
            String token = Jwts.builder()
                    .signWith(SignatureAlgorithm.HS512, "xiaolanshu") //指定加密算法，加密签名
                    .setClaims(map) //写入数据
                    .setExpiration(new Date(now + 300000000)) //失效时间
                    .compact();

            Map<String,Object> userMap = new HashMap<>();
            userMap.put("user",userSelected);
            userMap.put("token",token);

            return R.ok("登录成功","user_data",userMap);

        }
//        登录失败
        else
            return R.fail("密码错误");
    }
    // 注册
    @Override
    public Map register(String username, String password) {
        //判断用户名是否已经存在
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUsername,username);
        User user1 = userMapper.selectOne(lqw);
        if (user1.getUsername().equals(username)){
            return R.fail("用户名已存在");
        }
        //保存用户信息
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        //设置默认头像
        user.setAvatar("https://tanhua-zxm.oss-cn-hangzhou.aliyuncs.com/xingxuan/%E7%94%A8%E6%88%B7/default.jpg");
        userMapper.insert(user);
        return R.ok("注册成功");
    }
}
