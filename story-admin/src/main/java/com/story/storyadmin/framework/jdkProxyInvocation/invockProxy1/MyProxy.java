package com.story.storyadmin.framework.jdkProxyInvocation.invockProxy1;

import org.springframework.util.FileCopyUtils;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author: 59688
 * @date: 2021/9/29
 * @description: 自定义MyProxy
 * MyProxy的作用就相当于JDK的Proxy。MyProxy做了哪些事情呢？
 *
 * 第一：需要根据interfaces接口构造出动态代理类需要的方法。（其实就是利用反射获取）
 *
 * 第二：把动态生成的代理类（即.java文件）进行编译，生成字节码文件（即.class文件），然后利用类加载进行加载
 *
 * 第三：动态代理类进行加载后，利用反射机制，通过构造方法进行实例化，并在实例化时，初始化业务Hanlder
 */
public class MyProxy {
    private static final String rt = "\r";

    public static Object newProxyInstance(MyClassLoader loader,Class<?> interfaces,MyInvocationHandler h) throws IllegalArgumentException{
        if (h == null){
            throw new NullPointerException();
        }
        // 根据接口构造代理类：$MyProxy0
        Method[] methods = interfaces.getMethods();
        StringBuffer proxyClassString = new StringBuffer();
        proxyClassString.append("package ")
                .append(loader.getProxyClassPackage()).append(";").append(rt)
                .append("import java.lang.reflect.Method;").append(rt)
                .append("public class $MyProxy0 implements ").append(interfaces.getName()).append("{").append(rt)
                .append("MyInvocationHandler h;").append(rt)
                .append("public $MyProxy0(MyInvocationHandler h){").append(rt).append("this.h = h;}").append(rt)
                .append(getMethodString(methods,interfaces)).append("}");

        // 写入Java文件，进行编译
        String fileName = loader.getDir()+ File.separator+"$MyProxy0.java";
        File myProxyFile = new File(fileName);
        try {
            compile(proxyClassString,myProxyFile);
            //利用自定义的classloader加载
            Class $myProxy0 = loader.findClass("$MyProxy0");
            //$MyProxy0初始化
            Constructor constructor = $myProxy0.getConstructor(MyInvocationHandler.class);
            Object o= constructor.newInstance(h);
            return o;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;

    }

    private static void compile(StringBuffer proxyClassString,File myProxyFile) throws IOException {
        FileCopyUtils.copy(proxyClassString.toString().getBytes(),myProxyFile);
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager standardJavaFileManager = javaCompiler.getStandardFileManager(null,null,null);
        Iterable javaFileObjects = standardJavaFileManager.getJavaFileObjects(myProxyFile);
        JavaCompiler.CompilationTask task = javaCompiler.getTask(null,standardJavaFileManager,null,null,null,javaFileObjects);
        task.call();
        standardJavaFileManager.close();

    }

    private static String getMethodString(Method[] methods,Class interfaces){
      StringBuffer methodStringBuffer = new StringBuffer();
      for (Method method:methods){
          methodStringBuffer.append("public void ").append(method.getName())
                  .append("()").append("throws Throwable{ ")
                  .append("Method method1 = ").append(interfaces.getName())
                  .append(".class.getMethod(\"").append(method.getName())
                  .append("\",new Class[] {});")
                  .append("this.h.invoke(this,method1,null);}").append(rt);
      }
        return methodStringBuffer.toString();
    }
}




