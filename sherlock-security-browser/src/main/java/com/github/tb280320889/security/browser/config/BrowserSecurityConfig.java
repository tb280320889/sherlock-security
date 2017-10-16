package com.github.tb280320889.security.browser.config;

import com.github.tb280320889.security.core.authentication.AbstractChannelSecurityConfig;
import com.github.tb280320889.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.github.tb280320889.security.core.property.SecurityConstants;
import com.github.tb280320889.security.core.property.SecurityProperties;
import com.github.tb280320889.security.core.validation.ValidateCodeSecurityConfig;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * Created by TangBin on 2017/10/12.
 */

@Configuration
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig {

  private final SecurityProperties securityProperties;
  private final DataSource dataSource;
  private final UserDetailsService userDetailsService;
  private final SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
  private final ValidateCodeSecurityConfig validateCodeSecurityConfig;
  private final AuthenticationSuccessHandler sherlockAuthenticationSuccessHandler;
  private final AuthenticationFailureHandler sherlockAuthenticationFailureHandler;
  private final SpringSocialConfigurer sherlockSocialSecurityConfig;


  /**
   * @param securityProperties
   * @param dataSource
   * @param userDetailsService
   * @param smsCodeAuthenticationSecurityConfig
   * @param validateCodeSecurityConfig
   */
  @Autowired
  public BrowserSecurityConfig(SecurityProperties securityProperties, DataSource dataSource, UserDetailsService userDetailsService, SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig, ValidateCodeSecurityConfig validateCodeSecurityConfig, AuthenticationSuccessHandler sherlockAuthenticationSuccessHandler, AuthenticationFailureHandler sherlockAuthenticationFailureHandler, SpringSocialConfigurer sherlockSocialSecurityConfig) {
    super(sherlockAuthenticationSuccessHandler, sherlockAuthenticationFailureHandler);
    this.securityProperties = securityProperties;
    this.dataSource = dataSource;
    this.userDetailsService = userDetailsService;
    this.smsCodeAuthenticationSecurityConfig = smsCodeAuthenticationSecurityConfig;
    this.validateCodeSecurityConfig = validateCodeSecurityConfig;
    this.sherlockAuthenticationSuccessHandler = sherlockAuthenticationSuccessHandler;
    this.sherlockAuthenticationFailureHandler = sherlockAuthenticationFailureHandler;
    this.sherlockSocialSecurityConfig = sherlockSocialSecurityConfig;
  }

  /**
   * @return
   */
  @Bean
  public PersistentTokenRepository persistentTokenRepository() {
    final JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
    tokenRepository.setDataSource(dataSource);
//    tokenRepository.setCreateTableOnStartup(true);
    return tokenRepository;
  }


  @Override
  protected void configure(HttpSecurity http) throws Exception {
    applyPasswordAuthenticationConfig(http);

    http.apply(validateCodeSecurityConfig)
        .and()
        .apply(smsCodeAuthenticationSecurityConfig)
        .and()
        .apply(sherlockSocialSecurityConfig)
        .and()
        .rememberMe()
        .tokenRepository(persistentTokenRepository())
        .tokenValiditySeconds(securityProperties.getBrowserProperties().getRememberMeSeconds())
        .userDetailsService(userDetailsService)
        .and()
        .authorizeRequests()
        .antMatchers(
            SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
            SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
            securityProperties.getBrowserProperties().getLoginPage(),
            SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .csrf().disable();
  }


}
