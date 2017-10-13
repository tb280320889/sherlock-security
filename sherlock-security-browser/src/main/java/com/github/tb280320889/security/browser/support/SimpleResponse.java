package com.github.tb280320889.security.browser.support;

import lombok.Data;

/**
 * Created by TangBin on 2017/10/13.
 */

@Data
public class SimpleResponse {

  private Object content;

  public SimpleResponse(Object content) {
    this.content = content;
  }
}
