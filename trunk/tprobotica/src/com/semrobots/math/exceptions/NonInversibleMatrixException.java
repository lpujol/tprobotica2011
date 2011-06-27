package com.semrobots.math.exceptions;

public class NonInversibleMatrixException extends RuntimeException {

	private static final long serialVersionUID = 1032401840900316339L;

	public NonInversibleMatrixException() {
	}

	public NonInversibleMatrixException(String arg0) {
		super(arg0);
	}

	public NonInversibleMatrixException(Throwable arg0) {
		super(arg0);
	}

	public NonInversibleMatrixException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
