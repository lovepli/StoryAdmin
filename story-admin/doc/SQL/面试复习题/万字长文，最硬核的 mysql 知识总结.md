> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [mp.weixin.qq.com](https://mp.weixin.qq.com/s/IqG0s0OBZ5fIbRXAUIL7KQ)

![](https://mmbiz.qpic.cn/mmbiz_jpg/IJUXwBNpKlgg9F7Xgy0vb9XmwBhiatAos48saf1R1Dr0RLkpBnxyHKJUOf8fneOhRKSAibcNA3pHUdcH8GicpjD4Q/640?wx_fmt=jpeg)

### mysql 登录

*   远程登录方式

*   本地登陆方式


```
mysql：mysql -h 主机名 -P 端口号 -u 用户名 -p密码
mysql：mysql -uroot -p密码
```

### 数据库操作命令

*   创建数据库、删除数据库、展示所有数据库名。

*   查看当前数据库名、查看所有表、查看其他数据库的表。

*   查看数据库的版本、表结构、以及字符集、数据库引擎


```
-- 如果该数据库不存在，创建该数据库
create database if not exists 数据库名;

-- 设置指定数据库的字符集为gbk/uft8
alter database 数据库名 character set gbk/uft8;

-- 如果该数据库存在删除该数据库
drop database if exists 数据库名 ;  

-- 查看所有数据库
show databases;  

-- 使用数据库
use 数据库名; 

-- 在当前数据库下查看所有表格
show tables;  

-- 查看其他数据库的全部表格
show tables from 数据库名; 

-- 查看当前数据库名
select database();  

-- 查看当前MySQl登陆的用户
select user();  

-- 查看当前数据库的版本
select version(); 

-- 查看表结构
desc 表名; 

-- 查看数据库的字符集
show variables like '%character%';
show variables like '%char%';
show variables like 'collation%';

-- 查看数据库的引擎
show engines;  
```

### 表操作命令

*   创建表、修改表数据（插入表数据、修改表数据、删除表数据、查询表数据）。

*   修改表结构（新增字段、修改表字段、增加主键、调整字段顺序、指定位置添加字段）。

*   根据原表创建新表（仅复制表结构、复制表结构和数据、复制部分表字段和表数据）。


```
-- 创建员工表，先在数据库里面创建该表，为了后面做测试用
create table dept(
    department_id int primary key auto_increment, -- 部门编号
    dname varchar(14) ,   -- 部门名字
    location varchar(13)   -- 地址
);

create table employee(
    employee_id  int  primary key auto_increment, -- 员工编号
    c_name varchar(20)， -- 员工中文名
    e_name varchar(20)， -- 员工英文名
    hiredate date, -- 雇佣日期，入职日期
    salary int, -- 薪水
    comm int,  -- 奖金
    job_id int,     -- 所属工种
    department_id int not null, -- 部门编号
    manager_id int   -- 直接领导编号
);

-- 表中插入数据
insert into dept values(10,'财务部','北京');
insert into dept values(20,'研发部','上海');
insert into dept values(30,'销售部','广州');
insert into dept values(40,'行政部','深圳');
insert into dept values(50,'人力资源','惠州');
-- 表中插入数据
insert into employee values(1,'刘一','liuyi','1980-12-17',7902,800,1,10,2);
insert into employee values(2,'陈二','chener','1981-02-20',7698,1600,3,30,3);
insert into employee values(3,'张三','zhangsan','1981-02-22',7698,1250,5,30,4);
insert into employee values(4,'李四','lisi','1981-04-02',7839,2975,2,20,5);
insert into employee values(5,'王五','wangwu','1981-09-28',7698,1250,1,40,0);
insert into employee values(6,'赵六','zhaoliu','1981-05-01',7839,2850,3,50,5);

-- 更新数据：
update employee  set c_name ="刘一一" where id=1;

-- 删除数据：
delete from employee  where id=1;

-- 查询数据
select * from employee where id in(1,4);

-- 修改表名
alter table 旧表名 rename to 新表名;

 -- 修改表注释   
alter table 表名 comment '系统信息表';

-- 修改字段类型和注释
alter table 表名  modify column 字段名 varchar(20) COMMENT '新的注释';

-- 设置字段允许为空
alter table 表名  modify column 字段名 varchar(255) null COMMENT '新注释';

-- 增加一个字段，设好数据类型，且不为空，添加注释
alert table 表名 add  字段名  varchar(255) not null comment '新注释'; 

-- 增加非空、自增主键
alter table 表名 add 字段名 int(5) not null auto_increment ,add primary key (aid); 

-- 修改字段名字(要重新指定该字段的类型)
alter table t_app change 原字段名 新字段名 varchar(20) not null;

-- 删除字段
alter table 表名 drop 字段名; 

-- 在某个字段后增加字段
alter table 表名 add column 新字段名 int  not null default 0 after 字段名；

-- 调整字段顺序 
alter table employee  change num num  int not null after departmen_id ; 

-- 表的删除
drop table 表名 ;

--复制表的结构，不复制表数据
create table 新表名 like 旧表名;

--复制表的结构，同时也复制表数据
create table 新表名 select * from 旧表名;

--只复制部分表结构和对应的数据，并且带筛选条件
create table user1 select id, name,salary from user  where salary>3000;

--仅复制部分字段
create table user2 select id, name from user;
```

### 表约束

*   NOT NULL：       非空约束

*   DEFAULT：         默认，用于保证该字段有默认值。

*   PRIMARY KEY：主键约束

*   UNIQUE:              唯一约束

*   CHECK：           检查约束

*   FOREIGN KEY：外键约束。


```
create table student (
id int,
name varchar(20) ,
gender char(1) ,
seat int,
age int,
class_id int, 

PRIMARY KEY(id), -- 主键
UNIQUE (seat), -- 唯一键
CHECK(gender="男" or gender="女"),-- 检查
FOREIGN KEY (class_id) REFERENCES class (id) -- 外键
)
```

注释：这些约束在你创建表和修改表的时候都可以使用。

### 表查询

*   distinct(去重)

*   limit(分页查询)

*   offset(跳过多少条)

*   UNION 和 UNION ALL(联合查询)

*   like(模糊查询)

*   where、between、in、or、and 条件关键字

*   order by (asc 升序、desc 降序排序)

*   group by (分组查询)

*   having 关键字

*   case(流程控制)


```
-- distinct(去重)
select distinct 字段名 from 表名;

-- limit(初始记录行的偏移量是 0(而不是 1),第一个参数指定第一个返回记录行的偏移量，第二个参数指定返回记录行的最大数目。)
select * from 表名 limit 5,10;   -- 检索记录行6-15

-- offset(跳过多少条)
selete * from employee limit 2 offset 1;
+----+----------+-------+--------------+------+
|  2 | lisi       | 12000 |           40 |   90 |
|  3 | wangwu |     0 |           50 |    0 |
+----+----------+-------+--------------+------+
-- 注意： 
-- 1.数据库数据计算是从0开始的 
-- 2.offset X是跳过X个数据，limit Y是选取Y个数据 
-- 3.limit X,Y 中X表示跳过X个数据，读取Y个数据

--union 和union all（union all是直接连接，取到得是所有值，记录可能有重复 union 是取唯一值，记录没有重复。）

-- UNION 的语法如下： 
[SQL 语句 1] 
UNION 
[SQL 语句 2]

-- UNION ALL 的语法如下： 
[SQL 语句 1] 
UNION ALL 
[SQL 语句 2]

-- UNION全连接查询，把部门表和员工表的所有数据都查出来，若有两个表都有匹配数据的就显示匹配数据，若其中有一个表在另一个表中没有匹配数据的输就显示为null
select e.ename,d.dname
     FROM employee e 
     left JOIN dept d
     ON e.department_id= d.department_id
UNION
select e.ename,d.dname
     FROM employee e 
     right JOIN dept d
     ON e.department_id= d.department_id;
+--------+----------+
| ename | dname   |
+--------+----------+
| 刘一   | 财务部   |
| 陈二   | 销售部   |
| 张三   | NULL     |
| 李四   | 研发部   |
| 王五   | 行政部   |
| 赵六   | 人力资源 |
+--------+----------+

-- 模糊查询：like，%标识匹配任意哥字符，_表示匹配一个字符
-- 查询employee表里面名字含有豪字的员工的全部信息
select * form employee where c_name like '%豪%';

-- 查询员工名中第三个字母为a，第五个字母为b的员工信息;
select * from employee where c_name like '__达_法%';

-- 当查询的信息信息看里面还有_这样的特殊字符;
select * from employee  where c_name like '_\_%';

-- 查询工资在5000到6000之间的员工信息;
select * from employee  where salary between 5000 and 6000;

-- in、or、and关键字 
select * from dept where location in ('北京','上海');
select * from dept where location ='北京' or  location ='上海' ;
select * from employee  where (department_id=30 or  department_id=40) and salary >3000;

-- 选择工资不在3000到5000的员工的姓名和工资，按工资降序
select  name, salary, department_id from employees where salary not between 3000 and 5000 order by salary desc;

-- 查询每个部门的员工个数
select count (*) , department_ id from  employee  group by  department_ id;

-- 给30号部门的增加500，40号部门增加1000，50号部门增加1500
select  *, (
        case department_id
        when  30 then
            salary+500
        when 40 then
            salary+1000
        when 50 then
            salary+1500
        else salary
        end
    ) '涨后工资'
from
    employee;

-- 显示员工的薪资等级
select *, (
    case
    when salary >=7900 then
            '高薪资'
    when salary >=7800 then
            '中等薪资'
    when salary >=7700 then
            '低薪资'
     else
            '太难了'
     end
    ) '薪资等级'
from
    employee
order by salary desc;

-- 语法格式 
select 字段1,字段2,字段3 from 表名  [where 筛选条件]  [group by 分组]  [having 筛选条件1]  [order by 排序列表]
```

### mysql 字符串函数

*   concat()：拼接字符串

*   substr()：截取字符串

*   instr()：获取子串第一次出现的索引

*   lpad()：左边以指定字符填充到指定长度

*   rpad()：右边以指定字符填充到指定长度

*   upper()：转换为大写

*   lover()：转换为小写

*   replace()：替换函数

*   length()：获取字节长度

*   trim()：去掉字符串前后空格


```
-- 将英文名全部转换大写和全部转换为小写，然后进行拼接。
select concat(upper(e_name) ,lower(e_name))  from employee;

-- substr，注意:索引从1开始，截取从指定索引处后面所有字符
select substr("欢迎关注非科班的科班，带你一起提升代码内功',7) str ;

-- 从指定索引截取指定长度的字符串substr(str,num1,num2)第二个参数时索引、第三个参数是指定的长度。
select substr('欢迎关注非科班的科班，带你一起提升代码内功',5,6) str;

-- 姓名中首字符大写，其他字符小写然后用_拼接，显示出来
SELECT CONCAT (UPPER (SUBSTR(last_ name,1,1)),'_' ,LOWER (SUBSTR(last_ name,2))) out_put
FROM employees;

-- instr返回子串在指定字符串第一次出现的素引，如果找不到返回0
select instr('欢迎关注非科班的科班，带你一起提升代码内功', '科班') as str;

-- length获取字符串长度、trim()去掉字符串前后的空字符串
select length(trim("   非科班的科班   ")) as str; 

-- lpad用指定的字符实现左填充指定长度
select lpad('非科班的科班',9,'*') as str;

-- rpad用指定的字符实现右填充指定长度
select rpad('非科班的科班',9,'*') as str;

-- replace 替换
select replace('非科班的科班', '科班','javaboy') as str;
```

### mysql 字符串函数

*   now：返回当前的日期和时间

*   year：返回年份

*   month：返回月份

*   monthname：以英文形式返回月份

*   day：返回日

*   hour：小时

*   mimute：分钟

*   second：秒

*   datediff：返回两个日期相差的天数

*   date_format：将时间日期转换为字符串

*   str_to_date：将字符转换成日期

*   curdate：返回当前日期，不包含时间

*   curtime：返回当前时间，不包含日期


```
-- now()返回当前系統日期+时间
select now();

-- curdate返回当前系统日期，不包含时间，curtime返回当前时间，不包含日期
select curdate();
select curtime();

-- 可以获取指定的部分，年、月、日、小时、分钟、秒
select year(now())年;
select year('2020-1-1') 年;
select year(hiredate) 年 from employee;
select month(now()) 月;
select monthname(now()) 月;

-- %Y    四位的年份
-- %y    2位的年份
-- %m    2位的月份( 01,02...11,12)
-- %c    1位的月份( 1,2...11,12)
-- %d    日( 01,02,.. )
-- %H    24小时制的小时
-- %h    12小时制的小时
-- %i    分钟( 00,01...59)
-- %s    秒( 00,01...59)

-- str_to_date:将字符串转换为指定格式的日期对象
select str_to_date('2-28-2020','%m-%d-%Y');

-- 查询入职日期为2020-2-28的员工信息
select  * from employee where hiredate = '2020-2-28' ;

-- date_format:将日期转换成字符
select date_format('2020/02/28','%Y年%m月 %d日')；
select date_format(now(), '%y年%m月%d日') as date ; 

-- 查询工资大于7800的员工的中文名、入职日期
select c_name 中文名, date_format(hiredate,'%m月/%d日 %y年') 入职日期 from employee where salary>7800;
```

### mysql 数学函数

*   ceil：中文意思表示天花板，表示向上取整

*   floor：中文意思表示地板，表示向下取整

*   round：对数字取四舍五入

*   rand：随机取 0-1 之间的所及小数

*   mod：取模运算

*   truncate：截取，类似于字符串的 substr 的用法


```
-- ceil表示向上取整。整数：返回本身。小数：返回的是与该数相邻并比该数大的整数
select ceil(2) ;
select ceil(2.21) ;
select ceil(-2) ;
select ceil(-2.1) ;

-- floor向下取整。整数：返回本身。小数：返回的是与该数相邻并比该数大的整数
select floor(2) ;
select floor(2.21) ;
select floor(-2) ;
select floor(-2.1) ;

-- rand在0-1之间随机去一个随机数
select round(rand()*10) ;  --取一个0-10的随机整数

-- round对数字取四舍五入
select round(-1.55) ;
select round(1.567,2) ;

-- mod取余运算
select mod(3,2) ;
select 3%2;

-- truncate截取，第一个是要截取的数字，第二个是要截取的位数，从小数点后开始算
select truncate(2.3345534,1) ;
```

### 创建视图和索引

*   视图 view，创建，查询视图，删除视图

*   索引 index，创建索引，删除索引


```
语法格式如下：
create view <视图名> [新字段名1,新字段名2,新字段名3,新字段名4,....] as <select语句>

--创建一个员工的视图
create view v_employee (id,name,sal,department,hiredate)
as select employee_id,c_name,salary,department_id,hiredate 
from employee ;

 --查询视图
select * from v_employee where sal>7800;

--修改视图
alter view v_employee as select * from employee where salary>7800;

--删除视图
drop view v_employee ;

--基于多表创建视图
create view v_test as select e.c_name,e.hiredate,e.salary,d.dname,d.location from employee e,dept d where e.department_id=d.department_id;

select * from v_test
+--------+------------+--------+--------------+----------+
| c_name  | hiredate   | salary | dname        | location |
+--------+------------+--------+--------------+----------+
| 刘一   | 1980-12-17 |   7902 | 财务部       | 北京     |
| 陈二   | 1981-02-20 |   7698 | 销售部       | 广州     |
| 张三   | 1981-02-22 |   7698 | 销售部       | 广州     |
| 李四   | 1981-04-02 |   7839 | 研发部       | 上海     |
| 王五   | 1981-09-28 |   7698 | 行政部       | 深圳     |
| 赵六   | 1981-05-01 |   7839 | 人力资源     | 惠州     |
+--------+------------+--------+--------------+----------+

--(1)使用alter table 语句创建索性,使用场景是在表创建完毕之后再添加索引。语法如下：
alter table 表名 add 索引类型 （unique,primary key,fulltext,index）[索引名]（字段名）

-- 普通索引（当column_list有多个的时候使用逗号隔开）
alter table table_name add index index_name (column_list) ;

-- 唯一索引（当column_list有多个的时候使用逗号隔开）
alter table table_name add unique (column_list) ;

--主键索引（当column_list有多个的时候使用逗号隔开）
alter table table_name add primary key (column_list) ;

-- (2)使用create index语句对表增加索引,create index可用于对表增加普通索引或UNIQUE索引，可用于建表时创建索引。
create index index_name on table_name(username(length)); 

-- create只能添加这两种索引;
create index index_name on table_name(column_list);
create UNIQUE index index_name on table_name(column_list);
create index index_employee on employee(salary,hiredate,c_name);

-- (3)删除索引,删除索引可以使用ALTER TABLE或DROP INDEX语句来实现。
drop index index_name on table_name ;
alter table table_name drop index index_name ;
alter table table_name drop primary key ;
```

> **视图**  
> 是一个虚拟表（非真实存在），其本质是【根据 SQL 语句获取动态的数据集，并为其命名】，用户使用时只需使用【名称】即可获取结果集，并可以将其当作表来使用，使用视图时，将其当作表进行操作即可，由于视图是虚拟表，所以无法使用其对真实表进行创建、更新和删除操作，仅能做查询用。  
> **索引**  
> 数据库中将数据整齐的排列在磁盘阵列中，当获取数据的时候只需要逐个搜索，并返回结果，但是 如果开发的应用有几百上千万甚至亿级别的数据，那么不深入了解索引的原理， 写出来程序就根本跑不动，光查一个数据库就得好几天，因此就需要索引，能够快速的查找的需要的数据。

### mysql 连接查询

*   内连接：


*   等值连接

*   非等值连接


*   外连接：


*   左外连接：

*   右外连接：


*   自连接


```
-- 内连接
select  e.c_name,d.dname,d.location from employee e inner join dept d on e.department_id= d.department_id;
+--------+----------+---------+
| c_name | dname   | location |
+--------+----------+---------+
| 刘一   | 财务部   | 北京    |
| 陈二   | 销售部   | 广州    |
| 张三   | 销售部   | 广州    |
| 李四   | 研发部   | 上海    |
| 王五   | 行政部   | 深圳    |
| 赵六   | 人力资源 | 惠州    |
+--------+----------+---------+

-- 左外连接，是指以左边的表的数据为基准，去匹配右边的表的数据，如果匹配到就显示，匹配不到就显示为null
-- 查询employee表中的所有数据和dept表中与employee中相匹配的数据，若是没有匹配的就显示null
select e.c_name,d.dname from employee e left outer join dept d  on d.department_id = e.department_id ;

-- 修改employee中的数据
update employee set department_id=60 where employee_id=3;

-- 重新查询，由于dept表中不存在60的数据，所以再dept表中没有对应的匹配数据，显示为null
select e.c_name,d.dname from employee e left outer join dept d  on d.department_id = e.department_id ;
+--------+----------+
| ename | dname   |
+--------+----------+
| 刘一   | 财务部   |
| 陈二   | 销售部   |
| 张三   | NULL     |
| 李四   | 研发部   |
| 王五   | 行政部   |
| 赵六   | 人力资源 |
+--------+----------+

-- 右外连接和左外连接只不过是左右表相换也能达到同样的效果
-- 这里就是查询dept部门表对应所有部门和employee表中与之对应的数据，你会发现本来employee中有6条数据，只显示了5条数据，因为有一个人的部门60再dept中没有数据，所以就没有显示出来。
select e.c_name,d.dname from employee e right outer join dept d  on d.department_id = e.department_id;
+--------+----------+
| ename | dname   |
+--------+----------+
| 刘一   | 财务部   |
| 陈二   | 销售部   |
| 李四   | 研发部   |
| 王五   | 行政部   |
| 赵六   | 人力资源 |
+--------+----------+

-- 自连接查询就是当前表与自身的连接查询，关键点在于虚拟化出一张表给一个别名
-- 查询员工以及他的上司的名称，由于上司也是员工，所以这里虚拟化出一张上司表
select e.c_name 员工名,b.c_name 上司名 from employee e  left join employee b on e.manager_id= b.employee_id;
+--------+--------+
| 员工名 | 上司名 |
+--------+--------+
| 刘一   | 陈二   |
| 陈二   | 张三   |
| 张三   | 李四   |
| 李四   | 王五   |
| 王五   | NULL   |
| 赵六   | 王五   |
+--------+--------+
```

### mysql 子连接查询

*   按子查询出现在主查询中的不同位置分


*   select 后面：仅仅支持标量子查询。

*   from 后面：支持表子查询。

*   where 或 having 后面：支持标量子查询（单列单行）、列子查询（单列多行）、行子查询（多列多行）

*   exists 后面（即相关子查询）：表子查询（多行、多列）


```
-- select后面的子查询
-- 查询每个部门员工个数
SELECT d.*,
  (SELECT count(*)
   FROM employee b
   WHERE b.department_id = d.department_id) 
   AS 员工个数
FROM dept d;

-- 查询员工号等于3的部门名称
SELECT 
    (SELECT a.dname 
    FROM dept a, employee b 
    WHERE a.department_id = b.department_id 
    AND b.employee_id = 3) 
    AS 部门名;

-- from后面的子查询
-- 查询每个部门平均工资的工资等级
-- （1）先查询每个部门平均工资
SELECT
  department_id,
  avg(a.salary)
FROM employee a
GROUP BY a.department_id;

-- （2）然后是查询薪资等级表
SELECT *
FROM job_grades;

-- （3）将上面2个结果连接查询，筛选条件:平均工资 between lowest_sal and highest_sal;
SELECT
  t1.department_id,
  avg_salary AS '平均工资',
  t2.grade_level
FROM (SELECT
        department_id,
        avg(a.salary) avg_salary
      FROM employees a
      GROUP BY a.department_id) t1, job_grades t2
WHERE
  t1.avg_salary BETWEEN t2.lowest_sal AND t2.highest_sal;


-- where和having后面的子查询
-- 查询谁的工资比javaboy的高？
-- （1）查询lisi的工资
SELECT salary FROM employee WHERE e_name = 'lisi';

-- （2）查询员工信息，满足salary>上面的结果
SELECT *
FROM employee a
WHERE a.salary > (SELECT salary
                  FROM employee
                  WHERE e_name = 'lisi');


--having后的子查询
--查询最低工资大于40号部门最低工资的部门id和其最低工资
-- （1）查询40号部门的最低工资
SELECT min(salary)
FROM employee
WHERE department_id = 40;

--（2）查询每个部门的最低工资
SELECT
  min(salary),
  department_id
FROM employee
GROUP BY department_id;

--（3）③在②的基础上筛选，满足min(salary)>①
SELECT
  min(a.salary) minsalary,
  department_id
FROM employee a
GROUP BY a.department_id
HAVING min(a.salary) > (SELECT min(salary)
                        FROM employee
                        WHERE department_id = 50);
```



![](https://mmbiz.qpic.cn/mmbiz_jpg/IJUXwBNpKlgg9F7Xgy0vb9XmwBhiatAosqOgtVt1uiaFIaOwzJJ1DF7y9EWIHpibdibEL39LdXz3dl0icy6MP00Hh1Q/640?wx_fmt=jpeg)

_**[往期精彩回顾]**_

[[万字长文，一文搞懂 TCP/IP 和 HTTP、HTTPS]](http://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247483880&idx=1&sn=b4b71f169dddc41925814ab4e5bc208b&chksm=fbf7e82acc80613c2f9d2e11aba576dd5d367140ab74c33f6b79e0e6a3cbb84e388a50fded7d&scene=21#wechat_redirect)

[[手撕 ArrayList 底层，透彻分析源码]](http://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247483876&idx=1&sn=b318a710c13fda6f8efd862f4479cfc2&chksm=fbf7e826cc806130d1835676e83cabb457867c3a0cce8edf43f86ffc31e2adeaca2f359b5c76&scene=21#wechat_redirect)

[[Dubbo 和 Zookeeper 入门到实战，看这篇就够了]](http://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247483840&idx=1&sn=ce20c07dce3fc51b0b7ab08fe6e4b430&chksm=fbf7e802cc8061145455a37dfb38749d53b7d43d5c2638d6398b714a9dbb4557a0a9f9434aed&scene=21#wechat_redirect)