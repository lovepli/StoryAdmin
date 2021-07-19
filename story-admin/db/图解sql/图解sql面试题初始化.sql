
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

-- 其他sql语句知识点：
--新增表字段
--ALTER TABLE d_student ADD `age` varchar(255) DEFAULT NULL COMMENT '年龄';

--
----删除表字段
--ALTER TABLE d_student drop `age`;
