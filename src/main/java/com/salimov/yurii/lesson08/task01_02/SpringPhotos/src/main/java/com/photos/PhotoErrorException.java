package com.photos;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public final class PhotoErrorException extends RuntimeException {

    public PhotoErrorException() {
        super();
    }

    public PhotoErrorException(final String message) {
        super(message);
    }

    public PhotoErrorException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public PhotoErrorException(final Throwable cause) {
        super(cause);
    }
}