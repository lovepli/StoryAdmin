/*
 Navicat Premium Data Transfer

 Source Server         : docker_MYSQL5.7
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : localhost:3306
 Source Schema         : story_admin

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 21/08/2020 17:30:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ST_SYSTEM_SEQUENCE
-- ----------------------------
DROP TABLE IF EXISTS `ST_SYSTEM_SEQUENCE`;
CREATE TABLE `ST_SYSTEM_SEQUENCE`  (
  `table_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `column_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sequence` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`table_name`, `column_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ST_SYSTEM_SEQUENCE
-- ----------------------------
INSERT INTO `ST_SYSTEM_SEQUENCE` VALUES ('ATTACHMENT', 'SEQUENCE', 16);

-- ----------------------------
-- Table structure for st_att
-- ----------------------------
DROP TABLE IF EXISTS `st_att`;
CREATE TABLE `st_att`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件名',
  `slot_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '批次',
  `file_cate` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类',
  `type` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件类型',
  `file_size` bigint(20) NULL DEFAULT NULL COMMENT '文件大小',
  `origin_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '源文件名',
  `path` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '存储路径',
  `description` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `yn_flag` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '有效标志',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `editor` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '附件表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of st_att
-- ----------------------------
INSERT INTO `st_att` VALUES (1, NULL, 'fb135e8c-15c5-4248-91d1-ce6cfd762315', NULL, 'text/plain', 24, 'test.txt', 'sysmgr\\att\\upload\\2019\\09\\20190917103547386_.txt', NULL, '0', NULL, 'admin', '2019-09-17 02:35:45', '2019-09-17 03:10:06');
INSERT INTO `st_att` VALUES (2, NULL, '6e4b7792-b047-4bc5-a776-60ec1ea800bc', NULL, 'text/plain', 24, 'test.txt', 'sysmgr\\att\\upload\\2019\\09\\20190917104316003_.txt', NULL, '0', NULL, 'admin', '2019-09-17 02:43:13', '2019-09-17 03:10:11');
INSERT INTO `st_att` VALUES (3, NULL, '1123b4ff-dc8e-463a-a667-a98b5fac286b', NULL, 'text/plain', 24, 'test.txt', 'sysmgr\\att\\upload\\2019\\09\\20190917104429759_.txt', NULL, '0', 'admin', 'admin', '2019-09-17 02:44:27', '2020-08-12 07:26:58');
INSERT INTO `st_att` VALUES (4, NULL, 'a5805f49-50ae-482b-a08a-4f5e481d0bb5', NULL, 'text/plain', 24, 'test.txt', 'sysmgr\\att\\upload\\2019\\09\\20190917104607876_.txt', NULL, '1', 'admin', 'admin', '2019-09-17 02:46:07', '2019-09-17 02:46:07');
INSERT INTO `st_att` VALUES (5, NULL, 'ecd4d39d-1c12-40d6-af53-3e25e298a577', NULL, 'text/plain', 24, 'test.txt', 'sysmgr\\att\\upload\\2019\\09\\20190917105941981_.txt', NULL, '1', 'admin', 'admin', '2019-09-17 02:59:41', '2019-09-17 02:59:41');
INSERT INTO `st_att` VALUES (6, NULL, '4ca31b41-9e3f-4e45-a664-83224b42dc17', NULL, 'text/plain', 24, 'test.txt', 'sysmgr\\att\\upload\\2019\\09\\20190917110251321_.txt', NULL, '1', 'admin', 'admin', '2019-09-17 03:02:49', '2019-09-17 03:02:49');
INSERT INTO `st_att` VALUES (7, NULL, '9317023f-0b72-4709-a452-c6631d26988a', NULL, 'text/plain', 24, 'test.txt', 'sysmgr\\att\\upload\\2019\\09\\20190917111001050_.txt', NULL, '1', 'admin', 'admin', '2019-09-17 03:10:01', '2019-09-17 03:10:01');
INSERT INTO `st_att` VALUES (8, NULL, '35f2c37a-2b8e-4d2c-a9be-782656897c96', NULL, 'application/octet-stream', 96, 'test.rar', 'sysmgr\\att\\upload\\2019\\09\\20190917111905377_.rar', NULL, '1', 'admin', 'admin', '2019-09-17 03:19:05', '2019-09-17 03:19:05');
INSERT INTO `st_att` VALUES (9, NULL, '1829f0c9-6aa8-4634-a206-28b50d6d39d4', NULL, 'application/octet-stream', 96, 'test.rar', 'sysmgr\\att\\upload\\2019\\09\\20190917112253117_.rar', NULL, '1', 'admin', 'admin', '2019-09-17 03:22:53', '2019-09-17 03:22:53');
INSERT INTO `st_att` VALUES (10, NULL, '8df3f2af-3830-496a-afee-af76bca3d699', NULL, '.rar', 96, 'test.rar', 'sysmgr\\att\\upload\\2019\\09\\20190917112513577_.rar', NULL, '1', 'admin', 'admin', '2019-09-17 03:25:14', '2019-09-17 03:25:14');
INSERT INTO `st_att` VALUES (11, NULL, 'aa', NULL, '.png', 142525, 'SSO.png', '11\\2020\\08\\20200812165238537_.png', NULL, '0', 'admin', 'admin', '2020-08-12 08:52:39', '2020-08-12 09:04:56');
INSERT INTO `st_att` VALUES (12, NULL, 'aa', NULL, '.png', 181858, 'shiro+redis登录认证.png', '11\\2020\\08\\20200812165659503_.png', NULL, '0', 'admin', 'admin', '2020-08-12 08:57:00', '2020-08-12 09:04:58');
INSERT INTO `st_att` VALUES (13, NULL, '第一批次', NULL, '.jpeg', 45399, '3b292df5e0fe99257730fddd24c843d88cb171e2.jpeg', 'sysmgr\\att\\upload\\2020\\08\\20200813135013169_.jpeg', NULL, '1', 'admin', 'admin', '2020-08-13 05:50:13', '2020-08-13 05:50:13');

-- ----------------------------
-- Table structure for st_attachment
-- ----------------------------
DROP TABLE IF EXISTS `st_attachment`;
CREATE TABLE `st_attachment`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sequence` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `file_name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `file_path` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `upload_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `uploader` bigint(20) NOT NULL,
  `STATUS` decimal(2, 0) NOT NULL DEFAULT 1 COMMENT '1 正常 0 已被删除',
  `deleter` bigint(20) NULL DEFAULT NULL,
  `delete_date` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `real_file_name` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `file_size` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `SYSTEM_FILE_SYSTEM_STAFF_id_fk`(`uploader`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '附件表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of st_attachment
-- ----------------------------
INSERT INTO `st_attachment` VALUES (5, 'ATS200814000012', '面试问题总结.docx', 'D:\\bird_files\\ATS200814000012_面试问题总结.docx', '2020-08-14 05:12:43', 1, 1, NULL, '2020-08-14 05:12:42', 'ATS200814000012_面试问题总结.docx', 11671);
INSERT INTO `st_attachment` VALUES (6, 'ATS200814000031', '面试问题总结.docx', 'D:\\bird_files\\ATS200814000031_面试问题总结.docx', '2020-08-14 05:28:45', 1, 1, NULL, '2020-08-14 05:28:45', 'ATS200814000031_面试问题总结.docx', 11671);
INSERT INTO `st_attachment` VALUES (13, 'ATS200814000103', '图标.png', 'D:\\bird_files\\ATS200814000103_图标.png', '2020-08-14 07:24:31', 1, 1, NULL, '2020-08-14 07:24:31', 'ATS200814000103_图标.png', 25292);
INSERT INTO `st_attachment` VALUES (14, 'ATS200814000110', '鱼片.png', 'D:\\bird_files\\ATS200814000110_鱼片.png', '2020-08-14 07:24:39', 1, 1, NULL, '2020-08-14 07:24:39', 'ATS200814000110_鱼片.png', 625);
INSERT INTO `st_attachment` VALUES (18, 'ATS200814000159', 'STORY-ADMIN.pdf', 'D:\\bird_files\\ATS200814000159_STORY-ADMIN.pdf', '2020-08-14 08:00:07', 1, 1, NULL, '2020-08-14 08:00:07', 'ATS200814000159_STORY-ADMIN.pdf', 41025);
INSERT INTO `st_attachment` VALUES (19, 'ATS200814000161', '各种规格材料说明.docx', 'D:\\bird_files\\ATS200814000161_各种规格材料说明.docx', '2020-08-14 08:32:20', 1, 1, NULL, '2020-08-14 08:32:19', 'ATS200814000161_各种规格材料说明.docx', 11647);

-- ----------------------------
-- Table structure for st_authority
-- ----------------------------
DROP TABLE IF EXISTS `st_authority`;
CREATE TABLE `st_authority`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '权限名称',
  `code` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '权限编码',
  `full_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '编号路径',
  `authority_desc` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '权限描述',
  `show_order` int(11) NULL DEFAULT NULL COMMENT '排序',
  `pid` bigint(20) NULL DEFAULT NULL COMMENT '父Id',
  `yn_flag` char(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '有效标志',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
  `editor` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '修改人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 79 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of st_authority
-- ----------------------------
INSERT INTO `st_authority` VALUES (1, '公共', 'public', '0', NULL, 1, 0, '1', NULL, NULL, NULL, '2019-07-26 16:44:51');
INSERT INTO `st_authority` VALUES (2, '系统管理', 'sysmgr', '0', NULL, 1, 0, '1', NULL, NULL, NULL, '2019-07-26 11:56:42');
INSERT INTO `st_authority` VALUES (3, '用户管理', 'sysmgr.user', '0-2', NULL, 1, 2, '1', NULL, NULL, NULL, '2019-07-26 18:44:24');
INSERT INTO `st_authority` VALUES (4, '角色管理', 'sysmgr.rule', '0-2', NULL, 2, 2, '1', NULL, NULL, NULL, '2019-07-26 11:53:51');
INSERT INTO `st_authority` VALUES (5, '菜单管理', 'sysmgr.resource', '0-2', NULL, 3, 2, '1', NULL, NULL, NULL, '2019-07-26 18:44:50');
INSERT INTO `st_authority` VALUES (6, '权限管理', 'sysmgr.authority', '0-2', NULL, 4, 2, '1', NULL, NULL, NULL, '2019-07-26 18:44:53');
INSERT INTO `st_authority` VALUES (7, '查询用户', 'sysmgr.user.query', '0-2-3', NULL, 1, 3, '1', NULL, NULL, NULL, '2019-07-26 18:44:57');
INSERT INTO `st_authority` VALUES (8, '编辑用户', 'sysmgr.user.save', '0-2-3', NULL, 2, 3, '1', NULL, NULL, NULL, '2019-07-26 18:45:00');
INSERT INTO `st_authority` VALUES (9, '删除用户', 'sysmgr.user.delete', '0-2-3', NULL, 3, 3, '1', NULL, NULL, NULL, '2019-07-26 18:45:04');
INSERT INTO `st_authority` VALUES (10, '查询角色', 'sysmgr.role.query', '0-2-4', NULL, 1, 4, '1', NULL, NULL, NULL, '2019-07-26 18:45:06');
INSERT INTO `st_authority` VALUES (11, '编辑角色', 'sysmgr.role.save', '0-2-4', NULL, 2, 4, '1', NULL, NULL, NULL, '2019-07-26 18:45:09');
INSERT INTO `st_authority` VALUES (12, '删除角色', 'sysmgr.role.delete', '0-2-4', NULL, 3, 4, '1', NULL, NULL, NULL, '2019-07-26 18:45:13');
INSERT INTO `st_authority` VALUES (13, '查询菜单', 'sysmgr.resource.query', '0-2-5', NULL, 1, 5, '1', NULL, NULL, NULL, '2019-07-26 11:50:34');
INSERT INTO `st_authority` VALUES (14, '编辑菜单', 'sysmgr.resource.save', '0-2-5', NULL, 2, 5, '1', NULL, NULL, NULL, '2019-07-26 18:45:23');
INSERT INTO `st_authority` VALUES (15, '删除菜单', 'sysmgr.resource.delete', '0-2-5', NULL, 3, 5, '1', NULL, NULL, NULL, '2019-07-26 18:45:26');
INSERT INTO `st_authority` VALUES (16, '查询权限', 'sysmgr.authority.query', '0-2-6', NULL, 1, 6, '1', NULL, NULL, NULL, '2019-07-26 18:45:28');
INSERT INTO `st_authority` VALUES (17, '编辑权限', 'sysmgr.authority.save', '0-2-6', NULL, 2, 6, '1', NULL, NULL, NULL, '2019-07-26 18:45:31');
INSERT INTO `st_authority` VALUES (18, '删除权限', 'sysmgr.authority.delete', '0-2-6', NULL, 3, 6, '1', NULL, NULL, NULL, '2019-07-26 18:45:35');
INSERT INTO `st_authority` VALUES (30, '登录日志', 'sysmgr.loginlog', '0-2', NULL, 5, 2, '1', 'admin', 'admin', '2019-07-26 03:11:50', '2019-07-26 18:45:38');
INSERT INTO `st_authority` VALUES (31, '查询日志', 'sysmgr.loginlog.query', '0-2-30', NULL, 1, 30, '1', 'admin', 'admin', '2019-07-26 03:12:14', '2019-07-26 11:37:46');
INSERT INTO `st_authority` VALUES (32, '删除日志', 'sysmgr.loginlog.delete', '0-2-30', NULL, 2, 30, '1', 'admin', 'admin', '2019-07-26 03:12:44', '2019-07-26 11:37:54');
INSERT INTO `st_authority` VALUES (33, '基础信息', 'baseinfo', '0', NULL, 3, 0, '1', 'admin', 'admin', '2019-07-26 03:13:11', '2019-07-26 03:13:11');
INSERT INTO `st_authority` VALUES (34, '字典管理', 'baseinfo.dict', '0-33', NULL, 1, 33, '1', 'admin', 'admin', '2019-07-26 03:13:31', '2019-07-26 03:13:31');
INSERT INTO `st_authority` VALUES (35, '查询字典', 'baseinfo.dict.query', '0-33-34', NULL, 1, 34, '1', 'admin', 'admin', '2019-07-26 03:14:01', '2019-07-26 11:37:20');
INSERT INTO `st_authority` VALUES (36, '编辑字典', 'baseinfo.dict.save', '0-33-34', NULL, 2, 34, '1', 'admin', 'admin', '2019-07-26 03:14:43', '2019-07-26 11:37:30');
INSERT INTO `st_authority` VALUES (37, '删除字典', 'baseinfo.dict.delete', '0-33-34', NULL, 3, 34, '1', 'admin', 'admin', '2019-07-26 03:15:03', '2019-07-26 11:37:38');
INSERT INTO `st_authority` VALUES (38, '附件管理', 'sysmgr.att', '0-2', NULL, 6, 2, '1', 'admin', 'admin', '2019-09-24 13:03:19', '2019-09-24 21:05:50');
INSERT INTO `st_authority` VALUES (39, '查询附件', 'sysmgr.att.query', '0-2-38', NULL, 1, 38, '1', 'admin', 'admin', '2019-09-24 13:05:23', '2019-09-24 21:06:07');
INSERT INTO `st_authority` VALUES (40, '删除附件', 'sysmgr.att.delete', '0-2-38', NULL, 2, 38, '1', 'admin', 'admin', '2019-09-24 13:06:55', '2019-09-24 21:08:27');
INSERT INTO `st_authority` VALUES (41, '系统日志', 'sysmgr.syslog', '0-2', NULL, 7, 2, '1', 'admin', 'admin', '2019-09-24 13:09:29', '2019-09-24 21:09:48');
INSERT INTO `st_authority` VALUES (42, '查询系统日志', 'sysmgr.syslog.query', '0-2-41', NULL, 1, 41, '1', 'admin', 'admin', '2019-09-24 13:13:39', '2019-09-24 13:13:39');
INSERT INTO `st_authority` VALUES (43, '系统备份', 'sysmgr.backup', '0-2', NULL, 8, 2, '1', 'admin', 'admin', '2019-09-25 06:10:01', '2019-09-25 06:10:01');
INSERT INTO `st_authority` VALUES (44, '查询备份', 'sysmgr.backup.query', '0-2-43', NULL, 1, 43, '1', 'admin', 'admin', '2019-09-25 06:10:15', '2019-09-25 06:10:15');
INSERT INTO `st_authority` VALUES (45, '删除备份', 'sysmgr.backup.delete', '0-2-43', NULL, 2, 43, '1', 'admin', 'admin', '2019-09-25 06:10:35', '2019-09-25 06:12:43');
INSERT INTO `st_authority` VALUES (46, '定时任务', 'sysmgr.schedulejob', '0-2', NULL, 9, 2, '0', 'admin', 'admin', '2019-09-25 06:11:03', '2020-08-21 08:41:19');
INSERT INTO `st_authority` VALUES (47, '查询任务', 'sysmgr.schedulejob.query', '0-2-46', NULL, 1, 46, '1', 'admin', 'admin', '2019-09-25 06:11:35', '2019-09-25 06:12:34');
INSERT INTO `st_authority` VALUES (48, '编辑任务', 'sysmgr.schedulejob.save', '0-2-46', NULL, 2, 46, '1', 'admin', 'admin', '2019-09-25 06:12:04', '2019-09-25 06:12:04');
INSERT INTO `st_authority` VALUES (49, '删除任务', 'sysmgr.schedulejob.delete', '0-2-46', NULL, 3, 46, '1', 'admin', 'admin', '2019-09-25 06:12:25', '2019-09-25 06:12:25');
INSERT INTO `st_authority` VALUES (50, '上传附件', 'sysmgr.att.upload', '0-2-38', NULL, 3, 38, '1', 'admin', 'admin', '2019-09-25 06:15:38', '2019-09-25 06:15:57');
INSERT INTO `st_authority` VALUES (51, '个人空间', 'tool', '0', NULL, 10, 0, '1', 'admin', 'admin', '2019-09-25 07:11:47', '2019-09-25 07:11:47');
INSERT INTO `st_authority` VALUES (52, '待办事项', 'tool.todo', '0-51', NULL, 1, 51, '1', 'admin', 'admin', '2019-09-25 07:13:25', '2019-09-25 07:13:25');
INSERT INTO `st_authority` VALUES (53, '查询待办事项', 'tool.todo.query', '0-51-52', NULL, 1, 52, '1', 'admin', 'admin', '2019-09-25 07:13:41', '2019-09-25 07:13:41');
INSERT INTO `st_authority` VALUES (54, '编辑待办事项', 'tool.todo.save', '0-51-52', NULL, 2, 52, '1', 'admin', 'admin', '2019-09-25 07:14:22', '2019-09-25 07:14:22');
INSERT INTO `st_authority` VALUES (55, '删除待办事项', 'tool.todo.delete', '0-51-52', NULL, 3, 52, '1', 'admin', 'admin', '2019-09-25 07:14:45', '2019-09-25 07:14:45');
INSERT INTO `st_authority` VALUES (56, '系统公告', 'sysmgr.inform', '0-2', NULL, 20, 2, '1', 'admin', 'admin', '2020-08-11 08:46:27', '2020-08-11 08:46:27');
INSERT INTO `st_authority` VALUES (57, '查询列表', 'sysmgr.inform.query', '0-2-56', NULL, 1, 56, '1', 'admin', 'admin', '2020-08-11 08:47:25', '2020-08-13 01:14:08');
INSERT INTO `st_authority` VALUES (58, '查看详情', 'sysmgr.inform.query', '0-2-56', NULL, 2, 56, '1', 'admin', 'admin', '2020-08-11 08:48:13', '2020-08-13 01:11:14');
INSERT INTO `st_authority` VALUES (59, '新增公告', 'sysmgr.inform.save', '0-2-56', NULL, 3, 56, '1', 'admin', 'admin', '2020-08-11 08:48:51', '2020-08-11 08:48:51');
INSERT INTO `st_authority` VALUES (60, '置顶', 'sysmgr.inform.top', '0-2-56', NULL, 4, 56, '1', 'admin', 'admin', '2020-08-11 08:49:32', '2020-08-13 01:11:27');
INSERT INTO `st_authority` VALUES (61, '取消置顶', 'sysmgr.inform.untop', '0-2-56', NULL, 5, 56, '1', 'admin', 'admin', '2020-08-11 08:50:05', '2020-08-13 01:11:44');
INSERT INTO `st_authority` VALUES (62, '附件上传下载', 'sysmgr.file', '0-2', NULL, 21, 2, '1', 'admin', 'admin', '2020-08-12 06:46:06', '2020-08-12 06:46:06');
INSERT INTO `st_authority` VALUES (63, '附件上传', 'sysmgr.file.upload', '0-2-62', NULL, 1, 62, '1', 'admin', 'admin', '2020-08-12 06:46:38', '2020-08-12 06:46:38');
INSERT INTO `st_authority` VALUES (64, '附件删除', 'sysmgr.file.delete', '0-2-62', NULL, 3, 62, '1', 'admin', 'admin', '2020-08-12 06:47:12', '2020-08-12 06:47:12');
INSERT INTO `st_authority` VALUES (65, '附件下载', 'sysmgr.file.download', '0-2-62', NULL, 2, 62, '1', 'admin', 'admin', '2020-08-12 06:47:38', '2020-08-12 06:47:38');
INSERT INTO `st_authority` VALUES (66, '撤销', 'sysmgr.inform.cancel', '0-2-56', NULL, 6, 56, '1', 'admin', 'admin', '2020-08-13 01:13:37', '2020-08-13 01:13:37');
INSERT INTO `st_authority` VALUES (67, '过期', 'sysmgr.inform.outdate', '0-2-56', NULL, 7, 56, '1', 'admin', 'admin', '2020-08-13 01:14:39', '2020-08-13 01:14:39');
INSERT INTO `st_authority` VALUES (68, '部门管理', 'sysmgr.dept', '0-2', NULL, 10, 2, '1', 'admin', 'admin', '2020-08-14 08:34:41', '2020-08-14 08:34:41');
INSERT INTO `st_authority` VALUES (69, '查询权限', 'sysmgr.dept.query', '0-2-68', NULL, 1, 68, '1', 'admin', 'admin', '2020-08-14 08:35:12', '2020-08-14 08:38:03');
INSERT INTO `st_authority` VALUES (70, '编辑权限', 'sysmgr.dept.save', '0-2-68', NULL, 2, 68, '1', 'admin', 'admin', '2020-08-14 08:35:41', '2020-08-14 08:38:17');
INSERT INTO `st_authority` VALUES (71, '删除权限', 'sysmgr.dept.delete', '0-2-68', NULL, 3, 68, '1', 'admin', 'admin', '2020-08-14 08:37:32', '2020-08-14 08:38:30');
INSERT INTO `st_authority` VALUES (72, '服务监控', 'monitor', '0', NULL, 1, 0, '1', 'admin', 'admin', '2020-08-21 07:54:45', '2020-08-21 07:54:45');
INSERT INTO `st_authority` VALUES (73, '系统监控', 'monitor.server', '0-72', NULL, 1, 72, '1', 'admin', 'admin', '2020-08-21 07:55:37', '2020-08-21 07:55:37');
INSERT INTO `st_authority` VALUES (74, '系统监控查询', 'monitor.server.query', '0-72-73', NULL, 1, 73, '1', 'admin', 'admin', '2020-08-21 07:56:44', '2020-08-21 07:56:44');
INSERT INTO `st_authority` VALUES (75, '定时任务', 'monitor.schedulejob', '0-72', NULL, 2, 72, '1', 'admin', 'admin', '2020-08-21 08:27:21', '2020-08-21 08:30:06');
INSERT INTO `st_authority` VALUES (76, '查询任务', 'monitor.schedulejob.query', '0-72-75', NULL, 1, 75, '1', 'admin', 'admin', '2020-08-21 08:28:12', '2020-08-21 08:28:12');
INSERT INTO `st_authority` VALUES (77, '编辑任务', 'monitor.schedulejob.save', '0-72-75', NULL, 2, 75, '1', 'admin', 'admin', '2020-08-21 08:28:48', '2020-08-21 08:28:48');
INSERT INTO `st_authority` VALUES (78, '删除任务', 'monitor.schedulejob.delete', '0-72-75', NULL, 3, 75, '1', 'admin', 'admin', '2020-08-21 08:29:21', '2020-08-21 08:29:41');

-- ----------------------------
-- Table structure for st_backup
-- ----------------------------
DROP TABLE IF EXISTS `st_backup`;
CREATE TABLE `st_backup`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `backup_date` datetime(0) NULL DEFAULT NULL,
  `backup_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `backup_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `db_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `file_size` bigint(20) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `runtime` bigint(20) NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  `yn_flag` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '有效标志',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `editor` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'DB备份表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of st_backup
-- ----------------------------
INSERT INTO `st_backup` VALUES (34, '2019-09-10 10:28:01', '2019-09-10 数据备份', '/201909/1568111280776.sql', 'story_admin', 38289, NULL, 0, 10, '1', NULL, NULL, '2019-09-10 10:28:01', '2019-09-10 10:28:01');
INSERT INTO `st_backup` VALUES (35, '2019-09-10 10:29:00', '2019-09-10 数据备份', '/201909/1568111340472.sql', 'story_admin', 38491, NULL, 0, 10, '1', NULL, NULL, '2019-09-10 10:29:00', '2019-09-10 10:29:00');
INSERT INTO `st_backup` VALUES (36, '2019-09-10 10:30:00', '2019-09-10 数据备份', '/201909/1568111400482.sql', 'story_admin', 38660, NULL, 0, 10, '0', NULL, 'admin', '2019-09-10 10:30:00', '2019-09-10 11:38:42');

-- ----------------------------
-- Table structure for st_dept
-- ----------------------------
DROP TABLE IF EXISTS `st_dept`;
CREATE TABLE `st_dept`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '部门名称',
  `pid` bigint(20) NULL DEFAULT NULL COMMENT '父id',
  `show_order` int(11) NULL DEFAULT NULL COMMENT '排序',
  `dept_desc` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '部门描述',
  `yn_flag` char(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '有效标志',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `editor` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `created_time` datetime(0) NULL DEFAULT NULL,
  `modified_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of st_dept
-- ----------------------------
INSERT INTO `st_dept` VALUES (1, '研发部门', 0, 1, 'hello,world', '1', 'admin', 'admin', '2020-07-31 08:29:50', '2020-07-31 08:32:36');
INSERT INTO `st_dept` VALUES (2, '测试部门', 0, 2, NULL, '1', 'admin', 'admin', '2020-07-31 08:30:20', '2020-07-31 08:30:20');
INSERT INTO `st_dept` VALUES (3, '运维部门', 0, 3, NULL, '1', 'admin', 'admin', '2020-07-31 08:30:30', '2020-07-31 08:31:45');
INSERT INTO `st_dept` VALUES (4, '销售部门', 0, 4, NULL, '0', 'admin', 'admin', '2020-07-31 08:31:00', '2020-08-18 07:12:56');
INSERT INTO `st_dept` VALUES (5, '研发一组', 1, 1, NULL, '1', 'admin', 'admin', '2020-07-31 08:31:16', '2020-07-31 08:31:16');
INSERT INTO `st_dept` VALUES (6, '研发二组', 1, 2, '1111111', '1', 'admin', 'admin', '2020-07-31 08:31:30', '2020-07-31 08:46:36');
INSERT INTO `st_dept` VALUES (7, '预备研发一组', 5, 1, '测试数据', '1', 'admin', 'admin', '2020-07-31 08:32:04', '2020-07-31 08:42:13');
INSERT INTO `st_dept` VALUES (8, '预备研发二组', 5, 2, '11', '1', 'admin', 'admin', '2020-07-31 08:32:17', '2020-07-31 08:46:25');
INSERT INTO `st_dept` VALUES (9, '新增部门', 6, NULL, NULL, '0', 'admin', 'admin', '2020-07-31 08:36:53', '2020-07-31 08:37:01');

-- ----------------------------
-- Table structure for st_dict
-- ----------------------------
DROP TABLE IF EXISTS `st_dict`;
CREATE TABLE `st_dict`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `code` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '编码',
  `chn_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '中文名称',
  `eng_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '英文名称',
  `show_order` int(11) NULL DEFAULT NULL COMMENT '排序',
  `parent_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '父编码',
  `yn_flag` char(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '有效标志',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
  `editor` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '修改人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of st_dict
-- ----------------------------
INSERT INTO `st_dict` VALUES (1, 'true_or_false', '是否', 'YesOrNo', 1, '', '1', 'admin', 'admin', '2019-07-25 09:54:56', '2019-07-25 18:05:07');
INSERT INTO `st_dict` VALUES (2, '1', '是', 'Yes', 1, 'true_or_false', '1', 'admin', 'admin', '2019-07-25 09:54:56', '2019-07-25 20:10:18');
INSERT INTO `st_dict` VALUES (3, '0', '否', 'No', 2, 'true_or_false', '1', 'admin', 'admin', '2019-07-25 09:54:56', '2019-07-25 20:10:20');
INSERT INTO `st_dict` VALUES (4, 'effective_or_ineffective', '有效无效', NULL, 2, '', '1', 'admin', 'admin', '2019-07-25 09:54:56', '2019-07-25 17:57:15');
INSERT INTO `st_dict` VALUES (5, '1', '有效', 'Effective', 1, 'effective_or_ineffective', '1', 'admin', 'admin', '2019-07-25 09:54:56', '2019-07-25 20:06:09');
INSERT INTO `st_dict` VALUES (6, '0', '无效', 'Ineffective', 2, 'effective_or_ineffective', '1', 'admin', 'admin', '2019-07-25 09:54:56', '2019-07-25 20:06:11');
INSERT INTO `st_dict` VALUES (7, 'enable_or_disable', '启用或禁用', NULL, 3, '', '1', 'admin', 'admin', '2019-07-25 11:44:38', '2019-07-25 17:57:15');
INSERT INTO `st_dict` VALUES (8, '1', '启用', 'Enable', 1, 'enable_or_disable', '1', 'admin', 'admin', '2019-07-25 09:54:56', '2019-07-26 10:31:54');
INSERT INTO `st_dict` VALUES (9, '2', '禁用', 'Disabled', 2, 'enable_or_disable', '1', 'admin', 'admin', '2019-07-25 09:54:56', '2019-07-26 10:31:57');

-- ----------------------------
-- Table structure for st_inform
-- ----------------------------
DROP TABLE IF EXISTS `st_inform`;
CREATE TABLE `st_inform`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
  `top` tinyint(1) NOT NULL COMMENT '是否置顶',
  `status` decimal(2, 0) NOT NULL COMMENT '0 撤销  1 正常或过期',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  `attchment_list` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `canceler` bigint(20) NULL DEFAULT NULL COMMENT '撤销人ID',
  `cancel_date` datetime(0) NULL DEFAULT NULL COMMENT '撤销时间',
  `outdate_operator` bigint(20) NULL DEFAULT NULL COMMENT '过期操作人ID',
  `outdate_date` datetime(0) NULL DEFAULT NULL COMMENT '过期时间',
  `create_date` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `creator` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of st_inform
-- ----------------------------
INSERT INTO `st_inform` VALUES (8, '测试', 0, 1, 'hello world！', '5,6,13', NULL, NULL, NULL, NULL, '2020-08-12 06:50:18', 1);
INSERT INTO `st_inform` VALUES (21, '搬砖的一天', 1, 1, '又是搬砖的一天！', '5,6,14', NULL, NULL, NULL, NULL, '2020-08-14 02:33:58', 1);
INSERT INTO `st_inform` VALUES (24, '上传一张图片', 0, 1, '666', '13,14', NULL, NULL, NULL, NULL, '2020-08-14 07:20:40', 1);
INSERT INTO `st_inform` VALUES (25, '测试上传二张图片', 0, 2, '23333', '14', NULL, NULL, NULL, '2020-08-21 03:11:17', '2020-08-14 07:24:44', 1);
INSERT INTO `st_inform` VALUES (29, '今天周五', 1, 1, '今天周五', '', NULL, NULL, NULL, NULL, '2020-08-14 08:00:27', NULL);
INSERT INTO `st_inform` VALUES (31, '测试日志功能', 0, 1, '6666', '', NULL, NULL, NULL, NULL, '2020-08-18 07:13:48', NULL);
INSERT INTO `st_inform` VALUES (32, '2020年8月19日测试', 0, 1, '2020年8月19日测试', '', NULL, NULL, NULL, NULL, '2020-08-19 01:08:44', NULL);
INSERT INTO `st_inform` VALUES (33, '测试公告分页功能', 0, 2, '测试公告分页功能', '', NULL, NULL, NULL, '2020-08-19 01:40:13', '2020-08-19 01:15:12', NULL);
INSERT INTO `st_inform` VALUES (34, '再测试一次', 0, 0, '再测试一次', '', NULL, '2020-08-19 01:36:41', NULL, NULL, '2020-08-19 01:15:33', NULL);
INSERT INTO `st_inform` VALUES (35, '测试23333', 0, 2, '测试23333', '', NULL, NULL, NULL, '2020-08-19 01:37:07', '2020-08-19 01:15:48', NULL);

-- ----------------------------
-- Table structure for st_login_log
-- ----------------------------
DROP TABLE IF EXISTS `st_login_log`;
CREATE TABLE `st_login_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '访问时间',
  `content` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '内容',
  `yn_flag` char(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '有效标志',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
  `editor` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '修改人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 196 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '登录日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of st_login_log
-- ----------------------------
INSERT INTO `st_login_log` VALUES (1, 'admin', '2019-07-26 05:50:21', '登录成功', '1', 'admin', 'admin', '2019-07-26 05:50:21', '2019-07-26 13:50:55');
INSERT INTO `st_login_log` VALUES (2, 'admin', '2019-07-26 08:31:30', '登录成功', '1', 'admin', 'admin', '2019-07-26 08:31:30', '2019-07-26 16:31:30');
INSERT INTO `st_login_log` VALUES (3, 'admin', '2019-07-26 08:58:40', '登录成功', '1', 'admin', 'admin', '2019-07-26 08:58:40', '2019-07-26 16:58:39');
INSERT INTO `st_login_log` VALUES (4, 'admin', '2019-07-26 09:04:12', '登录成功', '1', 'admin', 'admin', '2019-07-26 09:04:12', '2019-07-26 17:04:12');
INSERT INTO `st_login_log` VALUES (5, 'admin', '2019-07-26 11:35:11', '登录成功', '1', 'admin', 'admin', '2019-07-26 11:35:11', '2019-07-26 19:35:11');
INSERT INTO `st_login_log` VALUES (6, 'admin', '2019-07-29 06:06:49', '登录成功', '1', 'admin', 'admin', '2019-07-29 06:06:49', '2019-07-29 14:06:48');
INSERT INTO `st_login_log` VALUES (7, 'admin', '2019-07-29 06:20:18', '登录成功', '1', 'admin', 'admin', '2019-07-29 06:20:18', '2019-07-29 14:20:18');
INSERT INTO `st_login_log` VALUES (8, 'admin', '2019-07-29 09:12:12', '登录成功', '1', 'admin', 'admin', '2019-07-29 09:12:12', '2019-07-29 17:12:11');
INSERT INTO `st_login_log` VALUES (9, 'admin', '2019-07-30 11:36:51', '登录成功', '0', 'admin', 'admin', '2019-07-30 11:36:51', '2020-08-18 07:11:20');
INSERT INTO `st_login_log` VALUES (10, 'admin', '2019-07-30 11:53:40', '登录成功', '0', 'admin', 'admin', '2019-07-30 11:53:40', '2020-08-18 07:11:17');
INSERT INTO `st_login_log` VALUES (11, 'admin', '2019-08-01 06:03:23', '登录成功', '1', 'admin', 'admin', '2019-08-01 06:03:23', '2019-08-01 14:03:22');
INSERT INTO `st_login_log` VALUES (12, 'admin', '2019-08-01 06:34:26', '登录成功', '1', 'admin', 'admin', '2019-08-01 06:34:26', '2019-08-01 14:34:25');
INSERT INTO `st_login_log` VALUES (13, 'admin', '2019-08-03 12:43:20', '登录成功', '1', 'admin', 'admin', '2019-08-03 12:43:20', '2019-08-03 20:43:20');
INSERT INTO `st_login_log` VALUES (14, 'admin', '2019-08-05 02:41:57', '登录成功', '1', 'admin', 'admin', '2019-08-05 02:41:57', '2019-08-05 10:41:57');
INSERT INTO `st_login_log` VALUES (15, 'admin', '2019-08-05 11:45:57', '登录成功', '1', 'admin', 'admin', '2019-08-05 11:45:57', '2019-08-05 19:45:56');
INSERT INTO `st_login_log` VALUES (16, 'admin', '2019-08-07 02:31:36', '登录成功', '1', 'admin', 'admin', '2019-08-07 02:31:36', '2019-08-07 10:31:35');
INSERT INTO `st_login_log` VALUES (17, 'admin', '2019-08-12 06:03:37', '登录成功', '1', 'admin', 'admin', '2019-08-12 06:03:37', '2019-08-12 14:03:36');
INSERT INTO `st_login_log` VALUES (18, 'admin', '2019-08-12 06:24:23', '登录成功', '1', 'admin', 'admin', '2019-08-12 06:24:23', '2019-08-12 14:24:22');
INSERT INTO `st_login_log` VALUES (19, 'admin', '2019-08-12 06:45:17', '登录成功', '1', 'admin', 'admin', '2019-08-12 06:45:17', '2019-08-12 14:45:16');
INSERT INTO `st_login_log` VALUES (20, 'admin', '2019-08-12 06:45:58', '登录成功', '1', 'admin', 'admin', '2019-08-12 06:45:58', '2019-08-12 14:45:58');
INSERT INTO `st_login_log` VALUES (21, 'admin', '2019-08-12 06:46:36', '登录成功', '1', 'admin', 'admin', '2019-08-12 06:46:36', '2019-08-12 14:46:36');
INSERT INTO `st_login_log` VALUES (22, 'admin', '2019-08-12 07:38:59', '登录成功', '1', 'admin', 'admin', '2019-08-12 07:38:59', '2019-08-12 15:38:58');
INSERT INTO `st_login_log` VALUES (23, 'admin', '2019-08-12 08:22:30', '登录成功', '1', 'admin', 'admin', '2019-08-12 08:22:30', '2019-08-12 16:22:29');
INSERT INTO `st_login_log` VALUES (24, 'admin', '2019-08-12 09:40:29', '登录成功', '1', 'admin', 'admin', '2019-08-12 09:40:29', '2019-08-12 17:40:29');
INSERT INTO `st_login_log` VALUES (25, 'admin', '2019-08-12 10:47:12', '登录成功', '1', 'admin', 'admin', '2019-08-12 10:47:12', '2019-08-12 18:47:12');
INSERT INTO `st_login_log` VALUES (26, 'admin', '2019-08-13 01:37:26', '登录成功', '1', 'admin', 'admin', '2019-08-13 01:37:26', '2019-08-13 09:37:25');
INSERT INTO `st_login_log` VALUES (27, 'admin', '2019-08-13 05:48:12', '登录成功', '1', 'admin', 'admin', '2019-08-13 05:48:12', '2019-08-13 13:48:11');
INSERT INTO `st_login_log` VALUES (28, 'admin', '2019-08-13 07:03:36', '登录成功', '1', 'admin', 'admin', '2019-08-13 07:03:36', '2019-08-13 15:03:35');
INSERT INTO `st_login_log` VALUES (29, 'admin', '2019-08-13 07:52:05', '登录成功', '1', 'admin', 'admin', '2019-08-13 07:52:05', '2019-08-13 15:52:05');
INSERT INTO `st_login_log` VALUES (30, 'admin', '2019-08-14 06:04:20', '登录成功', '1', 'admin', 'admin', '2019-08-14 06:04:20', '2019-08-14 14:04:20');
INSERT INTO `st_login_log` VALUES (31, 'admin', '2019-08-14 09:57:30', '登录成功', '1', 'admin', 'admin', '2019-08-14 09:57:30', '2019-08-14 17:57:30');
INSERT INTO `st_login_log` VALUES (32, 'admin', '2019-08-15 02:05:58', '登录成功', '1', 'admin', 'admin', '2019-08-15 02:05:58', '2019-08-15 10:05:57');
INSERT INTO `st_login_log` VALUES (33, 'admin', '2019-08-15 05:54:23', '登录成功', '1', 'admin', 'admin', '2019-08-15 05:54:23', '2019-08-15 13:54:23');
INSERT INTO `st_login_log` VALUES (34, 'admin', '2019-08-16 04:52:55', '登录成功', '1', 'admin', 'admin', '2019-08-16 04:52:55', '2019-08-16 12:52:55');
INSERT INTO `st_login_log` VALUES (35, 'admin', '2019-08-16 05:03:52', '登录成功', '1', 'admin', 'admin', '2019-08-16 05:03:52', '2019-08-16 13:03:51');
INSERT INTO `st_login_log` VALUES (36, 'admin', '2019-08-16 05:06:07', '登录成功', '1', 'admin', 'admin', '2019-08-16 05:06:07', '2019-08-16 13:06:06');
INSERT INTO `st_login_log` VALUES (37, 'admin', '2019-08-16 06:07:13', '登录成功', '1', 'admin', 'admin', '2019-08-16 06:07:13', '2019-08-16 14:07:12');
INSERT INTO `st_login_log` VALUES (38, 'admin', '2019-08-16 06:07:46', '登录成功', '1', 'admin', 'admin', '2019-08-16 06:07:46', '2019-08-16 14:07:45');
INSERT INTO `st_login_log` VALUES (39, 'admin', '2019-08-16 06:08:04', '登录成功', '1', 'admin', 'admin', '2019-08-16 06:08:04', '2019-08-16 14:08:03');
INSERT INTO `st_login_log` VALUES (40, 'admin', '2019-08-18 12:37:26', '登录成功', '1', 'admin', 'admin', '2019-08-18 12:37:26', '2019-08-18 20:37:26');
INSERT INTO `st_login_log` VALUES (41, 'admin', '2019-08-19 01:35:08', '登录成功', '1', 'admin', 'admin', '2019-08-19 01:35:08', '2019-08-19 09:35:08');
INSERT INTO `st_login_log` VALUES (42, 'admin', '2019-08-19 03:02:58', '登录成功', '1', 'admin', 'admin', '2019-08-19 03:02:58', '2019-08-19 11:02:58');
INSERT INTO `st_login_log` VALUES (43, 'admin', '2019-08-19 03:03:04', '登录成功', '1', 'admin', 'admin', '2019-08-19 03:03:04', '2019-08-19 11:03:04');
INSERT INTO `st_login_log` VALUES (44, 'admin', '2019-08-19 03:03:07', '登录成功', '1', 'admin', 'admin', '2019-08-19 03:03:07', '2019-08-19 11:03:07');
INSERT INTO `st_login_log` VALUES (45, 'admin', '2019-08-19 03:04:53', '登录成功', '1', 'admin', 'admin', '2019-08-19 03:04:53', '2019-08-19 11:04:52');
INSERT INTO `st_login_log` VALUES (46, 'admin', '2019-08-19 03:18:46', '登录成功', '1', 'admin', 'admin', '2019-08-19 03:18:46', '2019-08-19 11:18:45');
INSERT INTO `st_login_log` VALUES (47, 'admin', '2019-08-19 10:22:09', '登录成功', '1', 'admin', 'admin', '2019-08-19 10:22:09', '2019-08-19 18:22:08');
INSERT INTO `st_login_log` VALUES (48, 'admin', '2019-08-23 08:42:16', '登录成功', '1', 'admin', 'admin', '2019-08-23 08:42:16', '2019-08-23 16:42:15');
INSERT INTO `st_login_log` VALUES (49, 'admin', '2019-08-26 11:59:42', '登录成功', '1', 'admin', 'admin', '2019-08-26 11:59:42', '2019-08-26 19:59:41');
INSERT INTO `st_login_log` VALUES (50, 'admin', '2019-08-28 03:27:06', '登录成功', '1', 'admin', 'admin', '2019-08-28 03:27:06', '2019-08-28 11:27:06');
INSERT INTO `st_login_log` VALUES (51, 'admin', '2019-08-30 06:05:28', '登录成功', '1', 'admin', 'admin', '2019-08-30 06:05:28', '2019-08-30 14:05:28');
INSERT INTO `st_login_log` VALUES (52, 'admin', '2019-09-06 02:17:43', '登录成功', '1', 'admin', 'admin', '2019-09-06 02:17:43', '2019-09-06 10:17:43');
INSERT INTO `st_login_log` VALUES (53, 'admin', '2019-09-06 12:01:25', '登录成功', '1', 'admin', 'admin', '2019-09-06 12:01:25', '2019-09-06 20:01:24');
INSERT INTO `st_login_log` VALUES (54, 'admin', '2019-09-06 12:02:53', '登录成功', '1', 'admin', 'admin', '2019-09-06 12:02:53', '2019-09-06 20:02:53');
INSERT INTO `st_login_log` VALUES (55, 'admin', '2019-09-07 12:49:07', '登录成功', '1', 'admin', 'admin', '2019-09-07 12:49:07', '2019-09-07 20:49:06');
INSERT INTO `st_login_log` VALUES (56, 'admin', '2019-09-07 12:54:26', '登录成功', '1', 'admin', 'admin', '2019-09-07 12:54:26', '2019-09-07 20:54:26');
INSERT INTO `st_login_log` VALUES (57, 'admin', '2019-09-07 12:55:38', '登录成功', '1', 'admin', 'admin', '2019-09-07 12:55:38', '2019-09-07 20:55:38');
INSERT INTO `st_login_log` VALUES (58, 'admin', '2019-09-07 12:56:42', '登录成功', '1', 'admin', 'admin', '2019-09-07 12:56:42', '2019-09-07 20:56:41');
INSERT INTO `st_login_log` VALUES (59, 'admin', '2019-09-07 12:59:28', '登录成功', '1', 'admin', 'admin', '2019-09-07 12:59:28', '2019-09-07 20:59:27');
INSERT INTO `st_login_log` VALUES (60, 'admin', '2019-09-07 13:05:07', '登录成功', '1', 'admin', 'admin', '2019-09-07 13:05:07', '2019-09-07 21:05:07');
INSERT INTO `st_login_log` VALUES (61, 'admin', '2019-09-07 13:06:19', '登录成功', '1', 'admin', 'admin', '2019-09-07 13:06:19', '2019-09-07 21:06:19');
INSERT INTO `st_login_log` VALUES (62, 'admin', '2019-09-07 13:08:12', '登录成功', '1', 'admin', 'admin', '2019-09-07 13:08:12', '2019-09-07 21:08:11');
INSERT INTO `st_login_log` VALUES (63, 'admin', '2019-09-07 13:10:11', '登录成功', '1', 'admin', 'admin', '2019-09-07 13:10:11', '2019-09-07 21:10:10');
INSERT INTO `st_login_log` VALUES (64, 'admin', '2019-09-07 13:12:38', '登录成功', '1', 'admin', 'admin', '2019-09-07 13:12:38', '2019-09-07 21:12:38');
INSERT INTO `st_login_log` VALUES (65, 'admin', '2019-09-07 13:26:07', '登录成功', '1', 'admin', 'admin', '2019-09-07 13:26:07', '2019-09-07 21:26:07');
INSERT INTO `st_login_log` VALUES (66, 'admin', '2019-09-09 02:32:46', '登录成功', '1', 'admin', 'admin', '2019-09-09 02:32:46', '2019-09-09 10:32:46');
INSERT INTO `st_login_log` VALUES (67, 'admin', '2019-09-09 06:42:40', '登录成功', '1', 'admin', 'admin', '2019-09-09 06:42:40', '2019-09-09 14:42:39');
INSERT INTO `st_login_log` VALUES (68, 'admin', '2019-09-10 03:27:06', '登录成功', '1', 'admin', 'admin', '2019-09-10 03:27:06', '2019-09-10 11:27:05');
INSERT INTO `st_login_log` VALUES (69, 'admin', '2019-09-10 12:09:47', '登录成功', '1', 'admin', 'admin', '2019-09-10 12:09:47', '2019-09-10 20:09:47');
INSERT INTO `st_login_log` VALUES (70, 'admin', '2019-09-11 02:10:38', '登录成功', '1', 'admin', 'admin', '2019-09-11 02:10:38', '2019-09-11 10:10:38');
INSERT INTO `st_login_log` VALUES (71, 'admin', '2019-09-12 01:39:31', '登录成功', '1', 'admin', 'admin', '2019-09-12 01:39:31', '2019-09-12 09:39:31');
INSERT INTO `st_login_log` VALUES (72, 'admin', '2019-09-12 02:02:23', '登录成功', '1', 'admin', 'admin', '2019-09-12 02:02:23', '2019-09-12 10:02:22');
INSERT INTO `st_login_log` VALUES (73, 'admin', '2019-09-12 07:58:35', '登录成功', '1', 'admin', 'admin', '2019-09-12 07:58:35', '2019-09-12 15:58:34');
INSERT INTO `st_login_log` VALUES (74, 'admin', '2019-09-12 11:25:01', '登录成功', '1', 'admin', 'admin', '2019-09-12 11:25:01', '2019-09-12 19:25:00');
INSERT INTO `st_login_log` VALUES (75, 'admin', '2019-09-16 06:15:41', '登录成功', '1', 'admin', 'admin', '2019-09-16 06:15:41', '2019-09-16 14:15:41');
INSERT INTO `st_login_log` VALUES (76, 'admin', '2019-09-16 07:03:26', '登录成功', '1', 'admin', 'admin', '2019-09-16 07:03:26', '2019-09-16 15:03:26');
INSERT INTO `st_login_log` VALUES (77, 'admin', '2019-09-17 02:09:06', '登录成功', '1', 'admin', 'admin', '2019-09-17 02:09:06', '2019-09-17 10:09:06');
INSERT INTO `st_login_log` VALUES (78, 'admin', '2019-09-17 10:09:14', '登录成功', '1', 'admin', 'admin', '2019-09-17 10:09:14', '2019-09-17 18:09:13');
INSERT INTO `st_login_log` VALUES (79, 'admin', '2019-09-18 06:26:24', '登录成功', '1', 'admin', 'admin', '2019-09-18 06:26:24', '2019-09-18 14:26:24');
INSERT INTO `st_login_log` VALUES (80, 'admin', '2019-09-19 05:57:54', '登录成功', '1', 'admin', 'admin', '2019-09-19 05:57:54', '2019-09-19 13:57:54');
INSERT INTO `st_login_log` VALUES (81, 'admin', '2019-09-20 02:49:42', '登录成功', '1', 'admin', 'admin', '2019-09-20 02:49:42', '2019-09-20 10:49:41');
INSERT INTO `st_login_log` VALUES (82, 'admin', '2019-09-24 02:06:37', '登录成功', '1', 'admin', 'admin', '2019-09-24 02:06:37', '2019-09-24 10:06:37');
INSERT INTO `st_login_log` VALUES (83, 'admin', '2019-09-25 06:08:16', '登录成功', '1', 'admin', 'admin', '2019-09-25 06:08:16', '2019-09-25 14:08:15');
INSERT INTO `st_login_log` VALUES (84, 'admin', '2019-09-25 06:22:56', '登录成功', '1', 'admin', 'admin', '2019-09-25 06:22:56', '2019-09-25 14:22:55');
INSERT INTO `st_login_log` VALUES (85, 'admin', '2019-09-25 07:18:07', '登录成功', '1', 'admin', 'admin', '2019-09-25 07:18:07', '2019-09-25 15:18:07');
INSERT INTO `st_login_log` VALUES (86, 'admin', '2019-09-25 07:18:25', '登录成功', '1', 'admin', 'admin', '2019-09-25 07:18:25', '2019-09-25 15:18:25');
INSERT INTO `st_login_log` VALUES (87, 'admin', '2020-08-07 05:52:45', '登录成功', '1', 'admin', 'admin', '2020-08-07 05:52:45', '2020-08-07 05:52:45');
INSERT INTO `st_login_log` VALUES (88, 'admin', '2020-08-07 06:27:11', '登录成功', '1', 'admin', 'admin', '2020-08-07 06:27:11', '2020-08-07 06:27:10');
INSERT INTO `st_login_log` VALUES (89, 'admin', '2020-08-10 02:20:43', '登录成功', '1', 'admin', 'admin', '2020-08-10 02:20:43', '2020-08-10 02:20:43');
INSERT INTO `st_login_log` VALUES (90, 'admin', '2020-08-10 03:14:55', '登录成功', '1', 'admin', 'admin', '2020-08-10 03:14:55', '2020-08-10 03:14:55');
INSERT INTO `st_login_log` VALUES (91, 'admin', '2020-08-10 03:29:41', '登录成功', '1', 'admin', 'admin', '2020-08-10 03:29:41', '2020-08-10 03:29:40');
INSERT INTO `st_login_log` VALUES (92, 'admin', '2020-08-10 04:32:38', '登录成功', '1', 'admin', 'admin', '2020-08-10 04:32:38', '2020-08-10 04:32:38');
INSERT INTO `st_login_log` VALUES (93, 'admin', '2020-08-10 04:40:49', '登录成功', '1', 'admin', 'admin', '2020-08-10 04:40:49', '2020-08-10 04:40:48');
INSERT INTO `st_login_log` VALUES (94, 'admin', '2020-08-10 04:52:29', '登录成功', '1', 'admin', 'admin', '2020-08-10 04:52:29', '2020-08-10 04:52:28');
INSERT INTO `st_login_log` VALUES (95, 'admin', '2020-08-10 05:43:50', '登录成功', '1', 'admin', 'admin', '2020-08-10 05:43:50', '2020-08-10 05:43:50');
INSERT INTO `st_login_log` VALUES (96, 'admin', '2020-08-10 06:03:20', '登录成功', '1', 'admin', 'admin', '2020-08-10 06:03:20', '2020-08-10 06:03:19');
INSERT INTO `st_login_log` VALUES (97, 'admin', '2020-08-11 08:59:52', '登录成功', '1', 'admin', 'admin', '2020-08-11 08:59:52', '2020-08-11 08:59:51');
INSERT INTO `st_login_log` VALUES (98, 'admin', '2020-08-12 01:18:05', '登录成功', '1', 'admin', 'admin', '2020-08-12 01:18:05', '2020-08-12 01:18:04');
INSERT INTO `st_login_log` VALUES (99, 'admin', '2020-08-12 01:18:39', '登录成功', '1', 'admin', 'admin', '2020-08-12 01:18:39', '2020-08-12 01:18:38');
INSERT INTO `st_login_log` VALUES (100, 'admin', '2020-08-12 01:51:39', '登录成功', '1', 'admin', 'admin', '2020-08-12 01:51:39', '2020-08-12 01:51:38');
INSERT INTO `st_login_log` VALUES (101, 'admin', '2020-08-12 03:44:33', '登录成功', '1', 'admin', 'admin', '2020-08-12 03:44:33', '2020-08-12 03:44:33');
INSERT INTO `st_login_log` VALUES (102, 'admin', '2020-08-12 03:52:36', '登录成功', '1', 'admin', 'admin', '2020-08-12 03:52:36', '2020-08-12 03:52:36');
INSERT INTO `st_login_log` VALUES (103, 'admin', '2020-08-12 04:15:56', '登录成功', '1', 'admin', 'admin', '2020-08-12 04:15:56', '2020-08-12 04:15:56');
INSERT INTO `st_login_log` VALUES (104, 'admin', '2020-08-12 06:02:12', '登录成功', '1', 'admin', 'admin', '2020-08-12 06:02:12', '2020-08-12 06:02:11');
INSERT INTO `st_login_log` VALUES (105, 'admin', '2020-08-12 06:49:06', '登录成功', '1', 'admin', 'admin', '2020-08-12 06:49:06', '2020-08-12 06:49:05');
INSERT INTO `st_login_log` VALUES (106, 'admin', '2020-08-12 06:52:00', '登录成功', '1', 'admin', 'admin', '2020-08-12 06:52:00', '2020-08-12 06:52:00');
INSERT INTO `st_login_log` VALUES (107, 'admin', '2020-08-12 06:55:36', '登录成功', '1', 'admin', 'admin', '2020-08-12 06:55:36', '2020-08-12 06:55:36');
INSERT INTO `st_login_log` VALUES (108, 'admin', '2020-08-12 06:56:54', '登录成功', '1', 'admin', 'admin', '2020-08-12 06:56:54', '2020-08-12 06:56:53');
INSERT INTO `st_login_log` VALUES (109, 'admin', '2020-08-12 07:04:18', '登录成功', '1', 'admin', 'admin', '2020-08-12 07:04:18', '2020-08-12 07:04:18');
INSERT INTO `st_login_log` VALUES (110, 'admin', '2020-08-12 08:52:28', '登录成功', '1', 'admin', 'admin', '2020-08-12 08:52:28', '2020-08-12 08:52:28');
INSERT INTO `st_login_log` VALUES (111, 'admin', '2020-08-12 09:07:27', '登录成功', '1', 'admin', 'admin', '2020-08-12 09:07:27', '2020-08-12 09:07:27');
INSERT INTO `st_login_log` VALUES (112, 'admin', '2020-08-12 09:09:03', '登录成功', '1', 'admin', 'admin', '2020-08-12 09:09:03', '2020-08-12 09:09:03');
INSERT INTO `st_login_log` VALUES (113, 'admin', '2020-08-12 09:20:09', '登录成功', '1', 'admin', 'admin', '2020-08-12 09:20:09', '2020-08-12 09:20:08');
INSERT INTO `st_login_log` VALUES (114, 'admin', '2020-08-13 01:09:14', '登录成功', '1', 'admin', 'admin', '2020-08-13 01:09:14', '2020-08-13 01:09:13');
INSERT INTO `st_login_log` VALUES (115, 'admin', '2020-08-13 01:18:15', '登录成功', '1', 'admin', 'admin', '2020-08-13 01:18:15', '2020-08-13 01:18:15');
INSERT INTO `st_login_log` VALUES (116, 'admin', '2020-08-13 01:45:28', '登录成功', '1', 'admin', 'admin', '2020-08-13 01:45:28', '2020-08-13 01:45:28');
INSERT INTO `st_login_log` VALUES (117, 'admin', '2020-08-13 01:50:59', '登录成功', '1', 'admin', 'admin', '2020-08-13 01:50:59', '2020-08-13 01:50:59');
INSERT INTO `st_login_log` VALUES (118, 'admin', '2020-08-13 02:18:11', '登录成功', '1', 'admin', 'admin', '2020-08-13 02:18:11', '2020-08-13 02:18:10');
INSERT INTO `st_login_log` VALUES (119, 'admin', '2020-08-13 02:40:58', '登录成功', '1', 'admin', 'admin', '2020-08-13 02:40:58', '2020-08-13 02:40:58');
INSERT INTO `st_login_log` VALUES (120, 'admin', '2020-08-13 02:47:38', '登录成功', '1', 'admin', 'admin', '2020-08-13 02:47:38', '2020-08-13 02:47:38');
INSERT INTO `st_login_log` VALUES (121, 'admin', '2020-08-13 03:05:12', '登录成功', '1', 'admin', 'admin', '2020-08-13 03:05:12', '2020-08-13 03:05:12');
INSERT INTO `st_login_log` VALUES (122, 'admin', '2020-08-13 03:25:56', '登录成功', '1', 'admin', 'admin', '2020-08-13 03:25:56', '2020-08-13 03:25:55');
INSERT INTO `st_login_log` VALUES (123, 'admin', '2020-08-13 03:35:58', '登录成功', '1', 'admin', 'admin', '2020-08-13 03:35:58', '2020-08-13 03:35:58');
INSERT INTO `st_login_log` VALUES (124, 'admin', '2020-08-13 04:20:54', '登录成功', '1', 'admin', 'admin', '2020-08-13 04:20:54', '2020-08-13 04:20:53');
INSERT INTO `st_login_log` VALUES (125, 'admin', '2020-08-13 04:28:37', '登录成功', '1', 'admin', 'admin', '2020-08-13 04:28:37', '2020-08-13 04:28:36');
INSERT INTO `st_login_log` VALUES (126, 'admin', '2020-08-13 04:29:50', '登录成功', '1', 'admin', 'admin', '2020-08-13 04:29:50', '2020-08-13 04:29:50');
INSERT INTO `st_login_log` VALUES (127, 'admin', '2020-08-13 06:21:49', '登录成功', '1', 'admin', 'admin', '2020-08-13 06:21:49', '2020-08-13 06:21:48');
INSERT INTO `st_login_log` VALUES (128, 'admin', '2020-08-13 06:26:39', '登录成功', '1', 'admin', 'admin', '2020-08-13 06:26:39', '2020-08-13 06:26:39');
INSERT INTO `st_login_log` VALUES (129, 'admin', '2020-08-13 06:29:50', '登录成功', '1', 'admin', 'admin', '2020-08-13 06:29:50', '2020-08-13 06:29:49');
INSERT INTO `st_login_log` VALUES (130, 'admin', '2020-08-13 06:30:17', '登录成功', '1', 'admin', 'admin', '2020-08-13 06:30:17', '2020-08-13 06:30:17');
INSERT INTO `st_login_log` VALUES (131, 'admin', '2020-08-13 06:38:34', '登录成功', '1', 'admin', 'admin', '2020-08-13 06:38:34', '2020-08-13 06:38:33');
INSERT INTO `st_login_log` VALUES (132, 'admin', '2020-08-13 06:40:09', '登录成功', '1', 'admin', 'admin', '2020-08-13 06:40:09', '2020-08-13 06:40:09');
INSERT INTO `st_login_log` VALUES (133, 'admin', '2020-08-13 06:44:01', '登录成功', '1', 'admin', 'admin', '2020-08-13 06:44:01', '2020-08-13 06:44:01');
INSERT INTO `st_login_log` VALUES (134, 'admin', '2020-08-13 07:20:39', '登录成功', '1', 'admin', 'admin', '2020-08-13 07:20:39', '2020-08-13 07:20:38');
INSERT INTO `st_login_log` VALUES (135, 'admin', '2020-08-13 07:50:20', '登录成功', '1', 'admin', 'admin', '2020-08-13 07:50:20', '2020-08-13 07:50:19');
INSERT INTO `st_login_log` VALUES (136, 'admin', '2020-08-13 09:29:45', '登录成功', '1', 'admin', 'admin', '2020-08-13 09:29:45', '2020-08-13 09:29:45');
INSERT INTO `st_login_log` VALUES (137, 'admin', '2020-08-13 09:33:16', '登录成功', '1', 'admin', 'admin', '2020-08-13 09:33:16', '2020-08-13 09:33:16');
INSERT INTO `st_login_log` VALUES (138, 'admin', '2020-08-14 00:54:15', '登录成功', '1', 'admin', 'admin', '2020-08-14 00:54:15', '2020-08-14 00:54:14');
INSERT INTO `st_login_log` VALUES (139, 'admin', '2020-08-14 03:22:25', '登录成功', '1', 'admin', 'admin', '2020-08-14 03:22:25', '2020-08-14 03:22:24');
INSERT INTO `st_login_log` VALUES (140, 'admin', '2020-08-14 05:07:08', '登录成功', '1', 'admin', 'admin', '2020-08-14 05:07:08', '2020-08-14 05:07:07');
INSERT INTO `st_login_log` VALUES (141, 'admin', '2020-08-14 06:26:42', '登录成功', '1', 'admin', 'admin', '2020-08-14 06:26:42', '2020-08-14 06:26:42');
INSERT INTO `st_login_log` VALUES (142, 'admin', '2020-08-14 06:49:25', '登录成功', '1', 'admin', 'admin', '2020-08-14 06:49:25', '2020-08-14 06:49:24');
INSERT INTO `st_login_log` VALUES (143, 'admin', '2020-08-14 07:33:51', '登录成功', '1', 'admin', 'admin', '2020-08-14 07:33:51', '2020-08-14 07:33:50');
INSERT INTO `st_login_log` VALUES (144, 'admin', '2020-08-14 07:43:03', '登录成功', '1', 'admin', 'admin', '2020-08-14 07:43:03', '2020-08-14 07:43:02');
INSERT INTO `st_login_log` VALUES (145, 'admin', '2020-08-14 08:31:01', '登录成功', '1', 'admin', 'admin', '2020-08-14 08:31:01', '2020-08-14 08:31:00');
INSERT INTO `st_login_log` VALUES (146, 'admin', '2020-08-14 08:44:42', '登录成功', '1', 'admin', 'admin', '2020-08-14 08:44:42', '2020-08-14 08:44:42');
INSERT INTO `st_login_log` VALUES (147, 'admin', '2020-08-17 01:28:27', '登录成功', '1', 'admin', 'admin', '2020-08-17 01:28:27', '2020-08-17 01:28:27');
INSERT INTO `st_login_log` VALUES (148, 'admin', '2020-08-17 03:22:38', '登录成功', '1', 'admin', 'admin', '2020-08-17 03:22:38', '2020-08-17 03:22:38');
INSERT INTO `st_login_log` VALUES (149, 'admin', '2020-08-17 03:42:12', '登录成功', '1', 'admin', 'admin', '2020-08-17 03:42:12', '2020-08-17 03:42:11');
INSERT INTO `st_login_log` VALUES (150, 'admin', '2020-08-17 04:29:32', '登录成功', '1', 'admin', 'admin', '2020-08-17 04:29:32', '2020-08-17 04:29:31');
INSERT INTO `st_login_log` VALUES (151, 'admin', '2020-08-17 06:14:18', '登录成功', '1', 'admin', 'admin', '2020-08-17 06:14:18', '2020-08-17 06:14:18');
INSERT INTO `st_login_log` VALUES (152, 'admin', '2020-08-17 08:00:09', '登录成功', '1', 'admin', 'admin', '2020-08-17 08:00:09', '2020-08-17 08:00:09');
INSERT INTO `st_login_log` VALUES (153, 'admin', '2020-08-17 08:14:20', '登录成功', '1', 'admin', 'admin', '2020-08-17 08:14:20', '2020-08-17 08:14:20');
INSERT INTO `st_login_log` VALUES (154, 'admin', '2020-08-18 02:01:40', '登录成功', '1', 'admin', 'admin', '2020-08-18 02:01:40', '2020-08-18 02:01:39');
INSERT INTO `st_login_log` VALUES (155, 'admin', '2020-08-18 02:58:34', '登录成功', '1', 'admin', 'admin', '2020-08-18 02:58:34', '2020-08-18 02:58:34');
INSERT INTO `st_login_log` VALUES (156, 'admin', '2020-08-18 03:18:24', '登录成功', '1', 'admin', 'admin', '2020-08-18 03:18:24', '2020-08-18 03:18:24');
INSERT INTO `st_login_log` VALUES (157, 'admin', '2020-08-18 06:30:04', '登录成功', '1', 'admin', 'admin', '2020-08-18 06:30:04', '2020-08-18 06:30:04');
INSERT INTO `st_login_log` VALUES (158, 'admin', '2020-08-18 07:20:23', '登录成功', '1', 'admin', 'admin', '2020-08-18 07:20:23', '2020-08-18 07:20:22');
INSERT INTO `st_login_log` VALUES (159, 'admin', '2020-08-18 08:03:22', '登录成功', '1', 'admin', 'admin', '2020-08-18 08:03:22', '2020-08-18 08:03:22');
INSERT INTO `st_login_log` VALUES (160, 'admin', '2020-08-18 08:04:06', '登录成功', '1', 'admin', 'admin', '2020-08-18 08:04:06', '2020-08-18 08:04:06');
INSERT INTO `st_login_log` VALUES (161, 'admin', '2020-08-18 09:31:30', '登录成功', '1', 'admin', 'admin', '2020-08-18 09:31:30', '2020-08-18 09:31:29');
INSERT INTO `st_login_log` VALUES (162, 'admin', '2020-08-18 09:49:11', '登录成功', '1', 'admin', 'admin', '2020-08-18 09:49:11', '2020-08-18 09:49:10');
INSERT INTO `st_login_log` VALUES (163, 'admin', '2020-08-19 00:49:20', '登录成功', '1', 'admin', 'admin', '2020-08-19 00:49:20', '2020-08-19 00:49:20');
INSERT INTO `st_login_log` VALUES (164, 'admin', '2020-08-19 03:02:58', '登录成功', '1', 'admin', 'admin', '2020-08-19 03:02:58', '2020-08-19 03:02:58');
INSERT INTO `st_login_log` VALUES (165, 'admin', '2020-08-19 03:03:27', '登录成功', '1', 'admin', 'admin', '2020-08-19 03:03:27', '2020-08-19 03:03:27');
INSERT INTO `st_login_log` VALUES (166, 'admin', '2020-08-19 03:13:10', '登录成功', '1', 'admin', 'admin', '2020-08-19 03:13:10', '2020-08-19 03:13:09');
INSERT INTO `st_login_log` VALUES (167, 'admin', '2020-08-19 03:19:15', '登录成功', '1', 'admin', 'admin', '2020-08-19 03:19:15', '2020-08-19 03:19:15');
INSERT INTO `st_login_log` VALUES (168, 'admin', '2020-08-19 03:25:45', '登录成功', '1', 'admin', 'admin', '2020-08-19 03:25:45', '2020-08-19 03:25:45');
INSERT INTO `st_login_log` VALUES (169, 'admin', '2020-08-19 03:35:56', '登录成功', '1', 'admin', 'admin', '2020-08-19 03:35:56', '2020-08-19 03:35:56');
INSERT INTO `st_login_log` VALUES (170, 'admin', '2020-08-19 04:06:13', '登录成功', '1', 'admin', 'admin', '2020-08-19 04:06:13', '2020-08-19 04:06:12');
INSERT INTO `st_login_log` VALUES (171, 'admin', '2020-08-19 06:10:51', '登录成功', '1', 'admin', 'admin', '2020-08-19 06:10:51', '2020-08-19 06:10:51');
INSERT INTO `st_login_log` VALUES (172, 'admin', '2020-08-19 07:12:51', '登录成功', '1', 'admin', 'admin', '2020-08-19 07:12:51', '2020-08-19 07:12:51');
INSERT INTO `st_login_log` VALUES (173, 'admin', '2020-08-19 08:16:11', '登录成功', '1', 'admin', 'admin', '2020-08-19 08:16:11', '2020-08-19 08:16:10');
INSERT INTO `st_login_log` VALUES (174, 'admin', '2020-08-19 08:41:27', '登录成功', '1', 'admin', 'admin', '2020-08-19 08:41:27', '2020-08-19 08:41:27');
INSERT INTO `st_login_log` VALUES (175, 'admin', '2020-08-19 08:43:46', '登录成功', '1', 'admin', 'admin', '2020-08-19 08:43:46', '2020-08-19 08:43:45');
INSERT INTO `st_login_log` VALUES (176, 'admin', '2020-08-19 08:53:37', '登录成功', '1', 'admin', 'admin', '2020-08-19 08:53:37', '2020-08-19 08:53:37');
INSERT INTO `st_login_log` VALUES (177, 'admin', '2020-08-21 02:55:30', '登录成功', '1', 'admin', 'admin', '2020-08-21 02:55:30', '2020-08-21 02:55:29');
INSERT INTO `st_login_log` VALUES (178, 'admin', '2020-08-21 02:57:00', '登录成功', '1', 'admin', 'admin', '2020-08-21 02:57:00', '2020-08-21 02:57:00');
INSERT INTO `st_login_log` VALUES (179, 'admin', '2020-08-21 02:57:22', '登录成功', '1', 'admin', 'admin', '2020-08-21 02:57:22', '2020-08-21 02:57:21');
INSERT INTO `st_login_log` VALUES (180, 'admin', '2020-08-21 03:12:01', '登录成功', '1', 'admin', 'admin', '2020-08-21 03:12:01', '2020-08-21 03:12:01');
INSERT INTO `st_login_log` VALUES (181, 'admin', '2020-08-21 05:48:13', '登录成功', '1', 'admin', 'admin', '2020-08-21 05:48:13', '2020-08-21 05:48:12');
INSERT INTO `st_login_log` VALUES (182, 'admin', '2020-08-21 05:57:26', '登录成功', '1', 'admin', 'admin', '2020-08-21 05:57:26', '2020-08-21 05:57:25');
INSERT INTO `st_login_log` VALUES (183, 'admin', '2020-08-21 05:58:48', '登录成功', '1', 'admin', 'admin', '2020-08-21 05:58:48', '2020-08-21 05:58:48');
INSERT INTO `st_login_log` VALUES (184, 'admin', '2020-08-21 07:44:22', '登录成功', '1', 'admin', 'admin', '2020-08-21 07:44:22', '2020-08-21 07:44:22');
INSERT INTO `st_login_log` VALUES (185, 'admin', '2020-08-21 08:00:38', '登录成功', '1', 'admin', 'admin', '2020-08-21 08:00:38', '2020-08-21 08:00:37');
INSERT INTO `st_login_log` VALUES (186, 'admin', '2020-08-21 08:17:22', '登录成功', '1', 'admin', 'admin', '2020-08-21 08:17:22', '2020-08-21 08:17:21');
INSERT INTO `st_login_log` VALUES (187, 'admin', '2020-08-21 08:41:46', '登录成功', '1', 'admin', 'admin', '2020-08-21 08:41:46', '2020-08-21 08:41:45');
INSERT INTO `st_login_log` VALUES (188, 'admin', '2020-08-21 08:51:25', '登录成功', '1', 'admin', 'admin', '2020-08-21 08:51:25', '2020-08-21 08:51:25');
INSERT INTO `st_login_log` VALUES (189, 'admin', '2020-08-21 08:52:14', '登录成功', '1', 'admin', 'admin', '2020-08-21 08:52:14', '2020-08-21 08:52:13');
INSERT INTO `st_login_log` VALUES (190, 'admin', '2020-08-21 08:58:00', '登录成功', '1', 'admin', 'admin', '2020-08-21 08:58:00', '2020-08-21 08:57:59');
INSERT INTO `st_login_log` VALUES (191, 'admin', '2020-08-21 09:06:38', '登录成功', '1', 'admin', 'admin', '2020-08-21 09:06:38', '2020-08-21 09:06:38');
INSERT INTO `st_login_log` VALUES (192, 'admin', '2020-08-21 09:07:35', '登录成功', '1', 'admin', 'admin', '2020-08-21 09:07:35', '2020-08-21 09:07:34');
INSERT INTO `st_login_log` VALUES (193, 'admin', '2020-08-21 09:10:21', '登录成功', '1', 'admin', 'admin', '2020-08-21 09:10:21', '2020-08-21 09:10:20');
INSERT INTO `st_login_log` VALUES (194, 'admin', '2020-08-21 09:14:01', '登录成功', '1', 'admin', 'admin', '2020-08-21 09:14:01', '2020-08-21 09:14:01');
INSERT INTO `st_login_log` VALUES (195, 'admin', '2020-08-21 09:16:49', '登录成功', '1', 'admin', 'admin', '2020-08-21 09:16:49', '2020-08-21 09:16:48');

-- ----------------------------
-- Table structure for st_resource
-- ----------------------------
DROP TABLE IF EXISTS `st_resource`;
CREATE TABLE `st_resource`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '菜单名称',
  `full_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '菜单编号路径',
  `icon_class` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '图标样式类',
  `show_order` int(11) NULL DEFAULT NULL COMMENT '排序',
  `url` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '链接',
  `component` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '页面路径',
  `authority_id` bigint(20) NULL DEFAULT NULL COMMENT '权限ID',
  `pid` bigint(20) NULL DEFAULT NULL COMMENT '父id',
  `resource_desc` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '菜单描述',
  `yn_flag` char(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '有效标志',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
  `editor` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '修改人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of st_resource
-- ----------------------------
INSERT INTO `st_resource` VALUES (1, '系统管理', '0', 'nested', 30, '/sysmgr', '/layout/Layout', 1, 0, NULL, '1', 'admin', 'admin', '2019-07-12 09:20:40', '2019-09-25 07:25:47');
INSERT INTO `st_resource` VALUES (2, '用户管理', '0-1', 'user', 1, 'user', '/sysmgr/user/index', 7, 1, NULL, '1', 'admin', 'admin', '2019-07-12 09:20:40', '2019-08-19 11:04:38');
INSERT INTO `st_resource` VALUES (3, '角色管理', '0-1', 'peoples', 3, 'role', '/sysmgr/role/index', 10, 1, NULL, '1', 'admin', 'admin', '2019-07-12 09:20:40', '2020-08-14 08:43:05');
INSERT INTO `st_resource` VALUES (4, '菜单管理', '0-1', 'list', 4, 'menu', '/sysmgr/menu/index', 13, 1, NULL, '1', 'admin', 'admin', '2019-07-12 09:20:40', '2019-08-19 11:04:42');
INSERT INTO `st_resource` VALUES (5, '权限管理', '0-1', 'password', 5, 'authority', '/sysmgr/authority/index', 16, 1, NULL, '1', 'admin', 'admin', '2019-07-12 09:20:40', '2019-08-19 11:04:43');
INSERT INTO `st_resource` VALUES (6, '基础信息', '0', 'nested', 20, '/baseinfo', '/layout/Layout', 1, 0, NULL, '1', 'admin', 'admin', '2019-07-12 09:20:40', '2019-09-25 07:25:42');
INSERT INTO `st_resource` VALUES (7, '字典管理', '0-6', 'component', 1, 'dict', '/baseinfo/dict', 1, 6, NULL, '1', 'admin', 'admin', '2019-07-12 09:54:30', '2019-07-16 09:17:55');
INSERT INTO `st_resource` VALUES (8, '附件管理', '0-1', 'zip', 7, 'att', '/sysmgr/att/index', 39, 1, NULL, '1', 'admin', 'admin', '2019-07-12 10:25:37', '2019-09-25 06:18:31');
INSERT INTO `st_resource` VALUES (9, '登陆日志', '0-1', 'people', 6, 'loginlog', '/sysmgr/loginlog/index', 31, 1, NULL, '0', 'admin', 'admin', '2019-07-12 10:35:56', '2020-08-21 08:51:59');
INSERT INTO `st_resource` VALUES (10, '系统备份', '0-1', 'clipboard', 9, 'backup', '/sysmgr/backup/index', 44, 1, NULL, '1', 'admin', 'admin', '2019-07-12 10:49:11', '2019-09-25 06:18:49');
INSERT INTO `st_resource` VALUES (11, '系统日志', '0-1', 'documentation', 8, 'syslog', '/sysmgr/syslog/index', 42, 1, NULL, '0', 'admin', 'admin', '2019-07-12 11:58:59', '2020-08-21 08:52:03');
INSERT INTO `st_resource` VALUES (12, '定时任务', '0-1', 'guide', 20, 'schedulejob', '/sysmgr/schedulejob/index', 47, 1, NULL, '0', 'admin', 'admin', '2019-08-19 03:02:50', '2020-08-21 08:41:03');
INSERT INTO `st_resource` VALUES (13, '个人空间', '0', 'nested', 10, '/tools', '/layout/Layout', 1, 0, '', '1', 'admin', 'admin', '2019-09-25 06:28:53', '2019-09-25 14:30:48');
INSERT INTO `st_resource` VALUES (14, '待办事项', '0-13', 'table', NULL, 'todolist', '/tools/todolist', 53, 13, NULL, '1', 'admin', 'admin', '2019-09-25 07:09:20', '2019-09-25 07:17:58');
INSERT INTO `st_resource` VALUES (15, '系统公告', '0-1', 'user', 30, 'InformManagement', '/sysmgr/inform/index', 57, 1, NULL, '1', 'admin', 'admin', '2020-08-11 08:57:20', '2020-08-11 08:57:20');
INSERT INTO `st_resource` VALUES (16, '部门管理', '0-1', 'peoples', 10, 'dept', '/sysmgr/dept/index', 69, 1, NULL, '1', 'admin', 'admin', '2020-08-14 08:44:03', '2020-08-14 08:44:17');
INSERT INTO `st_resource` VALUES (17, '服务监控', '0', 'nested', 40, '/monitor/server', '/layout/Layout', 1, 0, NULL, '1', 'admin', 'admin', '2020-08-21 07:52:18', '2020-08-21 08:11:20');
INSERT INTO `st_resource` VALUES (18, '系统监控', '0', 'nested', 40, '/monitor/server', '/layout/Layout', 1, 0, NULL, '0', 'admin', 'admin', '2020-08-21 07:52:19', '2020-08-21 07:52:32');
INSERT INTO `st_resource` VALUES (19, '系统监控', '0-17', 'documentation', 1, 'server', '/monitor/server/index', 74, 17, NULL, '1', 'admin', 'admin', '2020-08-21 07:59:49', '2020-08-21 08:17:06');
INSERT INTO `st_resource` VALUES (20, '定时任务', '0-17', 'guide', 2, 'schedulejob', '/monitor/schedulejob/index', 76, 17, NULL, '1', 'admin', 'admin', '2020-08-21 08:23:27', '2020-08-21 09:21:33');
INSERT INTO `st_resource` VALUES (21, '日志管理', '0-1', 'documentation', 31, 'log', '/layout/Layout', 1, 1, NULL, '1', 'admin', 'admin', '2020-08-21 08:47:00', '2020-08-21 09:24:34');
INSERT INTO `st_resource` VALUES (22, '登录日志', '0-1-21', 'people', 1, 'loginlog', '/sysmgr/log/loginlog/index', 31, 21, NULL, '1', 'admin', 'admin', '2020-08-21 08:48:01', '2020-08-21 09:17:19');
INSERT INTO `st_resource` VALUES (23, '操作日志', '0-1-21', 'documentation', 2, 'syslog', '/sysmgr/log/syslog/index', 42, 21, NULL, '1', 'admin', 'admin', '2020-08-21 08:48:56', '2020-08-21 09:16:01');

-- ----------------------------
-- Table structure for st_role
-- ----------------------------
DROP TABLE IF EXISTS `st_role`;
CREATE TABLE `st_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '角色名称',
  `role_desc` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '角色描述',
  `yn_flag` char(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '有效标志',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
  `editor` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '修改人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of st_role
-- ----------------------------
INSERT INTO `st_role` VALUES (1, '管理员', '系统管理员', '1', NULL, 'admin', '2018-12-29 11:23:15', '2020-08-17 04:27:39');
INSERT INTO `st_role` VALUES (3, '供应商', '供应商大大', '1', NULL, NULL, NULL, '2020-08-17 04:27:53');
INSERT INTO `st_role` VALUES (4, '游客', '游客大大', '1', NULL, NULL, NULL, '2020-08-17 04:28:07');

-- ----------------------------
-- Table structure for st_role_authority
-- ----------------------------
DROP TABLE IF EXISTS `st_role_authority`;
CREATE TABLE `st_role_authority`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `authority_id` bigint(20) NOT NULL COMMENT '权限ID',
  `yn_flag` char(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '有效标志',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
  `editor` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '修改人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 882 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '角色权限关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of st_role_authority
-- ----------------------------
INSERT INTO `st_role_authority` VALUES (219, 1, 0, '0', 'admin', NULL, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES (220, 1, 1, '0', 'admin', NULL, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES (221, 1, 2, '0', 'admin', NULL, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES (222, 1, 3, '0', 'admin', NULL, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES (223, 1, 4, '0', 'admin', NULL, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES (224, 1, 5, '0', 'admin', NULL, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES (225, 1, 6, '0', 'admin', NULL, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES (226, 1, 7, '0', 'admin', NULL, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES (227, 1, 8, '0', 'admin', NULL, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES (228, 1, 9, '0', 'admin', NULL, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES (229, 1, 10, '0', 'admin', NULL, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES (230, 1, 11, '0', 'admin', NULL, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES (231, 1, 12, '0', 'admin', NULL, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES (232, 1, 13, '0', 'admin', NULL, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES (233, 1, 14, '0', 'admin', NULL, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES (234, 1, 15, '0', 'admin', NULL, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES (235, 1, 16, '0', 'admin', NULL, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES (236, 1, 17, '0', 'admin', NULL, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES (237, 1, 18, '0', 'admin', NULL, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES (238, 1, 30, '0', 'admin', NULL, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES (239, 1, 31, '0', 'admin', NULL, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES (240, 1, 32, '0', 'admin', NULL, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES (241, 1, 33, '0', 'admin', NULL, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES (242, 1, 34, '0', 'admin', NULL, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES (243, 1, 35, '0', 'admin', NULL, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES (244, 1, 36, '0', 'admin', NULL, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES (245, 1, 37, '0', 'admin', NULL, '2019-07-26 03:15:48', '2019-09-25 06:22:42');
INSERT INTO `st_role_authority` VALUES (246, 3, 0, '0', 'admin', NULL, '2019-07-26 05:54:06', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (247, 3, 1, '0', 'admin', NULL, '2019-07-26 05:54:06', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (248, 1, 0, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (249, 1, 1, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (250, 1, 2, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (251, 1, 3, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (252, 1, 4, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (253, 1, 5, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (254, 1, 6, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (255, 1, 7, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (256, 1, 8, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (257, 1, 9, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (258, 1, 10, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (259, 1, 11, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (260, 1, 12, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (261, 1, 13, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (262, 1, 14, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (263, 1, 15, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (264, 1, 16, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (265, 1, 17, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (266, 1, 18, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (267, 1, 30, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (268, 1, 31, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (269, 1, 32, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (270, 1, 33, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (271, 1, 34, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (272, 1, 35, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (273, 1, 36, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (274, 1, 37, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (275, 1, 38, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (276, 1, 39, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (277, 1, 40, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (278, 1, 41, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (279, 1, 42, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (280, 1, 43, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (281, 1, 44, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (282, 1, 45, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (283, 1, 46, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (284, 1, 47, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (285, 1, 48, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (286, 1, 49, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (287, 1, 50, '0', 'admin', NULL, '2019-09-25 06:22:42', '2019-09-25 07:18:14');
INSERT INTO `st_role_authority` VALUES (288, 1, 0, '0', 'admin', NULL, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES (289, 1, 1, '0', 'admin', NULL, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES (290, 1, 2, '0', 'admin', NULL, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES (291, 1, 3, '0', 'admin', NULL, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES (292, 1, 4, '0', 'admin', NULL, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES (293, 1, 5, '0', 'admin', NULL, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES (294, 1, 6, '0', 'admin', NULL, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES (295, 1, 7, '0', 'admin', NULL, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES (296, 1, 8, '0', 'admin', NULL, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES (297, 1, 9, '0', 'admin', NULL, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES (298, 1, 10, '0', 'admin', NULL, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES (299, 1, 11, '0', 'admin', NULL, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES (300, 1, 12, '0', 'admin', NULL, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES (301, 1, 13, '0', 'admin', NULL, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES (302, 1, 14, '0', 'admin', NULL, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES (303, 1, 15, '0', 'admin', NULL, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES (304, 1, 16, '0', 'admin', NULL, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES (305, 1, 17, '0', 'admin', NULL, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES (306, 1, 18, '0', 'admin', NULL, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES (307, 1, 30, '0', 'admin', NULL, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES (308, 1, 31, '0', 'admin', NULL, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES (309, 1, 32, '0', 'admin', NULL, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES (310, 1, 33, '0', 'admin', NULL, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES (311, 1, 34, '0', 'admin', NULL, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES (312, 1, 35, '0', 'admin', NULL, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES (313, 1, 36, '0', 'admin', NULL, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES (314, 1, 37, '0', 'admin', NULL, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES (315, 1, 38, '0', 'admin', NULL, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES (316, 1, 39, '0', 'admin', NULL, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES (317, 1, 40, '0', 'admin', NULL, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES (318, 1, 41, '0', 'admin', NULL, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES (319, 1, 42, '0', 'admin', NULL, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES (320, 1, 43, '0', 'admin', NULL, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES (321, 1, 44, '0', 'admin', NULL, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES (322, 1, 45, '0', 'admin', NULL, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES (323, 1, 46, '0', 'admin', NULL, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES (324, 1, 47, '0', 'admin', NULL, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES (325, 1, 48, '0', 'admin', NULL, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES (326, 1, 49, '0', 'admin', NULL, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES (327, 1, 50, '0', 'admin', NULL, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES (328, 1, 51, '0', 'admin', NULL, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES (329, 1, 52, '0', 'admin', NULL, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES (330, 1, 53, '0', 'admin', NULL, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES (331, 1, 54, '0', 'admin', NULL, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES (332, 1, 55, '0', 'admin', NULL, '2019-09-25 07:18:14', '2020-08-11 08:59:38');
INSERT INTO `st_role_authority` VALUES (333, 1, 0, '0', 'admin', NULL, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES (334, 1, 1, '0', 'admin', NULL, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES (335, 1, 2, '0', 'admin', NULL, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES (336, 1, 3, '0', 'admin', NULL, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES (337, 1, 4, '0', 'admin', NULL, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES (338, 1, 5, '0', 'admin', NULL, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES (339, 1, 6, '0', 'admin', NULL, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES (340, 1, 7, '0', 'admin', NULL, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES (341, 1, 8, '0', 'admin', NULL, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES (342, 1, 9, '0', 'admin', NULL, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES (343, 1, 10, '0', 'admin', NULL, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES (344, 1, 11, '0', 'admin', NULL, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES (345, 1, 12, '0', 'admin', NULL, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES (346, 1, 13, '0', 'admin', NULL, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES (347, 1, 14, '0', 'admin', NULL, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES (348, 1, 15, '0', 'admin', NULL, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES (349, 1, 16, '0', 'admin', NULL, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES (350, 1, 17, '0', 'admin', NULL, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES (351, 1, 18, '0', 'admin', NULL, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES (352, 1, 30, '0', 'admin', NULL, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES (353, 1, 31, '0', 'admin', NULL, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES (354, 1, 32, '0', 'admin', NULL, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES (355, 1, 33, '0', 'admin', NULL, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES (356, 1, 34, '0', 'admin', NULL, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES (357, 1, 35, '0', 'admin', NULL, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES (358, 1, 36, '0', 'admin', NULL, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES (359, 1, 37, '0', 'admin', NULL, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES (360, 1, 38, '0', 'admin', NULL, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES (361, 1, 39, '0', 'admin', NULL, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES (362, 1, 40, '0', 'admin', NULL, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES (363, 1, 41, '0', 'admin', NULL, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES (364, 1, 42, '0', 'admin', NULL, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES (365, 1, 43, '0', 'admin', NULL, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES (366, 1, 44, '0', 'admin', NULL, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES (367, 1, 45, '0', 'admin', NULL, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES (368, 1, 46, '0', 'admin', NULL, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES (369, 1, 47, '0', 'admin', NULL, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES (370, 1, 48, '0', 'admin', NULL, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES (371, 1, 49, '0', 'admin', NULL, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES (372, 1, 50, '0', 'admin', NULL, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES (373, 1, 51, '0', 'admin', NULL, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES (374, 1, 52, '0', 'admin', NULL, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES (375, 1, 53, '0', 'admin', NULL, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES (376, 1, 54, '0', 'admin', NULL, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES (377, 1, 55, '0', 'admin', NULL, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES (378, 1, 56, '0', 'admin', NULL, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES (379, 1, 57, '0', 'admin', NULL, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES (380, 1, 58, '0', 'admin', NULL, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES (381, 1, 59, '0', 'admin', NULL, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES (382, 1, 60, '0', 'admin', NULL, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES (383, 1, 61, '0', 'admin', NULL, '2020-08-11 08:59:38', '2020-08-12 06:48:10');
INSERT INTO `st_role_authority` VALUES (384, 1, 0, '0', 'admin', NULL, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES (385, 1, 1, '0', 'admin', NULL, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES (386, 1, 2, '0', 'admin', NULL, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES (387, 1, 3, '0', 'admin', NULL, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES (388, 1, 4, '0', 'admin', NULL, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES (389, 1, 5, '0', 'admin', NULL, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES (390, 1, 6, '0', 'admin', NULL, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES (391, 1, 7, '0', 'admin', NULL, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES (392, 1, 8, '0', 'admin', NULL, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES (393, 1, 9, '0', 'admin', NULL, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES (394, 1, 10, '0', 'admin', NULL, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES (395, 1, 11, '0', 'admin', NULL, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES (396, 1, 12, '0', 'admin', NULL, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES (397, 1, 13, '0', 'admin', NULL, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES (398, 1, 14, '0', 'admin', NULL, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES (399, 1, 15, '0', 'admin', NULL, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES (400, 1, 16, '0', 'admin', NULL, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES (401, 1, 17, '0', 'admin', NULL, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES (402, 1, 18, '0', 'admin', NULL, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES (403, 1, 30, '0', 'admin', NULL, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES (404, 1, 31, '0', 'admin', NULL, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES (405, 1, 32, '0', 'admin', NULL, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES (406, 1, 33, '0', 'admin', NULL, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES (407, 1, 34, '0', 'admin', NULL, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES (408, 1, 35, '0', 'admin', NULL, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES (409, 1, 36, '0', 'admin', NULL, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES (410, 1, 37, '0', 'admin', NULL, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES (411, 1, 38, '0', 'admin', NULL, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES (412, 1, 39, '0', 'admin', NULL, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES (413, 1, 40, '0', 'admin', NULL, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES (414, 1, 41, '0', 'admin', NULL, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES (415, 1, 42, '0', 'admin', NULL, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES (416, 1, 43, '0', 'admin', NULL, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES (417, 1, 44, '0', 'admin', NULL, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES (418, 1, 45, '0', 'admin', NULL, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES (419, 1, 46, '0', 'admin', NULL, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES (420, 1, 47, '0', 'admin', NULL, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES (421, 1, 48, '0', 'admin', NULL, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES (422, 1, 49, '0', 'admin', NULL, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES (423, 1, 50, '0', 'admin', NULL, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES (424, 1, 51, '0', 'admin', NULL, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES (425, 1, 52, '0', 'admin', NULL, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES (426, 1, 53, '0', 'admin', NULL, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES (427, 1, 54, '0', 'admin', NULL, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES (428, 1, 55, '0', 'admin', NULL, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES (429, 1, 56, '0', 'admin', NULL, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES (430, 1, 57, '0', 'admin', NULL, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES (431, 1, 58, '0', 'admin', NULL, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES (432, 1, 59, '0', 'admin', NULL, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES (433, 1, 60, '0', 'admin', NULL, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES (434, 1, 61, '0', 'admin', NULL, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES (435, 1, 62, '0', 'admin', NULL, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES (436, 1, 63, '0', 'admin', NULL, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES (437, 1, 64, '0', 'admin', NULL, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES (438, 1, 65, '0', 'admin', NULL, '2020-08-12 06:48:10', '2020-08-13 01:18:09');
INSERT INTO `st_role_authority` VALUES (439, 1, 0, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (440, 1, 1, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (441, 1, 2, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (442, 1, 3, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (443, 1, 4, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (444, 1, 5, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (445, 1, 6, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (446, 1, 7, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (447, 1, 8, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (448, 1, 9, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (449, 1, 10, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (450, 1, 11, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (451, 1, 12, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (452, 1, 13, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (453, 1, 14, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (454, 1, 15, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (455, 1, 16, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (456, 1, 17, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (457, 1, 18, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (458, 1, 30, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (459, 1, 31, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (460, 1, 32, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (461, 1, 33, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (462, 1, 34, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (463, 1, 35, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (464, 1, 36, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (465, 1, 37, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (466, 1, 38, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (467, 1, 39, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (468, 1, 40, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (469, 1, 41, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (470, 1, 42, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (471, 1, 43, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (472, 1, 44, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (473, 1, 45, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (474, 1, 46, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (475, 1, 47, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (476, 1, 48, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (477, 1, 49, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (478, 1, 50, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (479, 1, 51, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (480, 1, 52, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (481, 1, 53, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (482, 1, 54, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (483, 1, 55, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (484, 1, 56, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (485, 1, 57, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (486, 1, 58, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (487, 1, 59, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (488, 1, 60, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (489, 1, 61, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (490, 1, 62, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (491, 1, 63, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (492, 1, 64, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (493, 1, 65, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (494, 1, 66, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (495, 1, 67, '0', 'admin', NULL, '2020-08-13 01:18:09', '2020-08-14 08:44:33');
INSERT INTO `st_role_authority` VALUES (496, 1, 0, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (497, 1, 1, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (498, 1, 2, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (499, 1, 3, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (500, 1, 4, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (501, 1, 5, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (502, 1, 6, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (503, 1, 7, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (504, 1, 8, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (505, 1, 9, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (506, 1, 10, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (507, 1, 11, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (508, 1, 12, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (509, 1, 13, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (510, 1, 14, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (511, 1, 15, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (512, 1, 16, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (513, 1, 17, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (514, 1, 18, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (515, 1, 30, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (516, 1, 31, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (517, 1, 32, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (518, 1, 33, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (519, 1, 34, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (520, 1, 35, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (521, 1, 36, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (522, 1, 37, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (523, 1, 38, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (524, 1, 39, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (525, 1, 40, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (526, 1, 41, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (527, 1, 42, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (528, 1, 43, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (529, 1, 44, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (530, 1, 45, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (531, 1, 46, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (532, 1, 47, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (533, 1, 48, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (534, 1, 49, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (535, 1, 50, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (536, 1, 51, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (537, 1, 52, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (538, 1, 53, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (539, 1, 54, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (540, 1, 55, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (541, 1, 56, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (542, 1, 57, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (543, 1, 58, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (544, 1, 59, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (545, 1, 60, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (546, 1, 61, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (547, 1, 62, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (548, 1, 63, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (549, 1, 64, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (550, 1, 65, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (551, 1, 66, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (552, 1, 67, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (553, 1, 68, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (554, 1, 69, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (555, 1, 70, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (556, 1, 71, '0', 'admin', NULL, '2020-08-14 08:44:33', '2020-08-21 08:00:31');
INSERT INTO `st_role_authority` VALUES (557, 3, 0, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (558, 3, 1, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (559, 3, 2, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (560, 3, 3, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (561, 3, 4, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (562, 3, 5, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (563, 3, 6, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (564, 3, 7, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (565, 3, 8, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (566, 3, 9, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (567, 3, 10, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (568, 3, 11, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (569, 3, 12, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (570, 3, 13, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (571, 3, 14, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (572, 3, 15, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (573, 3, 16, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (574, 3, 17, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (575, 3, 18, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (576, 3, 30, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (577, 3, 31, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (578, 3, 32, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (579, 3, 33, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (580, 3, 34, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (581, 3, 35, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (582, 3, 36, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (583, 3, 37, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (584, 3, 38, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (585, 3, 39, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (586, 3, 40, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (587, 3, 41, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (588, 3, 42, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (589, 3, 43, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (590, 3, 44, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (591, 3, 45, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (592, 3, 46, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (593, 3, 47, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (594, 3, 48, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (595, 3, 49, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (596, 3, 50, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (597, 3, 51, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (598, 3, 52, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (599, 3, 53, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (600, 3, 54, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (601, 3, 55, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (602, 3, 56, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (603, 3, 57, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (604, 3, 58, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (605, 3, 59, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (606, 3, 60, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (607, 3, 61, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (608, 3, 62, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (609, 3, 63, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (610, 3, 64, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (611, 3, 65, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (612, 3, 66, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (613, 3, 67, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (614, 3, 68, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (615, 3, 69, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (616, 3, 70, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (617, 3, 71, '1', 'admin', 'admin', '2020-08-18 08:05:16', '2020-08-18 08:05:16');
INSERT INTO `st_role_authority` VALUES (618, 1, 0, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (619, 1, 1, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (620, 1, 2, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (621, 1, 3, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (622, 1, 4, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (623, 1, 5, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (624, 1, 6, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (625, 1, 7, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (626, 1, 8, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (627, 1, 9, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (628, 1, 10, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (629, 1, 11, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (630, 1, 12, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (631, 1, 13, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (632, 1, 14, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (633, 1, 15, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (634, 1, 16, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (635, 1, 17, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (636, 1, 18, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (637, 1, 30, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (638, 1, 31, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (639, 1, 32, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (640, 1, 33, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (641, 1, 34, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (642, 1, 35, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (643, 1, 36, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (644, 1, 37, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (645, 1, 38, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (646, 1, 39, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (647, 1, 40, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (648, 1, 41, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (649, 1, 42, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (650, 1, 43, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (651, 1, 44, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (652, 1, 45, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (653, 1, 46, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (654, 1, 47, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (655, 1, 48, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (656, 1, 49, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (657, 1, 50, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (658, 1, 51, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (659, 1, 52, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (660, 1, 53, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (661, 1, 54, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (662, 1, 55, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (663, 1, 56, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (664, 1, 57, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (665, 1, 58, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (666, 1, 59, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (667, 1, 60, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (668, 1, 61, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (669, 1, 62, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (670, 1, 63, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (671, 1, 64, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (672, 1, 65, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (673, 1, 66, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (674, 1, 67, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (675, 1, 68, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (676, 1, 69, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (677, 1, 70, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (678, 1, 71, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (679, 1, 72, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (680, 1, 73, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (681, 1, 74, '0', 'admin', NULL, '2020-08-21 08:00:31', '2020-08-21 08:30:47');
INSERT INTO `st_role_authority` VALUES (682, 1, 0, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (683, 1, 1, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (684, 1, 2, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (685, 1, 3, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (686, 1, 4, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (687, 1, 5, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (688, 1, 6, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (689, 1, 7, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (690, 1, 8, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (691, 1, 9, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (692, 1, 10, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (693, 1, 11, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (694, 1, 12, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (695, 1, 13, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (696, 1, 14, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (697, 1, 15, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (698, 1, 16, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (699, 1, 17, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (700, 1, 18, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (701, 1, 30, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (702, 1, 31, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (703, 1, 32, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (704, 1, 33, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (705, 1, 34, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (706, 1, 35, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (707, 1, 36, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (708, 1, 37, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (709, 1, 38, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (710, 1, 39, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (711, 1, 40, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (712, 1, 41, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (713, 1, 42, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (714, 1, 43, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (715, 1, 44, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (716, 1, 45, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (717, 1, 46, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (718, 1, 47, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (719, 1, 48, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (720, 1, 49, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (721, 1, 50, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (722, 1, 51, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (723, 1, 52, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (724, 1, 53, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (725, 1, 54, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (726, 1, 55, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (727, 1, 56, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (728, 1, 57, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (729, 1, 58, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (730, 1, 59, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (731, 1, 60, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (732, 1, 61, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (733, 1, 62, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (734, 1, 63, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (735, 1, 64, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (736, 1, 65, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (737, 1, 66, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (738, 1, 67, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (739, 1, 68, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (740, 1, 69, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (741, 1, 70, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (742, 1, 71, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (743, 1, 72, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (744, 1, 73, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (745, 1, 74, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (746, 1, 75, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (747, 1, 76, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (748, 1, 77, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (749, 1, 78, '0', 'admin', NULL, '2020-08-21 08:30:47', '2020-08-21 08:30:56');
INSERT INTO `st_role_authority` VALUES (750, 1, 0, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (751, 1, 1, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (752, 1, 2, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (753, 1, 3, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (754, 1, 4, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (755, 1, 5, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (756, 1, 6, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (757, 1, 7, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (758, 1, 8, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (759, 1, 9, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (760, 1, 10, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (761, 1, 11, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (762, 1, 12, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (763, 1, 13, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (764, 1, 14, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (765, 1, 15, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (766, 1, 16, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (767, 1, 17, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (768, 1, 18, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (769, 1, 30, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (770, 1, 31, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (771, 1, 32, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (772, 1, 33, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (773, 1, 34, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (774, 1, 35, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (775, 1, 36, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (776, 1, 37, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (777, 1, 38, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (778, 1, 39, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (779, 1, 40, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (780, 1, 41, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (781, 1, 42, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (782, 1, 43, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (783, 1, 44, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (784, 1, 45, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (785, 1, 46, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (786, 1, 47, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (787, 1, 48, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (788, 1, 49, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (789, 1, 50, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (790, 1, 51, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (791, 1, 52, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (792, 1, 53, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (793, 1, 54, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (794, 1, 55, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (795, 1, 56, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (796, 1, 57, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (797, 1, 58, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (798, 1, 59, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (799, 1, 60, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (800, 1, 61, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (801, 1, 62, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (802, 1, 63, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (803, 1, 64, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (804, 1, 65, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (805, 1, 66, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (806, 1, 67, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (807, 1, 68, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (808, 1, 69, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (809, 1, 70, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (810, 1, 71, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (811, 1, 72, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (812, 1, 73, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (813, 1, 74, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (814, 1, 75, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (815, 1, 76, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (816, 1, 77, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (817, 1, 78, '0', 'admin', NULL, '2020-08-21 08:30:56', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (818, 1, 0, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (819, 1, 1, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (820, 1, 2, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (821, 1, 3, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (822, 1, 4, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (823, 1, 5, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (824, 1, 6, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (825, 1, 7, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (826, 1, 8, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (827, 1, 9, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (828, 1, 10, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (829, 1, 11, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (830, 1, 12, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (831, 1, 13, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (832, 1, 14, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (833, 1, 15, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (834, 1, 16, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (835, 1, 17, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (836, 1, 18, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (837, 1, 30, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (838, 1, 31, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (839, 1, 32, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (840, 1, 33, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (841, 1, 34, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (842, 1, 35, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (843, 1, 36, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (844, 1, 37, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (845, 1, 38, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (846, 1, 39, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (847, 1, 40, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (848, 1, 41, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (849, 1, 42, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (850, 1, 43, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (851, 1, 44, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (852, 1, 45, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (853, 1, 50, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (854, 1, 51, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (855, 1, 52, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (856, 1, 53, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (857, 1, 54, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (858, 1, 55, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (859, 1, 56, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (860, 1, 57, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (861, 1, 58, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (862, 1, 59, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (863, 1, 60, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (864, 1, 61, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (865, 1, 62, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (866, 1, 63, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (867, 1, 64, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (868, 1, 65, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (869, 1, 66, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (870, 1, 67, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (871, 1, 68, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (872, 1, 69, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (873, 1, 70, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (874, 1, 71, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (875, 1, 72, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (876, 1, 73, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (877, 1, 74, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (878, 1, 75, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (879, 1, 76, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (880, 1, 77, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');
INSERT INTO `st_role_authority` VALUES (881, 1, 78, '1', 'admin', 'admin', '2020-08-21 08:58:37', '2020-08-21 08:58:37');

-- ----------------------------
-- Table structure for st_schedule_job
-- ----------------------------
DROP TABLE IF EXISTS `st_schedule_job`;
CREATE TABLE `st_schedule_job`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `job_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'JobID',
  `job_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'Job名称',
  `cron` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'cron表达式',
  `start_job` bit(1) NULL DEFAULT NULL COMMENT '启动状态',
  `job_class` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '方法',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `fire_time` datetime(0) NULL DEFAULT NULL COMMENT '触发时间',
  `last_fire_time` datetime(0) NULL DEFAULT NULL COMMENT '上次触发时间',
  `next_fire_time` datetime(0) NULL DEFAULT NULL COMMENT '下次触发时间',
  `job_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'Job描述',
  `fail_reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '失败原因',
  `yn_flag` char(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '有效标志',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
  `editor` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '修改人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '定时任务' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of st_schedule_job
-- ----------------------------
INSERT INTO `st_schedule_job` VALUES (1, 'StoryDemoJob', '框架演示任务', '0 0/5 * * * ?', b'1', 'com.story.storyadmin.scheduler.StoryDemoJob', '2019-08-19 06:14:06', '2020-08-14 09:25:00', '2020-08-14 09:20:00', '2020-08-14 09:30:00', '演示，仅此而已', NULL, '1', 'admin', 'admin', '2019-08-19 06:14:20', '2020-08-21 08:42:06');
INSERT INTO `st_schedule_job` VALUES (2, 'DbBackupJob', '数据库定时备份', '0 0 2 ? * TUE', b'0', 'com.story.storyadmin.scheduler.DbBackupJob', '2019-08-30 07:42:21', '2019-09-10 10:30:00', '2019-09-10 10:29:00', '2019-09-10 10:31:00', NULL, 'org.quartz.core.JobRunShell.run(JobRunShell.java:218)\norg.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\n', '1', 'admin', 'admin', '2019-08-30 07:42:29', '2019-09-10 19:37:28');

-- ----------------------------
-- Table structure for st_todo
-- ----------------------------
DROP TABLE IF EXISTS `st_todo`;
CREATE TABLE `st_todo`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `title` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '编码',
  `start` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `end` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `days_count` int(11) NULL DEFAULT NULL COMMENT '天数',
  `url` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '链接',
  `task_content` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '内容',
  `level` int(11) NULL DEFAULT NULL COMMENT '优先级',
  `executor` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '执行人',
  `yn_flag` char(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '有效标志',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
  `editor` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '修改人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '待办事项' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of st_todo
-- ----------------------------
INSERT INTO `st_todo` VALUES (1, '测试标题', '2019-08-15 14:28:12', '2019-08-16 14:28:18', NULL, NULL, '测试内容', 1, NULL, '0', NULL, 'admin', NULL, '2019-08-16 11:39:42');
INSERT INTO `st_todo` VALUES (2, '出差1', '2019-08-14 17:31:14', '2019-08-14 17:31:19', NULL, NULL, '出差内容', 2, NULL, '0', NULL, 'admin', NULL, '2019-08-16 11:39:46');
INSERT INTO `st_todo` VALUES (3, '测试3', '2019-07-30 00:00:00', '2019-08-01 00:00:00', NULL, NULL, '测试3', 1, NULL, '0', NULL, 'admin', NULL, '2019-08-16 11:39:34');
INSERT INTO `st_todo` VALUES (4, '生日', '2019-08-29 00:00:00', '2019-08-31 00:00:00', NULL, NULL, '生日', 2, NULL, '0', NULL, 'admin', NULL, '2019-08-16 11:39:50');
INSERT INTO `st_todo` VALUES (5, '回家', '2019-08-07 00:00:00', '2019-08-08 00:00:00', NULL, NULL, '回家', 1, NULL, '0', NULL, 'admin', NULL, '2019-08-16 11:39:38');
INSERT INTO `st_todo` VALUES (6, '约会3', '2019-08-12 00:00:00', '2019-08-12 23:59:00', NULL, 'www.baidu.com', '大望路', 2, NULL, '0', NULL, 'admin', NULL, '2019-08-16 11:38:59');
INSERT INTO `st_todo` VALUES (7, '聚会', '2019-08-25 00:00:00', '2019-08-25 23:59:00', NULL, NULL, '主日聚会', 3, NULL, '1', NULL, 'admin', NULL, '2019-08-18 12:38:23');
INSERT INTO `st_todo` VALUES (8, '练车', '2019-09-07 00:00:00', '2019-09-08 00:00:00', NULL, NULL, '练车', 5, NULL, '1', NULL, 'admin', NULL, '2019-09-06 02:20:10');
INSERT INTO `st_todo` VALUES (9, '测试', '2019-09-07 00:00:00', '2019-09-07 23:59:00', NULL, NULL, NULL, NULL, NULL, '1', NULL, 'admin', NULL, '2019-09-06 02:21:04');
INSERT INTO `st_todo` VALUES (10, '测试2', '2019-09-07 00:00:00', '2019-09-07 23:59:00', NULL, NULL, NULL, NULL, NULL, '1', NULL, 'admin', NULL, '2019-09-06 02:21:16');
INSERT INTO `st_todo` VALUES (11, '测试3', '2019-09-07 00:00:00', '2019-09-07 23:59:00', NULL, NULL, NULL, NULL, NULL, '1', NULL, 'admin', NULL, '2019-09-06 02:21:23');
INSERT INTO `st_todo` VALUES (12, '出差', '2019-09-17 00:00:00', '2019-09-20 00:00:00', NULL, NULL, NULL, 1, NULL, '1', NULL, 'admin', NULL, '2019-09-09 06:59:11');
INSERT INTO `st_todo` VALUES (13, '测试4', '2019-09-07 00:00:00', '2019-09-07 23:59:00', NULL, NULL, NULL, 3, NULL, '1', NULL, 'admin', NULL, '2019-09-10 06:38:34');
INSERT INTO `st_todo` VALUES (14, '上午10点开发小组进行开会', '2020-08-19 10:00:00', '2020-08-19 10:00:00', NULL, 'https://www.baidu.com/', '晚上6点开发小组进行开会，大家务必不要迟到', 3, NULL, '1', NULL, 'admin', NULL, '2020-08-19 01:54:05');

-- ----------------------------
-- Table structure for st_user
-- ----------------------------
DROP TABLE IF EXISTS `st_user`;
CREATE TABLE `st_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `account` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '账号',
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '姓名',
  `password` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '密码',
  `email` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '邮箱',
  `last_pwd_modified_time` datetime(0) NULL DEFAULT NULL COMMENT '上次修改密码时间',
  `status` char(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '0' COMMENT '状态',
  `yn_flag` char(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '有效标志',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
  `editor` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '修改人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `erp_flag` char(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'ERP账号标识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of st_user
-- ----------------------------
INSERT INTO `st_user` VALUES (1, 'admin', '管理员', 'bcd0b29f69059fe4c69802525c4ab550', 'admin@admin.admin', '2019-01-17 15:05:04', '1', '1', NULL, 'admin', '2018-12-29 10:40:07', '2020-08-17 07:06:56', '0');
INSERT INTO `st_user` VALUES (2, 'njsun', 'sunnj01', '0ace0a5fc5802a43305c8ae547a81afe', 'aa@aa.aa2', '2019-01-17 15:25:42', '0', '1', NULL, NULL, NULL, '2019-01-17 15:25:42', '0');
INSERT INTO `st_user` VALUES (4, 'test', '测试', '0ace0a5fc5802a43305c8ae547a81afe', 'test@aa.aa', '2019-01-17 15:23:28', '1', '1', NULL, NULL, NULL, '2019-01-17 15:23:28', '0');
INSERT INTO `st_user` VALUES (8, 'lovepli', 'lipan', 'a4e24a4d31f8c8aee8cf346945b0890c', 'pli38546@gmail.com', '2020-08-18 04:30:37', '1', '0', 'admin', 'admin', '2020-08-18 04:30:37', '2020-08-18 06:42:28', '0');

-- ----------------------------
-- Table structure for st_user_role
-- ----------------------------
DROP TABLE IF EXISTS `st_user_role`;
CREATE TABLE `st_user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `yn_flag` char(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '有效标志',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
  `editor` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '修改人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户角色关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of st_user_role
-- ----------------------------
INSERT INTO `st_user_role` VALUES (3, 1, 1, '0', 'admin', NULL, '2019-01-04 10:38:31', '2020-08-17 03:42:06');
INSERT INTO `st_user_role` VALUES (6, 1, 0, '0', 'admin', NULL, '2020-08-17 03:42:06', '2020-08-21 02:56:46');
INSERT INTO `st_user_role` VALUES (7, 1, 1, '0', 'admin', NULL, '2020-08-17 03:42:06', '2020-08-21 02:56:46');
INSERT INTO `st_user_role` VALUES (8, 1, 3, '0', 'admin', NULL, '2020-08-17 03:42:06', '2020-08-21 02:56:46');
INSERT INTO `st_user_role` VALUES (9, 1, 4, '0', 'admin', NULL, '2020-08-17 03:42:06', '2020-08-21 02:56:46');
INSERT INTO `st_user_role` VALUES (10, 4, 0, '1', 'admin', 'admin', '2020-08-17 04:30:36', '2020-08-17 04:30:36');
INSERT INTO `st_user_role` VALUES (11, 4, 1, '1', 'admin', 'admin', '2020-08-17 04:30:36', '2020-08-17 04:30:36');
INSERT INTO `st_user_role` VALUES (12, 4, 3, '1', 'admin', 'admin', '2020-08-17 04:30:36', '2020-08-17 04:30:36');
INSERT INTO `st_user_role` VALUES (13, 4, 4, '1', 'admin', 'admin', '2020-08-17 04:30:36', '2020-08-17 04:30:36');
INSERT INTO `st_user_role` VALUES (14, 1, 1, '0', 'admin', NULL, '2020-08-21 02:56:46', '2020-08-21 02:59:47');
INSERT INTO `st_user_role` VALUES (15, 1, 1, '1', 'admin', 'admin', '2020-08-21 02:59:47', '2020-08-21 02:59:47');

SET FOREIGN_KEY_CHECKS = 1;
