package cn.smxy.forum.domain.param.insert;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("添加评论参数")
public class AddCommentDTO {
    @ApiModelProperty("评论类型 0-帖子 1-评论")
    private String commentType;
    @ApiModelProperty("父ID")
    private Long parentId;
    @ApiModelProperty("评论内容")
    private String commentContent;
}
