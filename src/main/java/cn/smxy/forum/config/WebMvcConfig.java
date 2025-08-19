package cn.smxy.forum.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 把磁盘目录映射成 /upload/** 的 URL
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:F:/Java/YearDesign/upload/");
    }
}
