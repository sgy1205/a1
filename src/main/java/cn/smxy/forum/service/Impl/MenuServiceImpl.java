package cn.smxy.forum.service.Impl;

import cn.smxy.forum.domain.entity.Menu;
import cn.smxy.forum.domain.other.TreeSelect;
import cn.smxy.forum.mapper.MenuMapper;
import cn.smxy.forum.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<String> queryPermissionByUserId(Long userId) {
        return menuMapper.queryPermissionByUserId(userId);
    }

    @Override
    public Integer getNumberOfRoleMenu(Long menuId) {
        return menuMapper.getNumberOfRoleMenu(menuId);
    }

    /**
     * 把平铺的菜单列表转成前端所需的树形 TreeSelect
     */
    public List<TreeSelect> buildTreeSelect(List<Menu> menus) {
        // 1. 先全部转成 TreeSelect（平铺）
        Map<Long, TreeSelect> nodeMap = menus.stream()
                .collect(Collectors.toMap(Menu::getMenuId,
                        m -> new TreeSelect(m.getMenuId(), m.getMenuName(), new ArrayList<>())));

        // 2. 再挂父子关系
        List<TreeSelect> roots = new ArrayList<>();
        for (Menu m : menus) {
            TreeSelect node = nodeMap.get(m.getMenuId());
            if (m.getParentId() == null || m.getParentId() == 0) {   // 顶级节点
                roots.add(node);
            } else {
                // 找到父节点，把自己挂进去
                TreeSelect parent = nodeMap.get(m.getParentId());
                if (parent != null) {
                    parent.getChildren().add(node);
                }
            }
        }

        return roots;
    }
}
