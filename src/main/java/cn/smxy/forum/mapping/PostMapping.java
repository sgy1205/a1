package cn.smxy.forum.mapping;

import cn.smxy.forum.domain.entity.Post;
import cn.smxy.forum.domain.param.insert.AddPostDTO;
import cn.smxy.forum.domain.vo.PostUpdateDetailVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapping {

    PostMapping INSTANCE= Mappers.getMapper(PostMapping.class);

    @Mapping(target = "browseNumber", ignore = true)
    Post toPost(AddPostDTO addPostDTO);

    @Mapping(target = "tagsIds", ignore = true)
    PostUpdateDetailVo toPostUpdateDetailVo(Post post);
}
