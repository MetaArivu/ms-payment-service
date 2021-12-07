package com.payment.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import com.payment.server.secutiry.JWTUtil;

import brave.propagation.ExtraFieldPropagation;
import reactor.core.publisher.Mono;

@Component
public class RequestFilter implements WebFilter {
 

	@Autowired
	private JWTUtil jwtUtil;

	private static final Logger log = (Logger) LoggerFactory.getLogger(RequestFilter.class);

	@SuppressWarnings("deprecation")
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
		long startTime = System.currentTimeMillis();
		log.debug("API call started...");
		ServerHttpRequest request = exchange.getRequest();
		RequestPath path = request.getPath();

		return chain.filter(exchange).doOnSubscribe(s -> {
			String authorization = exchange.getRequest().getHeaders().getFirst("Authorization");

			try {
				if (authorization != null) {
					String authToken = authorization.substring(7);
					String userName = jwtUtil.getUsernameFromToken(authToken);
					log.info("user name=" + userName);
					ExtraFieldPropagation.set("userName", userName);
				}

			} catch (Exception e) {
				log.error("Exception={}", e.getMessage());
				log.error("{}",e);
			}

			log.info("authorization={}", authorization);
			ExtraFieldPropagation.set("Authorization", authorization);
		}).doFinally(signalType -> {
			long totalTime = System.currentTimeMillis() - startTime;
			log.info("IP={}, Path={}, TT= {}", request.getRemoteAddress().toString(), path.toString(), totalTime);
			MDC.clear();
		});

	}

}