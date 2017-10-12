package com.github.tb280320889.security.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by TangBin on 2017/10/12.
 */

@Slf4j
//@Component
public class TimeInterceptor implements HandlerInterceptor {
  @Override
  public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

    log.info("pre handler");
    final HandlerMethod handlerMethod = (HandlerMethod) o;
    log.info("handler class name : {} ", handlerMethod.getBean().getClass().getName());
    log.info("handler method name : {} ", handlerMethod.getMethod().getName());

    httpServletRequest.setAttribute("startTime", System.currentTimeMillis());
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    log.info("post handler");
    final Long startTime = (Long) (httpServletRequest.getAttribute("startTime"));
    final long end = System.currentTimeMillis();
    log.info("post handler used : {} ms ", end - startTime);

  }

  @Override
  public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    log.info("after handler completion");
    final Long startTime = (Long) (httpServletRequest.getAttribute("startTime"));
    final long end = System.currentTimeMillis();
    log.info("time interceptor used : {} ms ", end - startTime);
    log.info("exception is {} ", e);
  }
}
