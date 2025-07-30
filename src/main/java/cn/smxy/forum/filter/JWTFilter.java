package cn.smxy.forum.filter;

import cn.smxy.forum.domain.entity.LoginUser;
import cn.smxy.forum.utils.JWTUtil;
import cn.smxy.forum.utils.RedisUtil;
import cn.smxy.forum.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JWTFilter extends OncePerRequestFilter {
    @Autowired
    private RedisUtil redisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取token
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            // 没有token则放行，有的接口不需要token，不需要解析
            filterChain.doFilter(request, response);
            return;
        }
        // 解析token
        boolean b = JWTUtil.verifyToken(token);
        if (!b) {
            throw new RuntimeException("Token不合法");
        }
        // redis中获取用户信息
        Long userId = JWTUtil.getUserId(token);
        LoginUser user = redisUtil.getCacheObject("user:" + userId);
        System.out.println("user:" + userId);
        System.out.println(user);
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户未登录或者登陆过期");
        }
        // SecurityContextHolder.getContext().setAuthentication()需要一个授权对象，所以要先创建一个
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, null);
        // 将用户信息存入SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
