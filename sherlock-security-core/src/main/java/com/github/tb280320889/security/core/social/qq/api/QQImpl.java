package com.github.tb280320889.security.core.social.qq.api;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

/**
 * Created by TangBin on 2017/10/16.
 */

@Slf4j
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

  private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";
  private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

  private String appId;
  private String openId;
  private ObjectMapper objectMapper = new ObjectMapper();

  public QQImpl(String accessToken, String appId) {
    super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
    this.appId = appId;

    final String url = String.format(URL_GET_OPENID, accessToken);
    final String result = getRestTemplate().getForObject(url, String.class);

    log.info("QQImpl result : {} ", result);

    this.openId = StringUtils.substringBetween(result, "\"openid\":\"", "\"}");
  }

  @Override
  public QQUserInfo getQQUserInfo() {
    final String url = String.format(URL_GET_USERINFO, appId, openId);
    String result = getRestTemplate().getForObject(url, String.class);

    log.info("getQQUserInfo result : {} ", result);

    try {
      final QQUserInfo qqUserInfo = objectMapper.readValue(result, QQUserInfo.class);
      qqUserInfo.setOpenId(openId);
      return qqUserInfo;
    } catch (IOException e) {
      throw new RuntimeException("fail to get user info", e);
    }

  }

}
