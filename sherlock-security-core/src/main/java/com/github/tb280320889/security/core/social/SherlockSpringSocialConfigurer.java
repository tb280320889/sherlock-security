package com.github.tb280320889.security.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * Created by TangBin on 2017/10/16.
 */

public class SherlockSpringSocialConfigurer extends SpringSocialConfigurer {

  private String filterProcessesUrl;

  public SherlockSpringSocialConfigurer(String filterProcessesUrl) {
    this.filterProcessesUrl = filterProcessesUrl;
  }

  @SuppressWarnings("unchecked")
  @Override
  protected <T> T postProcess(T object) {
    final SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
    filter.setFilterProcessesUrl(filterProcessesUrl);
    return (T) filter;
  }
}
