package cn.smxy.forum.mapper;

import cn.smxy.forum.domain.entity.Role;
import cn.smxy.forum.domain.param.insert.AddRoleDTO;
import cn.smxy.forum.domain.param.update.UpdateRoleDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {

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
    Integer addRoleMenu(@Param("menuIds") List<Long> menuIds, @Param("roleId") Long roleId);

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
    Integer deleteRoleMenu(@Param("roleId") Long roleId);

    /**
     * 获取绑定的角色权限ID
     * @param roleId
     * @return
     */
    List<Long> selectRoleMenuIds(@Param("roleId") Long roleId);

    /**
     * 删除与用户绑定的角色
     * @param roleId
     * @return
     */
    Integer deleteUserRole(@Param("roleId") Long roleId);
}
