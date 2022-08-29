package com.zl.uua;

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
public class UaaAplication{
	public static void main(String[] args) {
		try {
			SpringApplication.run(UaaAplication.class, args);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
