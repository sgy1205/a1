package cn.smxy.forum.service.Impl;

import cn.smxy.forum.domain.entity.Post;
import cn.smxy.forum.domain.entity.PostAudit;
import cn.smxy.forum.domain.param.update.UpdatePostAuditStatusDTO;
import cn.smxy.forum.mapper.PostAuditMapper;
import cn.smxy.forum.mapper.PostMapper;
import cn.smxy.forum.service.IPointsService;
import cn.smxy.forum.service.IPostAuditService;
import cn.smxy.forum.service.IPostService;
import cn.smxy.forum.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostAuditServiceImpl extends ServiceImpl<PostAuditMapper, PostAudit> implements IPostAuditService {

    @Autowired
    private PostAuditMapper postAuditMapper;
    @Autowired
    private IPostService postService;
    @Autowired
    private IPointsService pointsService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updatePostAuditStatus(UpdatePostAuditStatusDTO updatePostAuditStatusDTO) {
        Post post = postService.getById(updatePostAuditStatusDTO.getPostId());
        if(postAuditMapper.updatePostAuditStatus(updatePostAuditStatusDTO)>0){
            if(updatePostAuditStatusDTO.getAuditStatus()==1){
                postService.addPostNotification("你的帖子："+post.getTitle()+"  审核通过",post.getUserId(),post.getPostId());
                pointsService.addPoints(post.getUserId(),"1");
            }else if(updatePostAuditStatusDTO.getAuditStatus()==2){
                postService.addPostNotification("你的帖子："+post.getTitle()+"  审核未通过",post.getUserId(),post.getPostId());
            }
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public Boolean getPostAuditStatus(Long postId) {
        LambdaQueryWrapper<PostAudit> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PostAudit::getPostId, postId);
        PostAudit postAudit = postAuditMapper.selectOne(lqw);
        if(postAudit.getAuditStatus().equals("1")){
            return true;
        }else {
            return false;
        }
    }
}
