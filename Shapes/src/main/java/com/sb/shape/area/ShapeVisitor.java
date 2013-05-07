package com.sb.shape.area;

import com.sb.model.Circle;
import com.sb.model.Rectangle;
import com.sb.model.Shape;
import com.sb.model.Triangle;

public interface ShapeVisitor<R, P> {
	
	R visit(Circle paramCircle, P paramP);

	R visit(Circle paramCircle);

	R visit(Rectangle paramRectangle, P paramP);

	R visit(Rectangle paramRectangle);

	R visit(Triangle paramTriangle, P paramP);

	R visit(Triangle paramTriangle);

	R visit(Shape<?, ?> paramShape, P paramP);

	R visit(Shape<?, ?> paramShape);
}