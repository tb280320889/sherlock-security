package com.github.tb280320889.security.demo.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.tb280320889.security.demo.validator.MyConstraint;

import java.sql.Timestamp;

import javax.validation.constraints.Past;

import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by TangBin on 2017/10/11.
 */

@Setter
@EqualsAndHashCode
@ToString
public class User {

  @MyConstraint(message = "this is a test")
  private String username;
  private String password;
  private Integer id;

  @Past(message = "birthday must be in the past")
  private Timestamp birthday;

  @JsonView(UserSimpleView.class)
  public String getUsername() {
    return username;
  }

  @JsonView(UserDetailView.class)
  @NotBlank(message = "password must not be blank")
  public String getPassword() {
    return password;
  }

  public Integer getId() {
    return id;
  }

  public Timestamp getBirthday() {
    return birthday;
  }

  public interface UserSimpleView {
  }

  public interface UserDetailView extends UserSimpleView {
  }
}
