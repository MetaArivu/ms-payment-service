package com.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;

import brave.sampler.Sampler;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(title = "APIs", version = "1.0", description = "Payment Service Documentation APIs v1.0"))
@SpringBootApplication(exclude = { MongoAutoConfiguration.class, MongoDataAutoConfiguration.class })
@EnableReactiveMongoAuditing
public class PaymentMsReactiveApplication {

	public static void main(String[] args) {
		System.setProperty("spring.webflux.base-path", APPConstant.CONTEXT_PATH);
		SpringApplication.run(PaymentMsReactiveApplication.class, args);
	}

	@EventListener({ EnvironmentChangeEvent.class })
	public void onRefresh() {
		System.out.println("environment Changed..");
	}

	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}
}
