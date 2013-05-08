package com.sb.shape.area;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.sb.exception.UnknownShapeException;
import com.sb.model.Circle;
import com.sb.model.Parameter;
import com.sb.model.Rectangle;
import com.sb.model.Shape;
import com.sb.model.Triangle;

public class ShapeAreaTest {

	private final ShapeVisitor<BigDecimal,Parameter> visitor = new ShapeAreaVisitor<BigDecimal, Object>();

	@Rule
    public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void shouldReturnAreaOfCircle() {
		Circle circle = new Circle(new BigDecimal("1"));
		BigDecimal area = circle.accept(visitor, null);
		BigDecimal bigDecimal = new BigDecimal(String.valueOf(Math.PI));
		Assert.assertEquals(bigDecimal, area);
	}
	
	@Test
	public void shouldThrowExceptionWhenRadiusIsNegative() {
		BigDecimal radius = new BigDecimal("-1");
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Circle radius : " + radius+ " cannot be less than or equal to zero");
		Circle circle = new Circle(radius);
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
	
	@Test
	public void shouldThrowExceptionWhenRectangleBaseIsNegative() {
		BigDecimal base = new BigDecimal("-4");
		BigDecimal height = new BigDecimal("3");
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Rectangle base : " + base+ " cannot be less than or equal to zero");
		Rectangle rectangle = new Rectangle(base, height);
		rectangle.accept(visitor, null);
	}
	
	@Test
	public void shouldThrowExceptionWhenRectangleHeightIsNegative() {
		BigDecimal base = new BigDecimal("4");
		BigDecimal height = new BigDecimal("-3");
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Rectangle height : " + height+ " cannot be less than or equal to zero");
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
	
	@Test
	public void shouldThrowExceptionWhenTriangleBaseIsNegative() {
		BigDecimal base = new BigDecimal("-4");
		BigDecimal height = new BigDecimal("3");
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Triangle base : " + base+ " cannot be less than or equal to zero");
		Triangle triangle = new Triangle(base, height);
		triangle.accept(visitor, null);
	}
	
	@Test
	public void shouldThrowExceptionWhenTriangleHeightIsNegative() {
		BigDecimal base = new BigDecimal("4");
		BigDecimal height = new BigDecimal("-3");
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Triangle height : " + height+ " cannot be less than or equal to zero");
		Triangle triangle = new Triangle(base, height);
		triangle.accept(visitor, null);
	}
	
	@Test
	public void givenAnUnknownShapeWhenAreaIsCalculatedThenUnknownShapeExceptionShouldResult() {
		UnknownShape shape = new UnknownShape();
		exception.expect(UnknownShapeException.class);
		exception.expectMessage("Shape with class name: " + shape.getClass().getCanonicalName()+ " is NOT handled yet.");
		shape.accept(visitor, null);
		
	}
	
	private class UnknownShape implements Shape<BigDecimal,Parameter> {

		public BigDecimal accept(ShapeVisitor<BigDecimal, Parameter> v, Parameter p) {
			return v.visit(this,null);
		}
		
		
	}
}
