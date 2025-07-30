package cn.smxy.forum.service.Impl;

import cn.smxy.forum.domain.entity.LoginUser;
import cn.smxy.forum.domain.entity.User;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserServiceImpl service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        // 查询到对应的用户
        wrapper.eq(User::getUserName, username);
        User user = service.getOne(wrapper);
        // 判断用户是否为空
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户不存在");
        }
        // 将用户封装成UserDetails
        // UserDetails是一个接口，所以先去entity创建一个实现类
        // 主要用来存储登录的用户的信息
        return new LoginUser(user);
    }
}
