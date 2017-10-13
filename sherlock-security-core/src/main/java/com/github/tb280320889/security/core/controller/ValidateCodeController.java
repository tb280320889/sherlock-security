package com.github.tb280320889.security.core.controller;

import com.github.tb280320889.security.core.validation.ImageCode;
import com.github.tb280320889.security.core.validation.ValidateCodeGenerator;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created by TangBin on 2017/10/13.
 */

@RestController
@RequestMapping("/validate_code")
@Slf4j
public class ValidateCodeController {
  public static final String VALIDATE_CODE = "/validate_code";
  public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";
  private final ValidateCodeGenerator imageCodeGenerator;
  private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

  @Autowired
  public ValidateCodeController(ValidateCodeGenerator imageCodeGenerator) {
    this.imageCodeGenerator = imageCodeGenerator;
  }

  /**
   * @param httpServletRequest
   * @param httpServletResponse
   * @throws IOException
   */
  @GetMapping("/image_code")
  public void createCode(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {

    ImageCode imageCode = imageCodeGenerator.generateCode(new ServletWebRequest(httpServletRequest));

    sessionStrategy.setAttribute(new ServletWebRequest(httpServletRequest), SESSION_KEY, imageCode);

    ImageIO.write(imageCode.getImage(), "JPEG", httpServletResponse.getOutputStream());

  }


}
