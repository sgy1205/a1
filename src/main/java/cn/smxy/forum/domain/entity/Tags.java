package cn.smxy.forum.domain.entity;

import cn.smxy.forum.domain.other.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Tags extends BaseEntity {

  @TableId(type = IdType.AUTO)
  private Long tagsId;
  private String tagsName;
}
