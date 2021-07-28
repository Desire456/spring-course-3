package com.example.api.validator;

import com.example.api.constraint.NameConstraint;
import com.example.api.service.UserService;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

@RequiredArgsConstructor
public class NameConstraintValidator implements ConstraintValidator<NameConstraint, String> {
  private final UserService service;

  private final List<String> forbidden = List.of(
      "admin",
      "root"
  );

  public void initialize(NameConstraint constraint) {
  }

  public boolean isValid(String value, ConstraintValidatorContext context) {
    return !forbidden.contains(value.toLowerCase());
  }
}
