package cn.smxy.forum.service.Impl;

import cn.smxy.forum.domain.entity.Post;
import cn.smxy.forum.domain.param.query.PostManagerPageListDTO;
import cn.smxy.forum.domain.vo.PostManagerPageListVo;
import cn.smxy.forum.mapper.PostMapper;
import cn.smxy.forum.service.IPostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements IPostService {

    @Autowired
    private PostMapper postMapper;

    @Override
    public List<PostManagerPageListVo> getPostManagerPageListVo(PostManagerPageListDTO postManagerPageListDTO) {
        return postMapper.getPostManagerPageListVo(postManagerPageListDTO);
    }
}
