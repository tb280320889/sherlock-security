package com.github.tb280320889.security.core.validation;

import com.github.tb280320889.security.core.property.SecurityConstants;

/**
 * Created by TangBin on 2017/10/16.
 */

public enum ValidateCodeType {

  /**
   * sms verification code
   */
  SMS {
    @Override
    public String getParamNameOnValidate() {
      return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_SMS;
    }
  },
  /**
   * image code
   */
  IMAGE {
    @Override
    public String getParamNameOnValidate() {
      return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE;
    }
  };

  /**
   * para name from request
   *
   * @return
   */
  public abstract String getParamNameOnValidate();
}
