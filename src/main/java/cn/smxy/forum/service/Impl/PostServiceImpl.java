package cn.smxy.forum.service.Impl;

import cn.smxy.forum.domain.entity.Notification;
import cn.smxy.forum.domain.entity.Post;
import cn.smxy.forum.domain.entity.PostAudit;
import cn.smxy.forum.domain.param.query.PostManagerPageListDTO;
import cn.smxy.forum.domain.vo.PostManagerPageListVo;
import cn.smxy.forum.mapper.PostMapper;
import cn.smxy.forum.service.IPostAuditService;
import cn.smxy.forum.service.IPostService;
import cn.smxy.forum.utils.R;
import cn.smxy.forum.utils.RedisUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements IPostService {

    @Autowired
    private PostMapper postMapper;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public List<PostManagerPageListVo> getPostManagerPageListVo(PostManagerPageListDTO postManagerPageListDTO) {
        return postMapper.getPostManagerPageListVo(postManagerPageListDTO);
    }

    @Override
    public void addPostNotification(String msg,Long userId,Long postId) {
        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setMessage(msg);
        notification.setType("0");
        notification.setRelatedId(postId);
        notification.setRelatedType("1");
        redisUtil.addToListTail("notifications", notification);
    }

    @Override
    public boolean postManagerRecommend(Long postId) {
        Post post = postMapper.selectById(postId);
        String msg="你的帖子："+post.getTitle()+"  ";

        if(post.getRecommend().equals("0")){
            post.setRecommend("1");
        }else {
            post.setRecommend("0");
        }

        if(postMapper.updateById(post)>0){
            if(post.getRecommend().equals("0")){
                addPostNotification(msg+"被取消推荐",post.getUserId(),postId);
            }else{
                addPostNotification(msg+"被推荐",post.getUserId(),postId);
            }
            return true;
        }else{
            return false;
        }
    }
}
