package com.example.api.error;

public class ErrorCodes {

    private ErrorCodes() {
    }

    public static final String INVALID_FIELD_VALUE = "err.invalid_field_value";
    public static final String UNKNOWN = "err.unknown";
    public static final String INVALID_MEDIA_TYPE = "err.invalid_media_type";
    public static final String MEDIA_UPLOAD_ERROR = "err.media_upload_error";
    public static final String ATTRIBUTE_IS_NOT_PRESENTED = "err.attribute_is_not_presented.%s";
}
