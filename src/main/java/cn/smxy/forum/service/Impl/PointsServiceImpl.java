package cn.smxy.forum.service.Impl;

import cn.smxy.forum.domain.entity.Points;
import cn.smxy.forum.domain.entity.User;
import cn.smxy.forum.domain.vo.UserPointsRankVo;
import cn.smxy.forum.mapper.PointsMapper;
import cn.smxy.forum.service.IPointsService;
import cn.smxy.forum.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class PointsServiceImpl extends ServiceImpl<PointsMapper, Points> implements IPointsService {

    @Autowired
    private PointsMapper pointsMapper;
    @Autowired
    private IUserService userService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addPoints(Long userId, String pointType) {
        Points points = new Points();
        points.setUserId(userId);
        points.setPointType(pointType);
        if(pointType.equals("0")){
            points.setPointNumber(3L);

            if(pointsMapper.insert(points)>0){
                return userService.updateUserPoints(userId,3);
            }else {
                return false;
            }

        }else {
            points.setPointNumber(1L);

            if(pointsMapper.insert(points)>0){
                return userService.updateUserPoints(userId,1);
            }else {
                return false;
            }
        }
    }

    @Override
    public Long getUserPoints(Long userId) {
        User user = userService.getById(userId);
        return user.getPoints();
    }

    @Override
    public List<UserPointsRankVo> getUserPointsRank() {
        return pointsMapper.getUserPointsRank();
    }


}
