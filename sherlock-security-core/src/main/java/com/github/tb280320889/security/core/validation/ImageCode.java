package com.github.tb280320889.security.core.validation;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * Created by TangBin on 2017/10/13.
 */

@Data
public class ImageCode {
  private BufferedImage image;
  private String code;
  private LocalDateTime expireTime;

  public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
    this.image = image;
    this.code = code;
    this.expireTime = expireTime;
  }

  /**
   * @param image
   * @param code
   * @param expireIn
   */
  public ImageCode(BufferedImage image, String code, Integer expireIn) {
    this.image = image;
    this.code = code;
    this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
  }

  public boolean isExpired() {
    return LocalDateTime.now().isAfter(expireTime);
  }
}
