package cn.smxy.forum.service.Impl;

import cn.smxy.forum.domain.entity.Role;
import cn.smxy.forum.domain.param.insert.AddRoleDTO;
import cn.smxy.forum.domain.param.update.UpdateRoleDTO;
import cn.smxy.forum.domain.vo.RoleDetailVo;
import cn.smxy.forum.mapper.RoleMapper;
import cn.smxy.forum.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Integer addRole(AddRoleDTO addRoleDTO) {
        return roleMapper.addRole(addRoleDTO);
    }

    @Override
    public Integer addRoleMenu(List<Long> menuIds, Long roleId) {
        return roleMapper.addRoleMenu(menuIds, roleId);
    }

    @Override
    public Integer updateRole(UpdateRoleDTO updateRoleDTO) {
        return roleMapper.updateRole(updateRoleDTO);
    }

    @Override
    public Integer deleteRoleMenu(Long roleId) {
        return roleMapper.deleteRoleMenu(roleId);
    }

    @Override
    public RoleDetailVo getRoleDetail(Long roleId) {
        Role role = roleMapper.selectById(roleId);
        RoleDetailVo roleDetailVo = new RoleDetailVo();
        roleDetailVo.setMenuIds(roleMapper.selectRoleMenuIds(roleId));
        roleDetailVo.setRoleId(role.getRoleId());
        roleDetailVo.setRoleName(role.getRoleName());
        roleDetailVo.setRemark(role.getRemark());
        roleDetailVo.setStatus(role.getStatus());
        roleDetailVo.setRoleKey(role.getRoleKey());
        return roleDetailVo;
    }

    @Override
    public Integer deleteUserRole(Long roleId) {
        return roleMapper.deleteUserRole(roleId);
    }
}
