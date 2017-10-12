package com.github.tb280320889.security.demo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by TangBin on 2017/10/12.
 */

@Slf4j
//@Component
public class TimeFilter implements Filter {
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    log.info("time filter init");
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    log.info("time filter start");
    final long start = System.currentTimeMillis();

    filterChain.doFilter(servletRequest, servletResponse);

    final long end = System.currentTimeMillis();
    log.info("time filter used : {} ms", end - start);
    log.info("time filter finished");

  }

  @Override
  public void destroy() {
    log.info("time filter destroyed");
  }
}
