package com.example.api.exception;

import com.example.api.error.ErrorCodes;

public class MediaUploadException extends AppException {
    @Override
    public String getCode() {
        return ErrorCodes.MEDIA_UPLOAD_ERROR;
    }

    public MediaUploadException() {
        super();
    }

    public MediaUploadException(String message) {
        super(message);
    }

    public MediaUploadException(String message, Throwable cause) {
        super(message, cause);
    }

    public MediaUploadException(Throwable cause) {
        super(cause);
    }

    public MediaUploadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
