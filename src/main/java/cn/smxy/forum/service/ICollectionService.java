package cn.smxy.forum.service;

import cn.smxy.forum.domain.entity.Collection;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ICollectionService extends IService<Collection> {

    /**
     * 帖子收藏
     * @param postId 帖子ID
     * @param userId 用户ID
     * @return
     */
    boolean collectionPost(Long postId,Long userId);

    /**
     * 修改帖子收藏状态（保存到redis中）
     * @param collection
     */
    void updateCollectionPostToRedis(Collection collection);

    /**
     * 批量更新帖子收藏状态
     */
    void updateCollectionPostBatch(List<Collection> collections);

    /**
     * 添加收藏消息提醒
     * @param userId
     * @param postId
     */
    void collectNotification(Long userId,Long postId);


}
