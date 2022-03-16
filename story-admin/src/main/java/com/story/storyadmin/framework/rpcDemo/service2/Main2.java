package com.story.storyadmin.framework.rpcDemo.service2;

import com.story.storyadmin.framework.rpcDemo.service.IProductService;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: lipan
 * @date: 2021年09月27日 10:19 下午
 * @description: 商品服务
 */
public class Main2 {

    /**
     *
     * 启动商品服务后，通过订单系统发起对商品服务的调用。
     *
     * 从这里，可以清晰的看到，商品服务读取了订单系统调用商品系统时发送的数据，利用反射机制，进行方法调用，并把调用结果写入Socket输出流。
     * @param args
     */
    public static void main(String[] args) {
        try{
            ServerSocket serverSocket = new ServerSocket(8888);
            while (true) {
                Socket socket = serverSocket.accept();
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

                // 读取网络协议
                String apiClassName = objectInputStream.readUTF();
                String methodName = objectInputStream.readUTF();
                Class[] parameterTypes = (Class[]) objectInputStream.readObject();
                Object[] args4Method = (Object[]) objectInputStream.readObject();

                Class clazz = null;
                // 服务注册： API到具体实现的映射关系
                if (apiClassName.equals(IProductService.class.getName())) {
                    clazz = ProductService.class;
                }

                Method method = clazz.getMethod(methodName, parameterTypes);
                Object invoke = method.invoke(clazz.newInstance(), args4Method);

                ObjectOutputStream objectOutputStream =new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.writeObject(invoke);
                objectOutputStream.flush();

                objectInputStream.close();
                objectOutputStream.close();

                socket.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
