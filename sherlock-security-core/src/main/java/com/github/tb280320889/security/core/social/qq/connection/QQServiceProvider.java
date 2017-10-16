package com.github.tb280320889.security.core.social.qq.connection;

import com.github.tb280320889.security.core.social.qq.api.QQ;
import com.github.tb280320889.security.core.social.qq.api.QQImpl;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * Created by TangBin on 2017/10/16.
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

  private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";
  private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";
  private String appId;


  public QQServiceProvider(String appId, String appSecret) {
    super(new OAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
    this.appId = appId;
  }

  @Override
  public QQ getApi(String accessToken) {
    return new QQImpl(accessToken, appId);
  }
}
