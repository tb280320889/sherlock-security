package com.github.tb280320889.security.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by TangBin on 2017/10/10.
 */

@SpringBootApplication
@RestController
public class DemoApplication {


  private static final String HELLO = "hello";

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @GetMapping(HELLO)
  public String hello() {
    return "Hello";
  }
}