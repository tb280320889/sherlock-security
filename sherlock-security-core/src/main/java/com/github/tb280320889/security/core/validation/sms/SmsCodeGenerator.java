package com.github.tb280320889.security.core.validation.sms;

import com.github.tb280320889.security.core.property.SecurityProperties;
import com.github.tb280320889.security.core.property.SmsCodeProperties;
import com.github.tb280320889.security.core.validation.ValidateCode;
import com.github.tb280320889.security.core.validation.ValidateCodeGenerator;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created by TangBin on 2017/10/16.
 */


@Component("smsValidateCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {
  private final SecurityProperties securityProperties;

  @Autowired
  public SmsCodeGenerator(SecurityProperties securityProperties) {
    this.securityProperties = securityProperties;
  }

  @Override
  public ValidateCode generateCode(ServletWebRequest servletWebRequest) {
    final SmsCodeProperties smsCodeProperties = securityProperties.getValidateCodeProperties().getSmsCodeProperties();

    final String code = RandomStringUtils.randomNumeric(smsCodeProperties.getLength());
    return new ValidateCode(code, smsCodeProperties.getExpireIn());
  }

}
