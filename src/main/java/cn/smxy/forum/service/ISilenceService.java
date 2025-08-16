package cn.smxy.forum.service;

import cn.smxy.forum.domain.entity.Silence;
import cn.smxy.forum.domain.param.insert.AddSilenceDTO;
import cn.smxy.forum.domain.param.update.UpdateSilenceStatusDTO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ISilenceService extends IService<Silence> {

    /**
     * 更新用户禁言状态
     * 定时任务，更新用户禁言状态,每60分钟执行一次
     * @return
     */
    public void updateUserSilenceStatus();

    /**
     * 根据用户ID更新用户禁言状态
     * @param userId
     * @return
     */
    public void updateUserSilenceStatusById(Long userId);

    /**
     * 获取用户最新的禁言记录
     * @param userId
     * @return
     */
    public Silence getSilenceRecord(Long userId);

    /**
     * 获取用户禁言状态
     */
    public String getUserSilenceStatus(Long userId);

    /**
     * 禁言
     */
    public boolean addSilence(AddSilenceDTO addSilenceDTO);

    /**
     * 修改禁言状态
     */
    public boolean updateSilenceStatus(UpdateSilenceStatusDTO updateSilenceStatusDTO);

    /**
     *禁言消息提醒
     * @param userId
     * @param type
     * @param signatureReason
     * @param signatureTime
     * @return
     */
    public boolean silenceRemind(Long userId,String type,String signatureReason,Integer signatureTime);

}
