package cn.smxy.yeardesign.service.Impl;

import cn.smxy.yeardesign.domain.entity.LoginUser;
import cn.smxy.yeardesign.domain.entity.User;
import cn.smxy.yeardesign.mapper.UserMapper;
import cn.smxy.yeardesign.service.IUserService;
import cn.smxy.yeardesign.utils.AjaxResult;
import cn.smxy.yeardesign.utils.JWTUtil;
import cn.smxy.yeardesign.utils.RedisUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
