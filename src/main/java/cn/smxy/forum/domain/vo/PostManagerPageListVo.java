package cn.smxy.forum.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("帖子管理分页数据")
public class PostManagerPageListVo {
    @ApiModelProperty("用户ID")
    private Long userId;
    @ApiModelProperty("用户昵称")
    private String nickName;
    @ApiModelProperty("用户头像")
    private String avatar;
    @ApiModelProperty("帖子ID")
    private Long postId;
    @ApiModelProperty("帖子发布时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @ApiModelProperty("帖子更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    @ApiModelProperty("浏览量")
    private Long browseNumber;
    @ApiModelProperty("点赞数")
    private Long likeNumber;
    @ApiModelProperty("收藏数量")
    private Long collectionNumber;
    @ApiModelProperty("帖子标题")
    private String title;
    @ApiModelProperty("帖子内容")
    private String content;
    @ApiModelProperty("帖子是否推荐 0-未推荐 1-推荐")
    private String recommend;
    @ApiModelProperty("帖子删除状态 0-存在 2-删除")
    private String delFlag;
    @ApiModelProperty("帖子审核备注")
    private String remark;
    @ApiModelProperty("帖子审核状态")
    private String auditStatus;
    @ApiModelProperty("帖子节点名称")
    private String node;
}
