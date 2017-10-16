package com.github.tb280320889.security.core.property;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.stereotype.Component;

/**
 * Created by TangBin on 2017/10/13.
 */


@EqualsAndHashCode(callSuper = true)
@Data
@Component
public class ImageCodeProperties extends SmsCodeProperties {

  private int width = 67;
  private int height = 23;

  public ImageCodeProperties() {
    setLength(4);
  }

}
