package cn.smxy.forum.service;

import cn.smxy.forum.domain.entity.Check;
import cn.smxy.forum.utils.R;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ICheckService extends IService<Check> {

    /**
     * 获取当前用户签到状态
     * @param userId
     * @return true 已签到  false 未签到
     */
    boolean getCheckStatus(Long userId);

    /**
     * 签到
     * @param userId
     * @return
     */
    R check(Long userId);

    /**
     * 获取用户连续签到数量
     * @param userId
     * @return
     */
    Integer getCheckNum(Long userId);

}
