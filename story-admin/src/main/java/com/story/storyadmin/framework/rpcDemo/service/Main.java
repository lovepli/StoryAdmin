package com.story.storyadmin.framework.rpcDemo.service;

import com.story.storyadmin.framework.rpcDemo.bean.Product;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

/**
 * @author: lipan
 * @date: 2021年09月27日 9:56 下午
 * @description: 订单系统调用商品服务
 *
 * 实现一个迷你版的RPC ：https://www.jianshu.com/p/29d75a25eeaf
 */
public class Main {

    /**
     * 在订单系统工程中需要引入商品服务API依赖。最重要的就是rpc方法了
     *
     * @param args
     */
    public static void main(String[] args) {
        IProductService productService = (IProductService)rpc(IProductService.class);
        Product product = productService.queryById(100);
        System.out.println(product);


    }


    /**
     * rpc实现方法
     * @param clazz
     * @return
     */
    public static Object rpc(final Class clazz){
        return Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class[]{clazz}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Socket socket = new Socket("127.0.0.1",8888);

                        // 我们想远程调用哪个方法，并传递给这个方法什么参数
                        // 注意我们只知道Product API ,并不知道Product API在Product 的实现
                        String apiClassName = clazz.getName();
                        String methodName = method.getName();
                        Class[] parameterTypes = method.getParameterTypes();

                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                        objectOutputStream.writeUTF(apiClassName);
                        objectOutputStream.writeUTF(methodName);
                        objectOutputStream.writeObject(parameterTypes);
                        objectOutputStream.writeObject(args);
                        objectOutputStream.flush();

                        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                        Object o = objectInputStream.readObject();

                        objectInputStream.close();
                        objectOutputStream.close();

                        socket.close();

                        return o;
                    }
                }

        );

    }

    /**
     * 第一，我们看到了Proxy.newProxyInstance，很显然在进行动态代理。也即是说，在订单服务调用商品服务的代码中，我们先是通过动态代理返回一个代理的IProductService类型对象，这意味着当代理对象调用queryById方法的时候，会自动调用invoke方法！
     *
     * 第二，我们看看invoke到底做了些什么？
     *
     * 它本质上就是进行Socket通信，那么它需要传递什么信息给到商品服务呢？
     *
     * 我们知道订单系统就是想调用商品服务的某个类的某个方法，然后把这个方法的返回结果传输给订单系统！
     *
     * 想一想，如何调用某个类的某个方法呢？
     *
     * 只要我们能确定这个类的全限定类名、确定方法名、确定方法的参数类型，给定方法需要的具体参数，通过反射就能实现。
     *
     * 商品服务调用后得到的结果，我们序列化写入Socket流中，在订单系统中反序列化得到对象即可。
     *
     * 第三，这里需要思考一个问题：在订单系统中我们只知道商品服务的API，并不知道这背后的API到底是如何实现的，所以我们需要有一个映射，就是商品服务的API到商品服务的实现的一个映射关系，其实这就是所谓的服务的注册！
     */


}
