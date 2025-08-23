package cn.smxy.forum.service;

import cn.smxy.forum.domain.entity.Post;
import cn.smxy.forum.domain.other.UpdatePostBrowseNumber;
import cn.smxy.forum.domain.other.UpdatePostCollectionNumber;
import cn.smxy.forum.domain.other.UpdatePostCommentNumber;
import cn.smxy.forum.domain.other.UpdatePostLikesNumber;
import cn.smxy.forum.domain.param.insert.AddPostDTO;
import cn.smxy.forum.domain.param.query.HomePostPageListDTO;
import cn.smxy.forum.domain.param.query.PostManagerPageListDTO;
import cn.smxy.forum.domain.param.update.UpdatePostDTO;
import cn.smxy.forum.domain.vo.PostDetailVo;
import cn.smxy.forum.domain.vo.PostManagerPageListVo;
import cn.smxy.forum.domain.vo.PostListVo;
import cn.smxy.forum.domain.vo.PostUpdateDetailVo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IPostService extends IService<Post> {

    /**
     * 获取帖子管理分页数据
     * @param postManagerPageListDTO
     * @return
     */
    List<PostManagerPageListVo> getPostManagerPageListVo(PostManagerPageListDTO postManagerPageListDTO);

    /**
     * 添加帖子消息通知
     */
    void addPostNotification(String msg,Long userId,Long postId);

    /**
     * 修改帖子推荐状态
     * @param postId
     * @return
     */
    boolean postManagerRecommend(Long postId);

    /**
     * 获取用户发的帖子总数
     * @param userId
     * @return
     */
    Long getPostCount(Long userId);

    /**
     * 获取用户中心帖子列表
     * @param userId
     * @return
     */
    List<PostListVo> getUserCenterPostListVo(Long userId);

    /**
     * 获取用户收藏的帖子列表
     * @param userId
     * @return
     */
    List<PostListVo> getUserCollection(Long userId);

    /**
     * 获取首页帖子列表
     * @param userId
     * @param postPageListDTO
     * @return
     */
    List<PostListVo> getHomePostList(Long userId, HomePostPageListDTO postPageListDTO);

    /**
     * 添加帖子
     * @param userId
     * @param addPostDTO
     * @return
     */
    Boolean addPost(Long userId, AddPostDTO addPostDTO);

    /**
     * 修改帖子
     * @param post
     * @param updatePostDTO
     * @return
     */
    Boolean updatePost(Post post, UpdatePostDTO updatePostDTO);

    /**
     * 获取帖子修改时的帖子详情
     * @param postId
     * @return
     */
    PostUpdateDetailVo getPostDetail(Long postId);

    /**
     * 点击帖子时的帖子详情
     * @param userId 登录用户ID
     * @param postId 帖子ID
     * @return
     */
    PostDetailVo getPostDetailToView(Long userId,Long postId);

    /**
     * 帖子点赞数修改(redis)
     * @param postId
     * @return
     */
    void incrementPostLikes(Long postId,Long count);

    /**
     * 帖子评论数修改(redis)
     * @param postId
     * @return
     */
    void incrementPostComment(Long postId,Long count);

    /**
     * 帖子收藏数修改(redis)
     * @param postId
     * @return
     */
    void incrementPostCollection(Long postId,Long count);

    /**
     * 帖子浏览数修改(redis)
     * @param postId
     * @return
     */
    void incrementPostBrowse(Long postId,Long count);

    /**
     * 修改帖子点赞数(数据库)
     * @return
     */
    Integer updatePostLikesNumber(List<UpdatePostLikesNumber> updatePostLikesNumber);

    /**
     * 修改帖子收藏数(数据库)
     * @return
     */
    Integer updatePostCollectionNumber(List<UpdatePostCollectionNumber> updatePostCollectionNumber);

    /**
     * 修改帖子评论数(数据库)
     * @return
     */
    Integer updatePostCommentNumber(List<UpdatePostCommentNumber> updatePostCommentNumber);


    /**
     * 修改帖子浏览量(数据库)
     * @return
     */
    Integer updatePostBrowseNumber(List<UpdatePostBrowseNumber> updatePostBrowseNumber);
}
