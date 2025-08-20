package cn.smxy.forum.domain.entity;


import cn.smxy.forum.domain.other.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Notification extends BaseEntity {

  @TableId(type = IdType.AUTO)
  private Long notificationId;
  private Long userId;
  private String type;
  private String message;
  private Long relatedId;
  private String relatedType;
  private Long readStatus;
  private String delFlag;
  private Long operatorId;
  private String avatar;
}
