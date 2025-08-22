package cn.smxy.forum.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("违禁词匹配结果")
public class ForbiddenWordCheckResultVo {
    @ApiModelProperty("包含的违禁词列表")
    private List<String> forbiddenWords;
    @ApiModelProperty("判断结果")
    private boolean result;
}
