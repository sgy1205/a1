package cn.smxy.forum.service;

import cn.smxy.forum.domain.entity.Notification;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface INotificationService extends IService<Notification> {

    /**
     * 定时添加消息列表到数据库
     */
    void addNotifications(List<Notification> notifications);

}
