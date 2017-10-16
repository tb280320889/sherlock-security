package com.github.tb280320889.security.core.validation.image;

import com.github.tb280320889.security.core.validation.ValidateCode;
import com.github.tb280320889.security.core.validation.impl.AbstractValidateCodeProcessor;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created by TangBin on 2017/10/16.
 */
@Component("imageValidateCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateCodeProcessor {
  @Override
  protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {

  }
}
