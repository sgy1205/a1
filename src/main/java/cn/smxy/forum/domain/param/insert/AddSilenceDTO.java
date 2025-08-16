package cn.smxy.forum.domain.param.insert;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("禁言")
public class AddSilenceDTO {
    @ApiModelProperty(value = "禁言用户ID",required = true)
    @NotNull(message = "禁言用户ID不能为空")
    private Long userId;
    @ApiModelProperty(value = "禁言类型 0-暂时禁言 1-永久禁言",required = true)
    @NotNull(message = "禁言类型不能为空")
    private String silenceType;
    @ApiModelProperty(value = "禁言原因" ,required = true)
    @NotNull(message = "禁言原因不能为空")
    private String signatureReason;
    @ApiModelProperty("禁言时间(禁言多少天)")
    private Integer signatureTime;
}
