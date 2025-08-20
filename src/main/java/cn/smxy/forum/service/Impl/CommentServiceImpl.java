package cn.smxy.forum.service.Impl;

import cn.smxy.forum.domain.entity.Comment;
import cn.smxy.forum.mapper.CommentMapper;
import cn.smxy.forum.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {
}
