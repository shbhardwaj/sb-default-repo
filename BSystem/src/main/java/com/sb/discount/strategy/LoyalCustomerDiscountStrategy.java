package com.sb.discount.strategy;

import java.math.BigDecimal;

import com.sb.model.Bill;

public class LoyalCustomerDiscountStrategy implements DiscountStrategy {

	public BigDecimal discount(final Bill bill) {
		return bill.nonGrocerytotalAmount().multiply(LOYAL_CUSTOMER_DISCOUNT);
	}

}
