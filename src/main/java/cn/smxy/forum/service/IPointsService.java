package cn.smxy.forum.service;

import cn.smxy.forum.domain.entity.Points;
import cn.smxy.forum.domain.vo.UserPointsRankVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IPointsService extends IService<Points> {

    /**
     * 添加积分记录
     * @param userId 用户ID
     * @param pointType 获取积分类型 0-签到 1-发贴
     * @return
     */
    boolean addPoints(Long userId,String pointType);

    /**
     * 获取用户积分数量
     * @param userId
     * @return
     */
    Long getUserPoints(Long userId);

    /**
     * 获取登录积分排行列表
     * @return
     */
    List<UserPointsRankVo> getUserPointsRank();

}
