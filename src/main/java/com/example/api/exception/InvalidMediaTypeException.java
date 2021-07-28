package com.example.api.exception;

import com.example.api.error.ErrorCodes;

public class InvalidMediaTypeException extends AppException {
    @Override
    public String getCode() {
        return ErrorCodes.INVALID_MEDIA_TYPE;
    }

    public InvalidMediaTypeException() {
        super();
    }

    public InvalidMediaTypeException(String message) {
        super(message);
    }

    public InvalidMediaTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidMediaTypeException(Throwable cause) {
        super(cause);
    }

    public InvalidMediaTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
