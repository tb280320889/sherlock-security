package com.github.tb280320889.security.core.validation;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created by TangBin on 2017/10/13.
 */

@Component
public interface ValidateCodeGenerator {
  ValidateCode generateCode(ServletWebRequest servletWebRequest);
}
