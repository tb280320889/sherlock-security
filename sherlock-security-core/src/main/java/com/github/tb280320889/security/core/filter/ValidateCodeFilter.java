package com.github.tb280320889.security.core.filter;

import com.github.tb280320889.security.core.controller.ValidateCodeController;
import com.github.tb280320889.security.core.exception.ValidateCodeException;
import com.github.tb280320889.security.core.property.SecurityProperties;
import com.github.tb280320889.security.core.validation.ImageCode;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Created by TangBin on 2017/10/13.
 */

@Getter
@Setter
public class ValidateCodeFilter extends OncePerRequestFilter {

  private final SecurityProperties securityProperties;
  private AuthenticationFailureHandler authenticationFailureHandler;
  private Set<String> urls = new LinkedHashSet<>(5);
  private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
  private AntPathMatcher antPathMatcher = new AntPathMatcher();

  @Autowired
  public ValidateCodeFilter(SecurityProperties securityProperties) {
    this.securityProperties = securityProperties;
  }

  @Override
  public void afterPropertiesSet() throws ServletException {
    super.afterPropertiesSet();
    final String[] configUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getValidateCodeProperties().getImageCodeProperties().getUrl(), ",");

    Collections.addAll(urls, configUrls);
    urls.add("/authentication/form");

  }

  /**
   * @param httpServletRequest
   * @param httpServletResponse
   * @param filterChain
   * @throws ServletException
   * @throws IOException
   */
  @Override
  protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

    boolean action = false;
    for (String url : urls) {
      if (antPathMatcher.match(url, httpServletRequest.getRequestURI())) {
        action = true;
      }
    }

    if (action) {
      try {
        validate(new ServletWebRequest(httpServletRequest));
      } catch (ValidateCodeException e) {
        authenticationFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
        return;
      }
    }

    filterChain.doFilter(httpServletRequest, httpServletResponse);
  }

  /**
   * @param servletWebRequest
   * @throws ServletRequestBindingException
   */
  private void validate(ServletWebRequest servletWebRequest) throws ServletRequestBindingException {
    final ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(servletWebRequest, ValidateCodeController.SESSION_KEY);
    final String codeInRequest = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), "imageCode");

    if (StringUtils.isBlank(codeInRequest)) {
      throw new ValidateCodeException("identifying code must not be blank");
    }

    if (codeInSession == null) {
      throw new ValidateCodeException("identifying code does not exists");
    }

    if (codeInSession.isExpired()) {
      sessionStrategy.removeAttribute(servletWebRequest, ValidateCodeController.SESSION_KEY);
      throw new ValidateCodeException("identifying code is expired");
    }

    if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
      throw new ValidateCodeException("identifying code does not match");
    }

    sessionStrategy.removeAttribute(servletWebRequest, ValidateCodeController.SESSION_KEY);
  }
}
