package cn.smxy.forum.service;

import cn.smxy.forum.domain.entity.Points;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IPointsService extends IService<Points> {

    /**
     * 添加积分记录
     * @param userId 用户ID
     * @param pointType 获取积分类型 0-签到 1-发贴
     * @return
     */
    boolean addPoints(Long userId,String pointType);

}
