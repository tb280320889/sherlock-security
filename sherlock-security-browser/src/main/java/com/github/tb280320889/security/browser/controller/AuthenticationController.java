package com.github.tb280320889.security.browser.controller;

import com.github.tb280320889.security.browser.support.SimpleResponse;
import com.github.tb280320889.security.core.property.SecurityProperties;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static com.github.tb280320889.security.browser.controller.AuthenticationController.AUTHENTICATION;

/**
 * Created by TangBin on 2017/10/13.
 */

@RestController
@Slf4j
@RequestMapping(AUTHENTICATION)
public class AuthenticationController {

  public static final String AUTHENTICATION = "/authentication";
  private final SecurityProperties securityProperties;
  private RequestCache requestCache = new HttpSessionRequestCache();
  private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

  @Autowired
  public AuthenticationController(SecurityProperties securityProperties) {
    this.securityProperties = securityProperties;
  }


  /**
   * @param httpServletRequest
   * @param httpServletResponse
   * @return
   * @throws IOException
   */
  @RequestMapping("/require")
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public SimpleResponse requireAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {

    final SavedRequest savedRequest = requestCache.getRequest(httpServletRequest, httpServletResponse);


    if (savedRequest != null) {
      final String targetUrl = savedRequest.getRedirectUrl();
      log.info("the request call redirect : {} ", targetUrl);
      if (StringUtils.endsWithIgnoreCase(targetUrl, ".html")) {
        redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, securityProperties.getBrowserProperties().getLoginPage());
      }
    }


    return new SimpleResponse("service need authentication, please guide user to login page");

  }

}
