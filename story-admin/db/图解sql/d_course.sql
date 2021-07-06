/*
Navicat MySQL Data Transfer

Source Server         : docker-mysql
Source Server Version : 50731
Source Host           : localhost:3306
Source Database       : story_admin2

Target Server Type    : MYSQL
Target Server Version : 50731
File Encoding         : 65001

Date: 2021-07-06 17:38:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `d_course`
-- ----------------------------
DROP TABLE IF EXISTS `d_course`;
CREATE TABLE `d_course` (
  `c_id` varchar(255) DEFAULT NULL,
  `c_name` varchar(255) DEFAULT NULL,
  `teacher_id` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of d_course
-- ----------------------------
INSERT INTO `d_course` VALUES ('0001', '语文', '0002');
INSERT INTO `d_course` VALUES ('0002', '数学', '0001');
INSERT INTO `d_course` VALUES ('0003', '英语', '0003');
