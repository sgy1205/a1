package cn.smxy.forum.mapper;

import cn.smxy.forum.domain.entity.Concern;
import cn.smxy.forum.domain.vo.ConcernListVo;
import cn.smxy.forum.domain.vo.FansListVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConcernMapper extends BaseMapper<Concern> {

    /**
     * 获取关注用户列表
     * @param userId
     * @return
     */
    List<ConcernListVo> getConcernList(@Param("userId") Long userId);

    /**
     * 获取粉丝列表
     * @param userId
     * @return
     */
    List<FansListVo> getFansList(@Param("userId") Long userId);

    /**
     * 获取用户中心数据关注用户列表
     * @param userId
     * @return
     */
    List<ConcernListVo> getUserCenterConcernList(@Param("userId") Long userId);

    /**
     * 获取用户中心数据粉丝列表
     * @param userId
     * @return
     */
    List<FansListVo> getUserCenterFansList(@Param("userId") Long userId);

}
