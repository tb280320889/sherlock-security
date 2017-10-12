package com.github.tb280320889.security.demo.domain;

import lombok.Data;

/**
 * Created by TangBin on 2017/10/12.
 */

@Data
public class FileInfo {
  private String path;

  public FileInfo(String path) {
    this.path = path;
  }
}
