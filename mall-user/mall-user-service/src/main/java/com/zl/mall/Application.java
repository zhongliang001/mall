package com.zl.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 
 * @author coolz
 *
 */
@EnableEurekaClient
@SpringBootApplication
@EnableCaching
@EnableFeignClients
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}
}
