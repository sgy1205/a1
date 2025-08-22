package cn.smxy.forum.domain.param.update;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("修改点赞状态参数")
public class UpdateLikesDTO {

    @ApiModelProperty("点赞的帖子或评论ID")
    private Long targetId;
    @ApiModelProperty("点赞类型 0-帖子 1-评论")
    private String type;
    @ApiModelProperty("如果点赞的是评论传入的帖子ID")
    private Long postId;
}
