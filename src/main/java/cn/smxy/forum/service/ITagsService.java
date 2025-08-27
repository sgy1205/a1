package cn.smxy.forum.service;

import cn.smxy.forum.domain.entity.Tags;
import cn.smxy.forum.domain.vo.TagsListVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ITagsService extends IService<Tags> {

    /**
     * 获取推荐的标签列表
     * @return
     */
    List<Tags> getRecommendTagsList();

    /**
     * 获取帖子绑定的标签列表
     * @param postId
     * @return
     */
    List<TagsListVo> getTagsListVoByPostId(Long postId);

}
