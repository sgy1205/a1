package cn.smxy.forum.domain.entity;

import cn.smxy.forum.domain.other.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Silence extends BaseEntity {

  @TableId(type = IdType.AUTO)
  private Long silenceId;
  private String type;
  private Long userId;
  private Date signatureTime;
  private String signatureReason;
  private String delFlag;

}
