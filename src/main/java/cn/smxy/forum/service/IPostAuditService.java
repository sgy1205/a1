package cn.smxy.forum.service;

import cn.smxy.forum.domain.entity.PostAudit;
import cn.smxy.forum.domain.param.update.UpdatePostAuditStatusDTO;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IPostAuditService extends IService<PostAudit> {

    /**
     * 更新帖子的审核状态
     * @param updatePostAuditStatusDTO
     * @return
     */
    Integer updatePostAuditStatus(UpdatePostAuditStatusDTO updatePostAuditStatusDTO);

}
