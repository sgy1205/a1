package cn.smxy.forum.domain.entity;

import cn.smxy.forum.domain.other.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostAudit extends BaseEntity {

  @TableId(type = IdType.AUTO)
  private Long postAuditId;
  private Long postId;
  private String auditStatus;
  private String remark;
  private String delFlag;

}
