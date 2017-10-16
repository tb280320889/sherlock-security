package com.github.tb280320889.security.core.validation.sms;

/**
 * Created by TangBin on 2017/10/16.
 */

public interface SmsCodeSender {
  void send(String mobileNumber, String code);
}
