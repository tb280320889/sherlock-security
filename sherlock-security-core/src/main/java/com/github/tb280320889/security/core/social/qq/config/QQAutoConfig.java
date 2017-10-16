package com.github.tb280320889.security.core.social.qq.config;

import com.github.tb280320889.security.core.property.QQProperties;
import com.github.tb280320889.security.core.property.SecurityProperties;
import com.github.tb280320889.security.core.social.qq.connection.QQConnectionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * Created by TangBin on 2017/10/16.
 */

@Configuration
@ConditionalOnProperty(prefix = "sherlock.security.social-properties.qq", name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

  private final SecurityProperties securityProperties;

  @Autowired
  public QQAutoConfig(SecurityProperties securityProperties) {
    this.securityProperties = securityProperties;
  }

  @Override
  protected ConnectionFactory<?> createConnectionFactory() {

    final QQProperties qqConfig = securityProperties.getSocialProperties().getQqProperties();
    return new QQConnectionFactory(qqConfig.getProviderId(), qqConfig.getAppId(), qqConfig.getAppSecret());
  }
}
