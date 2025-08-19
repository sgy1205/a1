package cn.smxy.forum.domain.param.update;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("修改密码参数")
public class UpdatePasswordDTO {
    @ApiModelProperty("旧密码")
    private String oldPassword;
    @ApiModelProperty("新密码")
    private String newPassword;
}
