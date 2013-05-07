package com.sb.exception;

import com.sb.model.Parameter;
import com.sb.model.Shape;

public class UnknownShapeException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	protected UnknownShapeException(String message) {
		super(message);
	}

	public UnknownShapeException(Shape<?, ?> shape, Parameter p) {
		this("Shape with class name: " + shape.getClass().getCanonicalName()
				+ " is NOT handled yet.");
	}
}