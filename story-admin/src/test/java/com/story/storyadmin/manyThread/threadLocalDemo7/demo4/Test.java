package com.story.storyadmin.manyThread.threadLocalDemo7.demo4;

import com.story.storyadmin.config.threadpool.VisiableThreadPoolTaskExecutor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author: 59688
 * @date: 2021/7/30
 * @description:
 *  *     在线程少的情况下是没有问题的，我们在每个线程里调用date方法，也就是在每个线程里都执行了创建SimpleDateFormat对象，每个对象在各自的线程里面执行格式化时间
 *  *     <p>但是我们是否会思考到，假如有1000个线程需要格式化时间，那么需要调用1000次date方法，也就是需要创建1000个作用一样的SimpleDateFormat对象，这样是不是太浪费内存了？也给GC带来压力？
 *  *     <p>于是我们联想到，1000个线程来共享一个SimpleDateFormat对象，这样SimpleDateFormat对象只需要创建一次即可，代码如下：
 */
@ExtendWith(SpringExtension.class) //导入spring测试框架[2]
@SpringBootTest  //提供spring依赖注入
@Transactional  //事务管理，默认回滚,如果配置了多数据源记得指定事务管理器
@DisplayName("单元测试")
public class Test {

    @Autowired(required=true)
    private VisiableThreadPoolTaskExecutor threadPoolTaskExecutor;

    @org.junit.jupiter.api.Test
    @DisplayName("多线程共享数据")
    public void test() throws ExecutionException, InterruptedException {
        System.out.println("-----线程-----" + Thread.currentThread().getName());

        // 创建多个有返回值的任务
        List<Future> list = new ArrayList<Future>();
        // 开启五个线程
        for (int i=0;i<10;i++){
            int finalI =i;
            Future<String> future = threadPoolTaskExecutor.submit(new DateFormatDemoRunnable(new DateFormatDemo(finalI)));
            list.add(future);
        }

        // 获取所有并发任务的运行结果
        for (Future f : list) {
            // 从 Future 对象上获取任务的返回值，并输出到控制台
            System.out.println(">>>" + f.get().toString());
        }


    }
}
