package com.github.tb280320889.security.demo.controller;

import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

/**
 * Created by TangBin on 2017/10/11.
 */

@Data
public class UserQueryCondition {

  private String username;

  @ApiModelProperty("age start number")
  private Integer age;

  @ApiModelProperty("age end number")
  private Integer ageTo;
  private String xxx;

}
