package com.github.tb280320889.security.core.property;

import lombok.Getter;
import lombok.Setter;

import org.springframework.stereotype.Component;

/**
 * Created by TangBin on 2017/10/16.
 */

@Getter
@Setter
@Component
public class SocialProperties {
  private QQProperties qqProperties = new QQProperties();
  private String filterProcessUrl = "/auth";
}
