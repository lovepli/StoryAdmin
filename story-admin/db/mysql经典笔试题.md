https://www.cnblogs.com/xiqingbo/p/mysql-01.html#%E8%96%AA%E8%B5%84%E7%AD%89%E7%BA%A7%E8%A1%A8%EF%BC%9A

习题和解答
 1. 取得每个部门最高薪水的人员名称
 先取得部门最高薪水：
 select deptno,max(sal) from emp group  by deptno;    

最终查询语句及结果：                                                                                                
 select e.ename,t.* from (select deptno,max(sal) maxsal from emp group by deptno) t
 join emp e on t.deptno = e.deptno and t.maxsal = e.sal;

2. 哪些人的薪水在部门平均薪水之上
先取得部门平均薪水：
  select deptno,avg(sal) avgsal from emp group by deptno;

  最终查询语句及结果：   
  select e1.ename,e1.sal,e1.deptno from emp e1 join
  (select deptno,avg(sal) avgsal from emp group by deptno) e2 on e1. deptno = e2.deptno
  where e1.sal > e2.avgsal order by deptno; 


3. 取得部门中（所有人）平均薪水等级
  先取得所有人的薪水等级：
  select e.*,s.grade from emp e join salgrade s on e.sal between s.losal and hisal;

  最终查询语句及结果：   
  select t.deptno,avg(t.grade) avgsalgrade from
  (select e.*,s.grade from emp e join salgrade s on e.sal between s.losal and hisal) t
  group by t.deptno;

4. 不准用组函数（max），取得最高薪水（给出两种解决方案）
 第一种解决方案：
  根据降序后取第一条记录：                                                                                                                                                                          
  select ename,sal from emp order by sal desc limit 1;

  第二种解决方案：
  自连接：其中一个值不小于任何一个值，distinct去重后使用not in 过滤查询结果后即为最大值：
  select ename,sal from emp where sal not in
  (select distinct a.sal from emp a join emp b on a.sal < b.sal);

5. 取得平均薪水最高的部门的部门编号
第一种方案：
  先取得部门的平均薪水后降序排序取第一条数据：
  select deptno,avg(sal) avgsal from emp group by deptno order by avgsal desc limit 1;
第二种方案：
  先取得每个部门的平均薪水：
  select avg(sal) avgsal from emp group by deptno;

  再将上面的查询结果当做临时表使用max分组函数取得最大值和部门编号：
  select t.deptno, max(t.avgsal) maxavgsal from
  (select deptno,avg(sal) avgsal from emp group by deptno) t;

6. 取得平均薪水最高的部门的部门名称
先取得每个部门的平均薪水：
  select avg(sal) avgsal from emp group by deptno;

  再将上面的查询结果当做临时表使用max分组函数取得最大值和部门编号：
  select t.deptno, max(t.avgsal) maxavgsal from (select deptno,avg(sal) avgsal from emp group by deptno) t;

  最后将该表与部门表连接后查询出部门名称:
  select t.deptno,d.dname, max(t.avgsal) maxavgsal from 
  (select deptno,avg(sal) avgsal from emp group by deptno) t join dept d on t.deptno = d.deptno;

7. 求平均薪水的等级最低的部门的部门名称
先求出每个部门平均薪水：
  select deptno,avg(sal) avgsal from emp group by deptno;

  再求出每个部门平均薪水的等级：
  select t.deptno,s.grade  from (select deptno,avg(sal) avgsal from emp group by deptno) t
  join salgrade s on t.avgsal between s.losal and hisal;

  最后将查询出来的数据当做一张临时表与部门表连表查询出部门名称：
  select t1.deptno,d.dname,t1.grade from (select t.deptno,s.grade  from 
  (select deptno,avg(sal) avgsal from emp group by deptno) t join salgrade s
  on t.avgsal between s.losal and hisal) t1 join dept d on t1.deptno = d.deptno;

8. 取得比普通员工（员工代码没有在mgr上出现的）的最高薪水还要高的经理人姓名
 先取得所有普通员工的最高薪水（共8个）：
  select max(e.sal) maxsal from emp e where ename not in
  (select e.ename from emp e join emp e1 on e.empno = e1.mgr);

  最终查询语句及结果：
  select ename from emp where sal > (select max(e.sal) maxsal from emp e
  where ename not in (select e.ename from emp e join emp e1 on e.empno = e1.mgr));

9. 取得薪水最高的前五名员工
 select * from emp order by sal desc limit 5

10. 取得薪水最高的第六到第十名员工
 select * from emp order by sal desc limit 5,5

11. 取得最后入职的5名员工
select * from emp order by hiredate desc limit 5

12. 取得每个薪水等级有多少员工
select s.grade,count(1) count from emp e join salgrade s
on e.sal between s.losal and s.hisal group by grade;


14. 列出所有员工及领导的名字 
select e.ename,e1.ename leadername from emp e left join emp e1 on e.mgr = e1.empno

15. 列出受雇日期早于其直接上级的所有员工编号、姓名、部门名称
先列出受雇日期遭遇其直接上级的员工编号、姓名、部门编号：
  select e.empno, e.ename,e.deptno,e.hiredate,e1.ename leadername,e1.hiredate from emp e
  left join emp e1 on e.mgr = e1.empno where e.hiredate < e1.hiredate;
最终查询语句及结果：
  select t.empno,t.ename,d.dname from (select e.empno, e.ename,e.deptno,e1.ename leadername from emp e
  left join emp e1 on e.mgr = e1.empno where e.hiredate < e1.hiredate) t join dept d on t.deptno = d.deptno;

16. 列出部门名称和这些部门的员工信息，同时列出那些没有员工的部门
select d.deptno,d.dname,e.* from emp e right join dept d on e.deptno = d.deptno order by d.deptno;

17. 列出至少有5个员工的所有部门
select deptno, count(1) counter from emp group by deptno having counter >= 5;

18. 列出薪水比“SMITH”多的所有员工信息
先查出‘SMTH’的薪水：
  select sal from emp where ename = 'SMITH';

  最终查询语句及结果：
  select * from emp where sal > (select sal from emp where ename = 'SMITH');

19. 列出所有“CLERK”（办事员）的姓名及其部门名称，部门人数
根据部门分组找出工作为“CLERK”的员工姓名及部门名称：
  select e.ename,d.dname from emp e join dept d on e.deptno = d.deptno where job = 'CLERK';

  再找出每个部门的人数：
  select count(*) number from emp group by deptno;

  最后以上两张表联合查询：
  select d1.ename,d1.dname,d2.number from
  (select e.deptno,e.ename,d.dname from emp e join dept d on e.deptno = d.deptno where job = 'CLERK') d1
  join (select deptno,count(*) number from emp group by deptno) d2 on d1.deptno = d2.deptno;

20. 列出最低薪水大于1500的各种工作及从事此工作的全部雇员人数
select job,count(*) peoNumber from emp group by job having min(sal) > 1500;

21. 列出在部门“SALES”<销售部>工作的员工的姓名，假定不知道销售部门的部门编号
先找出“SALES”<销售部>的部门编号：
  select deptno from dept where dname = 'SALES';

  最后通过部门编号直接列出“SALES”部门工作的员工姓名：
  select ename from emp where deptno = (select deptno from dept where dname = 'SALES');

22. 列出薪金高于公司平均薪金的所有员工，所在部门、上级领导、雇员的工资等级
先找出薪金高于公司平均薪金的所有员工：
  select ename from emp where sal > (select avg(sal) avgsal from emp);

  再找出薪金高于公司平均薪金的所有员工，所在部门：
  select e.ename,d.dname from emp e join dept d on e.deptno = d.deptno
  where sal > (select avg(sal) avgsal from emp);

  然后找出薪金高于公司平均薪金的所有员工，所在部门、上级领导：
  select e1.ename,d.dname,e2.ename from emp e1 join dept d on e1.deptno = d.deptno
  left join emp e2 on e1.mgr = e2.empno where e1.sal > (select avg(sal) avgsal from emp);

  最后列出薪金高于公司平均薪金的所有员工，所在部门、上级领导、工资等级：
  select e1.ename,d.dname,e2.ename,s.grade from emp e1 join dept d on e1.deptno = d.deptno
  left join emp e2 on e1.mgr = e2.empno join salgrade s on e1.sal between s.losal and s.hisal
  where e1.sal > (select avg(sal) avgsal from emp);

23. 列出与“SCOTT”从事相同工作的所有员工及部门名称
先找出与“SCOTT”从事相同工作的所有员工：
  select ename from emp where job = (select job from emp where ename = 'SCOTT');

  再联合部门表找出与“SCOTT”从事相同工作的所有员工及部门名称：
  select e.ename,d.dname from emp e join dept d on e.deptno = d.deptno
  where job = (select job from emp where ename = 'SCOTT') and ename <> 'SCOTT';

24. 列出薪金等于部门30中员工的薪金的其它员工的姓名和薪金
先找出30部门中员工的薪金：
  select sal from emp where deptno = 30;

  最终查询语句及结果：
  select ename,sal from emp where sal in (select sal from emp where deptno = 30) and deptno <> 30;
  Empty set (0.00 sec)

25. 列出薪金高于在部门30工作的所有员工的薪金的员工姓名和薪金、部门名称
 先找出在30部门工作的所有员工中最高薪金：
  select max(sal) maxsal from emp where deptno = 30;

  再找出薪金高于以上薪金的员工姓名和薪金的员工：
  select ename,sal from emp where sal > (select max(sal) maxsal from emp where deptno = 30) and deptno <> 30;

  最后找出薪金高于在部门30工作的所有员工的薪金的员工姓名和薪金、部门名称：
  select e.ename,e.sal,d.dname from emp e join dept d on e.deptno = d.deptno
  where e.sal > (select max(sal) maxsal from emp where deptno = 30) and e.deptno <> 30;

26. 列出在每个部门工作的员工数量、平均工资和平均服务期限（*）
// 其中涉及的函数包括：ifnull(字段,0)      datediff(now(),日期)      right join
  select d.deptno,ifnull(count(e.ename),0) peonumber,ifnull(avg(e.sal),0) avgsal,
  ifnull(avg(datediff(now(),e.hiredate)/365),0) servertime from emp e right join dept d
  on e.deptno = d.deptno group by e.deptno order by deptno;

27. 列出所有员工的姓名、部门名称和工资
select e.ename,d.dname,e.sal from emp e join dept d on e.deptno = d.deptno;

28. 列出所有部门的详细信息和人数
select d.*,count(e.ename) peoNumber from dept d left join emp e 
  on d.deptno = e.deptno group by e.deptno order by d.deptno;

29. 列出各种工作的最低工资及从事此工作的雇员姓名
先找出各种工作的最低工资：
  select job,min(sal) minSal from emp group by job;

  最终查询语句及结果：
  select ename,job,sal from emp where sal in (select min(sal) minSal from emp group by job);

30. 列出各个部门MANAGER的最低薪金
select deptno,min(sal) minSal from emp where job = 'MANAGER' group by deptno;

31. 列出所有员工的年工资，按年薪从低到高掋序
select ename,ifnull(sal,0) * 12 annualSal from emp order by annualSal;

32. 求出员工领导的薪水超过3000的员工名称和领导名称
select e1.ename,e2.ename,e2.sal from emp e1 join emp e2 on e1.mgr = e2.empno where e2.sal > 3000;

33. 求部门名称中带“S”字符的部门员工的工资合计、部门人数
select d.dname,ifnull(sum(e.sal),0) sumSal,count(e.ename) peoNumber from emp e right join dept d
  on e.deptno = d.deptno where d.dname like '%S%' group by d.deptno;

34. 给任职日期超过30年的员工加薪10%
  update emp set sal = sal * 1.1 where datediff(now(),hiredate)/365 > 30;
  
  
35.查询部门编号为20,30的两个部门所有员工的信息
select empno, empname, sal, deptno
from emp
where deptno in (80, 90) 

36.查询工资大于7369号员工工资的员工的信息
select * 
from emp
where sal > (
      select sal
      from emp
      where empno = 7369
)


37.查询与7369号或7499号员工的mgr和deptno相同的其他员工的empno, mgr, deptno
--多列子查询（不成对比较 & 成对比较）
[方式一]
select empno, mgr, deptno
from emp
where mgr in (
      select mgr
      from emp
      where empno in(7369, 7499)
) and deptno in (
      select deptno
      from emp
      where empno in(7369, 7499)
) and empno not in (7369, 7499);

[方式二]
select empno, mgr, deptno
from emp
where (mgr, deptno) in (
      select mgr, deptno
      from emp
      where empno in (7369, 7499)
) and empno not in(7369, 7499);

38.查询所有部门的平均薪资，取最大的一个薪资
--在 FROM 子句中使用子查询
[方式一]
select max(avg(sal))
from emp
group by deptno;

[方式二]
select max(avg_sal)
from (
      select avg(sal) avg_sal
      from emp
      group by deptno
) e

39.返回比本部门平均工资高的员工的ename, deptno, sal及平均工资
--在 FROM 子句中使用子查询
[方式一]
select ename, deptno, sal, (select avg(sal) from emp where deptno = e1.deptno)
from emp e1
where sal > (
      select avg(sal)
      from emp e2
      where e1.deptno = e2.deptno
)
[方式二]
select ename, e1.deptno, sal, avg_sal
from emp e1, (
     select deptno, avg(sal) avg_sal
     from emp
     group by deptno
) e2
where e1.deptno = e2.deptno
and e1.sal > e2.avg_sal;

40.若部门为10 查看工资的 1.1 倍，部门号为 20 工资的1.2倍，其余 1.3 倍
case...when ... then... when ... then ... else ... end

SELECT
 empno,
 ename,
 sal,
CASE
  deptno 
  WHEN 10 THEN
  sal * 1.1                                                           
  WHEN 20 THEN
  sal * 1.2  ELSE sal * 1.3                                                           
 END "new_sal" 
FROM
 emp;
SELECT
 empno,
 ename,
 sal,
 decode( deptno, 10, sal * 1.1, 20, sal * 1.2,  sal * 1.3 ) "new_sal" 
FROM
 emp;
 
 
 41.显式员工的empno,ename和location。其中，若员工deptno与location_id为1800的deptno相同，则location为’Canada’,其余则为’USA’
 --单列子查询表达式
select empno, ename, case deptno when (
                    select deptno
                    from dept
                    where location_id = 1800
) then 'Canada' else 'USA' end "location"
from emp;

42.查询员工的empno,ename,要求按照员工的dname排序 
select empno, ename
from emp e1
order by (
      select dname
      from dept d1
      where e1.deptno = d1.deptno
)

43.查询公司管理者的empno,ename,job_id,deptno信息
SQL 优化：能使用 EXISTS 就不要使用 IN

--EXISTS 操作符
• EXISTS 操作符检查在子查询中是否存在满足条件的行
• 如果在子查询中存在满足条件的行:
– 不在子查询中继续查找
– 条件返回 TRUE

select empno, ename, job_id, deptno
from emp
where empno in (
      select mgr
      from emp
)


select empno, ename, job_id, deptno
from emp e1
where exists (
      select 'x'
      from emp e2
      where e1.empno = e2.mgr
) 

44.查询dept表中，不存在于emp表中的部门的deptno和dname
select deptno, dname
from dept d1
where not exists (
      select 'x'
      from emp e1
      where e1.deptno = d1.deptno
)

45.更改 108 员工的信息: 使其工资变为所在部门中的最高工资, job 变为公司中平均工资最低的 job
update emp e1
set sal = (
    select max(sal)
    from emp e2
    where e1.deptno = e2.deptno
), job_id = (
   select job_id
   from emp
   group by job_id
   having avg(sal) = (
         select min(avg(sal))
         from emp
         group by job_id
   )
)
where empno = 108;

46.删除 108 号员工所在部门中工资最低的那个员工
delete from emp e1
where sal = (
      select min(sal)
      from emp
      where deptno = (
            select deptno
            from emp
            where empno = 108
      )
)

select * from emp where empno = 108;
select * from emp where deptno = 100
order by sal;

rollback;

 

--WITH 子句
47. 查询公司中各部门的总工资大于公司中各部门的平均总工资的部门信息
WITH
dept_costs AS (
SELECT d.department_name, SUM(e.salary) AS dept_total
FROM emp e, departments d
WHERE e.deptno = d.deptno
GROUP BY d.department_name),
avg_cost AS (
SELECT SUM(dept_total)/COUNT(*) AS dept_avg
FROM dept_costs)
SELECT *
FROM dept_costs
WHERE dept_total >
(SELECT dept_avg
FROM avg_cost)
ORDER BY department_name;

 

附加题目：
48.查询员工的ename, deptno, salary.其中员工的salary,deptno与有奖金的任何一个员工的salary,
deptno相同即可

select ename, deptno, salary
from emp
where (salary,deptno) in (
select salary,deptno
from emp
where commission_pct is not null
)

 

49.选择工资大于所有job = 'SA_MAN'的员工的工资的员工的ename, job, sal

select ename, job, sal
from emp
where sal > all(
select sal
from emp
where job = 'SA_MAN'
)

 

50.选择所有没有管理者的员工的ename

select ename
from emp e1
where not exists (
select 'A'
from emp e2
where e1.mgr = e2.empno
)


51. 查询10，50，20号部门的job，deptno并且deptno按10，50，20的顺序排列
Column dummy noprint;
select job , deptno ,1 dummy
from emp
where deptno = 10
union
select job , deptno , 2
from emp
where deptno = 50
union
select job , deptno , 3
from emp
where deptno= 20
order by 3
































