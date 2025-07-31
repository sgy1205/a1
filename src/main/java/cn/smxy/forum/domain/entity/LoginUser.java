package cn.smxy.forum.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
// 解决后续redis读取数据时反序列化报错
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginUser implements UserDetails {

    public User user;

    // 用来保存用户的权限信息
    private List<String> permissions;

    @JsonIgnore// 不手动添加的话后去反序列化会出现异常
// 权限集合需要转换成这种类型的Security才能判断
    private List<GrantedAuthority> authorities;

    // 写一个构造函数用来接收权限集合
    public LoginUser(User user, List<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }
    /**
     * 设置权限信息的返回
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 把permissions中的权限封装成对应的SimpleGrantedAuthority对象
        if (authorities == null) {
            authorities = new ArrayList<>();
        }
        // 循环将权限字符串封装成需要的集合
        for (String p : permissions) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(p);
            authorities.add(authority);
        }
        return authorities;
    }

    /**
     * 框架中会自动调用获取用户名和密码的操作，所以返回值要重写一下
     * @return
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    /**
     *     布尔值记得改为True，否则可能无法访问
      */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
