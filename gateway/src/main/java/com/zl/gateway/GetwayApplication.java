package com.zl.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;

/**
 * 
 * @author coolz
 *
 */
@SpringBootApplication
@EnableApolloConfig(value="application.yml")
public class GetwayApplication {
	public static void main(String[] args) {
		try {
		SpringApplication.run(GetwayApplication.class, args);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}

