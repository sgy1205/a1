package cn.smxy.forum.service;

import cn.smxy.forum.domain.entity.TagsPost;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ITagsPostService extends IService<TagsPost> {

    /**
     * 批量添加帖子和标签关联数据
     * @param postId
     * @param tagsIds
     * @return
     */
    Boolean addTagsPost(Long postId, List<Long> tagsIds);

    /**
     * 删除帖子和标签关联数据
     * @param postId
     * @return
     */
    Integer deleteTagsPost(Long postId);

}
