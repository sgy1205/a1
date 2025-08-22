package cn.smxy.forum.mapper;

import cn.smxy.forum.domain.entity.Post;
import cn.smxy.forum.domain.param.query.HomePostPageListDTO;
import cn.smxy.forum.domain.param.query.PostManagerPageListDTO;
import cn.smxy.forum.domain.param.update.UpdatePostAuditStatusDTO;
import cn.smxy.forum.domain.vo.PostListVo;
import cn.smxy.forum.domain.vo.PostManagerPageListVo;
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
     * @return
     */
    List<PostListVo> getHomePostList(@Param("userId") Long userId,@Param("postPageListDTO") HomePostPageListDTO postPageListDTO);

}
