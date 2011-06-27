package com.semrobots.math.exceptions;

public class InvalidMatrixOpException extends RuntimeException {

	private static final long serialVersionUID = 2348724446237153417L;

	public InvalidMatrixOpException() {
	}

	public InvalidMatrixOpException(String arg0) {
		super(arg0);
	}

	public InvalidMatrixOpException(Throwable arg0) {
		super(arg0);
	}

	public InvalidMatrixOpException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
