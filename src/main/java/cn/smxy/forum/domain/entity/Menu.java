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
public class Menu extends BaseEntity {
  @TableId(type = IdType.AUTO)
  private Long menuId;
  private String menuName;
  private Long parentId;
  private String status;
  private String perms;
  private String menuType;
  private String icon;
  private String remark;
}
