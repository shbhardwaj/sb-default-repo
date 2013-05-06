package com.sb.model;

import java.math.BigDecimal;

public class Item {

	private final String name;
	private final BigDecimal basePrice;
	private final int quantity;
	private final SaleTax saleTax;
	private final ImportTax importTax;
	
	public Item(String name, BigDecimal basePrice, int quantity,
			SaleTax saleTax, ImportTax importTax) {
		super();
		this.name = name;
		this.basePrice = basePrice;
		this.quantity = quantity;
		this.saleTax = saleTax;
		this.importTax = importTax;
	}

	public String getName() {
		return name;
	}

	public BigDecimal salePrice() {
		return new BigDecimal(quantity).multiply(basePrice.add(tax()));
	}
	
	public BigDecimal tax() {
		//TODO: Need to do the rounding of this. 
		return round(basePrice.multiply(saleTax.getTaxRate()).add(
				basePrice.multiply(importTax.getTaxRate())));
	}
	
	public BigDecimal round(BigDecimal value) {
		double scaledNumber = value.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() * 100;
		double resolution = scaledNumber % 5;
		if (resolution != 0) {
			scaledNumber += 5 - resolution;
		}
		double result = scaledNumber / 100;
		return new BigDecimal(String.valueOf(result));
	}
	
}
