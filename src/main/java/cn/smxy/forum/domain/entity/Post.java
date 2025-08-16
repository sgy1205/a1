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
public class Post extends BaseEntity {

  @TableId(type = IdType.AUTO)
  private Long postId;
  private Long userId;
  private Long postAuditId;
  private String recommend;
  private String title;
  private String node;
  private String content;
  private Long likeNumber;
  private Long commentNumber;
  private Long collectNumber;
  private Long browseNumber;
  private String delFlag;

}
