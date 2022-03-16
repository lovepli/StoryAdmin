package com.story.storyadmin.manyThread.JUC.AQS.utils.CountDownLatchDemo;

/**
 * @author: lipan
 * @date: 2021年08月26日 9:52 上午
 * @description: CountDownLatch
 * 俗称倒计数器，允许一个或多个线程一直等待，直到一组在其他线程执行的操作全部完成。当一个线程调用await方法时，就会阻塞当前线程。每当有线程调用一次 countDown 方法时，计数就会减 1。当 count 的值等于 0 的时候，被阻塞的线程才会继续运行。
   如下代码，可用于当收到服务器响应，才返回zookeeper。
 */
public class CountDownLatchDemo {

    //@Bean(name = "zkClient")
    //public ZooKeeper zkClient(){
    //    ZooKeeper zooKeeper=null;
    //    try {
    //        final CountDownLatch countDownLatch = new CountDownLatch(1);
    //        //连接成功后，会回调watcher监听，此连接操作是异步的，执行完new语句后，直接调用后续代码
    //        //  可指定多台服务地址 127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183
    //        zooKeeper = new ZooKeeper(connectString, timeout, new Watcher() {
    //            @Override
    //            public void process(WatchedEvent event) {
    //                if(Event.KeeperState.SyncConnected==event.getState()){
    //                    //如果收到了服务端的响应事件,连接成功
    //                    countDownLatch.countDown();
    //                }
    //            }
    //        });
    //        countDownLatch.await();
    //        log.info("【初始化ZooKeeper连接状态....】={}",zooKeeper.getState());
    //
    //    }catch (Exception e){
    //        log.error("初始化ZooKeeper连接异常....】={}",e);
    //    }
    //    return  zooKeeper;
    //}

}
