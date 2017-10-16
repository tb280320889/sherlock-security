package com.github.tb280320889.security.core.validation.image;

import com.github.tb280320889.security.core.validation.ValidateCode;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by TangBin on 2017/10/13.
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class ImageCode extends ValidateCode {
  private BufferedImage image;


  /**
   * @param image
   * @param code
   * @param expireTime
   */
  public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
    super(code, expireTime);
    this.image = image;
  }

  /**
   * @param image
   * @param code
   * @param expireIn
   */
  public ImageCode(BufferedImage image, String code, Integer expireIn) {
    super(code, expireIn);
    this.image = image;
  }

}
