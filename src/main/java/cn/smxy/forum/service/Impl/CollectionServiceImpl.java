package cn.smxy.forum.service.Impl;

import cn.smxy.forum.domain.entity.Collection;
import cn.smxy.forum.mapper.CollectionMapper;
import cn.smxy.forum.service.ICollectionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CollectionServiceImpl extends ServiceImpl<CollectionMapper, Collection> implements ICollectionService {
}
