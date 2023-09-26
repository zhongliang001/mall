package com.zl.mall.gateway;


import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/**
 * @author zhongliang
 */
@Configuration
@EnableWebFlux
public class WebConfig implements WebFluxConfigurer {
	
    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        List<String> list = new ArrayList<String>();
        list.add("http://127.0.0.1:8081");
        list.add("http://127.0.0.1:90");
        list.add("http://192.168.111.130:5173");
        list.add("http://localhost:5173");
        list.add("http://192.168.31.194");
        list.add("http://195966hp14.iok.la");
        list.add("http://192.168.111.129:8011");
        list.add("http://192.168.111.130:4173");
        //1,允许任何来源
        corsConfiguration.setAllowedOrigins(list);
        //2,允许任何请求头
        corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
        //3,允许任何方法
        corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);
        //4,允许凭证
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsWebFilter(source);
    }
}
