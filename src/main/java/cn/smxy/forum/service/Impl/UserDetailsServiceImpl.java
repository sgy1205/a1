package cn.smxy.forum.service.Impl;

import cn.smxy.forum.domain.entity.LoginUser;
import cn.smxy.forum.domain.entity.User;
import cn.smxy.forum.service.IMenuService;
import cn.smxy.forum.service.IRoleService;
import cn.smxy.forum.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUserService service;
    @Autowired
    private IMenuService menuService;
    @Autowired
    private IRoleService roleService;

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
        // 获取用户权限
        boolean isAdmin = false;
        List<String> permissions;
        List<String> roleKeysByUserId = roleService.getRoleKeysByUserId(user.getUserId());
        for (int i = 0; i < roleKeysByUserId.size(); i++) {
            if(roleKeysByUserId.get(i).equals("admin")){
                isAdmin = true;
            }
        }
        if(isAdmin){
            permissions = menuService.getAllermission();
        }else{
            permissions = menuService.queryPermissionByUserId(user.getUserId());
        }
        // 将用户封装成UserDetails
        // UserDetails是一个接口，所以先去entity创建一个实现类
        // 主要用来存储登录的用户的信息
        return new LoginUser(user, permissions);
    }
}
