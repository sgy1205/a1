package cn.smxy.forum.controller;

import cn.smxy.forum.domain.entity.Notification;
import cn.smxy.forum.domain.vo.NotificationListVo;
import cn.smxy.forum.mapping.NotificationMapping;
import cn.smxy.forum.service.INotificationService;
import cn.smxy.forum.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.smxy.forum.constant.Constants.*;

@RestController
@RequestMapping("/notification")
@Api(tags = "消息通知模块")
public class NotificationController extends BaseController {

    @Autowired
    private INotificationService notificationService;

    @GetMapping("/list")
    @ApiOperation("获取当前登录用户的消息列表")
    public R<List<NotificationListVo>> notificationList() {
        LambdaQueryWrapper<Notification> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Notification::getDelFlag,NO_DELETE)
                .eq(Notification::getUserId,getUserId());
        List<Notification> notifications = notificationService.list(lqw);

        List<NotificationListVo> listVos= NotificationMapping.INSTANCE.toListVoList(notifications);
        return R.ok(listVos);
    }

    @PostMapping("/readAll")
    @ApiOperation("将所有未读消息改为已读")
    public R readAllNotification() {
        LambdaUpdateWrapper<Notification> luw = new LambdaUpdateWrapper<>();
        luw.set(Notification::getReadStatus, 1L)
                .eq(Notification::getDelFlag,NO_DELETE)
                .eq(Notification::getUserId,getUserId())
                .eq(Notification::getReadStatus,0);

        return R.to(notificationService.update(luw), "修改");
    }

    @DeleteMapping()
    @ApiOperation("删除消息")
    public R deleteNotifications(@RequestBody List<Long> notificationIds) {
        LambdaUpdateWrapper<Notification> luw = new LambdaUpdateWrapper<>();
        luw.set(Notification::getDelFlag,DELETE)
                .in(Notification::getNotificationId,notificationIds);

        return R.to(notificationService.update(luw), "删除");
    }

}
