package com.payment.domainlayer.service;

import com.payment.adapter.entities.PaymentDetails;

import reactor.core.publisher.Mono;

public interface PaymentService {

	
	public Mono<PaymentDetails> save(PaymentDetails _prod);
	
	
}
