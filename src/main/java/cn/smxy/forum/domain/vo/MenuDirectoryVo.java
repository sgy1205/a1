package cn.smxy.forum.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("菜单目录列表数据")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDirectoryVo {
    @ApiModelProperty("菜单ID")
    private Long menuId;
    @ApiModelProperty("菜单名称")
    private String menuName;
    @ApiModelProperty("菜单状态 0-正常 1-禁用")
    private String status;
    @ApiModelProperty("菜单图标")
    private String icon;
    @ApiModelProperty("备注")
    private String remark;
}
