package cn.smxy.forum.service;

import cn.smxy.forum.domain.entity.User;
import cn.smxy.forum.domain.other.UserManagerDetail;
import cn.smxy.forum.domain.param.query.UserPageListDTO;
import cn.smxy.forum.domain.param.update.UpdateUserDTO;
import cn.smxy.forum.domain.vo.UserManagerPageListVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IUserService extends IService<User> {


    /**
     * 获取用户管理分页查询
     * @param userPageListDTO
     * @return
     */
    List<UserManagerPageListVo> getUserPageList(UserPageListDTO userPageListDTO);

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    UserManagerDetail getUserDetailById(Long userId);

    /**
     * 修改用户信息
     * @param updateUserDTO
     * @param updateBy
     * @return
     */
    Integer updateUser(UpdateUserDTO updateUserDTO, String updateBy);

    /**
     * 删除用户权限
     * @param userId
     * @return
     */
    Integer deleteUserRole(Long userId);

    /**
     * 添加用户权限列表
     * @param userId
     * @param roleIds
     * @return
     */
    Integer addUserRoles(Long userId,List<Long> roleIds);

    /**
     * 判断用户名是否重复
     * @param userId
     * @param userName
     * @return
     */
    boolean checkUserName(Long userId, String userName);

    /**
     * 判断昵称是否重复
     * @param userId
     * @param nickName
     * @return
     */
    boolean checkNickName(Long userId, String nickName);

    /**
     * 判断邮箱是否重复
     * @param userId
     * @param email
     * @return
     */
    boolean checkEmail(Long userId, String email);

    /**
     * 修改密码
     * @param userId
     * @param oldpassword
     * @param newPassword
     * @return
     */
    boolean updatePassword(Long userId,String oldpassword, String newPassword);

}
