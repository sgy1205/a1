package cn.smxy.forum.service.Impl;

import cn.smxy.forum.domain.entity.Likes;
import cn.smxy.forum.mapper.LikesMapper;
import cn.smxy.forum.service.ILikesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class LikesServiceImpl extends ServiceImpl<LikesMapper, Likes> implements ILikesService {
}
