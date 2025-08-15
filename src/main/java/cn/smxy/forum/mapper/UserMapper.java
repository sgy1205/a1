package cn.smxy.forum.mapper;

import cn.smxy.forum.domain.entity.User;
import cn.smxy.forum.domain.other.UserManagerDetail;
import cn.smxy.forum.domain.param.query.UserPageListDTO;
import cn.smxy.forum.domain.param.update.UpdateUserDTO;
import cn.smxy.forum.domain.vo.UserManagerPageListVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

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
    UserManagerDetail getUserDetailById(@Param("userId") Long userId);

    /**
     * 修改用户信息
     * @param updateUserDTO
     * @param updateBy
     * @return
     */
    Integer updateUser(@Param("updateUserDTO") UpdateUserDTO updateUserDTO, @Param("updateBy") String updateBy);

    /**
     * 删除用户权限
     * @param userId
     * @return
     */
    Integer deleteUserRole(@Param("userId")Long userId);

    /**
     * 添加用户权限列表
     * @param userId
     * @param roleIds
     * @return
     */
    Integer addUserRoles(@Param("userId")Long userId,@Param("roleIds")List<Long> roleIds);

    /**
     * 根据用户名或邮箱获取用户名
     * @param loginStr
     * @return
     */
    String getUserNameForLogin(@Param("loginStr") String loginStr);


}
