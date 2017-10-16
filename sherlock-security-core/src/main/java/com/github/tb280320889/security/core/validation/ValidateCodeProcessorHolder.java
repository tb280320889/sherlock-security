package com.github.tb280320889.security.core.validation;

import com.github.tb280320889.security.core.exception.ValidateCodeException;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by TangBin on 2017/10/16.
 */

@Component
public class ValidateCodeProcessorHolder {

  private final Map<String, ValidateCodeProcessor> validateCodeProcessors;

  @Autowired
  public ValidateCodeProcessorHolder(Map<String, ValidateCodeProcessor> validateCodeProcessors) {
    this.validateCodeProcessors = validateCodeProcessors;
  }

  public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType validateCodeType) {
    return findValidateCodeProcessor(validateCodeType.toString().toLowerCase());
  }

  public ValidateCodeProcessor findValidateCodeProcessor(String type) {
    final String name = type.toLowerCase() + ValidateCodeProcessor.class.getSimpleName();
    final ValidateCodeProcessor processor = validateCodeProcessors.get(name);
    if (processor == null) {
      throw new ValidateCodeException("validate code processor " + name + " does not exists");
    }
    return processor;
  }


}
