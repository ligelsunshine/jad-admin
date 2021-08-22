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

 Date: 03/07/2021 15:17:01
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for db_database
-- ----------------------------
DROP TABLE IF EXISTS `db_database`;
CREATE TABLE `db_database`
(
    `id`                varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
    `name`              varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '数据库中文名称',
    `type`              varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '类型',
    `host`              varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '地址',
    `port`              varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '端口',
    `db_name`           varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '数据库名称',
    `username`          varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '用户',
    `password`          varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '密码',
    `url`               varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '连接字符串',
    `driver_class_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '驱动类',
    `is_sys_db`         tinyint(1) DEFAULT '0' COMMENT '是否是系统数据库',
    `remark`            varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
    `create_time`       datetime                                                DEFAULT NULL COMMENT '创建时间',
    `create_by`         varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '创建人',
    `update_time`       datetime                                                DEFAULT NULL COMMENT '更新时间',
    `update_by`         varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '更新人',
    `enable`            bit(1)                                                  DEFAULT NULL COMMENT '是否启用【未启用：0,已启用：1】',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of db_database
-- ----------------------------
BEGIN;
INSERT INTO `db_database`
VALUES ('0e8b903533e144ba8c8f80b2f2bc1d5a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        '2020-09-13 21:52:28', NULL, '2020-09-14 22:29:18', NULL, b'1');
INSERT INTO `db_database`
VALUES ('4cce7499ce904f12bdd71ee438b4dd36', NULL, 'MySql', 'host', 'port', 'dbName', 'username', 'password',
        'connect_str', 'driverClassName', 1, 'remark', '2020-09-11 15:10:24', NULL, '2020-09-13 21:17:44', NULL, b'0');
INSERT INTO `db_database`
VALUES ('715277d876794548858b37e0a4c013f6', 'string', 'MySql', 'string', 'string', 'string', 'string', 'string',
        'string', 'string', 0, 'string', '2020-09-13 21:35:08', NULL, NULL, NULL, b'1');
INSERT INTO `db_database`
VALUES ('a61cf12391f4497088bc0c1b8ccd7522', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        '2020-09-13 21:40:26', NULL, '2020-09-14 22:27:54', NULL, b'1');
INSERT INTO `db_database`
VALUES ('adc4368453684453a5918fce6a4357af', 'string', 'MySql', 'string', 'string', 'string', 'string', 'string',
        'string', 'string', 0, 'string', '2020-09-13 21:39:21', NULL, '2020-09-14 23:39:51', NULL, b'0');
COMMIT;

-- ----------------------------
-- Table structure for form_base_field
-- ----------------------------
DROP TABLE IF EXISTS `form_base_field`;
CREATE TABLE `form_base_field`
(
    `id`                  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
    `table_id`            varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '表单id',
    `name`                varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '中文名称',
    `is_main`             tinyint(1) DEFAULT '0' COMMENT '是否为主属性',
    `attribute_name`      varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '实体属性名称',
    `attribute_data_type` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '实体属性数据类型（基本数据类型）',
    `field_name`          varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '表单字段名称',
    `field_data_type`     varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '表单字段数据类型（数据库数据类型）',
    `field_length`        int                                                     DEFAULT NULL COMMENT '表单字段长度',
    `field_decimal_point` int                                                     DEFAULT NULL COMMENT '表单字段精确小数',
    `field_not_null`      tinyint(1) DEFAULT '0' COMMENT '表单字段是否不为空',
    `field_default_value` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '表单字段默认值',
    `sort_num`            int                                                     DEFAULT NULL COMMENT '排序数',
    `control_type`        varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '控件类型',
    `tag`                 varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '标识数据/枚举数据，例如：枚举数据 男=0,女=1',
    `placeholder`         varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '控件placeholder',
    `regular_txt`         varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '数据正则表达式验证',
    `regular_msg`         varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '数据正则表达式错误提示',
    `remark`              varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
    `create_time`         datetime                                                DEFAULT NULL COMMENT '创建时间',
    `create_by`           varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '创建人',
    `update_time`         datetime                                                DEFAULT NULL COMMENT '更新时间',
    `update_by`           varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '更新人',
    `enable`              bit(1)                                                  DEFAULT NULL COMMENT '是否启用【未启用：0,已启用：1】',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of form_base_field
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for form_field
-- ----------------------------
DROP TABLE IF EXISTS `form_field`;
CREATE TABLE `form_field`
(
    `id`                  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
    `table_id`            varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '表单id',
    `name`                varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '中文名称',
    `is_main`             tinyint(1) DEFAULT '0' COMMENT '是否为主属性',
    `attribute_name`      varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '实体属性名称',
    `attribute_data_type` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '实体属性数据类型（基本数据类型）',
    `field_name`          varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '表单字段名称',
    `field_data_type`     varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '表单字段数据类型（数据库数据类型）',
    `field_length`        int                                                     DEFAULT NULL COMMENT '表单字段长度',
    `field_decimal_point` int                                                     DEFAULT NULL COMMENT '表单字段精确小数',
    `field_not_null`      tinyint(1) DEFAULT '0' COMMENT '表单字段是否不为空',
    `field_default_value` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '表单字段默认值',
    `sort_num`            int                                                     DEFAULT NULL COMMENT '排序数',
    `control_type`        varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '控件类型',
    `tag`                 varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '标识数据/枚举数据，例如：枚举数据 男=0,女=1',
    `placeholder`         varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '控件placeholder',
    `regular_txt`         varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '数据正则表达式验证',
    `regular_msg`         varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '数据正则表达式错误提示',
    `remark`              varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
    `create_time`         datetime                                                DEFAULT NULL COMMENT '创建时间',
    `create_by`           varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '创建人',
    `update_time`         datetime                                                DEFAULT NULL COMMENT '更新时间',
    `update_by`           varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '更新人',
    `enable`              bit(1)                                                  DEFAULT NULL COMMENT '是否启用【未启用：0,已启用：1】',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of form_field
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for form_table
-- ----------------------------
DROP TABLE IF EXISTS `form_table`;
CREATE TABLE `form_table`
(
    `id`          varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
    `database_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '数据库id',
    `namespace`   varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '命名空间',
    `name`        varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '中文名称',
    `entity_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '实体名称',
    `table_name`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '表单名称（命名空间_实体名称）',
    `isld`        tinyint(1) DEFAULT '0' COMMENT '是否逻辑删除',
    `isfk`        tinyint(1) DEFAULT '0' COMMENT '是否作为外键表',
    `remark`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
    `create_time` datetime                                                DEFAULT NULL COMMENT '创建时间',
    `create_by`   varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '创建人',
    `update_time` datetime                                                DEFAULT NULL COMMENT '更新时间',
    `update_by`   varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '更新人',
    `enable`      bit(1)                                                  DEFAULT NULL COMMENT '是否启用【未启用：0,已启用：1】',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of form_table
-- ----------------------------
BEGIN;
INSERT INTO `form_table`
VALUES ('c66ffa3acc3a4fabbfd825a879f39940', 'a0c0ca7b35da47cca18dcc61a76fface', 'form', '表单字段', 'FromField',
        'form_field', 0, 0, '表单字段', '2020-09-10 15:54:52', NULL, '2020-09-10 16:17:21', NULL, b'1');
INSERT INTO `form_table`
VALUES ('sdf', NULL, NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, b'0');
COMMIT;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`
(
    `id`          varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
    `p_id`        varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '父级菜单ID',
    `name`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '姓名',
    `path`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单URL',
    `permissions` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
    `component`   varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '前端组件URL',
    `type`        tinyint                                                 DEFAULT NULL COMMENT '类型 [0：目录 1：菜单 2：按钮]',
    `icon`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单图标',
    `order_num`   int                                                     DEFAULT NULL COMMENT '排序',
    `remark`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
    `create_time` datetime                                                DEFAULT NULL COMMENT '创建时间',
    `create_by`   varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '创建人',
    `update_time` datetime                                                DEFAULT NULL COMMENT '更新时间',
    `update_by`   varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '更新人',
    `enable`      bit(1)                                                  DEFAULT NULL COMMENT '是否启用【未启用：0,已启用：1】',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `id`          varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '描述',
    `name`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色名',
    `code`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色编码',
    `level`       int                                                     DEFAULT NULL COMMENT '角色级别',
    `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色描述',
    `remark`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
    `create_time` datetime                                                DEFAULT NULL COMMENT '创建时间',
    `create_by`   varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '创建人',
    `update_time` datetime                                                DEFAULT NULL COMMENT '更新时间',
    `update_by`   varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '更新人',
    `enable`      bit(1)                                                  DEFAULT NULL COMMENT '是否启用【未启用：0,已启用：1】',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`
(
    `id`          varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '描述',
    `role_id`     varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '角色ID',
    `menu_id`     varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '菜单',
    `remark`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
    `create_time` datetime                                                DEFAULT NULL COMMENT '创建时间',
    `create_by`   varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '创建人',
    `update_time` datetime                                                DEFAULT NULL COMMENT '更新时间',
    `update_by`   varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '更新人',
    `enable`      bit(1)                                                  DEFAULT NULL COMMENT '是否启用【未启用：0,已启用：1】',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `id`          varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
    `username`    varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '账号',
    `password`    varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码',
    `name`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '姓名',
    `sex`         varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '性别',
    `age`         int                                                     DEFAULT NULL COMMENT '年龄',
    `birthday`    datetime                                                DEFAULT NULL COMMENT '出生日期',
    `email`       varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
    `phone`       varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '电话',
    `avatar`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像',
    `city`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '所在城市',
    `last_login`  datetime                                                DEFAULT NULL COMMENT '最后登录时间',
    `remark`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
    `create_time` datetime                                                DEFAULT NULL COMMENT '创建时间',
    `create_by`   varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '创建人',
    `update_time` datetime                                                DEFAULT NULL COMMENT '更新时间',
    `update_by`   varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '更新人',
    `enable`      bit(1)                                                  DEFAULT NULL COMMENT '是否启用【未启用：0,已启用：1】',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user`
VALUES ('30d808cbd7c94c66b4512718151b00aa', 'admin', '$2a$10$eLXgj/sT2Vbq.GsWfsPCCeLlRCmsQ2bnPFsyysRAVOgj7CZM5UOB2',
        '成应奎', '男', 24, '2020-09-04 22:57:46', 'cxxwl96@sina.com', '15100001111', '/head/head.jpg', NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, b'1');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`
(
    `id`          varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '描述',
    `user_id`     varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '用户ID',
    `role_id`     varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '角色ID',
    `remark`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
    `create_time` datetime                                                DEFAULT NULL COMMENT '创建时间',
    `create_by`   varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '创建人',
    `update_time` datetime                                                DEFAULT NULL COMMENT '更新时间',
    `update_by`   varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '更新人',
    `enable`      bit(1)                                                  DEFAULT NULL COMMENT '是否启用【未启用：0,已启用：1】',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
COMMIT;

SET
FOREIGN_KEY_CHECKS = 1;
