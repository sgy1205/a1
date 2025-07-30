package cn.smxy.yeardesign.service.Impl;

import cn.smxy.yeardesign.domain.entity.User;
import cn.smxy.yeardesign.domain.param.insert.RegisterDTO;
import cn.smxy.yeardesign.service.IRegisterService;
import cn.smxy.yeardesign.service.IUserService;
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
