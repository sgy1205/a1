package cn.smxy.forum.service.Impl;

import cn.smxy.forum.domain.entity.Menu;
import cn.smxy.forum.mapper.MenuMapper;
import cn.smxy.forum.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<String> queryPermissionByUserId(Long userId) {
        return menuMapper.queryPermissionByUserId(userId);
    }
}
