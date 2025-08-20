package cn.smxy.forum.service.Impl;

import cn.smxy.forum.domain.entity.Notification;
import cn.smxy.forum.mapper.NotificationMapper;
import cn.smxy.forum.service.INotificationService;
import cn.smxy.forum.utils.RedisUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NotificationServiceImpl extends ServiceImpl<NotificationMapper, Notification> implements INotificationService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addNotifications(List<Notification> notifications) {
        this.saveBatch(notifications);
    }
}
