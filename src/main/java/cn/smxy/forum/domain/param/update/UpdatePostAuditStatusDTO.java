package cn.smxy.forum.domain.param.update;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("修改审核状态参数")
public class UpdatePostAuditStatusDTO {
    @ApiModelProperty(value = "帖子ID", required = true)
    @NotNull(message = "帖子ID不能为空")
    private Long postId;
    @ApiModelProperty(value = "帖子审核状态 0-未审核 1-审核通过 2-审核未通过", required = true)
    @NotNull(message = "帖子审核状态不能为空")
    private Integer auditStatus;
}
