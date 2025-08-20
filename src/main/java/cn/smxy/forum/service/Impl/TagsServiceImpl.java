package cn.smxy.forum.service.Impl;

import cn.smxy.forum.domain.entity.Tags;
import cn.smxy.forum.mapper.TagsMapper;
import cn.smxy.forum.service.ITagsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class TagsServiceImpl extends ServiceImpl<TagsMapper, Tags> implements ITagsService {
}
