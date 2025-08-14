package cn.smxy.forum.service;

import cn.smxy.forum.domain.entity.Menu;
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

}
