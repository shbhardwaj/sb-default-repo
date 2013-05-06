package com.sb.model;

import java.math.BigDecimal;
import java.util.List;

public class Bill {

	private Customer customer;
	private List<Item> items;
	private BigDecimal totalSalesTax = new BigDecimal("0");
	private BigDecimal totalAmount = new BigDecimal("0");

	public Bill(Customer customer, List<Item> items) {
		super();
		this.customer = customer;
		this.items = items;
		init();
	}

	private void init() {
		for (Item item : items) {
			totalSalesTax = totalSalesTax.add(item.tax());
			totalAmount = totalAmount.add(item.salePrice());
		}
	}

	public Customer getCustomer() {
		return customer;
	}

	public BigDecimal getTotalSalesTax() {
		return totalSalesTax;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

}
