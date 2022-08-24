package com.zl.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;

@SpringBootApplication
@EnableApolloConfig
public class GetwayApplication {
	public static void main(String[] args) {
		SpringApplication.run(GetwayApplication.class, args);
	}

}

