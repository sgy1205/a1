package cn.smxy.forum.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@ApiModel("评论区评论显示数据")
public class CommentListVo {
    @ApiModelProperty("评论ID")
    private Long commentId;
    @ApiModelProperty("评论用户ID")
    private Long userId;
    @ApiModelProperty("父ID")
    private Long parentId;
    @ApiModelProperty("评论内容")
    private String commentContent;
    @ApiModelProperty("评论时间")
    private Date createTime;
    @ApiModelProperty("评论点赞数")
    private Long likeNumber;
    @ApiModelProperty("评论者昵称")
    private String nickName;
    @ApiModelProperty("评论者头像")
    private String avatar;
    @ApiModelProperty("子评论数")
    private Long childNumber;
    @ApiModelProperty("点赞状态")
    private String likeStatus;
    @ApiModelProperty("回复的目标评论的用户昵称")
    private String replyNickName;
    @ApiModelProperty("根评论ID（用于构建树形结构）")
    private Long rootId;
    @ApiModelProperty("评论层级")
    private Integer level;
    @ApiModelProperty("子评论列表")
    private List<CommentListVo> children;
}
