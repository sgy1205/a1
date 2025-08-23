package cn.smxy.forum.mapper;

import cn.smxy.forum.domain.entity.Comment;
import cn.smxy.forum.domain.other.UpdateCommentLikesNumber;
import cn.smxy.forum.domain.vo.CommentListVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper extends BaseMapper<Comment> {

    /**
     * 修改评论点赞数(数据库)
     * @return
     */
    Integer updateCommentLikesNumber(List<UpdateCommentLikesNumber> updateCommentLikesNumbers);

    /**
     * 获取评论区评论列表
     * @return
     */
    List<CommentListVo> selectCommentListVo(@Param("userId") Long userId,@Param("postId") Long postId);

}
