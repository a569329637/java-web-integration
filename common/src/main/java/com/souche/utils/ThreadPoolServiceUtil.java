package com.souche.utils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author guishangquan
 * @Description 线程池服务,提供多线程异步处理功能
 * @Package com.souche.utils
 * @date 17/4/27
 **/
public class ThreadPoolServiceUtil {
    /**
     * 主线程数
     */
    private int corePoolSize = 1;

    /**
     * 最大线程数
     */
    private int maximumPoolSize = 120;

    /**
     * 线程池维护线程所允许的空闲时间
     */
    private long keepAliveTime = 2000;

    /**
     * 单例的线程池类
     */
    private ThreadPoolExecutor threadPoolExecutor;

    /**
     * 线程池所使用的缓冲队列的大小
     */
    private int queueSize = 1000;
    private boolean inited = false;

    /**
     * 当线程池满时，是否阻塞住
     */
    private boolean blockWhenFull = true;

    public void init() {
        if(inited) {
            return;
        }
        this.threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(queueSize), new BlockingQueuePut());
        inited = true;
    }

    /**
     * 向线程池中添加任务
     *
     * @param task task 任务(必须实现Runnable接口)
     */
    public void addTask(Runnable task) {
        if(!inited) {
            init();
        }

        threadPoolExecutor.execute(task);
    }

    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public void setMaximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }

    public void setKeepAliveTime(long keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    public void setQueueSize(int queueSize) {
        this.queueSize = queueSize;
    }

    private class BlockingQueuePut implements RejectedExecutionHandler {
        /**
         * define the reject policy when executor queue is full
         * @see java.util.concurrent.RejectedExecutionHandler
         * #rejectedExecution(java.lang.Runnable, java.util.concurrent.ThreadPoolExecutor)
         */
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            if(blockWhenFull) {
                try {
                    executor.getQueue().put(r);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public void setBlockWhenFull(boolean blockWhenFull) {
        this.blockWhenFull = blockWhenFull;
    }

    /**
     * 获取线程池中正在执行的线程数目
     *
     * @return
     */
    public int getActiveCount() {
        return threadPoolExecutor.getActiveCount();
    }

    /**
     * 停止
     */
    public void stop() {
        threadPoolExecutor.shutdownNow();
    }
}
