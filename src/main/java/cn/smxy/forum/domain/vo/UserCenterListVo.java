package cn.smxy.forum.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户个人中心信息")
public class UserCenterListVo {

    @ApiModelProperty("账号")
    private String userName;

    @ApiModelProperty("用户头像")
    private String avatar;

    @ApiModelProperty("用户昵称")
    private String nickName;

    @ApiModelProperty("用户签名")
    private String signature;

    @ApiModelProperty("用户积分")
    private Long points;

    @ApiModelProperty("话题数")
    private Long postNumber;

    @ApiModelProperty("评论数")
    private Long commentNumber;

    @ApiModelProperty("注册排名")
    private Long registerRank;

    @ApiModelProperty("粉丝总数")
    private Long fanTotal;

    @ApiModelProperty("关注总数")
    private Long concernTotal;

    @ApiModelProperty("粉丝列表信息")
    private List<FansListVo> fansListVos;

    @ApiModelProperty("关注列表信息")
    private List<ConcernListVo> concernListVos;

    @ApiModelProperty("用户背景")
    private String background;

    @ApiModelProperty("用户邮箱")
    private String email;
}
