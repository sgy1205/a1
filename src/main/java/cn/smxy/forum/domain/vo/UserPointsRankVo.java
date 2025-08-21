package cn.smxy.forum.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("获得用户积分排行数据")
public class UserPointsRankVo {
    @ApiModelProperty("用户ID")
    private Long userId;
    @ApiModelProperty("用户帖子数")
    private Integer postNum;
    @ApiModelProperty("用户评论数")
    private Integer commentNum;
    @ApiModelProperty("用户昵称")
    private String nickName;
    @ApiModelProperty("用户头像")
    private String avatar;
    @ApiModelProperty("用户积分数")
    private Integer points;
}
