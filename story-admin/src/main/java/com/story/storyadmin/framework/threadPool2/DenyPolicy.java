package com.story.storyadmin.framework.threadPool2;



/**
 * @Author Nxy
 * @Date 2020/3/14 14:26
 * @Description 拒绝策略
 */
public interface DenyPolicy {

    public void reject(Runnable task, ThreadPool pool);

    /**
     * @Author Nxy
     * @Date 2020/3/14 14:27
     * @Description 任务队列溢出异常
     */
    class OutOfRunnableQueueException extends RuntimeException {
        OutOfRunnableQueueException(String msg) {
            super(msg);
        }
    }

    /**
     * @Author Nxy
     * @Date 2020/3/14 14:30
     * @Description 直接丢弃任务
     */
    class DiscardDenyPolicy implements DenyPolicy {
        @Override
        public void reject(Runnable task, ThreadPool pool) {
            //do nothing
        }
    }

    /**
     * @Author Nxy
     * @Date 2020/3/14 14:42
     * @Description 抛出 任务队列溢出异常
     */
    class throwExceptionDenyPolicy implements DenyPolicy {
        @Override
        public void reject(Runnable task, ThreadPool pool) {
            throw new OutOfRunnableQueueException("task queue is full!");
        }
    }

    /**
     * @Author Nxy
     * @Date 2020/3/14 14:42
     * @Description 由提交线程直接执行任务
     */
    class RunnerDenyPolicy implements DenyPolicy {
        @Override
        public void reject(Runnable task, ThreadPool pool) {
            task.run();
        }
    }
}