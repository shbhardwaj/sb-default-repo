package com.sb.shape.area;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.sb.exception.UnknownShapeException;
import com.sb.model.Circle;
import com.sb.model.Parameter;
import com.sb.model.Rectangle;
import com.sb.model.Shape;
import com.sb.model.Triangle;

public class ShapeAreaTest {

	private final ShapeVisitor<BigDecimal,Parameter> visitor = new ShapeAreaVisitor<BigDecimal, Object>();

	@Test
	public void shouldReturnAreaOfCircle() {
		Circle circle = new Circle(new BigDecimal("1"));
		BigDecimal area = circle.accept(visitor, null);
		BigDecimal bigDecimal = new BigDecimal(String.valueOf(Math.PI));
		Assert.assertEquals(bigDecimal, area);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenRadiusIsNegative() {
		Circle circle = new Circle(new BigDecimal("-1"));
		circle.accept(visitor, null);
	}

	@Test
	public void shouldReturnAreaOfRectangle() {
		BigDecimal base = new BigDecimal("4");
		BigDecimal height = new BigDecimal("3");
		Rectangle rectangle = new Rectangle(base, height);
		BigDecimal area = rectangle.accept(visitor, null);
		Assert.assertEquals(new BigDecimal("12"), area);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenRectangleBaseIsNegative() {
		BigDecimal base = new BigDecimal("-4");
		BigDecimal height = new BigDecimal("3");
		Rectangle rectangle = new Rectangle(base, height);
		rectangle.accept(visitor, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenRectangleHeightIsNegative() {
		BigDecimal base = new BigDecimal("4");
		BigDecimal height = new BigDecimal("-3");
		Rectangle rectangle = new Rectangle(base, height);
		rectangle.accept(visitor, null);
	}
	
	@Test
	public void shouldReturnAreaOfTriangle() {
		BigDecimal base = new BigDecimal("4");
		BigDecimal height = new BigDecimal("3");
		Triangle triangle = new Triangle(base, height);
		BigDecimal area = triangle.accept(visitor, null);
		Assert.assertEquals(new BigDecimal("6"), area);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenTriangleBaseIsNegative() {
		BigDecimal base = new BigDecimal("-4");
		BigDecimal height = new BigDecimal("3");
		Triangle triangle = new Triangle(base, height);
		triangle.accept(visitor, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenTriangleHeightIsNegative() {
		BigDecimal base = new BigDecimal("4");
		BigDecimal height = new BigDecimal("-3");
		Triangle triangle = new Triangle(base, height);
		triangle.accept(visitor, null);
	}
	
	@Test(expected = UnknownShapeException.class)
	public void givenAnUnknownShapeWhenAreaIsCalculatedThenUnknownShapeExceptionShouldResult() {
		UnknownShape shape = new UnknownShape();
		shape.accept(visitor, null);
		
	}
	
	private class UnknownShape implements Shape<BigDecimal,Parameter> {

		public BigDecimal accept(ShapeVisitor<BigDecimal, Parameter> v, Parameter p) {
			return v.visit(this,null);
		}
		
		
	}
}
