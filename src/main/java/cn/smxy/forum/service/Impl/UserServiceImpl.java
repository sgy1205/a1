package cn.smxy.forum.service.Impl;

import cn.smxy.forum.domain.entity.User;
import cn.smxy.forum.mapper.UserMapper;
import cn.smxy.forum.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
