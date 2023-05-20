package com.zl.mall.gateway.filter;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;

import com.google.gson.Gson;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@ConfigurationProperties(prefix = "auth")
public class AuthorizeFilter implements GlobalFilter, Ordered {
	private final Logger logger = LoggerFactory.getLogger(AuthorizeFilter.class);

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ApplicationProperties applicationProperties;

	@Override
	public int getOrder() {
		return 0;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerHttpRequest serverHttpRequest = exchange.getRequest();
		ServerHttpResponse serverHttpResponse = exchange.getResponse();
		String token = serverHttpRequest.getHeaders().getFirst("token");
		RequestPath path = serverHttpRequest.getPath();
		logger.info("请求路由是:{}", path.value());

		List<String> whiteList = applicationProperties.getWhiteList();
		if (whiteList.contains(path.value())) {
			return chain.filter(exchange);
		}
		if (StringUtils.isBlank(token)) {
			serverHttpResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
			return getVoidMono(serverHttpResponse);
		} else if (!"null".equals(token)) {
			MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
			queryParams.add("token", token);
			String object = restTemplate.postForObject("/uaa/oauth/check_token", queryParams,
					String.class);
			if (StringUtils.isBlank(object)) {
				serverHttpResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
				return getVoidMono(serverHttpResponse);
			}
		}

		return chain.filter(exchange);
	}

	private Mono<Void> getVoidMono(ServerHttpResponse serverHttpResponse) {
		serverHttpResponse.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
		Gson gson = new Gson();
		DataBuffer dataBuffer = serverHttpResponse.bufferFactory().wrap(gson.toJson("未授权").getBytes());
		return serverHttpResponse.writeWith(Flux.just(dataBuffer));
	}
}
