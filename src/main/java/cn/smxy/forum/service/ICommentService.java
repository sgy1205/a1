package cn.smxy.forum.service;

import cn.smxy.forum.domain.entity.Comment;
import cn.smxy.forum.domain.other.UpdateCommentLikesNumber;
import cn.smxy.forum.domain.param.insert.AddCommentDTO;
import cn.smxy.forum.domain.vo.CommentListVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface ICommentService extends IService<Comment> {

    /**
     * 获取用户评论总数
     * @param userId
     * @return
     */
    Long getCommentCount(Long userId);

    /**
     * 评论点赞数修改(redis)
     * @param commentId
     * @return
     */
    void incrementCommentLikes(Long commentId,Long count);

    /**
     * 修改评论点赞数(数据库)
     * @return
     */
    Integer updateCommentLikesNumber(List<UpdateCommentLikesNumber> updateCommentLikesNumbers);

    /**
     * 添加评论
     * @param commentDTO
     * @param userId 登录用户ID
     * @return
     */
    Boolean insertComment(AddCommentDTO commentDTO, Long userId);

    /**
     * 删除评论
     * @param comment
     * @return
     */
    Boolean deleteComment(Comment comment);

    /**
     * 获取评论区
     * @param postId
     * @param userId
     * @return
     */
    List<CommentListVo> getCommentTree(Long postId,Long userId);

    /**
     * 构建平级的children结构
     * @param allComments
     * @return
     */
    List<CommentListVo> buildCommentTree(List<CommentListVo> allComments);

    /**
     * 找到根父评论（level=0的评论）
     * @param comment
     * @param commentMap
     * @return
     */
    CommentListVo findRootParent(CommentListVo comment, Map<Long, CommentListVo> commentMap);

    /**
     * 评论消息提醒
     * @param type 类别 0-帖子 1-评论
     * @param userId 接收消息的用户ID
     * @param commentUserId 评论的用户ID
     * @param relatedId 评论的帖子或评论ID
     */
    void commentNotification(String type,Long userId,Long commentUserId,Long relatedId);
}
