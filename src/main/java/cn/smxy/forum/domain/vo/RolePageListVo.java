package cn.smxy.forum.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("角色分页查询数据")
public class RolePageListVo {
    @ApiModelProperty("角色ID")
    private Long roleId;
    @ApiModelProperty("角色名称")
    private String roleName;
    @ApiModelProperty("角色权限字符")
    private String roleKey;
    @ApiModelProperty("角色状态 0-正常 1-停用")
    private String status;
    @ApiModelProperty("删除标志 0-正常 2-删除")
    private String delFlag;
    @ApiModelProperty("备注")
    private String remark;
}
