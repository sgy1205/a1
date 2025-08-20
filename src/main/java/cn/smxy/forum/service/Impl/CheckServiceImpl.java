package cn.smxy.forum.service.Impl;

import cn.smxy.forum.domain.entity.Check;
import cn.smxy.forum.mapper.CheckMapper;
import cn.smxy.forum.service.ICheckService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CheckServiceImpl extends ServiceImpl<CheckMapper, Check> implements ICheckService {
}
