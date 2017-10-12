package com.github.tb280320889.security.demo.aspect;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created by TangBin on 2017/10/12.
 */

@Aspect
@Component
@Slf4j
public class TimeAspect {

  /**
   * @param proceedingJoinPoint
   * @return
   */
  @Around("execution(* com.github.tb280320889.security.demo.controller.UserController.*(..))")
  public Object handleControllerMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    final long start = System.currentTimeMillis();
    log.info("time aspect start");

    final Object[] args = proceedingJoinPoint.getArgs();

    for (Object arg : args) {
      log.info("args : {} ", arg.toString());
    }

    log.info("time aspect finished");
    final long end = System.currentTimeMillis();
    log.info("time aspect time used : {} ms", start - end);
    return proceedingJoinPoint.proceed();
  }
}
