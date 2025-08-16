package cn.smxy.forum.mapper;

import cn.smxy.forum.domain.entity.Post;
import cn.smxy.forum.domain.param.query.PostManagerPageListDTO;
import cn.smxy.forum.domain.param.update.UpdatePostAuditStatusDTO;
import cn.smxy.forum.domain.vo.PostManagerPageListVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface PostMapper extends BaseMapper<Post> {

    /**
     * 获取帖子管理分页数据
     * @param postManagerPageListDTO
     * @return
     */
    List<PostManagerPageListVo> getPostManagerPageListVo(PostManagerPageListDTO postManagerPageListDTO);

}
