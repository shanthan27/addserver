package com.sample.adserver.exceptions;

public class AdDoesNotExistsWithThePartnerIdException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public AdDoesNotExistsWithThePartnerIdException(final String message) {
        super(message);
    }

}
