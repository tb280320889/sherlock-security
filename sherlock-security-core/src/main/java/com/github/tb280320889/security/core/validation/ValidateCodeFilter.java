package com.github.tb280320889.security.core.validation;

import com.github.tb280320889.security.core.exception.ValidateCodeException;
import com.github.tb280320889.security.core.property.SecurityConstants;
import com.github.tb280320889.security.core.property.SecurityProperties;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Created by TangBin on 2017/10/13.
 */

@Getter
@Setter
@Slf4j
@Component
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

  private final AuthenticationFailureHandler authenticationFailureHandler;
  private final SecurityProperties securityProperties;
  private final ValidateCodeProcessorHolder validateCodeProcessorHolder;
  /**
   * all the url which need validate code
   */
  private Map<String, ValidateCodeType> urlMap = new HashMap<>(8);
  /**
   * Util Class to verify the request url and config url
   */
  private AntPathMatcher pathMatcher = new AntPathMatcher();

  /**
   * @param authenticationFailureHandler validate code failure handler
   * @param securityProperties           system config
   * @param validateCodeProcessorHolder  validate code processor
   */
  @Autowired
  public ValidateCodeFilter(AuthenticationFailureHandler authenticationFailureHandler, SecurityProperties securityProperties, ValidateCodeProcessorHolder validateCodeProcessorHolder) {
    this.authenticationFailureHandler = authenticationFailureHandler;
    this.securityProperties = securityProperties;
    this.validateCodeProcessorHolder = validateCodeProcessorHolder;
  }

  /**
   * init  config which urls to intercept
   */
  @Override
  public void afterPropertiesSet() throws ServletException {
    super.afterPropertiesSet();

    urlMap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM, ValidateCodeType.IMAGE);
    addUrlToMap(securityProperties.getValidateCodeProperties().getImageCodeProperties().getUrl(), ValidateCodeType.IMAGE);

    urlMap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE, ValidateCodeType.SMS);
    addUrlToMap(securityProperties.getValidateCodeProperties().getImageCodeProperties().getUrl(), ValidateCodeType.SMS);
  }

  /**
   * put validate code type into map
   *
   * @param urlString
   * @param type
   */
  protected void addUrlToMap(String urlString, ValidateCodeType type) {
    if (StringUtils.isNotBlank(urlString)) {
      String[] urls = StringUtils.splitByWholeSeparatorPreserveAllTokens(urlString, ",");
      for (String url : urls) {
        urlMap.put(url, type);
      }
    }
  }

  /**
   * @param request
   * @param response
   * @param chain
   * @throws ServletException
   * @throws IOException
   */
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws ServletException, IOException {

    ValidateCodeType type = getValidateCodeType(request);
    if (type != null) {
      log.info("verify request (" + request.getRequestURI() + ") validate code, validate code type : " + type);
      try {
        validateCodeProcessorHolder.findValidateCodeProcessor(type)
            .validate(new ServletWebRequest(request, response));
        log.info("verify validate code successfully");
      } catch (ValidateCodeException exception) {
        authenticationFailureHandler.onAuthenticationFailure(request, response, exception);
        return;
      }
    }
    chain.doFilter(request, response);
  }

  /**
   * get validate code from request , if needn't return null
   *
   * @param request
   * @return
   */
  private ValidateCodeType getValidateCodeType(HttpServletRequest request) {
    ValidateCodeType result = null;
    if (!StringUtils.equalsIgnoreCase(request.getMethod(), "get")) {
      Set<String> urls = urlMap.keySet();
      for (String url : urls) {
        if (pathMatcher.match(url, request.getRequestURI())) {
          result = urlMap.get(url);
        }
      }
    }
    return result;
  }
}
