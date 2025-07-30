package cn.smxy.forum.service.Impl;

import cn.smxy.forum.domain.entity.LoginUser;
import cn.smxy.forum.domain.param.other.LoginBodyDTO;
import cn.smxy.forum.service.ILoginService;
import cn.smxy.forum.utils.JWTUtil;
import cn.smxy.forum.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class LoginServiceImpl implements ILoginService {


    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public String login(String userName, String password) {
        // AuthenticationManager.authenticate()进行用户认证
        // 需要一个参数:Authentication的实现类，所以需要把user转换成Authentication的实现类
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userName,password);
        Authentication authenticate = authenticationManager.authenticate(token);
        // 如果认证没通过就给出对应的提示
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("认证失败");
        }
        // 如果通过则根据SysUser生成jwt
        // 获取登录的用户信息
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String jwt = JWTUtil.createToken(loginUser.getUser());
        // 将用户信息存入redis
        redisUtil.setCacheObject("user:" + loginUser.getUser().getUserId(), loginUser);
        //把token响应给前端
        return jwt;
    }

}
