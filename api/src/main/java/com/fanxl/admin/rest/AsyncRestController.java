package com.fanxl.admin.rest;

import com.fanxl.admin.utils.DeferredResultHolder;
import com.fanxl.admin.utils.MockQueue;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @description 异步调用是为了解决tomcat的容器线程占用问题，
 * 传统同步调用: 当一个请求过来了，tomcat会为这个请求分配一个线程，只有请求处理完毕，这个线程才会被释放掉，而tomcat的线程有限，当请求比较多的时候，
 * 势必会阻塞接口请求
 * 异步调用: 当请求过来之后，tomcat线程会重启另外一个副线程去处理请求逻辑，自己马上被释放掉，从而增加更多的处理能力
 * @author: fanxl
 * @date: 2018/12/19 0019 11:20
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/v1/async")
public class AsyncRestController {

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @GetMapping("sync")
    public String sync() throws Exception {
        log.info("主线程开始...");
        TimeUnit.SECONDS.sleep(2);
        log.info("主线程返回...");
        return "success";
    }

    /**
     * 下面形式是通过Runnable来实现，启动和返回都是在一个线程完成
     * 实际开发中，启动和返回可能是在不同线程中
     * @return
     */
    @GetMapping()
    public Callable<String> async() throws Exception {
        log.info("主线程开始...");

        Callable<String> callable = () -> {
            log.info("副线程开始...");
            TimeUnit.SECONDS.sleep(2);
            log.info("副线程结束...");
            return "success";
        };

        log.info("主线程返回...");
        return callable;
    }

    @GetMapping("order")
    public DeferredResult<String> asyncOrder()  {
        log.info("主线程开始...");

        String orderId = RandomStringUtils.randomNumeric(8);
        mockQueue.setPlaceOrder(orderId);

        DeferredResult<String> result = new DeferredResult<>();
        deferredResultHolder.getMap().put(orderId, result);

        log.info("主线程返回...");
        return result;
    }

    /**
     * Mono 返回一个元素
     * @return
     */
    @GetMapping("mono")
    public Mono<String> mono() {
        log.info("主线程开始...");
        Mono<String> result = Mono.fromSupplier(() -> doSomething());
        log.info("主线程返回...");
        return result;
    }

    /**
     * Flux 返回 0 - n个元素, 并且以流的形式一个个的返回
     * @return
     */
    @GetMapping(value = "flux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> flux() {
        log.info("主线程开始...");
        Flux<String> result = Flux.fromStream(IntStream.range(0, 8).mapToObj(i -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "flux data --" + i;
        }));
        log.info("主线程返回...");
        return result;
    }

    private String doSomething() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "i am ok";
    }


}
