package cn.smxy.forum.domain.param.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户分页查询参数")
public class UserPageListDTO {
    @ApiModelProperty("用户ID")
    private Long userId;
    @ApiModelProperty("账号")
    private String userName;
    @ApiModelProperty("昵称")
    private String nickName;
    @ApiModelProperty("用户类型 0-管理员 1-用户")
    private String userType;
}
