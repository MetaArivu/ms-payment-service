package com.payment.adapter.controller.v1;

import static com.payment.APPConstant.V1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payment.adapter.dto.Response;
import com.payment.adapter.entities.PaymentDetails;
import com.payment.domainlayer.service.PaymentService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping(V1)
@CrossOrigin("*")
public class PaymentController {

	@Autowired
	private PaymentService svc;

	@PostMapping(value = "/")
	public Mono<ResponseEntity<Response<PaymentDetails>>> save(@RequestBody PaymentDetails _prod) {

		return svc.save(_prod).map(prod -> {
			return new ResponseEntity<Response<PaymentDetails>>(
					new Response<PaymentDetails>(true, "Record Saved Successully", prod), HttpStatus.OK);
		}).defaultIfEmpty(new ResponseEntity<Response<PaymentDetails>>(
				new Response<PaymentDetails>(false, "Record Not Saved Successully"), HttpStatus.NOT_FOUND));

	}
 
}
