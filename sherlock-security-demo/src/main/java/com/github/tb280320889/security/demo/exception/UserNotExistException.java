package com.github.tb280320889.security.demo.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by TangBin on 2017/10/12.
 */
@Getter
@Setter
public class UserNotExistException extends RuntimeException {

  private static final long serialVersionUID = -1510117753981399803L;

  private String id;

  public UserNotExistException(String id) {
    super("user not exist");
    this.id = id;
  }
}
