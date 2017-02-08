package com.sample.adserver.exceptions;

public class AdAlreadyExistsWithThePartnerIdException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AdAlreadyExistsWithThePartnerIdException(final String message) {
        super(message);
    }
}
