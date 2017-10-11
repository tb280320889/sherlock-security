package com.github.tb280320889.security.demo.domain;

import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonView;

import org.hibernate.validator.constraints.NotBlank;

import java.sql.Timestamp;

/**
 * Created by TangBin on 2017/10/11.
 */

@Setter
@EqualsAndHashCode
@ToString
public class User {

  private String username;
  private String password;
  private Integer id;
  private Timestamp birthday;

  @JsonView(UserSimpleView.class)
  public String getUsername() {
    return username;
  }

  @JsonView(UserDetailView.class)
  @NotBlank
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
