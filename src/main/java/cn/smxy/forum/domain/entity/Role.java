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
public class Role extends BaseEntity {

  @TableId(type = IdType.AUTO)
  private Long roleId;
  private String roleName;
  private String roleKey;
  private String status;
  private String delFlag;
  private String remark;
}
