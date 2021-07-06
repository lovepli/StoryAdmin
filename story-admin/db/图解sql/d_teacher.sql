/*
Navicat MySQL Data Transfer

Source Server         : docker-mysql
Source Server Version : 50731
Source Host           : localhost:3306
Source Database       : story_admin2

Target Server Type    : MYSQL
Target Server Version : 50731
File Encoding         : 65001

Date: 2021-07-06 17:38:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `d_teacher`
-- ----------------------------
DROP TABLE IF EXISTS `d_teacher`;
CREATE TABLE `d_teacher` (
  `t_id` varchar(255) DEFAULT NULL,
  `t_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of d_teacher
-- ----------------------------
INSERT INTO `d_teacher` VALUES ('0001', '孟扎扎');
INSERT INTO `d_teacher` VALUES ('0002', '马化腾');
INSERT INTO `d_teacher` VALUES ('0003', 'NULL');
INSERT INTO `d_teacher` VALUES ('0004', '');
