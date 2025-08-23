package cn.smxy.forum.service.Impl;

import cn.smxy.forum.domain.entity.*;
import cn.smxy.forum.mapper.CollectionMapper;
import cn.smxy.forum.service.ICollectionService;
import cn.smxy.forum.service.IPostAuditService;
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
public class CollectionServiceImpl extends ServiceImpl<CollectionMapper, Collection> implements ICollectionService {

    @Autowired
    private CollectionMapper collectionMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private IPostService postService;
    @Autowired
    private IPostAuditService postAuditService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean collectionPost(Long postId, Long userId) {
        if(!postAuditService.getPostAuditStatus(postId)){
            return false;
        }
        LambdaQueryWrapper<Collection> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Collection::getPostId, postId)
                .eq(Collection::getUserId, userId);
        Collection collection = collectionMapper.selectOne(lqw);
        if (collection == null) {
            collection = new Collection();
            collection.setPostId(postId);
            collection.setUserId(userId);

            if(collectionMapper.insert(collection)>0){
                postService.incrementPostCollection(postId,1L);
                this.collectNotification(userId,postId);
                return true;
            }else{
                return false;
            }
        }else{
            if(collection.getDelFlag().equals(NO_DELETE)){
                collection.setDelFlag(DELETE);
                postService.incrementPostCollection(postId,-1L);
            }else{
                collection.setDelFlag(NO_DELETE);
                postService.incrementPostCollection(postId,1L);
                this.collectNotification(userId,postId);
            }
            this.updateCollectionPostToRedis(collection);
            return true;
        }
    }

    @Override
    public void updateCollectionPostToRedis(Collection collection) {
        redisUtil.addToListTail(REDIS_UPDATECOLLECTION_KEY, collection);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCollectionPostBatch(List<Collection> collections) {
        this.updateBatchById(collections);
    }

    @Override
    public void collectNotification(Long userId, Long postId) {
        Post post = postService.getById(postId);
        LoginUser loginUser = redisUtil.getCacheObject("user:" + userId);
        User user = loginUser.getUser();
        Notification notification = new Notification();
        notification.setUserId(post.getUserId());
        notification.setType("1");
        notification.setMessage("你的帖子："+post.getTitle()+"被 "+user.getNickName() +"收藏");
        notification.setRelatedId(postId);
        notification.setRelatedType("1");
        notification.setOperatorId(userId);
        notification.setAvatar(user.getAvatar());

        redisUtil.addToListTail(REDIS_NOTIFICATIONS_KEY, notification);
    }


}
