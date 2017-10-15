package com.github.tb280320889.security.core.property;

import lombok.Data;

import org.springframework.stereotype.Component;

/**
 * Created by TangBin on 2017/10/13.
 */


@Data
@Component
public class BrowserProperties {

  private String loginPage = "/login/basic-login.html";

  private LoginResponseType loginType = LoginResponseType.JSON;

  private Integer rememberMeSeconds = 3600; // 1 hour

}
