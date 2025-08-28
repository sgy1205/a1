package cn.smxy.forum.service;

import cn.smxy.forum.domain.entity.Role;
import cn.smxy.forum.domain.param.insert.AddRoleDTO;
import cn.smxy.forum.domain.param.update.UpdateRoleDTO;
import cn.smxy.forum.domain.vo.RoleDetailVo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IRoleService extends IService<Role> {

    /**
     *  添加角色
     * @param addRoleDTO
     * @return
     */
    Integer addRole(AddRoleDTO addRoleDTO);

    /**
     * 添加角色和权限菜单关联
     * @param menuIds
     * @param roleId
     * @return
     */
    Integer addRoleMenu(List<Long> menuIds, Long roleId);

    /**
     * 修改角色
     * @param updateRoleDTO
     * @return
     */
    Integer updateRole(UpdateRoleDTO updateRoleDTO);

    /**
     * 删除关联的角色权限
     * @param roleId
     * @return
     */
    Integer deleteRoleMenu(Long roleId);

    /**
     * 获取当前角色详情
     * @param roleId
     * @return
     */
    RoleDetailVo getRoleDetail(Long roleId);

    /**
     * 删除与用户绑定的角色
     * @param roleId
     * @return
     */
    Integer deleteUserRole(Long roleId);

    /**
     * 为用户添加普通角色权限
     * @param userId
     * @return
     */
    Integer addUserCommonRole(Long userId);

    /**
     * 获取用户绑定的角色标识
     * @param userId
     * @return
     */
    List<String> getRoleKeysByUserId(Long userId);

}
