package cn.smxy.forum.service.Impl;

import cn.smxy.forum.domain.entity.TagsPost;
import cn.smxy.forum.mapper.TagsPostMapper;
import cn.smxy.forum.service.ITagsPostService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagsPostServiceImpl extends ServiceImpl<TagsPostMapper, TagsPost> implements ITagsPostService {

    @Autowired
    private TagsPostMapper tagsPostMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addTagsPost(Long postId, List<Long> tagsIds) {
        if (CollectionUtils.isEmpty(tagsIds)) {
            return false;
        }

        // 构建实体列表
        List<TagsPost> postTags = tagsIds.stream()
                .map(tagId -> {
                    TagsPost tagsPost = new TagsPost();
                    tagsPost.setPostId(postId);
                    tagsPost.setTagsId(tagId);
                    return tagsPost;
                })
                .collect(Collectors.toList());

        // 批量插入
        return this.saveBatch(postTags);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteTagsPost(Long postId) {
        LambdaQueryWrapper<TagsPost> lqw = new LambdaQueryWrapper<>();
        lqw.eq(TagsPost::getPostId, postId);
        return tagsPostMapper.delete(lqw);
    }
}
