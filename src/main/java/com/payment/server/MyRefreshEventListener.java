package com.payment.server;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service
public class MyRefreshEventListener implements ApplicationListener<RefreshScopeRefreshedEvent> {

	private static final Logger log = (Logger) LoggerFactory.getLogger(MyRefreshEventListener.class);

	@Override
	public void onApplicationEvent(RefreshScopeRefreshedEvent event) {
		
		log.info("/refresh is called..");
	}

}
