package com.github.tb280320889.security.core.authentication.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * Created by TangBin on 2017/10/16.
 */

@Component
public class SmsCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

  private final AuthenticationSuccessHandler sherlockAuthenticationSuccessHandler;
  private final AuthenticationFailureHandler sherlockAuthenticationFailureHandler;
  private final UserDetailsService userDetailsService;

  @Autowired
  public SmsCodeAuthenticationSecurityConfig(AuthenticationSuccessHandler sherlockAuthenticationSuccessHandler, AuthenticationFailureHandler sherlockAuthenticationFailureHandler, UserDetailsService userDetailsService) {
    this.sherlockAuthenticationSuccessHandler = sherlockAuthenticationSuccessHandler;
    this.sherlockAuthenticationFailureHandler = sherlockAuthenticationFailureHandler;
    this.userDetailsService = userDetailsService;
  }


  @Override
  public void configure(HttpSecurity http) throws Exception {
    final SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();
    smsCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
    smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(sherlockAuthenticationSuccessHandler);
    smsCodeAuthenticationFilter.setAuthenticationFailureHandler(sherlockAuthenticationFailureHandler);

    final SmsCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider();
    smsCodeAuthenticationProvider.setUserDetailsService(userDetailsService);
    http.authenticationProvider(smsCodeAuthenticationProvider)
        .addFilterAfter(smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

  }
}
