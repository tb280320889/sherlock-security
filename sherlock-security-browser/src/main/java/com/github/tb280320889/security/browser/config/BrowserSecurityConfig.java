package com.github.tb280320889.security.browser.config;

import com.github.tb280320889.security.browser.authentication.SherlockAuthenticationFailureHandler;
import com.github.tb280320889.security.browser.authentication.SherlockAuthenticationSuccessHandler;
import com.github.tb280320889.security.browser.controller.AuthenticationController;
import com.github.tb280320889.security.core.controller.ValidateCodeController;
import com.github.tb280320889.security.core.filter.ValidateCodeFilter;
import com.github.tb280320889.security.core.property.SecurityProperties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

/**
 * Created by TangBin on 2017/10/12.
 */

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

  private final SecurityProperties securityProperties;
  private final DataSource dataSource;
  private final UserDetailsService userDetailsService;
  private final SherlockAuthenticationFailureHandler sherlockAuthenticationFailureHandler;
  private final SherlockAuthenticationSuccessHandler sherlockAuthenticationSuccessHandler;

  /**
   * @param securityProperties
   * @param dataSource
   * @param userDetailsService
   * @param sherlockAuthenticationFailureHandler
   * @param sherlockAuthenticationSuccessHandler
   */
  @Autowired
  public BrowserSecurityConfig(SecurityProperties securityProperties, @Qualifier("dataSource") DataSource dataSource, UserDetailsService userDetailsService, SherlockAuthenticationFailureHandler sherlockAuthenticationFailureHandler, SherlockAuthenticationSuccessHandler sherlockAuthenticationSuccessHandler) {
    this.securityProperties = securityProperties;
    this.dataSource = dataSource;
    this.userDetailsService = userDetailsService;
    this.sherlockAuthenticationFailureHandler = sherlockAuthenticationFailureHandler;
    this.sherlockAuthenticationSuccessHandler = sherlockAuthenticationSuccessHandler;
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
    ValidateCodeFilter filter = new ValidateCodeFilter(securityProperties);
    filter.setAuthenticationFailureHandler(sherlockAuthenticationFailureHandler);
    filter.afterPropertiesSet();

    http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
        .formLogin()
        .loginPage(AuthenticationController.AUTHENTICATION + "/require")
        .loginProcessingUrl("/authentication/form")
        .successHandler(sherlockAuthenticationSuccessHandler)
        .failureHandler(sherlockAuthenticationFailureHandler)
        .and()

        .rememberMe()
        .tokenRepository(persistentTokenRepository())
        .tokenValiditySeconds(securityProperties.getBrowserProperties().getRememberMeSeconds())
        .userDetailsService(userDetailsService)
        .and()

        .authorizeRequests()
        .antMatchers(AuthenticationController.AUTHENTICATION + "/*",
            "/login/*",
            ValidateCodeController.VALIDATE_CODE + "/*")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .csrf().disable();
  }


}
