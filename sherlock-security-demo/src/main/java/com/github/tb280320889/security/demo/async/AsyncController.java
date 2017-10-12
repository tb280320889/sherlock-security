package com.github.tb280320889.security.demo.async;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * Created by TangBin on 2017/10/12.
 */

@RestController
@RequestMapping("async")
@Slf4j
public class AsyncController {

  private final MockQueue mockQueue;

  private final DeferredResultHolder deferredResultHolder;

  @Autowired
  public AsyncController(MockQueue mockQueue, DeferredResultHolder deferredResultHolder) {
    this.mockQueue = mockQueue;
    this.deferredResultHolder = deferredResultHolder;
  }

  /**
   * @return
   * @throws InterruptedException
   */
  @GetMapping("order")
  public DeferredResult<String> order() throws InterruptedException {
    log.info("main thread start");

    final String orderNumber = RandomStringUtils.randomNumeric(8);
    mockQueue.setPlaceOrder(orderNumber);

    final DeferredResult<String> result = new DeferredResult<>();
    deferredResultHolder.getMap().put(orderNumber, result);


//    final Callable<String> result = () -> {
//      log.info("secondary thread start");
//      Thread.sleep(1000L);
//      log.info("secondary thread return");
//      return "success";
//    };

    log.info("main thread return");
    return result;
  }

}
