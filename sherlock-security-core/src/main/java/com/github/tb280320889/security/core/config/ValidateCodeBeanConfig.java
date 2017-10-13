package com.github.tb280320889.security.core.config;

import com.github.tb280320889.security.core.property.SecurityProperties;
import com.github.tb280320889.security.core.validation.ImageCodeGenerator;
import com.github.tb280320889.security.core.validation.ValidateCodeGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by TangBin on 2017/10/13.
 */

@Configuration
public class ValidateCodeBeanConfig {

  private final SecurityProperties securityProperties;

  @Autowired
  public ValidateCodeBeanConfig(SecurityProperties securityProperties) {
    this.securityProperties = securityProperties;
  }

  @Bean
  @ConditionalOnMissingBean(name = "imageCodeGenerator")
  public ValidateCodeGenerator imageCodeGenerator() {
    return new ImageCodeGenerator(securityProperties);
  }

}
