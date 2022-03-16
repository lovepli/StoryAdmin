> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [www.cnblogs.com](https://www.cnblogs.com/orzlin/p/9717399.html)

一、前言
----

最近公司项目准备开始重构，框架选定为 SpringBoot+Mybatis，本篇主要记录了在 IDEA 中搭建 SpringBoot 多模块项目的过程。

### 1、开发工具及系统环境

*   IDE：IntelliJ IDEA 2018.2
*   系统环境：mac OSX

### 2、项目目录结构

*   biz 层：业务逻辑层
*   dao 层：数据持久层
*   web 层：请求处理层

二、搭建步骤
------

### 1、创建父工程

① IDEA 工具栏选择菜单 File -> New -> Project...

![](https://img2018.cnblogs.com/blog/204550/201809/204550-20180929114720086-1827595763.png)

② 选择 Spring Initializr，Initializr 默认选择 Default，点击 Next

![](https://img2018.cnblogs.com/blog/204550/201809/204550-20180928105045425-1810831414.png)

③ 填写输入框，点击 Next

![](https://img2018.cnblogs.com/blog/204550/201809/204550-20180928105241577-1662965500.png)

④ 这步不需要选择直接点 Next

![](https://img2018.cnblogs.com/blog/204550/201809/204550-20180929115148574-917490050.png)

⑤ 点击 Finish 创建项目

![](https://img2018.cnblogs.com/blog/204550/201809/204550-20180929115241953-406078462.png)

⑥ 最终得到的项目目录结构如下

![](https://img2018.cnblogs.com/blog/204550/201809/204550-20180929101648370-622219953.png)

⑦ 删除无用的. mvn 目录、src 目录、mvnw 及 mvnw.cmd 文件，最终只留. gitignore 和 pom.xml

![](https://img2018.cnblogs.com/blog/204550/201809/204550-20180928105613194-1653611796.png)

### 2、创建子模块

① 选择项目根目录 beta 右键呼出菜单，选择 New -> Module

![](https://img2018.cnblogs.com/blog/204550/201809/204550-20180929115754662-1379666907.png)

② 选择 Maven，点击 Next

![](https://img2018.cnblogs.com/blog/204550/201809/204550-20180929115912955-1618636439.png)

③ 填写 ArifactId，点击 Next

![](https://img2018.cnblogs.com/blog/204550/201809/204550-20180929120020062-485086833.png)

④ 修改 Module name 增加横杠提升可读性，点击 Finish

![](https://img2018.cnblogs.com/blog/204550/201809/204550-20180929120153193-1448936531.png)

⑤ 同理添加【beta-dao】、【beta-web】子模块，最终得到项目目录结构如下图

![](https://img2018.cnblogs.com/blog/204550/201809/204550-20180928105853002-1169526402.png)

### 3、运行项目

① 在 beta-web 层创建 com.yibao.beta.web 包（注意：这是多层目录结构并非单个目录名，com >> yibao >> beta >> web）并添加入口类 BetaWebApplication.java

[![](http://common.cnblogs.com/images/copycode.gif)](javascript:void(0); "复制代码")

```
package com.yibao.beta.web;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author linjian
 * @date 2018/9/29
 */
@SpringBootApplication
public class BetaWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(BetaWebApplication.class, args);
    }
}
```

[![](http://common.cnblogs.com/images/copycode.gif)](javascript:void(0); "复制代码")

② 在 com.yibao.beta.web 包中添加 controller 目录并新建一个 controller，添加 test 方法测试接口是否可以正常访问

[![](http://common.cnblogs.com/images/copycode.gif)](javascript:void(0); "复制代码")

```
package com.yibao.beta.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linjian
 * @date 2018/9/29
 */
@RestController
@RequestMapping("demo")
public class DemoController {

    @GetMapping("test")
    public String test() {
        return "Hello World!";
    }
}
```

[![](http://common.cnblogs.com/images/copycode.gif)](javascript:void(0); "复制代码")

③ 运行 BetaWebApplication 类中的 main 方法启动项目，默认端口为 8080，访问 http://localhost:8080/demo/test 得到如下效果

![](https://img2018.cnblogs.com/blog/204550/201809/204550-20180929110149898-1746617917.png)

以上虽然项目能正常启动，但是模块间的依赖关系却还未添加，下面继续完善

### 4、配置模块间的依赖关系

各个子模块的依赖关系：biz 层依赖 dao 层，web 层依赖 biz 层

① 父 pom 文件中声明所有子模块依赖（dependencyManagement 及 dependencies 的区别自行查阅文档）

[![](http://common.cnblogs.com/images/copycode.gif)](javascript:void(0); "复制代码")

```
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>com.yibao.beta</groupId>
            <artifactId>beta-biz</artifactId>
            <version>${beta.version}</version>
        </dependency>
        <dependency>
            <groupId>com.yibao.beta</groupId>
            <artifactId>beta-dao</artifactId>
            <version>${beta.version}</version>
        </dependency>
        <dependency>
            <groupId>com.yibao.beta</groupId>
            <artifactId>beta-web</artifactId>
            <version>${beta.version}</version>
        </dependency>
    </dependencies>
</dependencyManagement>
```

[![](http://common.cnblogs.com/images/copycode.gif)](javascript:void(0); "复制代码")

其中 ${beta.version} 定义在 properties 标签中

② 在 beta-web 层中的 pom 文件中添加 beta-biz 依赖

```
<dependencies>
    <dependency>
        <groupId>com.yibao.beta</groupId>
        <artifactId>beta-biz</artifactId>
    </dependency>
</dependencies>
```

③ 在 beta-biz 层中的 pom 文件中添加 beta-dao 依赖

```
<dependencies>
    <dependency>
        <groupId>com.yibao.beta</groupId>
        <artifactId>beta-dao</artifactId>
    </dependency>
</dependencies>
```

### 5、web 层调用 biz 层接口测试

① 在 beta-biz 层创建 com.yibao.beta.biz 包，添加 service 目录并在其中创建 DemoService 接口类

[![](http://common.cnblogs.com/images/copycode.gif)](javascript:void(0); "复制代码")

```
package com.yibao.beta.biz.service;

/**
 * @author linjian
 * @date 2018/9/29
 */
public interface DemoService {

    String test();
}
```

[![](http://common.cnblogs.com/images/copycode.gif)](javascript:void(0); "复制代码")

[![](http://common.cnblogs.com/images/copycode.gif)](javascript:void(0); "复制代码")

```
package com.yibao.beta.biz.service.impl;

import com.yibao.beta.biz.service.DemoService;
import org.springframework.stereotype.Service;

/**
 * @author linjian
 * @date 2018/9/29
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Override
    public String test() {
        return "test";
    }
}
```

[![](http://common.cnblogs.com/images/copycode.gif)](javascript:void(0); "复制代码")

② DemoController 通过 @Autowired 注解注入 DemoService，修改 DemoController 的 test 方法使之调用 DemoService 的 test 方法，最终如下所示

[![](http://common.cnblogs.com/images/copycode.gif)](javascript:void(0); "复制代码")

```
package com.yibao.beta.web.controller;

import com.yibao.beta.biz.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linjian
 * @date 2018/9/29
 */
@RestController
@RequestMapping("demo")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @GetMapping("test")
    public String test() {
        return demoService.test();
    }
}
```

[![](http://common.cnblogs.com/images/copycode.gif)](javascript:void(0); "复制代码")

③ 再次运行 BetaWebApplication 类中的 main 方法启动项目，发现如下报错

[![](http://common.cnblogs.com/images/copycode.gif)](javascript:void(0); "复制代码")

```
***************************
APPLICATION FAILED TO START
***************************

Description:

Field demoService in com.yibao.beta.web.controller.DemoController required a bean of type 'com.yibao.beta.biz.service.DemoService' that could not be found.


Action:

Consider defining a bean of type 'com.yibao.beta.biz.service.DemoService' in your configuration.
```

[![](http://common.cnblogs.com/images/copycode.gif)](javascript:void(0); "复制代码")

原因是找不到 DemoService 类，此时需要在 BetaWebApplication 入口类中增加包扫描，设置 @SpringBootApplication 注解中的 scanBasePackages 值为 com.yibao.beta，最终如下所示

[![](http://common.cnblogs.com/images/copycode.gif)](javascript:void(0); "复制代码")

```
package com.yibao.beta.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author linjian
 * @date 2018/9/29
 */
@SpringBootApplication(scanBasePackages = "com.yibao.beta")
@MapperScan("com.yibao.beta.dao.mapper")
public class BetaWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(BetaWebApplication.class, args);
    }
}
```

[![](http://common.cnblogs.com/images/copycode.gif)](javascript:void(0); "复制代码")

设置完后重新运行 main 方法，项目正常启动，访问 http://localhost:8080/demo/test 得到如下效果

![](https://img2018.cnblogs.com/blog/204550/201809/204550-20180929111538821-252829066.png)

### 6、集成 Mybatis

① 父 pom 文件中声明 mybatis-spring-boot-starter 及 lombok 依赖

[![](http://common.cnblogs.com/images/copycode.gif)](javascript:void(0); "复制代码")

```
dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>1.3.2</version>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.16.22</version>
        </dependency>
    </dependencies>
</dependencyManagement>
```

[![](http://common.cnblogs.com/images/copycode.gif)](javascript:void(0); "复制代码")

② 在 beta-dao 层中的 pom 文件中添加上述依赖

[![](http://common.cnblogs.com/images/copycode.gif)](javascript:void(0); "复制代码")

```
<dependencies>
    <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
    </dependency>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
    </dependency>
</dependencies>
```

[![](http://common.cnblogs.com/images/copycode.gif)](javascript:void(0); "复制代码")

③ 在 beta-dao 层创建 com.yibao.beta.dao 包，通过 mybatis-genertaor 工具生成 dao 层相关文件（DO、Mapper、xml），存放目录如下

![](https://img2018.cnblogs.com/blog/204550/201809/204550-20180929143916284-1100099645.png)

④ applicatio.properties 文件添加 jdbc 及 mybatis 相应配置项

[![](http://common.cnblogs.com/images/copycode.gif)](javascript:void(0); "复制代码")

```
spring.datasource.driverClassName = com.mysql.jdbc.Driver
spring.datasource.url = jdbc:mysql://192.168.1.1/test?useUnicode=true&characterEncoding=utf-8
spring.datasource.username = test
spring.datasource.password = 123456

mybatis.mapper-locations = classpath:mybatis/*.xml
mybatis.type-aliases-package = com.yibao.beta.dao.entity
```

[![](http://common.cnblogs.com/images/copycode.gif)](javascript:void(0); "复制代码")

⑤ DemoService 通过 @Autowired 注解注入 UserMapper，修改 DemoService 的 test 方法使之调用 UserMapper 的 selectByPrimaryKey 方法，最终如下所示

[![](http://common.cnblogs.com/images/copycode.gif)](javascript:void(0); "复制代码")

```
package com.yibao.beta.biz.service.impl;

import com.yibao.beta.biz.service.DemoService;
import com.yibao.beta.dao.entity.UserDO;
import com.yibao.beta.dao.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author linjian
 * @date 2018/9/29
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public String test() {
        UserDO user = userMapper.selectByPrimaryKey(1);
        return user.toString();
    }
}
```

[![](http://common.cnblogs.com/images/copycode.gif)](javascript:void(0); "复制代码")

⑥ 再次运行 BetaWebApplication 类中的 main 方法启动项目，发现如下报错

[![](http://common.cnblogs.com/images/copycode.gif)](javascript:void(0); "复制代码")

```
APPLICATION FAILED TO START
***************************

Description:

Field userMapper in com.yibao.beta.biz.service.impl.DemoServiceImpl required a bean of type 'com.yibao.beta.dao.mapper.UserMapper' that could not be found.


Action:

Consider defining a bean of type 'com.yibao.beta.dao.mapper.UserMapper' in your configuration.
```

[![](http://common.cnblogs.com/images/copycode.gif)](javascript:void(0); "复制代码")

原因是找不到 UserMapper 类，此时需要在 BetaWebApplication 入口类中增加 dao 层包扫描，添加 @MapperScan 注解并设置其值为 com.yibao.beta.dao.mapper，最终如下所示

[![](http://common.cnblogs.com/images/copycode.gif)](javascript:void(0); "复制代码")

```
package com.yibao.beta.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author linjian
 * @date 2018/9/29
 */
@SpringBootApplication(scanBasePackages = "com.yibao.beta")
@MapperScan("com.yibao.beta.dao.mapper")
public class BetaWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(BetaWebApplication.class, args);
    }
}
```

[![](http://common.cnblogs.com/images/copycode.gif)](javascript:void(0); "复制代码")

设置完后重新运行 main 方法，项目正常启动，访问 http://localhost:8080/demo/test 得到如下效果

![](https://img2018.cnblogs.com/blog/204550/201809/204550-20180929113757380-376292526.png)

至此，一个简单的 SpringBoot+Mybatis 多模块项目已经搭建完毕，我们也通过启动项目调用接口验证其正确性。

四、总结
----

一个层次分明的多模块工程结构不仅方便维护，而且有利于后续微服务化。在此结构的基础上还可以扩展 common 层（公共组件）、server 层（如 dubbo 对外提供的服务）

此为项目重构的第一步，后续还会的框架中集成 logback、disconf、redis、dubbo 等组件

五、未提到的坑
-------

在搭建过程中还遇到一个 maven 私服的问题，原因是公司内部的 maven 私服配置的中央仓库为阿里的远程仓库，它与 maven 自带的远程仓库相比有些 jar 包版本并不全，导致在搭建过程中好几次因为没拉到相应 jar 包导致项目启动不了。