package com.example.api.exception;

import com.example.api.error.ErrorCodes;
import lombok.Getter;

public class AttributeIsNotPresentedException extends AppException {
    @Getter
    private String attributeName;

    @Override
    public String getCode() {
        return ErrorCodes.ATTRIBUTE_IS_NOT_PRESENTED;
    }

    public AttributeIsNotPresentedException(String attributeName) {
        this.attributeName = attributeName;
    }

    public AttributeIsNotPresentedException(String message, String attributeName) {
        super(message);
        this.attributeName = attributeName;
    }
}
