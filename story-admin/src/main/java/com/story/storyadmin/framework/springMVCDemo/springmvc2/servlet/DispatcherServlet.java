package com.story.storyadmin.framework.springMVCDemo.springmvc2.servlet;

import com.story.storyadmin.framework.springMVCDemo.springmvc2.annotation.*;
import com.story.storyadmin.framework.springMVCDemo.springmvc2.controller.UserController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: 59688
 * @date: 2021/9/28
 * @description: 编写核心控制器：DispatcherServlet
 * 在Spring MVC中，DispatcherServlet是核心，下面我们来实现它。首先来说，Spring MVC中的DispatcherServlet说到底，还是HttpServlet的子类，因此我这边自己的DispatcherSerlvet需要extends HttpServlet。
 *
 */
@WebServlet(name = "dispatcherServlet" ,urlPatterns = "/",loadOnStartup = 1 ,
            initParams = {@WebInitParam(name = "base-package",value = "com.story.storyadmin.framework.springMVCDemo.springmvc2")})
public class DispatcherServlet extends HttpServlet {
    private String basePackage= "";
    private List<String> packageNames = new ArrayList<>();
    private Map<String,Object> instanceMap = new HashMap<String,Object>();

    private Map<String,String> nameMap = new HashMap<>();

    private Map<String, Method> urlMethodMap = new HashMap<>();

    private Map<Method,String> methodPackageMap = new HashMap<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
         basePackage = config.getInitParameter("base-package");

         try {

             scanBasePackage(basePackage);
             instance(packageNames);

             springIOC();
             handlerUrlMethodMap();



         }catch (ClassNotFoundException e){
             e.printStackTrace();
         }catch (InstantiationException e){
             e.printStackTrace();
         }catch (IllegalAccessException e){
             e.printStackTrace();
         }
    }

    /**
     * 扫描基包
     * 注意，基包是X.Y.Z的形式，而URL是X/Y/Z的形式，需要转换。
     * @param basePackage
     */
    private void scanBasePackage(String basePackage){
        URL url = this.getClass().getClassLoader().getResource(basePackage.replaceAll("\\.","/"));
        File basePackageFile = new File(url.getPath());
        System.out.println("scan:"+basePackageFile);
        File [] childFiles = basePackageFile.listFiles();
        for (File file:childFiles){
            if (file.isDirectory()){
                scanBasePackage(basePackage+"."+file.getName());
            }else  if (file.isFile()){
                packageNames.add(basePackage+"."+file.getName().split("\\.")[0]);
            }
        }
    }

    /**
     * 实例化
     * 从这里你可以看出，我们完成了被注解标注的类的实例化，以及和注解名称的映射。
     * @param packageNames
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private void instance(List<String> packageNames) throws ClassNotFoundException,IllegalAccessException,InstantiationException{
if (packageNames.size()<1){
    return;
}
for (String string:packageNames){
    Class c= Class.forName(string);

    if (c.isAnnotationPresent(Controller.class)){
        Controller controller = (Controller) c.getAnnotation(Controller.class);
        String controllerName = controller.value();

        instanceMap.put(controllerName,c.newInstance());
        nameMap.put(string,controllerName);
        System.out.println("Controller:"+string+", value:"+controller.value());
    }else if (c.isAnnotationPresent(Service.class)){
        Service service = (Service) c.getAnnotation(Service.class);
        String serviceName = service.value();

        instanceMap.put(serviceName,c.newInstance());
        nameMap.put(string,serviceName);
        System.out.println("Service:"+string+", value:"+service.value());
    }else if (c.isAnnotationPresent(Repository.class)){
        Repository repository = (Repository) c.getAnnotation(Repository.class);
        String repositoryName = repository.value();

        instanceMap.put(repositoryName,c.newInstance());
        nameMap.put(string,repositoryName);
        System.out.println("Repository:"+string+", value:"+repository.value());
    }

}

    }

    /**
     * 依赖注入
     * 以前，我们总是说Spring IOC，下面不就是在做这个事情么？
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     */
    private  void springIOC() throws ClassNotFoundException , IllegalAccessException{
        for (Map.Entry<String,Object> entry:instanceMap.entrySet()){
            Field [] fields = entry.getValue().getClass().getDeclaredFields();

            for (Field field:fields){
                if (field.isAnnotationPresent(Qualifier.class)){
                    String name = field.getAnnotation(Qualifier.class).value();
                    field.setAccessible(true);
                    field.set(entry.getValue(),instanceMap.get(name));
                }
            }
        }
    }

    /**
     * URL映射处理
     * URL，我们需要提取出来，映射到Controller的Method上。
     * @throws ClassNotFoundException
     */
    private void  handlerUrlMethodMap() throws ClassNotFoundException{
        if (packageNames.size() <1){
            return;
        }
        for (String string:packageNames){
            Class c =Class.forName(string);
            if (c.isAnnotationPresent(Controller.class)){
                Method [] methods = c.getMethods();
                StringBuffer baseUrl = new StringBuffer();
                if (c.isAnnotationPresent(RequestMapping.class)){
                    RequestMapping requestMapping = (RequestMapping) c.getAnnotation(RequestMapping.class);
                    baseUrl.append(requestMapping.value());
                }
                for (Method method:methods){
                    if (method.isAnnotationPresent(RequestMapping.class)){
                        RequestMapping requestMapping =method.getAnnotation(RequestMapping.class);
                        baseUrl.append(requestMapping.value());

                        urlMethodMap.put(baseUrl.toString(),method);
                        methodPackageMap.put(method,string);
                    }
                }

            }




        }

    }


    /**
     * 在doPost方法中，非常简单，我们只需要提取出URL，通过URL映射到Method上，然后通过反射的方式进行调用即可。
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
   String uri  = req.getRequestURI();
   String contextPath = req.getContextPath();
   String path = uri.replaceAll(contextPath,"");

   Method method = urlMethodMap.get(path);
   if (method != null){
       String packageName= methodPackageMap.get(method);
       String controllerName = nameMap.get(packageName);

       UserController userController = (UserController) instanceMap.get(controllerName);
       try {
           method.setAccessible(true);
           method.invoke(userController);
       }catch (IllegalAccessException e){
           e.printStackTrace();
       }catch (InvocationTargetException e){
           e.printStackTrace();
       }
   }


    }
}
