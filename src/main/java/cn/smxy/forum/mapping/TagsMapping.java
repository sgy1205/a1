package cn.smxy.forum.mapping;

import cn.smxy.forum.domain.entity.Tags;
import cn.smxy.forum.domain.vo.TagsListVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TagsMapping {

    TagsMapping INSTANCE= Mappers.getMapper(TagsMapping.class);

    TagsListVo toTagsListVo(Tags tags);

    List<TagsListVo> toTagsListVos(List<Tags> tags);

}
