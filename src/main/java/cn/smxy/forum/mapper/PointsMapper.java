package cn.smxy.forum.mapper;

import cn.smxy.forum.domain.entity.Points;
import cn.smxy.forum.domain.vo.UserPointsRankVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface PointsMapper extends BaseMapper<Points> {

    /**
     * 获取登录积分排行列表
     * @return
     */
    List<UserPointsRankVo> getUserPointsRank();

}
