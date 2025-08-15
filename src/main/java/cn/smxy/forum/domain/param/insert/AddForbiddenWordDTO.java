package cn.smxy.forum.domain.param.insert;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel("违禁词新增参数")
public class AddForbiddenWordDTO {

  @ApiModelProperty(value = "类型（0-词组 1-正则）")
  private Integer type;

  @ApiModelProperty(value = "违禁词",required = true)
  @NotNull(message = "违禁词不能为空")
  private String forbiddenWord;

  @ApiModelProperty(value = "备注")
  private String remark;

}
