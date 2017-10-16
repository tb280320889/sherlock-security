package com.github.tb280320889.security.core.authentication.mobile;

import lombok.Setter;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by TangBin on 2017/10/16.
 */

@Setter
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

  private UserDetailsService userDetailsService;

  /*
   * (non-Javadoc)
   *
   * @see org.springframework.security.authentication.AuthenticationProvider#
   * authenticate(org.springframework.security.core.Authentication)
   */
  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {

    SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken) authentication;
    UserDetails user = userDetailsService.loadUserByUsername((String) authenticationToken.getPrincipal());
    if (user == null) {
      throw new InternalAuthenticationServiceException("can not load user information");
    }

    SmsCodeAuthenticationToken authenticationResult = new SmsCodeAuthenticationToken(user, user.getAuthorities());
    authenticationResult.setDetails(authenticationToken.getDetails());
    return authenticationResult;
  }

  /*
   * (non-Javadoc)
   *
   * @see org.springframework.security.authentication.AuthenticationProvider#
   * supports(java.lang.Class)
   */
  @Override
  public boolean supports(Class<?> authentication) {
    return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
  }

}
