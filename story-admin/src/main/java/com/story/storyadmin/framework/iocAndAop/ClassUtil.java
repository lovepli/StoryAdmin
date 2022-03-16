package com.story.storyadmin.framework.iocAndAop;


import lombok.extern.slf4j.Slf4j;
import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Classname ClassUtil
 * @Description
 * @Date 2020/8/8 15:20
 * @Created by zhangtianci
 * 4.工具包
 *
 * 作为框架 类加载/反射/校验等功能。
 */
@Slf4j
public class ClassUtil {

    public static final String FILE_PROTOCOL = "file";

    /**
     * 获取类加载器
     * 因为项目部署在tomcat等一些web容器中
     * 这些web容器加载部署在里面的apps 所以需要自定义classLoader去加载class文件
     * 当我们写框架需要去通过类加载器去加载class文件时 就需要通过Thread.currentThread().getContextClassLoader()
     * 拿到tomcat的自定义的classLoader去加载项目里面的class文件
     * 详情 参考我写的classLoader加载相关文章
     * @return
     */
    public static ClassLoader getClassLoader(){
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 通过包名加载class
     *
     * 点进去Class.forName()方法进去看看
     *
     * @CallerSensitive
     *     public static Class<?> forName(String className)
     *                 throws ClassNotFoundException {
     *         Class<?> caller = Reflection.getCallerClass();
     *         return forName0(className, true, ClassLoader.getClassLoader(caller), caller);
     *     }
     *
     *     获取到调用这个方法的class对象  然后用这个class对象的classLoader去加载这个class文件
     *     所以归根结底是 拿到tomcat的自定义的classLoader去加载的class文件
     *     所以没问题
     * @param className class全名=package + 类名
     * @return
     */
    public static Class<?> loadClass(String className){
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            log.error("loadClass failed!",e);
            throw new RuntimeException(e);
        }

    }


    /**
     * 通过一个class对象实例化一个实例对象(通过默认构造器)
     * @param clazz
     * @param accessible
     * @param <T>
     * @return
     */
    public static <T> T newInstance(Class<?> clazz, boolean accessible){
        try {
            //clazz.getDeclaredConstructor() 获取指定参数类表的构造器
            //这里获取默认构造器
            Constructor constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(accessible);
            return (T)constructor.newInstance();
        } catch (Exception e) {
            log.error("new instance failed!");
            throw new RuntimeException(e);
        }
    }

    /**
     * 设置类的属性值
     *
     * @param field      成员变量
     * @param target     类实例
     * @param value      成员变量的值
     * @param accessible 是否允许设置私有属性
     */
    public static void setField(Field field, Object target, Object value, boolean accessible){
        field.setAccessible(accessible);
        try {
            field.set(target, value);
        } catch (IllegalAccessException e) {
            log.error("setField error", e);
            throw new RuntimeException(e);
        }
    }


    /**
     * 加载包路径下的所有的class文件
     * 将class对象放进set集合中
     *
     * 1.获取classLoader加载器
     * 2.递归加载路径下的所有class文件
     * @param packageName
     * @return
     */
    public static Set<Class<?>> extractPackageClass(String packageName){

        //1.获取classLoader加载器
        ClassLoader classLoader = getClassLoader();
        //2.获取资源文件的的url
        URL url = classLoader.getResource(packageName.replace(".","/"));
        if (url == null){
            log.warn("unable to retrieve anything from package: " + packageName);
            return  null;
        }

        //3.依据不同的资源类型，采用不同的方式获取资源的集合
        Set<Class<?>> classSet = null;
        //过滤出文件类型的资源
        if (url.getProtocol().equalsIgnoreCase(FILE_PROTOCOL)){
            classSet = new HashSet<Class<?>>();
            File packageDirectory = new File(url.getPath());
            extractClassFile(classSet, packageDirectory, packageName);
        }
        //TODO 此处可以加入针对其他类型资源的处理

        return null;
    }

    /**
     * 递归加载包路径下的class文件
     * @param classSet
     * @param packageDirectory
     * @param packageName
     */
    private static void extractClassFile(Set<Class<?>> classSet, File packageDirectory, String packageName) {

        if(!packageDirectory.isDirectory()){
            return;
        }
        //如果是一个文件夹，则调用其listFiles方法获取文件夹下的文件或文件夹
        File[] files = packageDirectory.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if(pathname.isDirectory()){
                    return true;
                } else{
                    //获取文件的绝对值路径
                    String absoluteFilePath = pathname.getAbsolutePath();
                    if(absoluteFilePath.endsWith(".class")){
                        //若是class文件，则直接加载
                        addToClassSet(absoluteFilePath);
                    }
                }
                return false;
            }


            //根据class文件的绝对值路径，获取并生成class对象，并放入classSet中
            private void addToClassSet(String absoluteFilePath) {
                //1.从class文件的绝对值路径里提取出包含了package的类名
                //如/Users/zhangtc/springframework/simple-spring/target/classes/com/zhangtianci/entity/dto/MainPageInfoDTO.class
                //需要弄成com.zhangtianci.entity.dto.MainPageInfoDTO
                absoluteFilePath = absoluteFilePath.replace(File.separator, ".");
                String className = absoluteFilePath.substring(absoluteFilePath.indexOf(packageName));
                className = className.substring(0, className.lastIndexOf("."));
                //2.通过反射机制获取对应的Class对象并加入到classSet里
                Class targetClass = loadClass(className);
                classSet.add(targetClass);
            }
        });

        if(files == null){
            return;
        }
        Arrays.stream(files).forEach( file -> {
            extractClassFile(classSet,file,packageName);
        });


    }


}