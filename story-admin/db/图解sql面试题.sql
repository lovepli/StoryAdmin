/*
-- ----------------------------
--  Table structure for `d_course`
-- ----------------------------
DROP TABLE IF EXISTS `d_course`;
CREATE TABLE `d_course` (
  `id` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `teacher_id` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `d_score`
-- ----------------------------
DROP TABLE IF EXISTS `d_score`;
CREATE TABLE `d_score` (
  `id` varchar(255) DEFAULT NULL,
  `course_id` varchar(255) DEFAULT NULL,
  `course` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `d_student`
-- ----------------------------
DROP TABLE IF EXISTS `d_student`;
CREATE TABLE `d_student` (
  `id` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `sex` char(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `d_teacher`
-- ----------------------------
DROP TABLE IF EXISTS `d_teacher`;
CREATE TABLE `d_teacher` (
  `id` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

*/

-- 批量插入学生
-- insert d_student VALUES('0001','猴子','1989-01-01','男');
-- insert d_student VALUES('0002','猴子','1990-12-21','女');
-- insert d_student VALUES('0003','马云','1991-12-21','男');
-- insert d_student VALUES('0004','王思聪','1990-05-20','男');

-- 批量插入成绩
-- insert d_score VALUES('0001','0001','80');
-- insert d_score VALUES('0001','0002','90');
-- insert d_score VALUES('0001','0003','99');
-- insert d_score VALUES('0002','0001','59');
-- insert d_score VALUES('0002','0002','60');
-- insert d_score VALUES('0002','0003','80');
-- insert d_score VALUES('0003','0001','50');
-- insert d_score VALUES('0003','0002','55');
-- insert d_score VALUES('0003','0003','56');

-- 批量插入课程
-- insert d_course VALUES('0001','语文','0002');
-- insert d_course VALUES('0002','数学','0001');
-- insert d_course VALUES('0003','英语','0003');

-- 批量插入教师
-- insert d_teacher VALUES('0001','孟扎扎');
-- insert d_teacher VALUES('0002','马化腾');
-- insert d_teacher VALUES('0003','NULL');
-- insert d_teacher VALUES('0004','');

-- 题目类型
-- 1、汇总分析
-- 1.1 查询结构排序
-- 1.2 分组的指定条件

-- 1.1 【查询学生的总成绩并进行排名】

-- 【知识点】分组查询
-- 分析思路
-- select 查询结果 [总成绩：sum(成绩), 学号]
-- from 从哪张表中查找数据 [成绩表score]
-- where 查询条件 [没有]
-- group by 分组 [学生的总成绩：按照每个学生学号进行分组]
-- order by 排序 [按照总成绩进行排序：sum(成绩)];
-- 答案：
-- select ds.id as '学号', sum(ds.course) as '总成绩' from d_score ds group by ds.id order by sum(ds.course);

-- 1.2 【查询平均成绩大于60分的学生的学号和平均成绩】
-- 【知识点】分组+条件
-- 分析思路
-- select 查询结果 [学号, 平均成绩: avg(成绩)]
-- from 从哪张表中查找数据 [成绩表score]
-- where 查询条件 [没有]
-- group by 分组 [学号]
-- having 分组条件 [平均成绩大于60分：avg(成绩 ) >60]
-- order by 排序 [没有];
-- 答案：
-- select ds.id as '学号', avg(ds.course) as '平均成绩' from d_score ds group by ds.id having avg(ds.course) >60 ORDER BY avg(ds.course) ; -- 根据平均成绩排序

-- 2、复杂查询
-- 2.1【查询各学生的年龄（精确到月份）】
-- 【知识点】时间格式转化 timestampdiff(month ,birthday ,now())/12
-- select dst.id,dst.birthday from d_student dst;

-- 2.2【查询本月过生日的学生】
-- select * from d_student dst where month(dst.birthday) = month(now())+2;
-- select IFNULL((select dst.name from d_student dst where month(dst.birthday) = month(now())+2),null) as '学生姓名';

-- 3.多表查询
-- 3.1 【检索"0001"课程分数小于60，按分数降序排列的学生信息】
-- select dst.*, ds.course from d_student dst left join d_score ds on dst.id = ds.id where ds.course_id = '0001' and ds.course <60 order by ds.course desc;

-- 3.2 【查询不同老师所教不同课程平均分从高到低显示】

-- select avg(ds.course) as '课程平均成绩' from d_score ds group by ds.course_id order by avg(ds.course);
-- select dt.id as '教师号',dt.name as '教师名称',avg(ds.course) as '课程平均成绩' from d_teacher dt left join d_course dc on dt.id=dc.teacher_id left join d_score ds on dc.id=ds.course_id group by ds.course_id order by avg(ds.course) desc;

-- 【知识点】分组+条件+排序+多表连接，思路如图
-- select a.教师号,a.教师姓名,avg(c.成绩) from  teacher as a inner join course as b on a.教师号= b.教师号 inner join score  c on b.课程号= c.课程号 group by a.教师姓名 order by avg(c.成绩) desc;

-- 左连接 left join on
-- select dt.name as '教师名称',avg(ds.course) as '课程平均成绩' from d_teacher dt left join d_course dc on dt.id=dc.teacher_id left join d_score ds on dc.id=ds.course_id group by dt.name order by avg(ds.course) desc;
-- 正确答案：
-- 内连接 inner join on 内连接，左表和右表都存在都部分
-- select dt.name as '教师名称',avg(ds.course) as '课程平均成绩' from d_teacher dt inner join d_course dc on dt.id=dc.teacher_id inner join d_score ds on dc.id=ds.course_id group by dt.name order by avg(ds.course) desc;

-- 3.3 【查询课程名称为"数学"，且分数低于60的学生姓名和分数】

-- select dst.name as '学生姓名',ds.course as '分数' from d_student dst inner join d_score ds on dst.id =ds.id where ds.course_id =(select id from d_course dc where dc.name= '数学') and ds.course <60;
-- 或者三表联接查询
-- select dst.name as '学生姓名',ds.course as '分数' from d_student dst inner join d_score ds on dst.id =ds.id  inner join d_course dc on ds.course_id = dc.id  where dc.name= '数学' and ds.course <60;

-- 3.4 【查询任何一门课程成绩在70分以上的姓名、课程名称和分数（与上题类似）】
-- select dst.id as '学号',dst.name as '学生姓名',dc.name as '课程名称', ds.course as '分数' from d_student dst inner join d_score ds on dst.id =ds.id  inner join d_course dc on ds.course_id = dc.id  where ds.course > 70;

-- 3.5 【查询两门及其以上不及格课程的同学的学号，姓名及其平均成绩】
-- select b.姓名,avg(a.成绩),a.学号 from score as a inner join student as b on a.学号 =b.学号 where a.成绩 <60 group by a.学号 having count(a.学号 ) >=2;
-- select dst.id, avg(ds.course) from d_score ds inner join d_student dst  on dst.id=ds.id where ds.course < 60  GROUP BY ds.id having count(ds.id)>=2;

-- 3.6 【查询不同课程成绩相同的学生的学生编号、课程编号、学生成绩】
-- 思路：查找的内容都是在一张表中，使用相同的表联表查询 或者交叉连接 cross join
-- 1、复制成绩表a,b
-- 2、用学号连接a表，b表
-- 3、在a,b表找出不同的课程号，相同成绩的学号
-- select distinct a.id,a.course ,a.course_id  from d_score as a inner join d_score b on a.id = b.id where a.course = b.course and a.course_id != b.course_id
-- select distinct a.id as '学号',a.course as '成绩',a.course_id  as '课程表' from d_score as a inner join d_score b on a.id = b.id where a.course = b.course and a.course_id != b.course_id
-- 或者
-- select distinct a.id,a.course ,a.course_id  from d_score as a cross join d_score b on a.id = b.id where a.course = b.course and a.course_id != b.course_id

-- 3.7 【查询课程编号为“0001”的课程比“0002”的课程成绩高的所有学生的学号】
-- select * from d_score ds where ds.course_id = '0001';
-- select * from d_score ds where ds.course_id = '0002';

-- select * from (select * from d_score ds where ds.course_id = '0001') as a inner join (select * from d_score ds where ds.course_id = '0002') as b on a.id = b.id where a.course > b.course;
-- select a.id as '学号' from (select * from d_score ds where ds.course_id = '0001') as a inner join (select * from d_score ds where ds.course_id = '0002') as b on a.id = b.id  inner join d_student dst on dst.id =a.id  where a.course > b.course;

-- 3.8 【查询学过编号为“0001”的课程并且也学过编号为“0002”的课程的学生的学号、姓名】
-- select a.id as '学号' from (select * from d_score ds where ds.course_id = '0001') as a inner join (select * from d_score ds where ds.course_id = '0002') as b on a.id = b.id  inner join d_student dst on dst.id =a.id ;

-- 3.9 【查询学过“孟扎扎”老师所教的所有课的同学的学号、姓名】
-- select s.学号 ,s.姓名,a.学号 ,b.课程号,c.教师号 ,c.教师姓名
-- from student as s
-- inner join score as a
-- on s.学号 =a.学号
-- inner join  course  b on a.课程号 =b.课程号
-- inner join  teacher c  on b.教师号 = c.教师号
-- where c.教师姓名 ='孟扎扎';

-- 3.10 【查询没学过"孟扎扎"老师讲授的任一门课程的学生姓名 （与上题类似，"没学过"用not in来实现)】
--
-- select 姓名 ,学号
-- from student
-- where 学号 not in (
-- select a.学号
-- from student as a
-- inner join score as b
-- on a.学号 =b.学号
-- inner join course as c on b.课程号 =c.课程号
-- inner join teacher as d on c.教师号 =d.教师号
-- where d.教师姓名 ='孟扎扎');

-- 3.11 【查询没学过“孟扎扎”老师课的学生的学号、姓名（与上题类似）】

-- select 学号, 姓名
-- from student
-- where 学号 not in
-- (select 学号 from score where 课程号=
-- (select 课程号 from course  where 教师号 =
-- (select 教师号 from teacher where 教师姓名 ='孟扎扎')
-- )
-- );

-- 3.12 【查询选修“孟扎扎”老师所授课程的学生中成绩最高的学生姓名及其成绩（与上题类似,用成绩排名，用 limit 1得出最高一个）】

-- select a.姓名,b.成绩
-- from student as a
-- inner join score as b on a.学号=b.学号
-- inner join course as c on b.课程号 =c.课程号
-- inner join teacher as d on c.教师号 = d.教师号
-- where d.教师姓名 = '孟扎扎'
-- order by b.成绩 desc limit 1;

-- 3.13 【查询至少有一门课与学号为“0001”的学生所学课程相同的学生的学号和姓名】
-- select 学号 ,姓名
-- from student
-- where 学号 in
-- (select distinct(学号) from score where 课程号 in
-- (select 课程号 from score where 学号=0001))
--  and 学号 !=0001;

-- 3.14 【按平均成绩从高到低显示所有学生的所有课程的成绩以及平均成绩】
-- 【知识点】多表连接 新建字段 ，思路如图

-- select a.学号,avg(a.成绩 ),
-- max(case when b.课程名称  = '数学' then a.成绩 else null end ) as '数学',
-- max(case when b.课程名称  = '语文' then a.成绩 else null end ) as '语文',
-- max(case when b.课程名称  = '英语' then a.成绩 else null end ) as '英语'
-- from score as a
-- inner join course as b
-- on a.课程号 =b.课程号
-- group by a.学号 ;

-- 4.SQL高级功能
-- 4.1 【查询学生平均成绩及其名次】
-- 思路：
-- 1、按照每个学号的平均成绩排名
-- 2、使用专用窗口函数now_number 增加 ‘排名’一列

-- select 学号 ,avg(成绩),
-- row_number () over( order by avg(成绩) desc)
-- from score
-- group by 学号  ;
--

-- 4.2 【按各科成绩进行排序，并显示排名】
-- select 课程号 ,
-- row_number () over(partition by 课程号 order by 成绩 )
-- from score ;

-- 4.3【查询每门功成绩最好的前两名学生姓名】
-- 【知识点】窗口函数排名+多表连接+条件
-- 思路：
-- 1.按照每科课程的成绩排名
-- 2.使用专用窗口函数now_number增加‘排名’一列
-- 3.找出排名1，2的学号
-- 4.用学号连接学生表得出学生姓名


-- select a.课程号 ,b.姓名 ,a.成绩,a.ranking from (
-- select 课程号 ,学号 ,成绩 ,
-- row_number () over(partition by 课程号 order by 成绩 desc) as ranking
-- from  score) as a
-- inner join student as b on a.学号 =b.学号
-- where a.ranking <3 ;

-- 4.4【查询所有课程的成绩第2名到第3名的学生信息及该课程成绩（与上一题相似）】

-- select b.姓名 ,a.课程号 ,a.成绩
-- from (
-- select 课程号 ,学号 ,成绩 ,
-- row_number () over( partition by 课程号 order by 成绩 desc) as ranking
-- from  score ) as a
-- inner join student as b
-- on a.学号 =b.学号
-- where a.ranking in( 2,3) ;

-- 4.5【查询各科成绩前三名的记录（不考虑成绩并列情况）（与上一题相似）】
-- select b.姓名 ,a.课程号 ,a.成绩
-- from (
-- select 课程号 ,学号 ,成绩 ,
-- row_number () over( partition by 课程号 order by 成绩 desc) as 'ranking'
-- from  score ) as a
-- inner join student as b
-- on a.学号 =b.学号
-- where a.ranking <4 ;




