package com.github.tb280320889.security.core.validation;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * Created by TangBin on 2017/10/16.
 */

@Data
public class ValidateCode {
  private String code;
  private LocalDateTime expireTime;

  public ValidateCode(String code, LocalDateTime expireTime) {
    this.code = code;
    this.expireTime = expireTime;
  }

  public ValidateCode(String code, Integer expireIn) {
    this.code = code;
    this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
  }

  public boolean isExpired() {
    return LocalDateTime.now().isAfter(expireTime);
  }
}
