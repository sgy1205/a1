package cn.smxy.forum.task;

import cn.smxy.forum.domain.entity.Collection;
import cn.smxy.forum.domain.entity.Likes;
import cn.smxy.forum.service.ILikesService;
import cn.smxy.forum.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

import static cn.smxy.forum.constant.Constants.*;

@Component
public class LikesTask {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ILikesService likesService;

    /**
     * 从redis取出要修改的收藏列表，储存到数据库
     */
    @Scheduled(cron = "0/2 * * * * ?")// 每2秒执行一次
    public void likesUpdate(){
        List<Likes> likes = redisUtil.popFromList(REDIS_LIKES_KEY, 300, Likes.class);
        if (likes != null && !likes.isEmpty()) {
            likesService.updateLikesBatch(likes);
        }
    }

}
