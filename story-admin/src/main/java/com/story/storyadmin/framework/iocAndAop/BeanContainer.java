package com.story.storyadmin.framework.iocAndAop;

import lombok.extern.slf4j.Slf4j;


import java.lang.annotation.Annotation;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Classname BeanContainer
 * @Description bean容器
 * <p>
 * BeanContainer应该是单例的 采用内部枚举的方式实现。
 *
 * 应该拥有的实例方法:
 * 1.boolean isLoad() 是否加载
 * 2.int getSize()    获取bean个数
 * 3.loadBeans(String packageName) 根据包名加载所有被配置标记的class文件/
 * 4.获取/删除/增加bean及提供一些便利的方法
 *
 * @Date 2020/8/6 14:41
 * @Created by zhangtianci
 */
@Slf4j
public class BeanContainer {
    /**
     * 存放所有被配置标记（xml/注解）的class，并new出一个实例对象bean
     * 存放在一个map中
     */
    private final Map<Class<?>, Object> beanMap = new ConcurrentHashMap<>();

    /**
     * 加载bean的注解列表
     */
    private static final List<Class<? extends Annotation>> BEAN_ANNOTATION
            = Arrays.asList(Component.class, Controller.class, Service.class, Repository.class);

    /**
     * 是否已经加载过bean
     */
    private boolean loaded = false;

    /**
     * 获取bean容器
     *
     * @return
     */
    public static BeanContainer getInstance() {
        return ContainerHolder.HOLDER.instance;
    }

    private enum ContainerHolder {
        HOLDER;

        private BeanContainer instance;

        ContainerHolder() {
            instance = new BeanContainer();
        }
    }

    /**
     * 是否已经加载过bean
     */
    public boolean isLoad(){
        return loaded;
    }

    /**
     * 获取容器中bean的个数
     */

    public int getSize(){
        return beanMap.size();
    }

    /**
     * 加载指定路径下的所有class文件
     * 并将所有被配置标记（xml/注解）的class对象和new出一个实例对象放入容器
     */
    public synchronized void loadBeans(String packageName){
        //判断容器是否已经加载过
        if (loaded){
            log.warn("Container has loaded!");
            return;
        }
        // 导出所有的class对象
        Set<Class<?>> classSet = ClassUtil.extractPackageClass(packageName);
        //为空直接return
        if (ValidationUtil.isEmpty(classSet)) {
            log.warn("extract nothing from packageName" + packageName);
            return;
        }

        classSet.stream().forEach(clazz -> {
            for (Class<? extends Annotation> annotationClazz : BEAN_ANNOTATION) {
                if (clazz.isAnnotationPresent(annotationClazz)){
                    //将目标类本身作为键，目标类的实例作为值，放入到beanMap中
                    beanMap.put(clazz, ClassUtil.newInstance(clazz, true));
                }
            }

        });

        loaded = true;
    }


    /**
     * 添加一个class对象及其Bean实例
     *
     * @param clazz Class对象
     * @param bean  Bean实例
     * @return 原有的Bean实例, 没有则返回null
     */
    public Object addBean(Class<?> clazz, Object bean) {
        return beanMap.put(clazz, bean);
    }

    /**
     * 移除一个IOC容器管理的对象
     *
     * @param clazz Class对象
     * @return 删除的Bean实例, 没有则返回null
     */
    public Object removeBean(Class<?> clazz) {
        return beanMap.remove(clazz);
    }

    /**
     * 根据Class对象获取Bean实例
     *
     * @param clazz Class对象
     * @return Bean实例
     */
    public Object getBean(Class<?> clazz) {
        return beanMap.get(clazz);
    }
    /**
     * 获取容器管理的所有Class对象集合
     *
     * @return Class集合
     */
    public Set<Class<?>> getClasses(){
        return beanMap.keySet();
    }
    /**
     * 获取所有Bean集合
     *
     * @return Bean集合
     */
    public Set<Object> getBeans(){
        return new HashSet<>( beanMap.values());
    }
    /**
     * 根据注解筛选出Bean的Class集合
     *
     * @param annotation 注解
     * @return Class集合
     */
    public Set<Class<?>> getClassesByAnnotation(Class<? extends Annotation> annotation){
        //1.获取beanMap的所有class对象
        Set<Class<?>> keySet = getClasses();
        if(ValidationUtil.isEmpty(keySet)){
            log.warn("nothing in beanMap");
            return null;
        }
        //2.通过注解筛选被注解标记的class对象，并添加到classSet里
        Set<Class<?>> classSet = new HashSet<>();
        for(Class<?> clazz : keySet){
            //类是否有相关的注解标记
            if(clazz.isAnnotationPresent(annotation)){
                classSet.add(clazz);
            }
        }
        return classSet.size() > 0? classSet: null;
    }
    /**
     * 通过接口或者父类获取实现类或者子类的Class集合，不包括其本身
     *
     * @param interfaceOrClass 接口Class或者父类Class
     * @return Class集合
     */
    public Set<Class<?>> getClassesBySuper(Class<?> interfaceOrClass){
        //1.获取beanMap的所有class对象
        Set<Class<?>> keySet = getClasses();
        if(ValidationUtil.isEmpty(keySet)){
            log.warn("nothing in beanMap");
            return null;
        }
        //2.判断keySet里的元素是否是传入的接口或者类的子类，如果是，就将其添加到classSet里
        Set<Class<?>> classSet = new HashSet<>();
        for(Class<?> clazz : keySet){
            //判断keySet里的元素是否是传入的接口或者类的子类
            if(interfaceOrClass.isAssignableFrom(clazz) && !clazz.equals(interfaceOrClass)){
                classSet.add(clazz);
            }
        }
        return classSet.size() > 0? classSet: null;
    }


}