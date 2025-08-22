package cn.smxy.forum.service;

import cn.smxy.forum.domain.entity.Likes;
import cn.smxy.forum.domain.param.update.UpdateLikesDTO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ILikesService extends IService<Likes> {

    /**
     * 修改点赞状态
     * @param userId
     * @param updateLikesDTO
     * @return
     */
    boolean updateLikes(Long userId, UpdateLikesDTO updateLikesDTO);

    /**
     * 将要保存的点赞消息保存到redis中
     * @param likes
     */
    void updateLikesToRedis(Likes likes);

    /**
     * 批量修改点赞状态
     * @param likes
     */
    void updateLikesBatch(List<Likes> likes);

    /**
     * 点赞的消息提醒
     * @param userId 点赞用户ID
     * @param postId 帖子ID
     * @param targetId 点赞的主体ID
     * @param type 点赞类型 0-帖子 1-评论
     */
    void likesNotification(Long userId,Long postId,Long targetId,String type);

}
