package com.sb.discount.strategy;

import java.math.BigDecimal;

import com.sb.model.Bill;

public class NoDiscountStrategy implements DiscountStrategy {

	public BigDecimal discount(final Bill bill) {
		return new BigDecimal("0");
	}

}
