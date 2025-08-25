package cn.smxy.forum.service.Impl;

import cn.smxy.forum.domain.entity.User;
import cn.smxy.forum.domain.param.insert.RegisterDTO;
import cn.smxy.forum.service.IRegisterService;
import cn.smxy.forum.service.IRoleService;
import cn.smxy.forum.service.IUserService;
import cn.smxy.forum.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import static cn.smxy.forum.constant.Constants.*;

@Component
public class RegisterServiceImpl implements IRegisterService {

    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;

    @Override
    public R register(RegisterDTO registerDTO) {
        User user= new User();
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUserName, registerDTO.getUserName()).or()
                .eq(User::getEmail, registerDTO.getEmail())
                .eq(User::getDelFlag,NO_DELETE);
        if(userService.count(lqw)>0){
            return R.fail("该账号或邮箱已存在");
        }else{
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            registerDTO.setPassword(encoder.encode(registerDTO.getPassword()));
            BeanUtils.copyProperties(registerDTO, user);
            user.setNickName(user.getUserName());
            if(userService.save(user)){
                user.setRegisterRank(user.getUserId());
                roleService.addUserCommonRole(user.getUserId());
                return R.to(userService.updateById(user),"注册");
            }else {
                return R.fail("注册失败");
            }
        }
    }
}
