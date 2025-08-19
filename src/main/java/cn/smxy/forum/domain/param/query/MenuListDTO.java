package cn.smxy.forum.domain.param.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("获取权限菜单列表参数")
public class MenuListDTO {
    @ApiModelProperty("菜单名称")
    private String menuName;
    @ApiModelProperty("菜单状态")
    private String status;
    @ApiModelProperty("菜单类型")
    private String menuType;
}
