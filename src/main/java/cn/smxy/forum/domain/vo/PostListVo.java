package cn.smxy.forum.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("获取用户中心帖子列表")
public class PostListVo {
    @ApiModelProperty("用户ID")
    private Long userId;
    @ApiModelProperty("帖子ID")
    private Long postId;
    @ApiModelProperty("用户头像")
    private String avatar;
    @ApiModelProperty("昵称")
    private String nickName;
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("内容")
    private String content;
    @ApiModelProperty("节点")
    private String node;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("修改时间")
    private Date updateTime;
    @ApiModelProperty("点赞数量")
    private Long likeNumber;
    @ApiModelProperty("评论数量")
    private Long commentNumber;
    @ApiModelProperty("点赞状态 0-未点赞 1-已点赞")
    private String likeStatus;
    @ApiModelProperty("审核状态")
    private String auditStatus;
}
