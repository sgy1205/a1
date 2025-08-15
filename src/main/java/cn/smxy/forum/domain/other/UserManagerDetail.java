package cn.smxy.forum.domain.other;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户管理的用户详情")
public class UserManagerDetail {
    @ApiModelProperty("用户ID")
    private Long userId;
    @ApiModelProperty("用户类型 0-管理员 1-用户")
    private String userType;
    @ApiModelProperty("头像")
    private String avatar;
    @ApiModelProperty("账号")
    private String userName;
    @ApiModelProperty("昵称")
    private String nickName;
    @ApiModelProperty("邮箱")
    private String email;
    @ApiModelProperty("性别 0-男 1-女 2-未知")
    private String sex;
    @ApiModelProperty("签名（描述）")
    private String signature;
    @ApiModelProperty("用户角色列表")
    private List<Long> roleIds;
}
