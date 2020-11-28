/*
 Navicat Premium Data Transfer

 Source Server         : docker_mysql5.7
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3307
 Source Schema         : story_admin2

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 27/11/2020 22:36:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '文件名',
  `path` varchar(255) NOT NULL COMMENT '路径',
  `type` varchar(255) NOT NULL COMMENT '类型',
  `size` varchar(255) NOT NULL COMMENT '大小',
  `parent_id` bigint(11) NOT NULL COMMENT '父级ID',
  `personal` tinyint(1) NOT NULL COMMENT '是否是个人网盘',
  `created_time` datetime NOT NULL COMMENT '创造时间',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of file
-- ----------------------------
BEGIN;
INSERT INTO `file` VALUES (1, '根目录', '/', '文件夹', '-', 0, 1, '2020-01-01 00:00:00', 1);
INSERT INTO `file` VALUES (2, '美国队长', '/upload/file/1606142970336-美国队长.jpg', 'jpg', '137KB', 1, 0, '2020-11-23 22:49:30', 1);
INSERT INTO `file` VALUES (4, '图像', '/upload/file/1606143033010-图像.jpeg', 'jpeg', '13KB', 3, 0, '2020-11-23 22:50:33', 1);
INSERT INTO `file` VALUES (6, 'lipan', '/', '文件夹', '-', 1, 0, '2020-11-25 23:01:33', 1);
INSERT INTO `file` VALUES (7, '可达鸭', '/upload/file/1606316516840-图像.jpeg', 'jpeg', '13KB', 6, 0, '2020-11-25 23:01:57', 1);
INSERT INTO `file` VALUES (8, '美国队长', '/upload/file/1606317416610-美国队长.jpg', 'jpg', '137KB', 1, 1, '2020-11-25 23:16:57', 1);
INSERT INTO `file` VALUES (9, '《Java小咖秀全级别工程狮面试手册》', '/upload/file/1606317427670-《Java小咖秀全级别工程狮面试手册》.pdf', 'pdf', '12MB', 1, 1, '2020-11-25 23:17:08', 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
