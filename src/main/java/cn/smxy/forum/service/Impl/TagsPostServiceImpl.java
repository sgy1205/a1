package cn.smxy.forum.service.Impl;

import cn.smxy.forum.domain.entity.TagsPost;
import cn.smxy.forum.mapper.TagsPostMapper;
import cn.smxy.forum.service.ITagsPostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class TagsPostServiceImpl extends ServiceImpl<TagsPostMapper, TagsPost> implements ITagsPostService {
}
