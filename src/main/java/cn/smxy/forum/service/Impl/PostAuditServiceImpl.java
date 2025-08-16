package cn.smxy.forum.service.Impl;

import cn.smxy.forum.domain.entity.Post;
import cn.smxy.forum.domain.entity.PostAudit;
import cn.smxy.forum.domain.param.update.UpdatePostAuditStatusDTO;
import cn.smxy.forum.mapper.PostAuditMapper;
import cn.smxy.forum.mapper.PostMapper;
import cn.smxy.forum.service.IPostAuditService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostAuditServiceImpl extends ServiceImpl<PostAuditMapper, PostAudit> implements IPostAuditService {

    @Autowired
    private PostAuditMapper postAuditMapper;
    @Autowired
    private PostMapper postMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updatePostAuditStatus(UpdatePostAuditStatusDTO updatePostAuditStatusDTO) {
        return postAuditMapper.updatePostAuditStatus(updatePostAuditStatusDTO);
    }

}
