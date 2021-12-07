package com.payment.server;

import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;


import ch.qos.logback.classic.Logger;

@Configuration
public class AppEventListener {

	private static final Logger log = (Logger) LoggerFactory.getLogger(AppEventListener.class);
	  
	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
		log.info("Payment Service Getting Started..");
	}

}