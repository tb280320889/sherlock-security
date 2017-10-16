package com.github.tb280320889.security.core.social.qq.connection;

import com.github.tb280320889.security.core.social.qq.api.QQ;
import com.github.tb280320889.security.core.social.qq.api.QQUserInfo;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * Created by TangBin on 2017/10/16.
 */

public class QQAdapter implements ApiAdapter<QQ> {
  @Override
  public boolean test(QQ api) {
    return true;
  }

  @Override
  public void setConnectionValues(QQ api, ConnectionValues connectionValues) {
    final QQUserInfo qqUserInfo = api.getQQUserInfo();
    connectionValues.setDisplayName(qqUserInfo.getNickname());
    connectionValues.setImageUrl(qqUserInfo.getFigureurl_qq_1());
    connectionValues.setProfileUrl(null);
    connectionValues.setProviderUserId(qqUserInfo.getOpenId());

  }

  @Override
  public UserProfile fetchUserProfile(QQ api) {
    return null;
  }

  @Override
  public void updateStatus(QQ api, String s) {
    //do nothing
  }
}
