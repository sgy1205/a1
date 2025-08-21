package cn.smxy.forum.domain.param.update;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel("用户邮箱编辑参数")
public class UpdateUserEmailDTO {

  @ApiModelProperty(value = "用户邮箱",required = true)
  @NotNull(message = "用户邮箱不能为空")
  private String email;

}
