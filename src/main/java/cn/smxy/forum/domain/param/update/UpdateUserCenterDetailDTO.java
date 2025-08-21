package cn.smxy.forum.domain.param.update;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel("用户个人资料编辑参数")
public class UpdateUserCenterDetailDTO {

  @ApiModelProperty("用户头像")
  private String avatar;

  @ApiModelProperty(value = "用户昵称",required = true)
  @NotNull(message = "用户昵称不能为空")
  private String nickName;

  @ApiModelProperty("用户签名")
  private String signature;
}
