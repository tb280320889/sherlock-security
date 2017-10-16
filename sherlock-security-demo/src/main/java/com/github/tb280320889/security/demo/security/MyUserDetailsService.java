package com.github.tb280320889.security.demo.security;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

/**
 * Created by TangBin on 2017/10/13.
 */

@Slf4j
@Component
public class MyUserDetailsService implements UserDetailsService, SocialUserDetailsService {

  private final PasswordEncoder passwordEncoder;

  @Autowired
  public MyUserDetailsService(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    log.info("form login username : {} ", username);
    return buildUser(username);
  }

  private SocialUserDetails buildUser(String userId) {
    final boolean enabled = true;
    final boolean accountNonExpired = true;
    final boolean credentialsNonExpired = true;
    final boolean accountNonLocked = true;
    final String password = passwordEncoder.encode("123456");

    log.info("password : {} ", password);

    return new SocialUser(userId, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
  }

  @Override
  public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {

    log.info("social login userId : {} ", userId);
    return buildUser(userId);
  }
}
