package com.github.tb280320889.security.demo.controller;

import com.github.tb280320889.security.demo.exception.UserNotExistException;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * Created by TangBin on 2017/10/12.
 */

@ControllerAdvice
public class ControllerExceptionHandler {

  /**
   * @param e
   * @return
   */
  @ExceptionHandler(UserNotExistException.class)
  @ResponseBody
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public Map<String, Object> handleUserNotExistException(UserNotExistException e) {
    final HashMap<String, Object> result = new HashMap<>();
    result.put("id", e.getId());
    result.put("message", e.getMessage());

    return result;
  }

}
