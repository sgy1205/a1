package cn.smxy.forum.service.Impl;

import cn.smxy.forum.domain.entity.Concern;
import cn.smxy.forum.mapper.ConcernMapper;
import cn.smxy.forum.service.IConcernService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ConcernServiceImpl extends ServiceImpl<ConcernMapper, Concern> implements IConcernService {
}
