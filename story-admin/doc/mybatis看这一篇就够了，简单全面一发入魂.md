> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [blog.csdn.net](https://blog.csdn.net/vcj1009784814/article/details/106391982)

### 文章目录

*   [Mybatis](#Mybatis_1)
*   *   [概述](#_3)
*   [快速入门](#_19)
*   *   [原生开发示例](#_28)
*   [基于 Mapper 代理的示例](#Mapper_394)
*   [基于注解的示例](#_479)
*   [应用场景](#_659)
*   *   [主键返回](#_661)
*   [批量查询](#_716)
*   [动态 SQL](#SQL_748)
*   [缓存](#_887)
*   [关联查询](#_906)
*   [延迟加载](#_910)
*   [逆向工程](#_1004)
*   [PageHelper 分页插件](#PageHelper_1148)
*   [Mybatis Plus](#Mybatis_Plus_1257)

Mybatis
=======

概述
--

1.  mybatis 是什么？有什么特点？

    它是一款半自动的 ORM 持久层框架，具有较高的 SQL 灵活性，支持高级映射 (一对一，一对多)，动态 SQL，延迟加载和缓存等特性，但它的数据库无关性较低

    *   什么是 ORM？

        Object Relation Mapping，对象关系映射。对象指的是 Java 对象，关系指的是数据库中的关系模型，对象关系映射，指的就是在 Java 对象和数据库的关系模型之间建立一种对应关系，比如用一个 Java 的 Student 类，去对应数据库中的一张 student 表，类中的属性和表中的列一一对应。Student 类就对应 student 表，一个 Student 对象就对应 student 表中的一行数据

    *   为什么 mybatis 是半自动的 ORM 框架？

        用 mybatis 进行开发，需要手动编写 SQL 语句。而全自动的 ORM 框架，如 hibernate，则不需要编写 SQL 语句。用 hibernate 开发，只需要定义好 ORM 映射关系，就可以直接进行 CRUD 操作了。由于 mybatis 需要手写 SQL 语句，所以它有较高的灵活性，可以根据需要，自由地对 SQL 进行定制，也因为要手写 SQL，当要切换数据库时，SQL 语句可能就要重写，因为不同的数据库有不同的**方言** (Dialect)，所以 mybatis 的数据库无关性低。虽然 mybatis 需要手写 SQL，但相比 JDBC，它提供了输入映射和输出映射，可以很方便地进行 SQL 参数设置，以及结果集封装。并且还提供了**关联查询**和**动态 SQL** 等功能，极大地提升了开发的效率。并且它的学习成本也比 hibernate 低很多


快速入门
----

只需要通过如下几个步骤，即可用 mybatis 快速进行持久层的开发

1.  编写全局配置文件
2.  编写 mapper 映射文件
3.  加载全局配置文件，生成 SqlSessionFactory
4.  创建 SqlSession，调用 mapper 映射文件中的 SQL 语句来执行 CRUD 操作

### 原生开发示例

1.  在本地虚拟机 mysql 上创建一个库 yogurt，并在里面创建一张 student 表

    ![](https://imgconvert.csdnimg.cn/aHR0cDovL3Fhc2IxMXhxeS5ia3QuY2xvdWRkbi5jb20vaW1hZ2UtMjAyMDA1MjUyMTAzMDQzMDUucG5n?x-oss-process=image/format,png)

2.  打开 IDEA，创建一个 maven 项目

    ![](https://imgconvert.csdnimg.cn/aHR0cDovL3Fhc2IxMXhxeS5ia3QuY2xvdWRkbi5jb20vaW1hZ2UtMjAyMDA1MjUyMDQ4MTA5NjYucG5n?x-oss-process=image/format,png)

3.  导入依赖的 jar 包

    ```
    <dependencies>
            <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
                <version>5.1.10</version>
            </dependency>
            <dependency>
                <groupId>org.mybatis</groupId>
                <artifactId>mybatis</artifactId>
                <version>3.4.6</version>
            </dependency>
            <dependency>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
                <version>1.18.12</version>
                <scope>provided</scope>
            </dependency>
            <dependency>
                <groupId>junit</groupId>
                <artifactId>junit</artifactId>
                <version>4.10</version>
                <scope>test</scope>
            </dependency>
        </dependencies>
    ```

4.  创建一个 po 类

    ```
    package com.yogurt.po;
    
    import lombok.*;
    
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public class Student {
    
    	private Integer id;
    
    	private String name;
    
    	private Integer score;
    
    	private Integer age;
    
    	private Integer gender;
    
    }
    ```

5.  编写 mapper 映射文件 (编写 SQL)

    ```
    <!-- StudentMapper.xml -->
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE mapper
            PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
            "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
    
    <mapper namespace="test">
        <select resultType="com.yogurt.po.Student">
            SELECT * FROM student;
        </select>
    
        <insert parameterType="com.yogurt.po.Student">
            INSERT INTO student (name,score,age,gender) VALUES (#{name},#{score},#{age},#{gender});
        </insert>
        
        <delete parameterType="int">
            DELETE FROM student WHERE id = #{id};
        </delete>
    </mapper>
    ```

6.  编写数据源 properties 文件

    ```
    db.url=jdbc:mysql://192.168.183.129:3306/yogurt?characterEncoding=utf8
    db.user=root
    db.password=root
    db.driver=com.mysql.jdbc.Driver
    ```

7.  编写全局配置文件 (主要是配置数据源信息)

    ```
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE configuration
            PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
            "http://mybatis.org/dtd/mybatis-3-config.dtd">
    <configuration>
        <!-- 配置文件信息 -->
        <properties resource="properties/db.properties"></properties>
    
        <environments default="development">
            <environment>
                <transactionManager type="JDBC"/>
                <dataSource type="POOLED">
                    <!-- 从配置文件中加载属性 -->
                    <property ${db.driver}"/>
                    <property ${db.url}"/>
                    <property ${db.user}"/>
                    <property ${db.password}"/>
                </dataSource>
            </environment>
        </environments>
    
        <mappers>
            <!-- 加载前面编写的SQL语句的文件 -->
            <mapper resource="StudentMapper.xml"/>
        </mappers>
    
    </configuration>
    ```

8.  编写 dao 类

    ```
    package com.yogurt.dao;
    
    import com.yogurt.po.Student;
    import org.apache.ibatis.io.Resources;
    import org.apache.ibatis.session.SqlSession;
    import org.apache.ibatis.session.SqlSessionFactory;
    import org.apache.ibatis.session.SqlSessionFactoryBuilder;
    
    import java.io.IOException;
    import java.io.InputStream;
    import java.util.List;
    
    public class StudentDao {
    
    	private SqlSessionFactory sqlSessionFactory;
    
    	public StudentDao(String configPath) throws IOException {
    		InputStream inputStream = Resources.getResourceAsStream(configPath);
    		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    	}
    
    	public List<Student> findAll() {
    		SqlSession sqlSession = sqlSessionFactory.openSession();
    		List<Student> studentList = sqlSession.selectList("findAll");
    		sqlSession.close();
    		return studentList;
    	}
    
    	public int addStudent(Student student) {
    		SqlSession sqlSession = sqlSessionFactory.openSession();
    		int rowsAffected = sqlSession.insert("insert", student);
    		sqlSession.commit();
    		sqlSession.close();
    		return rowsAffected;
    	}
    
    	public int deleteStudent(int id) {
    		SqlSession sqlSession = sqlSessionFactory.openSession();
    		int rowsAffected = sqlSession.delete("delete",id);
    		sqlSession.commit();
    		sqlSession.close();
    		return rowsAffected;
    	}
    }
    ```

9.  测试

    ```
    public class SimpleTest {
    
    	private StudentDao studentDao;
    
    	@Before
    	public void init() throws IOException {
    		studentDao = new StudentDao("mybatis-config.xml");
    	}
    
    	@Test
    	public void insertTest() {
    		Student student = new Student();
    		student.setName("yogurt");
    		student.setAge(24);
    		student.setGender(1);
    		student.setScore(100);
    		studentDao.addStudent(student);
    	}
    
    	@Test
    	public void findAllTest() {
    		List<Student> all = studentDao.findAll();
    		all.forEach(System.out::println);
    	}
    }
    ```

    ![](https://imgconvert.csdnimg.cn/aHR0cDovL3Fhc2IxMXhxeS5ia3QuY2xvdWRkbi5jb20vaW1hZ2UtMjAyMDA1MjUyMTQ0MjI1MjkucG5n?x-oss-process=image/format,png)


**总结**：

1.  编写 mapper.xml，书写 SQL，并定义好 SQL 的输入参数，和输出参数
2.  编写全局配置文件，配置数据源，以及要加载的 mapper.xml 文件
3.  通过全局配置文件，创建 SqlSessionFactory
4.  每次进行 CRUD 时，通过 SqlSessionFactory 创建一个 SqlSession
5.  调用 SqlSession 上的`selectOne`，`selectList`，`insert`，`delete`，`update`等方法，传入 mapper.xml 中 SQL 标签的 id，以及输入参数

**注意要点**

1.  全局配置文件中，各个标签要按照如下顺序进行配置，因为 mybatis 加载配置文件的源码中是按照这个顺序进行解析的

    ```
    <configuration>
    	<!-- 配置顺序如下
         properties  
    
         settings
    
         typeAliases
    
         typeHandlers
    
         objectFactory
    
         plugins
    
         environments
            environment
                transactionManager
                dataSource
    
         mappers
         -->
    </configuration>
    ```

    各个子标签说明如下

    *   `<properties>`

        一般将数据源的信息单独放在一个 properties 文件中, 然后用这个标签引入, 在下面 environment 标签中，就可以用`${}`占位符快速获取数据源的信息

    *   `<settings>`

        用来开启或关闭 mybatis 的一些特性，比如可以用`<setting />`来开启延迟加载，可以用`<settings />`来开启二级缓存

    *   `<typeAliases>`

        在 mapper.xml 中需要使用`parameterType`和`resultType`属性来配置 SQL 语句的输入参数类型和输出参数类型，类必须要写上全限定名，比如一个 SQL 的返回值映射为 Student 类，则`resultType`属性要写`com.yogurt.po.Student`，这太长了，所以可以用别名来简化书写，比如

        ```
        <typeAliases>
            <typeAlias type="com.yogurt.po.Student" alias="student"/>
        </typeAliases>
        ```

        之后就可以在`resultType`上直接写`student`，mybatis 会根据别名配置自动找到对应的类。

        当然，如果想要一次性给某个包下的所有类设置别名，可以用如下的方式

        ```
        <typeAliases>
           <package />
        </typeAliases>
        ```

        如此，指定包下的所有类，都会以简单类名的小写形式，作为它的别名

        另外，对于基本的 Java 类型 -> 8 大基本类型以及包装类，以及 String 类型，mybatis 提供了默认的别名，别名为其简单类名的小写，比如原本需要写`java.lang.String`，其实可以简写为`string`

    *   `<typeHandlers>`

        用于处理 Java 类型和 Jdbc 类型之间的转换，mybatis 有许多内置的 TypeHandler，比如 StringTypeHandler，会处理 Java 类型 String 和 Jdbc 类型 CHAR 和 VARCHAR。这个标签用的不多

    *   `<objectFactory>`

        mybatis 会根据`resultType`或`resultMap`的属性来将查询得到的结果封装成对应的 Java 类，它有一个默认的 DefaultObjectFactory，用于创建对象实例，这个标签用的也不多

    *   `<plugins>`

        可以用来配置 mybatis 的插件，比如在开发中经常需要对查询结果进行分页，就需要用到 pageHelper 分页插件，这些插件就是通过这个标签进行配置的。在 mybatis 底层，运用了责任链模式 + 动态代理去实现插件的功能

        ```
        <!-- PageHelper 分页插件 -->
        <plugins>
          <plugin interceptor="com.github.pagehelper.PageInterceptor">
             <property />
          </plugin>
        </plugins>
        ```

    *   `<environments>`

        用来配置数据源

    *   `<mappers>`

        用来配置 mapper.xml 映射文件，这些 xml 文件里都是 SQL 语句

2.  mapper.xml 的 SQL 语句中的占位符`${}`和`#{}`

    一般会采用`#{}`，`#{}`在 mybatis 中，最后会被解析为`?`，其实就是 Jdbc 的 PreparedStatement 中的`?`占位符，它有预编译的过程，会对输入参数进行类型解析 (如果入参是 String 类型，设置参数时会自动加上引号)，可以防止 SQL 注入，如果`parameterType`属性指定的入参类型是简单类型的话 (简单类型指的是 8 种 java 原始类型再加一个 String)，`#{}`中的变量名可以任意，如果入参类型是 pojo，比如是 Student 类

    ```
    public class Student{
    	private String name;
        private Integer age;
        //setter/getter
    }
    ```

    那么`#{name}`表示取入参对象 Student 中的 name 属性，`#{age}`表示取 age 属性，这个过程是通过反射来做的，这不同于`${}`，`${}`取对象的属性使用的是 OGNL(Object Graph Navigation Language) 表达式

    而`${}`，一般会用在模糊查询的情景，比如`SELECT * FROM student WHERE name like '%${name}%';`

    它的处理阶段在`#{}`之前，它不会做参数类型解析，而仅仅是做了字符串的拼接，若入参的 Student 对象的 name 属性为 zhangsan，则上面那条 SQL 最终被解析为`SELECT * FROM student WHERE name like '%zhangsan%';`

    而如果此时用的是`SELECT * FROM student WHERE name like '%#{name}%';` 这条 SQL 最终就会变成

    `SELECT * FROM student WHERE name like '%'zhangsan'%';` 所以模糊查询只能用`${}`, 虽然普通的入参也可以用`${}`, 但由于`${}`不会做类型解析，就存在 SQL 注入的风险，比如

    `SELECT * FROM user WHERE name = '${name}' AND password = '${password}'`

    我可以让一个 user 对象的 password 属性为`'OR '1' = '1`，最终的 SQL 就变成了

    `SELECT * FROM user WHERE name = 'yogurt' AND password = ''OR '1' = '1'`，因为`OR '1' = '1'`恒成立，这样攻击者在不需要知道用户名和密码的情况下，也能够完成登录验证

    另外, 对于 pojo 的入参，`${}`中获取对象属性的语法和`#{}`几乎一样，但`${}`在 mybatis 底层是通过 OGNL 表达式语言进行处理的，这跟`#{}`的反射处理有所不同

    对于简单类型 (8 种 java 原始类型再加一个 String) 的入参，`${}`中参数的名字必须是`value`，例子如下

    ```
    <select parameterType="string" resultType="int">
            SELECT count(1) FROM `user` WHERE name like '%${value}%'
    </select>
    ```

    为什么简单类型的变量名必须为 value 呢？因为 mybatis 源码中写死的 value，哈哈

    ![](https://imgconvert.csdnimg.cn/aHR0cDovL3Fhc2IxMXhxeS5ia3QuY2xvdWRkbi5jb20vaW1hZ2UtMjAyMDA1MjUyMzIxNDQxNjUucG5n?x-oss-process=image/format,png)


上面其实是比较原始的开发方式，我们需要编写 dao 类，针对 mapper.xml 中的每个 SQL 标签，做一次封装，SQL 标签的 id 要以**字符串**的形式传递给 SqlSession 的相关方法，容易出错，非常不方便；为了简化开发，mybatis 提供了 mapper 接口代理的开发方式，不需要再编写 dao 类，只需要编写一个 mapper 接口，一个 mapper 的接口和一个 mapper.xml 相对应，只需要调用 SqlSession 对象上的`getMapper()`，传入 mapper 接口的 class 信息，即可获得一个 mapper 代理对象，直接调用 mapper 接口中的方法，即相当于调用 mapper.xml 中的各个 SQL 标签，此时就不需要指定 SQL 标签的 id 字符串了，mapper 接口中的一个方法，就对应了 mapper.xml 中的一个 SQL 标签

### 基于 Mapper 代理的示例

全局配置文件和 mapper.xml 文件是最基本的配置，仍然需要。不过，这次我们不编写 dao 类，我们直接创建一个 mapper 接口

```
package com.yogurt.mapper;

import com.yogurt.po.Student;

import java.util.List;

public interface StudentMapper {

	List<Student> findAll();

	int insert(Student student);

	int delete(Integer id);

	List<Student> findByName(String value);
}
```

而我们的 mapper.xml 文件如下

```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.yogurt.mapper.StudentMapper">
    <select id="findAll" resultType="com.yogurt.po.Student">
        SELECT * FROM student;
    </select>

    <insert id="insert" parameterType="com.yogurt.po.Student">
        INSERT INTO student (name,score,age,gender) VALUES (#{name},#{score},#{age},#{gender});
    </insert>

    <delete id="delete" parameterType="int">
        DELETE FROM student WHERE id = #{id};
    </delete>

    <select id="findByName" parameterType="string" resultType="student">
        SELECT * FROM student WHERE name like '%${value}%';
    </select>
</mapper>
```

mapper 接口和 mapper.xml 之间需要遵循一定规则，才能成功的让 mybatis 将 mapper 接口和 mapper.xml 绑定起来

1.  mapper 接口的全限定名，要和 mapper.xml 的 namespace 属性一致
2.  mapper 接口中的方法名要和 mapper.xml 中的 SQL 标签的 id 一致
3.  mapper 接口中的方法入参类型，要和 mapper.xml 中 SQL 语句的入参类型一致
4.  mapper 接口中的方法出参类型，要和 mapper.xml 中 SQL 语句的返回值类型一致

测试代码如下

```
public class MapperProxyTest {
	private SqlSessionFactory sqlSessionFactory;

	@Before
	public void init() throws IOException {
		InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
	}

	@Test
	public void test() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
		List<Student> studentList = mapper.findAll();
		studentList.forEach(System.out::println);
	}
}
```

结果如下

![](https://imgconvert.csdnimg.cn/aHR0cDovL3Fhc2IxMXhxeS5ia3QuY2xvdWRkbi5jb20vaW1hZ2UtMjAyMDA1MjUyMzQ5NDU4NjUucG5n?x-oss-process=image/format,png)

这个 mapper 接口，mybatis 会自动找到对应的 mapper.xml，然后对 mapper 接口使用动态代理的方式生成一个代理类

### 基于注解的示例

如果实在看 xml 配置文件不顺眼，则可以考虑使用注解的开发方式，不过注解的开发方式，会将 SQL 语句写到代码文件中，后续的维护性和扩展性不是很好（如果想修改 SQL 语句，就得改代码，得重新打包部署，而如果用 xml 方式，则只需要修改 xml，用新的 xml 取替换旧的 xml 即可）

使用注解的开发方式，也还是得有一个全局配置的 xml 文件，不过 mapper.xml 就可以省掉了，具体操作只用 2 步，如下

1.  创建一个 Mapper 接口

    ```
    package com.yogurt.mapper;
    import com.yogurt.po.Student;
    import org.apache.ibatis.annotations.Insert;
    import org.apache.ibatis.annotations.Select;
    import java.util.List;
    
    public interface PureStudentMapper {
    
    	@Select("SELECT * FROM student")
    	List<Student> findAll();
    
    	@Insert("INSERT INTO student (name,age,score,gender) VALUES (#{name},#{age},#{score},#{gender})")
    	int insert(Student student);
    }
    ```

2.  在全局配置文件中修改`<mappers>`标签，直接指定加载这个类

    ```
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE configuration
            PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
            "http://mybatis.org/dtd/mybatis-3-config.dtd">
    <configuration>
        <properties resource="properties/db.properties"></properties>
        <typeAliases>
            <package />
        </typeAliases>
        <environments default="development">
            <environment>
                <transactionManager type="JDBC"/>
                <dataSource type="POOLED">
                    <property ${db.driver}"/>
                    <property ${db.url}"/>
                    <property ${db.user}"/>
                    <property ${db.password}"/>
                </dataSource>
            </environment>
        </environments>
    
        <mappers>
            <mapper/>
        </mappers>
    
    </configuration>
    ```


测试代码如下

```
public class PureMapperTest {

	private SqlSessionFactory sqlSessionFactory;

	@Before
	public void init() throws IOException {
		InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	@Test
	public void test() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		PureStudentMapper mapper = sqlSession.getMapper(PureStudentMapper.class);
		mapper.insert(new Student(10,"Tomcat",120,60,0));
        sqlSession.commit();
		List<Student> studentList = mapper.findAll();
		studentList.forEach(System.out::println);
	}
}
```

结果如下

![](https://imgconvert.csdnimg.cn/aHR0cDovL3Fhc2IxMXhxeS5ia3QuY2xvdWRkbi5jb20vaW1hZ2UtMjAyMDA1MjYwMDA2NTA5MjAucG5n?x-oss-process=image/format,png)  
注：当使用注解开发时，若需要传入多个参数，可以结合`@Param`注解，示例如下

```
package org.mybatis.demo.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mybatis.demo.po.Student;

import java.util.List;

public interface PureStudentMapper {

	@Select("SELECT * FROM student WHERE name like '%${name}%' AND major like '%${major}%'")
	List<Student> find(@Param("name") String name, @Param("major") String major);
}
```

`@Param`标签会被 mybatis 处理并封装成一个 Map 对象，比如上面的示例中，实际传入的参数是一个 Map 对象，`@Param`标签帮忙向 Map 中设置了值，即它做了

```
Map<String,Object> map = new HashMap<>();
map.put("name", name);
map.put("major",major);
```

将方法形参中的`name`和`major`放到了 map 对象中，所以在`@Select`标签中可以用`${name}`和`${major}`取出 map 对象中的值。  
--------------------(我是分割线)

上面我们见到了在全局配置文件中，两种配置 mapper 的方式，分别是

```
<!-- 在mapper接口中使用注解 -->
<mappers>
    <mapper class="com.yogurt.mapper.PureStudentMapper"/>
</mappers>

<!-- 普通加载xml -->
<mappers>
    <mapper resource="StudentMapper.xml"/>
</mappers>
```

而在实际工作中，一般我们会将一张表的 SQL 操作封装在一个 mapper.xml 中，可能有许多张表需要操作，那么我们是不是要在`<mappers>`标签下写多个`<mapper>`标签呢？其实不用，还有第三种加载 mapper 的方法，使用`<package>`标签

```
<mappers>
    <package />
</mappers>
```

这样就会自动加载`com.yogurt.mapper`包下的所有 mapper，这种方式需要将 mapper 接口文件和 mapper.xml 文件都放在`com.yogurt.mapper`包下，且接口文件和 xml 文件的文件名要一致。注意，在 IDEA 的 maven 开发环境下，maven 中还需配置`<resources>`标签，否则 maven 打包不会将 java 源码目录下的 xml 文件打包进去，见下文

三种加载 mapper 的方式总结

*   `<mapper resource="" />`

    加载普通的 xml 文件，传入 xml 的相对路径 (相对于类路径)

*   `<mapper />`

    使用 mapper 接口的全限定名来加载，若 mapper 接口采用注解方式，则不需要 xml; 若 mapper 接口没有采用注解方式，则 mapper 接口和 xml 文件的名称要相同，且在同一个目录

*   `<package />`

    扫描指定包下的所有 mapper，若 mapper 接口采用注解方式，则不需要 xml; 若 mapper 接口没有采用注解方式，则 mapper 接口和 xml 文件的名称要相同，且在同一目录


注意：用后两种方式加载 mapper 接口和 mapper.xml 映射文件时，可能会报错

![](https://imgconvert.csdnimg.cn/aHR0cDovL3Fhc2IxMXhxeS5ia3QuY2xvdWRkbi5jb20vaW1hZ2UtMjAyMDA1MjcyMDU2NTc0NTcucG5n?x-oss-process=image/format,png)

仔细检查了一下，mapper 接口文件和 xml 映射文件确实放在了同一个目录下，而且文件名一致，xml 映射文件的 namespace 也和 mapper 接口的全限定名对的上。为什么会这样呢？  
![](https://img-blog.csdnimg.cn/20200527222736840.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3ZjajEwMDk3ODQ4MTQ=,size_16,color_FFFFFF,t_70)

其实是因为，对于`src/main/java` 源码目录下的文件，maven 打包时只会将该目录下的 java 文件打包，而其他类型的文件都不会被打包进去，去工程目录的 target 目录下看看 maven 构建后生成的文件

![](https://imgconvert.csdnimg.cn/aHR0cDovL3Fhc2IxMXhxeS5ia3QuY2xvdWRkbi5jb20vaW1hZ2UtMjAyMDA1MjcyMTA5NTI0MjMucG5n?x-oss-process=image/format,png)

我们需要在 pom.xml 中的`<build>` 标签下 添加`<resources>` 标签，指定打包时要将 xml 文件打包进去

```
<build>
	<resources>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.xml</include>
                </includes>
            </resource>
        </resources>
</build>
```

此时再用 maven 进行打包，看到对应目录下有了 xml 映射文件（**特别注意**，这里配置了`pom.xml`下的`resource`标签后，可能会引发一些问题，例如原本`src/main/resources`资源目录下的文件没有被打包进来，参考我的这篇文章 [maven 打包时的资源文件问题](https://blog.csdn.net/vcj1009784814/article/details/115118255)）

![](https://imgconvert.csdnimg.cn/aHR0cDovL3Fhc2IxMXhxeS5ia3QuY2xvdWRkbi5jb20vaW1hZ2UtMjAyMDA1MjcyMTA4MzU0MDcucG5n?x-oss-process=image/format,png)

此时再运行单元测试，就能正常得到结果了

应用场景
----

### 主键返回

通常我们会将数据库表的主键 id 设为自增。在插入一条记录时，我们不设置其主键 id，而让数据库自动生成该条记录的主键 id，那么在插入一条记录后，如何得到数据库自动生成的这条记录的主键 id 呢？有两种方式

1.  使用`useGeneratedKeys`和`keyProperty`属性

    ```
    <insert parameterType="com.yogurt.po.Student" useGeneratedKeys="true" keyProperty="id">
            INSERT INTO student (name,score,age,gender) VALUES (#{name},#{score},#{age},#{gender});
        </insert>
    ```

2.  使用`<selectKey>`子标签

    ```
    <insert parameterType="com.yogurt.po.Student">
            INSERT INTO student (name,score,age,gender) VALUES (#{name},#{score},#{age},#{gender});
            <selectKey keyProperty="id" order="AFTER" resultType="int" >
                SELECT LAST_INSERT_ID();
            </selectKey>
        </insert>
    ```

    如果使用的是 mysql 这样的支持自增主键的数据库，可以简单的使用第一种方式；对于不支持自增主键的数据库，如 oracle，则没有主键返回这一概念，而需要在插入之前先生成一个主键。此时可以用`<selectKey>`标签，设置其`order`属性为`BEFORE`，并在标签体内写上生成主键的 SQL 语句，这样在插入之前，会先处理`<selectKey>`，生成主键，再执行真正的插入操作。

    `<selectKey>`标签其实就是一条 SQL，这条 SQL 的执行，可以放在**主 SQL** 执行之前或之后，并且会将其执行得到的结果封装到入参的 Java 对象的指定属性上。注意`<selectKey>`子标签只能用在`<insert>`和`<update>`标签中。上面的`LAST_INSERT_ID()`实际上是 MySQL 提供的一个函数，可以用来获取最近插入或更新的记录的主键 id。


测试代码如下

```
public class MapperProxyTest {
	private SqlSessionFactory sqlSessionFactory;

	@Before
	public void init() throws IOException {
		InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
	}

	@Test
	public void test() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
		Student student = new Student(-1, "Podman", 130, 15, 0);
		mapper.insert(student);
		sqlSession.commit();
		System.out.println(student.getId());
	}
}
```

结果如下

![](https://imgconvert.csdnimg.cn/aHR0cDovL3Fhc2IxMXhxeS5ia3QuY2xvdWRkbi5jb20vaW1hZ2UtMjAyMDA1MjYyMDQ5NTcyMDcucG5n?x-oss-process=image/format,png)

### 批量查询

主要是动态 SQL 标签的使用，注意如果`parameterType`是`List`的话，则在标签体内引用这个`List`，只能用变量名`list`，如果`parameterType`是数组，则只能用变量名`array`

```
<select id="batchFind" resultType="student" parameterType="java.util.List">
        SELECT * FROM student
        <where>
            <if test="list != null and list.size() > 0">
                AND id in
                <foreach collection="list" item="id" open="(" separator="," close=")">
                    #{id}
                </foreach>
            </if>
        </where>
</select>
```

```
@Test
	public void testBatchQuery() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
		List<Student> students = mapper.batchFind(Arrays.asList(1, 2, 3, 7, 9));
		students.forEach(System.out::println);
	}
```

结果

![](https://imgconvert.csdnimg.cn/aHR0cDovL3Fhc2IxMXhxeS5ia3QuY2xvdWRkbi5jb20vaW1hZ2UtMjAyMDA1MjYyMTA2NDEzMDAucG5n?x-oss-process=image/format,png)

### 动态 SQL

可以根据具体的参数条件，来对 SQL 语句进行动态拼接。

比如在以前的开发中，由于不确定查询参数是否存在，许多人会使用类似于`where 1 = 1` 来作为前缀，然后后面用`AND` 拼接要查询的参数，这样，就算要查询的参数为空，也能够正确执行查询，如果不加`1 = 1`，则如果查询参数为空，SQL 语句就会变成`SELECT * FROM student where`，SQL 不合法。

mybatis 里的动态标签主要有

*   `if`

    ```
    <!-- 示例 -->
    <select resultType="student" parameterType="student">
            SELECT * FROM student WHERE age >= 18
            <if test="name != null and name != ''">
                AND name like '%${name}%'
            </if>
    </select>
    ```

    当满足 test 条件时，才会将`<if>`标签内的 SQL 语句拼接上去

*   `choose`

    ```
    <!-- choose 和 when , otherwise 是配套标签 
    类似于java中的switch，只会选中满足条件的一个
    -->
    <select
         resultType="Blog">
      SELECT * FROM BLOG WHERE state = ‘ACTIVE’
      <choose>
        <when test="title != null">
          AND title like #{title}
        </when>
        <when test="author != null and author.name != null">
          AND author_name like #{author.name}
        </when>
        <otherwise>
          AND featured = 1
        </otherwise>
      </choose>
    </select>
    ```

*   `trim`

    *   `where`

        `<where>`标签只会在至少有一个子元素返回了 SQL 语句时，才会向 SQL 语句中添加 WHERE，并且如果 WHERE 之后是以 AND 或 OR 开头，会自动将其删掉

        ```
        <select
             resultType="Blog">
          SELECT * FROM BLOG
          <where>
            <if test="state != null">
                 state = #{state}
            </if>
            <if test="title != null">
                AND title like #{title}
            </if>
            <if test="author != null and author.name != null">
                AND author_name like #{author.name}
            </if>
          </where>
        </select>
        ```

        `<where>`标签可以用`<trim>`标签代替

        ```
        <trim prefix="WHERE" prefixOverrides="AND | OR">
           ...
        </trim>
        ```

    *   `set`

        在至少有一个子元素返回了 SQL 语句时，才会向 SQL 语句中添加 SET，并且如果 SET 之后是以`,`开头的话，会自动将其删掉

        `<set>`标签相当于如下的`<trim>`标签

        ```
        <trim prefix="SET" prefixOverrides=",">
           ...
        </trim>
        ```


    可以通过`<trim>`标签更加灵活地对 SQL 进行定制
    
    实际上在 mybatis 源码，也能看到 trim 与 set,where 标签的父子关系
    
    ![](https://imgconvert.csdnimg.cn/aHR0cDovL3Fhc2IxMXhxeS5ia3QuY2xvdWRkbi5jb20vaW1hZ2UtMjAyMDA1MjYyMTM0NTU3NzMucG5n?x-oss-process=image/format,png)

*   `foreach`

    用来做迭代拼接的，通常会与 SQL 语句中的`IN`查询条件结合使用，注意，到 parameterType 为 List（链表）或者 Array（数组），后面在引用时，参数名必须为 list 或者 array。如在 foreach 标签中，collection 属性则为需要迭代的集合，由于入参是个 List，所以参数名必须为 list

    ```
    <select resultType="student" parameterType="list">
            SELECT * FROM student WHERE id in
            <foreach collection="list" item="item" open="(" separator="," close=")">
              #{item}
            </foreach>
    </select>
    ```

*   `sql`

    可将重复的 SQL 片段提取出来，然后在需要的地方，使用`<include>`标签进行引用

    ```
    <select parameterType="user" resultType="user">
    	SELECT * FROM user
    	<include refid="whereClause"/>
    </select>
    
    <sql>
         <where>
             <if test user != null>
             	AND username like '%${user.name}%'
             </if>
         </where>
    </sql>
    ```

*   `bind`

    mybatis 的动态 SQL 都是用 OGNL 表达式进行解析的，如果需要创建 OGNL 表达式以外的变量，可以用 bind 标签

    ```
    <select resultType="Blog">
      <bind  />
      SELECT * FROM BLOG
      WHERE title LIKE #{pattern}
    </select>
    ```


### 缓存

*   一级缓存

    默认开启，同一个 SqlSesion 级别共享的缓存，在一个 SqlSession 的生命周期内，执行 2 次相同的 SQL 查询，则第二次 SQL 查询会直接取缓存的数据，而不走数据库，当然，若第一次和第二次相同的 SQL 查询之间，执行了 DML(INSERT/UPDATE/DELETE)，则一级缓存会被清空，第二次查询相同 SQL 仍然会走数据库

    一级缓存在下面情况会被清除

    *   在同一个 SqlSession 下执行**增删改**操作时（不必提交），会清除一级缓存
    *   SqlSession 提交或关闭时（关闭时会自动提交），会清除一级缓存
    *   对 mapper.xml 中的某个 CRUD 标签，设置属性`flushCache=true`，这样会导致该 MappedStatement 的一级缓存，二级缓存都失效（一个 CRUD 标签在 mybatis 中会被封装成一个 MappedStatement）
    *   在全局配置文件中设置 `<setting />`，这样会使一级缓存失效，二级缓存不受影响
*   二级缓存

    默认关闭，可通过全局配置文件中的`<settings />`开启二级缓存总开关，然后在某个具体的 mapper.xml 中增加`<cache />`，即开启了该 mapper.xml 的二级缓存。二级缓存是 mapper 级别的缓存，粒度比一级缓存大，多个 SqlSession 可以共享同一个 mapper 的二级缓存。注意开启二级缓存后，SqlSession 需要提交，查询的数据才会被刷新到二级缓存当中


缓存的详细分析可以参考我之前的文章 => [极简 mybatis 缓存](https://blog.csdn.net/vcj1009784814/article/details/102978287)

### 关联查询

使用`<resultMap>` 标签以及`<association>`和`<collection>` 子标签，进行关联查询，比较简单，不多说

### 延迟加载

延迟加载是结合关联查询进行应用的。也就是说，只在`<association>`和`<collection>` 标签上起作用

对于关联查询，若不采用延迟加载策略，而是一次性将关联的从信息都查询出来，则在主信息比较多的情况下，会产生 **N+1 问题**，导致性能降低。比如用户信息和订单信息是一对多的关系，在查询用户信息时，设置了关联查询订单信息，如不采用延迟加载策略，假设共有 100 个用户，则我们查这 100 个用户的基本信息只需要一次 SQL 查询

```
select * from user;
```

若开启了关联查询，且不是延迟加载，则对于这 100 个用户，会发出 100 条 SQL 去查用户对应的订单信息，这样会造成不必要的性能开销（其实我认为称之为 1+N 问题更为合适）

```
select * from orders where u_id = 1;
select * from orders where u_id = 2;
....
select * from orders where u_id = 100;
```

当我们可能只关心 id=3 的用户的订单信息，则很多的关联信息是无用的，于是，采用延迟加载策略，可以按需加载从信息，在需要某个主信息对应的从信息时，再发送 SQL 去执行查询，而不是一次性全部查出来，这样能很好的提升性能。

另外，针对 N+1 问题，除了采用延迟加载的策略按需进行关联查询。如果在某些场景下，确实需要查询所有主信息关联的从信息。在上面的例子中，就是如果确实需要把这 100 个用户关联的订单信息全部查询出来，那怎么办呢？这里提供 2 个解决思路。

1 是采用**连接查询**，只使用 1 条 SQL 即可，如下

```
select * from user as u left join orders as o on u.id = o.u_id;
```

但使用连接查询查出来的结果是两表的笛卡尔积，还需要自行进行数据的分组处理

2 是使用两个步骤来完成，先执行一条 SQL，查出全部的用户信息，并把用户的 id 放在一个集合中，然后第二条 SQL 采用`IN`关键字查询即可。这种方式也可以简化为子查询，如下

```
select * from orders where u_id in (select id from user);
```

现在说回来，mybatis 的延迟加载默认是关闭的，可以通过全局配置文件中的`<setting />`来开启，开启后，所有的 SELECT 查询，若有关联对象，都会采用延迟加载的策略。当然，也可以对指定的某个 CRUD 标签单独禁用延迟加载策略，通过设置 SELECT 标签中的`fetchType=eager`，则可以关闭该标签的延迟加载。

(还有一个侵入式延迟加载的概念，在配置文件中通过`<setting >`来开启，大概是说，访问主对象中的主信息时，就会触发延迟加载，将从信息查询上来，这其实并不是真正意义的延迟加载，真正意义上的延迟加载应该是访问主对象中的从信息时，才触发延迟加载，去加载从信息，侵入式延迟加载默认是关闭的，一般情况下可以不用管他)

注意，延迟加载在关联查询的场景下才有意义。需要配合`<resultMap>`标签下的`<association>`和`<collecction>` 标签使用

```
<!-- StudentMapper.xml -->
<resultMap id="studentExt" type="com.yogurt.po.StudentExt">
        <result property="id" column="id"/>
        <result property="name" column="name"/>
        <result property="score" column="score"/>
        <result property="age" column="age"/>
        <result property="gender" column="gender"/>
		<!-- 当延迟加载总开关开启时，resultMap下的association和collection标签中，若通过select属性指定嵌套查询的SQL，则其fetchType默认是lazy的，当在延迟加载总开关开启时，需要对个别的关联查询禁用延迟加载时，才有必要配置fetchType = eager -->
    	<!--
 		column用于指定用于关联查询的列
		property用于指定要封装到StudentExt中的哪个属性
		javaType用于指定关联查询得到的对象
		select用于指定关联查询时,调用的是哪一个DQL
		-->
        <association property="clazz" javaType="com.yogurt.po.Clazz" column="class_id"
                     select="com.yogurt.mapper.ClassMapper.findById" fetchType="lazy"/>

    </resultMap>

    <select id="findLazy" parameterType="string" resultMap="studentExt">
        SELECT * FROM student WHERE name like '%${value}%';
    </select>
```

```
<!-- com.yogurt.mapper.ClassMapper -->
<select id="findById" parameterType="int" resultType="com.yogurt.po.Clazz">
        SELECT * FROM class WHERE id = #{id}
</select>
```

```
/** 用于封装关联查询的对象 **/
public class StudentExt{

	private Integer id;

	private String name;

	private Integer score;

	private Integer age;

	private Integer gender;

    /** 关联对象 **/
	private Clazz clazz;
    
   	//getter/setter
}
```

### 逆向工程

mybatis 官方提供了 mapper 自动生成工具 mybatis-generator-core 来针对单表，生成 PO 类，以及 Mapper 接口和 mapper.xml 映射文件。针对单表，可以不需要再手动编写 xml 配置文件和 mapper 接口文件了，非常方便。美中不足的是它不支持生成关联查询。一般做关联查询，就自己单独写 SQL 就好了。

基于 IDEA 的 mybatis 逆向工程操作步骤如下

1.  配置 maven 插件

    ```
    <build>
            <plugins>
                <plugin>
                    <groupId>org.mybatis.generator</groupId>
                    <artifactId>mybatis-generator-maven-plugin</artifactId>
                    <version>1.3.7</version>
                    <configuration>
                        <!-- 输出日志 -->
                        <verbose>true</verbose>
                        <overwrite>true</overwrite>
                    </configuration>
                </plugin>
            </plugins>
        </build>
    ```

2.  在 resources 目录下创建名为 generatorConfig.xml 的配置文件

    ![](https://imgconvert.csdnimg.cn/aHR0cDovL3Fhc2IxMXhxeS5ia3QuY2xvdWRkbi5jb20vaW1hZ2UtMjAyMDA1MjcyMDM1NTY3NjYucG5n?x-oss-process=image/format,png)

3.  配置文件的模板如下

    ```
    <?xml version="1.0" encoding="UTF-8"?>
    <!DOCTYPE generatorConfiguration
            PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
            "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
    
    <generatorConfiguration>
        <!--导入属性配置-->
        <properties resource="properties/xx.properties"></properties>
    
        <!-- 指定数据库驱动的jdbc驱动jar包的位置 -->
        <classPathEntry location="C:\Users\Vergi\.m2\repository\mysql\mysql-connector-java\8.0.11\mysql-connector-java-8.0.11.jar" />
        <!-- context 是逆向工程的主要配置信息 -->
        <!-- id：起个名字 -->
        <!-- targetRuntime：设置生成的文件适用于那个 mybatis 版本 -->
        <context targetRuntime="MyBatis3">
            <!--optional,旨在创建class时，对注释进行控制-->
            <commentGenerator>
                <property  />
                <!-- 是否去除自动生成的注释 true：是 ： false:否 -->
                <property  />
            </commentGenerator>
    
            <!--jdbc的数据库连接-->
            <jdbcConnection driverClass="${db.driver}"
                            connectionURL="${db.url}"
                            userId="${db.user}"
                            password="${db.password}">
            </jdbcConnection>
    
    
            <!--非必须，类型处理器，在数据库类型和java类型之间的转换控制-->
            <javaTypeResolver>
                <!-- 默认情况下数据库中的 decimal，bigInt 在 Java 对应是 sql 下的 BigDecimal 类 -->
                <!-- 不是 double 和 long 类型 -->
                <!-- 使用常用的基本类型代替 sql 包下的引用类型 -->
                <property  />
            </javaTypeResolver>
    
            <!-- targetPackage：生成的实体类所在的包 -->
            <!-- targetProject：生成的实体类所在的硬盘位置 -->
            <javaModelGenerator targetPackage="mybatis.generator.model"
                                targetProject=".\src\main\java">
                <!-- 是否允许子包 -->
                <property  />
                <!-- 是否清理从数据库中查询出的字符串左右两边的空白字符 -->
                <property  />
            </javaModelGenerator>
    
            <!-- targetPackage 和 targetProject：生成的 mapper.xml 文件的包和位置 -->
            <sqlMapGenerator targetPackage="mybatis.generator.mappers"
                             targetProject=".\src\main\resources">
                <!-- 针对数据库的一个配置，是否把 schema 作为字包名 -->
                <property  />
            </sqlMapGenerator>
    
            <!-- targetPackage 和 targetProject：生成的 mapper接口文件的包和位置 -->
            <javaClientGenerator type="XMLMAPPER"
                                 targetPackage="mybatis.generator.dao" targetProject=".\src\main\java">
                <!-- 针对 oracle 数据库的一个配置，是否把 schema 作为子包名 -->
                <property  />
            </javaClientGenerator>
            <!-- 这里指定要生成的表 -->
            <table table/>
            <table table/>
        </context>
    </generatorConfiguration>
    ```

4.  双击执行 mybatis-generator 的 maven 插件

    ![](https://imgconvert.csdnimg.cn/aHR0cDovL3Fhc2IxMXhxeS5ia3QuY2xvdWRkbi5jb20vaW1hZ2UtMjAyMDA1MjcyMDM5MDI0OTEucG5n?x-oss-process=image/format,png)


执行日志如下

![](https://imgconvert.csdnimg.cn/aHR0cDovL3Fhc2IxMXhxeS5ia3QuY2xvdWRkbi5jb20vaW1hZ2UtMjAyMDA1MjcyMDM5NDA4MTcucG5n?x-oss-process=image/format,png)

生成的文件如下

![](https://imgconvert.csdnimg.cn/aHR0cDovL3Fhc2IxMXhxeS5ia3QuY2xvdWRkbi5jb20vaW1hZ2UtMjAyMDA1MjcyMDQwNDM5MTAucG5n?x-oss-process=image/format,png)

能看到 mybatis-generator 除了给我们生成了基本的 PO 类（上图的 Student 和 Product），还额外生成了 Example 类。Example 类是为了方便执行 SQL 时传递查询条件的。使用的示例如下

```
public class GeneratorTest {

	private SqlSessionFactory sqlSessionFactory;

	@Before
	public void init() throws IOException {
		InputStream resourceAsStream = Resources.getResourceAsStream("mysql8-config.xml");
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
	}

	@Test
	public void test() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
		StudentExample example = new StudentExample();
		StudentExample.Criteria criteria = example.createCriteria();
		criteria.andNameLike("%o%");
		List<Student> students = mapper.selectByExample(example);
		students.forEach(System.out::println);
	}
}
```

结果如下

![](https://imgconvert.csdnimg.cn/aHR0cDovL3Fhc2IxMXhxeS5ia3QuY2xvdWRkbi5jb20vaW1hZ2UtMjAyMDA1MjcyMTExMTUzNTYucG5n?x-oss-process=image/format,png)

### PageHelper 分页插件

使用该插件，快速实现查询结果的分页，使用步骤如下

1.  pom.xml 中配置依赖

    ```
    <dependency>
    	<groupId>com.github.pagehelper</groupId>
    	<artifactId>pagehelper</artifactId>
    	<version>5.1.6</version>
    </dependency>
    ```

2.  mybatis 全局配置文件中配置`<plugin>`标签

    ```
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE configuration
            PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
            "http://mybatis.org/dtd/mybatis-3-config.dtd">
    <configuration>
        <properties resource="properties/xx.properties"></properties>
    
        <plugins>
            <plugin interceptor="com.github.pagehelper.PageInterceptor">
                <property />
            </plugin>
        </plugins>
    
        <environments default="development">
            <environment>
                <transactionManager type="JDBC"/>
                <dataSource type="POOLED">
                    <property ${db.driver}"/>
                    <property ${db.url}"/>
                    <property ${db.user}"/>
                    <property ${db.password}"/>
                </dataSource>
            </environment>
        </environments>
    
        <mappers>
            <package />
        </mappers>
    
    </configuration>
    ```

3.  在执行查询之前，先设置分页信息

    ```
    // 查询第一页,每页3条信息
    PageHelper.startPage(1,3);
    ```

    先看一下查所有数据

    ```
    @Test
    	public void test() {
    		SqlSession sqlSession = sqlSessionFactory.openSession();
    		ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);
    		//PageHelper.startPage(1,3);
    		List<Product> products = mapper.selectByExample(new ProductExample());
    		products.forEach(System.out::println);
    	}
    ```

    ![](https://imgconvert.csdnimg.cn/aHR0cDovL3Fhc2IxMXhxeS5ia3QuY2xvdWRkbi5jb20vaW1hZ2UtMjAyMDA1MjcyMTIzMjk5ODgucG5n?x-oss-process=image/format,png)

    加上 PageHelper 分页

    ```
    @Test
    	public void test() {
    		SqlSession sqlSession = sqlSessionFactory.openSession();
    		ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);
    		PageHelper.startPage(1,3);
    		List<Product> products = mapper.selectByExample(new ProductExample());
    		products.forEach(System.out::println);
    	}
    ```

    ![](https://imgconvert.csdnimg.cn/aHR0cDovL3Fhc2IxMXhxeS5ia3QuY2xvdWRkbi5jb20vaW1hZ2UtMjAyMDA1MjcyMTI0MzI1NDMucG5n?x-oss-process=image/format,png)  
    **特别注意**：在编写 mapper.xml 的时候，SQL 语句的结尾不要带上`;`，因为 PageHelper 插件是在 SQL 末尾拼接`LIMIT`关键字来进行分页的，若 SQL 语句带上了`;`，就会造成 SQL 语法错误  
    ![](https://img-blog.csdnimg.cn/20210318100134383.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3ZjajEwMDk3ODQ4MTQ=,size_16,color_FFFFFF,t_70)  
    另外，PageHelper 会先查询总数量，然后再发出分页查询，打开 mybatis 的日志时，可以看到发出了 2 条 SQL![](https://img-blog.csdnimg.cn/20210318101330171.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3ZjajEwMDk3ODQ4MTQ=,size_16,color_FFFFFF,t_70)  
    当开启 PageHelper 时，查询得到的`List`实际是 PageHelper 中自定义的一个类`Page`，这个类实现了`List`接口，并封装了分页的相关信息（总页数，当前页码等）。  
    ![](https://img-blog.csdnimg.cn/20210318101700947.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3ZjajEwMDk3ODQ4MTQ=,size_16,color_FFFFFF,t_70)  
    可以通过`PageInfo`来获取分页的相关信息，代码如下

    ```
    @Test
    public void test() {
    	SqlSession sqlSession = factory.openSession();
    	PageHelper.startPage(1,3);
    	ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);
    	List<Product> list = mapper.findAll();
    	list.forEach(System.out::println);
    	PageInfo<Product> pageInfo = new PageInfo<>(list);
    	System.out.println(pageInfo.getTotal()); // 获得总数
    	System.out.println(pageInfo.getPageSize());  // 获得总页数
    }
    ```

    PageHelper 插件的源码分析可以查看我之前的文章 =>  
    [极简 PageHelper 源码分析](https://blog.csdn.net/vcj1009784814/article/details/99759738)


### Mybatis Plus

mybatis 虽然非常方便，但也需要编写大量的 SQL 语句，于是 mybatis plus 就应运而生了。它是一个 mybatis 增强工具，为了简化开发，提高效率。搭配 Spring-Boot 食用简直不要太爽。

可以参考我的这篇文章 [mybatis-plus 一发入魂](https://blog.csdn.net/vcj1009784814/article/details/115159687) ，或者 [mybatis-plus 官网](http://mp.baomidou.com/#/)，以及慕课网的[入门教程](https://www.imooc.com/learn/1130)和[进阶教程](https://www.imooc.com/learn/1171)

**（完）**

注：该文是一篇较为全面详细的笔记，内容篇幅很长。当对 mybatis 的使用较为熟练后，可以查看这篇极为简短的 [mybatis 精髓总结](https://blog.csdn.net/vcj1009784814/article/details/103072323?spm=1001.2014.3001.5502)，从整体架构和源码层面上把握 mybatis。