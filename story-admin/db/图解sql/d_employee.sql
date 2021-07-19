/*
Navicat MySQL Data Transfer

Source Server         : docker-mysql
Source Server Version : 50731
Source Host           : localhost:3306
Source Database       : story_admin2

Target Server Type    : MYSQL
Target Server Version : 50731
File Encoding         : 65001

Date: 2021-07-12 09:06:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `d_employee`
-- ----------------------------
DROP TABLE IF EXISTS `d_employee`;
CREATE TABLE `d_employee` (
  `e_id` varchar(255) NOT NULL,
  `e_name` varchar(255) DEFAULT NULL,
  `dept_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`e_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of d_employee
-- ----------------------------
INSERT INTO `d_employee` VALUES ('0001', '张三', '0001');
INSERT INTO `d_employee` VALUES ('0002', '李四', '0001');
INSERT INTO `d_employee` VALUES ('0003', '王五', '0002');
INSERT INTO `d_employee` VALUES ('0004', '赵六', '0002');
