package com.onlinebankingsystem.springproject.exception;

public class CustomerNotFoundException extends RuntimeException {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomerNotFoundException(Long id) {super("The customer id '" + id + "' does not exist in our records." );}

	public CustomerNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public CustomerNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public CustomerNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public CustomerNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
