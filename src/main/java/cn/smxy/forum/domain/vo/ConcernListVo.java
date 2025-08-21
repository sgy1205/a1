package cn.smxy.forum.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("用户关注信息列表")
public class ConcernListVo {

  @ApiModelProperty("用户id")
  private Integer userId;

  @ApiModelProperty("用户头像")
  private String avatar;

  @ApiModelProperty("用户昵称")
  private String nickName;

  @ApiModelProperty("用户签名")
  private String signature;
}
