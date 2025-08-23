package cn.smxy.forum.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("帖子详情数据")
public class PostDetailVo {
    @ApiModelProperty("帖子ID")
    private Long postId;
    @ApiModelProperty("帖子标题")
    private String title;
    @ApiModelProperty("内容")
    private String content;
    @ApiModelProperty("节点")
    private String node;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("更新时间")
    private Date updateTime;
    @ApiModelProperty("点赞数")
    private Long likeNumber;
    @ApiModelProperty("评论数")
    private Long commentNumber;
    @ApiModelProperty("收藏数")
    private Long collectionNumber;
    @ApiModelProperty("浏览量")
    private Long browseNumber;
    @ApiModelProperty("点赞状态")
    private String likeStatus;
    @ApiModelProperty("收藏状态")
    private String collectionStatus;
    @ApiModelProperty("审核状态 0-未审核 1-审核通过 2-审核未通过")
    private String auditStatus;
    @ApiModelProperty("作者ID")
    private Long userId;
    @ApiModelProperty("作者昵称")
    private String nickName;
}
