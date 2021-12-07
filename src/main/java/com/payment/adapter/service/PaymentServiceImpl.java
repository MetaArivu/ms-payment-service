package com.payment.adapter.service;

import static com.payment.APPConstant.KAFKA_TOPIC_PAYMENT_EVENT;

import java.util.ArrayList;
import java.util.List;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.payment.adapter.entities.PaymentDetails;
import com.payment.adapter.repository.PaymentRepository;
import com.payment.domainlayer.service.PaymentService;
import com.payment.server.exceptions.InvalidInputException;

import reactor.core.publisher.Mono;

@Service
public class PaymentServiceImpl implements PaymentService {

	private static final Logger log = (Logger) LoggerFactory.getLogger(PaymentServiceImpl.class);

	@Autowired
	private PaymentRepository prodRepo;

	@Autowired
	private KafkaTemplate<String, PaymentDetails> kafkaTemplate;
 

	@Override
	public Mono<PaymentDetails> save(PaymentDetails _payment) {
		String authHeader = MDC.get("Authorization");
		log.info("authToken={}",authHeader);
		log.info("Save Payment Details=" + _payment);

		if (_payment == null || !_payment.isValid()) {
			Mono<PaymentDetails> fallback = Mono.error(new InvalidInputException(PaymentDetails.invalidMsg()));
			return fallback;
		}

		_payment.setStatus(true);
		return prodRepo.save(_payment).doOnNext((prod) -> {
			this.publishEvent(_payment, authHeader);
		});
	}
 
	private void publishEvent(PaymentDetails event, String authHeader) {

		List<Header> recordHeaders = new ArrayList<Header>();
		recordHeaders.add(new RecordHeader("Authorization", authHeader.getBytes()));
		
		log.info("Publishing on topic={} data={}", KAFKA_TOPIC_PAYMENT_EVENT, event);
		ListenableFuture<SendResult<String, PaymentDetails>> listenableFuture = kafkaTemplate
				.send(buildProducerRecord(event,authHeader));

		listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, PaymentDetails>>() {

			@Override
			public void onSuccess(SendResult<String, PaymentDetails> result) {
				log.info("Ack Received, Message published successfully on topic={}, key={}",
						KAFKA_TOPIC_PAYMENT_EVENT, result.getProducerRecord().key());

			}

			@Override
			public void onFailure(Throwable ex) {
				log.error("Message cannot be published Exception={}, Event={}, Topic={}", ex.getMessage(), event,
						KAFKA_TOPIC_PAYMENT_EVENT);
				log.error("Exception=", ex);
			}
		});
		
	}
	
	private ProducerRecord<String, PaymentDetails> buildProducerRecord(PaymentDetails event, String authHeader) {

		List<Header> recordHeaders = new ArrayList<Header>();
		recordHeaders.add(new RecordHeader("Authorization", authHeader.getBytes()));

		return new ProducerRecord<String, PaymentDetails>(KAFKA_TOPIC_PAYMENT_EVENT, null, event.getId(), event, recordHeaders);
	}
 
	
}
