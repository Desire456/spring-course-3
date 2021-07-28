package com.example.api.advice;

import com.example.api.dto.ErrorResponseDto;
import com.example.api.error.ErrorCodes;
import com.example.api.exception.AppException;
import com.example.api.exception.AttributeIsNotPresentedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class AppRestControllerAdvice {
    @ExceptionHandler
    public ResponseEntity<ErrorResponseDto> handleAppException(AppException e) {
        e.printStackTrace();
        if (e instanceof AttributeIsNotPresentedException) {
            return ResponseEntity.status(400).body(new ErrorResponseDto(String.format(e.getCode(),
                    ((AttributeIsNotPresentedException) e).getAttributeName())));
        }

        return ResponseEntity.status(400).body(new ErrorResponseDto(e.getCode()));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleValidationException(MethodArgumentNotValidException e) {
        e.printStackTrace();
        // TODO: handle errors
        final List<ObjectError> errors = e.getAllErrors();
        return new ErrorResponseDto(ErrorCodes.INVALID_FIELD_VALUE);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDto handleException(Exception e) {
        e.printStackTrace();
        return new ErrorResponseDto(ErrorCodes.UNKNOWN);
    }
}
