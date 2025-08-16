package cn.smxy.forum.service.Impl;

import cn.smxy.forum.domain.entity.Silence;
import cn.smxy.forum.domain.entity.User;
import cn.smxy.forum.domain.param.insert.AddSilenceDTO;
import cn.smxy.forum.domain.param.update.UpdateSilenceStatusDTO;
import cn.smxy.forum.mapper.SilenceMapper;
import cn.smxy.forum.mapper.UserMapper;
import cn.smxy.forum.service.ISilenceService;
import cn.smxy.forum.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class SilenceServiceImpl extends ServiceImpl<SilenceMapper, Silence> implements ISilenceService {

    @Autowired
    private SilenceMapper SilenceMapper;
    @Autowired
    private IUserService userService;

    @Override
    @Scheduled(cron = "0 0 * * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void updateUserSilenceStatus() {
        List<Long> userIdList = SilenceMapper.getSilenceCancelUserIdList();
        if(userIdList.size()>0){
            for (int i = 0; i < userIdList.size(); i++) {
                silenceRemind(userIdList.get(i),"1",null,null);
            }
            SilenceMapper.updateUserSilenceStatus();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUserSilenceStatusById(Long userId) {
        silenceRemind(userId,"1",null,null);
        SilenceMapper.updateUserSilenceStatusById(userId);
    }

    @Override
    public Silence getSilenceRecord(Long userId) {
        return SilenceMapper.getSilenceRecord(userId);
    }

    @Override
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
    public boolean silenceRemind(Long userId, String type, String signatureReason, Integer signatureTime) {
        return false;
    }

}
