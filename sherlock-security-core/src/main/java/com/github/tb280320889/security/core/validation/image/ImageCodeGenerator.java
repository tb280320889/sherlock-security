package com.github.tb280320889.security.core.validation.image;

import com.github.tb280320889.security.core.property.ImageCodeProperties;
import com.github.tb280320889.security.core.property.SecurityProperties;
import com.github.tb280320889.security.core.validation.ValidateCodeGenerator;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.security.SecureRandom;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created by TangBin on 2017/10/13.
 */


public class ImageCodeGenerator implements ValidateCodeGenerator {

  private final SecurityProperties securityProperties;

  @Autowired
  public ImageCodeGenerator(SecurityProperties securityProperties) {
    this.securityProperties = securityProperties;
  }

  @Override
  public ImageCode generateCode(ServletWebRequest httpServletRequest) {

    final ImageCodeProperties imageCodeProperties = securityProperties.getValidateCodeProperties().getImageCodeProperties();

    int width = ServletRequestUtils.getIntParameter(httpServletRequest.getRequest(), "width", imageCodeProperties.getWidth());
    int height = ServletRequestUtils.getIntParameter(httpServletRequest.getRequest(), "height", imageCodeProperties.getHeight());

    final BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

    final Graphics graphics = image.getGraphics();

    final Random random = new SecureRandom();

    graphics.setColor(getRandColor(200, 250));
    graphics.fillRect(0, 0, width, height);
    graphics.setFont(new Font("Times new Roman", Font.ITALIC, 20));
    graphics.setColor(getRandColor(160, 200));
    for (int i = 0; i < imageCodeProperties.getLength(); i++) {
      final int x = random.nextInt(width);
      final int y = random.nextInt(height);
      final int xl = random.nextInt(12);
      final int yl = random.nextInt(12);
      graphics.drawLine(x, y, x + xl, y + yl);
    }
    String sRand = "";
    for (int i = 0; i < 4; i++) {
      final String rand = String.valueOf(random.nextInt(10));
      sRand += rand;
      graphics.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
      graphics.drawString(rand, 13 * i + 6, 16);
    }
    graphics.dispose();

    return new ImageCode(image, sRand, imageCodeProperties.getExpireIn());
  }

  private Color getRandColor(int fc, int bc) {
    final Random random = new SecureRandom();
    if (fc > 255) {
      fc = 255;
    }
    if (bc > 255) {
      bc = 255;
    }
    int r = fc + random.nextInt(bc - fc);
    int g = fc + random.nextInt(bc - fc);
    int b = fc + random.nextInt(bc - fc);
    return new Color(r, g, b);
  }

}
