package cn.smxy.forum.domain.entity;

import cn.smxy.forum.domain.other.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("Collect")
public class Collection extends BaseEntity {
  @TableId(type = IdType.AUTO)
  private Long collectionId;
  private Long userId;
  private Long postId;
  private String delFlag;

}
