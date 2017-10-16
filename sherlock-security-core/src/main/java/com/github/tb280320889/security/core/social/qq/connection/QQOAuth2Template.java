package com.github.tb280320889.security.core.social.qq.connection;

import java.nio.charset.Charset;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * Created by TangBin on 2017/10/16.
 */

@Slf4j
public class QQOAuth2Template extends OAuth2Template {
  public QQOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
    super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
  }

  @Override
  protected RestTemplate createRestTemplate() {
    final RestTemplate restTemplate = super.createRestTemplate();
    restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
    return restTemplate;
  }

  @Override
  protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
    String responseStr = getRestTemplate().postForObject(accessTokenUrl, parameters, String.class);

    log.info("receive accessToken response : {} ", responseStr);

    final String[] items = StringUtils.splitByWholeSeparatorPreserveAllTokens(responseStr, "&");

    final String accessToken = StringUtils.substringAfterLast(items[0], "=");
    final Long expiresIn = Long.valueOf(StringUtils.substringAfterLast(items[1], "="));
    final String refreshToken = StringUtils.substringAfterLast(items[2], "=");
    return new AccessGrant(accessToken, null, refreshToken, expiresIn);

  }
}
