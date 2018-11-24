package com.daji.jyhx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableRedisHttpSession
public class JyhxApplication {

	public static void main(String[] args) {
		SpringApplication.run(JyhxApplication.class, args);
	}
}
