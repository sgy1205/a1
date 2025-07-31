package cn.smxy.forum.utils;

import cn.smxy.forum.domain.entity.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyExpressionUtil {
    public boolean myAuthority(String authority) {
        // 获取当前用户权限
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 获取到登录的用户
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 获取用户权限
        List<String> permissions = loginUser.getPermissions();
        //判断用户权限集合中是否存在对应的权限
        return permissions.contains(authority);
    }
}
