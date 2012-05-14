package com.sb.discount.strategy;

import java.math.BigDecimal;

import com.sb.model.Bill;

public class AffiliateDiscountStrategy implements DiscountStrategy {

	public BigDecimal discount(final Bill bill) {
		return bill.nonGrocerytotalAmount().multiply(AFFILIATE_DISCOUNT);
	}

}
