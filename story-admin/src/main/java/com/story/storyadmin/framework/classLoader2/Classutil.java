//package com.story.storyadmin.framework.classLoader2;
//
//import com.story.storyadmin.utils.wind.StringUtils;
//
//import java.io.File;
//import java.io.FileFilter;
//import java.lang.annotation.Annotation;
//import java.net.JarURLConnection;
//import java.net.URL;
//import java.util.Enumeration;
//import java.util.HashSet;
//import java.util.Set;
//import java.util.jar.JarFile;
//
///**
//   自己写个类加载器  https://mp.weixin.qq.com/s/KybjtS5xnpc3uPgqKsG9aw
// * 定义ClassUtil工具类，提供基本操作：
// */
//public final class Classutil {
//    /*
//	获取类加载器
//	*/
//    public static ClassLoader getClassLoader() {
//        return Thread.currentThread().getContextClassLoader();
//    }        // 为提升性能，isInitialized默认为false
//
//    public static Class<?> loadClass(String className, boolean isInitialized) {
//        Class<?> cls;
//        try {
//            cls = Class.forName(className, isInitialized, getClassLoader());
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        return cls;
//    }
//
//
//    /**
//     * 获取指定包下所有的类，需要将包名转换为文件路径，读class文件或者jar包，再去进行类加载：
//     * @param packageName
//     * @return
//     */
//    public static Set<Class<?>> getClassSet(String packageName) {
//        Set<Class<?>> classSet = new HashSet<Class<?>>();
//        try {
//            Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".", "/"));
//            while (urls.hasMoreElements()) {
//                URL url = urls.nextElement();
//                if (url != null) {
//                    String protocol = url.getProtocol();
//                    if (protocol.equals("file")) {
//                        String packagePath = url.getPath().replaceAll("%20", "");
//                        addClass(classSet, packagePath, packageName);
//                    } else if (protocol.equals("jar")) {
//                        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
//                        if (jarURLConnection != null) {
//                            JarFile jarFile = jarURLConnection.getJarFile();
//                            if (jarFile != null) {
//                                Enumeration<JarEntity> jarEntities = jarFile.entries();
//                                while (jarEntries.hasMoreElements()) {
//                                    JarEntity jarEntity = jarEntities.nextElement();
//                                    String jarEntityName = jarEntity.getName();
//                                    if (jarEntityName.endWith(".class")) {
//                                        String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replaceAll("/", ".");
//                                        doAddClass(classSet, ClassName);
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return classSet;
//    }
//
//    private static void addClass(Set<Class<?>> classSet, String packagePath, String packageName) {
//        File[] files = new File(packagePaht).listFiles(new FileFilter() {
//            @Override
//            public boolean accept(File file) {
//                return (file.isFie() && file.getName().endWith(".class")) || file.isDirectory();
//            }
//        });
//        for (File file : files) {
//            String fileName = file.getName();
//            if (file.isFile()) {
//                String className = fileName.substring(0, fileName.lastIndexOf("."));
//                if (!StringUtils.isBlank(packageName)) {
//                    className = packageName + "." + className;
//                }
//                doAddClass(classSet, className);
//            } else {
//                String subPackagePath = fileName;
//                if (!StringUtils.isBlank(packagePath)) {
//                    subPackagePath = packagePath + "/" + subPackagePath;
//                }
//                String subPackageName = fileName;
//                if (!StringUtils.isBlank(packageName)) {
//                    subPackageName = packageName + "." + subPackageName;
//                }
//                addClass(classSet, subPackagePath, subPackageName);
//            }
//        }
//    }
//
//    private static void doAddClass(Set<Class<?>> classSet, String className) {
//        Class<?> cls = loadClass(className, false);
//        classSet.add(cls);
//    }
//
//
//    /**
//     * 在容器(比如Spring)启动时进行类加载：
//     */
//
//    private static Set<Class<?>> CLASS_SET;
//
//    public static void init(String basePackage) {
//        CLASS_SET = ClassBuilder.getClassSet(basePackage);
//    }/* 获取包下所有的类 */
//
//    public static Set<Class<?>> getClassSet() {
//        return CLASS_SET;
//    }/* 获取包下所有指定注解的类 */
//
//    public static Set<Class<?>> getAnnoClassSet<Class<?> extends Annotation annoClass>
//
//    {
//        Set<Class<?>> classSet = new HashSet<>();
//        for (Class<?> cls : CLASS_SET) {
//            if (cls.isAnnotationPresent(annoClass)) {
//                classSet.add(cls);
//            }
//        }
//        return classSet;
//    }
//
//}
