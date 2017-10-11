package com.github.tb280320889.security.demo.validator;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by TangBin on 2017/10/11.
 */

@Target( {ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyConstraintValidator.class)
public @interface MyConstraint {

  /**
   * @return
   */
  String message();

  /**
   * @return
   */
  Class<?>[] groups() default {};

  /**
   * @return
   */
  Class<? extends Payload>[] payload() default {};
}
