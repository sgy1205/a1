package cn.smxy.forum.domain.param.update;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("修改权限菜单")
public class UpdateMenuDTO {
    @ApiModelProperty("菜单ID")
    private Long menuId;
    @ApiModelProperty("菜单名称")
    private String menuName;
    @ApiModelProperty("父菜单ID 0-目录")
    private Long parentId;
    @ApiModelProperty("菜单状态")
    private String status;
    @ApiModelProperty("权限标识")
    private String perms;
    @ApiModelProperty("菜单类别")
    private String menuType;
    @ApiModelProperty("菜单图标")
    private String icon;
    @ApiModelProperty("备注")
    private String remark;
}
