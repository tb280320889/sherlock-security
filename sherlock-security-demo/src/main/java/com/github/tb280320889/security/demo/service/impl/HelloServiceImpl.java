package com.github.tb280320889.security.demo.service.impl;

import lombok.extern.slf4j.Slf4j;

import com.github.tb280320889.security.demo.service.HelloService;

import org.springframework.stereotype.Service;

/**
 * Created by TangBin on 2017/10/11.
 */

@Service
@Slf4j
public class HelloServiceImpl implements HelloService {

  @Override
  public void greeting(String message) {
    log.info("hello");
  }
}
