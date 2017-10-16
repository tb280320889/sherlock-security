package com.github.tb280320889.security.core.social.qq.connection;

import com.github.tb280320889.security.core.social.qq.api.QQ;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * Created by TangBin on 2017/10/16.
 */

public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {
  public QQConnectionFactory(String providerId, String appId, String appSecret) {
    super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
  }
}
