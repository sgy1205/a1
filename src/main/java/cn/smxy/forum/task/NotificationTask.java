package cn.smxy.forum.task;

import cn.smxy.forum.domain.entity.Notification;
import cn.smxy.forum.service.INotificationService;
import cn.smxy.forum.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotificationTask {

    private static final Logger log = LoggerFactory.getLogger(NotificationTask.class);
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private INotificationService notificationService;

    /**
     * 从redis取出保存的消息列表，储存到数据库
     */
    @Scheduled(cron = "0/30 * * * * ?")// 每30秒执行一次
    public void checkNotifications() {
        List<Notification> notifications = redisUtil.popFromList("notifications",300);
        if (notifications != null && !notifications.isEmpty()) {
            notificationService.addNotifications(notifications);
        }
    }

}
