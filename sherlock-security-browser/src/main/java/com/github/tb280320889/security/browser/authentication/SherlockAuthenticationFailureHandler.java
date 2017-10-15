package com.github.tb280320889.security.browser.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tb280320889.security.browser.support.SimpleResponse;
import com.github.tb280320889.security.core.property.LoginResponseType;
import com.github.tb280320889.security.core.property.SecurityProperties;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;


/**
 * Created by TangBin on 2017/10/14.
 */

@Component
public class SherlockAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

  private final ObjectMapper objectMapper;
  private final SecurityProperties securityProperties;
  private Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  public SherlockAuthenticationFailureHandler(ObjectMapper objectMapper, SecurityProperties securityProperties) {
    this.objectMapper = objectMapper;
    this.securityProperties = securityProperties;
  }


  /* (non-Javadoc)
   * @see org.springframework.security.web.authentication.AuthenticationFailureHandler#onAuthenticationFailure(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.AuthenticationException)
   */
  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                      AuthenticationException exception) throws IOException, ServletException {

    logger.info("登录失败");

    if (LoginResponseType.JSON.equals(securityProperties.getBrowserProperties().getLoginType())) {
      response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
      response.setContentType("application/json;charset=UTF-8");
      response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(exception.getMessage())));
    } else {
      super.onAuthenticationFailure(request, response, exception);
    }


  }

}
