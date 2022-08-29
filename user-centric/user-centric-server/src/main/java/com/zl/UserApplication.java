package com.zl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;

@EnableAutoConfiguration
@EnableEurekaClient
@Configuration
@ComponentScan
@EnableApolloConfig(value = "application.yml")
@EnableFeignClients
@MapperScan("com.zl.centric.*.mapper")
public class UserApplication {
	public static void main(String[] args) {
		try {
			SpringApplication.run(UserApplication.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
