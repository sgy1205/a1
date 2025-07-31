package cn.smxy.forum.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu {

  @TableId(type = IdType.AUTO)
  private Long menuId;
  private String menuName;
  private Long parentId;
  private String status;
  private String perms;
  private String icon;
  private String createBy;
  private Date createTime;
  private String updateBy;
  private Date updateTime;
  private String remark;
}
