package cn.smxy.forum.service.Impl;

import cn.smxy.forum.domain.entity.*;
import cn.smxy.forum.domain.other.UpdateCommentLikesNumber;
import cn.smxy.forum.domain.param.insert.AddCommentDTO;
import cn.smxy.forum.domain.vo.CommentListVo;
import cn.smxy.forum.mapper.CommentMapper;
import cn.smxy.forum.service.ICommentService;
import cn.smxy.forum.service.IPostService;
import cn.smxy.forum.utils.RedisUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static cn.smxy.forum.constant.Constants.*;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private IPostService postService;

    @Override
    public Long getCommentCount(Long userId) {
        LambdaQueryWrapper<Comment> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Comment::getUserId, userId)
                .eq(Comment::getDelFlag,NO_DELETE);

        return commentMapper.selectCount(lqw);
    }

    @Override
    public void incrementCommentLikes(Long commentId, Long count) {
        redisUtil.incrCount(REDIS_COMMENTLIKES_INCRCOUNTKEY,commentId,count);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateCommentLikesNumber(List<UpdateCommentLikesNumber> updateCommentLikesNumbers) {
        return commentMapper.updateCommentLikesNumber(updateCommentLikesNumbers);
    }

    @Override
    public Boolean insertComment(AddCommentDTO commentDTO, Long userId) {
        Comment comment = new Comment();
        comment.setUserId(userId);
        comment.setCommentContent(commentDTO.getCommentContent());
        comment.setCommentType(commentDTO.getCommentType());
        comment.setParentId(commentDTO.getParentId());
        if(comment.getCommentType().equals("0")){
            postService.incrementPostComment(commentDTO.getParentId(),1L);
        }

        this.commentNotification(commentDTO.getCommentType(),userId,commentDTO.getParentId());
        return commentMapper.insert(comment)>0;
    }

    @Override
    public Boolean deleteComment(Comment comment) {
        comment.setDelFlag(DELETE);
        if(comment.getCommentType().equals("0")){
            postService.incrementPostComment(comment.getParentId(),-1L);
        }
        return commentMapper.updateById(comment)>0;
    }

    @Override
    public List<CommentListVo> getCommentTree(Long postId,Long userId) {
        // 一次性查询所有评论（包括所有层级的）
        List<CommentListVo> allComments = commentMapper.selectCommentListVo(postId,userId);

        // 构建树形结构
        return buildCommentTree(allComments);
    }

    @Override
    public List<CommentListVo> buildCommentTree(List<CommentListVo> allComments) {
        // 创建Map用于快速查找评论
        Map<Long, CommentListVo> commentMap = new HashMap<>();
        List<CommentListVo> rootComments = new ArrayList<>();

        // 第一步：将所有评论放入Map，并找出根评论
        for (CommentListVo comment : allComments) {
            commentMap.put(comment.getCommentId(), comment);

            // level为0的是根评论
            if (comment.getLevel() == 0) {
                rootComments.add(comment);
            }
        }

        // 第二步：构建平级的children结构（所有子评论都在同一层级）
        for (CommentListVo comment : allComments) {
            // 如果不是根评论，就找到父评论并添加到children中
            if (comment.getLevel() > 0) {
                CommentListVo parent = findRootParent(comment, commentMap);
                if (parent != null) {
                    if (parent.getChildren() == null) {
                        parent.setChildren(new ArrayList<>());
                    }
                    // 设置回复昵称（如果是回复其他评论）
                    if (comment.getLevel() > 1) {
                        CommentListVo repliedComment = commentMap.get(comment.getParentId());
                        if (repliedComment != null) {
                            comment.setReplyNickName(repliedComment.getNickName());
                        }
                    }else{
                        comment.setReplyNickName(null);
                    }
                    parent.getChildren().add(comment);
                }
            }else {
                comment.setReplyNickName(null);
            }
        }

        return rootComments;
    }

    @Override
    // 找到根父评论（level=0的评论）
    public CommentListVo findRootParent(CommentListVo comment, Map<Long, CommentListVo> commentMap) {
        CommentListVo current = comment;
        while (current.getLevel() > 0) {
            current = commentMap.get(current.getParentId());
            if (current == null) break;
        }
        return current;
    }

    @Override
    public void commentNotification(String type,Long commentUserId,Long relatedId) {
        LoginUser loginUser=redisUtil.getCacheObject("user:" + commentUserId);
        User user = loginUser.getUser();

        Notification notification = new Notification();
        notification.setType("1");
        notification.setRelatedId(relatedId);
        if(type.equals("0")){
            Post post = postService.getById(relatedId);
            if(post.getUserId().equals(commentUserId)){
                return;
            }
            notification.setMessage("你的帖子："+post.getTitle()+" 获得一条新的评论");
            notification.setUserId(post.getUserId());
            notification.setRelatedType("1");
        }else{
            Comment comment = commentMapper.selectById(relatedId);
            if(comment.getUserId().equals(commentUserId)){
                return;
            }
            notification.setUserId(comment.getUserId());
            notification.setMessage("你的评论获得一条新的回复");
            notification.setRelatedType("2");
            notification.setCarrierId(comment.getParentId());
        }
        notification.setOperatorId(user.getUserId());
        notification.setAvatar(user.getAvatar());
        redisUtil.addToListTail(REDIS_NOTIFICATIONS_KEY,notification);
    }

}
