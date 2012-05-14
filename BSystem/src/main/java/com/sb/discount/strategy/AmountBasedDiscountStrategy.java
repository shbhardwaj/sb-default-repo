package com.sb.discount.strategy;

import java.math.BigDecimal;

import com.sb.model.Bill;

public class AmountBasedDiscountStrategy implements DiscountStrategy {

	public BigDecimal discount(final Bill bill) {
		int discount = (bill.totalAmount().intValue()/100)*5;
		return new BigDecimal(String.valueOf(discount));
	}

}
