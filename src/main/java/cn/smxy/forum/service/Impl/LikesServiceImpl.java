package cn.smxy.forum.service.Impl;

import cn.smxy.forum.domain.entity.*;
import cn.smxy.forum.domain.param.update.UpdateLikesDTO;
import cn.smxy.forum.mapper.LikesMapper;
import cn.smxy.forum.service.ICommentService;
import cn.smxy.forum.service.ILikesService;
import cn.smxy.forum.service.IPostService;
import cn.smxy.forum.utils.RedisUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static cn.smxy.forum.constant.Constants.*;

@Service
public class LikesServiceImpl extends ServiceImpl<LikesMapper, Likes> implements ILikesService {

    @Autowired
    private LikesMapper likesMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private IPostService postService;
    @Autowired
    private ICommentService commentService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateLikes(Long userId, UpdateLikesDTO updateLikesDTO) {
        LambdaQueryWrapper<Likes> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Likes::getUserId, userId)
                .eq(Likes::getTargetId, updateLikesDTO.getTargetId())
                .eq(Likes::getType, updateLikesDTO.getType());
        Likes likes = likesMapper.selectOne(lqw);
        if (likes == null) {
            likes = new Likes();
            likes.setUserId(userId);
            likes.setTargetId(updateLikesDTO.getTargetId());
            likes.setType(updateLikesDTO.getType());
            if(likesMapper.insert(likes)>0){
                if(updateLikesDTO.getType().equals("0")){
                    likesNotification(userId,0L,updateLikesDTO.getTargetId(),updateLikesDTO.getType());
                }else{
                    likesNotification(userId,updateLikesDTO.getPostId(),updateLikesDTO.getTargetId(),updateLikesDTO.getType());
                }
                return true;
            }else{
                return false;
            }
        }else{
            if(likes.getDelFlag().equals(NO_DELETE)){
                likes.setDelFlag(DELETE);
            }else{
                likes.setDelFlag(NO_DELETE);
                if(updateLikesDTO.getType().equals("0")){
                    likesNotification(userId,0L,updateLikesDTO.getTargetId(),updateLikesDTO.getType());
                }else{
                    likesNotification(userId,updateLikesDTO.getPostId(),updateLikesDTO.getTargetId(),updateLikesDTO.getType());
                }
            }
            this.updateLikesToRedis(likes);
            return true;
        }
    }

    @Override
    public void updateLikesToRedis(Likes likes) {
        redisUtil.addToListTail(REDIS_LIKES_KEY,likes);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateLikesBatch(List<Likes> likes) {
        this.updateBatchById(likes);
    }

    @Override
    public void likesNotification(Long userId, Long postId, Long targetId,String type) {
        LoginUser loginUser = redisUtil.getCacheObject("user:" + userId);
        User user = loginUser.getUser();
        Notification notification = new Notification();
        if(type.equals("0")){
            Post post = postService.getById(targetId);
            notification.setUserId(post.getUserId());
            notification.setMessage("你的帖子："+post.getTitle()+"被 "+user.getNickName()+" 点赞");
            notification.setRelatedType("1");
        }else{
            Comment comment = commentService.getById(targetId);
            notification.setUserId(comment.getUserId());
            notification.setMessage(user.getNickName()+" 赞了你的评论");
            notification.setRelatedType("2");
            notification.setCarrierId(postId);
        }
        notification.setType("1");
        notification.setRelatedId(targetId);
        notification.setOperatorId(userId);
        notification.setAvatar(user.getAvatar());

        redisUtil.addToListTail(REDIS_NOTIFICATIONS_KEY,notification);
    }


}
