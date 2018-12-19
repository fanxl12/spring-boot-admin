package com.fanxl.admin.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @description 模拟队列
 * @author: fanxl
 * @date: 2018/12/19 0019 11:42
 */
@Slf4j
@Component
public class MockQueue {

    private String placeOrder;

    private String completeOrder;

    public String getPlaceOrder() {
        return placeOrder;
    }

    public void setPlaceOrder(String placeOrder) {
        new Thread(() -> {
            log.info("接到下单请求, {}", placeOrder);
            try {
                TimeUnit.SECONDS.sleep(1);
            }catch (Exception e) {
                e.printStackTrace();
            }
            log.info("下单请求处理完毕, {}", placeOrder);
            this.completeOrder = placeOrder;
        }).start();
    }

    public String getCompleteOrder() {
        return completeOrder;
    }

    public void setCompleteOrder(String completeOrder) {
        this.completeOrder = completeOrder;
    }
}
