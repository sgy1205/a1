package cn.smxy.forum.service.Impl;

import cn.smxy.forum.domain.entity.User;
import cn.smxy.forum.domain.param.insert.RegisterDTO;
import cn.smxy.forum.service.IRegisterService;
import cn.smxy.forum.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class RegisterServiceImpl implements IRegisterService {

    @Autowired
    private IUserService userService;

    @Override
    public boolean register(RegisterDTO registerDTO) {
        User user= new User();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        registerDTO.setPassword(encoder.encode(registerDTO.getPassword()));
        BeanUtils.copyProperties(registerDTO, user);

        return userService.save(user);
    }
}
