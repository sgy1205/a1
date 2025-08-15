package cn.smxy.forum.controller;

import cn.smxy.forum.domain.entity.Role;
import cn.smxy.forum.domain.other.TableDataInfo;
import cn.smxy.forum.domain.param.insert.AddRoleDTO;
import cn.smxy.forum.domain.param.other.PageQuery;
import cn.smxy.forum.domain.param.query.RolePageListDTO;
import cn.smxy.forum.domain.param.update.UpdateRoleDTO;
import cn.smxy.forum.domain.vo.RoleDetailVo;
import cn.smxy.forum.domain.vo.RolePageListVo;
import cn.smxy.forum.mapping.RoleMapping;
import cn.smxy.forum.service.IRoleService;
import cn.smxy.forum.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/role")
@Api(tags = "角色管理模块")
public class RoleController extends BaseController {

    @Autowired
    private IRoleService roleService;

    @GetMapping("/pageList")
    @ApiOperation("角色列表分页查询")
    public TableDataInfo<List<RolePageListVo>> getRolePageList(@Validated PageQuery pageQuery,RolePageListDTO rolePageListDTO){
        Page<Role> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize());
        LambdaQueryWrapper<Role> lqw=new LambdaQueryWrapper<>();
        lqw.like(rolePageListDTO.getRoleName()!=null,Role::getRoleName, rolePageListDTO.getRoleName());
        lqw.eq(rolePageListDTO.getStatus()!=null,Role::getStatus, rolePageListDTO.getStatus());

        Page<Role> roleList=roleService.page(page,lqw);

        List<RolePageListVo> pageListVoList = RoleMapping.INSTANCE.toPageListVoList(roleList.getRecords());

        return getDataTable(pageListVoList);
    }

    @PostMapping()
    @ApiOperation("添加角色")
    public R addRole(@RequestBody AddRoleDTO addRoleDTO){
        addRoleDTO.setCreateTime(new Date());
        addRoleDTO.setUpdateTime(new Date());
        addRoleDTO.setCreateBy(getUsername());
        addRoleDTO.setUpdateBy(getUsername());

        LambdaQueryWrapper<Role> lqw=new LambdaQueryWrapper<>();
        lqw.eq(Role::getRoleName,addRoleDTO.getRoleName());
        lqw.eq(Role::getDelFlag,"0");

        if(roleService.count(lqw)>0){
            return R.fail("添加失败，该角色名已存在");
        }
        if(roleService.addRole(addRoleDTO)>0){
            lqw.eq(Role::getRoleName,addRoleDTO.getRoleName());
            Role role = roleService.getOne(lqw);
            if(roleService.addRoleMenu(addRoleDTO.getMenuIds(), role.getRoleId())>0){
                return R.ok();
            }else {
                return R.fail("添加角色权限失败");
            }
        }else{
            return R.fail("添加失败");
        }
    }

    @PutMapping()
    @ApiOperation("修改角色")
    public R updateRole(@RequestBody UpdateRoleDTO updateRoleDTO){
        updateRoleDTO.setUpdateTime(new Date());
        updateRoleDTO.setUpdateBy(getUsername());

        LambdaQueryWrapper<Role> lqw=new LambdaQueryWrapper<>();
        lqw.eq(Role::getRoleName,updateRoleDTO.getRoleName());
        lqw.eq(Role::getDelFlag,"0");
        lqw.ne(Role::getRoleId,updateRoleDTO.getRoleId());
        if(roleService.count(lqw)>0){
            return R.fail("修改失败，该角色名已存在");
        }
        //修改权限菜单
        if(roleService.updateRole(updateRoleDTO)>0){
            roleService.deleteRoleMenu(updateRoleDTO.getRoleId());
            return R.to(roleService.addRoleMenu(updateRoleDTO.getMenuIds(),updateRoleDTO.getRoleId())>0,"修改");
        }else {
            return R.fail();
        }
    }

    @GetMapping("/{roleId}")
    @ApiOperation("获取当前角色信息")
    public R<RoleDetailVo> getRoleById(@PathVariable("roleId") Long roleId){
        return R.ok(roleService.getRoleDetail(roleId));
    }

    @PostMapping("/updateStatus/{roleId}")
    @ApiOperation("修改角色状态")
    public R updateRoleStatus(@PathVariable("roleId") Long roleId){
        Role role = roleService.getById(roleId);
        if(role.getStatus().equals("0")){
            role.setStatus("1");
        }else{
            role.setStatus("0");
        }
        return R.to(roleService.updateById(role),"修改");
    }

    @DeleteMapping("/{roleId}")
    @ApiOperation("修改角色删除状态")
    public R deleteRole(@PathVariable("roleId") Long roleId){
        Role role = roleService.getById(roleId);
        LambdaUpdateWrapper<Role> luw=new LambdaUpdateWrapper<>();
        if(role.getDelFlag().equals("0")){
            luw.eq(Role::getRoleId,roleId).set(Role::getDelFlag,"2");
        }else{
            LambdaQueryWrapper<Role> lqw=new LambdaQueryWrapper<>();
            lqw.eq(Role::getRoleName,role.getRoleName());
            lqw.eq(Role::getDelFlag,"0");
            if(roleService.count(lqw)>0){
                return R.fail("已存在相同角色名称，恢复失败");
            }else{
                luw.eq(Role::getRoleId,roleId).set(Role::getDelFlag,"0");
            }
        }

        return R.to(roleService.update(luw),"操作");
    }

}
