package com.sb.model;

import com.sb.shape.area.ShapeVisitor;
import java.math.BigDecimal;

public class Triangle implements Shape<BigDecimal, Parameter> {
	
	private final BigDecimal base;
	private final BigDecimal height;

	public Triangle(BigDecimal base, BigDecimal height) {
		if (base.doubleValue() <= 0D) {
			throw new IllegalArgumentException("Triangle base : " + base
					+ " cannot be less than or equal to zero");
		}
		if (height.doubleValue() <= 0D) {
			throw new IllegalArgumentException("Triangle height : " + height
					+ " cannot be less than or equal to zero");
		}
		this.base = base;
		this.height = height;
	}

	public BigDecimal accept(ShapeVisitor<BigDecimal, Parameter> v, Parameter p) {
		return (BigDecimal) v.visit(this, p);
	}

	public BigDecimal getBase() {
		return this.base;
	}

	public BigDecimal getHeight() {
		return this.height;
	}
}