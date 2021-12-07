package com.payment.server.secutiry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.server.ServerWebExchange;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.adapter.dto.Response;

import reactor.core.publisher.Mono;


@ControllerAdvice
public class MyAuthenticationEntryPoint implements ServerAuthenticationEntryPoint {

	private static final Logger log = (Logger) LoggerFactory.getLogger(MyAuthenticationEntryPoint.class);

	@Override
	public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException ex) {
		log.error("Invalid Token, AuthenticationException="+ex.getMessage());
		exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
		exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
        DataBuffer db = null;
		try {
			db = new DefaultDataBufferFactory().wrap(new ObjectMapper().writeValueAsBytes(new Response<String>(false, "Invalid Token")));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return exchange.getResponse().writeWith(Mono.just(db));
	}
 
	
	
}
