package cn.smxy.forum.mapper;

import cn.smxy.forum.domain.entity.Post;
import cn.smxy.forum.domain.other.UpdatePostBrowseNumber;
import cn.smxy.forum.domain.other.UpdatePostCollectionNumber;
import cn.smxy.forum.domain.other.UpdatePostLikesNumber;
import cn.smxy.forum.domain.other.UpdatePostCommentNumber;
import cn.smxy.forum.domain.param.query.HomePostPageListDTO;
import cn.smxy.forum.domain.param.query.PostManagerPageListDTO;
import cn.smxy.forum.domain.param.update.UpdatePostAuditStatusDTO;
import cn.smxy.forum.domain.vo.PostDetailVo;
import cn.smxy.forum.domain.vo.PostListVo;
import cn.smxy.forum.domain.vo.PostManagerPageListVo;
import cn.smxy.forum.utils.R;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PostMapper extends BaseMapper<Post> {

    /**
     * 获取帖子管理分页数据
     * @param postManagerPageListDTO
     * @return
     */
    List<PostManagerPageListVo> getPostManagerPageListVo(PostManagerPageListDTO postManagerPageListDTO);

    /**
     * 获取用户中心帖子列表(自己的)
     * @param userId
     * @return
     */
    List<PostListVo> getOwnUserPostListVo(@Param("userId") Long userId);

    /**
     * 获取用户中心帖子列表(他人的)
     * @param userId 自己的ID
     * @param otherUserId 他人的ID
     * @return
     */
    List<PostListVo> getOtherUserPostListVo(@Param("userId") Long userId,@Param("otherUserId") Long otherUserId);

    /**
     * 获取用户收藏的帖子列表
     * @param userId
     * @return
     */
    List<PostListVo> getUserCollection(@Param("userId") Long userId);

    /**
     * 获取首页帖子列表
     * @param userId
     * @param postPageListDTO
     * @param orderType 排序 1-创建时间 2-点赞数
     * @return
     */
    List<PostListVo> getHomePostList(@Param("userId") Long userId
            ,@Param("postPageListDTO") HomePostPageListDTO postPageListDTO,@Param("orderType") String orderType);

    /**
     * 点击帖子时的帖子详情
     * @param userId 登录用户ID
     * @param postId 帖子ID
     * @return
     */
    PostDetailVo getPostDetailToView(@Param("userId") Long userId,@Param("postId") Long postId);

    /**
     * 修改帖子点赞数
     * @return
     */
    Integer updatePostLikesNumber(List<UpdatePostLikesNumber> updatePostLikesNumber);

    /**
     * 修改帖子收藏数
     * @return
     */
    Integer updatePostCollectionNumber(List<UpdatePostCollectionNumber> updatePostCollectionNumber);

    /**
     * 修改帖子评论数
     * @return
     */
    Integer updatePostCommentNumber(List<UpdatePostCommentNumber> updatePostCommentNumber);


    /**
     * 修改帖子浏览量
     * @return
     */
    Integer updatePostBrowseNumber(List<UpdatePostBrowseNumber> updatePostBrowseNumber);

    /**
     * 获取用户帖子数量
     */
    Long getPostCount(@Param("userId") Long userId);
}
