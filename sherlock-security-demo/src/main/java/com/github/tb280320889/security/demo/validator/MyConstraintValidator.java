package com.github.tb280320889.security.demo.validator;

import lombok.extern.slf4j.Slf4j;

import com.github.tb280320889.security.demo.service.HelloService;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by TangBin on 2017/10/11.
 */

@Slf4j
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {

  private final HelloService helloService;

  @Autowired
  public MyConstraintValidator(HelloService helloService) {
    this.helloService = helloService;
  }


  @Override
  public void initialize(MyConstraint myConstraint) {
    log.info("my validator init");
  }

  @Override
  public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
    helloService.greeting(o.toString());
    return false;
  }
}
