package cn.smxy.forum.mapper;

import cn.smxy.forum.domain.entity.Tags;
import cn.smxy.forum.domain.vo.TagsListVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TagsMapper extends BaseMapper<Tags> {

    /**
     * 获取帖子绑定的标签列表
     * @param postId
     * @return
     */
    List<TagsListVo> getTagsListVoByPostId(@Param("postId") Long postId);

}
