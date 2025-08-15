package cn.smxy.forum.mapping;

import cn.smxy.forum.domain.entity.Role;
import cn.smxy.forum.domain.vo.RoleNameListVo;
import cn.smxy.forum.domain.vo.RolePageListVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoleMapping {

    RoleMapping INSTANCE= Mappers.getMapper(RoleMapping.class);

    RolePageListVo toPageListVo(Role role);

    List<RolePageListVo> toPageListVoList(List<Role> roles);

    RoleNameListVo toRoleNameListVo(Role role);

    List<RoleNameListVo> toRoleNameListVoList(List<Role> roles);
}
