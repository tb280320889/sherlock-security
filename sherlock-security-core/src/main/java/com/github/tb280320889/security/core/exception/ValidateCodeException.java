package com.github.tb280320889.security.core.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by TangBin on 2017/10/13.
 */

public class ValidateCodeException extends AuthenticationException {

  private static final long serialVersionUID = 751209937228846359L;

  public ValidateCodeException(String msg) {
    super(msg);
  }
}
