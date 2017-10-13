package com.github.tb280320889.security.core.validation;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created by TangBin on 2017/10/13.
 */

//@Component("imageCodeGenerator")
@Slf4j
public class DemoImageCodeGenerator implements ValidateCodeGenerator {
  @Override
  public ImageCode generateCode(ServletWebRequest servletWebRequest) {
    log.info("newer image code logic");
    return null;
  }
}
