package cn.smxy.forum.domain.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("积分记录列表数据")
public class PointListVo {
    @ApiModelProperty("积分类型 0-签到 1-发贴")
    private String pointType;
    @ApiModelProperty("积分数量")
    private Long pointNumber;
    @ApiModelProperty("积分获取时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
