package cn.smxy.forum.service.Impl;

import cn.smxy.forum.domain.entity.Notification;
import cn.smxy.forum.domain.entity.Post;
import cn.smxy.forum.domain.entity.PostAudit;
import cn.smxy.forum.domain.entity.TagsPost;
import cn.smxy.forum.domain.other.UpdatePostBrowseNumber;
import cn.smxy.forum.domain.other.UpdatePostCollectionNumber;
import cn.smxy.forum.domain.other.UpdatePostCommentNumber;
import cn.smxy.forum.domain.other.UpdatePostLikesNumber;
import cn.smxy.forum.domain.param.insert.AddPostDTO;
import cn.smxy.forum.domain.param.query.HomePostPageListDTO;
import cn.smxy.forum.domain.param.query.PostManagerPageListDTO;
import cn.smxy.forum.domain.param.update.UpdatePostDTO;
import cn.smxy.forum.domain.vo.PostDetailVo;
import cn.smxy.forum.domain.vo.PostListVo;
import cn.smxy.forum.domain.vo.PostManagerPageListVo;
import cn.smxy.forum.domain.vo.PostUpdateDetailVo;
import cn.smxy.forum.mapper.PostMapper;
import cn.smxy.forum.mapping.PostMapping;
import cn.smxy.forum.service.IPostAuditService;
import cn.smxy.forum.service.IPostService;
import cn.smxy.forum.service.ITagsPostService;
import cn.smxy.forum.utils.RedisUtil;
import cn.smxy.forum.utils.SecurityUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

import static cn.smxy.forum.constant.Constants.*;

@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements IPostService {

    @Autowired
    private PostMapper postMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    @Lazy
    private IPostAuditService postAuditService;
    @Autowired
    private ITagsPostService tagsPostService;

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
        redisUtil.addToListTail(REDIS_NOTIFICATIONS_KEY, notification);
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

    @Override
    public Long getPostCount(Long userId) {
        LambdaQueryWrapper<Post> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Post::getUserId,userId)
                .eq(Post::getDelFlag,NO_DELETE);
        return postMapper.selectCount(lqw);
    }

    @Override
    public List<PostListVo> getUserCenterPostListVo(Long userId) {
        if(userId.equals(SecurityUtils.getUserId())){
            return postMapper.getOwnUserPostListVo(userId);
        }else{
            return postMapper.getOtherUserPostListVo(SecurityUtils.getUserId(),userId);
        }
    }

    @Override
    public List<PostListVo> getUserCollection(Long userId) {
        return postMapper.getUserCollection(userId);
    }

    @Override
    public List<PostListVo> getHomePostList(Long userId, HomePostPageListDTO postPageListDTO) {
        if(postPageListDTO.getNode().isEmpty() && postPageListDTO.getRecommend().isEmpty() && postPageListDTO.getConcernStatus().isEmpty()){
            return postMapper.getHomePostList(userId,postPageListDTO,"1");
        }else{
            return postMapper.getHomePostList(userId,postPageListDTO,"2");
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addPost(Long userId, AddPostDTO addPostDTO) {
        Post post = PostMapping.INSTANCE.toPost(addPostDTO);
        post.setUserId(userId);
        PostAudit postAudit = new PostAudit();
        if(postMapper.insert(post)>0){
            postAudit.setPostId(post.getPostId());
            if(postAuditService.save(postAudit)){
                post.setPostAuditId(postAudit.getPostAuditId());
                tagsPostService.addTagsPost(post.getPostId(),addPostDTO.getTagsIds());
                return postMapper.updateById(post)>0;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updatePost(Post post, UpdatePostDTO updatePostDTO) {
        post.setTitle(updatePostDTO.getTitle());
        post.setContent(updatePostDTO.getContent());
        post.setNode(updatePostDTO.getNode());

        if(postMapper.updateById(post)>0){
            LambdaQueryWrapper<PostAudit> lqw = new LambdaQueryWrapper<>();
            lqw.eq(PostAudit::getPostId,post.getPostId());
            PostAudit postAudit = postAuditService.getOne(lqw);
            postAudit.setAuditStatus("0");
            if(postAuditService.updateById(postAudit)){
                tagsPostService.deleteTagsPost(post.getPostId());
                return tagsPostService.addTagsPost(post.getPostId(),updatePostDTO.getTagsIds());
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    @Override
    public PostUpdateDetailVo getPostDetail(Long postId) {
        PostUpdateDetailVo postUpdateDetailVo = PostMapping.INSTANCE.toPostUpdateDetailVo(postMapper.selectById(postId));
        LambdaQueryWrapper<TagsPost> lqw = new LambdaQueryWrapper<>();
        lqw.eq(TagsPost::getPostId,postId);
        List<TagsPost> tagsPosts = tagsPostService.list(lqw);
        List<Long> tagIds = tagsPosts.stream()
                .map(TagsPost::getTagsId)  // 假设 TagsPost 有 getTagsId() 方法
                .collect(Collectors.toList());

        postUpdateDetailVo.setTagsIds(tagIds);

        return postUpdateDetailVo;
    }

    @Override
    public PostDetailVo getPostDetailToView(Long userId, Long postId) {
        if(postAuditService.getPostAuditStatus(postId)){
            this.incrementPostBrowse(postId,1L);
        }
        return postMapper.getPostDetailToView(userId,postId);
    }

    @Override
    public void incrementPostLikes(Long postId,Long count) {
        redisUtil.incrCount(REDIS_POSTLIKES_INCRCOUNTKEY,postId,count);
    }

    @Override
    public void incrementPostComment(Long postId,Long count) {
        redisUtil.incrCount(REDIS_POSTCOMMENT_INCRCOUNTKEY,postId,count);
    }

    @Override
    public void incrementPostCollection(Long postId,Long count) {
        redisUtil.incrCount(REDIS_POSTCOLLECTION_INCRCOUNTKEY,postId,count);
    }

    @Override
    public void incrementPostBrowse(Long postId,Long count) {
        redisUtil.incrCount(REDIS_POSTBROWSE_INCRCOUNTKEY,postId,count);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updatePostLikesNumber(List<UpdatePostLikesNumber> updatePostLikesNumber) {
        return postMapper.updatePostLikesNumber(updatePostLikesNumber);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updatePostCollectionNumber(List<UpdatePostCollectionNumber> updatePostCollectionNumber) {
        return postMapper.updatePostCollectionNumber(updatePostCollectionNumber);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updatePostCommentNumber(List<UpdatePostCommentNumber> updatePostCommentNumber) {
        return postMapper.updatePostCommentNumber(updatePostCommentNumber);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updatePostBrowseNumber(List<UpdatePostBrowseNumber> updatePostBrowseNumber) {
        return postMapper.updatePostBrowseNumber(updatePostBrowseNumber);
    }


}
