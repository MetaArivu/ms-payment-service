package com.payment.adapter.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.payment.adapter.entities.PaymentDetails;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface PaymentRepository extends ReactiveMongoRepository<PaymentDetails, String> {

	public Mono<PaymentDetails> findByCustomerIdAndActive(String id, boolean active);
	
	

}
