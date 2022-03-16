package com.story.storyadmin.arithmetic.rateLimiter;

/**
 * @author: lipan
 * @date: 2021年10月05日 10:47 上午
 * @description: 对高并发流量控制的一点思考 https://www.jianshu.com/p/d9504fc0af4d
 */
public class Demo {

    /**
     * 分布式场景下的限流
     * 上面所说的限流的一些方式，都是针对单机而言的，其实大部分的场景，单机的限流已经足够了。分布式下限流的手段常常需要多种技术相结合，比如Nginx+Lua，Redis+Lua等去做。本文主要讨论的是单机的限流，这里就不在详细介绍分布式场景下的限流了。
     *
     * 一句话，让系统的流量，先到队列中排队、限流，不要让流量直接打到系统上。
     *
     * 作者：张丰哲
     * 链接：https://www.jianshu.com/p/d9504fc0af4d
     * 来源：简书
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
