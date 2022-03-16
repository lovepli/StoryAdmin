package com.story.storyadmin.framework.rpcDemo2;

import java.io.*;
import java.net.URL;
import java.util.*;

/**
 * @Author Nxy
 * @Date 2020/3/23 23:02
 * @Description 简单的远程注册中心
 * 注册内容写入文件，用于进程间共享
 */
public class BasicWebRegistry implements WebRegistry {
    private Map<String, List<URL>> registerMap = new HashMap<String, List<URL>>(1024);
    public static final String path = "C://tmp//rpc";
    private static BasicWebRegistry basicWebRegister;

    public static BasicWebRegistry getInstance() {
        if (basicWebRegister == null) {
            synchronized (BasicWebRegistry.class) {
                if (basicWebRegister == null) {
                    basicWebRegister = new BasicWebRegistry();
                }
            }
        }
        return basicWebRegister;
    }

    @Override
    public void register(String interfaceName, URL host) {
        if (registerMap.containsKey(interfaceName)) {
            List<URL> list = registerMap.get(interfaceName);
            list.add(host);
        } else {
            List<URL> list = new LinkedList<URL>();
            list.add(host);
            registerMap.put(interfaceName, list);
        }
        try {
            saveFile(path, registerMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public URL getRandomURL(String interfaceName) {
        int i = 0;
        //尝试 5 次读取注册文件
        try {
            while (i < 5) {
                return getRandomURLOnce(interfaceName);
            }
        } catch (IOException e) {
            e.printStackTrace();
            i++;
        }
        return null;
    }


    public URL getRandomURLOnce(String interfaceName) throws IOException {
        try {
            registerMap = (Map<String, List<URL>>) readFile(path);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        List<URL> list = registerMap.get(interfaceName);
        Random random = new Random();
        int i = random.nextInt(list.size());
        return list.get(i);
    }

    /**
     * 写入文件
     *
     * @param path
     * @param object
     * @throws IOException
     */
    private void saveFile(String path, Object object) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(object);
    }

    /**
     * 从文件中读取
     *
     * @param path
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private Object readFile(String path) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(new File(path));
        ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
        return inputStream.readObject();
    }

    private Object readResolve() {
        return getInstance();
    }
}