package cn.smxy.forum.domain.param.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("首页分页查询参数")
public class HomePostPageListDTO {
    @ApiModelProperty("搜索内容")
    private String searchContent;
    @ApiModelProperty("时间范围（几天内 24小时内传入1，时间不限传入0）")
    private Integer timeFrame;
    @ApiModelProperty("节点名称")
    private String node;
    @ApiModelProperty("是否推荐 0-未推荐 1-推荐")
    private String recommend;
    @ApiModelProperty("是否关注 0-未关注 1-关注")
    private String concernStatus;
}
