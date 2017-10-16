package com.github.tb280320889.security.core.validation.impl;

import com.github.tb280320889.security.core.exception.ValidateCodeException;
import com.github.tb280320889.security.core.validation.ValidateCode;
import com.github.tb280320889.security.core.validation.ValidateCodeGenerator;
import com.github.tb280320889.security.core.validation.ValidateCodeProcessor;
import com.github.tb280320889.security.core.validation.ValidateCodeType;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created by TangBin on 2017/10/16.
 */

public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {

  /**
   * Util class to operate session
   */
  private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
  /**
   * collect all  {@link ValidateCodeGenerator} interface's implements
   */
  @Autowired
  private Map<String, ValidateCodeGenerator> validateCodeGenerators;

  /**
   * @param request
   * @throws Exception
   */
  @Override
  public void create(ServletWebRequest request) throws Exception {
    C validateCode = generate(request);
    save(request, validateCode);
    send(request, validateCode);
  }

  /**
   * generate validate code
   *
   * @param request
   * @return
   */
  @SuppressWarnings("unchecked")
  private C generate(ServletWebRequest request) {
    String type = getValidateCodeType(request).toString().toLowerCase();
    String generatorName = type + ' ' + ValidateCodeGenerator.class.getSimpleName();
    ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(generatorName);
    if (validateCodeGenerator == null) {
      throw new ValidateCodeException("validate generator " + generatorName + " does not exist");
    }
    return (C) validateCodeGenerator.generateCode(request);
  }

  /**
   * save validate code
   *
   * @param request
   * @param validateCode
   */
  private void save(ServletWebRequest request, C validateCode) {
    sessionStrategy.setAttribute(request, getSessionKey(request), validateCode);
  }

  /**
   * the session key while creating validate code
   *
   * @param request
   * @return
   */
  private String getSessionKey(ServletWebRequest request) {
    return SESSION_KEY_PREFIX + getValidateCodeType(request).toString().toUpperCase();
  }

  /**
   * send validate code
   *
   * @param request
   * @param validateCode
   * @throws Exception
   */
  protected abstract void send(ServletWebRequest request, C validateCode) throws Exception;

  /**
   * get validate code type from request url
   *
   * @param request
   * @return
   */
  private ValidateCodeType getValidateCodeType(ServletWebRequest request) {
    String type = StringUtils.substringBefore(getClass().getSimpleName(), "CodeProcessor");
    return ValidateCodeType.valueOf(type.toUpperCase());
  }

  @SuppressWarnings("unchecked")
  @Override
  public void validate(ServletWebRequest request) {

    ValidateCodeType processorType = getValidateCodeType(request);
    String sessionKey = getSessionKey(request);

    C codeInSession = (C) sessionStrategy.getAttribute(request, sessionKey);

    String codeInRequest;
    try {
      codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),
          processorType.getParamNameOnValidate());
    } catch (ServletRequestBindingException e) {
      throw new ValidateCodeException("fail to get validate code value");
    }

    if (StringUtils.isBlank(codeInRequest)) {
      throw new ValidateCodeException(processorType + " validate code must not be null");
    }

    if (codeInSession == null) {
      throw new ValidateCodeException(processorType + " validate code does not exist");
    }

    if (codeInSession.isExpired()) {
      sessionStrategy.removeAttribute(request, sessionKey);
      throw new ValidateCodeException(processorType + " validate code is expired");
    }

    if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
      throw new ValidateCodeException(processorType + " validate code does not match");
    }

    sessionStrategy.removeAttribute(request, sessionKey);
  }

}
