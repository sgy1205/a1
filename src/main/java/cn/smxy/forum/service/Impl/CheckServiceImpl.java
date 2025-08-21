package cn.smxy.forum.service.Impl;

import cn.smxy.forum.domain.entity.Check;
import cn.smxy.forum.domain.entity.LoginUser;
import cn.smxy.forum.mapper.CheckMapper;
import cn.smxy.forum.service.ICheckService;
import cn.smxy.forum.utils.R;
import cn.smxy.forum.utils.RedisUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class CheckServiceImpl extends ServiceImpl<CheckMapper, Check> implements ICheckService {

    @Autowired
    private CheckMapper checkMapper;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean getCheckStatus(Long userId) {

        String verifyKey="user:" + userId;
        LoginUser loginUser = redisUtil.getCacheObject(verifyKey);
        String checkStr=loginUser.getCheckStr();

        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDate = today.format(formatter);

        if(checkStr.isEmpty()){
            //redis没有
            LambdaQueryWrapper<Check> lqw = new LambdaQueryWrapper<>();
            lqw.eq(Check::getUserId, userId)
                    .ge(Check::getCheckTime, today.atStartOfDay())
                    .lt(Check::getCheckTime, today.plusDays(1).atStartOfDay());
            Check check = checkMapper.selectOne(lqw);
            if(check==null){
                return false;
            }else{
                checkStr=formattedDate+""+check.getCheckNum();
                loginUser.setCheckStr(checkStr);
                redisUtil.setCacheObject(verifyKey,loginUser);
                return true;
            }
        }else if(checkStr.substring(0,8).equals(formattedDate)){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public R check(Long userId) {

        if(getCheckStatus(userId)){
            return R.fail("今天已签到，请勿重复签到");
        }

        String verifyKey="user:" + userId;
        LoginUser loginUser = redisUtil.getCacheObject(verifyKey);
        String checkStr=loginUser.getCheckStr();

        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDate = today.format(formatter);
        String formattedYesterday = yesterday.format(formatter);

        LambdaQueryWrapper<Check> lqw=new LambdaQueryWrapper<>();
        lqw.eq(Check::getUserId,userId)
                .ge(Check::getCheckTime, yesterday.atStartOfDay())
                .lt(Check::getCheckTime, yesterday.plusDays(1).atStartOfDay());;
        Check check=checkMapper.selectOne(lqw);
        Check newCheck=new Check();
        if(check==null){
            newCheck.setUserId(userId);
            newCheck.setCheckNum(1L);
            if(checkMapper.insert(check)>0){
                loginUser.setCheckStr(formattedDate+""+newCheck.getCheckNum());
                redisUtil.setCacheObject(verifyKey,loginUser);
                return R.ok();
            }else {
                return R.fail("签到失败,请稍后再试！");
            }
        }else{
            newCheck.setUserId(userId);
            newCheck.setCheckNum(check.getCheckNum()+1);
            if(checkMapper.insert(check)>0){
                loginUser.setCheckStr(formattedDate+""+newCheck.getCheckNum());
                redisUtil.setCacheObject(verifyKey,loginUser);
                return R.ok();
            }else {
                return R.fail("签到失败,请稍后再试！");
            }
        }
    }

    @Override
    public Integer getCheckNum(Long userId) {
        String verifyKey="user:" + userId;
        LoginUser loginUser = redisUtil.getCacheObject(verifyKey);
        String checkStr=loginUser.getCheckStr();
        if(checkStr==null){
            return 0;
        }else {
            Integer checkNum=Integer.valueOf(checkStr.substring(8));
            return checkNum;
        }
    }


}
