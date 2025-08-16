package cn.smxy.forum.domain.param.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("帖子管理分页查询")
public class PostManagerPageListDTO {
    @ApiModelProperty("帖子ID")
    private Long postId;
    @ApiModelProperty("发布用户ID")
    private Long userId;
    @ApiModelProperty("状态 0-存在 2-删除")
    private String delFlag;
    @ApiModelProperty("是否推荐 0-未推荐 1-推荐")
    private String recommend;
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("帖子审核状态 0-未审核 1-审核通过 2-审核未通过")
    private String auditStatus;
}
