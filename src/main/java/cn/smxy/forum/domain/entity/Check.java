package cn.smxy.forum.domain.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@TableName("`check`")
public class Check {

  @TableId(type = IdType.AUTO)
  private Long checkId;
  private Long userId;

  @Schema(description = "创建时间", example = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @TableField(fill = FieldFill.INSERT)
  private Date checkTime;

  private Long checkNum;

}
