package cn.smxy.forum.controller;

import cn.smxy.forum.domain.entity.Menu;
import cn.smxy.forum.domain.other.TableDataInfo;
import cn.smxy.forum.domain.other.TreeSelect;
import cn.smxy.forum.domain.param.insert.AddMenuDTO;
import cn.smxy.forum.domain.param.other.PageQuery;
import cn.smxy.forum.domain.param.update.UpdateMenuDTO;
import cn.smxy.forum.domain.vo.MenuDirectoryVo;
import cn.smxy.forum.domain.vo.SunMenuListVo;
import cn.smxy.forum.mapping.MenuMapping;
import cn.smxy.forum.service.IMenuService;
import cn.smxy.forum.utils.BeanUtils;
import cn.smxy.forum.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
@Api(tags = "权限菜单模块")
public class MenuController extends BaseController{

    @Autowired
    private IMenuService menuService;

    @GetMapping("/directory")
    @ApiOperation("获取权限菜单目录列表")
//    @PreAuthorize("@myExpressionUtil.myAuthority('menu:directory')")
    public R<List<MenuDirectoryVo>> getMenuPageList() {
        LambdaQueryWrapper<Menu> lqw=new LambdaQueryWrapper<>();
        lqw.eq(Menu::getMenuType,"0");
        List<Menu> menuList=menuService.list(lqw);

        return R.ok(MenuMapping.INSTANCE.toMenuDirectoryVoList(menuList));
    }

    @GetMapping("/menuList")
    @ApiOperation("获取树形菜单列表")
    public R<List<TreeSelect>> getSunMenuList(){
        List<TreeSelect> treeSelects = menuService.buildTreeSelect(menuService.list());
        return R.ok(treeSelects);
    }

    @PostMapping()
    @ApiOperation("添加权限菜单")
    public R addMenu(@Validated @RequestBody AddMenuDTO addMenuDTO){
        LambdaQueryWrapper<Menu> lqw=new LambdaQueryWrapper<>();
        lqw.eq(Menu::getMenuName,addMenuDTO.getMenuName());
        if(menuService.count(lqw)>0){
            return R.fail("该菜单名称已存在");
        }else{
            Menu menu=new Menu();
            BeanUtils.copyBeanProp(menu,addMenuDTO);
            return R.to(menuService.save(menu),"添加");
        }
    }

    @GetMapping("/{menuId}")
    @ApiOperation("获取当前菜单详情")
    public R<Menu> getMenu(@PathVariable("menuId") Long menuId){
        LambdaQueryWrapper<Menu> lqw=new LambdaQueryWrapper<>();
        lqw.eq(Menu::getMenuId,menuId);
        return R.ok(menuService.getOne(lqw));
    }

    @PutMapping()
    @ApiOperation("修改权限菜单")
    public R updateMenu(@Validated @RequestBody UpdateMenuDTO updateMenuDTO){
        LambdaQueryWrapper<Menu> lqw=new LambdaQueryWrapper<>();
        lqw.eq(Menu::getMenuName,updateMenuDTO.getMenuName());
        lqw.ne(Menu::getMenuId,updateMenuDTO.getMenuId());
        if(menuService.count(lqw)>0){
            return R.fail("该菜单名称已存在");
        }else{
            Menu menu=new Menu();
            BeanUtils.copyBeanProp(menu,updateMenuDTO);
            return R.to(menuService.updateById(menu),"修改");
        }
    }

    @DeleteMapping("/{menuId}")
    @ApiOperation("删除权限菜单")
    public R deleteMenu(@PathVariable("menuId") Long menuId){
        LambdaQueryWrapper<Menu> lqw=new LambdaQueryWrapper<>();
        lqw.eq(Menu::getParentId,menuId);
        if(menuService.count(lqw)>0){
            return R.fail("存在子菜单,不允许删除");
        }
        if(menuService.getNumberOfRoleMenu(menuId)>0){
            return R.fail("该菜单已绑定角色，不允许删除");
        }
        return R.to(menuService.removeById(menuId),"删除");
    }

}
