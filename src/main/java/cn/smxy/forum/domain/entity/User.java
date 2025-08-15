package cn.smxy.forum.domain.entity;

import cn.smxy.forum.domain.other.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {

  @TableId(type = IdType.AUTO)
  private Long userId;
  private String userName;
  private String nickName;
  private String password;
  private String userType;
  private String phonenumber;
  private String email;
  private String avatar;
  private String sex;
  private String status;
  private String remark;
  private Long points;
  private Long registerRank;
  private String signature;
  private String silenceStatus;
  private String delFlag;
}
