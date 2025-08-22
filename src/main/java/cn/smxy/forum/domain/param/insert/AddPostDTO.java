package cn.smxy.forum.domain.param.insert;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("添加帖子")
public class AddPostDTO {
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("节点")
    private String node;
    @ApiModelProperty("内容")
    private String content;
    @ApiModelProperty("标签ID列表")
    private List<Long> tagsIds;
}
