package com.souche.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author guishangquan
 * @date 2018/6/5
 */
public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job> {
    private static final int MAX_THREAD_NUM = 10;
    private static final int DEFAULT_THREAD_NUM = 5;
    private static final int MIN_THREAD_NUM = 1;
    private final LinkedList<Job> jobs = new LinkedList<>();
    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());
    private int workerNum;
    private AtomicInteger id = new AtomicInteger(0);

    public DefaultThreadPool() {
        this.workerNum = DEFAULT_THREAD_NUM;
        initWorkers(this.workerNum);
    }

    public DefaultThreadPool(int workerNum) {
        if (workerNum > MAX_THREAD_NUM) {
            this.workerNum = MAX_THREAD_NUM;
        } else if (workerNum < MIN_THREAD_NUM) {
            this.workerNum = MIN_THREAD_NUM;
        } else {
            this.workerNum = workerNum;
        }
        initWorkers(this.workerNum);
    }

    private void initWorkers(int num) {
        for (int i = 0; i < num; ++i) {
            Worker worker = new Worker("default-thread-pool-" + id.incrementAndGet());
            worker.start();
            workers.add(worker);
        }
        workerNum += num;
    }

    @Override
    public void execute(Job job) {
        if (null != job) {
            synchronized (jobs) {
                jobs.add(job);
                jobs.notify();
            }
        }
    }

    @Override
    public void shutdown() {
        for (Worker worker : workers) {
            worker.shutdown();
        }
    }

    @Override
    public void addWorker(int num) {
        synchronized (jobs) {
            if (workerNum + num > MAX_THREAD_NUM) {
                throw new IllegalArgumentException("参数错误");
            }
            initWorkers(num);
        }

    }

    @Override
    public void removeWorker(int num) {
        synchronized (jobs) {
            if (workerNum - num < MIN_THREAD_NUM) {
                throw new IllegalArgumentException("参数错误");
            }

            for (int i = 0; i < num;) {
                Worker worker = workers.get(i);
                if (workers.remove(worker)) {
                    worker.shutdown();
                    ++ i;
                }
            }
            workerNum -= num;
        }
    }

    @Override
    public int getJobSize() {
        return jobs.size();
    }

    class Worker extends Thread {

        private volatile boolean isShutdown = false;

        Worker(String name) {
            super(name);
        }

        @Override
        public void run() {
            Job job;
            while (!isShutdown) {
                synchronized (jobs) {
                    while (jobs.size() <= 0) {
                        try {
                            jobs.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    job = jobs.removeFirst();
                }
                if (null != job) {
                    try {
                        job.run();
                    } catch (Exception e) {
                        // 忽略任务执行中的异常
                    }
                }
            }
        }

        public void shutdown() {
            isShutdown = true;
        }
    }
}
