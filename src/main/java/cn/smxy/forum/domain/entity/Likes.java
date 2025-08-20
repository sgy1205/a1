package cn.smxy.forum.domain.entity;

import cn.smxy.forum.domain.other.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Likes extends BaseEntity {

  @TableId(type = IdType.AUTO)
  private Long likesId;
  private Long userId;
  private String type;
  private Long postId;
  private String delFlag;

}
