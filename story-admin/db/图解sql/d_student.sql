/*
Navicat MySQL Data Transfer

Source Server         : docker-mysql
Source Server Version : 50731
Source Host           : localhost:3306
Source Database       : story_admin2

Target Server Type    : MYSQL
Target Server Version : 50731
File Encoding         : 65001

Date: 2021-07-06 17:38:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `d_student`
-- ----------------------------
DROP TABLE IF EXISTS `d_student`;
CREATE TABLE `d_student` (
  `s_id` varchar(255) DEFAULT NULL,
  `s_name` varchar(255) DEFAULT NULL,
  `s_birthday` date DEFAULT NULL,
  `s_sex` char(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of d_student
-- ----------------------------
INSERT INTO `d_student` VALUES ('0001', '猴子', '1989-01-01', '男');
INSERT INTO `d_student` VALUES ('0002', '猴子', '1990-12-21', '女');
INSERT INTO `d_student` VALUES ('0003', '马云', '1991-12-21', '男');
INSERT INTO `d_student` VALUES ('0004', '王思聪', '1990-05-20', '男');
