package com.github.tb280320889.security.core.social;

import com.github.tb280320889.security.core.property.SecurityProperties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * Created by TangBin on 2017/10/16.
 */

@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {

  private final DataSource dataSource;
  private final SecurityProperties securityProperties;

  @Autowired
  public SocialConfig(@Qualifier("dataSource") DataSource dataSource, SecurityProperties securityProperties) {
    this.dataSource = dataSource;
    this.securityProperties = securityProperties;
  }

  @Override
  public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
    final JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
    repository.setTablePrefix("sherlock_");
    return repository;

  }


  @Bean
  public SpringSocialConfigurer sherlockSocialSecurityConfig() {
    final String filterProcessUrl = securityProperties.getSocialProperties().getFilterProcessUrl();
    final SherlockSpringSocialConfigurer springSocialConfigurer = new SherlockSpringSocialConfigurer(filterProcessUrl);

    return springSocialConfigurer;
  }

}
