package com.github.tb280320889.security.browser.config;

import com.github.tb280320889.security.browser.controller.AuthenticationController;
import com.github.tb280320889.security.core.property.SecurityProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by TangBin on 2017/10/12.
 */

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {


  private final SecurityProperties securityProperties;

  @Autowired
  public BrowserSecurityConfig(SecurityProperties securityProperties) {
    this.securityProperties = securityProperties;
  }


  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.formLogin()
        .loginPage(AuthenticationController.AUTHENTICATION + "/require")
        .loginProcessingUrl("/authentication/form")
        .and()
        .authorizeRequests()
        .antMatchers(AuthenticationController.AUTHENTICATION + "/*",
            securityProperties.getBrowserProperties().getLoginPage())
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .csrf().disable();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
