package com.story.storyadmin.junit5.contiPerfRuleTestDemo18;

import com.story.storyadmin.domain.entity.sysmgr.User;
import com.story.storyadmin.service.sysmgr.UserService;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.concurrent.ExecutionException;

/**
 * @author: 59688
 * @date: 2021/8/5
 * @description: ContiPerfRule测试 https://blog.lqdev.cn/2018/07/26/springboot/chapter-thirteen/
 */
@RunWith(SpringRunner.class)
//SpringBootTest 是springboot 用于测试的注解，可指定启动类或者测试环境等，这里直接默认。
@SpringBootTest
public class Test1 {

    @Autowired
    private UserService userService;

    //引入 ContiPerf 进行性能测试
    // @Rule是Junit提供的一个扩展接口注解，其接口类为：org.junit.rules.MethodRule，注意在Junit5中，已经被TestRule所以替代了。
    //也可以通过对类指定 @PerfTest 和 @Required，表示类中方法的默认设置。
    @Rule
    public ContiPerfRule contiPerfRule = new ContiPerfRule();

    @Test
    //10个线程 执行10次
    @PerfTest(invocations = 100,threads = 10)
    public void test() throws ExecutionException, InterruptedException {
        String msg = "17671226463";
        User user = userService.selectUserById(1l);
        System.out.println("用户手机号码："+user.getPhone());
        //断言 是否和预期一致
        Assert.assertEquals(msg, user.getPhone());

        //控制台会有性能报告，同时访问：target/contiperf-report/index.html，会有图表提示。
    }

    /**
     * 注解参数说明
     * @PerfTest
     *
     * invocations：执行次数n，与线程数量无关，默认值为1
     * threads：线程池数量n，并发执行n个线程
     * duration：重复地执行时间n，测试至少执行n毫秒
     *
     * @Required注解：
     @Required(throughput = 20)：要求每秒至少执行20个测试；
     @Required(average = 50)：要求平均执行时间不超过50ms；
     @Required(median = 45)：要求所有执行的50%不超过45ms；
     @Required(max = 2000)：所有执行的执行时间没有超过2s；
     @Required(totalTime = 5000)：要求总的执行时间不超过5s；
     @Required(percentile90 = 3000)：要求90%的测试不超过3s；
     @Required(percentile95 = 5000)：要求95%的测试不超过5s；
     @Required(percentile99 = 10000)：要求99%的测试不超过10s;
     @Required(percentiles = “66:200,96:500”)：要求66%的测试不超过200ms，96%的测试不超过500ms。
     *
     * 图表中的指标：
     * Execution time: 执行时间
     * Throughput: TPS
     * Min. latency: 最小响应时间
     * Average latency: 平均响应时间
     * Median: 响应时间中位数
     * 90%: 90%响应时间范围
     * Max latency: 最大响应时间
     */





}
