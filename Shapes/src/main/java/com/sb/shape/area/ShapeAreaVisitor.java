package com.sb.shape.area;

import com.sb.exception.UnknownShapeException;
import com.sb.model.Circle;
import com.sb.model.Parameter;
import com.sb.model.Rectangle;
import com.sb.model.Shape;
import com.sb.model.Triangle;
import java.math.BigDecimal;

public class ShapeAreaVisitor<R, P> implements	ShapeVisitor<BigDecimal, Parameter> {
	
	public BigDecimal visit(Circle circle, Parameter p) {
		double area = 3.141592653589793D * Math.pow(circle.getRadius()
				.doubleValue(), 2.0D);
		return new BigDecimal(String.valueOf(area));
	}

	public BigDecimal visit(Circle circle) {
		return visit(circle, null);
	}

	public BigDecimal visit(Rectangle rectangle, Parameter p) {
		return rectangle.getBase().multiply(rectangle.getHeight());
	}

	public BigDecimal visit(Rectangle rectangle) {
		return visit(rectangle, null);
	}

	public BigDecimal visit(Triangle triangle, Parameter p) {
		return triangle.getBase().multiply(triangle.getHeight())
				.divide(new BigDecimal("2"));
	}

	public BigDecimal visit(Triangle triangle) {
		return visit(triangle, null);
	}

	public BigDecimal visit(Shape<?, ?> shape, Parameter p) {
		throw new UnknownShapeException(shape, p);
	}

	public BigDecimal visit(Shape<?, ?> shape) {
		throw new UnknownShapeException(shape, null);
	}
}