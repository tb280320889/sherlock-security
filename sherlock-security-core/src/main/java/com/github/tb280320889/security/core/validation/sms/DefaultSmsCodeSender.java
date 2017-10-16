package com.github.tb280320889.security.core.validation.sms;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by TangBin on 2017/10/16.
 */

@Slf4j
public class DefaultSmsCodeSender implements SmsCodeSender {
  @Override
  public void send(String mobileNumber, String code) {
    log.warn("send sms code : {} to {} ", code, mobileNumber);
  }
}
