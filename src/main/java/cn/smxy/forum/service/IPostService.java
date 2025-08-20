package cn.smxy.forum.service;

import cn.smxy.forum.domain.entity.Post;
import cn.smxy.forum.domain.param.query.PostManagerPageListDTO;
import cn.smxy.forum.domain.vo.PostManagerPageListVo;
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
}
