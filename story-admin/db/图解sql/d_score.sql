/*
Navicat MySQL Data Transfer

Source Server         : docker-mysql
Source Server Version : 50731
Source Host           : localhost:3306
Source Database       : story_admin2

Target Server Type    : MYSQL
Target Server Version : 50731
File Encoding         : 65001

Date: 2021-07-06 17:38:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `d_score`
-- ----------------------------
DROP TABLE IF EXISTS `d_score`;
CREATE TABLE `d_score` (
  `ds_id` varchar(255) DEFAULT NULL,
  `course_id` varchar(255) DEFAULT NULL,
  `ds_score` int(11) DEFAULT NULL
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
