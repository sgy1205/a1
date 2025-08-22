package cn.smxy.forum.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("修改帖子时的帖子详情")
public class PostUpdateDetailVo {
    @ApiModelProperty("帖子ID")
    private Long postId;
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("节点")
    private String node;
    @ApiModelProperty("内容")
    private String content;
    @ApiModelProperty("标签ID列表")
    private List<Long> tagsIds;
}
