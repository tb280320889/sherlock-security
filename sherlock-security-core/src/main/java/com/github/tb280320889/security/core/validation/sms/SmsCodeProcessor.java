package com.github.tb280320889.security.core.validation.sms;

import com.github.tb280320889.security.core.property.SecurityConstants;
import com.github.tb280320889.security.core.validation.ValidateCode;
import com.github.tb280320889.security.core.validation.impl.AbstractValidateCodeProcessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created by TangBin on 2017/10/16.
 */

@Component("smsValidateCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

  private final SmsCodeSender smsCodeSender;

  @Autowired
  public SmsCodeProcessor(SmsCodeSender smsCodeSender) {
    this.smsCodeSender = smsCodeSender;
  }

  /**
   * @param request
   * @param validateCode
   * @throws Exception
   */
  @Override
  protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
    final String paramName = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;
    final String mobileNumber = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), paramName);
    smsCodeSender.send(mobileNumber, validateCode.getCode());
  }
}
