package com.github.tb280320889.security.core.validation;

import com.github.tb280320889.security.core.property.SecurityProperties;
import com.github.tb280320889.security.core.validation.image.ImageCodeGenerator;
import com.github.tb280320889.security.core.validation.sms.DefaultSmsCodeSender;
import com.github.tb280320889.security.core.validation.sms.SmsCodeSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by TangBin on 2017/10/16.
 */

@Configuration
public class ValidateCodeBeanConfig {

  private final SecurityProperties securityProperties;

  @Autowired
  public ValidateCodeBeanConfig(SecurityProperties securityProperties) {
    this.securityProperties = securityProperties;
  }

  @Bean
  @ConditionalOnMissingBean(name = "imageValidateCOdeGenerator")
  public ValidateCodeGenerator imageValidateCodeGenerator() {
    return new ImageCodeGenerator(securityProperties);
  }


  @Bean
  @ConditionalOnMissingBean(SmsCodeSender.class)
  public SmsCodeSender smsCodeSender() {
    return new DefaultSmsCodeSender();
  }

}
