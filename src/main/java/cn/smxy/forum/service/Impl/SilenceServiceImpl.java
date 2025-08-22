package cn.smxy.forum.service.Impl;

import cn.smxy.forum.domain.entity.Notification;
import cn.smxy.forum.domain.entity.Silence;
import cn.smxy.forum.domain.entity.User;
import cn.smxy.forum.domain.param.insert.AddSilenceDTO;
import cn.smxy.forum.domain.param.update.UpdateSilenceStatusDTO;
import cn.smxy.forum.mapper.SilenceMapper;
import cn.smxy.forum.service.ISilenceService;
import cn.smxy.forum.service.IUserService;
import cn.smxy.forum.utils.RedisUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static cn.smxy.forum.constant.Constants.REDIS_NOTIFICATIONS_KEY;

@Service
public class SilenceServiceImpl extends ServiceImpl<SilenceMapper, Silence> implements ISilenceService {

    @Autowired
    private SilenceMapper SilenceMapper;
    @Autowired
    private IUserService userService;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void updateUserSilenceStatusById(Long userId) {
        silenceRemind(userId,"1",null,null);
        SilenceMapper.updateUserSilenceStatusById(userId);
    }

    @Override
    public Silence getSilenceRecord(Long userId) {
        return SilenceMapper.getSilenceRecord(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String getUserSilenceStatus(Long userId) {
        User user=userService.getById(userId);
        if(user.getSilenceStatus().equals("1")){
            updateUserSilenceStatusById(userId);
            user=userService.getById(userId);
        }
        return user.getSilenceStatus();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addSilence(AddSilenceDTO addSilenceDTO) {
        Silence silence=new Silence();
        silence.setUserId(addSilenceDTO.getUserId());
        silence.setType(addSilenceDTO.getSilenceType());
        silence.setSignatureReason(addSilenceDTO.getSignatureReason());

        User user=userService.getById(addSilenceDTO.getUserId());

        Date now=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);

        silence.setCreateTime(now);

        if(silence.getType().equals("1")){
            calendar.add(Calendar.YEAR,100);
            silence.setSignatureTime(calendar.getTime());
            user.setSilenceStatus("2");
            silenceRemind(addSilenceDTO.getUserId(),"0",addSilenceDTO.getSignatureReason(),36500);
        }else {
            if(addSilenceDTO.getSignatureTime()==null || addSilenceDTO.getSignatureTime()<0){
                return false;
            }
            calendar.add(Calendar.DAY_OF_YEAR, addSilenceDTO.getSignatureTime());
            silence.setSignatureTime(calendar.getTime());
            user.setSilenceStatus("1");
            silenceRemind(addSilenceDTO.getUserId(),"0",addSilenceDTO.getSignatureReason(),addSilenceDTO.getSignatureTime());
        }
        if(SilenceMapper.insert(silence)>0){
            return userService.updateById(user);
        }else{
            return false;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateSilenceStatus(UpdateSilenceStatusDTO updateSilenceStatusDTO) {
        User user=userService.getById(updateSilenceStatusDTO.getUserId());
        Silence silenceRecord = SilenceMapper.getSilenceRecord(updateSilenceStatusDTO.getUserId());
        Date date=silenceRecord.getSignatureTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if(updateSilenceStatusDTO.getSilenceStatus().equals("0")){
            silenceRecord.setDelFlag("2");
            user.setSilenceStatus("0");
            silenceRemind(updateSilenceStatusDTO.getUserId(), "1",null,null);
        }else if(updateSilenceStatusDTO.getSilenceStatus().equals("1")){
            if(updateSilenceStatusDTO.getSilenceTime()==null || updateSilenceStatusDTO.getSilenceTime()<0){
                return false;
            }
            calendar.add(Calendar.DAY_OF_YEAR,updateSilenceStatusDTO.getSilenceTime());
            user.setSilenceStatus("1");
            silenceRecord.setSignatureTime(calendar.getTime());
            silenceRemind(updateSilenceStatusDTO.getUserId(),"2",null,updateSilenceStatusDTO.getSilenceTime());
        }else {
            calendar.add(Calendar.YEAR,100);
            silenceRecord.setType("1");
            user.setSilenceStatus("2");
            silenceRecord.setSignatureTime(calendar.getTime());

            silenceRemind(updateSilenceStatusDTO.getUserId(),"2",null,36500);
        }

        return userService.updateById(user)&&SilenceMapper.updateById(silenceRecord)>0;
    }

    @Override
    public void silenceRemind(Long userId, String type, String signatureReason, Integer signatureTime) {
        Notification notification=new Notification();
        notification.setUserId(userId);
        notification.setType("0");
        if(type.equals("0")){
            notification.setMessage("你已被禁言，禁言原因为："+signatureReason+",禁言时间为："+signatureTime+"天");
        }else if(type.equals("1")){
            notification.setMessage("你的禁言状态已取消");
        }else if(type.equals("2")){
            notification.setMessage("你的禁言状态被修改，禁言时间为"+signatureTime+"天");
        }
        redisUtil.addToListTail(REDIS_NOTIFICATIONS_KEY,notification);
    }

    @Override
    public List<Long> getSilenceCancelUserIdList() {
        return SilenceMapper.getSilenceCancelUserIdList();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateUserSilenceStatus() {
        return SilenceMapper.updateUserSilenceStatus();
    }

    @Override
    public void batchRelieveSilenceRemind(List<Long> userIds) {
        if (userIds == null || userIds.isEmpty()) {
            return;
        }

        List<Notification> notifications = new ArrayList<>();
        for (Long userId : userIds) {
            Notification notification = new Notification();
            notification.setUserId(userId);
            notification.setMessage("你的禁言状态已取消");
            notification.setType("0");
            notifications.add(notification);
        }
        redisUtil.addAllToListTail(REDIS_NOTIFICATIONS_KEY, notifications);
    }

}
