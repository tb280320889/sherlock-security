package com.github.tb280320889.security.demo.domain;

import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by TangBin on 2017/10/11.
 */

@Setter
@EqualsAndHashCode
@ToString
public class User {

  private String username;
  private String password;

  @JsonView(UserSimpleView.class)
  public String getUsername() {
    return username;
  }

  @JsonView(UserDetailView.class)
  public String getPassword() {
    return password;
  }


  public interface UserSimpleView {
  }

  public interface UserDetailView extends UserSimpleView {
  }
}
