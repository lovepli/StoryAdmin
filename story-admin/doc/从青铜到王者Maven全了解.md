> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [mp.weixin.qq.com](https://mp.weixin.qq.com/s/uLEZPVLfQfEqUGCg9dHbJA)

Maven 是每个 Java 程序都会遇到的包管理工具，今天整理一下 Maven 的相关知识，从青铜到王者，一文全了解

### 1、maven 是什么，为什么存在？项目结构是什么样子，怎么定位 jar

官方网站说了好多，整的多复杂一样，简单说：maven 是一个管理包的工具。

Maven 存在的必要性是什么呐？想想开源的 jar 包如此之多，版本又如此之多，在没有 Maven 之前，我们管理 jar 包全部都是下载之后创建一个 lib 的文件夹，然后项目进行引用，在其他的项目成员需要修改一个 jar 的时候需要到处拷贝，在部署的时候也很麻烦，问题存在就要解决，因此出现了 Maven，统一管理，统一的仓库，只需要配置是要哪个版本的包，直接下载就够了，不用拷贝，是不是很方便。

现在大的问题解决了，怎么定位一个 jar 包呐？

![](https://mmbiz.qpic.cn/sz_mmbiz_png/qZiaNAjhV5icj0iatRqicGiaFX0SP1tBCJ6c4Eqp9lgKjyaWOgWM0IhHCZwj7tXhJv1gG3onUjZpp3wIHFcLso0pMwg/640?wx_fmt=png)

### 2、Idea 的操作

#### 1. 新建 maven 项目

File -> 新建 ->project

![](https://mmbiz.qpic.cn/sz_mmbiz_png/qZiaNAjhV5icj0iatRqicGiaFX0SP1tBCJ6c4DbuPN9UfIJQnAiaXyHfIqlXnn4hhWvjLLS6evmicQRfL1eawV1TXDf7Q/640?wx_fmt=png)

勾选从原型（模板）创建，选择 maven-archetype-qiuckstart

![](https://mmbiz.qpic.cn/sz_mmbiz_png/qZiaNAjhV5icj0iatRqicGiaFX0SP1tBCJ6c4HGt0NfquYY2YQDRaIKfemg9jia1mwrB7ibIicBRaib0t2FKoKugRTzFwFw/640?wx_fmt=png)

填入项目的名字，和 groupId （公司域名反过来，如 com.alibaba）

![](https://mmbiz.qpic.cn/sz_mmbiz_png/qZiaNAjhV5icj0iatRqicGiaFX0SP1tBCJ6c46n0RibcH0NaSdeicpEXc699vhhdOHEeP4j7DZlPIw8RvRiaiad7abBJmCA/640?wx_fmt=png)

选择本地仓库的位置，和自定义的 setting 配置

![](https://mmbiz.qpic.cn/sz_mmbiz_png/qZiaNAjhV5icj0iatRqicGiaFX0SP1tBCJ6c4d1k6f3ianNfQm7FMHg37J2aG0wUtT32s3nrkhnhvrTAfUBibXS2L0DnA/640?wx_fmt=png)

一路 finish，然后等待 idea 创建模板项目就好了。

#### 2. 配置仓库

Maven 仓库有三种类型：

*   本地（local）

*   中央（central）

*   远程（remote）


当我们执行 Maven 构建命令时，**Maven 开始按照以下顺序查找依赖的库**：

*   **步骤 1** － 在本地仓库中搜索，如果找不到，执行步骤 2，如果找到了则执行其他操作。

*   **步骤 2** － 在中央仓库中搜索，如果找不到，并且有一个或多个远程仓库已经设置，则执行步骤 4，如果找到了则下载到本地仓库中以备将来引用。

*   **步骤 3** － 如果远程仓库没有被设置，Maven 将简单的停滞处理并抛出错误（无法找到依赖的文件）。

*   **步骤 4** － 在一个或多个远程仓库中搜索依赖的文件，如果找到则下载到本地仓库以备将来引用，否则 Maven 将停止处理并抛出错误（无法找到依赖的文件）。


阿里云仓库配置：

```
   <repositories>
       <repository>
           <id>central</id>
           <name>aliyun maven</name>
           <url>https://maven.aliyun.com/repository/public/</url>
           <layout>default</layout>
           <!-- 是否开启发布版构件下载 -->
           <releases>
               <enabled>true</enabled>
           </releases>
           <!-- 是否开启快照版构件下载 -->
           <snapshots>
               <enabled>false</enabled>
           </snapshots>
       </repository>
   </repositories>
```

#### 3. 添加依赖，添加 fastjson 的依赖

举个例子：

```
       <dependency>
           <groupId>com.alibaba</groupId>
           <artifactId>fastjson</artifactId>
       </dependency>
```

#### 4. 打包项目

![](https://mmbiz.qpic.cn/sz_mmbiz_png/qZiaNAjhV5icj0iatRqicGiaFX0SP1tBCJ6c4gZ4Ijr6PibWYKlkt2z6cJib2rFxavrlwvAwu676Sc7ZTIl9pyYF1Ss3w/640?wx_fmt=png)

### 4、Maven 坐标主要组成

*   groupId：组织标识（包名），一般常用公司域名的反序，比如 com.alibaba

*   artifactId：项目名称，项目的具体名称

*   version：项目的当前版本 ，一般版本号为 大版本. 小版本. 小版本序号

*   packaging：项目的打包方式，最为常见的 jar 和 war 两种


### 5、maven 生命周期

#### 5.1 名词解释

*   **lifecycle：**生命周期，这是 maven 最高级别的的控制单元，它是一系列的 phase 组成，也就是说，一个生命周期，就是一个大任务的总称，不管它里面分成多少个子任务，反正就是运行一个 lifecycle，就是交待了一个任务，运行完后，就得到了一个结果，中间的过程，是 phase 完成的，自己可以定义自己的 lifecycle，包含自己想要的 phase

*   **phase：**可以理解为任务单元，lifecycle 是总任务，phase 就是总任务分出来的一个个子任务，但是这些子任务是被规格化的，它可以同时被多个 lifecycle 所包含，一个 lifecycle 可以包含任意个 phase，phase 的执行是按顺序的，一个 phase 可以绑定很多个 goal，至少为一个，没有 goal 的 phase 是没有意义的

*   **goal:** 这是执行任务的最小单元，它可以绑定到任意个 phase 中，一个 phase 有一个或多个 goal，goal 也是按顺序执行的，一个 phase 被执行时，绑定到 phase 里的 goal 会按绑定的时间被顺序执行，不管 phase 己经绑定了多少个 goal，你自己定义的 goal 都可以继续绑到 phase 中

*   **mojo:** lifecycle 与 phase 与 goal 都是概念上的东西，mojo 才是做具体事情的，可以简单理解 mojo 为 goal 的实现类，它继承于 AbstractMojo，有一个 execute 方法，goal 等的定义都是通过在 mojo 里定义一些注释的 anotation 来实现的，maven 会在打包时，自动根据这些 anotation 生成一些 xml 文件，放在 plugin 的 jar 包里


**可以通俗理解为 lifecyle 是一个公司，phrase 是具体的部门，goal 是一个项目，Mojo 是项目内部的人，其他的都是管理层级，具体的执行还是人。**

#### 5.2 生命周期

![](https://mmbiz.qpic.cn/sz_mmbiz_png/qZiaNAjhV5icj0iatRqicGiaFX0SP1tBCJ6c4ZCp65j11zSCoPFSpY6jCGfOYP112icyG9ZBibx6Ff2icbJRNcWsgVeQxg/640?wx_fmt=png)

#### 5.3 goal 的概念

一个 goal 是独立的，它可以被绑定到多个 phase 中去，也可以一个 phase 都没有。如果一个 goal 没有被绑定到任何一个 lifecycle，它仍然可以直接被调用，而不是被 lifecycle 调用。

因此可以这样理解 phase 与 goal 的关系：

1.  phase 其实就是 goal 的容器。实际被执行的都是 goal。phase 被执行时，实际执行的都是被绑定到该 phase 的 goal。

2.  goal 与 goal 之间是独立的。因此单独执行一个 goal 不会导致其他 goal 被执行。


**goal 可以通俗理解为一个项目。**

#### 5.4 生命周期和 phase 的关系

clean 生命周期每套生命周期都由一组阶段 (Phase) 组成，我们平时在命令行输入的命令总会对应于一个特定的阶段。比如，运行 mvn clean ，这个的 clean 是 Clean 生命周期的一个阶段。有 Clean 生命周期，也有 clean 阶段。Clean 生命周期一共包含了三个阶段：

1.  pre-clean 执行一些需要在 clean 之前完成的工作

2.  clean 移除所有上一次构建生成的文件

3.  post-clean 执行一些需要在 clean 之后立刻完成的工作


"mvn clean" 中的 clean 就是上面的 clean，在一个生命周期中，运行某个阶段的时候，它之前的所有阶段都会被运行，也就是说，"mvn clean" 等同于 mvn pre-clean clean ，如果我们运行 mvn post-clean ，那么 pre-clean，clean 都会被运行。这是 Maven 很重要的一个规则，可以大大简化命令行的输入

执行 phase 实际执行的是 goal。如果一个 phase 没有绑定 goal，那这个 phase 就不会被执行。

```
<plugin>
  <groupId>com.mycompany.example</groupId>
  <artifactId>display-maven-plugin</artifactId>
  <version>1.0</version>
  <executions>
    <execution>
      <phase>process-test-resources</phase>
      <goals>
        <goal>time</goal>
      </goals>
    </execution>
  </executions>
</plugin>
```

**一个生命周期包含一些列的步骤，当执行生命周期的时候，会把所有的步骤执行一次**

官方文档：

http://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html

http://maven.apache.org/ref/3.3.9/maven-core/lifecycles.html

### 6、idea maven 的配置

POM 中可以指定以下配置：

*   项目依赖 dependencies

*   插件  plugins

*   执行目标

*   项目构建 profile

*   项目版本

*   项目开发者列表

*   相关邮件列表信息


具体的配置可以参考 fastjson 的配置：

https://github.com/alibaba/fastjson/blob/master/pom.xml

### 7、POM 有 2 个很重要的关系：聚合、继承

#### 一、聚合

如果我们想一次构建多个项目模块，那我们就需要对多个项目模块进行聚合

1. 聚合配置代码

```
 <modules>
       <module>模块一</module>
       <module>模块二</module>
       <module>模块三</module>
 </modules>
```

例如：对项目的 Hello、HelloFriend、MakeFriends 这三个模块进行聚合

```
 <modules>
       <module>../Hello</module>  
       <module>../HelloFriend</module>        
       <module>../MakeFriends</module>
 </modules>
```

其中 module 的路径为相对路径。

#### 二、继承

继承为了消除重复的配置，我们把很多相同的配置提取出来，例如：grouptId，version，相同的依赖包等。

继承配置代码：

```
<parent>  
         <groupId>me.gacl.maven</groupId>
         <artifactId>ParentProject</artifactId>
         <version>0.0.1-SNAPSHOT</version>
         <relativePath>../ParentProject/pom.xml</relativePath>  
</parent>
```

Idea 中可以新建一个 maven 项目，然后删光文件夹，只留一个 pom.xml，然后添加模块，选择继承。

![](https://mmbiz.qpic.cn/sz_mmbiz_png/qZiaNAjhV5icj0iatRqicGiaFX0SP1tBCJ6c45n6DqSciaKQXX3vzFicg8rShDq4AqvCUmQDI0cam58ueYiahhzSW8V8FA/640?wx_fmt=png)

### 8.Maven 中的 profile

*   Maven 中有一个概念叫做：`profile`，它主要是为了解决不同环境所需的不同变量、配置等问题。比如我们内网开发的数据库配置，端口配置等是和生产环境不同的，这个时候就需要区分。

*   有了 profile，可以根据激活的条件，启动不同条件下的配置信息。

*   profile 是可以有多个的，也可以同时激活多个 profile，方便自由组合。


```
<profiles>
       <profile>
           <!--不同环境Profile的唯一id-->
           <!--开发环境-->
           <id>dev</id>
           <properties>
               <!--profiles.active是自定义的字段（名字随便起），自定义字段可以有多个-->
               <profiles.active>dev</profiles.active>
           </properties>
       </profile>
       <profile>
           <!--线上环境-->
           <id>prod</id>
           <properties>
               <profiles.active>prod</profiles.active>
           </properties>
           <activation>
               <activeByDefault>true</activeByDefault>
           </activation>
       </profile>
   </profiles>
```

![](https://mmbiz.qpic.cn/sz_mmbiz_png/qZiaNAjhV5icj0iatRqicGiaFX0SP1tBCJ6c4aRtcB3rGwBicEicl7ib2G8O8ODtfsTspMqdKlvT7qq0bHSHVFs2zyrtYQ/640?wx_fmt=png)

Idea 中会显示配置的两个 profile ，可以选择激活

![](https://mmbiz.qpic.cn/sz_mmbiz_png/qZiaNAjhV5icj0iatRqicGiaFX0SP1tBCJ6c4fcRLwjImBiagkiaAwsDM1y1E59XowDnGictMcv6ZLFjrfSSqX7XFBr12g/640?wx_fmt=png)

pom 文件里的配置为

```
<build>
       <resources>
           <resource>
               <directory>src/main/resources/</directory>
               <!--先排除掉两个文件夹-->
               <excludes>
                   <exclude>dev/*</exclude>
                   <exclude>prod/*</exclude>
               </excludes>
               <includes>
                   <!--如果有其他定义通用文件，需要包含进来-->
                   <!--<include>messages/*</include>-->
               </includes>
           </resource>
           <resource>
               <!--这里是关键！根据不同的环境，把对应文件夹里的配置文件打包-->
               <directory>src/main/resources/${profiles.active}</directory>
           </resource>
       </resources>
   </build>

  <profiles>
       <profile>
           <!--不同环境Profile的唯一id-->
           <!--开发环境-->
           <id>dev</id>
           <properties>
               <!--profiles.active是自定义的字段（名字随便起），自定义字段可以有多个-->
               <profiles.active>dev</profiles.active>
           </properties>
       </profile>
       <profile>
           <!--线上环境-->
           <id>prod</id>
           <properties>
               <profiles.active>prod</profiles.active>
           </properties>
           <activation>
               <activeByDefault>true</activeByDefault>
           </activation>
       </profile>
   </profiles>
```

### 9、maven 插件

1.  Maven 的核心仅仅定义了抽象的生命周期，具体的任务都是交由插件完成的。

2.  每个插件都能实现多个功能，每个功能就是一个插件目标 goal。

3.  Maven 的生命周期与插件目标相互绑定，以完成某个具体的构建任务，例如 compile 就是插件 maven-compiler-plugin 的一个插件目标。


常用插件：

```
maven-antrun-plugin

maven-archetype-plugin

maven-assembly-plugin

maven-dependency-plugin

maven-enforcer-plugin

maven-help-plugin

maven-release-plugin

maven-resources-plugin

maven-surefire-plugin

build-helper-maven-plugin

exec-maven-plugin

jetty-maven-plugin

versions-maven-plugin
```

### 10 环境变量

```
${basedir}表示项目根目录,即包含pom.xml文件的目录;

${version}表示项目版本;

${project.basedir}同${basedir};

${project.baseUri}表示项目文件地址;

${maven.build.timestamp}表示项目构件开始时间;

${maven.build.timestamp.format}表示属性${maven.build.timestamp}的展示格式,默认值为yyyyMMdd-HHmm,可自定义其格式,其类型可参考java.text.SimpleDateFormat。
${project.build.directory}表示主源码路径;

${project.build.sourceEncoding}表示主源码的编码格式;

${project.build.sourceDirectory}表示主源码路径;

${project.build.finalName}表示输出文件名称;

${project.version}表示项目版本,与${version}相同;

${project.xxx} 当前pom文件的任意节点的内容
${env.xxx} 获取系统环境变量。
${settings.xxx} 指代了settings.xml中对应元素的值。
```

### 11、Maven 依赖冲突的 2 个方法

1. **统一版本**

使用 dependencyManagement 进行版本锁定，dependencyManagement 可以统一管理项目的版本号，确保应用的各个项目的依赖和版本一致。

如果我们项目中只想使用 spring core 5.2.0 的包，pom.xml 可以改为如下

```
<dependencyManagement>
       <dependencies>
           <dependency>
               <groupId>org.springframework</groupId>
               <artifactId>spring-core</artifactId>
               <version>5.2.0.RELEASE</version>
           </dependency>
       </dependencies>
   </dependencyManagement>

   <dependencies>
       <dependency>
           <groupId>org.springframework</groupId>
           <artifactId>spring-context</artifactId>
           <version>5.2.7.RELEASE</version>
       </dependency>

       <dependency>
           <groupId>org.springframework</groupId>
           <artifactId>spring-aop</artifactId>
           <version>5.2.0.RELEASE</version>
       </dependency>

   </dependencies>
```

2. **排除依赖**

依赖查找的两个原则：

**使用路径近者优先原则**：直接依赖级别高于传递依赖。

**使用第一声明者优先原则**：谁先定义的就用谁的传递依赖，即在 pom.xml 文件自上而下，先声明的 jar 坐标，就先引用该 jar 的传递依赖。

Idea 可以安装 maven helper 插件，解决冲突。

maven helper 插件安装成功，点开 pom.xml 会发现多了一个 Dependency Analyzer 视图，如下上面按钮的图标含义如下

*   Conflicts（查看冲突）

*   All Dependencies as List（列表形式查看所有依赖）

*   All Dependencies as Tree（树形式查看所有依赖）


上图说明有 3 个 jar 存在冲突，点击冲突的 jar，可以查看和哪个 jar 产生冲突，如下图

![](https://mmbiz.qpic.cn/sz_mmbiz_png/qZiaNAjhV5icj0iatRqicGiaFX0SP1tBCJ6c4mBBUL3d5HR7Libib8JsYibMNB6LGRGfBvcPfZOQzrMcgjrrCfvwn8PagQ/640?wx_fmt=png)

点开 pom.xml，切换到 Dependency Analyzer 视图，选择 All Dependencies as Tree，点击要排除的 jar，右键会出现 Execlude 选项，如下

![](https://mmbiz.qpic.cn/sz_mmbiz_png/qZiaNAjhV5icj0iatRqicGiaFX0SP1tBCJ6c4EVCwjcmiah3dUlApHh1sXiaNbstOYgPYIu25mB09rlFh5G2icCIpXU4uw/640?wx_fmt=png)

### 总结：

Maven 是开发中常用的工具，很重要，所以尽可能的掌握。

原创打字不容易，点赞，转发，关注三连，关注我公众号：【香菜聊游戏】有更多福利哦

公众号

![](https://mmbiz.qpic.cn/sz_mmbiz_jpg/qZiaNAjhV5icj0iatRqicGiaFX0SP1tBCJ6c4vvySgV5ZFy8s3Z227sjYwGHthqZoUiaSiaOhC5bqB11PZqabJzW9uFBQ/640?wx_fmt=jpeg)

还不赶紧点赞！！！