package com.sb.model;

import com.sb.shape.area.ShapeVisitor;
import java.math.BigDecimal;

public class Circle implements Shape<BigDecimal, Parameter> {
	
	private final BigDecimal radius;

	public Circle(BigDecimal radius) {
		if (radius.doubleValue() <= 0D) {
			throw new IllegalArgumentException(" Circle radius : " + radius
					+ " cannot be less than or equal to zero");
		}
		this.radius = radius;
	}

	public BigDecimal getRadius() {
		return this.radius;
	}

	public BigDecimal accept(ShapeVisitor<BigDecimal, Parameter> v, Parameter p) {
		return (BigDecimal) v.visit(this, p);
	}
}