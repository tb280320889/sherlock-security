package com.github.tb280320889.security.core.validation;

import com.github.tb280320889.security.core.property.SecurityProperties;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created by TangBin on 2017/10/13.
 */


public class SmsCodeGenerator implements ValidateCodeGenerator {

  private final SecurityProperties securityProperties;

  @Autowired
  public SmsCodeGenerator(SecurityProperties securityProperties) {
    this.securityProperties = securityProperties;
  }

  @Override
  public ValidateCode generateCode(ServletWebRequest httpServletRequest) {

    final String code = RandomStringUtils.randomNumeric(securityProperties.getValidateCodeProperties().getSmsCodeProperties().getLength());

    return new ValidateCode(code, securityProperties.getValidateCodeProperties().getSmsCodeProperties().getExpireIn());
  }


}
