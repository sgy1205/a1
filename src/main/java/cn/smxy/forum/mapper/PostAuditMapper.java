package cn.smxy.forum.mapper;

import cn.smxy.forum.domain.entity.PostAudit;
import cn.smxy.forum.domain.param.update.UpdatePostAuditStatusDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface PostAuditMapper extends BaseMapper<PostAudit>{

    /**
     * 更新帖子的审核状态
     * @param updatePostAuditStatusDTO
     * @return
     */
    Integer updatePostAuditStatus(UpdatePostAuditStatusDTO updatePostAuditStatusDTO);

}
