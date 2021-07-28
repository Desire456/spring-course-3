package com.example.api.rest;

import com.example.api.dto.ErrorResponseDto;
import com.example.api.error.ErrorCodes;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

// last resort
@RestController
@RequestMapping("${server.error.path:${error.path:/error}}")
public class AppErrorController extends AbstractErrorController {
  public AppErrorController(ErrorAttributes errorAttributes) {
    super(errorAttributes);
  }

  @RequestMapping
  public ResponseEntity<ErrorResponseDto> error(HttpServletRequest req, HttpServletResponse resp) {
    final HttpStatus status = HttpStatus.valueOf(
        resp.getStatus()
    );

    // TODO: handle errors
    final Map<String, Object> errors = getErrorAttributes(req, ErrorAttributeOptions.defaults());

    final ErrorResponseDto response = new ErrorResponseDto(
        ErrorCodes.UNKNOWN
    );

    return new ResponseEntity<>(response, status);
  }
}
