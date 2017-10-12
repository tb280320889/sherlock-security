package com.github.tb280320889.security.demo.async;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by TangBin on 2017/10/12.
 */

@Component
@Slf4j
public class QueueListener implements ApplicationListener<ContextRefreshedEvent> {

  private final MockQueue mockQueue;

  private final DeferredResultHolder deferredResultHolder;

  @Autowired
  public QueueListener(DeferredResultHolder deferredResultHolder, MockQueue mockQueue) {
    this.deferredResultHolder = deferredResultHolder;
    this.mockQueue = mockQueue;
  }

  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

    new Thread(() -> {
      while (true) {
        if (StringUtils.isNoneBlank(mockQueue.getCompleteOrder())) {
          final String orderNumber = mockQueue.getCompleteOrder();
          log.info("return result from order : {} ", orderNumber);
          deferredResultHolder.getMap().get(orderNumber).setResult("place order success");

          mockQueue.setCompleteOrder(null);
        } else {
          try {
            Thread.sleep(1000L);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }
    ).start();


  }
}
