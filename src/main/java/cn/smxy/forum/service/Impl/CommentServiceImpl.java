package cn.smxy.forum.service.Impl;

import cn.smxy.forum.domain.entity.Comment;
import cn.smxy.forum.mapper.CommentMapper;
import cn.smxy.forum.service.ICommentService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static cn.smxy.forum.constant.Constants.*;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public Long getCommentCount(Long userId) {
        LambdaQueryWrapper<Comment> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Comment::getUserId, userId)
                .eq(Comment::getDelFlag,NO_DELETE);

        return commentMapper.selectCount(lqw);
    }
}
