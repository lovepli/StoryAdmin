/*
Navicat MySQL Data Transfer

Source Server         : docker-mysql
Source Server Version : 50731
Source Host           : localhost:3306
Source Database       : story_admin2

Target Server Type    : MYSQL
Target Server Version : 50731
File Encoding         : 65001

Date: 2021-07-14 19:44:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `attendance`
-- ----------------------------
DROP TABLE IF EXISTS `attendance`;
CREATE TABLE `attendance` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `sign_date` date NOT NULL,
  `sign_in_time` time NOT NULL,
  `sign_out_time` time NOT NULL,
  `has_sign_out` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of attendance
-- ----------------------------
INSERT INTO `attendance` VALUES ('2', '1', '2020-12-30', '07:09:56', '07:53:47', '1');
INSERT INTO `attendance` VALUES ('3', '1', '2021-01-04', '02:52:26', '02:52:28', '1');

-- ----------------------------
-- Table structure for `attendance_time`
-- ----------------------------
DROP TABLE IF EXISTS `attendance_time`;
CREATE TABLE `attendance_time` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `begin` varchar(255) NOT NULL DEFAULT '',
  `end` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of attendance_time
-- ----------------------------
INSERT INTO `attendance_time` VALUES ('1', '08:30', '17:30');

-- ----------------------------
-- Table structure for `d_course`
-- ----------------------------
DROP TABLE IF EXISTS `d_course`;
CREATE TABLE `d_course` (
  `c_id` varchar(255) NOT NULL,
  `c_name` varchar(255) DEFAULT '',
  `teacher_id` varchar(255) DEFAULT '',
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of d_course
-- ----------------------------
INSERT INTO `d_course` VALUES ('0001', '语文', '0002');
INSERT INTO `d_course` VALUES ('0002', '数学', '0001');
INSERT INTO `d_course` VALUES ('0003', '英语', '0003');

-- ----------------------------
-- Table structure for `d_dept`
-- ----------------------------
DROP TABLE IF EXISTS `d_dept`;
CREATE TABLE `d_dept` (
  `d_id` varchar(255) NOT NULL,
  `d_name` varchar(255) DEFAULT '',
  `parent_id` varchar(255) DEFAULT '',
  PRIMARY KEY (`d_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of d_dept
-- ----------------------------
INSERT INTO `d_dept` VALUES ('0', '公司总部', null);
INSERT INTO `d_dept` VALUES ('0001', '财务部', '0');
INSERT INTO `d_dept` VALUES ('00010003', '研发一组', '0003');
INSERT INTO `d_dept` VALUES ('0002', '人事部', '0');
INSERT INTO `d_dept` VALUES ('00020003', '研发二组', '0003');
INSERT INTO `d_dept` VALUES ('0003', '研发部', '0');

-- ----------------------------
-- Table structure for `d_employee`
-- ----------------------------
DROP TABLE IF EXISTS `d_employee`;
CREATE TABLE `d_employee` (
  `e_id` varchar(255) NOT NULL,
  `e_name` varchar(255) DEFAULT '',
  `dept_id` varchar(255) DEFAULT '',
  PRIMARY KEY (`e_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of d_employee
-- ----------------------------
INSERT INTO `d_employee` VALUES ('0001', '张三', '0001');
INSERT INTO `d_employee` VALUES ('0002', '李四', '0001');
INSERT INTO `d_employee` VALUES ('0003', '王五', '0002');
INSERT INTO `d_employee` VALUES ('0004', '赵六', '0002');

-- ----------------------------
-- Table structure for `d_score`
-- ----------------------------
DROP TABLE IF EXISTS `d_score`;
CREATE TABLE `d_score` (
  `ds_id` varchar(255) DEFAULT NULL,
  `course_id` varchar(255) DEFAULT '',
  `ds_score` int(11) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of d_score
-- ----------------------------
INSERT INTO `d_score` VALUES ('0001', '0001', '80');
INSERT INTO `d_score` VALUES ('0001', '0002', '90');
INSERT INTO `d_score` VALUES ('0001', '0003', '99');
INSERT INTO `d_score` VALUES ('0002', '0001', '59');
INSERT INTO `d_score` VALUES ('0002', '0002', '60');
INSERT INTO `d_score` VALUES ('0002', '0003', '80');
INSERT INTO `d_score` VALUES ('0003', '0001', '50');
INSERT INTO `d_score` VALUES ('0003', '0002', '55');
INSERT INTO `d_score` VALUES ('0003', '0003', '56');

-- ----------------------------
-- Table structure for `d_student`
-- ----------------------------
DROP TABLE IF EXISTS `d_student`;
CREATE TABLE `d_student` (
  `s_id` varchar(255) NOT NULL,
  `s_name` varchar(255) DEFAULT '',
  `s_birthday` date DEFAULT NULL,
  `s_sex` char(50) DEFAULT NULL,
  PRIMARY KEY (`s_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of d_student
-- ----------------------------
INSERT INTO `d_student` VALUES ('0001', '猴子', '1989-01-01', '男');
INSERT INTO `d_student` VALUES ('0002', '猴子', '1990-12-21', '女');
INSERT INTO `d_student` VALUES ('0003', '马云', '1991-12-21', '男');
INSERT INTO `d_student` VALUES ('0004', '王思聪', '1990-05-20', '男');

-- ----------------------------
-- Table structure for `d_teacher`
-- ----------------------------
DROP TABLE IF EXISTS `d_teacher`;
CREATE TABLE `d_teacher` (
  `t_id` varchar(255) NOT NULL,
  `t_name` varchar(255) DEFAULT '',
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of d_teacher
-- ----------------------------
INSERT INTO `d_teacher` VALUES ('0001', '孟扎扎');
INSERT INTO `d_teacher` VALUES ('0002', '马化腾');
INSERT INTO `d_teacher` VALUES ('0003', 'NULL');
INSERT INTO `d_teacher` VALUES ('0004', '');

-- ----------------------------
-- Table structure for `file`
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '文件名',
  `path` varchar(255) NOT NULL DEFAULT '' COMMENT '路径',
  `type` varchar(255) NOT NULL DEFAULT '' COMMENT '类型',
  `size` varchar(255) NOT NULL DEFAULT '' COMMENT '大小',
  `parent_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '父级ID',
  `personal` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否是个人网盘',
  `created_time` datetime NOT NULL COMMENT '创造时间',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of file
-- ----------------------------
INSERT INTO `file` VALUES ('1', '根目录', '/', '文件夹', '-', '0', '1', '2020-01-01 00:00:00', '1');
INSERT INTO `file` VALUES ('2', '美国队长', '/upload/file/1606142970336-美国队长.jpg', 'jpg', '137KB', '1', '0', '2020-11-23 22:49:30', '1');
INSERT INTO `file` VALUES ('4', '图像', '/upload/file/1606143033010-图像.jpeg', 'jpeg', '13KB', '3', '0', '2020-11-23 22:50:33', '1');
INSERT INTO `file` VALUES ('7', '可达鸭', '/upload/file/1606316516840-图像.jpeg', 'jpeg', '13KB', '6', '0', '2020-11-25 23:01:57', '1');
INSERT INTO `file` VALUES ('8', '美国队长', '/upload/file/1606317416610-美国队长.jpg', 'jpg', '137KB', '1', '1', '2020-11-25 23:16:57', '1');
INSERT INTO `file` VALUES ('9', '《Java小咖秀全级别工程狮面试手册》', '/upload/file/1606317427670-《Java小咖秀全级别工程狮面试手册》.pdf', 'pdf', '12MB', '1', '1', '2020-11-25 23:17:08', '1');
INSERT INTO `file` VALUES ('13', 'lipan', '/', '文件夹', '-', '1', '0', '2020-11-28 14:41:12', '1');
INSERT INTO `file` VALUES ('14', '11', '/', '文件夹', '-', '13', '0', '2020-11-28 14:41:25', '1');
INSERT INTO `file` VALUES ('25', '图像', '/upload/file/1606748693608-图像.jpeg', 'jpeg', '13KB', '24', '0', '2020-11-30 15:04:54', '1');
INSERT INTO `file` VALUES ('26', '2222', '/', '文件夹', '-', '13', '0', '2020-11-30 15:05:09', '1');
INSERT INTO `file` VALUES ('27', 'echarts', '/upload/file/1606748723033-echarts.png', 'png', '21KB', '26', '0', '2020-11-30 15:05:23', '1');
INSERT INTO `file` VALUES ('28', 'pp', '/upload/file/1625208525302-pp.png', 'png', '2KB', '1', '1', '2021-07-02 06:48:45', '1');

-- ----------------------------
-- Table structure for `leave`
-- ----------------------------
DROP TABLE IF EXISTS `leave`;
CREATE TABLE `leave` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `begin_date` date NOT NULL,
  `end_date` date NOT NULL,
  `reason` varchar(500) NOT NULL DEFAULT '',
  `comment` varchar(500) DEFAULT '',
  `type` varchar(4) NOT NULL DEFAULT '',
  `status` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of leave
-- ----------------------------
INSERT INTO `leave` VALUES ('1', '1', '2020-11-23', '2020-11-24', '结婚', '', '婚假', '0');
INSERT INTO `leave` VALUES ('2', '1', '2020-12-01', '2020-12-02', '1', '同意', '事假', '1');
INSERT INTO `leave` VALUES ('3', '1', '2020-11-30', '2020-12-01', '22', '不同意', '婚假', '2');

-- ----------------------------
-- Table structure for `notice`
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL DEFAULT '',
  `has_read` tinyint(4) NOT NULL DEFAULT '0',
  `created_time` datetime NOT NULL,
  `receiver_name` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES ('1', '不同意', '0', '2020-12-29 20:31:52', 'admin');
INSERT INTO `notice` VALUES ('2', '拒绝', '0', '2020-12-29 20:40:27', 'admin');

-- ----------------------------
-- Table structure for `st_att`
-- ----------------------------
DROP TABLE IF EXISTS `st_att`;
CREATE TABLE `st_att` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT '' COMMENT '文件名',
  `slot_id` varchar(64) DEFAULT '' COMMENT '批次',
  `file_cate` varchar(64) DEFAULT NULL COMMENT '分类',
  `type` varchar(64) DEFAULT '' COMMENT '文件类型',
  `file_size` bigint(20) DEFAULT '0' COMMENT '文件大小',
  `origin_name` varchar(64) DEFAULT '' COMMENT '源文件名',
  `path` varchar(64) DEFAULT '' COMMENT '存储路径',
  `description` varchar(256) DEFAULT '' COMMENT '描述',
  `yn_flag` char(2) DEFAULT '1' COMMENT '有效标志',
  `creator` varchar(32) DEFAULT '' COMMENT '创建人',
  `editor` varchar(32) DEFAULT '' COMMENT '修改人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='附件表';

-- ----------------------------
-- Records of st_att
-- ----------------------------
INSERT INTO `st_att` VALUES ('1', null, 'fb135e8c-15c5-4248-91d1-ce6cfd762315', null, 'text/plain', '24', 'test.txt', 'sysmgr\\att\\upload\\2019\\09\\20190917103547386_.txt', null, '0', null, 'admin', '2019-09-17 02:35:45', '2019-09-17 03:10:06');
INSERT INTO `st_att` VALUES ('2', null, '6e4b7792-b047-4bc5-a776-60ec1ea800bc', null, 'text/plain', '24', 'test.txt', 'sysmgr\\att\\upload\\2019\\09\\20190917104316003_.txt', null, '0', null, 'admin', '2019-09-17 02:43:13', '2019-09-17 03:10:11');
INSERT INTO `st_att` VALUES ('3', null, '1123b4ff-dc8e-463a-a667-a98b5fac286b', null, 'text/plain', '24', 'test.txt', 'sysmgr\\att\\upload\\2019\\09\\20190917104429759_.txt', null, '0', 'admin', 'admin', '2019-09-17 02:44:27', '2020-08-12 07:26:58');
INSERT INTO `st_att` VALUES ('4', null, 'a5805f49-50ae-482b-a08a-4f5e481d0bb5', null, 'text/plain', '24', 'test.txt', 'sysmgr\\att\\upload\\2019\\09\\20190917104607876_.txt', null, '1', 'admin', 'admin', '2019-09-17 02:46:07', '2019-09-17 02:46:07');
INSERT INTO `st_att` VALUES ('5', null, 'ecd4d39d-1c12-40d6-af53-3e25e298a577', null, 'text/plain', '24', 'test.txt', 'sysmgr\\att\\upload\\2019\\09\\20190917105941981_.txt', null, '1', 'admin', 'admin', '2019-09-17 02:59:41', '2019-09-17 02:59:41');
INSERT INTO `st_att` VALUES ('6', null, '4ca31b41-9e3f-4e45-a664-83224b42dc17', null, 'text/plain', '24', 'test.txt', 'sysmgr\\att\\upload\\2019\\09\\20190917110251321_.txt', null, '1', 'admin', 'admin', '2019-09-17 03:02:49', '2019-09-17 03:02:49');
INSERT INTO `st_att` VALUES ('7', null, '9317023f-0b72-4709-a452-c6631d26988a', null, 'text/plain', '24', 'test.txt', 'sysmgr\\att\\upload\\2019\\09\\20190917111001050_.txt', null, '1', 'admin', 'admin', '2019-09-17 03:10:01', '2019-09-17 03:10:01');
INSERT INTO `st_att` VALUES ('8', null, '35f2c37a-2b8e-4d2c-a9be-782656897c96', null, 'application/octet-stream', '96', 'test.rar', 'sysmgr\\att\\upload\\2019\\09\\20190917111905377_.rar', null, '1', 'admin', 'admin', '2019-09-17 03:19:05', '2019-09-17 03:19:05');
INSERT INTO `st_att` VALUES ('9', null, '1829f0c9-6aa8-4634-a206-28b50d6d39d4', null, 'application/octet-stream', '96', 'test.rar', 'sysmgr\\att\\upload\\2019\\09\\20190917112253117_.rar', null, '1', 'admin', 'admin', '2019-09-17 03:22:53', '2019-09-17 03:22:53');
INSERT INTO `st_att` VALUES ('10', null, '8df3f2af-3830-496a-afee-af76bca3d699', null, '.rar', '96', 'test.rar', 'sysmgr\\att\\upload\\2019\\09\\20190917112513577_.rar', null, '1', 'admin', 'admin', '2019-09-17 03:25:14', '2019-09-17 03:25:14');
INSERT INTO `st_att` VALUES ('11', null, 'aa', null, '.png', '142525', 'SSO.png', '11\\2020\\08\\20200812165238537_.png', null, '0', 'admin', 'admin', '2020-08-12 08:52:39', '2020-08-12 09:04:56');
INSERT INTO `st_att` VALUES ('12', null, 'aa', null, '.png', '181858', 'shiro+redis登录认证.png', '11\\2020\\08\\20200812165659503_.png', null, '0', 'admin', 'admin', '2020-08-12 08:57:00', '2020-08-12 09:04:58');
INSERT INTO `st_att` VALUES ('13', null, '第一批次', null, '.jpeg', '45399', '3b292df5e0fe99257730fddd24c843d88cb171e2.jpeg', 'sysmgr\\att\\upload\\2020\\08\\20200813135013169_.jpeg', null, '0', 'admin', 'admin', '2020-08-13 05:50:13', '2020-09-11 06:33:15');
INSERT INTO `st_att` VALUES ('14', null, '第一批次', null, '.jpg', '5908', 'tp2.jpg', 'sysmgr\\att\\upload\\2020\\09\\20200911142915167_.jpg', null, '0', 'admin', 'admin', '2020-09-11 06:29:15', '2020-09-14 06:53:44');
INSERT INTO `st_att` VALUES ('15', null, '第一批次', null, '.jpg', '5908', 'tp2.jpg', 'sysmgr\\att\\upload\\2020\\09\\20200914145357431_.jpg', null, '0', 'admin', 'admin', '2020-09-14 06:53:57', '2020-09-14 07:03:59');
INSERT INTO `st_att` VALUES ('16', null, '第一批次', null, '.jpg', '5908', 'tp2.jpg', 'sysmgr\\att\\upload\\2020\\09\\20200914150407051_.jpg', null, '1', 'admin', 'admin', '2020-09-14 07:04:07', '2020-09-14 07:04:07');
INSERT INTO `st_att` VALUES ('17', null, '第一批次', null, '.png', '111388', 'Snipaste_2020-09-10_15-38-22.png', 'sysmgr\\att\\upload\\2020\\09\\20200914151202294_.png', null, '1', 'admin', 'admin', '2020-09-14 07:12:02', '2020-09-14 07:12:02');
INSERT INTO `st_att` VALUES ('18', null, '第一批次', null, '.jpg', '5908', 'tp2.jpg', 'sysmgr\\att\\upload\\2020\\09\\20200914153941751_.jpg', null, '1', 'admin', 'admin', '2020-09-14 07:39:42', '2020-09-14 07:39:42');
INSERT INTO `st_att` VALUES ('19', null, '第一批次', null, '.png', '374443', 'Snipaste_2020-09-10_15-39-00.png', 'sysmgr\\att\\upload\\2020\\09\\20200914154355914_.png', null, '1', 'admin', 'admin', '2020-09-14 07:43:56', '2020-09-14 07:43:56');
INSERT INTO `st_att` VALUES ('20', null, '第一批次', null, '.png', '111388', 'Snipaste_2020-09-10_15-38-22.png', 'sysmgr\\att\\upload\\2020\\10\\20201009113102005_.png', null, '1', 'admin', 'admin', '2020-10-09 03:31:02', '2020-10-09 03:31:02');
INSERT INTO `st_att` VALUES ('21', null, '第一批次', null, '.png', '0', 'Snipaste_2020-09-10_15-39-00.png', 'sysmgr\\att\\upload\\2020\\10\\20201009113908569_.png', null, '0', 'admin', 'admin', '2020-10-09 03:39:09', '2020-10-09 03:43:32');
INSERT INTO `st_att` VALUES ('22', null, '第一批次', null, '.jpg', '5908', 'tp2.jpg', 'sysmgr\\att\\upload\\2020\\10\\20201013113642075_.jpg', null, '1', 'admin', 'admin', '2020-10-13 03:36:42', '2020-10-13 03:36:42');
INSERT INTO `st_att` VALUES ('23', null, '第一批次', null, '.pdf', '817167', 'Spring面试题总结.pdf', 'sysmgr\\att\\upload\\2021\\06\\20210615183517572_.pdf', null, '1', 'admin', 'admin', '2021-06-15 10:35:18', '2021-06-15 10:35:18');
INSERT INTO `st_att` VALUES ('24', null, '第一批次', null, '.pdf', '208646', 'linux常用命令.pdf', 'D:/upload/sysmgr/att/upload/2021/07/20210702100846395_.pdf', null, '1', 'admin', 'admin', '2021-07-02 02:08:46', '2021-07-02 06:59:43');
INSERT INTO `st_att` VALUES ('25', null, '第一批次', null, '.png', '2151', 'pp.png', 'sysmgr\\att\\upload\\2021\\07\\20210702121927435_.png', null, '1', 'admin', 'admin', '2021-07-02 04:19:27', '2021-07-02 04:19:27');
INSERT INTO `st_att` VALUES ('26', 'pp.png', null, null, '.png', '2151', 'pp.png', 'D:\\upload\\2021-07-02 15:28:22.654\\pp.png', null, '1', 'admin', null, '2021-07-02 07:28:23', '2021-07-02 07:28:22');
INSERT INTO `st_att` VALUES ('27', 'linux常用命令.pdf', null, null, '.pdf', '208646', 'linux常用命令.pdf', 'D:\\upload\\2021-07-02 15:30:51.497\\linux常用命令.pdf', null, '1', 'admin', null, '2021-07-02 07:30:52', '2021-07-02 07:30:51');
INSERT INTO `st_att` VALUES ('28', 'Java基础面试题.pdf', null, null, '.pdf', '1014627', 'Java基础面试题.pdf', 'D:\\upload\\2021-07-02\\Java基础面试题.pdf', null, '1', 'admin', null, '2021-07-02 07:36:20', '2021-07-02 07:36:19');
INSERT INTO `st_att` VALUES ('29', 'pp.png', null, null, '.png', '2151', 'pp.png', 'D:\\upload\\2021-07-02\\pp.png', null, '1', 'admin', null, '2021-07-02 07:37:15', '2021-07-02 07:37:14');
INSERT INTO `st_att` VALUES ('30', 'pp.png', null, null, '.png', '2151', 'pp.png', 'D:\\upload\\2021-07-02\\pp.png', null, '1', 'admin', null, '2021-07-02 08:11:24', '2021-07-02 08:11:23');
INSERT INTO `st_att` VALUES ('31', null, '第一批次', null, '.png', '2151', 'pp.png', 'sysmgr\\att\\upload\\2021\\07\\20210702161143744_.png', null, '1', 'admin', null, '2021-07-02 08:11:44', '2021-07-02 08:11:43');
INSERT INTO `st_att` VALUES ('32', null, null, null, '.png', null, '666ppp.png', 'D:\\upload\\2021-07-02\\pp.png', null, '1', null, null, null, '2021-07-12 04:33:43');
INSERT INTO `st_att` VALUES ('33', null, null, null, '.png', null, '888.png', 'D:\\upload\\2021-07-02\\pp.png', null, '1', null, null, null, '2021-07-12 04:33:43');

-- ----------------------------
-- Table structure for `st_attachment`
-- ----------------------------
DROP TABLE IF EXISTS `st_attachment`;
CREATE TABLE `st_attachment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sequence` varchar(20) NOT NULL DEFAULT '',
  `file_name` varchar(40) NOT NULL DEFAULT '',
  `file_path` varchar(200) NOT NULL DEFAULT '',
  `upload_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `uploader` bigint(20) NOT NULL,
  `STATUS` decimal(2,0) NOT NULL DEFAULT '1' COMMENT '1 正常 0 已被删除',
  `deleter` bigint(20) DEFAULT NULL,
  `delete_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `real_file_name` varchar(80) DEFAULT '',
  `file_size` bigint(20) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `SYSTEM_FILE_SYSTEM_STAFF_id_fk` (`uploader`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='附件表';

-- ----------------------------
-- Records of st_attachment
-- ----------------------------
INSERT INTO `st_attachment` VALUES ('5', 'ATS200814000012', '面试问题总结.docx', 'D:\\bird_files\\ATS200814000012_面试问题总结.docx', '2020-08-14 05:12:43', '1', '1', null, '2020-08-14 05:12:42', 'ATS200814000012_面试问题总结.docx', '11671');
INSERT INTO `st_attachment` VALUES ('6', 'ATS200814000031', '面试问题总结.docx', 'D:\\bird_files\\ATS200814000031_面试问题总结.docx', '2020-08-14 05:28:45', '1', '1', null, '2020-08-14 05:28:45', 'ATS200814000031_面试问题总结.docx', '11671');
INSERT INTO `st_attachment` VALUES ('13', 'ATS200814000103', '图标.png', 'D:\\bird_files\\ATS200814000103_图标.png', '2020-08-14 07:24:31', '1', '1', null, '2020-08-14 07:24:31', 'ATS200814000103_图标.png', '25292');
INSERT INTO `st_attachment` VALUES ('14', 'ATS200814000110', '鱼片.png', 'D:\\bird_files\\ATS200814000110_鱼片.png', '2020-08-14 07:24:39', '1', '1', null, '2020-08-14 07:24:39', 'ATS200814000110_鱼片.png', '625');
INSERT INTO `st_attachment` VALUES ('18', 'ATS200814000159', 'STORY-ADMIN.pdf', 'D:\\bird_files\\ATS200814000159_STORY-ADMIN.pdf', '2020-08-14 08:00:07', '1', '1', null, '2020-08-14 08:00:07', 'ATS200814000159_STORY-ADMIN.pdf', '41025');
INSERT INTO `st_attachment` VALUES ('19', 'ATS200814000161', '各种规格材料说明.docx', 'D:\\bird_files\\ATS200814000161_各种规格材料说明.docx', '2020-08-14 08:32:20', '1', '1', null, '2020-08-14 08:32:19', 'ATS200814000161_各种规格材料说明.docx', '11647');
INSERT INTO `st_attachment` VALUES ('20', 'ATS200827000179', '读卡器配置文档.docx', 'D:\\bird_files\\ATS200827000179_读卡器配置文档.docx', '2020-08-27 08:34:51', '1', '1', null, '2020-08-27 08:34:50', 'ATS200827000179_读卡器配置文档.docx', '348769');
INSERT INTO `st_attachment` VALUES ('21', 'ATS200827000180', '面试问题总结.docx', 'D:\\bird_files\\ATS200827000180_面试问题总结.docx', '2020-08-27 08:37:41', '1', '1', null, '2020-08-27 08:37:41', 'ATS200827000180_面试问题总结.docx', '11671');
INSERT INTO `st_attachment` VALUES ('22', 'ATS200827000180', '各种规格材料说明.docx', 'D:\\bird_files\\ATS200827000180_各种规格材料说明.docx', '2020-08-27 08:37:42', '1', '1', null, '2020-08-27 08:37:41', 'ATS200827000180_各种规格材料说明.docx', '11647');
INSERT INTO `st_attachment` VALUES ('23', 'ATS200827000197', '少儿银行项目归档说明.docx', 'D:\\bird_files\\ATS200827000197_少儿银行项目归档说明.docx', '2020-08-27 08:41:44', '1', '1', null, '2020-08-27 08:41:43', 'ATS200827000197_少儿银行项目归档说明.docx', '18471');
INSERT INTO `st_attachment` VALUES ('24', 'ATS200827000193', '新增学校扩展服务部署.docx', 'D:\\bird_files\\ATS200827000193_新增学校扩展服务部署.docx', '2020-08-27 08:41:44', '1', '1', null, '2020-08-27 08:41:43', 'ATS200827000193_新增学校扩展服务部署.docx', '20191');
INSERT INTO `st_attachment` VALUES ('25', 'ATS200827000202', '新增学校扩展服务部署.docx', 'D:\\bird_files\\ATS200827000202_新增学校扩展服务部署.docx', '2020-08-27 08:46:03', '1', '1', null, '2020-08-27 08:46:03', 'ATS200827000202_新增学校扩展服务部署.docx', '20191');
INSERT INTO `st_attachment` VALUES ('26', 'ATS200827000204', '少儿银行项目归档说明.docx', 'D:\\bird_files\\ATS200827000204_少儿银行项目归档说明.docx', '2020-08-27 08:46:03', '1', '1', null, '2020-08-27 08:46:03', 'ATS200827000204_少儿银行项目归档说明.docx', '18471');
INSERT INTO `st_attachment` VALUES ('27', 'ATS200911000210', 'tp1.jfif', 'D:\\bird_files\\ATS200911000210_tp1.jfif', '2020-09-11 07:29:53', '1', '1', null, '2020-09-11 07:29:53', 'ATS200911000210_tp1.jfif', '5074');
INSERT INTO `st_attachment` VALUES ('28', 'ATS200911000219', 'tp2.jpg', 'D:\\bird_files\\ATS200911000219_tp2.jpg', '2020-09-11 07:29:54', '1', '1', null, '2020-09-11 07:29:53', 'ATS200911000219_tp2.jpg', '5908');
INSERT INTO `st_attachment` VALUES ('29', 'ATS200914000223', 'tp1.jfif', 'D:\\bird_files\\ATS200914000223_tp1.jfif', '2020-09-14 04:04:15', '1', '1', null, '2020-09-14 04:04:14', 'ATS200914000223_tp1.jfif', '5074');
INSERT INTO `st_attachment` VALUES ('30', 'ATS200914000231', '面试问题总结.docx', 'D:\\bird_files\\ATS200914000231_面试问题总结.docx', '2020-09-14 06:19:45', '1', '1', null, '2020-09-14 06:19:45', 'ATS200914000231_面试问题总结.docx', '11671');
INSERT INTO `st_attachment` VALUES ('31', 'ATS200921000243', 'Snipaste_2020-09-10_15-38-22.png', 'D:\\bird_files\\ATS200921000243_Snipaste_2020-09-10_15-38-22.png', '2020-09-21 07:45:32', '1', '1', null, '2020-09-21 07:45:31', 'ATS200921000243_Snipaste_2020-09-10_15-38-22.png', '111388');
INSERT INTO `st_attachment` VALUES ('32', 'ATS200921000243', 'Snipaste_2020-09-10_15-39-00.png', 'D:\\bird_files\\ATS200921000243_Snipaste_2020-09-10_15-39-00.png', '2020-09-21 07:45:32', '1', '1', null, '2020-09-21 07:45:31', 'ATS200921000243_Snipaste_2020-09-10_15-39-00.png', '374443');
INSERT INTO `st_attachment` VALUES ('33', 'ATS200921000253', 'Snipaste_2020-09-10_15-47-02.png', 'D:\\bird_files\\ATS200921000253_Snipaste_2020-09-10_15-47-02.png', '2020-09-21 08:00:28', '1', '1', null, '2020-09-21 08:00:27', 'ATS200921000253_Snipaste_2020-09-10_15-47-02.png', '18636');
INSERT INTO `st_attachment` VALUES ('34', 'ATS200921000257', 'Snipaste_2020-09-10_15-49-07.png', 'D:\\bird_files\\ATS200921000257_Snipaste_2020-09-10_15-49-07.png', '2020-09-21 08:00:28', '1', '1', null, '2020-09-21 08:00:28', 'ATS200921000257_Snipaste_2020-09-10_15-49-07.png', '11080');
INSERT INTO `st_attachment` VALUES ('35', 'ATS200921000267', 'Snipaste_2020-09-10_15-53-43.png', 'D:\\bird_files\\ATS200921000267_Snipaste_2020-09-10_15-53-43.png', '2020-09-21 08:03:48', '1', '1', null, '2020-09-21 08:03:47', 'ATS200921000267_Snipaste_2020-09-10_15-53-43.png', '123179');
INSERT INTO `st_attachment` VALUES ('36', 'ATS200921000267', 'Snipaste_2020-09-10_15-39-00.png', 'D:\\bird_files\\ATS200921000267_Snipaste_2020-09-10_15-39-00.png', '2020-09-21 08:03:48', '1', '1', null, '2020-09-21 08:03:47', 'ATS200921000267_Snipaste_2020-09-10_15-39-00.png', '374443');
INSERT INTO `st_attachment` VALUES ('37', 'ATS200921000278', 'Snipaste_2020-09-10_15-47-02.png', 'D:\\bird_files\\ATS200921000278_Snipaste_2020-09-10_15-47-02.png', '2020-09-21 08:07:58', '1', '1', null, '2020-09-21 08:07:58', 'ATS200921000278_Snipaste_2020-09-10_15-47-02.png', '18636');
INSERT INTO `st_attachment` VALUES ('38', 'ATS200921000279', 'Snipaste_2020-09-10_15-49-07.png', 'D:\\bird_files\\ATS200921000279_Snipaste_2020-09-10_15-49-07.png', '2020-09-21 08:07:58', '1', '1', null, '2020-09-21 08:07:58', 'ATS200921000279_Snipaste_2020-09-10_15-49-07.png', '11080');
INSERT INTO `st_attachment` VALUES ('39', 'ATS200921000287', 'Snipaste_2020-09-10_15-44-43.png', 'D:\\bird_files\\ATS200921000287_Snipaste_2020-09-10_15-44-43.png', '2020-09-21 08:07:58', '1', '1', null, '2020-09-21 08:07:58', 'ATS200921000287_Snipaste_2020-09-10_15-44-43.png', '18087');
INSERT INTO `st_attachment` VALUES ('40', 'ATS200927000290', 'carbon.png', 'D:\\bird_files\\ATS200927000290_carbon.png', '2020-09-27 01:24:01', '1', '1', null, '2020-09-27 01:24:01', 'ATS200927000290_carbon.png', '355405');
INSERT INTO `st_attachment` VALUES ('41', 'ATS200927000305', 'Excel导出结果.xlsx', 'D:\\bird_files\\ATS200927000305_Excel导出结果.xlsx', '2020-09-27 01:26:45', '1', '1', null, '2020-09-27 01:26:44', 'ATS200927000305_Excel导出结果.xlsx', '3748');
INSERT INTO `st_attachment` VALUES ('42', 'ATS200927000319', 'Excel导入模板.xlsx', 'D:\\bird_files\\ATS200927000319_Excel导入模板.xlsx', '2020-09-27 01:26:45', '1', '1', null, '2020-09-27 01:26:45', 'ATS200927000319_Excel导入模板.xlsx', '5788');
INSERT INTO `st_attachment` VALUES ('43', 'ATS201022000325', 'pp.png', 'D:\\bird_files\\ATS201022000325_pp.png', '2020-10-22 03:07:00', '1', '1', null, '2020-10-22 03:07:00', 'ATS201022000325_pp.png', '2151');

-- ----------------------------
-- Table structure for `st_authority`
-- ----------------------------
DROP TABLE IF EXISTS `st_authority`;
CREATE TABLE `st_authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(64) COLLATE utf8_bin DEFAULT '' COMMENT '权限名称',
  `code` varchar(32) COLLATE utf8_bin DEFAULT '' COMMENT '权限编码',
  `full_id` varchar(200) COLLATE utf8_bin DEFAULT '' COMMENT '编号路径',
  `authority_desc` varchar(200) COLLATE utf8_bin DEFAULT '' COMMENT '权限描述',
  `show_order` int(11) DEFAULT '0' COMMENT '排序',
  `pid` bigint(20) DEFAULT '0' COMMENT '父Id',
  `yn_flag` char(2) COLLATE utf8_bin DEFAULT '1' COMMENT '有效标志',
  `creator` varchar(32) COLLATE utf8_bin DEFAULT '' COMMENT '创建人',
  `editor` varchar(32) COLLATE utf8_bin DEFAULT '' COMMENT '修改人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=186 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='权限表';

-- ----------------------------
-- Records of st_authority
-- ----------------------------
INSERT INTO `st_authority` VALUES ('1', '公共', 'public', '0', null, '1', '0', '1', null, null, null, '2019-07-26 16:44:51');
INSERT INTO `st_authority` VALUES ('2', '系统管理', 'sysmgr', '0', null, '1', '0', '1', null, null, null, '2019-07-26 11:56:42');
INSERT INTO `st_authority` VALUES ('3', '用户管理', 'sysmgr.user', '0-2', null, '1', '2', '1', null, null, null, '2019-07-26 18:44:24');
INSERT INTO `st_authority` VALUES ('4', '角色管理', 'sysmgr.rule', '0-2', null, '2', '2', '1', null, null, null, '2019-07-26 11:53:51');
INSERT INTO `st_authority` VALUES ('5', '菜单管理', 'sysmgr.resource', '0-2', null, '3', '2', '1', null, null, null, '2019-07-26 18:44:50');
INSERT INTO `st_authority` VALUES ('6', '权限管理', 'sysmgr.authority', '0-2', null, '4', '2', '1', null, null, null, '2019-07-26 18:44:53');
INSERT INTO `st_authority` VALUES ('7', '查询用户', 'sysmgr.user.query', '0-2-3', null, '1', '3', '1', null, null, null, '2019-07-26 18:44:57');
INSERT INTO `st_authority` VALUES ('8', '编辑用户', 'sysmgr.user.save', '0-2-3', null, '2', '3', '1', null, null, null, '2019-07-26 18:45:00');
INSERT INTO `st_authority` VALUES ('9', '删除用户', 'sysmgr.user.delete', '0-2-3', null, '3', '3', '1', null, null, null, '2019-07-26 18:45:04');
INSERT INTO `st_authority` VALUES ('10', '查询角色', 'sysmgr.role.query', '0-2-4', null, '1', '4', '1', null, null, null, '2019-07-26 18:45:06');
INSERT INTO `st_authority` VALUES ('11', '编辑角色', 'sysmgr.role.save', '0-2-4', null, '2', '4', '1', null, null, null, '2019-07-26 18:45:09');
INSERT INTO `st_authority` VALUES ('12', '删除角色', 'sysmgr.role.delete', '0-2-4', null, '3', '4', '1', null, null, null, '2019-07-26 18:45:13');
INSERT INTO `st_authority` VALUES ('13', '查询菜单', 'sysmgr.resource.query', '0-2-5', null, '1', '5', '1', null, null, null, '2019-07-26 11:50:34');
INSERT INTO `st_authority` VALUES ('14', '编辑菜单', 'sysmgr.resource.save', '0-2-5', null, '2', '5', '1', null, null, null, '2019-07-26 18:45:23');
INSERT INTO `st_authority` VALUES ('15', '删除菜单', 'sysmgr.resource.delete', '0-2-5', null, '3', '5', '1', null, null, null, '2019-07-26 18:45:26');
INSERT INTO `st_authority` VALUES ('16', '查询权限', 'sysmgr.authority.query', '0-2-6', null, '1', '6', '1', null, null, null, '2019-07-26 18:45:28');
INSERT INTO `st_authority` VALUES ('17', '编辑权限', 'sysmgr.authority.save', '0-2-6', null, '2', '6', '1', null, null, null, '2019-07-26 18:45:31');
INSERT INTO `st_authority` VALUES ('18', '删除权限', 'sysmgr.authority.delete', '0-2-6', null, '3', '6', '1', null, null, null, '2019-07-26 18:45:35');
INSERT INTO `st_authority` VALUES ('30', '登录日志', 'sysmgr.loginlog', '0-2', null, '5', '2', '0', 'admin', 'admin', '2019-07-26 03:11:50', '2020-10-28 01:40:42');
INSERT INTO `st_authority` VALUES ('31', '查询日志', 'sysmgr.loginlog.query', '0-2-30', null, '1', '30', '1', 'admin', 'admin', '2019-07-26 03:12:14', '2019-07-26 11:37:46');
INSERT INTO `st_authority` VALUES ('32', '删除日志', 'sysmgr.loginlog.delete', '0-2-30', null, '2', '30', '1', 'admin', 'admin', '2019-07-26 03:12:44', '2019-07-26 11:37:54');
INSERT INTO `st_authority` VALUES ('33', '基础信息', 'baseinfo', '0', null, '3', '0', '1', 'admin', 'admin', '2019-07-26 03:13:11', '2019-07-26 03:13:11');
INSERT INTO `st_authority` VALUES ('34', '字典管理', 'baseinfo.dict', '0-33', null, '1', '33', '1', 'admin', 'admin', '2019-07-26 03:13:31', '2019-07-26 03:13:31');
INSERT INTO `st_authority` VALUES ('35', '查询字典', 'baseinfo.dict.query', '0-33-34', null, '1', '34', '1', 'admin', 'admin', '2019-07-26 03:14:01', '2019-07-26 11:37:20');
INSERT INTO `st_authority` VALUES ('36', '编辑字典', 'baseinfo.dict.save', '0-33-34', null, '2', '34', '1', 'admin', 'admin', '2019-07-26 03:14:43', '2019-07-26 11:37:30');
INSERT INTO `st_authority` VALUES ('37', '删除字典', 'baseinfo.dict.delete', '0-33-34', null, '3', '34', '1', 'admin', 'admin', '2019-07-26 03:15:03', '2019-07-26 11:37:38');
INSERT INTO `st_authority` VALUES ('38', '附件管理', 'sysmgr.att', '0-2', null, '6', '2', '1', 'admin', 'admin', '2019-09-24 13:03:19', '2019-09-24 21:05:50');
INSERT INTO `st_authority` VALUES ('39', '查询附件', 'sysmgr.att.query', '0-2-38', null, '1', '38', '1', 'admin', 'admin', '2019-09-24 13:05:23', '2019-09-24 21:06:07');
INSERT INTO `st_authority` VALUES ('40', '删除附件', 'sysmgr.att.delete', '0-2-38', null, '2', '38', '1', 'admin', 'admin', '2019-09-24 13:06:55', '2019-09-24 21:08:27');
INSERT INTO `st_authority` VALUES ('41', '系统日志', 'sysmgr.syslog', '0-2', null, '7', '2', '0', 'admin', 'admin', '2019-09-24 13:09:29', '2020-10-28 01:40:48');
INSERT INTO `st_authority` VALUES ('42', '查询系统日志', 'sysmgr.syslog.query', '0-2-41', null, '1', '41', '1', 'admin', 'admin', '2019-09-24 13:13:39', '2019-09-24 13:13:39');
INSERT INTO `st_authority` VALUES ('43', '系统备份', 'sysmgr.backup', '0-2', null, '8', '2', '1', 'admin', 'admin', '2019-09-25 06:10:01', '2019-09-25 06:10:01');
INSERT INTO `st_authority` VALUES ('44', '查询备份', 'sysmgr.backup.query', '0-2-43', null, '1', '43', '1', 'admin', 'admin', '2019-09-25 06:10:15', '2019-09-25 06:10:15');
INSERT INTO `st_authority` VALUES ('45', '删除备份', 'sysmgr.backup.delete', '0-2-43', null, '2', '43', '1', 'admin', 'admin', '2019-09-25 06:10:35', '2019-09-25 06:12:43');
INSERT INTO `st_authority` VALUES ('46', '定时任务', 'sysmgr.schedulejob', '0-2', null, '9', '2', '0', 'admin', 'admin', '2019-09-25 06:11:03', '2020-08-21 08:41:19');
INSERT INTO `st_authority` VALUES ('47', '查询任务', 'sysmgr.schedulejob.query', '0-2-46', null, '1', '46', '1', 'admin', 'admin', '2019-09-25 06:11:35', '2019-09-25 06:12:34');
INSERT INTO `st_authority` VALUES ('48', '编辑任务', 'sysmgr.schedulejob.save', '0-2-46', null, '2', '46', '1', 'admin', 'admin', '2019-09-25 06:12:04', '2019-09-25 06:12:04');
INSERT INTO `st_authority` VALUES ('49', '删除任务', 'sysmgr.schedulejob.delete', '0-2-46', null, '3', '46', '1', 'admin', 'admin', '2019-09-25 06:12:25', '2019-09-25 06:12:25');
INSERT INTO `st_authority` VALUES ('50', '上传附件', 'sysmgr.att.upload', '0-2-38', null, '3', '38', '1', 'admin', 'admin', '2019-09-25 06:15:38', '2019-09-25 06:15:57');
INSERT INTO `st_authority` VALUES ('51', '个人空间', 'tool', '0', null, '10', '0', '1', 'admin', 'admin', '2019-09-25 07:11:47', '2019-09-25 07:11:47');
INSERT INTO `st_authority` VALUES ('52', '待办事项', 'tool.todo', '0-51', null, '1', '51', '1', 'admin', 'admin', '2019-09-25 07:13:25', '2019-09-25 07:13:25');
INSERT INTO `st_authority` VALUES ('53', '查询待办事项', 'tool.todo.query', '0-51-52', null, '1', '52', '1', 'admin', 'admin', '2019-09-25 07:13:41', '2019-09-25 07:13:41');
INSERT INTO `st_authority` VALUES ('54', '编辑待办事项', 'tool.todo.save', '0-51-52', null, '2', '52', '1', 'admin', 'admin', '2019-09-25 07:14:22', '2019-09-25 07:14:22');
INSERT INTO `st_authority` VALUES ('55', '删除待办事项', 'tool.todo.delete', '0-51-52', null, '3', '52', '1', 'admin', 'admin', '2019-09-25 07:14:45', '2019-09-25 07:14:45');
INSERT INTO `st_authority` VALUES ('56', '系统公告', 'sysmgr.inform', '0-2', null, '20', '2', '1', 'admin', 'admin', '2020-08-11 08:46:27', '2020-08-11 08:46:27');
INSERT INTO `st_authority` VALUES ('57', '查询列表', 'sysmgr.inform.query', '0-2-56', null, '1', '56', '1', 'admin', 'admin', '2020-08-11 08:47:25', '2020-08-13 01:14:08');
INSERT INTO `st_authority` VALUES ('58', '查看详情', 'sysmgr.inform.query', '0-2-56', null, '2', '56', '1', 'admin', 'admin', '2020-08-11 08:48:13', '2020-08-13 01:11:14');
INSERT INTO `st_authority` VALUES ('59', '新增公告', 'sysmgr.inform.save', '0-2-56', null, '3', '56', '1', 'admin', 'admin', '2020-08-11 08:48:51', '2020-08-11 08:48:51');
INSERT INTO `st_authority` VALUES ('60', '置顶', 'sysmgr.inform.top', '0-2-56', null, '4', '56', '1', 'admin', 'admin', '2020-08-11 08:49:32', '2020-08-13 01:11:27');
INSERT INTO `st_authority` VALUES ('61', '取消置顶', 'sysmgr.inform.untop', '0-2-56', null, '5', '56', '1', 'admin', 'admin', '2020-08-11 08:50:05', '2020-08-13 01:11:44');
INSERT INTO `st_authority` VALUES ('62', '附件上传下载', 'sysmgr.file', '0-2', null, '21', '2', '1', 'admin', 'admin', '2020-08-12 06:46:06', '2020-08-12 06:46:06');
INSERT INTO `st_authority` VALUES ('63', '附件上传', 'sysmgr.file.upload', '0-2-62', null, '1', '62', '1', 'admin', 'admin', '2020-08-12 06:46:38', '2020-08-12 06:46:38');
INSERT INTO `st_authority` VALUES ('64', '附件删除', 'sysmgr.file.delete', '0-2-62', null, '3', '62', '1', 'admin', 'admin', '2020-08-12 06:47:12', '2020-08-12 06:47:12');
INSERT INTO `st_authority` VALUES ('65', '附件下载', 'sysmgr.file.download', '0-2-62', null, '2', '62', '1', 'admin', 'admin', '2020-08-12 06:47:38', '2020-08-12 06:47:38');
INSERT INTO `st_authority` VALUES ('66', '撤销', 'sysmgr.inform.cancel', '0-2-56', null, '6', '56', '1', 'admin', 'admin', '2020-08-13 01:13:37', '2020-08-13 01:13:37');
INSERT INTO `st_authority` VALUES ('67', '过期', 'sysmgr.inform.outdate', '0-2-56', null, '7', '56', '1', 'admin', 'admin', '2020-08-13 01:14:39', '2020-08-13 01:14:39');
INSERT INTO `st_authority` VALUES ('68', '部门管理', 'sysmgr.dept', '0-2', null, '10', '2', '1', 'admin', 'admin', '2020-08-14 08:34:41', '2020-08-14 08:34:41');
INSERT INTO `st_authority` VALUES ('69', '查询权限', 'sysmgr.dept.query', '0-2-68', null, '1', '68', '1', 'admin', 'admin', '2020-08-14 08:35:12', '2020-08-14 08:38:03');
INSERT INTO `st_authority` VALUES ('70', '编辑权限', 'sysmgr.dept.save', '0-2-68', null, '2', '68', '1', 'admin', 'admin', '2020-08-14 08:35:41', '2020-08-14 08:38:17');
INSERT INTO `st_authority` VALUES ('71', '删除权限', 'sysmgr.dept.delete', '0-2-68', null, '3', '68', '1', 'admin', 'admin', '2020-08-14 08:37:32', '2020-08-14 08:38:30');
INSERT INTO `st_authority` VALUES ('72', '服务监控', 'monitor', '0', null, '1', '0', '1', 'admin', 'admin', '2020-08-21 07:54:45', '2020-08-21 07:54:45');
INSERT INTO `st_authority` VALUES ('73', '系统监控', 'monitor.server', '0-72', null, '1', '72', '1', 'admin', 'admin', '2020-08-21 07:55:37', '2020-08-21 07:55:37');
INSERT INTO `st_authority` VALUES ('74', '系统监控查询', 'monitor.server.query', '0-72-73', null, '1', '73', '1', 'admin', 'admin', '2020-08-21 07:56:44', '2020-08-21 07:56:44');
INSERT INTO `st_authority` VALUES ('75', '定时任务', 'monitor.schedulejob', '0-72', null, '2', '72', '1', 'admin', 'admin', '2020-08-21 08:27:21', '2020-08-21 08:30:06');
INSERT INTO `st_authority` VALUES ('76', '查询任务', 'monitor.schedulejob.query', '0-72-75', null, '1', '75', '1', 'admin', 'admin', '2020-08-21 08:28:12', '2020-08-21 08:28:12');
INSERT INTO `st_authority` VALUES ('77', '编辑任务', 'monitor.schedulejob.save', '0-72-75', null, '2', '75', '1', 'admin', 'admin', '2020-08-21 08:28:48', '2020-08-21 08:28:48');
INSERT INTO `st_authority` VALUES ('78', '删除任务', 'monitor.schedulejob.delete', '0-72-75', null, '3', '75', '1', 'admin', 'admin', '2020-08-21 08:29:21', '2020-08-21 08:29:41');
INSERT INTO `st_authority` VALUES ('79', 'swaggerAPI文档', 'tool.swagger', '0-51', null, '2', '51', '0', 'admin', 'admin', '2020-08-24 03:16:41', '2020-08-24 03:21:31');
INSERT INTO `st_authority` VALUES ('80', '查询API文档', 'tool.swagger.info', '0-51-79', null, '1', '79', '1', 'admin', 'admin', '2020-08-24 03:17:32', '2020-08-24 03:17:32');
INSERT INTO `st_authority` VALUES ('81', 'Swagger_API文档', 'document', '0', null, '1', '0', '1', 'admin', 'admin', '2020-08-24 03:24:20', '2020-08-24 03:24:20');
INSERT INTO `st_authority` VALUES ('82', 'swagger文档', 'document.swagger', '0-81', null, '1', '81', '1', 'admin', 'admin', '2020-08-24 03:25:20', '2020-08-24 03:25:20');
INSERT INTO `st_authority` VALUES ('83', '查询API接口文档', 'document.swagger.query', '0-81-82', null, '1', '82', '1', 'admin', 'admin', '2020-08-24 03:25:54', '2020-08-24 03:26:35');
INSERT INTO `st_authority` VALUES ('84', '数据监控', 'monitor.druid', '0-72', null, '3', '72', '1', 'admin', 'admin', '2020-08-24 06:39:18', '2020-08-24 06:39:18');
INSERT INTO `st_authority` VALUES ('85', '数据监控', 'monitor.druid', '0-72', null, '3', '72', '0', 'admin', 'admin', '2020-08-24 06:39:18', '2020-08-24 06:39:39');
INSERT INTO `st_authority` VALUES ('86', '数据监控', 'monitor.druid', '0-72', null, '3', '72', '0', 'admin', 'admin', '2020-08-24 06:39:18', '2020-08-24 06:39:36');
INSERT INTO `st_authority` VALUES ('87', '数据监控', 'monitor.druid', '0-72', null, '3', '72', '0', 'admin', 'admin', '2020-08-24 06:39:18', '2020-08-24 06:39:34');
INSERT INTO `st_authority` VALUES ('88', '数据监控', 'monitor.druid', '0-72', null, '3', '72', '0', 'admin', 'admin', '2020-08-24 06:39:19', '2020-08-24 06:39:31');
INSERT INTO `st_authority` VALUES ('89', '数据监控', 'monitor.druid', '0-72', null, '3', '72', '0', 'admin', 'admin', '2020-08-24 06:39:19', '2020-08-24 06:39:29');
INSERT INTO `st_authority` VALUES ('90', '数据监控查询', 'monitor.druid.query', '0-72-84', null, '1', '84', '1', 'admin', 'admin', '2020-08-24 06:40:20', '2020-08-24 06:40:20');
INSERT INTO `st_authority` VALUES ('91', '下载附件', 'sysmgr.att.download', '0-2-38', null, '4', '38', '1', 'admin', 'admin', '2020-09-11 03:36:05', '2020-09-11 03:36:05');
INSERT INTO `st_authority` VALUES ('92', 'wind管理', 'sys', '0', null, '6', '0', '0', 'admin', 'admin', '2020-10-23 08:49:50', '2020-10-23 14:23:01');
INSERT INTO `st_authority` VALUES ('93', 'wind管理', 'sys', '0', null, '6', '0', '0', 'admin', 'admin', '2020-10-23 08:49:50', '2020-10-23 08:49:59');
INSERT INTO `st_authority` VALUES ('94', 'w字典管理', 'sys.dict', '0-92', null, '1', '92', '1', 'admin', 'admin', '2020-10-23 08:51:05', '2020-10-23 08:54:28');
INSERT INTO `st_authority` VALUES ('95', 'w字典组管理', 'sys.dict.group', '0-92', null, '2', '92', '0', 'admin', 'admin', '2020-10-23 08:52:29', '2020-10-23 09:12:30');
INSERT INTO `st_authority` VALUES ('96', '字典查询', 'sys.dict.query', '0-92-94', null, '1', '94', '1', 'admin', 'admin', '2020-10-23 08:53:16', '2020-10-23 09:09:04');
INSERT INTO `st_authority` VALUES ('97', '字典保存', 'sys.dict.save', '0-92-94', null, '2', '94', '1', 'admin', 'admin', '2020-10-23 08:54:52', '2020-10-23 09:09:20');
INSERT INTO `st_authority` VALUES ('98', '字典删除', 'sys.dict.delete', '0-92-94', null, '3', '94', '1', 'admin', 'admin', '2020-10-23 08:55:17', '2020-10-23 09:09:32');
INSERT INTO `st_authority` VALUES ('99', '查询', 'sys.dict.group.query', '0-92-95', null, '1', '95', '1', 'admin', 'admin', '2020-10-23 08:56:19', '2020-10-23 08:56:19');
INSERT INTO `st_authority` VALUES ('100', '保存', 'sys.dict.group.save', '0-92-95', null, '2', '95', '1', 'admin', 'admin', '2020-10-23 08:56:44', '2020-10-23 08:56:44');
INSERT INTO `st_authority` VALUES ('101', '删除', 'sys.dict.group.delete', '0-92-95', null, '3', '95', '1', 'admin', 'admin', '2020-10-23 08:57:04', '2020-10-23 08:57:04');
INSERT INTO `st_authority` VALUES ('102', '刷新', 'sys.dict.force.refresh', '0-92-95', null, '4', '95', '1', 'admin', 'admin', '2020-10-23 08:57:48', '2020-10-23 08:57:48');
INSERT INTO `st_authority` VALUES ('103', '字典组查询', 'sys.dict.group.query', '0-92-94', null, '4', '94', '1', 'admin', 'admin', '2020-10-23 09:10:17', '2020-10-23 09:10:17');
INSERT INTO `st_authority` VALUES ('104', '字典组保存', 'sys.dict.group.save', '0-92-94', null, '5', '94', '1', 'admin', 'admin', '2020-10-23 09:10:55', '2020-10-23 09:10:55');
INSERT INTO `st_authority` VALUES ('105', '字典组删除', 'sys.dict.group.delete', '0-92-94', null, '6', '94', '1', 'admin', 'admin', '2020-10-23 09:11:30', '2020-10-23 09:11:30');
INSERT INTO `st_authority` VALUES ('106', '字典刷新', 'sys.dict.force.refresh', '0-92-94', null, '7', '94', '1', 'admin', 'admin', '2020-10-23 09:12:05', '2020-10-23 09:12:05');
INSERT INTO `st_authority` VALUES ('107', 'W字典管理', 'sysmgr.dict', '0-2', null, '23', '2', '1', 'admin', 'admin', '2020-10-23 14:13:37', '2020-10-23 14:13:37');
INSERT INTO `st_authority` VALUES ('108', '查询字典列表', 'sysmgr.dict.query', '0-2-107', null, '1', '107', '1', 'admin', 'admin', '2020-10-23 14:16:50', '2020-10-23 14:16:50');
INSERT INTO `st_authority` VALUES ('109', '保存字典', 'sysmgr.dict.save', '0-2-107', null, '2', '107', '1', 'admin', 'admin', '2020-10-23 14:17:35', '2020-10-23 14:17:35');
INSERT INTO `st_authority` VALUES ('110', '删除字典', 'sysmgr.dict.delete', '0-2-107', null, '3', '107', '1', 'admin', 'admin', '2020-10-23 14:18:42', '2020-10-23 14:18:42');
INSERT INTO `st_authority` VALUES ('111', '字典组查询', 'sysmgr.dict.group.query', '0-2-107', null, '4', '107', '1', 'admin', 'admin', '2020-10-23 14:20:02', '2020-10-27 02:21:38');
INSERT INTO `st_authority` VALUES ('112', '字典组保存', 'sysmgr.dict.group.save', '0-2-107', null, '5', '107', '1', 'admin', 'admin', '2020-10-23 14:20:58', '2020-10-23 14:20:58');
INSERT INTO `st_authority` VALUES ('113', '字典组删除', 'sysmgr.dict.group.delete', '0-2-107', null, '6', '107', '1', 'admin', 'admin', '2020-10-23 14:21:51', '2020-10-23 14:21:51');
INSERT INTO `st_authority` VALUES ('114', '字典刷新', 'sysmgr.dict.force.refresh', '0-2-107', null, '7', '107', '1', 'admin', 'admin', '2020-10-23 14:22:26', '2020-10-23 14:22:51');
INSERT INTO `st_authority` VALUES ('115', '车辆管理', 'sysmgr.car', '0-2', null, '24', '2', '0', 'admin', 'admin', '2020-10-26 15:31:27', '2020-10-27 14:12:20');
INSERT INTO `st_authority` VALUES ('116', '查询车辆列表', 'sysmgr.car.query', '0-2-107', null, '2', '107', '0', 'admin', 'admin', '2020-10-26 15:31:59', '2020-10-26 15:33:14');
INSERT INTO `st_authority` VALUES ('117', '查询车辆列表', 'sysmgr.car.query', '0-2-115', null, '1', '115', '1', 'admin', 'admin', '2020-10-26 15:33:02', '2020-10-26 15:33:02');
INSERT INTO `st_authority` VALUES ('118', '保存车辆', 'sysmgr.car.save', '0-2-115', null, '2', '115', '1', 'admin', 'admin', '2020-10-26 15:33:55', '2020-10-26 15:33:55');
INSERT INTO `st_authority` VALUES ('119', '删除车辆', 'sys.car.delete', '0-2-115', null, '3', '115', '1', 'admin', 'admin', '2020-10-26 15:34:34', '2020-10-26 15:34:34');
INSERT INTO `st_authority` VALUES ('120', '查询车辆组列表', 'sysmgr.car.carmodel.query', '0-2-115', null, '4', '115', '1', 'admin', 'admin', '2020-10-26 15:35:31', '2020-10-26 15:35:31');
INSERT INTO `st_authority` VALUES ('121', '保存车辆组', 'sysmgr.car.carmodel.save', '0-2-115', null, '5', '115', '1', 'admin', 'admin', '2020-10-26 15:36:11', '2020-10-26 15:36:11');
INSERT INTO `st_authority` VALUES ('122', '删除车辆组', 'sysmgr.car.carmodel.delete', '0-2-115', null, '6', '115', '1', 'admin', 'admin', '2020-10-26 15:36:45', '2020-10-26 15:36:45');
INSERT INTO `st_authority` VALUES ('123', '样例管理', 'test', '0', null, '11', '0', '1', 'admin', 'admin', '2020-10-27 06:46:30', '2020-10-27 06:46:37');
INSERT INTO `st_authority` VALUES ('124', '左树右表', 'test.treeandtable', '0-123', null, '1', '123', '1', 'admin', 'admin', '2020-10-27 06:48:02', '2020-10-27 07:45:29');
INSERT INTO `st_authority` VALUES ('125', '树形表格查询列表', 'test.treeandtable.list', '0-123-124', null, '1', '124', '1', 'admin', 'admin', '2020-10-27 06:48:32', '2020-10-27 06:50:38');
INSERT INTO `st_authority` VALUES ('126', '树形表格保存', 'test.treeandtable.save', '0-123-124', null, '2', '124', '1', 'admin', 'admin', '2020-10-27 06:49:00', '2020-10-27 06:49:00');
INSERT INTO `st_authority` VALUES ('127', '树形表格删除', 'test.treeandtable.delete', '0-123-124', null, '3', '124', '1', 'admin', 'admin', '2020-10-27 06:49:22', '2020-10-27 06:49:22');
INSERT INTO `st_authority` VALUES ('128', '树形表格', 'test.treetable', '0-123', null, '2', '123', '1', 'admin', 'admin', '2020-10-27 07:49:53', '2020-10-27 07:49:53');
INSERT INTO `st_authority` VALUES ('129', '查询树形表格列表', 'test.treetable.list', '0-123-128', null, '1', '128', '1', 'admin', 'admin', '2020-10-27 07:50:40', '2020-10-27 07:50:40');
INSERT INTO `st_authority` VALUES ('130', '树形表格保存', 'test.treetable.save', '0-123-128', null, '2', '128', '1', 'admin', 'admin', '2020-10-27 07:51:10', '2020-10-27 07:51:10');
INSERT INTO `st_authority` VALUES ('131', '树形表格删除', 'test.treetable.delete', '0-123-128', null, '3', '128', '1', 'admin', 'admin', '2020-10-27 07:51:44', '2020-10-27 07:51:44');
INSERT INTO `st_authority` VALUES ('132', '展开表格', 'test.expandtable', '0-123', null, '3', '123', '1', 'admin', 'admin', '2020-10-27 08:29:07', '2020-10-27 14:08:40');
INSERT INTO `st_authority` VALUES ('133', '查询列表', 'test.expandtable.list', '0-123-132', null, '1', '132', '1', 'admin', 'admin', '2020-10-27 08:29:29', '2020-10-27 08:29:29');
INSERT INTO `st_authority` VALUES ('134', '保存', 'test.expandtable.save', '0-123-132', null, '2', '132', '1', 'admin', 'admin', '2020-10-27 08:29:48', '2020-10-27 08:29:48');
INSERT INTO `st_authority` VALUES ('135', '删除', 'test.expandtable.delete', '0-123-132', null, '3', '132', '1', 'admin', 'admin', '2020-10-27 08:30:06', '2020-10-27 08:30:06');
INSERT INTO `st_authority` VALUES ('136', '文章表格', 'test.table', '0-123', null, '4', '123', '1', 'admin', 'admin', '2020-10-27 12:01:37', '2020-10-27 12:01:37');
INSERT INTO `st_authority` VALUES ('137', '查询文章表格列表', 'test.table.list', '0-123-136', null, '1', '136', '1', 'admin', 'admin', '2020-10-27 12:02:00', '2020-10-27 12:02:00');
INSERT INTO `st_authority` VALUES ('138', '保存文章表格列表', 'test.table.save', '0-123-136', null, '2', '136', '1', 'admin', 'admin', '2020-10-27 12:02:31', '2020-10-27 12:02:31');
INSERT INTO `st_authority` VALUES ('139', '删除文章表格列表', 'test.table.delete', '0-123-136', null, '3', '136', '1', 'admin', 'admin', '2020-10-27 12:02:54', '2020-10-27 12:02:54');
INSERT INTO `st_authority` VALUES ('140', '查询文章先详情', 'test.table.query', '0-123-136', null, '4', '136', '1', 'admin', 'admin', '2020-10-27 12:04:12', '2020-10-27 12:04:12');
INSERT INTO `st_authority` VALUES ('141', '双表联动', 'test.car', '0-123', null, '5', '123', '1', 'admin', 'admin', '2020-10-27 14:04:32', '2020-10-27 14:04:32');
INSERT INTO `st_authority` VALUES ('142', '查询车辆列表', 'test.car.query', '0-123-141', null, '1', '141', '1', 'admin', 'admin', '2020-10-27 14:05:14', '2020-10-27 14:05:14');
INSERT INTO `st_authority` VALUES ('143', '保存', 'test.car.save', '0-123-141', null, '2', '141', '1', 'admin', 'admin', '2020-10-27 14:05:43', '2020-10-27 14:05:43');
INSERT INTO `st_authority` VALUES ('144', '删除车辆', 'test.car.delete', '0-123-141', null, '3', '141', '1', 'admin', 'admin', '2020-10-27 14:06:18', '2020-10-27 14:06:18');
INSERT INTO `st_authority` VALUES ('145', '查询车辆组列表', 'test.car.carmodel.query', '0-123-141', null, '4', '141', '1', 'admin', 'admin', '2020-10-27 14:06:52', '2020-10-27 14:06:52');
INSERT INTO `st_authority` VALUES ('146', '保存车辆组', 'test.car.carmodel.save', '0-123-141', null, '5', '141', '1', 'admin', 'admin', '2020-10-27 14:07:28', '2020-10-27 14:07:28');
INSERT INTO `st_authority` VALUES ('147', '删除车辆组', 'test.car.carmodel.delete', '0-123-141', null, '6', '141', '1', 'admin', 'admin', '2020-10-27 14:08:10', '2020-10-27 14:08:10');
INSERT INTO `st_authority` VALUES ('148', '日志管理', 'sysmgr.log', '0-2', null, '23', '2', '1', 'admin', 'admin', '2020-10-28 01:28:20', '2020-10-28 01:28:20');
INSERT INTO `st_authority` VALUES ('149', '系统日志', 'sysmgr.log.sys', '0-2-148', null, '1', '148', '1', 'admin', 'admin', '2020-10-28 01:31:19', '2020-10-28 13:15:12');
INSERT INTO `st_authority` VALUES ('150', '登录日志', 'sysmgr.log.login', '0-2-148', null, '2', '148', '1', 'admin', 'admin', '2020-10-28 01:31:49', '2020-10-28 01:31:49');
INSERT INTO `st_authority` VALUES ('151', '查询系统日志', 'sysmgr.log.sys.query', '0-2-148-149', null, '1', '149', '1', 'admin', 'admin', '2020-10-28 01:32:37', '2020-10-28 01:32:37');
INSERT INTO `st_authority` VALUES ('152', '查询登录日志', 'sysmgr.log.login.query', '0-2-148-150', null, '1', '150', '1', 'admin', 'admin', '2020-10-28 01:33:28', '2020-10-28 01:33:28');
INSERT INTO `st_authority` VALUES ('153', '删除登录日志', 'sysmgr.log.login.delete', '0-2-148-150', null, '2', '150', '1', 'admin', 'admin', '2020-10-28 01:34:10', '2020-10-28 01:34:10');
INSERT INTO `st_authority` VALUES ('154', '树形表格查询详情', 'test.treeandtable.query', '0-123-124', null, '4', '124', '1', 'admin', 'admin', '2020-10-28 14:31:19', '2020-10-28 14:31:19');
INSERT INTO `st_authority` VALUES ('155', '查看详情', 'test.expandtable.query', '0-123-132', null, '4', '132', '1', 'admin', 'admin', '2020-10-28 14:39:48', '2020-10-28 14:39:48');
INSERT INTO `st_authority` VALUES ('156', '组织管理', 'sysmgr.organization', '0-2', null, '24', '2', '1', 'admin', 'admin', '2020-10-29 07:43:55', '2020-10-29 07:44:05');
INSERT INTO `st_authority` VALUES ('157', '查询组织列表', 'sysmgr.organization.list', '0-2-156', null, '1', '156', '1', 'admin', 'admin', '2020-10-29 07:44:33', '2020-10-29 07:44:33');
INSERT INTO `st_authority` VALUES ('158', '添加组织', 'sysmgr.organization.add', '0-2-156', null, '2', '156', '1', 'admin', 'admin', '2020-10-29 07:46:12', '2020-10-29 07:47:00');
INSERT INTO `st_authority` VALUES ('159', '修改组织', 'sysmgr.organization.update', '0-2-156', null, '3', '156', '1', 'admin', 'admin', '2020-10-29 07:46:49', '2020-10-29 07:46:49');
INSERT INTO `st_authority` VALUES ('160', '删除组织', 'sysmgr.organization.delete', '0-2-156', null, '4', '156', '1', 'admin', 'admin', '2020-10-29 07:47:24', '2020-10-29 07:47:24');
INSERT INTO `st_authority` VALUES ('161', 'w部门管理', 'sysmgr.rydept', '0-2', null, '25', '2', '1', 'admin', 'admin', '2020-10-30 02:02:15', '2020-10-30 02:02:34');
INSERT INTO `st_authority` VALUES ('162', '获取部门列表', 'sysmgr.rydept.list', '0-2-161', null, '1', '161', '1', 'admin', 'admin', '2020-10-30 02:03:22', '2020-10-30 02:03:22');
INSERT INTO `st_authority` VALUES ('163', '根据部门编号获取详细信息', 'sysmgr.rydept.query', '0-2-161', null, '2', '161', '1', 'admin', 'admin', '2020-10-30 02:04:24', '2020-10-30 02:04:24');
INSERT INTO `st_authority` VALUES ('164', '新增部门', 'sysmgr.rydept.add', '0-2-161', null, '3', '161', '1', 'admin', 'admin', '2020-10-30 02:05:12', '2020-10-30 02:05:12');
INSERT INTO `st_authority` VALUES ('165', '修改部门', 'sysmgr.rydept.edit', '0-2-161', null, '4', '161', '1', 'admin', 'admin', '2020-10-30 02:05:42', '2020-10-30 02:05:42');
INSERT INTO `st_authority` VALUES ('166', '删除部门', 'sysmgr.rydept.delete', '0-2-161', null, '5', '161', '1', 'admin', 'admin', '2020-10-30 02:06:11', '2020-10-30 02:06:11');
INSERT INTO `st_authority` VALUES ('167', 'w岗位管理', 'sysmgr.post', '0-2', null, '26', '2', '1', 'admin', 'admin', '2020-10-30 09:30:22', '2020-10-30 09:30:22');
INSERT INTO `st_authority` VALUES ('168', '获取岗位列表', 'sysmgr.post.list', '0-2-167', null, '1', '167', '1', 'admin', 'admin', '2020-10-30 09:30:39', '2020-10-30 09:30:39');
INSERT INTO `st_authority` VALUES ('169', '根据岗位编号获取详细信息', 'sysmgr.post.query', '0-2-167', null, '2', '167', '1', 'admin', 'admin', '2020-10-30 09:31:23', '2020-10-30 09:31:23');
INSERT INTO `st_authority` VALUES ('170', '新增岗位', 'sysmgr.post.add', '0-2-167', null, '3', '167', '1', 'admin', 'admin', '2020-10-30 09:31:48', '2020-10-30 09:31:48');
INSERT INTO `st_authority` VALUES ('171', '修改岗位', 'sysmgr.post.edit', '0-2-167', null, '4', '167', '1', 'admin', 'admin', '2020-10-30 09:32:16', '2020-10-30 09:32:16');
INSERT INTO `st_authority` VALUES ('172', '删除岗位', 'sysmgr.post.remove', '0-2-167', null, '5', '167', '1', 'admin', 'admin', '2020-10-30 09:32:47', '2020-10-30 09:32:47');
INSERT INTO `st_authority` VALUES ('173', 'oa系统', 'oasys', '0', null, '12', '0', '1', 'admin', 'admin', '2020-11-26 15:35:55', '2020-11-26 15:36:58');
INSERT INTO `st_authority` VALUES ('174', '网盘', 'oasys.file', '0-173', null, '1', '173', '1', 'admin', 'admin', '2020-11-26 15:36:48', '2020-11-26 15:36:48');
INSERT INTO `st_authority` VALUES ('175', '查询网盘列表', 'oasys.file.query', '0-173-174', null, '1', '174', '1', 'admin', 'admin', '2020-11-26 15:37:40', '2020-11-26 15:45:31');
INSERT INTO `st_authority` VALUES ('176', '保存网盘', 'oasys.file.save', '0-173-174', null, '2', '174', '1', 'admin', 'admin', '2020-11-26 15:38:04', '2020-11-26 15:38:04');
INSERT INTO `st_authority` VALUES ('177', '上传文件', 'oasys.file.upload', '0-173-174', null, '3', '174', '1', 'admin', 'admin', '2020-11-26 15:39:00', '2020-11-26 15:39:00');
INSERT INTO `st_authority` VALUES ('178', '删除网盘', 'oasys.file.delete', '0-173-174', null, '4', '174', '1', 'admin', 'admin', '2020-11-26 15:39:27', '2020-11-26 15:39:27');
INSERT INTO `st_authority` VALUES ('179', '考勤', 'oasys.sign', '0-173', null, '2', '173', '1', 'admin', 'admin', '2020-12-02 14:32:40', '2020-12-02 14:32:40');
INSERT INTO `st_authority` VALUES ('180', '签到签退', 'oasys.sign.query', '0-173-179', null, '1', '179', '1', 'admin', 'admin', '2020-12-02 14:37:33', '2020-12-02 14:37:33');
INSERT INTO `st_authority` VALUES ('181', '请假', 'oasys.leave', '0-173', null, '3', '173', '1', 'admin', 'admin', '2020-12-02 14:38:45', '2020-12-02 14:38:45');
INSERT INTO `st_authority` VALUES ('182', '请假', 'oasys.leave.query', '0-173-181', null, '1', '181', '1', 'admin', 'admin', '2020-12-02 14:39:07', '2020-12-02 14:39:07');
INSERT INTO `st_authority` VALUES ('183', '表头通知', 'oasys.notice', '0-173', null, '4', '173', '1', 'admin', 'admin', '2020-12-02 16:59:34', '2020-12-02 16:59:34');
INSERT INTO `st_authority` VALUES ('184', '表头通知', 'oasys.notice.query', '0-173-183', null, '1', '183', '1', 'admin', 'admin', '2020-12-02 16:59:51', '2020-12-02 16:59:51');
INSERT INTO `st_authority` VALUES ('185', '聊天室', 'syschat', '0', null, '13', '0', '1', 'admin', 'admin', '2020-12-28 13:44:24', '2020-12-28 13:44:24');

-- ----------------------------
-- Table structure for `st_backup`
-- ----------------------------
DROP TABLE IF EXISTS `st_backup`;
CREATE TABLE `st_backup` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `backup_date` datetime DEFAULT NULL,
  `backup_name` varchar(255) DEFAULT '',
  `backup_path` varchar(255) DEFAULT NULL,
  `db_name` varchar(255) DEFAULT '',
  `file_size` bigint(20) DEFAULT '0',
  `remark` varchar(255) DEFAULT '',
  `runtime` bigint(20) DEFAULT '0',
  `status` int(11) DEFAULT '0',
  `yn_flag` char(2) DEFAULT '1' COMMENT '有效标志',
  `creator` varchar(32) DEFAULT '' COMMENT '创建人',
  `editor` varchar(32) DEFAULT '' COMMENT '修改人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='DB备份表';

-- ----------------------------
-- Records of st_backup
-- ----------------------------
INSERT INTO `st_backup` VALUES ('34', '2019-09-10 10:28:01', '2019-09-10 数据备份', '/201909/1568111280776.sql', 'story_admin', '38289', null, '0', '10', '1', null, null, '2019-09-10 10:28:01', '2019-09-10 10:28:01');
INSERT INTO `st_backup` VALUES ('35', '2019-09-10 10:29:00', '2019-09-10 数据备份', '/201909/1568111340472.sql', 'story_admin', '38491', null, '0', '10', '1', null, null, '2019-09-10 10:29:00', '2019-09-10 10:29:00');
INSERT INTO `st_backup` VALUES ('36', '2019-09-10 10:30:00', '2019-09-10 数据备份', '/201909/1568111400482.sql', 'story_admin', '38660', null, '0', '10', '0', null, 'admin', '2019-09-10 10:30:00', '2019-09-10 11:38:42');

-- ----------------------------
-- Table structure for `st_dept`
-- ----------------------------
DROP TABLE IF EXISTS `st_dept`;
CREATE TABLE `st_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '部门名称',
  `full_id` varchar(200) COLLATE utf8_bin DEFAULT '' COMMENT '编号路径',
  `pid` bigint(20) DEFAULT '0' COMMENT '父id',
  `show_order` int(11) DEFAULT '0' COMMENT '排序',
  `dept_desc` varchar(200) COLLATE utf8_bin DEFAULT '' COMMENT '部门描述',
  `yn_flag` char(2) COLLATE utf8_bin DEFAULT '1' COMMENT '有效标志',
  `creator` varchar(32) COLLATE utf8_bin DEFAULT '',
  `editor` varchar(32) COLLATE utf8_bin DEFAULT '',
  `created_time` datetime DEFAULT NULL,
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='部门表';

-- ----------------------------
-- Records of st_dept
-- ----------------------------
INSERT INTO `st_dept` VALUES ('1', '研发部', '0', '0', '1', null, '1', 'admin', 'admin', '2021-01-05 06:58:30', '2021-01-05 06:58:59');
INSERT INTO `st_dept` VALUES ('11', '研发一组', '0-1', '1', '1', null, '1', 'admin', 'admin', '2021-01-05 06:59:40', '2021-01-05 06:59:40');

-- ----------------------------
-- Table structure for `st_dict`
-- ----------------------------
DROP TABLE IF EXISTS `st_dict`;
CREATE TABLE `st_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `code` varchar(64) COLLATE utf8_bin DEFAULT '' COMMENT '编码',
  `chn_name` varchar(32) COLLATE utf8_bin DEFAULT '' COMMENT '中文名称',
  `eng_name` varchar(64) COLLATE utf8_bin DEFAULT '' COMMENT '英文名称',
  `show_order` int(11) DEFAULT '0' COMMENT '排序',
  `parent_code` varchar(64) COLLATE utf8_bin DEFAULT '' COMMENT '父编码',
  `yn_flag` char(2) COLLATE utf8_bin DEFAULT '1' COMMENT '有效标志',
  `creator` varchar(32) COLLATE utf8_bin DEFAULT '' COMMENT '创建人',
  `editor` varchar(32) COLLATE utf8_bin DEFAULT '' COMMENT '修改人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='字典表';

-- ----------------------------
-- Records of st_dict
-- ----------------------------
INSERT INTO `st_dict` VALUES ('1', 'true_or_false', '是否', 'YesOrNo', '1', '', '1', 'admin', 'admin', '2019-07-25 09:54:56', '2019-07-25 18:05:07');
INSERT INTO `st_dict` VALUES ('2', '1', '是', 'Yes', '1', 'true_or_false', '1', 'admin', 'admin', '2019-07-25 09:54:56', '2019-07-25 20:10:18');
INSERT INTO `st_dict` VALUES ('3', '0', '否', 'No', '2', 'true_or_false', '1', 'admin', 'admin', '2019-07-25 09:54:56', '2019-07-25 20:10:20');
INSERT INTO `st_dict` VALUES ('4', 'effective_or_ineffective', '有效无效', null, '2', '', '1', 'admin', 'admin', '2019-07-25 09:54:56', '2019-07-25 17:57:15');
INSERT INTO `st_dict` VALUES ('5', '1', '有效', 'Effective', '1', 'effective_or_ineffective', '1', 'admin', 'admin', '2019-07-25 09:54:56', '2019-07-25 20:06:09');
INSERT INTO `st_dict` VALUES ('6', '0', '无效', 'Ineffective', '2', 'effective_or_ineffective', '1', 'admin', 'admin', '2019-07-25 09:54:56', '2019-07-25 20:06:11');
INSERT INTO `st_dict` VALUES ('7', 'enable_or_disable', '启用或禁用', null, '3', '', '1', 'admin', 'admin', '2019-07-25 11:44:38', '2019-07-25 17:57:15');
INSERT INTO `st_dict` VALUES ('8', '1', '启用', 'Enable', '1', 'enable_or_disable', '1', 'admin', 'admin', '2019-07-25 09:54:56', '2019-07-26 10:31:54');
INSERT INTO `st_dict` VALUES ('9', '2', '禁用', 'Disabled', '2', 'enable_or_disable', '1', 'admin', 'admin', '2019-07-25 09:54:56', '2019-07-26 10:31:57');

-- ----------------------------
-- Table structure for `st_image_file`
-- ----------------------------
DROP TABLE IF EXISTS `st_image_file`;
CREATE TABLE `st_image_file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(255) DEFAULT '',
  `lujing` varchar(255) DEFAULT '',
  `url` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of st_image_file
-- ----------------------------
INSERT INTO `st_image_file` VALUES ('3', '20201013093858_tp2.jpg', 'D:/fileUpload/20201013093858_tp2.jpg', 'http://localhost:9430/images/20201013093858_tp2.jpg');
INSERT INTO `st_image_file` VALUES ('5', '20201013114810_tp2.jpg', 'D:/fileUpload/20201013114810_tp2.jpg', 'http://localhost:9430/images/20201013114810_tp2.jpg');
INSERT INTO `st_image_file` VALUES ('8', '20201013153023_tp2.jpg', 'D:/fileUpload/20201013153023_tp2.jpg', 'http://localhost:9430/images/20201013153023_tp2.jpg');
INSERT INTO `st_image_file` VALUES ('9', '20201013153214_tp2.jpg', 'D:/fileUpload/20201013153214_tp2.jpg', 'http://localhost:9430/images/20201013153214_tp2.jpg');
INSERT INTO `st_image_file` VALUES ('10', '20201013153338_tp2.jpg', 'D:/fileUpload/20201013153338_tp2.jpg', 'http://localhost:9430/images/20201013153338_tp2.jpg');
INSERT INTO `st_image_file` VALUES ('11', '20201013153512_tp2.jpg', 'D:/fileUpload/20201013153512_tp2.jpg', 'http://localhost:9430/images/20201013153512_tp2.jpg');
INSERT INTO `st_image_file` VALUES ('15', '20201013153850_tp2.jpg', 'D:/fileUpload/20201013153850_tp2.jpg', 'http://localhost:9430/images/20201013153850_tp2.jpg');
INSERT INTO `st_image_file` VALUES ('21', '20201013165918_pp.png', 'D:/fileUpload/20201013165918_pp.png', 'http://localhost:9430/images/20201013165918_pp.png');
INSERT INTO `st_image_file` VALUES ('22', '20201013170420_pp.png', 'D:/fileUpload/20201013170420_pp.png', 'http://localhost:9430/images/20201013170420_pp.png');
INSERT INTO `st_image_file` VALUES ('23', '20201013171250_pp.png', 'D:/fileUpload/20201013171250_pp.png', 'http://localhost:9430/images/20201013171250_pp.png');
INSERT INTO `st_image_file` VALUES ('24', '20201013171310_pp.png', 'D:/fileUpload/20201013171310_pp.png', 'http://localhost:9430/images/20201013171310_pp.png');
INSERT INTO `st_image_file` VALUES ('25', '20201019160619_pp.png', 'D:/fileUpload/20201019160619_pp.png', 'http://localhost:9430/images/20201019160619_pp.png');
INSERT INTO `st_image_file` VALUES ('26', '20201019163547_pp.png', 'D:/fileUpload/20201019163547_pp.png', 'http://localhost:9430/images/20201019163547_pp.png');
INSERT INTO `st_image_file` VALUES ('27', '20201019163608_pp.png', 'D:/fileUpload/20201019163608_pp.png', 'http://localhost:9430/images/20201019163608_pp.png');
INSERT INTO `st_image_file` VALUES ('28', '20201019164256_pp.png', 'D:/fileUpload/20201019164256_pp.png', 'http://localhost:9430/images/20201019164256_pp.png');
INSERT INTO `st_image_file` VALUES ('29', '20201019173455_pp.png', 'D:/fileUpload/20201019173455_pp.png', 'http://localhost:9430/images/20201019173455_pp.png');
INSERT INTO `st_image_file` VALUES ('30', '20201020092958_pp.png', 'D:/fileUpload/20201020092958_pp.png', 'http://localhost:9430/images/20201020092958_pp.png');
INSERT INTO `st_image_file` VALUES ('31', '20201020093234_pp.png', 'D:/fileUpload/20201020093234_pp.png', 'http://localhost:9430/images/20201020093234_pp.png');
INSERT INTO `st_image_file` VALUES ('32', '20201020093615_pp.png', 'D:/fileUpload/20201020093615_pp.png', 'http://localhost:9430/images/20201020093615_pp.png');
INSERT INTO `st_image_file` VALUES ('33', '20201020095019_pp.png', 'D:/fileUpload/20201020095019_pp.png', 'http://localhost:9430/images/20201020095019_pp.png');
INSERT INTO `st_image_file` VALUES ('34', '20201020101830_pp.png', 'D:/fileUpload/20201020101830_pp.png', 'http://localhost:9430/images/20201020101830_pp.png');
INSERT INTO `st_image_file` VALUES ('35', '20201023230144_图像.jpeg', '/Users/lipan/Desktop/fileUpload/20201023230144_图像.jpeg', 'http://localhost:9430/images/20201023230144_图像.jpeg');
INSERT INTO `st_image_file` VALUES ('36', '20201231165829_pp.png', 'D:/fileUpload/20201231165829_pp.png', 'http://localhost:9430/images/20201231165829_pp.png');
INSERT INTO `st_image_file` VALUES ('37', '20210702102131_pp.png', 'D:/fileUpload/20210702102131_pp.png', 'http://localhost:9430/images/20210702102131_pp.png');
INSERT INTO `st_image_file` VALUES ('38', '20210702102204_pp.png', 'D:/fileUpload/20210702102204_pp.png', 'http://localhost:9430/images/20210702102204_pp.png');

-- ----------------------------
-- Table structure for `st_inform`
-- ----------------------------
DROP TABLE IF EXISTS `st_inform`;
CREATE TABLE `st_inform` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL DEFAULT '' COMMENT '标题',
  `top` tinyint(1) NOT NULL COMMENT '是否置顶',
  `status` decimal(2,0) NOT NULL DEFAULT '1' COMMENT '0 撤销  1 正常或过期',
  `content` text NOT NULL COMMENT '内容',
  `attchment_list` varchar(255) DEFAULT '',
  `canceler` bigint(20) DEFAULT '1' COMMENT '撤销人ID',
  `cancel_date` datetime DEFAULT NULL COMMENT '撤销时间',
  `outdate_operator` bigint(20) DEFAULT '1' COMMENT '过期操作人ID',
  `outdate_date` datetime DEFAULT NULL COMMENT '过期时间',
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `creator_id` bigint(20) DEFAULT '1',
  `yn_flag` char(2) DEFAULT '1',
  `creator` varchar(32) DEFAULT NULL,
  `editor` varchar(32) DEFAULT '',
  `created_time` datetime DEFAULT NULL,
  `modified_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of st_inform
-- ----------------------------
INSERT INTO `st_inform` VALUES ('8', '测试', '0', '1', 'hello world！', '5,6,13', null, null, null, null, '2020-08-12 06:50:18', '1', null, null, null, null, null);
INSERT INTO `st_inform` VALUES ('21', '搬砖的一天', '1', '1', '又是搬砖的一天！', '5,6,14', null, null, null, null, '2020-08-14 02:33:58', '1', null, null, null, null, null);
INSERT INTO `st_inform` VALUES ('24', '上传一张图片', '0', '1', '666', '13,14', null, null, null, null, '2020-08-14 07:20:40', '1', null, null, null, null, null);
INSERT INTO `st_inform` VALUES ('25', '测试上传二张图片', '0', '2', '23333', '14', null, null, null, '2020-08-21 03:11:17', '2020-08-14 07:24:44', '1', null, null, null, null, null);
INSERT INTO `st_inform` VALUES ('29', '今天周五', '1', '1', '今天周五', '', null, null, null, null, '2020-08-14 08:00:27', null, null, null, null, null, null);
INSERT INTO `st_inform` VALUES ('31', '测试日志功能', '0', '1', '6666', '', null, null, null, null, '2020-08-18 07:13:48', null, null, null, null, null, null);
INSERT INTO `st_inform` VALUES ('32', '2020年8月19日测试', '0', '1', '2020年8月19日测试', '', null, null, null, null, '2020-08-19 01:08:44', null, null, null, null, null, null);
INSERT INTO `st_inform` VALUES ('33', '测试公告分页功能', '0', '2', '测试公告分页功能', '', null, null, null, '2020-08-19 01:40:13', '2020-08-19 01:15:12', null, null, null, null, null, null);
INSERT INTO `st_inform` VALUES ('34', '再测试一次', '0', '0', '再测试一次', '', null, '2020-08-19 01:36:41', null, null, '2020-08-19 01:15:33', null, null, null, null, null, null);
INSERT INTO `st_inform` VALUES ('35', '测试23333', '0', '2', '测试23333', '', null, null, null, '2020-08-19 01:37:07', '2020-08-19 01:15:48', null, null, null, null, null, null);
INSERT INTO `st_inform` VALUES ('36', '今天修改文件上传的bug', '1', '1', '今天修改文件上传的bug', '', null, null, null, null, '2020-08-27 08:03:18', null, null, null, null, null, null);
INSERT INTO `st_inform` VALUES ('37', '测试一遍再', '1', '1', '阿达', '', null, null, null, null, '2020-08-27 08:06:46', null, null, null, null, null, null);
INSERT INTO `st_inform` VALUES ('38', '6666', '0', '1', 'sdfs', '', null, null, null, null, '2020-08-27 08:09:38', null, null, null, null, null, null);
INSERT INTO `st_inform` VALUES ('39', '21', '0', '1', '阿斯达1', '', null, null, null, null, '2020-08-27 08:10:46', null, null, null, null, null, null);
INSERT INTO `st_inform` VALUES ('40', '测试888', '0', '1', '文学赏析\n李白《静夜思》诗意图\n李白《静夜思》诗意图\n《静夜思》没有奇特新颖的想象，没有精工华美的辞藻，只是用叙述的语气，写远客思乡之情，然而它却意味深长，耐人寻绎，千百年来，如此广泛地吸引着读者。全诗从“疑”到“举头”，从“举头”到“低头”，形象地揭示了诗人内心活动，鲜明地勾勒出一幅生动形象的月夜思乡图，抒发了作者在寂静的月夜思念家乡的感受。客中深夜不能成眠、短梦初回的情景。这时庭院是寂寥的，透过窗户的皎洁月光射到床前，带来了冷森森的秋宵寒意。诗人朦胧地乍一望去，在迷离恍惚的心情中，真好像是地上铺了一层白皑皑的浓霜；可是再定神一看，四周围的环境告诉他，这不是霜痕而是月色。月色不免吸引着他抬头一看，一轮娟娟素魄正挂在窗前，秋夜的太空是如此明净。秋月是分外光明的，然而它又是清冷的。对孤身远客来说，最容易触动旅思秋怀，使人感到客况萧条，年华易逝。凝望着月亮，也最容易使人产生遐想，想到故乡的一切，想到家里的亲人。想着，想着，头渐渐地低了下去，完全浸入于沉思之中。\n前两句写诗人在作客他乡的特定环境中一刹那间所产生的错觉。一个作客他乡的人都会有这样的感：白天奔波忙碌，倒还能冲淡离愁，到了夜深人静的时候，思乡的情绪，就难免一阵阵地在心头泛起波澜。在月明之夜，尤其是月色如霜的秋夜更是如此。“疑是地上霜”中的“疑”字，生动地表达了诗人睡梦初醒，迷离恍惚中将照射在床前的清冷月光误作铺在地面的浓霜。“霜”字用得更妙，既形容了月光的皎洁，又表达了季节的寒冷，还烘托出诗人飘泊他乡的孤寂凄凉之情。\n后两句通过动作神态的刻画，深化思乡之情。“望”字照应了前句的“疑”字，表明诗人已从迷朦转为清醒，他翘首凝望着月亮，不禁想起，此刻他的故乡也正处在这轮明月的照耀下，自然引出了“低头思故乡”的结句。“低头”这一动作描画出诗人完全处于沉思之中。“思”字给读者留下丰富的想象：那家乡的父老兄弟、亲朋好友，那家乡的一山一水、一草一木，那逝去的年华与往事，无不在思念之中。一个“思”字所包涵的内容实在太丰富了。\n短短四句诗，写得清新朴素，明白如话。构思细致而深曲，脱口吟成、浑然无迹。内容是单纯，却又是丰富的；内容是容易理解的，却又是体味不尽的。诗人所没有说的比他已经说出来的要多得多，体现了“自然”、“无意于工而无不工”的妙境。 [3]  [9]', '', null, null, null, null, '2020-08-27 08:15:43', null, null, null, null, null, null);
INSERT INTO `st_inform` VALUES ('41', '测试888', '0', '1', '文学赏析\n李白《静夜思》诗意图\n李白《静夜思》诗意图\n《静夜思》没有奇特新颖的想象，没有精工华美的辞藻，只是用叙述的语气，写远客思乡之情，然而它却意味深长，耐人寻绎，千百年来，如此广泛地吸引着读者。全诗从“疑”到“举头”，从“举头”到“低头”，形象地揭示了诗人内心活动，鲜明地勾勒出一幅生动形象的月夜思乡图，抒发了作者在寂静的月夜思念家乡的感受。客中深夜不能成眠、短梦初回的情景。这时庭院是寂寥的，透过窗户的皎洁月光射到床前，带来了冷森森的秋宵寒意。诗人朦胧地乍一望去，在迷离恍惚的心情中，真好像是地上铺了一层白皑皑的浓霜；可是再定神一看，四周围的环境告诉他，这不是霜痕而是月色。月色不免吸引着他抬头一看，一轮娟娟素魄正挂在窗前，秋夜的太空是如此明净。秋月是分外光明的，然而它又是清冷的。对孤身远客来说，最容易触动旅思秋怀，使人感到客况萧条，年华易逝。凝望着月亮，也最容易使人产生遐想，想到故乡的一切，想到家里的亲人。想着，想着，头渐渐地低了下去，完全浸入于沉思之中。\n前两句写诗人在作客他乡的特定环境中一刹那间所产生的错觉。一个作客他乡的人都会有这样的感：白天奔波忙碌，倒还能冲淡离愁，到了夜深人静的时候，思乡的情绪，就难免一阵阵地在心头泛起波澜。在月明之夜，尤其是月色如霜的秋夜更是如此。“疑是地上霜”中的“疑”字，生动地表达了诗人睡梦初醒，迷离恍惚中将照射在床前的清冷月光误作铺在地面的浓霜。“霜”字用得更妙，既形容了月光的皎洁，又表达了季节的寒冷，还烘托出诗人飘泊他乡的孤寂凄凉之情。\n后两句通过动作神态的刻画，深化思乡之情。“望”字照应了前句的“疑”字，表明诗人已从迷朦转为清醒，他翘首凝望着月亮，不禁想起，此刻他的故乡也正处在这轮明月的照耀下，自然引出了“低头思故乡”的结句。“低头”这一动作描画出诗人完全处于沉思之中。“思”字给读者留下丰富的想象：那家乡的父老兄弟、亲朋好友，那家乡的一山一水、一草一木，那逝去的年华与往事，无不在思念之中。一个“思”字所包涵的内容实在太丰富了。\n短短四句诗，写得清新朴素，明白如话。构思细致而深曲，脱口吟成、浑然无迹。内容是单纯，却又是丰富的；内容是容易理解的，却又是体味不尽的。诗人所没有说的比他已经说出来的要多得多，体现了“自然”、“无意于工而无不工”的妙境。 [3]  [9]', '', null, null, null, null, '2020-08-27 08:15:43', null, null, null, null, null, null);
INSERT INTO `st_inform` VALUES ('42', '测试测试', '0', '1', '阿达', '', null, null, null, null, '2020-08-27 08:20:23', null, null, null, null, null, null);
INSERT INTO `st_inform` VALUES ('43', '测试一下再', '0', '1', '测试一下再', '', null, null, null, null, '2020-08-27 08:34:56', null, null, null, null, null, null);
INSERT INTO `st_inform` VALUES ('44', '测试666', '0', '1', '阿达', '', null, null, null, null, '2020-08-27 08:37:43', null, null, null, null, null, null);
INSERT INTO `st_inform` VALUES ('45', '232', '0', '0', 'sdfs', '', null, '2020-09-10 07:52:46', null, null, '2020-08-27 08:41:46', null, null, null, null, null, null);
INSERT INTO `st_inform` VALUES ('46', '999', '0', '1', '大大', '', null, null, null, null, '2020-08-27 08:46:09', null, null, null, null, null, null);
INSERT INTO `st_inform` VALUES ('47', '测试上传附件功能20200211', '1', '1', '测试上传附件功能20200211', '13,14,27,28', null, null, null, null, '2020-09-11 07:29:57', '1', null, null, null, null, null);
INSERT INTO `st_inform` VALUES ('48', '上传其他格式文件', '1', '1', '上传其他格式文件', '30', null, null, null, null, '2020-09-14 06:19:49', null, null, null, null, null, null);
INSERT INTO `st_inform` VALUES ('49', '20200921测试', '1', '1', '20200921测试', '', null, null, null, null, '2020-09-21 07:45:35', null, null, null, null, null, null);
INSERT INTO `st_inform` VALUES ('50', 'q', '0', '1', '11', '', null, null, null, null, '2020-09-21 08:00:58', null, null, null, null, null, null);
INSERT INTO `st_inform` VALUES ('51', '22', '1', '1', '22', '', null, null, null, null, '2020-09-21 08:03:51', null, null, null, null, null, null);
INSERT INTO `st_inform` VALUES ('52', '0927', '1', '1', '0927', '40', null, null, null, null, '2020-09-27 01:24:21', null, null, null, null, null, null);
INSERT INTO `st_inform` VALUES ('53', '0927测试上传多个文件', '1', '1', 'adc', '41,42', null, null, null, null, '2020-09-27 01:27:11', null, null, null, null, null, null);
INSERT INTO `st_inform` VALUES ('54', '1022', '1', '1', '多租户管理平台', '43', null, null, null, null, '2020-10-22 03:07:02', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `st_login_log`
-- ----------------------------
DROP TABLE IF EXISTS `st_login_log`;
CREATE TABLE `st_login_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '用户名',
  `login_time` datetime DEFAULT NULL COMMENT '访问时间',
  `content` varchar(64) COLLATE utf8_bin DEFAULT '' COMMENT '内容',
  `yn_flag` char(2) COLLATE utf8_bin DEFAULT '1' COMMENT '有效标志',
  `creator` varchar(32) COLLATE utf8_bin DEFAULT '' COMMENT '创建人',
  `editor` varchar(32) COLLATE utf8_bin DEFAULT '' COMMENT '修改人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1321344106551652556 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='登录日志';

-- ----------------------------
-- Records of st_login_log
-- ----------------------------
INSERT INTO `st_login_log` VALUES ('1321344106551652355', 'admin', '2020-10-28 12:52:01', '登录成功', '1', 'admin', 'admin', '2020-10-28 12:52:01', '2020-10-28 12:52:01');
INSERT INTO `st_login_log` VALUES ('1321344106551652356', 'admin', '2020-10-28 12:55:10', '登录成功', '1', 'admin', 'admin', '2020-10-28 12:55:10', '2020-10-28 12:55:09');
INSERT INTO `st_login_log` VALUES ('1321344106551652357', 'admin', '2020-10-28 13:08:18', '登录成功', '1', 'admin', 'admin', '2020-10-28 13:08:18', '2020-10-28 13:08:18');
INSERT INTO `st_login_log` VALUES ('1321344106551652358', 'admin', '2020-10-28 13:12:33', '登录成功', '1', 'admin', 'admin', '2020-10-28 13:12:33', '2020-10-28 13:12:33');
INSERT INTO `st_login_log` VALUES ('1321344106551652359', 'admin', '2020-10-28 13:12:54', '登录成功', '1', 'admin', 'admin', '2020-10-28 13:12:54', '2020-10-28 13:12:53');
INSERT INTO `st_login_log` VALUES ('1321344106551652360', 'admin', '2020-10-28 13:13:31', '登录成功', '1', 'admin', 'admin', '2020-10-28 13:13:31', '2020-10-28 13:13:30');
INSERT INTO `st_login_log` VALUES ('1321344106551652361', 'admin', '2020-10-28 14:34:50', '登录成功', '1', 'admin', 'admin', '2020-10-28 14:34:50', '2020-10-28 14:34:49');
INSERT INTO `st_login_log` VALUES ('1321344106551652362', 'admin', '2020-10-28 14:44:51', '登录成功', '1', 'admin', 'admin', '2020-10-28 14:44:51', '2020-10-28 14:44:51');
INSERT INTO `st_login_log` VALUES ('1321344106551652363', 'admin', '2020-10-29 01:18:49', '登录成功', '1', 'admin', 'admin', '2020-10-29 01:18:49', '2020-10-29 01:18:49');
INSERT INTO `st_login_log` VALUES ('1321344106551652364', 'admin', '2020-10-29 01:28:49', '登录成功', '1', 'admin', 'admin', '2020-10-29 01:28:49', '2020-10-29 01:28:48');
INSERT INTO `st_login_log` VALUES ('1321344106551652365', 'admin', '2020-10-29 01:43:12', '登录成功', '1', 'admin', 'admin', '2020-10-29 01:43:12', '2020-10-29 01:43:11');
INSERT INTO `st_login_log` VALUES ('1321344106551652366', 'admin', '2020-10-29 02:23:35', '登录成功', '1', 'admin', 'admin', '2020-10-29 02:23:35', '2020-10-29 02:23:34');
INSERT INTO `st_login_log` VALUES ('1321344106551652367', 'admin', '2020-10-29 03:04:02', '登录成功', '1', 'admin', 'admin', '2020-10-29 03:04:02', '2020-10-29 03:04:02');
INSERT INTO `st_login_log` VALUES ('1321344106551652368', 'admin', '2020-10-29 06:23:54', '登录成功', '1', 'admin', 'admin', '2020-10-29 06:23:54', '2020-10-29 06:23:53');
INSERT INTO `st_login_log` VALUES ('1321344106551652369', 'admin', '2020-10-29 07:41:41', '登录成功', '1', 'admin', 'admin', '2020-10-29 07:41:41', '2020-10-29 07:41:40');
INSERT INTO `st_login_log` VALUES ('1321344106551652370', 'admin', '2020-10-29 07:54:24', '登录成功', '1', 'admin', 'admin', '2020-10-29 07:54:24', '2020-10-29 07:54:23');
INSERT INTO `st_login_log` VALUES ('1321344106551652371', 'admin', '2020-10-29 08:17:37', '登录成功', '1', 'admin', 'admin', '2020-10-29 08:17:37', '2020-10-29 08:17:37');
INSERT INTO `st_login_log` VALUES ('1321344106551652372', 'admin', '2020-10-30 01:59:49', '登录成功', '1', 'admin', 'admin', '2020-10-30 01:59:49', '2020-10-30 01:59:48');
INSERT INTO `st_login_log` VALUES ('1321344106551652373', 'admin', '2020-10-30 02:10:34', '登录成功', '1', 'admin', 'admin', '2020-10-30 02:10:34', '2020-10-30 02:10:34');
INSERT INTO `st_login_log` VALUES ('1321344106551652374', 'admin', '2020-10-30 03:28:15', '登录成功', '1', 'admin', 'admin', '2020-10-30 03:28:15', '2020-10-30 03:28:14');
INSERT INTO `st_login_log` VALUES ('1321344106551652375', 'admin', '2020-10-30 06:19:39', '登录成功', '1', 'admin', 'admin', '2020-10-30 06:19:39', '2020-10-30 06:19:39');
INSERT INTO `st_login_log` VALUES ('1321344106551652376', 'admin', '2020-10-30 07:01:50', '登录成功', '1', 'admin', 'admin', '2020-10-30 07:01:50', '2020-10-30 07:01:50');
INSERT INTO `st_login_log` VALUES ('1321344106551652377', 'admin', '2020-10-30 09:28:34', '登录成功', '1', 'admin', 'admin', '2020-10-30 09:28:34', '2020-10-30 09:28:33');
INSERT INTO `st_login_log` VALUES ('1321344106551652378', 'admin', '2020-10-30 09:35:19', '登录成功', '1', 'admin', 'admin', '2020-10-30 09:35:19', '2020-10-30 09:35:19');
INSERT INTO `st_login_log` VALUES ('1321344106551652379', 'admin', '2020-10-30 12:23:00', '登录成功', '1', 'admin', 'admin', '2020-10-30 12:23:00', '2020-10-30 12:22:59');
INSERT INTO `st_login_log` VALUES ('1321344106551652380', 'admin', '2020-11-26 15:26:22', '登录成功', '1', 'admin', 'admin', '2020-11-26 15:26:22', '2020-11-26 15:26:21');
INSERT INTO `st_login_log` VALUES ('1321344106551652381', 'admin', '2020-11-26 15:46:08', '登录成功', '1', 'admin', 'admin', '2020-11-26 15:46:08', '2020-11-26 15:46:08');
INSERT INTO `st_login_log` VALUES ('1321344106551652382', 'admin', '2020-11-27 12:36:39', '登录成功', '1', 'admin', 'admin', '2020-11-27 12:36:39', '2020-11-27 12:36:38');
INSERT INTO `st_login_log` VALUES ('1321344106551652383', 'admin', '2020-11-27 13:27:42', '登录成功', '1', 'admin', 'admin', '2020-11-27 13:27:42', '2020-11-27 13:27:42');
INSERT INTO `st_login_log` VALUES ('1321344106551652384', 'admin', '2020-11-27 15:29:03', '登录成功', '1', 'admin', 'admin', '2020-11-27 15:29:03', '2020-11-27 15:29:02');
INSERT INTO `st_login_log` VALUES ('1321344106551652385', 'admin', '2020-11-28 06:21:12', '登录成功', '1', 'admin', 'admin', '2020-11-28 06:21:12', '2020-11-28 06:21:11');
INSERT INTO `st_login_log` VALUES ('1321344106551652386', 'admin', '2020-11-28 12:24:42', '登录成功', '1', 'admin', 'admin', '2020-11-28 12:24:42', '2020-11-28 12:24:41');
INSERT INTO `st_login_log` VALUES ('1321344106551652387', 'admin', '2020-11-28 13:52:45', '登录成功', '1', 'admin', 'admin', '2020-11-28 13:52:45', '2020-11-28 13:52:45');
INSERT INTO `st_login_log` VALUES ('1321344106551652388', 'admin', '2020-11-28 15:12:00', '登录成功', '1', 'admin', 'admin', '2020-11-28 15:12:00', '2020-11-28 15:11:59');
INSERT INTO `st_login_log` VALUES ('1321344106551652389', 'admin', '2020-11-28 15:22:14', '登录成功', '1', 'admin', 'admin', '2020-11-28 15:22:14', '2020-11-28 15:22:14');
INSERT INTO `st_login_log` VALUES ('1321344106551652390', 'admin', '2020-11-30 14:27:33', '登录成功', '1', 'admin', 'admin', '2020-11-30 14:27:33', '2020-11-30 14:27:32');
INSERT INTO `st_login_log` VALUES ('1321344106551652391', 'admin', '2020-11-30 15:17:58', '登录成功', '1', 'admin', 'admin', '2020-11-30 15:17:58', '2020-11-30 15:17:58');
INSERT INTO `st_login_log` VALUES ('1321344106551652392', 'admin', '2020-11-30 15:47:30', '登录成功', '1', 'admin', 'admin', '2020-11-30 15:47:30', '2020-11-30 15:47:29');
INSERT INTO `st_login_log` VALUES ('1321344106551652393', 'admin', '2020-11-30 16:42:16', '登录成功', '1', 'admin', 'admin', '2020-11-30 16:42:16', '2020-11-30 16:42:15');
INSERT INTO `st_login_log` VALUES ('1321344106551652394', 'admin', '2020-12-01 16:28:23', '登录成功', '1', 'admin', 'admin', '2020-12-01 16:28:23', '2020-12-01 16:28:22');
INSERT INTO `st_login_log` VALUES ('1321344106551652395', 'admin', '2020-12-02 14:16:48', '登录成功', '1', 'admin', 'admin', '2020-12-02 14:16:48', '2020-12-02 14:16:46');
INSERT INTO `st_login_log` VALUES ('1321344106551652396', 'admin', '2020-12-02 15:25:11', '登录成功', '1', 'admin', 'admin', '2020-12-02 15:25:11', '2020-12-02 15:25:10');
INSERT INTO `st_login_log` VALUES ('1321344106551652397', 'admin', '2020-12-02 16:32:51', '登录成功', '1', 'admin', 'admin', '2020-12-02 16:32:51', '2020-12-02 16:32:50');
INSERT INTO `st_login_log` VALUES ('1321344106551652398', 'admin', '2020-12-02 17:11:10', '登录成功', '1', 'admin', 'admin', '2020-12-02 17:11:10', '2020-12-02 17:11:09');
INSERT INTO `st_login_log` VALUES ('1321344106551652399', 'admin', '2020-12-09 14:19:04', '登录成功', '1', 'admin', 'admin', '2020-12-09 14:19:04', '2020-12-09 14:19:04');
INSERT INTO `st_login_log` VALUES ('1321344106551652400', 'admin', '2020-12-09 15:33:48', '登录成功', '1', 'admin', 'admin', '2020-12-09 15:33:48', '2020-12-09 15:33:47');
INSERT INTO `st_login_log` VALUES ('1321344106551652401', 'admin', '2020-12-14 15:38:27', '登录成功', '1', 'admin', 'admin', '2020-12-14 15:38:27', '2020-12-14 15:38:27');
INSERT INTO `st_login_log` VALUES ('1321344106551652402', 'admin', '2020-12-14 15:41:14', '登录成功', '1', 'admin', 'admin', '2020-12-14 15:41:14', '2020-12-14 15:41:14');
INSERT INTO `st_login_log` VALUES ('1321344106551652403', 'admin', '2020-12-14 15:41:33', '登录成功', '1', 'admin', 'admin', '2020-12-14 15:41:33', '2020-12-14 15:41:33');
INSERT INTO `st_login_log` VALUES ('1321344106551652404', 'admin', '2020-12-14 15:42:09', '登录成功', '1', 'admin', 'admin', '2020-12-14 15:42:09', '2020-12-14 15:42:08');
INSERT INTO `st_login_log` VALUES ('1321344106551652405', 'admin', '2020-12-14 16:11:04', '登录成功', '1', 'admin', 'admin', '2020-12-14 16:11:04', '2020-12-14 16:11:04');
INSERT INTO `st_login_log` VALUES ('1321344106551652406', 'admin', '2020-12-27 11:26:35', '登录成功', '1', 'admin', 'admin', '2020-12-27 11:26:35', '2020-12-27 11:26:35');
INSERT INTO `st_login_log` VALUES ('1321344106551652407', 'admin', '2020-12-28 12:11:55', '登录成功', '1', 'admin', 'admin', '2020-12-28 12:11:55', '2020-12-28 12:11:54');
INSERT INTO `st_login_log` VALUES ('1321344106551652408', 'admin', '2020-12-28 12:15:39', '登录成功', '1', 'admin', 'admin', '2020-12-28 12:15:39', '2020-12-28 12:15:38');
INSERT INTO `st_login_log` VALUES ('1321344106551652409', 'admin', '2020-12-28 13:19:17', '登录成功', '1', 'admin', 'admin', '2020-12-28 13:19:17', '2020-12-28 13:19:17');
INSERT INTO `st_login_log` VALUES ('1321344106551652410', 'admin', '2020-12-28 13:36:06', '登录成功', '1', 'admin', 'admin', '2020-12-28 13:36:06', '2020-12-28 13:36:05');
INSERT INTO `st_login_log` VALUES ('1321344106551652411', 'admin', '2020-12-28 13:53:46', '登录成功', '1', 'admin', 'admin', '2020-12-28 13:53:46', '2020-12-28 13:53:46');
INSERT INTO `st_login_log` VALUES ('1321344106551652412', 'admin', '2020-12-28 14:15:35', '登录成功', '1', 'admin', 'admin', '2020-12-28 14:15:35', '2020-12-28 14:15:35');
INSERT INTO `st_login_log` VALUES ('1321344106551652413', 'admin', '2020-12-29 10:47:08', '登录成功', '1', 'admin', 'admin', '2020-12-29 10:47:08', '2020-12-29 10:47:07');
INSERT INTO `st_login_log` VALUES ('1321344106551652414', 'admin', '2020-12-29 12:08:52', '登录成功', '1', 'admin', 'admin', '2020-12-29 12:08:52', '2020-12-29 12:08:52');
INSERT INTO `st_login_log` VALUES ('1321344106551652415', 'admin', '2020-12-29 12:09:17', '登录成功', '1', 'admin', 'admin', '2020-12-29 12:09:17', '2020-12-29 12:09:16');
INSERT INTO `st_login_log` VALUES ('1321344106551652416', 'admin', '2020-12-31 07:08:52', '登录成功', '1', 'admin', 'admin', '2020-12-31 07:08:52', '2020-12-31 07:08:51');
INSERT INTO `st_login_log` VALUES ('1321344106551652417', 'admin', '2020-12-31 08:46:15', '登录成功', '1', 'admin', 'admin', '2020-12-31 08:46:15', '2020-12-31 08:46:15');
INSERT INTO `st_login_log` VALUES ('1321344106551652418', 'admin', '2020-12-31 08:46:43', '登录成功', '1', 'admin', 'admin', '2020-12-31 08:46:43', '2020-12-31 08:46:43');
INSERT INTO `st_login_log` VALUES ('1321344106551652419', 'admin', '2021-01-04 02:06:27', '登录成功', '1', 'admin', 'admin', '2021-01-04 02:06:27', '2021-01-04 02:06:27');
INSERT INTO `st_login_log` VALUES ('1321344106551652420', 'admin', '2021-01-04 03:00:24', '登录成功', '1', 'admin', 'admin', '2021-01-04 03:00:24', '2021-01-04 03:00:24');
INSERT INTO `st_login_log` VALUES ('1321344106551652421', 'admin', '2021-01-04 06:11:23', '登录成功', '1', 'admin', 'admin', '2021-01-04 06:11:23', '2021-01-04 06:11:22');
INSERT INTO `st_login_log` VALUES ('1321344106551652422', 'admin', '2021-01-04 08:12:04', '登录成功', '1', 'admin', 'admin', '2021-01-04 08:12:04', '2021-01-04 08:12:04');
INSERT INTO `st_login_log` VALUES ('1321344106551652423', 'admin', '2021-01-04 09:51:36', '登录成功', '1', 'admin', 'admin', '2021-01-04 09:51:36', '2021-01-04 09:51:35');
INSERT INTO `st_login_log` VALUES ('1321344106551652424', 'admin', '2021-01-05 01:54:05', '登录成功', '1', 'admin', 'admin', '2021-01-05 01:54:05', '2021-01-05 01:54:04');
INSERT INTO `st_login_log` VALUES ('1321344106551652425', 'admin', '2021-01-05 02:22:24', '登录成功', '1', 'admin', 'admin', '2021-01-05 02:22:24', '2021-01-05 02:22:23');
INSERT INTO `st_login_log` VALUES ('1321344106551652426', 'admin', '2021-01-05 02:41:48', '登录成功', '1', 'admin', 'admin', '2021-01-05 02:41:48', '2021-01-05 02:41:47');
INSERT INTO `st_login_log` VALUES ('1321344106551652427', 'admin', '2021-01-05 02:44:59', '登录成功', '1', 'admin', 'admin', '2021-01-05 02:44:59', '2021-01-05 02:44:58');
INSERT INTO `st_login_log` VALUES ('1321344106551652428', 'admin', '2021-01-05 02:51:28', '登录成功', '1', 'admin', 'admin', '2021-01-05 02:51:28', '2021-01-05 02:51:28');
INSERT INTO `st_login_log` VALUES ('1321344106551652429', 'admin', '2021-01-05 04:34:27', '登录成功', '1', 'admin', 'admin', '2021-01-05 04:34:27', '2021-01-05 04:34:26');
INSERT INTO `st_login_log` VALUES ('1321344106551652430', 'admin', '2021-01-05 06:37:36', '登录成功', '1', 'admin', 'admin', '2021-01-05 06:37:36', '2021-01-05 06:37:35');
INSERT INTO `st_login_log` VALUES ('1321344106551652431', 'admin', '2021-01-06 10:59:13', '登录成功', '1', 'admin', 'admin', '2021-01-06 10:59:13', '2021-01-06 10:59:13');
INSERT INTO `st_login_log` VALUES ('1321344106551652432', 'admin', '2021-01-06 11:00:04', '登录成功', '1', 'admin', 'admin', '2021-01-06 11:00:04', '2021-01-06 11:00:04');
INSERT INTO `st_login_log` VALUES ('1321344106551652433', 'admin', '2021-01-07 01:05:22', '登录成功', '1', 'admin', 'admin', '2021-01-07 01:05:22', '2021-01-07 01:05:21');
INSERT INTO `st_login_log` VALUES ('1321344106551652434', 'admin', '2021-01-07 02:23:36', '登录成功', '1', 'admin', 'admin', '2021-01-07 02:23:36', '2021-01-07 02:23:35');
INSERT INTO `st_login_log` VALUES ('1321344106551652435', 'admin', '2021-01-07 02:23:56', '登录成功', '1', 'admin', 'admin', '2021-01-07 02:23:56', '2021-01-07 02:23:56');
INSERT INTO `st_login_log` VALUES ('1321344106551652436', 'admin', '2021-01-07 02:26:39', '登录成功', '1', 'admin', 'admin', '2021-01-07 02:26:39', '2021-01-07 02:26:39');
INSERT INTO `st_login_log` VALUES ('1321344106551652437', 'admin', '2021-01-07 02:28:40', '登录成功', '1', 'admin', 'admin', '2021-01-07 02:28:40', '2021-01-07 02:28:40');
INSERT INTO `st_login_log` VALUES ('1321344106551652438', 'admin', '2021-01-07 08:56:48', '登录成功', '1', 'admin', 'admin', '2021-01-07 08:56:48', '2021-01-07 08:56:47');
INSERT INTO `st_login_log` VALUES ('1321344106551652439', 'admin', '2021-01-07 08:57:02', '登录成功', '1', 'admin', 'admin', '2021-01-07 08:57:02', '2021-01-07 08:57:01');
INSERT INTO `st_login_log` VALUES ('1321344106551652440', 'admin', '2021-01-07 08:58:46', '登录成功', '1', 'admin', 'admin', '2021-01-07 08:58:46', '2021-01-07 08:58:45');
INSERT INTO `st_login_log` VALUES ('1321344106551652441', 'admin', '2021-01-07 08:59:09', '登录成功', '1', 'admin', 'admin', '2021-01-07 08:59:09', '2021-01-07 08:59:09');
INSERT INTO `st_login_log` VALUES ('1321344106551652442', 'admin', '2021-01-07 09:02:55', '登录成功', '1', 'admin', 'admin', '2021-01-07 09:02:55', '2021-01-07 09:02:55');
INSERT INTO `st_login_log` VALUES ('1321344106551652443', 'admin', '2021-01-07 09:07:13', '登录成功', '1', 'admin', 'admin', '2021-01-07 09:07:13', '2021-01-07 09:07:12');
INSERT INTO `st_login_log` VALUES ('1321344106551652444', 'admin', '2021-01-07 09:09:39', '登录成功', '1', 'admin', 'admin', '2021-01-07 09:09:39', '2021-01-07 09:09:39');
INSERT INTO `st_login_log` VALUES ('1321344106551652445', 'admin', '2021-01-07 09:15:06', '登录成功', '1', 'admin', 'admin', '2021-01-07 09:15:06', '2021-01-07 09:15:05');
INSERT INTO `st_login_log` VALUES ('1321344106551652446', 'admin', '2021-01-07 09:15:43', '登录成功', '1', 'admin', 'admin', '2021-01-07 09:15:43', '2021-01-07 09:15:42');
INSERT INTO `st_login_log` VALUES ('1321344106551652447', 'admin', '2021-01-07 09:18:48', '登录成功', '1', 'admin', 'admin', '2021-01-07 09:18:48', '2021-01-07 09:18:48');
INSERT INTO `st_login_log` VALUES ('1321344106551652448', 'admin', '2021-01-07 09:22:07', '登录成功', '1', 'admin', 'admin', '2021-01-07 09:22:07', '2021-01-07 09:22:07');
INSERT INTO `st_login_log` VALUES ('1321344106551652449', 'admin', '2021-01-15 06:21:18', '登录成功', '1', 'admin', 'admin', '2021-01-15 06:21:18', '2021-01-15 06:21:17');
INSERT INTO `st_login_log` VALUES ('1321344106551652450', 'admin', '2021-01-15 06:33:19', '登录成功', '1', 'admin', 'admin', '2021-01-15 06:33:19', '2021-01-15 06:33:19');
INSERT INTO `st_login_log` VALUES ('1321344106551652451', 'admin', '2021-01-15 07:51:05', '登录成功', '1', 'admin', 'admin', '2021-01-15 07:51:05', '2021-01-15 07:51:05');
INSERT INTO `st_login_log` VALUES ('1321344106551652452', 'admin', '2021-01-15 08:25:35', '登录成功', '1', 'admin', 'admin', '2021-01-15 08:25:35', '2021-01-15 08:25:35');
INSERT INTO `st_login_log` VALUES ('1321344106551652453', 'admin', '2021-01-18 01:42:10', '登录成功', '1', 'admin', 'admin', '2021-01-18 01:42:10', '2021-01-18 01:42:10');
INSERT INTO `st_login_log` VALUES ('1321344106551652454', 'admin', '2021-01-18 03:13:01', '登录成功', '1', 'admin', 'admin', '2021-01-18 03:13:01', '2021-01-18 03:13:01');
INSERT INTO `st_login_log` VALUES ('1321344106551652455', 'admin', '2021-01-18 07:25:29', '登录成功', '1', 'admin', 'admin', '2021-01-18 07:25:29', '2021-01-18 07:25:29');
INSERT INTO `st_login_log` VALUES ('1321344106551652456', 'admin', '2021-01-18 08:22:33', '登录成功', '1', 'admin', 'admin', '2021-01-18 08:22:33', '2021-01-18 08:22:32');
INSERT INTO `st_login_log` VALUES ('1321344106551652457', 'admin', '2021-01-18 09:13:25', '登录成功', '1', 'admin', 'admin', '2021-01-18 09:13:25', '2021-01-18 09:13:25');
INSERT INTO `st_login_log` VALUES ('1321344106551652458', 'admin', '2021-01-19 01:09:11', '登录成功', '1', 'admin', 'admin', '2021-01-19 01:09:11', '2021-01-19 01:09:11');
INSERT INTO `st_login_log` VALUES ('1321344106551652459', 'admin', '2021-01-19 03:07:33', '登录成功', '1', 'admin', 'admin', '2021-01-19 03:07:33', '2021-01-19 03:07:33');
INSERT INTO `st_login_log` VALUES ('1321344106551652460', 'admin', '2021-01-19 04:51:41', '登录成功', '1', 'admin', 'admin', '2021-01-19 04:51:41', '2021-01-19 04:51:41');
INSERT INTO `st_login_log` VALUES ('1321344106551652461', 'admin', '2021-01-19 06:39:56', '登录成功', '1', 'admin', 'admin', '2021-01-19 06:39:56', '2021-01-19 06:39:56');
INSERT INTO `st_login_log` VALUES ('1321344106551652462', 'admin', '2021-01-19 08:04:40', '登录成功', '1', 'admin', 'admin', '2021-01-19 08:04:40', '2021-01-19 08:04:39');
INSERT INTO `st_login_log` VALUES ('1321344106551652463', 'admin', '2021-01-19 10:08:27', '登录成功', '1', 'admin', 'admin', '2021-01-19 10:08:27', '2021-01-19 10:08:27');
INSERT INTO `st_login_log` VALUES ('1321344106551652464', 'admin', '2021-01-19 12:12:10', '登录成功', '1', 'admin', 'admin', '2021-01-19 12:12:10', '2021-01-19 12:12:10');
INSERT INTO `st_login_log` VALUES ('1321344106551652465', 'admin', '2021-01-20 00:51:33', '登录成功', '1', 'admin', 'admin', '2021-01-20 00:51:33', '2021-01-20 00:51:32');
INSERT INTO `st_login_log` VALUES ('1321344106551652466', 'admin', '2021-01-20 02:23:25', '登录成功', '1', 'admin', 'admin', '2021-01-20 02:23:25', '2021-01-20 02:23:25');
INSERT INTO `st_login_log` VALUES ('1321344106551652467', 'admin', '2021-01-20 04:41:28', '登录成功', '1', 'admin', 'admin', '2021-01-20 04:41:28', '2021-01-20 04:41:28');
INSERT INTO `st_login_log` VALUES ('1321344106551652468', 'admin', '2021-01-20 06:35:48', '登录成功', '1', 'admin', 'admin', '2021-01-20 06:35:48', '2021-01-20 06:35:48');
INSERT INTO `st_login_log` VALUES ('1321344106551652469', 'admin', '2021-01-20 08:15:16', '登录成功', '1', 'admin', 'admin', '2021-01-20 08:15:16', '2021-01-20 08:15:16');
INSERT INTO `st_login_log` VALUES ('1321344106551652470', 'admin', '2021-01-20 09:07:55', '登录成功', '1', 'admin', 'admin', '2021-01-20 09:07:55', '2021-01-20 09:07:55');
INSERT INTO `st_login_log` VALUES ('1321344106551652471', 'admin', '2021-01-21 06:35:53', '登录成功', '1', 'admin', 'admin', '2021-01-21 06:35:53', '2021-01-21 06:35:53');
INSERT INTO `st_login_log` VALUES ('1321344106551652472', 'admin', '2021-01-21 09:32:04', '登录成功', '1', 'admin', 'admin', '2021-01-21 09:32:04', '2021-01-21 09:32:04');
INSERT INTO `st_login_log` VALUES ('1321344106551652473', 'admin', '2021-01-21 11:51:35', '登录成功', '1', 'admin', 'admin', '2021-01-21 11:51:35', '2021-01-21 11:51:35');
INSERT INTO `st_login_log` VALUES ('1321344106551652474', 'admin', '2021-01-22 04:51:25', '登录成功', '1', 'admin', 'admin', '2021-01-22 04:51:25', '2021-01-22 04:51:25');
INSERT INTO `st_login_log` VALUES ('1321344106551652475', 'admin', '2021-01-22 06:33:49', '登录成功', '1', 'admin', 'admin', '2021-01-22 06:33:49', '2021-01-22 06:33:48');
INSERT INTO `st_login_log` VALUES ('1321344106551652476', 'admin', '2021-01-22 08:54:30', '登录成功', '1', 'admin', 'admin', '2021-01-22 08:54:30', '2021-01-22 08:54:30');
INSERT INTO `st_login_log` VALUES ('1321344106551652477', 'admin', '2021-01-27 01:52:20', '登录成功', '1', 'admin', 'admin', '2021-01-27 01:52:20', '2021-01-27 01:52:20');
INSERT INTO `st_login_log` VALUES ('1321344106551652478', 'admin', '2021-01-27 07:25:49', '登录成功', '1', 'admin', 'admin', '2021-01-27 07:25:49', '2021-01-27 07:25:49');
INSERT INTO `st_login_log` VALUES ('1321344106551652479', 'admin', '2021-01-28 01:35:00', '登录成功', '1', 'admin', 'admin', '2021-01-28 01:35:00', '2021-01-28 01:34:59');
INSERT INTO `st_login_log` VALUES ('1321344106551652480', 'admin', '2021-01-28 01:36:19', '登录成功', '1', 'admin', 'admin', '2021-01-28 01:36:19', '2021-01-28 01:36:19');
INSERT INTO `st_login_log` VALUES ('1321344106551652481', 'admin', '2021-01-28 01:36:33', '登录成功', '1', 'admin', 'admin', '2021-01-28 01:36:33', '2021-01-28 01:36:32');
INSERT INTO `st_login_log` VALUES ('1321344106551652482', 'admin', '2021-01-28 01:47:47', '登录成功', '1', 'admin', 'admin', '2021-01-28 01:47:47', '2021-01-28 01:47:46');
INSERT INTO `st_login_log` VALUES ('1321344106551652483', 'admin', '2021-01-28 01:48:20', '登录成功', '1', 'admin', 'admin', '2021-01-28 01:48:20', '2021-01-28 01:48:19');
INSERT INTO `st_login_log` VALUES ('1321344106551652484', 'admin', '2021-01-28 01:48:33', '登录成功', '1', 'admin', 'admin', '2021-01-28 01:48:33', '2021-01-28 01:48:33');
INSERT INTO `st_login_log` VALUES ('1321344106551652485', 'admin', '2021-01-28 01:49:09', '登录成功', '1', 'admin', 'admin', '2021-01-28 01:49:09', '2021-01-28 01:49:08');
INSERT INTO `st_login_log` VALUES ('1321344106551652486', 'admin', '2021-01-28 01:51:27', '登录成功', '1', 'admin', 'admin', '2021-01-28 01:51:27', '2021-01-28 01:51:26');
INSERT INTO `st_login_log` VALUES ('1321344106551652487', 'admin', '2021-01-28 02:22:35', '登录成功', '1', 'admin', 'admin', '2021-01-28 02:22:35', '2021-01-28 02:22:35');
INSERT INTO `st_login_log` VALUES ('1321344106551652488', 'admin', '2021-01-28 02:29:45', '登录成功', '1', 'admin', 'admin', '2021-01-28 02:29:45', '2021-01-28 02:29:45');
INSERT INTO `st_login_log` VALUES ('1321344106551652489', 'admin', '2021-01-28 03:27:44', '登录成功', '1', 'admin', 'admin', '2021-01-28 03:27:44', '2021-01-28 03:27:43');
INSERT INTO `st_login_log` VALUES ('1321344106551652490', 'admin', '2021-02-02 09:19:23', '登录成功', '1', 'admin', 'admin', '2021-02-02 09:19:23', '2021-02-02 09:19:22');
INSERT INTO `st_login_log` VALUES ('1321344106551652491', 'admin', '2021-02-02 09:20:10', '登录成功', '1', 'admin', 'admin', '2021-02-02 09:20:10', '2021-02-02 09:20:10');
INSERT INTO `st_login_log` VALUES ('1321344106551652492', 'admin', '2021-02-02 10:56:05', '登录成功', '1', 'admin', 'admin', '2021-02-02 10:56:05', '2021-02-02 10:56:04');
INSERT INTO `st_login_log` VALUES ('1321344106551652493', 'admin', '2021-02-05 09:48:39', '登录成功', '1', 'admin', 'admin', '2021-02-05 09:48:39', '2021-02-05 09:48:38');
INSERT INTO `st_login_log` VALUES ('1321344106551652494', 'admin', '2021-02-07 02:45:41', '登录成功', '1', 'admin', 'admin', '2021-02-07 02:45:41', '2021-02-07 02:45:40');
INSERT INTO `st_login_log` VALUES ('1321344106551652495', 'admin', '2021-02-07 03:04:30', '登录成功', '1', 'admin', 'admin', '2021-02-07 03:04:30', '2021-02-07 03:04:29');
INSERT INTO `st_login_log` VALUES ('1321344106551652496', 'admin', '2021-02-07 06:09:47', '登录成功', '1', 'admin', 'admin', '2021-02-07 06:09:47', '2021-02-07 06:09:46');
INSERT INTO `st_login_log` VALUES ('1321344106551652497', 'admin', '2021-02-07 07:51:30', '登录成功', '1', 'admin', 'admin', '2021-02-07 07:51:30', '2021-02-07 07:51:29');
INSERT INTO `st_login_log` VALUES ('1321344106551652498', 'admin', '2021-02-07 08:51:56', '登录成功', '1', 'admin', 'admin', '2021-02-07 08:51:56', '2021-02-07 08:51:55');
INSERT INTO `st_login_log` VALUES ('1321344106551652499', 'admin', '2021-02-08 01:45:44', '登录成功', '1', 'admin', 'admin', '2021-02-08 01:45:44', '2021-02-08 01:45:44');
INSERT INTO `st_login_log` VALUES ('1321344106551652500', 'admin', '2021-02-19 03:09:30', '登录成功', '1', 'admin', 'admin', '2021-02-19 03:09:30', '2021-02-19 03:09:29');
INSERT INTO `st_login_log` VALUES ('1321344106551652501', 'admin', '2021-02-19 04:17:12', '登录成功', '1', 'admin', 'admin', '2021-02-19 04:17:12', '2021-02-19 04:17:11');
INSERT INTO `st_login_log` VALUES ('1321344106551652502', 'admin', '2021-02-19 04:22:05', '登录成功', '1', 'admin', 'admin', '2021-02-19 04:22:05', '2021-02-19 04:22:04');
INSERT INTO `st_login_log` VALUES ('1321344106551652503', 'admin', '2021-02-19 04:55:47', '登录成功', '1', 'admin', 'admin', '2021-02-19 04:55:47', '2021-02-19 04:55:47');
INSERT INTO `st_login_log` VALUES ('1321344106551652504', 'admin', '2021-02-19 06:14:16', '登录成功', '1', 'admin', 'admin', '2021-02-19 06:14:16', '2021-02-19 06:14:15');
INSERT INTO `st_login_log` VALUES ('1321344106551652505', 'admin', '2021-02-19 06:36:05', '登录成功', '1', 'admin', 'admin', '2021-02-19 06:36:05', '2021-02-19 06:36:05');
INSERT INTO `st_login_log` VALUES ('1321344106551652506', 'admin', '2021-02-19 06:36:51', '登录成功', '1', 'admin', 'admin', '2021-02-19 06:36:51', '2021-02-19 06:36:50');
INSERT INTO `st_login_log` VALUES ('1321344106551652507', 'admin', '2021-02-19 08:32:07', '登录成功', '1', 'admin', 'admin', '2021-02-19 08:32:07', '2021-02-19 08:32:06');
INSERT INTO `st_login_log` VALUES ('1321344106551652508', 'admin', '2021-03-02 11:08:18', '登录成功', '1', 'admin', 'admin', '2021-03-02 11:08:18', '2021-03-02 11:08:18');
INSERT INTO `st_login_log` VALUES ('1321344106551652509', 'admin', '2021-03-02 11:41:54', '登录成功', '1', 'admin', 'admin', '2021-03-02 11:41:54', '2021-03-02 11:41:54');
INSERT INTO `st_login_log` VALUES ('1321344106551652510', 'admin', '2021-03-10 10:31:50', '登录成功', '1', 'admin', 'admin', '2021-03-10 10:31:50', '2021-03-10 10:31:49');
INSERT INTO `st_login_log` VALUES ('1321344106551652511', 'admin', '2021-03-26 11:15:49', '登录成功', '1', 'admin', 'admin', '2021-03-26 11:15:49', '2021-03-26 11:15:48');
INSERT INTO `st_login_log` VALUES ('1321344106551652512', 'admin', '2021-03-26 11:25:40', '登录成功', '1', 'admin', 'admin', '2021-03-26 11:25:40', '2021-03-26 11:25:40');
INSERT INTO `st_login_log` VALUES ('1321344106551652513', 'admin', '2021-03-26 11:29:11', '登录成功', '1', 'admin', 'admin', '2021-03-26 11:29:11', '2021-03-26 11:29:10');
INSERT INTO `st_login_log` VALUES ('1321344106551652514', 'admin', '2021-03-26 11:34:24', '登录成功', '1', 'admin', 'admin', '2021-03-26 11:34:24', '2021-03-26 11:34:23');
INSERT INTO `st_login_log` VALUES ('1321344106551652515', 'admin', '2021-03-26 11:38:05', '登录成功', '1', 'admin', 'admin', '2021-03-26 11:38:05', '2021-03-26 11:38:04');
INSERT INTO `st_login_log` VALUES ('1321344106551652516', 'admin', '2021-03-26 11:38:24', '登录成功', '1', 'admin', 'admin', '2021-03-26 11:38:24', '2021-03-26 11:38:23');
INSERT INTO `st_login_log` VALUES ('1321344106551652517', 'admin', '2021-03-26 11:40:48', '登录成功', '1', 'admin', 'admin', '2021-03-26 11:40:48', '2021-03-26 11:40:48');
INSERT INTO `st_login_log` VALUES ('1321344106551652518', 'admin', '2021-03-26 11:57:28', '登录成功', '1', 'admin', 'admin', '2021-03-26 11:57:28', '2021-03-26 11:57:27');
INSERT INTO `st_login_log` VALUES ('1321344106551652519', 'admin', '2021-03-26 12:00:57', '登录成功', '1', 'admin', 'admin', '2021-03-26 12:00:57', '2021-03-26 12:00:57');
INSERT INTO `st_login_log` VALUES ('1321344106551652520', 'admin', '2021-03-26 12:01:57', '登录成功', '1', 'admin', 'admin', '2021-03-26 12:01:57', '2021-03-26 12:01:57');
INSERT INTO `st_login_log` VALUES ('1321344106551652521', 'admin', '2021-03-26 12:02:13', '登录成功', '1', 'admin', 'admin', '2021-03-26 12:02:13', '2021-03-26 12:02:12');
INSERT INTO `st_login_log` VALUES ('1321344106551652522', 'admin', '2021-03-26 12:04:28', '登录成功', '1', 'admin', 'admin', '2021-03-26 12:04:28', '2021-03-26 12:04:28');
INSERT INTO `st_login_log` VALUES ('1321344106551652523', 'admin', '2021-03-26 12:07:11', '登录成功', '1', 'admin', 'admin', '2021-03-26 12:07:11', '2021-03-26 12:07:10');
INSERT INTO `st_login_log` VALUES ('1321344106551652524', 'admin', '2021-03-26 12:15:59', '登录成功', '1', 'admin', 'admin', '2021-03-26 12:15:59', '2021-03-26 12:15:58');
INSERT INTO `st_login_log` VALUES ('1321344106551652525', 'admin', '2021-03-26 12:24:08', '登录成功', '1', 'admin', 'admin', '2021-03-26 12:24:08', '2021-03-26 12:24:08');
INSERT INTO `st_login_log` VALUES ('1321344106551652526', 'admin', '2021-03-26 12:31:09', '登录成功', '1', 'admin', 'admin', '2021-03-26 12:31:09', '2021-03-26 12:31:08');
INSERT INTO `st_login_log` VALUES ('1321344106551652527', 'admin', '2021-03-26 12:41:59', '登录成功', '1', 'admin', 'admin', '2021-03-26 12:41:59', '2021-03-26 12:41:59');
INSERT INTO `st_login_log` VALUES ('1321344106551652528', 'admin', '2021-03-26 12:49:50', '登录成功', '1', 'admin', 'admin', '2021-03-26 12:49:50', '2021-03-26 12:49:50');
INSERT INTO `st_login_log` VALUES ('1321344106551652529', 'admin', '2021-03-26 12:51:37', '登录成功', '1', 'admin', 'admin', '2021-03-26 12:51:37', '2021-03-26 12:51:36');
INSERT INTO `st_login_log` VALUES ('1321344106551652530', 'admin', '2021-03-26 12:56:28', '登录成功', '1', 'admin', 'admin', '2021-03-26 12:56:28', '2021-03-26 12:56:27');
INSERT INTO `st_login_log` VALUES ('1321344106551652531', 'admin', '2021-05-06 10:21:11', '登录成功', '1', 'admin', 'admin', '2021-05-06 10:21:11', '2021-05-06 10:21:11');
INSERT INTO `st_login_log` VALUES ('1321344106551652532', 'admin', '2021-05-12 06:28:35', '登录成功', '1', 'admin', 'admin', '2021-05-12 06:28:35', '2021-05-12 06:28:35');
INSERT INTO `st_login_log` VALUES ('1321344106551652533', 'admin', '2021-05-12 09:32:02', '登录成功', '1', 'admin', 'admin', '2021-05-12 09:32:02', '2021-05-12 09:32:01');
INSERT INTO `st_login_log` VALUES ('1321344106551652534', 'admin', '2021-05-12 11:09:59', '登录成功', '1', 'admin', 'admin', '2021-05-12 11:09:59', '2021-05-12 11:09:59');
INSERT INTO `st_login_log` VALUES ('1321344106551652535', 'admin', '2021-05-13 00:49:36', '登录成功', '1', 'admin', 'admin', '2021-05-13 00:49:36', '2021-05-13 00:49:35');
INSERT INTO `st_login_log` VALUES ('1321344106551652536', 'admin', '2021-05-13 01:53:28', '登录成功', '1', 'admin', 'admin', '2021-05-13 01:53:28', '2021-05-13 01:53:27');
INSERT INTO `st_login_log` VALUES ('1321344106551652537', 'admin', '2021-05-13 08:36:53', '登录成功', '1', 'admin', 'admin', '2021-05-13 08:36:53', '2021-05-13 08:36:53');
INSERT INTO `st_login_log` VALUES ('1321344106551652538', 'admin', '2021-06-07 01:18:23', '登录成功', '1', 'admin', 'admin', '2021-06-07 01:18:23', '2021-06-07 01:18:23');
INSERT INTO `st_login_log` VALUES ('1321344106551652539', 'admin', '2021-06-08 02:40:59', '登录成功', '1', 'admin', 'admin', '2021-06-08 02:40:59', '2021-06-08 02:40:58');
INSERT INTO `st_login_log` VALUES ('1321344106551652540', 'admin', '2021-06-15 10:33:40', '登录成功', '1', 'admin', 'admin', '2021-06-15 10:33:40', '2021-06-15 10:33:39');
INSERT INTO `st_login_log` VALUES ('1321344106551652541', 'admin', '2021-07-01 08:05:09', '登录成功', '1', 'admin', 'admin', '2021-07-01 08:05:09', '2021-07-01 08:05:09');
INSERT INTO `st_login_log` VALUES ('1321344106551652542', 'admin', '2021-07-01 09:25:59', '登录成功', '1', 'admin', 'admin', '2021-07-01 09:25:59', '2021-07-01 09:25:59');
INSERT INTO `st_login_log` VALUES ('1321344106551652543', 'admin', '2021-07-01 09:39:43', '登录成功', '1', 'admin', 'admin', '2021-07-01 09:39:43', '2021-07-01 09:39:42');
INSERT INTO `st_login_log` VALUES ('1321344106551652544', 'admin', '2021-07-02 00:53:47', '登录成功', '1', 'admin', 'admin', '2021-07-02 00:53:47', '2021-07-02 00:53:46');
INSERT INTO `st_login_log` VALUES ('1321344106551652545', 'admin', '2021-07-02 01:51:27', '登录成功', '1', 'admin', 'admin', '2021-07-02 01:51:27', '2021-07-02 01:51:26');
INSERT INTO `st_login_log` VALUES ('1321344106551652546', 'admin', '2021-07-02 02:41:05', '登录成功', '1', 'admin', 'admin', '2021-07-02 02:41:05', '2021-07-02 02:41:04');
INSERT INTO `st_login_log` VALUES ('1321344106551652547', 'admin', '2021-07-02 04:17:50', '登录成功', '1', 'admin', 'admin', '2021-07-02 04:17:50', '2021-07-02 04:17:49');
INSERT INTO `st_login_log` VALUES ('1321344106551652548', 'admin', '2021-07-02 06:14:51', '登录成功', '1', 'admin', 'admin', '2021-07-02 06:14:51', '2021-07-02 06:14:50');
INSERT INTO `st_login_log` VALUES ('1321344106551652549', 'admin', '2021-07-02 06:48:05', '登录成功', '1', 'admin', 'admin', '2021-07-02 06:48:05', '2021-07-02 06:48:05');
INSERT INTO `st_login_log` VALUES ('1321344106551652550', 'admin', '2021-07-02 06:51:44', '登录成功', '1', 'admin', 'admin', '2021-07-02 06:51:44', '2021-07-02 06:51:43');
INSERT INTO `st_login_log` VALUES ('1321344106551652551', 'admin', '2021-07-02 08:11:01', '登录成功', '1', 'admin', 'admin', '2021-07-02 08:11:01', '2021-07-02 08:11:00');
INSERT INTO `st_login_log` VALUES ('1321344106551652552', 'admin', '2021-07-12 03:01:56', '登录成功', '1', 'admin', 'admin', '2021-07-12 03:01:56', '2021-07-12 03:01:55');
INSERT INTO `st_login_log` VALUES ('1321344106551652553', 'admin', '2021-07-12 04:17:06', '登录成功', '1', 'admin', 'admin', '2021-07-12 04:17:06', '2021-07-12 04:17:06');
INSERT INTO `st_login_log` VALUES ('1321344106551652554', 'admin', '2021-07-12 07:59:30', '登录成功', '1', 'admin', 'admin', '2021-07-12 07:59:30', '2021-07-12 07:59:29');
INSERT INTO `st_login_log` VALUES ('1321344106551652555', 'admin', '2021-07-14 11:24:12', '登录成功', '1', 'admin', 'admin', '2021-07-14 11:24:12', '2021-07-14 11:24:11');

-- ----------------------------
-- Table structure for `st_resource`
-- ----------------------------
DROP TABLE IF EXISTS `st_resource`;
CREATE TABLE `st_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '菜单名称',
  `full_id` varchar(200) COLLATE utf8_bin DEFAULT '' COMMENT '菜单编号路径',
  `icon_class` varchar(32) COLLATE utf8_bin DEFAULT '' COMMENT '图标样式类',
  `show_order` int(11) DEFAULT NULL COMMENT '排序0',
  `url` varchar(64) COLLATE utf8_bin DEFAULT '' COMMENT '链接',
  `component` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '页面路径',
  `authority_id` bigint(20) DEFAULT NULL COMMENT '权限ID',
  `pid` bigint(20) DEFAULT NULL COMMENT '父id',
  `resource_desc` varchar(200) COLLATE utf8_bin DEFAULT '' COMMENT '菜单描述',
  `yn_flag` char(2) COLLATE utf8_bin DEFAULT '1' COMMENT '有效标志',
  `creator` varchar(32) COLLATE utf8_bin DEFAULT '' COMMENT '创建人',
  `editor` varchar(32) COLLATE utf8_bin DEFAULT '' COMMENT '修改人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='菜单表';

-- ----------------------------
-- Records of st_resource
-- ----------------------------
INSERT INTO `st_resource` VALUES ('1', '系统管理', '0', 'nested', '3', '/sysmgr', '/layout/Layout', '1', '0', null, '1', 'admin', 'admin', '2019-07-12 09:20:40', '2020-10-27 06:53:54');
INSERT INTO `st_resource` VALUES ('2', '用户管理', '0-1', 'user', '3', 'user', '/sysmgr/user/index', '7', '1', null, '1', 'admin', 'admin', '2019-07-12 09:20:40', '2020-10-23 15:01:12');
INSERT INTO `st_resource` VALUES ('3', '角色管理', '0-1', 'peoples', '3', 'role', '/sysmgr/role/index', '10', '1', null, '1', 'admin', 'admin', '2019-07-12 09:20:40', '2020-08-14 08:43:05');
INSERT INTO `st_resource` VALUES ('4', '菜单管理', '0-1', 'list', '4', 'menu', '/sysmgr/menu/index', '13', '1', null, '1', 'admin', 'admin', '2019-07-12 09:20:40', '2019-08-19 11:04:42');
INSERT INTO `st_resource` VALUES ('5', '权限管理', '0-1', 'password', '5', 'authority', '/sysmgr/authority/index', '16', '1', null, '1', 'admin', 'admin', '2019-07-12 09:20:40', '2019-08-19 11:04:43');
INSERT INTO `st_resource` VALUES ('6', '基础信息', '0', 'nested', '2', '/baseinfo', '/layout/Layout', '1', '0', null, '1', 'admin', 'admin', '2019-07-12 09:20:40', '2020-10-27 06:53:48');
INSERT INTO `st_resource` VALUES ('7', '字典管理', '0-6', 'component', '1', 'dict', '/baseinfo/dict', '35', '6', null, '1', 'admin', 'admin', '2019-07-12 09:54:30', '2020-10-28 01:22:40');
INSERT INTO `st_resource` VALUES ('8', '附件管理', '0-1', 'zip', '7', 'att', '/sysmgr/att/index', '39', '1', null, '1', 'admin', 'admin', '2019-07-12 10:25:37', '2019-09-25 06:18:31');
INSERT INTO `st_resource` VALUES ('9', '登陆日志', '0-1', 'people', '6', 'loginlog', '/sysmgr/loginlog/index', '31', '1', null, '0', 'admin', 'admin', '2019-07-12 10:35:56', '2020-08-21 08:51:59');
INSERT INTO `st_resource` VALUES ('10', '系统备份', '0-1', 'clipboard', '9', 'backup', '/sysmgr/backup/index', '44', '1', null, '1', 'admin', 'admin', '2019-07-12 10:49:11', '2019-09-25 06:18:49');
INSERT INTO `st_resource` VALUES ('11', '系统日志', '0-1', 'documentation', '8', 'syslog', '/sysmgr/syslog/index', '42', '1', null, '0', 'admin', 'admin', '2019-07-12 11:58:59', '2020-08-21 08:52:03');
INSERT INTO `st_resource` VALUES ('12', '定时任务', '0-1', 'guide', '20', 'schedulejob', '/sysmgr/schedulejob/index', '47', '1', null, '0', 'admin', 'admin', '2019-08-19 03:02:50', '2020-08-21 08:41:03');
INSERT INTO `st_resource` VALUES ('13', '个人空间', '0', 'nested', '1', '/tools', '/layout/Layout', '1', '0', '', '1', 'admin', 'admin', '2019-09-25 06:28:53', '2020-10-27 06:53:41');
INSERT INTO `st_resource` VALUES ('14', '待办事项', '0-13', 'table', null, 'todolist', '/tools/todolist', '53', '13', null, '1', 'admin', 'admin', '2019-09-25 07:09:20', '2019-09-25 07:17:58');
INSERT INTO `st_resource` VALUES ('15', '系统公告', '0-1', 'user', '30', 'InformManagement', '/sysmgr/inform/index', '57', '1', null, '1', 'admin', 'admin', '2020-08-11 08:57:20', '2020-08-11 08:57:20');
INSERT INTO `st_resource` VALUES ('16', '部门管理', '0-1', 'peoples', '10', 'dept', '/sysmgr/dept/index', '69', '1', null, '1', 'admin', 'admin', '2020-08-14 08:44:03', '2020-08-14 08:44:17');
INSERT INTO `st_resource` VALUES ('17', '服务监控', '0', 'nested', '39', '/monitor', '/layout/Layout', '1', '0', null, '1', 'admin', 'admin', '2020-08-21 07:52:18', '2020-10-28 12:54:56');
INSERT INTO `st_resource` VALUES ('18', '系统监控', '0', 'nested', '40', '/monitor/server', '/layout/Layout', '1', '0', null, '0', 'admin', 'admin', '2020-08-21 07:52:19', '2020-08-21 07:52:32');
INSERT INTO `st_resource` VALUES ('19', '系统监控', '0-17', 'documentation', '1', 'server', '/monitor/server/index', '74', '17', null, '1', 'admin', 'admin', '2020-08-21 07:59:49', '2020-08-24 03:33:36');
INSERT INTO `st_resource` VALUES ('20', '定时任务', '0-17', 'guide', '2', 'schedulejob', '/monitor/schedulejob/index', '76', '17', null, '1', 'admin', 'admin', '2020-08-21 08:23:27', '2020-08-24 03:45:07');
INSERT INTO `st_resource` VALUES ('21', '日志管理', '0-1', 'documentation', '38', '/log', '/layout/Layout', '1', '1', null, '1', 'admin', 'admin', '2020-08-21 08:47:00', '2021-02-19 06:23:52');
INSERT INTO `st_resource` VALUES ('22', '登录日志', '0-1-21', 'people', '2', 'loginlog', '/sysmgr/log/loginlog/index', '152', '21', null, '1', 'admin', 'admin', '2020-08-21 08:48:01', '2020-10-28 13:14:23');
INSERT INTO `st_resource` VALUES ('23', '操作日志', '0-1-21', 'documentation', '1', 'syslog', '/sysmgr/log/syslog/index', '151', '21', null, '1', 'admin', 'admin', '2020-08-21 08:48:56', '2020-10-28 13:14:29');
INSERT INTO `st_resource` VALUES ('24', '系统工具', '0', 'nested', '40', '/tool', '/layout/Layout', '1', '0', null, '1', 'admin', 'admin', '2020-08-24 03:30:35', '2020-10-27 06:55:09');
INSERT INTO `st_resource` VALUES ('25', 'Swagger_API接口文档', '0-24', 'documentation', '1', 'swagger', '/tool/swagger/index', '83', '24', null, '1', 'admin', 'admin', '2020-08-24 03:33:54', '2020-08-24 03:34:31');
INSERT INTO `st_resource` VALUES ('26', '数据监控', '0-17', 'documentation', '3', 'druid', '/monitor/druid/index', '90', '17', null, '1', 'admin', 'admin', '2020-08-24 06:42:42', '2020-08-24 06:46:14');
INSERT INTO `st_resource` VALUES ('27', 'w字典管理', '0-6', 'user', '2', 'wdict', '/sysmgr/dict/index', '96', '6', null, '0', 'admin', 'admin', '2020-10-23 09:15:28', '2020-10-23 09:36:58');
INSERT INTO `st_resource` VALUES ('28', 'w字典管理', '0-1', 'component', '32', 'wdict', '/sysmgr/wdict/index', '108', '1', null, '1', 'admin', 'admin', '2020-10-23 09:36:29', '2020-10-24 05:22:57');
INSERT INTO `st_resource` VALUES ('29', '车辆管理', '0-1', 'user', '37', 'TwoTable', '/sysmgr/car/index', '117', '1', null, '0', 'admin', 'admin', '2020-10-26 15:43:39', '2020-10-27 14:12:07');
INSERT INTO `st_resource` VALUES ('30', '表格样例管理', '0', 'nested', '41', '/test', '/layout/Layout', '1', '0', null, '1', 'admin', 'admin', '2020-10-27 06:52:50', '2020-10-27 06:55:16');
INSERT INTO `st_resource` VALUES ('31', '左树右表', '0-30', 'user', '1', 'TreeAndTable', '/demo/treeAndTable/treeAndTableList', '125', '30', null, '1', 'admin', 'admin', '2020-10-27 06:57:57', '2020-10-27 07:55:37');
INSERT INTO `st_resource` VALUES ('32', '树形表格', '0-30', 'user', '2', 'TreeTable', '/demo/treeTable/treeTableList', '129', '30', null, '1', 'admin', 'admin', '2020-10-27 07:53:22', '2020-10-27 07:53:32');
INSERT INTO `st_resource` VALUES ('33', '展开表格', '0-30', 'user', '3', 'ExpandTableList', '/demo/expandTable/expandTableList', '133', '30', null, '1', 'admin', 'admin', '2020-10-27 08:33:01', '2020-10-27 08:33:01');
INSERT INTO `st_resource` VALUES ('34', '文章表格', '0-30', 'user', '4', 'ComplexTable', '/demo/table/tableList', '125', '30', null, '1', 'admin', 'admin', '2020-10-27 12:07:15', '2020-10-27 12:07:15');
INSERT INTO `st_resource` VALUES ('35', '双表联动', '0-30', 'user', '5', 'TwoTable', '/demo/car/index', '142', '30', null, '1', 'admin', 'admin', '2020-10-27 14:10:11', '2020-10-27 14:10:37');
INSERT INTO `st_resource` VALUES ('36', '组织管理', '0-1', 'peoples', '39', 'organization', '/sysmgr/organization/organizationList', '157', '1', null, '1', 'admin', 'admin', '2020-10-29 07:51:02', '2020-10-29 07:52:55');
INSERT INTO `st_resource` VALUES ('37', 'w部门管理', '0-1', 'user', '40', 'wdept', '/sysmgr/ruoyi/dept/index', '162', '1', null, '1', 'admin', 'admin', '2020-10-30 02:09:49', '2020-10-30 02:09:49');
INSERT INTO `st_resource` VALUES ('38', 'w岗位管理', '0-1', 'user', '41', 'wpost', '/sysmgr/ruoyi/post/index', '168', '1', null, '1', 'admin', 'admin', '2020-10-30 09:34:19', '2020-10-30 09:34:19');
INSERT INTO `st_resource` VALUES ('39', 'oa系统', '0', 'nested', '42', '/oasys', '/layout/Layout', '1', '0', null, '1', 'admin', 'admin', '2020-11-26 15:40:56', '2020-11-26 15:40:56');
INSERT INTO `st_resource` VALUES ('40', '公共网盘', '0-39', 'user', '1', 'oasysfile', '/oa_sys/PublicNetDisk', '175', '39', null, '1', 'admin', 'admin', '2020-11-26 15:45:00', '2020-11-30 15:13:05');
INSERT INTO `st_resource` VALUES ('41', '私人网盘', '0-39', 'user', '2', 'oasyspersonalfile', '/oa_sys/PersonalNetDisk', '175', '39', null, '1', 'admin', 'admin', '2020-11-30 15:16:42', '2020-11-30 15:16:42');
INSERT INTO `st_resource` VALUES ('42', '考勤-签到签退', '0-39', 'user', '3', 'oasysignInsignout', '/oa_sys/SignInSignOut', '180', '39', null, '1', 'admin', 'admin', '2020-12-02 14:51:20', '2020-12-02 14:56:09');
INSERT INTO `st_resource` VALUES ('43', '签到记录', '0-39', 'user', '4', 'oasysignrecord', '/oa_sys/SignRecord', '180', '39', null, '1', 'admin', 'admin', '2020-12-02 14:59:54', '2020-12-02 14:59:54');
INSERT INTO `st_resource` VALUES ('44', '签到设置', '0-39', 'user', '5', 'oasyssignsetting', '/oa_sys/SignSetting', '180', '39', null, '1', 'admin', 'admin', '2020-12-02 15:10:56', '2020-12-02 15:10:56');
INSERT INTO `st_resource` VALUES ('45', '请假', '0-39', 'user', '6', 'oasysaskleave', '/oa_sys/AskLeave', '182', '39', null, '1', 'admin', 'admin', '2020-12-02 15:18:28', '2020-12-02 15:18:28');
INSERT INTO `st_resource` VALUES ('46', '审批请假', '0-39', 'user', '7', 'oasyscheckleave', '/oa_sys/CheckLeave', '182', '39', null, '1', 'admin', 'admin', '2020-12-02 15:22:29', '2020-12-02 15:22:29');
INSERT INTO `st_resource` VALUES ('47', '聊天室', '0', 'nested', '43', '/chatsys', '/layout/Layout', '1', '0', null, '1', 'admin', 'admin', '2020-12-28 13:51:12', '2020-12-28 13:51:12');
INSERT INTO `st_resource` VALUES ('48', '聊天室', '0-47', 'user', '1', 'chatsys', '/websocket/chat', '185', '47', null, '1', 'admin', 'admin', '2020-12-28 13:53:08', '2020-12-28 13:55:50');

-- ----------------------------
-- Table structure for `st_role`
-- ----------------------------
DROP TABLE IF EXISTS `st_role`;
CREATE TABLE `st_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '角色名称',
  `role_desc` varchar(200) COLLATE utf8_bin DEFAULT '' COMMENT '角色描述',
  `yn_flag` char(2) COLLATE utf8_bin DEFAULT '1' COMMENT '有效标志',
  `creator` varchar(32) COLLATE utf8_bin DEFAULT '' COMMENT '创建人',
  `editor` varchar(32) COLLATE utf8_bin DEFAULT '' COMMENT '修改人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='角色表';

-- ----------------------------
-- Records of st_role
-- ----------------------------
INSERT INTO `st_role` VALUES ('1', 'admin', null, '1', 'admin', 'admin', '2020-10-20 02:52:55', '2020-10-24 05:17:12');
INSERT INTO `st_role` VALUES ('2', '管理员', '系统管理员', '0', null, 'admin', '2018-12-29 11:23:15', '2020-10-24 05:17:01');
INSERT INTO `st_role` VALUES ('3', '供应商', '供应商大大', '1', null, null, null, '2020-08-17 04:27:53');
INSERT INTO `st_role` VALUES ('4', '游客', '游客大大', '1', null, null, null, '2020-08-17 04:28:07');
INSERT INTO `st_role` VALUES ('5', '管理员', null, '0', 'admin', 'admin', '2020-10-20 01:55:29', '2020-10-20 02:52:33');

-- ----------------------------
-- Table structure for `st_role_authority`
-- ----------------------------
DROP TABLE IF EXISTS `st_role_authority`;
CREATE TABLE `st_role_authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `authority_id` bigint(20) NOT NULL COMMENT '权限ID',
  `yn_flag` char(2) COLLATE utf8_bin DEFAULT '1' COMMENT '有效标志',
  `creator` varchar(32) COLLATE utf8_bin DEFAULT '' COMMENT '创建人',
  `editor` varchar(32) COLLATE utf8_bin DEFAULT '' COMMENT '修改人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4710 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='角色权限关系表';

-- ----------------------------
-- Records of st_role_authority
-- ----------------------------
INSERT INTO `st_role_authority` VALUES ('219', '1', '0', '0', 'admin', null, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES ('220', '1', '1', '0', 'admin', null, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES ('221', '1', '2', '0', 'admin', null, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES ('222', '1', '3', '0', 'admin', null, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES ('223', '1', '4', '0', 'admin', null, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES ('224', '1', '5', '0', 'admin', null, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES ('225', '1', '6', '0', 'admin', null, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES ('226', '1', '7', '0', 'admin', null, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES ('227', '1', '8', '0', 'admin', null, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES ('228', '1', '9', '0', 'admin', null, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES ('229', '1', '10', '0', 'admin', null, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES ('230', '1', '11', '0', 'admin', null, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES ('231', '1', '12', '0', 'admin', null, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES ('232', '1', '13', '0', 'admin', null, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES ('233', '1', '14', '0', 'admin', null, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES ('234', '1', '15', '0', 'admin', null, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES ('235', '1', '16', '0', 'admin', null, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES ('236', '1', '17', '0', 'admin', null, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES ('237', '1', '18', '0', 'admin', null, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES ('238', '1', '30', '0', 'admin', null, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES ('239', '1', '31', '0', 'admin', null, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES ('240', '1', '32', '0', 'admin', null, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES ('241', '1', '33', '0', 'admin', null, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES ('242', '1', '34', '0', 'admin', null, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES ('243', '1', '35', '0', 'admin', null, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES ('244', '1', '36', '0', 'admin', null, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES ('245', '1', '37', '0', 'admin', null, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES ('246', '3', '0', '0', 'admin', null, '2019-07-26 05:54:06', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('247', '3', '1', '0', 'admin', null, '2019-07-26 05:54:06', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('248', '1', '0', '0', 'admin', null, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES ('249', '1', '1', '0', 'admin', null, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES ('250', '1', '2', '0', 'admin', null, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES ('251', '1', '3', '0', 'admin', null, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES ('252', '1', '4', '0', 'admin', null, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES ('253', '1', '5', '0', 'admin', null, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES ('254', '1', '6', '0', 'admin', null, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES ('255', '1', '7', '0', 'admin', null, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES ('256', '1', '8', '0', 'admin', null, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES ('257', '1', '9', '0', 'admin', null, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES ('258', '1', '10', '0', 'admin', null, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES ('259', '1', '11', '0', 'admin', null, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES ('260', '1', '12', '0', 'admin', null, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES ('261', '1', '13', '0', 'admin', null, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES ('262', '1', '14', '0', 'admin', null, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES ('263', '1', '15', '0', 'admin', null, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES ('264', '1', '16', '0', 'admin', null, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES ('265', '1', '17', '0', 'admin', null, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES ('266', '1', '18', '0', 'admin', null, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES ('267', '1', '30', '0', 'admin', null, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES ('268', '1', '31', '0', 'admin', null, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES ('269', '1', '32', '0', 'admin', null, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES ('270', '1', '33', '0', 'admin', null, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES ('271', '1', '34', '0', 'admin', null, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES ('272', '1', '35', '0', 'admin', null, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES ('273', '1', '36', '0', 'admin', null, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES ('274', '1', '37', '0', 'admin', null, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES ('275', '1', '38', '0', 'admin', null, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES ('276', '1', '39', '0', 'admin', null, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES ('277', '1', '40', '0', 'admin', null, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES ('278', '1', '41', '0', 'admin', null, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES ('279', '1', '42', '0', 'admin', null, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES ('280', '1', '43', '0', 'admin', null, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES ('281', '1', '44', '0', 'admin', null, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES ('282', '1', '45', '0', 'admin', null, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES ('283', '1', '46', '0', 'admin', null, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES ('284', '1', '47', '0', 'admin', null, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES ('285', '1', '48', '0', 'admin', null, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES ('286', '1', '49', '0', 'admin', null, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES ('287', '1', '50', '0', 'admin', null, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES ('288', '1', '0', '0', 'admin', null, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES ('289', '1', '1', '0', 'admin', null, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES ('290', '1', '2', '0', 'admin', null, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES ('291', '1', '3', '0', 'admin', null, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES ('292', '1', '4', '0', 'admin', null, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES ('293', '1', '5', '0', 'admin', null, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES ('294', '1', '6', '0', 'admin', null, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES ('295', '1', '7', '0', 'admin', null, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES ('296', '1', '8', '0', 'admin', null, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES ('297', '1', '9', '0', 'admin', null, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES ('298', '1', '10', '0', 'admin', null, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES ('299', '1', '11', '0', 'admin', null, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES ('300', '1', '12', '0', 'admin', null, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES ('301', '1', '13', '0', 'admin', null, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES ('302', '1', '14', '0', 'admin', null, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES ('303', '1', '15', '0', 'admin', null, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES ('304', '1', '16', '0', 'admin', null, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES ('305', '1', '17', '0', 'admin', null, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES ('306', '1', '18', '0', 'admin', null, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES ('307', '1', '30', '0', 'admin', null, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES ('308', '1', '31', '0', 'admin', null, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES ('309', '1', '32', '0', 'admin', null, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES ('310', '1', '33', '0', 'admin', null, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES ('311', '1', '34', '0', 'admin', null, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES ('312', '1', '35', '0', 'admin', null, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES ('313', '1', '36', '0', 'admin', null, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES ('314', '1', '37', '0', 'admin', null, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES ('315', '1', '38', '0', 'admin', null, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES ('316', '1', '39', '0', 'admin', null, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES ('317', '1', '40', '0', 'admin', null, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES ('318', '1', '41', '0', 'admin', null, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES ('319', '1', '42', '0', 'admin', null, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES ('320', '1', '43', '0', 'admin', null, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES ('321', '1', '44', '0', 'admin', null, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES ('322', '1', '45', '0', 'admin', null, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES ('323', '1', '46', '0', 'admin', null, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES ('324', '1', '47', '0', 'admin', null, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES ('325', '1', '48', '0', 'admin', null, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES ('326', '1', '49', '0', 'admin', null, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES ('327', '1', '50', '0', 'admin', null, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES ('328', '1', '51', '0', 'admin', null, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES ('329', '1', '52', '0', 'admin', null, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES ('330', '1', '53', '0', 'admin', null, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES ('331', '1', '54', '0', 'admin', null, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES ('332', '1', '55', '0', 'admin', null, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES ('333', '1', '0', '0', 'admin', null, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES ('334', '1', '1', '0', 'admin', null, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES ('335', '1', '2', '0', 'admin', null, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES ('336', '1', '3', '0', 'admin', null, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES ('337', '1', '4', '0', 'admin', null, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES ('338', '1', '5', '0', 'admin', null, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES ('339', '1', '6', '0', 'admin', null, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES ('340', '1', '7', '0', 'admin', null, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES ('341', '1', '8', '0', 'admin', null, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES ('342', '1', '9', '0', 'admin', null, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES ('343', '1', '10', '0', 'admin', null, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES ('344', '1', '11', '0', 'admin', null, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES ('345', '1', '12', '0', 'admin', null, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES ('346', '1', '13', '0', 'admin', null, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES ('347', '1', '14', '0', 'admin', null, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES ('348', '1', '15', '0', 'admin', null, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES ('349', '1', '16', '0', 'admin', null, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES ('350', '1', '17', '0', 'admin', null, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES ('351', '1', '18', '0', 'admin', null, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES ('352', '1', '30', '0', 'admin', null, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES ('353', '1', '31', '0', 'admin', null, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES ('354', '1', '32', '0', 'admin', null, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES ('355', '1', '33', '0', 'admin', null, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES ('356', '1', '34', '0', 'admin', null, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES ('357', '1', '35', '0', 'admin', null, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES ('358', '1', '36', '0', 'admin', null, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES ('359', '1', '37', '0', 'admin', null, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES ('360', '1', '38', '0', 'admin', null, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES ('361', '1', '39', '0', 'admin', null, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES ('362', '1', '40', '0', 'admin', null, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES ('363', '1', '41', '0', 'admin', null, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES ('364', '1', '42', '0', 'admin', null, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES ('365', '1', '43', '0', 'admin', null, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES ('366', '1', '44', '0', 'admin', null, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES ('367', '1', '45', '0', 'admin', null, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES ('368', '1', '46', '0', 'admin', null, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES ('369', '1', '47', '0', 'admin', null, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES ('370', '1', '48', '0', 'admin', null, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES ('371', '1', '49', '0', 'admin', null, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES ('372', '1', '50', '0', 'admin', null, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES ('373', '1', '51', '0', 'admin', null, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES ('374', '1', '52', '0', 'admin', null, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES ('375', '1', '53', '0', 'admin', null, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES ('376', '1', '54', '0', 'admin', null, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES ('377', '1', '55', '0', 'admin', null, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES ('378', '1', '56', '0', 'admin', null, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES ('379', '1', '57', '0', 'admin', null, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES ('380', '1', '58', '0', 'admin', null, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES ('381', '1', '59', '0', 'admin', null, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES ('382', '1', '60', '0', 'admin', null, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES ('383', '1', '61', '0', 'admin', null, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES ('384', '1', '0', '0', 'admin', null, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES ('385', '1', '1', '0', 'admin', null, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES ('386', '1', '2', '0', 'admin', null, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES ('387', '1', '3', '0', 'admin', null, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES ('388', '1', '4', '0', 'admin', null, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES ('389', '1', '5', '0', 'admin', null, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES ('390', '1', '6', '0', 'admin', null, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES ('391', '1', '7', '0', 'admin', null, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES ('392', '1', '8', '0', 'admin', null, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES ('393', '1', '9', '0', 'admin', null, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES ('394', '1', '10', '0', 'admin', null, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES ('395', '1', '11', '0', 'admin', null, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES ('396', '1', '12', '0', 'admin', null, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES ('397', '1', '13', '0', 'admin', null, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES ('398', '1', '14', '0', 'admin', null, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES ('399', '1', '15', '0', 'admin', null, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES ('400', '1', '16', '0', 'admin', null, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES ('401', '1', '17', '0', 'admin', null, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES ('402', '1', '18', '0', 'admin', null, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES ('403', '1', '30', '0', 'admin', null, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES ('404', '1', '31', '0', 'admin', null, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES ('405', '1', '32', '0', 'admin', null, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES ('406', '1', '33', '0', 'admin', null, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES ('407', '1', '34', '0', 'admin', null, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES ('408', '1', '35', '0', 'admin', null, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES ('409', '1', '36', '0', 'admin', null, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES ('410', '1', '37', '0', 'admin', null, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES ('411', '1', '38', '0', 'admin', null, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES ('412', '1', '39', '0', 'admin', null, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES ('413', '1', '40', '0', 'admin', null, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES ('414', '1', '41', '0', 'admin', null, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES ('415', '1', '42', '0', 'admin', null, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES ('416', '1', '43', '0', 'admin', null, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES ('417', '1', '44', '0', 'admin', null, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES ('418', '1', '45', '0', 'admin', null, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES ('419', '1', '46', '0', 'admin', null, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES ('420', '1', '47', '0', 'admin', null, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES ('421', '1', '48', '0', 'admin', null, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES ('422', '1', '49', '0', 'admin', null, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES ('423', '1', '50', '0', 'admin', null, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES ('424', '1', '51', '0', 'admin', null, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES ('425', '1', '52', '0', 'admin', null, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES ('426', '1', '53', '0', 'admin', null, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES ('427', '1', '54', '0', 'admin', null, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES ('428', '1', '55', '0', 'admin', null, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES ('429', '1', '56', '0', 'admin', null, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES ('430', '1', '57', '0', 'admin', null, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES ('431', '1', '58', '0', 'admin', null, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES ('432', '1', '59', '0', 'admin', null, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES ('433', '1', '60', '0', 'admin', null, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES ('434', '1', '61', '0', 'admin', null, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES ('435', '1', '62', '0', 'admin', null, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES ('436', '1', '63', '0', 'admin', null, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES ('437', '1', '64', '0', 'admin', null, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES ('438', '1', '65', '0', 'admin', null, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES ('439', '1', '0', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('440', '1', '1', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('441', '1', '2', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('442', '1', '3', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('443', '1', '4', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('444', '1', '5', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('445', '1', '6', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('446', '1', '7', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('447', '1', '8', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('448', '1', '9', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('449', '1', '10', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('450', '1', '11', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('451', '1', '12', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('452', '1', '13', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('453', '1', '14', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('454', '1', '15', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('455', '1', '16', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('456', '1', '17', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('457', '1', '18', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('458', '1', '30', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('459', '1', '31', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('460', '1', '32', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('461', '1', '33', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('462', '1', '34', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('463', '1', '35', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('464', '1', '36', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('465', '1', '37', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('466', '1', '38', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('467', '1', '39', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('468', '1', '40', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('469', '1', '41', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('470', '1', '42', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('471', '1', '43', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('472', '1', '44', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('473', '1', '45', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('474', '1', '46', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('475', '1', '47', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('476', '1', '48', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('477', '1', '49', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('478', '1', '50', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('479', '1', '51', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('480', '1', '52', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('481', '1', '53', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('482', '1', '54', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('483', '1', '55', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('484', '1', '56', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('485', '1', '57', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('486', '1', '58', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('487', '1', '59', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('488', '1', '60', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('489', '1', '61', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('490', '1', '62', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('491', '1', '63', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('492', '1', '64', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('493', '1', '65', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('494', '1', '66', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('495', '1', '67', '0', 'admin', null, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES ('496', '1', '0', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('497', '1', '1', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('498', '1', '2', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('499', '1', '3', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('500', '1', '4', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('501', '1', '5', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('502', '1', '6', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('503', '1', '7', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('504', '1', '8', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('505', '1', '9', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('506', '1', '10', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('507', '1', '11', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('508', '1', '12', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('509', '1', '13', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('510', '1', '14', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('511', '1', '15', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('512', '1', '16', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('513', '1', '17', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('514', '1', '18', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('515', '1', '30', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('516', '1', '31', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('517', '1', '32', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('518', '1', '33', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('519', '1', '34', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('520', '1', '35', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('521', '1', '36', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('522', '1', '37', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('523', '1', '38', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('524', '1', '39', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('525', '1', '40', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('526', '1', '41', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('527', '1', '42', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('528', '1', '43', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('529', '1', '44', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('530', '1', '45', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('531', '1', '46', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('532', '1', '47', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('533', '1', '48', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('534', '1', '49', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('535', '1', '50', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('536', '1', '51', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('537', '1', '52', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('538', '1', '53', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('539', '1', '54', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('540', '1', '55', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('541', '1', '56', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('542', '1', '57', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('543', '1', '58', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('544', '1', '59', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('545', '1', '60', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('546', '1', '61', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('547', '1', '62', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('548', '1', '63', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('549', '1', '64', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('550', '1', '65', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('551', '1', '66', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('552', '1', '67', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('553', '1', '68', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('554', '1', '69', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('555', '1', '70', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('556', '1', '71', '0', 'admin', null, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES ('557', '3', '0', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('558', '3', '1', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('559', '3', '2', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('560', '3', '3', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('561', '3', '4', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('562', '3', '5', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('563', '3', '6', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('564', '3', '7', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('565', '3', '8', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('566', '3', '9', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('567', '3', '10', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('568', '3', '11', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('569', '3', '12', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('570', '3', '13', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('571', '3', '14', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('572', '3', '15', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('573', '3', '16', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('574', '3', '17', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('575', '3', '18', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('576', '3', '30', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('577', '3', '31', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('578', '3', '32', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('579', '3', '33', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('580', '3', '34', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('581', '3', '35', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('582', '3', '36', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('583', '3', '37', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('584', '3', '38', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('585', '3', '39', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('586', '3', '40', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('587', '3', '41', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('588', '3', '42', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('589', '3', '43', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('590', '3', '44', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('591', '3', '45', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('592', '3', '46', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('593', '3', '47', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('594', '3', '48', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('595', '3', '49', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('596', '3', '50', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('597', '3', '51', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('598', '3', '52', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('599', '3', '53', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('600', '3', '54', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('601', '3', '55', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('602', '3', '56', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('603', '3', '57', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('604', '3', '58', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('605', '3', '59', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('606', '3', '60', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('607', '3', '61', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('608', '3', '62', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('609', '3', '63', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('610', '3', '64', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('611', '3', '65', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('612', '3', '66', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('613', '3', '67', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('614', '3', '68', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('615', '3', '69', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('616', '3', '70', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('617', '3', '71', '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES ('618', '1', '0', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('619', '1', '1', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('620', '1', '2', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('621', '1', '3', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('622', '1', '4', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('623', '1', '5', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('624', '1', '6', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('625', '1', '7', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('626', '1', '8', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('627', '1', '9', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('628', '1', '10', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('629', '1', '11', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('630', '1', '12', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('631', '1', '13', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('632', '1', '14', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('633', '1', '15', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('634', '1', '16', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('635', '1', '17', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('636', '1', '18', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('637', '1', '30', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('638', '1', '31', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('639', '1', '32', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('640', '1', '33', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('641', '1', '34', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('642', '1', '35', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('643', '1', '36', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('644', '1', '37', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('645', '1', '38', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('646', '1', '39', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('647', '1', '40', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('648', '1', '41', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('649', '1', '42', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('650', '1', '43', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('651', '1', '44', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('652', '1', '45', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('653', '1', '46', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('654', '1', '47', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('655', '1', '48', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('656', '1', '49', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('657', '1', '50', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('658', '1', '51', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('659', '1', '52', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('660', '1', '53', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('661', '1', '54', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('662', '1', '55', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('663', '1', '56', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('664', '1', '57', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('665', '1', '58', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('666', '1', '59', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('667', '1', '60', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('668', '1', '61', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('669', '1', '62', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('670', '1', '63', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('671', '1', '64', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('672', '1', '65', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('673', '1', '66', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('674', '1', '67', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('675', '1', '68', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('676', '1', '69', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('677', '1', '70', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('678', '1', '71', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('679', '1', '72', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('680', '1', '73', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('681', '1', '74', '0', 'admin', null, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES ('682', '1', '0', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('683', '1', '1', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('684', '1', '2', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('685', '1', '3', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('686', '1', '4', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('687', '1', '5', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('688', '1', '6', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('689', '1', '7', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('690', '1', '8', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('691', '1', '9', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('692', '1', '10', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('693', '1', '11', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('694', '1', '12', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('695', '1', '13', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('696', '1', '14', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('697', '1', '15', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('698', '1', '16', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('699', '1', '17', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('700', '1', '18', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('701', '1', '30', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('702', '1', '31', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('703', '1', '32', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('704', '1', '33', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('705', '1', '34', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('706', '1', '35', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('707', '1', '36', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('708', '1', '37', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('709', '1', '38', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('710', '1', '39', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('711', '1', '40', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('712', '1', '41', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('713', '1', '42', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('714', '1', '43', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('715', '1', '44', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('716', '1', '45', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('717', '1', '46', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('718', '1', '47', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('719', '1', '48', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('720', '1', '49', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('721', '1', '50', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('722', '1', '51', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('723', '1', '52', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('724', '1', '53', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('725', '1', '54', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('726', '1', '55', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('727', '1', '56', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('728', '1', '57', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('729', '1', '58', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('730', '1', '59', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('731', '1', '60', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('732', '1', '61', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('733', '1', '62', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('734', '1', '63', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('735', '1', '64', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('736', '1', '65', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('737', '1', '66', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('738', '1', '67', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('739', '1', '68', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('740', '1', '69', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('741', '1', '70', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('742', '1', '71', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('743', '1', '72', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('744', '1', '73', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('745', '1', '74', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('746', '1', '75', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('747', '1', '76', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('748', '1', '77', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('749', '1', '78', '0', 'admin', null, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES ('750', '1', '0', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('751', '1', '1', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('752', '1', '2', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('753', '1', '3', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('754', '1', '4', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('755', '1', '5', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('756', '1', '6', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('757', '1', '7', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('758', '1', '8', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('759', '1', '9', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('760', '1', '10', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('761', '1', '11', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('762', '1', '12', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('763', '1', '13', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('764', '1', '14', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('765', '1', '15', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('766', '1', '16', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('767', '1', '17', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('768', '1', '18', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('769', '1', '30', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('770', '1', '31', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('771', '1', '32', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('772', '1', '33', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('773', '1', '34', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('774', '1', '35', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('775', '1', '36', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('776', '1', '37', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('777', '1', '38', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('778', '1', '39', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('779', '1', '40', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('780', '1', '41', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('781', '1', '42', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('782', '1', '43', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('783', '1', '44', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('784', '1', '45', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('785', '1', '46', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('786', '1', '47', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('787', '1', '48', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('788', '1', '49', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('789', '1', '50', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('790', '1', '51', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('791', '1', '52', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('792', '1', '53', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('793', '1', '54', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('794', '1', '55', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('795', '1', '56', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('796', '1', '57', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('797', '1', '58', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('798', '1', '59', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('799', '1', '60', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('800', '1', '61', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('801', '1', '62', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('802', '1', '63', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('803', '1', '64', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('804', '1', '65', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('805', '1', '66', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('806', '1', '67', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('807', '1', '68', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('808', '1', '69', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('809', '1', '70', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('810', '1', '71', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('811', '1', '72', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('812', '1', '73', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('813', '1', '74', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('814', '1', '75', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('815', '1', '76', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('816', '1', '77', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('817', '1', '78', '0', 'admin', null, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES ('818', '1', '0', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('819', '1', '1', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('820', '1', '2', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('821', '1', '3', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('822', '1', '4', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('823', '1', '5', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('824', '1', '6', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('825', '1', '7', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('826', '1', '8', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('827', '1', '9', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('828', '1', '10', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('829', '1', '11', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('830', '1', '12', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('831', '1', '13', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('832', '1', '14', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('833', '1', '15', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('834', '1', '16', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('835', '1', '17', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('836', '1', '18', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('837', '1', '30', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('838', '1', '31', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('839', '1', '32', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('840', '1', '33', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('841', '1', '34', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('842', '1', '35', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('843', '1', '36', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('844', '1', '37', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('845', '1', '38', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('846', '1', '39', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('847', '1', '40', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('848', '1', '41', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('849', '1', '42', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('850', '1', '43', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('851', '1', '44', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('852', '1', '45', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('853', '1', '50', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('854', '1', '51', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('855', '1', '52', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('856', '1', '53', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('857', '1', '54', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('858', '1', '55', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('859', '1', '56', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('860', '1', '57', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('861', '1', '58', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('862', '1', '59', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('863', '1', '60', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('864', '1', '61', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('865', '1', '62', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('866', '1', '63', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('867', '1', '64', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('868', '1', '65', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('869', '1', '66', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('870', '1', '67', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('871', '1', '68', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('872', '1', '69', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('873', '1', '70', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('874', '1', '71', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('875', '1', '72', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('876', '1', '73', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('877', '1', '74', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('878', '1', '75', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('879', '1', '76', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('880', '1', '77', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('881', '1', '78', '0', 'admin', null, '2020-08-21 08:58:37', '2020-08-24 03:34:53');
INSERT INTO `st_role_authority` VALUES ('882', '1', '0', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('883', '1', '1', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('884', '1', '2', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('885', '1', '3', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('886', '1', '4', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('887', '1', '5', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('888', '1', '6', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('889', '1', '7', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('890', '1', '8', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('891', '1', '9', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('892', '1', '10', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('893', '1', '11', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('894', '1', '12', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('895', '1', '13', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('896', '1', '14', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('897', '1', '15', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('898', '1', '16', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('899', '1', '17', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('900', '1', '18', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('901', '1', '30', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('902', '1', '31', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('903', '1', '32', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('904', '1', '33', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('905', '1', '34', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('906', '1', '35', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('907', '1', '36', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('908', '1', '37', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('909', '1', '38', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('910', '1', '39', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('911', '1', '40', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('912', '1', '41', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('913', '1', '42', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('914', '1', '43', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('915', '1', '44', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('916', '1', '45', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('917', '1', '50', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('918', '1', '51', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('919', '1', '52', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('920', '1', '53', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('921', '1', '54', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('922', '1', '55', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('923', '1', '56', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('924', '1', '57', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('925', '1', '58', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('926', '1', '59', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('927', '1', '60', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('928', '1', '61', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('929', '1', '62', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('930', '1', '63', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('931', '1', '64', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('932', '1', '65', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('933', '1', '66', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('934', '1', '67', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('935', '1', '68', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('936', '1', '69', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('937', '1', '70', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('938', '1', '71', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('939', '1', '72', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('940', '1', '73', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('941', '1', '74', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('942', '1', '75', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('943', '1', '76', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('944', '1', '77', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('945', '1', '78', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('946', '1', '81', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('947', '1', '82', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('948', '1', '83', '0', 'admin', null, '2020-08-24 03:34:53', '2020-08-24 06:43:30');
INSERT INTO `st_role_authority` VALUES ('949', '1', '0', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('950', '1', '1', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('951', '1', '2', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('952', '1', '3', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('953', '1', '4', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('954', '1', '5', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('955', '1', '6', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('956', '1', '7', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('957', '1', '8', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('958', '1', '9', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('959', '1', '10', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('960', '1', '11', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('961', '1', '12', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('962', '1', '13', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('963', '1', '14', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('964', '1', '15', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('965', '1', '16', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('966', '1', '17', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('967', '1', '18', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('968', '1', '30', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('969', '1', '31', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('970', '1', '32', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('971', '1', '33', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('972', '1', '34', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('973', '1', '35', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('974', '1', '36', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('975', '1', '37', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('976', '1', '38', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('977', '1', '39', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('978', '1', '40', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('979', '1', '41', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('980', '1', '42', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('981', '1', '43', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('982', '1', '44', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('983', '1', '45', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('984', '1', '50', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('985', '1', '51', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('986', '1', '52', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('987', '1', '53', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('988', '1', '54', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('989', '1', '55', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('990', '1', '56', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('991', '1', '57', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('992', '1', '58', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('993', '1', '59', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('994', '1', '60', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('995', '1', '61', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('996', '1', '62', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('997', '1', '63', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('998', '1', '64', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('999', '1', '65', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1000', '1', '66', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1001', '1', '67', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1002', '1', '68', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1003', '1', '69', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1004', '1', '70', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1005', '1', '71', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1006', '1', '72', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1007', '1', '73', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1008', '1', '74', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1009', '1', '75', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1010', '1', '76', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1011', '1', '77', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1012', '1', '78', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1013', '1', '81', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1014', '1', '82', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1015', '1', '83', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1016', '1', '84', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1017', '1', '90', '0', 'admin', null, '2020-08-24 06:43:30', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1018', '1', '0', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1019', '1', '1', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1020', '1', '2', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1021', '1', '3', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1022', '1', '4', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1023', '1', '5', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1024', '1', '6', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1025', '1', '7', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1026', '1', '8', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1027', '1', '9', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1028', '1', '10', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1029', '1', '11', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1030', '1', '12', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1031', '1', '13', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1032', '1', '14', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1033', '1', '15', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1034', '1', '16', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1035', '1', '17', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1036', '1', '18', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1037', '1', '30', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1038', '1', '31', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1039', '1', '32', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1040', '1', '33', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1041', '1', '34', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1042', '1', '35', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1043', '1', '36', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1044', '1', '37', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1045', '1', '38', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1046', '1', '39', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1047', '1', '40', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1048', '1', '41', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1049', '1', '42', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1050', '1', '43', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1051', '1', '44', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1052', '1', '45', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1053', '1', '50', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1054', '1', '51', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1055', '1', '52', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1056', '1', '53', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1057', '1', '54', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1058', '1', '55', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1059', '1', '56', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1060', '1', '57', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1061', '1', '58', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1062', '1', '59', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1063', '1', '60', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1064', '1', '61', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1065', '1', '62', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1066', '1', '63', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1067', '1', '64', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1068', '1', '65', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1069', '1', '66', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1070', '1', '67', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1071', '1', '68', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1072', '1', '69', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1073', '1', '70', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1074', '1', '71', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1075', '1', '72', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1076', '1', '73', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1077', '1', '74', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1078', '1', '75', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1079', '1', '76', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1080', '1', '77', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1081', '1', '78', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1082', '1', '81', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1083', '1', '82', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1084', '1', '83', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1085', '1', '84', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1086', '1', '90', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1087', '1', '91', '0', 'admin', null, '2020-09-11 03:36:43', '2020-10-24 10:26:26');
INSERT INTO `st_role_authority` VALUES ('1088', '5', '0', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1089', '5', '1', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1090', '5', '2', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1091', '5', '3', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1092', '5', '4', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1093', '5', '5', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1094', '5', '6', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1095', '5', '7', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1096', '5', '8', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1097', '5', '9', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1098', '5', '10', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1099', '5', '11', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1100', '5', '12', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1101', '5', '13', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1102', '5', '14', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1103', '5', '15', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1104', '5', '16', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1105', '5', '17', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1106', '5', '18', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1107', '5', '30', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1108', '5', '31', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1109', '5', '32', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1110', '5', '33', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1111', '5', '34', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1112', '5', '35', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1113', '5', '36', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1114', '5', '37', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1115', '5', '38', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1116', '5', '39', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1117', '5', '40', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1118', '5', '41', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1119', '5', '42', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1120', '5', '43', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1121', '5', '44', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1122', '5', '45', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1123', '5', '50', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1124', '5', '51', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1125', '5', '52', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1126', '5', '53', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1127', '5', '54', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1128', '5', '55', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1129', '5', '56', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1130', '5', '57', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1131', '5', '58', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1132', '5', '59', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1133', '5', '60', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1134', '5', '61', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1135', '5', '62', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1136', '5', '63', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1137', '5', '64', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1138', '5', '65', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1139', '5', '66', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1140', '5', '67', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1141', '5', '68', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1142', '5', '69', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1143', '5', '70', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1144', '5', '71', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1145', '5', '72', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1146', '5', '73', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1147', '5', '74', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1148', '5', '75', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1149', '5', '76', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1150', '5', '77', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1151', '5', '78', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1152', '5', '81', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1153', '5', '82', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1154', '5', '83', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1155', '5', '84', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1156', '5', '90', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1157', '5', '91', '1', 'admin', 'admin', '2020-10-20 01:55:35', '2020-10-20 01:55:35');
INSERT INTO `st_role_authority` VALUES ('1158', '6', '0', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1159', '6', '1', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1160', '6', '2', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1161', '6', '3', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1162', '6', '4', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1163', '6', '5', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1164', '6', '6', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1165', '6', '7', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1166', '6', '8', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1167', '6', '9', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1168', '6', '10', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1169', '6', '11', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1170', '6', '12', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1171', '6', '13', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1172', '6', '14', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1173', '6', '15', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1174', '6', '16', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1175', '6', '17', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1176', '6', '18', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1177', '6', '30', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1178', '6', '31', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1179', '6', '32', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1180', '6', '33', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1181', '6', '34', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1182', '6', '35', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1183', '6', '36', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1184', '6', '37', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1185', '6', '38', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1186', '6', '39', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1187', '6', '40', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1188', '6', '41', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1189', '6', '42', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1190', '6', '43', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1191', '6', '44', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1192', '6', '45', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1193', '6', '50', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1194', '6', '51', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1195', '6', '52', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1196', '6', '53', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1197', '6', '54', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1198', '6', '55', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1199', '6', '56', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1200', '6', '57', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1201', '6', '58', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1202', '6', '59', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1203', '6', '60', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1204', '6', '61', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1205', '6', '62', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1206', '6', '63', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1207', '6', '64', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1208', '6', '65', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1209', '6', '66', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1210', '6', '67', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1211', '6', '68', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1212', '6', '69', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1213', '6', '70', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1214', '6', '71', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1215', '6', '72', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1216', '6', '73', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1217', '6', '74', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1218', '6', '75', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1219', '6', '76', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1220', '6', '77', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1221', '6', '78', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1222', '6', '81', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1223', '6', '82', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1224', '6', '83', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1225', '6', '84', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1226', '6', '90', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1227', '6', '91', '0', 'admin', null, '2020-10-20 02:53:00', '2020-10-23 09:16:25');
INSERT INTO `st_role_authority` VALUES ('1228', '6', '0', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1229', '6', '1', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1230', '6', '2', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1231', '6', '3', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1232', '6', '4', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1233', '6', '5', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1234', '6', '6', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1235', '6', '7', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1236', '6', '8', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1237', '6', '9', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1238', '6', '10', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1239', '6', '11', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1240', '6', '12', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1241', '6', '13', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1242', '6', '14', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1243', '6', '15', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1244', '6', '16', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1245', '6', '17', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1246', '6', '18', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1247', '6', '30', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1248', '6', '31', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1249', '6', '32', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1250', '6', '33', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1251', '6', '34', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1252', '6', '35', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1253', '6', '36', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1254', '6', '37', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1255', '6', '38', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1256', '6', '39', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1257', '6', '40', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1258', '6', '41', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1259', '6', '42', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1260', '6', '43', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1261', '6', '44', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1262', '6', '45', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1263', '6', '50', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1264', '6', '51', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1265', '6', '52', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1266', '6', '53', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1267', '6', '54', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1268', '6', '55', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1269', '6', '56', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1270', '6', '57', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1271', '6', '58', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1272', '6', '59', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1273', '6', '60', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1274', '6', '61', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1275', '6', '62', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1276', '6', '63', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1277', '6', '64', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1278', '6', '65', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1279', '6', '66', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1280', '6', '67', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1281', '6', '68', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1282', '6', '69', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1283', '6', '70', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1284', '6', '71', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1285', '6', '72', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1286', '6', '73', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1287', '6', '74', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1288', '6', '75', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1289', '6', '76', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1290', '6', '77', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1291', '6', '78', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1292', '6', '81', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1293', '6', '82', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1294', '6', '83', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1295', '6', '84', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1296', '6', '90', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1297', '6', '91', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1298', '6', '92', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1299', '6', '94', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1300', '6', '96', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1301', '6', '97', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1302', '6', '98', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1303', '6', '103', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1304', '6', '104', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1305', '6', '105', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1306', '6', '106', '0', 'admin', null, '2020-10-23 09:16:25', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1307', '6', '0', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1308', '6', '1', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1309', '6', '2', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1310', '6', '3', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1311', '6', '4', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1312', '6', '5', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1313', '6', '6', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1314', '6', '7', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1315', '6', '8', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1316', '6', '9', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1317', '6', '10', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1318', '6', '11', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1319', '6', '12', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1320', '6', '13', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1321', '6', '14', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1322', '6', '15', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1323', '6', '16', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1324', '6', '17', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1325', '6', '18', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1326', '6', '30', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1327', '6', '31', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1328', '6', '32', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1329', '6', '33', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1330', '6', '34', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1331', '6', '35', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1332', '6', '36', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1333', '6', '37', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1334', '6', '38', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1335', '6', '39', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1336', '6', '40', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1337', '6', '41', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1338', '6', '42', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1339', '6', '43', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1340', '6', '44', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1341', '6', '45', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1342', '6', '50', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1343', '6', '51', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1344', '6', '52', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1345', '6', '53', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1346', '6', '54', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1347', '6', '55', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1348', '6', '56', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1349', '6', '57', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1350', '6', '58', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1351', '6', '59', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1352', '6', '60', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1353', '6', '61', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1354', '6', '62', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1355', '6', '63', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1356', '6', '64', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1357', '6', '65', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1358', '6', '66', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1359', '6', '67', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1360', '6', '68', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1361', '6', '69', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1362', '6', '70', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1363', '6', '71', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1364', '6', '72', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1365', '6', '73', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1366', '6', '74', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1367', '6', '75', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1368', '6', '76', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1369', '6', '77', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1370', '6', '78', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1371', '6', '81', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1372', '6', '82', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1373', '6', '83', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1374', '6', '84', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1375', '6', '90', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1376', '6', '91', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1377', '6', '107', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1378', '6', '108', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1379', '6', '109', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1380', '6', '110', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1381', '6', '111', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1382', '6', '112', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1383', '6', '113', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1384', '6', '114', '1', 'admin', 'admin', '2020-10-23 14:42:41', '2020-10-23 14:42:41');
INSERT INTO `st_role_authority` VALUES ('1385', '1', '0', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1386', '1', '1', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1387', '1', '2', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1388', '1', '3', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1389', '1', '4', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1390', '1', '5', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1391', '1', '6', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1392', '1', '7', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1393', '1', '8', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1394', '1', '9', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1395', '1', '10', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1396', '1', '11', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1397', '1', '12', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1398', '1', '13', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1399', '1', '14', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1400', '1', '15', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1401', '1', '16', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1402', '1', '17', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1403', '1', '18', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1404', '1', '30', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1405', '1', '31', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1406', '1', '32', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1407', '1', '33', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1408', '1', '34', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1409', '1', '35', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1410', '1', '36', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1411', '1', '37', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1412', '1', '38', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1413', '1', '39', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1414', '1', '40', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1415', '1', '41', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1416', '1', '42', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1417', '1', '43', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1418', '1', '44', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1419', '1', '45', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1420', '1', '50', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1421', '1', '51', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1422', '1', '52', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1423', '1', '53', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1424', '1', '54', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1425', '1', '55', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1426', '1', '56', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1427', '1', '57', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1428', '1', '58', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1429', '1', '59', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1430', '1', '60', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1431', '1', '61', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1432', '1', '62', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1433', '1', '63', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1434', '1', '64', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1435', '1', '65', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1436', '1', '66', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1437', '1', '67', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1438', '1', '68', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1439', '1', '69', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1440', '1', '70', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1441', '1', '71', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1442', '1', '72', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1443', '1', '73', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1444', '1', '74', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1445', '1', '75', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1446', '1', '76', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1447', '1', '77', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1448', '1', '78', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1449', '1', '81', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1450', '1', '82', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1451', '1', '83', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1452', '1', '84', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1453', '1', '90', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1454', '1', '91', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1455', '1', '107', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1456', '1', '108', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1457', '1', '109', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1458', '1', '110', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1459', '1', '111', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1460', '1', '112', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1461', '1', '113', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1462', '1', '114', '0', 'admin', null, '2020-10-24 10:26:26', '2020-10-26 15:44:07');
INSERT INTO `st_role_authority` VALUES ('1463', '1', '0', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1464', '1', '1', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1465', '1', '2', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1466', '1', '3', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1467', '1', '4', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1468', '1', '5', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1469', '1', '6', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1470', '1', '7', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1471', '1', '8', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1472', '1', '9', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1473', '1', '10', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1474', '1', '11', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1475', '1', '12', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1476', '1', '13', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1477', '1', '14', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1478', '1', '15', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1479', '1', '16', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1480', '1', '17', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1481', '1', '18', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1482', '1', '30', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1483', '1', '31', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1484', '1', '32', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1485', '1', '33', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1486', '1', '34', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1487', '1', '35', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1488', '1', '36', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1489', '1', '37', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1490', '1', '38', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1491', '1', '39', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1492', '1', '40', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1493', '1', '41', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1494', '1', '42', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1495', '1', '43', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1496', '1', '44', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1497', '1', '45', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1498', '1', '50', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1499', '1', '51', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1500', '1', '52', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1501', '1', '53', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1502', '1', '54', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1503', '1', '55', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1504', '1', '56', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1505', '1', '57', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1506', '1', '58', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1507', '1', '59', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1508', '1', '60', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1509', '1', '61', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1510', '1', '62', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1511', '1', '63', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1512', '1', '64', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1513', '1', '65', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1514', '1', '66', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1515', '1', '67', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1516', '1', '68', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1517', '1', '69', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1518', '1', '70', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1519', '1', '71', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1520', '1', '72', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1521', '1', '73', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1522', '1', '74', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1523', '1', '75', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1524', '1', '76', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1525', '1', '77', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1526', '1', '78', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1527', '1', '81', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1528', '1', '82', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1529', '1', '83', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1530', '1', '84', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1531', '1', '90', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1532', '1', '91', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1533', '1', '107', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1534', '1', '108', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1535', '1', '109', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1536', '1', '110', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1537', '1', '111', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1538', '1', '112', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1539', '1', '113', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1540', '1', '114', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1541', '1', '115', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1542', '1', '117', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1543', '1', '118', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1544', '1', '119', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1545', '1', '120', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1546', '1', '121', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1547', '1', '122', '0', 'admin', null, '2020-10-26 15:44:07', '2020-10-27 02:10:55');
INSERT INTO `st_role_authority` VALUES ('1548', '1', '0', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1549', '1', '1', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1550', '1', '2', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1551', '1', '3', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1552', '1', '4', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1553', '1', '5', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1554', '1', '6', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1555', '1', '7', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1556', '1', '8', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1557', '1', '9', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1558', '1', '10', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1559', '1', '11', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1560', '1', '12', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1561', '1', '13', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1562', '1', '14', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1563', '1', '15', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1564', '1', '16', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1565', '1', '17', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1566', '1', '18', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1567', '1', '30', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1568', '1', '31', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1569', '1', '32', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1570', '1', '33', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1571', '1', '34', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1572', '1', '35', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1573', '1', '36', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1574', '1', '37', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1575', '1', '38', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1576', '1', '39', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1577', '1', '40', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1578', '1', '41', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1579', '1', '42', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1580', '1', '43', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1581', '1', '44', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1582', '1', '45', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1583', '1', '50', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1584', '1', '51', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1585', '1', '52', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1586', '1', '53', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1587', '1', '54', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1588', '1', '55', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1589', '1', '56', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1590', '1', '57', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1591', '1', '58', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1592', '1', '59', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1593', '1', '60', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1594', '1', '61', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1595', '1', '62', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1596', '1', '63', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1597', '1', '64', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1598', '1', '65', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1599', '1', '66', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1600', '1', '67', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1601', '1', '68', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1602', '1', '69', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1603', '1', '70', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1604', '1', '71', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1605', '1', '91', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1606', '1', '107', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1607', '1', '108', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1608', '1', '109', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1609', '1', '110', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1610', '1', '111', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1611', '1', '112', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1612', '1', '113', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1613', '1', '114', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1614', '1', '115', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1615', '1', '117', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1616', '1', '118', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1617', '1', '119', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1618', '1', '120', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1619', '1', '121', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1620', '1', '122', '0', 'admin', null, '2020-10-27 02:10:55', '2020-10-27 04:17:48');
INSERT INTO `st_role_authority` VALUES ('1621', '1', '0', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1622', '1', '1', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1623', '1', '2', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1624', '1', '3', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1625', '1', '4', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1626', '1', '5', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1627', '1', '6', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1628', '1', '7', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1629', '1', '8', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1630', '1', '9', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1631', '1', '10', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1632', '1', '11', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1633', '1', '12', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1634', '1', '13', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1635', '1', '14', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1636', '1', '15', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1637', '1', '16', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1638', '1', '17', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1639', '1', '18', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1640', '1', '30', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1641', '1', '31', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1642', '1', '32', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1643', '1', '38', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1644', '1', '39', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1645', '1', '40', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1646', '1', '41', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1647', '1', '42', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1648', '1', '43', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1649', '1', '44', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1650', '1', '45', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1651', '1', '50', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1652', '1', '51', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1653', '1', '52', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1654', '1', '53', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1655', '1', '54', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1656', '1', '55', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1657', '1', '56', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1658', '1', '57', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1659', '1', '58', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1660', '1', '59', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1661', '1', '60', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1662', '1', '61', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1663', '1', '62', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1664', '1', '63', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1665', '1', '64', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1666', '1', '65', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1667', '1', '66', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1668', '1', '67', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1669', '1', '68', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1670', '1', '69', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1671', '1', '70', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1672', '1', '71', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1673', '1', '91', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1674', '1', '107', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1675', '1', '108', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1676', '1', '109', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1677', '1', '110', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1678', '1', '111', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1679', '1', '112', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1680', '1', '113', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1681', '1', '114', '0', 'admin', null, '2020-10-27 04:17:48', '2020-10-27 06:45:15');
INSERT INTO `st_role_authority` VALUES ('1682', '1', '0', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1683', '1', '2', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1684', '1', '3', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1685', '1', '4', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1686', '1', '5', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1687', '1', '6', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1688', '1', '7', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1689', '1', '8', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1690', '1', '9', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1691', '1', '10', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1692', '1', '11', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1693', '1', '12', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1694', '1', '13', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1695', '1', '14', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1696', '1', '15', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1697', '1', '16', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1698', '1', '17', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1699', '1', '18', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1700', '1', '30', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1701', '1', '31', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1702', '1', '32', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1703', '1', '38', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1704', '1', '39', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1705', '1', '40', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1706', '1', '41', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1707', '1', '42', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1708', '1', '43', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1709', '1', '44', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1710', '1', '45', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1711', '1', '50', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1712', '1', '51', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1713', '1', '52', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1714', '1', '53', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1715', '1', '54', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1716', '1', '55', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1717', '1', '56', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1718', '1', '57', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1719', '1', '58', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1720', '1', '59', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1721', '1', '60', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1722', '1', '61', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1723', '1', '62', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1724', '1', '63', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1725', '1', '64', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1726', '1', '65', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1727', '1', '66', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1728', '1', '67', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1729', '1', '68', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1730', '1', '69', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1731', '1', '70', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1732', '1', '71', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1733', '1', '91', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1734', '1', '107', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1735', '1', '108', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1736', '1', '109', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1737', '1', '110', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1738', '1', '111', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1739', '1', '112', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1740', '1', '113', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1741', '1', '114', '0', 'admin', null, '2020-10-27 06:45:15', '2020-10-27 06:45:24');
INSERT INTO `st_role_authority` VALUES ('1742', '1', '0', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1743', '1', '1', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1744', '1', '2', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1745', '1', '3', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1746', '1', '4', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1747', '1', '5', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1748', '1', '6', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1749', '1', '7', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1750', '1', '8', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1751', '1', '9', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1752', '1', '10', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1753', '1', '11', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1754', '1', '12', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1755', '1', '13', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1756', '1', '14', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1757', '1', '15', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1758', '1', '16', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1759', '1', '17', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1760', '1', '18', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1761', '1', '30', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1762', '1', '31', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1763', '1', '32', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1764', '1', '38', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1765', '1', '39', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1766', '1', '40', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1767', '1', '41', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1768', '1', '42', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1769', '1', '43', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1770', '1', '44', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1771', '1', '45', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1772', '1', '50', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1773', '1', '51', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1774', '1', '52', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1775', '1', '53', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1776', '1', '54', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1777', '1', '55', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1778', '1', '56', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1779', '1', '57', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1780', '1', '58', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1781', '1', '59', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1782', '1', '60', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1783', '1', '61', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1784', '1', '62', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1785', '1', '63', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1786', '1', '64', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1787', '1', '65', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1788', '1', '66', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1789', '1', '67', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1790', '1', '68', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1791', '1', '69', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1792', '1', '70', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1793', '1', '71', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1794', '1', '91', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1795', '1', '107', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1796', '1', '108', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1797', '1', '109', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1798', '1', '110', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1799', '1', '111', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1800', '1', '112', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1801', '1', '113', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1802', '1', '114', '0', 'admin', null, '2020-10-27 06:45:24', '2020-10-27 06:58:28');
INSERT INTO `st_role_authority` VALUES ('1803', '1', '0', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1804', '1', '1', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1805', '1', '2', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1806', '1', '3', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1807', '1', '4', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1808', '1', '5', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1809', '1', '6', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1810', '1', '7', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1811', '1', '8', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1812', '1', '9', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1813', '1', '10', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1814', '1', '11', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1815', '1', '12', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1816', '1', '13', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1817', '1', '14', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1818', '1', '15', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1819', '1', '16', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1820', '1', '17', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1821', '1', '18', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1822', '1', '30', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1823', '1', '31', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1824', '1', '32', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1825', '1', '38', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1826', '1', '39', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1827', '1', '40', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1828', '1', '41', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1829', '1', '42', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1830', '1', '43', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1831', '1', '44', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1832', '1', '45', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1833', '1', '50', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1834', '1', '51', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1835', '1', '52', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1836', '1', '53', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1837', '1', '54', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1838', '1', '55', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1839', '1', '56', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1840', '1', '57', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1841', '1', '58', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1842', '1', '59', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1843', '1', '60', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1844', '1', '61', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1845', '1', '62', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1846', '1', '63', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1847', '1', '64', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1848', '1', '65', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1849', '1', '66', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1850', '1', '67', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1851', '1', '68', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1852', '1', '69', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1853', '1', '70', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1854', '1', '71', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1855', '1', '91', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1856', '1', '107', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1857', '1', '108', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1858', '1', '109', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1859', '1', '110', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1860', '1', '111', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1861', '1', '112', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1862', '1', '113', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1863', '1', '114', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1864', '1', '123', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1865', '1', '124', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1866', '1', '125', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1867', '1', '126', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1868', '1', '127', '0', 'admin', null, '2020-10-27 06:58:28', '2020-10-27 07:46:52');
INSERT INTO `st_role_authority` VALUES ('1869', '1', '0', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1870', '1', '1', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1871', '1', '2', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1872', '1', '3', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1873', '1', '4', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1874', '1', '5', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1875', '1', '6', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1876', '1', '7', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1877', '1', '8', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1878', '1', '9', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1879', '1', '10', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1880', '1', '11', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1881', '1', '12', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1882', '1', '13', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1883', '1', '14', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1884', '1', '15', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1885', '1', '16', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1886', '1', '17', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1887', '1', '18', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1888', '1', '30', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1889', '1', '31', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1890', '1', '32', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1891', '1', '38', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1892', '1', '39', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1893', '1', '40', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1894', '1', '41', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1895', '1', '42', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1896', '1', '43', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1897', '1', '44', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1898', '1', '45', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1899', '1', '50', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1900', '1', '51', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1901', '1', '52', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1902', '1', '53', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1903', '1', '54', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1904', '1', '55', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1905', '1', '56', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1906', '1', '57', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1907', '1', '58', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1908', '1', '59', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1909', '1', '60', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1910', '1', '61', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1911', '1', '62', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1912', '1', '63', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1913', '1', '64', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1914', '1', '65', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1915', '1', '66', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1916', '1', '67', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1917', '1', '68', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1918', '1', '69', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1919', '1', '70', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1920', '1', '71', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1921', '1', '91', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1922', '1', '107', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1923', '1', '108', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1924', '1', '109', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1925', '1', '110', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1926', '1', '111', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1927', '1', '112', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1928', '1', '113', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1929', '1', '114', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1930', '1', '123', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1931', '1', '124', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1932', '1', '125', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1933', '1', '126', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1934', '1', '127', '0', 'admin', null, '2020-10-27 07:46:52', '2020-10-27 07:53:47');
INSERT INTO `st_role_authority` VALUES ('1935', '1', '0', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1936', '1', '1', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1937', '1', '2', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1938', '1', '3', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1939', '1', '4', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1940', '1', '5', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1941', '1', '6', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1942', '1', '7', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1943', '1', '8', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1944', '1', '9', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1945', '1', '10', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1946', '1', '11', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1947', '1', '12', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1948', '1', '13', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1949', '1', '14', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1950', '1', '15', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1951', '1', '16', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1952', '1', '17', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1953', '1', '18', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1954', '1', '30', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1955', '1', '31', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1956', '1', '32', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1957', '1', '38', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1958', '1', '39', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1959', '1', '40', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1960', '1', '41', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1961', '1', '42', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1962', '1', '43', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1963', '1', '44', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1964', '1', '45', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1965', '1', '50', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1966', '1', '51', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1967', '1', '52', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1968', '1', '53', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1969', '1', '54', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1970', '1', '55', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1971', '1', '56', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1972', '1', '57', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1973', '1', '58', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1974', '1', '59', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1975', '1', '60', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1976', '1', '61', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1977', '1', '62', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1978', '1', '63', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1979', '1', '64', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1980', '1', '65', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1981', '1', '66', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1982', '1', '67', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1983', '1', '68', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1984', '1', '69', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1985', '1', '70', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1986', '1', '71', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1987', '1', '91', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1988', '1', '107', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1989', '1', '108', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1990', '1', '109', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1991', '1', '110', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1992', '1', '111', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1993', '1', '112', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1994', '1', '113', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1995', '1', '114', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1996', '1', '123', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1997', '1', '124', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1998', '1', '125', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('1999', '1', '126', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('2000', '1', '127', '0', 'admin', null, '2020-10-27 07:53:47', '2020-10-27 07:54:37');
INSERT INTO `st_role_authority` VALUES ('2001', '1', '0', '0', 'admin', null, '2020-10-27 07:54:37', '2020-10-27 08:26:52');
INSERT INTO `st_role_authority` VALUES ('2002', '1', '128', '0', 'admin', null, '2020-10-27 07:54:37', '2020-10-27 08:26:52');
INSERT INTO `st_role_authority` VALUES ('2003', '1', '1', '0', 'admin', null, '2020-10-27 07:54:37', '2020-10-27 08:26:52');
INSERT INTO `st_role_authority` VALUES ('2004', '1', '129', '0', 'admin', null, '2020-10-27 07:54:37', '2020-10-27 08:26:52');
INSERT INTO `st_role_authority` VALUES ('2005', '1', '2', '0', 'admin', null, '2020-10-27 07:54:37', '2020-10-27 08:26:52');
INSERT INTO `st_role_authority` VALUES ('2006', '1', '130', '0', 'admin', null, '2020-10-27 07:54:37', '2020-10-27 08:26:52');
INSERT INTO `st_role_authority` VALUES ('2007', '1', '3', '0', 'admin', null, '2020-10-27 07:54:37', '2020-10-27 08:26:52');
INSERT INTO `st_role_authority` VALUES ('2008', '1', '131', '0', 'admin', null, '2020-10-27 07:54:37', '2020-10-27 08:26:52');
INSERT INTO `st_role_authority` VALUES ('2009', '1', '4', '0', 'admin', null, '2020-10-27 07:54:37', '2020-10-27 08:26:52');
INSERT INTO `st_role_authority` VALUES ('2010', '1', '5', '0', 'admin', null, '2020-10-27 07:54:37', '2020-10-27 08:26:52');
INSERT INTO `st_role_authority` VALUES ('2011', '1', '6', '0', 'admin', null, '2020-10-27 07:54:37', '2020-10-27 08:26:52');
INSERT INTO `st_role_authority` VALUES ('2012', '1', '7', '0', 'admin', null, '2020-10-27 07:54:37', '2020-10-27 08:26:52');
INSERT INTO `st_role_authority` VALUES ('2013', '1', '8', '0', 'admin', null, '2020-10-27 07:54:37', '2020-10-27 08:26:52');
INSERT INTO `st_role_authority` VALUES ('2014', '1', '9', '0', 'admin', null, '2020-10-27 07:54:37', '2020-10-27 08:26:52');
INSERT INTO `st_role_authority` VALUES ('2015', '1', '10', '0', 'admin', null, '2020-10-27 07:54:37', '2020-10-27 08:26:52');
INSERT INTO `st_role_authority` VALUES ('2016', '1', '11', '0', 'admin', null, '2020-10-27 07:54:37', '2020-10-27 08:26:52');
INSERT INTO `st_role_authority` VALUES ('2017', '1', '12', '0', 'admin', null, '2020-10-27 07:54:37', '2020-10-27 08:26:52');
INSERT INTO `st_role_authority` VALUES ('2018', '1', '13', '0', 'admin', null, '2020-10-27 07:54:37', '2020-10-27 08:26:52');
INSERT INTO `st_role_authority` VALUES ('2019', '1', '14', '0', 'admin', null, '2020-10-27 07:54:37', '2020-10-27 08:26:52');
INSERT INTO `st_role_authority` VALUES ('2020', '1', '15', '0', 'admin', null, '2020-10-27 07:54:37', '2020-10-27 08:26:52');
INSERT INTO `st_role_authority` VALUES ('2021', '1', '16', '0', 'admin', null, '2020-10-27 07:54:37', '2020-10-27 08:26:52');
INSERT INTO `st_role_authority` VALUES ('2022', '1', '17', '0', 'admin', null, '2020-10-27 07:54:37', '2020-10-27 08:26:52');
INSERT INTO `st_role_authority` VALUES ('2023', '1', '18', '0', 'admin', null, '2020-10-27 07:54:37', '2020-10-27 08:26:52');
INSERT INTO `st_role_authority` VALUES ('2024', '1', '41', '0', 'admin', null, '2020-10-27 07:54:37', '2020-10-27 08:26:52');
INSERT INTO `st_role_authority` VALUES ('2025', '1', '42', '0', 'admin', null, '2020-10-27 07:54:37', '2020-10-27 08:26:52');
INSERT INTO `st_role_authority` VALUES ('2026', '1', '56', '0', 'admin', null, '2020-10-27 07:54:37', '2020-10-27 08:26:52');
INSERT INTO `st_role_authority` VALUES ('2027', '1', '57', '0', 'admin', null, '2020-10-27 07:54:37', '2020-10-27 08:26:52');
INSERT INTO `st_role_authority` VALUES ('2028', '1', '58', '0', 'admin', null, '2020-10-27 07:54:37', '2020-10-27 08:26:52');
INSERT INTO `st_role_authority` VALUES ('2029', '1', '59', '0', 'admin', null, '2020-10-27 07:54:37', '2020-10-27 08:26:52');
INSERT INTO `st_role_authority` VALUES ('2030', '1', '60', '0', 'admin', null, '2020-10-27 07:54:37', '2020-10-27 08:26:52');
INSERT INTO `st_role_authority` VALUES ('2031', '1', '61', '0', 'admin', null, '2020-10-27 07:54:37', '2020-10-27 08:26:52');
INSERT INTO `st_role_authority` VALUES ('2032', '1', '62', '0', 'admin', null, '2020-10-27 07:54:37', '2020-10-27 08:26:52');
INSERT INTO `st_role_authority` VALUES ('2033', '1', '63', '0', 'admin', null, '2020-10-27 07:54:37', '2020-10-27 08:26:52');
INSERT INTO `st_role_authority` VALUES ('2034', '1', '64', '0', 'admin', null, '2020-10-27 07:54:37', '2020-10-27 08:26:52');
INSERT INTO `st_role_authority` VALUES ('2035', '1', '65', '0', 'admin', null, '2020-10-27 07:54:37', '2020-10-27 08:26:52');
INSERT INTO `st_role_authority` VALUES ('2036', '1', '66', '0', 'admin', null, '2020-10-27 07:54:37', '2020-10-27 08:26:52');
INSERT INTO `st_role_authority` VALUES ('2037', '1', '67', '0', 'admin', null, '2020-10-27 07:54:37', '2020-10-27 08:26:52');
INSERT INTO `st_role_authority` VALUES ('2038', '1', '107', '0', 'admin', null, '2020-10-27 07:54:37', '2020-10-27 08:26:52');
INSERT INTO `st_role_authority` VALUES ('2039', '1', '108', '0', 'admin', null, '2020-10-27 07:54:37', '2020-10-27 08:26:52');
INSERT INTO `st_role_authority` VALUES ('2040', '1', '109', '0', 'admin', null, '2020-10-27 07:54:37', '2020-10-27 08:26:52');
INSERT INTO `st_role_authority` VALUES ('2041', '1', '110', '0', 'admin', null, '2020-10-27 07:54:37', '2020-10-27 08:26:52');
INSERT INTO `st_role_authority` VALUES ('2042', '1', '111', '0', 'admin', null, '2020-10-27 07:54:37', '2020-10-27 08:26:52');
INSERT INTO `st_role_authority` VALUES ('2043', '1', '112', '0', 'admin', null, '2020-10-27 07:54:37', '2020-10-27 08:26:52');
INSERT INTO `st_role_authority` VALUES ('2044', '1', '113', '0', 'admin', null, '2020-10-27 07:54:37', '2020-10-27 08:26:52');
INSERT INTO `st_role_authority` VALUES ('2045', '1', '114', '0', 'admin', null, '2020-10-27 07:54:37', '2020-10-27 08:26:52');
INSERT INTO `st_role_authority` VALUES ('2046', '1', '123', '0', 'admin', null, '2020-10-27 07:54:37', '2020-10-27 08:26:52');
INSERT INTO `st_role_authority` VALUES ('2047', '1', '124', '0', 'admin', null, '2020-10-27 07:54:37', '2020-10-27 08:26:52');
INSERT INTO `st_role_authority` VALUES ('2048', '1', '125', '0', 'admin', null, '2020-10-27 07:54:37', '2020-10-27 08:26:52');
INSERT INTO `st_role_authority` VALUES ('2049', '1', '126', '0', 'admin', null, '2020-10-27 07:54:37', '2020-10-27 08:26:52');
INSERT INTO `st_role_authority` VALUES ('2050', '1', '127', '0', 'admin', null, '2020-10-27 07:54:37', '2020-10-27 08:26:52');
INSERT INTO `st_role_authority` VALUES ('2051', '1', '0', '0', 'admin', null, '2020-10-27 08:26:52', '2020-10-27 08:33:16');
INSERT INTO `st_role_authority` VALUES ('2052', '1', '64', '0', 'admin', null, '2020-10-27 08:26:52', '2020-10-27 08:33:16');
INSERT INTO `st_role_authority` VALUES ('2053', '1', '128', '0', 'admin', null, '2020-10-27 08:26:52', '2020-10-27 08:33:16');
INSERT INTO `st_role_authority` VALUES ('2054', '1', '1', '0', 'admin', null, '2020-10-27 08:26:52', '2020-10-27 08:33:16');
INSERT INTO `st_role_authority` VALUES ('2055', '1', '65', '0', 'admin', null, '2020-10-27 08:26:52', '2020-10-27 08:33:16');
INSERT INTO `st_role_authority` VALUES ('2056', '1', '129', '0', 'admin', null, '2020-10-27 08:26:52', '2020-10-27 08:33:16');
INSERT INTO `st_role_authority` VALUES ('2057', '1', '2', '0', 'admin', null, '2020-10-27 08:26:52', '2020-10-27 08:33:16');
INSERT INTO `st_role_authority` VALUES ('2058', '1', '66', '0', 'admin', null, '2020-10-27 08:26:52', '2020-10-27 08:33:16');
INSERT INTO `st_role_authority` VALUES ('2059', '1', '130', '0', 'admin', null, '2020-10-27 08:26:52', '2020-10-27 08:33:16');
INSERT INTO `st_role_authority` VALUES ('2060', '1', '3', '0', 'admin', null, '2020-10-27 08:26:52', '2020-10-27 08:33:16');
INSERT INTO `st_role_authority` VALUES ('2061', '1', '67', '0', 'admin', null, '2020-10-27 08:26:52', '2020-10-27 08:33:16');
INSERT INTO `st_role_authority` VALUES ('2062', '1', '131', '0', 'admin', null, '2020-10-27 08:26:52', '2020-10-27 08:33:16');
INSERT INTO `st_role_authority` VALUES ('2063', '1', '4', '0', 'admin', null, '2020-10-27 08:26:52', '2020-10-27 08:33:16');
INSERT INTO `st_role_authority` VALUES ('2064', '1', '5', '0', 'admin', null, '2020-10-27 08:26:52', '2020-10-27 08:33:16');
INSERT INTO `st_role_authority` VALUES ('2065', '1', '6', '0', 'admin', null, '2020-10-27 08:26:52', '2020-10-27 08:33:16');
INSERT INTO `st_role_authority` VALUES ('2066', '1', '7', '0', 'admin', null, '2020-10-27 08:26:52', '2020-10-27 08:33:16');
INSERT INTO `st_role_authority` VALUES ('2067', '1', '8', '0', 'admin', null, '2020-10-27 08:26:52', '2020-10-27 08:33:16');
INSERT INTO `st_role_authority` VALUES ('2068', '1', '9', '0', 'admin', null, '2020-10-27 08:26:52', '2020-10-27 08:33:16');
INSERT INTO `st_role_authority` VALUES ('2069', '1', '10', '0', 'admin', null, '2020-10-27 08:26:52', '2020-10-27 08:33:16');
INSERT INTO `st_role_authority` VALUES ('2070', '1', '11', '0', 'admin', null, '2020-10-27 08:26:52', '2020-10-27 08:33:16');
INSERT INTO `st_role_authority` VALUES ('2071', '1', '12', '0', 'admin', null, '2020-10-27 08:26:52', '2020-10-27 08:33:16');
INSERT INTO `st_role_authority` VALUES ('2072', '1', '13', '0', 'admin', null, '2020-10-27 08:26:52', '2020-10-27 08:33:16');
INSERT INTO `st_role_authority` VALUES ('2073', '1', '14', '0', 'admin', null, '2020-10-27 08:26:52', '2020-10-27 08:33:16');
INSERT INTO `st_role_authority` VALUES ('2074', '1', '15', '0', 'admin', null, '2020-10-27 08:26:52', '2020-10-27 08:33:16');
INSERT INTO `st_role_authority` VALUES ('2075', '1', '16', '0', 'admin', null, '2020-10-27 08:26:52', '2020-10-27 08:33:16');
INSERT INTO `st_role_authority` VALUES ('2076', '1', '17', '0', 'admin', null, '2020-10-27 08:26:52', '2020-10-27 08:33:16');
INSERT INTO `st_role_authority` VALUES ('2077', '1', '18', '0', 'admin', null, '2020-10-27 08:26:52', '2020-10-27 08:33:16');
INSERT INTO `st_role_authority` VALUES ('2078', '1', '107', '0', 'admin', null, '2020-10-27 08:26:52', '2020-10-27 08:33:16');
INSERT INTO `st_role_authority` VALUES ('2079', '1', '108', '0', 'admin', null, '2020-10-27 08:26:52', '2020-10-27 08:33:16');
INSERT INTO `st_role_authority` VALUES ('2080', '1', '109', '0', 'admin', null, '2020-10-27 08:26:52', '2020-10-27 08:33:16');
INSERT INTO `st_role_authority` VALUES ('2081', '1', '110', '0', 'admin', null, '2020-10-27 08:26:52', '2020-10-27 08:33:16');
INSERT INTO `st_role_authority` VALUES ('2082', '1', '111', '0', 'admin', null, '2020-10-27 08:26:52', '2020-10-27 08:33:16');
INSERT INTO `st_role_authority` VALUES ('2083', '1', '112', '0', 'admin', null, '2020-10-27 08:26:52', '2020-10-27 08:33:16');
INSERT INTO `st_role_authority` VALUES ('2084', '1', '113', '0', 'admin', null, '2020-10-27 08:26:52', '2020-10-27 08:33:16');
INSERT INTO `st_role_authority` VALUES ('2085', '1', '114', '0', 'admin', null, '2020-10-27 08:26:52', '2020-10-27 08:33:16');
INSERT INTO `st_role_authority` VALUES ('2086', '1', '56', '0', 'admin', null, '2020-10-27 08:26:52', '2020-10-27 08:33:16');
INSERT INTO `st_role_authority` VALUES ('2087', '1', '57', '0', 'admin', null, '2020-10-27 08:26:52', '2020-10-27 08:33:16');
INSERT INTO `st_role_authority` VALUES ('2088', '1', '58', '0', 'admin', null, '2020-10-27 08:26:52', '2020-10-27 08:33:16');
INSERT INTO `st_role_authority` VALUES ('2089', '1', '59', '0', 'admin', null, '2020-10-27 08:26:52', '2020-10-27 08:33:16');
INSERT INTO `st_role_authority` VALUES ('2090', '1', '123', '0', 'admin', null, '2020-10-27 08:26:52', '2020-10-27 08:33:16');
INSERT INTO `st_role_authority` VALUES ('2091', '1', '60', '0', 'admin', null, '2020-10-27 08:26:52', '2020-10-27 08:33:16');
INSERT INTO `st_role_authority` VALUES ('2092', '1', '124', '0', 'admin', null, '2020-10-27 08:26:52', '2020-10-27 08:33:16');
INSERT INTO `st_role_authority` VALUES ('2093', '1', '61', '0', 'admin', null, '2020-10-27 08:26:52', '2020-10-27 08:33:16');
INSERT INTO `st_role_authority` VALUES ('2094', '1', '125', '0', 'admin', null, '2020-10-27 08:26:52', '2020-10-27 08:33:16');
INSERT INTO `st_role_authority` VALUES ('2095', '1', '62', '0', 'admin', null, '2020-10-27 08:26:52', '2020-10-27 08:33:16');
INSERT INTO `st_role_authority` VALUES ('2096', '1', '126', '0', 'admin', null, '2020-10-27 08:26:52', '2020-10-27 08:33:16');
INSERT INTO `st_role_authority` VALUES ('2097', '1', '63', '0', 'admin', null, '2020-10-27 08:26:52', '2020-10-27 08:33:16');
INSERT INTO `st_role_authority` VALUES ('2098', '1', '127', '0', 'admin', null, '2020-10-27 08:26:52', '2020-10-27 08:33:16');
INSERT INTO `st_role_authority` VALUES ('2099', '1', '0', '0', 'admin', null, '2020-10-27 08:33:16', '2020-10-27 12:15:56');
INSERT INTO `st_role_authority` VALUES ('2100', '1', '128', '0', 'admin', null, '2020-10-27 08:33:16', '2020-10-27 12:15:56');
INSERT INTO `st_role_authority` VALUES ('2101', '1', '1', '0', 'admin', null, '2020-10-27 08:33:16', '2020-10-27 12:15:56');
INSERT INTO `st_role_authority` VALUES ('2102', '1', '129', '0', 'admin', null, '2020-10-27 08:33:16', '2020-10-27 12:15:56');
INSERT INTO `st_role_authority` VALUES ('2103', '1', '2', '0', 'admin', null, '2020-10-27 08:33:16', '2020-10-27 12:15:56');
INSERT INTO `st_role_authority` VALUES ('2104', '1', '130', '0', 'admin', null, '2020-10-27 08:33:16', '2020-10-27 12:15:56');
INSERT INTO `st_role_authority` VALUES ('2105', '1', '3', '0', 'admin', null, '2020-10-27 08:33:16', '2020-10-27 12:15:56');
INSERT INTO `st_role_authority` VALUES ('2106', '1', '131', '0', 'admin', null, '2020-10-27 08:33:16', '2020-10-27 12:15:56');
INSERT INTO `st_role_authority` VALUES ('2107', '1', '4', '0', 'admin', null, '2020-10-27 08:33:16', '2020-10-27 12:15:56');
INSERT INTO `st_role_authority` VALUES ('2108', '1', '132', '0', 'admin', null, '2020-10-27 08:33:16', '2020-10-27 12:15:56');
INSERT INTO `st_role_authority` VALUES ('2109', '1', '5', '0', 'admin', null, '2020-10-27 08:33:16', '2020-10-27 12:15:56');
INSERT INTO `st_role_authority` VALUES ('2110', '1', '133', '0', 'admin', null, '2020-10-27 08:33:16', '2020-10-27 12:15:56');
INSERT INTO `st_role_authority` VALUES ('2111', '1', '6', '0', 'admin', null, '2020-10-27 08:33:16', '2020-10-27 12:15:56');
INSERT INTO `st_role_authority` VALUES ('2112', '1', '134', '0', 'admin', null, '2020-10-27 08:33:16', '2020-10-27 12:15:56');
INSERT INTO `st_role_authority` VALUES ('2113', '1', '7', '0', 'admin', null, '2020-10-27 08:33:16', '2020-10-27 12:15:56');
INSERT INTO `st_role_authority` VALUES ('2114', '1', '135', '0', 'admin', null, '2020-10-27 08:33:16', '2020-10-27 12:15:56');
INSERT INTO `st_role_authority` VALUES ('2115', '1', '8', '0', 'admin', null, '2020-10-27 08:33:16', '2020-10-27 12:15:56');
INSERT INTO `st_role_authority` VALUES ('2116', '1', '9', '0', 'admin', null, '2020-10-27 08:33:16', '2020-10-27 12:15:56');
INSERT INTO `st_role_authority` VALUES ('2117', '1', '10', '0', 'admin', null, '2020-10-27 08:33:16', '2020-10-27 12:15:56');
INSERT INTO `st_role_authority` VALUES ('2118', '1', '11', '0', 'admin', null, '2020-10-27 08:33:16', '2020-10-27 12:15:56');
INSERT INTO `st_role_authority` VALUES ('2119', '1', '12', '0', 'admin', null, '2020-10-27 08:33:16', '2020-10-27 12:15:56');
INSERT INTO `st_role_authority` VALUES ('2120', '1', '13', '0', 'admin', null, '2020-10-27 08:33:16', '2020-10-27 12:15:56');
INSERT INTO `st_role_authority` VALUES ('2121', '1', '14', '0', 'admin', null, '2020-10-27 08:33:16', '2020-10-27 12:15:56');
INSERT INTO `st_role_authority` VALUES ('2122', '1', '15', '0', 'admin', null, '2020-10-27 08:33:16', '2020-10-27 12:15:56');
INSERT INTO `st_role_authority` VALUES ('2123', '1', '16', '0', 'admin', null, '2020-10-27 08:33:16', '2020-10-27 12:15:56');
INSERT INTO `st_role_authority` VALUES ('2124', '1', '17', '0', 'admin', null, '2020-10-27 08:33:16', '2020-10-27 12:15:56');
INSERT INTO `st_role_authority` VALUES ('2125', '1', '18', '0', 'admin', null, '2020-10-27 08:33:16', '2020-10-27 12:15:56');
INSERT INTO `st_role_authority` VALUES ('2126', '1', '56', '0', 'admin', null, '2020-10-27 08:33:16', '2020-10-27 12:15:56');
INSERT INTO `st_role_authority` VALUES ('2127', '1', '57', '0', 'admin', null, '2020-10-27 08:33:16', '2020-10-27 12:15:56');
INSERT INTO `st_role_authority` VALUES ('2128', '1', '58', '0', 'admin', null, '2020-10-27 08:33:16', '2020-10-27 12:15:56');
INSERT INTO `st_role_authority` VALUES ('2129', '1', '59', '0', 'admin', null, '2020-10-27 08:33:16', '2020-10-27 12:15:56');
INSERT INTO `st_role_authority` VALUES ('2130', '1', '60', '0', 'admin', null, '2020-10-27 08:33:16', '2020-10-27 12:15:56');
INSERT INTO `st_role_authority` VALUES ('2131', '1', '61', '0', 'admin', null, '2020-10-27 08:33:16', '2020-10-27 12:15:56');
INSERT INTO `st_role_authority` VALUES ('2132', '1', '62', '0', 'admin', null, '2020-10-27 08:33:16', '2020-10-27 12:15:56');
INSERT INTO `st_role_authority` VALUES ('2133', '1', '63', '0', 'admin', null, '2020-10-27 08:33:16', '2020-10-27 12:15:56');
INSERT INTO `st_role_authority` VALUES ('2134', '1', '64', '0', 'admin', null, '2020-10-27 08:33:16', '2020-10-27 12:15:56');
INSERT INTO `st_role_authority` VALUES ('2135', '1', '65', '0', 'admin', null, '2020-10-27 08:33:16', '2020-10-27 12:15:56');
INSERT INTO `st_role_authority` VALUES ('2136', '1', '66', '0', 'admin', null, '2020-10-27 08:33:16', '2020-10-27 12:15:56');
INSERT INTO `st_role_authority` VALUES ('2137', '1', '67', '0', 'admin', null, '2020-10-27 08:33:16', '2020-10-27 12:15:56');
INSERT INTO `st_role_authority` VALUES ('2138', '1', '107', '0', 'admin', null, '2020-10-27 08:33:16', '2020-10-27 12:15:56');
INSERT INTO `st_role_authority` VALUES ('2139', '1', '108', '0', 'admin', null, '2020-10-27 08:33:16', '2020-10-27 12:15:56');
INSERT INTO `st_role_authority` VALUES ('2140', '1', '109', '0', 'admin', null, '2020-10-27 08:33:16', '2020-10-27 12:15:56');
INSERT INTO `st_role_authority` VALUES ('2141', '1', '110', '0', 'admin', null, '2020-10-27 08:33:16', '2020-10-27 12:15:56');
INSERT INTO `st_role_authority` VALUES ('2142', '1', '111', '0', 'admin', null, '2020-10-27 08:33:16', '2020-10-27 12:15:56');
INSERT INTO `st_role_authority` VALUES ('2143', '1', '112', '0', 'admin', null, '2020-10-27 08:33:16', '2020-10-27 12:15:56');
INSERT INTO `st_role_authority` VALUES ('2144', '1', '113', '0', 'admin', null, '2020-10-27 08:33:16', '2020-10-27 12:15:56');
INSERT INTO `st_role_authority` VALUES ('2145', '1', '114', '0', 'admin', null, '2020-10-27 08:33:16', '2020-10-27 12:15:56');
INSERT INTO `st_role_authority` VALUES ('2146', '1', '123', '0', 'admin', null, '2020-10-27 08:33:16', '2020-10-27 12:15:56');
INSERT INTO `st_role_authority` VALUES ('2147', '1', '124', '0', 'admin', null, '2020-10-27 08:33:16', '2020-10-27 12:15:56');
INSERT INTO `st_role_authority` VALUES ('2148', '1', '125', '0', 'admin', null, '2020-10-27 08:33:16', '2020-10-27 12:15:56');
INSERT INTO `st_role_authority` VALUES ('2149', '1', '126', '0', 'admin', null, '2020-10-27 08:33:16', '2020-10-27 12:15:56');
INSERT INTO `st_role_authority` VALUES ('2150', '1', '127', '0', 'admin', null, '2020-10-27 08:33:16', '2020-10-27 12:15:56');
INSERT INTO `st_role_authority` VALUES ('2151', '1', '0', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2152', '1', '128', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2153', '1', '1', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2154', '1', '129', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2155', '1', '2', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2156', '1', '130', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2157', '1', '3', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2158', '1', '131', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2159', '1', '4', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2160', '1', '132', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2161', '1', '5', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2162', '1', '133', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2163', '1', '6', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2164', '1', '134', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2165', '1', '7', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2166', '1', '135', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2167', '1', '8', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2168', '1', '136', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2169', '1', '9', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2170', '1', '137', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2171', '1', '10', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2172', '1', '138', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2173', '1', '11', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2174', '1', '139', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2175', '1', '12', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2176', '1', '140', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2177', '1', '13', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2178', '1', '14', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2179', '1', '15', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2180', '1', '16', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2181', '1', '17', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2182', '1', '18', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2183', '1', '56', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2184', '1', '57', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2185', '1', '58', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2186', '1', '59', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2187', '1', '60', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2188', '1', '61', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2189', '1', '62', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2190', '1', '63', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2191', '1', '64', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2192', '1', '65', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2193', '1', '66', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2194', '1', '67', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2195', '1', '107', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2196', '1', '108', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2197', '1', '109', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2198', '1', '110', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2199', '1', '111', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2200', '1', '112', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2201', '1', '113', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2202', '1', '114', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2203', '1', '123', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2204', '1', '124', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2205', '1', '125', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2206', '1', '126', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2207', '1', '127', '0', 'admin', null, '2020-10-27 12:15:56', '2020-10-27 13:56:21');
INSERT INTO `st_role_authority` VALUES ('2208', '1', '0', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2209', '1', '1', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2210', '1', '2', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2211', '1', '3', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2212', '1', '4', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2213', '1', '132', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2214', '1', '5', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2215', '1', '133', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2216', '1', '6', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2217', '1', '134', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2218', '1', '7', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2219', '1', '135', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2220', '1', '8', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2221', '1', '136', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2222', '1', '9', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2223', '1', '137', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2224', '1', '10', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2225', '1', '138', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2226', '1', '11', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2227', '1', '139', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2228', '1', '12', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2229', '1', '140', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2230', '1', '13', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2231', '1', '14', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2232', '1', '15', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2233', '1', '16', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2234', '1', '17', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2235', '1', '18', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2236', '1', '56', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2237', '1', '57', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2238', '1', '58', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2239', '1', '59', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2240', '1', '60', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2241', '1', '61', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2242', '1', '62', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2243', '1', '63', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2244', '1', '64', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2245', '1', '65', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2246', '1', '66', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2247', '1', '67', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2248', '1', '107', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2249', '1', '108', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2250', '1', '109', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2251', '1', '110', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2252', '1', '111', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2253', '1', '112', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2254', '1', '113', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2255', '1', '114', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2256', '1', '115', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2257', '1', '117', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2258', '1', '118', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2259', '1', '119', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2260', '1', '120', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2261', '1', '121', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2262', '1', '122', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2263', '1', '123', '0', 'admin', null, '2020-10-27 13:56:21', '2020-10-27 14:12:40');
INSERT INTO `st_role_authority` VALUES ('2264', '1', '0', '0', 'admin', null, '2020-10-27 14:12:40', '2020-10-28 01:18:17');
INSERT INTO `st_role_authority` VALUES ('2265', '1', '1', '0', 'admin', null, '2020-10-27 14:12:40', '2020-10-28 01:18:17');
INSERT INTO `st_role_authority` VALUES ('2266', '1', '2', '0', 'admin', null, '2020-10-27 14:12:40', '2020-10-28 01:18:17');
INSERT INTO `st_role_authority` VALUES ('2267', '1', '3', '0', 'admin', null, '2020-10-27 14:12:40', '2020-10-28 01:18:17');
INSERT INTO `st_role_authority` VALUES ('2268', '1', '4', '0', 'admin', null, '2020-10-27 14:12:40', '2020-10-28 01:18:17');
INSERT INTO `st_role_authority` VALUES ('2269', '1', '132', '0', 'admin', null, '2020-10-27 14:12:40', '2020-10-28 01:18:17');
INSERT INTO `st_role_authority` VALUES ('2270', '1', '5', '0', 'admin', null, '2020-10-27 14:12:40', '2020-10-28 01:18:17');
INSERT INTO `st_role_authority` VALUES ('2271', '1', '133', '0', 'admin', null, '2020-10-27 14:12:40', '2020-10-28 01:18:17');
INSERT INTO `st_role_authority` VALUES ('2272', '1', '6', '0', 'admin', null, '2020-10-27 14:12:40', '2020-10-28 01:18:17');
INSERT INTO `st_role_authority` VALUES ('2273', '1', '134', '0', 'admin', null, '2020-10-27 14:12:40', '2020-10-28 01:18:17');
INSERT INTO `st_role_authority` VALUES ('2274', '1', '7', '0', 'admin', null, '2020-10-27 14:12:40', '2020-10-28 01:18:17');
INSERT INTO `st_role_authority` VALUES ('2275', '1', '135', '0', 'admin', null, '2020-10-27 14:12:40', '2020-10-28 01:18:17');
INSERT INTO `st_role_authority` VALUES ('2276', '1', '8', '0', 'admin', null, '2020-10-27 14:12:40', '2020-10-28 01:18:17');
INSERT INTO `st_role_authority` VALUES ('2277', '1', '9', '0', 'admin', null, '2020-10-27 14:12:40', '2020-10-28 01:18:17');
INSERT INTO `st_role_authority` VALUES ('2278', '1', '10', '0', 'admin', null, '2020-10-27 14:12:40', '2020-10-28 01:18:17');
INSERT INTO `st_role_authority` VALUES ('2279', '1', '11', '0', 'admin', null, '2020-10-27 14:12:40', '2020-10-28 01:18:17');
INSERT INTO `st_role_authority` VALUES ('2280', '1', '12', '0', 'admin', null, '2020-10-27 14:12:40', '2020-10-28 01:18:17');
INSERT INTO `st_role_authority` VALUES ('2281', '1', '13', '0', 'admin', null, '2020-10-27 14:12:40', '2020-10-28 01:18:17');
INSERT INTO `st_role_authority` VALUES ('2282', '1', '141', '0', 'admin', null, '2020-10-27 14:12:40', '2020-10-28 01:18:17');
INSERT INTO `st_role_authority` VALUES ('2283', '1', '14', '0', 'admin', null, '2020-10-27 14:12:40', '2020-10-28 01:18:17');
INSERT INTO `st_role_authority` VALUES ('2284', '1', '142', '0', 'admin', null, '2020-10-27 14:12:40', '2020-10-28 01:18:17');
INSERT INTO `st_role_authority` VALUES ('2285', '1', '15', '0', 'admin', null, '2020-10-27 14:12:40', '2020-10-28 01:18:17');
INSERT INTO `st_role_authority` VALUES ('2286', '1', '143', '0', 'admin', null, '2020-10-27 14:12:40', '2020-10-28 01:18:17');
INSERT INTO `st_role_authority` VALUES ('2287', '1', '16', '0', 'admin', null, '2020-10-27 14:12:40', '2020-10-28 01:18:17');
INSERT INTO `st_role_authority` VALUES ('2288', '1', '144', '0', 'admin', null, '2020-10-27 14:12:40', '2020-10-28 01:18:17');
INSERT INTO `st_role_authority` VALUES ('2289', '1', '17', '0', 'admin', null, '2020-10-27 14:12:40', '2020-10-28 01:18:17');
INSERT INTO `st_role_authority` VALUES ('2290', '1', '145', '0', 'admin', null, '2020-10-27 14:12:40', '2020-10-28 01:18:17');
INSERT INTO `st_role_authority` VALUES ('2291', '1', '18', '0', 'admin', null, '2020-10-27 14:12:40', '2020-10-28 01:18:17');
INSERT INTO `st_role_authority` VALUES ('2292', '1', '146', '0', 'admin', null, '2020-10-27 14:12:40', '2020-10-28 01:18:17');
INSERT INTO `st_role_authority` VALUES ('2293', '1', '147', '0', 'admin', null, '2020-10-27 14:12:40', '2020-10-28 01:18:17');
INSERT INTO `st_role_authority` VALUES ('2294', '1', '56', '0', 'admin', null, '2020-10-27 14:12:40', '2020-10-28 01:18:17');
INSERT INTO `st_role_authority` VALUES ('2295', '1', '57', '0', 'admin', null, '2020-10-27 14:12:40', '2020-10-28 01:18:17');
INSERT INTO `st_role_authority` VALUES ('2296', '1', '58', '0', 'admin', null, '2020-10-27 14:12:40', '2020-10-28 01:18:17');
INSERT INTO `st_role_authority` VALUES ('2297', '1', '59', '0', 'admin', null, '2020-10-27 14:12:40', '2020-10-28 01:18:17');
INSERT INTO `st_role_authority` VALUES ('2298', '1', '60', '0', 'admin', null, '2020-10-27 14:12:40', '2020-10-28 01:18:17');
INSERT INTO `st_role_authority` VALUES ('2299', '1', '61', '0', 'admin', null, '2020-10-27 14:12:40', '2020-10-28 01:18:17');
INSERT INTO `st_role_authority` VALUES ('2300', '1', '62', '0', 'admin', null, '2020-10-27 14:12:40', '2020-10-28 01:18:17');
INSERT INTO `st_role_authority` VALUES ('2301', '1', '63', '0', 'admin', null, '2020-10-27 14:12:40', '2020-10-28 01:18:17');
INSERT INTO `st_role_authority` VALUES ('2302', '1', '64', '0', 'admin', null, '2020-10-27 14:12:40', '2020-10-28 01:18:17');
INSERT INTO `st_role_authority` VALUES ('2303', '1', '65', '0', 'admin', null, '2020-10-27 14:12:40', '2020-10-28 01:18:17');
INSERT INTO `st_role_authority` VALUES ('2304', '1', '66', '0', 'admin', null, '2020-10-27 14:12:40', '2020-10-28 01:18:17');
INSERT INTO `st_role_authority` VALUES ('2305', '1', '67', '0', 'admin', null, '2020-10-27 14:12:40', '2020-10-28 01:18:17');
INSERT INTO `st_role_authority` VALUES ('2306', '1', '107', '0', 'admin', null, '2020-10-27 14:12:40', '2020-10-28 01:18:17');
INSERT INTO `st_role_authority` VALUES ('2307', '1', '108', '0', 'admin', null, '2020-10-27 14:12:40', '2020-10-28 01:18:17');
INSERT INTO `st_role_authority` VALUES ('2308', '1', '109', '0', 'admin', null, '2020-10-27 14:12:40', '2020-10-28 01:18:17');
INSERT INTO `st_role_authority` VALUES ('2309', '1', '110', '0', 'admin', null, '2020-10-27 14:12:40', '2020-10-28 01:18:17');
INSERT INTO `st_role_authority` VALUES ('2310', '1', '111', '0', 'admin', null, '2020-10-27 14:12:40', '2020-10-28 01:18:17');
INSERT INTO `st_role_authority` VALUES ('2311', '1', '112', '0', 'admin', null, '2020-10-27 14:12:40', '2020-10-28 01:18:17');
INSERT INTO `st_role_authority` VALUES ('2312', '1', '113', '0', 'admin', null, '2020-10-27 14:12:40', '2020-10-28 01:18:17');
INSERT INTO `st_role_authority` VALUES ('2313', '1', '114', '0', 'admin', null, '2020-10-27 14:12:40', '2020-10-28 01:18:17');
INSERT INTO `st_role_authority` VALUES ('2314', '1', '123', '0', 'admin', null, '2020-10-27 14:12:40', '2020-10-28 01:18:17');
INSERT INTO `st_role_authority` VALUES ('2315', '1', '0', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2316', '1', '1', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2317', '1', '2', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2318', '1', '3', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2319', '1', '4', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2320', '1', '132', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2321', '1', '5', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2322', '1', '133', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2323', '1', '6', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2324', '1', '134', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2325', '1', '7', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2326', '1', '135', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2327', '1', '8', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2328', '1', '9', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2329', '1', '10', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2330', '1', '11', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2331', '1', '12', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2332', '1', '13', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2333', '1', '141', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2334', '1', '14', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2335', '1', '142', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2336', '1', '15', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2337', '1', '143', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2338', '1', '16', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2339', '1', '144', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2340', '1', '17', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2341', '1', '145', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2342', '1', '18', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2343', '1', '146', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2344', '1', '147', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2345', '1', '56', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2346', '1', '57', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2347', '1', '58', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2348', '1', '59', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2349', '1', '60', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2350', '1', '61', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2351', '1', '62', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2352', '1', '63', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2353', '1', '64', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2354', '1', '65', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2355', '1', '66', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2356', '1', '67', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2357', '1', '72', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2358', '1', '73', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2359', '1', '74', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2360', '1', '75', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2361', '1', '76', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2362', '1', '77', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2363', '1', '78', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2364', '1', '84', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2365', '1', '90', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2366', '1', '107', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2367', '1', '108', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2368', '1', '109', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2369', '1', '110', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2370', '1', '111', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2371', '1', '112', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2372', '1', '113', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2373', '1', '114', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2374', '1', '123', '0', 'admin', null, '2020-10-28 01:18:17', '2020-10-28 01:26:10');
INSERT INTO `st_role_authority` VALUES ('2375', '1', '0', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2376', '1', '1', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2377', '1', '2', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2378', '1', '3', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2379', '1', '4', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2380', '1', '132', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2381', '1', '5', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2382', '1', '133', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2383', '1', '6', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2384', '1', '134', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2385', '1', '7', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2386', '1', '135', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2387', '1', '8', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2388', '1', '9', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2389', '1', '10', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2390', '1', '11', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2391', '1', '12', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2392', '1', '13', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2393', '1', '141', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2394', '1', '14', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2395', '1', '142', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2396', '1', '15', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2397', '1', '143', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2398', '1', '16', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2399', '1', '144', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2400', '1', '17', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2401', '1', '145', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2402', '1', '18', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2403', '1', '146', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2404', '1', '147', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2405', '1', '30', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2406', '1', '31', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2407', '1', '32', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2408', '1', '41', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2409', '1', '42', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2410', '1', '56', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2411', '1', '57', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2412', '1', '58', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2413', '1', '59', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2414', '1', '60', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2415', '1', '61', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2416', '1', '62', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2417', '1', '63', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2418', '1', '64', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2419', '1', '65', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2420', '1', '66', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2421', '1', '67', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2422', '1', '72', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2423', '1', '73', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2424', '1', '74', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2425', '1', '75', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2426', '1', '76', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2427', '1', '77', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2428', '1', '78', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2429', '1', '84', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2430', '1', '90', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2431', '1', '107', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2432', '1', '108', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2433', '1', '109', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2434', '1', '110', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2435', '1', '111', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2436', '1', '112', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2437', '1', '113', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2438', '1', '114', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2439', '1', '123', '0', 'admin', null, '2020-10-28 01:26:10', '2020-10-28 01:41:44');
INSERT INTO `st_role_authority` VALUES ('2440', '1', '0', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2441', '1', '1', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2442', '1', '2', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2443', '1', '3', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2444', '1', '4', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2445', '1', '132', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2446', '1', '5', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2447', '1', '133', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2448', '1', '6', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2449', '1', '134', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2450', '1', '7', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2451', '1', '135', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2452', '1', '8', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2453', '1', '9', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2454', '1', '10', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2455', '1', '11', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2456', '1', '12', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2457', '1', '13', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2458', '1', '141', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2459', '1', '14', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2460', '1', '142', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2461', '1', '15', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2462', '1', '143', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2463', '1', '16', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2464', '1', '144', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2465', '1', '17', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2466', '1', '145', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2467', '1', '18', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2468', '1', '146', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2469', '1', '147', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2470', '1', '148', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2471', '1', '149', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2472', '1', '150', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2473', '1', '151', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2474', '1', '152', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2475', '1', '153', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2476', '1', '56', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2477', '1', '57', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2478', '1', '58', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2479', '1', '59', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2480', '1', '60', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2481', '1', '61', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2482', '1', '62', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2483', '1', '63', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2484', '1', '64', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2485', '1', '65', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2486', '1', '66', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2487', '1', '67', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2488', '1', '72', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2489', '1', '73', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2490', '1', '74', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2491', '1', '75', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2492', '1', '76', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2493', '1', '77', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2494', '1', '78', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2495', '1', '84', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2496', '1', '90', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2497', '1', '107', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2498', '1', '108', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2499', '1', '109', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2500', '1', '110', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2501', '1', '111', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2502', '1', '112', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2503', '1', '113', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2504', '1', '114', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2505', '1', '123', '0', 'admin', null, '2020-10-28 01:41:44', '2020-10-28 01:41:53');
INSERT INTO `st_role_authority` VALUES ('2506', '1', '0', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2507', '1', '128', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2508', '1', '1', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2509', '1', '129', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2510', '1', '2', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2511', '1', '130', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2512', '1', '3', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2513', '1', '131', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2514', '1', '4', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2515', '1', '132', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2516', '1', '5', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2517', '1', '133', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2518', '1', '6', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2519', '1', '134', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2520', '1', '7', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2521', '1', '135', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2522', '1', '8', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2523', '1', '136', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2524', '1', '9', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2525', '1', '137', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2526', '1', '10', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2527', '1', '138', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2528', '1', '11', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2529', '1', '139', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2530', '1', '12', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2531', '1', '140', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2532', '1', '13', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2533', '1', '141', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2534', '1', '14', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2535', '1', '142', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2536', '1', '15', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2537', '1', '143', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2538', '1', '16', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2539', '1', '144', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2540', '1', '17', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2541', '1', '145', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2542', '1', '18', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2543', '1', '146', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2544', '1', '147', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2545', '1', '148', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2546', '1', '149', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2547', '1', '150', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2548', '1', '151', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2549', '1', '152', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2550', '1', '153', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2551', '1', '56', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2552', '1', '57', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2553', '1', '58', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2554', '1', '59', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2555', '1', '60', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2556', '1', '61', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2557', '1', '62', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2558', '1', '63', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2559', '1', '64', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2560', '1', '65', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2561', '1', '66', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2562', '1', '67', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2563', '1', '72', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2564', '1', '73', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2565', '1', '74', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2566', '1', '75', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2567', '1', '76', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2568', '1', '77', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2569', '1', '78', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2570', '1', '84', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2571', '1', '90', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2572', '1', '107', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2573', '1', '108', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2574', '1', '109', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2575', '1', '110', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2576', '1', '111', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2577', '1', '112', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2578', '1', '113', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2579', '1', '114', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2580', '1', '123', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2581', '1', '124', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2582', '1', '125', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2583', '1', '126', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2584', '1', '127', '0', 'admin', null, '2020-10-28 01:41:53', '2020-10-28 01:45:33');
INSERT INTO `st_role_authority` VALUES ('2585', '1', '0', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2586', '1', '128', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2587', '1', '1', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2588', '1', '129', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2589', '1', '2', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2590', '1', '130', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2591', '1', '3', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2592', '1', '131', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2593', '1', '4', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2594', '1', '132', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2595', '1', '5', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2596', '1', '133', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2597', '1', '6', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2598', '1', '134', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2599', '1', '7', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2600', '1', '135', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2601', '1', '8', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2602', '1', '136', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2603', '1', '9', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2604', '1', '137', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2605', '1', '10', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2606', '1', '138', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2607', '1', '11', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2608', '1', '139', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2609', '1', '12', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2610', '1', '140', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2611', '1', '13', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2612', '1', '141', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2613', '1', '14', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2614', '1', '142', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2615', '1', '15', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2616', '1', '143', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2617', '1', '16', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2618', '1', '144', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2619', '1', '17', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2620', '1', '145', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2621', '1', '18', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2622', '1', '146', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2623', '1', '147', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2624', '1', '56', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2625', '1', '57', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2626', '1', '58', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2627', '1', '59', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2628', '1', '60', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2629', '1', '61', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2630', '1', '62', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2631', '1', '63', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2632', '1', '64', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2633', '1', '65', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2634', '1', '66', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2635', '1', '67', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2636', '1', '72', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2637', '1', '73', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2638', '1', '74', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2639', '1', '75', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2640', '1', '76', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2641', '1', '77', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2642', '1', '78', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2643', '1', '84', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2644', '1', '90', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2645', '1', '123', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2646', '1', '124', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2647', '1', '125', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2648', '1', '126', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2649', '1', '127', '0', 'admin', null, '2020-10-28 01:45:33', '2020-10-28 13:06:15');
INSERT INTO `st_role_authority` VALUES ('2650', '1', '0', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2651', '1', '128', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2652', '1', '1', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2653', '1', '129', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2654', '1', '2', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2655', '1', '130', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2656', '1', '3', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2657', '1', '131', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2658', '1', '4', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2659', '1', '132', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2660', '1', '5', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2661', '1', '133', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2662', '1', '6', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2663', '1', '134', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2664', '1', '7', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2665', '1', '135', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2666', '1', '8', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2667', '1', '136', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2668', '1', '9', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2669', '1', '137', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2670', '1', '10', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2671', '1', '138', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2672', '1', '11', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2673', '1', '139', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2674', '1', '12', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2675', '1', '140', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2676', '1', '13', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2677', '1', '141', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2678', '1', '14', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2679', '1', '142', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2680', '1', '15', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2681', '1', '143', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2682', '1', '16', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2683', '1', '144', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2684', '1', '17', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2685', '1', '145', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2686', '1', '18', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2687', '1', '146', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2688', '1', '147', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2689', '1', '56', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2690', '1', '57', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2691', '1', '58', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2692', '1', '59', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2693', '1', '60', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2694', '1', '61', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2695', '1', '62', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2696', '1', '63', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2697', '1', '64', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2698', '1', '65', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2699', '1', '66', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2700', '1', '67', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2701', '1', '123', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2702', '1', '124', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2703', '1', '125', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2704', '1', '126', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2705', '1', '127', '0', 'admin', null, '2020-10-28 13:06:15', '2020-10-28 13:08:10');
INSERT INTO `st_role_authority` VALUES ('2706', '1', '0', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2707', '1', '128', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2708', '1', '1', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2709', '1', '129', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2710', '1', '2', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2711', '1', '130', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2712', '1', '3', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2713', '1', '131', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2714', '1', '4', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2715', '1', '132', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2716', '1', '5', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2717', '1', '133', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2718', '1', '6', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2719', '1', '134', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2720', '1', '7', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2721', '1', '135', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2722', '1', '8', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2723', '1', '136', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2724', '1', '9', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2725', '1', '137', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2726', '1', '10', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2727', '1', '138', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2728', '1', '11', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2729', '1', '139', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2730', '1', '12', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2731', '1', '140', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2732', '1', '13', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2733', '1', '141', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2734', '1', '14', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2735', '1', '142', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2736', '1', '15', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2737', '1', '143', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2738', '1', '16', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2739', '1', '144', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2740', '1', '17', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2741', '1', '145', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2742', '1', '18', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2743', '1', '146', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2744', '1', '147', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2745', '1', '148', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2746', '1', '149', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2747', '1', '150', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2748', '1', '151', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2749', '1', '152', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2750', '1', '153', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2751', '1', '56', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2752', '1', '57', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2753', '1', '58', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2754', '1', '59', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2755', '1', '60', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2756', '1', '61', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2757', '1', '62', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2758', '1', '63', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2759', '1', '64', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2760', '1', '65', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2761', '1', '66', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2762', '1', '67', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2763', '1', '123', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2764', '1', '124', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2765', '1', '125', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2766', '1', '126', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2767', '1', '127', '0', 'admin', null, '2020-10-28 13:08:10', '2020-10-28 13:12:24');
INSERT INTO `st_role_authority` VALUES ('2768', '1', '0', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2769', '1', '128', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2770', '1', '1', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2771', '1', '129', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2772', '1', '2', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2773', '1', '130', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2774', '1', '3', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2775', '1', '131', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2776', '1', '4', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2777', '1', '132', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2778', '1', '5', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2779', '1', '133', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2780', '1', '6', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2781', '1', '134', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2782', '1', '7', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2783', '1', '135', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2784', '1', '8', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2785', '1', '136', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2786', '1', '9', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2787', '1', '137', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2788', '1', '10', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2789', '1', '138', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2790', '1', '11', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2791', '1', '139', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2792', '1', '12', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2793', '1', '140', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2794', '1', '13', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2795', '1', '141', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2796', '1', '14', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2797', '1', '142', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2798', '1', '15', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2799', '1', '143', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2800', '1', '16', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2801', '1', '144', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2802', '1', '17', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2803', '1', '145', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2804', '1', '18', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2805', '1', '146', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2806', '1', '147', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2807', '1', '148', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2808', '1', '150', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2809', '1', '152', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2810', '1', '153', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2811', '1', '56', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2812', '1', '57', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2813', '1', '58', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2814', '1', '59', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2815', '1', '60', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2816', '1', '61', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2817', '1', '62', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2818', '1', '63', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2819', '1', '64', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2820', '1', '65', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2821', '1', '66', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2822', '1', '67', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2823', '1', '123', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2824', '1', '124', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2825', '1', '125', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2826', '1', '126', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2827', '1', '127', '0', 'admin', null, '2020-10-28 13:12:24', '2020-10-28 13:13:14');
INSERT INTO `st_role_authority` VALUES ('2828', '1', '0', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2829', '1', '128', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2830', '1', '1', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2831', '1', '129', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2832', '1', '2', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2833', '1', '130', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2834', '1', '3', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2835', '1', '131', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2836', '1', '4', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2837', '1', '132', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2838', '1', '5', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2839', '1', '133', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2840', '1', '6', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2841', '1', '134', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2842', '1', '7', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2843', '1', '135', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2844', '1', '8', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2845', '1', '136', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2846', '1', '9', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2847', '1', '137', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2848', '1', '10', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2849', '1', '138', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2850', '1', '11', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2851', '1', '139', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2852', '1', '12', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2853', '1', '140', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2854', '1', '13', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2855', '1', '141', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2856', '1', '14', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2857', '1', '142', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2858', '1', '15', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2859', '1', '143', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2860', '1', '16', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2861', '1', '144', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2862', '1', '17', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2863', '1', '145', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2864', '1', '18', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2865', '1', '146', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2866', '1', '147', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2867', '1', '56', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2868', '1', '57', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2869', '1', '58', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2870', '1', '59', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2871', '1', '60', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2872', '1', '61', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2873', '1', '62', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2874', '1', '63', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2875', '1', '64', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2876', '1', '65', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2877', '1', '66', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2878', '1', '67', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2879', '1', '123', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2880', '1', '124', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2881', '1', '125', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2882', '1', '126', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2883', '1', '127', '0', 'admin', null, '2020-10-28 13:13:14', '2020-10-28 14:31:47');
INSERT INTO `st_role_authority` VALUES ('2884', '1', '0', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2885', '1', '128', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2886', '1', '1', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2887', '1', '129', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2888', '1', '2', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2889', '1', '130', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2890', '1', '3', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2891', '1', '131', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2892', '1', '4', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2893', '1', '132', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2894', '1', '5', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2895', '1', '133', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2896', '1', '6', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2897', '1', '134', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2898', '1', '7', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2899', '1', '135', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2900', '1', '8', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2901', '1', '136', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2902', '1', '9', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2903', '1', '137', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2904', '1', '10', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2905', '1', '138', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2906', '1', '11', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2907', '1', '139', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2908', '1', '12', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2909', '1', '140', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2910', '1', '13', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2911', '1', '141', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2912', '1', '14', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2913', '1', '142', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2914', '1', '15', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2915', '1', '143', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2916', '1', '16', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2917', '1', '144', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2918', '1', '17', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2919', '1', '145', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2920', '1', '18', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2921', '1', '146', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2922', '1', '147', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2923', '1', '154', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2924', '1', '56', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2925', '1', '57', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2926', '1', '58', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2927', '1', '59', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2928', '1', '60', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2929', '1', '61', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2930', '1', '62', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2931', '1', '63', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2932', '1', '64', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2933', '1', '65', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2934', '1', '66', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2935', '1', '67', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2936', '1', '123', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2937', '1', '124', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2938', '1', '125', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2939', '1', '126', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2940', '1', '127', '0', 'admin', null, '2020-10-28 14:31:47', '2020-10-28 14:40:05');
INSERT INTO `st_role_authority` VALUES ('2941', '1', '0', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2942', '1', '128', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2943', '1', '1', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2944', '1', '129', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2945', '1', '2', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2946', '1', '130', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2947', '1', '3', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2948', '1', '131', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2949', '1', '4', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2950', '1', '132', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2951', '1', '5', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2952', '1', '133', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2953', '1', '6', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2954', '1', '134', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2955', '1', '7', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2956', '1', '135', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2957', '1', '8', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2958', '1', '136', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2959', '1', '9', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2960', '1', '137', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2961', '1', '10', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2962', '1', '138', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2963', '1', '11', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2964', '1', '139', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2965', '1', '12', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2966', '1', '140', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2967', '1', '13', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2968', '1', '141', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2969', '1', '14', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2970', '1', '142', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2971', '1', '15', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2972', '1', '143', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2973', '1', '16', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2974', '1', '144', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2975', '1', '17', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2976', '1', '145', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2977', '1', '18', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2978', '1', '146', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2979', '1', '147', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2980', '1', '154', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2981', '1', '155', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2982', '1', '56', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2983', '1', '57', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2984', '1', '58', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2985', '1', '59', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2986', '1', '60', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2987', '1', '61', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2988', '1', '62', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2989', '1', '63', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2990', '1', '64', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2991', '1', '65', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2992', '1', '66', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2993', '1', '67', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2994', '1', '123', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2995', '1', '124', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2996', '1', '125', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2997', '1', '126', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2998', '1', '127', '0', 'admin', null, '2020-10-28 14:40:05', '2020-10-29 02:23:22');
INSERT INTO `st_role_authority` VALUES ('2999', '1', '0', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3000', '1', '128', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3001', '1', '1', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3002', '1', '129', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3003', '1', '2', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3004', '1', '130', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3005', '1', '3', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3006', '1', '131', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3007', '1', '4', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3008', '1', '132', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3009', '1', '5', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3010', '1', '133', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3011', '1', '6', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3012', '1', '134', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3013', '1', '7', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3014', '1', '135', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3015', '1', '8', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3016', '1', '136', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3017', '1', '9', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3018', '1', '137', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3019', '1', '10', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3020', '1', '138', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3021', '1', '11', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3022', '1', '139', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3023', '1', '12', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3024', '1', '140', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3025', '1', '13', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3026', '1', '141', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3027', '1', '14', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3028', '1', '142', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3029', '1', '15', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3030', '1', '143', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3031', '1', '16', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3032', '1', '144', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3033', '1', '17', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3034', '1', '145', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3035', '1', '18', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3036', '1', '146', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3037', '1', '147', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3038', '1', '154', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3039', '1', '155', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3040', '1', '56', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3041', '1', '57', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3042', '1', '58', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3043', '1', '59', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3044', '1', '60', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3045', '1', '61', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3046', '1', '62', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3047', '1', '63', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3048', '1', '64', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3049', '1', '65', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3050', '1', '66', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3051', '1', '67', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3052', '1', '107', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3053', '1', '108', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3054', '1', '109', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3055', '1', '110', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3056', '1', '111', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3057', '1', '112', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3058', '1', '113', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3059', '1', '114', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3060', '1', '123', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3061', '1', '124', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3062', '1', '125', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3063', '1', '126', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3064', '1', '127', '0', 'admin', null, '2020-10-29 02:23:22', '2020-10-29 07:54:03');
INSERT INTO `st_role_authority` VALUES ('3065', '1', '0', '0', 'admin', null, '2020-10-29 07:54:03', '2020-10-29 08:17:28');
INSERT INTO `st_role_authority` VALUES ('3066', '1', '64', '0', 'admin', null, '2020-10-29 07:54:03', '2020-10-29 08:17:28');
INSERT INTO `st_role_authority` VALUES ('3067', '1', '1', '0', 'admin', null, '2020-10-29 07:54:03', '2020-10-29 08:17:28');
INSERT INTO `st_role_authority` VALUES ('3068', '1', '65', '0', 'admin', null, '2020-10-29 07:54:03', '2020-10-29 08:17:28');
INSERT INTO `st_role_authority` VALUES ('3069', '1', '2', '0', 'admin', null, '2020-10-29 07:54:03', '2020-10-29 08:17:28');
INSERT INTO `st_role_authority` VALUES ('3070', '1', '66', '0', 'admin', null, '2020-10-29 07:54:03', '2020-10-29 08:17:28');
INSERT INTO `st_role_authority` VALUES ('3071', '1', '3', '0', 'admin', null, '2020-10-29 07:54:03', '2020-10-29 08:17:28');
INSERT INTO `st_role_authority` VALUES ('3072', '1', '67', '0', 'admin', null, '2020-10-29 07:54:03', '2020-10-29 08:17:28');
INSERT INTO `st_role_authority` VALUES ('3073', '1', '4', '0', 'admin', null, '2020-10-29 07:54:03', '2020-10-29 08:17:28');
INSERT INTO `st_role_authority` VALUES ('3074', '1', '5', '0', 'admin', null, '2020-10-29 07:54:03', '2020-10-29 08:17:28');
INSERT INTO `st_role_authority` VALUES ('3075', '1', '6', '0', 'admin', null, '2020-10-29 07:54:03', '2020-10-29 08:17:28');
INSERT INTO `st_role_authority` VALUES ('3076', '1', '7', '0', 'admin', null, '2020-10-29 07:54:03', '2020-10-29 08:17:28');
INSERT INTO `st_role_authority` VALUES ('3077', '1', '8', '0', 'admin', null, '2020-10-29 07:54:03', '2020-10-29 08:17:28');
INSERT INTO `st_role_authority` VALUES ('3078', '1', '9', '0', 'admin', null, '2020-10-29 07:54:03', '2020-10-29 08:17:28');
INSERT INTO `st_role_authority` VALUES ('3079', '1', '10', '0', 'admin', null, '2020-10-29 07:54:03', '2020-10-29 08:17:28');
INSERT INTO `st_role_authority` VALUES ('3080', '1', '11', '0', 'admin', null, '2020-10-29 07:54:03', '2020-10-29 08:17:28');
INSERT INTO `st_role_authority` VALUES ('3081', '1', '12', '0', 'admin', null, '2020-10-29 07:54:03', '2020-10-29 08:17:28');
INSERT INTO `st_role_authority` VALUES ('3082', '1', '13', '0', 'admin', null, '2020-10-29 07:54:03', '2020-10-29 08:17:28');
INSERT INTO `st_role_authority` VALUES ('3083', '1', '14', '0', 'admin', null, '2020-10-29 07:54:03', '2020-10-29 08:17:28');
INSERT INTO `st_role_authority` VALUES ('3084', '1', '15', '0', 'admin', null, '2020-10-29 07:54:03', '2020-10-29 08:17:28');
INSERT INTO `st_role_authority` VALUES ('3085', '1', '16', '0', 'admin', null, '2020-10-29 07:54:03', '2020-10-29 08:17:28');
INSERT INTO `st_role_authority` VALUES ('3086', '1', '17', '0', 'admin', null, '2020-10-29 07:54:03', '2020-10-29 08:17:28');
INSERT INTO `st_role_authority` VALUES ('3087', '1', '18', '0', 'admin', null, '2020-10-29 07:54:03', '2020-10-29 08:17:28');
INSERT INTO `st_role_authority` VALUES ('3088', '1', '156', '0', 'admin', null, '2020-10-29 07:54:03', '2020-10-29 08:17:28');
INSERT INTO `st_role_authority` VALUES ('3089', '1', '157', '0', 'admin', null, '2020-10-29 07:54:03', '2020-10-29 08:17:28');
INSERT INTO `st_role_authority` VALUES ('3090', '1', '158', '0', 'admin', null, '2020-10-29 07:54:03', '2020-10-29 08:17:28');
INSERT INTO `st_role_authority` VALUES ('3091', '1', '159', '0', 'admin', null, '2020-10-29 07:54:03', '2020-10-29 08:17:28');
INSERT INTO `st_role_authority` VALUES ('3092', '1', '160', '0', 'admin', null, '2020-10-29 07:54:03', '2020-10-29 08:17:28');
INSERT INTO `st_role_authority` VALUES ('3093', '1', '56', '0', 'admin', null, '2020-10-29 07:54:03', '2020-10-29 08:17:28');
INSERT INTO `st_role_authority` VALUES ('3094', '1', '57', '0', 'admin', null, '2020-10-29 07:54:03', '2020-10-29 08:17:28');
INSERT INTO `st_role_authority` VALUES ('3095', '1', '58', '0', 'admin', null, '2020-10-29 07:54:03', '2020-10-29 08:17:28');
INSERT INTO `st_role_authority` VALUES ('3096', '1', '59', '0', 'admin', null, '2020-10-29 07:54:03', '2020-10-29 08:17:28');
INSERT INTO `st_role_authority` VALUES ('3097', '1', '60', '0', 'admin', null, '2020-10-29 07:54:03', '2020-10-29 08:17:28');
INSERT INTO `st_role_authority` VALUES ('3098', '1', '61', '0', 'admin', null, '2020-10-29 07:54:03', '2020-10-29 08:17:28');
INSERT INTO `st_role_authority` VALUES ('3099', '1', '62', '0', 'admin', null, '2020-10-29 07:54:03', '2020-10-29 08:17:28');
INSERT INTO `st_role_authority` VALUES ('3100', '1', '63', '0', 'admin', null, '2020-10-29 07:54:03', '2020-10-29 08:17:28');
INSERT INTO `st_role_authority` VALUES ('3101', '1', '0', '0', 'admin', null, '2020-10-29 08:17:28', '2020-10-30 02:10:25');
INSERT INTO `st_role_authority` VALUES ('3102', '1', '1', '0', 'admin', null, '2020-10-29 08:17:28', '2020-10-30 02:10:25');
INSERT INTO `st_role_authority` VALUES ('3103', '1', '2', '0', 'admin', null, '2020-10-29 08:17:28', '2020-10-30 02:10:25');
INSERT INTO `st_role_authority` VALUES ('3104', '1', '66', '0', 'admin', null, '2020-10-29 08:17:28', '2020-10-30 02:10:25');
INSERT INTO `st_role_authority` VALUES ('3105', '1', '3', '0', 'admin', null, '2020-10-29 08:17:28', '2020-10-30 02:10:25');
INSERT INTO `st_role_authority` VALUES ('3106', '1', '67', '0', 'admin', null, '2020-10-29 08:17:28', '2020-10-30 02:10:25');
INSERT INTO `st_role_authority` VALUES ('3107', '1', '4', '0', 'admin', null, '2020-10-29 08:17:28', '2020-10-30 02:10:25');
INSERT INTO `st_role_authority` VALUES ('3108', '1', '68', '0', 'admin', null, '2020-10-29 08:17:28', '2020-10-30 02:10:25');
INSERT INTO `st_role_authority` VALUES ('3109', '1', '5', '0', 'admin', null, '2020-10-29 08:17:28', '2020-10-30 02:10:25');
INSERT INTO `st_role_authority` VALUES ('3110', '1', '69', '0', 'admin', null, '2020-10-29 08:17:28', '2020-10-30 02:10:25');
INSERT INTO `st_role_authority` VALUES ('3111', '1', '6', '0', 'admin', null, '2020-10-29 08:17:28', '2020-10-30 02:10:25');
INSERT INTO `st_role_authority` VALUES ('3112', '1', '70', '0', 'admin', null, '2020-10-29 08:17:28', '2020-10-30 02:10:25');
INSERT INTO `st_role_authority` VALUES ('3113', '1', '7', '0', 'admin', null, '2020-10-29 08:17:28', '2020-10-30 02:10:25');
INSERT INTO `st_role_authority` VALUES ('3114', '1', '71', '0', 'admin', null, '2020-10-29 08:17:28', '2020-10-30 02:10:25');
INSERT INTO `st_role_authority` VALUES ('3115', '1', '8', '0', 'admin', null, '2020-10-29 08:17:28', '2020-10-30 02:10:25');
INSERT INTO `st_role_authority` VALUES ('3116', '1', '9', '0', 'admin', null, '2020-10-29 08:17:28', '2020-10-30 02:10:25');
INSERT INTO `st_role_authority` VALUES ('3117', '1', '10', '0', 'admin', null, '2020-10-29 08:17:28', '2020-10-30 02:10:25');
INSERT INTO `st_role_authority` VALUES ('3118', '1', '11', '0', 'admin', null, '2020-10-29 08:17:28', '2020-10-30 02:10:25');
INSERT INTO `st_role_authority` VALUES ('3119', '1', '12', '0', 'admin', null, '2020-10-29 08:17:28', '2020-10-30 02:10:25');
INSERT INTO `st_role_authority` VALUES ('3120', '1', '13', '0', 'admin', null, '2020-10-29 08:17:28', '2020-10-30 02:10:25');
INSERT INTO `st_role_authority` VALUES ('3121', '1', '14', '0', 'admin', null, '2020-10-29 08:17:28', '2020-10-30 02:10:25');
INSERT INTO `st_role_authority` VALUES ('3122', '1', '15', '0', 'admin', null, '2020-10-29 08:17:28', '2020-10-30 02:10:25');
INSERT INTO `st_role_authority` VALUES ('3123', '1', '16', '0', 'admin', null, '2020-10-29 08:17:28', '2020-10-30 02:10:25');
INSERT INTO `st_role_authority` VALUES ('3124', '1', '17', '0', 'admin', null, '2020-10-29 08:17:28', '2020-10-30 02:10:25');
INSERT INTO `st_role_authority` VALUES ('3125', '1', '18', '0', 'admin', null, '2020-10-29 08:17:28', '2020-10-30 02:10:25');
INSERT INTO `st_role_authority` VALUES ('3126', '1', '156', '0', 'admin', null, '2020-10-29 08:17:28', '2020-10-30 02:10:25');
INSERT INTO `st_role_authority` VALUES ('3127', '1', '157', '0', 'admin', null, '2020-10-29 08:17:28', '2020-10-30 02:10:25');
INSERT INTO `st_role_authority` VALUES ('3128', '1', '158', '0', 'admin', null, '2020-10-29 08:17:28', '2020-10-30 02:10:25');
INSERT INTO `st_role_authority` VALUES ('3129', '1', '159', '0', 'admin', null, '2020-10-29 08:17:28', '2020-10-30 02:10:25');
INSERT INTO `st_role_authority` VALUES ('3130', '1', '160', '0', 'admin', null, '2020-10-29 08:17:28', '2020-10-30 02:10:25');
INSERT INTO `st_role_authority` VALUES ('3131', '1', '56', '0', 'admin', null, '2020-10-29 08:17:28', '2020-10-30 02:10:25');
INSERT INTO `st_role_authority` VALUES ('3132', '1', '57', '0', 'admin', null, '2020-10-29 08:17:28', '2020-10-30 02:10:25');
INSERT INTO `st_role_authority` VALUES ('3133', '1', '58', '0', 'admin', null, '2020-10-29 08:17:28', '2020-10-30 02:10:25');
INSERT INTO `st_role_authority` VALUES ('3134', '1', '59', '0', 'admin', null, '2020-10-29 08:17:28', '2020-10-30 02:10:25');
INSERT INTO `st_role_authority` VALUES ('3135', '1', '60', '0', 'admin', null, '2020-10-29 08:17:28', '2020-10-30 02:10:25');
INSERT INTO `st_role_authority` VALUES ('3136', '1', '61', '0', 'admin', null, '2020-10-29 08:17:28', '2020-10-30 02:10:25');
INSERT INTO `st_role_authority` VALUES ('3137', '1', '0', '0', 'admin', null, '2020-10-30 02:10:25', '2020-10-30 09:35:09');
INSERT INTO `st_role_authority` VALUES ('3138', '1', '1', '0', 'admin', null, '2020-10-30 02:10:25', '2020-10-30 09:35:09');
INSERT INTO `st_role_authority` VALUES ('3139', '1', '2', '0', 'admin', null, '2020-10-30 02:10:25', '2020-10-30 09:35:09');
INSERT INTO `st_role_authority` VALUES ('3140', '1', '66', '0', 'admin', null, '2020-10-30 02:10:25', '2020-10-30 09:35:09');
INSERT INTO `st_role_authority` VALUES ('3141', '1', '3', '0', 'admin', null, '2020-10-30 02:10:25', '2020-10-30 09:35:09');
INSERT INTO `st_role_authority` VALUES ('3142', '1', '67', '0', 'admin', null, '2020-10-30 02:10:25', '2020-10-30 09:35:09');
INSERT INTO `st_role_authority` VALUES ('3143', '1', '4', '0', 'admin', null, '2020-10-30 02:10:25', '2020-10-30 09:35:09');
INSERT INTO `st_role_authority` VALUES ('3144', '1', '68', '0', 'admin', null, '2020-10-30 02:10:25', '2020-10-30 09:35:09');
INSERT INTO `st_role_authority` VALUES ('3145', '1', '5', '0', 'admin', null, '2020-10-30 02:10:25', '2020-10-30 09:35:09');
INSERT INTO `st_role_authority` VALUES ('3146', '1', '69', '0', 'admin', null, '2020-10-30 02:10:25', '2020-10-30 09:35:09');
INSERT INTO `st_role_authority` VALUES ('3147', '1', '6', '0', 'admin', null, '2020-10-30 02:10:25', '2020-10-30 09:35:09');
INSERT INTO `st_role_authority` VALUES ('3148', '1', '70', '0', 'admin', null, '2020-10-30 02:10:25', '2020-10-30 09:35:09');
INSERT INTO `st_role_authority` VALUES ('3149', '1', '7', '0', 'admin', null, '2020-10-30 02:10:25', '2020-10-30 09:35:09');
INSERT INTO `st_role_authority` VALUES ('3150', '1', '71', '0', 'admin', null, '2020-10-30 02:10:25', '2020-10-30 09:35:09');
INSERT INTO `st_role_authority` VALUES ('3151', '1', '8', '0', 'admin', null, '2020-10-30 02:10:25', '2020-10-30 09:35:09');
INSERT INTO `st_role_authority` VALUES ('3152', '1', '9', '0', 'admin', null, '2020-10-30 02:10:25', '2020-10-30 09:35:09');
INSERT INTO `st_role_authority` VALUES ('3153', '1', '10', '0', 'admin', null, '2020-10-30 02:10:25', '2020-10-30 09:35:09');
INSERT INTO `st_role_authority` VALUES ('3154', '1', '11', '0', 'admin', null, '2020-10-30 02:10:25', '2020-10-30 09:35:09');
INSERT INTO `st_role_authority` VALUES ('3155', '1', '12', '0', 'admin', null, '2020-10-30 02:10:25', '2020-10-30 09:35:09');
INSERT INTO `st_role_authority` VALUES ('3156', '1', '13', '0', 'admin', null, '2020-10-30 02:10:25', '2020-10-30 09:35:09');
INSERT INTO `st_role_authority` VALUES ('3157', '1', '14', '0', 'admin', null, '2020-10-30 02:10:25', '2020-10-30 09:35:09');
INSERT INTO `st_role_authority` VALUES ('3158', '1', '15', '0', 'admin', null, '2020-10-30 02:10:25', '2020-10-30 09:35:09');
INSERT INTO `st_role_authority` VALUES ('3159', '1', '16', '0', 'admin', null, '2020-10-30 02:10:25', '2020-10-30 09:35:09');
INSERT INTO `st_role_authority` VALUES ('3160', '1', '17', '0', 'admin', null, '2020-10-30 02:10:25', '2020-10-30 09:35:09');
INSERT INTO `st_role_authority` VALUES ('3161', '1', '18', '0', 'admin', null, '2020-10-30 02:10:25', '2020-10-30 09:35:09');
INSERT INTO `st_role_authority` VALUES ('3162', '1', '156', '0', 'admin', null, '2020-10-30 02:10:25', '2020-10-30 09:35:09');
INSERT INTO `st_role_authority` VALUES ('3163', '1', '157', '0', 'admin', null, '2020-10-30 02:10:25', '2020-10-30 09:35:09');
INSERT INTO `st_role_authority` VALUES ('3164', '1', '158', '0', 'admin', null, '2020-10-30 02:10:25', '2020-10-30 09:35:09');
INSERT INTO `st_role_authority` VALUES ('3165', '1', '159', '0', 'admin', null, '2020-10-30 02:10:25', '2020-10-30 09:35:09');
INSERT INTO `st_role_authority` VALUES ('3166', '1', '160', '0', 'admin', null, '2020-10-30 02:10:25', '2020-10-30 09:35:09');
INSERT INTO `st_role_authority` VALUES ('3167', '1', '161', '0', 'admin', null, '2020-10-30 02:10:25', '2020-10-30 09:35:09');
INSERT INTO `st_role_authority` VALUES ('3168', '1', '162', '0', 'admin', null, '2020-10-30 02:10:25', '2020-10-30 09:35:09');
INSERT INTO `st_role_authority` VALUES ('3169', '1', '163', '0', 'admin', null, '2020-10-30 02:10:25', '2020-10-30 09:35:09');
INSERT INTO `st_role_authority` VALUES ('3170', '1', '164', '0', 'admin', null, '2020-10-30 02:10:25', '2020-10-30 09:35:09');
INSERT INTO `st_role_authority` VALUES ('3171', '1', '165', '0', 'admin', null, '2020-10-30 02:10:25', '2020-10-30 09:35:09');
INSERT INTO `st_role_authority` VALUES ('3172', '1', '166', '0', 'admin', null, '2020-10-30 02:10:25', '2020-10-30 09:35:09');
INSERT INTO `st_role_authority` VALUES ('3173', '1', '56', '0', 'admin', null, '2020-10-30 02:10:25', '2020-10-30 09:35:09');
INSERT INTO `st_role_authority` VALUES ('3174', '1', '57', '0', 'admin', null, '2020-10-30 02:10:25', '2020-10-30 09:35:09');
INSERT INTO `st_role_authority` VALUES ('3175', '1', '58', '0', 'admin', null, '2020-10-30 02:10:25', '2020-10-30 09:35:09');
INSERT INTO `st_role_authority` VALUES ('3176', '1', '59', '0', 'admin', null, '2020-10-30 02:10:25', '2020-10-30 09:35:09');
INSERT INTO `st_role_authority` VALUES ('3177', '1', '60', '0', 'admin', null, '2020-10-30 02:10:25', '2020-10-30 09:35:09');
INSERT INTO `st_role_authority` VALUES ('3178', '1', '61', '0', 'admin', null, '2020-10-30 02:10:25', '2020-10-30 09:35:09');
INSERT INTO `st_role_authority` VALUES ('3179', '1', '0', '0', 'admin', null, '2020-10-30 09:35:09', '2020-11-26 15:33:47');
INSERT INTO `st_role_authority` VALUES ('3180', '1', '1', '0', 'admin', null, '2020-10-30 09:35:09', '2020-11-26 15:33:47');
INSERT INTO `st_role_authority` VALUES ('3181', '1', '2', '0', 'admin', null, '2020-10-30 09:35:09', '2020-11-26 15:33:47');
INSERT INTO `st_role_authority` VALUES ('3182', '1', '66', '0', 'admin', null, '2020-10-30 09:35:09', '2020-11-26 15:33:47');
INSERT INTO `st_role_authority` VALUES ('3183', '1', '3', '0', 'admin', null, '2020-10-30 09:35:09', '2020-11-26 15:33:47');
INSERT INTO `st_role_authority` VALUES ('3184', '1', '67', '0', 'admin', null, '2020-10-30 09:35:09', '2020-11-26 15:33:47');
INSERT INTO `st_role_authority` VALUES ('3185', '1', '4', '0', 'admin', null, '2020-10-30 09:35:09', '2020-11-26 15:33:47');
INSERT INTO `st_role_authority` VALUES ('3186', '1', '5', '0', 'admin', null, '2020-10-30 09:35:09', '2020-11-26 15:33:47');
INSERT INTO `st_role_authority` VALUES ('3187', '1', '6', '0', 'admin', null, '2020-10-30 09:35:09', '2020-11-26 15:33:47');
INSERT INTO `st_role_authority` VALUES ('3188', '1', '7', '0', 'admin', null, '2020-10-30 09:35:09', '2020-11-26 15:33:47');
INSERT INTO `st_role_authority` VALUES ('3189', '1', '8', '0', 'admin', null, '2020-10-30 09:35:09', '2020-11-26 15:33:47');
INSERT INTO `st_role_authority` VALUES ('3190', '1', '9', '0', 'admin', null, '2020-10-30 09:35:09', '2020-11-26 15:33:47');
INSERT INTO `st_role_authority` VALUES ('3191', '1', '10', '0', 'admin', null, '2020-10-30 09:35:09', '2020-11-26 15:33:47');
INSERT INTO `st_role_authority` VALUES ('3192', '1', '11', '0', 'admin', null, '2020-10-30 09:35:09', '2020-11-26 15:33:47');
INSERT INTO `st_role_authority` VALUES ('3193', '1', '12', '0', 'admin', null, '2020-10-30 09:35:09', '2020-11-26 15:33:47');
INSERT INTO `st_role_authority` VALUES ('3194', '1', '13', '0', 'admin', null, '2020-10-30 09:35:09', '2020-11-26 15:33:47');
INSERT INTO `st_role_authority` VALUES ('3195', '1', '14', '0', 'admin', null, '2020-10-30 09:35:09', '2020-11-26 15:33:47');
INSERT INTO `st_role_authority` VALUES ('3196', '1', '15', '0', 'admin', null, '2020-10-30 09:35:09', '2020-11-26 15:33:47');
INSERT INTO `st_role_authority` VALUES ('3197', '1', '16', '0', 'admin', null, '2020-10-30 09:35:09', '2020-11-26 15:33:47');
INSERT INTO `st_role_authority` VALUES ('3198', '1', '17', '0', 'admin', null, '2020-10-30 09:35:09', '2020-11-26 15:33:47');
INSERT INTO `st_role_authority` VALUES ('3199', '1', '18', '0', 'admin', null, '2020-10-30 09:35:09', '2020-11-26 15:33:47');
INSERT INTO `st_role_authority` VALUES ('3200', '1', '161', '0', 'admin', null, '2020-10-30 09:35:09', '2020-11-26 15:33:47');
INSERT INTO `st_role_authority` VALUES ('3201', '1', '162', '0', 'admin', null, '2020-10-30 09:35:09', '2020-11-26 15:33:47');
INSERT INTO `st_role_authority` VALUES ('3202', '1', '163', '0', 'admin', null, '2020-10-30 09:35:09', '2020-11-26 15:33:47');
INSERT INTO `st_role_authority` VALUES ('3203', '1', '164', '0', 'admin', null, '2020-10-30 09:35:09', '2020-11-26 15:33:47');
INSERT INTO `st_role_authority` VALUES ('3204', '1', '165', '0', 'admin', null, '2020-10-30 09:35:09', '2020-11-26 15:33:47');
INSERT INTO `st_role_authority` VALUES ('3205', '1', '166', '0', 'admin', null, '2020-10-30 09:35:09', '2020-11-26 15:33:47');
INSERT INTO `st_role_authority` VALUES ('3206', '1', '167', '0', 'admin', null, '2020-10-30 09:35:09', '2020-11-26 15:33:47');
INSERT INTO `st_role_authority` VALUES ('3207', '1', '168', '0', 'admin', null, '2020-10-30 09:35:09', '2020-11-26 15:33:47');
INSERT INTO `st_role_authority` VALUES ('3208', '1', '169', '0', 'admin', null, '2020-10-30 09:35:09', '2020-11-26 15:33:47');
INSERT INTO `st_role_authority` VALUES ('3209', '1', '170', '0', 'admin', null, '2020-10-30 09:35:09', '2020-11-26 15:33:47');
INSERT INTO `st_role_authority` VALUES ('3210', '1', '171', '0', 'admin', null, '2020-10-30 09:35:09', '2020-11-26 15:33:47');
INSERT INTO `st_role_authority` VALUES ('3211', '1', '172', '0', 'admin', null, '2020-10-30 09:35:09', '2020-11-26 15:33:47');
INSERT INTO `st_role_authority` VALUES ('3212', '1', '56', '0', 'admin', null, '2020-10-30 09:35:09', '2020-11-26 15:33:47');
INSERT INTO `st_role_authority` VALUES ('3213', '1', '57', '0', 'admin', null, '2020-10-30 09:35:09', '2020-11-26 15:33:47');
INSERT INTO `st_role_authority` VALUES ('3214', '1', '58', '0', 'admin', null, '2020-10-30 09:35:09', '2020-11-26 15:33:47');
INSERT INTO `st_role_authority` VALUES ('3215', '1', '59', '0', 'admin', null, '2020-10-30 09:35:09', '2020-11-26 15:33:47');
INSERT INTO `st_role_authority` VALUES ('3216', '1', '60', '0', 'admin', null, '2020-10-30 09:35:09', '2020-11-26 15:33:47');
INSERT INTO `st_role_authority` VALUES ('3217', '1', '61', '0', 'admin', null, '2020-10-30 09:35:09', '2020-11-26 15:33:47');
INSERT INTO `st_role_authority` VALUES ('3218', '1', '0', '0', 'admin', null, '2020-11-26 15:33:47', '2020-11-26 15:46:00');
INSERT INTO `st_role_authority` VALUES ('3219', '1', '1', '0', 'admin', null, '2020-11-26 15:33:47', '2020-11-26 15:46:00');
INSERT INTO `st_role_authority` VALUES ('3220', '1', '2', '0', 'admin', null, '2020-11-26 15:33:47', '2020-11-26 15:46:00');
INSERT INTO `st_role_authority` VALUES ('3221', '1', '66', '0', 'admin', null, '2020-11-26 15:33:47', '2020-11-26 15:46:00');
INSERT INTO `st_role_authority` VALUES ('3222', '1', '3', '0', 'admin', null, '2020-11-26 15:33:47', '2020-11-26 15:46:00');
INSERT INTO `st_role_authority` VALUES ('3223', '1', '67', '0', 'admin', null, '2020-11-26 15:33:47', '2020-11-26 15:46:00');
INSERT INTO `st_role_authority` VALUES ('3224', '1', '4', '0', 'admin', null, '2020-11-26 15:33:47', '2020-11-26 15:46:00');
INSERT INTO `st_role_authority` VALUES ('3225', '1', '5', '0', 'admin', null, '2020-11-26 15:33:47', '2020-11-26 15:46:00');
INSERT INTO `st_role_authority` VALUES ('3226', '1', '6', '0', 'admin', null, '2020-11-26 15:33:47', '2020-11-26 15:46:00');
INSERT INTO `st_role_authority` VALUES ('3227', '1', '7', '0', 'admin', null, '2020-11-26 15:33:47', '2020-11-26 15:46:00');
INSERT INTO `st_role_authority` VALUES ('3228', '1', '8', '0', 'admin', null, '2020-11-26 15:33:47', '2020-11-26 15:46:00');
INSERT INTO `st_role_authority` VALUES ('3229', '1', '9', '0', 'admin', null, '2020-11-26 15:33:47', '2020-11-26 15:46:00');
INSERT INTO `st_role_authority` VALUES ('3230', '1', '10', '0', 'admin', null, '2020-11-26 15:33:47', '2020-11-26 15:46:00');
INSERT INTO `st_role_authority` VALUES ('3231', '1', '11', '0', 'admin', null, '2020-11-26 15:33:47', '2020-11-26 15:46:00');
INSERT INTO `st_role_authority` VALUES ('3232', '1', '12', '0', 'admin', null, '2020-11-26 15:33:47', '2020-11-26 15:46:00');
INSERT INTO `st_role_authority` VALUES ('3233', '1', '13', '0', 'admin', null, '2020-11-26 15:33:47', '2020-11-26 15:46:00');
INSERT INTO `st_role_authority` VALUES ('3234', '1', '14', '0', 'admin', null, '2020-11-26 15:33:47', '2020-11-26 15:46:00');
INSERT INTO `st_role_authority` VALUES ('3235', '1', '15', '0', 'admin', null, '2020-11-26 15:33:47', '2020-11-26 15:46:00');
INSERT INTO `st_role_authority` VALUES ('3236', '1', '16', '0', 'admin', null, '2020-11-26 15:33:47', '2020-11-26 15:46:00');
INSERT INTO `st_role_authority` VALUES ('3237', '1', '17', '0', 'admin', null, '2020-11-26 15:33:47', '2020-11-26 15:46:00');
INSERT INTO `st_role_authority` VALUES ('3238', '1', '18', '0', 'admin', null, '2020-11-26 15:33:47', '2020-11-26 15:46:00');
INSERT INTO `st_role_authority` VALUES ('3239', '1', '56', '0', 'admin', null, '2020-11-26 15:33:47', '2020-11-26 15:46:00');
INSERT INTO `st_role_authority` VALUES ('3240', '1', '57', '0', 'admin', null, '2020-11-26 15:33:47', '2020-11-26 15:46:00');
INSERT INTO `st_role_authority` VALUES ('3241', '1', '58', '0', 'admin', null, '2020-11-26 15:33:47', '2020-11-26 15:46:00');
INSERT INTO `st_role_authority` VALUES ('3242', '1', '59', '0', 'admin', null, '2020-11-26 15:33:47', '2020-11-26 15:46:00');
INSERT INTO `st_role_authority` VALUES ('3243', '1', '60', '0', 'admin', null, '2020-11-26 15:33:47', '2020-11-26 15:46:00');
INSERT INTO `st_role_authority` VALUES ('3244', '1', '61', '0', 'admin', null, '2020-11-26 15:33:47', '2020-11-26 15:46:00');
INSERT INTO `st_role_authority` VALUES ('3245', '1', '0', '0', 'admin', null, '2020-11-26 15:46:00', '2020-12-02 15:11:51');
INSERT INTO `st_role_authority` VALUES ('3246', '1', '1', '0', 'admin', null, '2020-11-26 15:46:00', '2020-12-02 15:11:51');
INSERT INTO `st_role_authority` VALUES ('3247', '1', '2', '0', 'admin', null, '2020-11-26 15:46:00', '2020-12-02 15:11:51');
INSERT INTO `st_role_authority` VALUES ('3248', '1', '66', '0', 'admin', null, '2020-11-26 15:46:00', '2020-12-02 15:11:51');
INSERT INTO `st_role_authority` VALUES ('3249', '1', '3', '0', 'admin', null, '2020-11-26 15:46:00', '2020-12-02 15:11:51');
INSERT INTO `st_role_authority` VALUES ('3250', '1', '67', '0', 'admin', null, '2020-11-26 15:46:00', '2020-12-02 15:11:51');
INSERT INTO `st_role_authority` VALUES ('3251', '1', '4', '0', 'admin', null, '2020-11-26 15:46:00', '2020-12-02 15:11:51');
INSERT INTO `st_role_authority` VALUES ('3252', '1', '5', '0', 'admin', null, '2020-11-26 15:46:00', '2020-12-02 15:11:51');
INSERT INTO `st_role_authority` VALUES ('3253', '1', '6', '0', 'admin', null, '2020-11-26 15:46:00', '2020-12-02 15:11:51');
INSERT INTO `st_role_authority` VALUES ('3254', '1', '7', '0', 'admin', null, '2020-11-26 15:46:00', '2020-12-02 15:11:51');
INSERT INTO `st_role_authority` VALUES ('3255', '1', '8', '0', 'admin', null, '2020-11-26 15:46:00', '2020-12-02 15:11:51');
INSERT INTO `st_role_authority` VALUES ('3256', '1', '9', '0', 'admin', null, '2020-11-26 15:46:00', '2020-12-02 15:11:51');
INSERT INTO `st_role_authority` VALUES ('3257', '1', '10', '0', 'admin', null, '2020-11-26 15:46:00', '2020-12-02 15:11:51');
INSERT INTO `st_role_authority` VALUES ('3258', '1', '11', '0', 'admin', null, '2020-11-26 15:46:00', '2020-12-02 15:11:51');
INSERT INTO `st_role_authority` VALUES ('3259', '1', '12', '0', 'admin', null, '2020-11-26 15:46:00', '2020-12-02 15:11:51');
INSERT INTO `st_role_authority` VALUES ('3260', '1', '13', '0', 'admin', null, '2020-11-26 15:46:00', '2020-12-02 15:11:51');
INSERT INTO `st_role_authority` VALUES ('3261', '1', '14', '0', 'admin', null, '2020-11-26 15:46:00', '2020-12-02 15:11:51');
INSERT INTO `st_role_authority` VALUES ('3262', '1', '15', '0', 'admin', null, '2020-11-26 15:46:00', '2020-12-02 15:11:51');
INSERT INTO `st_role_authority` VALUES ('3263', '1', '16', '0', 'admin', null, '2020-11-26 15:46:00', '2020-12-02 15:11:51');
INSERT INTO `st_role_authority` VALUES ('3264', '1', '17', '0', 'admin', null, '2020-11-26 15:46:00', '2020-12-02 15:11:51');
INSERT INTO `st_role_authority` VALUES ('3265', '1', '18', '0', 'admin', null, '2020-11-26 15:46:00', '2020-12-02 15:11:51');
INSERT INTO `st_role_authority` VALUES ('3266', '1', '173', '0', 'admin', null, '2020-11-26 15:46:00', '2020-12-02 15:11:51');
INSERT INTO `st_role_authority` VALUES ('3267', '1', '174', '0', 'admin', null, '2020-11-26 15:46:00', '2020-12-02 15:11:51');
INSERT INTO `st_role_authority` VALUES ('3268', '1', '175', '0', 'admin', null, '2020-11-26 15:46:00', '2020-12-02 15:11:51');
INSERT INTO `st_role_authority` VALUES ('3269', '1', '176', '0', 'admin', null, '2020-11-26 15:46:00', '2020-12-02 15:11:51');
INSERT INTO `st_role_authority` VALUES ('3270', '1', '177', '0', 'admin', null, '2020-11-26 15:46:00', '2020-12-02 15:11:51');
INSERT INTO `st_role_authority` VALUES ('3271', '1', '178', '0', 'admin', null, '2020-11-26 15:46:00', '2020-12-02 15:11:51');
INSERT INTO `st_role_authority` VALUES ('3272', '1', '56', '0', 'admin', null, '2020-11-26 15:46:00', '2020-12-02 15:11:51');
INSERT INTO `st_role_authority` VALUES ('3273', '1', '57', '0', 'admin', null, '2020-11-26 15:46:00', '2020-12-02 15:11:51');
INSERT INTO `st_role_authority` VALUES ('3274', '1', '58', '0', 'admin', null, '2020-11-26 15:46:00', '2020-12-02 15:11:51');
INSERT INTO `st_role_authority` VALUES ('3275', '1', '59', '0', 'admin', null, '2020-11-26 15:46:00', '2020-12-02 15:11:51');
INSERT INTO `st_role_authority` VALUES ('3276', '1', '60', '0', 'admin', null, '2020-11-26 15:46:00', '2020-12-02 15:11:51');
INSERT INTO `st_role_authority` VALUES ('3277', '1', '61', '0', 'admin', null, '2020-11-26 15:46:00', '2020-12-02 15:11:51');
INSERT INTO `st_role_authority` VALUES ('3278', '1', '0', '0', 'admin', null, '2020-12-02 15:11:51', '2020-12-02 15:22:38');
INSERT INTO `st_role_authority` VALUES ('3279', '1', '1', '0', 'admin', null, '2020-12-02 15:11:51', '2020-12-02 15:22:38');
INSERT INTO `st_role_authority` VALUES ('3280', '1', '2', '0', 'admin', null, '2020-12-02 15:11:51', '2020-12-02 15:22:38');
INSERT INTO `st_role_authority` VALUES ('3281', '1', '66', '0', 'admin', null, '2020-12-02 15:11:51', '2020-12-02 15:22:38');
INSERT INTO `st_role_authority` VALUES ('3282', '1', '3', '0', 'admin', null, '2020-12-02 15:11:51', '2020-12-02 15:22:38');
INSERT INTO `st_role_authority` VALUES ('3283', '1', '67', '0', 'admin', null, '2020-12-02 15:11:51', '2020-12-02 15:22:38');
INSERT INTO `st_role_authority` VALUES ('3284', '1', '4', '0', 'admin', null, '2020-12-02 15:11:51', '2020-12-02 15:22:38');
INSERT INTO `st_role_authority` VALUES ('3285', '1', '5', '0', 'admin', null, '2020-12-02 15:11:51', '2020-12-02 15:22:38');
INSERT INTO `st_role_authority` VALUES ('3286', '1', '6', '0', 'admin', null, '2020-12-02 15:11:51', '2020-12-02 15:22:38');
INSERT INTO `st_role_authority` VALUES ('3287', '1', '7', '0', 'admin', null, '2020-12-02 15:11:51', '2020-12-02 15:22:38');
INSERT INTO `st_role_authority` VALUES ('3288', '1', '8', '0', 'admin', null, '2020-12-02 15:11:51', '2020-12-02 15:22:38');
INSERT INTO `st_role_authority` VALUES ('3289', '1', '9', '0', 'admin', null, '2020-12-02 15:11:51', '2020-12-02 15:22:38');
INSERT INTO `st_role_authority` VALUES ('3290', '1', '10', '0', 'admin', null, '2020-12-02 15:11:51', '2020-12-02 15:22:38');
INSERT INTO `st_role_authority` VALUES ('3291', '1', '11', '0', 'admin', null, '2020-12-02 15:11:51', '2020-12-02 15:22:38');
INSERT INTO `st_role_authority` VALUES ('3292', '1', '12', '0', 'admin', null, '2020-12-02 15:11:51', '2020-12-02 15:22:38');
INSERT INTO `st_role_authority` VALUES ('3293', '1', '13', '0', 'admin', null, '2020-12-02 15:11:51', '2020-12-02 15:22:38');
INSERT INTO `st_role_authority` VALUES ('3294', '1', '14', '0', 'admin', null, '2020-12-02 15:11:51', '2020-12-02 15:22:38');
INSERT INTO `st_role_authority` VALUES ('3295', '1', '15', '0', 'admin', null, '2020-12-02 15:11:51', '2020-12-02 15:22:38');
INSERT INTO `st_role_authority` VALUES ('3296', '1', '16', '0', 'admin', null, '2020-12-02 15:11:51', '2020-12-02 15:22:38');
INSERT INTO `st_role_authority` VALUES ('3297', '1', '17', '0', 'admin', null, '2020-12-02 15:11:51', '2020-12-02 15:22:38');
INSERT INTO `st_role_authority` VALUES ('3298', '1', '18', '0', 'admin', null, '2020-12-02 15:11:51', '2020-12-02 15:22:38');
INSERT INTO `st_role_authority` VALUES ('3299', '1', '173', '0', 'admin', null, '2020-12-02 15:11:51', '2020-12-02 15:22:38');
INSERT INTO `st_role_authority` VALUES ('3300', '1', '174', '0', 'admin', null, '2020-12-02 15:11:51', '2020-12-02 15:22:38');
INSERT INTO `st_role_authority` VALUES ('3301', '1', '175', '0', 'admin', null, '2020-12-02 15:11:51', '2020-12-02 15:22:38');
INSERT INTO `st_role_authority` VALUES ('3302', '1', '176', '0', 'admin', null, '2020-12-02 15:11:51', '2020-12-02 15:22:38');
INSERT INTO `st_role_authority` VALUES ('3303', '1', '177', '0', 'admin', null, '2020-12-02 15:11:51', '2020-12-02 15:22:38');
INSERT INTO `st_role_authority` VALUES ('3304', '1', '178', '0', 'admin', null, '2020-12-02 15:11:51', '2020-12-02 15:22:38');
INSERT INTO `st_role_authority` VALUES ('3305', '1', '179', '0', 'admin', null, '2020-12-02 15:11:51', '2020-12-02 15:22:38');
INSERT INTO `st_role_authority` VALUES ('3306', '1', '180', '0', 'admin', null, '2020-12-02 15:11:51', '2020-12-02 15:22:38');
INSERT INTO `st_role_authority` VALUES ('3307', '1', '56', '0', 'admin', null, '2020-12-02 15:11:51', '2020-12-02 15:22:38');
INSERT INTO `st_role_authority` VALUES ('3308', '1', '57', '0', 'admin', null, '2020-12-02 15:11:51', '2020-12-02 15:22:38');
INSERT INTO `st_role_authority` VALUES ('3309', '1', '58', '0', 'admin', null, '2020-12-02 15:11:51', '2020-12-02 15:22:38');
INSERT INTO `st_role_authority` VALUES ('3310', '1', '59', '0', 'admin', null, '2020-12-02 15:11:51', '2020-12-02 15:22:38');
INSERT INTO `st_role_authority` VALUES ('3311', '1', '60', '0', 'admin', null, '2020-12-02 15:11:51', '2020-12-02 15:22:38');
INSERT INTO `st_role_authority` VALUES ('3312', '1', '61', '0', 'admin', null, '2020-12-02 15:11:51', '2020-12-02 15:22:38');
INSERT INTO `st_role_authority` VALUES ('3313', '1', '0', '0', 'admin', null, '2020-12-02 15:22:38', '2020-12-09 15:34:59');
INSERT INTO `st_role_authority` VALUES ('3314', '1', '1', '0', 'admin', null, '2020-12-02 15:22:38', '2020-12-09 15:34:59');
INSERT INTO `st_role_authority` VALUES ('3315', '1', '2', '0', 'admin', null, '2020-12-02 15:22:38', '2020-12-09 15:34:59');
INSERT INTO `st_role_authority` VALUES ('3316', '1', '66', '0', 'admin', null, '2020-12-02 15:22:38', '2020-12-09 15:34:59');
INSERT INTO `st_role_authority` VALUES ('3317', '1', '3', '0', 'admin', null, '2020-12-02 15:22:38', '2020-12-09 15:34:59');
INSERT INTO `st_role_authority` VALUES ('3318', '1', '67', '0', 'admin', null, '2020-12-02 15:22:38', '2020-12-09 15:34:59');
INSERT INTO `st_role_authority` VALUES ('3319', '1', '4', '0', 'admin', null, '2020-12-02 15:22:38', '2020-12-09 15:34:59');
INSERT INTO `st_role_authority` VALUES ('3320', '1', '5', '0', 'admin', null, '2020-12-02 15:22:38', '2020-12-09 15:34:59');
INSERT INTO `st_role_authority` VALUES ('3321', '1', '6', '0', 'admin', null, '2020-12-02 15:22:38', '2020-12-09 15:34:59');
INSERT INTO `st_role_authority` VALUES ('3322', '1', '7', '0', 'admin', null, '2020-12-02 15:22:38', '2020-12-09 15:34:59');
INSERT INTO `st_role_authority` VALUES ('3323', '1', '8', '0', 'admin', null, '2020-12-02 15:22:38', '2020-12-09 15:34:59');
INSERT INTO `st_role_authority` VALUES ('3324', '1', '9', '0', 'admin', null, '2020-12-02 15:22:38', '2020-12-09 15:34:59');
INSERT INTO `st_role_authority` VALUES ('3325', '1', '10', '0', 'admin', null, '2020-12-02 15:22:38', '2020-12-09 15:34:59');
INSERT INTO `st_role_authority` VALUES ('3326', '1', '11', '0', 'admin', null, '2020-12-02 15:22:38', '2020-12-09 15:34:59');
INSERT INTO `st_role_authority` VALUES ('3327', '1', '12', '0', 'admin', null, '2020-12-02 15:22:38', '2020-12-09 15:34:59');
INSERT INTO `st_role_authority` VALUES ('3328', '1', '13', '0', 'admin', null, '2020-12-02 15:22:38', '2020-12-09 15:34:59');
INSERT INTO `st_role_authority` VALUES ('3329', '1', '14', '0', 'admin', null, '2020-12-02 15:22:38', '2020-12-09 15:34:59');
INSERT INTO `st_role_authority` VALUES ('3330', '1', '15', '0', 'admin', null, '2020-12-02 15:22:38', '2020-12-09 15:34:59');
INSERT INTO `st_role_authority` VALUES ('3331', '1', '16', '0', 'admin', null, '2020-12-02 15:22:38', '2020-12-09 15:34:59');
INSERT INTO `st_role_authority` VALUES ('3332', '1', '17', '0', 'admin', null, '2020-12-02 15:22:38', '2020-12-09 15:34:59');
INSERT INTO `st_role_authority` VALUES ('3333', '1', '18', '0', 'admin', null, '2020-12-02 15:22:38', '2020-12-09 15:34:59');
INSERT INTO `st_role_authority` VALUES ('3334', '1', '173', '0', 'admin', null, '2020-12-02 15:22:38', '2020-12-09 15:34:59');
INSERT INTO `st_role_authority` VALUES ('3335', '1', '174', '0', 'admin', null, '2020-12-02 15:22:38', '2020-12-09 15:34:59');
INSERT INTO `st_role_authority` VALUES ('3336', '1', '175', '0', 'admin', null, '2020-12-02 15:22:38', '2020-12-09 15:34:59');
INSERT INTO `st_role_authority` VALUES ('3337', '1', '176', '0', 'admin', null, '2020-12-02 15:22:38', '2020-12-09 15:34:59');
INSERT INTO `st_role_authority` VALUES ('3338', '1', '177', '0', 'admin', null, '2020-12-02 15:22:38', '2020-12-09 15:34:59');
INSERT INTO `st_role_authority` VALUES ('3339', '1', '178', '0', 'admin', null, '2020-12-02 15:22:38', '2020-12-09 15:34:59');
INSERT INTO `st_role_authority` VALUES ('3340', '1', '179', '0', 'admin', null, '2020-12-02 15:22:38', '2020-12-09 15:34:59');
INSERT INTO `st_role_authority` VALUES ('3341', '1', '180', '0', 'admin', null, '2020-12-02 15:22:38', '2020-12-09 15:34:59');
INSERT INTO `st_role_authority` VALUES ('3342', '1', '181', '0', 'admin', null, '2020-12-02 15:22:38', '2020-12-09 15:34:59');
INSERT INTO `st_role_authority` VALUES ('3343', '1', '182', '0', 'admin', null, '2020-12-02 15:22:38', '2020-12-09 15:34:59');
INSERT INTO `st_role_authority` VALUES ('3344', '1', '56', '0', 'admin', null, '2020-12-02 15:22:38', '2020-12-09 15:34:59');
INSERT INTO `st_role_authority` VALUES ('3345', '1', '57', '0', 'admin', null, '2020-12-02 15:22:38', '2020-12-09 15:34:59');
INSERT INTO `st_role_authority` VALUES ('3346', '1', '58', '0', 'admin', null, '2020-12-02 15:22:38', '2020-12-09 15:34:59');
INSERT INTO `st_role_authority` VALUES ('3347', '1', '59', '0', 'admin', null, '2020-12-02 15:22:38', '2020-12-09 15:34:59');
INSERT INTO `st_role_authority` VALUES ('3348', '1', '60', '0', 'admin', null, '2020-12-02 15:22:38', '2020-12-09 15:34:59');
INSERT INTO `st_role_authority` VALUES ('3349', '1', '61', '0', 'admin', null, '2020-12-02 15:22:38', '2020-12-09 15:34:59');
INSERT INTO `st_role_authority` VALUES ('3350', '1', '0', '0', 'admin', null, '2020-12-09 15:34:59', '2020-12-14 16:10:40');
INSERT INTO `st_role_authority` VALUES ('3351', '1', '1', '0', 'admin', null, '2020-12-09 15:34:59', '2020-12-14 16:10:40');
INSERT INTO `st_role_authority` VALUES ('3352', '1', '2', '0', 'admin', null, '2020-12-09 15:34:59', '2020-12-14 16:10:40');
INSERT INTO `st_role_authority` VALUES ('3353', '1', '66', '0', 'admin', null, '2020-12-09 15:34:59', '2020-12-14 16:10:40');
INSERT INTO `st_role_authority` VALUES ('3354', '1', '3', '0', 'admin', null, '2020-12-09 15:34:59', '2020-12-14 16:10:40');
INSERT INTO `st_role_authority` VALUES ('3355', '1', '67', '0', 'admin', null, '2020-12-09 15:34:59', '2020-12-14 16:10:40');
INSERT INTO `st_role_authority` VALUES ('3356', '1', '4', '0', 'admin', null, '2020-12-09 15:34:59', '2020-12-14 16:10:40');
INSERT INTO `st_role_authority` VALUES ('3357', '1', '5', '0', 'admin', null, '2020-12-09 15:34:59', '2020-12-14 16:10:40');
INSERT INTO `st_role_authority` VALUES ('3358', '1', '6', '0', 'admin', null, '2020-12-09 15:34:59', '2020-12-14 16:10:40');
INSERT INTO `st_role_authority` VALUES ('3359', '1', '7', '0', 'admin', null, '2020-12-09 15:34:59', '2020-12-14 16:10:40');
INSERT INTO `st_role_authority` VALUES ('3360', '1', '8', '0', 'admin', null, '2020-12-09 15:34:59', '2020-12-14 16:10:40');
INSERT INTO `st_role_authority` VALUES ('3361', '1', '9', '0', 'admin', null, '2020-12-09 15:34:59', '2020-12-14 16:10:40');
INSERT INTO `st_role_authority` VALUES ('3362', '1', '10', '0', 'admin', null, '2020-12-09 15:34:59', '2020-12-14 16:10:40');
INSERT INTO `st_role_authority` VALUES ('3363', '1', '11', '0', 'admin', null, '2020-12-09 15:34:59', '2020-12-14 16:10:40');
INSERT INTO `st_role_authority` VALUES ('3364', '1', '12', '0', 'admin', null, '2020-12-09 15:34:59', '2020-12-14 16:10:40');
INSERT INTO `st_role_authority` VALUES ('3365', '1', '13', '0', 'admin', null, '2020-12-09 15:34:59', '2020-12-14 16:10:40');
INSERT INTO `st_role_authority` VALUES ('3366', '1', '14', '0', 'admin', null, '2020-12-09 15:34:59', '2020-12-14 16:10:40');
INSERT INTO `st_role_authority` VALUES ('3367', '1', '15', '0', 'admin', null, '2020-12-09 15:34:59', '2020-12-14 16:10:40');
INSERT INTO `st_role_authority` VALUES ('3368', '1', '16', '0', 'admin', null, '2020-12-09 15:34:59', '2020-12-14 16:10:40');
INSERT INTO `st_role_authority` VALUES ('3369', '1', '17', '0', 'admin', null, '2020-12-09 15:34:59', '2020-12-14 16:10:40');
INSERT INTO `st_role_authority` VALUES ('3370', '1', '18', '0', 'admin', null, '2020-12-09 15:34:59', '2020-12-14 16:10:40');
INSERT INTO `st_role_authority` VALUES ('3371', '1', '56', '0', 'admin', null, '2020-12-09 15:34:59', '2020-12-14 16:10:40');
INSERT INTO `st_role_authority` VALUES ('3372', '1', '57', '0', 'admin', null, '2020-12-09 15:34:59', '2020-12-14 16:10:40');
INSERT INTO `st_role_authority` VALUES ('3373', '1', '58', '0', 'admin', null, '2020-12-09 15:34:59', '2020-12-14 16:10:40');
INSERT INTO `st_role_authority` VALUES ('3374', '1', '59', '0', 'admin', null, '2020-12-09 15:34:59', '2020-12-14 16:10:40');
INSERT INTO `st_role_authority` VALUES ('3375', '1', '60', '0', 'admin', null, '2020-12-09 15:34:59', '2020-12-14 16:10:40');
INSERT INTO `st_role_authority` VALUES ('3376', '1', '61', '0', 'admin', null, '2020-12-09 15:34:59', '2020-12-14 16:10:40');
INSERT INTO `st_role_authority` VALUES ('3377', '1', '0', '0', 'admin', null, '2020-12-14 16:10:40', '2020-12-28 12:15:14');
INSERT INTO `st_role_authority` VALUES ('3378', '1', '1', '0', 'admin', null, '2020-12-14 16:10:40', '2020-12-28 12:15:14');
INSERT INTO `st_role_authority` VALUES ('3379', '1', '2', '0', 'admin', null, '2020-12-14 16:10:40', '2020-12-28 12:15:14');
INSERT INTO `st_role_authority` VALUES ('3380', '1', '66', '0', 'admin', null, '2020-12-14 16:10:40', '2020-12-28 12:15:14');
INSERT INTO `st_role_authority` VALUES ('3381', '1', '3', '0', 'admin', null, '2020-12-14 16:10:40', '2020-12-28 12:15:14');
INSERT INTO `st_role_authority` VALUES ('3382', '1', '67', '0', 'admin', null, '2020-12-14 16:10:40', '2020-12-28 12:15:14');
INSERT INTO `st_role_authority` VALUES ('3383', '1', '4', '0', 'admin', null, '2020-12-14 16:10:40', '2020-12-28 12:15:14');
INSERT INTO `st_role_authority` VALUES ('3384', '1', '5', '0', 'admin', null, '2020-12-14 16:10:40', '2020-12-28 12:15:14');
INSERT INTO `st_role_authority` VALUES ('3385', '1', '6', '0', 'admin', null, '2020-12-14 16:10:40', '2020-12-28 12:15:14');
INSERT INTO `st_role_authority` VALUES ('3386', '1', '7', '0', 'admin', null, '2020-12-14 16:10:40', '2020-12-28 12:15:14');
INSERT INTO `st_role_authority` VALUES ('3387', '1', '8', '0', 'admin', null, '2020-12-14 16:10:40', '2020-12-28 12:15:14');
INSERT INTO `st_role_authority` VALUES ('3388', '1', '9', '0', 'admin', null, '2020-12-14 16:10:40', '2020-12-28 12:15:14');
INSERT INTO `st_role_authority` VALUES ('3389', '1', '10', '0', 'admin', null, '2020-12-14 16:10:40', '2020-12-28 12:15:14');
INSERT INTO `st_role_authority` VALUES ('3390', '1', '11', '0', 'admin', null, '2020-12-14 16:10:40', '2020-12-28 12:15:14');
INSERT INTO `st_role_authority` VALUES ('3391', '1', '12', '0', 'admin', null, '2020-12-14 16:10:40', '2020-12-28 12:15:14');
INSERT INTO `st_role_authority` VALUES ('3392', '1', '13', '0', 'admin', null, '2020-12-14 16:10:40', '2020-12-28 12:15:14');
INSERT INTO `st_role_authority` VALUES ('3393', '1', '14', '0', 'admin', null, '2020-12-14 16:10:40', '2020-12-28 12:15:14');
INSERT INTO `st_role_authority` VALUES ('3394', '1', '15', '0', 'admin', null, '2020-12-14 16:10:40', '2020-12-28 12:15:14');
INSERT INTO `st_role_authority` VALUES ('3395', '1', '16', '0', 'admin', null, '2020-12-14 16:10:40', '2020-12-28 12:15:14');
INSERT INTO `st_role_authority` VALUES ('3396', '1', '17', '0', 'admin', null, '2020-12-14 16:10:40', '2020-12-28 12:15:14');
INSERT INTO `st_role_authority` VALUES ('3397', '1', '18', '0', 'admin', null, '2020-12-14 16:10:40', '2020-12-28 12:15:14');
INSERT INTO `st_role_authority` VALUES ('3398', '1', '173', '0', 'admin', null, '2020-12-14 16:10:40', '2020-12-28 12:15:14');
INSERT INTO `st_role_authority` VALUES ('3399', '1', '174', '0', 'admin', null, '2020-12-14 16:10:40', '2020-12-28 12:15:14');
INSERT INTO `st_role_authority` VALUES ('3400', '1', '175', '0', 'admin', null, '2020-12-14 16:10:40', '2020-12-28 12:15:14');
INSERT INTO `st_role_authority` VALUES ('3401', '1', '176', '0', 'admin', null, '2020-12-14 16:10:40', '2020-12-28 12:15:14');
INSERT INTO `st_role_authority` VALUES ('3402', '1', '177', '0', 'admin', null, '2020-12-14 16:10:40', '2020-12-28 12:15:14');
INSERT INTO `st_role_authority` VALUES ('3403', '1', '178', '0', 'admin', null, '2020-12-14 16:10:40', '2020-12-28 12:15:14');
INSERT INTO `st_role_authority` VALUES ('3404', '1', '179', '0', 'admin', null, '2020-12-14 16:10:40', '2020-12-28 12:15:14');
INSERT INTO `st_role_authority` VALUES ('3405', '1', '180', '0', 'admin', null, '2020-12-14 16:10:40', '2020-12-28 12:15:14');
INSERT INTO `st_role_authority` VALUES ('3406', '1', '181', '0', 'admin', null, '2020-12-14 16:10:40', '2020-12-28 12:15:14');
INSERT INTO `st_role_authority` VALUES ('3407', '1', '182', '0', 'admin', null, '2020-12-14 16:10:40', '2020-12-28 12:15:14');
INSERT INTO `st_role_authority` VALUES ('3408', '1', '183', '0', 'admin', null, '2020-12-14 16:10:40', '2020-12-28 12:15:14');
INSERT INTO `st_role_authority` VALUES ('3409', '1', '56', '0', 'admin', null, '2020-12-14 16:10:40', '2020-12-28 12:15:14');
INSERT INTO `st_role_authority` VALUES ('3410', '1', '184', '0', 'admin', null, '2020-12-14 16:10:40', '2020-12-28 12:15:14');
INSERT INTO `st_role_authority` VALUES ('3411', '1', '57', '0', 'admin', null, '2020-12-14 16:10:40', '2020-12-28 12:15:14');
INSERT INTO `st_role_authority` VALUES ('3412', '1', '58', '0', 'admin', null, '2020-12-14 16:10:40', '2020-12-28 12:15:14');
INSERT INTO `st_role_authority` VALUES ('3413', '1', '59', '0', 'admin', null, '2020-12-14 16:10:40', '2020-12-28 12:15:14');
INSERT INTO `st_role_authority` VALUES ('3414', '1', '60', '0', 'admin', null, '2020-12-14 16:10:40', '2020-12-28 12:15:14');
INSERT INTO `st_role_authority` VALUES ('3415', '1', '61', '0', 'admin', null, '2020-12-14 16:10:40', '2020-12-28 12:15:14');
INSERT INTO `st_role_authority` VALUES ('3416', '1', '0', '0', 'admin', null, '2020-12-28 12:15:14', '2020-12-28 13:53:35');
INSERT INTO `st_role_authority` VALUES ('3417', '1', '1', '0', 'admin', null, '2020-12-28 12:15:14', '2020-12-28 13:53:35');
INSERT INTO `st_role_authority` VALUES ('3418', '1', '2', '0', 'admin', null, '2020-12-28 12:15:14', '2020-12-28 13:53:35');
INSERT INTO `st_role_authority` VALUES ('3419', '1', '3', '0', 'admin', null, '2020-12-28 12:15:14', '2020-12-28 13:53:35');
INSERT INTO `st_role_authority` VALUES ('3420', '1', '4', '0', 'admin', null, '2020-12-28 12:15:14', '2020-12-28 13:53:35');
INSERT INTO `st_role_authority` VALUES ('3421', '1', '5', '0', 'admin', null, '2020-12-28 12:15:14', '2020-12-28 13:53:35');
INSERT INTO `st_role_authority` VALUES ('3422', '1', '6', '0', 'admin', null, '2020-12-28 12:15:14', '2020-12-28 13:53:35');
INSERT INTO `st_role_authority` VALUES ('3423', '1', '7', '0', 'admin', null, '2020-12-28 12:15:14', '2020-12-28 13:53:35');
INSERT INTO `st_role_authority` VALUES ('3424', '1', '8', '0', 'admin', null, '2020-12-28 12:15:14', '2020-12-28 13:53:35');
INSERT INTO `st_role_authority` VALUES ('3425', '1', '9', '0', 'admin', null, '2020-12-28 12:15:14', '2020-12-28 13:53:35');
INSERT INTO `st_role_authority` VALUES ('3426', '1', '10', '0', 'admin', null, '2020-12-28 12:15:14', '2020-12-28 13:53:35');
INSERT INTO `st_role_authority` VALUES ('3427', '1', '11', '0', 'admin', null, '2020-12-28 12:15:14', '2020-12-28 13:53:35');
INSERT INTO `st_role_authority` VALUES ('3428', '1', '12', '0', 'admin', null, '2020-12-28 12:15:14', '2020-12-28 13:53:35');
INSERT INTO `st_role_authority` VALUES ('3429', '1', '13', '0', 'admin', null, '2020-12-28 12:15:14', '2020-12-28 13:53:35');
INSERT INTO `st_role_authority` VALUES ('3430', '1', '14', '0', 'admin', null, '2020-12-28 12:15:14', '2020-12-28 13:53:35');
INSERT INTO `st_role_authority` VALUES ('3431', '1', '15', '0', 'admin', null, '2020-12-28 12:15:14', '2020-12-28 13:53:35');
INSERT INTO `st_role_authority` VALUES ('3432', '1', '16', '0', 'admin', null, '2020-12-28 12:15:14', '2020-12-28 13:53:35');
INSERT INTO `st_role_authority` VALUES ('3433', '1', '17', '0', 'admin', null, '2020-12-28 12:15:14', '2020-12-28 13:53:35');
INSERT INTO `st_role_authority` VALUES ('3434', '1', '18', '0', 'admin', null, '2020-12-28 12:15:14', '2020-12-28 13:53:35');
INSERT INTO `st_role_authority` VALUES ('3435', '1', '38', '0', 'admin', null, '2020-12-28 12:15:14', '2020-12-28 13:53:35');
INSERT INTO `st_role_authority` VALUES ('3436', '1', '39', '0', 'admin', null, '2020-12-28 12:15:14', '2020-12-28 13:53:35');
INSERT INTO `st_role_authority` VALUES ('3437', '1', '40', '0', 'admin', null, '2020-12-28 12:15:14', '2020-12-28 13:53:35');
INSERT INTO `st_role_authority` VALUES ('3438', '1', '43', '0', 'admin', null, '2020-12-28 12:15:14', '2020-12-28 13:53:35');
INSERT INTO `st_role_authority` VALUES ('3439', '1', '44', '0', 'admin', null, '2020-12-28 12:15:14', '2020-12-28 13:53:35');
INSERT INTO `st_role_authority` VALUES ('3440', '1', '45', '0', 'admin', null, '2020-12-28 12:15:14', '2020-12-28 13:53:35');
INSERT INTO `st_role_authority` VALUES ('3441', '1', '50', '0', 'admin', null, '2020-12-28 12:15:14', '2020-12-28 13:53:35');
INSERT INTO `st_role_authority` VALUES ('3442', '1', '56', '0', 'admin', null, '2020-12-28 12:15:14', '2020-12-28 13:53:35');
INSERT INTO `st_role_authority` VALUES ('3443', '1', '57', '0', 'admin', null, '2020-12-28 12:15:14', '2020-12-28 13:53:35');
INSERT INTO `st_role_authority` VALUES ('3444', '1', '58', '0', 'admin', null, '2020-12-28 12:15:14', '2020-12-28 13:53:35');
INSERT INTO `st_role_authority` VALUES ('3445', '1', '59', '0', 'admin', null, '2020-12-28 12:15:14', '2020-12-28 13:53:35');
INSERT INTO `st_role_authority` VALUES ('3446', '1', '60', '0', 'admin', null, '2020-12-28 12:15:14', '2020-12-28 13:53:35');
INSERT INTO `st_role_authority` VALUES ('3447', '1', '61', '0', 'admin', null, '2020-12-28 12:15:14', '2020-12-28 13:53:35');
INSERT INTO `st_role_authority` VALUES ('3448', '1', '62', '0', 'admin', null, '2020-12-28 12:15:14', '2020-12-28 13:53:35');
INSERT INTO `st_role_authority` VALUES ('3449', '1', '63', '0', 'admin', null, '2020-12-28 12:15:14', '2020-12-28 13:53:35');
INSERT INTO `st_role_authority` VALUES ('3450', '1', '64', '0', 'admin', null, '2020-12-28 12:15:14', '2020-12-28 13:53:35');
INSERT INTO `st_role_authority` VALUES ('3451', '1', '65', '0', 'admin', null, '2020-12-28 12:15:14', '2020-12-28 13:53:35');
INSERT INTO `st_role_authority` VALUES ('3452', '1', '66', '0', 'admin', null, '2020-12-28 12:15:14', '2020-12-28 13:53:35');
INSERT INTO `st_role_authority` VALUES ('3453', '1', '67', '0', 'admin', null, '2020-12-28 12:15:14', '2020-12-28 13:53:35');
INSERT INTO `st_role_authority` VALUES ('3454', '1', '68', '0', 'admin', null, '2020-12-28 12:15:14', '2020-12-28 13:53:35');
INSERT INTO `st_role_authority` VALUES ('3455', '1', '69', '0', 'admin', null, '2020-12-28 12:15:14', '2020-12-28 13:53:35');
INSERT INTO `st_role_authority` VALUES ('3456', '1', '70', '0', 'admin', null, '2020-12-28 12:15:14', '2020-12-28 13:53:35');
INSERT INTO `st_role_authority` VALUES ('3457', '1', '71', '0', 'admin', null, '2020-12-28 12:15:14', '2020-12-28 13:53:35');
INSERT INTO `st_role_authority` VALUES ('3458', '1', '91', '0', 'admin', null, '2020-12-28 12:15:14', '2020-12-28 13:53:35');
INSERT INTO `st_role_authority` VALUES ('3459', '1', '107', '0', 'admin', null, '2020-12-28 12:15:14', '2020-12-28 13:53:35');
INSERT INTO `st_role_authority` VALUES ('3460', '1', '108', '0', 'admin', null, '2020-12-28 12:15:14', '2020-12-28 13:53:35');
INSERT INTO `st_role_authority` VALUES ('3461', '1', '109', '0', 'admin', null, '2020-12-28 12:15:14', '2020-12-28 13:53:35');
INSERT INTO `st_role_authority` VALUES ('3462', '1', '110', '0', 'admin', null, '2020-12-28 12:15:14', '2020-12-28 13:53:35');
INSERT INTO `st_role_authority` VALUES ('3463', '1', '111', '0', 'admin', null, '2020-12-28 12:15:14', '2020-12-28 13:53:35');
INSERT INTO `st_role_authority` VALUES ('3464', '1', '112', '0', 'admin', null, '2020-12-28 12:15:14', '2020-12-28 13:53:35');
INSERT INTO `st_role_authority` VALUES ('3465', '1', '113', '0', 'admin', null, '2020-12-28 12:15:14', '2020-12-28 13:53:35');
INSERT INTO `st_role_authority` VALUES ('3466', '1', '114', '0', 'admin', null, '2020-12-28 12:15:14', '2020-12-28 13:53:35');
INSERT INTO `st_role_authority` VALUES ('3467', '1', '0', '0', 'admin', null, '2020-12-28 13:53:35', '2020-12-29 12:09:08');
INSERT INTO `st_role_authority` VALUES ('3468', '1', '1', '0', 'admin', null, '2020-12-28 13:53:35', '2020-12-29 12:09:08');
INSERT INTO `st_role_authority` VALUES ('3469', '1', '2', '0', 'admin', null, '2020-12-28 13:53:35', '2020-12-29 12:09:08');
INSERT INTO `st_role_authority` VALUES ('3470', '1', '3', '0', 'admin', null, '2020-12-28 13:53:35', '2020-12-29 12:09:08');
INSERT INTO `st_role_authority` VALUES ('3471', '1', '4', '0', 'admin', null, '2020-12-28 13:53:35', '2020-12-29 12:09:08');
INSERT INTO `st_role_authority` VALUES ('3472', '1', '5', '0', 'admin', null, '2020-12-28 13:53:35', '2020-12-29 12:09:08');
INSERT INTO `st_role_authority` VALUES ('3473', '1', '6', '0', 'admin', null, '2020-12-28 13:53:35', '2020-12-29 12:09:08');
INSERT INTO `st_role_authority` VALUES ('3474', '1', '7', '0', 'admin', null, '2020-12-28 13:53:35', '2020-12-29 12:09:08');
INSERT INTO `st_role_authority` VALUES ('3475', '1', '8', '0', 'admin', null, '2020-12-28 13:53:35', '2020-12-29 12:09:08');
INSERT INTO `st_role_authority` VALUES ('3476', '1', '9', '0', 'admin', null, '2020-12-28 13:53:35', '2020-12-29 12:09:08');
INSERT INTO `st_role_authority` VALUES ('3477', '1', '10', '0', 'admin', null, '2020-12-28 13:53:35', '2020-12-29 12:09:08');
INSERT INTO `st_role_authority` VALUES ('3478', '1', '11', '0', 'admin', null, '2020-12-28 13:53:35', '2020-12-29 12:09:08');
INSERT INTO `st_role_authority` VALUES ('3479', '1', '12', '0', 'admin', null, '2020-12-28 13:53:35', '2020-12-29 12:09:08');
INSERT INTO `st_role_authority` VALUES ('3480', '1', '13', '0', 'admin', null, '2020-12-28 13:53:35', '2020-12-29 12:09:08');
INSERT INTO `st_role_authority` VALUES ('3481', '1', '14', '0', 'admin', null, '2020-12-28 13:53:35', '2020-12-29 12:09:08');
INSERT INTO `st_role_authority` VALUES ('3482', '1', '15', '0', 'admin', null, '2020-12-28 13:53:35', '2020-12-29 12:09:08');
INSERT INTO `st_role_authority` VALUES ('3483', '1', '16', '0', 'admin', null, '2020-12-28 13:53:35', '2020-12-29 12:09:08');
INSERT INTO `st_role_authority` VALUES ('3484', '1', '17', '0', 'admin', null, '2020-12-28 13:53:35', '2020-12-29 12:09:08');
INSERT INTO `st_role_authority` VALUES ('3485', '1', '18', '0', 'admin', null, '2020-12-28 13:53:35', '2020-12-29 12:09:08');
INSERT INTO `st_role_authority` VALUES ('3486', '1', '38', '0', 'admin', null, '2020-12-28 13:53:35', '2020-12-29 12:09:08');
INSERT INTO `st_role_authority` VALUES ('3487', '1', '39', '0', 'admin', null, '2020-12-28 13:53:35', '2020-12-29 12:09:08');
INSERT INTO `st_role_authority` VALUES ('3488', '1', '40', '0', 'admin', null, '2020-12-28 13:53:35', '2020-12-29 12:09:08');
INSERT INTO `st_role_authority` VALUES ('3489', '1', '43', '0', 'admin', null, '2020-12-28 13:53:35', '2020-12-29 12:09:08');
INSERT INTO `st_role_authority` VALUES ('3490', '1', '44', '0', 'admin', null, '2020-12-28 13:53:35', '2020-12-29 12:09:08');
INSERT INTO `st_role_authority` VALUES ('3491', '1', '45', '0', 'admin', null, '2020-12-28 13:53:35', '2020-12-29 12:09:08');
INSERT INTO `st_role_authority` VALUES ('3492', '1', '50', '0', 'admin', null, '2020-12-28 13:53:35', '2020-12-29 12:09:08');
INSERT INTO `st_role_authority` VALUES ('3493', '1', '56', '0', 'admin', null, '2020-12-28 13:53:35', '2020-12-29 12:09:08');
INSERT INTO `st_role_authority` VALUES ('3494', '1', '57', '0', 'admin', null, '2020-12-28 13:53:35', '2020-12-29 12:09:08');
INSERT INTO `st_role_authority` VALUES ('3495', '1', '185', '0', 'admin', null, '2020-12-28 13:53:35', '2020-12-29 12:09:08');
INSERT INTO `st_role_authority` VALUES ('3496', '1', '58', '0', 'admin', null, '2020-12-28 13:53:35', '2020-12-29 12:09:08');
INSERT INTO `st_role_authority` VALUES ('3497', '1', '59', '0', 'admin', null, '2020-12-28 13:53:35', '2020-12-29 12:09:08');
INSERT INTO `st_role_authority` VALUES ('3498', '1', '60', '0', 'admin', null, '2020-12-28 13:53:35', '2020-12-29 12:09:08');
INSERT INTO `st_role_authority` VALUES ('3499', '1', '61', '0', 'admin', null, '2020-12-28 13:53:35', '2020-12-29 12:09:08');
INSERT INTO `st_role_authority` VALUES ('3500', '1', '62', '0', 'admin', null, '2020-12-28 13:53:35', '2020-12-29 12:09:08');
INSERT INTO `st_role_authority` VALUES ('3501', '1', '63', '0', 'admin', null, '2020-12-28 13:53:35', '2020-12-29 12:09:08');
INSERT INTO `st_role_authority` VALUES ('3502', '1', '64', '0', 'admin', null, '2020-12-28 13:53:35', '2020-12-29 12:09:08');
INSERT INTO `st_role_authority` VALUES ('3503', '1', '65', '0', 'admin', null, '2020-12-28 13:53:35', '2020-12-29 12:09:08');
INSERT INTO `st_role_authority` VALUES ('3504', '1', '66', '0', 'admin', null, '2020-12-28 13:53:35', '2020-12-29 12:09:08');
INSERT INTO `st_role_authority` VALUES ('3505', '1', '67', '0', 'admin', null, '2020-12-28 13:53:35', '2020-12-29 12:09:08');
INSERT INTO `st_role_authority` VALUES ('3506', '1', '68', '0', 'admin', null, '2020-12-28 13:53:35', '2020-12-29 12:09:08');
INSERT INTO `st_role_authority` VALUES ('3507', '1', '69', '0', 'admin', null, '2020-12-28 13:53:35', '2020-12-29 12:09:08');
INSERT INTO `st_role_authority` VALUES ('3508', '1', '70', '0', 'admin', null, '2020-12-28 13:53:35', '2020-12-29 12:09:08');
INSERT INTO `st_role_authority` VALUES ('3509', '1', '71', '0', 'admin', null, '2020-12-28 13:53:35', '2020-12-29 12:09:08');
INSERT INTO `st_role_authority` VALUES ('3510', '1', '91', '0', 'admin', null, '2020-12-28 13:53:35', '2020-12-29 12:09:08');
INSERT INTO `st_role_authority` VALUES ('3511', '1', '107', '0', 'admin', null, '2020-12-28 13:53:35', '2020-12-29 12:09:08');
INSERT INTO `st_role_authority` VALUES ('3512', '1', '108', '0', 'admin', null, '2020-12-28 13:53:35', '2020-12-29 12:09:08');
INSERT INTO `st_role_authority` VALUES ('3513', '1', '109', '0', 'admin', null, '2020-12-28 13:53:35', '2020-12-29 12:09:08');
INSERT INTO `st_role_authority` VALUES ('3514', '1', '110', '0', 'admin', null, '2020-12-28 13:53:35', '2020-12-29 12:09:08');
INSERT INTO `st_role_authority` VALUES ('3515', '1', '111', '0', 'admin', null, '2020-12-28 13:53:35', '2020-12-29 12:09:08');
INSERT INTO `st_role_authority` VALUES ('3516', '1', '112', '0', 'admin', null, '2020-12-28 13:53:35', '2020-12-29 12:09:08');
INSERT INTO `st_role_authority` VALUES ('3517', '1', '113', '0', 'admin', null, '2020-12-28 13:53:35', '2020-12-29 12:09:08');
INSERT INTO `st_role_authority` VALUES ('3518', '1', '114', '0', 'admin', null, '2020-12-28 13:53:35', '2020-12-29 12:09:08');
INSERT INTO `st_role_authority` VALUES ('3519', '1', '0', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3520', '1', '1', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3521', '1', '2', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3522', '1', '3', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3523', '1', '4', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3524', '1', '5', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3525', '1', '6', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3526', '1', '7', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3527', '1', '8', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3528', '1', '9', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3529', '1', '10', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3530', '1', '11', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3531', '1', '12', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3532', '1', '13', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3533', '1', '14', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3534', '1', '15', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3535', '1', '16', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3536', '1', '17', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3537', '1', '18', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3538', '1', '38', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3539', '1', '39', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3540', '1', '40', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3541', '1', '43', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3542', '1', '44', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3543', '1', '45', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3544', '1', '173', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3545', '1', '174', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3546', '1', '175', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3547', '1', '176', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3548', '1', '177', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3549', '1', '50', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3550', '1', '178', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3551', '1', '179', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3552', '1', '180', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3553', '1', '181', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3554', '1', '182', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3555', '1', '183', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3556', '1', '56', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3557', '1', '184', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3558', '1', '57', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3559', '1', '58', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3560', '1', '59', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3561', '1', '60', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3562', '1', '61', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3563', '1', '62', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3564', '1', '63', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3565', '1', '64', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3566', '1', '65', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3567', '1', '66', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3568', '1', '67', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3569', '1', '68', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3570', '1', '69', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3571', '1', '70', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3572', '1', '71', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3573', '1', '91', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3574', '1', '107', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3575', '1', '108', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3576', '1', '109', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3577', '1', '110', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3578', '1', '111', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3579', '1', '112', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3580', '1', '113', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3581', '1', '114', '0', 'admin', null, '2020-12-29 12:09:08', '2020-12-31 08:46:32');
INSERT INTO `st_role_authority` VALUES ('3582', '1', '0', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3583', '1', '128', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3584', '1', '1', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3585', '1', '129', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3586', '1', '2', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3587', '1', '130', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3588', '1', '3', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3589', '1', '131', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3590', '1', '4', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3591', '1', '132', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3592', '1', '5', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3593', '1', '133', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3594', '1', '6', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3595', '1', '134', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3596', '1', '7', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3597', '1', '135', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3598', '1', '8', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3599', '1', '136', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3600', '1', '9', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3601', '1', '137', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3602', '1', '10', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3603', '1', '138', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3604', '1', '11', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3605', '1', '139', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3606', '1', '12', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3607', '1', '140', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3608', '1', '13', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3609', '1', '141', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3610', '1', '14', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3611', '1', '142', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3612', '1', '15', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3613', '1', '143', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3614', '1', '16', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3615', '1', '144', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3616', '1', '17', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3617', '1', '145', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3618', '1', '18', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3619', '1', '146', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3620', '1', '147', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3621', '1', '154', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3622', '1', '155', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3623', '1', '38', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3624', '1', '39', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3625', '1', '40', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3626', '1', '43', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3627', '1', '44', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3628', '1', '45', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3629', '1', '50', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3630', '1', '56', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3631', '1', '57', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3632', '1', '58', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3633', '1', '59', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3634', '1', '60', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3635', '1', '61', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3636', '1', '62', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3637', '1', '63', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3638', '1', '64', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3639', '1', '65', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3640', '1', '66', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3641', '1', '67', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3642', '1', '68', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3643', '1', '69', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3644', '1', '70', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3645', '1', '71', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3646', '1', '91', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3647', '1', '107', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3648', '1', '108', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3649', '1', '109', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3650', '1', '110', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3651', '1', '111', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3652', '1', '112', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3653', '1', '113', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3654', '1', '114', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3655', '1', '123', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3656', '1', '124', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3657', '1', '125', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3658', '1', '126', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3659', '1', '127', '0', 'admin', null, '2020-12-31 08:46:32', '2021-01-05 02:51:21');
INSERT INTO `st_role_authority` VALUES ('3660', '1', '0', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3661', '1', '1', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3662', '1', '2', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3663', '1', '3', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3664', '1', '4', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3665', '1', '5', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3666', '1', '6', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3667', '1', '7', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3668', '1', '8', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3669', '1', '9', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3670', '1', '10', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3671', '1', '11', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3672', '1', '12', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3673', '1', '13', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3674', '1', '14', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3675', '1', '15', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3676', '1', '16', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3677', '1', '17', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3678', '1', '18', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3679', '1', '38', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3680', '1', '39', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3681', '1', '40', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3682', '1', '43', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3683', '1', '44', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3684', '1', '45', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3685', '1', '173', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3686', '1', '174', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3687', '1', '175', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3688', '1', '176', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3689', '1', '177', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3690', '1', '50', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3691', '1', '178', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3692', '1', '179', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3693', '1', '180', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3694', '1', '181', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3695', '1', '182', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3696', '1', '183', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3697', '1', '56', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3698', '1', '184', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3699', '1', '57', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3700', '1', '58', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3701', '1', '59', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3702', '1', '60', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3703', '1', '61', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3704', '1', '62', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3705', '1', '63', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3706', '1', '64', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3707', '1', '65', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3708', '1', '66', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3709', '1', '67', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3710', '1', '68', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3711', '1', '69', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3712', '1', '70', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3713', '1', '71', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3714', '1', '91', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3715', '1', '107', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3716', '1', '108', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3717', '1', '109', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3718', '1', '110', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3719', '1', '111', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3720', '1', '112', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3721', '1', '113', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3722', '1', '114', '0', 'admin', null, '2021-01-05 02:51:21', '2021-01-05 06:37:26');
INSERT INTO `st_role_authority` VALUES ('3723', '1', '0', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3724', '1', '1', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3725', '1', '2', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3726', '1', '3', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3727', '1', '4', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3728', '1', '5', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3729', '1', '6', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3730', '1', '7', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3731', '1', '8', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3732', '1', '9', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3733', '1', '10', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3734', '1', '11', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3735', '1', '12', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3736', '1', '13', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3737', '1', '14', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3738', '1', '15', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3739', '1', '16', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3740', '1', '17', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3741', '1', '18', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3742', '1', '156', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3743', '1', '157', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3744', '1', '158', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3745', '1', '159', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3746', '1', '160', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3747', '1', '161', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3748', '1', '162', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3749', '1', '163', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3750', '1', '164', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3751', '1', '165', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3752', '1', '38', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3753', '1', '166', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3754', '1', '39', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3755', '1', '167', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3756', '1', '40', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3757', '1', '168', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3758', '1', '169', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3759', '1', '170', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3760', '1', '43', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3761', '1', '171', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3762', '1', '44', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3763', '1', '172', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3764', '1', '45', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3765', '1', '50', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3766', '1', '56', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3767', '1', '57', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3768', '1', '58', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3769', '1', '59', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3770', '1', '60', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3771', '1', '61', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3772', '1', '62', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3773', '1', '63', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3774', '1', '64', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3775', '1', '65', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3776', '1', '66', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3777', '1', '67', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3778', '1', '68', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3779', '1', '69', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3780', '1', '70', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3781', '1', '71', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3782', '1', '91', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3783', '1', '107', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3784', '1', '108', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3785', '1', '109', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3786', '1', '110', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3787', '1', '111', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3788', '1', '112', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3789', '1', '113', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3790', '1', '114', '0', 'admin', null, '2021-01-05 06:37:26', '2021-01-05 06:40:07');
INSERT INTO `st_role_authority` VALUES ('3791', '1', '0', '0', 'admin', null, '2021-01-05 06:40:07', '2021-01-06 10:59:55');
INSERT INTO `st_role_authority` VALUES ('3792', '1', '1', '0', 'admin', null, '2021-01-05 06:40:07', '2021-01-06 10:59:55');
INSERT INTO `st_role_authority` VALUES ('3793', '1', '2', '0', 'admin', null, '2021-01-05 06:40:07', '2021-01-06 10:59:55');
INSERT INTO `st_role_authority` VALUES ('3794', '1', '3', '0', 'admin', null, '2021-01-05 06:40:07', '2021-01-06 10:59:55');
INSERT INTO `st_role_authority` VALUES ('3795', '1', '4', '0', 'admin', null, '2021-01-05 06:40:07', '2021-01-06 10:59:55');
INSERT INTO `st_role_authority` VALUES ('3796', '1', '5', '0', 'admin', null, '2021-01-05 06:40:07', '2021-01-06 10:59:55');
INSERT INTO `st_role_authority` VALUES ('3797', '1', '6', '0', 'admin', null, '2021-01-05 06:40:07', '2021-01-06 10:59:55');
INSERT INTO `st_role_authority` VALUES ('3798', '1', '7', '0', 'admin', null, '2021-01-05 06:40:07', '2021-01-06 10:59:55');
INSERT INTO `st_role_authority` VALUES ('3799', '1', '8', '0', 'admin', null, '2021-01-05 06:40:07', '2021-01-06 10:59:55');
INSERT INTO `st_role_authority` VALUES ('3800', '1', '9', '0', 'admin', null, '2021-01-05 06:40:07', '2021-01-06 10:59:55');
INSERT INTO `st_role_authority` VALUES ('3801', '1', '10', '0', 'admin', null, '2021-01-05 06:40:07', '2021-01-06 10:59:55');
INSERT INTO `st_role_authority` VALUES ('3802', '1', '11', '0', 'admin', null, '2021-01-05 06:40:07', '2021-01-06 10:59:55');
INSERT INTO `st_role_authority` VALUES ('3803', '1', '12', '0', 'admin', null, '2021-01-05 06:40:07', '2021-01-06 10:59:55');
INSERT INTO `st_role_authority` VALUES ('3804', '1', '13', '0', 'admin', null, '2021-01-05 06:40:07', '2021-01-06 10:59:55');
INSERT INTO `st_role_authority` VALUES ('3805', '1', '14', '0', 'admin', null, '2021-01-05 06:40:07', '2021-01-06 10:59:55');
INSERT INTO `st_role_authority` VALUES ('3806', '1', '15', '0', 'admin', null, '2021-01-05 06:40:07', '2021-01-06 10:59:55');
INSERT INTO `st_role_authority` VALUES ('3807', '1', '16', '0', 'admin', null, '2021-01-05 06:40:07', '2021-01-06 10:59:55');
INSERT INTO `st_role_authority` VALUES ('3808', '1', '17', '0', 'admin', null, '2021-01-05 06:40:07', '2021-01-06 10:59:55');
INSERT INTO `st_role_authority` VALUES ('3809', '1', '18', '0', 'admin', null, '2021-01-05 06:40:07', '2021-01-06 10:59:55');
INSERT INTO `st_role_authority` VALUES ('3810', '1', '38', '0', 'admin', null, '2021-01-05 06:40:07', '2021-01-06 10:59:55');
INSERT INTO `st_role_authority` VALUES ('3811', '1', '39', '0', 'admin', null, '2021-01-05 06:40:07', '2021-01-06 10:59:55');
INSERT INTO `st_role_authority` VALUES ('3812', '1', '40', '0', 'admin', null, '2021-01-05 06:40:07', '2021-01-06 10:59:55');
INSERT INTO `st_role_authority` VALUES ('3813', '1', '43', '0', 'admin', null, '2021-01-05 06:40:07', '2021-01-06 10:59:55');
INSERT INTO `st_role_authority` VALUES ('3814', '1', '44', '0', 'admin', null, '2021-01-05 06:40:07', '2021-01-06 10:59:55');
INSERT INTO `st_role_authority` VALUES ('3815', '1', '45', '0', 'admin', null, '2021-01-05 06:40:07', '2021-01-06 10:59:55');
INSERT INTO `st_role_authority` VALUES ('3816', '1', '50', '0', 'admin', null, '2021-01-05 06:40:07', '2021-01-06 10:59:55');
INSERT INTO `st_role_authority` VALUES ('3817', '1', '56', '0', 'admin', null, '2021-01-05 06:40:07', '2021-01-06 10:59:55');
INSERT INTO `st_role_authority` VALUES ('3818', '1', '57', '0', 'admin', null, '2021-01-05 06:40:07', '2021-01-06 10:59:55');
INSERT INTO `st_role_authority` VALUES ('3819', '1', '58', '0', 'admin', null, '2021-01-05 06:40:07', '2021-01-06 10:59:55');
INSERT INTO `st_role_authority` VALUES ('3820', '1', '59', '0', 'admin', null, '2021-01-05 06:40:07', '2021-01-06 10:59:55');
INSERT INTO `st_role_authority` VALUES ('3821', '1', '60', '0', 'admin', null, '2021-01-05 06:40:07', '2021-01-06 10:59:55');
INSERT INTO `st_role_authority` VALUES ('3822', '1', '61', '0', 'admin', null, '2021-01-05 06:40:07', '2021-01-06 10:59:55');
INSERT INTO `st_role_authority` VALUES ('3823', '1', '62', '0', 'admin', null, '2021-01-05 06:40:07', '2021-01-06 10:59:55');
INSERT INTO `st_role_authority` VALUES ('3824', '1', '63', '0', 'admin', null, '2021-01-05 06:40:07', '2021-01-06 10:59:55');
INSERT INTO `st_role_authority` VALUES ('3825', '1', '64', '0', 'admin', null, '2021-01-05 06:40:07', '2021-01-06 10:59:55');
INSERT INTO `st_role_authority` VALUES ('3826', '1', '65', '0', 'admin', null, '2021-01-05 06:40:07', '2021-01-06 10:59:55');
INSERT INTO `st_role_authority` VALUES ('3827', '1', '66', '0', 'admin', null, '2021-01-05 06:40:07', '2021-01-06 10:59:55');
INSERT INTO `st_role_authority` VALUES ('3828', '1', '67', '0', 'admin', null, '2021-01-05 06:40:07', '2021-01-06 10:59:55');
INSERT INTO `st_role_authority` VALUES ('3829', '1', '68', '0', 'admin', null, '2021-01-05 06:40:07', '2021-01-06 10:59:55');
INSERT INTO `st_role_authority` VALUES ('3830', '1', '69', '0', 'admin', null, '2021-01-05 06:40:07', '2021-01-06 10:59:55');
INSERT INTO `st_role_authority` VALUES ('3831', '1', '70', '0', 'admin', null, '2021-01-05 06:40:07', '2021-01-06 10:59:55');
INSERT INTO `st_role_authority` VALUES ('3832', '1', '71', '0', 'admin', null, '2021-01-05 06:40:07', '2021-01-06 10:59:55');
INSERT INTO `st_role_authority` VALUES ('3833', '1', '91', '0', 'admin', null, '2021-01-05 06:40:07', '2021-01-06 10:59:55');
INSERT INTO `st_role_authority` VALUES ('3834', '1', '107', '0', 'admin', null, '2021-01-05 06:40:07', '2021-01-06 10:59:55');
INSERT INTO `st_role_authority` VALUES ('3835', '1', '108', '0', 'admin', null, '2021-01-05 06:40:07', '2021-01-06 10:59:55');
INSERT INTO `st_role_authority` VALUES ('3836', '1', '109', '0', 'admin', null, '2021-01-05 06:40:07', '2021-01-06 10:59:55');
INSERT INTO `st_role_authority` VALUES ('3837', '1', '110', '0', 'admin', null, '2021-01-05 06:40:07', '2021-01-06 10:59:55');
INSERT INTO `st_role_authority` VALUES ('3838', '1', '111', '0', 'admin', null, '2021-01-05 06:40:07', '2021-01-06 10:59:55');
INSERT INTO `st_role_authority` VALUES ('3839', '1', '112', '0', 'admin', null, '2021-01-05 06:40:07', '2021-01-06 10:59:55');
INSERT INTO `st_role_authority` VALUES ('3840', '1', '113', '0', 'admin', null, '2021-01-05 06:40:07', '2021-01-06 10:59:55');
INSERT INTO `st_role_authority` VALUES ('3841', '1', '114', '0', 'admin', null, '2021-01-05 06:40:07', '2021-01-06 10:59:55');
INSERT INTO `st_role_authority` VALUES ('3842', '1', '0', '0', 'admin', null, '2021-01-06 10:59:55', '2021-01-15 06:31:22');
INSERT INTO `st_role_authority` VALUES ('3843', '1', '1', '0', 'admin', null, '2021-01-06 10:59:55', '2021-01-15 06:31:22');
INSERT INTO `st_role_authority` VALUES ('3844', '1', '2', '0', 'admin', null, '2021-01-06 10:59:55', '2021-01-15 06:31:22');
INSERT INTO `st_role_authority` VALUES ('3845', '1', '3', '0', 'admin', null, '2021-01-06 10:59:55', '2021-01-15 06:31:22');
INSERT INTO `st_role_authority` VALUES ('3846', '1', '4', '0', 'admin', null, '2021-01-06 10:59:55', '2021-01-15 06:31:22');
INSERT INTO `st_role_authority` VALUES ('3847', '1', '5', '0', 'admin', null, '2021-01-06 10:59:55', '2021-01-15 06:31:22');
INSERT INTO `st_role_authority` VALUES ('3848', '1', '6', '0', 'admin', null, '2021-01-06 10:59:55', '2021-01-15 06:31:22');
INSERT INTO `st_role_authority` VALUES ('3849', '1', '7', '0', 'admin', null, '2021-01-06 10:59:55', '2021-01-15 06:31:22');
INSERT INTO `st_role_authority` VALUES ('3850', '1', '8', '0', 'admin', null, '2021-01-06 10:59:55', '2021-01-15 06:31:22');
INSERT INTO `st_role_authority` VALUES ('3851', '1', '9', '0', 'admin', null, '2021-01-06 10:59:55', '2021-01-15 06:31:22');
INSERT INTO `st_role_authority` VALUES ('3852', '1', '10', '0', 'admin', null, '2021-01-06 10:59:55', '2021-01-15 06:31:22');
INSERT INTO `st_role_authority` VALUES ('3853', '1', '11', '0', 'admin', null, '2021-01-06 10:59:55', '2021-01-15 06:31:22');
INSERT INTO `st_role_authority` VALUES ('3854', '1', '12', '0', 'admin', null, '2021-01-06 10:59:55', '2021-01-15 06:31:22');
INSERT INTO `st_role_authority` VALUES ('3855', '1', '13', '0', 'admin', null, '2021-01-06 10:59:55', '2021-01-15 06:31:22');
INSERT INTO `st_role_authority` VALUES ('3856', '1', '14', '0', 'admin', null, '2021-01-06 10:59:55', '2021-01-15 06:31:22');
INSERT INTO `st_role_authority` VALUES ('3857', '1', '15', '0', 'admin', null, '2021-01-06 10:59:55', '2021-01-15 06:31:22');
INSERT INTO `st_role_authority` VALUES ('3858', '1', '16', '0', 'admin', null, '2021-01-06 10:59:55', '2021-01-15 06:31:22');
INSERT INTO `st_role_authority` VALUES ('3859', '1', '17', '0', 'admin', null, '2021-01-06 10:59:55', '2021-01-15 06:31:22');
INSERT INTO `st_role_authority` VALUES ('3860', '1', '18', '0', 'admin', null, '2021-01-06 10:59:55', '2021-01-15 06:31:22');
INSERT INTO `st_role_authority` VALUES ('3861', '1', '38', '0', 'admin', null, '2021-01-06 10:59:55', '2021-01-15 06:31:22');
INSERT INTO `st_role_authority` VALUES ('3862', '1', '39', '0', 'admin', null, '2021-01-06 10:59:55', '2021-01-15 06:31:22');
INSERT INTO `st_role_authority` VALUES ('3863', '1', '40', '0', 'admin', null, '2021-01-06 10:59:55', '2021-01-15 06:31:22');
INSERT INTO `st_role_authority` VALUES ('3864', '1', '43', '0', 'admin', null, '2021-01-06 10:59:55', '2021-01-15 06:31:22');
INSERT INTO `st_role_authority` VALUES ('3865', '1', '44', '0', 'admin', null, '2021-01-06 10:59:55', '2021-01-15 06:31:22');
INSERT INTO `st_role_authority` VALUES ('3866', '1', '45', '0', 'admin', null, '2021-01-06 10:59:55', '2021-01-15 06:31:22');
INSERT INTO `st_role_authority` VALUES ('3867', '1', '50', '0', 'admin', null, '2021-01-06 10:59:55', '2021-01-15 06:31:22');
INSERT INTO `st_role_authority` VALUES ('3868', '1', '56', '0', 'admin', null, '2021-01-06 10:59:55', '2021-01-15 06:31:22');
INSERT INTO `st_role_authority` VALUES ('3869', '1', '57', '0', 'admin', null, '2021-01-06 10:59:55', '2021-01-15 06:31:22');
INSERT INTO `st_role_authority` VALUES ('3870', '1', '185', '0', 'admin', null, '2021-01-06 10:59:55', '2021-01-15 06:31:22');
INSERT INTO `st_role_authority` VALUES ('3871', '1', '58', '0', 'admin', null, '2021-01-06 10:59:55', '2021-01-15 06:31:22');
INSERT INTO `st_role_authority` VALUES ('3872', '1', '59', '0', 'admin', null, '2021-01-06 10:59:55', '2021-01-15 06:31:22');
INSERT INTO `st_role_authority` VALUES ('3873', '1', '60', '0', 'admin', null, '2021-01-06 10:59:55', '2021-01-15 06:31:22');
INSERT INTO `st_role_authority` VALUES ('3874', '1', '61', '0', 'admin', null, '2021-01-06 10:59:55', '2021-01-15 06:31:22');
INSERT INTO `st_role_authority` VALUES ('3875', '1', '62', '0', 'admin', null, '2021-01-06 10:59:55', '2021-01-15 06:31:22');
INSERT INTO `st_role_authority` VALUES ('3876', '1', '63', '0', 'admin', null, '2021-01-06 10:59:55', '2021-01-15 06:31:22');
INSERT INTO `st_role_authority` VALUES ('3877', '1', '64', '0', 'admin', null, '2021-01-06 10:59:55', '2021-01-15 06:31:22');
INSERT INTO `st_role_authority` VALUES ('3878', '1', '65', '0', 'admin', null, '2021-01-06 10:59:55', '2021-01-15 06:31:22');
INSERT INTO `st_role_authority` VALUES ('3879', '1', '66', '0', 'admin', null, '2021-01-06 10:59:55', '2021-01-15 06:31:22');
INSERT INTO `st_role_authority` VALUES ('3880', '1', '67', '0', 'admin', null, '2021-01-06 10:59:55', '2021-01-15 06:31:22');
INSERT INTO `st_role_authority` VALUES ('3881', '1', '68', '0', 'admin', null, '2021-01-06 10:59:55', '2021-01-15 06:31:22');
INSERT INTO `st_role_authority` VALUES ('3882', '1', '69', '0', 'admin', null, '2021-01-06 10:59:55', '2021-01-15 06:31:22');
INSERT INTO `st_role_authority` VALUES ('3883', '1', '70', '0', 'admin', null, '2021-01-06 10:59:55', '2021-01-15 06:31:22');
INSERT INTO `st_role_authority` VALUES ('3884', '1', '71', '0', 'admin', null, '2021-01-06 10:59:55', '2021-01-15 06:31:22');
INSERT INTO `st_role_authority` VALUES ('3885', '1', '91', '0', 'admin', null, '2021-01-06 10:59:55', '2021-01-15 06:31:22');
INSERT INTO `st_role_authority` VALUES ('3886', '1', '107', '0', 'admin', null, '2021-01-06 10:59:55', '2021-01-15 06:31:22');
INSERT INTO `st_role_authority` VALUES ('3887', '1', '108', '0', 'admin', null, '2021-01-06 10:59:55', '2021-01-15 06:31:22');
INSERT INTO `st_role_authority` VALUES ('3888', '1', '109', '0', 'admin', null, '2021-01-06 10:59:55', '2021-01-15 06:31:22');
INSERT INTO `st_role_authority` VALUES ('3889', '1', '110', '0', 'admin', null, '2021-01-06 10:59:55', '2021-01-15 06:31:22');
INSERT INTO `st_role_authority` VALUES ('3890', '1', '111', '0', 'admin', null, '2021-01-06 10:59:55', '2021-01-15 06:31:22');
INSERT INTO `st_role_authority` VALUES ('3891', '1', '112', '0', 'admin', null, '2021-01-06 10:59:55', '2021-01-15 06:31:22');
INSERT INTO `st_role_authority` VALUES ('3892', '1', '113', '0', 'admin', null, '2021-01-06 10:59:55', '2021-01-15 06:31:22');
INSERT INTO `st_role_authority` VALUES ('3893', '1', '114', '0', 'admin', null, '2021-01-06 10:59:55', '2021-01-15 06:31:22');
INSERT INTO `st_role_authority` VALUES ('3894', '1', '0', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3895', '1', '1', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3896', '1', '2', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3897', '1', '3', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3898', '1', '4', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3899', '1', '5', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3900', '1', '6', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3901', '1', '7', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3902', '1', '8', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3903', '1', '9', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3904', '1', '10', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3905', '1', '11', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3906', '1', '12', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3907', '1', '13', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3908', '1', '14', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3909', '1', '15', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3910', '1', '16', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3911', '1', '17', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3912', '1', '18', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3913', '1', '38', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3914', '1', '39', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3915', '1', '40', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3916', '1', '43', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3917', '1', '44', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3918', '1', '45', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3919', '1', '173', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3920', '1', '174', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3921', '1', '175', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3922', '1', '176', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3923', '1', '177', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3924', '1', '50', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3925', '1', '178', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3926', '1', '179', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3927', '1', '180', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3928', '1', '181', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3929', '1', '182', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3930', '1', '183', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3931', '1', '56', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3932', '1', '184', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3933', '1', '57', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3934', '1', '58', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3935', '1', '59', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3936', '1', '60', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3937', '1', '61', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3938', '1', '62', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3939', '1', '63', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3940', '1', '64', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3941', '1', '65', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3942', '1', '66', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3943', '1', '67', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3944', '1', '68', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3945', '1', '69', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3946', '1', '70', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3947', '1', '71', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3948', '1', '91', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3949', '1', '107', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3950', '1', '108', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3951', '1', '109', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3952', '1', '110', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3953', '1', '111', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3954', '1', '112', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3955', '1', '113', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3956', '1', '114', '0', 'admin', null, '2021-01-15 06:31:22', '2021-01-15 07:50:57');
INSERT INTO `st_role_authority` VALUES ('3957', '1', '0', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('3958', '1', '128', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('3959', '1', '1', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('3960', '1', '129', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('3961', '1', '2', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('3962', '1', '130', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('3963', '1', '3', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('3964', '1', '131', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('3965', '1', '4', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('3966', '1', '132', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('3967', '1', '5', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('3968', '1', '133', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('3969', '1', '6', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('3970', '1', '134', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('3971', '1', '7', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('3972', '1', '135', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('3973', '1', '8', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('3974', '1', '136', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('3975', '1', '9', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('3976', '1', '137', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('3977', '1', '10', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('3978', '1', '138', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('3979', '1', '11', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('3980', '1', '139', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('3981', '1', '12', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('3982', '1', '140', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('3983', '1', '13', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('3984', '1', '141', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('3985', '1', '14', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('3986', '1', '142', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('3987', '1', '15', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('3988', '1', '143', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('3989', '1', '16', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('3990', '1', '144', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('3991', '1', '17', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('3992', '1', '145', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('3993', '1', '18', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('3994', '1', '146', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('3995', '1', '147', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('3996', '1', '154', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('3997', '1', '155', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('3998', '1', '38', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('3999', '1', '39', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('4000', '1', '40', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('4001', '1', '43', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('4002', '1', '44', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('4003', '1', '45', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('4004', '1', '50', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('4005', '1', '56', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('4006', '1', '57', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('4007', '1', '58', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('4008', '1', '59', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('4009', '1', '60', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('4010', '1', '61', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('4011', '1', '62', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('4012', '1', '63', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('4013', '1', '64', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('4014', '1', '65', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('4015', '1', '66', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('4016', '1', '67', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('4017', '1', '68', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('4018', '1', '69', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('4019', '1', '70', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('4020', '1', '71', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('4021', '1', '91', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('4022', '1', '107', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('4023', '1', '108', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('4024', '1', '109', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('4025', '1', '110', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('4026', '1', '111', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('4027', '1', '112', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('4028', '1', '113', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('4029', '1', '114', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('4030', '1', '123', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('4031', '1', '124', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('4032', '1', '125', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('4033', '1', '126', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('4034', '1', '127', '0', 'admin', null, '2021-01-15 07:50:57', '2021-01-15 08:25:25');
INSERT INTO `st_role_authority` VALUES ('4035', '1', '0', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4036', '1', '1', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4037', '1', '2', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4038', '1', '3', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4039', '1', '4', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4040', '1', '5', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4041', '1', '6', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4042', '1', '7', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4043', '1', '8', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4044', '1', '9', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4045', '1', '10', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4046', '1', '11', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4047', '1', '12', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4048', '1', '13', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4049', '1', '14', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4050', '1', '15', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4051', '1', '16', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4052', '1', '17', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4053', '1', '18', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4054', '1', '38', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4055', '1', '39', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4056', '1', '40', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4057', '1', '43', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4058', '1', '44', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4059', '1', '45', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4060', '1', '173', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4061', '1', '174', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4062', '1', '175', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4063', '1', '176', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4064', '1', '177', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4065', '1', '50', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4066', '1', '178', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4067', '1', '179', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4068', '1', '180', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4069', '1', '181', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4070', '1', '182', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4071', '1', '183', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4072', '1', '56', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4073', '1', '184', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4074', '1', '57', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4075', '1', '58', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4076', '1', '59', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4077', '1', '60', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4078', '1', '61', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4079', '1', '62', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4080', '1', '63', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4081', '1', '64', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4082', '1', '65', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4083', '1', '66', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4084', '1', '67', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4085', '1', '68', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4086', '1', '69', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4087', '1', '70', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4088', '1', '71', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4089', '1', '91', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4090', '1', '107', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4091', '1', '108', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4092', '1', '109', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4093', '1', '110', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4094', '1', '111', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4095', '1', '112', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4096', '1', '113', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4097', '1', '114', '0', 'admin', null, '2021-01-15 08:25:25', '2021-02-02 09:20:02');
INSERT INTO `st_role_authority` VALUES ('4098', '1', '0', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4099', '1', '128', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4100', '1', '1', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4101', '1', '129', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4102', '1', '2', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4103', '1', '130', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4104', '1', '3', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4105', '1', '131', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4106', '1', '4', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4107', '1', '132', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4108', '1', '5', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4109', '1', '133', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4110', '1', '6', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4111', '1', '134', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4112', '1', '7', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4113', '1', '135', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4114', '1', '8', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4115', '1', '136', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4116', '1', '9', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4117', '1', '137', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4118', '1', '10', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4119', '1', '138', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4120', '1', '11', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4121', '1', '139', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4122', '1', '12', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4123', '1', '140', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4124', '1', '13', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4125', '1', '141', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4126', '1', '14', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4127', '1', '142', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4128', '1', '15', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4129', '1', '143', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4130', '1', '16', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4131', '1', '144', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4132', '1', '17', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4133', '1', '145', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4134', '1', '18', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4135', '1', '146', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4136', '1', '147', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4137', '1', '154', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4138', '1', '155', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4139', '1', '38', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4140', '1', '39', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4141', '1', '40', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4142', '1', '43', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4143', '1', '44', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4144', '1', '45', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4145', '1', '50', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4146', '1', '56', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4147', '1', '57', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4148', '1', '58', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4149', '1', '59', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4150', '1', '60', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4151', '1', '61', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4152', '1', '62', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4153', '1', '63', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4154', '1', '64', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4155', '1', '65', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4156', '1', '66', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4157', '1', '67', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4158', '1', '68', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4159', '1', '69', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4160', '1', '70', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4161', '1', '71', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4162', '1', '91', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4163', '1', '107', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4164', '1', '108', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4165', '1', '109', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4166', '1', '110', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4167', '1', '111', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4168', '1', '112', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4169', '1', '113', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4170', '1', '114', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4171', '1', '123', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4172', '1', '124', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4173', '1', '125', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4174', '1', '126', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4175', '1', '127', '0', 'admin', null, '2021-02-02 09:20:02', '2021-02-02 09:20:24');
INSERT INTO `st_role_authority` VALUES ('4176', '1', '0', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4177', '1', '128', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4178', '1', '1', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4179', '1', '129', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4180', '1', '2', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4181', '1', '130', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4182', '1', '3', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4183', '1', '131', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4184', '1', '4', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4185', '1', '132', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4186', '1', '5', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4187', '1', '133', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4188', '1', '6', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4189', '1', '134', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4190', '1', '7', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4191', '1', '135', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4192', '1', '8', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4193', '1', '136', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4194', '1', '9', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4195', '1', '137', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4196', '1', '10', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4197', '1', '138', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4198', '1', '11', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4199', '1', '139', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4200', '1', '12', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4201', '1', '140', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4202', '1', '13', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4203', '1', '141', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4204', '1', '14', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4205', '1', '142', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4206', '1', '15', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4207', '1', '143', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4208', '1', '16', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4209', '1', '144', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4210', '1', '17', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4211', '1', '145', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4212', '1', '18', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4213', '1', '146', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4214', '1', '147', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4215', '1', '154', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4216', '1', '155', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4217', '1', '38', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4218', '1', '39', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4219', '1', '40', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4220', '1', '43', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4221', '1', '44', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4222', '1', '45', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4223', '1', '50', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4224', '1', '56', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4225', '1', '57', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4226', '1', '58', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4227', '1', '59', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4228', '1', '60', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4229', '1', '61', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4230', '1', '62', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4231', '1', '63', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4232', '1', '64', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4233', '1', '65', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4234', '1', '66', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4235', '1', '67', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4236', '1', '68', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4237', '1', '69', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4238', '1', '70', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4239', '1', '71', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4240', '1', '91', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4241', '1', '107', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4242', '1', '108', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4243', '1', '109', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4244', '1', '110', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4245', '1', '111', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4246', '1', '112', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4247', '1', '113', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4248', '1', '114', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4249', '1', '123', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4250', '1', '124', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4251', '1', '125', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4252', '1', '126', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4253', '1', '127', '0', 'admin', null, '2021-02-02 09:20:24', '2021-02-19 04:55:36');
INSERT INTO `st_role_authority` VALUES ('4254', '1', '0', '0', 'admin', null, '2021-02-19 04:55:36', '2021-02-19 06:14:04');
INSERT INTO `st_role_authority` VALUES ('4255', '1', '1', '0', 'admin', null, '2021-02-19 04:55:36', '2021-02-19 06:14:04');
INSERT INTO `st_role_authority` VALUES ('4256', '1', '2', '0', 'admin', null, '2021-02-19 04:55:36', '2021-02-19 06:14:04');
INSERT INTO `st_role_authority` VALUES ('4257', '1', '66', '0', 'admin', null, '2021-02-19 04:55:36', '2021-02-19 06:14:04');
INSERT INTO `st_role_authority` VALUES ('4258', '1', '3', '0', 'admin', null, '2021-02-19 04:55:36', '2021-02-19 06:14:04');
INSERT INTO `st_role_authority` VALUES ('4259', '1', '67', '0', 'admin', null, '2021-02-19 04:55:36', '2021-02-19 06:14:04');
INSERT INTO `st_role_authority` VALUES ('4260', '1', '4', '0', 'admin', null, '2021-02-19 04:55:36', '2021-02-19 06:14:04');
INSERT INTO `st_role_authority` VALUES ('4261', '1', '68', '0', 'admin', null, '2021-02-19 04:55:36', '2021-02-19 06:14:04');
INSERT INTO `st_role_authority` VALUES ('4262', '1', '5', '0', 'admin', null, '2021-02-19 04:55:36', '2021-02-19 06:14:04');
INSERT INTO `st_role_authority` VALUES ('4263', '1', '69', '0', 'admin', null, '2021-02-19 04:55:36', '2021-02-19 06:14:04');
INSERT INTO `st_role_authority` VALUES ('4264', '1', '6', '0', 'admin', null, '2021-02-19 04:55:36', '2021-02-19 06:14:04');
INSERT INTO `st_role_authority` VALUES ('4265', '1', '70', '0', 'admin', null, '2021-02-19 04:55:36', '2021-02-19 06:14:04');
INSERT INTO `st_role_authority` VALUES ('4266', '1', '7', '0', 'admin', null, '2021-02-19 04:55:36', '2021-02-19 06:14:04');
INSERT INTO `st_role_authority` VALUES ('4267', '1', '71', '0', 'admin', null, '2021-02-19 04:55:36', '2021-02-19 06:14:04');
INSERT INTO `st_role_authority` VALUES ('4268', '1', '8', '0', 'admin', null, '2021-02-19 04:55:36', '2021-02-19 06:14:04');
INSERT INTO `st_role_authority` VALUES ('4269', '1', '9', '0', 'admin', null, '2021-02-19 04:55:36', '2021-02-19 06:14:04');
INSERT INTO `st_role_authority` VALUES ('4270', '1', '10', '0', 'admin', null, '2021-02-19 04:55:36', '2021-02-19 06:14:04');
INSERT INTO `st_role_authority` VALUES ('4271', '1', '11', '0', 'admin', null, '2021-02-19 04:55:36', '2021-02-19 06:14:04');
INSERT INTO `st_role_authority` VALUES ('4272', '1', '12', '0', 'admin', null, '2021-02-19 04:55:36', '2021-02-19 06:14:04');
INSERT INTO `st_role_authority` VALUES ('4273', '1', '13', '0', 'admin', null, '2021-02-19 04:55:36', '2021-02-19 06:14:04');
INSERT INTO `st_role_authority` VALUES ('4274', '1', '14', '0', 'admin', null, '2021-02-19 04:55:36', '2021-02-19 06:14:04');
INSERT INTO `st_role_authority` VALUES ('4275', '1', '15', '0', 'admin', null, '2021-02-19 04:55:36', '2021-02-19 06:14:04');
INSERT INTO `st_role_authority` VALUES ('4276', '1', '16', '0', 'admin', null, '2021-02-19 04:55:36', '2021-02-19 06:14:04');
INSERT INTO `st_role_authority` VALUES ('4277', '1', '17', '0', 'admin', null, '2021-02-19 04:55:36', '2021-02-19 06:14:04');
INSERT INTO `st_role_authority` VALUES ('4278', '1', '18', '0', 'admin', null, '2021-02-19 04:55:36', '2021-02-19 06:14:04');
INSERT INTO `st_role_authority` VALUES ('4279', '1', '148', '0', 'admin', null, '2021-02-19 04:55:36', '2021-02-19 06:14:04');
INSERT INTO `st_role_authority` VALUES ('4280', '1', '149', '0', 'admin', null, '2021-02-19 04:55:36', '2021-02-19 06:14:04');
INSERT INTO `st_role_authority` VALUES ('4281', '1', '150', '0', 'admin', null, '2021-02-19 04:55:36', '2021-02-19 06:14:04');
INSERT INTO `st_role_authority` VALUES ('4282', '1', '151', '0', 'admin', null, '2021-02-19 04:55:36', '2021-02-19 06:14:04');
INSERT INTO `st_role_authority` VALUES ('4283', '1', '152', '0', 'admin', null, '2021-02-19 04:55:36', '2021-02-19 06:14:04');
INSERT INTO `st_role_authority` VALUES ('4284', '1', '153', '0', 'admin', null, '2021-02-19 04:55:36', '2021-02-19 06:14:04');
INSERT INTO `st_role_authority` VALUES ('4285', '1', '91', '0', 'admin', null, '2021-02-19 04:55:36', '2021-02-19 06:14:04');
INSERT INTO `st_role_authority` VALUES ('4286', '1', '38', '0', 'admin', null, '2021-02-19 04:55:36', '2021-02-19 06:14:04');
INSERT INTO `st_role_authority` VALUES ('4287', '1', '39', '0', 'admin', null, '2021-02-19 04:55:36', '2021-02-19 06:14:04');
INSERT INTO `st_role_authority` VALUES ('4288', '1', '40', '0', 'admin', null, '2021-02-19 04:55:36', '2021-02-19 06:14:04');
INSERT INTO `st_role_authority` VALUES ('4289', '1', '43', '0', 'admin', null, '2021-02-19 04:55:36', '2021-02-19 06:14:04');
INSERT INTO `st_role_authority` VALUES ('4290', '1', '44', '0', 'admin', null, '2021-02-19 04:55:36', '2021-02-19 06:14:04');
INSERT INTO `st_role_authority` VALUES ('4291', '1', '45', '0', 'admin', null, '2021-02-19 04:55:36', '2021-02-19 06:14:04');
INSERT INTO `st_role_authority` VALUES ('4292', '1', '50', '0', 'admin', null, '2021-02-19 04:55:36', '2021-02-19 06:14:04');
INSERT INTO `st_role_authority` VALUES ('4293', '1', '56', '0', 'admin', null, '2021-02-19 04:55:36', '2021-02-19 06:14:04');
INSERT INTO `st_role_authority` VALUES ('4294', '1', '57', '0', 'admin', null, '2021-02-19 04:55:36', '2021-02-19 06:14:04');
INSERT INTO `st_role_authority` VALUES ('4295', '1', '58', '0', 'admin', null, '2021-02-19 04:55:36', '2021-02-19 06:14:04');
INSERT INTO `st_role_authority` VALUES ('4296', '1', '59', '0', 'admin', null, '2021-02-19 04:55:36', '2021-02-19 06:14:04');
INSERT INTO `st_role_authority` VALUES ('4297', '1', '60', '0', 'admin', null, '2021-02-19 04:55:36', '2021-02-19 06:14:04');
INSERT INTO `st_role_authority` VALUES ('4298', '1', '61', '0', 'admin', null, '2021-02-19 04:55:36', '2021-02-19 06:14:04');
INSERT INTO `st_role_authority` VALUES ('4299', '1', '0', '0', 'admin', null, '2021-02-19 06:14:04', '2021-02-19 06:35:28');
INSERT INTO `st_role_authority` VALUES ('4300', '1', '1', '0', 'admin', null, '2021-02-19 06:14:04', '2021-02-19 06:35:28');
INSERT INTO `st_role_authority` VALUES ('4301', '1', '2', '0', 'admin', null, '2021-02-19 06:14:04', '2021-02-19 06:35:28');
INSERT INTO `st_role_authority` VALUES ('4302', '1', '66', '0', 'admin', null, '2021-02-19 06:14:04', '2021-02-19 06:35:28');
INSERT INTO `st_role_authority` VALUES ('4303', '1', '3', '0', 'admin', null, '2021-02-19 06:14:04', '2021-02-19 06:35:28');
INSERT INTO `st_role_authority` VALUES ('4304', '1', '67', '0', 'admin', null, '2021-02-19 06:14:04', '2021-02-19 06:35:28');
INSERT INTO `st_role_authority` VALUES ('4305', '1', '4', '0', 'admin', null, '2021-02-19 06:14:04', '2021-02-19 06:35:28');
INSERT INTO `st_role_authority` VALUES ('4306', '1', '68', '0', 'admin', null, '2021-02-19 06:14:04', '2021-02-19 06:35:28');
INSERT INTO `st_role_authority` VALUES ('4307', '1', '5', '0', 'admin', null, '2021-02-19 06:14:04', '2021-02-19 06:35:28');
INSERT INTO `st_role_authority` VALUES ('4308', '1', '69', '0', 'admin', null, '2021-02-19 06:14:04', '2021-02-19 06:35:28');
INSERT INTO `st_role_authority` VALUES ('4309', '1', '6', '0', 'admin', null, '2021-02-19 06:14:04', '2021-02-19 06:35:28');
INSERT INTO `st_role_authority` VALUES ('4310', '1', '70', '0', 'admin', null, '2021-02-19 06:14:04', '2021-02-19 06:35:28');
INSERT INTO `st_role_authority` VALUES ('4311', '1', '7', '0', 'admin', null, '2021-02-19 06:14:04', '2021-02-19 06:35:28');
INSERT INTO `st_role_authority` VALUES ('4312', '1', '71', '0', 'admin', null, '2021-02-19 06:14:04', '2021-02-19 06:35:28');
INSERT INTO `st_role_authority` VALUES ('4313', '1', '8', '0', 'admin', null, '2021-02-19 06:14:04', '2021-02-19 06:35:28');
INSERT INTO `st_role_authority` VALUES ('4314', '1', '9', '0', 'admin', null, '2021-02-19 06:14:04', '2021-02-19 06:35:28');
INSERT INTO `st_role_authority` VALUES ('4315', '1', '10', '0', 'admin', null, '2021-02-19 06:14:04', '2021-02-19 06:35:28');
INSERT INTO `st_role_authority` VALUES ('4316', '1', '11', '0', 'admin', null, '2021-02-19 06:14:04', '2021-02-19 06:35:28');
INSERT INTO `st_role_authority` VALUES ('4317', '1', '12', '0', 'admin', null, '2021-02-19 06:14:04', '2021-02-19 06:35:28');
INSERT INTO `st_role_authority` VALUES ('4318', '1', '13', '0', 'admin', null, '2021-02-19 06:14:04', '2021-02-19 06:35:28');
INSERT INTO `st_role_authority` VALUES ('4319', '1', '14', '0', 'admin', null, '2021-02-19 06:14:04', '2021-02-19 06:35:28');
INSERT INTO `st_role_authority` VALUES ('4320', '1', '15', '0', 'admin', null, '2021-02-19 06:14:04', '2021-02-19 06:35:28');
INSERT INTO `st_role_authority` VALUES ('4321', '1', '16', '0', 'admin', null, '2021-02-19 06:14:04', '2021-02-19 06:35:28');
INSERT INTO `st_role_authority` VALUES ('4322', '1', '17', '0', 'admin', null, '2021-02-19 06:14:04', '2021-02-19 06:35:28');
INSERT INTO `st_role_authority` VALUES ('4323', '1', '18', '0', 'admin', null, '2021-02-19 06:14:04', '2021-02-19 06:35:28');
INSERT INTO `st_role_authority` VALUES ('4324', '1', '148', '0', 'admin', null, '2021-02-19 06:14:04', '2021-02-19 06:35:28');
INSERT INTO `st_role_authority` VALUES ('4325', '1', '149', '0', 'admin', null, '2021-02-19 06:14:04', '2021-02-19 06:35:28');
INSERT INTO `st_role_authority` VALUES ('4326', '1', '151', '0', 'admin', null, '2021-02-19 06:14:04', '2021-02-19 06:35:28');
INSERT INTO `st_role_authority` VALUES ('4327', '1', '91', '0', 'admin', null, '2021-02-19 06:14:04', '2021-02-19 06:35:28');
INSERT INTO `st_role_authority` VALUES ('4328', '1', '38', '0', 'admin', null, '2021-02-19 06:14:04', '2021-02-19 06:35:28');
INSERT INTO `st_role_authority` VALUES ('4329', '1', '39', '0', 'admin', null, '2021-02-19 06:14:04', '2021-02-19 06:35:28');
INSERT INTO `st_role_authority` VALUES ('4330', '1', '40', '0', 'admin', null, '2021-02-19 06:14:04', '2021-02-19 06:35:28');
INSERT INTO `st_role_authority` VALUES ('4331', '1', '43', '0', 'admin', null, '2021-02-19 06:14:04', '2021-02-19 06:35:28');
INSERT INTO `st_role_authority` VALUES ('4332', '1', '44', '0', 'admin', null, '2021-02-19 06:14:04', '2021-02-19 06:35:28');
INSERT INTO `st_role_authority` VALUES ('4333', '1', '45', '0', 'admin', null, '2021-02-19 06:14:04', '2021-02-19 06:35:28');
INSERT INTO `st_role_authority` VALUES ('4334', '1', '50', '0', 'admin', null, '2021-02-19 06:14:04', '2021-02-19 06:35:28');
INSERT INTO `st_role_authority` VALUES ('4335', '1', '56', '0', 'admin', null, '2021-02-19 06:14:04', '2021-02-19 06:35:28');
INSERT INTO `st_role_authority` VALUES ('4336', '1', '57', '0', 'admin', null, '2021-02-19 06:14:04', '2021-02-19 06:35:28');
INSERT INTO `st_role_authority` VALUES ('4337', '1', '58', '0', 'admin', null, '2021-02-19 06:14:04', '2021-02-19 06:35:28');
INSERT INTO `st_role_authority` VALUES ('4338', '1', '59', '0', 'admin', null, '2021-02-19 06:14:04', '2021-02-19 06:35:28');
INSERT INTO `st_role_authority` VALUES ('4339', '1', '60', '0', 'admin', null, '2021-02-19 06:14:04', '2021-02-19 06:35:28');
INSERT INTO `st_role_authority` VALUES ('4340', '1', '61', '0', 'admin', null, '2021-02-19 06:14:04', '2021-02-19 06:35:28');
INSERT INTO `st_role_authority` VALUES ('4341', '1', '0', '0', 'admin', null, '2021-02-19 06:35:28', '2021-02-19 06:35:55');
INSERT INTO `st_role_authority` VALUES ('4342', '1', '1', '0', 'admin', null, '2021-02-19 06:35:28', '2021-02-19 06:35:55');
INSERT INTO `st_role_authority` VALUES ('4343', '1', '2', '0', 'admin', null, '2021-02-19 06:35:28', '2021-02-19 06:35:55');
INSERT INTO `st_role_authority` VALUES ('4344', '1', '66', '0', 'admin', null, '2021-02-19 06:35:28', '2021-02-19 06:35:55');
INSERT INTO `st_role_authority` VALUES ('4345', '1', '3', '0', 'admin', null, '2021-02-19 06:35:28', '2021-02-19 06:35:55');
INSERT INTO `st_role_authority` VALUES ('4346', '1', '67', '0', 'admin', null, '2021-02-19 06:35:28', '2021-02-19 06:35:55');
INSERT INTO `st_role_authority` VALUES ('4347', '1', '4', '0', 'admin', null, '2021-02-19 06:35:28', '2021-02-19 06:35:55');
INSERT INTO `st_role_authority` VALUES ('4348', '1', '68', '0', 'admin', null, '2021-02-19 06:35:28', '2021-02-19 06:35:55');
INSERT INTO `st_role_authority` VALUES ('4349', '1', '5', '0', 'admin', null, '2021-02-19 06:35:28', '2021-02-19 06:35:55');
INSERT INTO `st_role_authority` VALUES ('4350', '1', '69', '0', 'admin', null, '2021-02-19 06:35:28', '2021-02-19 06:35:55');
INSERT INTO `st_role_authority` VALUES ('4351', '1', '6', '0', 'admin', null, '2021-02-19 06:35:28', '2021-02-19 06:35:55');
INSERT INTO `st_role_authority` VALUES ('4352', '1', '70', '0', 'admin', null, '2021-02-19 06:35:28', '2021-02-19 06:35:55');
INSERT INTO `st_role_authority` VALUES ('4353', '1', '7', '0', 'admin', null, '2021-02-19 06:35:28', '2021-02-19 06:35:55');
INSERT INTO `st_role_authority` VALUES ('4354', '1', '71', '0', 'admin', null, '2021-02-19 06:35:28', '2021-02-19 06:35:55');
INSERT INTO `st_role_authority` VALUES ('4355', '1', '8', '0', 'admin', null, '2021-02-19 06:35:28', '2021-02-19 06:35:55');
INSERT INTO `st_role_authority` VALUES ('4356', '1', '9', '0', 'admin', null, '2021-02-19 06:35:28', '2021-02-19 06:35:55');
INSERT INTO `st_role_authority` VALUES ('4357', '1', '10', '0', 'admin', null, '2021-02-19 06:35:28', '2021-02-19 06:35:55');
INSERT INTO `st_role_authority` VALUES ('4358', '1', '11', '0', 'admin', null, '2021-02-19 06:35:28', '2021-02-19 06:35:55');
INSERT INTO `st_role_authority` VALUES ('4359', '1', '12', '0', 'admin', null, '2021-02-19 06:35:28', '2021-02-19 06:35:55');
INSERT INTO `st_role_authority` VALUES ('4360', '1', '13', '0', 'admin', null, '2021-02-19 06:35:28', '2021-02-19 06:35:55');
INSERT INTO `st_role_authority` VALUES ('4361', '1', '14', '0', 'admin', null, '2021-02-19 06:35:28', '2021-02-19 06:35:55');
INSERT INTO `st_role_authority` VALUES ('4362', '1', '15', '0', 'admin', null, '2021-02-19 06:35:28', '2021-02-19 06:35:55');
INSERT INTO `st_role_authority` VALUES ('4363', '1', '16', '0', 'admin', null, '2021-02-19 06:35:28', '2021-02-19 06:35:55');
INSERT INTO `st_role_authority` VALUES ('4364', '1', '17', '0', 'admin', null, '2021-02-19 06:35:28', '2021-02-19 06:35:55');
INSERT INTO `st_role_authority` VALUES ('4365', '1', '18', '0', 'admin', null, '2021-02-19 06:35:28', '2021-02-19 06:35:55');
INSERT INTO `st_role_authority` VALUES ('4366', '1', '148', '0', 'admin', null, '2021-02-19 06:35:28', '2021-02-19 06:35:55');
INSERT INTO `st_role_authority` VALUES ('4367', '1', '149', '0', 'admin', null, '2021-02-19 06:35:28', '2021-02-19 06:35:55');
INSERT INTO `st_role_authority` VALUES ('4368', '1', '150', '0', 'admin', null, '2021-02-19 06:35:28', '2021-02-19 06:35:55');
INSERT INTO `st_role_authority` VALUES ('4369', '1', '151', '0', 'admin', null, '2021-02-19 06:35:28', '2021-02-19 06:35:55');
INSERT INTO `st_role_authority` VALUES ('4370', '1', '152', '0', 'admin', null, '2021-02-19 06:35:28', '2021-02-19 06:35:55');
INSERT INTO `st_role_authority` VALUES ('4371', '1', '153', '0', 'admin', null, '2021-02-19 06:35:28', '2021-02-19 06:35:55');
INSERT INTO `st_role_authority` VALUES ('4372', '1', '91', '0', 'admin', null, '2021-02-19 06:35:28', '2021-02-19 06:35:55');
INSERT INTO `st_role_authority` VALUES ('4373', '1', '38', '0', 'admin', null, '2021-02-19 06:35:28', '2021-02-19 06:35:55');
INSERT INTO `st_role_authority` VALUES ('4374', '1', '39', '0', 'admin', null, '2021-02-19 06:35:28', '2021-02-19 06:35:55');
INSERT INTO `st_role_authority` VALUES ('4375', '1', '40', '0', 'admin', null, '2021-02-19 06:35:28', '2021-02-19 06:35:55');
INSERT INTO `st_role_authority` VALUES ('4376', '1', '43', '0', 'admin', null, '2021-02-19 06:35:28', '2021-02-19 06:35:55');
INSERT INTO `st_role_authority` VALUES ('4377', '1', '44', '0', 'admin', null, '2021-02-19 06:35:28', '2021-02-19 06:35:55');
INSERT INTO `st_role_authority` VALUES ('4378', '1', '45', '0', 'admin', null, '2021-02-19 06:35:28', '2021-02-19 06:35:55');
INSERT INTO `st_role_authority` VALUES ('4379', '1', '50', '0', 'admin', null, '2021-02-19 06:35:28', '2021-02-19 06:35:55');
INSERT INTO `st_role_authority` VALUES ('4380', '1', '56', '0', 'admin', null, '2021-02-19 06:35:28', '2021-02-19 06:35:55');
INSERT INTO `st_role_authority` VALUES ('4381', '1', '57', '0', 'admin', null, '2021-02-19 06:35:28', '2021-02-19 06:35:55');
INSERT INTO `st_role_authority` VALUES ('4382', '1', '58', '0', 'admin', null, '2021-02-19 06:35:28', '2021-02-19 06:35:55');
INSERT INTO `st_role_authority` VALUES ('4383', '1', '59', '0', 'admin', null, '2021-02-19 06:35:28', '2021-02-19 06:35:55');
INSERT INTO `st_role_authority` VALUES ('4384', '1', '60', '0', 'admin', null, '2021-02-19 06:35:28', '2021-02-19 06:35:55');
INSERT INTO `st_role_authority` VALUES ('4385', '1', '61', '0', 'admin', null, '2021-02-19 06:35:28', '2021-02-19 06:35:55');
INSERT INTO `st_role_authority` VALUES ('4386', '1', '0', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4387', '1', '128', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4388', '1', '1', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4389', '1', '129', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4390', '1', '2', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4391', '1', '130', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4392', '1', '3', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4393', '1', '131', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4394', '1', '4', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4395', '1', '132', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4396', '1', '5', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4397', '1', '133', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4398', '1', '6', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4399', '1', '134', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4400', '1', '7', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4401', '1', '135', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4402', '1', '8', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4403', '1', '136', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4404', '1', '9', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4405', '1', '137', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4406', '1', '10', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4407', '1', '138', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4408', '1', '11', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4409', '1', '139', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4410', '1', '12', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4411', '1', '140', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4412', '1', '13', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4413', '1', '141', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4414', '1', '14', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4415', '1', '142', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4416', '1', '15', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4417', '1', '143', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4418', '1', '16', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4419', '1', '144', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4420', '1', '17', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4421', '1', '145', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4422', '1', '18', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4423', '1', '146', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4424', '1', '147', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4425', '1', '148', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4426', '1', '149', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4427', '1', '150', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4428', '1', '151', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4429', '1', '152', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4430', '1', '153', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4431', '1', '154', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4432', '1', '155', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4433', '1', '38', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4434', '1', '39', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4435', '1', '40', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4436', '1', '43', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4437', '1', '44', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4438', '1', '45', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4439', '1', '50', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4440', '1', '56', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4441', '1', '57', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4442', '1', '58', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4443', '1', '59', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4444', '1', '60', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4445', '1', '61', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4446', '1', '66', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4447', '1', '67', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4448', '1', '68', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4449', '1', '69', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4450', '1', '70', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4451', '1', '71', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4452', '1', '91', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4453', '1', '123', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4454', '1', '124', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4455', '1', '125', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4456', '1', '126', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4457', '1', '127', '0', 'admin', null, '2021-02-19 06:35:55', '2021-02-19 06:36:43');
INSERT INTO `st_role_authority` VALUES ('4458', '1', '0', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4459', '1', '128', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4460', '1', '1', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4461', '1', '129', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4462', '1', '2', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4463', '1', '130', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4464', '1', '3', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4465', '1', '131', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4466', '1', '4', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4467', '1', '132', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4468', '1', '5', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4469', '1', '133', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4470', '1', '6', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4471', '1', '134', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4472', '1', '7', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4473', '1', '135', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4474', '1', '8', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4475', '1', '136', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4476', '1', '9', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4477', '1', '137', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4478', '1', '10', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4479', '1', '138', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4480', '1', '11', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4481', '1', '139', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4482', '1', '12', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4483', '1', '140', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4484', '1', '13', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4485', '1', '141', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4486', '1', '14', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4487', '1', '142', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4488', '1', '15', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4489', '1', '143', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4490', '1', '16', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4491', '1', '144', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4492', '1', '17', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4493', '1', '145', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4494', '1', '18', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4495', '1', '146', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4496', '1', '147', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4497', '1', '148', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4498', '1', '149', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4499', '1', '150', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4500', '1', '151', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4501', '1', '152', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4502', '1', '153', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4503', '1', '154', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4504', '1', '155', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4505', '1', '38', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4506', '1', '39', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4507', '1', '40', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4508', '1', '43', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4509', '1', '44', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4510', '1', '45', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4511', '1', '50', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4512', '1', '56', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4513', '1', '57', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4514', '1', '58', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4515', '1', '59', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4516', '1', '60', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4517', '1', '61', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4518', '1', '66', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4519', '1', '67', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4520', '1', '68', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4521', '1', '69', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4522', '1', '70', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4523', '1', '71', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4524', '1', '91', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4525', '1', '107', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4526', '1', '108', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4527', '1', '109', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4528', '1', '110', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4529', '1', '111', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4530', '1', '112', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4531', '1', '113', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4532', '1', '114', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4533', '1', '123', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4534', '1', '124', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4535', '1', '125', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4536', '1', '126', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4537', '1', '127', '0', 'admin', null, '2021-02-19 06:36:43', '2021-07-02 06:47:56');
INSERT INTO `st_role_authority` VALUES ('4538', '1', '0', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4539', '1', '128', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4540', '1', '1', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4541', '1', '129', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4542', '1', '2', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4543', '1', '130', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4544', '1', '3', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4545', '1', '131', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4546', '1', '4', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4547', '1', '132', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4548', '1', '5', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4549', '1', '133', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4550', '1', '6', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4551', '1', '134', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4552', '1', '7', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4553', '1', '135', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4554', '1', '8', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4555', '1', '136', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4556', '1', '9', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4557', '1', '137', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4558', '1', '10', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4559', '1', '138', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4560', '1', '11', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4561', '1', '139', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4562', '1', '12', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4563', '1', '140', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4564', '1', '13', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4565', '1', '141', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4566', '1', '14', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4567', '1', '142', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4568', '1', '15', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4569', '1', '143', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4570', '1', '16', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4571', '1', '144', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4572', '1', '17', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4573', '1', '145', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4574', '1', '18', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4575', '1', '146', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4576', '1', '147', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4577', '1', '148', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4578', '1', '149', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4579', '1', '150', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4580', '1', '151', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4581', '1', '152', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4582', '1', '153', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4583', '1', '154', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4584', '1', '155', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4585', '1', '38', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4586', '1', '39', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4587', '1', '40', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4588', '1', '43', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4589', '1', '44', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4590', '1', '45', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4591', '1', '173', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4592', '1', '174', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4593', '1', '175', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4594', '1', '176', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4595', '1', '177', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4596', '1', '50', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4597', '1', '178', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4598', '1', '179', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4599', '1', '180', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4600', '1', '181', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4601', '1', '182', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4602', '1', '183', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4603', '1', '56', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4604', '1', '184', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4605', '1', '57', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4606', '1', '58', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4607', '1', '59', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4608', '1', '60', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4609', '1', '61', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4610', '1', '66', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4611', '1', '67', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4612', '1', '68', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4613', '1', '69', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4614', '1', '70', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4615', '1', '71', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4616', '1', '91', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4617', '1', '107', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4618', '1', '108', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4619', '1', '109', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4620', '1', '110', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4621', '1', '111', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4622', '1', '112', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4623', '1', '113', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4624', '1', '114', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4625', '1', '123', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4626', '1', '124', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4627', '1', '125', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4628', '1', '126', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4629', '1', '127', '0', 'admin', null, '2021-07-02 06:47:56', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4630', '1', '0', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4631', '1', '128', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4632', '1', '1', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4633', '1', '129', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4634', '1', '2', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4635', '1', '130', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4636', '1', '3', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4637', '1', '131', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4638', '1', '4', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4639', '1', '132', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4640', '1', '5', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4641', '1', '133', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4642', '1', '6', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4643', '1', '134', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4644', '1', '7', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4645', '1', '135', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4646', '1', '8', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4647', '1', '136', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4648', '1', '9', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4649', '1', '137', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4650', '1', '10', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4651', '1', '138', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4652', '1', '11', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4653', '1', '139', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4654', '1', '12', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4655', '1', '140', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4656', '1', '13', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4657', '1', '141', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4658', '1', '14', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4659', '1', '142', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4660', '1', '15', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4661', '1', '143', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4662', '1', '16', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4663', '1', '144', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4664', '1', '17', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4665', '1', '145', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4666', '1', '18', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4667', '1', '146', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4668', '1', '147', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4669', '1', '148', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4670', '1', '149', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4671', '1', '150', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4672', '1', '151', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4673', '1', '152', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4674', '1', '153', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4675', '1', '154', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4676', '1', '155', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4677', '1', '38', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4678', '1', '39', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4679', '1', '40', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4680', '1', '43', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4681', '1', '44', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4682', '1', '45', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4683', '1', '50', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4684', '1', '56', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4685', '1', '57', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4686', '1', '58', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4687', '1', '59', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4688', '1', '60', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4689', '1', '61', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4690', '1', '66', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4691', '1', '67', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4692', '1', '68', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4693', '1', '69', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4694', '1', '70', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4695', '1', '71', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4696', '1', '91', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4697', '1', '107', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4698', '1', '108', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4699', '1', '109', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4700', '1', '110', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4701', '1', '111', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4702', '1', '112', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4703', '1', '113', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4704', '1', '114', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4705', '1', '123', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4706', '1', '124', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4707', '1', '125', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4708', '1', '126', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');
INSERT INTO `st_role_authority` VALUES ('4709', '1', '127', '1', 'admin', 'admin', '2021-07-02 06:51:34', '2021-07-02 06:51:34');

-- ----------------------------
-- Table structure for `st_schedule_job`
-- ----------------------------
DROP TABLE IF EXISTS `st_schedule_job`;
CREATE TABLE `st_schedule_job` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `job_id` varchar(255) COLLATE utf8_bin DEFAULT '' COMMENT 'JobID',
  `job_name` varchar(255) COLLATE utf8_bin DEFAULT '' COMMENT 'Job名称',
  `cron` varchar(255) COLLATE utf8_bin DEFAULT '' COMMENT 'cron表达式',
  `start_job` bit(1) DEFAULT b'0' COMMENT '启动状态',
  `job_class` varchar(255) COLLATE utf8_bin DEFAULT '' COMMENT '方法',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `fire_time` datetime DEFAULT NULL COMMENT '触发时间',
  `last_fire_time` datetime DEFAULT NULL COMMENT '上次触发时间',
  `next_fire_time` datetime DEFAULT NULL COMMENT '下次触发时间',
  `job_desc` varchar(255) COLLATE utf8_bin DEFAULT '' COMMENT 'Job描述',
  `fail_reason` varchar(255) COLLATE utf8_bin DEFAULT '' COMMENT '失败原因',
  `yn_flag` char(2) COLLATE utf8_bin DEFAULT '1' COMMENT '有效标志',
  `creator` varchar(32) COLLATE utf8_bin DEFAULT '' COMMENT '创建人',
  `editor` varchar(32) COLLATE utf8_bin DEFAULT '' COMMENT '修改人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='定时任务';

-- ----------------------------
-- Records of st_schedule_job
-- ----------------------------
INSERT INTO `st_schedule_job` VALUES ('1', 'StoryDemoJob', '框架演示任务', '0 0/5 * * * ?', '', 'com.story.storyadmin.scheduler.StoryDemoJob', '2019-08-19 06:14:06', '2020-09-10 07:55:00', '2020-09-10 07:50:00', '2020-09-10 08:00:00', '演示，仅此而已', null, '1', 'admin', 'admin', '2019-08-19 06:14:20', '2020-09-10 07:57:08');
INSERT INTO `st_schedule_job` VALUES ('2', 'DbBackupJob', '数据库定时备份', '0 0 2 ? * TUE', '', 'com.story.storyadmin.scheduler.DbBackupJob', '2019-08-30 07:42:21', '2019-09-10 10:30:00', '2019-09-10 10:29:00', '2019-09-10 10:31:00', null, 'org.quartz.core.JobRunShell.run(JobRunShell.java:218)\norg.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\n', '1', 'admin', 'admin', '2019-08-30 07:42:29', '2019-09-10 19:37:28');

-- ----------------------------
-- Table structure for `ST_SYSTEM_SEQUENCE`
-- ----------------------------
DROP TABLE IF EXISTS `ST_SYSTEM_SEQUENCE`;
CREATE TABLE `ST_SYSTEM_SEQUENCE` (
  `table_name` varchar(20) NOT NULL,
  `column_name` varchar(20) NOT NULL,
  `sequence` int(11) DEFAULT '0',
  PRIMARY KEY (`table_name`,`column_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of ST_SYSTEM_SEQUENCE
-- ----------------------------
INSERT INTO `ST_SYSTEM_SEQUENCE` VALUES ('ATTACHMENT', 'SEQUENCE', '32');

-- ----------------------------
-- Table structure for `st_todo`
-- ----------------------------
DROP TABLE IF EXISTS `st_todo`;
CREATE TABLE `st_todo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `title` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '标题',
  `start` datetime DEFAULT NULL COMMENT '开始时间',
  `end` datetime DEFAULT NULL COMMENT '结束时间',
  `days_count` int(11) DEFAULT '0' COMMENT '天数',
  `url` varchar(64) COLLATE utf8_bin DEFAULT '' COMMENT '链接',
  `task_content` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '内容',
  `level` int(11) DEFAULT '0' COMMENT '优先级',
  `executor` varchar(32) COLLATE utf8_bin DEFAULT '' COMMENT '执行人',
  `yn_flag` char(2) COLLATE utf8_bin DEFAULT '1' COMMENT '有效标志',
  `creator` varchar(32) COLLATE utf8_bin DEFAULT '' COMMENT '创建人',
  `editor` varchar(32) COLLATE utf8_bin DEFAULT '' COMMENT '修改人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='待办事项';

-- ----------------------------
-- Records of st_todo
-- ----------------------------
INSERT INTO `st_todo` VALUES ('1', '测试标题', '2019-08-15 14:28:12', '2019-08-16 14:28:18', null, null, '测试内容', '1', null, '0', null, 'admin', null, '2019-08-16 11:39:42');
INSERT INTO `st_todo` VALUES ('2', '出差1', '2019-08-14 17:31:14', '2019-08-14 17:31:19', null, null, '出差内容', '2', null, '0', null, 'admin', null, '2019-08-16 11:39:46');
INSERT INTO `st_todo` VALUES ('3', '测试3', '2019-07-30 00:00:00', '2019-08-01 00:00:00', null, null, '测试3', '1', null, '0', null, 'admin', null, '2019-08-16 11:39:34');
INSERT INTO `st_todo` VALUES ('4', '生日', '2019-08-29 00:00:00', '2019-08-31 00:00:00', null, null, '生日', '2', null, '0', null, 'admin', null, '2019-08-16 11:39:50');
INSERT INTO `st_todo` VALUES ('5', '回家', '2019-08-07 00:00:00', '2019-08-08 00:00:00', null, null, '回家', '1', null, '0', null, 'admin', null, '2019-08-16 11:39:38');
INSERT INTO `st_todo` VALUES ('6', '约会3', '2019-08-12 00:00:00', '2019-08-12 23:59:00', null, 'www.baidu.com', '大望路', '2', null, '0', null, 'admin', null, '2019-08-16 11:38:59');
INSERT INTO `st_todo` VALUES ('7', '聚会', '2019-08-25 00:00:00', '2019-08-25 23:59:00', null, null, '主日聚会', '3', null, '1', null, 'admin', null, '2019-08-18 12:38:23');
INSERT INTO `st_todo` VALUES ('8', '练车', '2019-09-07 00:00:00', '2019-09-08 00:00:00', null, null, '练车', '5', null, '1', null, 'admin', null, '2019-09-06 02:20:10');
INSERT INTO `st_todo` VALUES ('9', '测试', '2019-09-07 00:00:00', '2019-09-07 23:59:00', null, null, null, null, null, '1', null, 'admin', null, '2019-09-06 02:21:04');
INSERT INTO `st_todo` VALUES ('10', '测试2', '2019-09-07 00:00:00', '2019-09-07 23:59:00', null, null, null, null, null, '1', null, 'admin', null, '2019-09-06 02:21:16');
INSERT INTO `st_todo` VALUES ('11', '测试3', '2019-09-07 00:00:00', '2019-09-07 23:59:00', null, null, null, null, null, '1', null, 'admin', null, '2019-09-06 02:21:23');
INSERT INTO `st_todo` VALUES ('12', '出差', '2019-09-17 00:00:00', '2019-09-20 00:00:00', null, null, null, '1', null, '1', null, 'admin', null, '2019-09-09 06:59:11');
INSERT INTO `st_todo` VALUES ('13', '测试4', '2019-09-07 00:00:00', '2019-09-07 23:59:00', null, null, null, '3', null, '1', null, 'admin', null, '2019-09-10 06:38:34');
INSERT INTO `st_todo` VALUES ('14', '上午10点开发小组进行开会', '2020-08-19 10:00:00', '2020-08-19 10:00:00', null, 'https://www.baidu.com/', '晚上6点开发小组进行开会，大家务必不要迟到', '3', null, '1', null, 'admin', null, '2020-08-19 01:54:05');

-- ----------------------------
-- Table structure for `st_user`
-- ----------------------------
DROP TABLE IF EXISTS `st_user`;
CREATE TABLE `st_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '姓名',
  `account` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '账号',
  `password` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '密码',
  `avatar` varchar(255) CHARACTER SET utf8 DEFAULT '' COMMENT '用户头像图片',
  `email` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '邮箱',
  `last_pwd_modified_time` datetime DEFAULT NULL COMMENT '上次修改密码时间',
  `status` char(2) COLLATE utf8_bin DEFAULT '0' COMMENT '状态',
  `yn_flag` char(2) COLLATE utf8_bin DEFAULT '1' COMMENT '有效标志（1表示有效，0表示无效）',
  `creator` varchar(32) COLLATE utf8_bin DEFAULT '' COMMENT '创建人',
  `editor` varchar(32) COLLATE utf8_bin DEFAULT '' COMMENT '修改人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `erp_flag` char(2) COLLATE utf8_bin DEFAULT '0' COMMENT 'ERP账号标识',
  `age` int(11) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8_bin DEFAULT '',
  `sex` char(2) COLLATE utf8_bin DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
-- Records of st_user
-- ----------------------------
INSERT INTO `st_user` VALUES ('1', '管理员', 'admin', 'bcd0b29f69059fe4c69802525c4ab550', 'http://localhost:9430/images/20201023230144_图像.jpeg', 'admin@admin.admin', '2019-01-17 15:05:04', '1', '1', null, 'admin', '2018-12-29 10:40:07', '2020-10-24 05:22:02', '0', '0', null, '', '0');
INSERT INTO `st_user` VALUES ('2', 'sunnj666', 'njsun666', '9faa0dc953d4c953bd385d4a0adfd488', 'http://localhost:9430/images/20201013171310_pp.png', 'aa@aa.aa2', '2020-10-13 09:13:41', '0', '0', null, 'admin', null, '2020-10-20 01:25:04', '0', '0', null, '', '0');
INSERT INTO `st_user` VALUES ('4', '测试', 'test', '0ace0a5fc5802a43305c8ae547a81afe', 'http://localhost:9430/images/20201013170420_pp.png', 'test@aa.aa', '2019-01-17 15:23:28', '1', '0', null, 'admin', null, '2020-10-13 09:12:25', '0', '0', null, '', '0');
INSERT INTO `st_user` VALUES ('8', 'lipan', 'lovepli', 'a4e24a4d31f8c8aee8cf346945b0890c', 'http://localhost:9430/images/20201013170420_pp.png', 'pli38546@gmail.com', '2020-08-18 04:30:37', '1', '0', 'admin', 'admin', '2020-08-18 04:30:37', '2020-10-13 09:10:59', '0', '0', null, '', '0');
INSERT INTO `st_user` VALUES ('19', 'lipan', 'lovepli', 'a4e24a4d31f8c8aee8cf346945b0890c', 'http://localhost:9430/images/20201013170420_pp.png', 'pli38546@gmail.com', '2020-10-13 08:22:17', '1', '0', 'admin', 'admin', '2020-10-13 08:22:17', '2020-10-13 09:14:10', '0', '0', null, '', '0');
INSERT INTO `st_user` VALUES ('20', '李攀', 'lovepli', 'a4e24a4d31f8c8aee8cf346945b0890c', 'http://localhost:9430/images/20201019160619_pp.png', 'pli38546@gmail.com', '2020-10-19 08:06:24', '1', '0', 'admin', 'admin', '2020-10-19 08:06:24', '2020-10-19 08:06:27', '0', '0', null, '', '0');
INSERT INTO `st_user` VALUES ('21', 'lipan', 'lovepli', 'a4e24a4d31f8c8aee8cf346945b0890c', 'http://localhost:9430/images/20201019163547_pp.png', 'pli38546@gmail.com', '2020-10-19 08:35:51', '1', '0', 'admin', 'admin', '2020-10-19 08:35:50', '2020-10-19 09:33:53', '0', '0', null, '', '0');
INSERT INTO `st_user` VALUES ('22', 'lipan', 'lovepli', 'a4e24a4d31f8c8aee8cf346945b0890c', 'http://localhost:9430/images/20201019173455_pp.png', 'pli38546@gmail.com', '2020-10-19 09:34:58', '1', '0', 'admin', 'admin', '2020-10-19 09:34:58', '2020-10-19 09:38:54', '0', '0', null, '', '0');
INSERT INTO `st_user` VALUES ('23', '3333333333333333', '3333333333333', '01bf2093f07cfd72b5b84b4237c21383', 'http://localhost:9430/images/20201020092958_pp.png', 'pli38546@gmail.com', '2020-10-20 01:30:03', '1', '0', 'admin', 'admin', '2020-10-20 01:30:03', '2020-10-20 01:30:08', '0', '0', null, '', '0');
INSERT INTO `st_user` VALUES ('24', '1111111', '111111111', '8466b45507a9e7727b287fde064ac832', 'http://localhost:9430/images/20201020093234_pp.png', 'pli38546@gmail.com', '2020-10-20 01:32:38', '1', '0', 'admin', 'admin', '2020-10-20 01:32:38', '2020-10-20 01:33:07', '0', '0', null, '', '0');
INSERT INTO `st_user` VALUES ('25', '1111111111111', '1111111111111', 'd774a55a677f714b586fd7822e73f0e9', 'http://localhost:9430/images/20201020093615_pp.png', 'pli38546@gmail.com', '2020-10-20 01:36:20', '1', '0', 'admin', 'admin', '2020-10-20 01:36:20', '2020-10-20 01:37:07', '0', '0', null, '', '0');
INSERT INTO `st_user` VALUES ('26', '222222222222222', '22222222222222222', '854eb16a3633b81e52c20fdee8e0c292', 'http://localhost:9430/images/20201020095019_pp.png', 'pli38546@gmail.com', '2020-10-20 01:50:24', '1', '0', 'admin', 'admin', '2020-10-20 01:50:24', '2020-10-20 01:50:32', '0', '0', null, '', '0');
INSERT INTO `st_user` VALUES ('27', '22222222222222222', '4444444444444444444444', '9faa0dc953d4c953bd385d4a0adfd488', 'http://localhost:9430/images/20201020101830_pp.png', 'pli38546@gmail.com', '2020-10-20 02:20:48', '1', '0', 'admin', 'admin', '2020-10-20 02:18:34', '2020-10-20 02:21:02', '0', '0', null, '', '0');
INSERT INTO `st_user` VALUES ('28', '3333333333333333', '333333333333333333', 'f5bc12ceb752caa7c402440990e5d37e', 'http://localhost:9430/images/20201023230144_图像.jpeg', 'pli38546@gmail.com', '2020-10-20 03:56:54', '1', '0', 'admin', 'admin', '2020-10-20 03:56:54', '2020-10-24 05:21:54', '0', '0', null, '', '0');
INSERT INTO `st_user` VALUES ('29', '李攀', 'lp123456', 'a4e24a4d31f8c8aee8cf346945b0890c', 'http://localhost:9430/images/20210702102204_pp.png', null, '2021-07-02 02:22:08', '1', '1', 'admin', 'admin', '2021-07-02 02:22:08', '2020-10-24 05:22:02', '0', '0', null, '', '0');
INSERT INTO `st_user` VALUES ('30', 'name', 'account', null, '', 'email', null, '0', null, null, null, null, '2021-07-13 13:05:33', null, '0', null, '', '0');
INSERT INTO `st_user` VALUES ('31', 'name2', 'account2', null, '', 'email2', null, '0', null, null, null, null, '2021-07-13 13:05:33', null, '0', null, '', '0');
INSERT INTO `st_user` VALUES ('32', 'name2', 'account2', null, '', 'email2', null, '0', null, null, null, null, '2021-07-14 00:45:00', null, '0', null, '', '0');
INSERT INTO `st_user` VALUES ('33', '张三', '账户1', null, '', '1171205514@qq.com', null, '0', null, null, null, null, '2021-07-14 00:48:09', null, '0', null, '', '0');
INSERT INTO `st_user` VALUES ('34', '李四', '账户2', null, '', '1171205514@qq.com', null, '0', null, null, null, null, '2021-07-14 00:48:09', null, '0', null, '', '0');

-- ----------------------------
-- Table structure for `st_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `st_user_role`;
CREATE TABLE `st_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `yn_flag` char(2) COLLATE utf8_bin DEFAULT '1' COMMENT '有效标志',
  `creator` varchar(32) COLLATE utf8_bin DEFAULT '' COMMENT '创建人',
  `editor` varchar(32) COLLATE utf8_bin DEFAULT '' COMMENT '修改人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='用户角色关系表';

-- ----------------------------
-- Records of st_user_role
-- ----------------------------
INSERT INTO `st_user_role` VALUES ('3', '1', '1', '0', 'admin', null, '2019-01-04 10:38:31', '2020-08-17 03:42:06');
INSERT INTO `st_user_role` VALUES ('6', '1', '0', '0', 'admin', null, '2020-08-17 03:42:06', '2020-08-21 02:56:46');
INSERT INTO `st_user_role` VALUES ('7', '1', '1', '0', 'admin', null, '2020-08-17 03:42:06', '2020-08-21 02:56:46');
INSERT INTO `st_user_role` VALUES ('8', '1', '3', '0', 'admin', null, '2020-08-17 03:42:06', '2020-08-21 02:56:46');
INSERT INTO `st_user_role` VALUES ('9', '1', '4', '0', 'admin', null, '2020-08-17 03:42:06', '2020-08-21 02:56:46');
INSERT INTO `st_user_role` VALUES ('10', '4', '0', '1', 'admin', 'admin', '2020-08-17 04:30:36', '2020-08-17 04:30:36');
INSERT INTO `st_user_role` VALUES ('11', '4', '1', '1', 'admin', 'admin', '2020-08-17 04:30:36', '2020-08-17 04:30:36');
INSERT INTO `st_user_role` VALUES ('12', '4', '3', '1', 'admin', 'admin', '2020-08-17 04:30:36', '2020-08-17 04:30:36');
INSERT INTO `st_user_role` VALUES ('13', '4', '4', '1', 'admin', 'admin', '2020-08-17 04:30:36', '2020-08-17 04:30:36');
INSERT INTO `st_user_role` VALUES ('14', '1', '1', '0', 'admin', null, '2020-08-21 02:56:46', '2020-08-21 02:59:47');
INSERT INTO `st_user_role` VALUES ('15', '1', '1', '0', 'admin', null, '2020-08-21 02:59:47', '2020-10-20 01:26:16');
INSERT INTO `st_user_role` VALUES ('16', '19', '1', '1', 'admin', 'admin', '2020-10-13 08:22:31', '2020-10-13 08:22:31');
INSERT INTO `st_user_role` VALUES ('17', '22', '0', '0', 'admin', null, '2020-10-19 09:35:12', '2020-10-19 09:37:28');
INSERT INTO `st_user_role` VALUES ('18', '22', '1', '0', 'admin', null, '2020-10-19 09:35:12', '2020-10-19 09:37:28');
INSERT INTO `st_user_role` VALUES ('19', '22', '3', '0', 'admin', null, '2020-10-19 09:35:12', '2020-10-19 09:37:28');
INSERT INTO `st_user_role` VALUES ('20', '22', '4', '0', 'admin', null, '2020-10-19 09:35:12', '2020-10-19 09:37:28');
INSERT INTO `st_user_role` VALUES ('21', '1', '1', '0', 'admin', null, '2020-10-20 01:26:16', '2020-10-20 02:24:17');
INSERT INTO `st_user_role` VALUES ('22', '1', '3', '0', 'admin', null, '2020-10-20 01:26:16', '2020-10-20 02:24:17');
INSERT INTO `st_user_role` VALUES ('23', '1', '0', '0', 'admin', null, '2020-10-20 02:24:17', '2020-10-27 02:10:07');
INSERT INTO `st_user_role` VALUES ('24', '1', '3', '0', 'admin', null, '2020-10-20 02:24:17', '2020-10-27 02:10:07');
INSERT INTO `st_user_role` VALUES ('25', '1', '4', '0', 'admin', null, '2020-10-20 02:24:17', '2020-10-27 02:10:07');
INSERT INTO `st_user_role` VALUES ('26', '1', '5', '0', 'admin', null, '2020-10-20 02:24:17', '2020-10-27 02:10:07');
INSERT INTO `st_user_role` VALUES ('27', '1', '1', '0', 'admin', null, '2020-10-27 02:10:07', '2021-01-05 04:35:36');
INSERT INTO `st_user_role` VALUES ('28', '1', '1', '0', 'admin', null, '2021-01-05 04:35:36', '2021-01-05 04:35:36');
INSERT INTO `st_user_role` VALUES ('29', '1', '3', '0', 'admin', null, '2021-01-05 04:35:36', '2021-01-05 04:35:36');
INSERT INTO `st_user_role` VALUES ('30', '1', '1', '1', 'admin', 'admin', '2021-01-05 04:35:36', '2021-01-05 04:35:36');
INSERT INTO `st_user_role` VALUES ('31', '1', '3', '1', 'admin', 'admin', '2021-01-05 04:35:36', '2021-01-05 04:35:36');

-- ----------------------------
-- Table structure for `sys_dept`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父部门id',
  `ancestors` varchar(50) DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) DEFAULT '' COMMENT '部门名称',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `leader` varchar(20) DEFAULT '' COMMENT '负责人',
  `phone` varchar(11) DEFAULT '' COMMENT '联系电话',
  `email` varchar(50) DEFAULT '' COMMENT '邮箱',
  `status` char(1) DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8mb4 COMMENT='部门表';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('100', '0', '0', '若依科技', '0', '若依', '17671226463', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2020-10-30 07:27:26');
INSERT INTO `sys_dept` VALUES ('101', '100', '0,100', '深圳总公司', '1', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2020-10-30 07:27:26');
INSERT INTO `sys_dept` VALUES ('102', '100', '0,100', '长沙分公司', '2', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES ('103', '101', '0,100,101', '研发部门', '1', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES ('104', '101', '0,100,101', '市场部门', '2', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES ('105', '101', '0,100,101', '测试部门', '3', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES ('106', '101', '0,100,101', '财务部门', '4', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES ('107', '101', '0,100,101', '运维部门', '5', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES ('108', '102', '0,100,102', '市场部门', '1', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES ('109', '102', '0,100,102', '财务部门', '2', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES ('110', '101', '0,100,101', '销售部', '6', '若依', '15888888888', 'ry@qq.com', '1', '0', 'admin', '2020-10-30 07:27:13', '', '2020-10-30 07:27:40');

-- ----------------------------
-- Table structure for `sys_dict`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gid` varchar(32) DEFAULT '' COMMENT '分组ID',
  `label` varchar(100) DEFAULT '' COMMENT '键值键',
  `value` varchar(100) DEFAULT '' COMMENT '值',
  `sort` int(5) DEFAULT '0' COMMENT '排序',
  `remarks` varchar(100) DEFAULT '' COMMENT '描述',
  `create_by` varchar(32) DEFAULT '',
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT '',
  `update_date` datetime DEFAULT NULL,
  `del_flag` char(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='数据字典';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('1', '1', '1', '1', '1', '1', null, null, null, null, '0');
INSERT INTO `sys_dict` VALUES ('2', '1', '1', '2', '2', '2', null, null, null, null, '0');
INSERT INTO `sys_dict` VALUES ('3', '2', '1111111', '7777777777777', '1', '', 'admin', '2020-10-27 03:14:33', 'admin', '2020-10-27 04:15:04', '1');
INSERT INTO `sys_dict` VALUES ('4', '1', '33333333333', '333333', '11', '', 'admin', '2020-10-27 03:45:56', 'admin', '2020-10-27 04:12:33', '1');
INSERT INTO `sys_dict` VALUES ('5', '3', '22', '22', '1', '', 'admin', '2020-10-28 01:12:33', null, null, '0');

-- ----------------------------
-- Table structure for `sys_dict_group`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_group`;
CREATE TABLE `sys_dict_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) DEFAULT '' COMMENT '分组名称',
  `code` varchar(100) DEFAULT '' COMMENT '分组编码',
  `remarks` varchar(100) DEFAULT '' COMMENT '备注',
  `create_by` varchar(32) DEFAULT '',
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT '',
  `update_date` datetime DEFAULT NULL,
  `del_flag` char(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='字典分组';

-- ----------------------------
-- Records of sys_dict_group
-- ----------------------------
INSERT INTO `sys_dict_group` VALUES ('1', '1', '1', '1', null, null, null, null, '0');
INSERT INTO `sys_dict_group` VALUES ('2', '111', '7777', '1', 'admin', '2020-10-27 03:04:35', 'admin', '2020-10-27 04:13:04', '1');
INSERT INTO `sys_dict_group` VALUES ('3', '2', '2', '', 'admin', '2020-10-28 01:12:27', null, null, '0');

-- ----------------------------
-- Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) NOT NULL DEFAULT '' COMMENT '菜单名称',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父菜单ID',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `path` varchar(200) DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) DEFAULT '' COMMENT '组件路径',
  `is_frame` int(1) DEFAULT '1' COMMENT '是否为外链（0是 1否）',
  `menu_type` char(1) DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) DEFAULT '' COMMENT '权限标识',
  `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1061 DEFAULT CHARSET=utf8 COMMENT='菜单权限表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '系统管理', '0', '1', 'system', null, '1', 'M', '0', '0', '', 'system', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统管理目录');
INSERT INTO `sys_menu` VALUES ('2', '系统监控', '0', '2', 'monitor', null, '1', 'M', '0', '0', '', 'monitor', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统监控目录');
INSERT INTO `sys_menu` VALUES ('3', '系统工具', '0', '3', 'tool', null, '1', 'M', '0', '0', '', 'tool', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统工具目录');
INSERT INTO `sys_menu` VALUES ('4', '若依官网', '0', '4', 'http://ruoyi.vip', null, '0', 'M', '0', '0', '', 'guide', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '若依官网地址');
INSERT INTO `sys_menu` VALUES ('100', '用户管理', '1', '1', 'user', 'system/user/index', '1', 'C', '0', '0', 'system:user:list', 'user', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '用户管理菜单');
INSERT INTO `sys_menu` VALUES ('101', '角色管理', '1', '2', 'role', 'system/role/index', '1', 'C', '0', '0', 'system:role:list', 'peoples', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '角色管理菜单');
INSERT INTO `sys_menu` VALUES ('102', '菜单管理', '1', '3', 'menu', 'system/menu/index', '1', 'C', '0', '0', 'system:menu:list', 'tree-table', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '菜单管理菜单');
INSERT INTO `sys_menu` VALUES ('103', '部门管理', '1', '4', 'dept', 'system/dept/index', '1', 'C', '0', '0', 'system:dept:list', 'tree', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '部门管理菜单');
INSERT INTO `sys_menu` VALUES ('104', '岗位管理', '1', '5', 'post', 'system/post/index', '1', 'C', '0', '0', 'system:post:list', 'post', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '岗位管理菜单');
INSERT INTO `sys_menu` VALUES ('105', '字典管理', '1', '6', 'dict', 'system/dict/index', '1', 'C', '0', '0', 'system:dict:list', 'dict', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '字典管理菜单');
INSERT INTO `sys_menu` VALUES ('106', '参数设置', '1', '7', 'config', 'system/config/index', '1', 'C', '0', '0', 'system:config:list', 'edit', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '参数设置菜单');
INSERT INTO `sys_menu` VALUES ('107', '通知公告', '1', '8', 'notice', 'system/notice/index', '1', 'C', '0', '0', 'system:notice:list', 'message', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知公告菜单');
INSERT INTO `sys_menu` VALUES ('108', '日志管理', '1', '9', 'log', 'system/log/index', '1', 'M', '0', '0', '', 'log', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '日志管理菜单');
INSERT INTO `sys_menu` VALUES ('109', '在线用户', '2', '1', 'online', 'monitor/online/index', '1', 'C', '0', '0', 'monitor:online:list', 'online', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '在线用户菜单');
INSERT INTO `sys_menu` VALUES ('110', '定时任务', '2', '2', 'job', 'monitor/job/index', '1', 'C', '0', '0', 'monitor:job:list', 'job', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '定时任务菜单');
INSERT INTO `sys_menu` VALUES ('111', '数据监控', '2', '3', 'druid', 'monitor/druid/index', '1', 'C', '0', '0', 'monitor:druid:list', 'druid', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '数据监控菜单');
INSERT INTO `sys_menu` VALUES ('112', '服务监控', '2', '4', 'server', 'monitor/server/index', '1', 'C', '0', '0', 'monitor:server:list', 'server', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '服务监控菜单');
INSERT INTO `sys_menu` VALUES ('113', '表单构建', '3', '1', 'build', 'tool/build/index', '1', 'C', '0', '0', 'tool:build:list', 'build', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '表单构建菜单');
INSERT INTO `sys_menu` VALUES ('114', '代码生成', '3', '2', 'gen', 'tool/gen/index', '1', 'C', '0', '0', 'tool:gen:list', 'code', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '代码生成菜单');
INSERT INTO `sys_menu` VALUES ('115', '系统接口', '3', '3', 'swagger', 'tool/swagger/index', '1', 'C', '0', '0', 'tool:swagger:list', 'swagger', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统接口菜单');
INSERT INTO `sys_menu` VALUES ('500', '操作日志', '108', '1', 'operlog', 'monitor/operlog/index', '1', 'C', '0', '0', 'monitor:operlog:list', 'form', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '操作日志菜单');
INSERT INTO `sys_menu` VALUES ('501', '登录日志', '108', '2', 'logininfor', 'monitor/logininfor/index', '1', 'C', '0', '0', 'monitor:logininfor:list', 'logininfor', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '登录日志菜单');
INSERT INTO `sys_menu` VALUES ('1001', '用户查询', '100', '1', '', '', '1', 'F', '0', '0', 'system:user:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1002', '用户新增', '100', '2', '', '', '1', 'F', '0', '0', 'system:user:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1003', '用户修改', '100', '3', '', '', '1', 'F', '0', '0', 'system:user:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1004', '用户删除', '100', '4', '', '', '1', 'F', '0', '0', 'system:user:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1005', '用户导出', '100', '5', '', '', '1', 'F', '0', '0', 'system:user:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1006', '用户导入', '100', '6', '', '', '1', 'F', '0', '0', 'system:user:import', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1007', '重置密码', '100', '7', '', '', '1', 'F', '0', '0', 'system:user:resetPwd', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1008', '角色查询', '101', '1', '', '', '1', 'F', '0', '0', 'system:role:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1009', '角色新增', '101', '2', '', '', '1', 'F', '0', '0', 'system:role:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1010', '角色修改', '101', '3', '', '', '1', 'F', '0', '0', 'system:role:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1011', '角色删除', '101', '4', '', '', '1', 'F', '0', '0', 'system:role:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1012', '角色导出', '101', '5', '', '', '1', 'F', '0', '0', 'system:role:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1013', '菜单查询', '102', '1', '', '', '1', 'F', '0', '0', 'system:menu:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1014', '菜单新增', '102', '2', '', '', '1', 'F', '0', '0', 'system:menu:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1015', '菜单修改', '102', '3', '', '', '1', 'F', '0', '0', 'system:menu:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1016', '菜单删除', '102', '4', '', '', '1', 'F', '0', '0', 'system:menu:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1017', '部门查询', '103', '1', '', '', '1', 'F', '0', '0', 'system:dept:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1018', '部门新增', '103', '2', '', '', '1', 'F', '0', '0', 'system:dept:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1019', '部门修改', '103', '3', '', '', '1', 'F', '0', '0', 'system:dept:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1020', '部门删除', '103', '4', '', '', '1', 'F', '0', '0', 'system:dept:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1021', '岗位查询', '104', '1', '', '', '1', 'F', '0', '0', 'system:post:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1022', '岗位新增', '104', '2', '', '', '1', 'F', '0', '0', 'system:post:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1023', '岗位修改', '104', '3', '', '', '1', 'F', '0', '0', 'system:post:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1024', '岗位删除', '104', '4', '', '', '1', 'F', '0', '0', 'system:post:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1025', '岗位导出', '104', '5', '', '', '1', 'F', '0', '0', 'system:post:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1026', '字典查询', '105', '1', '#', '', '1', 'F', '0', '0', 'system:dict:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1027', '字典新增', '105', '2', '#', '', '1', 'F', '0', '0', 'system:dict:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1028', '字典修改', '105', '3', '#', '', '1', 'F', '0', '0', 'system:dict:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1029', '字典删除', '105', '4', '#', '', '1', 'F', '0', '0', 'system:dict:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1030', '字典导出', '105', '5', '#', '', '1', 'F', '0', '0', 'system:dict:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1031', '参数查询', '106', '1', '#', '', '1', 'F', '0', '0', 'system:config:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1032', '参数新增', '106', '2', '#', '', '1', 'F', '0', '0', 'system:config:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1033', '参数修改', '106', '3', '#', '', '1', 'F', '0', '0', 'system:config:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1034', '参数删除', '106', '4', '#', '', '1', 'F', '0', '0', 'system:config:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1035', '参数导出', '106', '5', '#', '', '1', 'F', '0', '0', 'system:config:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1036', '公告查询', '107', '1', '#', '', '1', 'F', '0', '0', 'system:notice:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1037', '公告新增', '107', '2', '#', '', '1', 'F', '0', '0', 'system:notice:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1038', '公告修改', '107', '3', '#', '', '1', 'F', '0', '0', 'system:notice:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1039', '公告删除', '107', '4', '#', '', '1', 'F', '0', '0', 'system:notice:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1040', '操作查询', '500', '1', '#', '', '1', 'F', '0', '0', 'monitor:operlog:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1041', '操作删除', '500', '2', '#', '', '1', 'F', '0', '0', 'monitor:operlog:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1042', '日志导出', '500', '4', '#', '', '1', 'F', '0', '0', 'monitor:operlog:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1043', '登录查询', '501', '1', '#', '', '1', 'F', '0', '0', 'monitor:logininfor:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1044', '登录删除', '501', '2', '#', '', '1', 'F', '0', '0', 'monitor:logininfor:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1045', '日志导出', '501', '3', '#', '', '1', 'F', '0', '0', 'monitor:logininfor:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1046', '在线查询', '109', '1', '#', '', '1', 'F', '0', '0', 'monitor:online:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1047', '批量强退', '109', '2', '#', '', '1', 'F', '0', '0', 'monitor:online:batchLogout', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1048', '单条强退', '109', '3', '#', '', '1', 'F', '0', '0', 'monitor:online:forceLogout', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1049', '任务查询', '110', '1', '#', '', '1', 'F', '0', '0', 'monitor:job:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1050', '任务新增', '110', '2', '#', '', '1', 'F', '0', '0', 'monitor:job:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1051', '任务修改', '110', '3', '#', '', '1', 'F', '0', '0', 'monitor:job:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1052', '任务删除', '110', '4', '#', '', '1', 'F', '0', '0', 'monitor:job:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1053', '状态修改', '110', '5', '#', '', '1', 'F', '0', '0', 'monitor:job:changeStatus', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1054', '任务导出', '110', '7', '#', '', '1', 'F', '0', '0', 'monitor:job:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1055', '生成查询', '114', '1', '#', '', '1', 'F', '0', '0', 'tool:gen:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1056', '生成修改', '114', '2', '#', '', '1', 'F', '0', '0', 'tool:gen:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1057', '生成删除', '114', '3', '#', '', '1', 'F', '0', '0', 'tool:gen:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1058', '导入代码', '114', '2', '#', '', '1', 'F', '0', '0', 'tool:gen:import', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1059', '预览代码', '114', '4', '#', '', '1', 'F', '0', '0', 'tool:gen:preview', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1060', '生成代码', '114', '5', '#', '', '1', 'F', '0', '0', 'tool:gen:code', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');

-- ----------------------------
-- Table structure for `sys_organization`
-- ----------------------------
DROP TABLE IF EXISTS `sys_organization`;
CREATE TABLE `sys_organization` (
  `id` varchar(64) NOT NULL,
  `name` varchar(100) DEFAULT '' COMMENT '机构名称',
  `parent_id` varchar(32) DEFAULT '' COMMENT '父节点',
  `parent_ids` varchar(1000) DEFAULT '' COMMENT '父节点路径',
  `create_by` varchar(32) DEFAULT '',
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT '',
  `update_date` datetime DEFAULT NULL,
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标记',
  `remarks` varchar(255) DEFAULT '',
  `tenant_id` varchar(64) DEFAULT '00000000' COMMENT '租户ID',
  PRIMARY KEY (`id`),
  KEY `idx_sys_organization_parent_id` (`parent_id`),
  KEY `idx_sys_organization_parent_ids` (`parent_ids`(255))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_organization
-- ----------------------------
INSERT INTO `sys_organization` VALUES ('05fa0ccf85e2dac369445c927fed4ed9', '西游网络', null, null, '4028ea815a3d2a8c015a3d2f8d2a0002', '2019-12-17 11:07:53', null, null, '0', null, '89013384');
INSERT INTO `sys_organization` VALUES ('103c59b6e4dcdb4a00c4d128c431a3d5', '中国人民银行广东分行', '40288ab85b6080e1015b60996d690005', '40288ab85b6080e1015b60996d690005/', '4028ea815a3d2a8c015a3d2f8d2a0002', '2020-01-25 21:26:56', '4028ea815a3d2a8c015a3d2f8d2a0002', '2020-05-26 23:25:41', '0', null, '00000000');
INSERT INTO `sys_organization` VALUES ('1469131e587ee3da82308dd0a2956dbf', '大汉无忧', null, null, '4028ea815a3d2a8c015a3d2f8d2a0002', '2020-07-18 11:22:22', null, null, '0', null, '28648086');
INSERT INTO `sys_organization` VALUES ('166b5fc7b4f3ebb4753d2ff8f1b93b75', '研发部', '560a801d8dd49606d8ca5d22f8931b5b', '560a801d8dd49606d8ca5d22f8931b5b/', 'd91b7ebd90205eed81897b308fb7500e', '2020-10-22 15:24:08', null, null, '0', '开发', '79200349');
INSERT INTO `sys_organization` VALUES ('2752fb67239052ca7bb9666e52726fa0', '广州分行', '103c59b6e4dcdb4a00c4d128c431a3d5', '40288ab85b6080e1015b60996d690005/103c59b6e4dcdb4a00c4d128c431a3d5/', '4028ea815a3d2a8c015a3d2f8d2a0002', '2020-10-21 14:25:46', '4028ea815a3d2a8c015a3d2f8d2a0002', '2020-10-21 14:26:09', '0', '珠江新城', '00000000');
INSERT INTO `sys_organization` VALUES ('3409babe66b970ed4c841bf0ec72b887', '李四的租户', null, null, '67d2d0b2a68b0178ca1aa3c3a1e0bd1c', '2020-07-18 10:56:47', null, null, '0', null, '96192153');
INSERT INTO `sys_organization` VALUES ('40288ab85b6080e1015b60996d690005', '中国人民银行总行', null, null, null, null, '4028ea815a3d2a8c015a3d2f8d2a0002', '2020-10-22 09:17:38', '0', null, '00000000');
INSERT INTO `sys_organization` VALUES ('4ca062d0ed5803df2e4b86754c1b3e7e', '张三的租户', null, null, 'ff803cb98718d47127fd5291ab959a49', '2020-07-18 10:52:27', null, null, '0', null, '82517141');
INSERT INTO `sys_organization` VALUES ('4fc77d59737c481fe874d8199aee35d3', '人力资源部', '560a801d8dd49606d8ca5d22f8931b5b', '560a801d8dd49606d8ca5d22f8931b5b/', 'd91b7ebd90205eed81897b308fb7500e', '2020-10-22 15:23:42', null, null, '0', 'hr', '79200349');
INSERT INTO `sys_organization` VALUES ('560a801d8dd49606d8ca5d22f8931b5b', '蚂蚁金服', null, null, '4028ea815a3d2a8c015a3d2f8d2a0002', '2020-10-22 11:19:10', 'd91b7ebd90205eed81897b308fb7500e', '2020-10-22 11:29:54', '0', '蚂蚁集团总部', '79200349');
INSERT INTO `sys_organization` VALUES ('61e4139e4991d18f54392cfc46fe083f', '财务部', '560a801d8dd49606d8ca5d22f8931b5b', '560a801d8dd49606d8ca5d22f8931b5b/', 'd91b7ebd90205eed81897b308fb7500e', '2020-10-22 15:24:32', null, null, '0', '资产', '79200349');
INSERT INTO `sys_organization` VALUES ('68a207cc33fa7d89924bdb3d030e5b59', 'lisixu的租户', null, null, '4028ea815a3d2a8c015a3d2f8d2a0002', '2020-07-18 11:00:14', null, null, '0', null, '27317614');
INSERT INTO `sys_organization` VALUES ('74c1e35673fa31b2716f296d6f45ff85', '租户名称', null, null, '4028ea815a3d2a8c015a3d2f8d2a0002', '2020-07-18 11:15:42', null, null, '0', null, '31126092');
INSERT INTO `sys_organization` VALUES ('82e352cab34c0c5d45fb048f99a1d93f', '深圳分行', '103c59b6e4dcdb4a00c4d128c431a3d5', '40288ab85b6080e1015b60996d690005/103c59b6e4dcdb4a00c4d128c431a3d5/', '4028ea815a3d2a8c015a3d2f8d2a0002', '2020-01-25 21:27:12', '4028ea815a3d2a8c015a3d2f8d2a0002', '2020-10-22 09:19:08', '0', '后海', '00000000');
INSERT INTO `sys_organization` VALUES ('d269cc34a28843303a8863dde83a3d3a', '山海科技', null, null, '4028ea815a3d2a8c015a3d2f8d2a0002', '2019-12-13 10:01:07', null, null, '0', null, '35798760');
INSERT INTO `sys_organization` VALUES ('d2cae9f8eefd5c0941bf44dcb57cec5b', '中国人民银行湖北分行', '40288ab85b6080e1015b60996d690005', '40288ab85b6080e1015b60996d690005/', '4028ea815a3d2a8c015a3d2f8d2a0002', '2020-05-26 23:25:53', '4028ea815a3d2a8c015a3d2f8d2a0002', '2020-10-22 09:19:39', '0', null, '00000000');
INSERT INTO `sys_organization` VALUES ('ea33ae995d2daa7f41843eeca8976c65', '九州科技', null, null, '4028ea815a3d2a8c015a3d2f8d2a0002', '2019-12-16 09:28:50', null, null, '0', null, '86865558');

-- ----------------------------
-- Table structure for `sys_post`
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post` (
  `post_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) NOT NULL DEFAULT '' COMMENT '岗位编码',
  `post_name` varchar(50) NOT NULL DEFAULT '' COMMENT '岗位名称',
  `post_sort` int(4) NOT NULL DEFAULT '0' COMMENT '显示顺序',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='岗位信息表';

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES ('1', 'ceo', '董事长', '1', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_post` VALUES ('2', 'se', '项目经理', '2', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_post` VALUES ('4', 'user', '普通员工', '4', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');

-- ----------------------------
-- Table structure for `sys_user_post`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `post_id` bigint(20) NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`,`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与岗位关联表';

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
INSERT INTO `sys_user_post` VALUES ('1', '1');
INSERT INTO `sys_user_post` VALUES ('2', '2');

-- ----------------------------
-- Table structure for `test_car`
-- ----------------------------
DROP TABLE IF EXISTS `test_car`;
CREATE TABLE `test_car` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) DEFAULT '' COMMENT '名称',
  `code` varchar(100) DEFAULT '' COMMENT '品牌代码',
  `remarks` varchar(100) DEFAULT '' COMMENT '备注',
  `create_by` varchar(32) DEFAULT '',
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT '',
  `update_date` datetime DEFAULT NULL,
  `del_flag` char(1) DEFAULT NULL COMMENT '0',
  `tenant_id` varchar(64) NOT NULL DEFAULT '00000000' COMMENT '租户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='字典分组';

-- ----------------------------
-- Records of test_car
-- ----------------------------
INSERT INTO `test_car` VALUES ('1', '奔驰', '1', '1', null, null, 'admin', '2020-10-28 16:07:25', '1', '00000000');
INSERT INTO `test_car` VALUES ('2', '宝马', '66677', '', 'admin', '2020-10-27 03:44:37', 'admin', '2020-10-29 02:31:41', '0', '00000000');
INSERT INTO `test_car` VALUES ('3', '1111', '11', '', 'admin', '2020-10-28 16:07:35', 'admin', '2020-10-28 16:07:39', '1', '00000000');
INSERT INTO `test_car` VALUES ('4', '奔驰', '002', '', 'admin', '2020-10-29 01:34:55', null, null, '0', '00000000');
INSERT INTO `test_car` VALUES ('5', '333', '333', '', 'admin', '2021-02-19 06:39:57', 'admin', '2021-02-19 06:42:43', '1', '00000000');

-- ----------------------------
-- Table structure for `test_car_model`
-- ----------------------------
DROP TABLE IF EXISTS `test_car_model`;
CREATE TABLE `test_car_model` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `car_id` bigint(32) DEFAULT '0' COMMENT '汽车',
  `name` varchar(100) DEFAULT '' COMMENT '型号名',
  `value` varchar(100) DEFAULT '' COMMENT '型号代码',
  `sort` int(5) DEFAULT '0' COMMENT '排序',
  `remarks` varchar(100) DEFAULT '' COMMENT '描述',
  `create_by` varchar(32) DEFAULT '',
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT '',
  `update_date` datetime DEFAULT NULL,
  `del_flag` char(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `test_car_model_car_id` (`car_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COMMENT='数据字典';

-- ----------------------------
-- Records of test_car_model
-- ----------------------------
INSERT INTO `test_car_model` VALUES ('1', '1', '奔驰007', '007', '1', '1', null, null, 'admin', '2020-10-29 02:16:26', '1');
INSERT INTO `test_car_model` VALUES ('2', '1', '11111', '1111', '1', '', 'admin', '2020-10-27 03:36:14', 'admin', '2020-10-27 03:39:17', '1');
INSERT INTO `test_car_model` VALUES ('3', '1', '111111111', '11111111', '1', '', 'admin', '2020-10-27 04:13:54', 'admin', '2020-10-27 04:14:02', '1');
INSERT INTO `test_car_model` VALUES ('4', '1', '111', '111', '1', '', 'admin', '2020-10-28 15:58:50', 'admin', '2020-10-28 16:07:16', '1');
INSERT INTO `test_car_model` VALUES ('5', '4', '奔驰迈巴赫', '222', '1', '', 'admin', '2020-10-29 01:36:10', 'admin', '2020-10-29 02:16:24', '1');
INSERT INTO `test_car_model` VALUES ('6', '4', '111', '11', '1', '', 'admin', '2020-10-29 01:42:45', 'admin', '2020-10-29 02:16:09', '1');
INSERT INTO `test_car_model` VALUES ('7', '4', '22', '22', '1', '', 'admin', '2020-10-29 02:16:33', null, null, '0');
INSERT INTO `test_car_model` VALUES ('8', '2', '22', '22', '1', '', 'admin', '2020-10-29 02:16:57', 'admin', '2020-10-29 02:17:02', '1');
INSERT INTO `test_car_model` VALUES ('9', '4', '33', '3344', '1', '', 'admin', '2020-10-29 02:17:09', 'admin', '2020-10-29 02:31:32', '0');
INSERT INTO `test_car_model` VALUES ('10', '4', '555', '555', '6', '', 'admin', '2020-10-29 02:31:08', 'admin', '2020-10-29 02:31:27', '1');
INSERT INTO `test_car_model` VALUES ('11', '2', '3333', '333', '1', '', 'admin', '2020-10-29 02:31:17', null, null, '0');

-- ----------------------------
-- Table structure for `test_expand_table`
-- ----------------------------
DROP TABLE IF EXISTS `test_expand_table`;
CREATE TABLE `test_expand_table` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '商品名称',
  `shop` varchar(100) NOT NULL DEFAULT '' COMMENT '所属店铺',
  `category` varchar(100) NOT NULL DEFAULT '' COMMENT '商品分类',
  `address` text COMMENT '店铺地址',
  `description` longtext COMMENT '商品描述',
  `tag` varchar(200) DEFAULT '' COMMENT '标签',
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT '',
  `update_date` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT '',
  `del_flag` char(1) DEFAULT '0',
  `create_by` varchar(32) DEFAULT '',
  `organization_id` varchar(64) DEFAULT '' COMMENT '组织ID',
  `tenant_id` varchar(64) NOT NULL DEFAULT '00000000' COMMENT '租户ID',
  `image` text COMMENT '图片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of test_expand_table
-- ----------------------------
INSERT INTO `test_expand_table` VALUES ('1', '111', '1111', '111', '111', '111', '1111', '2020-10-27 08:33:45', 'admin', '2020-10-27 08:33:51', null, '1', 'admin', null, '00000000', '111');
INSERT INTO `test_expand_table` VALUES ('2', '1', '1', '1', '1', '1', '1', '2020-10-27 08:34:40', 'admin', '2020-10-28 14:45:22', null, '1', 'admin', null, '00000000', '1');
INSERT INTO `test_expand_table` VALUES ('3', '2333', '2222', '233', '2', '2', '2', '2020-10-27 11:58:36', 'admin', '2021-02-02 10:04:00', null, '1', 'admin', null, '00000000', '2');
INSERT INTO `test_expand_table` VALUES ('4', '11', '11', '11', '11', '11', '11', '2021-02-02 09:41:23', null, null, null, '0', 'admin', null, '00000000', '11');

-- ----------------------------
-- Table structure for `test_table`
-- ----------------------------
DROP TABLE IF EXISTS `test_table`;
CREATE TABLE `test_table` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) DEFAULT '' COMMENT '标题',
  `author` bigint(20) NOT NULL COMMENT '作者',
  `type` varchar(250) DEFAULT '' COMMENT '类型',
  `level` varchar(100) DEFAULT '' COMMENT '密码',
  `content` longtext COMMENT '内容',
  `status` varchar(32) DEFAULT '0' COMMENT '状态',
  `tag` varchar(200) DEFAULT NULL COMMENT '标签',
  `readings` int(11) DEFAULT '0' COMMENT '阅读数',
  `publish_date` datetime DEFAULT NULL COMMENT '发布时间',
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT '',
  `update_date` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT '',
  `del_flag` char(1) DEFAULT '0',
  `create_by` varchar(32) DEFAULT NULL,
  `tenant_id` varchar(64) NOT NULL DEFAULT '00000000' COMMENT '租户ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_test_table_title` (`title`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of test_table
-- ----------------------------
INSERT INTO `test_table` VALUES ('1', 'testuser', '1', 'china', '2', '&lt;p&gt;&lt;img class=&quot;wscnph&quot; src=&quot;data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAQwAAAC8CAMAAAC672BgAAAA8FBMVEWA5KyFd3H+9KJ0ZmKA567/96OFc2+FdXBZoHmFcW5/cWuDdHB/4ap1ZGFXnXf/+KR5a2Z826aGb254em561qKCw5qGfXSCrI31659puY1XoXlhpH6BzaCB1aSEh3qCoYeCmIKBrY389cSPhnd0Z2zVy5Ln4ZuQ5atmsIZ1g3KBupWGhnmEj36Cpop9sY99k359xJmyqIbu6Lrg2amXk4Cln4BhVVinm3x4cGXRyZ6dkXt0nYBsWFpzlXyysInJwJDK25/c7qW76Kbi2Zi/tYmRrYujmH+9uo3Rx5KMf2/G66bm76Or56lliXBgmHdnhG6Nte/hAAAHMklEQVR4nO2dbVfaSBSASTLJEJJIohAjb6IIRVSUrey6rlu2i7W2W+3//zc7M4GAIRnkHDjpubnPl4Jtz5k85947d16QQgFBEARBEARBEARBEARBEARBEARBEARBEARBEARBECQRQrIewa8DORxkPYRfBjLYNxvz2Mh7jJALarRnEuxBM9vBZE3FVKgSOiAn+5e5jg1SNRXFHAgHXosajTzbICeGolCRJ6RBqXGVaxnHXMaFXZgFiZ9rGW3KZCg8O8iQydjPekCZcs0iQzGqZBYk+7kuGhcsMhRzyGW0jaiW5hQhQ9RNkTHGMM8yrrkMMZ2INDEPcyxDhINiChlXBiul1TzLEAbalYJYpVCa6/rJDSgtm7CFPPFODs1cyygUhjct2iw0G9Vq9Wa4f+gxK/kVQkjLuFBMgaEYhnJ5NWjm1Afxrvh0okSwBYpBW8M8+iBV31gysRDiHzdzZ2OYoCLE9Id2rnSQw1QXXMdlJUc2SHpcCIyL/NggVbmL+UZHHiBNuQmRKe2chAbrL6RREdrIx1qFFQyZC8v3LYspucxFotjS/LBGv/328ZbZyMV+ObkyZS7uT08/dsY+3/fJQbfhSWcSa3R6Ov5du2UvjdYJdB2s3ZJmyR8sMu4OfP6ami3gqeIp8haj9PH0z3tr9oaaf3lZD3iHkIGsYvDQcBytVJrZKGkPfwOODftSHhi0FBK+0zQHsAzSkFYMHgzLMkqacwJYxtU6GYrFkmQRPSbg4xS7tWaFFgdwV04aG7pQ9itZj3lnkMM1c0kcCvmmwpq5ZAXIh45Nf1MZcJeuZLB2Lom5AHy5KTxsfj+GDzcwCoUNJ9YLyOu0ymYlg15mPeAdQqoblk+jnfWQd4e4+rmhDbB5QjbtMth0cgzWhr+pC9aOQ70EWNksMCwO1Evl6/cylqFWZ/zp07hjtUBu/G3Uf1r+JNDLuhp0O+2sB74L3rGxs3Dx1CurHL0cjCBuaWwwmVhPqi5UBIGuq18BJgq5eK8MqxOELrqW8sj+/AdeaNj03ZHREy7UgM0oZ132+kPWY9863v57A+N5Vi+6Z/5oNGUyPkMLDdKQylhEjXWvzumpepgv/2Y9+i0jPnyVTmkho6tHNthswtNl2gNWQ9fcUYlkzANDxIQ+eQxUfXQ2/QJrm0e+M15y44HxyDV0z87Gqvo0nk5gbQCKj9iky5hHBu3Mi+fZRNcno+cpSxNdD5wbUDakG6DuXIb1NSyZz6NuwEtGWEHV8r0G6hb1tSQyLG1+JcOa9Rh8YbJEefJwnvUDbBFbtpvhanMXT28cLGaVnusAOlDyZA2oO6+f1nOyDFX1HUCJ0pT0XJY2E0WtIMVFeew451BkkIZkZl0Exm05TcbE0Rwou17Sy1xaJOMxTYY+1TQwoSGTsTSXTNNKhhrcsdAAcltD1o1HWaL4aSqYjG9MBpBLTbJD50XHNU7LEt52MRnXMGR46W1GNJco1kQi4xOTcQdChmwyiRZpVEkvGbwH1TQXxOJVds4alQzaSesyVD6dMBkODBmSkrGYS57SXTAZZywysn6OrSC5AFqar0skXYYqVieac5D1c2yFZvqHCdyVfZ1kWKMB4ya5rH5q0b6O35PK+KY9gDiTJzepMmiUJco3Sf0Mu66sn2MrSLbGl3Y/72Uu1MB/gNGASmS4i33xsSxL1J4LZK9LkiYuXchQJTbYshXI5o6kgLqLl9ZTkG5D74K5mlBJXZosyVCsW4mNH1BcpB8UlJZlRJcRkoBzFE+qKVug7hsZ3EaKiyDrR9giaf24Vnr7nlXRZCAdxJObxNBwtfhPrMfkRIGTJQXxW5MTKobmxn9Ek/dBP2c9/i3TNuOZUtKi5fu6sgEqMAr87iM14i5WAiM5UcBd3eF143LJhuUmu1CU1dUruEtdBf5b+I9nqUJLTIW2Uj1nmkbxTR5oSRJitxyXw02kxAUnFhowXRSIHXrglFJdxELjA8Ak4ZChE5pw01XwqhHAjwvWlTMXrmWtTqix0Ih2Q/XvIE4HkvAOHGmtWMmTAGpY8E5jniTrrpGHJ9DBd7guZoGxZiYJQ2Oqqv/9AKyCNV2Ri3Ul1PzyI+vR7pi3MqQ+DLif4ZyxIiM9WYzrrAe7c+yDuI0cyyCDuIy0hgN+mjDa8aqRVkBh3ZtPxo7bSG44aMuzPc+2xVcmAdXCHsse3i3rcO4SXfj1YrFer/f7/aOjo0azwqxAc+I1jvr1WvHlTnNCtLuX4uvqnXKqvNaKIXshxXr/qAFqkeKJxyoWa7W9l5efP3++vO7VasXa68HbX5dKjfPIxQL2X+tZP8BWsXlkFEMlNS5FPGat+Noy6IJlFXtLkQHt6xl53ttes9FglaDfZ0Vh/sCvL+cHgnMWLqGtuqgZvGQ0Kp4opFmPfjfMv4szxBNUQsI33uyv3vxzBEEQBEEQBEEQBEEQBEEQBEEQBEEQBEEQBEEQJGf8D5P9jDLIl0d3AAAAAElFTkSuQmCC&quot; /&gt;testuser&lt;/p&gt;', 'draft', null, null, '2020-01-26 03:05:00', '2020-01-26 03:06:03', 'admin', '2020-10-28 14:45:43', null, '1', 'bf5a446c42964ad6a42b2bbb04604190', '00000000');
INSERT INTO `test_table` VALUES ('2', 'bbbb', '1', 'china', '1', '&lt;p&gt;asdas&amp;nbsp;&lt;/p&gt;', 'deleted', null, null, '2020-01-26 03:05:00', '2020-01-26 03:05:31', 'admin', '2020-10-27 12:38:18', null, '1', '092b7c63dfdd903132858bd100bb02c5', '00000000');
INSERT INTO `test_table` VALUES ('3', 'admin3', '1', 'CN', '1', '&lt;p&gt;&lt;img class=&quot;wscnph&quot; src=&quot;http://harbouross.oss-cn-beijing.aliyuncs.com/2019/05/18/1558169005633.png&quot; /&gt;&lt;img class=&quot;wscnph&quot; src=&quot;http://harbouross.oss-cn-beijing.aliyuncs.com/2019/05/18/1558169051041.png&quot; /&gt;dfasdfdsf&lt;/p&gt;', 'published', null, null, '2019-04-03 00:37:00', '2019-04-24 12:32:38', '4028ea815a3d2a8c015a3d2f8d2a0002', '2019-06-14 18:07:10', null, '0', '4028ea815a3d2a8c015a3d2f8d2a0002', '00000000');
INSERT INTO `test_table` VALUES ('4', 'admin2', '1', 'US', '1', '', 'published', null, null, '2019-04-24 00:02:00', '2019-04-23 23:20:23', 'admin', '2020-10-29 06:40:34', null, '0', '4028ea815a3d2a8c015a3d2f8d2a0002', '00000000');
INSERT INTO `test_table` VALUES ('5', '西游网络文章', '1', 'china', '3', '', 'published', null, null, '2019-12-17 11:20:00', '2019-12-17 11:23:42', 'admin', '2021-02-02 09:36:53', null, '0', '126657d9b6648cbd188cee3cbf713981', '89013384');
INSERT INTO `test_table` VALUES ('6', 'admin1', '1', 'china', '1', '&lt;p&gt;adasdasds&lt;/p&gt;\n&lt;p&gt;&lt;img class=&quot;wscnph&quot; src=&quot;data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMgAAADICAYAAACtWK6eAAAgAElEQVR4nO2deXhUVZ73P7f2quwrhARCwpoEAmEngOxgg7jQyow0Q9PiOGK3tvu8/draC9PdzzRurTNq2+207fBqD+04KgMtCiKgILJJgASy7zuprJWlqu59/6hUUdmKLBVSCefzPHlS67nn3Drf8zvnd875HckvehM+hjLUGRAMGdJQZ6AzmqHOAEIQgmt0VxeGVDRDJRAhCkFvca8rN1wsN1ogQhiCgeCsPzdMKDdKIEIYAm9yw4Qy2AIRwhAMJoMulMESiBCG4EYyaELxtkCEMARDideFovJWQghxCHwHr9VFb1gQIQyBL+IVazJQCyLEIfB1BlRHByIQIQ7BcKHfdbW/AhHiEAw3+lVn+yMQIQ7BcKXPdbevAhHiEAx3+lSH+yIQIQ7BSKHXdbm3AhHiEIw0elWnvTlRKBCMOHojEGE9BCOV69bt6wlEiEMw0vFYx0UXSyDwgCeBCOshuFnosa4LCyIQeKAngQjrIbjZ6LbOdycQIQ7BzUqXui+6WAKBBzoLRFgPwc1OBw0ICyIQeMBdIMJ6CAQOXFoQFkQg8IAQiEDgASEQgcADzrA/Yvwh6DdqtQqNWo1Go0atVmG12bG1/ynKsK1aCiD5wvkggmHM+NhI4uPHMCk+iuBAEyajnsbGZvKLq8nOLSUru4TGxpahzma/EQIR9BmdTsPcWZNYtXQGixYkEBkZTFhwAHq9BrVKjdVmo76hmbIqMydOZbLvwClOfnOZtjbbUGe9z0jtR7ANWzsouHH4mQzMmzOZ9Wtms3xJMuOiwzEadB6/Y7XZyS+q4oN9X/Mf73xKSenVG5RbryBJftGbhDgEHgnwN7IkNYl1a+awNDWJmKhQtNq+dT5aWm389eOv+NXzeyguqR6knHof0cUS9MjoyGBWLpvJutWzSZkeR8yYcFSq/oW6Neg1bP7uElpa2vjlb/+C2dzo5dwODkIggi7Ejo3gO6tns2b5TGZOjycyPMgr6apVKu797hLSM4v4w58OeCXNwUYIROAiaeo4Vi2bweoVKSQnxhIa7O/1a/ibDPzg3hUcPnqB7JxSr6fvbYRAbnIkSSIlOZ51a+awfPE0pk6KISjQNKjXTJg8ljvWzeOFVz8c1Ot4AyGQmxSdTsOiBQmsuCWZFYuTmTxxzHU9Ut5Cq1Fz+7r5vP/RcQoKK2/INfuLEMhNRmhIAAvnTWHNilksTU1kXEwEuj56pLzB1InRrF6ewh//7NtjESGQm4Sw0ACWL0lm/do5zE+ZyNixEUg37rjxLvgZ9WzcsIB9B05RVl4zZPm4HkIgI5yY6HBW3jKdtStnM2vGBKJHhyINnS46MHPaeG5ZnMR/vX9sqLPSI0IgI5Spk2JYszKFVUuTmZ4QS0SfXbUKvTneT5FB6uea8AA/I5vuXMShL85TXV3fv0QGGSGQEUZKcjzrVs/mlkXTmDZ1HEGBRvp3jmXH7zS0yWRVt5JvbqWuVabBKtNmk2mxKUhIqCUJvRpGB2qJC9aSGGkkUO9ZOZIkMW/WZBanJvLhx1/3I4+DjxDICMBg0DF31iTWrEhh+aIkpkyKGbBHqtpi40p1K7nmNjKqWjhTZKG4tg0Am0qiVZZRISErCmpJwi5LKJKCQS3hr1MxM8rI300LYs3EQKBnexQcYOKe2xfx+Rdp1NdbBpTnwUCsxRrGBAQYWTQ/gbUrZrJoQSKT4qPRavq/B6643sqF8mbSKls5V27hSlULZXVWKhusjA/Wc8+0YOzAx5l1IIFR7biWooCMhF1RXFalvkVmbICGn64YxebkEI/XrTY38NCTb7D/wOl+532wEBZkGBIWGsCyxdNZuyKFBXMmExc7CqmfI++GVjtfF1s4XmThZJGFPHMr9a0KMjJtNgWbXWZDYiDPLotiUpie/3ehlrYMhVCTFpNGwq6Aoiio2q8voQZJob5ZJrumhee/qmTGaCNJkYaeyxMSwB3r53Pky4s0NfnW3hEhkGFE1KgQlqQmsm7NHBbMnkJ0VGi/hZFrbuNofhOf5zZwpsxCeaMNFDBq1QQZVPhpNdS3yRg0sDUljDnRJsoarBzJb6TVpuCnUaGgYFcUhwVp3zkoASoJQk0qxqEjr9bKyWKLR4FIwNLUJGYmx/PVifR+lWewEAIZBowfF+lYI7V8JjOnxTMmKrTfMxjnK5rZn9nA8YImLla20thmw1+nYoy/FoNGhVotoQJkBdrsMqFGDVH+WgByalq5UN5MY5tMZaMNlRqC9SpQSdhlh0CU9u/aFFAjYdSoaLVfvxcfPSqUjRtSOXMum5aWtn6WzvsIgfgw0xNjWbN8JsuWTCc5aTxhIQH9TuvrYgv7Mxv4JKuevJpWZAWMehXjg3UYNSpkwCa3WwJFok12dLHCTVqi/HU022Q+z20k+2or44L1GHUSlU1WGlologO1qN1EIklglxUsVofApob3bD2cSJLEmmUz+PN70Vy4mO8ze9mFQHwMlUpi1owJrF42k+WLpzMzOR6Tsf8eqUtVLey5WMu+K/Xk1lixWO1oVBDppyHcX4tWJdEmg92tiySpwG4HGRgbqCUmSIO5xUabDD+cF8FdSUEossLJ0ib+/G0tBbVW4kN1qCSHV0uSoNkK5maZBTEGZo+5vkAAokaHkDJzAhmXC7Fa7f0uszcRAvERtFoNi+ZPZfXyFG5JTSRh8lgMem2/0yuss/JhRh3vp9eSUd2CTiURbFSh10BipJ71k4LIrmnjUG4D/gY1GsnhjZIASVGw2hX0Gom4UC1atUSAXs322aFEB2jRqR0dvHkxJsYEaPnpwXJqmu2EGTQoOMYkVy02/HQqNkwJIFCv7lWeNWo1UyfFYDDosVp9w+UrBDLE6PVaZs2cyLrVs7h1RQpTJsW4PEL9odUu82F6PX86W8O5ModHKCpAhwQ022S+mxjMfbNDCTdp+MXhCiqbbAQZVDhnKRQcD62yY05jXJBDpEaNikg/DZ/mNFDZZOXWiYGMCdCycWow3xQ3818X6wjUqdCqwNxip6FN5p6kINZN7n23UK2SCA8LxN/fSEODEMhNT1JiLPfcuYhli5OZkTgOraZ3LW1PpFW08P/SzOy5WEdFg5WxQToiTGqQIK+2jfhgLf84J5QZo42cKLJwttxCgF6NUavCZgcriit8h11RMGpUBBscVcTcYueVr6v505lqGttkMlJa+dnyUfjr1ayID2DvlXpabTKyWqKi0c7caCM/mh9OiLEvVUwi0M+Av0k/oPvgTYRAhoCxMeHcuno2371tIYvmJwx4Ta1dgY8y6njleBVfFTSh0amID9MRYdRgU2QaWmWsdoW4ED3jgx3jmYzqZgrMrejVamqa7WhVEgatCrvdYUvUkkSbHVrbI/VUNdn46HIdhXU2AvVq9qSZuWdaCPNijIwyqQk3qTFb7NS22BkfrOfx1EhmjDb2vTAK2Gy+Mf4AIZAbSnCwH99ZPYfb1s5lWWqSV3bulTRaeeObq7z9ZRXFta0Eh+oJMKqpaLBhaZOJDtCiKI65iUh/Lf46NZVNdg5lN2KuszImTI252U6zzc4Yfx3BBg2yoqBTq2hstVNY53C5hpnUzIs2ca7IQovFxtSxJgxah7StikJti0x+jZUJ4XqeWTqK1RP6vl3XarWRkV1KbX3TgO+LtxACuUHMnzuFv7/7Fu5YO4/I8ECvpHmqtJnfHirjvy/WMincwNPzopgRbUIFHCuw8El2PdUWO1oJdGoV8cE61CoobWhDQuKRxaNYOTGAulYbn2Y38FlOI3ZFIcJPi79Woq5F4cuiJu5NDibMqOGheWE0tthoa5PZPj+cxAg9FrvCB5fqyC5sYlKMHz9bMYo7EwLpT/CT+qZmzl7M9alIjEIgg0z0mDDuuXMx99yZSnJCbL9nvjtzIKeBn31Syje5DWyaHcajS0cxK8qIrr1mrpoQSKSfmn8/WQ2SxLggHePau1cxgVp+sjSSKeEGnEu3lsT6o9dU8GFGHXqNimarwtUmOx+nmZkxysBPlkSSPMrIi+tikBWF0f5aLDaF54+W84ej5cyKMfHL9WO4dVJAv8QBkJ1fTmZmMVar70RgFAIZRBYsSOC+Lau469Z5Xt3v/WFGHc/8rZT0kibumRPO8+tjiAnUUFJvJbfOSlK4nnCTms3JIXxZ0MihnEbmRpuIDnT83OEmDYF6NYfzGjFqJRaNNTE+WMeDc8L4ttTClapmkqP9WBobwoVSCy99VYVKgg2TA4kN1tFik9mX2cC7Z67y4fkaUuMD+NX6GOZF97/LqCgKR45dJD+v3Fu3ySsIgQwC4WGB3L5+Pj/43kpmJI4fkNvWHUWB9y/V8fMvykmvaGbqWH+2zA4lJlBDbm0bvz5YzqncBramRvDIokhig3XMi/Hj4OUGtGoINTq8ZAXmNn53spqP0sxE+mv4xZoo1kwMZGKYnhmRBsrqrDw8P5yVcf6kV7fy6L4SfnG4nIPZjYwN1mK22Pkmp5EKi43ts8N4bMVopoYNzPN08NhF/nPPYZ9aZgJCIF5nZnI89/3DKu5aN5+QIO/FlbIrCnsvN/DTz8soqbcSHWEgNkzLuADHT1hcb+VATj3FV+r5MFjHfXPCsdllcqvboMGKpUWmfXU6aRXN/OlsDa1tdsoarPzpm6ukjvPHpJUYHagjzF/HrCgjEX4aZmpUzIgyUNzQRnp1CwdzG8BiJ2mMkf+zejRbZoYQYhiYe/r0xVx+/fJfyc+vGOht8jpCIF5Cp9OwctlMHn1wA4vmTfV6+qdLWvjV0QryattIjjDQ1CajKA4Xb1mDjU+z6mlWYEpiMLcnBGNUS1S0ykwM0zExxkRWTSv5ZisxgTpGBWiJDdKSXmrDqpKw2OT2cYOERqOizGJl93kzt04K4GJFC/m1bQToVNRbZSL8NNw6I4QdC8JYGO034HKdPJvFL1/cw9kzWQNOazAQAvECkRHBfO/vl/H9v1vOxPGjAUef2lsD8tJ6K698XcWFimamhOkx6VRYFahtkXntVA1tNplPs+vRayR+uCSSHXPD0aggNkjHjxZGkBxt4tdfVLD/ch2LY/2YNsrIjrnhvPV1FWMjDeyYF45JqyLrahtp5S3Y7Qr/fbGWrwoaMbfYybraisUqszjWny0zQlg/2TGLPhBq6hrZ9+kZfv/2AdIu5CLLvrE4sTNCIANkWlIsO+5bxz13pmLSXxuIe0scrXaFPZdq+SSznugALcEGNS12BaNWotmm8LfMehQFNCo1gXrHUo/sqy2cKm1mTKCWlXH+3JMYRFFNG/959iq3JQaROtaPrSmhzIsxMSpAw5gALRWNNn5/+irflluIC9bRalM4VuCYjxgfouO2yYFsnRnKzKh+TP65UVx2lUNH0/jiq4t8dSKd0jLfDfkDYsttv9HpNKQuTOTxB29nxZLpg3adCxUWHt5fyoXyVqZG6JFlsCkKKsAO2NsXFZrUaiotNlQqhUh/DRfKmpkYrOOF22JYEGMivaqF+/6Sj04l8bN1McyPNuKvU1HTbONsaTP/m9nA3st12GUFlUqiySoTZFCxYnwAGxODWBzrh0nb/+286VnFHD91hc8OnePU2Swqq2q9do8GE2FB+kFwsD+bN93C/d9bzeQJYwbtOgpwqriZfHMbYX5qxz4LRUFqf08FaLUSKglq22w0We2UNbRxoUyBJhv1tW2cL29mQYyJUJOG8eF6/ut0DU99Usr8GCN+eglzk0zm1VYya1qpabKhU6sYE6TltikBfGdSIMvH+xNm6l81kRWFCxmF7P/sNF98eZErmcVUX/XN8D49IQTSR+LjRnPfllXct3klgQGDG+S5vlXmQmUrVrtCuJ8KW6edeZIkOZaWN9uwygrL4vxJiTLyRX4jX+U0sGicifkxjjyW1lspb7EzerSB+hYbH16uw2pXaG5TsMoKei1MjdCzJDaA70wOYMFYExH9FIbVZufrs5l8eSKdg0fSSLuQS3Ozb7lve4sQSC+RJJgzezI/vH89t6+di047MNdmb5BlhepmG41tMia1CjsSNpuMJLXv20DBJkNjm52FY/14ZukoJobqWDMxgIvJISSPMjA5TI+5xc6ei3Xk1LQx2k/j+I5VQUJhdICaCaF65o/1Y0WcH3Oi/QjQ9a8rVd9o4eS5bD4/ksaxE5e4cqWYZh+b1+grQiC9wGDQsXxZMk88dAcLZk2+YdcN1KtIijDw32m1VDXZGB2kRVEcg3/HDkAJNQqBOjUgUdpgZWKojqnheqaGOybuTpVaeOesmd1na6httWM2Opa3TwjRkxJlYOFYEzOjjEwNN7g2QvWVhsYWPjt6ngOHz3HuXDbpl4u8dAeGHjFIvw7BQX58797l3P+9VUyKi7rh188zt/HE/hI+ulLP6EAtIQY1Bo2qfQ2VhFoCOwo1FjsRJg3rJgcQF6Knqc3OpVILn16uJ8PcSpiflvgIA4mRBuZEG5kxysjUcD0Rfv1vI8sqzHx1KoPPDqfx1dfp5Bf43kTfQBEC8cD48aO47x9W8+D312K6QWdndEdaeTP//s1VjhdZqGu2Y1cU1JIjcoiCggI0W2Xqm+1IKolwkwa9JKFXZOKCdMyfEMDMGCOxQTomhugJMQ6se5iZW8anX3zLka8ucOFiPsUlw+rk2j4hBNIDc+dM5p/uu5VNty1Epeq/e9Nb1DbbOFvWQlp5C9nmFupa7DS2KdhkGUUCk1ZNuFGDn04izKhmlJ+W2GAtk8KNjA3svZVwTnAq7cEX3AOGnk8v4PBXFzhy7CJfn7riM9tiBxMhkE6YjHpWLJvBUz+6k9kzJgx1drqlqsmKxabQalOQ26MaatUqgvQqggwq1NeZpGwfvjhE4HwN2kPtKEgqFar2d9qsNs6m5XLsZAZ/+/QMGZcLaWhsHszi+RRikO5GRHgQmzct5cFtaxk7Jrz91d4dA3AjifDryzIPBYX2kCXtzxRZAlX7OwoosqObplJLqCQ1EtDQ1MyxrzP47Mh5Tp3OJONKEa2t1sEojk8jBNLOxIlj2Pa9VfzTP6zutHfDt8TRWxRZBpUKRQYFGRQFRXIIRQGwO8KGooBKktBpHMEdyqvrOHk6k6MnLvH5kTSyskuGuihDyk0vEJVKxbJbktlx362sWToTdT9dnb6C0i4AGZBk2SGQ9oBXiiIjy47/iiyjUqtd8zm5RRUcO5nBp5+fI+1CPnn5vrVxaai4qQUSHOzPhu/M44f3r2falJihzs4AcHSjnF0lZ49KURRkWUFGcTy2y8jtARzUKhV2WSYtvYTPjpzjq68zSLuYT2Xl8FgjdaO4aQUyccIYNm9ayvbNqwgL8d7GphuPY6CutEdZdww1HMKwywp2WW6fVFTaZ9+hsbmVtEuFnDqXxeGjaZxPy6O5uXVIS+Gr3HQC0em0LF0yjW3fW8Ftq+ai7m+EgSFGVmQcpgLk9qDTMgrIjiUqNrsdq2xHATSS4zzb+qYWTpzN5PCRNM6dzyEzs5i2Nt8JkOCL3FQCiYgI4p67FvOPW9cwqX1j07DBEXT92mOlfSyhOAQhK44/qyxjt9qxywqS5JjTKK6o4djJy5w+m8Wp05nk5pYNaVGGEzeNQKYnjWfb91axZdNS/IZwVryvOAbYEpJ0bQCO7DhdVkbBLjusid1ux2ZXsMt2R7fKrlBQXMWpb7M48uVFzp3LoUqML/rMiBdIYKCJxalJPPbgBhbOmTLU2ekXzojpzoG30j6+sCkyNrsd2W5HURznc9htdi5lF3P0RDonT14mPb0Qs7lhqIswbBnRApk6JYa77kjlB3+3gjGjPB8k6as4ln3IjsNpFAlZlrHbZWyyjNVuBxWoJBWWlla+vZTPufO5fH70PBfS8nwuhM5wZEQKJCTYn8Wpifzj1jWsWDx422EHDedBHYrkGlvIsozdOYeB43DN1jYbDZYWLmUUcvxkBidOXiYnp5TGm2gpyGAzogSiVquYPDmGH9y7gnvuWEREmHdi4N5oZGj3TjlctJJzPsOuoFKpaLW2culKMZezSjhx6gpnv80mJ7vEuZpE4EVGjECix4SRuiCR729ewbIFiUOdnX7gGIA7Bt8O7xSKwz1rkxXsNhlzXSNXcss5eORbzpzJ4kpmMebaxiHO98hmRAgkef5UHrx3JRtWzSYkeODBzG4UztW0itS+mlZ2zG1Ijnk97IpCTW0jufnlfHsxl3NpuaRfKSI9vdCnztAYyQxrgUSOjSB+1kRSNyxixbKZhBgGFsxsSJBAao9TIrcfHlNaYaawuIoLGYVculxIZlYxGVeKaGgQY4sbzbAUSFB4IJNmTGDaipmMmzkRf7WdhsY6FEP4sFp7K0kSdkWhvLKGwuJqKitrSc8s5kJGAZlZxeQXVApP1BAzrAQSEhFE3PTxTJmfQOycKUQGmphghMlGhRB1I5IcCCrfnARU2rfHNre0Ultnoaj0KrmFFWTllpGbW0ZBYSXFJdWUV5iHOqsCN4aFQIz+BhJmTWTKoiRiUiZhCDAyXg+zghTijSrUKi1Vza00NzYQFhDqteMGBoKiKLS22ai6Wk9Do4WScjNZuaWUlFRTWl5DflEVefkVXL1a376TT+CL+LRA/IP8iBwXSfLiJKYuTUYV6E+YBhL9JeYEqTBqoLxZoawVMuoVbNRxW1wAoTd4KYmiKDQ1t2KubaS8uo5acyPllbXk5JeTW1BBdXUtRcXVlFfUilWzwwyfFIhOr2VyygQSFiYSnTyBwFHBBGokJhglkgJUjDFImNvgjNlGdgvU2KChTYXN1kp2XRNzDDoGK8yCrChYrTaqr9ZTbW6gtMJMSelVikuqySuopKikmvKKGmpqGmhs8p2z9gT9w+cEYvQ3kLpuLvM3LEQTGoS/RmKCn0S8QUWMQcKohoImma9qFQqaHeFvDGqJKIMGc6uNdHMdk4P9CdZ7x6PVZrVRY26kuqaeiqpa8gorKSiuorioiuKyq5SW1VB9tZ4mIYYRiU8JRK1RM3/tbJZvWYWs1RKrV5gVrCFSD1a7Qpus0GSDU3Uyec3gr5bwVyso7aE4A7U6Si3N5NdbmBEe2PsjCNziMjRaWrha00hldR05+eXkF1aQk1tGflEl5RW1VFbW0mQRYrhZ8BmBqDUqRo+PZP7tqdgMBqYaFBYHqzCpoc4GLXbw10C9XaHSqmBUSfipHPvk5PZddAa1ChUKF811xAf5EajzXDxZVmi1WqmuqqesupasnFIyMovJK6ggN7+cwqIq6up858xuwY3HZwRi9DMyOWUifqND8ZcUZgWp8FNDVZuCTQFnLAUVEjqVRJusILfvlANcgQqC9VpKmiwUNFiYFhpIZyOiKArmukaqqus5czGPK5nF5OWVk5lbSkFhpZiME3TAZwSiN+kJigjCZrURZNLgr5Joag8yoG7vQskKGNVgUkODTeoQsUrCcbCMTq0CbFyqqSMu0B9/rQqb3U5FZS1FpVc5f6mAK1lFXMksIT2ziKqquiErs8D38RmBKIDN6jjAxSY7YjapuLbNVMEhFNn5XFGQnIMPZxoK2GUwaTWUNDTxzZUiTJYWjnyTTvrlIi6mF1BQKGanBb3HZwTSWNtIVVE1epXEVatMeavCZJOjK2VVQKcCo1qislmmwaqgbt+G6hx/OE2MubqOgpxyirNL+FtBFVdLr5KVUzrEpRMMV3xGIG3NbeReyMdcWoU2Moy0Bjuj9RqCtBI2GTQqiRqbTHqjjMUO+vYA5ZIk0WxpxVxppuBSIZdOZFCUXUpDjdhmKhg4PhW8WpIklty5kO/c9x1sWjVxBpjkr8YA1LYpXGmWqbaCVgUqFK4WX6Usv4KiK8VkfptDaW45NqsIYyPwHj5jQcAxrjh98ByjYsJJXjGTy3YteS1gkKDZLoNag95u42pxNZnnckj78hIl2aU0i0k6wSDhUxbEiSnAyNzVs0hanIQpPBidToOkgK2xifPHLnH5dCZFmSXYxaYhwSDjkwIBx6GZEdHhxEwcg9HfiKWxmdLccioKK4c6a4KbCJ/qYrmjKFBZXE1jXRManZaWphbaWoV7VnBj8VmBOLE0NANidlswNAz94XsCgQ8jBCIQeEAIRCDwgBCIQOABIRCBwANCIAKBB4RABAIPCIEIBB4QAhEIPCAEIhB4QAhEIPCAEIhA4AEhEIHAAz6/mrczBz/4KX4mIwCbt/+WvBLfCNuzYWUCISH+mM2N7D2UMWjXWZgSw8YNqURFhbFlx+uDdp3+sjAlhp3PbiM7p5jjJy/xzvunhjpLA2JYCeSNXduYOzsJgOpqM//86F19+v6f3z3IiXPFbL17LqnzkwaUl84//v99aguJCfGkZ+Sy99AzAGy9ey6vvvB4r9J7+IkXr1uZFqbE8MmHu1zPt959+rrf8UZZnTjLvPXuuTz543v5p0de5MS54g6f+f7mVcydncTc2UmkXy7wynWHkmEjkA0rE7h301rX8/DwkA7Pe8Pxk5c4ca6Y1PlJff5udwxG67gwJYZJE6J6fL+gsIzYcY73t272XIZjJzK9VlYnwUH+7HzuAQB+/8rjJC/p2ACsXjEPAIulhVfeOuy16w4Vw0IgG1Ym8Oa/Pe16XlBYRlNT3zdRmc1DdyLsm2/9DxfS8zq8Nj0xjge2d7SC39+8qtcV2tlS98TDT7zY94xeh1feOsz92zYQOy6K2HFR7H59h6ur98j25YSHhwBgMhmoK3jPY1q9sZpDjc8LxCkOk8kAOMTx/O883/jOdB4X/Pndgxw/eanbz268/RaWL50DdF+pnWTllAFw4pNfkZgQ73o9MSGeuoL3ulTOtasXsDh1RofX/PyMfSpHf3jwqbd58Km3u7zurLzv7Tngev+NXdtc4gyKvbfHNJ/5+R/Y/R/PAbBh3S08sv0yr7x1mHvvWeXdzPsAPi+QmcnxLnEAxI6L6nW/3on7uADgxLniLn1nJ+799QvpeV5r4ZzdouvRnXhjx0aybm0qr//xw26/s+P+O9l/4DgFRR0DWhw7keJJpKkAAA9wSURBVNm/zF6HvYcyeG/PAZeYxkZHsPXuua6GorrazGeff9Ptd1MXJLvuxVBa9N7i8wLZvedLHnrgbiyWZpf5tlhayC9whBP18zMSOy6K6mozlVUdD8B0b9l7y+hRYX36/P4Dxzl/IYvVK+YRHh7iqhxZOWUdxhLddSe6G8R3Fu+GlQn8686HMJkMbLz9Fu7c+kKHz3/4zhMkJsQzPnYMv3n+nW77/btf38GEuJhu8796xTxOfDIJgMiIkGv5+ORXXcq586V9ruf/+vL/MGP6JH69azd7D2WQc+Y113tv797Hzpf2sfXuuYDD2jrLVJbxJ8DZaA2et89b+LxA8krqeO3N99m950u+Pf4GAJcycli18V+Aaz+kyWRk/4F9HX7E7vrA1xsEx8dFux5PT4xj69095y0rp8x1vROfTCI8PITKKrOry+J+nVdfeJxXX+gulZ55ZPtyfvLkVpcFnZ40gYUpMa7KFhcdxKjIUMDR59/53APMmzOVZ//l3Q7u7wlxMT02FuHhIa6Gx53Onz9/IavD87ySOhbe6rDKu57b5EqjutrsuidO8b+35wAnzr3Ns4+td5Vl/4HjfbgTQ4fPCwRg50v72LAywfU8O+daC/veXw+6KtGTj26hoKiyS0udk3ft830ZBHceQHfG+cMPBu7jAXCMve74+190qPjOSrr79R1sWHcL4BgTJE+b1K0LFhwtt9PqOtN1OjwiIxxicbfQnqxwXHSQowF76wABASbuuG0pb+92iGNhSleLFRjgR3pGLpERIR0aMl/G5wXy7GPriR4T0aHrM2P6JN7Ytc31/FJGDnNnJ1FQWEbq/KQufv8JcTG8sWsbJaVVg57f8bFjyDnzGr/4zZ84diKz154k53ghLjqIj/7ysw5jlsNHTvP5kbNsuHVWly7UG7u20djYzPMv7+ahB+7GZDIQOy6KD97dSeqqR7tMpC689ZkOXbvnf/eeq0E58cmvCA8PIb+g1GUdPHmi/vDqw/iZjOw/cJwHn3qbf335f1zXmztrUpfPP/XLPcCeXt0PX8HnBbJubWqXViwxIb7bls3peuyM8/PpGbk9DnTBIbzO6e7df5TGxu5dysdPXmL36ztYuWyeq+tgMhkwmQykzk/qlzNh4a3PdHBhv/nW/xAQYGLncw9gsbSw95Ozrkq4MCWGO25bislkID0jlwd+9FuXx++1N9/vcZWBewPy5I/vdXn5xseOAaDJcn0Xelx0kMvFnJgQz+49X3a43rw5U12PHXMjb/f6PvgSPi8QZ/fIaf7BUZGcOF93fw3o0I1wDuBz8op55/1TPXqmOg9MARobm7t1kzrZcf+dHbxszus1NFiuX7ge2Lz9t3z0l5+5WvcP33kCcIjv3beedrXuv3/lcde1z1/IYu+hDDZufpbvb17lsQuTuiDZ9Th2XBRv/tvTfPS/R1xpzZ2dxK7nNrW3+N2zZdNi1+PDR053tVTzprseh4eH8Oxj64dNt8odnxeIcxLKaf4BVwV5ZPty16wuwOM/ed3V73bvRnz2+TceK7kzLaf1SM/IZXzsGEwmA3fcttS1RKU7zl/IIjEhHoulxdWSL7z1GeKig7iQnsfG228hK7vINZ/izNPhI6f54OOjHdJyuj3zSuo6zFA/9pM/cvDjCYSHh5CYEO8SjPs4wlk+hxes57JuvXtuh+/Fjovi5KmLXbx3D2y/i0kTx/aYzj0bV7oedy7Hs4+t7+BxNJkMbNuyvouVGQ4Mm9W8zkk1d0vxyluHeW/PAcBh5n/4wIZuv3u9sceGlQn85Mmtruev//FDPvrfI4Cj1f79K48TFx3U7Xf//O5BZqY+6BrUOskrqSMrp4zlS+e4BvvulmtUZKhrvOT868ntmVdSx6NPv4ql/fjp5UvnuCYzLZYW/umR3s+Y77j/TtfjZ37+Bx5+4kU+P3LWlV5BYVmH63THhpUJHayze7niooN46IFrrr/X3nwfcFiRnT/d3Ot8+go+b0GcOH+Q8bFjunSFnK3UhLgY13vus9T3bFzJurWpLp+9O51n6k+dueTqhjkntWLHRfHRX37WxYsE9GhZAFYtuzZz3nnSrrtxlCcrt/dQBoe++MblrXJy6ItvPOahM+5Wcu+hDOKigzj48W9c7z//u/dc663c1325s/3761yPnV4ruOZgcN7LvfuPsvOlfSxdksLc2UlsWHcLzz5WOKy6WsNCIO4uQ5PJ0KPrsafXnT9ySIh/h9d3Pbepgyu3oLCMf3z4VdfzZ37+B5d4YsdFcfzgy/zzs6/1enZ96ZIU1+M/vPqwa+4G6HZiszviooN4aPtaNt6xrNv5ig3rbiHnzHTe3r2vV10YZ2Py6127AXjpN/e70nU2DgBBgSa+Tct1LSlxz4+7Zdm950vX6+7et+pqM8/+y7sAPLvzbT54d6fLFR89JuK6XV5fYVgIpLyyoc8L7372kx+4fnjnmipnK+5cru3eOnY3z7D3UAavvfk+Tz66BXCI89UXHmfH/bm899eDHVyunddVLUyJ6bCQcO7spA6Wr6dx0YaVCcxMjmfK5HEkT5vUbQvu7GY6G4Tw8BCefHSLYx6osIy0i1lcySzk27Sus9W/ef4dEqfGul6/c+sL7H59BwvnTe/QOOx8aR+PbF/e5druWwycg/Otd8/tcL8tlhYeffpV1708ca64w328d9NaZkyfxOt//FAsVvQGeSV15Hm4kZ27XO4eL4ulpYM3prPVgO7F4cTZHXDOMYCjYu587gFq6xo7LG5058Xf7HA9dq5bcrdw925a22XC0jlwd1akzlRXm/nda391CfOR7cv58UP3dLAszi7hhnXdr+btbinKlh2vuyb9wLEpradVwn9+9yD+/kZWLpvHBx8f7bJcxmJp4bU33+8iTOd9dJatP8uAhoJhIZDe0N0Nd/5Y7jz1yz1MmjjWVan37j963Z15O1/ax7dpua5NUQDPv7ybd94/RezYyA4COX8hi4UpMS6Lsnf/UR586m2On7zUxWp15sy5y44NSZsvuSqoxdLCyVMX+eDjo11a21feOswrbx3mke3LuX39IpISJnQZS/UW98YhO6e4g0Aslhb+/O5BoN1LtuN14qKvLWfZePtpli+dg8XSwgM/+m2Pzgb3xuZSRo7PWw/w4SPY+kJ3u+bSLxd0mFTrzBu7trHvk5N9XjD37GPrmTJ5nEtUC1Ni+P5mxzLvzrsM39i1rcPsMjj66ksWTu427WMnMh3LR1JiWLVsRrddpOux9e65TE+M44O9x12Dd+dqBPDsCHDiXqaS0ioOfnH+uo6A3a/v6LIGzFP64NnB4SuMCIEIBIPFsJkHEQiGAiEQgcADQiACgQeEQAQCDwiBCAQeEAIRCDwgBCIQeEAIRCDwgBCIQOABIRCBwAPDfrHiruc2ERBgum6ofeexAQEBJhoaLB3WKvWF/qTzyPblJE6NBRxrxAYa1Pl6Zfa03stJXxYK+kKZh4phuxar8x4E9xiznXGPG+VOb1byDiSdDSsTePm3D3fZ6FRdbebRp1/t10LE3pS5N8cueIq9685Ql3moGVZdrLjoIHY9t4mcM6/x6guPd7vDrjNv7NrW7Q8Mjt147vG1vJlOXHQQb/7b093mMTw8hDf/7eke97l3TqevZfYWQ1VmX2JYCWTJwsk8sP2uXleShSkxHTYlPf/yboJi72Xv/mtROO64bel1f7T+pPPSb+7vEJF+ZuqD3HrnU66ACCaToVcHAPW1zOA4w8PJ4SOnefiJF7v8XY+hLLMvMawE4s7e/UcpKCzz+JmNG1JdjwsKr8XR3bLj9Q4/2oZbZ3k9nflzp7ke//HtveSV1HHiXDG73/ub63X3+FS9oTdlBlx9f4Cs7CJXEAr3v+vhK2UeaoaVQLJyynj+5d3MTH2QLTtev+4hOu7ncRz/Oq3De+5hetwr1O7Xd1BX8B5px150bezpazpb757bIZic+wDV/byR3hyJ0Ncyd6an803c8bUy+xLDSiAnzhWz86V9vQ4+5h7Ov/N5ee7RymdMd8SR3Xr3XFefO3ZclGtXXV/TiR0b6Xqtc4vf3REInuhrmTuz8XbHWOGNXdu6vZYvltmXGPZuXk+499tr665/WMuxE5lUV5tdEc6dB9n0NR3n9lagX0fFDRT3KInu++Xv3bSWJ39c1iHy+0gp82AxogXSV/JK6lh1+09YsnByh0NfhhvOM0O6I3ZcVIfDN0dKmQcLIZBOXC/E0HDg17t2c/FyqatbtmFlAj/+4XddkUpix0XxyPblrnHCSCjzYKECpKHOxGDh9LZAR9cngL//tUBvFZU1Xk3HPbJ754BynV3KzsNAvcneQxldAuCt2vgvVFdfi+To7pjojuFW5kFCGlaD9L7Sk6cK6HBmX1Z2kVfT8eS1cV8CYrG03NAuTW9CnToZKWUeKCNaIO5Hr7n73+OigzoEmvtgr+fz8vqaTudA1e4hPNesvDZoPnnq4nXL0B+6m/iMiw5yHZADXT1TnRluZR4sRvQY5N/f3NvBhfnhO084Qnv++No6pPSM3A4tmnPtkXs40r6mk1dSx+Ejp10eJOfRCmOjIzos3eh8roa3OPjxbzjxzQU+PXTakeexkWzbcu0Azepqc4d5ipFQ5sHCKRAJGJaLFj1x4lwxe/cfdf1A7udqgMPcO6OcQ9c5gX9+9C4efOrtPqcD8K8v/Rfz505zHcnmftAPOJaADFbozfDwEDasu6XbdVTOwNJORkqZBwEJRngXCxxLI97bc6DDoBNwnennvrr02IlM1ySX+5xAX9MBhzgf+NFvuxwNZ7G08N6eA13OO/cmp85c6vH1kVrmwULyi97kfDziLEhnujvYvjPOvRSePtObdNxxP5v9Rrag7jPWzri/3TGSyuxFJLjJBCIQ9IEuXawROx8iEPQRlxZG/BhEIBgInQUirIjgZqeDBoQFEQg80J1AhBUR3Kx0qfs9WRAhEsHNRrd1XnSxBAIPeBKIsCKCm4Ue67qwIAKBB64nEGFFBCMdj3W8NxZEiEQwUrlu3RZdLIHAA70ViLAigpFGr+p0XyyIEIlgpNDrutzXLpYQiWC406c63J8xiBCJYLjS57rb30G6EIlguNGvOjsQL5YQiWC40O+6OlA3rxCJwNcZUB31xjyINNBMCASDxIDrpTcnCoVIBL6C1xptb0dWdGZKREgRDAVeb6QHK/SoEIrgRjJovZfBjs0rhCIYTAa9W3+jglcLoQi8yQ0b797o6O5CKIKBcMMdQUN1/IF7QYVYBJ4YUu+oL5wP0t0NEKK5OfG5qYL/D78B7d70p5zxAAAAAElFTkSuQmCC&quot; /&gt;&lt;/p&gt;', 'published', null, null, '2019-06-13 00:00:00', '2019-06-14 18:12:40', '4028ea815a3d2a8c015a3d2f8d2a0002', '2020-02-04 02:08:04', null, '0', '4028ea815a3d2a8c015a3d2f8d2a0002', '00000000');
INSERT INTO `test_table` VALUES ('7', '山海文章', '1', 'china', '3', '&lt;p&gt;阿斯顿发送到，好像忘了标签&lt;/p&gt;', 'draft', null, null, '2019-12-17 11:25:00', '2019-12-17 11:26:11', 'admin', '2020-10-28 14:45:52', null, '1', '49cc47c9a5646525345621673dbc10fc', '35798760');
INSERT INTO `test_table` VALUES ('8', '', '1', 'china', '1', null, 'published', null, null, null, '2020-10-29 06:42:14', 'admin', '2020-10-29 06:42:56', null, '1', 'admin', '00000000');

-- ----------------------------
-- Table structure for `test_tree_and_table`
-- ----------------------------
DROP TABLE IF EXISTS `test_tree_and_table`;
CREATE TABLE `test_tree_and_table` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `name` varchar(255) DEFAULT '' COMMENT '部门名称',
  `type` varchar(250) DEFAULT '' COMMENT '类型',
  `tag` varchar(200) DEFAULT '' COMMENT '标签',
  `area_id` varchar(64) DEFAULT '',
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT '',
  `update_date` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT '',
  `del_flag` char(1) DEFAULT '0',
  `create_by` varchar(32) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of test_tree_and_table
-- ----------------------------
INSERT INTO `test_tree_and_table` VALUES ('030181840a5f449daad2a78c5c6b0d76', '市委', '市委', '22', '20d54a6a6bac28e00a424b1aee0773c7', null, 'admin', '2020-10-29 06:43:38', null, '0', null);
INSERT INTO `test_tree_and_table` VALUES ('10e79d5959bf71488ae6951ba5b6cc23', '虎门', '233', '33', null, '2020-10-29 06:45:07', null, null, null, '0', 'admin');
INSERT INTO `test_tree_and_table` VALUES ('289dc53ac88047ffe131ab852e714958', '11111', '11', '11', null, '2020-10-28 14:36:01', 'admin', '2020-10-28 14:36:14', null, '1', 'admin');
INSERT INTO `test_tree_and_table` VALUES ('c6201bd0a6534309a512575c3094ba7f', '城管', '城管', '阿达是否2', '70890a937a45c5aac09b34f667612206', null, 'admin', '2020-10-28 14:35:42', null, '0', null);

-- ----------------------------
-- Table structure for `test_tree_table`
-- ----------------------------
DROP TABLE IF EXISTS `test_tree_table`;
CREATE TABLE `test_tree_table` (
  `id` varchar(64) NOT NULL,
  `name` varchar(100) DEFAULT '' COMMENT '机构名称',
  `geocoding` varchar(32) DEFAULT '' COMMENT '是否叶子节点',
  `postal_code` varchar(32) DEFAULT '',
  `parent_id` varchar(32) DEFAULT '' COMMENT '父节点',
  `parent_ids` varchar(1000) DEFAULT '' COMMENT '父节点路径',
  `create_by` varchar(32) DEFAULT '',
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT '',
  `update_date` datetime DEFAULT NULL,
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标记',
  `remarks` varchar(255) DEFAULT NULL,
  `tenant_id` varchar(64) NOT NULL DEFAULT '00000000' COMMENT '租户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of test_tree_table
-- ----------------------------
INSERT INTO `test_tree_table` VALUES ('0246a2a1667c453fac27fa4fd118c18b', '陕西', '001001', '710000', null, null, '4028ea815a3d2a8c015a3d2f8d2a0002', '2019-05-02 23:33:26', '4028ea815a3d2a8c015a3d2f8d2a0002', '2019-05-26 18:26:54', '0', '陕西', '00000000');
INSERT INTO `test_tree_table` VALUES ('06cdf012b088513f11aeede4549ed121', '河西', '1123', '123123', 'e26e805cfeeb2b772db3924fddcbc807', '0246a2a1667c453fac27fa4fd118c18b/e26e805cfeeb2b772db3924fddcbc807/', '4028ea815a3d2a8c015a3d2f8d2a0002', '2019-11-13 12:02:21', null, null, '0', null, '00000000');
INSERT INTO `test_tree_table` VALUES ('1ac89971a6004d22b2a330986df11923', '河南', '00001', '0001', null, null, null, null, null, null, '0', null, '00000000');
INSERT INTO `test_tree_table` VALUES ('20d54a6a6bac28e00a424b1aee0773c7', '湖北', '421127', '22', null, null, '4028ea815a3d2a8c015a3d2f8d2a0002', '2020-10-21 14:11:30', '4028ea815a3d2a8c015a3d2f8d2a0002', '2020-10-21 14:11:59', '0', null, '00000000');
INSERT INTO `test_tree_table` VALUES ('2119058f59d19d3248e102d650a7bf42', '漯河', '000011', '000012', '1ac89971a6004d22b2a330986df11923', '1ac89971a6004d22b2a330986df11923/', null, null, null, null, '0', null, '00000000');
INSERT INTO `test_tree_table` VALUES ('9db9219474f6519913b3acd2f21554d4', '雁塔区', '1111', '11111', 'a25c56ceb3134e25abcd3f11241067b9', '0246a2a1667c453fac27fa4fd118c18b/a25c56ceb3134e25abcd3f11241067b9/', '4028ea815a3d2a8c015a3d2f8d2a0002', '2019-11-13 11:14:23', null, null, '0', null, '00000000');
INSERT INTO `test_tree_table` VALUES ('a25c56ceb3134e25abcd3f11241067b9', '西安', '2123165', '123123', '0246a2a1667c453fac27fa4fd118c18b', '0246a2a1667c453fac27fa4fd118c18b/', '4028ea815a3d2a8c015a3d2f8d2a0002', '2019-05-02 23:38:28', '4028ea815a3d2a8c015a3d2f8d2a0002', '2019-05-02 23:49:15', '0', '西安', '00000000');
INSERT INTO `test_tree_table` VALUES ('e26e805cfeeb2b772db3924fddcbc807', '咸阳', '11111', '22222', '0246a2a1667c453fac27fa4fd118c18b', '0246a2a1667c453fac27fa4fd118c18b/', '4028ea815a3d2a8c015a3d2f8d2a0002', '2019-11-13 11:38:14', null, null, '0', '咸阳', '00000000');
INSERT INTO `test_tree_table` VALUES ('f86cd3188268302758f215a8d69754a7', '11', '111', '1111', null, null, null, null, null, null, '0', null, '00000000');
INSERT INTO `test_tree_table` VALUES ('f9102cf866e5d615e1f9bf1563c1e7aa', '22', '2223', '22223', 'f86cd3188268302758f215a8d69754a7', 'f86cd3188268302758f215a8d69754a7/', null, null, null, null, '0', null, '00000000');
