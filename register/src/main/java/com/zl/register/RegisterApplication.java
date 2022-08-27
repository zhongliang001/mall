package com.zl.register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;

@SpringBootApplication
@EnableEurekaServer
@EnableApolloConfig(value="application.yml")
public class RegisterApplication {
	public static void main(String[] args) {
		SpringApplication.run(RegisterApplication.class, args);
	}

}
