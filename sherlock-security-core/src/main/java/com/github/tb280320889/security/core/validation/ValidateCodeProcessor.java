package com.github.tb280320889.security.core.validation;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created by TangBin on 2017/10/16.
 */

public interface ValidateCodeProcessor {


  /**
   * verification code's prefix in session
   */
  String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";


  /**
   * @param servletWebRequest
   * @throws Exception
   */
  void create(ServletWebRequest servletWebRequest) throws Exception;


  /**
   * @param servletWebRequest
   */
  void validate(ServletWebRequest servletWebRequest);

}
