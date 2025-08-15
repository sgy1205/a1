package cn.smxy.forum.domain.vo;

import cn.smxy.forum.domain.other.RoleNameList;
import cn.smxy.forum.domain.other.UserManagerDetail;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户管理的用户详情数据")
public class UserManagerDetailVo {
    @ApiModelProperty("用户详情")
    private UserManagerDetail userManagerDetail;
    @ApiModelProperty("角色列表")
    private List<RoleNameList> roleNameList;
}
