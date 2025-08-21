package cn.smxy.forum.domain.entity;

import cn.smxy.forum.domain.other.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Comment extends BaseEntity {

  @TableId(type = IdType.AUTO)
  private Long commentId;
  private Long userId;
  private String commentType;
  private Long postId;
  private Long parentId;
  private String commentContent;
  private Long likeNumber;
  private String delFlag;

}
