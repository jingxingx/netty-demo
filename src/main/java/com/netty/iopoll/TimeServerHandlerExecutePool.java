package com.netty.iopoll;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author jingxingxin
 * @Title: netty-demo
 * @Package com.netty.iopoll
 * @Description:
 * @date 2016/6/23 16:24
 */
public class TimeServerHandlerExecutePool {

    private ExecutorService executor ;

    public TimeServerHandlerExecutePool(int maxPoolSize, int queueSize) {
        executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),maxPoolSize,120L,
                TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(queueSize));
    }

    public void executor(Runnable task) {
        executor.execute(task);
    }
}
