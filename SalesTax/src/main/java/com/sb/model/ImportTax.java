package com.sb.model;

import java.math.BigDecimal;

public enum ImportTax {
	
	BASIC(new BigDecimal("0.05")), EXEMPT(new BigDecimal("0"));

	private final BigDecimal taxRate;

	private ImportTax(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	public BigDecimal getTaxRate() {
		return taxRate;
	}

}
