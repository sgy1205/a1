package cn.smxy.forum.service.Impl;

import cn.smxy.forum.domain.entity.Concern;
import cn.smxy.forum.domain.entity.LoginUser;
import cn.smxy.forum.domain.entity.Notification;
import cn.smxy.forum.domain.entity.User;
import cn.smxy.forum.domain.vo.ConcernListVo;
import cn.smxy.forum.domain.vo.FansListVo;
import cn.smxy.forum.mapper.ConcernMapper;
import cn.smxy.forum.service.IConcernService;
import cn.smxy.forum.service.IUserService;
import cn.smxy.forum.utils.R;
import cn.smxy.forum.utils.RedisUtil;
import cn.smxy.forum.utils.SecurityUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

import static cn.smxy.forum.constant.Constants.*;

@Service
public class ConcernServiceImpl extends ServiceImpl<ConcernMapper, Concern> implements IConcernService {

    @Autowired
    private ConcernMapper concernMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    @Lazy
    private IUserService userService;

    @Override
    public boolean updateConcern(Long userId) {
        LambdaQueryWrapper<Concern> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Concern::getUserId, SecurityUtils.getUserId())
                .eq(Concern::getConcernUserId,userId);
        Concern concern = concernMapper.selectOne(lqw);
        if (concern == null) {
            concern = new Concern();
            concern.setConcernUserId(userId);
            concern.setUserId(SecurityUtils.getUserId());
            if(concernMapper.insert(concern)>0){
                concernRemind(SecurityUtils.getUserId(),userId);
                return true;
            }else {
                return false;
            }
        }else {
            if(concern.getDelFlag().equals(DELETE)){
                concern.setDelFlag(NO_DELETE);
                if(concernMapper.updateById(concern)>0){
                    concernRemind(SecurityUtils.getUserId(),userId);
                    return true;
                }else {
                    return false;
                }
            }else {
                concern.setDelFlag(DELETE);
                return concernMapper.updateById(concern)>0;
            }
        }
    }

    @Override
    public List<ConcernListVo> getConcernList(Long userId) {
        return concernMapper.getConcernList(userId);
    }

    @Override
    public List<FansListVo> getFansList(Long userId) {
        return concernMapper.getFansList(userId);
    }

    @Override
    public void concernRemind(Long userId, Long concernId) {
        LoginUser loginUser = redisUtil.getCacheObject("user:" + userId);
        User user=loginUser.getUser();
        Notification notification = new Notification();
        notification.setUserId(concernId);
        notification.setType("1");
        notification.setMessage(user.getNickName()+" 关注了你");
        notification.setRelatedId(userId);
        notification.setRelatedType("0");
        notification.setNotificationId(userId);
        notification.setAvatar(user.getAvatar());

        redisUtil.addToListTail(REDIS_NOTIFICATIONS_KEY,notification);
    }

    @Override
    public List<ConcernListVo> getUserCenterConcernList(Long userId) {
        return concernMapper.getUserCenterConcernList(userId);
    }

    @Override
    public List<FansListVo> getUserCenterFansList(Long userId) {
        return concernMapper.getUserCenterFansList(userId);
    }

    @Override
    public Long getConcernCount(Long userId) {
        LambdaQueryWrapper<Concern> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Concern::getUserId,userId)
                        .eq(Concern::getDelFlag, NO_DELETE);

        return concernMapper.selectCount(lqw);
    }

    @Override
    public Long getFansCount(Long userId) {
        LambdaQueryWrapper<Concern> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Concern::getConcernUserId,userId)
                .eq(Concern::getDelFlag, NO_DELETE);

        return concernMapper.selectCount(lqw);
    }
}
