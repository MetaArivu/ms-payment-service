package com.payment.server.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class RequestAspect {

	private static final Logger log = (Logger) LoggerFactory.getLogger(RequestAspect.class);

	@Around("allOperations()")
	public Object validateToken(ProceedingJoinPoint joinPoint) throws Throwable {
		Object obj = joinPoint.proceed();
		log.info(joinPoint.getTarget().getClass().getName()+"| "+joinPoint.getSignature().getName());
		return obj;
	}

	@Pointcut("execution(* com.payment.adapter.controller.*..*(..))")
	public void allOperations() {
	}
}
