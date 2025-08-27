package cn.smxy.forum.service.Impl;

import cn.smxy.forum.domain.entity.Tags;
import cn.smxy.forum.domain.vo.TagsListVo;
import cn.smxy.forum.mapper.TagsMapper;
import cn.smxy.forum.service.ITagsService;
import cn.smxy.forum.utils.RedisUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class TagsServiceImpl extends ServiceImpl<TagsMapper, Tags> implements ITagsService {

    @Autowired
    private TagsMapper tagsMapper;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public List<Tags> getRecommendTagsList() {
        String verifyKey="recommendTagsList";
        List<Tags> list = redisUtil.getCacheObject(verifyKey);
        if (list == null) {
            LambdaQueryWrapper<Tags> lqw = new LambdaQueryWrapper<>();
            lqw.orderByDesc(Tags::getCreateTime).last("limit 5");
            list = tagsMapper.selectList(lqw);
            redisUtil.setCacheObject(verifyKey, list,1440, TimeUnit.MINUTES);
        }
        return list;
    }

    @Override
    public List<TagsListVo> getTagsListVoByPostId(Long postId) {
        return tagsMapper.getTagsListVoByPostId(postId);
    }
}
