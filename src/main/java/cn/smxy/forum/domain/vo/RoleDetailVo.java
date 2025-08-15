package cn.smxy.forum.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("角色详情数据")
public class RoleDetailVo {
    @ApiModelProperty("角色ID")
    private Long roleId;
    @ApiModelProperty("角色名称")
    private String roleName;
    @ApiModelProperty("角色权限字符")
    private String roleKey;
    @ApiModelProperty("状态")
    private String status;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("权限菜单ID列表")
    private List<Long> menuIds;
}
