package cn.smxy.forum.mapper;

import cn.smxy.forum.domain.entity.Silence;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SilenceMapper extends BaseMapper<Silence> {

    /**
     * 更新用户禁言状态
     * @return
     */
    public Integer updateUserSilenceStatus();

    /**
     * 根据用户ID更新用户禁言状态
     * @param userId
     * @return
     */
    public Integer updateUserSilenceStatusById(@Param("userId")Long userId);

    /**
     * 获取用户最新的禁言记录
     * @param userId
     * @return
     */
    public Silence getSilenceRecord(@Param("userId") Long userId);

    /**
     * 获取禁言时间结束，但仍然被禁言的用户ID
     * @return
     */
    public List<Long> getSilenceCancelUserIdList();

}
