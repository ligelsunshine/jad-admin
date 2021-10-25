/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : localhost:3306
 Source Schema         : dev_jad_admin

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 25/10/2021 23:23:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for devtools_generate
-- ----------------------------
DROP TABLE IF EXISTS `devtools_generate`;
CREATE TABLE `devtools_generate` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `ds` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '数据源',
  `module` varchar(255) DEFAULT NULL COMMENT 'module',
  `title` varchar(255) DEFAULT NULL COMMENT 'title',
  `name` varchar(255) DEFAULT NULL COMMENT 'name',
  `namespace` varchar(255) DEFAULT NULL COMMENT 'namespace',
  `main_field` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'main_field',
  `tree_model` bit(1) DEFAULT NULL COMMENT 'tree_model',
  `logic` bit(1) DEFAULT NULL COMMENT 'logic',
  `model` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '模型',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of devtools_generate
-- ----------------------------
BEGIN;
INSERT INTO `devtools_generate` VALUES ('5438993480059b4879e8de1b2731ac4d', 'slave', 'common', '数据源', 'datasource', 'sys', NULL, b'0', b'0', '{\"bigHump\":\"Datasource\",\"fieldSchema\":[{\"bigHump\":\"Name\",\"component\":\"Input\",\"enumVal\":[{\"bigHump\":\"Oracle\",\"lowerCaseUnderline\":\"oracle\",\"name\":\"Oracle\",\"smallHump\":\"oracle\",\"title\":\"Oracle\",\"upperCaseUnderline\":\"ORACLE\"},{\"bigHump\":\"MySQL\",\"lowerCaseUnderline\":\"my_s_q_l\",\"name\":\"MySQL\",\"smallHump\":\"mySQL\",\"title\":\"MySQL\",\"upperCaseUnderline\":\"MY_S_Q_L\"},{\"bigHump\":\"DB2\",\"lowerCaseUnderline\":\"d_b_2\",\"name\":\"DB2\",\"smallHump\":\"dB2\",\"title\":\"DB2\",\"upperCaseUnderline\":\"D_B_2\"},{\"bigHump\":\"PostgreSQL\",\"lowerCaseUnderline\":\"postgre_s_q_l\",\"name\":\"PostgreSQL\",\"smallHump\":\"postgreSQL\",\"title\":\"PostgreSQL\",\"upperCaseUnderline\":\"POSTGRE_S_Q_L\"},{\"bigHump\":\"H2\",\"lowerCaseUnderline\":\"h_2\",\"name\":\"H2\",\"smallHump\":\"h2\",\"title\":\"H2\",\"upperCaseUnderline\":\"H_2\"}],\"id\":\"3cefd9d553df44c89e122b1ba302ebac\",\"lowerCaseUnderline\":\"name\",\"name\":\"name\",\"orderNo\":-2,\"presents\":[\"ADD_FORM\",\"LIST\",\"EDIT_FORM\",\"DETAIL\",\"SEARCH_FORM\"],\"require\":false,\"rules\":[],\"smallHump\":\"name\",\"title\":\"数据库名称\",\"type\":\"STRING\",\"upperCaseUnderline\":\"NAME\"},{\"bigHump\":\"Type\",\"component\":\"Select\",\"defaultVal\":\"mysql\",\"enumVal\":[{\"bigHump\":\"Oracle\",\"lowerCaseUnderline\":\"oracle\",\"name\":\"oracle\",\"smallHump\":\"oracle\",\"title\":\"Oracle\",\"upperCaseUnderline\":\"ORACLE\"},{\"bigHump\":\"Mysql\",\"lowerCaseUnderline\":\"mysql\",\"name\":\"mysql\",\"smallHump\":\"mysql\",\"title\":\"MySQL\",\"upperCaseUnderline\":\"MYSQL\"},{\"bigHump\":\"Db2\",\"lowerCaseUnderline\":\"db_2\",\"name\":\"db2\",\"smallHump\":\"db2\",\"title\":\"DB2\",\"upperCaseUnderline\":\"DB_2\"},{\"bigHump\":\"Posstgresql\",\"lowerCaseUnderline\":\"posstgresql\",\"name\":\"posstgresql\",\"smallHump\":\"posstgresql\",\"title\":\"PostgreSQL\",\"upperCaseUnderline\":\"POSSTGRESQL\"},{\"bigHump\":\"H2\",\"lowerCaseUnderline\":\"h_2\",\"name\":\"h2\",\"smallHump\":\"h2\",\"title\":\"H2\",\"upperCaseUnderline\":\"H_2\"}],\"id\":\"a70935a671104677a28986e419dc7a96\",\"lowerCaseUnderline\":\"type\",\"name\":\"type\",\"orderNo\":-1,\"presents\":[\"ADD_FORM\",\"LIST\",\"EDIT_FORM\",\"DETAIL\",\"SEARCH_FORM\"],\"require\":false,\"rules\":[],\"smallHump\":\"type\",\"title\":\"类型\",\"type\":\"ENUM\",\"upperCaseUnderline\":\"TYPE\"},{\"bigHump\":\"Url\",\"component\":\"Input\",\"enumVal\":[],\"id\":\"f8a479feced5463b8df1a7786eebdf62\",\"lowerCaseUnderline\":\"url\",\"name\":\"url\",\"orderNo\":0,\"presents\":[\"ADD_FORM\",\"LIST\",\"EDIT_FORM\",\"DETAIL\",\"SEARCH_FORM\"],\"require\":true,\"rules\":[],\"smallHump\":\"url\",\"title\":\"URL\",\"type\":\"STRING\",\"upperCaseUnderline\":\"URL\"},{\"bigHump\":\"Username\",\"component\":\"Input\",\"enumVal\":[],\"id\":\"4ce7919a1ea84deda2f7876d900b8ed7\",\"lowerCaseUnderline\":\"username\",\"name\":\"username\",\"orderNo\":0,\"presents\":[\"ADD_FORM\",\"LIST\",\"EDIT_FORM\",\"DETAIL\",\"SEARCH_FORM\"],\"require\":true,\"rules\":[],\"smallHump\":\"username\",\"title\":\"用户名\",\"type\":\"STRING\",\"upperCaseUnderline\":\"USERNAME\"},{\"bigHump\":\"Password\",\"component\":\"Input\",\"enumVal\":[],\"id\":\"4aeeaaedf76a481186b79dcf4c9db6f4\",\"lowerCaseUnderline\":\"password\",\"name\":\"password\",\"orderNo\":0,\"presents\":[\"ADD_FORM\",\"LIST\",\"EDIT_FORM\",\"DETAIL\"],\"require\":true,\"rules\":[],\"smallHump\":\"password\",\"title\":\"密码\",\"type\":\"STRING\",\"upperCaseUnderline\":\"PASSWORD\"}],\"logic\":false,\"lowerCaseDash\":\"datasource\",\"lowerCaseUnderline\":\"datasource\",\"module\":\"common\",\"name\":\"datasource\",\"namespace\":\"sys\",\"namespaceBigHump\":\"Sys\",\"namespaceLowerCaseDash\":\"sys\",\"namespaceLowerCaseUnderline\":\"sys\",\"namespaceSmallHump\":\"sys\",\"smallHump\":\"datasource\",\"title\":\"数据源\",\"treeModel\":false}', NULL, '2021-10-16 23:43:52', NULL, '2021-10-17 22:17:07', NULL);
INSERT INTO `devtools_generate` VALUES ('87bd063a7069d7a423faaa9710728fa3', 'master', 'system', '学生', 'student', 'sys', 'title', b'0', b'0', '{\"bigHump\":\"Student\",\"fieldSchema\":[{\"bigHump\":\"IsMarry\",\"component\":\"Switch\",\"defaultVal\":\"\",\"enumVal\":[],\"id\":\"5\",\"lowerCaseUnderline\":\"is_marry\",\"name\":\"isMarry\",\"orderNo\":-1,\"presents\":[\"DETAIL\",\"LIST\",\"SEARCH_FORM\",\"ADD_FORM\",\"EDIT_FORM\"],\"require\":true,\"rules\":[],\"smallHump\":\"isMarry\",\"title\":\"是否已婚\",\"type\":\"BOOLEAN\",\"upperCaseUnderline\":\"IS_MARRY\"},{\"bigHump\":\"Name\",\"component\":\"Input\",\"defaultVal\":\"cxx\",\"enumVal\":[],\"id\":\"1\",\"lowerCaseUnderline\":\"name\",\"name\":\"name\",\"orderNo\":0,\"presents\":[\"DETAIL\",\"LIST\",\"SEARCH_FORM\",\"ADD_FORM\",\"EDIT_FORM\"],\"require\":true,\"rules\":[{\"max\":7,\"min\":4,\"type\":\"STRING_RANGE\"}],\"smallHump\":\"name\",\"title\":\"姓名\",\"type\":\"STRING\",\"upperCaseUnderline\":\"NAME\"},{\"bigHump\":\"Age\",\"component\":\"InputNumber\",\"defaultVal\":0,\"enumVal\":[],\"id\":\"2\",\"lowerCaseUnderline\":\"age\",\"name\":\"age\",\"orderNo\":0,\"presents\":[\"DETAIL\",\"LIST\",\"SEARCH_FORM\",\"ADD_FORM\",\"EDIT_FORM\"],\"require\":true,\"rules\":[],\"smallHump\":\"age\",\"title\":\"年龄\",\"type\":\"INT\",\"upperCaseUnderline\":\"AGE\"},{\"bigHump\":\"MyHeight\",\"component\":\"InputNumber\",\"defaultVal\":\"1.65\",\"enumVal\":[],\"id\":\"3\",\"lowerCaseUnderline\":\"my_height\",\"name\":\"my_height\",\"orderNo\":0,\"presents\":[\"DETAIL\",\"LIST\",\"SEARCH_FORM\",\"ADD_FORM\",\"EDIT_FORM\"],\"require\":false,\"rules\":[],\"smallHump\":\"myHeight\",\"title\":\"身高\",\"type\":\"FLOAT\",\"upperCaseUnderline\":\"MY_HEIGHT\"},{\"bigHump\":\"Birthday\",\"component\":\"DatePicker\",\"defaultVal\":\"2021-09-12T21:37:47\",\"enumVal\":[],\"id\":\"6\",\"lowerCaseUnderline\":\"birthday\",\"name\":\"birthday\",\"orderNo\":0,\"presents\":[\"DETAIL\",\"LIST\",\"SEARCH_FORM\",\"ADD_FORM\",\"EDIT_FORM\"],\"require\":true,\"rules\":[],\"smallHump\":\"birthday\",\"title\":\"生日\",\"type\":\"DATE\",\"upperCaseUnderline\":\"BIRTHDAY\"},{\"bigHump\":\"Sex\",\"component\":\"Select\",\"defaultVal\":\"Man\",\"enumVal\":[{\"bigHump\":\"Man\",\"lowerCaseUnderline\":\"man\",\"name\":\"Man\",\"smallHump\":\"man\",\"title\":\"男\",\"upperCaseUnderline\":\"MAN\"},{\"bigHump\":\"Woman\",\"lowerCaseUnderline\":\"woman\",\"name\":\"Woman\",\"smallHump\":\"woman\",\"title\":\"女\",\"upperCaseUnderline\":\"WOMAN\"}],\"id\":\"7\",\"lowerCaseUnderline\":\"sex\",\"name\":\"sex\",\"orderNo\":0,\"presents\":[\"DETAIL\",\"LIST\",\"SEARCH_FORM\",\"ADD_FORM\",\"EDIT_FORM\"],\"require\":true,\"rules\":[],\"smallHump\":\"sex\",\"title\":\"性别\",\"type\":\"ENUM\",\"upperCaseUnderline\":\"SEX\"},{\"bigHump\":\"Sdf\",\"component\":\"Input\",\"enumVal\":[],\"id\":\"f5fe85714b0c4588a4023c2d9d944e04\",\"lowerCaseUnderline\":\"sdf\",\"name\":\"sdf\",\"orderNo\":0,\"presents\":[\"DETAIL\",\"LIST\",\"ADD_FORM\",\"EDIT_FORM\"],\"require\":false,\"rules\":[],\"smallHump\":\"sdf\",\"title\":\"sdf\",\"type\":\"STRING\",\"upperCaseUnderline\":\"SDF\"},{\"bigHump\":\"MyMoney\",\"component\":\"InputNumber\",\"defaultVal\":\"1.99\",\"enumVal\":[],\"id\":\"4\",\"lowerCaseUnderline\":\"my_money\",\"name\":\"My_money\",\"orderNo\":1,\"presents\":[\"DETAIL\",\"LIST\",\"SEARCH_FORM\",\"ADD_FORM\",\"EDIT_FORM\"],\"require\":false,\"rules\":[],\"smallHump\":\"myMoney\",\"title\":\"余额\",\"type\":\"DOUBLE\",\"upperCaseUnderline\":\"MY_MONEY\"}],\"logic\":false,\"lowerCaseDash\":\"student\",\"lowerCaseUnderline\":\"student\",\"module\":\"system\",\"name\":\"student\",\"namespace\":\"sys\",\"namespaceBigHump\":\"Sys\",\"namespaceLowerCaseDash\":\"sys\",\"namespaceLowerCaseUnderline\":\"sys\",\"namespaceSmallHump\":\"sys\",\"smallHump\":\"student\",\"title\":\"学生\",\"treeModel\":false}', NULL, '2021-09-18 22:03:18', '30d808cbd7c94c66b4512718151b00aa', '2021-10-24 12:44:40', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_datasource
-- ----------------------------
DROP TABLE IF EXISTS `sys_datasource`;
CREATE TABLE `sys_datasource` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '数据库名称',
  `code` varchar(255) DEFAULT NULL COMMENT '编码',
  `type` tinyint DEFAULT '0' COMMENT '类型【Oracle:0, MySQL:1, DB2:2, PostgreSQL:3, H2:4】',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'URL',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '密码',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_datasource
-- ----------------------------
BEGIN;
INSERT INTO `sys_datasource` VALUES ('09dbd5b6093d9fdcae9f60c1b089459d', '系统数据库', 'master', 1, 'jdbc:mysql://localhost:3306/dev_jad_admin?useUnicode=true&characterEncoding=utf8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8&useSSL=true&allowMultiQueries=true&autoReconnect=true&useAffectRows=true', 'root', 'cxxwl96@sina.com', NULL, '2021-10-17 01:00:22', '30d808cbd7c94c66b4512718151b00aa', '2021-10-17 20:31:16', '30d808cbd7c94c66b4512718151b00aa');
INSERT INTO `sys_datasource` VALUES ('80aa47c21807605d3550149765cf1c0c', '系统数据库-子库', 'slave', 1, 'jdbc:mysql://localhost:3306/dev_jad_slave?useUnicode=true&characterEncoding=utf8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8&useSSL=true&allowMultiQueries=true&autoReconnect=true&useAffectRows=true', 'root', 'cxxwl96@sina.com', NULL, '2021-10-17 20:37:27', '30d808cbd7c94c66b4512718151b00aa', '2021-10-24 12:25:31', '30d808cbd7c94c66b4512718151b00aa');
COMMIT;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
  `p_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '父级菜单ID',
  `order_no` int DEFAULT NULL COMMENT '排序',
  `code` varchar(255) DEFAULT NULL COMMENT '编码',
  `name` varchar(255) DEFAULT NULL COMMENT '部门名称',
  `status` tinyint DEFAULT '0' COMMENT '状态【启用：0,停用：1】\n',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人',
  `enable` bit(1) DEFAULT NULL COMMENT '是否启用【未启用：0,已启用：1】',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
BEGIN;
INSERT INTO `sys_dept` VALUES ('0cea1f717129e855afba48ec22507c37', NULL, 0, 'HNFB', '华南分部', 0, NULL, '2021-08-21 23:33:49', '30d808cbd7c94c66b4512718151b00aa', '2021-08-22 00:40:36', '30d808cbd7c94c66b4512718151b00aa', NULL);
INSERT INTO `sys_dept` VALUES ('11ef896ee072e580771f436e14b26c7d', '3f0e4f121e5b61b6184d10897ad3d727', 0, NULL, '研发部', 0, NULL, '2021-08-21 23:36:31', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL, NULL);
INSERT INTO `sys_dept` VALUES ('38524e8f333f4b74dc1ef588a5b9926f', '3f0e4f121e5b61b6184d10897ad3d727', 0, NULL, '商务部', 0, NULL, '2021-08-22 00:00:48', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL, NULL);
INSERT INTO `sys_dept` VALUES ('3f0e4f121e5b61b6184d10897ad3d727', NULL, 0, 'HDFB', '华东分部', 0, NULL, '2021-08-21 23:33:31', '30d808cbd7c94c66b4512718151b00aa', '2021-08-22 00:39:32', '30d808cbd7c94c66b4512718151b00aa', NULL);
INSERT INTO `sys_dept` VALUES ('45ed35d2b0491d8772c4d2254781525f', '0cea1f717129e855afba48ec22507c37', 0, NULL, '市场部', 0, NULL, '2021-08-22 00:03:30', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL, NULL);
INSERT INTO `sys_dept` VALUES ('52a7755dcbfe990a82d0b24b7b45d049', '8f3c043c5ea7711e6050c5589de5bef1', 0, NULL, '研发部', 0, NULL, '2021-08-22 00:03:21', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL, NULL);
INSERT INTO `sys_dept` VALUES ('58cea91fc311d21e1c6c762981a2c3e8', '0cea1f717129e855afba48ec22507c37', 0, NULL, '财务部', 0, NULL, '2021-08-22 00:04:03', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL, NULL);
INSERT INTO `sys_dept` VALUES ('69fec399199bf749ff5c869467b5ead6', '0cea1f717129e855afba48ec22507c37', 0, NULL, '研发部', 0, NULL, '2021-08-22 00:03:16', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL, NULL);
INSERT INTO `sys_dept` VALUES ('6b74324967108c357a8697a944f88217', '8f3c043c5ea7711e6050c5589de5bef1', 0, NULL, '财务部', 0, NULL, '2021-08-22 00:04:10', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL, NULL);
INSERT INTO `sys_dept` VALUES ('7aaa9daa2ccf56260efb10b5bad2adb3', '3f0e4f121e5b61b6184d10897ad3d727', 0, NULL, '财务部', 0, NULL, '2021-08-22 00:00:59', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL, NULL);
INSERT INTO `sys_dept` VALUES ('805ee37b0dd3c132dbaf24b73b439fab', '8f3c043c5ea7711e6050c5589de5bef1', 0, NULL, '市场部', 0, NULL, '2021-08-22 00:03:37', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL, NULL);
INSERT INTO `sys_dept` VALUES ('8f3c043c5ea7711e6050c5589de5bef1', NULL, 0, 'XBFB', '西北分部', 0, NULL, '2021-08-21 23:34:02', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL, NULL);
INSERT INTO `sys_dept` VALUES ('9c54386f7574cc236c8afc0abf86b796', '0cea1f717129e855afba48ec22507c37', 0, NULL, '商务部', 0, NULL, '2021-08-22 00:03:48', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL, NULL);
INSERT INTO `sys_dept` VALUES ('9fd9b0139d140a37b76bf8bb3a79a007', '3f0e4f121e5b61b6184d10897ad3d727', 0, NULL, '市场部', 0, NULL, '2021-08-21 23:40:17', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL, NULL);
INSERT INTO `sys_dept` VALUES ('f0851c4699adbcdaec6df10a4e96427b', '8f3c043c5ea7711e6050c5589de5bef1', 0, NULL, '商务部', 0, NULL, '2021-08-22 00:03:54', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `p_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '父级菜单ID',
  `order_no` int DEFAULT NULL COMMENT '排序',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '编码',
  `type` tinyint DEFAULT NULL COMMENT '类型 [0：目录 1：菜单 2：按钮]',
  `permissions` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单URL',
  `name` varchar(255) DEFAULT NULL COMMENT '路由名称',
  `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '前端组件URL',
  `redirect` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '重定向',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单标题',
  `affix` bit(1) DEFAULT NULL COMMENT '是否固定标签',
  `ignore_keep_alive` bit(1) DEFAULT NULL COMMENT '是否忽略KeepAlive缓存',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单图标',
  `frame_src` varchar(255) DEFAULT NULL COMMENT '内嵌iframe的地址',
  `transition_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '指定该路由切换的动画名',
  `carry_param` bit(1) DEFAULT NULL COMMENT '如果该路由会携带参数，且需要在tab页上面显示。则需要设置为true',
  `hide_children_in_menu` bit(1) DEFAULT NULL COMMENT '是否隐藏所有子菜单',
  `hide_tab` bit(1) DEFAULT NULL COMMENT '当前路由不再标签页显示',
  `hide_menu` bit(1) DEFAULT NULL COMMENT '当前路由不再菜单显示',
  `ignore_route` bit(1) DEFAULT NULL COMMENT '本路由仅用于菜单生成，不会在实际的路由表中出现',
  `hide_path_for_children` bit(1) DEFAULT NULL COMMENT '是否在子级菜单的完整path中忽略本级path',
  `external` bit(1) DEFAULT NULL COMMENT '是否外链',
  `status` tinyint DEFAULT '0' COMMENT '状态【启用：0,停用：1】\n',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES ('009ace919a3d191932449f11fcb763b1', '7e84f61c521790ee185467bcb153f4ae', 0, NULL, 2, 'sys:user:delete', NULL, '9204f47cf53e4dd4bfea2febf947af26', NULL, NULL, '删除用户', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-10-22 21:38:47', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('0c0ea35ce26cb9942b77372731897214', '18853ceb4615b587d77a014b7a8d2336', 0, NULL, 1, NULL, 'sms', '56a2acefd6b84ab6a4b3ff0964eda4d7', '/msg/sms/index', NULL, 'SMS消息', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-08-15 23:41:44', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('0c5e77c3b84fbbe24bff5a9795b87317', 'ce632f999c5110f1136ef029b82aa791', 0, NULL, 1, NULL, 'post', 'a1345bc2190f46879167fb291020f670', '/sys/post/index', NULL, '岗位管理', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-08-15 23:27:58', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('0fa8988ea7fdff5ce988dda92af67400', 'ed5287e77f82d8cf064549bdce3728f7', 0, NULL, 1, NULL, 'about', 'ab5ab4e5e44d47438d5c020aac408beb', '/sys/about/index', NULL, '关于', b'0', b'0', 'simple-icons:about-dot-me', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-08-15 23:11:18', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('18853ceb4615b587d77a014b7a8d2336', NULL, 0, NULL, 0, NULL, '/msg', '7a98a7604feb4ef690b9cb7aab07485d', 'LAYOUT', NULL, '消息中心', b'0', b'0', 'ant-design:message-outlined', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-08-15 22:55:58', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('1c6e47b7c845522d27841449f1804886', '18853ceb4615b587d77a014b7a8d2336', 0, NULL, 1, NULL, 'temp', 'e581937d3f764f13a12cdf6b853580d7', '/msg/temp/index', NULL, '模板管理', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-08-15 23:40:48', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('1f140fbe7445ada922ea441729af24bd', 'd1b05c6888eaedd21abcce51d2433882', 0, NULL, 1, NULL, 'ModelDesign/:id', 'd865b00df9e94c4ebd5eeed645a67e09', '/devtools/generator/ModelDesign', NULL, '设计Model', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-09-21 00:14:24', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('1f971a4677901ce44ef1f82ef647c206', '7e84f61c521790ee185467bcb153f4ae', 0, NULL, 2, 'sys:user:update', NULL, '4e0294c1ae8b4df6b464120cb94fb8e6', NULL, NULL, '修改用户', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-10-22 21:43:08', '30d808cbd7c94c66b4512718151b00aa', '2021-10-22 21:48:06', '30d808cbd7c94c66b4512718151b00aa');
INSERT INTO `sys_menu` VALUES ('2b56fa6bd71df15a2acaa20cb8ec58b5', NULL, 0, NULL, 0, NULL, '/user', '0a2f80bcc90444ce9d0d6a0a740ed43f', 'LAYOUT', NULL, '个人中心', b'0', b'0', 'ant-design:user-outlined', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-08-15 22:57:21', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('2e80fce962c46d4b5334245035851535', 'ce632f999c5110f1136ef029b82aa791', 0, NULL, 1, NULL, 'role', 'feb13067664f451996ed17eec143b30a', '/sys/role/index', NULL, '角色管理', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-08-15 22:59:44', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('2f8ea043f9ae0daa906ad39d57599aa6', NULL, 0, NULL, 0, NULL, '/file-sys', '995c477e3fe541099c0fc8ab18cfd799', 'LAYOUT', NULL, '文件系统', b'0', b'0', 'ant-design:folder-open-outlined', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-08-15 22:55:15', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('397c27e2d91f14736e61e2baef96b860', '7f5ebaf51d971c4204e759ae9686e32b', 0, NULL, 1, NULL, 'service', 'fd1cef2532a84e76aeb0cf4616de00d7', '/sys-monitor/service/index', NULL, '服务监控（服务器信息、JVM监控、Redis监控）', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-08-15 23:24:47', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('41ce300ed10c87c39b70f2121c1cf76d', 'ce632f999c5110f1136ef029b82aa791', 0, NULL, 1, NULL, 'menu', '0881c7661d1848428ba6874e681700f7', '/sys/menu/Index', NULL, '菜单管理', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-08-15 23:00:21', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('489a735425de96fa0c2412f5b4ffed66', 'dd5c824b9f0f61a6cd3a2b703bdffc55', 0, NULL, 1, NULL, 'database', 'cf4f0fa807964515b1fc12aec15bd078', '/sys/datasource/Index', NULL, '数据源管理', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-08-15 23:04:21', '30d808cbd7c94c66b4512718151b00aa', '2021-10-17 00:46:45', '30d808cbd7c94c66b4512718151b00aa');
INSERT INTO `sys_menu` VALUES ('4a86291d875bf568c5fcd8947b0d13ec', 'ce632f999c5110f1136ef029b82aa791', 0, NULL, 1, NULL, 'tenant', 'f3011c75f23d4dcdaece4d955feee488', '/sys/tenant/index', NULL, '租户管理', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-08-15 23:29:50', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('4ff777cc924eaf5b8920ad5bb16e94b3', '2b56fa6bd71df15a2acaa20cb8ec58b5', 0, NULL, 1, NULL, 'dept', '7b65aa8f572d4612b5d7824deebd5f85', '/user/dept/index', NULL, '我的部门', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-08-15 23:45:45', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('5082de8a7767b411836cb5065ba936dc', '7f5ebaf51d971c4204e759ae9686e32b', 0, NULL, 1, NULL, 'task-schedule', '11ed265c3c65491ea3248a894ae03331', '/sys-monitor/task-schedule/index', NULL, '任务调度', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-08-15 23:23:38', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('54918519ff06ea7bf43ac7a960d01a48', 'ed5287e77f82d8cf064549bdce3728f7', 0, NULL, 1, NULL, 'workbench', 'a3d8a3c94f7f4d02a5159f489a2f5478', '/dashboard/workbench/index', NULL, '工作台', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-08-15 23:03:09', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('5a62974225f08b322ede180c608cf980', '7e84f61c521790ee185467bcb153f4ae', 0, NULL, 2, 'sys:user:get', NULL, '50bc1c0ebc194d2ca1af6f1e35bb01c0', NULL, NULL, '获取单个用户', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-10-22 21:47:17', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('6c43bf1b4c67c1c0658e58fbd16e256f', '7f5ebaf51d971c4204e759ae9686e32b', 0, NULL, 1, NULL, 'druid-monitor', '06d96885833b48e09ddb19ba3496ae0d', '/sys-monitor/druid-monitor', NULL, 'SQL监控（druid monitor）', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-08-15 23:26:18', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('7e84f61c521790ee185467bcb153f4ae', 'ce632f999c5110f1136ef029b82aa791', 0, NULL, 1, NULL, 'user', '64902a74da354b169afbb4a280275ac6', '/sys/user/index', NULL, '用户管理', b'0', b'0', '', NULL, NULL, b'0', b'1', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-08-15 22:59:13', '30d808cbd7c94c66b4512718151b00aa', '2021-09-04 23:44:51', '30d808cbd7c94c66b4512718151b00aa');
INSERT INTO `sys_menu` VALUES ('7f5ebaf51d971c4204e759ae9686e32b', NULL, 0, NULL, 0, NULL, '/sys-monitor', 'f13e4f0fbd3a4a8884d60dfb0b114857', 'LAYOUT', NULL, '系统监控', b'0', b'0', 'ant-design:monitor-outlined', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-08-15 22:52:39', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('80a7335610b81933e6b5b2b4e2cf6b35', '18853ceb4615b587d77a014b7a8d2336', 0, NULL, 1, NULL, 'notice', 'd5f5a1bc6698477f918609f4cbdaa3b8', '/msg/notice/index', NULL, '系统通告', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-08-15 23:41:13', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('9650554e64c53b03d64f9039e0ead1c0', 'ce632f999c5110f1136ef029b82aa791', 0, NULL, 1, NULL, 'dict', '562b2058b0d4497fa469cbffc4384472', '/sys/dict/index', NULL, '字典管理', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-08-15 23:28:29', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('9cd43fa42eb9e8e5aef73330534a4012', '18853ceb4615b587d77a014b7a8d2336', 0, NULL, 1, NULL, 'email', '672d816b116642baa4ed17665a776483', '/msg/email/index', NULL, 'Email消息', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-08-15 23:42:12', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('a6cf40d746f4fd88ee5f4646fd4933d0', 'ce632f999c5110f1136ef029b82aa791', 0, NULL, 1, NULL, 'data-classfly', 'cbeddb0e5b7a44fcbbe215f6d3b023bf', '/sys/data-classfly/index', NULL, '数据分类', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-08-15 23:29:06', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('ab7130763b48461c5f65d49436ed0a26', 'd6ddaddfc99cf44167649751f6505a3a', 0, NULL, 1, NULL, 'logs', '7ed46f99b73a48738441eaa602897e8f', '/logger/logs/index', NULL, '日志管理（日志文件）', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-08-15 23:40:17', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('b119d879a7b5317f2b85359f3cd2f257', '2b56fa6bd71df15a2acaa20cb8ec58b5', 0, NULL, 1, NULL, 'msg', '2b1ea1e71b7c4b1c8d5308de8ee5ea37', '/user/msg/index', NULL, '我的消息', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-08-15 23:45:16', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('b9f0438b9ec508e102a78620ba3c12dd', 'be23e50d8fcd326a6d2819cb7837ec39', 0, NULL, 1, NULL, 'netlify-icon', 'a6b06c5901ce4f589a107ecd443943aa', 'IFRAME', NULL, 'NetlifyIcon', b'0', b'0', '', 'https://icones.netlify.app/collection/all', NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-08-15 23:08:43', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('be23e50d8fcd326a6d2819cb7837ec39', 'dd5c824b9f0f61a6cd3a2b703bdffc55', 0, 'ICON', 0, NULL, 'icon-search', 'a24565889a8c4d049035b8831e00baf8', '', NULL, 'ICON查询', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-08-15 23:07:29', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('c8f3bebe18eb193941adf76053f53256', '7e84f61c521790ee185467bcb153f4ae', 0, NULL, 2, 'sys:user:save', NULL, 'bee17066f2fd403888cb3a14819fa48d', NULL, NULL, '添加用户', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-10-19 22:17:52', '30d808cbd7c94c66b4512718151b00aa', '2021-10-22 21:47:35', '30d808cbd7c94c66b4512718151b00aa');
INSERT INTO `sys_menu` VALUES ('ce632f999c5110f1136ef029b82aa791', NULL, 0, NULL, 0, NULL, '/sys', '31c23144efa4491496697c7ceb5ad8ca', 'LAYOUT', NULL, '系统管理', b'0', b'0', 'ant-design:setting-outlined', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-08-15 22:53:52', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('cf05748b508bb89d99214cf8bf243b22', 'be23e50d8fcd326a6d2819cb7837ec39', 0, NULL, 1, NULL, 'https://icon-sets.iconify.design/', '599b7df77e644e03b343876b3ba4d48a', 'IFRAME', NULL, 'Iconify', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-08-15 23:09:49', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('d1b05c6888eaedd21abcce51d2433882', 'dd5c824b9f0f61a6cd3a2b703bdffc55', 0, NULL, 1, NULL, 'generator', '5b86b3bf0928419ca983e30095252499', '/devtools/generator/index', NULL, 'Model', b'0', b'0', '', NULL, NULL, b'0', b'1', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-08-15 23:05:45', '30d808cbd7c94c66b4512718151b00aa', '2021-10-17 00:47:30', '30d808cbd7c94c66b4512718151b00aa');
INSERT INTO `sys_menu` VALUES ('d6ddaddfc99cf44167649751f6505a3a', NULL, 0, NULL, 0, NULL, '/logger', '80eef144094a4ccfa0b71b1fc73a79ed', 'LAYOUT', NULL, '日志系统', b'0', b'0', 'ant-design:file-text-outlined', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-08-15 22:55:30', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('d7f651661a658047b5485b04aebc43e6', 'dd5c824b9f0f61a6cd3a2b703bdffc55', 0, NULL, 1, NULL, 'http://127.0.0.1:8081/swagger-ui.html', 'fc38b7c3b6ba48c6a5586bf6bf357f01', 'IFRAME', NULL, 'Swagger', b'0', b'0', '', '', NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-08-21 12:40:40', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('dc6cf7cd963397678674aae58a2657b6', '7e84f61c521790ee185467bcb153f4ae', 0, NULL, 2, 'sys:user:get:page', NULL, '3101fe3a805b4c738f8cad51cf1d4b19', NULL, NULL, '分页获取用户', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-10-22 21:48:38', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('dd5c824b9f0f61a6cd3a2b703bdffc55', NULL, 0, NULL, 0, NULL, '/devtools', '8ed467e8225742578c60a5b7a1033fc3', 'LAYOUT', NULL, '开发者工具', b'0', b'0', 'ant-design:tool-outlined', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-08-15 22:51:19', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('e22f58fe26ebbccd884694f6dcc78bb5', '7e84f61c521790ee185467bcb153f4ae', 0, NULL, 1, NULL, 'UserDetail/:id', 'e14facc361874524820e73284939a9c4', '/sys/user/UserDetail', NULL, '用户详情', b'0', b'1', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-08-29 20:31:15', '30d808cbd7c94c66b4512718151b00aa', '2021-09-04 23:40:40', '30d808cbd7c94c66b4512718151b00aa');
INSERT INTO `sys_menu` VALUES ('ed5287e77f82d8cf064549bdce3728f7', NULL, 0, NULL, 0, NULL, '/dashboard', '5d8dbe9c694846c79867b1d9eb0babc3', 'LAYOUT', NULL, 'Dashboard', b'0', b'0', 'ant-design:appstore-outlined', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-08-15 22:47:20', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('ef6f1fb17b6ef1861e96f2fa12aecb89', 'ed5287e77f82d8cf064549bdce3728f7', 0, NULL, 1, NULL, 'analysis', 'b3fe0564ab824bfda37e245c88d2a6f0', '/dashboard/analysis/index', NULL, '分析页', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-08-15 23:02:01', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('f05cd7394c6940df27680bca06fbc08a', '2f8ea043f9ae0daa906ad39d57599aa6', 0, NULL, 1, NULL, 'file-store', '9ab86151dfb841739395e5e3920c0745', '/file-sys/file-store/index', NULL, '文件管理', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-08-15 23:51:41', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('febf2c72c3e1828dd24a2679ff9b9659', 'ce632f999c5110f1136ef029b82aa791', 0, NULL, 1, NULL, 'dept', '8d03ae6615464c78b7acb668da22f52d', '/sys/dept/index', NULL, '部门管理', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-08-15 23:27:33', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('ff53af146fe98bdfcc599c8e20f4c1dd', '2b56fa6bd71df15a2acaa20cb8ec58b5', 0, NULL, 1, NULL, 'settings', '572f838320a349578f9df5e83c17ccd9', '/user/settings/index', NULL, '个人设置', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-08-15 23:44:22', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色名',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色编码',
  `level` int DEFAULT NULL COMMENT '角色级别',
  `status` tinyint DEFAULT '0' COMMENT '状态【启用：0,停用：1】\n',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色描述',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人',
  `enable` bit(1) DEFAULT NULL COMMENT '是否启用【未启用：0,已启用：1】',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES ('4f276135ba8a1587156988ceff84ce9f', '系统管理员', 'admin', 10, 0, '系统管理员', NULL, '2021-07-11 18:52:05', '30d808cbd7c94c66b4512718151b00aa', '2021-08-26 22:42:13', '30d808cbd7c94c66b4512718151b00aa', b'1');
INSERT INTO `sys_role` VALUES ('6a0513946125893b73028e1d19ab3489', '普通用户', 'ordinary', 1, 0, '普通用户', NULL, '2021-07-11 19:08:42', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL, b'1');
INSERT INTO `sys_role` VALUES ('8288e8e0fc49ec42a6d83d121963af57', '超级管理员', 'administrator', 9999, 0, '系统超级管理员，最高级别管理员', NULL, '2021-07-11 18:49:42', '30d808cbd7c94c66b4512718151b00aa', '2021-10-05 21:17:34', '30d808cbd7c94c66b4512718151b00aa', b'1');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `role_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色ID',
  `menu_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单ID',
  `leaf` bit(1) DEFAULT NULL COMMENT '是否叶子节点',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` VALUES ('1cb73d7932892b2ee9b68d3380eea152', '4f276135ba8a1587156988ceff84ce9f', 'a6cf40d746f4fd88ee5f4646fd4933d0', b'1', NULL, '2021-10-25 23:09:56', 'eab5659651ea52b4a998c8722efbe9b2', NULL, NULL);
INSERT INTO `sys_role_menu` VALUES ('66bc40e76dd8a6418f85e1bad911e53c', '4f276135ba8a1587156988ceff84ce9f', 'febf2c72c3e1828dd24a2679ff9b9659', b'1', NULL, '2021-10-25 23:09:56', 'eab5659651ea52b4a998c8722efbe9b2', NULL, NULL);
INSERT INTO `sys_role_menu` VALUES ('7c501669167288db6c2ef0becc1f240d', '4f276135ba8a1587156988ceff84ce9f', '9650554e64c53b03d64f9039e0ead1c0', b'1', NULL, '2021-10-25 23:09:56', 'eab5659651ea52b4a998c8722efbe9b2', NULL, NULL);
INSERT INTO `sys_role_menu` VALUES ('7d9a6b8ceba8c86dca7896c4c3d482da', '4f276135ba8a1587156988ceff84ce9f', '009ace919a3d191932449f11fcb763b1', b'1', NULL, '2021-10-25 23:09:56', 'eab5659651ea52b4a998c8722efbe9b2', NULL, NULL);
INSERT INTO `sys_role_menu` VALUES ('854217a8b9e13720a907665fa3f3829f', '4f276135ba8a1587156988ceff84ce9f', 'ef6f1fb17b6ef1861e96f2fa12aecb89', b'1', NULL, '2021-10-25 23:09:56', 'eab5659651ea52b4a998c8722efbe9b2', NULL, NULL);
INSERT INTO `sys_role_menu` VALUES ('b8aa6c111502ef07e98b214bb58206d2', '4f276135ba8a1587156988ceff84ce9f', 'e22f58fe26ebbccd884694f6dcc78bb5', b'1', NULL, '2021-10-25 23:09:56', 'eab5659651ea52b4a998c8722efbe9b2', NULL, NULL);
INSERT INTO `sys_role_menu` VALUES ('bbc6f5c63fcdd813005464594acda449', '4f276135ba8a1587156988ceff84ce9f', '2e80fce962c46d4b5334245035851535', b'1', NULL, '2021-10-25 23:09:56', 'eab5659651ea52b4a998c8722efbe9b2', NULL, NULL);
INSERT INTO `sys_role_menu` VALUES ('bf24a3c25e5724e98d05c220e55a2aae', '4f276135ba8a1587156988ceff84ce9f', '4a86291d875bf568c5fcd8947b0d13ec', b'1', NULL, '2021-10-25 23:09:56', 'eab5659651ea52b4a998c8722efbe9b2', NULL, NULL);
INSERT INTO `sys_role_menu` VALUES ('c35f0b607ff2d14c52c9bf9354f12b95', '4f276135ba8a1587156988ceff84ce9f', '5a62974225f08b322ede180c608cf980', b'1', NULL, '2021-10-25 23:09:56', 'eab5659651ea52b4a998c8722efbe9b2', NULL, NULL);
INSERT INTO `sys_role_menu` VALUES ('c513ad176b8eaa72c40d16b7045869f0', '4f276135ba8a1587156988ceff84ce9f', '7e84f61c521790ee185467bcb153f4ae', b'0', NULL, '2021-10-25 23:09:56', 'eab5659651ea52b4a998c8722efbe9b2', NULL, NULL);
INSERT INTO `sys_role_menu` VALUES ('c93b37d807471f17467430ff92df2b8c', '4f276135ba8a1587156988ceff84ce9f', '41ce300ed10c87c39b70f2121c1cf76d', b'1', NULL, '2021-10-25 23:09:56', 'eab5659651ea52b4a998c8722efbe9b2', NULL, NULL);
INSERT INTO `sys_role_menu` VALUES ('d8f61890ce13335d49b2eadcff835f3c', '4f276135ba8a1587156988ceff84ce9f', 'dc6cf7cd963397678674aae58a2657b6', b'1', NULL, '2021-10-25 23:09:56', 'eab5659651ea52b4a998c8722efbe9b2', NULL, NULL);
INSERT INTO `sys_role_menu` VALUES ('e715fe5c0ae72fae431b8846098bb274', '4f276135ba8a1587156988ceff84ce9f', '0c5e77c3b84fbbe24bff5a9795b87317', b'1', NULL, '2021-10-25 23:09:56', 'eab5659651ea52b4a998c8722efbe9b2', NULL, NULL);
INSERT INTO `sys_role_menu` VALUES ('eefa4577ddfa5d81cc1c5b23204b16f8', '4f276135ba8a1587156988ceff84ce9f', 'ed5287e77f82d8cf064549bdce3728f7', b'0', NULL, '2021-10-25 23:09:56', 'eab5659651ea52b4a998c8722efbe9b2', NULL, NULL);
INSERT INTO `sys_role_menu` VALUES ('f13ef547dcc0233ab473573f818b9f08', '4f276135ba8a1587156988ceff84ce9f', '1f971a4677901ce44ef1f82ef647c206', b'1', NULL, '2021-10-25 23:09:56', 'eab5659651ea52b4a998c8722efbe9b2', NULL, NULL);
INSERT INTO `sys_role_menu` VALUES ('fb4536a4ca8fb0562f26cdfa165bd1f5', '4f276135ba8a1587156988ceff84ce9f', 'c8f3bebe18eb193941adf76053f53256', b'1', NULL, '2021-10-25 23:09:56', 'eab5659651ea52b4a998c8722efbe9b2', NULL, NULL);
INSERT INTO `sys_role_menu` VALUES ('fd93595cf1263669a7d6fe1830893506', '4f276135ba8a1587156988ceff84ce9f', 'ce632f999c5110f1136ef029b82aa791', b'0', NULL, '2021-10-25 23:09:56', 'eab5659651ea52b4a998c8722efbe9b2', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_student
-- ----------------------------
DROP TABLE IF EXISTS `sys_student`;
CREATE TABLE `sys_student` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `is_marry` bit(1) DEFAULT NULL COMMENT '是否已婚',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '姓名',
  `age` int DEFAULT NULL COMMENT '年龄',
  `my_height` float DEFAULT NULL COMMENT '身高',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `sex` tinyint DEFAULT '0' COMMENT '性别【男:0, 女:1】',
  `sdf` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'sdf',
  `my_money` double DEFAULT NULL COMMENT '余额',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_student
-- ----------------------------
BEGIN;
INSERT INTO `sys_student` VALUES ('0f63c8a8b624ab2888df8204106b3e3f', b'1', 'sdfsdfs', 0, 1.65, '2021-09-12 21:37:47', 0, NULL, 1.99, NULL, '2021-10-24 13:02:39', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_student` VALUES ('72f0b0661fd13ffd8f72aa40de446706', b'1', 'cxx1233', 0, 1.65, '2021-09-12 21:37:47', 0, NULL, 1.99, NULL, '2021-10-24 13:02:22', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_student` VALUES ('78c4ad023177e8eabfb898195e8150cf', b'0', 'cxx1233', 0, 1.65, '2021-09-12 21:37:47', 0, NULL, 1.99, NULL, '2021-10-24 13:02:03', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '姓名',
  `sex` int DEFAULT NULL COMMENT '性别【未设置：0,男：1,女：2】',
  `age` int DEFAULT NULL COMMENT '年龄',
  `birthday` datetime DEFAULT NULL COMMENT '出生日期',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '电话',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像',
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '所在城市',
  `last_login` datetime DEFAULT NULL COMMENT '最后登录时间',
  `dept_id` varchar(64) DEFAULT NULL COMMENT '部门ID',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人',
  `enable` bit(1) DEFAULT NULL COMMENT '是否启用【未启用：0,已启用：1】',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES ('30d808cbd7c94c66b4512718151b00aa', 'administrator', '$2a$10$eLXgj/sT2Vbq.GsWfsPCCeLlRCmsQ2bnPFsyysRAVOgj7CZM5UOB2', '成应奎', 1, 24, '2021-09-05 20:24:23', 'cxxwl96@sina.com', '15100001111', '/head/head.jpg', NULL, NULL, NULL, NULL, '2021-09-05 23:56:58', NULL, '2021-09-06 01:08:10', '30d808cbd7c94c66b4512718151b00aa', b'1');
INSERT INTO `sys_user` VALUES ('eab5659651ea52b4a998c8722efbe9b2', 'cxx', '$2a$10$rwuPt.Tn6zQhXwDBULEDoO9.7ZhqdXfuLanTunruWBAvaukuOwbKO', 'cxx', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-09-05 23:24:08', NULL, '2021-10-17 22:53:17', '30d808cbd7c94c66b4512718151b00aa', b'1');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户ID',
  `role_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色ID',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人',
  `enable` bit(1) DEFAULT NULL COMMENT '是否启用【未启用：0,已启用：1】',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES ('28576fdb37e36fd06e84f80cf6324587', '30d808cbd7c94c66b4512718151b00aa', '8288e8e0fc49ec42a6d83d121963af57', NULL, '2021-09-06 01:08:10', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL, NULL);
INSERT INTO `sys_user_role` VALUES ('6583428dd21d5849a3f508580d3328d2', 'eab5659651ea52b4a998c8722efbe9b2', '4f276135ba8a1587156988ceff84ce9f', NULL, '2021-10-17 22:53:17', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
