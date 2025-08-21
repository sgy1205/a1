package cn.smxy.forum.domain.entity;

import cn.smxy.forum.domain.other.BaseEntity;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("Collect")
public class Collection extends BaseEntity {

  private Long collectionId;
  private Long userId;
  private Long postId;
  private String delFlag;

}
