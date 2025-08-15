package cn.smxy.forum.domain.param.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel("违禁词请求参数")
public class ForbiddenWordsPageListDTO {

    @ApiModelProperty(value = "类型（0-词组 1-正则）")
    private Integer type;

    @ApiModelProperty(value = "违禁词")
    private String forbiddenWord;
}
