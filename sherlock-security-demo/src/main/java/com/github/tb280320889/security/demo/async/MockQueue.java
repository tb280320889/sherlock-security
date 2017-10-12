package com.github.tb280320889.security.demo.async;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

/**
 * Created by TangBin on 2017/10/12.
 */

@Data
@Component
@Slf4j
public class MockQueue {

  private String placeOrder;

  private String completeOrder;

  public void setPlaceOrder(String placeOrder) throws InterruptedException {
    log.info("order received");
    Thread.sleep(3000L);
    this.completeOrder = placeOrder;
    log.info("order finished");
  }

}
