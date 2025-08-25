package cn.smxy.forum.task;

import cn.smxy.forum.domain.entity.Collection;
import cn.smxy.forum.service.ICollectionService;
import cn.smxy.forum.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

import static cn.smxy.forum.constant.Constants.*;

@Component
public class CollectionTask {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ICollectionService collectionService;

    /**
     * 从redis取出要修改的收藏列表，储存到数据库
     */
    @Scheduled(cron = "0/2 * * * * ?")// 每2秒执行一次
    public void collectionUpdate(){
        List<Collection> collections = redisUtil.popFromList(REDIS_UPDATECOLLECTION_KEY, 300, Collection.class);
        if (collections != null && !collections.isEmpty()) {
            collectionService.updateCollectionPostBatch(collections);
        }
    }

}
