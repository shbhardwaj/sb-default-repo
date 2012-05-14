package com.sb.model;

import com.sb.discount.strategy.AffiliateDiscountStrategy;
import com.sb.discount.strategy.DiscountStrategy;
import com.sb.discount.strategy.EmployeeDiscountStrategy;
import com.sb.discount.strategy.LoyalCustomerDiscountStrategy;
import com.sb.discount.strategy.NoDiscountStrategy;

public enum CustomerType {

	EMPLOYEE(new EmployeeDiscountStrategy()), AFFILIATE(new AffiliateDiscountStrategy()), LOYAL(new LoyalCustomerDiscountStrategy()), NORMAL(
			new NoDiscountStrategy());

	private DiscountStrategy discountStrategy;

	CustomerType(DiscountStrategy discountStrategy) {
		this.discountStrategy = discountStrategy;
	}

	public DiscountStrategy getDiscountStrategy() {
		return discountStrategy;
	}
	
}
