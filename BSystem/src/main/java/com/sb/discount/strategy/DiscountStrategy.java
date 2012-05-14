package com.sb.discount.strategy;

import java.math.BigDecimal;

import com.sb.model.Bill;

public interface DiscountStrategy {
	
	BigDecimal EMPLOYEE_DISCOUNT = new BigDecimal("0.3");
	BigDecimal AFFILIATE_DISCOUNT = new BigDecimal("0.1");
	BigDecimal LOYAL_CUSTOMER_DISCOUNT = new BigDecimal("0.05");

	BigDecimal discount(final Bill bill);
}
