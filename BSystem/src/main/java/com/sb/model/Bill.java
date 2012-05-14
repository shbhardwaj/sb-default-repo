package com.sb.model;

import java.math.BigDecimal;
import java.util.List;

import com.sb.discount.strategy.AmountBasedDiscountStrategy;
import com.sb.discount.strategy.DiscountStrategy;

public class Bill {

	private Customer customer;
	private List<LineItem> lineItems;
	private BigDecimal totalAmount = new BigDecimal(0);
	private BigDecimal nonGroceryTotalAmount = new BigDecimal(0);

	public Bill(Customer customer, List<LineItem> lineItems) {
		super();
		this.customer = customer;
		this.lineItems = lineItems;
		init();
	}

	private void init() {
		for (LineItem lineItem : lineItems) {
			totalAmount = totalAmount.add(lineItem.getPrice());
			if (!lineItem.isGrocery()) {
				nonGroceryTotalAmount = nonGroceryTotalAmount.add(lineItem.getPrice());
			}
		}
	}

	public BigDecimal netPayableAmount() {
		return totalAmount.subtract((discountStrategy().discount(this).add(new AmountBasedDiscountStrategy().discount(this))));
	}

	private DiscountStrategy discountStrategy() {
		return customer.getCustomerType().getDiscountStrategy();
	}

	public BigDecimal totalAmount() {
		return totalAmount;
	}

	public BigDecimal nonGrocerytotalAmount() {
		return nonGroceryTotalAmount;
	}
}
