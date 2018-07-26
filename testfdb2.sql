/*
Navicat MySQL Data Transfer

Source Server         : BPGLR
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : testfdb2

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-07-26 15:42:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_dept`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级部门ID，一级部门为0',
  `name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='部门管理';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1', '0', '白云电气集团', '2', '0');
INSERT INTO `sys_dept` VALUES ('3', '1', '杨新分公司', '2', '0');
INSERT INTO `sys_dept` VALUES ('4', '3', '自动化事业部1', '0', '-1');
INSERT INTO `sys_dept` VALUES ('5', '3', '轨道交通事业部', '1', '-1');
INSERT INTO `sys_dept` VALUES ('9', '1', '世科公司', '1', '0');
INSERT INTO `sys_dept` VALUES ('10', '3', 'test', '0', '-1');
INSERT INTO `sys_dept` VALUES ('11', '9', 'gongsi1', '1', '0');

-- ----------------------------
-- Table structure for `sys_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `time` bigint(20) NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='系统日志';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('1', 'admin', '新增菜单', 'com.bpg.lr.serviceImpl.login.SysMenuServiceImpl.insert()', '{\"menuId\":null,\"parentId\":0,\"name\":\"功能3\",\"url\":null,\"perms\":null,\"type\":0,\"icon\":\"fa fa-th-list\",\"orderNum\":0,\"list\":null,\"parentName\":\"根菜单\",\"open\":null}', '11', '127.0.0.1', '2018-07-26 14:00:54');
INSERT INTO `sys_log` VALUES ('2', 'admin', '登陆', 'com.bpg.lr.controller.login.HomeController.loginUser()', '\"admin\"', '268', '127.0.0.1', '2018-07-26 14:26:53');

-- ----------------------------
-- Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `sn` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '系统管理', null, null, '0', 'fa fa-cog', '10', null);
INSERT INTO `sys_menu` VALUES ('2', '1', '用户管理', 'modules/sys/user.html', null, '1', 'fa fa-user', '1', null);
INSERT INTO `sys_menu` VALUES ('3', '1', '角色管理', 'modules/sys/role.html', null, '1', 'fa fa-user-secret', '2', null);
INSERT INTO `sys_menu` VALUES ('4', '1', '菜单管理', 'modules/sys/menu.html', null, '1', 'fa fa-th-list', '3', null);
INSERT INTO `sys_menu` VALUES ('5', '1', 'SQL监控', 'druid/sql.html', null, '1', 'fa fa-bug', '4', null);
INSERT INTO `sys_menu` VALUES ('29', '1', '系统日志', 'modules/sys/log.html', 'sys:log:list', '1', 'fa fa-file-text-o', '7', null);
INSERT INTO `sys_menu` VALUES ('31', '1', '部门管理', 'modules/sys/dept.html', null, '1', 'fa fa-file-code-o', '6', null);
INSERT INTO `sys_menu` VALUES ('36', '1', '数据字典管理', 'modules/sys/dict.html', null, '1', 'fa fa-bookmark-o', '8', null);
INSERT INTO `sys_menu` VALUES ('41', '0', '功能1', 'tee', null, '0', 'fa fa-envelope', '1', null);
INSERT INTO `sys_menu` VALUES ('42', '0', '功能2', null, null, '0', 'fa fa-user', '0', null);
INSERT INTO `sys_menu` VALUES ('43', '0', '功能3', null, null, '0', 'fa fa-th-list', '0', null);

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '管理员', '管理员角色', '3', '2018-06-27 09:31:53');
INSERT INTO `sys_role` VALUES ('2', '操作员12', '操作员1', null, '2018-06-27 09:31:59');

-- ----------------------------
-- Table structure for `sys_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('12', '4', '1');
INSERT INTO `sys_role_menu` VALUES ('13', '4', '3');
INSERT INTO `sys_role_menu` VALUES ('14', '4', '4');
INSERT INTO `sys_role_menu` VALUES ('15', '4', '5');
INSERT INTO `sys_role_menu` VALUES ('16', '4', '29');
INSERT INTO `sys_role_menu` VALUES ('17', '4', '36');
INSERT INTO `sys_role_menu` VALUES ('24', '2', '1');
INSERT INTO `sys_role_menu` VALUES ('25', '2', '2');
INSERT INTO `sys_role_menu` VALUES ('26', '2', '3');
INSERT INTO `sys_role_menu` VALUES ('27', '2', '4');
INSERT INTO `sys_role_menu` VALUES ('28', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('29', '1', '2');
INSERT INTO `sys_role_menu` VALUES ('30', '1', '3');
INSERT INTO `sys_role_menu` VALUES ('31', '1', '4');
INSERT INTO `sys_role_menu` VALUES ('32', '1', '31');
INSERT INTO `sys_role_menu` VALUES ('33', '1', '41');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(256) DEFAULT NULL COMMENT '盐',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'e1153123d7d180ceeb820d577ff119876678732a68eef4e6ffc0b1f06a01f91b', 'YzcmCZNvbXocrsz9dm8e', '', '15989065344', '1', '0', '2018-06-05 16:58:32');
INSERT INTO `sys_user` VALUES ('2', 'test', 'e1153123d7d180ceeb820d577ff119876678732a68eef4e6ffc0b1f06a01f91b', 'YzcmCZNvbXocrsz9dm8e', 'a@qq.com', '12465784547', '1', '9', '2018-06-13 10:56:35');
INSERT INTO `sys_user` VALUES ('47', 'test6', 'c8b16b4f6983c5fbbd207b146bd791e937f989fc39cf1b0dcafc6b42e3791f27', 'X73GNodix8YC86xVx5s9', '4646', '122323', '1', '4', '2018-06-22 15:08:41');
INSERT INTO `sys_user` VALUES ('68', 'test4', 'ab7ff3c0310bd6a749d9a21af2f6136d4d464807d0a049abd45ec6de9fc07c95', 'WMaKegw7fOGYOWKLQthV', '4646@qq.com', '159', '1', '4', '2018-06-27 13:37:24');
INSERT INTO `sys_user` VALUES ('69', 'test7', 'e1d7a51d7fbc3a8740a6d7527e45de6bda4678f7a49adf0302d016b652c62bf8', 'y8eN7AJzlkNL5sm5dK8n', 'tete', '123', '0', '11', '2018-07-10 11:42:35');

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '1');
INSERT INTO `sys_user_role` VALUES ('2', '46', '2');
INSERT INTO `sys_user_role` VALUES ('23', '48', '2');
INSERT INTO `sys_user_role` VALUES ('25', '47', '2');
INSERT INTO `sys_user_role` VALUES ('26', '47', '1');
INSERT INTO `sys_user_role` VALUES ('27', '47', '4');
INSERT INTO `sys_user_role` VALUES ('31', '68', '4');
INSERT INTO `sys_user_role` VALUES ('33', '69', '2');

-- ----------------------------
-- Function structure for `queryChildrenAreaInfo`
-- ----------------------------
DROP FUNCTION IF EXISTS `queryChildrenAreaInfo`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `queryChildrenAreaInfo`(depteId INT) RETURNS varchar(4000) CHARSET utf8
BEGIN
DECLARE sTemp VARCHAR(4000);
DECLARE sTempChd VARCHAR(4000);

SET sTemp='$';
SET sTempChd = CAST(depteId AS CHAR);

WHILE sTempChd IS NOT NULL DO
SET sTemp= CONCAT(sTemp,',',sTempChd);
SELECT GROUP_CONCAT(dept_id) INTO sTempChd FROM sys_dept WHERE FIND_IN_SET(parent_id,sTempChd)>0;
END WHILE;
RETURN sTemp;
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for `queryChildrenAreaInfo1`
-- ----------------------------
DROP FUNCTION IF EXISTS `queryChildrenAreaInfo1`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `queryChildrenAreaInfo1`(depteId INT) RETURNS varchar(4000) CHARSET utf8
BEGIN
DECLARE sTemp VARCHAR(4000);
DECLARE sTempChd VARCHAR(4000);

SET sTemp='$';
SET sTempChd = CAST(depteId AS CHAR);
SET sTemp = CONCAT(sTemp,',',sTempChd);

SELECT parent_id INTO sTempChd FROM sys_dept WHERE dept_id = sTempChd;
WHILE sTempChd <> 0 DO
SET sTemp = CONCAT(sTemp,',',sTempChd);
SELECT parent_id INTO sTempChd FROM sys_dept WHERE dept_id = sTempChd;
END WHILE;
RETURN sTemp;
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for `queryfatherAreaInfo`
-- ----------------------------
DROP FUNCTION IF EXISTS `queryfatherAreaInfo`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `queryfatherAreaInfo`(depteId INT) RETURNS varchar(4000) CHARSET utf8
BEGIN
DECLARE sTemp VARCHAR(4000);
DECLARE sTempChd VARCHAR(4000);

SET sTemp='$';
SET sTempChd = CAST(depteId AS CHAR);
SET sTemp = CONCAT(sTemp,',',sTempChd);

SELECT parent_id INTO sTempChd FROM sys_dept WHERE dept_id = sTempChd;
WHILE sTempChd <> 0 DO
SET sTemp = CONCAT(sTemp,',',sTempChd);
SELECT parent_id INTO sTempChd FROM sys_dept WHERE dept_id = sTempChd;
END WHILE;
RETURN sTemp;
END
;;
DELIMITER ;
