package cn.smxy.forum.domain.param.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("获取角色列表分页参数")
public class RolePageListDTO {
    @ApiModelProperty("角色名称")
    private String roleName;
    @ApiModelProperty("角色状态")
    private String status;
}
