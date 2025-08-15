package cn.smxy.forum.domain.entity;

import cn.smxy.forum.domain.other.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForbiddenWords extends BaseEntity {

  private Long forbiddenWordsId;
  private Long type;
  private String forbiddenWord;
  private String remark;
  private String status;
  private String delFlag;

}
