package com.github.tb280320889.security.demo.async;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * Created by TangBin on 2017/10/12.
 */

@Component
public class DeferredResultHolder {
  private Map<String, DeferredResult> map = new HashMap<String, DeferredResult>(5);

  public Map<String, DeferredResult> getMap() {
    return map;
  }

  public void setMap(Map<String, DeferredResult> map) {
    this.map = map;
  }
}
