package com.github.tb280320889.security.browser.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tb280320889.security.core.property.LoginResponseType;
import com.github.tb280320889.security.core.property.SecurityProperties;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


/**
 * Created by TangBin on 2017/10/14.
 */

@Component
@Slf4j
public class SherlockAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

  private final ObjectMapper objectMapper;
  private final SecurityProperties securityProperties;

  @Autowired
  public SherlockAuthenticationSuccessHandler(ObjectMapper objectMapper, SecurityProperties securityProperties) {
    this.objectMapper = objectMapper;
    this.securityProperties = securityProperties;
  }

  /*
   * (non-Javadoc)
   *
   * @see org.springframework.security.web.authentication.
   * AuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.
   * HttpServletRequest, javax.servlet.http.HttpServletResponse,
   * org.springframework.security.core.Authentication)
   */
  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                      Authentication authentication) throws IOException, ServletException {

    log.info("登录成功");

    if (LoginResponseType.JSON == securityProperties.getBrowserProperties().getLoginType()) {
      response.setContentType("application/json;charset=UTF-8");
      response.getWriter().write(objectMapper.writeValueAsString(authentication));
    } else {
      super.onAuthenticationSuccess(request, response, authentication);
    }

  }

}
