package cn.smxy.forum.service;

import cn.smxy.forum.domain.entity.Menu;
import cn.smxy.forum.domain.other.TreeSelect;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IMenuService extends IService<Menu> {

    List<String> queryPermissionByUserId(Long userId);

    /**
     * 获取菜单绑定的角色数量
     * @param menuId
     * @return
     */
    Integer getNumberOfRoleMenu(Long menuId);

    /**
     * 获取所有权限列表
     * @return
     */
    List<String> getAllermission();

    /**
     * 把平铺的菜单列表转成前端所需的树形 TreeSelect
     */
    public List<TreeSelect> buildTreeSelect(List<Menu> menus);

}
