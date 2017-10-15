package com.github.tb280320889.security.browser.service;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by TangBin on 2017/10/13.
 */

@Slf4j
@Component
public class MyUserDetailsService implements UserDetailsService {

  private final PasswordEncoder passwordEncoder;

  @Autowired
  public MyUserDetailsService(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    final boolean enabled = true;
    final boolean accountNonExpired = true;
    final boolean credentialsNonExpired = true;
    final boolean accountNonLocked = true;
    final User user = new User("sherlock", passwordEncoder.encode("123456"), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));

    return user;
  }

}
