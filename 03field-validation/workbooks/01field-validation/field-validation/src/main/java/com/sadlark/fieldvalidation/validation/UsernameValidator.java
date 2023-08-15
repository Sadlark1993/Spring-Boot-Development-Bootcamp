package com.sadlark.fieldvalidation.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UsernameValidator implements ConstraintValidator<Username, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
    Matcher matcher = pattern.matcher(value);
    if (matcher.find())
      return false;
    else
      return true;
  }

}
