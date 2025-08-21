package cn.smxy.forum.mapping;

import cn.smxy.forum.domain.entity.User;
import cn.smxy.forum.domain.vo.UserCenterListVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapping {

    UserMapping INSTANCE= Mappers.getMapper(UserMapping.class);

    @Mapping(target = "postNumber", ignore = true)
    UserCenterListVo toUserCenterListVo(User user);

}
