package cn.smxy.forum.task;

import cn.smxy.forum.service.ISilenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class UserSilenceTask {

    @Autowired
    private ISilenceService silenceService;

    /**
     * 更新用户禁言状态
     * 定时任务，更新用户禁言状态,每60分钟执行一次
     * @return
     */
    @Scheduled(cron = "0 0 * * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void updateUserSilenceStatus() {
        List<Long> userIdList = silenceService.getSilenceCancelUserIdList();
        if(userIdList.size()>0){
            for (int i = 0; i < userIdList.size(); i++) {
                silenceService.silenceRemind(userIdList.get(i),"1",null,null);
            }
            silenceService.updateUserSilenceStatus();
        }
    }

}
