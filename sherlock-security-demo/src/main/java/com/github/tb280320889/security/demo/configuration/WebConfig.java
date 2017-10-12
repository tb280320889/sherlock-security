package com.github.tb280320889.security.demo.configuration;

import com.github.tb280320889.security.demo.filter.TimeFilter;
import com.github.tb280320889.security.demo.interceptor.TimeInterceptor;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by TangBin on 2017/10/12.
 */

//@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

  private final TimeInterceptor timeInterceptor;

  @Autowired
  public WebConfig(TimeInterceptor timeInterceptor) {
    this.timeInterceptor = timeInterceptor;
  }

  /**
   * @return
   */
//  @Bean
  public FilterRegistrationBean timeFilter() {
    final FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();

    final TimeFilter timeFilter = new TimeFilter();
    filterRegistrationBean.setFilter(timeFilter);
    final ArrayList<String> urls = new ArrayList<>(5);

    urls.add("/*");
    filterRegistrationBean.setUrlPatterns(urls);
    return filterRegistrationBean;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(timeInterceptor);
  }

//
//  @Override
//  public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
//    configurer.setTaskExecutor()
//  }
}
