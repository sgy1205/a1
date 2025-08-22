package cn.smxy.forum.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("标签列表参数")
public class TagsListVo {
    @ApiModelProperty("标签ID")
    private Long tagsId;
    @ApiModelProperty("标签名称")
    private String tagsName;
}
