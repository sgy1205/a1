package cn.smxy.forum.service;

import cn.smxy.forum.domain.entity.Concern;
import cn.smxy.forum.domain.vo.ConcernListVo;
import cn.smxy.forum.domain.vo.FansListVo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IConcernService extends IService<Concern> {

    /**
     * 修改对用户的关注状态
     * @param userId 关注的用户
     * @return
     */
    boolean updateConcern(Long userId);

    /**
     * 获取关注用户列表
     * @param userId
     * @return
     */
    List<ConcernListVo> getConcernList(Long userId);

    /**
     * 获取粉丝列表
     * @param userId
     * @return
     */
    List<FansListVo> getFansList( Long userId);

    /**
     * 关注的消息提醒
     * @param userId 操作的用户
     * @param concernId 关注的用户
     */
    void concernRemind(Long userId,Long concernId);

    /**
     * 获取用户中心数据关注用户列表
     * @param userId
     * @return
     */
    List<ConcernListVo> getUserCenterConcernList(Long userId);

    /**
     * 获取用户中心数据粉丝列表
     * @param userId
     * @return
     */
    List<FansListVo> getUserCenterFansList(Long userId);

    /**
     * 获取关注的用户总数
     * @param userId
     * @return
     */
    Long getConcernCount(Long userId);

    /**
     * 获取粉丝总数
     * @param userId
     * @return
     */
    Long getFansCount(Long userId);

}
