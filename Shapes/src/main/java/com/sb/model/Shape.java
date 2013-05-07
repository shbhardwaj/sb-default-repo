package com.sb.model;

import com.sb.shape.area.ShapeVisitor;

public interface Shape<R, P> {
	
	R accept(ShapeVisitor<R, P> paramShapeVisitor, P paramP);
}