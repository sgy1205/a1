package cn.smxy.forum.mapper;

import cn.smxy.forum.domain.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {

    List<String> queryPermissionByUserId(@Param("userId") Long userId);

    /**
     * 获取菜单绑定的角色数量
     * @param menuId
     * @return
     */
    Integer getNumberOfRoleMenu(@Param("menuId")Long menuId);

}
