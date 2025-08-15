package cn.smxy.forum.domain.param.insert;

import cn.smxy.forum.domain.other.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("添加权限菜单参数")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddMenuDTO extends BaseEntity {
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
