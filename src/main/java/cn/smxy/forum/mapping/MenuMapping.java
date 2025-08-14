package cn.smxy.forum.mapping;


import cn.smxy.forum.domain.entity.Menu;
import cn.smxy.forum.domain.vo.MenuDirectoryVo;
import cn.smxy.forum.domain.vo.SunMenuListVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MenuMapping {

    MenuMapping INSTANCE= Mappers.getMapper(MenuMapping.class);

    // 单个对象映射
    MenuDirectoryVo toMenuDirectoryVo(Menu menu);

    // 单个对象映射
    SunMenuListVo toSunMenuListVo(Menu menu);

    List<MenuDirectoryVo> toMenuDirectoryVoList(List<Menu> menuList);

    List<SunMenuListVo> toSunMenuVoList(List<Menu> menuList);
}
