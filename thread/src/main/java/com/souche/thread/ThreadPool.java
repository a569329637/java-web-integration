package com.souche.thread;

/**
 * @author guishangquan
 * @date 2018/6/5
 */
public interface ThreadPool<Job extends Runnable> {
    /**
     * 执行一个任务
      */
    void execute(Job job);
    /**
     * 关闭线程池
     */
    void shutdown();
    /**
     * 增加工作线程
     */
    void addWorker(int num);
    /**
     * 减少工作线程
     */
    void removeWorker(int num);
    /**
     * 工作线程数
     * @return
     */
    int getJobSize();
}
