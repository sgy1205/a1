package cn.smxy.forum.mapping;

import cn.smxy.forum.domain.entity.Notification;
import cn.smxy.forum.domain.vo.NotificationListVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface NotificationMapping {

    NotificationMapping INSTANCE= Mappers.getMapper(NotificationMapping.class);

    NotificationListVo toListVo(Notification notification);

    List<NotificationListVo> toListVoList(List<Notification> notifications);
}
