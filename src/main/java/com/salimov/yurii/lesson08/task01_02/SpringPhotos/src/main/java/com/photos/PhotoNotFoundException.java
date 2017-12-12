package com.photos;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public final class PhotoNotFoundException extends RuntimeException {

    public PhotoNotFoundException() {
        super();
    }

    public PhotoNotFoundException(final String message) {
        super(message);
    }

    public PhotoNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public PhotoNotFoundException(final Throwable cause) {
        super(cause);
    }
}