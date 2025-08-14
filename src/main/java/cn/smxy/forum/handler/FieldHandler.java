package cn.smxy.forum.handler;

import cn.smxy.forum.constant.Constants;
import cn.smxy.forum.utils.SecurityUtils;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.catalina.security.SecurityUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

import static cn.smxy.forum.constant.Constants.DEFAULT_USER;

@Component
public class FieldHandler implements MetaObjectHandler {
    /**
     * 插入时的填充策略
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createBy", getCurrentUsernameOrDefault(), metaObject);
        this.setFieldValByName("updateBy", getCurrentUsernameOrDefault(), metaObject);
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }

    /**
     * 更新时的填充策略
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateBy", getCurrentUsernameOrDefault(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }

    /**
     * 获取当前用户名，如果为空则返回默认值
     */
    private String getCurrentUsernameOrDefault() {
        try {
            String username = SecurityUtils.getUsername();
            if (username == null) {
                return DEFAULT_USER;
            }
            return username;
        } catch (Exception e) {
            return DEFAULT_USER;
        }
    }
}
