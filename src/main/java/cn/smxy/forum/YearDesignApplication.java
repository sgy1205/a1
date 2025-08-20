package cn.smxy.forum;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("cn.smxy.forum.mapper")
@ComponentScan("cn.smxy")
public class YearDesignApplication {

	public static void main(String[] args) {
		SpringApplication.run(YearDesignApplication.class, args);
	}

}
