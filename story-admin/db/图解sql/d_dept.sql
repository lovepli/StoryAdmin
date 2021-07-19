/*
Navicat MySQL Data Transfer

Source Server         : docker-mysql
Source Server Version : 50731
Source Host           : localhost:3306
Source Database       : story_admin2

Target Server Type    : MYSQL
Target Server Version : 50731
File Encoding         : 65001

Date: 2021-07-12 09:06:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `d_dept`
-- ----------------------------
DROP TABLE IF EXISTS `d_dept`;
CREATE TABLE `d_dept` (
  `d_id` varchar(255) NOT NULL,
  `d_name` varchar(255) DEFAULT NULL,
  `parent_id` varchar(255) DEFAULT NULL,
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
