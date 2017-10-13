package com.github.tb280320889.security.core.property;

import lombok.Getter;
import lombok.Setter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by TangBin on 2017/10/13.
 */


@Getter
@Setter
@ConfigurationProperties(prefix = "sherlock.security")
@Component
public class SecurityProperties {

  private BrowserProperties browserProperties = new BrowserProperties();

  private ValidateCodeProperties validateCodeProperties = new ValidateCodeProperties();

}
