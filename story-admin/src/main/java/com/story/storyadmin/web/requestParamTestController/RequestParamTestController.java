package com.story.storyadmin.web.requestParamTestController;

import com.story.storyadmin.domain.entity.sysmgr.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author: 59688
 * @date: 2021/7/21
 * @description: SpringBoot后端接口请求参数映射方式详解 https://mp.weixin.qq.com/s/TSZrtwMvCnjF5UMvILPDOA
 */
@RestController
@RequestMapping("param")
@Slf4j
public class RequestParamTestController {

    /**
     * QueryString方式
     * QueryString参数传递的方式为，在请求URL中直接拼接请求参数，如URL?param1=value1&param2=value2
     *
     * QueryString参数传递方式对于请求方法GET、POST、PUT、PATCH、DELETE都适用
     *
     * 注意：
     * 也可以在自定义对象中将属性声明为数组、集合类型，来接收多个值
     * 对于映射Collection、List和Set类型参数时，即便前端提交的QueryString中的参数名称与Controller接口方法的参数名称一致，也不能省略@RequestParam注解，否则会抛出如下异常
     */

    /**
     * 1.映射基本类型参数
     * http://localhost:8080/param/queryString1?name=baobao&age=18
     * @param name
     * @param age
     */
    @RequestMapping("queryString1")
    public void testQueryString1(@RequestParam("name") String name, @RequestParam("age") Integer age) {
        log.info("name:{}   age:{}", name, age);
    }

    /**
     * 2.映射对象类型参数
     * http://localhost:8080/param/queryString2?name=baobao&age=18
     * @param user
     */
    @RequestMapping("queryString2")
    public void testQueryString2(User user) {
        log.info("name:{}   age:{}", user.getName(), user.getAge());
    }

    /**
     * 3.映射数组、集合类型参数
     * 前端有2种方式针对同一个参数传递多个值：
     * 1、在请求的QueryString中，拼接多个参数名称一样的参数即可，如URL?param=value1&param=value2&param=value3
     * 2、在请求的QueryString中，对同一个参数赋多个值，多个值之间用,隔开，如URL?param=value1,value2,value3
     */

     /**
     * 3.1 映射数组
      * http://localhost:8080/param/queryString3?name=baobao1&name=baobao2&name=baob3
      * http://localhost:8080/param/queryString3?name=baobao1,baobao2,baob3
     * @param nameArray
     */
    @RequestMapping("queryString3")
    public void testQueryString3(@RequestParam("name") String[] nameArray) {
        if (nameArray != null) {
            for (String name : nameArray) {
                log.info(name);
            }
        }
    }

    /**
     * 3.2 映射Collection
     * http://localhost:8080/param/queryString4?name=baobao1&name=baobao2&name=baob3
     * 可以发现框架默认采用的实现类是LinkedHashSet。那我们继续测试传递相同value的时候是否也会采用这个实现,可以发现传递多个相同value的时候框架采用的实现类还是LinkedHashSet，所以有去重的效果
     * @param nameCollection
     */
    @RequestMapping("queryString4")
    public void testQueryString4(@RequestParam("name") Collection<String> nameCollection) {
        if (nameCollection != null) {
            log.info("类型：{}", nameCollection.getClass());
            for (String name : nameCollection) {
                log.info(name);
            }
        }
    }

    /**
     * 3.3 映射List
     * http://localhost:8080/param/queryString5?name=baobao1&name=baobao2&name=baob3
     * 可以发现框架默认采用的实现类是ArrayList
     * @param nameList
     */
    @RequestMapping("queryString5")
    public void testQueryString5(@RequestParam("name") List<String> nameList) {
        if (nameList != null) {
            log.info("类型：{}", nameList.getClass());
            for (String name : nameList) {
                log.info(name);
            }
        }
    }

    /**
     * 3.4 映射Set
     * http://localhost:8080/param/queryString6?name=baobao1&name=baobao2&name=baob3
     * 可以发现框架默认采用的实现类是LinkedHashSet，与声明为Collection类型时一致
     * @param nameSet
     */
    @RequestMapping("queryString6")
    public void testQueryString6(@RequestParam("name") Set<String> nameSet) {
        if (nameSet != null) {
            log.info("类型：{}", nameSet.getClass());
            for (String name : nameSet) {
                log.info(name);
            }
        }
    }

    /**
     * 路径参数方式
     * 路径传参方式是将参数直接包含在URL路径中，比如URL/paramValue1/paramValue2
     *
     * 路径参数方式对于请求方法GET、POST、PUT、PATCH、DELETE都适用
     *
     * 路径参数方式映射到Controller接口方法中的数组、Collection、List和Set类型参数时，即便路径中的参数名称和接口方法中的参数名称一致，也不能省略@PathVariable注解
     */


    /**
     * 1.映射基本类型参数
     * 在Controller中用如下步骤接收参数：
     * 在接口对应的请求路径中用{参数名}形式标出路径参数
     * 在接口方法的参数上标注@PathVariable指名对应路径参数的参数名
     *
     * http://localhost:8080/param/path1/baobao/18
     * @param name
     * @param age
     */
    @RequestMapping("path1/{name}/{age}")
    public void testPath1(@PathVariable("name") String name, @PathVariable("age") Integer age) {
        log.info("name:{}   age:{}", name, age);
    }

    /**
     * 2.映射数组、集合类型参数
     * 直接给路径参数多个值，用,隔开即可
     *
     * 2.1 映射数组
     * 在Controller接口方法中声明数组类型参数，用@PathVariable指明路径参数的名称即可
     * http://localhost:8080/param/path2/baobao1,baobao2,baobao3/18,19,20
     * @param nameArray
     * @param ageArray
     */
    @RequestMapping("path2/{name}/{age}")
    public void testPath2(@PathVariable("name") String[] nameArray, @PathVariable("age") Integer[] ageArray) {
        for (String name : nameArray) {
            log.info(name);
        }
        for (Integer age : ageArray) {
            log.info(age.toString());
        }
    }


    /**
     * 2.2 映射Collection
     * 在Controller接口方法中声明Collection类型参数，用@PathVariable指明路径参数的名称即可
     * http://localhost:8080/param/path3/baobao1,baobao2,baobao3/18,19,20
     * 可以发现框架默认采用的类型是LinkedHashSet
     * @param nameCollection
     * @param ageCollection
     */
    @RequestMapping("path3/{name}/{age}")
    public void testPath3(@PathVariable("name") Collection<String> nameCollection, @PathVariable("age") Collection<Integer> ageCollection) {
        log.info("类型：{}", nameCollection.getClass());
        for (String name : nameCollection) {
            log.info(name);
        }
        for (Integer age : ageCollection) {
            log.info(age.toString());
        }
    }

    /**
     * 2.3 映射List
     * 在Controller接口方法中声明List类型参数，用@PathVariable指明路径参数的名称即可
     * http://localhost:8080/param/path4/baobao1,baobao2,baobao3/18,19,20
     * 框架采用的实现类是ArrayList
     * @param nameList
     * @param ageList
     */
    @RequestMapping("path4/{name}/{age}")
    public void testPath4(@PathVariable("name") List<String> nameList, @PathVariable("age") List<Integer> ageList) {
        log.info("类型：{}", nameList.getClass());
        for (String name : nameList) {
            log.info(name);
        }
        for (Integer age : ageList) {
            log.info(age.toString());
        }
    }

    /**
     * 2.4 映射Set
     * 在Controller接口方法中声明Set类型参数，用@PathVariable指明路径参数的名称即可
     * http://localhost:8080/param/path5/baobao1,baobao2,baobao3/18,19,20
     * 框架采用的实现类是LinkedHashSet
     * @param nameSet
     * @param ageSet
     */
    @RequestMapping("path5/{name}/{age}")
    public void testPath5(@PathVariable("name") Set<String> nameSet, @PathVariable("age") Set<Integer> ageSet) {
        log.info("类型：{}", nameSet.getClass());
        for (String name : nameSet) {
            log.info(name);
        }
        for (Integer age : ageSet) {
            log.info(age.toString());
        }
    }

    /**
     * 表单参数方式
     * 表单参数方式要求在请求头中携带Content-Type，值为application/x-www-form-urlencoded，并且请求体中以param:value形式携带参数，每行代表一个参数，多个参数就有多行
     *
     * 注意：表单参数方式不适用于GET请求，适用于POST、PUT、PATCH、DELETE
     * 也可以在自定义对象中将属性声明为数组、集合类型，来接收多个值
     */

    /**
     * 1.映射基本类型参数
     * 可以在Controller的接口中声明基本类型的参数，然后用@RequestParam注解修饰，指定请求体中的参数名称
     * http://localhost:8080/param/form1
     * @param name
     * @param age
     */
    @RequestMapping("form1")
    public void testForm1(@RequestParam("name") String name, @RequestParam("age") Integer age) {
        log.info("name:{}   age:{}", name, age);
    }

    /**
     * 2.映射对象类型参数
     * 定义一个对象，属性名称和请求体中的参数名称一致即可
     * http://localhost:8080/param/form2
     * @param user
     */
    @RequestMapping("form2")
    public void testForm2(User user) {
        log.info("name:{}   age:{}", user.getName(), user.getAge());
    }

    /**
     * 3.映射数组、集合类型参数
     * 请求体中的参数如果想要传递多个值，有2种方式：
     *     直接给参数多个值，用,隔开即可
     *     声明多个参数名一样的参数
     */

    /**
     * 3.1 映射数组
     * 在Controller接口方法中声明数组类型参数，用@RequestParam指明请求体中的参数名称即可
     * http://localhost:8080/param/form3
     * @param nameArray
     */
    @RequestMapping("form3")
    public void testForm3(@RequestParam("name") String[] nameArray) {
        for (String name : nameArray) {
            log.info(name);
        }
    }

    /**
     * 3.2 映射Collection
     * 在Controller接口方法中声明Collection类型参数，用@RequestParam指明请求体中的参数名称即可
     * http://localhost:8080/param/form4
     * @param nameCollection
     */
    @RequestMapping("form4")
    public void testForm4(@RequestParam("name") Collection<String> nameCollection) {
        log.info("类型：{}", nameCollection.getClass());
        for (String name : nameCollection) {
            log.info(name);
        }
    }

    /**
     * 3.3 映射List
     * 在Controller接口方法中声明List类型参数，用@RequestParam指明请求体中的参数名称即可
     * http://localhost:8080/param/form5
     * @param nameList
     */
    @RequestMapping("form5")
    public void testForm5(@RequestParam("nameList") List<String> nameList) {
        log.info("类型：{}", nameList.getClass());
        for (String name : nameList) {
            log.info(name);
        }
    }


    /**
     * 3.4 映射Set
     * 在Controller接口方法中声明Set类型参数，用@RequestParam指明请求体中的参数名称即可
     * http://localhost:8080/param/form6
     * @param nameSet
     */
    @RequestMapping("form6")
    public void testForm6(@RequestParam("name") Set<String> nameSet) {
        log.info("类型：{}", nameSet.getClass());
        for (String name : nameSet) {
            log.info(name);
        }
    }

    /**
     * FormData方式
     * 用于文件上传。要求在请求头中携带Content-Type，值为multipart/form-data。请求体中可以携带普通参数，也可以携带文件
     *
     * Postman测试发现这种方式适用于GET、POST、PUT、PATCH、DELETE，但浏览器一般只能用POST表单提交进行文件上传，所以建议用POST请求
     *
     * FormData方式中的普通参数(即非文件类型参数)同样也可以利用数组、集合类型接收多个值，可以封装为自定义对象，方法与表单参数方式类似
     */

    /**
     * 可以在Controller的接口中分别声明普通类型参数和文件类型参数，其中文件类型参数必须是MultipartFile类型，然后用@RequestParam注解修饰，指定请求体中对应的参数名称
     * http://localhost:8080/param/formData1
     * @param fileName
     * @param file
     */
    @RequestMapping("formData1")
    public void testFormData1(@RequestParam("fileName") String fileName, @RequestParam("file") MultipartFile file) {
        log.info("{}:{}", fileName, file.getSize());
    }

    /**
     * 请求体json方式
     * 要求请求头携带Content-Type，值为application/json。请求体中的内容为json格式
     *
     * 经过PostMan测试发现，用GET方法发送这种请求也可以顺利接收到参数，但是一般浏览器不支持GET请求携带请求体数据，所以还是建议实际开发中只针对POST、PUT、PATCH、DELETE采用这种方式进行参数映射
     */

    /**
     * 在Controller的接口方法映射这种请求参数只能通过自定义对象，自定义对象的属性名称要与请求体中json的属性名称一致，然后将自定义对象作为接口方法的参数，
     * 并标注@RequestBody注解，框架会自动将请求体的json转换为自定义对象
     * http://localhost:8080/param/json1
     * @param user
     */
    @RequestMapping("json1")
    public void testJson1(@RequestBody User user) {
        log.info(user.toString());
    }


}
