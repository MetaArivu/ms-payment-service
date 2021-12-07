package com.payment.adapter.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(value = "payment_details")
public class PaymentDetails extends BaseEntity {

	private String cartId;
	private String customerId;
	private double total;
	private boolean status;

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public static String invalidMsg() {
		return "Please enter valid Payment Info";
	}

	@Override
	public String toString() {
		return id + "|" + cartId + "|" + customerId + "|" + total + "|" + status;
	}

	@JsonIgnore
	@Override
	public boolean isValid() {
		return true;
	}

}
