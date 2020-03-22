package com.spring.jdbc.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Sale {
	private int id;
	private String item;
	private int quantity;
	private float amount;

	public Sale(String item, int quantity, float amount) {
		this.item = item;
		this.quantity = quantity;
		this.amount = amount;
	}
}
