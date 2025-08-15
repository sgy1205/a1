package cn.smxy.forum.service.Impl;

import cn.smxy.forum.domain.entity.User;
import cn.smxy.forum.domain.other.UserManagerDetail;
import cn.smxy.forum.domain.param.query.UserPageListDTO;
import cn.smxy.forum.domain.param.update.UpdateUserDTO;
import cn.smxy.forum.domain.vo.UserManagerPageListVo;
import cn.smxy.forum.mapper.UserMapper;
import cn.smxy.forum.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<UserManagerPageListVo> getUserPageList(UserPageListDTO userPageListDTO) {
        return userMapper.getUserPageList(userPageListDTO);
    }

    @Override
    public UserManagerDetail getUserDetailById(Long userId) {
        return userMapper.getUserDetailById(userId);
    }

    @Override
    public Integer updateUser(UpdateUserDTO updateUserDTO, String updateBy) {
        return userMapper.updateUser(updateUserDTO,updateBy);
    }

    @Override
    public Integer deleteUserRole(Long userId) {
        return userMapper.deleteUserRole(userId);
    }

    @Override
    public Integer addUserRoles(Long userId, List<Long> roleIds) {
        return userMapper.addUserRoles(userId, roleIds);
    }

    @Override
    public boolean checkUserName(Long userId, String userName) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUserName, userName);
        lqw.ne(User::getUserId, userId);
        lqw.eq(User::getDelFlag,0);
        return userMapper.selectCount(lqw) > 0;
    }

    @Override
    public boolean checkNickName(Long userId, String nickName) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getNickName, nickName);
        lqw.ne(User::getUserId, userId);
        lqw.eq(User::getDelFlag,0);
        return userMapper.selectCount(lqw) > 0;
    }

    @Override
    public boolean checkEmail(Long userId, String email) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getEmail, email);
        lqw.ne(User::getUserId, userId);
        lqw.eq(User::getDelFlag,0);
        return userMapper.selectCount(lqw) > 0;
    }
}
