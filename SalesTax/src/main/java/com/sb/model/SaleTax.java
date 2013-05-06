package com.sb.model;

import java.math.BigDecimal;

public enum SaleTax {
	
	BASIC(new BigDecimal("0.1")), EXEMPT(new BigDecimal("0"));

	private final BigDecimal taxRate;

	private SaleTax(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	public BigDecimal getTaxRate() {
		return taxRate;
	}
}
