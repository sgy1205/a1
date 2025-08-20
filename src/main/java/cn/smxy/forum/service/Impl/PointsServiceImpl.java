package cn.smxy.forum.service.Impl;

import cn.smxy.forum.domain.entity.Points;
import cn.smxy.forum.mapper.PointsMapper;
import cn.smxy.forum.service.IPointsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class PointsServiceImpl extends ServiceImpl<PointsMapper, Points> implements IPointsService {
}
