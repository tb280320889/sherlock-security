package com.github.tb280320889.security.core.property;

import lombok.Data;

import org.springframework.stereotype.Component;

/**
 * Created by TangBin on 2017/10/13.
 */


@Data
@Component
public class ImageCodeProperties {

  private int width = 67;
  private int height = 23;
  private int length = 4;
  private int expireIn = 60;
  private String url;

}
