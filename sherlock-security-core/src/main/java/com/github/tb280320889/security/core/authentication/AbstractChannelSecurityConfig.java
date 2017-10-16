package com.github.tb280320889.security.core.authentication;

import com.github.tb280320889.security.core.property.SecurityConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * Created by TangBin on 2017/10/16.
 */

public class AbstractChannelSecurityConfig extends WebSecurityConfigurerAdapter {

  private final AuthenticationSuccessHandler sherlockAuthenticationSuccessHandler;
  private final AuthenticationFailureHandler sherlockAuthenticationFailureHandler;

  @Autowired
  public AbstractChannelSecurityConfig(AuthenticationSuccessHandler sherlockAuthenticationSuccessHandler, AuthenticationFailureHandler sherlockAuthenticationFailureHandler) {
    this.sherlockAuthenticationSuccessHandler = sherlockAuthenticationSuccessHandler;
    this.sherlockAuthenticationFailureHandler = sherlockAuthenticationFailureHandler;
  }

  protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
    http.formLogin()
        .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
        .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
        .successHandler(sherlockAuthenticationSuccessHandler)
        .failureHandler(sherlockAuthenticationFailureHandler);
  }

}
