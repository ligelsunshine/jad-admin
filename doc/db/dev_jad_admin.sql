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

 Date: 18/12/2021 12:55:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for devtools_generate
-- ----------------------------
DROP TABLE IF EXISTS `devtools_generate`;
CREATE TABLE `devtools_generate`
(
    `id`          varchar(64) NOT NULL COMMENT '主键',
    `ds`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '数据源',
    `module`      varchar(255)                                                  DEFAULT NULL COMMENT 'module',
    `title`       varchar(255)                                                  DEFAULT NULL COMMENT 'title',
    `name`        varchar(255)                                                  DEFAULT NULL COMMENT 'name',
    `namespace`   varchar(255)                                                  DEFAULT NULL COMMENT 'namespace',
    `main_field`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'main_field',
    `tree_model`  bit(1)                                                        DEFAULT NULL COMMENT 'tree_model',
    `logic`       bit(1)                                                        DEFAULT NULL COMMENT 'logic',
    `model`       text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '模型',
    `remark`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci       DEFAULT NULL COMMENT '备注',
    `create_time` datetime                                                      DEFAULT NULL COMMENT '创建时间',
    `create_by`   varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci        DEFAULT NULL COMMENT '创建人',
    `update_time` datetime                                                      DEFAULT NULL COMMENT '更新时间',
    `update_by`   varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci        DEFAULT NULL COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of devtools_generate
-- ----------------------------
BEGIN;
INSERT INTO `devtools_generate`
VALUES ('38ba1e5cfcde5021c5902586c4f12648', 'master', 'common', '数据分类', 'DataClassify', 'sys', NULL, b'1', b'0',
        '{\"bigHump\":\"DataClassify\",\"fieldSchema\":[{\"bigHump\":\"Title\",\"component\":\"Input\",\"enumVal\":[],\"id\":\"1772b71d21124202bcce14d0526661ce\",\"lowerCaseUnderline\":\"title\",\"name\":\"title\",\"orderNo\":0,\"presents\":[\"ADD_FORM\",\"EDIT_FORM\",\"DETAIL\"],\"require\":true,\"rules\":[],\"smallHump\":\"title\",\"title\":\"标题\",\"type\":\"STRING\",\"upperCaseUnderline\":\"TITLE\"}],\"logic\":false,\"lowerCaseDash\":\"data-classify\",\"lowerCaseUnderline\":\"data_classify\",\"module\":\"common\",\"name\":\"DataClassify\",\"namespace\":\"sys\",\"namespaceBigHump\":\"Sys\",\"namespaceLowerCaseDash\":\"sys\",\"namespaceLowerCaseUnderline\":\"sys\",\"namespaceSmallHump\":\"sys\",\"smallHump\":\"dataClassify\",\"title\":\"数据分类\",\"treeModel\":true}',
        NULL, '2021-11-11 23:14:51', '30d808cbd7c94c66b4512718151b00aa', '2021-11-12 20:46:57',
        '30d808cbd7c94c66b4512718151b00aa');
INSERT INTO `devtools_generate`
VALUES ('41a5c2269b0c9038ecaecc0e085f9ee1', 'master', 'common', '字典数据', 'dictData', 'sys', NULL, b'0', b'0',
        '{\"bigHump\":\"DictData\",\"fieldSchema\":[{\"bigHump\":\"DictId\",\"component\":\"Input\",\"enumVal\":[],\"id\":\"4d824909cf674f6a846acdcb86b95262\",\"lowerCaseUnderline\":\"dict_id\",\"name\":\"dictId\",\"orderNo\":-1,\"presents\":[\"EDIT_FORM\",\"ADD_FORM\"],\"require\":true,\"rules\":[],\"smallHump\":\"dictId\",\"title\":\"字典ID\",\"type\":\"STRING\",\"upperCaseUnderline\":\"DICT_ID\"},{\"bigHump\":\"Name\",\"component\":\"Input\",\"enumVal\":[],\"id\":\"9b2f90319ee34c0a933532be23252acd\",\"lowerCaseUnderline\":\"name\",\"name\":\"name\",\"orderNo\":0,\"presents\":[\"EDIT_FORM\",\"DETAIL\",\"SEARCH_FORM\",\"ADD_FORM\",\"LIST\"],\"require\":true,\"rules\":[],\"smallHump\":\"name\",\"title\":\"名称\",\"type\":\"STRING\",\"upperCaseUnderline\":\"NAME\"},{\"bigHump\":\"Value\",\"component\":\"Input\",\"enumVal\":[],\"id\":\"f802d9ed769447148efadd44d867c0e8\",\"lowerCaseUnderline\":\"value\",\"name\":\"value\",\"orderNo\":0,\"presents\":[\"EDIT_FORM\",\"DETAIL\",\"SEARCH_FORM\",\"ADD_FORM\",\"LIST\"],\"require\":true,\"rules\":[],\"smallHump\":\"value\",\"title\":\"数据值\",\"type\":\"STRING\",\"upperCaseUnderline\":\"VALUE\"},{\"bigHump\":\"OrderNo\",\"component\":\"InputNumber\",\"enumVal\":[],\"id\":\"f6fa1c3fa29949c19eb19405c5ccd303\",\"lowerCaseUnderline\":\"order_no\",\"name\":\"orderNo\",\"orderNo\":1,\"presents\":[\"EDIT_FORM\",\"DETAIL\",\"ADD_FORM\",\"LIST\"],\"require\":false,\"rules\":[],\"smallHump\":\"orderNo\",\"title\":\"排序\",\"type\":\"INT\",\"upperCaseUnderline\":\"ORDER_NO\"}],\"logic\":false,\"lowerCaseDash\":\"dict-data\",\"lowerCaseUnderline\":\"dict_data\",\"module\":\"common\",\"name\":\"dictData\",\"namespace\":\"sys\",\"namespaceBigHump\":\"Sys\",\"namespaceLowerCaseDash\":\"sys\",\"namespaceLowerCaseUnderline\":\"sys\",\"namespaceSmallHump\":\"sys\",\"smallHump\":\"dictData\",\"title\":\"字典数据\",\"treeModel\":false}',
        NULL, '2021-11-05 20:52:46', '30d808cbd7c94c66b4512718151b00aa', '2021-11-05 20:59:55',
        '30d808cbd7c94c66b4512718151b00aa');
INSERT INTO `devtools_generate`
VALUES ('5438993480059b4879e8de1b2731ac4d', 'slave', 'common', '数据源', 'datasource', 'sys', NULL, b'0', b'0',
        '{\"bigHump\":\"Datasource\",\"fieldSchema\":[{\"bigHump\":\"Name\",\"component\":\"Input\",\"enumVal\":[{\"bigHump\":\"Oracle\",\"lowerCaseUnderline\":\"oracle\",\"name\":\"Oracle\",\"smallHump\":\"oracle\",\"title\":\"Oracle\",\"upperCaseUnderline\":\"ORACLE\"},{\"bigHump\":\"MySQL\",\"lowerCaseUnderline\":\"my_s_q_l\",\"name\":\"MySQL\",\"smallHump\":\"mySQL\",\"title\":\"MySQL\",\"upperCaseUnderline\":\"MY_S_Q_L\"},{\"bigHump\":\"DB2\",\"lowerCaseUnderline\":\"d_b_2\",\"name\":\"DB2\",\"smallHump\":\"dB2\",\"title\":\"DB2\",\"upperCaseUnderline\":\"D_B_2\"},{\"bigHump\":\"PostgreSQL\",\"lowerCaseUnderline\":\"postgre_s_q_l\",\"name\":\"PostgreSQL\",\"smallHump\":\"postgreSQL\",\"title\":\"PostgreSQL\",\"upperCaseUnderline\":\"POSTGRE_S_Q_L\"},{\"bigHump\":\"H2\",\"lowerCaseUnderline\":\"h_2\",\"name\":\"H2\",\"smallHump\":\"h2\",\"title\":\"H2\",\"upperCaseUnderline\":\"H_2\"}],\"id\":\"3cefd9d553df44c89e122b1ba302ebac\",\"lowerCaseUnderline\":\"name\",\"name\":\"name\",\"orderNo\":-2,\"presents\":[\"ADD_FORM\",\"LIST\",\"EDIT_FORM\",\"DETAIL\",\"SEARCH_FORM\"],\"require\":false,\"rules\":[],\"smallHump\":\"name\",\"title\":\"数据库名称\",\"type\":\"STRING\",\"upperCaseUnderline\":\"NAME\"},{\"bigHump\":\"Type\",\"component\":\"Select\",\"defaultVal\":\"mysql\",\"enumVal\":[{\"bigHump\":\"Oracle\",\"lowerCaseUnderline\":\"oracle\",\"name\":\"oracle\",\"smallHump\":\"oracle\",\"title\":\"Oracle\",\"upperCaseUnderline\":\"ORACLE\"},{\"bigHump\":\"Mysql\",\"lowerCaseUnderline\":\"mysql\",\"name\":\"mysql\",\"smallHump\":\"mysql\",\"title\":\"MySQL\",\"upperCaseUnderline\":\"MYSQL\"},{\"bigHump\":\"Db2\",\"lowerCaseUnderline\":\"db_2\",\"name\":\"db2\",\"smallHump\":\"db2\",\"title\":\"DB2\",\"upperCaseUnderline\":\"DB_2\"},{\"bigHump\":\"Posstgresql\",\"lowerCaseUnderline\":\"posstgresql\",\"name\":\"posstgresql\",\"smallHump\":\"posstgresql\",\"title\":\"PostgreSQL\",\"upperCaseUnderline\":\"POSSTGRESQL\"},{\"bigHump\":\"H2\",\"lowerCaseUnderline\":\"h_2\",\"name\":\"h2\",\"smallHump\":\"h2\",\"title\":\"H2\",\"upperCaseUnderline\":\"H_2\"}],\"id\":\"a70935a671104677a28986e419dc7a96\",\"lowerCaseUnderline\":\"type\",\"name\":\"type\",\"orderNo\":-1,\"presents\":[\"ADD_FORM\",\"LIST\",\"EDIT_FORM\",\"DETAIL\",\"SEARCH_FORM\"],\"require\":false,\"rules\":[],\"smallHump\":\"type\",\"title\":\"类型\",\"type\":\"ENUM\",\"upperCaseUnderline\":\"TYPE\"},{\"bigHump\":\"Url\",\"component\":\"Input\",\"enumVal\":[],\"id\":\"f8a479feced5463b8df1a7786eebdf62\",\"lowerCaseUnderline\":\"url\",\"name\":\"url\",\"orderNo\":0,\"presents\":[\"ADD_FORM\",\"LIST\",\"EDIT_FORM\",\"DETAIL\",\"SEARCH_FORM\"],\"require\":true,\"rules\":[],\"smallHump\":\"url\",\"title\":\"URL\",\"type\":\"STRING\",\"upperCaseUnderline\":\"URL\"},{\"bigHump\":\"Username\",\"component\":\"Input\",\"enumVal\":[],\"id\":\"4ce7919a1ea84deda2f7876d900b8ed7\",\"lowerCaseUnderline\":\"username\",\"name\":\"username\",\"orderNo\":0,\"presents\":[\"ADD_FORM\",\"LIST\",\"EDIT_FORM\",\"DETAIL\",\"SEARCH_FORM\"],\"require\":true,\"rules\":[],\"smallHump\":\"username\",\"title\":\"用户名\",\"type\":\"STRING\",\"upperCaseUnderline\":\"USERNAME\"},{\"bigHump\":\"Password\",\"component\":\"Input\",\"enumVal\":[],\"id\":\"4aeeaaedf76a481186b79dcf4c9db6f4\",\"lowerCaseUnderline\":\"password\",\"name\":\"password\",\"orderNo\":0,\"presents\":[\"ADD_FORM\",\"LIST\",\"EDIT_FORM\",\"DETAIL\"],\"require\":true,\"rules\":[],\"smallHump\":\"password\",\"title\":\"密码\",\"type\":\"STRING\",\"upperCaseUnderline\":\"PASSWORD\"}],\"logic\":false,\"lowerCaseDash\":\"datasource\",\"lowerCaseUnderline\":\"datasource\",\"module\":\"common\",\"name\":\"datasource\",\"namespace\":\"sys\",\"namespaceBigHump\":\"Sys\",\"namespaceLowerCaseDash\":\"sys\",\"namespaceLowerCaseUnderline\":\"sys\",\"namespaceSmallHump\":\"sys\",\"smallHump\":\"datasource\",\"title\":\"数据源\",\"treeModel\":false}',
        NULL, '2021-10-16 23:43:52', NULL, '2021-10-17 22:17:07', NULL);
INSERT INTO `devtools_generate`
VALUES ('8b20592d78466b272c3af3f21ea0fa3d', 'master', 'file-store', '对象存储', 'FileStore', 'sys', NULL, b'0', b'0',
        '{\"bigHump\":\"FileStore\",\"fieldSchema\":[{\"bigHump\":\"GroupId\",\"component\":\"Input\",\"enumVal\":[],\"id\":\"2eeb0813b3214347b7698fa227bae130\",\"lowerCaseUnderline\":\"group_id\",\"name\":\"groupId\",\"orderNo\":0,\"presents\":[\"LIST\",\"EDIT_FORM\",\"DETAIL\",\"ADD_FORM\"],\"require\":false,\"rules\":[],\"smallHump\":\"groupId\",\"title\":\"分组\",\"type\":\"STRING\",\"upperCaseUnderline\":\"GROUP_ID\"},{\"bigHump\":\"Name\",\"component\":\"Input\",\"enumVal\":[],\"id\":\"e2af2db533934be5af2542cafb78c6d4\",\"lowerCaseUnderline\":\"name\",\"name\":\"name\",\"orderNo\":0,\"presents\":[\"LIST\",\"SEARCH_FORM\",\"EDIT_FORM\",\"DETAIL\",\"ADD_FORM\"],\"require\":false,\"rules\":[],\"smallHump\":\"name\",\"title\":\"名称\",\"type\":\"STRING\",\"upperCaseUnderline\":\"NAME\"},{\"bigHump\":\"Type\",\"component\":\"Input\",\"enumVal\":[],\"id\":\"77138ba98cf843c2a57a6721972f9889\",\"lowerCaseUnderline\":\"type\",\"name\":\"type\",\"orderNo\":0,\"presents\":[\"LIST\",\"EDIT_FORM\",\"DETAIL\",\"ADD_FORM\"],\"require\":false,\"rules\":[],\"smallHump\":\"type\",\"title\":\"类型\",\"type\":\"STRING\",\"upperCaseUnderline\":\"TYPE\"},{\"bigHump\":\"Size\",\"component\":\"InputNumber\",\"enumVal\":[],\"id\":\"70914b847c6c41b492986193dbf0382d\",\"lowerCaseUnderline\":\"size\",\"name\":\"size\",\"orderNo\":0,\"presents\":[\"LIST\",\"EDIT_FORM\",\"DETAIL\",\"ADD_FORM\"],\"require\":false,\"rules\":[],\"smallHump\":\"size\",\"title\":\"大小\",\"type\":\"LONG\",\"upperCaseUnderline\":\"SIZE\"},{\"bigHump\":\"Path\",\"component\":\"Input\",\"enumVal\":[],\"id\":\"7a3e95892f9e405a9cff7b6bf541957c\",\"lowerCaseUnderline\":\"path\",\"name\":\"path\",\"orderNo\":0,\"presents\":[\"LIST\",\"EDIT_FORM\",\"DETAIL\",\"ADD_FORM\"],\"require\":false,\"rules\":[],\"smallHump\":\"path\",\"title\":\"路径\",\"type\":\"STRING\",\"upperCaseUnderline\":\"PATH\"},{\"bigHump\":\"Md5\",\"component\":\"Input\",\"enumVal\":[],\"id\":\"6f47beb6b71c45f2a1873b388b9fb13f\",\"lowerCaseUnderline\":\"md_5\",\"name\":\"md5\",\"orderNo\":0,\"presents\":[\"LIST\",\"EDIT_FORM\",\"DETAIL\",\"ADD_FORM\"],\"require\":false,\"rules\":[],\"smallHump\":\"md5\",\"title\":\"MD5\",\"type\":\"STRING\",\"upperCaseUnderline\":\"MD_5\"},{\"bigHump\":\"Memi\",\"component\":\"Input\",\"enumVal\":[],\"id\":\"87bba2460e9e4a68849b13768b13be0d\",\"lowerCaseUnderline\":\"memi\",\"name\":\"memi\",\"orderNo\":1,\"presents\":[\"LIST\",\"EDIT_FORM\",\"DETAIL\",\"ADD_FORM\"],\"require\":false,\"rules\":[],\"smallHump\":\"memi\",\"title\":\"MEMI类型\",\"type\":\"STRING\",\"upperCaseUnderline\":\"MEMI\"},{\"bigHump\":\"AccessPolicy\",\"component\":\"Select\",\"enumVal\":[{\"bigHump\":\"Public\",\"lowerCaseUnderline\":\"public\",\"name\":\"public\",\"smallHump\":\"public\",\"title\":\"公开\",\"upperCaseUnderline\":\"PUBLIC\"},{\"bigHump\":\"Private\",\"lowerCaseUnderline\":\"private\",\"name\":\"private\",\"smallHump\":\"private\",\"title\":\"私有\",\"upperCaseUnderline\":\"PRIVATE\"}],\"id\":\"00a587ab23de45cda0fdabbf9d702fb0\",\"lowerCaseUnderline\":\"access_policy\",\"name\":\"accessPolicy\",\"orderNo\":2,\"presents\":[\"LIST\",\"EDIT_FORM\",\"DETAIL\",\"ADD_FORM\"],\"require\":false,\"rules\":[],\"smallHump\":\"accessPolicy\",\"title\":\"访问策略\",\"type\":\"ENUM\",\"upperCaseUnderline\":\"ACCESS_POLICY\"},{\"bigHump\":\"Store\",\"component\":\"Select\",\"enumVal\":[{\"bigHump\":\"Local\",\"lowerCaseUnderline\":\"local\",\"name\":\"local\",\"smallHump\":\"local\",\"title\":\"本地存储\",\"upperCaseUnderline\":\"LOCAL\"},{\"bigHump\":\"Minio\",\"lowerCaseUnderline\":\"minio\",\"name\":\"minio\",\"smallHump\":\"minio\",\"title\":\"minio存储\",\"upperCaseUnderline\":\"MINIO\"},{\"bigHump\":\"Qiniu\",\"lowerCaseUnderline\":\"qiniu\",\"name\":\"qiniu\",\"smallHump\":\"qiniu\",\"title\":\"七牛云\",\"upperCaseUnderline\":\"QINIU\"},{\"bigHump\":\"Aliyun\",\"lowerCaseUnderline\":\"aliyun\",\"name\":\"aliyun\",\"smallHump\":\"aliyun\",\"title\":\"阿里云\",\"upperCaseUnderline\":\"ALIYUN\"},{\"bigHump\":\"Tencent\",\"lowerCaseUnderline\":\"tencent\",\"name\":\"tencent\",\"smallHump\":\"tencent\",\"title\":\"腾讯云\",\"upperCaseUnderline\":\"TENCENT\"}],\"id\":\"14a63ef2848b41389ba4f5ea4542327e\",\"lowerCaseUnderline\":\"store\",\"name\":\"store\",\"orderNo\":3,\"presents\":[\"LIST\",\"SEARCH_FORM\",\"EDIT_FORM\",\"DETAIL\",\"ADD_FORM\"],\"require\":false,\"rules\":[],\"smallHump\":\"store\",\"title\":\"存储源\",\"type\":\"ENUM\",\"upperCaseUnderline\":\"STORE\"}],\"logic\":false,\"lowerCaseDash\":\"file-store\",\"lowerCaseUnderline\":\"file_store\",\"module\":\"file-store\",\"name\":\"FileStore\",\"namespace\":\"sys\",\"namespaceBigHump\":\"Sys\",\"namespaceLowerCaseDash\":\"sys\",\"namespaceLowerCaseUnderline\":\"sys\",\"namespaceSmallHump\":\"sys\",\"smallHump\":\"fileStore\",\"title\":\"对象存储\",\"treeModel\":false}',
        NULL, '2021-11-19 23:22:27', '30d808cbd7c94c66b4512718151b00aa', '2021-11-19 23:23:38',
        '30d808cbd7c94c66b4512718151b00aa');
INSERT INTO `devtools_generate`
VALUES ('9ad727da1fa0c7e3dba3093dcf1d12d0', 'master', 'common', '组件测试', 'ComponetTest', 'sys', NULL, b'0', b'0',
        '{\"bigHump\":\"ComponetTest\",\"fieldSchema\":[{\"bigHump\":\"Week\",\"component\":\"Input\",\"enumVal\":[],\"id\":\"32552aa9cb4d40df85dacc8380a450c5\",\"lowerCaseUnderline\":\"week\",\"name\":\"week\",\"orderNo\":0,\"presents\":[\"LIST\",\"DETAIL\",\"ADD_FORM\",\"EDIT_FORM\"],\"require\":false,\"rules\":[],\"smallHump\":\"week\",\"title\":\"周\",\"type\":\"STRING\",\"upperCaseUnderline\":\"WEEK\"},{\"bigHump\":\"Classify\",\"component\":\"Input\",\"enumVal\":[],\"id\":\"4f1904df2ff1496d8a31a52393b7529c\",\"lowerCaseUnderline\":\"classify\",\"name\":\"classify\",\"orderNo\":0,\"presents\":[\"LIST\",\"DETAIL\",\"ADD_FORM\",\"EDIT_FORM\"],\"require\":false,\"rules\":[],\"smallHump\":\"classify\",\"title\":\"分类\",\"type\":\"STRING\",\"upperCaseUnderline\":\"CLASSIFY\"}],\"logic\":false,\"lowerCaseDash\":\"componet-test\",\"lowerCaseUnderline\":\"componet_test\",\"module\":\"common\",\"name\":\"ComponetTest\",\"namespace\":\"sys\",\"namespaceBigHump\":\"Sys\",\"namespaceLowerCaseDash\":\"sys\",\"namespaceLowerCaseUnderline\":\"sys\",\"namespaceSmallHump\":\"sys\",\"smallHump\":\"componetTest\",\"title\":\"组件测试\",\"treeModel\":false}',
        NULL, '2021-11-13 20:33:12', '30d808cbd7c94c66b4512718151b00aa', '2021-11-13 20:41:41',
        '30d808cbd7c94c66b4512718151b00aa');
INSERT INTO `devtools_generate`
VALUES ('dc77126dab059353ed5e75f632404241', 'master', 'system', '自定义组件', 'CustomComponentTest', 'sys', NULL, b'0', b'0',
        '{\"bigHump\":\"CustomComponentTest\",\"fieldSchema\":[{\"bigHump\":\"SinglePic\",\"component\":\"Upload\",\"enumVal\":[],\"id\":\"350d0b21ef6642c3a28cba4147504269\",\"lowerCaseUnderline\":\"single_pic\",\"name\":\"singlePic\",\"orderNo\":0,\"presents\":[\"EDIT_FORM\",\"ADD_FORM\",\"DETAIL\",\"LIST\"],\"require\":false,\"rules\":[],\"smallHump\":\"singlePic\",\"title\":\"单图片\",\"type\":\"STRING\",\"upperCaseUnderline\":\"SINGLE_PIC\"},{\"bigHump\":\"MultiplePic\",\"component\":\"Upload\",\"enumVal\":[],\"id\":\"1a0fdcd3761b4e7a930c0cda19c6574a\",\"lowerCaseUnderline\":\"multiple_pic\",\"name\":\"multiplePic\",\"orderNo\":0,\"presents\":[\"EDIT_FORM\",\"ADD_FORM\",\"DETAIL\",\"LIST\"],\"require\":false,\"rules\":[],\"smallHump\":\"multiplePic\",\"title\":\"多图片\",\"type\":\"STRING\",\"upperCaseUnderline\":\"MULTIPLE_PIC\"},{\"bigHump\":\"SingleFile\",\"component\":\"Upload\",\"enumVal\":[],\"id\":\"7cfd16dee60043e09b3ceae7522eb069\",\"lowerCaseUnderline\":\"single_file\",\"name\":\"singleFile\",\"orderNo\":0,\"presents\":[\"EDIT_FORM\",\"ADD_FORM\",\"DETAIL\",\"LIST\"],\"require\":false,\"rules\":[],\"smallHump\":\"singleFile\",\"title\":\"单文件\",\"type\":\"STRING\",\"upperCaseUnderline\":\"SINGLE_FILE\"},{\"bigHump\":\"MultipleFile\",\"component\":\"Upload\",\"enumVal\":[],\"id\":\"1056312141494f24a9419ad890387b69\",\"lowerCaseUnderline\":\"multiple_file\",\"name\":\"multipleFile\",\"orderNo\":0,\"presents\":[\"EDIT_FORM\",\"ADD_FORM\",\"DETAIL\",\"LIST\"],\"require\":false,\"rules\":[],\"smallHump\":\"multipleFile\",\"title\":\"多文件\",\"type\":\"STRING\",\"upperCaseUnderline\":\"MULTIPLE_FILE\"}],\"logic\":false,\"lowerCaseDash\":\"custom-component-test\",\"lowerCaseUnderline\":\"custom_component_test\",\"module\":\"system\",\"moduleLowerCase\":\"system\",\"name\":\"CustomComponentTest\",\"namespace\":\"sys\",\"namespaceBigHump\":\"Sys\",\"namespaceLowerCaseDash\":\"sys\",\"namespaceLowerCaseUnderline\":\"sys\",\"namespaceSmallHump\":\"sys\",\"smallHump\":\"customComponentTest\",\"title\":\"自定义组件\",\"treeModel\":false}',
        NULL, '2021-12-12 22:06:42', '30d808cbd7c94c66b4512718151b00aa', '2021-12-12 22:08:35',
        '30d808cbd7c94c66b4512718151b00aa');
INSERT INTO `devtools_generate`
VALUES ('f39af217e6395248fcc0d76e24ab9d96', 'master', 'common', '字典', 'dict', 'sys', NULL, b'0', b'0',
        '{\"bigHump\":\"Dict\",\"fieldSchema\":[{\"bigHump\":\"Title\",\"component\":\"Input\",\"enumVal\":[],\"id\":\"38dfbe654de4432db6a07860ee8318f5\",\"lowerCaseUnderline\":\"title\",\"name\":\"title\",\"orderNo\":0,\"presents\":[\"EDIT_FORM\",\"DETAIL\",\"SEARCH_FORM\",\"ADD_FORM\",\"LIST\"],\"require\":true,\"rules\":[],\"smallHump\":\"title\",\"title\":\"字典名称\",\"type\":\"STRING\",\"upperCaseUnderline\":\"TITLE\"},{\"bigHump\":\"Code\",\"component\":\"Input\",\"enumVal\":[],\"id\":\"91814bfe8b6644bfb31e0fe8d3702e5a\",\"lowerCaseUnderline\":\"code\",\"name\":\"code\",\"orderNo\":0,\"presents\":[\"EDIT_FORM\",\"DETAIL\",\"SEARCH_FORM\",\"ADD_FORM\",\"LIST\"],\"require\":true,\"rules\":[{\"message\":\"仅字母和数字组成\",\"pattern\":\"^[0-9a-zA-Z]+$\",\"type\":\"REGEXP\"}],\"smallHump\":\"code\",\"title\":\"字典编码\",\"type\":\"STRING\",\"upperCaseUnderline\":\"CODE\"}],\"logic\":false,\"lowerCaseDash\":\"dict\",\"lowerCaseUnderline\":\"dict\",\"module\":\"common\",\"name\":\"dict\",\"namespace\":\"sys\",\"namespaceBigHump\":\"Sys\",\"namespaceLowerCaseDash\":\"sys\",\"namespaceLowerCaseUnderline\":\"sys\",\"namespaceSmallHump\":\"sys\",\"smallHump\":\"dict\",\"title\":\"字典\",\"treeModel\":false}',
        NULL, '2021-11-04 23:28:19', '30d808cbd7c94c66b4512718151b00aa', '2021-11-05 20:33:47',
        '30d808cbd7c94c66b4512718151b00aa');
COMMIT;

-- ----------------------------
-- Table structure for sys_custom_component_test
-- ----------------------------
DROP TABLE IF EXISTS `sys_custom_component_test`;
CREATE TABLE `sys_custom_component_test`
(
    `id`            varchar(64) NOT NULL COMMENT '主键',
    `single_pic`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '单图片',
    `multiple_pic`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '多图片',
    `single_file`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '单文件',
    `multiple_file` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '多文件',
    `remark`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci       DEFAULT NULL COMMENT '备注',
    `create_time`   datetime                                                      DEFAULT NULL COMMENT '创建时间',
    `create_by`     varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci        DEFAULT NULL COMMENT '创建人',
    `update_time`   datetime                                                      DEFAULT NULL COMMENT '更新时间',
    `update_by`     varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci        DEFAULT NULL COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='自定义组件';

-- ----------------------------
-- Records of sys_custom_component_test
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_data_classify
-- ----------------------------
DROP TABLE IF EXISTS `sys_data_classify`;
CREATE TABLE `sys_data_classify`
(
    `id`          varchar(64) NOT NULL COMMENT '主键',
    `p_id`        varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci        DEFAULT NULL COMMENT '父级菜单ID',
    `order_no`    int                                                           DEFAULT NULL COMMENT '排序',
    `code`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '编码',
    `title`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '标题',
    `remark`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci       DEFAULT NULL COMMENT '备注',
    `create_time` datetime                                                      DEFAULT NULL COMMENT '创建时间',
    `create_by`   varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci        DEFAULT NULL COMMENT '创建人',
    `update_time` datetime                                                      DEFAULT NULL COMMENT '更新时间',
    `update_by`   varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci        DEFAULT NULL COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_data_classify
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_datasource
-- ----------------------------
DROP TABLE IF EXISTS `sys_datasource`;
CREATE TABLE `sys_datasource`
(
    `id`          varchar(64) NOT NULL COMMENT '主键',
    `name`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '数据库名称',
    `code`        varchar(255)                                                  DEFAULT NULL COMMENT '编码',
    `type`        tinyint                                                       DEFAULT '0' COMMENT '类型【Oracle:0, MySQL:1, DB2:2, PostgreSQL:3, H2:4】',
    `url`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'URL',
    `username`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户名',
    `password`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '密码',
    `remark`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci       DEFAULT NULL COMMENT '备注',
    `create_time` datetime                                                      DEFAULT NULL COMMENT '创建时间',
    `create_by`   varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci        DEFAULT NULL COMMENT '创建人',
    `update_time` datetime                                                      DEFAULT NULL COMMENT '更新时间',
    `update_by`   varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci        DEFAULT NULL COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_datasource
-- ----------------------------
BEGIN;
INSERT INTO `sys_datasource`
VALUES ('09dbd5b6093d9fdcae9f60c1b089459d', '系统数据库', 'master', 1,
        'jdbc:mysql://localhost:3306/dev_jad_admin?useUnicode=true&characterEncoding=utf8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8&useSSL=true&allowMultiQueries=true&autoReconnect=true&useAffectRows=true',
        'root', 'cxxwl96@sina.com', NULL, '2021-10-17 01:00:22', '30d808cbd7c94c66b4512718151b00aa',
        '2021-10-17 20:31:16', '30d808cbd7c94c66b4512718151b00aa');
INSERT INTO `sys_datasource`
VALUES ('80aa47c21807605d3550149765cf1c0c', '系统数据库-子库', 'slave', 1,
        'jdbc:mysql://localhost:3306/dev_jad_slave?useUnicode=true&characterEncoding=utf8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8&useSSL=true&allowMultiQueries=true&autoReconnect=true&useAffectRows=true',
        'root', 'cxxwl96@sina.com', NULL, '2021-10-17 20:37:27', '30d808cbd7c94c66b4512718151b00aa',
        '2021-10-24 12:25:31', '30d808cbd7c94c66b4512718151b00aa');
COMMIT;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`
(
    `id`          varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
    `p_id`        varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '父级菜单ID',
    `order_no`    int                                                     DEFAULT NULL COMMENT '排序',
    `code`        varchar(255)                                            DEFAULT NULL COMMENT '编码',
    `name`        varchar(255)                                            DEFAULT NULL COMMENT '部门名称',
    `status`      tinyint                                                 DEFAULT '0' COMMENT '状态【启用：0,停用：1】\n',
    `remark`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
    `create_time` datetime                                                DEFAULT NULL COMMENT '创建时间',
    `create_by`   varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '创建人',
    `update_time` datetime                                                DEFAULT NULL COMMENT '更新时间',
    `update_by`   varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '更新人',
    `enable`      bit(1)                                                  DEFAULT NULL COMMENT '是否启用【未启用：0,已启用：1】',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
BEGIN;
INSERT INTO `sys_dept`
VALUES ('0cea1f717129e855afba48ec22507c37', NULL, 0, 'HNFB', '华南分部', 0, NULL, '2021-08-21 23:33:49',
        '30d808cbd7c94c66b4512718151b00aa', '2021-08-22 00:40:36', '30d808cbd7c94c66b4512718151b00aa', NULL);
INSERT INTO `sys_dept`
VALUES ('11ef896ee072e580771f436e14b26c7d', '3f0e4f121e5b61b6184d10897ad3d727', 0, NULL, '研发部', 0, NULL,
        '2021-08-21 23:36:31', '30d808cbd7c94c66b4512718151b00aa', '2021-11-03 23:21:10',
        '30d808cbd7c94c66b4512718151b00aa', NULL);
INSERT INTO `sys_dept`
VALUES ('38524e8f333f4b74dc1ef588a5b9926f', '3f0e4f121e5b61b6184d10897ad3d727', 0, NULL, '商务部', 0, NULL,
        '2021-08-22 00:00:48', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL, NULL);
INSERT INTO `sys_dept`
VALUES ('3f0e4f121e5b61b6184d10897ad3d727', NULL, 0, 'HDFB', '华东分部', 0, NULL, '2021-08-21 23:33:31',
        '30d808cbd7c94c66b4512718151b00aa', '2021-11-03 23:39:33', '30d808cbd7c94c66b4512718151b00aa', NULL);
INSERT INTO `sys_dept`
VALUES ('45ed35d2b0491d8772c4d2254781525f', '0cea1f717129e855afba48ec22507c37', 0, NULL, '市场部', 0, NULL,
        '2021-08-22 00:03:30', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL, NULL);
INSERT INTO `sys_dept`
VALUES ('52a7755dcbfe990a82d0b24b7b45d049', '8f3c043c5ea7711e6050c5589de5bef1', 0, NULL, '研发部', 0, NULL,
        '2021-08-22 00:03:21', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL, NULL);
INSERT INTO `sys_dept`
VALUES ('58cea91fc311d21e1c6c762981a2c3e8', '0cea1f717129e855afba48ec22507c37', 0, NULL, '财务部', 0, NULL,
        '2021-08-22 00:04:03', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL, NULL);
INSERT INTO `sys_dept`
VALUES ('69fec399199bf749ff5c869467b5ead6', '0cea1f717129e855afba48ec22507c37', 0, NULL, '研发部', 0, NULL,
        '2021-08-22 00:03:16', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL, NULL);
INSERT INTO `sys_dept`
VALUES ('6b74324967108c357a8697a944f88217', '8f3c043c5ea7711e6050c5589de5bef1', 0, NULL, '财务部', 0, NULL,
        '2021-08-22 00:04:10', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL, NULL);
INSERT INTO `sys_dept`
VALUES ('7aaa9daa2ccf56260efb10b5bad2adb3', '3f0e4f121e5b61b6184d10897ad3d727', 0, NULL, '财务部', 0, NULL,
        '2021-08-22 00:00:59', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL, NULL);
INSERT INTO `sys_dept`
VALUES ('805ee37b0dd3c132dbaf24b73b439fab', '8f3c043c5ea7711e6050c5589de5bef1', 0, NULL, '市场部', 0, NULL,
        '2021-08-22 00:03:37', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL, NULL);
INSERT INTO `sys_dept`
VALUES ('8f3c043c5ea7711e6050c5589de5bef1', NULL, 0, 'XBFB', '西北分部', 0, NULL, '2021-08-21 23:34:02',
        '30d808cbd7c94c66b4512718151b00aa', NULL, NULL, NULL);
INSERT INTO `sys_dept`
VALUES ('9c54386f7574cc236c8afc0abf86b796', '0cea1f717129e855afba48ec22507c37', 0, NULL, '商务部', 0, NULL,
        '2021-08-22 00:03:48', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL, NULL);
INSERT INTO `sys_dept`
VALUES ('9fd9b0139d140a37b76bf8bb3a79a007', '3f0e4f121e5b61b6184d10897ad3d727', 0, NULL, '市场部', 0, NULL,
        '2021-08-21 23:40:17', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL, NULL);
INSERT INTO `sys_dept`
VALUES ('f0851c4699adbcdaec6df10a4e96427b', '8f3c043c5ea7711e6050c5589de5bef1', 0, NULL, '商务部', 0, NULL,
        '2021-08-22 00:03:54', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`
(
    `id`          varchar(64) NOT NULL COMMENT '主键',
    `title`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '字典名称',
    `code`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin        DEFAULT NULL COMMENT '字典编码',
    `remark`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci       DEFAULT NULL COMMENT '备注',
    `create_time` datetime                                                      DEFAULT NULL COMMENT '创建时间',
    `create_by`   varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci        DEFAULT NULL COMMENT '创建人',
    `update_time` datetime                                                      DEFAULT NULL COMMENT '更新时间',
    `update_by`   varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci        DEFAULT NULL COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict`
VALUES ('c0b7aa4e5deeb96735d92569685d47aa', '数据状态', 'STATUS', NULL, '2021-11-07 23:28:58',
        '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_dict`
VALUES ('f4239ccc110a94e51df3213ce83d1b28', '周', 'WEEK', '', '2021-11-05 21:17:18', '30d808cbd7c94c66b4512718151b00aa',
        '2021-11-07 01:11:38', '30d808cbd7c94c66b4512718151b00aa');
COMMIT;

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`
(
    `id`          varchar(64) NOT NULL COMMENT '主键',
    `dict_id`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '字典ID',
    `name`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '名称',
    `value`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '数据值',
    `order_no`    int                                                           DEFAULT NULL COMMENT '排序',
    `remark`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci       DEFAULT NULL COMMENT '备注',
    `create_time` datetime                                                      DEFAULT NULL COMMENT '创建时间',
    `create_by`   varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci        DEFAULT NULL COMMENT '创建人',
    `update_time` datetime                                                      DEFAULT NULL COMMENT '更新时间',
    `update_by`   varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci        DEFAULT NULL COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict_data`
VALUES ('2d0cec4d87beddef28f9858419354bbb', 'f4239ccc110a94e51df3213ce83d1b28', '周五', 'Friday', 0, NULL,
        '2021-11-07 01:10:09', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_dict_data`
VALUES ('3c92fe5b74f956db73f9d86c38a50c45', 'c0b7aa4e5deeb96735d92569685d47aa', '停用', 'Disable', 0, NULL,
        '2021-11-07 23:54:56', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_dict_data`
VALUES ('7041fb04a70aceb8ae094aa7de25b3b5', 'f4239ccc110a94e51df3213ce83d1b28', '周六', 'Saturday', 0, NULL,
        '2021-11-07 01:10:38', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_dict_data`
VALUES ('776cb0e3623984277bfc81c520e83884', 'f4239ccc110a94e51df3213ce83d1b28', '周四', 'Thursday', 0, NULL,
        '2021-11-07 01:09:52', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_dict_data`
VALUES ('938270f3b08f2be03982e289587e44d9', 'f4239ccc110a94e51df3213ce83d1b28', '周日', 'Sunday', 0, NULL,
        '2021-11-07 01:10:53', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_dict_data`
VALUES ('b8aa301fbfbb481336a0d627c6150e67', 'f4239ccc110a94e51df3213ce83d1b28', '周二', 'Tuesday', 0, NULL,
        '2021-11-07 01:07:58', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_dict_data`
VALUES ('bd1cb035dd5ef5c06495c7c77d05ccb7', 'f4239ccc110a94e51df3213ce83d1b28', '周一', 'Monday', 0, NULL,
        '2021-11-07 01:07:28', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_dict_data`
VALUES ('e955c41b2aa6ef0a1d16accfb5cf60ce', 'f4239ccc110a94e51df3213ce83d1b28', '周三', 'Wednesday', 0, NULL,
        '2021-11-07 01:09:27', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_dict_data`
VALUES ('fb80fdf994b431836632a0c3826df5ac', 'c0b7aa4e5deeb96735d92569685d47aa', '启动', 'Enable', 0, NULL,
        '2021-11-07 23:45:16', '30d808cbd7c94c66b4512718151b00aa', '2021-11-07 23:54:22',
        '30d808cbd7c94c66b4512718151b00aa');
COMMIT;

-- ----------------------------
-- Table structure for sys_file_store
-- ----------------------------
DROP TABLE IF EXISTS `sys_file_store`;
CREATE TABLE `sys_file_store`
(
    `id`            varchar(64) NOT NULL COMMENT '主键',
    `group_id`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '分组',
    `name`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '名称',
    `type`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '类型',
    `size`          bigint                                                        DEFAULT NULL COMMENT '大小',
    `path`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '路径',
    `md5`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'MD5',
    `memi`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'MEMI类型',
    `access_policy` tinyint                                                       DEFAULT '0' COMMENT '访问策略【公开:0, 私有:1】',
    `store`         tinyint                                                       DEFAULT '0' COMMENT '存储源【本地存储:0, minio存储:1, 七牛云:2, 阿里云:3, 腾讯云:4】',
    `remark`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci       DEFAULT NULL COMMENT '备注',
    `create_time`   datetime                                                      DEFAULT NULL COMMENT '创建时间',
    `create_by`     varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci        DEFAULT NULL COMMENT '创建人',
    `update_time`   datetime                                                      DEFAULT NULL COMMENT '更新时间',
    `update_by`     varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci        DEFAULT NULL COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='对象存储';

-- ----------------------------
-- Records of sys_file_store
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`
(
    `id`                     varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '主键',
    `p_id`                   varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci        DEFAULT NULL COMMENT '父级菜单ID',
    `order_no`               int                                                           DEFAULT NULL COMMENT '排序',
    `code`                   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '编码',
    `type`                   tinyint                                                       DEFAULT NULL COMMENT '类型 [0：目录 1：菜单 2：按钮]',
    `permissions`            varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci       DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
    `path`                   varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci       DEFAULT NULL COMMENT '菜单URL',
    `name`                   varchar(255)                                                  DEFAULT NULL COMMENT '路由名称',
    `component`              varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci       DEFAULT NULL COMMENT '前端组件URL',
    `redirect`               varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci       DEFAULT NULL COMMENT '重定向',
    `title`                  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单标题',
    `affix`                  bit(1)                                                        DEFAULT NULL COMMENT '是否固定标签',
    `ignore_keep_alive`      bit(1)                                                        DEFAULT NULL COMMENT '是否忽略KeepAlive缓存',
    `icon`                   varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci       DEFAULT NULL COMMENT '菜单图标',
    `frame_src`              varchar(255)                                                  DEFAULT NULL COMMENT '内嵌iframe的地址',
    `transition_name`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci       DEFAULT NULL COMMENT '指定该路由切换的动画名',
    `carry_param`            bit(1)                                                        DEFAULT NULL COMMENT '如果该路由会携带参数，且需要在tab页上面显示。则需要设置为true',
    `hide_children_in_menu`  bit(1)                                                        DEFAULT NULL COMMENT '是否隐藏所有子菜单',
    `hide_tab`               bit(1)                                                        DEFAULT NULL COMMENT '当前路由不再标签页显示',
    `hide_menu`              bit(1)                                                        DEFAULT NULL COMMENT '当前路由不再菜单显示',
    `ignore_route`           bit(1)                                                        DEFAULT NULL COMMENT '本路由仅用于菜单生成，不会在实际的路由表中出现',
    `hide_path_for_children` bit(1)                                                        DEFAULT NULL COMMENT '是否在子级菜单的完整path中忽略本级path',
    `external`               bit(1)                                                        DEFAULT NULL COMMENT '是否外链',
    `status`                 tinyint                                                       DEFAULT '0' COMMENT '状态【启用：0,停用：1】\n',
    `remark`                 varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci       DEFAULT NULL COMMENT '备注',
    `create_time`            datetime                                                      DEFAULT NULL COMMENT '创建时间',
    `create_by`              varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci        DEFAULT NULL COMMENT '创建人',
    `update_time`            datetime                                                      DEFAULT NULL COMMENT '更新时间',
    `update_by`              varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci        DEFAULT NULL COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb3
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu`
VALUES ('009ace919a3d191932449f11fcb763b1', '7e84f61c521790ee185467bcb153f4ae', 0, NULL, 2, 'sys:user:delete', NULL,
        '1455552881344921600', NULL, NULL, '删除用户', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0',
        0, NULL, '2021-10-22 21:38:47', '30d808cbd7c94c66b4512718151b00aa', '2021-11-02 23:10:25', NULL);
INSERT INTO `sys_menu`
VALUES ('04dfcf16f0815289f22b67e633fde65e', '2e80fce962c46d4b5334245035851535', 0, NULL, 2, 'sys:role:delete', NULL,
        '1455552881344921601', NULL, NULL, '删除角色', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0',
        0, NULL, '2021-10-30 15:10:01', '30d808cbd7c94c66b4512718151b00aa', '2021-11-02 23:10:25', NULL);
INSERT INTO `sys_menu`
VALUES ('07d4f49b537e853ef735bda8e2c7c1d9', '489a735425de96fa0c2412f5b4ffed66', 0, NULL, 2, 'sys:datasource:get:page',
        NULL, '1455552881344921602', NULL, NULL, '分页获取数据源', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0',
        b'0', b'0', 0, NULL, '2021-10-31 21:38:29', '30d808cbd7c94c66b4512718151b00aa', '2021-11-02 23:10:25', NULL);
INSERT INTO `sys_menu`
VALUES ('0c0ea35ce26cb9942b77372731897214', '18853ceb4615b587d77a014b7a8d2336', 0, NULL, 1, NULL, 'sms',
        '1455552881344921603', '/msg/sms/index', NULL, 'SMS消息', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0',
        b'0', b'0', b'0', 0, NULL, '2021-08-15 23:41:44', '30d808cbd7c94c66b4512718151b00aa', '2021-11-02 23:10:25',
        NULL);
INSERT INTO `sys_menu`
VALUES ('0c5e77c3b84fbbe24bff5a9795b87317', 'ce632f999c5110f1136ef029b82aa791', 0, NULL, 1, NULL, 'post',
        '1455552881344921604', '/sys/post/index', NULL, '岗位管理', b'0', b'0', 'ph:briefcase', NULL, NULL, b'0', b'0',
        b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-08-15 23:27:58', '30d808cbd7c94c66b4512718151b00aa',
        '2021-11-07 23:57:56', '30d808cbd7c94c66b4512718151b00aa');
INSERT INTO `sys_menu`
VALUES ('0fa8988ea7fdff5ce988dda92af67400', 'ed5287e77f82d8cf064549bdce3728f7', 0, NULL, 1, NULL, 'about',
        '1455552881344921605', '/sys/about/index', NULL, '关于', b'0', b'0', 'simple-icons:about-dot-me', NULL, NULL,
        b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-08-15 23:11:18', '30d808cbd7c94c66b4512718151b00aa',
        '2021-11-02 23:10:25', NULL);
INSERT INTO `sys_menu`
VALUES ('10928cb4098f2880326ba7cd62e2020d', '2f8ea043f9ae0daa906ad39d57599aa6', 0, NULL, 1, NULL, 'store',
        '1462047235890745344', '/file-store/oos/Index', NULL, '对象存储', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0',
        b'0', b'0', b'0', b'0', 0, NULL, '2021-11-20 21:16:39', '30d808cbd7c94c66b4512718151b00aa',
        '2021-11-20 21:21:35', '30d808cbd7c94c66b4512718151b00aa');
INSERT INTO `sys_menu`
VALUES ('18853ceb4615b587d77a014b7a8d2336', NULL, 0, NULL, 0, NULL, '/msg', '1455552881344921606', 'LAYOUT', NULL,
        '消息中心', b'0', b'0', 'ant-design:message-outlined', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0,
        NULL, '2021-08-15 22:55:58', '30d808cbd7c94c66b4512718151b00aa', '2021-11-02 23:10:25', NULL);
INSERT INTO `sys_menu`
VALUES ('1c6e47b7c845522d27841449f1804886', '18853ceb4615b587d77a014b7a8d2336', 0, NULL, 1, NULL, 'temp',
        '1455552881344921607', '/msg/temp/index', NULL, '模板管理', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0',
        b'0', b'0', b'0', 0, NULL, '2021-08-15 23:40:48', '30d808cbd7c94c66b4512718151b00aa', '2021-11-02 23:10:25',
        NULL);
INSERT INTO `sys_menu`
VALUES ('1e0383311df18aad7d41ce429d5c7c89', '1f140fbe7445ada922ea441729af24bd', 0, NULL, 2, 'devtools:generator:get',
        NULL, '1455552881344921608', NULL, NULL, '获取单个Model', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0',
        b'0', b'0', 0, NULL, '2021-10-31 23:00:34', '30d808cbd7c94c66b4512718151b00aa', '2021-11-02 23:10:25', NULL);
INSERT INTO `sys_menu`
VALUES ('1f140fbe7445ada922ea441729af24bd', 'd1b05c6888eaedd21abcce51d2433882', 0, NULL, 1, NULL, 'ModelDesign/:id',
        '1455552881344921609', '/devtools/generator/ModelDesign', NULL, '设计Model', b'0', b'0', '', NULL, NULL, b'0',
        b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-09-21 00:14:24', '30d808cbd7c94c66b4512718151b00aa',
        '2021-11-02 23:10:25', NULL);
INSERT INTO `sys_menu`
VALUES ('1f580c5675344646d79613eeca400988', '9650554e64c53b03d64f9039e0ead1c0', 0, NULL, 2, 'sys:dict:get:page', NULL,
        '1457292818171842560', NULL, NULL, '分页获取字典', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0',
        b'0', 0, NULL, '2021-11-07 18:24:18', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('1f971a4677901ce44ef1f82ef647c206', '7e84f61c521790ee185467bcb153f4ae', 0, NULL, 2, 'sys:user:update', NULL,
        '1455552881344921610', NULL, NULL, '修改用户', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0',
        0, NULL, '2021-10-22 21:43:08', '30d808cbd7c94c66b4512718151b00aa', '2021-10-22 21:48:06',
        '30d808cbd7c94c66b4512718151b00aa');
INSERT INTO `sys_menu`
VALUES ('2b56fa6bd71df15a2acaa20cb8ec58b5', NULL, 0, NULL, 0, NULL, '/user', '1455552881344921611', 'LAYOUT', NULL,
        '个人中心', b'0', b'0', 'ant-design:user-outlined', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL,
        '2021-08-15 22:57:21', '30d808cbd7c94c66b4512718151b00aa', '2021-11-02 23:10:25', NULL);
INSERT INTO `sys_menu`
VALUES ('2e80fce962c46d4b5334245035851535', 'ce632f999c5110f1136ef029b82aa791', 0, NULL, 1, NULL, 'role',
        '1455552881344921612', '/sys/role/index', NULL, '角色管理', b'0', b'0', 'carbon:user-role', NULL, NULL, b'0', b'0',
        b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-08-15 22:59:44', '30d808cbd7c94c66b4512718151b00aa',
        '2021-11-07 23:58:58', '30d808cbd7c94c66b4512718151b00aa');
INSERT INTO `sys_menu`
VALUES ('2f8ea043f9ae0daa906ad39d57599aa6', NULL, 0, NULL, 0, NULL, '/file', '1455552881344921613', 'LAYOUT', NULL,
        '文件系统', b'0', b'0', 'ant-design:folder-open-outlined', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0,
        NULL, '2021-08-15 22:55:15', '30d808cbd7c94c66b4512718151b00aa', '2021-11-17 00:35:42',
        '30d808cbd7c94c66b4512718151b00aa');
INSERT INTO `sys_menu`
VALUES ('31ffccf27c7efed0a71cc58eaabaadc6', '2e80fce962c46d4b5334245035851535', 0, NULL, 2, 'sys:role:update:status',
        NULL, '1455552881344921614', NULL, NULL, '修改角色状态', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0',
        b'0', b'0', 0, NULL, '2021-10-30 15:12:19', '30d808cbd7c94c66b4512718151b00aa', '2021-11-02 23:10:25', NULL);
INSERT INTO `sys_menu`
VALUES ('339a52ae378b9c95116dfd3ce9108c67', '489a735425de96fa0c2412f5b4ffed66', 0, NULL, 2, 'sys:datasource:save', NULL,
        '1455552881344921615', NULL, NULL, '添加数据源', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0',
        b'0', 0, NULL, '2021-10-31 21:35:53', '30d808cbd7c94c66b4512718151b00aa', '2021-11-02 23:10:25', NULL);
INSERT INTO `sys_menu`
VALUES ('397c27e2d91f14736e61e2baef96b860', '7f5ebaf51d971c4204e759ae9686e32b', 0, NULL, 1, NULL, 'service',
        '1455552881344921616', '/sys-monitor/service/index', NULL, '服务监控（服务器信息、JVM监控、Redis监控）', b'0', b'0',
        'ant-design:cloud-server-outlined', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL,
        '2021-08-15 23:24:47', '30d808cbd7c94c66b4512718151b00aa', '2021-11-07 01:50:40',
        '30d808cbd7c94c66b4512718151b00aa');
INSERT INTO `sys_menu`
VALUES ('39824349b89c501f622b24050c78bdaa', 'd1b05c6888eaedd21abcce51d2433882', 0, NULL, 2, 'devtools:generator:update',
        NULL, '1455552881344921617', NULL, NULL, '修改Model', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0',
        b'0', b'0', 0, NULL, '2021-10-31 22:52:08', '30d808cbd7c94c66b4512718151b00aa', '2021-11-02 23:10:25', NULL);
INSERT INTO `sys_menu`
VALUES ('41ce300ed10c87c39b70f2121c1cf76d', 'ce632f999c5110f1136ef029b82aa791', 0, NULL, 1, NULL, 'menu',
        '1455552881344921618', '/sys/menu/Index', NULL, '菜单管理', b'0', b'0', 'ant-design:menu-outlined', NULL, NULL,
        b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-08-15 23:00:21', '30d808cbd7c94c66b4512718151b00aa',
        '2021-11-07 23:59:52', '30d808cbd7c94c66b4512718151b00aa');
INSERT INTO `sys_menu`
VALUES ('489a735425de96fa0c2412f5b4ffed66', 'dd5c824b9f0f61a6cd3a2b703bdffc55', 0, NULL, 1, NULL, 'database',
        '1455552881344921619', '/sys/datasource/Index', NULL, '数据源管理', b'0', b'0', 'mdi:database', NULL, NULL, b'0',
        b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-08-15 23:04:21', '30d808cbd7c94c66b4512718151b00aa',
        '2021-11-07 21:36:49', '30d808cbd7c94c66b4512718151b00aa');
INSERT INTO `sys_menu`
VALUES ('4a86291d875bf568c5fcd8947b0d13ec', 'ce632f999c5110f1136ef029b82aa791', 0, NULL, 1, NULL, 'tenant',
        '1455552881344921620', '/sys/tenant/index', NULL, '租户管理', b'0', b'0', 'ant-design:usergroup-add-outlined', NULL,
        NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-08-15 23:29:50',
        '30d808cbd7c94c66b4512718151b00aa', '2021-11-07 01:40:46', '30d808cbd7c94c66b4512718151b00aa');
INSERT INTO `sys_menu`
VALUES ('4ad6db6390589cb9fe63790822a68364', '41ce300ed10c87c39b70f2121c1cf76d', 0, NULL, 2, 'sys:menu:update', NULL,
        '1455552881344921621', NULL, NULL, '修改菜单', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0',
        0, NULL, '2021-10-30 13:50:07', '30d808cbd7c94c66b4512718151b00aa', '2021-11-02 23:10:25', NULL);
INSERT INTO `sys_menu`
VALUES ('4b1ef62ca63545700f70890c3a14b6ae', 'd1b05c6888eaedd21abcce51d2433882', 0, NULL, 2, 'devtools:generator:save',
        NULL, '1455552881344921622', NULL, NULL, '添加Model', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0',
        b'0', b'0', 0, NULL, '2021-10-31 22:51:10', '30d808cbd7c94c66b4512718151b00aa', '2021-11-02 23:10:25', NULL);
INSERT INTO `sys_menu`
VALUES ('4ca25ecde5bead634a9661a66e8bf300', '1f140fbe7445ada922ea441729af24bd', 0, NULL, 2, 'devtools:generator:table',
        NULL, '1455552881344921623', NULL, NULL, '生成/预览数据库表', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0',
        b'0', b'0', 0, NULL, '2021-10-31 23:04:00', '30d808cbd7c94c66b4512718151b00aa', '2021-11-02 23:10:25', NULL);
INSERT INTO `sys_menu`
VALUES ('4ff777cc924eaf5b8920ad5bb16e94b3', '2b56fa6bd71df15a2acaa20cb8ec58b5', 0, NULL, 1, NULL, 'dept',
        '1455552881344921624', '/user/dept/index', NULL, '我的部门', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0',
        b'0', b'0', b'0', 0, NULL, '2021-08-15 23:45:45', '30d808cbd7c94c66b4512718151b00aa', '2021-11-02 23:10:25',
        NULL);
INSERT INTO `sys_menu`
VALUES ('5082de8a7767b411836cb5065ba936dc', '7f5ebaf51d971c4204e759ae9686e32b', 0, NULL, 1, NULL, 'task-schedule',
        '1455552881344921625', '/sys-monitor/task-schedule/index', NULL, '任务调度', b'0', b'0', 'ic:baseline-schedule',
        NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-08-15 23:23:38',
        '30d808cbd7c94c66b4512718151b00aa', '2021-11-07 02:06:33', '30d808cbd7c94c66b4512718151b00aa');
INSERT INTO `sys_menu`
VALUES ('51181155b90a9a98ef0a288cf4b7fb8a', '9650554e64c53b03d64f9039e0ead1c0', 0, NULL, 2, 'sys:dictData:update', NULL,
        '1457293497586176000', NULL, NULL, '修改字典数据', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0',
        b'0', 0, NULL, '2021-11-07 18:27:00', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('51dd355657c4a4a1450510f08e23dc60', '7e84f61c521790ee185467bcb153f4ae', 0, NULL, 2, 'sys:role:get:list', NULL,
        '1455552881344921626', NULL, NULL, '获取所有角色', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0',
        b'0', 0, NULL, '2021-10-30 15:17:46', '30d808cbd7c94c66b4512718151b00aa', '2021-11-02 23:10:25', NULL);
INSERT INTO `sys_menu`
VALUES ('54918519ff06ea7bf43ac7a960d01a48', 'ed5287e77f82d8cf064549bdce3728f7', 0, NULL, 1, NULL, 'workbench',
        '1455552881344921627', '/dashboard/workbench/index', NULL, '工作台', b'0', b'0', 'ant-design:appstore-outlined',
        NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-08-15 23:03:09',
        '30d808cbd7c94c66b4512718151b00aa', '2021-11-07 01:47:49', '30d808cbd7c94c66b4512718151b00aa');
INSERT INTO `sys_menu`
VALUES ('5a62974225f08b322ede180c608cf980', '7e84f61c521790ee185467bcb153f4ae', 0, NULL, 2, 'sys:user:get', NULL,
        '1455552881344921628', NULL, NULL, '获取单个用户', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0',
        b'0', 0, NULL, '2021-10-22 21:47:17', '30d808cbd7c94c66b4512718151b00aa', '2021-10-28 22:17:22',
        '30d808cbd7c94c66b4512718151b00aa');
INSERT INTO `sys_menu`
VALUES ('5bd090c0ba4e8ffbfd8b329b7f6df1f4', 'a6cf40d746f4fd88ee5f4646fd4933d0', 0, NULL, 2, 'sys:dataClassify:save',
        NULL, '1459163041078472704', NULL, NULL, '添加数据分类', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0',
        b'0', b'0', 0, NULL, '2021-11-12 22:15:54', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('5e4f830646975fcab775771bccf33917', '41ce300ed10c87c39b70f2121c1cf76d', 0, NULL, 2, 'sys:menu:save', NULL,
        '1455552881344921629', NULL, NULL, '添加菜单', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0',
        0, NULL, '2021-10-30 13:49:12', '30d808cbd7c94c66b4512718151b00aa', '2021-11-02 23:10:25', NULL);
INSERT INTO `sys_menu`
VALUES ('5f4a3d58059a9af91b4f637e02e47449', '2e80fce962c46d4b5334245035851535', 0, NULL, 2,
        'sys:role:assignPermissions', NULL, '1455552881344921630', NULL, NULL, '分配权限', b'0', b'0', '', NULL, NULL, b'0',
        b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-10-30 15:14:16', '30d808cbd7c94c66b4512718151b00aa',
        '2021-11-02 23:10:25', NULL);
INSERT INTO `sys_menu`
VALUES ('63122251dae885d3aa2bde7e4b52a6f8', 'a6cf40d746f4fd88ee5f4646fd4933d0', 3, NULL, 2, 'sys:dataClassify:update',
        NULL, '1459163041078472707', NULL, NULL, '修改数据分类', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0',
        b'0', b'0', 0, NULL, '2021-11-12 22:15:54', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('6ad1cc0a21be11692188e937d6e4638b', '41ce300ed10c87c39b70f2121c1cf76d', 0, NULL, 2, 'sys:menu:get', NULL,
        '1455552881344921631', NULL, NULL, '显示菜单详情', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0',
        b'0', 0, NULL, '2021-10-30 13:50:29', '30d808cbd7c94c66b4512718151b00aa', '2021-10-30 14:51:34',
        '30d808cbd7c94c66b4512718151b00aa');
INSERT INTO `sys_menu`
VALUES ('6b7b04ef25e5e45fd713376a295e046e', 'febf2c72c3e1828dd24a2679ff9b9659', 0, NULL, 2, 'sys:dept:getDeptTree',
        NULL, '1455552881344921632', NULL, NULL, '获取部门树', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0',
        b'0', b'0', 0, NULL, '2021-10-28 23:34:25', '30d808cbd7c94c66b4512718151b00aa', '2021-11-02 23:10:25', NULL);
INSERT INTO `sys_menu`
VALUES ('6c43bf1b4c67c1c0658e58fbd16e256f', '7f5ebaf51d971c4204e759ae9686e32b', 0, NULL, 1, NULL, 'druid-monitor',
        '1455552881344921633', '/sys-monitor/druid-monitor', NULL, 'SQL监控（druid monitor）', b'0', b'0',
        'ant-design:console-sql-outlined', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL,
        '2021-08-15 23:26:18', '30d808cbd7c94c66b4512718151b00aa', '2021-11-07 01:50:20',
        '30d808cbd7c94c66b4512718151b00aa');
INSERT INTO `sys_menu`
VALUES ('772d7cc789e91151adb9480aa1a0a7d2', 'febf2c72c3e1828dd24a2679ff9b9659', 0, NULL, 2, 'sys:dept:delete', NULL,
        '1455552881344921634', NULL, NULL, '删除部门', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0',
        0, NULL, '2021-10-28 23:31:38', '30d808cbd7c94c66b4512718151b00aa', '2021-11-02 23:10:25', NULL);
INSERT INTO `sys_menu`
VALUES ('7838998813e05758c9418bd3c7b06eb0', '10928cb4098f2880326ba7cd62e2020d', 5, NULL, 2, 'sys:fileStore:get:page',
        NULL, '1462047335635488774', NULL, NULL, '分页获取对象存储', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0',
        b'0', b'0', 0, NULL, '2021-11-20 21:17:03', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('7d9e902b10f4383917f866c296b5e2bb', 'd1b05c6888eaedd21abcce51d2433882', 0, NULL, 2, 'sys:datasource:getAll',
        NULL, '1455552881344921635', NULL, NULL, '获取所有数据源', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0',
        b'0', b'0', 0, NULL, '2021-10-31 23:24:22', '30d808cbd7c94c66b4512718151b00aa', '2021-11-02 23:10:25', NULL);
INSERT INTO `sys_menu`
VALUES ('7e84f61c521790ee185467bcb153f4ae', 'ce632f999c5110f1136ef029b82aa791', 0, NULL, 1, NULL, 'user',
        '1455552881344921636', '/sys/user/index', NULL, '用户管理', b'0', b'0', 'ant-design:user-outlined', NULL, NULL,
        b'0', b'1', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-08-15 22:59:13', '30d808cbd7c94c66b4512718151b00aa',
        '2021-11-07 01:30:58', '30d808cbd7c94c66b4512718151b00aa');
INSERT INTO `sys_menu`
VALUES ('7f5ebaf51d971c4204e759ae9686e32b', NULL, 0, NULL, 0, NULL, '/sys-monitor', '1455552881344921637', 'LAYOUT',
        NULL, '系统监控', b'0', b'0', 'ant-design:monitor-outlined', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0',
        0, NULL, '2021-08-15 22:52:39', '30d808cbd7c94c66b4512718151b00aa', '2021-11-02 23:10:25', NULL);
INSERT INTO `sys_menu`
VALUES ('80a7335610b81933e6b5b2b4e2cf6b35', '18853ceb4615b587d77a014b7a8d2336', 0, NULL, 1, NULL, 'notice',
        '1455552881344921638', '/msg/notice/index', NULL, '系统通告', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0',
        b'0', b'0', b'0', 0, NULL, '2021-08-15 23:41:13', '30d808cbd7c94c66b4512718151b00aa', '2021-11-02 23:10:25',
        NULL);
INSERT INTO `sys_menu`
VALUES ('846eab332d18044256d5dc0485a01328', 'be23e50d8fcd326a6d2819cb7837ec39', 0, NULL, 1, NULL,
        'https://icon-sets.iconify.design/', '1458453898927583232', 'IFRAME', NULL, 'Iconify', b'0', b'0',
        'simple-icons:iconify', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-11-10 23:18:01',
        '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('84ce4cd45b00620a4025931b4759a303', 'febf2c72c3e1828dd24a2679ff9b9659', 0, NULL, 2, 'sys:dept:save', NULL,
        '1455552881344921639', NULL, NULL, '新增部门', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0',
        0, NULL, '2021-10-28 23:30:48', '30d808cbd7c94c66b4512718151b00aa', '2021-11-02 23:10:25', NULL);
INSERT INTO `sys_menu`
VALUES ('884a3f3b6f40108ae22f39321352d43a', '1f140fbe7445ada922ea441729af24bd', 0, NULL, 2,
        'devtools:generator:pathSelecct', NULL, '1455552881344921640', NULL, NULL, '路径选择', b'0', b'0', '', NULL, NULL,
        b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-10-31 23:34:14', '30d808cbd7c94c66b4512718151b00aa',
        '2021-11-02 23:10:25', NULL);
INSERT INTO `sys_menu`
VALUES ('8a808855681f429f59c4d4f946441f90', '2e80fce962c46d4b5334245035851535', 0, NULL, 2, 'sys:get:page', NULL,
        '1455552881344921641', NULL, NULL, '分页获取角色', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0',
        b'0', 0, NULL, '2021-10-30 15:13:35', '30d808cbd7c94c66b4512718151b00aa', '2021-11-02 23:10:25', NULL);
INSERT INTO `sys_menu`
VALUES ('8ab2bd51a4423f84b438767cc210be0c', '1f140fbe7445ada922ea441729af24bd', 0, NULL, 2,
        'devtools:generator:saveField', NULL, '1455552881344921642', NULL, NULL, '添加Field', b'0', b'0', '', NULL, NULL,
        b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-10-31 22:58:44', '30d808cbd7c94c66b4512718151b00aa',
        '2021-11-02 23:10:25', NULL);
INSERT INTO `sys_menu`
VALUES ('8b9a75e2e64dfc1e6bb3bcf90221b1ed', 'c9502845a936bb3e1fedac49e9d1fb08', 1, NULL, 2,
        'sys:customComponent:Testdelete', NULL, '1470034231221411841', NULL, NULL, '删除', b'0', b'0', '', NULL, NULL,
        b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-12-12 22:14:07', '30d808cbd7c94c66b4512718151b00aa',
        NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('8f10ac030130ee4e962c968e60e66e18', '10928cb4098f2880326ba7cd62e2020d', 4, NULL, 2, 'sys:fileStore:get', NULL,
        '1462047335635488773', NULL, NULL, '获取单个对象存储', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0',
        b'0', 0, NULL, '2021-11-20 21:17:03', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('908c9b55618a25f07058ed60f8061e71', 'febf2c72c3e1828dd24a2679ff9b9659', 0, NULL, 2, 'sys:dept:get', NULL,
        '1455552881344921644', NULL, NULL, '获取单个部门', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0',
        b'0', 0, NULL, '2021-10-28 23:34:01', '30d808cbd7c94c66b4512718151b00aa', '2021-11-02 23:10:25', NULL);
INSERT INTO `sys_menu`
VALUES ('9469a9efb13c4f7cbd4df5a724980f9a', '10928cb4098f2880326ba7cd62e2020d', 1, NULL, 2, 'sys:fileStore:delete',
        NULL, '1462047335635488770', NULL, NULL, '删除对象存储', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0',
        b'0', b'0', 0, NULL, '2021-11-20 21:17:03', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('964486229d809242069ace2f18ae6eb3', '1f140fbe7445ada922ea441729af24bd', 0, NULL, 2, 'devtools:generator:front',
        NULL, '1455552881344921645', NULL, NULL, '生成/预览前端代码', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0',
        b'0', b'0', 0, NULL, '2021-10-31 23:04:47', '30d808cbd7c94c66b4512718151b00aa', '2021-11-02 23:10:25', NULL);
INSERT INTO `sys_menu`
VALUES ('9650554e64c53b03d64f9039e0ead1c0', 'ce632f999c5110f1136ef029b82aa791', 0, NULL, 1, NULL, 'dict',
        '1455552881344921646', '/sys/dict/Index', NULL, '字典管理', b'0', b'0', 'ant-design:database-outlined', NULL, NULL,
        b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-08-15 23:28:29', '30d808cbd7c94c66b4512718151b00aa',
        '2021-11-07 01:39:55', '30d808cbd7c94c66b4512718151b00aa');
INSERT INTO `sys_menu`
VALUES ('9c08e42279dd31b3839501c3e8ac4209', '1f140fbe7445ada922ea441729af24bd', 0, NULL, 2,
        'devtools:generator:deleteField', NULL, '1455552881344921647', NULL, NULL, '删除Field', b'0', b'0', '', NULL,
        NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-10-31 22:59:12',
        '30d808cbd7c94c66b4512718151b00aa', '2021-11-02 23:10:25', NULL);
INSERT INTO `sys_menu`
VALUES ('9cd43fa42eb9e8e5aef73330534a4012', '18853ceb4615b587d77a014b7a8d2336', 0, NULL, 1, NULL, 'email',
        '1455552881344921648', '/msg/email/index', NULL, 'Email消息', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0',
        b'0', b'0', b'0', 0, NULL, '2021-08-15 23:42:12', '30d808cbd7c94c66b4512718151b00aa', '2021-11-02 23:10:25',
        NULL);
INSERT INTO `sys_menu`
VALUES ('9d6ecf9e2832e911f4b3b0aec941c8f4', '9650554e64c53b03d64f9039e0ead1c0', 0, NULL, 2, 'sys:dict:delete', NULL,
        '1457292536901816320', NULL, NULL, '删除字典', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0',
        0, NULL, '2021-11-07 18:23:11', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('9ebad79054696c971f27c1e3027a0dac', '9650554e64c53b03d64f9039e0ead1c0', 0, NULL, 2, 'sys:dictData:delete', NULL,
        '1457293396239208448', NULL, NULL, '删除字典数据', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0',
        b'0', 0, NULL, '2021-11-07 18:26:36', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('a3f749d9fa3c5c6b4dcb8c16d79d9dd0', '489a735425de96fa0c2412f5b4ffed66', 0, NULL, 2, 'sys:datasource:delete',
        NULL, '1455552881344921649', NULL, NULL, '删除数据源', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0',
        b'0', b'0', 0, NULL, '2021-10-31 21:36:34', '30d808cbd7c94c66b4512718151b00aa', '2021-11-02 23:10:25', NULL);
INSERT INTO `sys_menu`
VALUES ('a6cf40d746f4fd88ee5f4646fd4933d0', 'ce632f999c5110f1136ef029b82aa791', 0, NULL, 1, NULL, 'data-classify',
        '1455552881344921650', '/sys/data-classify/Index', NULL, '数据分类', b'0', b'0', 'carbon:data-class', NULL, NULL,
        b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-08-15 23:29:06', '30d808cbd7c94c66b4512718151b00aa',
        '2021-11-13 00:19:26', '30d808cbd7c94c66b4512718151b00aa');
INSERT INTO `sys_menu`
VALUES ('a7f8dd663bfa2ee883873d6c9773120b', 'd1b05c6888eaedd21abcce51d2433882', 0, NULL, 2,
        'devtools:generator:getModule', NULL, '1455552881344921651', NULL, NULL, '获取Module', b'0', b'0', '', NULL, NULL,
        b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-10-31 23:23:51', '30d808cbd7c94c66b4512718151b00aa',
        '2021-11-02 23:10:25', NULL);
INSERT INTO `sys_menu`
VALUES ('aa6d03008a07820d4c9bbd986b27a74f', '9650554e64c53b03d64f9039e0ead1c0', 0, NULL, 2, 'sys:dict:update', NULL,
        '1457292715029712896', NULL, NULL, '修改字典', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0',
        0, NULL, '2021-11-07 18:23:53', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('aaa5a3088ffafa59af019dcc40c7fb7c', 'a6cf40d746f4fd88ee5f4646fd4933d0', 5, NULL, 2, 'sys:dataClassify:getTree',
        NULL, '1459163041078472709', NULL, NULL, '获取数据分类树', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0',
        b'0', b'0', 0, NULL, '2021-11-12 22:15:54', '30d808cbd7c94c66b4512718151b00aa', '2021-11-12 23:38:33',
        '30d808cbd7c94c66b4512718151b00aa');
INSERT INTO `sys_menu`
VALUES ('ab7130763b48461c5f65d49436ed0a26', 'd6ddaddfc99cf44167649751f6505a3a', 0, NULL, 1, NULL, 'logs',
        '1455552881344921652', '/logger/logs/index', NULL, '日志管理（日志文件）', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0',
        b'0', b'0', b'0', b'0', 0, NULL, '2021-08-15 23:40:17', '30d808cbd7c94c66b4512718151b00aa',
        '2021-11-02 23:10:25', NULL);
INSERT INTO `sys_menu`
VALUES ('ace7435d4ac82750261acb11af75557f', '489a735425de96fa0c2412f5b4ffed66', 0, NULL, 2, 'sys:datasource:deleteArr',
        NULL, '1455552881344921653', NULL, NULL, '删除多个数据源', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0',
        b'0', b'0', 0, NULL, '2021-10-31 21:36:55', '30d808cbd7c94c66b4512718151b00aa', '2021-11-02 23:10:25', NULL);
INSERT INTO `sys_menu`
VALUES ('b08c73d026d23251b5d8d5d6769b1589', 'a6cf40d746f4fd88ee5f4646fd4933d0', 4, NULL, 2, 'sys:dataClassify:get',
        NULL, '1459163041078472708', NULL, NULL, '获取单个数据分类', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0',
        b'0', b'0', 0, NULL, '2021-11-12 22:15:54', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('b119d879a7b5317f2b85359f3cd2f257', '2b56fa6bd71df15a2acaa20cb8ec58b5', 0, NULL, 1, NULL, 'msg',
        '1455552881344921654', '/user/msg/index', NULL, '我的消息', b'0', b'0', 'ant-design:bell-outlined', NULL, NULL,
        b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-08-15 23:45:16', '30d808cbd7c94c66b4512718151b00aa',
        '2021-11-07 01:49:38', '30d808cbd7c94c66b4512718151b00aa');
INSERT INTO `sys_menu`
VALUES ('bc03939d194101fb9a980b1052de8978', '9650554e64c53b03d64f9039e0ead1c0', 0, NULL, 2, 'sys:dict:dictDataMgr',
        NULL, '1457347737067294720', NULL, NULL, '字典数据', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0',
        b'0', 0, NULL, '2021-11-07 22:02:32', '30d808cbd7c94c66b4512718151b00aa', '2021-11-08 23:49:36',
        '30d808cbd7c94c66b4512718151b00aa');
INSERT INTO `sys_menu`
VALUES ('bd7081bf63857bf13bb4a243989bd772', '41ce300ed10c87c39b70f2121c1cf76d', 0, NULL, 2, 'sys:menu:delete', NULL,
        '1455552881344921656', NULL, NULL, '删除菜单', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0',
        0, NULL, '2021-10-30 13:49:44', '30d808cbd7c94c66b4512718151b00aa', '2021-11-02 23:10:25', NULL);
INSERT INTO `sys_menu`
VALUES ('be23e50d8fcd326a6d2819cb7837ec39', 'dd5c824b9f0f61a6cd3a2b703bdffc55', 0, 'ICON', 0, NULL, 'icon-search',
        '1455552881344921657', '', NULL, 'ICON查询', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0',
        0, NULL, '2021-08-15 23:07:29', '30d808cbd7c94c66b4512718151b00aa', '2021-11-07 21:37:47',
        '30d808cbd7c94c66b4512718151b00aa');
INSERT INTO `sys_menu`
VALUES ('bef13f3fd69d059173d1d4f115a4f087', 'd1b05c6888eaedd21abcce51d2433882', 0, NULL, 2, 'devtools:generator:delete',
        NULL, '1455552881344921658', NULL, NULL, '删除Model', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0',
        b'0', b'0', 0, NULL, '2021-10-31 22:51:45', '30d808cbd7c94c66b4512718151b00aa', '2021-11-02 23:10:25', NULL);
INSERT INTO `sys_menu`
VALUES ('bf0ff363ede3a669a5f6ede74d8e6870', 'c9502845a936bb3e1fedac49e9d1fb08', 2, NULL, 2,
        'sys:customComponent:TestdeleteArr', NULL, '1470034231221411842', NULL, NULL, '批量删除', b'0', b'0', '', NULL,
        NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-12-12 22:14:07',
        '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('bf14101f45a4f21385bcbc9adee85123', '1f140fbe7445ada922ea441729af24bd', 0, NULL, 2,
        'devtools:generator:getFields', NULL, '1455552881344921659', NULL, NULL, '获取所有Field列表', b'0', b'0', '', NULL,
        NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-10-31 22:59:59',
        '30d808cbd7c94c66b4512718151b00aa', '2021-11-02 23:10:25', NULL);
INSERT INTO `sys_menu`
VALUES ('c11b5d12dde0c851e7c15077c25061f2', '10928cb4098f2880326ba7cd62e2020d', 3, NULL, 2, 'sys:fileStore:update',
        NULL, '1462047335635488772', NULL, NULL, '修改对象存储', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0',
        b'0', b'0', 0, NULL, '2021-11-20 21:17:03', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('c63616b9f20ccb162894fc6e0ced9f70', '10928cb4098f2880326ba7cd62e2020d', 2, NULL, 2, 'sys:fileStore:deleteArr',
        NULL, '1462047335635488771', NULL, NULL, '批量删除对象存储', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0',
        b'0', b'0', 0, NULL, '2021-11-20 21:17:03', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('c70573e5651c687867efbe506814413f', '41ce300ed10c87c39b70f2121c1cf76d', 0, NULL, 2, 'sys:menu:saveAuthButton',
        NULL, '1458475428021608448', NULL, NULL, '一键添加权限按钮', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0',
        b'0', b'0', 0, NULL, '2021-11-11 00:43:34', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('c8f3bebe18eb193941adf76053f53256', '7e84f61c521790ee185467bcb153f4ae', 0, NULL, 2, 'sys:user:save', NULL,
        '1455552881344921660', NULL, NULL, '添加用户', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0',
        0, NULL, '2021-10-19 22:17:52', '30d808cbd7c94c66b4512718151b00aa', '2021-10-22 21:47:35',
        '30d808cbd7c94c66b4512718151b00aa');
INSERT INTO `sys_menu`
VALUES ('c9502845a936bb3e1fedac49e9d1fb08', NULL, 0, NULL, 1, NULL, '/system/sys/custom-component-test/Index',
        '1470034043522113536', '/system/sys/custom-component-test/Index', NULL, '自定义组件', b'0', b'0', '', NULL, NULL,
        b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-12-12 22:13:23', '30d808cbd7c94c66b4512718151b00aa',
        NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('ce204d3ccb75750c1f496d33523eb078', 'd1b05c6888eaedd21abcce51d2433882', 0, NULL, 2, 'devtools:generator:design',
        NULL, '1455552881344921661', NULL, NULL, '设计Model按钮', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0',
        b'0', b'0', 0, NULL, '2021-10-31 22:55:15', '30d808cbd7c94c66b4512718151b00aa', '2021-11-02 23:10:25', NULL);
INSERT INTO `sys_menu`
VALUES ('ce632f999c5110f1136ef029b82aa791', NULL, 0, NULL, 0, NULL, '/sys', '1455552881344921662', 'LAYOUT', NULL,
        '系统管理', b'0', b'0', 'ant-design:setting-outlined', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0,
        NULL, '2021-08-15 22:53:52', '30d808cbd7c94c66b4512718151b00aa', '2021-11-02 23:10:25', NULL);
INSERT INTO `sys_menu`
VALUES ('cff9044bfd54ae3076d8240c59e4ad1c', 'd1b05c6888eaedd21abcce51d2433882', 0, NULL, 2,
        'devtools:generator:get:page', NULL, '1455552881344921664', NULL, NULL, '分页获取Model', b'0', b'0', '', NULL, NULL,
        b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-10-31 23:01:19', '30d808cbd7c94c66b4512718151b00aa',
        '2021-11-02 23:10:25', NULL);
INSERT INTO `sys_menu`
VALUES ('d167ad7c0e7838e01901b85e014af252', '9650554e64c53b03d64f9039e0ead1c0', 0, NULL, 2, 'sys:dictData:save', NULL,
        '1457293017539694592', NULL, NULL, '添加字典数据', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0',
        b'0', 0, NULL, '2021-11-07 18:25:05', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('d1b05c6888eaedd21abcce51d2433882', 'dd5c824b9f0f61a6cd3a2b703bdffc55', 0, NULL, 1, NULL, 'generator',
        '1455552881344921665', '/devtools/generator/index', NULL, 'Model', b'0', b'0', 'ri:code-s-slash-fill', NULL,
        NULL, b'0', b'1', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-08-15 23:05:45',
        '30d808cbd7c94c66b4512718151b00aa', '2021-11-07 21:36:01', '30d808cbd7c94c66b4512718151b00aa');
INSERT INTO `sys_menu`
VALUES ('d548fec74974686b97bed22da17d7e5f', '7e84f61c521790ee185467bcb153f4ae', 0, NULL, 2, 'sys:dept:getDeptTree',
        NULL, '1455552881344921666', NULL, NULL, '获取部门树', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0',
        b'0', b'0', 0, NULL, '2021-10-28 23:34:45', '30d808cbd7c94c66b4512718151b00aa', '2021-11-02 23:10:25', NULL);
INSERT INTO `sys_menu`
VALUES ('d5ff145c970da65c0d2e16ab6a4c00e5', 'febf2c72c3e1828dd24a2679ff9b9659', 0, NULL, 2, 'sys:dept:update', NULL,
        '1455552881344921667', NULL, NULL, '修改部门', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0',
        0, NULL, '2021-10-28 23:32:01', '30d808cbd7c94c66b4512718151b00aa', '2021-11-02 23:10:25', NULL);
INSERT INTO `sys_menu`
VALUES ('d6ddaddfc99cf44167649751f6505a3a', NULL, 0, NULL, 0, NULL, '/logger', '1455552881344921668', 'LAYOUT', NULL,
        '日志系统', b'0', b'0', 'ant-design:file-text-outlined', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0,
        NULL, '2021-08-15 22:55:30', '30d808cbd7c94c66b4512718151b00aa', '2021-11-02 23:10:25', NULL);
INSERT INTO `sys_menu`
VALUES ('d730d704fd70ee9b0945dadfe3552a3a', 'c9502845a936bb3e1fedac49e9d1fb08', 4, NULL, 2,
        'sys:customComponent:Testget', NULL, '1470034231221411844', NULL, NULL, '获取单个', b'0', b'0', '', NULL, NULL,
        b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-12-12 22:14:07', '30d808cbd7c94c66b4512718151b00aa',
        NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('d7f651661a658047b5485b04aebc43e6', 'dd5c824b9f0f61a6cd3a2b703bdffc55', 0, NULL, 1, NULL,
        'http://127.0.0.1:8081/swagger-ui.html', '1455552881344921669', 'IFRAME', NULL, 'Swagger', b'0', b'0',
        'cib:swagger', '', NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-08-21 12:40:40',
        '30d808cbd7c94c66b4512718151b00aa', '2021-11-07 02:01:36', '30d808cbd7c94c66b4512718151b00aa');
INSERT INTO `sys_menu`
VALUES ('d97f061fc4cd6c537726c301664c2b8f', '10928cb4098f2880326ba7cd62e2020d', 0, NULL, 2, 'sys:fileStore:save', NULL,
        '1462047335635488769', NULL, NULL, '添加对象存储', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0',
        b'0', 0, NULL, '2021-11-20 21:17:03', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('dc6cf7cd963397678674aae58a2657b6', '7e84f61c521790ee185467bcb153f4ae', 0, NULL, 2, 'sys:user:get:page', NULL,
        '1455552881344921670', NULL, NULL, '分页获取用户', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0',
        b'0', 0, NULL, '2021-10-22 21:48:38', '30d808cbd7c94c66b4512718151b00aa', '2021-11-02 23:10:25', NULL);
INSERT INTO `sys_menu`
VALUES ('dd5c824b9f0f61a6cd3a2b703bdffc55', NULL, 0, NULL, 0, NULL, '/devtools', '1455552881344921671', 'LAYOUT', NULL,
        '开发者工具', b'0', b'0', 'ant-design:tool-outlined', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL,
        '2021-08-15 22:51:19', '30d808cbd7c94c66b4512718151b00aa', '2021-11-02 23:10:25', NULL);
INSERT INTO `sys_menu`
VALUES ('dfcaaf2ad507f3dd2893c0355413997a', 'c9502845a936bb3e1fedac49e9d1fb08', 3, NULL, 2,
        'sys:customComponent:Testupdate', NULL, '1470034231221411843', NULL, NULL, '修改', b'0', b'0', '', NULL, NULL,
        b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-12-12 22:14:07', '30d808cbd7c94c66b4512718151b00aa',
        NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('e1a29e78475e4a620de20b456ebf1625', '2e80fce962c46d4b5334245035851535', 0, NULL, 2, 'sys:role:save', NULL,
        '1455552881344921672', NULL, NULL, '添加角色', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0',
        0, NULL, '2021-10-30 15:09:35', '30d808cbd7c94c66b4512718151b00aa', '2021-11-02 23:10:25', NULL);
INSERT INTO `sys_menu`
VALUES ('e22f58fe26ebbccd884694f6dcc78bb5', '7e84f61c521790ee185467bcb153f4ae', 0, NULL, 1, NULL, 'UserDetail/:id',
        '1455552881344921673', '/sys/user/UserDetail', NULL, '用户详情', b'0', b'1', '', NULL, NULL, b'0', b'0', b'0', b'0',
        b'0', b'0', b'0', 0, NULL, '2021-08-29 20:31:15', '30d808cbd7c94c66b4512718151b00aa', '2021-09-04 23:40:40',
        '30d808cbd7c94c66b4512718151b00aa');
INSERT INTO `sys_menu`
VALUES ('e7ffb8149e2af9ea325831eb5af4e840', '9650554e64c53b03d64f9039e0ead1c0', 0, NULL, 2, 'sys:dict:deleteArr', NULL,
        '1457292635363102720', NULL, NULL, '删除多个字典', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0',
        b'0', 0, NULL, '2021-11-07 18:23:34', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('e9cefffaf0b456802e61fb87f8fb7ee6', 'be23e50d8fcd326a6d2819cb7837ec39', 0, NULL, 1, NULL,
        'https://icones.netlify.app/collection/all', '1458453682908344320', 'IFRAME', NULL, 'NetlifyIcon', b'0', b'0',
        'teenyicons:netlify-solid', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL,
        '2021-11-10 23:17:10', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('ece982e424083a57c30cd4ee132d084f', '489a735425de96fa0c2412f5b4ffed66', 0, NULL, 2, 'sys:datasource:update',
        NULL, '1455552881344921674', NULL, NULL, '修改数据源', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0',
        b'0', b'0', 0, NULL, '2021-10-31 21:37:15', '30d808cbd7c94c66b4512718151b00aa', '2021-11-02 23:10:25', NULL);
INSERT INTO `sys_menu`
VALUES ('ed5287e77f82d8cf064549bdce3728f7', NULL, 0, NULL, 0, NULL, '/dashboard', '1455552881344921675', 'LAYOUT', NULL,
        'Dashboard', b'0', b'0', 'ant-design:appstore-outlined', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0',
        0, NULL, '2021-08-15 22:47:20', '30d808cbd7c94c66b4512718151b00aa', '2021-11-03 23:43:04',
        '30d808cbd7c94c66b4512718151b00aa');
INSERT INTO `sys_menu`
VALUES ('ef5834182516fae59cb9c66d792c0a01', '2e80fce962c46d4b5334245035851535', 0, NULL, 2, 'sys:role:update', NULL,
        '1455552881344921676', NULL, NULL, '修改角色', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0',
        0, NULL, '2021-10-30 15:10:25', '30d808cbd7c94c66b4512718151b00aa', '2021-11-02 23:10:25', NULL);
INSERT INTO `sys_menu`
VALUES ('ef6f1fb17b6ef1861e96f2fa12aecb89', 'ed5287e77f82d8cf064549bdce3728f7', 0, NULL, 1, NULL, 'analysis',
        '1455552881344921677', '/dashboard/analysis/index', NULL, '分析页', b'0', b'0',
        'ant-design:fund-projection-screen-outlined', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL,
        '2021-08-15 23:02:01', '30d808cbd7c94c66b4512718151b00aa', '2021-11-07 01:46:45',
        '30d808cbd7c94c66b4512718151b00aa');
INSERT INTO `sys_menu`
VALUES ('f0075d5980bc4a412d581766cbe1dd37', '9650554e64c53b03d64f9039e0ead1c0', 0, NULL, 2, 'sys:dictData:get:page',
        NULL, '1457293592067067904', NULL, NULL, '分页获取字典数据', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0',
        b'0', b'0', 0, NULL, '2021-11-07 18:27:22', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('f0c2a3c9f98bf9ca022ee2b2f9548264', 'a6cf40d746f4fd88ee5f4646fd4933d0', 1, NULL, 2, 'sys:dataClassify:delete',
        NULL, '1459163041078472705', NULL, NULL, '删除数据分类', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0',
        b'0', b'0', 0, NULL, '2021-11-12 22:15:54', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('f4d503cb8a313d4b97f4c02d985fe26a', 'c9502845a936bb3e1fedac49e9d1fb08', 5, NULL, 2,
        'sys:customComponent:Testget:page', NULL, '1470034231221411845', NULL, NULL, '分页获取', b'0', b'0', '', NULL, NULL,
        b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-12-12 22:14:07', '30d808cbd7c94c66b4512718151b00aa',
        NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('f4fd825c9fdd61119576ae47230a9438', '1f140fbe7445ada922ea441729af24bd', 0, NULL, 2,
        'devtools:generator:updateField', NULL, '1455552881344921679', NULL, NULL, '修改Field', b'0', b'0', '', NULL,
        NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-10-31 22:59:37',
        '30d808cbd7c94c66b4512718151b00aa', '2021-11-02 23:10:25', NULL);
INSERT INTO `sys_menu`
VALUES ('fbd828171aa746592f592646f0b61a72', '489a735425de96fa0c2412f5b4ffed66', 0, NULL, 2, 'sys:datasource:get', NULL,
        '1455552881344921680', NULL, NULL, '获取单个数据源', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0',
        b'0', 0, NULL, '2021-10-31 21:37:40', '30d808cbd7c94c66b4512718151b00aa', '2021-11-02 23:10:25', NULL);
INSERT INTO `sys_menu`
VALUES ('fd01a3a5320f1db4efe938cef06a1c57', '1f140fbe7445ada922ea441729af24bd', 0, NULL, 2, 'devtools:generator:back',
        NULL, '1455552881344921681', NULL, NULL, '生成/预览后端代码', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0',
        b'0', b'0', 0, NULL, '2021-10-31 23:04:23', '30d808cbd7c94c66b4512718151b00aa', '2021-11-02 23:10:25', NULL);
INSERT INTO `sys_menu`
VALUES ('fdb72353a3ca9afe4d76bf0bb60fe4a6', '9650554e64c53b03d64f9039e0ead1c0', 0, NULL, 2, 'sys:dict:save', NULL,
        '1457292395851567104', NULL, NULL, '添加字典', b'0', b'0', '', NULL, NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0',
        0, NULL, '2021-11-07 18:22:37', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('fe5111a8219e538e3b9ecc298c295e75', 'c9502845a936bb3e1fedac49e9d1fb08', 0, NULL, 2,
        'sys:customComponent:Testsave', NULL, '1470034231221411840', NULL, NULL, '添加', b'0', b'0', '', NULL, NULL, b'0',
        b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-12-12 22:14:07', '30d808cbd7c94c66b4512718151b00aa', NULL,
        NULL);
INSERT INTO `sys_menu`
VALUES ('febf2c72c3e1828dd24a2679ff9b9659', 'ce632f999c5110f1136ef029b82aa791', 0, NULL, 1, NULL, 'dept',
        '1455552881344921682', '/sys/dept/Index', NULL, '部门管理', b'0', b'0', 'ant-design:apartment-outlined', NULL, NULL,
        b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-08-15 23:27:33', '30d808cbd7c94c66b4512718151b00aa',
        '2021-11-07 01:33:13', '30d808cbd7c94c66b4512718151b00aa');
INSERT INTO `sys_menu`
VALUES ('ff53af146fe98bdfcc599c8e20f4c1dd', '2b56fa6bd71df15a2acaa20cb8ec58b5', 0, NULL, 1, NULL, 'settings',
        '1455552881344921683', '/user/settings/index', NULL, '个人设置', b'0', b'0', 'ant-design:setting-filled', NULL,
        NULL, b'0', b'0', b'0', b'0', b'0', b'0', b'0', 0, NULL, '2021-08-15 23:44:22',
        '30d808cbd7c94c66b4512718151b00aa', '2021-11-07 01:49:05', '30d808cbd7c94c66b4512718151b00aa');
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `id`          varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
    `name`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色名',
    `code`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色编码',
    `level`       int                                                     DEFAULT NULL COMMENT '角色级别',
    `status`      tinyint                                                 DEFAULT '0' COMMENT '状态【启用：0,停用：1】\n',
    `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色描述',
    `remark`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
    `create_time` datetime                                                DEFAULT NULL COMMENT '创建时间',
    `create_by`   varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '创建人',
    `update_time` datetime                                                DEFAULT NULL COMMENT '更新时间',
    `update_by`   varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '更新人',
    `enable`      bit(1)                                                  DEFAULT NULL COMMENT '是否启用【未启用：0,已启用：1】',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb3
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role`
VALUES ('4f276135ba8a1587156988ceff84ce9f', '系统管理员', 'admin', 10, 0, '系统管理员', NULL, '2021-07-11 18:52:05',
        '30d808cbd7c94c66b4512718151b00aa', '2021-08-26 22:42:13', '30d808cbd7c94c66b4512718151b00aa', b'1');
INSERT INTO `sys_role`
VALUES ('6a0513946125893b73028e1d19ab3489', '普通用户', 'ordinary', 1, 0, '普通用户', NULL, '2021-07-11 19:08:42',
        '30d808cbd7c94c66b4512718151b00aa', NULL, NULL, b'1');
INSERT INTO `sys_role`
VALUES ('8288e8e0fc49ec42a6d83d121963af57', '超级管理员', 'administrator', 9999, 0, '系统超级管理员，最高级别管理员', NULL,
        '2021-07-11 18:49:42', '30d808cbd7c94c66b4512718151b00aa', '2021-10-05 21:17:34',
        '30d808cbd7c94c66b4512718151b00aa', b'1');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`
(
    `id`          varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
    `role_id`     varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '角色ID',
    `menu_id`     varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '菜单ID',
    `leaf`        bit(1)                                                  DEFAULT NULL COMMENT '是否叶子节点',
    `remark`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
    `create_time` datetime                                                DEFAULT NULL COMMENT '创建时间',
    `create_by`   varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '创建人',
    `update_time` datetime                                                DEFAULT NULL COMMENT '更新时间',
    `update_by`   varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb3
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu`
VALUES ('01e070fb364e60b4bf7b4bd3a5209e91', '4f276135ba8a1587156988ceff84ce9f', 'ed5287e77f82d8cf064549bdce3728f7',
        b'0', NULL, '2021-11-12 23:44:02', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_role_menu`
VALUES ('19c79e16e6f5317665a3a30c05363d4d', '4f276135ba8a1587156988ceff84ce9f', 'aaa5a3088ffafa59af019dcc40c7fb7c',
        b'1', NULL, '2021-11-12 23:44:02', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_role_menu`
VALUES ('2b6ee0af4888d00f45a70618199b9c0b', '4f276135ba8a1587156988ceff84ce9f', '63122251dae885d3aa2bde7e4b52a6f8',
        b'1', NULL, '2021-11-12 23:44:02', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_role_menu`
VALUES ('449ee863b29209bf6285fab90e328978', '6a0513946125893b73028e1d19ab3489', 'ed5287e77f82d8cf064549bdce3728f7',
        b'0', NULL, '2021-10-31 01:12:00', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_role_menu`
VALUES ('5fb5ff4204fe7e18c553ac9d8669bc9f', '6a0513946125893b73028e1d19ab3489', 'ef6f1fb17b6ef1861e96f2fa12aecb89',
        b'1', NULL, '2021-10-31 01:12:00', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_role_menu`
VALUES ('6b4c007f2bf60d591d4cfe474de7511c', '4f276135ba8a1587156988ceff84ce9f', '5bd090c0ba4e8ffbfd8b329b7f6df1f4',
        b'1', NULL, '2021-11-12 23:44:02', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_role_menu`
VALUES ('bfffa76b80225ac6cf98102444471ebd', '4f276135ba8a1587156988ceff84ce9f', 'a6cf40d746f4fd88ee5f4646fd4933d0',
        b'0', NULL, '2021-11-12 23:44:02', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_role_menu`
VALUES ('c5fb22aa1750daaac19549f354800f7a', '4f276135ba8a1587156988ceff84ce9f', 'ef6f1fb17b6ef1861e96f2fa12aecb89',
        b'1', NULL, '2021-11-12 23:44:02', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_role_menu`
VALUES ('ce58bfd6f5e38a7ad4e6a7dcef80019d', '4f276135ba8a1587156988ceff84ce9f', 'b08c73d026d23251b5d8d5d6769b1589',
        b'1', NULL, '2021-11-12 23:44:02', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
INSERT INTO `sys_role_menu`
VALUES ('f3a90eff3f3427bfe02c31757d0f4015', '4f276135ba8a1587156988ceff84ce9f', 'ce632f999c5110f1136ef029b82aa791',
        b'0', NULL, '2021-11-12 23:44:02', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL);
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
    `sex`         int                                                     DEFAULT NULL COMMENT '性别【未设置：0,男：1,女：2】',
    `age`         int                                                     DEFAULT NULL COMMENT '年龄',
    `birthday`    datetime                                                DEFAULT NULL COMMENT '出生日期',
    `email`       varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
    `phone`       varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '电话',
    `avatar`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像',
    `city`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '所在城市',
    `last_login`  datetime                                                DEFAULT NULL COMMENT '最后登录时间',
    `dept_id`     varchar(64)                                             DEFAULT NULL COMMENT '部门ID',
    `remark`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
    `create_time` datetime                                                DEFAULT NULL COMMENT '创建时间',
    `create_by`   varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '创建人',
    `update_time` datetime                                                DEFAULT NULL COMMENT '更新时间',
    `update_by`   varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '更新人',
    `enable`      bit(1)                                                  DEFAULT NULL COMMENT '是否启用【未启用：0,已启用：1】',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb3
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user`
VALUES ('30d808cbd7c94c66b4512718151b00aa', 'administrator',
        '$2a$10$eLXgj/sT2Vbq.GsWfsPCCeLlRCmsQ2bnPFsyysRAVOgj7CZM5UOB2', '成应奎', 1, 24, '2021-09-05 20:24:23',
        'cxxwl96@sina.com', '15100001111', '/head/head.jpg', NULL, NULL, NULL, NULL, '2021-09-05 23:56:58', NULL,
        '2021-09-06 01:08:10', '30d808cbd7c94c66b4512718151b00aa', b'1');
INSERT INTO `sys_user`
VALUES ('eab5659651ea52b4a998c8722efbe9b2', 'cxx', '$2a$10$rwuPt.Tn6zQhXwDBULEDoO9.7ZhqdXfuLanTunruWBAvaukuOwbKO',
        'cxx', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-09-05 23:24:08', NULL,
        '2021-10-17 22:53:17', '30d808cbd7c94c66b4512718151b00aa', b'1');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`
(
    `id`          varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
    `user_id`     varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '用户ID',
    `role_id`     varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '角色ID',
    `remark`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
    `create_time` datetime                                                DEFAULT NULL COMMENT '创建时间',
    `create_by`   varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '创建人',
    `update_time` datetime                                                DEFAULT NULL COMMENT '更新时间',
    `update_by`   varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '更新人',
    `enable`      bit(1)                                                  DEFAULT NULL COMMENT '是否启用【未启用：0,已启用：1】',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb3
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role`
VALUES ('28576fdb37e36fd06e84f80cf6324587', '30d808cbd7c94c66b4512718151b00aa', '8288e8e0fc49ec42a6d83d121963af57',
        NULL, '2021-09-06 01:08:10', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL, NULL);
INSERT INTO `sys_user_role`
VALUES ('6583428dd21d5849a3f508580d3328d2', 'eab5659651ea52b4a998c8722efbe9b2', '4f276135ba8a1587156988ceff84ce9f',
        NULL, '2021-10-17 22:53:17', '30d808cbd7c94c66b4512718151b00aa', NULL, NULL, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
