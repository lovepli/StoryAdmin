![图像.jpeg](https://i.loli.net/2020/07/30/12mvwEgYzZ7ftcd.jpg)
# story-admin
 Springboot+Shiro+jwt+Redis+Mybatis 有效期内Token刷新方案

本项目为前后端分离的Web应用后端程序，采用技术框架如下：
# 后端技术
1. springboot v2.1.2.RELEASE
2. shiro v1.5.3
3. jwt
4. redis
5. mybatis-plus v3.1.2
6. MongoDB
7. mail
8. quartz
9. memcache
10. mysql v5.7

# 前端技术
1. Vue
2. Vuex
3. ElementUi
4. Axios
5. Es6

作者的博客：https://www.sundayfine.com/

使用jwt采用token有效期内刷新机制更新Token。
参考：1、story—admin 2、ruoyi 3、birdAdmin 开源项目

项目已实现功能包括：
1. 代办事项，日历
2. 字典管理
3. 系统管理
   -- 用户登录
   -- 用户管理
   -- 角色管理
   -- 权限管理
   -- 菜单管理
   -- 部门管理
   -- 系统首页公告，置顶，取消置顶，过期
   -- 附件文件上传、下载
   -- 表格文件导出PDF格式
   -- 表格文件的导入，导出功能(暂时未开发)
   -- 登录日志
   -- 操作日志，操作日志缓存到MongoDB
   -- 邮件功能 https://www.sundayfine.com/springboot-mail/
   -- 定时任务
   -- 天气详情，调用第三方接口
   -- 数据源druid监控
   -- Tomcat服务器监控
   -- swaggerAPI文档查看
   
### 项目说明：
    项目中数据库表都可以通过代码生成器生成基础的代码。应用的组件中mongoDB只存放了一些请求参数的日志，没有需求的可以直接移除。
    本地开发使用docker来配置开发环境，安装redis，mongoDB都是分分钟的事情。前端依旧采用了开源的vue-element-admin项目，
    并做了一些简单的修改，ElementUI的文档清晰明了，使用起来也是很方便。
    1.登录采用一张静态的图片做背景，并在其上增加了一些动态的效果，使得视觉效果更佳。当然图片什么的可以随意发挥。
    2.采用shiro做安全框架，使用jwt替换了以前常用的session+cookie。应用token有效期内自动刷新的方案，使用户无感知永久登录。
      文章链接：https://www.sundayfine.com/shiro-jwt/ 和 https://www.sundayfine.com/jwt-refresh-token/
      Shiro + JWT + Spring Boot Restful 简易教程 https://www.jianshu.com/p/f37f8c295057
      SpringBoot + Shiro + JWT集成Redis缓存(Jedis) https://blog.csdn.net/wang926454/article/details/82978632
    3.菜单与权限一样，都可以动态的方式配置。菜单与权限绑定，没有权限即无法看到菜单。权限可以控制到按钮级别，没有设计数据权限，
      开发者可以根据实际的业务场景来处理。
    4.使用mybatis-plus替换原来的mybatis，因为他提供了很多现成的基础功能，如代码生成，条件构造，分页插件，租户等功能，简化了
      不少重复的代码量，应用起来更加高效。
    5.集成了quartz，来完成基础的定时任务功能，如简单的定时数据备份等。对于单点部署的小项目来说已经足够，多个节点部署不想增加分
      布式任务调度框架的话，可采用redis锁的方式处理。
    6.在后端，依然使用统一的附件上传模块来保存附件，只需要继承一个基类，就可以获取到每次请求的附件信息，当然也提供了手动保存附件
      的注解，灵活多变。以前的文章中也写过。
    7.这里在前端使用了vue-lunar-full-calendar的组件实现日历功能，可以非常方便的实现一个便签一样的待办任务模块。
    

   

### 1. 登录功能的实现
     登录名/密码：admin/111111
     
     



#### 用户登录页
[![w8rs56.png](https://s1.ax1x.com/2020/09/09/w8rs56.png)](https://imgchr.com/i/w8rs56)
#### 首页图片
![au0AEt.png](https://s1.ax1x.com/2020/07/30/au0AEt.png)
![au07Pf.png](https://s1.ax1x.com/2020/07/30/au07Pf.png)
### 代办事项
![auBAsJ.png](https://s1.ax1x.com/2020/07/30/auBAsJ.png)
![auBDyQ.png](https://s1.ax1x.com/2020/07/30/auBDyQ.png)
### 字典管理
![auDUB9.png](https://s1.ax1x.com/2020/07/30/auDUB9.png)


### 遗留的问题
## 1、登录过程记住我功能存在缺陷，关于记住我之后的刷新时间机制，在jwtFilter过滤器中应该通过判断是否是记住我，来刷新时间
## 2、userContext不能获取用户的ID




