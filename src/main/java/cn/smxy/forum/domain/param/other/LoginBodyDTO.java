package cn.smxy.forum.domain.param.other;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("登录参数")
public class LoginBodyDTO {
    @ApiModelProperty("账号")
    private String loginStr;
    @ApiModelProperty("密码")
    private String password;
}
