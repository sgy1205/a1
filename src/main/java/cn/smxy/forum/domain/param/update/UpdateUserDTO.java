package cn.smxy.forum.domain.param.update;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户管理的修改用户详情参数")
public class UpdateUserDTO {
    @ApiModelProperty(value = "用户ID",required = true)
    @NotNull(message = "用户ID不能为空")
    private Long userId;
    @ApiModelProperty(value = "用户类型 0-管理员 1-用户",required = true)
    @NotNull(message = "用户类型不能为空")
    private String userType;
    @ApiModelProperty("头像")
    private String avatar;
    @ApiModelProperty(value = "账号",required = true)
    @NotNull(message = "账号不能为空")
    private String userName;
    @ApiModelProperty("昵称")
    private String nickName;
    @ApiModelProperty("邮箱")
    private String email;
    @ApiModelProperty("性别 0-男 1-女 2-未知")
    private String sex;
    @ApiModelProperty("签名（描述）")
    private String signature;
    @ApiModelProperty("角色ID")
    private List<Long> roleIds;
}
