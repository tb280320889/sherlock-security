package com.github.tb280320889.security.core.validation;


import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.stereotype.Component;

/**
 * Created by TangBin on 2017/10/16.
 */

@Component
public class ValidateCodeSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

  private final Filter validateCodeFilter;

  @Autowired
  public ValidateCodeSecurityConfig(Filter validateCodeFilter) {
    this.validateCodeFilter = validateCodeFilter;
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.addFilterBefore(validateCodeFilter, AbstractAuthenticationProcessingFilter.class);
  }
}
