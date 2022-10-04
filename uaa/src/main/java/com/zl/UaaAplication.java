package com.zl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;

/**
 * 
 * @author coolz
 *
 */
@SpringBootApplication
@EnableEurekaClient
@EnableApolloConfig(value="application.yml")
@EnableFeignClients
public class UaaAplication{
	public static void main(String[] args) {
		try {
			SpringApplication.run(UaaAplication.class, args);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
