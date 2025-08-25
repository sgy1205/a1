package cn.smxy.forum.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class CodeImage {

  @TableId(type = IdType.AUTO)
  private long codeImageId;
  private String imageUrl;

}
