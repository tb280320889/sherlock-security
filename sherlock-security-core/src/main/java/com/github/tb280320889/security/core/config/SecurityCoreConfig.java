package com.github.tb280320889.security.core.config;

import com.github.tb280320889.security.core.property.SecurityProperties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by TangBin on 2017/10/13.
 */

@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {


}
