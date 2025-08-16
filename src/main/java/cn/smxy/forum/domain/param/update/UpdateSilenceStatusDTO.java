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
@ApiModel("修改禁言状态参数")
public class UpdateSilenceStatusDTO {
    @ApiModelProperty(value = "禁言用户ID", required = true)
    @NotNull(message = "禁言用户ID不能为空")
    private Long userId;
    @ApiModelProperty(value = "禁言用户状态  0-解除禁言 1-修改暂时禁言时间 2-永久禁言", required = true)
    @NotNull(message = "禁言用户状态不能为空")
    private String silenceStatus;
    @ApiModelProperty(value = "禁言时间（从现在重新禁言的天数）")
    private Integer silenceTime;
}
