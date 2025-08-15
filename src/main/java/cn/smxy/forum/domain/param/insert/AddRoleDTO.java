package cn.smxy.forum.domain.param.insert;

import cn.smxy.forum.domain.other.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("添加角色参数")
public class AddRoleDTO extends BaseEntity {
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
