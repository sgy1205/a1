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

}
