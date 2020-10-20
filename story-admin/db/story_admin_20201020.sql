/*
Navicat MySQL Data Transfer

Source Server         : docker_MYSQL5.7
Source Server Version : 50731
Source Host           : localhost:3306
Source Database       : story_admin

Target Server Type    : MYSQL
Target Server Version : 50731
File Encoding         : 65001

Date: 2020-10-20 14:25:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `st_att`
-- ----------------------------
DROP TABLE IF EXISTS `st_att`;
CREATE TABLE `st_att` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL COMMENT '文件名',
  `slot_id` varchar(64) DEFAULT NULL COMMENT '批次',
  `file_cate` varchar(64) DEFAULT NULL COMMENT '分类',
  `type` varchar(64) DEFAULT NULL COMMENT '文件类型',
  `file_size` bigint(20) DEFAULT NULL COMMENT '文件大小',
  `origin_name` varchar(64) DEFAULT NULL COMMENT '源文件名',
  `path` varchar(64) DEFAULT NULL COMMENT '存储路径',
  `description` varchar(256) DEFAULT NULL COMMENT '描述',
  `yn_flag` char(2) DEFAULT NULL COMMENT '有效标志',
  `creator` varchar(32) DEFAULT NULL COMMENT '创建人',
  `editor` varchar(32) DEFAULT NULL COMMENT '修改人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='附件表';

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

-- ----------------------------
-- Table structure for `st_attachment`
-- ----------------------------
DROP TABLE IF EXISTS `st_attachment`;
CREATE TABLE `st_attachment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sequence` varchar(20) NOT NULL,
  `file_name` varchar(40) NOT NULL,
  `file_path` varchar(200) NOT NULL,
  `upload_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `uploader` bigint(20) NOT NULL,
  `STATUS` decimal(2,0) NOT NULL DEFAULT '1' COMMENT '1 正常 0 已被删除',
  `deleter` bigint(20) DEFAULT NULL,
  `delete_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `real_file_name` varchar(80) DEFAULT NULL,
  `file_size` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `SYSTEM_FILE_SYSTEM_STAFF_id_fk` (`uploader`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='附件表';

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

-- ----------------------------
-- Table structure for `st_authority`
-- ----------------------------
DROP TABLE IF EXISTS `st_authority`;
CREATE TABLE `st_authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '权限名称',
  `code` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '权限编码',
  `full_id` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '编号路径',
  `authority_desc` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '权限描述',
  `show_order` int(11) DEFAULT NULL COMMENT '排序',
  `pid` bigint(20) DEFAULT NULL COMMENT '父Id',
  `yn_flag` char(2) COLLATE utf8_bin DEFAULT NULL COMMENT '有效标志',
  `creator` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `editor` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='权限表';

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
INSERT INTO `st_authority` VALUES ('30', '登录日志', 'sysmgr.loginlog', '0-2', null, '5', '2', '1', 'admin', 'admin', '2019-07-26 03:11:50', '2019-07-26 18:45:38');
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
INSERT INTO `st_authority` VALUES ('41', '系统日志', 'sysmgr.syslog', '0-2', null, '7', '2', '1', 'admin', 'admin', '2019-09-24 13:09:29', '2019-09-24 21:09:48');
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

-- ----------------------------
-- Table structure for `st_backup`
-- ----------------------------
DROP TABLE IF EXISTS `st_backup`;
CREATE TABLE `st_backup` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `backup_date` datetime DEFAULT NULL,
  `backup_name` varchar(255) DEFAULT NULL,
  `backup_path` varchar(255) DEFAULT NULL,
  `db_name` varchar(255) DEFAULT NULL,
  `file_size` bigint(20) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `runtime` bigint(20) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `yn_flag` char(2) DEFAULT NULL COMMENT '有效标志',
  `creator` varchar(32) DEFAULT NULL COMMENT '创建人',
  `editor` varchar(32) DEFAULT NULL COMMENT '修改人',
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
  `name` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '部门名称',
  `pid` bigint(20) DEFAULT NULL COMMENT '父id',
  `show_order` int(11) DEFAULT NULL COMMENT '排序',
  `dept_desc` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '部门描述',
  `yn_flag` char(2) COLLATE utf8_bin DEFAULT NULL COMMENT '有效标志',
  `creator` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `editor` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='部门表';

-- ----------------------------
-- Records of st_dept
-- ----------------------------
INSERT INTO `st_dept` VALUES ('1', '研发部门', '0', '1', 'hello,world', '1', 'admin', 'admin', '2020-07-31 08:29:50', '2020-07-31 08:32:36');
INSERT INTO `st_dept` VALUES ('2', '测试部门', '0', '2', null, '1', 'admin', 'admin', '2020-07-31 08:30:20', '2020-07-31 08:30:20');
INSERT INTO `st_dept` VALUES ('3', '运维部门', '0', '3', null, '1', 'admin', 'admin', '2020-07-31 08:30:30', '2020-07-31 08:31:45');
INSERT INTO `st_dept` VALUES ('4', '销售部门', '0', '4', null, '0', 'admin', 'admin', '2020-07-31 08:31:00', '2020-08-18 07:12:56');
INSERT INTO `st_dept` VALUES ('5', '研发一组', '1', '1', null, '1', 'admin', 'admin', '2020-07-31 08:31:16', '2020-07-31 08:31:16');
INSERT INTO `st_dept` VALUES ('6', '研发二组', '1', '2', '1111111', '1', 'admin', 'admin', '2020-07-31 08:31:30', '2020-07-31 08:46:36');
INSERT INTO `st_dept` VALUES ('7', '预备研发一组', '5', '1', '测试数据', '1', 'admin', 'admin', '2020-07-31 08:32:04', '2020-07-31 08:42:13');
INSERT INTO `st_dept` VALUES ('8', '预备研发二组', '5', '2', '11', '1', 'admin', 'admin', '2020-07-31 08:32:17', '2020-07-31 08:46:25');
INSERT INTO `st_dept` VALUES ('9', '新增部门', '6', null, null, '0', 'admin', 'admin', '2020-07-31 08:36:53', '2020-07-31 08:37:01');

-- ----------------------------
-- Table structure for `st_dict`
-- ----------------------------
DROP TABLE IF EXISTS `st_dict`;
CREATE TABLE `st_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `code` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '编码',
  `chn_name` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '中文名称',
  `eng_name` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '英文名称',
  `show_order` int(11) DEFAULT NULL COMMENT '排序',
  `parent_code` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '父编码',
  `yn_flag` char(2) COLLATE utf8_bin DEFAULT NULL COMMENT '有效标志',
  `creator` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `editor` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
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
  `name` varchar(255) DEFAULT NULL,
  `lujing` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

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

-- ----------------------------
-- Table structure for `st_inform`
-- ----------------------------
DROP TABLE IF EXISTS `st_inform`;
CREATE TABLE `st_inform` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL COMMENT '标题',
  `top` tinyint(1) NOT NULL COMMENT '是否置顶',
  `status` decimal(2,0) NOT NULL COMMENT '0 撤销  1 正常或过期',
  `content` text NOT NULL COMMENT '内容',
  `attchment_list` varchar(255) DEFAULT NULL,
  `canceler` bigint(20) DEFAULT NULL COMMENT '撤销人ID',
  `cancel_date` datetime DEFAULT NULL COMMENT '撤销时间',
  `outdate_operator` bigint(20) DEFAULT NULL COMMENT '过期操作人ID',
  `outdate_date` datetime DEFAULT NULL COMMENT '过期时间',
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `creator` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of st_inform
-- ----------------------------
INSERT INTO `st_inform` VALUES ('8', '测试', '0', '1', 'hello world！', '5,6,13', null, null, null, null, '2020-08-12 06:50:18', '1');
INSERT INTO `st_inform` VALUES ('21', '搬砖的一天', '1', '1', '又是搬砖的一天！', '5,6,14', null, null, null, null, '2020-08-14 02:33:58', '1');
INSERT INTO `st_inform` VALUES ('24', '上传一张图片', '0', '1', '666', '13,14', null, null, null, null, '2020-08-14 07:20:40', '1');
INSERT INTO `st_inform` VALUES ('25', '测试上传二张图片', '0', '2', '23333', '14', null, null, null, '2020-08-21 03:11:17', '2020-08-14 07:24:44', '1');
INSERT INTO `st_inform` VALUES ('29', '今天周五', '1', '1', '今天周五', '', null, null, null, null, '2020-08-14 08:00:27', null);
INSERT INTO `st_inform` VALUES ('31', '测试日志功能', '0', '1', '6666', '', null, null, null, null, '2020-08-18 07:13:48', null);
INSERT INTO `st_inform` VALUES ('32', '2020年8月19日测试', '0', '1', '2020年8月19日测试', '', null, null, null, null, '2020-08-19 01:08:44', null);
INSERT INTO `st_inform` VALUES ('33', '测试公告分页功能', '0', '2', '测试公告分页功能', '', null, null, null, '2020-08-19 01:40:13', '2020-08-19 01:15:12', null);
INSERT INTO `st_inform` VALUES ('34', '再测试一次', '0', '0', '再测试一次', '', null, '2020-08-19 01:36:41', null, null, '2020-08-19 01:15:33', null);
INSERT INTO `st_inform` VALUES ('35', '测试23333', '0', '2', '测试23333', '', null, null, null, '2020-08-19 01:37:07', '2020-08-19 01:15:48', null);
INSERT INTO `st_inform` VALUES ('36', '今天修改文件上传的bug', '1', '1', '今天修改文件上传的bug', '', null, null, null, null, '2020-08-27 08:03:18', null);
INSERT INTO `st_inform` VALUES ('37', '测试一遍再', '1', '1', '阿达', '', null, null, null, null, '2020-08-27 08:06:46', null);
INSERT INTO `st_inform` VALUES ('38', '6666', '0', '1', 'sdfs', '', null, null, null, null, '2020-08-27 08:09:38', null);
INSERT INTO `st_inform` VALUES ('39', '21', '0', '1', '阿斯达1', '', null, null, null, null, '2020-08-27 08:10:46', null);
INSERT INTO `st_inform` VALUES ('40', '测试888', '0', '1', '文学赏析\n李白《静夜思》诗意图\n李白《静夜思》诗意图\n《静夜思》没有奇特新颖的想象，没有精工华美的辞藻，只是用叙述的语气，写远客思乡之情，然而它却意味深长，耐人寻绎，千百年来，如此广泛地吸引着读者。全诗从“疑”到“举头”，从“举头”到“低头”，形象地揭示了诗人内心活动，鲜明地勾勒出一幅生动形象的月夜思乡图，抒发了作者在寂静的月夜思念家乡的感受。客中深夜不能成眠、短梦初回的情景。这时庭院是寂寥的，透过窗户的皎洁月光射到床前，带来了冷森森的秋宵寒意。诗人朦胧地乍一望去，在迷离恍惚的心情中，真好像是地上铺了一层白皑皑的浓霜；可是再定神一看，四周围的环境告诉他，这不是霜痕而是月色。月色不免吸引着他抬头一看，一轮娟娟素魄正挂在窗前，秋夜的太空是如此明净。秋月是分外光明的，然而它又是清冷的。对孤身远客来说，最容易触动旅思秋怀，使人感到客况萧条，年华易逝。凝望着月亮，也最容易使人产生遐想，想到故乡的一切，想到家里的亲人。想着，想着，头渐渐地低了下去，完全浸入于沉思之中。\n前两句写诗人在作客他乡的特定环境中一刹那间所产生的错觉。一个作客他乡的人都会有这样的感：白天奔波忙碌，倒还能冲淡离愁，到了夜深人静的时候，思乡的情绪，就难免一阵阵地在心头泛起波澜。在月明之夜，尤其是月色如霜的秋夜更是如此。“疑是地上霜”中的“疑”字，生动地表达了诗人睡梦初醒，迷离恍惚中将照射在床前的清冷月光误作铺在地面的浓霜。“霜”字用得更妙，既形容了月光的皎洁，又表达了季节的寒冷，还烘托出诗人飘泊他乡的孤寂凄凉之情。\n后两句通过动作神态的刻画，深化思乡之情。“望”字照应了前句的“疑”字，表明诗人已从迷朦转为清醒，他翘首凝望着月亮，不禁想起，此刻他的故乡也正处在这轮明月的照耀下，自然引出了“低头思故乡”的结句。“低头”这一动作描画出诗人完全处于沉思之中。“思”字给读者留下丰富的想象：那家乡的父老兄弟、亲朋好友，那家乡的一山一水、一草一木，那逝去的年华与往事，无不在思念之中。一个“思”字所包涵的内容实在太丰富了。\n短短四句诗，写得清新朴素，明白如话。构思细致而深曲，脱口吟成、浑然无迹。内容是单纯，却又是丰富的；内容是容易理解的，却又是体味不尽的。诗人所没有说的比他已经说出来的要多得多，体现了“自然”、“无意于工而无不工”的妙境。 [3]  [9]', '', null, null, null, null, '2020-08-27 08:15:43', null);
INSERT INTO `st_inform` VALUES ('41', '测试888', '0', '1', '文学赏析\n李白《静夜思》诗意图\n李白《静夜思》诗意图\n《静夜思》没有奇特新颖的想象，没有精工华美的辞藻，只是用叙述的语气，写远客思乡之情，然而它却意味深长，耐人寻绎，千百年来，如此广泛地吸引着读者。全诗从“疑”到“举头”，从“举头”到“低头”，形象地揭示了诗人内心活动，鲜明地勾勒出一幅生动形象的月夜思乡图，抒发了作者在寂静的月夜思念家乡的感受。客中深夜不能成眠、短梦初回的情景。这时庭院是寂寥的，透过窗户的皎洁月光射到床前，带来了冷森森的秋宵寒意。诗人朦胧地乍一望去，在迷离恍惚的心情中，真好像是地上铺了一层白皑皑的浓霜；可是再定神一看，四周围的环境告诉他，这不是霜痕而是月色。月色不免吸引着他抬头一看，一轮娟娟素魄正挂在窗前，秋夜的太空是如此明净。秋月是分外光明的，然而它又是清冷的。对孤身远客来说，最容易触动旅思秋怀，使人感到客况萧条，年华易逝。凝望着月亮，也最容易使人产生遐想，想到故乡的一切，想到家里的亲人。想着，想着，头渐渐地低了下去，完全浸入于沉思之中。\n前两句写诗人在作客他乡的特定环境中一刹那间所产生的错觉。一个作客他乡的人都会有这样的感：白天奔波忙碌，倒还能冲淡离愁，到了夜深人静的时候，思乡的情绪，就难免一阵阵地在心头泛起波澜。在月明之夜，尤其是月色如霜的秋夜更是如此。“疑是地上霜”中的“疑”字，生动地表达了诗人睡梦初醒，迷离恍惚中将照射在床前的清冷月光误作铺在地面的浓霜。“霜”字用得更妙，既形容了月光的皎洁，又表达了季节的寒冷，还烘托出诗人飘泊他乡的孤寂凄凉之情。\n后两句通过动作神态的刻画，深化思乡之情。“望”字照应了前句的“疑”字，表明诗人已从迷朦转为清醒，他翘首凝望着月亮，不禁想起，此刻他的故乡也正处在这轮明月的照耀下，自然引出了“低头思故乡”的结句。“低头”这一动作描画出诗人完全处于沉思之中。“思”字给读者留下丰富的想象：那家乡的父老兄弟、亲朋好友，那家乡的一山一水、一草一木，那逝去的年华与往事，无不在思念之中。一个“思”字所包涵的内容实在太丰富了。\n短短四句诗，写得清新朴素，明白如话。构思细致而深曲，脱口吟成、浑然无迹。内容是单纯，却又是丰富的；内容是容易理解的，却又是体味不尽的。诗人所没有说的比他已经说出来的要多得多，体现了“自然”、“无意于工而无不工”的妙境。 [3]  [9]', '', null, null, null, null, '2020-08-27 08:15:43', null);
INSERT INTO `st_inform` VALUES ('42', '测试测试', '0', '1', '阿达', '', null, null, null, null, '2020-08-27 08:20:23', null);
INSERT INTO `st_inform` VALUES ('43', '测试一下再', '0', '1', '测试一下再', '', null, null, null, null, '2020-08-27 08:34:56', null);
INSERT INTO `st_inform` VALUES ('44', '测试666', '0', '1', '阿达', '', null, null, null, null, '2020-08-27 08:37:43', null);
INSERT INTO `st_inform` VALUES ('45', '232', '0', '0', 'sdfs', '', null, '2020-09-10 07:52:46', null, null, '2020-08-27 08:41:46', null);
INSERT INTO `st_inform` VALUES ('46', '999', '0', '1', '大大', '', null, null, null, null, '2020-08-27 08:46:09', null);
INSERT INTO `st_inform` VALUES ('47', '测试上传附件功能20200211', '1', '1', '测试上传附件功能20200211', '13,14,27,28', null, null, null, null, '2020-09-11 07:29:57', '1');
INSERT INTO `st_inform` VALUES ('48', '上传其他格式文件', '1', '1', '上传其他格式文件', '30', null, null, null, null, '2020-09-14 06:19:49', null);
INSERT INTO `st_inform` VALUES ('49', '20200921测试', '1', '1', '20200921测试', '', null, null, null, null, '2020-09-21 07:45:35', null);
INSERT INTO `st_inform` VALUES ('50', 'q', '0', '1', '11', '', null, null, null, null, '2020-09-21 08:00:58', null);
INSERT INTO `st_inform` VALUES ('51', '22', '1', '1', '22', '', null, null, null, null, '2020-09-21 08:03:51', null);
INSERT INTO `st_inform` VALUES ('52', '0927', '1', '1', '0927', '40', null, null, null, null, '2020-09-27 01:24:21', null);
INSERT INTO `st_inform` VALUES ('53', '0927测试上传多个文件', '1', '1', 'adc', '41,42', null, null, null, null, '2020-09-27 01:27:11', null);

-- ----------------------------
-- Table structure for `st_login_log`
-- ----------------------------
DROP TABLE IF EXISTS `st_login_log`;
CREATE TABLE `st_login_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `login_time` datetime DEFAULT NULL COMMENT '访问时间',
  `content` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '内容',
  `yn_flag` char(2) COLLATE utf8_bin DEFAULT NULL COMMENT '有效标志',
  `creator` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `editor` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=307 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='登录日志';

-- ----------------------------
-- Records of st_login_log
-- ----------------------------
INSERT INTO `st_login_log` VALUES ('1', 'admin', '2019-07-26 05:50:21', '登录成功', '0', 'admin', 'admin', '2019-07-26 05:50:21', '2020-09-15 03:18:31');
INSERT INTO `st_login_log` VALUES ('2', 'admin', '2019-07-26 08:31:30', '登录成功', '0', 'admin', 'admin', '2019-07-26 08:31:30', '2020-09-15 03:21:39');
INSERT INTO `st_login_log` VALUES ('3', 'admin', '2019-07-26 08:58:40', '登录成功', '0', 'admin', 'admin', '2019-07-26 08:58:40', '2020-09-15 03:21:39');
INSERT INTO `st_login_log` VALUES ('4', 'admin', '2019-07-26 09:04:12', '登录成功', '0', 'admin', 'admin', '2019-07-26 09:04:12', '2020-09-15 03:21:45');
INSERT INTO `st_login_log` VALUES ('5', 'admin', '2019-07-26 11:35:11', '登录成功', '0', 'admin', 'admin', '2019-07-26 11:35:11', '2020-09-15 03:21:45');
INSERT INTO `st_login_log` VALUES ('6', 'admin', '2019-07-29 06:06:49', '登录成功', '0', 'admin', 'admin', '2019-07-29 06:06:49', '2020-09-15 03:21:45');
INSERT INTO `st_login_log` VALUES ('7', 'admin', '2019-07-29 06:20:18', '登录成功', '0', 'admin', 'admin', '2019-07-29 06:20:18', '2020-09-15 03:21:45');
INSERT INTO `st_login_log` VALUES ('8', 'admin', '2019-07-29 09:12:12', '登录成功', '0', 'admin', 'admin', '2019-07-29 09:12:12', '2020-09-15 03:21:45');
INSERT INTO `st_login_log` VALUES ('9', 'admin', '2019-07-30 11:36:51', '登录成功', '0', 'admin', 'admin', '2019-07-30 11:36:51', '2020-09-15 03:21:45');
INSERT INTO `st_login_log` VALUES ('10', 'admin', '2019-07-30 11:53:40', '登录成功', '0', 'admin', 'admin', '2019-07-30 11:53:40', '2020-09-15 03:21:45');
INSERT INTO `st_login_log` VALUES ('11', 'admin', '2019-08-01 06:03:23', '登录成功', '0', 'admin', 'admin', '2019-08-01 06:03:23', '2020-09-15 03:21:45');
INSERT INTO `st_login_log` VALUES ('12', 'admin', '2019-08-01 06:34:26', '登录成功', '0', 'admin', 'admin', '2019-08-01 06:34:26', '2020-09-15 03:21:45');
INSERT INTO `st_login_log` VALUES ('13', 'admin', '2019-08-03 12:43:20', '登录成功', '0', 'admin', 'admin', '2019-08-03 12:43:20', '2020-09-15 03:21:45');
INSERT INTO `st_login_log` VALUES ('14', 'admin', '2019-08-05 02:41:57', '登录成功', '0', 'admin', 'admin', '2019-08-05 02:41:57', '2020-09-15 03:21:49');
INSERT INTO `st_login_log` VALUES ('15', 'admin', '2019-08-05 11:45:57', '登录成功', '0', 'admin', 'admin', '2019-08-05 11:45:57', '2020-09-15 03:21:49');
INSERT INTO `st_login_log` VALUES ('16', 'admin', '2019-08-07 02:31:36', '登录成功', '0', 'admin', 'admin', '2019-08-07 02:31:36', '2020-09-15 03:21:49');
INSERT INTO `st_login_log` VALUES ('17', 'admin', '2019-08-12 06:03:37', '登录成功', '0', 'admin', 'admin', '2019-08-12 06:03:37', '2020-09-15 03:21:49');
INSERT INTO `st_login_log` VALUES ('18', 'admin', '2019-08-12 06:24:23', '登录成功', '0', 'admin', 'admin', '2019-08-12 06:24:23', '2020-09-15 03:21:49');
INSERT INTO `st_login_log` VALUES ('19', 'admin', '2019-08-12 06:45:17', '登录成功', '0', 'admin', 'admin', '2019-08-12 06:45:17', '2020-09-15 03:21:49');
INSERT INTO `st_login_log` VALUES ('20', 'admin', '2019-08-12 06:45:58', '登录成功', '0', 'admin', 'admin', '2019-08-12 06:45:58', '2020-09-15 03:21:49');
INSERT INTO `st_login_log` VALUES ('21', 'admin', '2019-08-12 06:46:36', '登录成功', '0', 'admin', 'admin', '2019-08-12 06:46:36', '2020-09-15 03:21:49');
INSERT INTO `st_login_log` VALUES ('22', 'admin', '2019-08-12 07:38:59', '登录成功', '0', 'admin', 'admin', '2019-08-12 07:38:59', '2020-09-15 03:21:49');
INSERT INTO `st_login_log` VALUES ('23', 'admin', '2019-08-12 08:22:30', '登录成功', '0', 'admin', 'admin', '2019-08-12 08:22:30', '2020-09-15 03:21:49');
INSERT INTO `st_login_log` VALUES ('24', 'admin', '2019-08-12 09:40:29', '登录成功', '0', 'admin', 'admin', '2019-08-12 09:40:29', '2020-09-15 03:26:47');
INSERT INTO `st_login_log` VALUES ('25', 'admin', '2019-08-12 10:47:12', '登录成功', '0', 'admin', 'admin', '2019-08-12 10:47:12', '2020-09-15 03:26:47');
INSERT INTO `st_login_log` VALUES ('26', 'admin', '2019-08-13 01:37:26', '登录成功', '0', 'admin', 'admin', '2019-08-13 01:37:26', '2020-09-15 03:26:47');
INSERT INTO `st_login_log` VALUES ('27', 'admin', '2019-08-13 05:48:12', '登录成功', '0', 'admin', 'admin', '2019-08-13 05:48:12', '2020-09-15 03:26:47');
INSERT INTO `st_login_log` VALUES ('28', 'admin', '2019-08-13 07:03:36', '登录成功', '0', 'admin', 'admin', '2019-08-13 07:03:36', '2020-09-15 03:26:47');
INSERT INTO `st_login_log` VALUES ('29', 'admin', '2019-08-13 07:52:05', '登录成功', '0', 'admin', 'admin', '2019-08-13 07:52:05', '2020-09-15 03:26:47');
INSERT INTO `st_login_log` VALUES ('30', 'admin', '2019-08-14 06:04:20', '登录成功', '0', 'admin', 'admin', '2019-08-14 06:04:20', '2020-09-15 03:26:47');
INSERT INTO `st_login_log` VALUES ('31', 'admin', '2019-08-14 09:57:30', '登录成功', '0', 'admin', 'admin', '2019-08-14 09:57:30', '2020-09-15 03:26:47');
INSERT INTO `st_login_log` VALUES ('32', 'admin', '2019-08-15 02:05:58', '登录成功', '0', 'admin', 'admin', '2019-08-15 02:05:58', '2020-09-15 03:26:47');
INSERT INTO `st_login_log` VALUES ('33', 'admin', '2019-08-15 05:54:23', '登录成功', '0', 'admin', 'admin', '2019-08-15 05:54:23', '2020-09-15 03:26:47');
INSERT INTO `st_login_log` VALUES ('34', 'admin', '2019-08-16 04:52:55', '登录成功', '0', 'admin', 'admin', '2019-08-16 04:52:55', '2020-09-21 08:09:42');
INSERT INTO `st_login_log` VALUES ('35', 'admin', '2019-08-16 05:03:52', '登录成功', '0', 'admin', 'admin', '2019-08-16 05:03:52', '2020-09-21 08:09:42');
INSERT INTO `st_login_log` VALUES ('36', 'admin', '2019-08-16 05:06:07', '登录成功', '0', 'admin', 'admin', '2019-08-16 05:06:07', '2020-10-20 02:59:46');
INSERT INTO `st_login_log` VALUES ('37', 'admin', '2019-08-16 06:07:13', '登录成功', '0', 'admin', 'admin', '2019-08-16 06:07:13', '2020-10-20 02:59:51');
INSERT INTO `st_login_log` VALUES ('38', 'admin', '2019-08-16 06:07:46', '登录成功', '0', 'admin', 'admin', '2019-08-16 06:07:46', '2020-10-20 03:01:26');
INSERT INTO `st_login_log` VALUES ('39', 'admin', '2019-08-16 06:08:04', '登录成功', '0', 'admin', 'admin', '2019-08-16 06:08:04', '2020-10-20 02:59:55');
INSERT INTO `st_login_log` VALUES ('40', 'admin', '2019-08-18 12:37:26', '登录成功', '0', 'admin', 'admin', '2019-08-18 12:37:26', '2020-10-20 03:01:26');
INSERT INTO `st_login_log` VALUES ('41', 'admin', '2019-08-19 01:35:08', '登录成功', '1', 'admin', 'admin', '2019-08-19 01:35:08', '2019-08-19 09:35:08');
INSERT INTO `st_login_log` VALUES ('42', 'admin', '2019-08-19 03:02:58', '登录成功', '1', 'admin', 'admin', '2019-08-19 03:02:58', '2019-08-19 11:02:58');
INSERT INTO `st_login_log` VALUES ('43', 'admin', '2019-08-19 03:03:04', '登录成功', '1', 'admin', 'admin', '2019-08-19 03:03:04', '2019-08-19 11:03:04');
INSERT INTO `st_login_log` VALUES ('44', 'admin', '2019-08-19 03:03:07', '登录成功', '1', 'admin', 'admin', '2019-08-19 03:03:07', '2019-08-19 11:03:07');
INSERT INTO `st_login_log` VALUES ('45', 'admin', '2019-08-19 03:04:53', '登录成功', '1', 'admin', 'admin', '2019-08-19 03:04:53', '2019-08-19 11:04:52');
INSERT INTO `st_login_log` VALUES ('46', 'admin', '2019-08-19 03:18:46', '登录成功', '1', 'admin', 'admin', '2019-08-19 03:18:46', '2019-08-19 11:18:45');
INSERT INTO `st_login_log` VALUES ('47', 'admin', '2019-08-19 10:22:09', '登录成功', '1', 'admin', 'admin', '2019-08-19 10:22:09', '2019-08-19 18:22:08');
INSERT INTO `st_login_log` VALUES ('48', 'admin', '2019-08-23 08:42:16', '登录成功', '1', 'admin', 'admin', '2019-08-23 08:42:16', '2019-08-23 16:42:15');
INSERT INTO `st_login_log` VALUES ('49', 'admin', '2019-08-26 11:59:42', '登录成功', '1', 'admin', 'admin', '2019-08-26 11:59:42', '2019-08-26 19:59:41');
INSERT INTO `st_login_log` VALUES ('50', 'admin', '2019-08-28 03:27:06', '登录成功', '1', 'admin', 'admin', '2019-08-28 03:27:06', '2019-08-28 11:27:06');
INSERT INTO `st_login_log` VALUES ('51', 'admin', '2019-08-30 06:05:28', '登录成功', '1', 'admin', 'admin', '2019-08-30 06:05:28', '2019-08-30 14:05:28');
INSERT INTO `st_login_log` VALUES ('52', 'admin', '2019-09-06 02:17:43', '登录成功', '1', 'admin', 'admin', '2019-09-06 02:17:43', '2019-09-06 10:17:43');
INSERT INTO `st_login_log` VALUES ('53', 'admin', '2019-09-06 12:01:25', '登录成功', '1', 'admin', 'admin', '2019-09-06 12:01:25', '2019-09-06 20:01:24');
INSERT INTO `st_login_log` VALUES ('54', 'admin', '2019-09-06 12:02:53', '登录成功', '1', 'admin', 'admin', '2019-09-06 12:02:53', '2019-09-06 20:02:53');
INSERT INTO `st_login_log` VALUES ('55', 'admin', '2019-09-07 12:49:07', '登录成功', '1', 'admin', 'admin', '2019-09-07 12:49:07', '2019-09-07 20:49:06');
INSERT INTO `st_login_log` VALUES ('56', 'admin', '2019-09-07 12:54:26', '登录成功', '1', 'admin', 'admin', '2019-09-07 12:54:26', '2019-09-07 20:54:26');
INSERT INTO `st_login_log` VALUES ('57', 'admin', '2019-09-07 12:55:38', '登录成功', '1', 'admin', 'admin', '2019-09-07 12:55:38', '2019-09-07 20:55:38');
INSERT INTO `st_login_log` VALUES ('58', 'admin', '2019-09-07 12:56:42', '登录成功', '1', 'admin', 'admin', '2019-09-07 12:56:42', '2019-09-07 20:56:41');
INSERT INTO `st_login_log` VALUES ('59', 'admin', '2019-09-07 12:59:28', '登录成功', '1', 'admin', 'admin', '2019-09-07 12:59:28', '2019-09-07 20:59:27');
INSERT INTO `st_login_log` VALUES ('60', 'admin', '2019-09-07 13:05:07', '登录成功', '1', 'admin', 'admin', '2019-09-07 13:05:07', '2019-09-07 21:05:07');
INSERT INTO `st_login_log` VALUES ('61', 'admin', '2019-09-07 13:06:19', '登录成功', '1', 'admin', 'admin', '2019-09-07 13:06:19', '2019-09-07 21:06:19');
INSERT INTO `st_login_log` VALUES ('62', 'admin', '2019-09-07 13:08:12', '登录成功', '1', 'admin', 'admin', '2019-09-07 13:08:12', '2019-09-07 21:08:11');
INSERT INTO `st_login_log` VALUES ('63', 'admin', '2019-09-07 13:10:11', '登录成功', '1', 'admin', 'admin', '2019-09-07 13:10:11', '2019-09-07 21:10:10');
INSERT INTO `st_login_log` VALUES ('64', 'admin', '2019-09-07 13:12:38', '登录成功', '1', 'admin', 'admin', '2019-09-07 13:12:38', '2019-09-07 21:12:38');
INSERT INTO `st_login_log` VALUES ('65', 'admin', '2019-09-07 13:26:07', '登录成功', '1', 'admin', 'admin', '2019-09-07 13:26:07', '2019-09-07 21:26:07');
INSERT INTO `st_login_log` VALUES ('66', 'admin', '2019-09-09 02:32:46', '登录成功', '1', 'admin', 'admin', '2019-09-09 02:32:46', '2019-09-09 10:32:46');
INSERT INTO `st_login_log` VALUES ('67', 'admin', '2019-09-09 06:42:40', '登录成功', '1', 'admin', 'admin', '2019-09-09 06:42:40', '2019-09-09 14:42:39');
INSERT INTO `st_login_log` VALUES ('68', 'admin', '2019-09-10 03:27:06', '登录成功', '1', 'admin', 'admin', '2019-09-10 03:27:06', '2019-09-10 11:27:05');
INSERT INTO `st_login_log` VALUES ('69', 'admin', '2019-09-10 12:09:47', '登录成功', '1', 'admin', 'admin', '2019-09-10 12:09:47', '2019-09-10 20:09:47');
INSERT INTO `st_login_log` VALUES ('70', 'admin', '2019-09-11 02:10:38', '登录成功', '1', 'admin', 'admin', '2019-09-11 02:10:38', '2019-09-11 10:10:38');
INSERT INTO `st_login_log` VALUES ('71', 'admin', '2019-09-12 01:39:31', '登录成功', '1', 'admin', 'admin', '2019-09-12 01:39:31', '2019-09-12 09:39:31');
INSERT INTO `st_login_log` VALUES ('72', 'admin', '2019-09-12 02:02:23', '登录成功', '1', 'admin', 'admin', '2019-09-12 02:02:23', '2019-09-12 10:02:22');
INSERT INTO `st_login_log` VALUES ('73', 'admin', '2019-09-12 07:58:35', '登录成功', '1', 'admin', 'admin', '2019-09-12 07:58:35', '2019-09-12 15:58:34');
INSERT INTO `st_login_log` VALUES ('74', 'admin', '2019-09-12 11:25:01', '登录成功', '1', 'admin', 'admin', '2019-09-12 11:25:01', '2019-09-12 19:25:00');
INSERT INTO `st_login_log` VALUES ('75', 'admin', '2019-09-16 06:15:41', '登录成功', '1', 'admin', 'admin', '2019-09-16 06:15:41', '2019-09-16 14:15:41');
INSERT INTO `st_login_log` VALUES ('76', 'admin', '2019-09-16 07:03:26', '登录成功', '1', 'admin', 'admin', '2019-09-16 07:03:26', '2019-09-16 15:03:26');
INSERT INTO `st_login_log` VALUES ('77', 'admin', '2019-09-17 02:09:06', '登录成功', '1', 'admin', 'admin', '2019-09-17 02:09:06', '2019-09-17 10:09:06');
INSERT INTO `st_login_log` VALUES ('78', 'admin', '2019-09-17 10:09:14', '登录成功', '1', 'admin', 'admin', '2019-09-17 10:09:14', '2019-09-17 18:09:13');
INSERT INTO `st_login_log` VALUES ('79', 'admin', '2019-09-18 06:26:24', '登录成功', '1', 'admin', 'admin', '2019-09-18 06:26:24', '2019-09-18 14:26:24');
INSERT INTO `st_login_log` VALUES ('80', 'admin', '2019-09-19 05:57:54', '登录成功', '1', 'admin', 'admin', '2019-09-19 05:57:54', '2019-09-19 13:57:54');
INSERT INTO `st_login_log` VALUES ('81', 'admin', '2019-09-20 02:49:42', '登录成功', '1', 'admin', 'admin', '2019-09-20 02:49:42', '2019-09-20 10:49:41');
INSERT INTO `st_login_log` VALUES ('82', 'admin', '2019-09-24 02:06:37', '登录成功', '1', 'admin', 'admin', '2019-09-24 02:06:37', '2019-09-24 10:06:37');
INSERT INTO `st_login_log` VALUES ('83', 'admin', '2019-09-25 06:08:16', '登录成功', '1', 'admin', 'admin', '2019-09-25 06:08:16', '2019-09-25 14:08:15');
INSERT INTO `st_login_log` VALUES ('84', 'admin', '2019-09-25 06:22:56', '登录成功', '1', 'admin', 'admin', '2019-09-25 06:22:56', '2019-09-25 14:22:55');
INSERT INTO `st_login_log` VALUES ('85', 'admin', '2019-09-25 07:18:07', '登录成功', '1', 'admin', 'admin', '2019-09-25 07:18:07', '2019-09-25 15:18:07');
INSERT INTO `st_login_log` VALUES ('86', 'admin', '2019-09-25 07:18:25', '登录成功', '1', 'admin', 'admin', '2019-09-25 07:18:25', '2019-09-25 15:18:25');
INSERT INTO `st_login_log` VALUES ('87', 'admin', '2020-08-07 05:52:45', '登录成功', '1', 'admin', 'admin', '2020-08-07 05:52:45', '2020-08-07 05:52:45');
INSERT INTO `st_login_log` VALUES ('88', 'admin', '2020-08-07 06:27:11', '登录成功', '1', 'admin', 'admin', '2020-08-07 06:27:11', '2020-08-07 06:27:10');
INSERT INTO `st_login_log` VALUES ('89', 'admin', '2020-08-10 02:20:43', '登录成功', '1', 'admin', 'admin', '2020-08-10 02:20:43', '2020-08-10 02:20:43');
INSERT INTO `st_login_log` VALUES ('90', 'admin', '2020-08-10 03:14:55', '登录成功', '1', 'admin', 'admin', '2020-08-10 03:14:55', '2020-08-10 03:14:55');
INSERT INTO `st_login_log` VALUES ('91', 'admin', '2020-08-10 03:29:41', '登录成功', '1', 'admin', 'admin', '2020-08-10 03:29:41', '2020-08-10 03:29:40');
INSERT INTO `st_login_log` VALUES ('92', 'admin', '2020-08-10 04:32:38', '登录成功', '1', 'admin', 'admin', '2020-08-10 04:32:38', '2020-08-10 04:32:38');
INSERT INTO `st_login_log` VALUES ('93', 'admin', '2020-08-10 04:40:49', '登录成功', '1', 'admin', 'admin', '2020-08-10 04:40:49', '2020-08-10 04:40:48');
INSERT INTO `st_login_log` VALUES ('94', 'admin', '2020-08-10 04:52:29', '登录成功', '1', 'admin', 'admin', '2020-08-10 04:52:29', '2020-08-10 04:52:28');
INSERT INTO `st_login_log` VALUES ('95', 'admin', '2020-08-10 05:43:50', '登录成功', '1', 'admin', 'admin', '2020-08-10 05:43:50', '2020-08-10 05:43:50');
INSERT INTO `st_login_log` VALUES ('96', 'admin', '2020-08-10 06:03:20', '登录成功', '1', 'admin', 'admin', '2020-08-10 06:03:20', '2020-08-10 06:03:19');
INSERT INTO `st_login_log` VALUES ('97', 'admin', '2020-08-11 08:59:52', '登录成功', '1', 'admin', 'admin', '2020-08-11 08:59:52', '2020-08-11 08:59:51');
INSERT INTO `st_login_log` VALUES ('98', 'admin', '2020-08-12 01:18:05', '登录成功', '1', 'admin', 'admin', '2020-08-12 01:18:05', '2020-08-12 01:18:04');
INSERT INTO `st_login_log` VALUES ('99', 'admin', '2020-08-12 01:18:39', '登录成功', '1', 'admin', 'admin', '2020-08-12 01:18:39', '2020-08-12 01:18:38');
INSERT INTO `st_login_log` VALUES ('100', 'admin', '2020-08-12 01:51:39', '登录成功', '1', 'admin', 'admin', '2020-08-12 01:51:39', '2020-08-12 01:51:38');
INSERT INTO `st_login_log` VALUES ('101', 'admin', '2020-08-12 03:44:33', '登录成功', '1', 'admin', 'admin', '2020-08-12 03:44:33', '2020-08-12 03:44:33');
INSERT INTO `st_login_log` VALUES ('102', 'admin', '2020-08-12 03:52:36', '登录成功', '1', 'admin', 'admin', '2020-08-12 03:52:36', '2020-08-12 03:52:36');
INSERT INTO `st_login_log` VALUES ('103', 'admin', '2020-08-12 04:15:56', '登录成功', '1', 'admin', 'admin', '2020-08-12 04:15:56', '2020-08-12 04:15:56');
INSERT INTO `st_login_log` VALUES ('104', 'admin', '2020-08-12 06:02:12', '登录成功', '1', 'admin', 'admin', '2020-08-12 06:02:12', '2020-08-12 06:02:11');
INSERT INTO `st_login_log` VALUES ('105', 'admin', '2020-08-12 06:49:06', '登录成功', '1', 'admin', 'admin', '2020-08-12 06:49:06', '2020-08-12 06:49:05');
INSERT INTO `st_login_log` VALUES ('106', 'admin', '2020-08-12 06:52:00', '登录成功', '1', 'admin', 'admin', '2020-08-12 06:52:00', '2020-08-12 06:52:00');
INSERT INTO `st_login_log` VALUES ('107', 'admin', '2020-08-12 06:55:36', '登录成功', '1', 'admin', 'admin', '2020-08-12 06:55:36', '2020-08-12 06:55:36');
INSERT INTO `st_login_log` VALUES ('108', 'admin', '2020-08-12 06:56:54', '登录成功', '1', 'admin', 'admin', '2020-08-12 06:56:54', '2020-08-12 06:56:53');
INSERT INTO `st_login_log` VALUES ('109', 'admin', '2020-08-12 07:04:18', '登录成功', '1', 'admin', 'admin', '2020-08-12 07:04:18', '2020-08-12 07:04:18');
INSERT INTO `st_login_log` VALUES ('110', 'admin', '2020-08-12 08:52:28', '登录成功', '1', 'admin', 'admin', '2020-08-12 08:52:28', '2020-08-12 08:52:28');
INSERT INTO `st_login_log` VALUES ('111', 'admin', '2020-08-12 09:07:27', '登录成功', '1', 'admin', 'admin', '2020-08-12 09:07:27', '2020-08-12 09:07:27');
INSERT INTO `st_login_log` VALUES ('112', 'admin', '2020-08-12 09:09:03', '登录成功', '1', 'admin', 'admin', '2020-08-12 09:09:03', '2020-08-12 09:09:03');
INSERT INTO `st_login_log` VALUES ('113', 'admin', '2020-08-12 09:20:09', '登录成功', '1', 'admin', 'admin', '2020-08-12 09:20:09', '2020-08-12 09:20:08');
INSERT INTO `st_login_log` VALUES ('114', 'admin', '2020-08-13 01:09:14', '登录成功', '1', 'admin', 'admin', '2020-08-13 01:09:14', '2020-08-13 01:09:13');
INSERT INTO `st_login_log` VALUES ('115', 'admin', '2020-08-13 01:18:15', '登录成功', '1', 'admin', 'admin', '2020-08-13 01:18:15', '2020-08-13 01:18:15');
INSERT INTO `st_login_log` VALUES ('116', 'admin', '2020-08-13 01:45:28', '登录成功', '1', 'admin', 'admin', '2020-08-13 01:45:28', '2020-08-13 01:45:28');
INSERT INTO `st_login_log` VALUES ('117', 'admin', '2020-08-13 01:50:59', '登录成功', '1', 'admin', 'admin', '2020-08-13 01:50:59', '2020-08-13 01:50:59');
INSERT INTO `st_login_log` VALUES ('118', 'admin', '2020-08-13 02:18:11', '登录成功', '1', 'admin', 'admin', '2020-08-13 02:18:11', '2020-08-13 02:18:10');
INSERT INTO `st_login_log` VALUES ('119', 'admin', '2020-08-13 02:40:58', '登录成功', '1', 'admin', 'admin', '2020-08-13 02:40:58', '2020-08-13 02:40:58');
INSERT INTO `st_login_log` VALUES ('120', 'admin', '2020-08-13 02:47:38', '登录成功', '1', 'admin', 'admin', '2020-08-13 02:47:38', '2020-08-13 02:47:38');
INSERT INTO `st_login_log` VALUES ('121', 'admin', '2020-08-13 03:05:12', '登录成功', '1', 'admin', 'admin', '2020-08-13 03:05:12', '2020-08-13 03:05:12');
INSERT INTO `st_login_log` VALUES ('122', 'admin', '2020-08-13 03:25:56', '登录成功', '1', 'admin', 'admin', '2020-08-13 03:25:56', '2020-08-13 03:25:55');
INSERT INTO `st_login_log` VALUES ('123', 'admin', '2020-08-13 03:35:58', '登录成功', '1', 'admin', 'admin', '2020-08-13 03:35:58', '2020-08-13 03:35:58');
INSERT INTO `st_login_log` VALUES ('124', 'admin', '2020-08-13 04:20:54', '登录成功', '1', 'admin', 'admin', '2020-08-13 04:20:54', '2020-08-13 04:20:53');
INSERT INTO `st_login_log` VALUES ('125', 'admin', '2020-08-13 04:28:37', '登录成功', '1', 'admin', 'admin', '2020-08-13 04:28:37', '2020-08-13 04:28:36');
INSERT INTO `st_login_log` VALUES ('126', 'admin', '2020-08-13 04:29:50', '登录成功', '1', 'admin', 'admin', '2020-08-13 04:29:50', '2020-08-13 04:29:50');
INSERT INTO `st_login_log` VALUES ('127', 'admin', '2020-08-13 06:21:49', '登录成功', '1', 'admin', 'admin', '2020-08-13 06:21:49', '2020-08-13 06:21:48');
INSERT INTO `st_login_log` VALUES ('128', 'admin', '2020-08-13 06:26:39', '登录成功', '1', 'admin', 'admin', '2020-08-13 06:26:39', '2020-08-13 06:26:39');
INSERT INTO `st_login_log` VALUES ('129', 'admin', '2020-08-13 06:29:50', '登录成功', '1', 'admin', 'admin', '2020-08-13 06:29:50', '2020-08-13 06:29:49');
INSERT INTO `st_login_log` VALUES ('130', 'admin', '2020-08-13 06:30:17', '登录成功', '1', 'admin', 'admin', '2020-08-13 06:30:17', '2020-08-13 06:30:17');
INSERT INTO `st_login_log` VALUES ('131', 'admin', '2020-08-13 06:38:34', '登录成功', '1', 'admin', 'admin', '2020-08-13 06:38:34', '2020-08-13 06:38:33');
INSERT INTO `st_login_log` VALUES ('132', 'admin', '2020-08-13 06:40:09', '登录成功', '1', 'admin', 'admin', '2020-08-13 06:40:09', '2020-08-13 06:40:09');
INSERT INTO `st_login_log` VALUES ('133', 'admin', '2020-08-13 06:44:01', '登录成功', '1', 'admin', 'admin', '2020-08-13 06:44:01', '2020-08-13 06:44:01');
INSERT INTO `st_login_log` VALUES ('134', 'admin', '2020-08-13 07:20:39', '登录成功', '1', 'admin', 'admin', '2020-08-13 07:20:39', '2020-08-13 07:20:38');
INSERT INTO `st_login_log` VALUES ('135', 'admin', '2020-08-13 07:50:20', '登录成功', '1', 'admin', 'admin', '2020-08-13 07:50:20', '2020-08-13 07:50:19');
INSERT INTO `st_login_log` VALUES ('136', 'admin', '2020-08-13 09:29:45', '登录成功', '1', 'admin', 'admin', '2020-08-13 09:29:45', '2020-08-13 09:29:45');
INSERT INTO `st_login_log` VALUES ('137', 'admin', '2020-08-13 09:33:16', '登录成功', '1', 'admin', 'admin', '2020-08-13 09:33:16', '2020-08-13 09:33:16');
INSERT INTO `st_login_log` VALUES ('138', 'admin', '2020-08-14 00:54:15', '登录成功', '1', 'admin', 'admin', '2020-08-14 00:54:15', '2020-08-14 00:54:14');
INSERT INTO `st_login_log` VALUES ('139', 'admin', '2020-08-14 03:22:25', '登录成功', '1', 'admin', 'admin', '2020-08-14 03:22:25', '2020-08-14 03:22:24');
INSERT INTO `st_login_log` VALUES ('140', 'admin', '2020-08-14 05:07:08', '登录成功', '1', 'admin', 'admin', '2020-08-14 05:07:08', '2020-08-14 05:07:07');
INSERT INTO `st_login_log` VALUES ('141', 'admin', '2020-08-14 06:26:42', '登录成功', '1', 'admin', 'admin', '2020-08-14 06:26:42', '2020-08-14 06:26:42');
INSERT INTO `st_login_log` VALUES ('142', 'admin', '2020-08-14 06:49:25', '登录成功', '1', 'admin', 'admin', '2020-08-14 06:49:25', '2020-08-14 06:49:24');
INSERT INTO `st_login_log` VALUES ('143', 'admin', '2020-08-14 07:33:51', '登录成功', '1', 'admin', 'admin', '2020-08-14 07:33:51', '2020-08-14 07:33:50');
INSERT INTO `st_login_log` VALUES ('144', 'admin', '2020-08-14 07:43:03', '登录成功', '1', 'admin', 'admin', '2020-08-14 07:43:03', '2020-08-14 07:43:02');
INSERT INTO `st_login_log` VALUES ('145', 'admin', '2020-08-14 08:31:01', '登录成功', '1', 'admin', 'admin', '2020-08-14 08:31:01', '2020-08-14 08:31:00');
INSERT INTO `st_login_log` VALUES ('146', 'admin', '2020-08-14 08:44:42', '登录成功', '1', 'admin', 'admin', '2020-08-14 08:44:42', '2020-08-14 08:44:42');
INSERT INTO `st_login_log` VALUES ('147', 'admin', '2020-08-17 01:28:27', '登录成功', '1', 'admin', 'admin', '2020-08-17 01:28:27', '2020-08-17 01:28:27');
INSERT INTO `st_login_log` VALUES ('148', 'admin', '2020-08-17 03:22:38', '登录成功', '1', 'admin', 'admin', '2020-08-17 03:22:38', '2020-08-17 03:22:38');
INSERT INTO `st_login_log` VALUES ('149', 'admin', '2020-08-17 03:42:12', '登录成功', '1', 'admin', 'admin', '2020-08-17 03:42:12', '2020-08-17 03:42:11');
INSERT INTO `st_login_log` VALUES ('150', 'admin', '2020-08-17 04:29:32', '登录成功', '1', 'admin', 'admin', '2020-08-17 04:29:32', '2020-08-17 04:29:31');
INSERT INTO `st_login_log` VALUES ('151', 'admin', '2020-08-17 06:14:18', '登录成功', '1', 'admin', 'admin', '2020-08-17 06:14:18', '2020-08-17 06:14:18');
INSERT INTO `st_login_log` VALUES ('152', 'admin', '2020-08-17 08:00:09', '登录成功', '1', 'admin', 'admin', '2020-08-17 08:00:09', '2020-08-17 08:00:09');
INSERT INTO `st_login_log` VALUES ('153', 'admin', '2020-08-17 08:14:20', '登录成功', '1', 'admin', 'admin', '2020-08-17 08:14:20', '2020-08-17 08:14:20');
INSERT INTO `st_login_log` VALUES ('154', 'admin', '2020-08-18 02:01:40', '登录成功', '1', 'admin', 'admin', '2020-08-18 02:01:40', '2020-08-18 02:01:39');
INSERT INTO `st_login_log` VALUES ('155', 'admin', '2020-08-18 02:58:34', '登录成功', '1', 'admin', 'admin', '2020-08-18 02:58:34', '2020-08-18 02:58:34');
INSERT INTO `st_login_log` VALUES ('156', 'admin', '2020-08-18 03:18:24', '登录成功', '1', 'admin', 'admin', '2020-08-18 03:18:24', '2020-08-18 03:18:24');
INSERT INTO `st_login_log` VALUES ('157', 'admin', '2020-08-18 06:30:04', '登录成功', '1', 'admin', 'admin', '2020-08-18 06:30:04', '2020-08-18 06:30:04');
INSERT INTO `st_login_log` VALUES ('158', 'admin', '2020-08-18 07:20:23', '登录成功', '1', 'admin', 'admin', '2020-08-18 07:20:23', '2020-08-18 07:20:22');
INSERT INTO `st_login_log` VALUES ('159', 'admin', '2020-08-18 08:03:22', '登录成功', '1', 'admin', 'admin', '2020-08-18 08:03:22', '2020-08-18 08:03:22');
INSERT INTO `st_login_log` VALUES ('160', 'admin', '2020-08-18 08:04:06', '登录成功', '1', 'admin', 'admin', '2020-08-18 08:04:06', '2020-08-18 08:04:06');
INSERT INTO `st_login_log` VALUES ('161', 'admin', '2020-08-18 09:31:30', '登录成功', '1', 'admin', 'admin', '2020-08-18 09:31:30', '2020-08-18 09:31:29');
INSERT INTO `st_login_log` VALUES ('162', 'admin', '2020-08-18 09:49:11', '登录成功', '1', 'admin', 'admin', '2020-08-18 09:49:11', '2020-08-18 09:49:10');
INSERT INTO `st_login_log` VALUES ('163', 'admin', '2020-08-19 00:49:20', '登录成功', '1', 'admin', 'admin', '2020-08-19 00:49:20', '2020-08-19 00:49:20');
INSERT INTO `st_login_log` VALUES ('164', 'admin', '2020-08-19 03:02:58', '登录成功', '1', 'admin', 'admin', '2020-08-19 03:02:58', '2020-08-19 03:02:58');
INSERT INTO `st_login_log` VALUES ('165', 'admin', '2020-08-19 03:03:27', '登录成功', '1', 'admin', 'admin', '2020-08-19 03:03:27', '2020-08-19 03:03:27');
INSERT INTO `st_login_log` VALUES ('166', 'admin', '2020-08-19 03:13:10', '登录成功', '1', 'admin', 'admin', '2020-08-19 03:13:10', '2020-08-19 03:13:09');
INSERT INTO `st_login_log` VALUES ('167', 'admin', '2020-08-19 03:19:15', '登录成功', '1', 'admin', 'admin', '2020-08-19 03:19:15', '2020-08-19 03:19:15');
INSERT INTO `st_login_log` VALUES ('168', 'admin', '2020-08-19 03:25:45', '登录成功', '1', 'admin', 'admin', '2020-08-19 03:25:45', '2020-08-19 03:25:45');
INSERT INTO `st_login_log` VALUES ('169', 'admin', '2020-08-19 03:35:56', '登录成功', '1', 'admin', 'admin', '2020-08-19 03:35:56', '2020-08-19 03:35:56');
INSERT INTO `st_login_log` VALUES ('170', 'admin', '2020-08-19 04:06:13', '登录成功', '1', 'admin', 'admin', '2020-08-19 04:06:13', '2020-08-19 04:06:12');
INSERT INTO `st_login_log` VALUES ('171', 'admin', '2020-08-19 06:10:51', '登录成功', '1', 'admin', 'admin', '2020-08-19 06:10:51', '2020-08-19 06:10:51');
INSERT INTO `st_login_log` VALUES ('172', 'admin', '2020-08-19 07:12:51', '登录成功', '1', 'admin', 'admin', '2020-08-19 07:12:51', '2020-08-19 07:12:51');
INSERT INTO `st_login_log` VALUES ('173', 'admin', '2020-08-19 08:16:11', '登录成功', '1', 'admin', 'admin', '2020-08-19 08:16:11', '2020-08-19 08:16:10');
INSERT INTO `st_login_log` VALUES ('174', 'admin', '2020-08-19 08:41:27', '登录成功', '1', 'admin', 'admin', '2020-08-19 08:41:27', '2020-08-19 08:41:27');
INSERT INTO `st_login_log` VALUES ('175', 'admin', '2020-08-19 08:43:46', '登录成功', '1', 'admin', 'admin', '2020-08-19 08:43:46', '2020-08-19 08:43:45');
INSERT INTO `st_login_log` VALUES ('176', 'admin', '2020-08-19 08:53:37', '登录成功', '1', 'admin', 'admin', '2020-08-19 08:53:37', '2020-08-19 08:53:37');
INSERT INTO `st_login_log` VALUES ('177', 'admin', '2020-08-21 02:55:30', '登录成功', '1', 'admin', 'admin', '2020-08-21 02:55:30', '2020-08-21 02:55:29');
INSERT INTO `st_login_log` VALUES ('178', 'admin', '2020-08-21 02:57:00', '登录成功', '1', 'admin', 'admin', '2020-08-21 02:57:00', '2020-08-21 02:57:00');
INSERT INTO `st_login_log` VALUES ('179', 'admin', '2020-08-21 02:57:22', '登录成功', '1', 'admin', 'admin', '2020-08-21 02:57:22', '2020-08-21 02:57:21');
INSERT INTO `st_login_log` VALUES ('180', 'admin', '2020-08-21 03:12:01', '登录成功', '1', 'admin', 'admin', '2020-08-21 03:12:01', '2020-08-21 03:12:01');
INSERT INTO `st_login_log` VALUES ('181', 'admin', '2020-08-21 05:48:13', '登录成功', '1', 'admin', 'admin', '2020-08-21 05:48:13', '2020-08-21 05:48:12');
INSERT INTO `st_login_log` VALUES ('182', 'admin', '2020-08-21 05:57:26', '登录成功', '1', 'admin', 'admin', '2020-08-21 05:57:26', '2020-08-21 05:57:25');
INSERT INTO `st_login_log` VALUES ('183', 'admin', '2020-08-21 05:58:48', '登录成功', '1', 'admin', 'admin', '2020-08-21 05:58:48', '2020-08-21 05:58:48');
INSERT INTO `st_login_log` VALUES ('184', 'admin', '2020-08-21 07:44:22', '登录成功', '1', 'admin', 'admin', '2020-08-21 07:44:22', '2020-08-21 07:44:22');
INSERT INTO `st_login_log` VALUES ('185', 'admin', '2020-08-21 08:00:38', '登录成功', '1', 'admin', 'admin', '2020-08-21 08:00:38', '2020-08-21 08:00:37');
INSERT INTO `st_login_log` VALUES ('186', 'admin', '2020-08-21 08:17:22', '登录成功', '1', 'admin', 'admin', '2020-08-21 08:17:22', '2020-08-21 08:17:21');
INSERT INTO `st_login_log` VALUES ('187', 'admin', '2020-08-21 08:41:46', '登录成功', '1', 'admin', 'admin', '2020-08-21 08:41:46', '2020-08-21 08:41:45');
INSERT INTO `st_login_log` VALUES ('188', 'admin', '2020-08-21 08:51:25', '登录成功', '1', 'admin', 'admin', '2020-08-21 08:51:25', '2020-08-21 08:51:25');
INSERT INTO `st_login_log` VALUES ('189', 'admin', '2020-08-21 08:52:14', '登录成功', '1', 'admin', 'admin', '2020-08-21 08:52:14', '2020-08-21 08:52:13');
INSERT INTO `st_login_log` VALUES ('190', 'admin', '2020-08-21 08:58:00', '登录成功', '1', 'admin', 'admin', '2020-08-21 08:58:00', '2020-08-21 08:57:59');
INSERT INTO `st_login_log` VALUES ('191', 'admin', '2020-08-21 09:06:38', '登录成功', '1', 'admin', 'admin', '2020-08-21 09:06:38', '2020-08-21 09:06:38');
INSERT INTO `st_login_log` VALUES ('192', 'admin', '2020-08-21 09:07:35', '登录成功', '1', 'admin', 'admin', '2020-08-21 09:07:35', '2020-08-21 09:07:34');
INSERT INTO `st_login_log` VALUES ('193', 'admin', '2020-08-21 09:10:21', '登录成功', '1', 'admin', 'admin', '2020-08-21 09:10:21', '2020-08-21 09:10:20');
INSERT INTO `st_login_log` VALUES ('194', 'admin', '2020-08-21 09:14:01', '登录成功', '1', 'admin', 'admin', '2020-08-21 09:14:01', '2020-08-21 09:14:01');
INSERT INTO `st_login_log` VALUES ('195', 'admin', '2020-08-21 09:16:49', '登录成功', '1', 'admin', 'admin', '2020-08-21 09:16:49', '2020-08-21 09:16:48');
INSERT INTO `st_login_log` VALUES ('196', 'admin', '2020-08-24 02:16:28', '登录成功', '1', 'admin', 'admin', '2020-08-24 02:16:28', '2020-08-24 02:16:27');
INSERT INTO `st_login_log` VALUES ('197', 'admin', '2020-08-24 03:13:11', '登录成功', '1', 'admin', 'admin', '2020-08-24 03:13:11', '2020-08-24 03:13:11');
INSERT INTO `st_login_log` VALUES ('198', 'admin', '2020-08-24 03:35:21', '登录成功', '1', 'admin', 'admin', '2020-08-24 03:35:21', '2020-08-24 03:35:21');
INSERT INTO `st_login_log` VALUES ('199', 'admin', '2020-08-24 03:37:40', '登录成功', '1', 'admin', 'admin', '2020-08-24 03:37:40', '2020-08-24 03:37:39');
INSERT INTO `st_login_log` VALUES ('200', 'admin', '2020-08-24 03:45:22', '登录成功', '1', 'admin', 'admin', '2020-08-24 03:45:22', '2020-08-24 03:45:22');
INSERT INTO `st_login_log` VALUES ('201', 'admin', '2020-08-24 06:44:03', '登录成功', '1', 'admin', 'admin', '2020-08-24 06:44:03', '2020-08-24 06:44:03');
INSERT INTO `st_login_log` VALUES ('202', 'admin', '2020-08-24 08:18:20', '登录成功', '1', 'admin', 'admin', '2020-08-24 08:18:20', '2020-08-24 08:18:19');
INSERT INTO `st_login_log` VALUES ('203', 'admin', '2020-08-24 08:23:48', '登录成功', '1', 'admin', 'admin', '2020-08-24 08:23:48', '2020-08-24 08:23:48');
INSERT INTO `st_login_log` VALUES ('204', 'admin', '2020-08-24 08:24:18', '登录成功', '1', 'admin', 'admin', '2020-08-24 08:24:18', '2020-08-24 08:24:18');
INSERT INTO `st_login_log` VALUES ('205', 'admin', '2020-08-25 08:12:57', '登录成功', '1', 'admin', 'admin', '2020-08-25 08:12:57', '2020-08-25 08:12:57');
INSERT INTO `st_login_log` VALUES ('206', 'admin', '2020-08-26 06:19:00', '登录成功', '1', 'admin', 'admin', '2020-08-26 06:19:00', '2020-08-26 06:19:00');
INSERT INTO `st_login_log` VALUES ('207', 'admin', '2020-08-26 06:30:49', '登录成功', '1', 'admin', 'admin', '2020-08-26 06:30:49', '2020-08-26 06:30:48');
INSERT INTO `st_login_log` VALUES ('208', 'admin', '2020-08-26 06:34:10', '登录成功', '1', 'admin', 'admin', '2020-08-26 06:34:10', '2020-08-26 06:34:09');
INSERT INTO `st_login_log` VALUES ('209', 'admin', '2020-08-26 06:37:18', '登录成功', '1', 'admin', 'admin', '2020-08-26 06:37:18', '2020-08-26 06:37:18');
INSERT INTO `st_login_log` VALUES ('210', 'admin', '2020-08-26 06:37:35', '登录成功', '1', 'admin', 'admin', '2020-08-26 06:37:35', '2020-08-26 06:37:35');
INSERT INTO `st_login_log` VALUES ('211', 'admin', '2020-08-26 06:42:56', '登录成功', '1', 'admin', 'admin', '2020-08-26 06:42:56', '2020-08-26 06:42:55');
INSERT INTO `st_login_log` VALUES ('212', 'admin', '2020-08-26 06:43:11', '登录成功', '1', 'admin', 'admin', '2020-08-26 06:43:11', '2020-08-26 06:43:10');
INSERT INTO `st_login_log` VALUES ('213', 'admin', '2020-08-26 06:43:31', '登录成功', '1', 'admin', 'admin', '2020-08-26 06:43:31', '2020-08-26 06:43:31');
INSERT INTO `st_login_log` VALUES ('214', 'admin', '2020-08-26 08:08:40', '登录成功', '1', 'admin', 'admin', '2020-08-26 08:08:40', '2020-08-26 08:08:39');
INSERT INTO `st_login_log` VALUES ('215', 'admin', '2020-08-26 08:10:16', '登录成功', '1', 'admin', 'admin', '2020-08-26 08:10:16', '2020-08-26 08:10:15');
INSERT INTO `st_login_log` VALUES ('216', 'admin', '2020-08-26 08:18:01', '登录成功', '1', 'admin', 'admin', '2020-08-26 08:18:01', '2020-08-26 08:18:01');
INSERT INTO `st_login_log` VALUES ('217', 'admin', '2020-08-26 08:29:45', '登录成功', '1', 'admin', 'admin', '2020-08-26 08:29:45', '2020-08-26 08:29:45');
INSERT INTO `st_login_log` VALUES ('218', 'admin', '2020-08-26 09:12:07', '登录成功', '1', 'admin', 'admin', '2020-08-26 09:12:07', '2020-08-26 09:12:07');
INSERT INTO `st_login_log` VALUES ('219', 'admin', '2020-08-26 09:14:10', '登录成功', '1', 'admin', 'admin', '2020-08-26 09:14:10', '2020-08-26 09:14:09');
INSERT INTO `st_login_log` VALUES ('220', 'admin', '2020-08-26 09:23:05', '登录成功', '1', 'admin', 'admin', '2020-08-26 09:23:05', '2020-08-26 09:23:04');
INSERT INTO `st_login_log` VALUES ('221', 'admin', '2020-08-26 09:41:36', '登录成功', '1', 'admin', 'admin', '2020-08-26 09:41:36', '2020-08-26 09:41:35');
INSERT INTO `st_login_log` VALUES ('222', 'admin', '2020-08-26 09:45:23', '登录成功', '1', 'admin', 'admin', '2020-08-26 09:45:23', '2020-08-26 09:45:22');
INSERT INTO `st_login_log` VALUES ('223', 'admin', '2020-08-27 02:37:41', '登录成功', '1', 'admin', 'admin', '2020-08-27 02:37:41', '2020-08-27 02:37:40');
INSERT INTO `st_login_log` VALUES ('224', 'admin', '2020-08-27 02:43:41', '登录成功', '1', 'admin', 'admin', '2020-08-27 02:43:41', '2020-08-27 02:43:41');
INSERT INTO `st_login_log` VALUES ('225', 'admin', '2020-08-27 02:43:49', '登录成功', '1', 'admin', 'admin', '2020-08-27 02:43:49', '2020-08-27 02:43:48');
INSERT INTO `st_login_log` VALUES ('226', 'admin', '2020-08-27 03:00:25', '登录成功', '1', 'admin', 'admin', '2020-08-27 03:00:25', '2020-08-27 03:00:25');
INSERT INTO `st_login_log` VALUES ('227', 'admin', '2020-08-27 03:07:10', '登录成功', '1', 'admin', 'admin', '2020-08-27 03:07:10', '2020-08-27 03:07:09');
INSERT INTO `st_login_log` VALUES ('228', 'admin', '2020-08-27 03:43:24', '登录成功', '1', 'admin', 'admin', '2020-08-27 03:43:24', '2020-08-27 03:43:24');
INSERT INTO `st_login_log` VALUES ('229', 'admin', '2020-08-27 04:14:12', '登录成功', '1', 'admin', 'admin', '2020-08-27 04:14:12', '2020-08-27 04:14:12');
INSERT INTO `st_login_log` VALUES ('230', 'admin', '2020-08-27 04:15:58', '登录成功', '1', 'admin', 'admin', '2020-08-27 04:15:58', '2020-08-27 04:15:58');
INSERT INTO `st_login_log` VALUES ('231', 'admin', '2020-08-27 04:21:54', '登录成功', '1', 'admin', 'admin', '2020-08-27 04:21:54', '2020-08-27 04:21:54');
INSERT INTO `st_login_log` VALUES ('232', 'admin', '2020-08-27 04:29:07', '登录成功', '1', 'admin', 'admin', '2020-08-27 04:29:07', '2020-08-27 04:29:07');
INSERT INTO `st_login_log` VALUES ('233', 'admin', '2020-08-27 04:29:17', '登录成功', '1', 'admin', 'admin', '2020-08-27 04:29:17', '2020-08-27 04:29:17');
INSERT INTO `st_login_log` VALUES ('234', 'admin', '2020-08-27 05:54:16', '登录成功', '1', 'admin', 'admin', '2020-08-27 05:54:16', '2020-08-27 05:54:16');
INSERT INTO `st_login_log` VALUES ('235', 'admin', '2020-08-27 05:54:56', '登录成功', '1', 'admin', 'admin', '2020-08-27 05:54:56', '2020-08-27 05:54:55');
INSERT INTO `st_login_log` VALUES ('236', 'admin', '2020-08-27 05:55:08', '登录成功', '1', 'admin', 'admin', '2020-08-27 05:55:08', '2020-08-27 05:55:08');
INSERT INTO `st_login_log` VALUES ('237', 'admin', '2020-08-27 05:56:22', '登录成功', '1', 'admin', 'admin', '2020-08-27 05:56:22', '2020-08-27 05:56:22');
INSERT INTO `st_login_log` VALUES ('238', 'admin', '2020-08-27 06:11:09', '登录成功', '1', 'admin', 'admin', '2020-08-27 06:11:09', '2020-08-27 06:11:09');
INSERT INTO `st_login_log` VALUES ('239', 'admin', '2020-08-27 06:37:14', '登录成功', '1', 'admin', 'admin', '2020-08-27 06:37:14', '2020-08-27 06:37:14');
INSERT INTO `st_login_log` VALUES ('240', 'admin', '2020-08-27 07:51:44', '登录成功', '1', 'admin', 'admin', '2020-08-27 07:51:44', '2020-08-27 07:51:44');
INSERT INTO `st_login_log` VALUES ('241', 'admin', '2020-08-27 08:00:51', '登录成功', '1', 'admin', 'admin', '2020-08-27 08:00:51', '2020-08-27 08:00:51');
INSERT INTO `st_login_log` VALUES ('242', 'admin', '2020-08-27 08:01:14', '登录成功', '1', 'admin', 'admin', '2020-08-27 08:01:14', '2020-08-27 08:01:13');
INSERT INTO `st_login_log` VALUES ('243', 'admin', '2020-08-27 08:12:49', '登录成功', '1', 'admin', 'admin', '2020-08-27 08:12:49', '2020-08-27 08:12:48');
INSERT INTO `st_login_log` VALUES ('244', 'admin', '2020-08-27 08:13:05', '登录成功', '0', 'admin', 'admin', '2020-08-27 08:13:05', '2020-09-15 03:23:43');
INSERT INTO `st_login_log` VALUES ('245', 'admin', '2020-08-27 08:18:28', '登录成功', '0', 'admin', 'admin', '2020-08-27 08:18:28', '2020-09-15 03:23:43');
INSERT INTO `st_login_log` VALUES ('246', 'admin', '2020-08-27 08:18:36', '登录成功', '0', 'admin', 'admin', '2020-08-27 08:18:36', '2020-09-15 03:23:43');
INSERT INTO `st_login_log` VALUES ('247', 'admin', '2020-08-28 06:38:58', '登录成功', '0', 'admin', 'admin', '2020-08-28 06:38:58', '2020-09-15 03:23:43');
INSERT INTO `st_login_log` VALUES ('248', 'admin', '2020-08-28 07:58:09', '登录成功', '0', 'admin', 'admin', '2020-08-28 07:58:09', '2020-09-15 03:23:43');
INSERT INTO `st_login_log` VALUES ('249', 'admin', '2020-08-31 08:23:09', '登录成功', '0', 'admin', 'admin', '2020-08-31 08:23:09', '2020-09-15 03:23:43');
INSERT INTO `st_login_log` VALUES ('250', 'admin', '2020-08-31 08:52:39', '登录成功', '0', 'admin', 'admin', '2020-08-31 08:52:39', '2020-09-15 03:23:43');
INSERT INTO `st_login_log` VALUES ('251', 'admin', '2020-08-31 09:29:06', '登录成功', '0', 'admin', 'admin', '2020-08-31 09:29:06', '2020-09-15 03:23:43');
INSERT INTO `st_login_log` VALUES ('252', 'admin', '2020-09-09 03:06:10', '登录成功', '0', 'admin', 'admin', '2020-09-09 03:06:10', '2020-09-15 03:23:43');
INSERT INTO `st_login_log` VALUES ('253', 'admin', '2020-09-09 08:36:47', '登录成功', '0', 'admin', 'admin', '2020-09-09 08:36:47', '2020-09-15 03:23:43');
INSERT INTO `st_login_log` VALUES ('254', 'admin', '2020-09-10 01:20:47', '登录成功', '1', 'admin', 'admin', '2020-09-10 01:20:47', '2020-09-10 01:20:46');
INSERT INTO `st_login_log` VALUES ('255', 'admin', '2020-09-10 01:39:44', '登录成功', '1', 'admin', 'admin', '2020-09-10 01:39:44', '2020-09-10 01:39:44');
INSERT INTO `st_login_log` VALUES ('256', 'admin', '2020-09-10 02:50:20', '登录成功', '1', 'admin', 'admin', '2020-09-10 02:50:20', '2020-09-10 02:50:19');
INSERT INTO `st_login_log` VALUES ('257', 'admin', '2020-09-10 03:42:02', '登录成功', '1', 'admin', 'admin', '2020-09-10 03:42:02', '2020-09-10 03:42:01');
INSERT INTO `st_login_log` VALUES ('258', 'admin', '2020-09-10 06:14:30', '登录成功', '1', 'admin', 'admin', '2020-09-10 06:14:30', '2020-09-10 06:14:30');
INSERT INTO `st_login_log` VALUES ('259', 'admin', '2020-09-10 07:52:19', '登录成功', '1', 'admin', 'admin', '2020-09-10 07:52:19', '2020-09-10 07:52:18');
INSERT INTO `st_login_log` VALUES ('260', 'admin', '2020-09-10 08:47:38', '登录成功', '1', 'admin', 'admin', '2020-09-10 08:47:38', '2020-09-10 08:47:38');
INSERT INTO `st_login_log` VALUES ('261', 'admin', '2020-09-11 03:37:23', '登录成功', '1', 'admin', 'admin', '2020-09-11 03:37:23', '2020-09-11 03:37:22');
INSERT INTO `st_login_log` VALUES ('262', 'admin', '2020-09-11 06:26:21', '登录成功', '1', 'admin', 'admin', '2020-09-11 06:26:21', '2020-09-11 06:26:20');
INSERT INTO `st_login_log` VALUES ('263', 'admin', '2020-09-11 06:29:04', '登录成功', '1', 'admin', 'admin', '2020-09-11 06:29:04', '2020-09-11 06:29:04');
INSERT INTO `st_login_log` VALUES ('264', 'admin', '2020-09-11 07:33:51', '登录成功', '1', 'admin', 'admin', '2020-09-11 07:33:51', '2020-09-11 07:33:50');
INSERT INTO `st_login_log` VALUES ('265', 'admin', '2020-09-14 04:01:20', '登录成功', '1', 'admin', 'admin', '2020-09-14 04:01:20', '2020-09-14 04:01:19');
INSERT INTO `st_login_log` VALUES ('266', 'admin', '2020-09-14 04:16:52', '登录成功', '1', 'admin', 'admin', '2020-09-14 04:16:52', '2020-09-14 04:16:52');
INSERT INTO `st_login_log` VALUES ('267', 'admin', '2020-09-14 04:38:26', '登录成功', '1', 'admin', 'admin', '2020-09-14 04:38:26', '2020-09-14 04:38:26');
INSERT INTO `st_login_log` VALUES ('268', 'admin', '2020-09-14 06:16:31', '登录成功', '1', 'admin', 'admin', '2020-09-14 06:16:31', '2020-09-14 06:16:31');
INSERT INTO `st_login_log` VALUES ('269', 'admin', '2020-09-14 06:21:30', '登录成功', '1', 'admin', 'admin', '2020-09-14 06:21:30', '2020-09-14 06:21:30');
INSERT INTO `st_login_log` VALUES ('270', 'admin', '2020-09-14 07:19:16', '登录成功', '1', 'admin', 'admin', '2020-09-14 07:19:16', '2020-09-14 07:19:15');
INSERT INTO `st_login_log` VALUES ('271', 'admin', '2020-09-14 08:05:17', '登录成功', '1', 'admin', 'admin', '2020-09-14 08:05:17', '2020-09-14 08:05:17');
INSERT INTO `st_login_log` VALUES ('272', 'admin', '2020-09-14 09:29:57', '登录成功', '1', 'admin', 'admin', '2020-09-14 09:29:57', '2020-09-14 09:29:56');
INSERT INTO `st_login_log` VALUES ('273', 'admin', '2020-09-15 00:57:52', '登录成功', '1', 'admin', 'admin', '2020-09-15 00:57:52', '2020-09-15 00:57:51');
INSERT INTO `st_login_log` VALUES ('274', 'admin', '2020-09-15 01:09:22', '登录成功', '0', 'admin', 'admin', '2020-09-15 01:09:22', '2020-09-15 03:21:58');
INSERT INTO `st_login_log` VALUES ('275', 'admin', '2020-09-15 02:20:52', '登录成功', '0', 'admin', 'admin', '2020-09-15 02:20:52', '2020-09-15 03:21:58');
INSERT INTO `st_login_log` VALUES ('276', 'admin', '2020-09-15 03:01:46', '登录成功', '0', 'admin', 'admin', '2020-09-15 03:01:46', '2020-09-15 03:21:58');
INSERT INTO `st_login_log` VALUES ('277', 'admin', '2020-09-15 07:06:21', '登录成功', '1', 'admin', 'admin', '2020-09-15 07:06:21', '2020-09-15 07:06:20');
INSERT INTO `st_login_log` VALUES ('278', 'admin', '2020-09-21 07:26:12', '登录成功', '1', 'admin', 'admin', '2020-09-21 07:26:12', '2020-09-21 07:26:12');
INSERT INTO `st_login_log` VALUES ('279', 'admin', '2020-09-21 07:44:15', '登录成功', '1', 'admin', 'admin', '2020-09-21 07:44:15', '2020-09-21 07:44:14');
INSERT INTO `st_login_log` VALUES ('280', 'admin', '2020-09-23 08:44:56', '登录成功', '1', 'admin', 'admin', '2020-09-23 08:44:56', '2020-09-23 08:44:56');
INSERT INTO `st_login_log` VALUES ('281', 'admin', '2020-09-27 01:21:14', '登录成功', '1', 'admin', 'admin', '2020-09-27 01:21:14', '2020-09-27 01:21:13');
INSERT INTO `st_login_log` VALUES ('282', 'admin', '2020-10-09 02:43:29', '登录成功', '1', 'admin', 'admin', '2020-10-09 02:43:29', '2020-10-09 02:43:29');
INSERT INTO `st_login_log` VALUES ('283', 'admin', '2020-10-09 03:49:28', '登录成功', '1', 'admin', 'admin', '2020-10-09 03:49:28', '2020-10-09 03:49:28');
INSERT INTO `st_login_log` VALUES ('284', 'admin', '2020-10-10 09:48:11', '登录成功', '1', 'admin', 'admin', '2020-10-10 09:48:11', '2020-10-10 09:48:11');
INSERT INTO `st_login_log` VALUES ('285', 'admin', '2020-10-13 01:36:29', '登录成功', '1', 'admin', 'admin', '2020-10-13 01:36:29', '2020-10-13 01:36:28');
INSERT INTO `st_login_log` VALUES ('286', 'admin', '2020-10-13 03:02:47', '登录成功', '1', 'admin', 'admin', '2020-10-13 03:02:47', '2020-10-13 03:02:47');
INSERT INTO `st_login_log` VALUES ('287', 'admin', '2020-10-13 03:31:35', '登录成功', '1', 'admin', 'admin', '2020-10-13 03:31:35', '2020-10-13 03:31:35');
INSERT INTO `st_login_log` VALUES ('288', 'admin', '2020-10-13 07:08:46', '登录成功', '1', 'admin', 'admin', '2020-10-13 07:08:46', '2020-10-13 07:08:46');
INSERT INTO `st_login_log` VALUES ('289', 'admin', '2020-10-13 07:17:26', '登录成功', '1', 'admin', 'admin', '2020-10-13 07:17:26', '2020-10-13 07:17:25');
INSERT INTO `st_login_log` VALUES ('290', 'admin', '2020-10-13 07:33:23', '登录成功', '1', 'admin', 'admin', '2020-10-13 07:33:23', '2020-10-13 07:33:23');
INSERT INTO `st_login_log` VALUES ('291', 'admin', '2020-10-13 08:58:39', '登录成功', '1', 'admin', 'admin', '2020-10-13 08:58:39', '2020-10-13 08:58:39');
INSERT INTO `st_login_log` VALUES ('292', 'admin', '2020-10-14 01:59:30', '登录成功', '1', 'admin', 'admin', '2020-10-14 01:59:30', '2020-10-14 01:59:30');
INSERT INTO `st_login_log` VALUES ('293', 'admin', '2020-10-14 03:37:12', '登录成功', '1', 'admin', 'admin', '2020-10-14 03:37:12', '2020-10-14 03:37:11');
INSERT INTO `st_login_log` VALUES ('294', 'admin', '2020-10-19 08:05:42', '登录成功', '1', 'admin', 'admin', '2020-10-19 08:05:42', '2020-10-19 08:05:41');
INSERT INTO `st_login_log` VALUES ('295', 'admin', '2020-10-19 08:32:04', '登录成功', '1', 'admin', 'admin', '2020-10-19 08:32:04', '2020-10-19 08:32:04');
INSERT INTO `st_login_log` VALUES ('296', 'admin', '2020-10-19 09:33:46', '登录成功', '1', 'admin', 'admin', '2020-10-19 09:33:46', '2020-10-19 09:33:46');
INSERT INTO `st_login_log` VALUES ('297', 'admin', '2020-10-20 01:03:32', '登录成功', '1', 'admin', 'admin', '2020-10-20 01:03:32', '2020-10-20 01:03:31');
INSERT INTO `st_login_log` VALUES ('298', 'admin', '2020-10-20 02:16:40', '登录成功', '1', 'admin', 'admin', '2020-10-20 02:16:40', '2020-10-20 02:16:39');
INSERT INTO `st_login_log` VALUES ('299', 'admin', '2020-10-20 03:18:42', '登录成功', '1', 'admin', 'admin', '2020-10-20 03:18:42', '2020-10-20 03:18:42');
INSERT INTO `st_login_log` VALUES ('300', 'admin', '2020-10-20 03:22:33', '登录成功', '1', 'admin', 'admin', '2020-10-20 03:22:33', '2020-10-20 03:22:33');
INSERT INTO `st_login_log` VALUES ('301', 'admin', '2020-10-20 03:22:47', '登录成功', '1', 'admin', 'admin', '2020-10-20 03:22:47', '2020-10-20 03:22:47');
INSERT INTO `st_login_log` VALUES ('302', 'admin', '2020-10-20 03:24:40', '登录成功', '1', 'admin', 'admin', '2020-10-20 03:24:40', '2020-10-20 03:24:39');
INSERT INTO `st_login_log` VALUES ('303', 'admin', '2020-10-20 03:26:45', '登录成功', '1', 'admin', 'admin', '2020-10-20 03:26:45', '2020-10-20 03:26:44');
INSERT INTO `st_login_log` VALUES ('304', 'admin', '2020-10-20 03:28:16', '登录成功', '1', 'admin', 'admin', '2020-10-20 03:28:16', '2020-10-20 03:28:16');
INSERT INTO `st_login_log` VALUES ('305', 'admin', '2020-10-20 03:33:32', '登录成功', '1', 'admin', 'admin', '2020-10-20 03:33:32', '2020-10-20 03:33:31');
INSERT INTO `st_login_log` VALUES ('306', 'admin', '2020-10-20 03:33:45', '登录成功', '1', 'admin', 'admin', '2020-10-20 03:33:45', '2020-10-20 03:33:45');

-- ----------------------------
-- Table structure for `st_resource`
-- ----------------------------
DROP TABLE IF EXISTS `st_resource`;
CREATE TABLE `st_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '菜单名称',
  `full_id` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '菜单编号路径',
  `icon_class` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '图标样式类',
  `show_order` int(11) DEFAULT NULL COMMENT '排序',
  `url` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '链接',
  `component` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '页面路径',
  `authority_id` bigint(20) DEFAULT NULL COMMENT '权限ID',
  `pid` bigint(20) DEFAULT NULL COMMENT '父id',
  `resource_desc` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '菜单描述',
  `yn_flag` char(2) COLLATE utf8_bin DEFAULT NULL COMMENT '有效标志',
  `creator` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `editor` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='菜单表';

-- ----------------------------
-- Records of st_resource
-- ----------------------------
INSERT INTO `st_resource` VALUES ('1', '系统管理', '0', 'nested', '30', '/sysmgr', '/layout/Layout', '1', '0', null, '1', 'admin', 'admin', '2019-07-12 09:20:40', '2019-09-25 07:25:47');
INSERT INTO `st_resource` VALUES ('2', '用户管理', '0-1', 'user', '1', 'user', '/sysmgr/user/index', '7', '1', null, '1', 'admin', 'admin', '2019-07-12 09:20:40', '2019-08-19 11:04:38');
INSERT INTO `st_resource` VALUES ('3', '角色管理', '0-1', 'peoples', '3', 'role', '/sysmgr/role/index', '10', '1', null, '1', 'admin', 'admin', '2019-07-12 09:20:40', '2020-08-14 08:43:05');
INSERT INTO `st_resource` VALUES ('4', '菜单管理', '0-1', 'list', '4', 'menu', '/sysmgr/menu/index', '13', '1', null, '1', 'admin', 'admin', '2019-07-12 09:20:40', '2019-08-19 11:04:42');
INSERT INTO `st_resource` VALUES ('5', '权限管理', '0-1', 'password', '5', 'authority', '/sysmgr/authority/index', '16', '1', null, '1', 'admin', 'admin', '2019-07-12 09:20:40', '2019-08-19 11:04:43');
INSERT INTO `st_resource` VALUES ('6', '基础信息', '0', 'nested', '20', '/baseinfo', '/layout/Layout', '1', '0', null, '1', 'admin', 'admin', '2019-07-12 09:20:40', '2019-09-25 07:25:42');
INSERT INTO `st_resource` VALUES ('7', '字典管理', '0-6', 'component', '1', 'dict', '/baseinfo/dict', '1', '6', null, '1', 'admin', 'admin', '2019-07-12 09:54:30', '2019-07-16 09:17:55');
INSERT INTO `st_resource` VALUES ('8', '附件管理', '0-1', 'zip', '7', 'att', '/sysmgr/att/index', '39', '1', null, '1', 'admin', 'admin', '2019-07-12 10:25:37', '2019-09-25 06:18:31');
INSERT INTO `st_resource` VALUES ('9', '登陆日志', '0-1', 'people', '6', 'loginlog', '/sysmgr/loginlog/index', '31', '1', null, '0', 'admin', 'admin', '2019-07-12 10:35:56', '2020-08-21 08:51:59');
INSERT INTO `st_resource` VALUES ('10', '系统备份', '0-1', 'clipboard', '9', 'backup', '/sysmgr/backup/index', '44', '1', null, '1', 'admin', 'admin', '2019-07-12 10:49:11', '2019-09-25 06:18:49');
INSERT INTO `st_resource` VALUES ('11', '系统日志', '0-1', 'documentation', '8', 'syslog', '/sysmgr/syslog/index', '42', '1', null, '0', 'admin', 'admin', '2019-07-12 11:58:59', '2020-08-21 08:52:03');
INSERT INTO `st_resource` VALUES ('12', '定时任务', '0-1', 'guide', '20', 'schedulejob', '/sysmgr/schedulejob/index', '47', '1', null, '0', 'admin', 'admin', '2019-08-19 03:02:50', '2020-08-21 08:41:03');
INSERT INTO `st_resource` VALUES ('13', '个人空间', '0', 'nested', '10', '/tools', '/layout/Layout', '1', '0', '', '1', 'admin', 'admin', '2019-09-25 06:28:53', '2019-09-25 14:30:48');
INSERT INTO `st_resource` VALUES ('14', '待办事项', '0-13', 'table', null, 'todolist', '/tools/todolist', '53', '13', null, '1', 'admin', 'admin', '2019-09-25 07:09:20', '2019-09-25 07:17:58');
INSERT INTO `st_resource` VALUES ('15', '系统公告', '0-1', 'user', '30', 'InformManagement', '/sysmgr/inform/index', '57', '1', null, '1', 'admin', 'admin', '2020-08-11 08:57:20', '2020-08-11 08:57:20');
INSERT INTO `st_resource` VALUES ('16', '部门管理', '0-1', 'peoples', '10', 'dept', '/sysmgr/dept/index', '69', '1', null, '1', 'admin', 'admin', '2020-08-14 08:44:03', '2020-08-14 08:44:17');
INSERT INTO `st_resource` VALUES ('17', '服务监控', '0', 'nested', '40', '/monitor', '/layout/Layout', '1', '0', null, '1', 'admin', 'admin', '2020-08-21 07:52:18', '2020-08-24 03:45:11');
INSERT INTO `st_resource` VALUES ('18', '系统监控', '0', 'nested', '40', '/monitor/server', '/layout/Layout', '1', '0', null, '0', 'admin', 'admin', '2020-08-21 07:52:19', '2020-08-21 07:52:32');
INSERT INTO `st_resource` VALUES ('19', '系统监控', '0-17', 'documentation', '1', 'server', '/monitor/server/index', '74', '17', null, '1', 'admin', 'admin', '2020-08-21 07:59:49', '2020-08-24 03:33:36');
INSERT INTO `st_resource` VALUES ('20', '定时任务', '0-17', 'guide', '2', 'schedulejob', '/monitor/schedulejob/index', '76', '17', null, '1', 'admin', 'admin', '2020-08-21 08:23:27', '2020-08-24 03:45:07');
INSERT INTO `st_resource` VALUES ('21', '日志管理', '0-1', 'documentation', '31', 'log', '/layout/Layout', '1', '1', null, '1', 'admin', 'admin', '2020-08-21 08:47:00', '2020-08-21 09:24:34');
INSERT INTO `st_resource` VALUES ('22', '登录日志', '0-1-21', 'people', '1', 'loginlog', '/sysmgr/log/loginlog/index', '31', '21', null, '1', 'admin', 'admin', '2020-08-21 08:48:01', '2020-08-21 09:17:19');
INSERT INTO `st_resource` VALUES ('23', '操作日志', '0-1-21', 'documentation', '2', 'syslog', '/sysmgr/log/syslog/index', '42', '21', null, '1', 'admin', 'admin', '2020-08-21 08:48:56', '2020-08-21 09:16:01');
INSERT INTO `st_resource` VALUES ('24', '系统工具', '0', 'nested', '41', '/tool', '/layout/Layout', '1', '0', null, '1', 'admin', 'admin', '2020-08-24 03:30:35', '2020-08-24 03:43:31');
INSERT INTO `st_resource` VALUES ('25', 'Swagger_API接口文档', '0-24', 'documentation', '1', 'swagger', '/tool/swagger/index', '83', '24', null, '1', 'admin', 'admin', '2020-08-24 03:33:54', '2020-08-24 03:34:31');
INSERT INTO `st_resource` VALUES ('26', '数据监控', '0-17', 'documentation', '3', 'druid', '/monitor/druid/index', '90', '17', null, '1', 'admin', 'admin', '2020-08-24 06:42:42', '2020-08-24 06:46:14');

-- ----------------------------
-- Table structure for `st_role`
-- ----------------------------
DROP TABLE IF EXISTS `st_role`;
CREATE TABLE `st_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '角色名称',
  `role_desc` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '角色描述',
  `yn_flag` char(2) COLLATE utf8_bin DEFAULT NULL COMMENT '有效标志',
  `creator` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `editor` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='角色表';

-- ----------------------------
-- Records of st_role
-- ----------------------------
INSERT INTO `st_role` VALUES ('1', '管理员', '系统管理员', '0', null, 'admin', '2018-12-29 11:23:15', '2020-10-20 01:55:10');
INSERT INTO `st_role` VALUES ('3', '供应商', '供应商大大', '1', null, null, null, '2020-08-17 04:27:53');
INSERT INTO `st_role` VALUES ('4', '游客', '游客大大', '1', null, null, null, '2020-08-17 04:28:07');
INSERT INTO `st_role` VALUES ('5', '管理员', null, '0', 'admin', 'admin', '2020-10-20 01:55:29', '2020-10-20 02:52:33');
INSERT INTO `st_role` VALUES ('6', 'admin', null, '1', 'admin', 'admin', '2020-10-20 02:52:55', '2020-10-20 02:52:55');

-- ----------------------------
-- Table structure for `st_role_authority`
-- ----------------------------
DROP TABLE IF EXISTS `st_role_authority`;
CREATE TABLE `st_role_authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `authority_id` bigint(20) NOT NULL COMMENT '权限ID',
  `yn_flag` char(2) COLLATE utf8_bin DEFAULT NULL COMMENT '有效标志',
  `creator` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `editor` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1228 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='角色权限关系表';

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
INSERT INTO `st_role_authority` VALUES ('1018', '1', '0', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1019', '1', '1', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1020', '1', '2', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1021', '1', '3', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1022', '1', '4', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1023', '1', '5', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1024', '1', '6', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1025', '1', '7', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1026', '1', '8', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1027', '1', '9', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1028', '1', '10', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1029', '1', '11', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1030', '1', '12', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1031', '1', '13', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1032', '1', '14', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1033', '1', '15', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1034', '1', '16', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1035', '1', '17', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1036', '1', '18', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1037', '1', '30', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1038', '1', '31', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1039', '1', '32', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1040', '1', '33', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1041', '1', '34', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1042', '1', '35', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1043', '1', '36', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1044', '1', '37', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1045', '1', '38', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1046', '1', '39', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1047', '1', '40', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1048', '1', '41', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1049', '1', '42', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1050', '1', '43', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1051', '1', '44', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1052', '1', '45', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1053', '1', '50', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1054', '1', '51', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1055', '1', '52', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1056', '1', '53', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1057', '1', '54', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1058', '1', '55', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1059', '1', '56', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1060', '1', '57', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1061', '1', '58', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1062', '1', '59', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1063', '1', '60', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1064', '1', '61', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1065', '1', '62', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1066', '1', '63', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1067', '1', '64', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1068', '1', '65', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1069', '1', '66', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1070', '1', '67', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1071', '1', '68', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1072', '1', '69', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1073', '1', '70', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1074', '1', '71', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1075', '1', '72', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1076', '1', '73', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1077', '1', '74', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1078', '1', '75', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1079', '1', '76', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1080', '1', '77', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1081', '1', '78', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1082', '1', '81', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1083', '1', '82', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1084', '1', '83', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1085', '1', '84', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1086', '1', '90', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
INSERT INTO `st_role_authority` VALUES ('1087', '1', '91', '1', 'admin', 'admin', '2020-09-11 03:36:43', '2020-09-11 03:36:43');
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
INSERT INTO `st_role_authority` VALUES ('1158', '6', '0', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1159', '6', '1', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1160', '6', '2', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1161', '6', '3', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1162', '6', '4', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1163', '6', '5', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1164', '6', '6', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1165', '6', '7', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1166', '6', '8', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1167', '6', '9', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1168', '6', '10', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1169', '6', '11', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1170', '6', '12', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1171', '6', '13', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1172', '6', '14', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1173', '6', '15', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1174', '6', '16', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1175', '6', '17', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1176', '6', '18', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1177', '6', '30', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1178', '6', '31', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1179', '6', '32', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1180', '6', '33', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1181', '6', '34', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1182', '6', '35', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1183', '6', '36', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1184', '6', '37', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1185', '6', '38', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1186', '6', '39', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1187', '6', '40', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1188', '6', '41', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1189', '6', '42', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1190', '6', '43', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1191', '6', '44', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1192', '6', '45', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1193', '6', '50', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1194', '6', '51', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1195', '6', '52', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1196', '6', '53', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1197', '6', '54', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1198', '6', '55', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1199', '6', '56', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1200', '6', '57', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1201', '6', '58', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1202', '6', '59', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1203', '6', '60', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1204', '6', '61', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1205', '6', '62', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1206', '6', '63', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1207', '6', '64', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1208', '6', '65', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1209', '6', '66', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1210', '6', '67', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1211', '6', '68', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1212', '6', '69', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1213', '6', '70', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1214', '6', '71', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1215', '6', '72', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1216', '6', '73', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1217', '6', '74', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1218', '6', '75', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1219', '6', '76', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1220', '6', '77', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1221', '6', '78', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1222', '6', '81', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1223', '6', '82', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1224', '6', '83', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1225', '6', '84', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1226', '6', '90', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');
INSERT INTO `st_role_authority` VALUES ('1227', '6', '91', '1', 'admin', 'admin', '2020-10-20 02:53:00', '2020-10-20 02:53:00');

-- ----------------------------
-- Table structure for `st_schedule_job`
-- ----------------------------
DROP TABLE IF EXISTS `st_schedule_job`;
CREATE TABLE `st_schedule_job` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `job_id` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'JobID',
  `job_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'Job名称',
  `cron` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'cron表达式',
  `start_job` bit(1) DEFAULT NULL COMMENT '启动状态',
  `job_class` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '方法',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `fire_time` datetime DEFAULT NULL COMMENT '触发时间',
  `last_fire_time` datetime DEFAULT NULL COMMENT '上次触发时间',
  `next_fire_time` datetime DEFAULT NULL COMMENT '下次触发时间',
  `job_desc` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'Job描述',
  `fail_reason` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '失败原因',
  `yn_flag` char(2) COLLATE utf8_bin DEFAULT NULL COMMENT '有效标志',
  `creator` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `editor` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
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
INSERT INTO `ST_SYSTEM_SEQUENCE` VALUES ('ATTACHMENT', 'SEQUENCE', '31');

-- ----------------------------
-- Table structure for `st_todo`
-- ----------------------------
DROP TABLE IF EXISTS `st_todo`;
CREATE TABLE `st_todo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `title` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '编码',
  `start` datetime DEFAULT NULL COMMENT '开始时间',
  `end` datetime DEFAULT NULL COMMENT '结束时间',
  `days_count` int(11) DEFAULT NULL COMMENT '天数',
  `url` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '链接',
  `task_content` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '内容',
  `level` int(11) DEFAULT NULL COMMENT '优先级',
  `executor` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '执行人',
  `yn_flag` char(2) COLLATE utf8_bin DEFAULT NULL COMMENT '有效标志',
  `creator` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `editor` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
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
  `name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '姓名',
  `account` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '账号',
  `password` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '密码',
  `avatar` varchar(255) CHARACTER SET utf8 DEFAULT '' COMMENT '用户头像图片',
  `email` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '邮箱',
  `last_pwd_modified_time` datetime DEFAULT NULL COMMENT '上次修改密码时间',
  `status` char(2) COLLATE utf8_bin DEFAULT '0' COMMENT '状态',
  `yn_flag` char(2) COLLATE utf8_bin DEFAULT NULL COMMENT '有效标志',
  `creator` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `editor` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `erp_flag` char(2) COLLATE utf8_bin DEFAULT NULL COMMENT 'ERP账号标识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
-- Records of st_user
-- ----------------------------
INSERT INTO `st_user` VALUES ('1', '管理员', 'admin', 'bcd0b29f69059fe4c69802525c4ab550', 'http://localhost:9430/images/20201013093858_tp2.jpg', 'admin@admin.admin', '2019-01-17 15:05:04', '1', '1', null, 'admin', '2018-12-29 10:40:07', '2020-10-13 01:39:24', '0');
INSERT INTO `st_user` VALUES ('2', 'sunnj666', 'njsun666', '9faa0dc953d4c953bd385d4a0adfd488', 'http://localhost:9430/images/20201013171310_pp.png', 'aa@aa.aa2', '2020-10-13 09:13:41', '0', '0', null, 'admin', null, '2020-10-20 01:25:04', '0');
INSERT INTO `st_user` VALUES ('4', '测试', 'test', '0ace0a5fc5802a43305c8ae547a81afe', 'http://localhost:9430/images/20201013170420_pp.png', 'test@aa.aa', '2019-01-17 15:23:28', '1', '0', null, 'admin', null, '2020-10-13 09:12:25', '0');
INSERT INTO `st_user` VALUES ('8', 'lipan', 'lovepli', 'a4e24a4d31f8c8aee8cf346945b0890c', 'http://localhost:9430/images/20201013170420_pp.png', 'pli38546@gmail.com', '2020-08-18 04:30:37', '1', '0', 'admin', 'admin', '2020-08-18 04:30:37', '2020-10-13 09:10:59', '0');
INSERT INTO `st_user` VALUES ('19', 'lipan', 'lovepli', 'a4e24a4d31f8c8aee8cf346945b0890c', 'http://localhost:9430/images/20201013170420_pp.png', 'pli38546@gmail.com', '2020-10-13 08:22:17', '1', '0', 'admin', 'admin', '2020-10-13 08:22:17', '2020-10-13 09:14:10', '0');
INSERT INTO `st_user` VALUES ('20', '李攀', 'lovepli', 'a4e24a4d31f8c8aee8cf346945b0890c', 'http://localhost:9430/images/20201019160619_pp.png', 'pli38546@gmail.com', '2020-10-19 08:06:24', '1', '0', 'admin', 'admin', '2020-10-19 08:06:24', '2020-10-19 08:06:27', '0');
INSERT INTO `st_user` VALUES ('21', 'lipan', 'lovepli', 'a4e24a4d31f8c8aee8cf346945b0890c', 'http://localhost:9430/images/20201019163547_pp.png', 'pli38546@gmail.com', '2020-10-19 08:35:51', '1', '0', 'admin', 'admin', '2020-10-19 08:35:50', '2020-10-19 09:33:53', '0');
INSERT INTO `st_user` VALUES ('22', 'lipan', 'lovepli', 'a4e24a4d31f8c8aee8cf346945b0890c', 'http://localhost:9430/images/20201019173455_pp.png', 'pli38546@gmail.com', '2020-10-19 09:34:58', '1', '0', 'admin', 'admin', '2020-10-19 09:34:58', '2020-10-19 09:38:54', '0');
INSERT INTO `st_user` VALUES ('23', '3333333333333333', '3333333333333', '01bf2093f07cfd72b5b84b4237c21383', 'http://localhost:9430/images/20201020092958_pp.png', 'pli38546@gmail.com', '2020-10-20 01:30:03', '1', '0', 'admin', 'admin', '2020-10-20 01:30:03', '2020-10-20 01:30:08', '0');
INSERT INTO `st_user` VALUES ('24', '1111111', '111111111', '8466b45507a9e7727b287fde064ac832', 'http://localhost:9430/images/20201020093234_pp.png', 'pli38546@gmail.com', '2020-10-20 01:32:38', '1', '0', 'admin', 'admin', '2020-10-20 01:32:38', '2020-10-20 01:33:07', '0');
INSERT INTO `st_user` VALUES ('25', '1111111111111', '1111111111111', 'd774a55a677f714b586fd7822e73f0e9', 'http://localhost:9430/images/20201020093615_pp.png', 'pli38546@gmail.com', '2020-10-20 01:36:20', '1', '0', 'admin', 'admin', '2020-10-20 01:36:20', '2020-10-20 01:37:07', '0');
INSERT INTO `st_user` VALUES ('26', '222222222222222', '22222222222222222', '854eb16a3633b81e52c20fdee8e0c292', 'http://localhost:9430/images/20201020095019_pp.png', 'pli38546@gmail.com', '2020-10-20 01:50:24', '1', '0', 'admin', 'admin', '2020-10-20 01:50:24', '2020-10-20 01:50:32', '0');
INSERT INTO `st_user` VALUES ('27', '22222222222222222', '4444444444444444444444', '9faa0dc953d4c953bd385d4a0adfd488', 'http://localhost:9430/images/20201020101830_pp.png', 'pli38546@gmail.com', '2020-10-20 02:20:48', '1', '0', 'admin', 'admin', '2020-10-20 02:18:34', '2020-10-20 02:21:02', '0');
INSERT INTO `st_user` VALUES ('28', '3333333333333333', '333333333333333333', 'f5bc12ceb752caa7c402440990e5d37e', '', 'pli38546@gmail.com', '2020-10-20 03:56:54', '1', '0', 'admin', 'admin', '2020-10-20 03:56:54', '2020-10-20 03:56:59', '0');

-- ----------------------------
-- Table structure for `st_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `st_user_role`;
CREATE TABLE `st_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `yn_flag` char(2) COLLATE utf8_bin DEFAULT NULL COMMENT '有效标志',
  `creator` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `editor` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='用户角色关系表';

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
INSERT INTO `st_user_role` VALUES ('23', '1', '0', '1', 'admin', 'admin', '2020-10-20 02:24:17', '2020-10-20 02:24:17');
INSERT INTO `st_user_role` VALUES ('24', '1', '3', '1', 'admin', 'admin', '2020-10-20 02:24:17', '2020-10-20 02:24:17');
INSERT INTO `st_user_role` VALUES ('25', '1', '4', '1', 'admin', 'admin', '2020-10-20 02:24:17', '2020-10-20 02:24:17');
INSERT INTO `st_user_role` VALUES ('26', '1', '5', '1', 'admin', 'admin', '2020-10-20 02:24:17', '2020-10-20 02:24:17');
