package com.sb.model;

import java.math.BigDecimal;

public class LineItem {

	private String name;
	private ItemType type;
	private BigDecimal price;

	public LineItem(String name, ItemType type, BigDecimal price) {
		super();
		this.name = name;
		this.type = type;
		if (price.doubleValue() < 0)
			throw new IllegalArgumentException("Line item price: "+price.doubleValue()+" cannot be less than zero");
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public ItemType getType() {
		return type;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public boolean isGrocery() {
		return type.equals(ItemType.GROCERY);
	}

}
