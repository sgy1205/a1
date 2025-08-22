package cn.smxy.forum.service;

import cn.smxy.forum.domain.entity.Post;
import cn.smxy.forum.domain.param.insert.AddPostDTO;
import cn.smxy.forum.domain.param.query.HomePostPageListDTO;
import cn.smxy.forum.domain.param.query.PostManagerPageListDTO;
import cn.smxy.forum.domain.param.update.UpdatePostDTO;
import cn.smxy.forum.domain.vo.PostManagerPageListVo;
import cn.smxy.forum.domain.vo.PostListVo;
import cn.smxy.forum.domain.vo.PostUpdateDetailVo;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
