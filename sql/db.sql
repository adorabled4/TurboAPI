/*
 Navicat Premium Data Transfer

 Source Server         : centsos-192.168.159.134
 Source Server Type    : MySQL
 Source Server Version : 50740
 Source Host           : 192.168.159.134:3306
 Source Schema         : ry

 Target Server Type    : MySQL
 Target Server Version : 50740
 File Encoding         : 65001

 Date: 21/04/2023 15:51:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for gen_table
-- ----------------------------
DROP TABLE IF EXISTS `gen_table`;
CREATE TABLE `gen_table`  (
                              `table_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
                              `table_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '表名称',
                              `table_comment` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '表描述',
                              `sub_table_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关联子表的表名',
                              `sub_table_fk_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '子表关联的外键名',
                              `class_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '实体类名称',
                              `tpl_category` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作）',
                              `package_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生成包路径',
                              `module_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生成模块名',
                              `business_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生成业务名',
                              `function_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生成功能名',
                              `function_author` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生成功能作者',
                              `gen_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
                              `gen_path` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '/' COMMENT '生成路径（不填默认项目路径）',
                              `options` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '其它生成选项',
                              `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
                              `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                              `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
                              `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                              `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
                              PRIMARY KEY (`table_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '代码生成业务表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gen_table
-- ----------------------------
INSERT INTO `gen_table` VALUES (26, 't_poet', '', NULL, NULL, 'Poet', 'crud', 'com.api.apiinterface', 'apiinterface', 'poet', NULL, 'adorabled4', '0', '/', NULL, 'admin', '2023-03-11 11:17:57', '', NULL, NULL);

-- ----------------------------
-- Table structure for gen_table_column
-- ----------------------------
DROP TABLE IF EXISTS `gen_table_column`;
CREATE TABLE `gen_table_column`  (
                                     `column_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
                                     `table_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '归属表编号',
                                     `column_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '列名称',
                                     `column_comment` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '列描述',
                                     `column_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '列类型',
                                     `java_type` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'JAVA类型',
                                     `java_field` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'JAVA字段名',
                                     `is_pk` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否主键（1是）',
                                     `is_increment` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否自增（1是）',
                                     `is_required` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否必填（1是）',
                                     `is_insert` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否为插入字段（1是）',
                                     `is_edit` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否编辑字段（1是）',
                                     `is_list` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否列表字段（1是）',
                                     `is_query` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否查询字段（1是）',
                                     `query_type` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
                                     `html_type` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
                                     `dict_type` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典类型',
                                     `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
                                     `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
                                     `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                     `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
                                     `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                     PRIMARY KEY (`column_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 260 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '代码生成业务表字段' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gen_table_column
-- ----------------------------
INSERT INTO `gen_table_column` VALUES (254, '26', 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-03-11 11:17:57', '', NULL);
INSERT INTO `gen_table_column` VALUES (255, '26', 'author', '作者', 'varchar(64)', 'String', 'author', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-03-11 11:17:57', '', NULL);
INSERT INTO `gen_table_column` VALUES (256, '26', 'dynasty', '朝代', 'varchar(32)', 'String', 'dynasty', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-03-11 11:17:57', '', NULL);
INSERT INTO `gen_table_column` VALUES (257, '26', 'title', '题目', 'varchar(128)', 'String', 'title', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-03-11 11:17:57', '', NULL);
INSERT INTO `gen_table_column` VALUES (258, '26', 'poetry', '诗句', 'varchar(255)', 'String', 'poetry', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-03-11 11:17:57', '', NULL);
INSERT INTO `gen_table_column` VALUES (259, '26', 'create_time', '添加时间', 'timestamp', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 6, 'admin', '2023-03-11 11:17:57', '', NULL);

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
                               `config_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
                               `config_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '参数名称',
                               `config_key` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '参数键名',
                               `config_value` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '参数键值',
                               `config_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
                               `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
                               `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                               `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
                               `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                               `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
                               PRIMARY KEY (`config_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '参数配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (1, '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', 'Y', 'admin', '2023-03-09 13:37:10', '', NULL, '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow');
INSERT INTO `sys_config` VALUES (2, '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 'admin', '2023-03-09 13:37:10', '', NULL, '初始化密码 123456');
INSERT INTO `sys_config` VALUES (3, '主框架页-侧边栏主题', 'sys.index.sideTheme', 'theme-dark', 'Y', 'admin', '2023-03-09 13:37:10', '', NULL, '深色主题theme-dark，浅色主题theme-light');
INSERT INTO `sys_config` VALUES (4, '账号自助-验证码开关', 'sys.account.captchaEnabled', 'true', 'Y', 'admin', '2023-03-09 13:37:10', '', NULL, '是否开启验证码功能（true开启，false关闭）');
INSERT INTO `sys_config` VALUES (5, '账号自助-是否开启用户注册功能', 'sys.account.registerUser', 'false', 'Y', 'admin', '2023-03-09 13:37:10', '', NULL, '是否开启注册用户功能（true开启，false关闭）');
INSERT INTO `sys_config` VALUES (6, '用户登录-黑名单列表', 'sys.login.blackIPList', '', 'Y', 'admin', '2023-03-09 13:37:10', '', NULL, '设置登录IP黑名单限制，多个匹配项以;分隔，支持匹配（*通配、网段）');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
                             `dept_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
                             `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父部门id',
                             `ancestors` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '祖级列表',
                             `dept_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '部门名称',
                             `order_num` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
                             `leader` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责人',
                             `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
                             `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
                             `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
                             `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
                             `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
                             `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                             `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
                             `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                             PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 110 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (100, 0, '0', '若依科技', 0, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2023-03-09 13:37:10', '', NULL);
INSERT INTO `sys_dept` VALUES (101, 100, '0,100', '深圳总公司', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2023-03-09 13:37:10', '', NULL);
INSERT INTO `sys_dept` VALUES (102, 100, '0,100', '长沙分公司', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2023-03-09 13:37:10', '', NULL);
INSERT INTO `sys_dept` VALUES (103, 101, '0,100,101', '研发部门', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2023-03-09 13:37:10', '', NULL);
INSERT INTO `sys_dept` VALUES (104, 101, '0,100,101', '市场部门', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2023-03-09 13:37:10', '', NULL);
INSERT INTO `sys_dept` VALUES (105, 101, '0,100,101', '测试部门', 3, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2023-03-09 13:37:10', '', NULL);
INSERT INTO `sys_dept` VALUES (106, 101, '0,100,101', '财务部门', 4, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2023-03-09 13:37:10', '', NULL);
INSERT INTO `sys_dept` VALUES (107, 101, '0,100,101', '运维部门', 5, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2023-03-09 13:37:10', '', NULL);
INSERT INTO `sys_dept` VALUES (108, 102, '0,100,102', '市场部门', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2023-03-09 13:37:10', '', NULL);
INSERT INTO `sys_dept` VALUES (109, 102, '0,100,102', '财务部门', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2023-03-09 13:37:10', '', NULL);

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
                                  `dict_code` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
                                  `dict_sort` int(4) NULL DEFAULT 0 COMMENT '字典排序',
                                  `dict_label` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典标签',
                                  `dict_value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典键值',
                                  `dict_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典类型',
                                  `css_class` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
                                  `list_class` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表格回显样式',
                                  `is_default` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
                                  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
                                  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
                                  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
                                  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
                                  PRIMARY KEY (`dict_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES (1, 1, '男', '0', 'sys_user_sex', '', '', 'Y', '0', 'admin', '2023-03-09 13:37:10', '', NULL, '性别男');
INSERT INTO `sys_dict_data` VALUES (2, 2, '女', '1', 'sys_user_sex', '', '', 'N', '0', 'admin', '2023-03-09 13:37:10', '', NULL, '性别女');
INSERT INTO `sys_dict_data` VALUES (3, 3, '未知', '2', 'sys_user_sex', '', '', 'N', '0', 'admin', '2023-03-09 13:37:10', '', NULL, '性别未知');
INSERT INTO `sys_dict_data` VALUES (4, 1, '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 'admin', '2023-03-09 13:37:10', '', NULL, '显示菜单');
INSERT INTO `sys_dict_data` VALUES (5, 2, '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', 'admin', '2023-03-09 13:37:10', '', NULL, '隐藏菜单');
INSERT INTO `sys_dict_data` VALUES (6, 1, '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', 'admin', '2023-03-09 13:37:10', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (7, 2, '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', 'admin', '2023-03-09 13:37:10', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (8, 1, '正常', '0', 'sys_job_status', '', 'primary', 'Y', '0', 'admin', '2023-03-09 13:37:10', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (9, 2, '暂停', '1', 'sys_job_status', '', 'danger', 'N', '0', 'admin', '2023-03-09 13:37:10', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (10, 1, '默认', 'DEFAULT', 'sys_job_group', '', '', 'Y', '0', 'admin', '2023-03-09 13:37:10', '', NULL, '默认分组');
INSERT INTO `sys_dict_data` VALUES (11, 2, '系统', 'SYSTEM', 'sys_job_group', '', '', 'N', '0', 'admin', '2023-03-09 13:37:10', '', NULL, '系统分组');
INSERT INTO `sys_dict_data` VALUES (12, 1, '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', 'admin', '2023-03-09 13:37:10', '', NULL, '系统默认是');
INSERT INTO `sys_dict_data` VALUES (13, 2, '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', 'admin', '2023-03-09 13:37:10', '', NULL, '系统默认否');
INSERT INTO `sys_dict_data` VALUES (14, 1, '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', 'admin', '2023-03-09 13:37:10', '', NULL, '通知');
INSERT INTO `sys_dict_data` VALUES (15, 2, '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', 'admin', '2023-03-09 13:37:10', '', NULL, '公告');
INSERT INTO `sys_dict_data` VALUES (16, 1, '正常', '0', 'sys_notice_status', '', 'primary', 'Y', '0', 'admin', '2023-03-09 13:37:10', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (17, 2, '关闭', '1', 'sys_notice_status', '', 'danger', 'N', '0', 'admin', '2023-03-09 13:37:10', '', NULL, '关闭状态');
INSERT INTO `sys_dict_data` VALUES (18, 99, '其他', '0', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2023-03-09 13:37:10', '', NULL, '其他操作');
INSERT INTO `sys_dict_data` VALUES (19, 1, '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2023-03-09 13:37:10', '', NULL, '新增操作');
INSERT INTO `sys_dict_data` VALUES (20, 2, '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2023-03-09 13:37:10', '', NULL, '修改操作');
INSERT INTO `sys_dict_data` VALUES (21, 3, '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2023-03-09 13:37:10', '', NULL, '删除操作');
INSERT INTO `sys_dict_data` VALUES (22, 4, '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', 'admin', '2023-03-09 13:37:10', '', NULL, '授权操作');
INSERT INTO `sys_dict_data` VALUES (23, 5, '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2023-03-09 13:37:10', '', NULL, '导出操作');
INSERT INTO `sys_dict_data` VALUES (24, 6, '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2023-03-09 13:37:10', '', NULL, '导入操作');
INSERT INTO `sys_dict_data` VALUES (25, 7, '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2023-03-09 13:37:10', '', NULL, '强退操作');
INSERT INTO `sys_dict_data` VALUES (26, 8, '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2023-03-09 13:37:10', '', NULL, '生成操作');
INSERT INTO `sys_dict_data` VALUES (27, 9, '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2023-03-09 13:37:10', '', NULL, '清空操作');
INSERT INTO `sys_dict_data` VALUES (28, 1, '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', 'admin', '2023-03-09 13:37:10', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (29, 2, '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', 'admin', '2023-03-09 13:37:10', '', NULL, '停用状态');

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
                                  `dict_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
                                  `dict_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典名称',
                                  `dict_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典类型',
                                  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
                                  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
                                  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
                                  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
                                  PRIMARY KEY (`dict_id`) USING BTREE,
                                  UNIQUE INDEX `dict_type`(`dict_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES (1, '用户性别', 'sys_user_sex', '0', 'admin', '2023-03-09 13:37:10', '', NULL, '用户性别列表');
INSERT INTO `sys_dict_type` VALUES (2, '菜单状态', 'sys_show_hide', '0', 'admin', '2023-03-09 13:37:10', '', NULL, '菜单状态列表');
INSERT INTO `sys_dict_type` VALUES (3, '系统开关', 'sys_normal_disable', '0', 'admin', '2023-03-09 13:37:10', '', NULL, '系统开关列表');
INSERT INTO `sys_dict_type` VALUES (4, '任务状态', 'sys_job_status', '0', 'admin', '2023-03-09 13:37:10', '', NULL, '任务状态列表');
INSERT INTO `sys_dict_type` VALUES (5, '任务分组', 'sys_job_group', '0', 'admin', '2023-03-09 13:37:10', '', NULL, '任务分组列表');
INSERT INTO `sys_dict_type` VALUES (6, '系统是否', 'sys_yes_no', '0', 'admin', '2023-03-09 13:37:10', '', NULL, '系统是否列表');
INSERT INTO `sys_dict_type` VALUES (7, '通知类型', 'sys_notice_type', '0', 'admin', '2023-03-09 13:37:10', '', NULL, '通知类型列表');
INSERT INTO `sys_dict_type` VALUES (8, '通知状态', 'sys_notice_status', '0', 'admin', '2023-03-09 13:37:10', '', NULL, '通知状态列表');
INSERT INTO `sys_dict_type` VALUES (9, '操作类型', 'sys_oper_type', '0', 'admin', '2023-03-09 13:37:10', '', NULL, '操作类型列表');
INSERT INTO `sys_dict_type` VALUES (10, '系统状态', 'sys_common_status', '0', 'admin', '2023-03-09 13:37:10', '', NULL, '登录状态列表');

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job`  (
                            `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务ID',
                            `job_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '任务名称',
                            `job_group` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'DEFAULT' COMMENT '任务组名',
                            `invoke_target` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '调用目标字符串',
                            `cron_expression` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'cron执行表达式',
                            `misfire_policy` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
                            `concurrent` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '是否并发执行（0允许 1禁止）',
                            `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1暂停）',
                            `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
                            `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                            `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
                            `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                            `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注信息',
                            PRIMARY KEY (`job_id`, `job_name`, `job_group`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '定时任务调度表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_job
-- ----------------------------
INSERT INTO `sys_job` VALUES (1, '系统默认（无参）', 'DEFAULT', 'ryTask.ryNoParams', '0/10 * * * * ?', '3', '1', '1', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_job` VALUES (2, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '0/15 * * * * ?', '3', '1', '1', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_job` VALUES (3, '系统默认（多参）', 'DEFAULT', 'ryTask.ryMultipleParams(\'ry\', true, 2000L, 316.50D, 100)', '0/20 * * * * ?', '3', '1', '1', 'admin', '2023-03-09 13:37:10', '', NULL, '');

-- ----------------------------
-- Table structure for sys_job_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_job_log`;
CREATE TABLE `sys_job_log`  (
                                `job_log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
                                `job_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务名称',
                                `job_group` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务组名',
                                `invoke_target` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '调用目标字符串',
                                `job_message` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志信息',
                                `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
                                `exception_info` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '异常信息',
                                `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                PRIMARY KEY (`job_log_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '定时任务调度日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_job_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_logininfor
-- ----------------------------
DROP TABLE IF EXISTS `sys_logininfor`;
CREATE TABLE `sys_logininfor`  (
                                   `info_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
                                   `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '用户账号',
                                   `ipaddr` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录IP地址',
                                   `login_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录地点',
                                   `browser` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '浏览器类型',
                                   `os` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '操作系统',
                                   `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
                                   `msg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '提示消息',
                                   `login_time` datetime NULL DEFAULT NULL COMMENT '访问时间',
                                   PRIMARY KEY (`info_id`) USING BTREE,
                                   INDEX `idx_sys_logininfor_s`(`status`) USING BTREE,
                                   INDEX `idx_sys_logininfor_lt`(`login_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 113 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统访问记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_logininfor
-- ----------------------------
INSERT INTO `sys_logininfor` VALUES (100, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-03-10 15:48:55');
INSERT INTO `sys_logininfor` VALUES (101, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-03-10 15:56:13');
INSERT INTO `sys_logininfor` VALUES (102, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-03-11 09:53:43');
INSERT INTO `sys_logininfor` VALUES (103, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-03-11 11:17:35');
INSERT INTO `sys_logininfor` VALUES (104, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-03-13 05:35:23');
INSERT INTO `sys_logininfor` VALUES (105, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-04-04 11:38:07');
INSERT INTO `sys_logininfor` VALUES (106, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-04-06 08:35:33');
INSERT INTO `sys_logininfor` VALUES (107, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-04-11 13:08:11');
INSERT INTO `sys_logininfor` VALUES (108, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-04-15 03:55:55');
INSERT INTO `sys_logininfor` VALUES (109, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-04-15 05:17:34');
INSERT INTO `sys_logininfor` VALUES (110, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-04-18 09:13:53');
INSERT INTO `sys_logininfor` VALUES (111, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-04-20 12:04:37');
INSERT INTO `sys_logininfor` VALUES (112, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-04-20 13:15:12');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
                             `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
                             `menu_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
                             `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父菜单ID',
                             `order_num` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
                             `path` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '路由地址',
                             `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件路径',
                             `query` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路由参数',
                             `is_frame` int(1) NULL DEFAULT 1 COMMENT '是否为外链（0是 1否）',
                             `is_cache` int(1) NULL DEFAULT 0 COMMENT '是否缓存（0缓存 1不缓存）',
                             `menu_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
                             `visible` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
                             `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
                             `perms` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限标识',
                             `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '#' COMMENT '菜单图标',
                             `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
                             `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                             `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
                             `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                             `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
                             PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2010 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', 0, 1, 'system', NULL, '', 1, 0, 'M', '0', '0', '', 'system', 'admin', '2023-03-09 13:37:10', '', NULL, '系统管理目录');
INSERT INTO `sys_menu` VALUES (2, '系统监控', 0, 2, 'monitor', NULL, '', 1, 0, 'M', '0', '0', '', 'monitor', 'admin', '2023-03-09 13:37:10', '', NULL, '系统监控目录');
INSERT INTO `sys_menu` VALUES (3, '系统工具', 0, 3, 'tool', NULL, '', 1, 0, 'M', '0', '0', '', 'tool', 'admin', '2023-03-09 13:37:10', '', NULL, '系统工具目录');
INSERT INTO `sys_menu` VALUES (4, '若依官网', 0, 4, 'http://ruoyi.vip', NULL, '', 0, 0, 'M', '0', '0', '', 'guide', 'admin', '2023-03-09 13:37:10', '', NULL, '若依官网地址');
INSERT INTO `sys_menu` VALUES (100, '用户管理', 1, 1, 'user', 'system/user/index', '', 1, 0, 'C', '0', '0', 'system:user:list', 'user', 'admin', '2023-03-09 13:37:10', '', NULL, '用户管理菜单');
INSERT INTO `sys_menu` VALUES (101, '角色管理', 1, 2, 'role', 'system/role/index', '', 1, 0, 'C', '0', '0', 'system:role:list', 'peoples', 'admin', '2023-03-09 13:37:10', '', NULL, '角色管理菜单');
INSERT INTO `sys_menu` VALUES (102, '菜单管理', 1, 3, 'menu', 'system/menu/index', '', 1, 0, 'C', '0', '0', 'system:menu:list', 'tree-table', 'admin', '2023-03-09 13:37:10', '', NULL, '菜单管理菜单');
INSERT INTO `sys_menu` VALUES (103, '部门管理', 1, 4, 'dept', 'system/dept/index', '', 1, 0, 'C', '0', '0', 'system:dept:list', 'tree', 'admin', '2023-03-09 13:37:10', '', NULL, '部门管理菜单');
INSERT INTO `sys_menu` VALUES (104, '岗位管理', 1, 5, 'post', 'system/post/index', '', 1, 0, 'C', '0', '0', 'system:post:list', 'post', 'admin', '2023-03-09 13:37:10', '', NULL, '岗位管理菜单');
INSERT INTO `sys_menu` VALUES (105, '字典管理', 1, 6, 'dict', 'system/dict/index', '', 1, 0, 'C', '0', '0', 'system:dict:list', 'dict', 'admin', '2023-03-09 13:37:10', '', NULL, '字典管理菜单');
INSERT INTO `sys_menu` VALUES (106, '参数设置', 1, 7, 'config', 'system/config/index', '', 1, 0, 'C', '0', '0', 'system:config:list', 'edit', 'admin', '2023-03-09 13:37:10', '', NULL, '参数设置菜单');
INSERT INTO `sys_menu` VALUES (107, '通知公告', 1, 8, 'notice', 'system/notice/index', '', 1, 0, 'C', '0', '0', 'system:notice:list', 'message', 'admin', '2023-03-09 13:37:10', '', NULL, '通知公告菜单');
INSERT INTO `sys_menu` VALUES (108, '日志管理', 1, 9, 'log', '', '', 1, 0, 'M', '0', '0', '', 'log', 'admin', '2023-03-09 13:37:10', '', NULL, '日志管理菜单');
INSERT INTO `sys_menu` VALUES (109, '在线用户', 2, 1, 'online', 'monitor/online/index', '', 1, 0, 'C', '0', '0', 'monitor:online:list', 'online', 'admin', '2023-03-09 13:37:10', '', NULL, '在线用户菜单');
INSERT INTO `sys_menu` VALUES (110, '定时任务', 2, 2, 'job', 'monitor/job/index', '', 1, 0, 'C', '0', '0', 'monitor:job:list', 'job', 'admin', '2023-03-09 13:37:10', '', NULL, '定时任务菜单');
INSERT INTO `sys_menu` VALUES (111, '数据监控', 2, 3, 'druid', 'monitor/druid/index', '', 1, 0, 'C', '0', '0', 'monitor:druid:list', 'druid', 'admin', '2023-03-09 13:37:10', '', NULL, '数据监控菜单');
INSERT INTO `sys_menu` VALUES (112, '服务监控', 2, 4, 'server', 'monitor/server/index', '', 1, 0, 'C', '0', '0', 'monitor:server:list', 'server', 'admin', '2023-03-09 13:37:10', '', NULL, '服务监控菜单');
INSERT INTO `sys_menu` VALUES (113, '缓存监控', 2, 5, 'cache', 'monitor/cache/index', '', 1, 0, 'C', '0', '0', 'monitor:cache:list', 'redis', 'admin', '2023-03-09 13:37:10', '', NULL, '缓存监控菜单');
INSERT INTO `sys_menu` VALUES (114, '缓存列表', 2, 6, 'cacheList', 'monitor/cache/list', '', 1, 0, 'C', '0', '0', 'monitor:cache:list', 'redis-list', 'admin', '2023-03-09 13:37:10', '', NULL, '缓存列表菜单');
INSERT INTO `sys_menu` VALUES (115, '表单构建', 3, 1, 'build', 'tool/build/index', '', 1, 0, 'C', '0', '0', 'tool:build:list', 'build', 'admin', '2023-03-09 13:37:10', '', NULL, '表单构建菜单');
INSERT INTO `sys_menu` VALUES (116, '代码生成', 3, 2, 'gen', 'tool/gen/index', '', 1, 0, 'C', '0', '0', 'tool:gen:list', 'code', 'admin', '2023-03-09 13:37:10', '', NULL, '代码生成菜单');
INSERT INTO `sys_menu` VALUES (117, '系统接口', 3, 3, 'swagger', 'tool/swagger/index', '', 1, 0, 'C', '0', '0', 'tool:swagger:list', 'swagger', 'admin', '2023-03-09 13:37:10', '', NULL, '系统接口菜单');
INSERT INTO `sys_menu` VALUES (500, '操作日志', 108, 1, 'operlog', 'monitor/operlog/index', '', 1, 0, 'C', '0', '0', 'monitor:operlog:list', 'form', 'admin', '2023-03-09 13:37:10', '', NULL, '操作日志菜单');
INSERT INTO `sys_menu` VALUES (501, '登录日志', 108, 2, 'logininfor', 'monitor/logininfor/index', '', 1, 0, 'C', '0', '0', 'monitor:logininfor:list', 'logininfor', 'admin', '2023-03-09 13:37:10', '', NULL, '登录日志菜单');
INSERT INTO `sys_menu` VALUES (1000, '用户查询', 100, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:user:query', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1001, '用户新增', 100, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:user:add', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1002, '用户修改', 100, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:user:edit', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1003, '用户删除', 100, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:user:remove', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1004, '用户导出', 100, 5, '', '', '', 1, 0, 'F', '0', '0', 'system:user:export', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1005, '用户导入', 100, 6, '', '', '', 1, 0, 'F', '0', '0', 'system:user:import', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1006, '重置密码', 100, 7, '', '', '', 1, 0, 'F', '0', '0', 'system:user:resetPwd', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1007, '角色查询', 101, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:role:query', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1008, '角色新增', 101, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:role:add', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1009, '角色修改', 101, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:role:edit', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1010, '角色删除', 101, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:role:remove', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1011, '角色导出', 101, 5, '', '', '', 1, 0, 'F', '0', '0', 'system:role:export', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1012, '菜单查询', 102, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:query', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1013, '菜单新增', 102, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:add', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1014, '菜单修改', 102, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:edit', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1015, '菜单删除', 102, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:remove', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1016, '部门查询', 103, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:query', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1017, '部门新增', 103, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:add', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1018, '部门修改', 103, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:edit', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1019, '部门删除', 103, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:remove', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1020, '岗位查询', 104, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:post:query', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1021, '岗位新增', 104, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:post:add', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1022, '岗位修改', 104, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:post:edit', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1023, '岗位删除', 104, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:post:remove', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1024, '岗位导出', 104, 5, '', '', '', 1, 0, 'F', '0', '0', 'system:post:export', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1025, '字典查询', 105, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:query', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1026, '字典新增', 105, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:add', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1027, '字典修改', 105, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:edit', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1028, '字典删除', 105, 4, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:remove', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1029, '字典导出', 105, 5, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:export', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1030, '参数查询', 106, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:query', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1031, '参数新增', 106, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:add', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1032, '参数修改', 106, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:edit', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1033, '参数删除', 106, 4, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:remove', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1034, '参数导出', 106, 5, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:export', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1035, '公告查询', 107, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:query', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1036, '公告新增', 107, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:add', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1037, '公告修改', 107, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:edit', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1038, '公告删除', 107, 4, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:remove', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1039, '操作查询', 500, 1, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:operlog:query', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1040, '操作删除', 500, 2, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:operlog:remove', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1041, '日志导出', 500, 3, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:operlog:export', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1042, '登录查询', 501, 1, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:query', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1043, '登录删除', 501, 2, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:remove', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1044, '日志导出', 501, 3, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:export', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1045, '账户解锁', 501, 4, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:unlock', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1046, '在线查询', 109, 1, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:online:query', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1047, '批量强退', 109, 2, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:online:batchLogout', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1048, '单条强退', 109, 3, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:online:forceLogout', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1049, '任务查询', 110, 1, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:query', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1050, '任务新增', 110, 2, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:add', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1051, '任务修改', 110, 3, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:edit', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1052, '任务删除', 110, 4, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:remove', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1053, '状态修改', 110, 5, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:changeStatus', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1054, '任务导出', 110, 6, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:export', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1055, '生成查询', 116, 1, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:query', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1056, '生成修改', 116, 2, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:edit', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1057, '生成删除', 116, 3, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:remove', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1058, '导入代码', 116, 4, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:import', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1059, '预览代码', 116, 5, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:preview', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1060, '生成代码', 116, 6, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:code', '#', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2000, '项目管理', 0, 1, 'admin', NULL, NULL, 1, 0, 'M', '0', '0', NULL, 'build', 'admin', '2023-03-09 14:07:07', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2001, '接口管理', 2000, 1, 'interface', 'admin/interface/index', NULL, 1, 0, 'C', '0', '0', 'admin:interfaceEntity:list', 'druid', 'admin', '2023-03-09 14:09:28', 'admin', '2023-03-10 16:00:31', '');
INSERT INTO `sys_menu` VALUES (2002, '用户管理', 2000, 4, 'user', 'admin/user/index', NULL, 1, 0, 'C', '0', '0', 'admin:userEntity:list', 'peoples', 'admin', '2023-03-09 14:11:18', 'admin', '2023-03-09 14:32:59', '');
INSERT INTO `sys_menu` VALUES (2003, '接口次数管理', 2000, 3, 'userInterfaceInfo', 'admin/userInterfaceInfo/index', NULL, 1, 0, 'C', '0', '0', 'admin:userInterfaceInfoEntity:list', 'row', 'admin', '2023-03-09 14:12:02', 'admin', '2023-03-09 14:19:46', '');
INSERT INTO `sys_menu` VALUES (2004, '接口示例管理', 2000, 2, 'interfaceExample', 'admin/interfaceExample/index', NULL, 1, 0, 'C', '0', '0', 'admin:interfaceExampleEntity:list', 'edit', 'admin', '2023-03-09 14:14:03', 'admin', '2023-03-09 14:19:42', '');
INSERT INTO `sys_menu` VALUES (2005, 'api管理', 0, 0, 'api', NULL, NULL, 1, 0, 'M', '0', '0', '', 'date-range', 'admin', '2023-03-11 11:25:21', 'admin', '2023-04-15 06:40:03', '');
INSERT INTO `sys_menu` VALUES (2006, '随机诗句api', 2005, 1, '/apiinterface/poet', 'admin/poet/index', NULL, 1, 0, 'C', '0', '0', 'apiinterface:poet:list', 'education', 'admin', '2023-03-11 11:26:39', 'admin', '2023-04-04 11:59:12', '');
INSERT INTO `sys_menu` VALUES (2008, '新增诗句', 2006, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'apiinterface:poet:add', '#', 'admin', '2023-04-04 12:12:01', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2009, '诗句删除', 2006, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'apiinterface:poet:remove', '#', 'admin', '2023-04-04 12:13:48', '', NULL, '');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
                               `notice_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '公告ID',
                               `notice_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公告标题',
                               `notice_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公告类型（1通知 2公告）',
                               `notice_content` longblob NULL COMMENT '公告内容',
                               `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
                               `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
                               `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                               `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
                               `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                               `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
                               PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '通知公告表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES (1, '温馨提醒：2018-07-01 若依新版本发布啦', '2', 0xE696B0E78988E69CACE58685E5AEB9, '0', 'admin', '2023-03-09 13:37:10', '', NULL, '管理员');
INSERT INTO `sys_notice` VALUES (2, '维护通知：2018-07-01 若依系统凌晨维护', '1', 0xE7BBB4E68AA4E58685E5AEB9, '0', 'admin', '2023-03-09 13:37:10', '', NULL, '管理员');

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log`  (
                                 `oper_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
                                 `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '模块标题',
                                 `business_type` int(2) NULL DEFAULT 0 COMMENT '业务类型（0其它 1新增 2修改 3删除）',
                                 `method` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '方法名称',
                                 `request_method` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '请求方式',
                                 `operator_type` int(1) NULL DEFAULT 0 COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
                                 `oper_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '操作人员',
                                 `dept_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '部门名称',
                                 `oper_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '请求URL',
                                 `oper_ip` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '主机地址',
                                 `oper_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '操作地点',
                                 `oper_param` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '请求参数',
                                 `json_result` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '返回参数',
                                 `status` int(1) NULL DEFAULT 0 COMMENT '操作状态（0正常 1异常）',
                                 `error_msg` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '错误消息',
                                 `oper_time` datetime NULL DEFAULT NULL COMMENT '操作时间',
                                 `cost_time` bigint(20) NULL DEFAULT 0 COMMENT '消耗时间',
                                 PRIMARY KEY (`oper_id`) USING BTREE,
                                 INDEX `idx_sys_oper_log_bt`(`business_type`) USING BTREE,
                                 INDEX `idx_sys_oper_log_s`(`status`) USING BTREE,
                                 INDEX `idx_sys_oper_log_ot`(`oper_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 165 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '操作日志记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------
INSERT INTO `sys_oper_log` VALUES (100, '代码生成', 6, 'com.ruoyi.generator.controller.GenController.importTableSave()', 'POST', 1, 'admin', NULL, '/tool/gen/importTable', '127.0.0.1', '内网IP', '{\"tables\":\"t_white_list,t_user_interface_info,t_user,t_interface_example,t_interface\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-03-09 13:38:23', 195);
INSERT INTO `sys_oper_log` VALUES (101, '代码生成', 8, 'com.ruoyi.generator.controller.GenController.batchGenCode()', 'GET', 1, 'admin', NULL, '/tool/gen/batchGenCode', '127.0.0.1', '内网IP', '{\"tables\":\"t_interface,t_interface_example,t_user,t_user_interface_info,t_white_list\"}', NULL, 0, NULL, '2023-03-09 13:38:28', 412);
INSERT INTO `sys_oper_log` VALUES (102, '代码生成', 8, 'com.ruoyi.generator.controller.GenController.batchGenCode()', 'GET', 1, 'admin', NULL, '/tool/gen/batchGenCode', '127.0.0.1', '内网IP', '{\"tables\":\"t_interface,t_interface_example,t_user,t_user_interface_info,t_white_list\"}', NULL, 0, NULL, '2023-03-09 13:38:40', 157);
INSERT INTO `sys_oper_log` VALUES (103, '代码生成', 8, 'com.ruoyi.generator.controller.GenController.batchGenCode()', 'GET', 1, 'admin', NULL, '/tool/gen/batchGenCode', '127.0.0.1', '内网IP', '{\"tables\":\"t_interface,t_interface_example,t_user,t_user_interface_info,t_white_list\"}', NULL, 0, NULL, '2023-03-09 13:40:30', 388);
INSERT INTO `sys_oper_log` VALUES (104, '代码生成', 6, 'com.ruoyi.generator.controller.GenController.importTableSave()', 'POST', 1, 'admin', NULL, '/tool/gen/importTable', '127.0.0.1', '内网IP', '{\"tables\":\"t_interface_entity,t_interface_example_entity,t_user_entity,t_user_interface_info_entity\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-03-09 13:50:31', 205);
INSERT INTO `sys_oper_log` VALUES (105, '代码生成', 3, 'com.ruoyi.generator.controller.GenController.remove()', 'DELETE', 1, 'admin', NULL, '/tool/gen/1,2,3,4,5,6,7,8,9', '127.0.0.1', '内网IP', '{}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-03-09 13:50:35', 24);
INSERT INTO `sys_oper_log` VALUES (106, '代码生成', 6, 'com.ruoyi.generator.controller.GenController.importTableSave()', 'POST', 1, 'admin', NULL, '/tool/gen/importTable', '127.0.0.1', '内网IP', '{\"tables\":\"t_user_interface_info_entity,t_user_entity,t_interface_example_entity,t_interface_entity,t_white_list\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-03-09 13:50:42', 125);
INSERT INTO `sys_oper_log` VALUES (107, '代码生成', 8, 'com.ruoyi.generator.controller.GenController.batchGenCode()', 'GET', 1, 'admin', NULL, '/tool/gen/batchGenCode', '127.0.0.1', '内网IP', '{\"tables\":\"t_interface_entity,t_interface_example_entity,t_user_entity,t_user_interface_info_entity,t_white_list\"}', NULL, 0, NULL, '2023-03-09 13:50:50', 361);
INSERT INTO `sys_oper_log` VALUES (108, '代码生成', 3, 'com.ruoyi.generator.controller.GenController.remove()', 'DELETE', 1, 'admin', NULL, '/tool/gen/10,11,12,13,14', '127.0.0.1', '内网IP', '{}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-03-09 13:54:21', 6);
INSERT INTO `sys_oper_log` VALUES (109, '代码生成', 6, 'com.ruoyi.generator.controller.GenController.importTableSave()', 'POST', 1, 'admin', NULL, '/tool/gen/importTable', '127.0.0.1', '内网IP', '{\"tables\":\"t_interfaceEntity,t_interface_exampleEntity,t_userEntity,t_user_interface_infoEntity,t_white_list\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-03-09 13:54:29', 140);
INSERT INTO `sys_oper_log` VALUES (110, '代码生成', 8, 'com.ruoyi.generator.controller.GenController.batchGenCode()', 'GET', 1, 'admin', NULL, '/tool/gen/batchGenCode', '127.0.0.1', '内网IP', '{\"tables\":\"t_interfaceEntity,t_interface_exampleEntity,t_userEntity,t_user_interface_infoEntity,t_white_list\"}', NULL, 0, NULL, '2023-03-09 13:54:30', 168);
INSERT INTO `sys_oper_log` VALUES (111, '代码生成', 3, 'com.ruoyi.generator.controller.GenController.remove()', 'DELETE', 1, 'admin', NULL, '/tool/gen/15,16,17,18,19', '127.0.0.1', '内网IP', '{}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-03-09 13:57:02', 5);
INSERT INTO `sys_oper_log` VALUES (112, '代码生成', 6, 'com.ruoyi.generator.controller.GenController.importTableSave()', 'POST', 1, 'admin', NULL, '/tool/gen/importTable', '127.0.0.1', '内网IP', '{\"tables\":\"t_userInterfaceInfoEntity,t_interfaceExampleEntity,t_interfaceEntity,t_userEntity,t_white_list\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-03-09 13:57:15', 116);
INSERT INTO `sys_oper_log` VALUES (113, '代码生成', 8, 'com.ruoyi.generator.controller.GenController.batchGenCode()', 'GET', 1, 'admin', NULL, '/tool/gen/batchGenCode', '127.0.0.1', '内网IP', '{\"tables\":\"t_interfaceEntity,t_interfaceExampleEntity,t_userEntity,t_userInterfaceInfoEntity,t_white_list\"}', NULL, 0, NULL, '2023-03-09 13:57:17', 166);
INSERT INTO `sys_oper_log` VALUES (114, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.add()', 'POST', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"children\":[],\"createBy\":\"admin\",\"icon\":\"build\",\"isCache\":\"0\",\"isFrame\":\"1\",\"menuName\":\"项目管理\",\"menuType\":\"M\",\"orderNum\":1,\"params\":{},\"parentId\":0,\"path\":\"admin\",\"status\":\"0\",\"visible\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-03-09 14:07:07', 27);
INSERT INTO `sys_oper_log` VALUES (115, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.add()', 'POST', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"children\":[],\"createBy\":\"admin\",\"icon\":\"druid\",\"isCache\":\"0\",\"isFrame\":\"1\",\"menuName\":\"接口管理\",\"menuType\":\"C\",\"orderNum\":1,\"params\":{},\"parentId\":0,\"path\":\"admin/interface\",\"perms\":\"admin:interfaceEntity:list\",\"status\":\"0\",\"visible\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-03-09 14:09:28', 19);
INSERT INTO `sys_oper_log` VALUES (116, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"children\":[],\"createTime\":\"2023-03-09 14:09:28\",\"icon\":\"druid\",\"isCache\":\"0\",\"isFrame\":\"1\",\"menuId\":2001,\"menuName\":\"接口管理\",\"menuType\":\"C\",\"orderNum\":1,\"params\":{},\"parentId\":2000,\"path\":\"admin/interface\",\"perms\":\"admin:interfaceEntity:list\",\"status\":\"0\",\"updateBy\":\"admin\",\"visible\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-03-09 14:09:44', 23);
INSERT INTO `sys_oper_log` VALUES (117, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.add()', 'POST', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"children\":[],\"createBy\":\"admin\",\"icon\":\"peoples\",\"isCache\":\"0\",\"isFrame\":\"1\",\"menuName\":\"用户管理\",\"menuType\":\"M\",\"orderNum\":2,\"params\":{},\"parentId\":2000,\"path\":\"admin/user\",\"status\":\"0\",\"visible\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-03-09 14:11:18', 6);
INSERT INTO `sys_oper_log` VALUES (118, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.add()', 'POST', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"children\":[],\"createBy\":\"admin\",\"icon\":\"row\",\"isCache\":\"0\",\"isFrame\":\"1\",\"menuName\":\"接口次数管理\",\"menuType\":\"M\",\"orderNum\":3,\"params\":{},\"parentId\":2000,\"path\":\"admin/userInterfaceInfo\",\"status\":\"0\",\"visible\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-03-09 14:12:02', 17);
INSERT INTO `sys_oper_log` VALUES (119, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"children\":[],\"createTime\":\"2023-03-09 14:07:07\",\"icon\":\"form\",\"isCache\":\"0\",\"isFrame\":\"1\",\"menuId\":2000,\"menuName\":\"接口示例管理\",\"menuType\":\"M\",\"orderNum\":3,\"params\":{},\"parentId\":2000,\"path\":\"admin/interfaceExample\",\"perms\":\"\",\"status\":\"0\",\"visible\":\"0\"}', '{\"msg\":\"修改菜单\'接口示例管理\'失败，上级菜单不能选择自己\",\"code\":500}', 0, NULL, '2023-03-09 14:12:31', 3);
INSERT INTO `sys_oper_log` VALUES (120, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"children\":[],\"createTime\":\"2023-03-09 14:07:07\",\"icon\":\"form\",\"isCache\":\"0\",\"isFrame\":\"1\",\"menuId\":2000,\"menuName\":\"接口示例管理\",\"menuType\":\"M\",\"orderNum\":3,\"params\":{},\"parentId\":2000,\"path\":\"admin/interfaceExample\",\"perms\":\"\",\"status\":\"0\",\"visible\":\"0\"}', '{\"msg\":\"修改菜单\'接口示例管理\'失败，上级菜单不能选择自己\",\"code\":500}', 0, NULL, '2023-03-09 14:12:39', 4);
INSERT INTO `sys_oper_log` VALUES (121, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"children\":[],\"createTime\":\"2023-03-09 14:07:07\",\"icon\":\"form\",\"isCache\":\"0\",\"isFrame\":\"1\",\"menuId\":2000,\"menuName\":\"接口示例管理\",\"menuType\":\"M\",\"orderNum\":2,\"params\":{},\"parentId\":2000,\"path\":\"admin/interfaceExample\",\"perms\":\"\",\"status\":\"0\",\"visible\":\"0\"}', '{\"msg\":\"修改菜单\'接口示例管理\'失败，上级菜单不能选择自己\",\"code\":500}', 0, NULL, '2023-03-09 14:12:45', 3);
INSERT INTO `sys_oper_log` VALUES (122, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"children\":[],\"createTime\":\"2023-03-09 14:07:07\",\"icon\":\"form\",\"isCache\":\"0\",\"isFrame\":\"1\",\"menuId\":2000,\"menuName\":\"接口示例管理\",\"menuType\":\"C\",\"orderNum\":2,\"params\":{},\"parentId\":2000,\"path\":\"admin/interfaceExample\",\"perms\":\"admin:interfaceExampleEntity:list\",\"status\":\"0\",\"visible\":\"0\"}', '{\"msg\":\"修改菜单\'接口示例管理\'失败，上级菜单不能选择自己\",\"code\":500}', 0, NULL, '2023-03-09 14:13:29', 6);
INSERT INTO `sys_oper_log` VALUES (123, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"children\":[],\"createTime\":\"2023-03-09 14:07:07\",\"icon\":\"form\",\"isCache\":\"0\",\"isFrame\":\"1\",\"menuId\":2000,\"menuName\":\"接口示例管理\",\"menuType\":\"C\",\"orderNum\":2,\"params\":{},\"parentId\":2000,\"path\":\"admin/interfaceExample\",\"perms\":\"admin:interfaceExampleEntity:list\",\"status\":\"0\",\"visible\":\"0\"}', '{\"msg\":\"修改菜单\'接口示例管理\'失败，上级菜单不能选择自己\",\"code\":500}', 0, NULL, '2023-03-09 14:13:38', 3);
INSERT INTO `sys_oper_log` VALUES (124, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.add()', 'POST', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"children\":[],\"createBy\":\"admin\",\"icon\":\"edit\",\"isCache\":\"0\",\"isFrame\":\"1\",\"menuName\":\"接口示例管理\",\"menuType\":\"C\",\"orderNum\":2,\"params\":{},\"parentId\":2000,\"path\":\"admin/interfaceExample\",\"perms\":\"admin:interfaceExampleEntity:list\",\"status\":\"0\",\"visible\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-03-09 14:14:03', 8);
INSERT INTO `sys_oper_log` VALUES (125, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"children\":[],\"component\":\"admin/user/index\",\"createTime\":\"2023-03-09 14:11:18\",\"icon\":\"peoples\",\"isCache\":\"0\",\"isFrame\":\"1\",\"menuId\":2002,\"menuName\":\"用户管理\",\"menuType\":\"C\",\"orderNum\":2,\"params\":{},\"parentId\":2000,\"path\":\"user\",\"perms\":\"admin:user:list\",\"status\":\"0\",\"updateBy\":\"admin\",\"visible\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-03-09 14:18:44', 5);
INSERT INTO `sys_oper_log` VALUES (126, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"children\":[],\"component\":\"admin/userInterfaceInfo/index\",\"createTime\":\"2023-03-09 14:12:02\",\"icon\":\"row\",\"isCache\":\"0\",\"isFrame\":\"1\",\"menuId\":2003,\"menuName\":\"接口次数管理\",\"menuType\":\"C\",\"orderNum\":3,\"params\":{},\"parentId\":2000,\"path\":\"userInterfaceInfo\",\"perms\":\"admin:userInterfaceInfo:list\",\"status\":\"0\",\"updateBy\":\"admin\",\"visible\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-03-09 14:18:58', 6);
INSERT INTO `sys_oper_log` VALUES (127, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"children\":[],\"component\":\"admin/user/index\",\"createTime\":\"2023-03-09 14:11:18\",\"icon\":\"peoples\",\"isCache\":\"0\",\"isFrame\":\"1\",\"menuId\":2002,\"menuName\":\"用户管理\",\"menuType\":\"C\",\"orderNum\":2,\"params\":{},\"parentId\":2000,\"path\":\"user\",\"perms\":\"admin:userEntity:list\",\"status\":\"0\",\"updateBy\":\"admin\",\"visible\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-03-09 14:19:39', 5);
INSERT INTO `sys_oper_log` VALUES (128, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"children\":[],\"component\":\"admin/interfaceExample/index\",\"createTime\":\"2023-03-09 14:14:03\",\"icon\":\"edit\",\"isCache\":\"0\",\"isFrame\":\"1\",\"menuId\":2004,\"menuName\":\"接口示例管理\",\"menuType\":\"C\",\"orderNum\":2,\"params\":{},\"parentId\":2000,\"path\":\"interfaceExample\",\"perms\":\"admin:interfaceExampleEntity:list\",\"status\":\"0\",\"updateBy\":\"admin\",\"visible\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-03-09 14:19:42', 8);
INSERT INTO `sys_oper_log` VALUES (129, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"children\":[],\"component\":\"admin/userInterfaceInfo/index\",\"createTime\":\"2023-03-09 14:12:02\",\"icon\":\"row\",\"isCache\":\"0\",\"isFrame\":\"1\",\"menuId\":2003,\"menuName\":\"接口次数管理\",\"menuType\":\"C\",\"orderNum\":3,\"params\":{},\"parentId\":2000,\"path\":\"userInterfaceInfo\",\"perms\":\"admin:userInterfaceInfoEntity:list\",\"status\":\"0\",\"updateBy\":\"admin\",\"visible\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-03-09 14:19:46', 7);
INSERT INTO `sys_oper_log` VALUES (130, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"children\":[],\"component\":\"admin/interface/index\",\"createTime\":\"2023-03-09 14:09:28\",\"icon\":\"druid\",\"isCache\":\"0\",\"isFrame\":\"1\",\"menuId\":2001,\"menuName\":\"接口管理\",\"menuType\":\"C\",\"orderNum\":1,\"params\":{},\"parentId\":2000,\"path\":\"interface\",\"perms\":\"admin:interfaceEntity:list\",\"status\":\"0\",\"updateBy\":\"admin\",\"visible\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-03-09 14:23:58', 47);
INSERT INTO `sys_oper_log` VALUES (131, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"children\":[],\"component\":\"admin/user/index\",\"createTime\":\"2023-03-09 14:11:18\",\"icon\":\"peoples\",\"isCache\":\"0\",\"isFrame\":\"1\",\"menuId\":2002,\"menuName\":\"用户管理\",\"menuType\":\"C\",\"orderNum\":4,\"params\":{},\"parentId\":2000,\"path\":\"user\",\"perms\":\"admin:userEntity:list\",\"status\":\"0\",\"updateBy\":\"admin\",\"visible\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-03-09 14:32:59', 10);
INSERT INTO `sys_oper_log` VALUES (132, '代码生成', 3, 'com.ruoyi.generator.controller.GenController.remove()', 'DELETE', 1, 'admin', NULL, '/tool/gen/20,21,22,23,24', '127.0.0.1', '内网IP', '{}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-03-10 15:49:05', 34);
INSERT INTO `sys_oper_log` VALUES (133, '代码生成', 6, 'com.ruoyi.generator.controller.GenController.importTableSave()', 'POST', 1, 'admin', NULL, '/tool/gen/importTable', '127.0.0.1', '内网IP', '{\"tables\":\"t_interfaceEntity\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-03-10 15:49:57', 84);
INSERT INTO `sys_oper_log` VALUES (134, '代码生成', 8, 'com.ruoyi.generator.controller.GenController.batchGenCode()', 'GET', 1, 'admin', NULL, '/tool/gen/batchGenCode', '127.0.0.1', '内网IP', '{\"tables\":\"t_interfaceEntity\"}', NULL, 0, NULL, '2023-03-10 15:50:03', 308);
INSERT INTO `sys_oper_log` VALUES (135, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"children\":[],\"component\":\"admin/interface/index\",\"createTime\":\"2023-03-09 14:09:28\",\"icon\":\"druid\",\"isCache\":\"0\",\"isFrame\":\"1\",\"menuId\":2001,\"menuName\":\"接口管理\",\"menuType\":\"C\",\"orderNum\":1,\"params\":{},\"parentId\":2000,\"path\":\"interface\",\"perms\":\"admin:interfaceEntity:list\",\"status\":\"0\",\"updateBy\":\"admin\",\"visible\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-03-10 16:00:31', 28);
INSERT INTO `sys_oper_log` VALUES (136, '接口', 1, 'com.ruoyi.api.admin.controller.InterfaceEntityController.add()', 'POST', 1, 'admin', NULL, '/admin/interfaceEntity', '127.0.0.1', '内网IP', '{\"createTime\":\"2023-03-11 18:16:45\",\"description\":\"随机返回一句诗句, 包含作者、朝代等信息\",\"isDelete\":0,\"isFree\":0,\"method\":\"GET\",\"name\":\"随机诗句\",\"params\":{},\"requestHeader\":\"{}\",\"requestParam\":\"{}\",\"responseHeader\":\"{}\",\"url\":\"localhost:8123/api/poet/random\",\"userId\":1}', NULL, 1, '\r\n### Error updating database.  Cause: java.sql.SQLException: Field \'update_time\' doesn\'t have a default value\r\n### The error may exist in file [D:\\j2ee_project\\api-platform\\ruoyi-admin\\target\\classes\\mapper\\admin\\InterfaceEntityMapper.xml]\r\n### The error may involve com.ruoyi.api.admin.mapper.InterfaceEntityMapper.insertInterfaceEntity-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into t_interface_entity          ( name,             description,             is_free,             url,             method,             request_param,             request_header,             response_header,                          user_id,             create_time,                          is_delete )           values ( ?,             ?,             ?,             ?,             ?,             ?,             ?,             ?,                          ?,             ?,                          ? )\r\n### Cause: java.sql.SQLException: Field \'update_time\' doesn\'t have a default value\n; Field \'update_time\' doesn\'t have a default value; nested exception is java.sql.SQLException: Field \'update_time\' doesn\'t have a default value', '2023-03-11 10:16:44', 61);
INSERT INTO `sys_oper_log` VALUES (137, '接口', 1, 'com.ruoyi.api.admin.controller.InterfaceEntityController.add()', 'POST', 1, 'admin', NULL, '/admin/interfaceEntity', '127.0.0.1', '内网IP', '{\"createTime\":\"2023-03-11 18:17:20\",\"description\":\"随机返回一句诗句, 包含作者、朝代等信息\",\"id\":1,\"isDelete\":0,\"isFree\":0,\"method\":\"GET\",\"name\":\"随机诗句\",\"params\":{},\"requestHeader\":\"{}\",\"requestParam\":\"{}\",\"responseHeader\":\"{}\",\"url\":\"localhost:8123/api/poet/random\",\"userId\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-03-11 10:17:19', 5);
INSERT INTO `sys_oper_log` VALUES (138, '代码生成', 3, 'com.ruoyi.generator.controller.GenController.remove()', 'DELETE', 1, 'admin', NULL, '/tool/gen/25', '127.0.0.1', '内网IP', '{}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-03-11 11:17:53', 40);
INSERT INTO `sys_oper_log` VALUES (139, '代码生成', 6, 'com.ruoyi.generator.controller.GenController.importTableSave()', 'POST', 1, 'admin', NULL, '/tool/gen/importTable', '127.0.0.1', '内网IP', '{\"tables\":\"t_poet\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-03-11 11:17:57', 47);
INSERT INTO `sys_oper_log` VALUES (140, '代码生成', 8, 'com.ruoyi.generator.controller.GenController.batchGenCode()', 'GET', 1, 'admin', NULL, '/tool/gen/batchGenCode', '127.0.0.1', '内网IP', '{\"tables\":\"t_poet\"}', NULL, 0, NULL, '2023-03-11 11:18:03', 151);
INSERT INTO `sys_oper_log` VALUES (141, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.add()', 'POST', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"children\":[],\"icon\":\"date-range\",\"isCache\":\"0\",\"isFrame\":\"1\",\"menuName\":\"接口管理\",\"menuType\":\"M\",\"orderNum\":0,\"params\":{},\"parentId\":2000,\"path\":\"admin\",\"status\":\"0\",\"visible\":\"0\"}', '{\"msg\":\"新增菜单\'接口管理\'失败，菜单名称已存在\",\"code\":500}', 0, NULL, '2023-03-11 11:25:13', 6);
INSERT INTO `sys_oper_log` VALUES (142, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.add()', 'POST', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"children\":[],\"createBy\":\"admin\",\"icon\":\"date-range\",\"isCache\":\"0\",\"isFrame\":\"1\",\"menuName\":\"api管理\",\"menuType\":\"M\",\"orderNum\":0,\"params\":{},\"parentId\":2000,\"path\":\"admin\",\"status\":\"0\",\"visible\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-03-11 11:25:21', 6);
INSERT INTO `sys_oper_log` VALUES (143, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.add()', 'POST', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"children\":[],\"component\":\"admin/poet\",\"createBy\":\"admin\",\"icon\":\"education\",\"isCache\":\"0\",\"isFrame\":\"1\",\"menuName\":\"随机诗句api\",\"menuType\":\"C\",\"orderNum\":0,\"params\":{},\"parentId\":2005,\"path\":\"/apiinterface/poet\",\"perms\":\"apiinterface:poet:list\",\"status\":\"0\",\"visible\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-03-11 11:26:39', 17);
INSERT INTO `sys_oper_log` VALUES (144, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"children\":[],\"component\":\"admin/poet/index\",\"createTime\":\"2023-03-11 11:26:39\",\"icon\":\"education\",\"isCache\":\"0\",\"isFrame\":\"1\",\"menuId\":2006,\"menuName\":\"随机诗句api\",\"menuType\":\"C\",\"orderNum\":0,\"params\":{},\"parentId\":2005,\"path\":\"/apiinterface/poet\",\"perms\":\"apiinterface:poet:list\",\"status\":\"0\",\"updateBy\":\"admin\",\"visible\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-03-11 11:27:23', 5);
INSERT INTO `sys_oper_log` VALUES (145, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"children\":[],\"createTime\":\"2023-03-11 11:25:21\",\"icon\":\"date-range\",\"isCache\":\"0\",\"isFrame\":\"1\",\"menuId\":2005,\"menuName\":\"api管理\",\"menuType\":\"M\",\"orderNum\":0,\"params\":{},\"parentId\":0,\"path\":\"admin\",\"perms\":\"\",\"status\":\"0\",\"updateBy\":\"admin\",\"visible\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-03-11 11:29:14', 4);
INSERT INTO `sys_oper_log` VALUES (146, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/dev-api/system/menu', '127.0.0.1', '内网IP', '{\"children\":[],\"component\":\"admin/poet/index\",\"createTime\":\"2023-03-11 11:26:39\",\"icon\":\"education\",\"isCache\":\"0\",\"isFrame\":\"1\",\"menuId\":2006,\"menuName\":\"随机诗句api\",\"menuType\":\"C\",\"orderNum\":0,\"params\":{},\"parentId\":2005,\"path\":\"/apiinterface/poet\",\"perms\":\"apiinterface:poet:list,apiinterface:poet:add\",\"status\":\"0\",\"updateBy\":\"admin\",\"visible\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-04-04 11:58:29', 58);
INSERT INTO `sys_oper_log` VALUES (147, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/dev-api/system/menu', '127.0.0.1', '内网IP', '{\"children\":[],\"component\":\"admin/poet/index\",\"createTime\":\"2023-03-11 11:26:39\",\"icon\":\"education\",\"isCache\":\"0\",\"isFrame\":\"1\",\"menuId\":2006,\"menuName\":\"随机诗句api\",\"menuType\":\"C\",\"orderNum\":0,\"params\":{},\"parentId\":2005,\"path\":\"/apiinterface/poet\",\"perms\":\"apiinterface:poet:list;apiinterface:poet:add\",\"status\":\"0\",\"updateBy\":\"admin\",\"visible\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-04-04 11:59:12', 48);
INSERT INTO `sys_oper_log` VALUES (148, '菜单管理', 3, 'com.ruoyi.web.controller.system.SysMenuController.remove()', 'DELETE', 1, 'admin', NULL, '/dev-api/system/menu/2007', '127.0.0.1', '内网IP', '{}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-04-04 12:11:10', 52);
INSERT INTO `sys_oper_log` VALUES (149, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.add()', 'POST', 1, 'admin', NULL, '/dev-api/system/menu', '127.0.0.1', '内网IP', '{\"children\":[],\"createBy\":\"admin\",\"isCache\":\"0\",\"isFrame\":\"1\",\"menuName\":\"新增诗句\",\"menuType\":\"F\",\"orderNum\":1,\"params\":{},\"parentId\":2006,\"perms\":\"apiinterface:poet:add\",\"status\":\"0\",\"visible\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-04-04 12:12:01', 48);
INSERT INTO `sys_oper_log` VALUES (150, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.add()', 'POST', 1, 'admin', NULL, '/dev-api/system/menu', '127.0.0.1', '内网IP', '{\"children\":[],\"createBy\":\"admin\",\"isCache\":\"0\",\"isFrame\":\"1\",\"menuName\":\"诗句删除\",\"menuType\":\"F\",\"orderNum\":2,\"params\":{},\"parentId\":2006,\"perms\":\"apiinterface:poet:remove\",\"status\":\"0\",\"visible\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-04-04 12:13:48', 47);
INSERT INTO `sys_oper_log` VALUES (151, '接口', 2, 'com.ruoyi.api.admin.controller.InterfaceEntityController.edit()', 'PUT', 1, 'admin', NULL, '/dev-api/admin/interfaceEntity', '127.0.0.1', '内网IP', '{\"createTime\":\"2023-03-11 18:17:20\",\"description\":\"随机返回一句诗句, 包含作者、朝代等信息\",\"id\":1,\"isDelete\":0,\"isFree\":1,\"method\":\"GET\",\"name\":\"随机诗句\",\"params\":{},\"requestHeader\":\"{}\",\"requestParam\":\"{}\",\"responseHeader\":\"{}\",\"status\":1,\"updateTime\":\"2023-04-04 20:50:25\",\"url\":\"localhost:8123/api/poet/random\",\"userId\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-04-04 12:50:22', 60);
INSERT INTO `sys_oper_log` VALUES (152, '接口', 2, 'com.ruoyi.api.admin.controller.InterfaceEntityController.edit()', 'PUT', 1, 'admin', NULL, '/dev-api/admin/interfaceEntity', '127.0.0.1', '内网IP', '{\"createTime\":\"2022-04-06 16:00:43\",\"description\":\"coa6lsxxOZ\",\"id\":1000,\"isDelete\":0,\"isFree\":1,\"method\":\"GET\",\"name\":\"模拟接口\",\"params\":{},\"requestHeader\":\"uyc1bSAc97\",\"requestParam\":\"VoxeKq13Gy\",\"responseHeader\":\"rkg0yVi8j4\",\"status\":2,\"updateTime\":\"2023-04-15 12:30:58\",\"url\":\"https://drive.shiwest.jp/Handcrafts\",\"userId\":896}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-04-15 04:30:58', 17);
INSERT INTO `sys_oper_log` VALUES (153, '接口', 2, 'com.ruoyi.api.admin.controller.InterfaceEntityController.edit()', 'PUT', 1, 'admin', NULL, '/dev-api/admin/interfaceEntity', '127.0.0.1', '内网IP', '{\"createTime\":\"2023-03-11 18:17:20\",\"description\":\"随机返回一句诗句, 包含诗句、作者、朝代、标题等信息\",\"id\":1,\"isDelete\":0,\"isFree\":1,\"method\":\"GET\",\"name\":\"随机诗句\",\"params\":{},\"requestHeader\":\"{}\",\"requestParam\":\"{}\",\"responseHeader\":\"{}\",\"status\":1,\"updateTime\":\"2023-04-15 13:25:12\",\"url\":\"localhost:8123/api/poet/random\",\"userId\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-04-15 05:25:12', 3);
INSERT INTO `sys_oper_log` VALUES (154, '接口', 2, 'com.ruoyi.api.admin.controller.InterfaceEntityController.edit()', 'PUT', 1, 'admin', NULL, '/dev-api/admin/interfaceEntity', '127.0.0.1', '内网IP', '{\"createTime\":\"2023-03-11 18:17:20\",\"description\":\"随机返回一句诗句, 包含诗句、作者、朝代、标题等信息\",\"id\":1,\"isDelete\":0,\"isFree\":1,\"method\":\"GET\",\"name\":\"随机诗句\",\"params\":{},\"requestHeader\":\"{}\",\"requestParam\":\"{}\",\"responseHeader\":\"{}\",\"status\":1,\"updateTime\":\"2023-04-15 13:25:23\",\"url\":\"localhost:8123/api/poet/random\",\"userId\":91}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-04-15 05:25:23', 7);
INSERT INTO `sys_oper_log` VALUES (155, '接口', 2, 'com.ruoyi.api.admin.controller.InterfaceEntityController.edit()', 'PUT', 1, 'admin', NULL, '/dev-api/admin/interfaceEntity', '127.0.0.1', '内网IP', '{\"createTime\":\"2023-03-11 18:17:20\",\"description\":\"随机返回一句诗句, 包含诗句、作者、朝代、标题等信息\",\"id\":1,\"isDelete\":0,\"isFree\":1,\"method\":\"GET\",\"name\":\"随机诗句\",\"params\":{},\"requestHeader\":\"{}\",\"requestParam\":\"{}\",\"responseHeader\":\"{}\",\"status\":1,\"updateTime\":\"2023-04-15 13:26:09\",\"url\":\"localhost:8123/api/poet/random\",\"userId\":101}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-04-15 05:26:09', 3);
INSERT INTO `sys_oper_log` VALUES (156, '接口示例', 1, 'com.ruoyi.api.admin.controller.InterfaceExampleEntityController.add()', 'POST', 1, 'admin', NULL, '/dev-api/admin/interfaceExampleEntity', '127.0.0.1', '内网IP', '{\"callExample\":\"{}\",\"createTime\":\"2023-04-15 13:34:06\",\"interfaceId\":1,\"isDelete\":0,\"params\":{},\"returnExample\":\"{\\n\\\"code\\\": 200,\\n\\n\\\"data\\\": {\\n\\\"author\\\": \\\"李白\\\",\\n\\\"dynasty\\\": \\\"唐\\\",\\n\\\"title\\\": \\\"梦游天姥吟留别\\\",\\n\\\"poetry\\\": \\\"洞天石扉，訇然中开。\\\"\\n},\\n\\\"message\\\": \\\"ok\\\",\\n\\\"description\\\": \\\"\\\"\\n\\n}\"}', NULL, 1, '\r\n### Error updating database.  Cause: java.sql.SQLException: Field \'update_time\' doesn\'t have a default value\r\n### The error may exist in file [D:\\j2ee_project\\api-platform\\ruoyi-admin\\target\\classes\\mapper\\admin\\InterfaceExampleEntityMapper.xml]\r\n### The error may involve com.ruoyi.api.admin.mapper.InterfaceExampleEntityMapper.insertInterfaceExampleEntity-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into t_interface_example_entity          ( interface_id,             call_example,             return_example,             create_time,                          is_delete )           values ( ?,             ?,             ?,             ?,                          ? )\r\n### Cause: java.sql.SQLException: Field \'update_time\' doesn\'t have a default value\n; Field \'update_time\' doesn\'t have a default value; nested exception is java.sql.SQLException: Field \'update_time\' doesn\'t have a default value', '2023-04-15 05:34:05', 70);
INSERT INTO `sys_oper_log` VALUES (157, '接口示例', 1, 'com.ruoyi.api.admin.controller.InterfaceExampleEntityController.add()', 'POST', 1, 'admin', NULL, '/dev-api/admin/interfaceExampleEntity', '127.0.0.1', '内网IP', '{\"callExample\":\"{}\",\"createTime\":\"2023-04-15 13:34:28\",\"id\":1,\"interfaceId\":1,\"isDelete\":0,\"params\":{},\"returnExample\":\"{\\n\\\"code\\\": 200,\\n\\n\\\"data\\\": {\\n\\\"author\\\": \\\"李白\\\",\\n\\\"dynasty\\\": \\\"唐\\\",\\n\\\"title\\\": \\\"梦游天姥吟留别\\\",\\n\\\"poetry\\\": \\\"洞天石扉，訇然中开。\\\"\\n},\\n\\\"message\\\": \\\"ok\\\",\\n\\\"description\\\": \\\"\\\"\\n\\n}\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-04-15 05:34:28', 6);
INSERT INTO `sys_oper_log` VALUES (158, '接口示例', 2, 'com.ruoyi.api.admin.controller.InterfaceExampleEntityController.edit()', 'PUT', 1, 'admin', NULL, '/dev-api/admin/interfaceExampleEntity', '127.0.0.1', '内网IP', '{\"callExample\":\"{\\n\\\"555\\\":\\\"如334534\\\"\\n}\",\"createTime\":\"2023-04-15 13:34:29\",\"id\":1,\"interfaceId\":1,\"isDelete\":0,\"params\":{},\"returnExample\":\"{\\n\\\"code\\\": 200,\\n\\n\\\"data\\\": {\\n\\\"author\\\": \\\"李白\\\",\\n\\\"dynasty\\\": \\\"唐\\\",\\n\\\"title\\\": \\\"梦游天姥吟留别\\\",\\n\\\"poetry\\\": \\\"洞天石扉，訇然中开。\\\"\\n},\\n\\\"message\\\": \\\"ok\\\",\\n\\\"description\\\": \\\"\\\"\\n\\n}\",\"updateTime\":\"2023-04-15 14:29:28\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-04-15 06:29:27', 4);
INSERT INTO `sys_oper_log` VALUES (159, '接口示例', 2, 'com.ruoyi.api.admin.controller.InterfaceExampleEntityController.edit()', 'PUT', 1, 'admin', NULL, '/dev-api/admin/interfaceExampleEntity', '127.0.0.1', '内网IP', '{\"callExample\":\"{}\",\"createTime\":\"2023-04-15 13:34:29\",\"id\":1,\"interfaceId\":1,\"isDelete\":0,\"params\":{},\"returnExample\":\"{\\n\\\"code\\\": 200,\\n\\\"data\\\": {\\n\\\"author\\\": \\\"李白\\\",\\n\\\"dynasty\\\": \\\"唐\\\",\\n\\\"title\\\": \\\"梦游天姥吟留别\\\",\\n\\\"poetry\\\": \\\"洞天石扉，訇然中开。\\\"\\n},\\n\\\"message\\\": \\\"ok\\\",\\n\\\"description\\\": \\\"\\\"\\n\\n}\",\"updateTime\":\"2023-04-15 14:30:56\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-04-15 06:30:56', 3);
INSERT INTO `sys_oper_log` VALUES (160, '接口示例', 2, 'com.ruoyi.api.admin.controller.InterfaceExampleEntityController.edit()', 'PUT', 1, 'admin', NULL, '/dev-api/admin/interfaceExampleEntity', '127.0.0.1', '内网IP', '{\"callExample\":\"{}\",\"createTime\":\"2023-04-15 13:34:29\",\"id\":1,\"interfaceId\":1,\"isDelete\":0,\"params\":{},\"returnExample\":\"{\\n\\\"code\\\": 200,\\n\\\"data\\\": {\\n\\\"author\\\": \\\"李白\\\",\\n\\\"dynasty\\\": \\\"唐\\\",\\n\\\"title\\\": \\\"梦游天姥吟留别\\\",\\n\\\"poetry\\\": \\\"洞天石扉，訇然中开。\\\"\\n},\\n\\\"message\\\": \\\"ok\\\",\\n\\\"description\\\": \\\"\\\"\\n}\",\"updateTime\":\"2023-04-15 14:31:02\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-04-15 06:31:02', 2);
INSERT INTO `sys_oper_log` VALUES (161, '接口示例', 2, 'com.ruoyi.api.admin.controller.InterfaceExampleEntityController.edit()', 'PUT', 1, 'admin', NULL, '/dev-api/admin/interfaceExampleEntity', '127.0.0.1', '内网IP', '{\"callExample\":\"{}\",\"createTime\":\"2023-04-15 13:34:29\",\"id\":1,\"interfaceId\":1,\"isDelete\":0,\"params\":{},\"returnExample\":\"{\\n\\\"code\\\": 200,\\n\\\"data\\\": {\\n\\\"author\\\": \\\"李白\\\",\\n\\\"dynasty\\\": \\\"唐\\\",\\n\\\"title\\\": \\\"梦游天姥吟留别\\\",\\n\\\"poetry\\\": \\\"洞天石扉，訇然中开。\\\"\\n},\\n\\\"message\\\": \\\"ok\\\",\\n\\\"description\\\": \\\"\\\"\\n}\",\"updateTime\":\"2023-04-15 14:31:27\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-04-15 06:31:27', 3);
INSERT INTO `sys_oper_log` VALUES (162, '接口示例', 2, 'com.ruoyi.api.admin.controller.InterfaceExampleEntityController.edit()', 'PUT', 1, 'admin', NULL, '/dev-api/admin/interfaceExampleEntity', '127.0.0.1', '内网IP', '{\"callExample\":\"{}\",\"createTime\":\"2023-04-15 13:34:29\",\"id\":1,\"interfaceId\":1,\"isDelete\":0,\"params\":{},\"returnExample\":\"{\\n\\\"code\\\": 200,\\n\\\"data\\\": {\\n\\\"author\\\": \\\"李白\\\",\\n\\\"dynasty\\\": \\\"唐\\\",\\n\\\"title\\\": \\\"梦游天姥吟留别\\\",\\n\\\"poetry\\\": \\\"洞天石扉，訇然中开。\\\"\\n},\\n\\\"message\\\": \\\"ok\\\",\\n\\\"description\\\": \\\"\\\"\\n}\",\"updateTime\":\"2023-04-15 14:38:01\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-04-15 06:38:01', 2);
INSERT INTO `sys_oper_log` VALUES (163, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/dev-api/system/menu', '127.0.0.1', '内网IP', '{\"children\":[],\"createTime\":\"2023-03-11 11:25:21\",\"icon\":\"date-range\",\"isCache\":\"0\",\"isFrame\":\"1\",\"menuId\":2005,\"menuName\":\"api管理\",\"menuType\":\"M\",\"orderNum\":0,\"params\":{},\"parentId\":0,\"path\":\"api\",\"perms\":\"\",\"status\":\"0\",\"updateBy\":\"admin\",\"visible\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-04-15 06:40:03', 13);
INSERT INTO `sys_oper_log` VALUES (164, '接口', 1, 'com.ruoyi.api.admin.controller.InterfaceEntityController.add()', 'POST', 1, 'admin', NULL, '/dev-api/admin/interfaceEntity', '127.0.0.1', '内网IP', '{\"createTime\":\"2023-04-20 20:15:05\",\"description\":\"输入IP地址, 返回IP属地等信息\",\"id\":1030,\"isFree\":1,\"method\":\"GET\",\"name\":\"ip解析\",\"params\":{},\"requestHeader\":\"{}\",\"requestParam\":\"{\\nip : string\\n}\",\"responseHeader\":\"{}\",\"url\":\"http://localhost:88/dev-api/api/ip/ana\",\"userId\":101}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-04-20 12:15:05', 24);

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post`  (
                             `post_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
                             `post_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '岗位编码',
                             `post_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '岗位名称',
                             `post_sort` int(4) NOT NULL COMMENT '显示顺序',
                             `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态（0正常 1停用）',
                             `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
                             `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                             `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
                             `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                             `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
                             PRIMARY KEY (`post_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '岗位信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES (1, 'ceo', '董事长', 1, '0', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_post` VALUES (2, 'se', '项目经理', 2, '0', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_post` VALUES (3, 'hr', '人力资源', 3, '0', 'admin', '2023-03-09 13:37:10', '', NULL, '');
INSERT INTO `sys_post` VALUES (4, 'user', '普通员工', 4, '0', 'admin', '2023-03-09 13:37:10', '', NULL, '');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
                             `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
                             `role_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
                             `role_key` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色权限字符串',
                             `role_sort` int(4) NOT NULL COMMENT '显示顺序',
                             `data_scope` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
                             `menu_check_strictly` tinyint(1) NULL DEFAULT 1 COMMENT '菜单树选择项是否关联显示',
                             `dept_check_strictly` tinyint(1) NULL DEFAULT 1 COMMENT '部门树选择项是否关联显示',
                             `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色状态（0正常 1停用）',
                             `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
                             `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
                             `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                             `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
                             `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                             `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
                             PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'admin', 1, '1', 1, 1, '0', '0', 'admin', '2023-03-09 13:37:10', '', NULL, '超级管理员');
INSERT INTO `sys_role` VALUES (2, '普通角色', 'common', 2, '2', 1, 1, '0', '0', 'admin', '2023-03-09 13:37:10', '', NULL, '普通角色');

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept`  (
                                  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
                                  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
                                  PRIMARY KEY (`role_id`, `dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色和部门关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
INSERT INTO `sys_role_dept` VALUES (2, 100);
INSERT INTO `sys_role_dept` VALUES (2, 101);
INSERT INTO `sys_role_dept` VALUES (2, 105);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
                                  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
                                  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
                                  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (2, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2);
INSERT INTO `sys_role_menu` VALUES (2, 3);
INSERT INTO `sys_role_menu` VALUES (2, 4);
INSERT INTO `sys_role_menu` VALUES (2, 100);
INSERT INTO `sys_role_menu` VALUES (2, 101);
INSERT INTO `sys_role_menu` VALUES (2, 102);
INSERT INTO `sys_role_menu` VALUES (2, 103);
INSERT INTO `sys_role_menu` VALUES (2, 104);
INSERT INTO `sys_role_menu` VALUES (2, 105);
INSERT INTO `sys_role_menu` VALUES (2, 106);
INSERT INTO `sys_role_menu` VALUES (2, 107);
INSERT INTO `sys_role_menu` VALUES (2, 108);
INSERT INTO `sys_role_menu` VALUES (2, 109);
INSERT INTO `sys_role_menu` VALUES (2, 110);
INSERT INTO `sys_role_menu` VALUES (2, 111);
INSERT INTO `sys_role_menu` VALUES (2, 112);
INSERT INTO `sys_role_menu` VALUES (2, 113);
INSERT INTO `sys_role_menu` VALUES (2, 114);
INSERT INTO `sys_role_menu` VALUES (2, 115);
INSERT INTO `sys_role_menu` VALUES (2, 116);
INSERT INTO `sys_role_menu` VALUES (2, 117);
INSERT INTO `sys_role_menu` VALUES (2, 500);
INSERT INTO `sys_role_menu` VALUES (2, 501);
INSERT INTO `sys_role_menu` VALUES (2, 1000);
INSERT INTO `sys_role_menu` VALUES (2, 1001);
INSERT INTO `sys_role_menu` VALUES (2, 1002);
INSERT INTO `sys_role_menu` VALUES (2, 1003);
INSERT INTO `sys_role_menu` VALUES (2, 1004);
INSERT INTO `sys_role_menu` VALUES (2, 1005);
INSERT INTO `sys_role_menu` VALUES (2, 1006);
INSERT INTO `sys_role_menu` VALUES (2, 1007);
INSERT INTO `sys_role_menu` VALUES (2, 1008);
INSERT INTO `sys_role_menu` VALUES (2, 1009);
INSERT INTO `sys_role_menu` VALUES (2, 1010);
INSERT INTO `sys_role_menu` VALUES (2, 1011);
INSERT INTO `sys_role_menu` VALUES (2, 1012);
INSERT INTO `sys_role_menu` VALUES (2, 1013);
INSERT INTO `sys_role_menu` VALUES (2, 1014);
INSERT INTO `sys_role_menu` VALUES (2, 1015);
INSERT INTO `sys_role_menu` VALUES (2, 1016);
INSERT INTO `sys_role_menu` VALUES (2, 1017);
INSERT INTO `sys_role_menu` VALUES (2, 1018);
INSERT INTO `sys_role_menu` VALUES (2, 1019);
INSERT INTO `sys_role_menu` VALUES (2, 1020);
INSERT INTO `sys_role_menu` VALUES (2, 1021);
INSERT INTO `sys_role_menu` VALUES (2, 1022);
INSERT INTO `sys_role_menu` VALUES (2, 1023);
INSERT INTO `sys_role_menu` VALUES (2, 1024);
INSERT INTO `sys_role_menu` VALUES (2, 1025);
INSERT INTO `sys_role_menu` VALUES (2, 1026);
INSERT INTO `sys_role_menu` VALUES (2, 1027);
INSERT INTO `sys_role_menu` VALUES (2, 1028);
INSERT INTO `sys_role_menu` VALUES (2, 1029);
INSERT INTO `sys_role_menu` VALUES (2, 1030);
INSERT INTO `sys_role_menu` VALUES (2, 1031);
INSERT INTO `sys_role_menu` VALUES (2, 1032);
INSERT INTO `sys_role_menu` VALUES (2, 1033);
INSERT INTO `sys_role_menu` VALUES (2, 1034);
INSERT INTO `sys_role_menu` VALUES (2, 1035);
INSERT INTO `sys_role_menu` VALUES (2, 1036);
INSERT INTO `sys_role_menu` VALUES (2, 1037);
INSERT INTO `sys_role_menu` VALUES (2, 1038);
INSERT INTO `sys_role_menu` VALUES (2, 1039);
INSERT INTO `sys_role_menu` VALUES (2, 1040);
INSERT INTO `sys_role_menu` VALUES (2, 1041);
INSERT INTO `sys_role_menu` VALUES (2, 1042);
INSERT INTO `sys_role_menu` VALUES (2, 1043);
INSERT INTO `sys_role_menu` VALUES (2, 1044);
INSERT INTO `sys_role_menu` VALUES (2, 1045);
INSERT INTO `sys_role_menu` VALUES (2, 1046);
INSERT INTO `sys_role_menu` VALUES (2, 1047);
INSERT INTO `sys_role_menu` VALUES (2, 1048);
INSERT INTO `sys_role_menu` VALUES (2, 1049);
INSERT INTO `sys_role_menu` VALUES (2, 1050);
INSERT INTO `sys_role_menu` VALUES (2, 1051);
INSERT INTO `sys_role_menu` VALUES (2, 1052);
INSERT INTO `sys_role_menu` VALUES (2, 1053);
INSERT INTO `sys_role_menu` VALUES (2, 1054);
INSERT INTO `sys_role_menu` VALUES (2, 1055);
INSERT INTO `sys_role_menu` VALUES (2, 1056);
INSERT INTO `sys_role_menu` VALUES (2, 1057);
INSERT INTO `sys_role_menu` VALUES (2, 1058);
INSERT INTO `sys_role_menu` VALUES (2, 1059);
INSERT INTO `sys_role_menu` VALUES (2, 1060);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
                             `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
                             `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门ID',
                             `user_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户账号',
                             `nick_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户昵称',
                             `user_type` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '00' COMMENT '用户类型（00系统用户）',
                             `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '用户邮箱',
                             `phonenumber` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '手机号码',
                             `sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
                             `avatar` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '头像地址',
                             `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '密码',
                             `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
                             `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
                             `login_ip` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '最后登录IP',
                             `login_date` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
                             `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
                             `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                             `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
                             `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                             `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
                             PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 103, 'admin', '若依', '00', 'ry@163.com', '15888888888', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', '2023-04-20 21:15:12', 'admin', '2023-03-09 13:37:10', '', '2023-04-20 13:15:12', '管理员');
INSERT INTO `sys_user` VALUES (2, 105, 'ry', '若依', '00', 'ry@qq.com', '15666666666', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', '2023-03-09 13:37:10', 'admin', '2023-03-09 13:37:10', '', NULL, '测试员');

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post`  (
                                  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
                                  `post_id` bigint(20) NOT NULL COMMENT '岗位ID',
                                  PRIMARY KEY (`user_id`, `post_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户与岗位关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
INSERT INTO `sys_user_post` VALUES (1, 1);
INSERT INTO `sys_user_post` VALUES (2, 2);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
                                  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
                                  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
                                  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户和角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2);

-- ----------------------------
-- Table structure for t_interface_entity
-- ----------------------------
DROP TABLE IF EXISTS `t_interface_entity`;
CREATE TABLE `t_interface_entity`  (
                                       `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                       `name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '接口名称',
                                       `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '接口描述',
                                       `call_times` bigint(20) NOT NULL DEFAULT 0 COMMENT '接口调用次数',
                                       `is_free` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否免费',
                                       `url` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '接口地址',
                                       `method` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '请求方式',
                                       `request_param` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '{}' COMMENT '请求参数',
                                       `request_header` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '{}' COMMENT '请求头',
                                       `response_header` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '{}' COMMENT '响应头',
                                       `status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '接口状态(0-关闭 ,1-开启)',
                                       `user_id` int(11) NULL DEFAULT NULL COMMENT '创建人',
                                       `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                                       `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                       `is_delete` tinyint(4) NOT NULL DEFAULT 0 COMMENT '逻辑删除(1删除)',
                                       PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1031 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_interface_entity
-- ----------------------------
INSERT INTO `t_interface_entity` VALUES (1, '随机诗句', '随机返回一句诗句, 包含诗句、作者、朝代、标题等信息', 0, 1, 'http://localhost:88/dev-api/api/apiinterface/poet/random', 'GET', '{}', '{}', '{}', 1, 101, '2023-04-18 04:41:32', '2023-04-18 04:41:32', 0);
INSERT INTO `t_interface_entity` VALUES (1000, '模拟接口', 'coa6lsxxOZ', 0, 1, 'https://drive.shiwest.jp/Handcrafts', 'GET', 'VoxeKq13Gy', 'uyc1bSAc97', 'rkg0yVi8j4', 2, 896, '2022-04-06 16:00:43', '2023-04-15 12:30:59', 0);
INSERT INTO `t_interface_entity` VALUES (1001, 'waimachian', '4FwG7devfN', 0, 1, 'http://drive.shaolu.info/BaggageTravelEquipment', 'POST', 'dYgmHBSfaO', 'N1OwbSxVRM', 'p90cy23aWI', 2, 158, '2005-10-11 11:25:09', '2019-06-05 15:14:54', 0);
INSERT INTO `t_interface_entity` VALUES (1002, 'yunata', 'lEzTgufA3Z', 0, 1, 'https://drive.yzhou7.xyz/CollectiblesFineArt', 'POST', 'prVqFowkUV', 'd5jgQN6XYM', 'i51Ejyr3DZ', 2, 632, '2014-07-12 14:49:49', '2013-04-26 02:48:32', 0);
INSERT INTO `t_interface_entity` VALUES (1003, 'lhuang', '46Oat6qN22', 0, 1, 'https://auth.lingling9.info/Appliances', 'GET', 'lO6SX9uMqv', 'CixOtStlMO', 'zuFsANmAh1', 1, 124, '2021-04-28 02:22:56', '2005-12-20 18:25:30', 0);
INSERT INTO `t_interface_entity` VALUES (1004, 'songxiaoming', 'PZk5QEvlhr', 0, 1, 'https://drive.onodai7.org/SportsOutdoor', 'POST', 'Wd1FRyw1OB', 'MH2ZfcMol0', 'OD1KKLZ9E0', 1, 952, '2009-09-23 19:24:56', '2005-10-24 05:26:19', 0);
INSERT INTO `t_interface_entity` VALUES (1005, 'kimurat41', 'TG5U4f4wS2', 0, 1, 'http://drive.koo1984.us/HouseholdKitchenAppliances', 'GET', 'kSRpQLsxC3', 'olRhRlLaX2', 'ossdxFffHr', 2, 602, '2013-09-25 14:25:31', '2009-06-12 13:44:52', 0);
INSERT INTO `t_interface_entity` VALUES (1006, 'colori10', 'RHRuAYk7Vr', 0, 1, 'https://drive.choyeewu.cn/BeautyPersonalCare', 'POST', 'xCVHPIuQfH', 'a11Cggn22n', 'tye1aojeDl', 1, 41, '2015-01-29 09:16:36', '2001-02-01 09:34:54', 0);
INSERT INTO `t_interface_entity` VALUES (1007, 'masudhazuki', 'a3g8vGdlg2', 0, 1, 'https://video.yls.co.jp/AutomotivePartsAccessories', 'POST', 'SVLpEoEfCD', 'gtq8Ui5uQl', 'pLJrGNBxnv', 2, 19, '2003-10-31 17:53:37', '2007-08-17 00:51:01', 0);
INSERT INTO `t_interface_entity` VALUES (1008, 'wslu44', '9InaWvmrFB', 0, 1, 'http://www.lll1979.com/AppsGames', 'GET', 'qvaps6gqB4', 'ptRQzGQ5DO', 'hAiOekpNUg', 1, 11, '2013-06-30 17:07:57', '2008-06-08 01:26:07', 0);
INSERT INTO `t_interface_entity` VALUES (1009, 'wmc', 'PjLypcykrx', 0, 1, 'https://auth.changxiaoming.xyz/HealthBabyCare', 'GET', 'L9UCKYs1Kf', 'dAmM0o7VsL', 'W66vehzYo8', 1, 673, '2002-02-20 01:34:07', '2022-12-11 09:54:07', 0);
INSERT INTO `t_interface_entity` VALUES (1010, 'shanadams', 'z1l9i7K41h', 0, 1, 'http://www.nr9.jp/HouseholdKitchenAppliances', 'GET', '98A7ZJdYsy', 'x2y8jzCrPY', 'VPL6v69wTX', 2, 827, '2004-01-17 12:44:24', '2003-06-29 12:26:18', 0);
INSERT INTO `t_interface_entity` VALUES (1011, 'harm', 'FJxYIe9iSh', 0, 1, 'http://drive.shihancheng.xyz/BaggageTravelEquipment', 'GET', 'b8wSp87DN5', 'AjeQomOcwC', 'z7BnTJEKEZ', 1, 884, '2009-01-22 04:52:25', '2017-01-23 02:36:56', 0);
INSERT INTO `t_interface_entity` VALUES (1012, 'sara6', 'RCVBaZ5Byx', 0, 1, 'https://image.chkam.info/Food', 'GET', 'slsubM6G8A', 'uAoa4tPfYm', 'OwknSGdad0', 0, 474, '2009-08-25 16:09:40', '2021-05-12 10:39:42', 0);
INSERT INTO `t_interface_entity` VALUES (1013, 'patea1992', '5c91pB3BEx', 0, 1, 'https://image.ssw.co.jp/Others', 'GET', 'vIVV4SSJg1', 'W0Fy9ZYiaB', 'BmjFv8VecR', 1, 232, '2022-07-02 04:16:44', '2017-07-16 11:03:30', 0);
INSERT INTO `t_interface_entity` VALUES (1014, 'fuming', 'CZmn6jbXeJ', 0, 1, 'http://drive.daishimizu.com/PetSupplies', 'POST', 'pqzHbWAn5N', '20c5m2FQzZ', '9xCUrCzWeT', 1, 27, '2014-11-14 23:38:42', '2010-11-26 04:02:49', 0);
INSERT INTO `t_interface_entity` VALUES (1015, 'arimurayota96', 'HPIC9TapQp', 0, 1, 'http://www.xu62.biz/CDsVinyl', 'GET', 'L98h0RpK6Z', 'rOteBHG8nJ', 'Nf648u0xEL', 1, 584, '2015-12-10 08:30:30', '2007-02-12 18:54:26', 0);
INSERT INTO `t_interface_entity` VALUES (1016, 'sandrapar2', 'L0m4TjQh5S', 0, 1, 'https://www.bryant3.cn/ArtsHandicraftsSewing', 'POST', 'YatyRVk67O', 'ZcOVyQ3gwP', 'QSUFI1zZ5j', 0, 360, '2015-02-14 13:19:32', '2019-08-10 02:26:28', 0);
INSERT INTO `t_interface_entity` VALUES (1017, 'yuningwu425', 'uoQmRVuz7Z', 0, 1, 'https://www.takyuna2.us/BeautyPersonalCare', 'GET', 'pysHbnPhxM', 'kcXfhccYwq', '98uv26fwx3', 1, 340, '2015-10-13 11:18:32', '2020-03-09 16:09:20', 0);
INSERT INTO `t_interface_entity` VALUES (1018, 'nauchi', 'op9jZqMz8P', 0, 1, 'https://video.hana10.jp/PetSupplies', 'GET', 'P15nHe1hjl', '0QjmL5XPeE', 'JgzcN3MVwW', 1, 296, '2022-07-30 22:16:19', '2006-01-16 09:38:00', 0);
INSERT INTO `t_interface_entity` VALUES (1019, 'mitsukitaka823', '5oHfD8ZC90', 0, 1, 'http://image.ueno1981.net/HouseholdKitchenAppliances', 'GET', 'jFkvFvdHEm', 'v1ZVK2yGIO', 'W8dYVtx9Tk', 1, 139, '2017-06-24 01:52:41', '2015-10-25 23:35:31', 0);
INSERT INTO `t_interface_entity` VALUES (1020, 'ikki91', 'zUt05yo9rd', 0, 1, 'https://drive.jiehong4.xyz/ToolsHomeDecoration', 'GET', 'uEry5MR62u', 'yhw0alYXfL', 'zqbqzvuifg', 0, 409, '2011-02-01 21:51:08', '2007-02-14 11:35:09', 0);
INSERT INTO `t_interface_entity` VALUES (1021, 'ta10', 'MkEe2DncrZ', 0, 1, 'http://auth.kwlui.us/AppsGames', 'GET', 'div68qiewr', 'B8PfrF4cwT', 'q9B0eWnOs6', 2, 444, '2000-06-27 19:32:15', '2018-12-19 05:08:30', 0);
INSERT INTO `t_interface_entity` VALUES (1022, 'doris9', 'TfDMu3v8Fr', 0, 1, 'https://image.hilljacqueline7.org/HouseholdKitchenAppliances', 'GET', '7qRG81ONDR', 'VRRxTzXW3k', 'sXXb4dp8Gs', 1, 408, '2014-12-11 14:20:00', '2007-12-20 04:57:57', 0);
INSERT INTO `t_interface_entity` VALUES (1023, 'ont', '1SzfSD0GuB', 0, 1, 'https://auth.hoyin1027.co.jp/Food', 'POST', 'hCHpNgYfMn', '3oKh0WST32', '1ffWxjU6aH', 1, 350, '2014-03-30 16:17:37', '2018-01-19 04:16:59', 0);
INSERT INTO `t_interface_entity` VALUES (1024, 'yamashitak', '9PdeEtJgD3', 0, 1, 'https://image.waearl62.xyz/CollectiblesFineArt', 'POST', 'mzD6OwVuxx', 'fX3APlvaCf', 'lnCcKwDYwA', 2, 765, '2012-11-01 18:09:34', '2019-02-09 01:30:58', 0);
INSERT INTO `t_interface_entity` VALUES (1025, 'hikaru7', 'vsRdLT8a8b', 0, 1, 'https://auth.kym4.info/Books', 'GET', '9pCvOtS3I8', 'bYtby7kpNM', 'W6YsmDbwaQ', 1, 614, '2022-11-13 19:44:48', '2002-12-02 21:36:50', 0);
INSERT INTO `t_interface_entity` VALUES (1026, 'zzh', 'jzgidC79jf', 0, 1, 'http://www.wailok.net/BaggageTravelEquipment', 'POST', 'jdH2Oe15mJ', 'tPCkXPlOCm', 'HkOUW0JLGf', 2, 703, '2000-03-24 13:05:58', '2012-07-09 04:31:40', 0);
INSERT INTO `t_interface_entity` VALUES (1027, 'kwokming926', 'tFHi9ZwQeM', 0, 1, 'http://video.yuyuni.biz/HealthBabyCare', 'POST', '4WEkhyKogi', '1UQG853KIA', 'ULO7WA6cgc', 0, 197, '2014-05-25 16:07:50', '2019-09-08 15:54:11', 0);
INSERT INTO `t_interface_entity` VALUES (1028, 'hikarnakano', 'juP89i6LLV', 0, 1, 'https://drive.yujialun2010.info/HouseholdKitchenAppliances', 'GET', '4992MI1Ln1', 'gnl7vphX2s', 'ftNoxQLY3o', 1, 321, '2000-01-04 18:37:46', '2012-03-28 16:48:03', 0);
INSERT INTO `t_interface_entity` VALUES (1029, 'garti', 'GHpGYNJ8Fn', 0, 1, 'https://video.eitafujiw218.cn/ClothingShoesandJewelry', 'GET', 'JwOEZMEiAo', 'eHfdmUe6GI', 'KNRQywy5ie', 1, 706, '2019-05-02 00:20:14', '2021-02-28 00:39:19', 0);
INSERT INTO `t_interface_entity` VALUES (1030, 'ip解析', '输入IP地址, 返回IP属地等信息', 0, 1, 'http://localhost:88/dev-api/api/ip/ana', 'GET', '{\nip : string\n}', '{}', '{}', 1, 101, '2023-04-20 20:15:06', '2023-04-20 12:15:05', 0);

-- ----------------------------
-- Table structure for t_interface_example_entity
-- ----------------------------
DROP TABLE IF EXISTS `t_interface_example_entity`;
CREATE TABLE `t_interface_example_entity`  (
                                               `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                               `interface_id` bigint(20) NOT NULL COMMENT '接口id',
                                               `call_example` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调用示例',
                                               `return_example` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '返回示例',
                                               `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                                               `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                               `is_delete` tinyint(4) NOT NULL DEFAULT 0 COMMENT '逻辑删除(1删除)',
                                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_interface_example_entity
-- ----------------------------
INSERT INTO `t_interface_example_entity` VALUES (1, 1, '{}', '{\n\"code\": 200,\n\"data\": {\n\"author\": \"李白\",\n\"dynasty\": \"唐\",\n\"title\": \"梦游天姥吟留别\",\n\"poetry\": \"洞天石扉，訇然中开。\"\n},\n\"message\": \"ok\",\n\"description\": \"\"\n}', '2023-04-15 13:34:29', '2023-04-15 14:38:02', 0);

-- ----------------------------
-- Table structure for t_user_entity
-- ----------------------------
DROP TABLE IF EXISTS `t_user_entity`;
CREATE TABLE `t_user_entity`  (
                                  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                  `user_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '昵称',
                                  `user_account` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账号',
                                  `avatar_url` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'https://blog.dhx.icu/img/avater.png' COMMENT '头像',
                                  `gender` tinyint(4) NOT NULL DEFAULT 1 COMMENT '性别(1男0女)',
                                  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
                                  `phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话',
                                  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
                                  `access_key` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'ak',
                                  `secret_key` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'sk',
                                  `user_role` tinyint(4) NOT NULL DEFAULT 0 COMMENT '0-用户 1-管理员',
                                  `is_disabled` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否封禁用户',
                                  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                                  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
                                  `is_delete` tinyint(4) NOT NULL DEFAULT 0 COMMENT '逻辑删除(1删除)',
                                  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 102 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_entity
-- ----------------------------
INSERT INTO `t_user_entity` VALUES (1, 'Martha Hernandez', 'ZFdanL@', 'http://oss.dhx.icu/dhx/810a19d8bc3eb13593a08ea0a41ea8d3fd1f4441.jpg', 0, 'J6E5iPFCiY', '164-4122-5282', 'martha6@mail.com', 'W7KW2QMHvhW7ZdGCWIYrXa5sSPmxb5a7', 'zTLXAyGrzUfPSw0DGEvXWuW3C9pHN7ck', 0, 0, '2023-04-18 09:59:35', '2009-03-05 19:08:53', 1);
INSERT INTO `t_user_entity` VALUES (2, 'Gladys Hamilton', 'aI2=C=ra\\', 'https://www.qqkw.com/d/file/p/2022/04-18/d4432c92845533072acb0129b1d75ea6.jpg', 1, 'UsenUUa4x2', '80-8351-6844', 'hamiltonglady@gmail.com', 'gsBrf34ZfAKutsC8ZXtpu9QhE6c4RnMx', 'ywDUVn2ilTfZywz4prMrIXtMIWhjze2W', 0, 0, '2023-04-18 10:00:56', '2005-03-27 10:06:06', 1);
INSERT INTO `t_user_entity` VALUES (3, 'Miyazaki Akina', 'rH?aG:ihz', 'https://oss.dhx.icu/dhx/09a0b025fdfbQ5F.jpg', 1, 'SXpFN9gQ7n', '769-983-5162', 'miyazakiaki8@gmail.com', 'EbVmOZtkyWl6BAA1KjNqYQtXWiT2jrjR', 'UwZNGVL5OUZGe2pU5AhfB7g9KCKrSU13', 0, 0, '2023-04-18 09:26:47', '2011-07-03 08:04:20', 1);
INSERT INTO `t_user_entity` VALUES (4, 'Yam Sum Wing', '7[MVqA;gEe', 'https://oss.dhx.icu/dhx/0551b525cfgOUuG.jpg', 0, 'n9U5s6W9OJ', '184-0483-5478', 'swya@hotmail.com', 'hUzH8rs7Nc6I13803ej4kYRi0Aw2SUJ6', '5cG0Uecjkj7j6mz738VgzuCUzc6QKXuk', 0, 0, '2023-04-18 09:27:24', '2012-04-24 05:21:35', 0);
INSERT INTO `t_user_entity` VALUES (5, 'Meng Chung Yin', 'x0[3<gP9MG', 'https://blog.dhx.icu/img/avaterLANYANGYANG.jpg', 1, '4Kj5I2PXlX', '614-016-4976', 'chungyinmeng904@gmail.com', 'ERc448iqKecOFJsAwM4laSIcQ7Q279cK', 'CuxNkIUNDBPOLUqcQxL1vxqSm0ncPlGV', 0, 0, '2023-04-18 09:19:09', '2013-08-19 13:18:02', 0);
INSERT INTO `t_user_entity` VALUES (6, 'Shing Ka Man', '[GfjcpjG<F', 'https://oss.dhx.icu/dhx/wallhaven-5wr3m7.jpg', 0, '6xN3GuLDnR', '172-9496-4653', 'skaman10@gmail.com', '1iWnsRGOLNWPRerXM2o1j5dqX3apSSrG', 'jVKEw1UiEaG55pnxVj799m08wRj5a9ZO', 0, 0, '2023-04-18 09:27:39', '2010-01-16 07:35:12', 0);
INSERT INTO `t_user_entity` VALUES (7, 'Zhang Zhennan', 'ph:aJ?iUd', 'https://oss.dhx.icu/dhx/42e0c9ed85sZSK3.jpg', 0, '5I0C5j2kfg', '5220 280908', 'zhangzhenn9@icloud.com', '4TC7XxYLJT5bpA4oihrHV1UAbXF1iYw9', 'BKYdgrL983Cz7ifXT5oCwHhj4hC2lNHe', 0, 0, '2023-04-18 09:27:06', '2016-09-28 08:25:33', 1);
INSERT INTO `t_user_entity` VALUES (8, 'Harada Sakura', '0DftMw3R>', 'https://oss.dhx.icu/dhx/0551b525cfgOUuG.jpg', 1, 'L9pE9phbfZ', '614-752-6125', 'sakurhar@yahoo.com', 'TlGRrwupKIwyFIpHhPzemQTbItbrLdel', 'm9dCnCmcVuW7JirBxKjYtnwFWr65nP7q', 0, 0, '2023-04-18 09:27:24', '2015-12-14 02:45:44', 0);
INSERT INTO `t_user_entity` VALUES (9, 'Fujita Itsuki', 'k1PCprk', 'https://oss.dhx.icu/dhx/09a0b025fdfbQ5F.jpg', 0, 'Ho5tiQsx7Q', '70-7041-9336', 'ifujita@mail.com', 'lPvKnShFCvyrNvvCUTGgXMchr6wz1GQt', 'OJk9DIFT4bfmr8ho8HN0CdZTuWrO0UnI', 0, 0, '2023-04-18 09:26:47', '2017-10-06 14:09:30', 0);
INSERT INTO `t_user_entity` VALUES (10, 'Kao Wing Fat', 'U;iK=U', 'http://oss.dhx.icu/dhx/75ad32ce87c79d2a116bc9f75fbe8745.jpg', 0, 'dgzGqpjq65', '(116) 179 3992', 'wfka1968@hotmail.com', 'p25lu4Hion87R0xDTc2PDQTBFprmeYfb', 'vnQyYsfPV48wBChScbHd4CKiaojMpZgw', 0, 0, '2023-04-18 10:00:37', '2006-11-01 00:56:43', 0);
INSERT INTO `t_user_entity` VALUES (11, 'Ikeda Takuya', 'X:<H?S=', 'http://oss.dhx.icu/dhx/810a19d8bc3eb13593a08ea0a41ea8d3fd1f4441.jpg', 1, 'oKY1nDjj5o', '5852 587940', 'ikedtak@gmail.com', 'gQNMY2JDxgj9PJTJo4oLZjR0ZxL93Qbb', 'YSzbBETodGMErsKYgtiJfyciVXFhv7Dq', 0, 0, '2023-04-18 09:59:35', '2003-02-26 01:11:40', 0);
INSERT INTO `t_user_entity` VALUES (12, 'Jiang Zhennan', 'vEl@M28<', 'https://oss.dhx.icu/dhx/wallhaven-5wr3m7.jpg', 1, 't4AO54yobu', '614-278-1258', 'jiang8@gmail.com', 'Ak2yKLkh6YGvLhFdL8nAQzESu3GI3Mp7', 'Nn21D2V0qIzJBW2szqweKQn8q1Y2h44y', 0, 0, '2023-04-18 09:27:39', '2014-09-10 08:42:02', 1);
INSERT INTO `t_user_entity` VALUES (13, 'Yuen Fu Shing', 'A\\ZYaDnp', 'https://blog.dhx.icu/img/avaterLANYANGYANG.jpg', 0, 'FLS60gpyAO', '755-596-8957', 'fsy3@outlook.com', '8yn1PNN1elVHY0UU7bhp5u05LsKsQczj', 'SC53GVYxe4NeF0tYs6qQ0hfxCempPnOf', 0, 0, '2023-04-18 09:19:09', '2006-07-28 18:12:00', 1);
INSERT INTO `t_user_entity` VALUES (14, 'Fong Lik Sun', 'V=A2soSL', 'https://oss.dhx.icu/dhx/42e0c9ed85sZSK3.jpg', 0, 'A01Fa7jw1C', '70-0382-3175', 'fonliksun5@icloud.com', 'HIjFyAIvQikhi8mFd3CeFrwjlVtriZqa', 'Ml3hmqTlOKU7rPLoOQMs6y7bMgue4Ys5', 0, 0, '2023-04-18 09:27:06', '2001-04-16 07:31:57', 1);
INSERT INTO `t_user_entity` VALUES (15, 'Nancy Hall', 'SXFbQ;8re', 'https://oss.dhx.icu/dhx/09a0b025fdfbQ5F.jpg', 1, 'ZTeGfFLH2w', '212-981-8983', 'nancyha@gmail.com', 'EiWeQyLA5JSeMe1NHzmxnH9iWxQW2PeZ', 'XTHbOZ1KSfZpdo0YamVGd3Tiqu3OdnWR', 0, 0, '2023-04-18 09:26:47', '2019-02-23 10:09:57', 1);
INSERT INTO `t_user_entity` VALUES (16, 'Yuen Kwok Kuen', 'D?d=VDN16[', 'https://oss.dhx.icu/dhx/0551b525cfgOUuG.jpg', 1, 'tTzp1zX2S2', '5092 189388', 'kwokkuen10@hotmail.com', 'kNe35Fly3BZl6XRpUhl4me4HK7wKFf2o', 'BCkRG1WaPY3MxVXwHePEyQNOhU0iOYbn', 0, 0, '2023-04-18 09:27:24', '2014-06-24 13:51:49', 1);
INSERT INTO `t_user_entity` VALUES (17, 'Sheh Ming Sze', 'qUc?hEd', 'https://blog.dhx.icu/img/avaterLANYANGYANG.jpg', 1, '21bFV1krvc', '28-039-1731', 'mssh1117@mail.com', 'sdIMOEYLzFjZjnC2vH0JAkG6HJbRl9J3', 'XsoB1MbBXVGcEdWVQF2nxmUToBWNfDnp', 0, 0, '2023-04-18 09:19:09', '2002-10-27 20:32:52', 0);
INSERT INTO `t_user_entity` VALUES (18, 'Paula Bailey', 'cxombcq', 'https://oss.dhx.icu/dhx/wallhaven-5wr3m7.jpg', 1, '4gX7ZuXcqO', '212-474-3901', 'bailep@gmail.com', '9wUMwbaRqhlMLMMb2UEG9Y2Hm6xZq7Zn', 'EQAduiCFdAm1hz31YKVT8eM9JPtmjJCN', 0, 0, '2023-04-18 09:27:39', '2004-05-26 07:11:20', 1);
INSERT INTO `t_user_entity` VALUES (19, 'Wayne Hawkins', 'p@zhgfE', 'https://blog.dhx.icu/img/avaterLANYANGYANG.jpg', 0, 'nYSZ1W33am', '10-2269-7574', 'hawkinswa62@icloud.com', 'sGkHTI5W3izS47KXDZwfQPBZ2vxc4VKg', 'IoGsSbgJ9ryWjYsFtWVmuiDAHwDT4zau', 0, 0, '2023-04-18 09:19:09', '2016-04-20 12:14:20', 0);
INSERT INTO `t_user_entity` VALUES (20, 'Iwasaki Yota', 'm@[6@LLBPh', 'https://oss.dhx.icu/dhx/0551b525cfgOUuG.jpg', 1, 'ClaYdQzcI9', '(20) 8571 9550', 'yoiwasa820@gmail.com', 'w9ZGvG7phQMLc0ddVL6tyrs5ymL7nMSh', 'X0C5DSxYpn2VkEvPAR5Y2VjoeippQGR9', 0, 0, '2023-04-18 09:27:24', '2015-02-17 02:41:28', 1);
INSERT INTO `t_user_entity` VALUES (21, 'Joel Davis', 'i:MOvLDV[4', 'http://oss.dhx.icu/dhx/810a19d8bc3eb13593a08ea0a41ea8d3fd1f4441.jpg', 0, 'pHvCqXW1gL', '7107 441272', 'davis1997@gmail.com', 'Rh5ZVd5r4Fh3QRpJ4My4a7bDAYkxzY6X', '1s6TaXQVwgv0CKEnZGmhQiW3mQmBk9qz', 0, 0, '2023-04-18 09:59:35', '2015-09-01 18:09:35', 1);
INSERT INTO `t_user_entity` VALUES (22, 'Hashimoto Yamato', 'dPriNOZm:N', 'https://blog.dhx.icu/img/avaterLANYANGYANG.jpg', 0, 'zffk3iPJBm', '838-484-3849', 'hashimotoya9@outlook.com', 'nTKSWICThAAbr9vmZtxAGkqlQQ7lsLxe', 'tNRElOMxgumKSB8ZBpXLoDha77qNL6BO', 0, 0, '2023-04-18 09:19:09', '2014-01-21 15:04:58', 1);
INSERT INTO `t_user_entity` VALUES (23, 'Murakami Tsubasa', 'LkFSDVbjCI', 'https://blog.dhx.icu/img/avaterLANYANGYANG.jpg', 1, 'gglIjn7VA5', '80-3015-0204', 'tsubmura@gmail.com', 'k5K5HQbpY6vCPPXyfwOAapfNJdXWJNCb', 'axHSySRQsNLxWfPWp3brwd8C38G9BuZB', 0, 0, '2023-04-18 09:19:09', '2002-08-24 13:02:15', 0);
INSERT INTO `t_user_entity` VALUES (24, 'Takada Akina', 'AxUB0AiMJsiC', 'https://oss.dhx.icu/dhx/wallhaven-5wr3m7.jpg', 0, 'Mq3gXGmY3Q', '718-806-1405', 'takada64@icloud.com', 'M2JbLl090day5dSdfnxRSlQwBAvaUq21', 'vjpsp228uHfFzSwpq0xv6iZVfN4fcp9w', 0, 0, '2023-04-18 09:27:39', '2022-07-09 20:20:00', 0);
INSERT INTO `t_user_entity` VALUES (25, 'Feng Xiuying', 'rd1Pv:lXdin;', 'https://blog.dhx.icu/img/avaterLANYANGYANG.jpg', 0, 'ZZzpqXx36c', '213-564-3431', 'feng414@gmail.com', 'fOP3ai7gOjDkZ7CFexXx5TiEbE6BSIdF', 'b9aFToRaPt5XRzHX5lBuHm13YQP4iJaH', 0, 0, '2023-04-18 09:19:09', '2010-03-15 04:35:11', 0);
INSERT INTO `t_user_entity` VALUES (26, 'Chin Ka Ming', 'gLPg8s', 'https://blog.dhx.icu/img/avaterLANYANGYANG.jpg', 0, 'zwfMAfqSpp', '(121) 666 7512', 'chkm@gmail.com', 'i7aNPPqtcRfHgWU3RymB3kiKFOEJitCT', 'wa3fdEYEbMHwTUCHVhOgXloNK9sh762A', 0, 0, '2023-04-18 09:19:09', '2018-07-28 12:34:09', 1);
INSERT INTO `t_user_entity` VALUES (27, 'Xiong Zhiyuan', 'DgzOpsrxX', 'https://oss.dhx.icu/dhx/09a0b025fdfbQ5F.jpg', 0, 'BFOjbqrica', '718-810-6175', 'xiongzh@yahoo.com', 'ErsxgNT0Xp056Fc5io7LLNk5rdN7sAZW', 'Nmru06A4u1kceOCs27sgTX71wUbBxlj3', 0, 0, '2023-04-18 09:26:47', '2003-01-19 18:38:39', 1);
INSERT INTO `t_user_entity` VALUES (28, 'Koon Kwok Ming', '4@ODL=41q', 'https://oss.dhx.icu/dhx/0551b525cfgOUuG.jpg', 1, '6ORqUkrbZ8', '5377 516830', 'koonkwokming@outlook.com', 'wBZflCRvRQ5I1ZQ64hjiyxhGEqDhYYXF', 'OMIRqnSP0Y4Nxmd3N5rAZsALBksXVQIY', 0, 0, '2023-04-18 09:27:24', '2005-10-14 05:20:38', 0);
INSERT INTO `t_user_entity` VALUES (29, 'Xie Yunxi', 'GNMT4DVQ:', 'https://blog.dhx.icu/img/avaterLANYANGYANG.jpg', 0, 'sXnNQ8gadz', '5692 817882', 'xiey@outlook.com', 'F2NU59mpiyAU5zkGdVKJRf7FMN16RXqT', '8pqyrxQh99pnd7jvb4vXNoc7dmebZ8eq', 0, 0, '2023-04-18 09:19:09', '2017-03-25 23:30:03', 1);
INSERT INTO `t_user_entity` VALUES (30, 'Yuen Tsz Hin', 'cCeWgBGA?i', 'https://oss.dhx.icu/dhx/wallhaven-5wr3m7.jpg', 1, 'qKTcrvuEwv', '70-7837-5533', 'tszhin609@icloud.com', 'uFNLcoC4GBXjrPL1H9mF2JuUQbM9vxYQ', 'l2F1OdiPiSLcWRD9IxjVnYkQv7w1Ch3D', 0, 0, '2023-04-18 09:27:39', '2004-05-09 09:09:02', 0);
INSERT INTO `t_user_entity` VALUES (31, 'He Ziyi', 'n:kR4[\\C:10O', 'http://oss.dhx.icu/dhx/810a19d8bc3eb13593a08ea0a41ea8d3fd1f4441.jpg', 1, '108s3le0o9', '838-139-2130', 'zih@outlook.com', 'CGL56yrIi70GV2g9F5MIOAtc7GrvzAh2', 'KTiePMPxBJDGTjcZFTuzbHFUP2UfkPhU', 0, 0, '2023-04-18 09:59:35', '2020-02-09 13:38:06', 1);
INSERT INTO `t_user_entity` VALUES (32, 'Ding Jialun', '9zDAjFbqNe', 'https://oss.dhx.icu/dhx/0551b525cfgOUuG.jpg', 0, 'LTErBv22fd', '213-982-5858', 'jd1004@yahoo.com', 'yMbvE6FWHrDvLEVWDaWKBO62Bwpocjhb', 'E5907NEQ1iDRxfAhEBWonbP6X6YxRKsu', 0, 0, '2023-04-18 09:27:24', '2002-01-05 20:42:36', 1);
INSERT INTO `t_user_entity` VALUES (33, 'Ying Wing Sze', '1B@ovDI', 'https://oss.dhx.icu/dhx/09a0b025fdfbQ5F.jpg', 1, 'lnIkgwnOEm', '148-6359-3714', 'wsying213@gmail.com', 'ObQnUur3eWY97OuuGoD1avoTgDGEPEuv', 'lZrr6jos34rwub8PBvgJKVmNLozySzks', 0, 0, '2023-04-18 09:26:47', '2002-03-16 08:29:39', 1);
INSERT INTO `t_user_entity` VALUES (34, 'Ogawa Mio', 'czguqqY\\nrIb', 'https://blog.dhx.icu/img/avaterLANYANGYANG.jpg', 0, '8H4scrW4kW', '838-407-9943', 'ogawamio87@hotmail.com', 'CYLq7QWC2U0OFvt28Ue1YrVrUynwjQ1S', 'NPxfUsmsYYkccyh6OL9UzQZPOMTdWuFr', 0, 0, '2023-04-18 09:19:09', '2015-07-17 05:40:12', 1);
INSERT INTO `t_user_entity` VALUES (35, 'Billy Foster', 'xTdBLXi4hWyK', 'https://oss.dhx.icu/dhx/42e0c9ed85sZSK3.jpg', 0, 'Baecye4DXU', '212-280-9177', 'fbi3@icloud.com', '9XlsA080G5p60B0lNhPBIYERszcScfZe', 'llMGSbfdE1W9StW7w19KcTaqG6lYR9Vk', 0, 0, '2023-04-18 09:27:06', '2006-01-31 21:12:31', 0);
INSERT INTO `t_user_entity` VALUES (36, 'Ruby Bailey', 'duf@Gu5azZ', 'https://oss.dhx.icu/dhx/wallhaven-5wr3m7.jpg', 1, 'BJPx83Edob', '718-089-7001', 'rubybai206@icloud.com', 'mb8uVTSrCPLIIm0qkTOI6hUgPLgZm83D', '6V9SN47p5HiaJfJzxUKgvWRrCh8jImsp', 0, 0, '2023-04-18 09:27:39', '2012-08-02 01:14:34', 1);
INSERT INTO `t_user_entity` VALUES (37, 'Koyama Yuito', '?6adq?Avoiy', 'https://blog.dhx.icu/img/avaterLANYANGYANG.jpg', 1, 'tcRsgOpoXX', '(121) 636 7010', 'koyamayuito@gmail.com', 'Sk0RtACeDi0sck2B9VzzjZG0PeFGtwil', 'fBfStcwgKEWBTBRUbisyKNaXL8DiuCIx', 0, 0, '2023-04-18 09:19:09', '2015-12-28 09:19:39', 0);
INSERT INTO `t_user_entity` VALUES (38, 'Yamada Aoi', 'EIWzOTqQy', 'https://blog.dhx.icu/img/avaterLANYANGYANG.jpg', 1, 'jtJlBEsV6f', '212-690-8319', 'aoiy@outlook.com', 'tUgnEK0rmM5w8lxaOCqRnRAUmkWK1yB2', 'oGXfqxGv3ZO1n0e8bd8dmiyyM5BxjC7w', 0, 0, '2023-04-18 09:19:09', '2009-01-14 05:47:31', 1);
INSERT INTO `t_user_entity` VALUES (39, 'Iwasaki Nanami', 'vsbry3qt:qJm', 'https://oss.dhx.icu/dhx/09a0b025fdfbQ5F.jpg', 0, '1iYUeiV54v', '718-792-8072', 'nanamiiwasaki@gmail.com', 'JHFXWRXxOErEItX3tPSBePUczew3e9wn', 'GTpiT0xFUAdU7QL6wnZklH176tXVQKaH', 0, 0, '2023-04-18 09:26:47', '2010-05-06 15:58:15', 1);
INSERT INTO `t_user_entity` VALUES (40, 'Imai Minato', 'j2eZolT', 'https://oss.dhx.icu/dhx/0551b525cfgOUuG.jpg', 0, 'IKHj7KbmDj', '(161) 149 5356', 'mini@gmail.com', 'lcPhzyX2jvOZnnsiXO8DKMjG0NDdblZh', 'VqkzpHDD51ze8GPpCSUy3fTzit1bmgbj', 0, 0, '2023-04-18 09:27:24', '2001-08-17 23:16:44', 0);
INSERT INTO `t_user_entity` VALUES (41, 'Ng Ka Man', '5XpZ<2Id', 'http://oss.dhx.icu/dhx/810a19d8bc3eb13593a08ea0a41ea8d3fd1f4441.jpg', 0, 'KJJA5XfqyD', '212-746-8990', 'ng84@gmail.com', 'CxenN6FR22HEZlFlr6mfCgGJpWeRQUJU', 'v07inrePLGsa3WJPO7OGuu86GzpGSHhO', 0, 0, '2023-04-18 09:59:35', '2005-01-09 17:26:49', 0);
INSERT INTO `t_user_entity` VALUES (42, 'Hashimoto Ren', '[YzXC\\Lh?', 'https://oss.dhx.icu/dhx/wallhaven-5wr3m7.jpg', 1, 'mJoqDXG6hZ', '330-484-6444', 'renhashimoto@outlook.com', 'NOu0Tnw82L6rlVAERLlZ1TA4lB7zsGKA', 'Bu0NhNapl7jBdaaW4xzgAnnWrsFws94N', 0, 0, '2023-04-18 09:27:39', '2012-07-28 16:01:01', 0);
INSERT INTO `t_user_entity` VALUES (43, 'Lei Yunxi', '1DbYejFNhc', 'https://blog.dhx.icu/img/avaterLANYANGYANG.jpg', 0, 'RagkHDjrIm', '838-957-8236', 'lyunxi320@icloud.com', 'ErctrrNXeyCnpm9wFwiG1VkxW24ftazx', 'XOg6Q2Vm71VWbOIOe4EvWg62Mqw7AUUq', 0, 0, '2023-04-18 09:19:09', '2013-08-18 02:00:17', 1);
INSERT INTO `t_user_entity` VALUES (44, 'Yamashita Aoi', 'uJ1:p7jhgH', 'https://oss.dhx.icu/dhx/0551b525cfgOUuG.jpg', 0, 'ESZhxqDa7O', '148-7516-5488', 'aoiya@hotmail.com', 'QKFNqBFAyFWsTauvqDMX6S0Nwbl8ps4P', 'W6pCRap4zASrgJzK1AzL4iyKQ68shesX', 0, 0, '2023-04-18 09:27:24', '2021-06-09 21:52:20', 1);
INSERT INTO `t_user_entity` VALUES (45, 'Yu Xiaoming', '0pmQlSz', 'https://oss.dhx.icu/dhx/09a0b025fdfbQ5F.jpg', 1, 'OxPAsnmBrK', '330-201-8194', 'yu17@gmail.com', 'GCgMMNvA9Fw3PtiTWIw84sDIW4LihaaJ', 'eIvCaGs3b4MnGbQz0Ym91ZdnEV3US3J9', 0, 0, '2023-04-18 09:26:47', '2011-07-29 20:33:42', 0);
INSERT INTO `t_user_entity` VALUES (46, 'Chiba Nanami', 'fPXZNQ', 'https://blog.dhx.icu/img/avaterLANYANGYANG.jpg', 0, '9bGZbOIIyD', '838-375-5343', 'nanamic@mail.com', 'YD0ENtgIkxgAZrzmT9nZyEWdCrgaSFuk', 'ITyjrx4W2cNqbPwOjBLolLYNZMQvuri3', 0, 0, '2023-04-18 09:19:09', '2016-05-30 13:22:27', 0);
INSERT INTO `t_user_entity` VALUES (47, 'Yan Lan', 'jVjlqD9CArY', 'https://blog.dhx.icu/img/avaterLANYANGYANG.jpg', 0, 'uahytk80HH', '760-262-8565', 'lan7@icloud.com', 'olz94TsQZBHmnkXnjwCf39TaFGDIhiF3', '8bZrLhlzVXr7x8lhUzSJCav8LEg4pbxW', 0, 0, '2023-04-18 09:19:09', '2019-11-28 02:37:42', 0);
INSERT INTO `t_user_entity` VALUES (48, 'Hirano Hina', 'PleSkD7eMOg', 'https://oss.dhx.icu/dhx/wallhaven-5wr3m7.jpg', 1, 'TC3wERUdtI', '52-857-8519', 'hihina@yahoo.com', 'WHJHhQg5zFNYqE7iNcaPKjwcEQxX6iFW', 'O71tLqY760DyTgHretvvHwpmhvIW9OYk', 0, 0, '2023-04-18 09:27:39', '2009-03-22 08:40:40', 0);
INSERT INTO `t_user_entity` VALUES (49, 'Kondo Mai', '3D@o6NwV', 'https://oss.dhx.icu/dhx/42e0c9ed85sZSK3.jpg', 1, '1fY37PWzyK', '194-9513-3499', 'mai1012@outlook.com', '8leuKn0JxbqesRmegAL7WCRx8wk4lsOM', 'jv2VVmfnsJGfdzW9kQEO6G1CMkj7hH5i', 0, 0, '2023-04-18 09:27:06', '2022-09-14 13:10:34', 1);
INSERT INTO `t_user_entity` VALUES (50, 'Sean Ortiz', 'RLKSJu3jZoP7', 'https://blog.dhx.icu/img/avaterLANYANGYANG.jpg', 1, '1sWK4C1Xiq', '177-0555-2750', 'seanortiz@icloud.com', 'sS5Cc2C7opoxW0CGShiygyZ8eWcKfLBH', 'oAhi3rlN8ewHDWosn7gHB6OFzWMcw3o4', 0, 0, '2023-04-18 09:19:09', '2014-12-21 18:45:31', 1);
INSERT INTO `t_user_entity` VALUES (51, 'Long Jialun', 'hkmr7X', 'http://oss.dhx.icu/dhx/810a19d8bc3eb13593a08ea0a41ea8d3fd1f4441.jpg', 1, '9RuvjAP2UG', '312-103-9376', 'long1@gmail.com', 'qijJzZFzGurWD52irpxg5WRMSajfTtrE', 'gKcsjfqlRSTKRHyev7NuprVPtmCnuMkP', 0, 0, '2023-04-18 09:59:35', '2005-06-01 13:33:02', 1);
INSERT INTO `t_user_entity` VALUES (52, 'Jiang Lu', '@5d?OWM', 'https://oss.dhx.icu/dhx/0551b525cfgOUuG.jpg', 1, 'sghwZu33xv', '21-1544-4480', 'jl8@gmail.com', 'xUopaCb4jzL6j5v3JPI69NPe6nqdd6PG', '048YfUPm7x26C49nMvaOVm8DHuhkmThR', 0, 0, '2023-04-18 09:27:24', '2018-03-09 06:08:25', 0);
INSERT INTO `t_user_entity` VALUES (53, 'Kong Zitao', 'TduAWCoO2pw', 'https://blog.dhx.icu/img/avaterLANYANGYANG.jpg', 1, 'Df6qDJEuVJ', '614-897-0864', 'zitaokong@gmail.com', '14MXa2l3RuSuIrnT9hiVflWFbErfO0ci', 'cR6FRxlpuL5VZ4Rna9ugts76PL3DMrrf', 0, 0, '2023-04-18 09:19:09', '2001-08-05 13:02:11', 1);
INSERT INTO `t_user_entity` VALUES (54, 'Ma Zhennan', 'NLe56c29OVyx', 'https://oss.dhx.icu/dhx/wallhaven-5wr3m7.jpg', 1, 'lK54KJlsaP', '718-976-7958', 'mazhe@hotmail.com', 'kJWUiGwqqHbQO8DSSAx3N73ftepGoRrL', 'Wo8OrfVnZVCIgCA0bS4buSkMIYj1e5xj', 0, 0, '2023-04-18 09:27:39', '2016-02-06 16:55:56', 1);
INSERT INTO `t_user_entity` VALUES (55, 'Kathryn Ford', '9TfOz[h', 'https://blog.dhx.icu/img/avaterLANYANGYANG.jpg', 0, 'k1I894LrgN', '90-6610-4434', 'kathrynford9@gmail.com', 'T9ZgsQnSRzeEKoOT7XG4eKLOGsPeO9nb', 'GoouPzzinlng83i6RrSJbTqxgkDU5o8t', 0, 0, '2023-04-18 09:19:09', '2000-08-17 02:35:54', 0);
INSERT INTO `t_user_entity` VALUES (56, 'Wada Hikari', 'Zkd09;:NjMnY', 'https://oss.dhx.icu/dhx/0551b525cfgOUuG.jpg', 0, '5D1ZvtCoYT', '769-0394-9560', 'hikari2017@icloud.com', 'lS19JzJ2JPoj1F3p7Trm0W8Djd2Ub0wu', 'YE3kQ5GhLtc3QbEUCrcMAeRHisB7Ft8i', 0, 0, '2023-04-18 09:27:24', '2012-01-26 16:57:45', 0);
INSERT INTO `t_user_entity` VALUES (57, 'Jiang Anqi', 'vFjsIWJ7n[3', 'https://oss.dhx.icu/dhx/09a0b025fdfbQ5F.jpg', 1, 'pskxhVq0j9', '614-734-4528', 'anqiji@yahoo.com', 'YNpz4nauGcE0L9Ea0FXAmljR3HKeiNJ1', 'RHOe1EGu8dsHx9C0YegAo77AacHQD5G4', 0, 0, '2023-04-18 09:26:47', '2004-06-13 01:18:52', 0);
INSERT INTO `t_user_entity` VALUES (58, 'Arthur Weaver', 'N@J<nuptrB', 'https://blog.dhx.icu/img/avaterLANYANGYANG.jpg', 0, '5bAG5UXpkh', '28-513-6983', 'weava@hotmail.com', 'IuqZmCdzYvJyPfJ8h6qnvdFyyLiCj2O4', 'JzVlMKSs4PKz0seVnfivqfweKrs9gPHn', 0, 0, '2023-04-18 09:19:09', '2005-09-07 20:31:38', 1);
INSERT INTO `t_user_entity` VALUES (59, 'Sato Daichi', '3<8uEtIJ3k', 'https://blog.dhx.icu/img/avaterLANYANGYANG.jpg', 1, '2dNMoZrOVd', '(121) 427 5690', 'daichi4@icloud.com', '6PDRytidNc8ajgP9mk0drDVmwSbxbecq', '8EI0yBcQNxB7JR2EHaqCSnOc56l23aJK', 0, 0, '2023-04-18 09:19:09', '2023-02-17 07:15:50', 0);
INSERT INTO `t_user_entity` VALUES (60, 'Ren Zitao', 'gQ>BDb', 'https://oss.dhx.icu/dhx/wallhaven-5wr3m7.jpg', 1, 'xGcP70bC0o', '(151) 028 8053', 'zitao2009@yahoo.com', 'oRYsTQ8DXbpx9FV5xqGbopuD77vKI5bN', 'tcVRufQIunLPB16rRoEiKliR6A3bTyIW', 0, 0, '2023-04-18 09:27:39', '2013-09-24 14:03:03', 1);
INSERT INTO `t_user_entity` VALUES (61, 'Theresa Ross', 'e;DoX63m?Ib:', 'http://oss.dhx.icu/dhx/810a19d8bc3eb13593a08ea0a41ea8d3fd1f4441.jpg', 0, 'KH64ETRTDw', '80-8184-9897', 'rossthere@outlook.com', 'kYjpJ9MKJYT7LgoiOdpkPeXxcTYkzskQ', '37BboX6cyikqC5bEGbPDIYgqc1HaWTyy', 0, 0, '2023-04-18 09:59:35', '2002-01-14 20:00:28', 1);
INSERT INTO `t_user_entity` VALUES (62, 'Leroy Roberts', '9[JhIYqV5:', 'https://blog.dhx.icu/img/avaterLANYANGYANG.jpg', 1, '4G3BERy5RI', '614-270-8373', 'leroyr@outlook.com', '2OO7vXrhLh7nDlweyxVasm43F7XamR3C', 'CSA6jg9zwW8Kx3DQtx7yMJAfJNY6HyVu', 0, 0, '2023-04-18 09:19:09', '2004-05-26 10:43:27', 0);
INSERT INTO `t_user_entity` VALUES (63, 'Tang Wai Man', '@SsIb[B', 'https://oss.dhx.icu/dhx/42e0c9ed85sZSK3.jpg', 0, 'otDNNccDjm', '(20) 5530 3001', 'tanwaiman@gmail.com', 'iIzMDXNoUyaNpkdQvna2DXDYSfw7rhIn', 'TqZnqLdSkSexPym7pPOo0sSbDePwaJqP', 0, 0, '2023-04-18 09:27:06', '2019-05-12 18:15:36', 0);
INSERT INTO `t_user_entity` VALUES (64, 'Yue Wing Sze', ';Sa[:g@V', 'https://oss.dhx.icu/dhx/0551b525cfgOUuG.jpg', 0, 'Wo3JUMebbZ', '614-397-7714', 'yws@mail.com', '9gekUCz0CXN7cYPwaxOgs1MKWsUkmEtO', 'aetX4pv8ZchbRQcVVvLuhwXQ9Jx7i9tt', 0, 0, '2023-04-18 09:27:24', '2006-06-20 10:36:15', 1);
INSERT INTO `t_user_entity` VALUES (65, 'Bonnie Patterson', 'NWD4szD<ab', 'https://blog.dhx.icu/img/avaterLANYANGYANG.jpg', 0, 'LVTGpaLenc', '(1223) 83 2911', 'bonnie62@yahoo.com', '8ryIkWq80DkhcRuItLWAuA2Ld18wEly3', 'efOD7nJl3MLJZNsXtDbWlNn2bSABb27L', 0, 0, '2023-04-18 09:19:09', '2009-12-12 19:17:54', 0);
INSERT INTO `t_user_entity` VALUES (66, 'Patrick Jenkins', 'GFH3n8', 'https://oss.dhx.icu/dhx/wallhaven-5wr3m7.jpg', 0, 'stTq8Nrask', '769-756-3162', 'jenkpatri@gmail.com', 'hfHzDgqoqAJCAkglYvV9bAfoP4PXBlSy', 'T2HtU27mGdgEFFSIr0BXDqGPyZdzAqSg', 0, 0, '2023-04-18 09:27:39', '2008-10-08 13:07:26', 1);
INSERT INTO `t_user_entity` VALUES (67, 'Albert Tucker', '[81\\m[Wi3', 'https://blog.dhx.icu/img/avaterLANYANGYANG.jpg', 1, '7KgzQDlmw5', '5502 757255', 'albert5@hotmail.com', 'apvQaChwAXcmda3DabBMoPuzHY2P06qp', 'RE40V2yLlmk3cTpbuqR7KGgvOaTkC19w', 0, 0, '2023-04-18 09:19:09', '2008-12-15 19:10:57', 1);
INSERT INTO `t_user_entity` VALUES (68, 'Duan Anqi', 'oahG@?Ab\\O', 'https://oss.dhx.icu/dhx/0551b525cfgOUuG.jpg', 1, 'C8CawoAIfa', '188-5212-5110', 'duananq@mail.com', 'AP3LeZhot1d7uff1NLb6mHkPpWZBHUmT', 'mH3vnraJ5XA4KemzsEoSQyKJAW4k3MQh', 0, 0, '2023-04-18 09:27:24', '2011-08-17 19:59:16', 1);
INSERT INTO `t_user_entity` VALUES (69, 'Wei Anqi', 'b0uLwjHpN>M', 'https://oss.dhx.icu/dhx/09a0b025fdfbQ5F.jpg', 1, '7khgCPw16C', '7866 887782', 'aw611@outlook.com', 'USwvgl9amkOzCiWQ5OQJtn4A399qZZnG', 'Nb7sSja22IhwW6troMNYPKLpDoH7eTI3', 0, 0, '2023-04-18 09:26:47', '2021-01-01 05:46:24', 0);
INSERT INTO `t_user_entity` VALUES (70, 'Man Ka Ming', 't@qVp1BS4>', 'https://oss.dhx.icu/dhx/42e0c9ed85sZSK3.jpg', 1, 'n9tGhuLIs6', '(161) 087 3799', 'kamingman422@gmail.com', 'jarcnMTQXyUNIoglKJC4orYRbG693QUp', 'VM1TIEyb6OnDkB3WafRUcgHDYL2nuis5', 0, 0, '2023-04-18 09:27:06', '2004-07-31 06:09:44', 1);
INSERT INTO `t_user_entity` VALUES (71, 'Ng Lik Sun', '?B239alvk', 'http://oss.dhx.icu/dhx/810a19d8bc3eb13593a08ea0a41ea8d3fd1f4441.jpg', 1, '32gOmSn5wR', '5662 320968', 'lsng@icloud.com', 'DcYRT8TNbbmxjxbinVIeaeEexSPh0daA', 'wx0siwkXYyeuj3GSkp8s2QxhrDRuWmwJ', 0, 0, '2023-04-18 09:59:35', '2014-04-19 01:23:36', 0);
INSERT INTO `t_user_entity` VALUES (72, 'Ma Shihan', '5uRztIu', 'https://oss.dhx.icu/dhx/wallhaven-5wr3m7.jpg', 0, 'XDQQZG7W7m', '174-7033-3735', 'mshihan2006@gmail.com', 'sdMyOc6zkg5gcqpsqiVUrN7xs57DkZK6', '7oXeTYHw7ep0GhMrFVmFKPcaodvpnDXG', 0, 0, '2023-04-18 09:27:39', '2021-11-25 15:12:36', 1);
INSERT INTO `t_user_entity` VALUES (73, 'Lee Morris', 'dgCzePiB', 'https://blog.dhx.icu/img/avaterLANYANGYANG.jpg', 0, '1druIGWA8Y', '20-0209-3446', 'lee9@gmail.com', 'X4eKZ4oY00ouOYXP9P588ITSEe94aw2Y', 'MYOdwyIhnn2M27YDoVZDdYUaB1qIK9nG', 0, 0, '2023-04-18 09:19:09', '2008-09-22 02:31:40', 0);
INSERT INTO `t_user_entity` VALUES (74, 'Peng Shihan', 'R<Zy7xuQY4', 'https://blog.dhx.icu/img/avaterLANYANGYANG.jpg', 1, 'oIiS9qtWxq', '184-2017-6090', 'pengsh1120@gmail.com', 'm3EtvGthH9iBdxjAps65L1gRk6PSnEV4', '4wMhp6PgtBVOZMfx84z9n0RWbbx2dzD0', 0, 0, '2023-04-18 09:19:09', '2007-08-22 15:35:39', 0);
INSERT INTO `t_user_entity` VALUES (75, 'Tong Chiu Wai', '5zf7vFpi', 'https://oss.dhx.icu/dhx/09a0b025fdfbQ5F.jpg', 1, 'igGlL9vjve', '7024 268381', 'cwtong10@icloud.com', '8GTBqvsUTiLc9HgWikFleW631rcE6UsX', 'hsmtoiub4TFrsYUFdFzeDQsXgVanBlAh', 0, 0, '2023-04-18 09:26:47', '2003-10-12 19:04:25', 0);
INSERT INTO `t_user_entity` VALUES (76, 'Zou Xiuying', 'R;hc<I?', 'https://oss.dhx.icu/dhx/0551b525cfgOUuG.jpg', 1, 'IrPSn0wMI8', '3-8080-8384', 'xiuying115@hotmail.com', 'h51Ej8z0yLWoS4xoS4RuNWBglZ8DIL22', '299EiCldI4O9L0qV1dBPH5iFFbnujeKg', 0, 0, '2023-04-18 09:27:24', '2010-07-10 06:36:04', 1);
INSERT INTO `t_user_entity` VALUES (77, 'Samuel Ramos', 'YAoW85Vh', 'https://oss.dhx.icu/dhx/42e0c9ed85sZSK3.jpg', 0, 'FM32ZlTqa7', '28-7245-7307', 'ramsamuel@icloud.com', 'Re2w6wA39cZDWp2nKZIFoRgrdNNHRsEx', 'jvPtokzcfGGi1VpWHevvsSYH59ViwnUM', 0, 0, '2023-04-18 09:27:06', '2009-09-13 00:35:41', 0);
INSERT INTO `t_user_entity` VALUES (78, 'Tsang Tin Wing', 'mUhUEeZ\\b6W', 'https://oss.dhx.icu/dhx/wallhaven-5wr3m7.jpg', 1, 'EUnwGducCG', '5784 753837', 'tinwing607@gmail.com', 'p5zvTpVzGC8HOieJBjS8Dm6eH3HoNgX7', 'hoqTVTMRpouAOEd6p395ga6vzm8qMMWv', 0, 0, '2023-04-18 09:27:39', '2009-06-03 04:45:06', 1);
INSERT INTO `t_user_entity` VALUES (79, 'Gao Ziyi', 'I;qj;N9i', 'https://blog.dhx.icu/img/avaterLANYANGYANG.jpg', 0, 'DNo7BaQlkL', '312-189-0843', 'zgao@gmail.com', 'F88VIp6BsKZtvzkvwtJttpAARZuGFj6j', 'QXswmJf2q3WiYw42BTK840ZjpMW5S5LT', 0, 0, '2023-04-18 09:19:09', '2020-06-10 15:25:09', 1);
INSERT INTO `t_user_entity` VALUES (80, 'Ruth Reyes', 'tK>enN4J', 'https://oss.dhx.icu/dhx/0551b525cfgOUuG.jpg', 1, 'bqmeiQxY6j', '80-3613-7868', 'rre1996@outlook.com', 'axsgHAaKXoKqo1n7Z5JmVswkQeilyHAI', 'WXXW4RZ43A1nWFKgZJqFe53mj1Kb3qQE', 0, 0, '2023-04-18 09:27:24', '2018-09-20 21:42:43', 1);
INSERT INTO `t_user_entity` VALUES (81, 'Elizabeth Romero', 'SFp0p?l', 'http://oss.dhx.icu/dhx/810a19d8bc3eb13593a08ea0a41ea8d3fd1f4441.jpg', 0, 'c0yRydkbT3', '28-9413-3666', 'romero62@gmail.com', 'AnZtqZ7Zj0O4KiQAhxTqdXgSM56RDC5T', 'jmCANbRbZxf0Wh1xYWrXMgIMfrNxyXv9', 0, 0, '2023-04-18 09:59:35', '2021-11-15 15:28:23', 1);
INSERT INTO `t_user_entity` VALUES (82, 'Lu Yunxi', 'IFL5EWxJqr', 'https://blog.dhx.icu/img/avaterLANYANGYANG.jpg', 1, 'FQQg4p8M4i', '212-836-8292', 'ly64@outlook.com', 'ToXC7Y8nz0dRQNbXpWxAz4Qu0xjrhc7Q', 'dro9xUEczElSc71ZXV8YKFTrqzE0olZb', 0, 0, '2023-04-18 09:19:09', '2007-09-21 20:33:54', 1);
INSERT INTO `t_user_entity` VALUES (83, 'Che Chi Ming', 'YekRsy', 'https://blog.dhx.icu/img/avaterLANYANGYANG.jpg', 1, 'cflnyxOpi9', '(121) 545 1645', 'cmche@gmail.com', '2Zer5dn4N9yYVxOWXRZbnydRYlKHr4YR', 'fc1z88iTCvOkMwsa2hxJTzNoop12yYV1', 0, 0, '2023-04-18 09:19:09', '2012-08-08 05:55:00', 0);
INSERT INTO `t_user_entity` VALUES (84, 'Koyama Rin', 'o0@6f9', 'https://oss.dhx.icu/dhx/wallhaven-5wr3m7.jpg', 1, 'ymZJAv84J3', '312-763-8129', 'rinkoyama@mail.com', '1l4uRT8SNFFDxp29qMUCuqvUnThYQsnV', 'Mox5rZ6uFQ2toj2twyq3T8f8tqMerS0J', 0, 0, '2023-04-18 09:27:39', '2018-11-13 15:28:27', 1);
INSERT INTO `t_user_entity` VALUES (85, 'Annie Hernandez', 'q8WABl0\\', 'https://blog.dhx.icu/img/avaterLANYANGYANG.jpg', 1, 'ym1yK88WAD', '20-346-3964', 'hernaa@gmail.com', 'gY510w1crSjvgahJa2cksfpHAZktSQST', 'SZ2OMowLqTIgsmAlMEpSyowBLshQ76hC', 0, 0, '2023-04-18 09:19:09', '2011-08-25 01:56:53', 1);
INSERT INTO `t_user_entity` VALUES (86, 'Tanaka Ikki', 'ouHOGqc>@Q', 'https://blog.dhx.icu/img/avaterLANYANGYANG.jpg', 1, 'PM8MvUlaXy', '165-6065-8953', 'tanakaikki@gmail.com', 'RZcaKUmilvpRBdqogrhI9cjFc6p8h7wg', 'psw2tbQ94jNp4oPSqdMTRo1oPj8q4Mjq', 0, 0, '2023-04-18 09:19:09', '2000-04-03 19:10:26', 1);
INSERT INTO `t_user_entity` VALUES (87, 'Yoshida Eita', 'B<ig3@kGgMN', 'https://oss.dhx.icu/dhx/09a0b025fdfbQ5F.jpg', 1, 'sH5moUycbR', '7862 279318', 'yoshida713@hotmail.com', 'QctI0UduJeoQJPb1ztiZAifqPTOgCkqB', 'xoKkmRZRXAL7OziRfeUE3FDlQteUUzxm', 0, 0, '2023-04-18 09:26:47', '2011-05-19 07:52:48', 1);
INSERT INTO `t_user_entity` VALUES (88, 'Phillip Munoz', 'Tm7XCZ@>', 'https://oss.dhx.icu/dhx/0551b525cfgOUuG.jpg', 1, 'kiaTmhnr43', '74-979-9437', 'mphillip6@gmail.com', 'Eto3GQJfKCxtQxkjndAMTKXuYFHONCFU', 'Shh0wkEK4vNP6HOo9rRVsyJyfU5I3g6X', 0, 0, '2023-04-18 09:27:24', '2001-08-04 07:52:27', 1);
INSERT INTO `t_user_entity` VALUES (89, 'Chin Chung Yin', 'ze52Vzv', 'https://blog.dhx.icu/img/avaterLANYANGYANG.jpg', 1, 'muylUS62lG', '213-559-6093', 'cychi@yahoo.com', 'kg9X4m1XZLSJvppgKXOpodu76L9Qd6ap', 'y2rC7BXgAJ6lom5Y3vUI5iDItlMIuyvB', 0, 0, '2023-04-18 09:19:09', '2009-06-30 10:38:36', 0);
INSERT INTO `t_user_entity` VALUES (90, 'Su Rui', 'xl9A5Vz3p', 'https://oss.dhx.icu/dhx/wallhaven-5wr3m7.jpg', 0, 'twtXffpqnm', '(1865) 58 7604', 'su1@outlook.com', 'LvfiMBPEe88VIXBizmEcEdi6tv9SULbm', 'R2gUeWhWl1TpeXFMTKAC8deROp95J7Ab', 0, 0, '2023-04-18 09:27:39', '2011-02-10 18:44:44', 0);
INSERT INTO `t_user_entity` VALUES (91, 'Chic Fat', 'fJWo1Zyeg7', 'http://oss.dhx.icu/dhx/810a19d8bc3eb13593a08ea0a41ea8d3fd1f4441.jpg', 0, 'KKFiQEHF5I', '80-4315-5121', 'fachic@hotmail.com', 'mFIsmZk2H72v80L1XvwjuL7Di9iddSxp', 'E76pynhVCD7qw87ppsEI41Ui9nFBc6Ec', 0, 0, '2023-04-18 09:59:35', '2018-05-28 16:27:48', 1);
INSERT INTO `t_user_entity` VALUES (92, 'Endo Yuito', 'X6C>d6X;', 'https://oss.dhx.icu/dhx/0551b525cfgOUuG.jpg', 1, 'RdsYP98DlU', '838-702-7483', 'endo3@yahoo.com', 'PreHP2llvYrMztKuT4HCKIbRtPEG2nER', 'us7yNM1pRbDm36R4F0uOxQ31wGWHyssv', 0, 0, '2023-04-18 09:27:24', '2013-10-31 01:13:14', 0);
INSERT INTO `t_user_entity` VALUES (93, 'Liang Lan', 'EPWj@b4KP', 'https://oss.dhx.icu/dhx/09a0b025fdfbQ5F.jpg', 1, 'VtBvHRby2l', '(161) 715 5486', 'lalian@hotmail.com', 'rRjxhiZfU00RNB6tHwOW79iyhsZBbNUU', 'cH3kC0mfWXZiJDFxXTwLo3QKmqP1LVog', 0, 0, '2023-04-18 09:26:47', '2019-04-14 16:16:57', 0);
INSERT INTO `t_user_entity` VALUES (94, 'Chic Kwok Kuen', 'z?hsoxQb5sN@', 'https://blog.dhx.icu/img/avaterLANYANGYANG.jpg', 0, 's7dvm59QkJ', '212-457-1912', 'kkch@icloud.com', 'Q50tSLqz0XsK3hDjqQdIxJNDgbqiHG85', '2l9tE6Ge4hDwXr5RI1oJLS3aqZI4H6V1', 0, 0, '2023-04-18 09:19:09', '2005-05-31 09:38:26', 0);
INSERT INTO `t_user_entity` VALUES (95, 'Sato Rin', '7xT<1?FdWJuK', 'https://blog.dhx.icu/img/avaterLANYANGYANG.jpg', 1, 'aj1QQhkRSb', '755-6323-9826', 'rin6@gmail.com', 'HuwwgKgKiI365q1WyyLTLbHD1zMjH5VR', 'qfXhvjGCaod1uXILzyrzODgwpKrbLEfw', 0, 0, '2023-04-18 09:19:09', '2013-07-09 04:13:10', 0);
INSERT INTO `t_user_entity` VALUES (96, 'Nakamura Ayano', 'p1HKma8Fyt3U', 'https://oss.dhx.icu/dhx/wallhaven-5wr3m7.jpg', 0, '5h0JFeLE3M', '312-971-0061', 'ayanonak3@icloud.com', 'r8HbiDXaqDb4GRIAlTt47qq1gA9BwmiG', 'kQoGtxOw77ul9pGZWPjaN8RupvWu9GdF', 0, 0, '2023-04-18 09:27:39', '2011-01-06 14:25:30', 0);
INSERT INTO `t_user_entity` VALUES (97, 'Hara Aoshi', 'eqAKO3?K9', 'https://blog.dhx.icu/img/avaterLANYANGYANG.jpg', 0, 'ccYLvj9J9u', '(1865) 11 8023', 'haraaoshi9@gmail.com', '7m9nVE8j3Eht3cyADyBu3PeINcMEzrWu', '4m13RnWlPs8dP9scFVyKLvwKeT4C8KZd', 0, 0, '2023-04-18 09:19:09', '2001-07-02 02:42:06', 0);
INSERT INTO `t_user_entity` VALUES (98, 'Guo Shihan', '27hzdD?o3', 'https://oss.dhx.icu/dhx/42e0c9ed85sZSK3.jpg', 1, 'Qdel4Ra93C', '330-207-5686', 'shihaguo@yahoo.com', 'jzFwLrhoIDHedI8DSmJYrIz3wJiWF4O8', 'MJXt73uTiJuxisJ5CKM6ppZfaE74vBna', 0, 0, '2023-04-18 09:27:06', '2015-10-31 01:20:10', 1);
INSERT INTO `t_user_entity` VALUES (99, 'Watanabe Sakura', 'Gu0pV7\\[wLm', 'https://oss.dhx.icu/dhx/09a0b025fdfbQ5F.jpg', 1, 'f634pWMnt9', '3-6506-4164', 'watanabesakura13@gmail.com', 'pTCjteWSjZ1jaKwyxFNFqoHLSgAdA2PW', 'uAkM8KZ9icpqJI0LjyIj1USpsW07fqrH', 0, 0, '2023-04-18 09:26:47', '2004-06-06 18:53:54', 0);
INSERT INTO `t_user_entity` VALUES (100, 'Tin Ching Wan', 'URB2<OLMT<u', 'https://oss.dhx.icu/dhx/0551b525cfgOUuG.jpg', 1, 'PHwjw3jiGJ', '5188 244950', 'tincw@outlook.com', 'L10dvqNjSbskzBaPWmqmhNJL3LQ9xgVf', 'OHNSt0RYWUCrWhH73z3BhDze4qJwoqOl', 0, 0, '2023-04-18 09:27:24', '2021-12-20 01:55:45', 0);
INSERT INTO `t_user_entity` VALUES (101, '前端菜狗在线踩坑', 'adorabled4', 'http://oss.dhx.icu/dhx/810a19d8bc3eb13593a08ea0a41ea8d3fd1f4441.jpg', 1, '$2a$10$2iXe3NGlK.UMiBCm.bQTNOnU1UXxHD2qQXoPyuydg0K3p.fyYij7K', NULL, NULL, 'r3242rfj3w9r', 'g45hg34wrxsa', 0, 0, '2023-04-20 07:04:39', '2023-04-12 06:03:47', 0);

-- ----------------------------
-- Table structure for t_user_interface_info_entity
-- ----------------------------
DROP TABLE IF EXISTS `t_user_interface_info_entity`;
CREATE TABLE `t_user_interface_info_entity`  (
                                                 `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                                 `user_id` bigint(20) NOT NULL COMMENT '用户id',
                                                 `interface_id` bigint(20) NOT NULL COMMENT '接口id',
                                                 `total_num` int(11) NOT NULL DEFAULT 0 COMMENT '总次数',
                                                 `left_num` int(11) NOT NULL DEFAULT 100 COMMENT '剩余次数',
                                                 `status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '0-正常 ，1-禁用',
                                                 `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                                                 `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                                 `is_delete` tinyint(4) NOT NULL DEFAULT 0 COMMENT '逻辑删除(1删除)',
                                                 PRIMARY KEY (`id`, `user_id`, `interface_id`) USING BTREE,
                                                 INDEX `leftNumIndex`(`left_num`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2000003 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_interface_info_entity
-- ----------------------------
INSERT INTO `t_user_interface_info_entity` VALUES (1, 448, 69, 100, 64, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (2, 553, 440, 100, 6, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (3, 507, 990, 100, 36, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (4, 906, 702, 100, 65, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (5, 99, 210, 100, 14, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (6, 246, 611, 100, 77, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (7, 534, 936, 100, 43, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (8, 326, 360, 100, 85, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (9, 867, 372, 100, 93, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (10, 702, 222, 100, 11, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (11, 826, 29, 100, 78, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (12, 642, 658, 100, 56, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (13, 785, 394, 100, 47, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (14, 173, 298, 100, 68, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (15, 215, 654, 100, 98, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (16, 917, 272, 100, 88, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (17, 569, 768, 100, 43, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (18, 398, 236, 100, 53, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (19, 16, 453, 100, 34, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (20, 941, 947, 100, 13, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (21, 813, 806, 100, 64, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (22, 936, 16, 100, 78, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (23, 560, 31, 100, 1, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (24, 687, 428, 100, 68, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (25, 820, 919, 100, 39, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (26, 469, 780, 100, 90, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (27, 285, 903, 100, 34, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (28, 32, 459, 100, 1, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (29, 791, 10, 100, 1, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (30, 62, 438, 100, 4, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (31, 28, 188, 100, 17, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (32, 841, 956, 100, 72, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (33, 292, 317, 100, 9, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (34, 702, 876, 100, 31, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (35, 925, 83, 100, 27, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (36, 861, 354, 100, 42, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (37, 758, 965, 100, 29, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (38, 166, 235, 100, 19, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (39, 81, 358, 100, 8, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (40, 146, 607, 100, 84, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (41, 204, 939, 100, 94, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (42, 379, 588, 100, 18, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (43, 850, 319, 100, 8, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (44, 979, 123, 100, 87, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (45, 210, 937, 100, 12, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (46, 1, 895, 100, 99, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (47, 296, 27, 100, 58, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (48, 162, 471, 100, 93, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (49, 931, 124, 100, 92, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (50, 187, 738, 100, 79, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (51, 386, 662, 100, 19, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (52, 671, 602, 100, 60, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (53, 419, 216, 100, 41, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (54, 724, 552, 100, 27, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (55, 550, 374, 100, 9, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (56, 539, 643, 100, 66, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (57, 92, 838, 100, 2, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (58, 194, 145, 100, 14, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (59, 643, 829, 100, 63, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (60, 894, 327, 100, 72, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (61, 690, 917, 100, 70, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (62, 787, 396, 100, 35, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (63, 732, 401, 100, 65, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (64, 879, 22, 100, 20, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (65, 507, 469, 100, 3, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (66, 588, 605, 100, 58, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (67, 517, 986, 100, 81, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (68, 397, 580, 100, 31, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (69, 12, 657, 100, 10, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (70, 210, 788, 100, 58, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (71, 794, 298, 100, 58, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (72, 518, 756, 100, 18, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (73, 287, 677, 100, 17, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (74, 498, 131, 100, 30, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (75, 249, 400, 100, 1, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (76, 266, 195, 100, 11, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (77, 538, 225, 100, 56, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (78, 914, 955, 100, 45, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (79, 290, 484, 100, 56, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (80, 612, 790, 100, 47, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (81, 215, 685, 100, 64, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (82, 138, 361, 100, 82, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (83, 986, 326, 100, 19, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (84, 623, 958, 100, 48, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (85, 575, 311, 100, 82, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (86, 500, 758, 100, 68, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (87, 272, 802, 100, 92, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (88, 321, 354, 100, 56, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (89, 320, 124, 100, 4, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (90, 158, 645, 100, 51, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (91, 124, 229, 100, 43, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (92, 833, 994, 100, 61, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (93, 611, 532, 100, 76, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (94, 441, 747, 100, 99, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (95, 652, 488, 100, 64, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (96, 696, 956, 100, 25, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (97, 403, 646, 100, 35, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (98, 235, 717, 100, 96, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (99, 548, 300, 100, 78, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (100, 934, 962, 100, 3, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (101, 962, 261, 100, 78, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (102, 577, 584, 100, 84, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (103, 136, 619, 100, 84, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (104, 837, 271, 100, 69, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (105, 349, 172, 100, 92, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (106, 179, 368, 100, 55, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (107, 243, 574, 100, 99, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (108, 39, 619, 100, 31, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (109, 842, 261, 100, 55, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (110, 328, 207, 100, 85, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (111, 391, 845, 100, 58, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (112, 855, 646, 100, 36, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (113, 629, 692, 100, 7, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (114, 753, 435, 100, 24, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (115, 407, 546, 100, 0, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (116, 448, 524, 100, 30, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (117, 955, 487, 100, 47, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (118, 789, 176, 100, 46, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (119, 156, 23, 100, 91, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (120, 596, 849, 100, 14, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (121, 521, 406, 100, 98, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (122, 775, 284, 100, 50, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (123, 397, 655, 100, 55, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (124, 989, 812, 100, 23, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (125, 107, 993, 100, 51, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (126, 48, 758, 100, 87, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (127, 125, 553, 100, 83, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (128, 599, 462, 100, 51, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (129, 274, 366, 100, 7, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (130, 289, 75, 100, 81, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (131, 63, 917, 100, 86, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (132, 592, 71, 100, 85, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (133, 466, 299, 100, 66, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (134, 169, 189, 100, 77, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (135, 909, 868, 100, 85, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (136, 916, 221, 100, 96, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (137, 120, 977, 100, 24, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (138, 891, 348, 100, 32, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (139, 424, 437, 100, 87, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (140, 274, 92, 100, 42, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (141, 710, 156, 100, 46, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (142, 344, 48, 100, 5, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (143, 616, 758, 100, 86, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (144, 550, 803, 100, 17, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (145, 74, 76, 100, 28, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (146, 178, 738, 100, 86, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (147, 559, 549, 100, 47, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (148, 431, 845, 100, 76, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (149, 914, 307, 100, 39, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (150, 603, 0, 100, 68, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (151, 676, 896, 100, 21, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (152, 164, 594, 100, 4, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (153, 531, 579, 100, 56, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (154, 33, 372, 100, 66, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (155, 491, 37, 100, 65, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (156, 179, 988, 100, 27, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (157, 534, 421, 100, 37, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (158, 542, 453, 100, 7, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (159, 310, 8, 100, 22, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (160, 759, 413, 100, 89, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (161, 157, 753, 100, 82, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (162, 267, 721, 100, 39, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (163, 8, 996, 100, 52, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (164, 894, 762, 100, 42, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (165, 82, 455, 100, 56, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (166, 28, 151, 100, 51, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (167, 681, 685, 100, 87, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (168, 533, 702, 100, 83, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (169, 917, 586, 100, 53, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (170, 726, 506, 100, 17, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (171, 727, 19, 100, 26, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (172, 294, 862, 100, 80, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (173, 578, 821, 100, 22, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (174, 70, 116, 100, 71, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (175, 38, 990, 100, 90, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (176, 234, 941, 100, 35, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (177, 215, 114, 100, 6, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (178, 358, 448, 100, 26, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (179, 304, 850, 100, 10, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (180, 835, 35, 100, 73, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (181, 243, 492, 100, 35, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (182, 853, 636, 100, 57, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (183, 412, 598, 100, 79, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (184, 817, 737, 100, 25, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (185, 126, 670, 100, 88, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (186, 345, 507, 100, 65, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (187, 752, 208, 100, 59, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (188, 617, 563, 100, 2, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (189, 120, 521, 100, 33, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (190, 317, 137, 100, 58, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (191, 822, 165, 100, 91, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (192, 406, 979, 100, 79, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (193, 650, 941, 100, 24, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (194, 78, 607, 100, 84, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (195, 902, 402, 100, 46, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (196, 654, 355, 100, 80, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (197, 145, 391, 100, 61, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (198, 198, 484, 100, 65, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (199, 504, 135, 100, 42, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (200, 356, 572, 100, 14, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (201, 2, 154, 100, 45, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (202, 265, 129, 100, 84, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (203, 68, 172, 100, 84, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (204, 977, 16, 100, 69, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (205, 316, 972, 100, 91, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (206, 167, 806, 100, 48, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (207, 129, 117, 100, 68, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (208, 637, 295, 100, 95, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (209, 87, 525, 100, 74, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (210, 575, 249, 100, 81, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (211, 916, 289, 100, 86, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (212, 606, 925, 100, 84, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (213, 304, 681, 100, 64, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (214, 167, 862, 100, 66, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (215, 438, 54, 100, 37, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (216, 237, 159, 100, 90, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (217, 74, 40, 100, 39, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (218, 216, 32, 100, 25, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (219, 288, 607, 100, 9, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (220, 629, 501, 100, 68, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (221, 87, 2, 100, 15, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (222, 803, 99, 100, 68, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (223, 223, 236, 100, 96, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (224, 102, 829, 100, 77, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (225, 114, 851, 100, 97, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (226, 324, 999, 100, 55, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (227, 388, 525, 100, 81, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (228, 848, 643, 100, 43, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (229, 716, 949, 100, 69, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (230, 754, 328, 100, 19, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (231, 634, 650, 100, 88, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (232, 269, 586, 100, 84, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (233, 651, 581, 100, 56, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (234, 133, 922, 100, 26, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (235, 575, 125, 100, 65, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (236, 590, 853, 100, 45, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (237, 707, 466, 100, 31, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (238, 318, 542, 100, 17, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (239, 635, 831, 100, 96, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (240, 283, 532, 100, 26, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (241, 810, 71, 100, 43, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (242, 922, 696, 100, 37, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (243, 449, 489, 100, 57, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (244, 24, 675, 100, 72, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (245, 170, 867, 100, 88, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (246, 782, 423, 100, 26, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (247, 141, 980, 100, 64, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (248, 198, 604, 100, 41, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (249, 246, 304, 100, 14, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (250, 518, 533, 100, 49, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (251, 68, 714, 100, 1, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (252, 641, 172, 100, 60, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (253, 628, 824, 100, 94, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (254, 376, 653, 100, 93, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (255, 125, 392, 100, 82, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (256, 639, 422, 100, 33, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (257, 762, 754, 100, 17, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (258, 764, 922, 100, 87, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (259, 297, 422, 100, 84, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (260, 575, 525, 100, 61, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (261, 369, 596, 100, 50, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (262, 248, 111, 100, 68, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (263, 162, 521, 100, 88, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (264, 8, 994, 100, 38, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (265, 206, 826, 100, 24, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (266, 456, 157, 100, 7, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (267, 829, 666, 100, 63, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (268, 547, 72, 100, 94, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (269, 514, 213, 100, 81, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (270, 653, 427, 100, 25, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (271, 881, 361, 100, 79, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (272, 130, 604, 100, 22, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (273, 693, 541, 100, 74, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (274, 243, 35, 100, 2, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (275, 426, 771, 100, 91, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (276, 252, 818, 100, 46, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (277, 216, 99, 100, 57, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (278, 415, 739, 100, 46, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (279, 611, 776, 100, 59, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (280, 544, 590, 100, 56, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (281, 511, 690, 100, 6, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (282, 679, 278, 100, 59, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (283, 727, 779, 100, 78, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (284, 739, 245, 100, 15, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (285, 897, 446, 100, 38, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (286, 134, 526, 100, 48, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (287, 766, 799, 100, 25, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (288, 91, 487, 100, 80, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (289, 15, 21, 100, 24, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (290, 50, 717, 100, 80, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (291, 281, 104, 100, 28, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (292, 936, 635, 100, 98, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (293, 235, 648, 100, 10, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (294, 679, 510, 100, 54, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (295, 875, 139, 100, 38, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (296, 358, 412, 100, 31, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (297, 859, 445, 100, 39, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (298, 175, 727, 100, 3, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (299, 69, 465, 100, 97, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (300, 824, 716, 100, 78, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (301, 891, 367, 100, 99, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (302, 595, 814, 100, 62, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (303, 942, 955, 100, 10, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (304, 737, 211, 100, 66, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (305, 309, 20, 100, 99, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (306, 551, 121, 100, 97, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (307, 656, 841, 100, 90, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (308, 93, 948, 100, 57, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (309, 215, 396, 100, 15, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (310, 284, 624, 100, 3, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (311, 339, 739, 100, 73, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (312, 165, 291, 100, 54, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (313, 663, 365, 100, 50, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (314, 426, 322, 100, 87, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (315, 528, 461, 100, 86, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (316, 709, 55, 100, 70, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (317, 930, 600, 100, 92, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (318, 521, 594, 100, 50, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (319, 144, 413, 100, 73, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (320, 755, 26, 100, 15, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (321, 597, 331, 100, 56, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (322, 809, 721, 100, 37, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (323, 902, 322, 100, 17, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (324, 259, 910, 100, 74, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (325, 101, 69, 100, 17, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (326, 398, 821, 100, 62, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (327, 458, 138, 100, 62, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (328, 50, 885, 100, 25, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (329, 315, 628, 100, 38, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (330, 864, 456, 100, 13, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (331, 951, 799, 100, 50, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (332, 785, 472, 100, 13, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (333, 937, 621, 100, 14, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (334, 719, 919, 100, 30, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (335, 979, 506, 100, 9, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (336, 810, 598, 100, 53, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (337, 906, 892, 100, 40, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (338, 345, 771, 100, 39, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (339, 60, 789, 100, 78, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (340, 615, 843, 100, 70, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (341, 588, 24, 100, 16, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (342, 67, 626, 100, 70, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (343, 971, 164, 100, 3, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (344, 597, 614, 100, 5, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (345, 216, 975, 100, 14, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (346, 40, 838, 100, 57, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (347, 773, 317, 100, 41, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (348, 532, 830, 100, 37, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (349, 57, 476, 100, 61, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (350, 870, 310, 100, 92, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (351, 711, 673, 100, 80, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (352, 265, 19, 100, 22, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (353, 689, 156, 100, 69, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (354, 644, 976, 100, 81, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (355, 943, 641, 100, 95, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (356, 88, 518, 100, 36, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (357, 981, 91, 100, 92, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (358, 810, 805, 100, 53, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (359, 368, 986, 100, 88, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (360, 41, 116, 100, 83, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (361, 484, 924, 100, 48, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (362, 285, 897, 100, 93, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (363, 215, 944, 100, 22, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (364, 888, 926, 100, 31, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (365, 929, 608, 100, 88, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (366, 17, 882, 100, 46, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (367, 698, 591, 100, 69, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (368, 441, 620, 100, 4, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (369, 160, 76, 100, 14, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (370, 637, 51, 100, 60, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (371, 170, 913, 100, 57, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (372, 717, 802, 100, 6, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (373, 615, 555, 100, 58, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (374, 157, 116, 100, 73, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (375, 438, 100, 100, 90, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (376, 551, 831, 100, 32, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (377, 873, 420, 100, 90, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (378, 659, 456, 100, 56, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (379, 550, 534, 100, 7, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (380, 105, 249, 100, 68, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (381, 601, 427, 100, 19, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (382, 224, 461, 100, 92, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (383, 533, 310, 100, 3, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (384, 201, 417, 100, 38, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (385, 987, 82, 100, 83, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (386, 242, 240, 100, 0, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (387, 457, 83, 100, 53, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (388, 140, 566, 100, 61, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (389, 909, 570, 100, 50, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (390, 809, 604, 100, 64, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (391, 616, 540, 100, 72, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (392, 579, 45, 100, 65, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (393, 930, 97, 100, 12, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (394, 579, 551, 100, 63, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (395, 855, 480, 100, 78, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (396, 71, 804, 100, 4, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (397, 400, 526, 100, 83, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (398, 420, 551, 100, 3, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (399, 66, 966, 100, 65, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (400, 334, 903, 100, 19, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (401, 17, 518, 100, 100, 1, '2023-04-19 02:45:36', '2023-04-19 02:45:36', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (402, 751, 732, 100, 41, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (403, 864, 23, 100, 7, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (404, 961, 917, 100, 10, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (405, 628, 6, 100, 29, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (406, 76, 307, 100, 16, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (407, 710, 130, 100, 91, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (408, 677, 266, 100, 10, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (409, 691, 833, 100, 75, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (410, 944, 541, 100, 45, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (411, 143, 42, 100, 2, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (412, 366, 150, 100, 72, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (413, 26, 676, 100, 56, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (414, 592, 631, 100, 65, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (415, 367, 283, 100, 55, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (416, 377, 924, 100, 82, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (417, 496, 714, 100, 44, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (418, 506, 289, 100, 73, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (419, 207, 403, 100, 33, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (420, 399, 874, 100, 47, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (421, 364, 258, 100, 34, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (422, 807, 440, 100, 32, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (423, 736, 719, 100, 57, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (424, 138, 710, 100, 87, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (425, 606, 955, 100, 66, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (426, 215, 889, 100, 66, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (427, 643, 440, 100, 36, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (428, 899, 657, 100, 80, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (429, 154, 227, 100, 90, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (430, 49, 882, 100, 13, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (431, 803, 593, 100, 95, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (432, 749, 254, 100, 34, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (433, 449, 445, 100, 84, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (434, 434, 123, 100, 20, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (435, 956, 411, 100, 46, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (436, 326, 924, 100, 70, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (437, 492, 948, 100, 11, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (438, 257, 636, 100, 48, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (439, 163, 302, 100, 6, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (440, 282, 737, 100, 86, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (441, 116, 845, 100, 12, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (442, 609, 756, 100, 3, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (443, 93, 683, 100, 79, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (444, 53, 33, 100, 84, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (445, 872, 152, 100, 86, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (446, 532, 553, 100, 75, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (447, 395, 431, 100, 19, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (448, 141, 303, 100, 71, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (449, 152, 810, 100, 96, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (450, 232, 336, 100, 67, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (451, 299, 235, 100, 48, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (452, 597, 875, 100, 38, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (453, 247, 94, 100, 48, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (454, 537, 183, 100, 27, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (455, 86, 559, 100, 88, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (456, 945, 196, 100, 58, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (457, 638, 374, 100, 28, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (458, 14, 455, 100, 67, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (459, 60, 842, 100, 48, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (460, 383, 62, 100, 41, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (461, 781, 949, 100, 62, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (462, 325, 728, 100, 85, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (463, 819, 242, 100, 41, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (464, 167, 386, 100, 49, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (465, 919, 243, 100, 20, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (466, 57, 95, 100, 57, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (467, 588, 646, 100, 21, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (468, 541, 820, 100, 36, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (469, 914, 983, 100, 16, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (470, 149, 560, 100, 74, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (471, 938, 161, 100, 20, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (472, 428, 590, 100, 79, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (473, 934, 620, 100, 34, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (474, 246, 842, 100, 33, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (475, 214, 178, 100, 66, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (476, 243, 823, 100, 27, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (477, 833, 970, 100, 39, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (478, 785, 883, 100, 12, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (479, 204, 979, 100, 44, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (480, 21, 662, 100, 85, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (481, 122, 237, 100, 91, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (482, 284, 367, 100, 2, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (483, 307, 967, 100, 35, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (484, 393, 419, 100, 71, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (485, 914, 44, 100, 48, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (486, 937, 174, 100, 27, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (487, 738, 463, 100, 93, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (488, 863, 193, 100, 84, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (489, 939, 648, 100, 39, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (490, 403, 212, 100, 43, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (491, 410, 642, 100, 98, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (492, 33, 995, 100, 60, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (493, 624, 360, 100, 5, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (494, 329, 671, 100, 47, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (495, 810, 145, 100, 18, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (496, 77, 522, 100, 49, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (497, 559, 239, 100, 92, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (498, 686, 106, 100, 14, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (499, 521, 177, 100, 93, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (500, 650, 96, 100, 25, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (501, 861, 741, 100, 44, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (502, 851, 57, 100, 45, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (503, 344, 167, 100, 92, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (504, 489, 902, 100, 28, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (505, 339, 131, 100, 61, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (506, 813, 625, 100, 21, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (507, 770, 826, 100, 24, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (508, 653, 221, 100, 56, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (509, 879, 683, 100, 7, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (510, 476, 510, 100, 68, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (511, 365, 780, 100, 19, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (512, 884, 480, 100, 91, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (513, 640, 584, 100, 98, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (514, 523, 418, 100, 16, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (515, 872, 720, 100, 88, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (516, 622, 335, 100, 90, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (517, 118, 663, 100, 87, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (518, 472, 816, 100, 64, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (519, 397, 464, 100, 60, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (520, 197, 468, 100, 7, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (521, 141, 359, 100, 56, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (522, 816, 479, 100, 57, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (523, 151, 275, 100, 20, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (524, 607, 358, 100, 27, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (525, 657, 276, 100, 75, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (526, 239, 929, 100, 96, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (527, 424, 1, 100, 52, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (528, 447, 819, 100, 75, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (529, 814, 155, 100, 16, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (530, 105, 957, 100, 55, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (531, 382, 424, 100, 29, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (532, 271, 501, 100, 77, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (533, 809, 97, 100, 0, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (534, 240, 380, 100, 70, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (535, 611, 888, 100, 50, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (536, 509, 644, 100, 40, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (537, 95, 345, 100, 50, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (538, 118, 835, 100, 31, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (539, 254, 802, 100, 3, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (540, 667, 160, 100, 25, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (541, 83, 73, 100, 14, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (542, 119, 250, 100, 94, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (543, 806, 340, 100, 28, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (544, 114, 73, 100, 58, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (545, 883, 491, 100, 6, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (546, 493, 452, 100, 57, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (547, 430, 654, 100, 66, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (548, 340, 416, 100, 57, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (549, 571, 266, 100, 90, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (550, 733, 61, 100, 78, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (551, 105, 370, 100, 20, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (552, 539, 515, 100, 65, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (553, 995, 311, 100, 65, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (554, 10, 537, 100, 30, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (555, 315, 197, 100, 57, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (556, 18, 23, 100, 92, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (557, 51, 989, 100, 90, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (558, 765, 492, 100, 75, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (559, 64, 271, 100, 4, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (560, 666, 167, 100, 96, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (561, 394, 46, 100, 67, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (562, 369, 141, 100, 47, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (563, 922, 878, 100, 35, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (564, 47, 819, 100, 34, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (565, 283, 392, 100, 64, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (566, 64, 352, 100, 18, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (567, 125, 460, 100, 98, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (568, 491, 857, 100, 35, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (569, 67, 144, 100, 84, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (570, 722, 885, 100, 12, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (571, 587, 750, 100, 9, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (572, 785, 858, 100, 9, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (573, 836, 569, 100, 17, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (574, 349, 191, 100, 61, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (575, 64, 64, 100, 50, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (576, 935, 316, 100, 70, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (577, 479, 177, 100, 0, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (578, 247, 552, 100, 90, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (579, 109, 554, 100, 49, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (580, 464, 968, 100, 77, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (581, 138, 145, 100, 36, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (582, 840, 567, 100, 51, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (583, 218, 330, 100, 48, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (584, 673, 566, 100, 86, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (585, 651, 414, 100, 86, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (586, 92, 68, 100, 70, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (587, 301, 279, 100, 93, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (588, 77, 821, 100, 54, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (589, 676, 352, 100, 90, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (590, 340, 780, 100, 88, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (591, 249, 427, 100, 72, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (592, 680, 523, 100, 94, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (593, 128, 790, 100, 55, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (594, 798, 12, 100, 93, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (595, 908, 975, 100, 1, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (596, 959, 215, 100, 23, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (597, 707, 471, 100, 15, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (598, 945, 213, 100, 4, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (599, 305, 968, 100, 75, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (600, 661, 648, 100, 63, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (601, 345, 123, 100, 90, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (602, 46, 524, 100, 61, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (603, 593, 423, 100, 36, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (604, 306, 594, 100, 94, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (605, 980, 268, 100, 65, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (606, 312, 4, 100, 43, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (607, 655, 498, 100, 20, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (608, 738, 821, 100, 70, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (609, 810, 76, 100, 90, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (610, 217, 829, 100, 41, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (611, 914, 191, 100, 34, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (612, 703, 904, 100, 46, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (613, 901, 305, 100, 27, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (614, 910, 898, 100, 98, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (615, 861, 541, 100, 7, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (616, 555, 328, 100, 44, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (617, 496, 690, 100, 99, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (618, 436, 702, 100, 60, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (619, 273, 288, 100, 6, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (620, 400, 29, 100, 51, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (621, 432, 361, 100, 34, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (622, 204, 638, 100, 20, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (623, 559, 41, 100, 97, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (624, 365, 543, 100, 24, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (625, 969, 116, 100, 32, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (626, 986, 162, 100, 84, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (627, 419, 307, 100, 27, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (628, 414, 950, 100, 80, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (629, 746, 48, 100, 22, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (630, 367, 435, 100, 68, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (631, 350, 166, 100, 73, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (632, 452, 526, 100, 61, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (633, 662, 923, 100, 86, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (634, 507, 808, 100, 48, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (635, 962, 759, 100, 82, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (636, 189, 372, 100, 67, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (637, 170, 3, 100, 87, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (638, 258, 391, 100, 35, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (639, 319, 896, 100, 14, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (640, 730, 483, 100, 65, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (641, 129, 428, 100, 84, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (642, 273, 602, 100, 24, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (643, 198, 970, 100, 66, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (644, 994, 629, 100, 61, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (645, 137, 75, 100, 8, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (646, 565, 839, 100, 55, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (647, 20, 978, 100, 53, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (648, 513, 576, 100, 1, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (649, 778, 788, 100, 43, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (650, 9, 355, 100, 15, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (651, 101, 59, 100, 45, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (652, 487, 568, 100, 81, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (653, 467, 66, 100, 70, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (654, 593, 650, 100, 8, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (655, 324, 985, 100, 28, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (656, 813, 102, 100, 15, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (657, 547, 960, 100, 94, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (658, 825, 777, 100, 23, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (659, 856, 17, 100, 32, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (660, 913, 569, 100, 92, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (661, 596, 983, 100, 65, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (662, 543, 713, 100, 48, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (663, 504, 931, 100, 44, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (664, 710, 29, 100, 75, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (665, 364, 290, 100, 46, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (666, 353, 519, 100, 5, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (667, 754, 945, 100, 88, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (668, 407, 497, 100, 23, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (669, 565, 729, 100, 52, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (670, 510, 842, 100, 92, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (671, 749, 57, 100, 3, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (672, 962, 504, 100, 38, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (673, 23, 905, 100, 82, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (674, 704, 265, 100, 96, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (675, 673, 972, 100, 35, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (676, 727, 528, 100, 88, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (677, 141, 727, 100, 34, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (678, 749, 896, 100, 8, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (679, 548, 626, 100, 36, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (680, 597, 306, 100, 57, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (681, 261, 603, 100, 75, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (682, 273, 543, 100, 4, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (683, 164, 651, 100, 96, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (684, 780, 634, 100, 69, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (685, 752, 615, 100, 57, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (686, 178, 634, 100, 76, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (687, 499, 280, 100, 11, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (688, 996, 284, 100, 28, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (689, 581, 507, 100, 5, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (690, 933, 841, 100, 40, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (691, 775, 723, 100, 87, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (692, 488, 562, 100, 14, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (693, 395, 404, 100, 9, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (694, 373, 772, 100, 4, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (695, 335, 757, 100, 93, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (696, 548, 287, 100, 55, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (697, 599, 64, 100, 93, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (698, 783, 916, 100, 1, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (699, 694, 714, 100, 24, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (700, 540, 50, 100, 19, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (701, 88, 123, 100, 22, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (702, 537, 39, 100, 55, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (703, 651, 942, 100, 8, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (704, 975, 620, 100, 75, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (705, 241, 883, 100, 49, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (706, 307, 215, 100, 23, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (707, 703, 477, 100, 69, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (708, 122, 793, 100, 73, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (709, 388, 668, 100, 61, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (710, 460, 493, 100, 84, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (711, 99, 531, 100, 38, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (712, 23, 519, 100, 38, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (713, 156, 781, 100, 75, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (714, 258, 139, 100, 59, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (715, 492, 241, 100, 74, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (716, 622, 333, 100, 90, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (717, 834, 931, 100, 28, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (718, 259, 828, 100, 70, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (719, 415, 302, 100, 65, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (720, 966, 542, 100, 17, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (721, 673, 186, 100, 87, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (722, 689, 220, 100, 87, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (723, 237, 645, 100, 72, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (724, 125, 735, 100, 1, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (725, 991, 765, 100, 87, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (726, 526, 788, 100, 35, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (727, 765, 638, 100, 11, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (728, 297, 737, 100, 52, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (729, 76, 85, 100, 27, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (730, 980, 882, 100, 79, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (731, 614, 259, 100, 16, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (732, 754, 477, 100, 40, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (733, 41, 952, 100, 52, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (734, 482, 643, 100, 40, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (735, 505, 94, 100, 44, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (736, 901, 645, 100, 2, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (737, 152, 596, 100, 75, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (738, 999, 252, 100, 72, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (739, 532, 36, 100, 33, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (740, 946, 684, 100, 48, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (741, 992, 233, 100, 43, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (742, 271, 843, 100, 71, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (743, 680, 852, 100, 27, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (744, 162, 768, 100, 20, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (745, 917, 836, 100, 21, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (746, 933, 357, 100, 46, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (747, 851, 588, 100, 64, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (748, 979, 26, 100, 85, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (749, 198, 54, 100, 32, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (750, 357, 576, 100, 5, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (751, 424, 608, 100, 28, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (752, 445, 653, 100, 27, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (753, 45, 901, 100, 49, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (754, 457, 265, 100, 63, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (755, 281, 249, 100, 70, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (756, 844, 397, 100, 59, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (757, 923, 659, 100, 84, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (758, 662, 466, 100, 46, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (759, 530, 97, 100, 78, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (760, 209, 203, 100, 49, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (761, 324, 916, 100, 14, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (762, 222, 948, 100, 23, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (763, 586, 910, 100, 71, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (764, 981, 861, 100, 87, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (765, 392, 279, 100, 22, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (766, 980, 69, 100, 49, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (767, 267, 662, 100, 77, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (768, 957, 911, 100, 40, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (769, 923, 735, 100, 68, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (770, 532, 645, 100, 22, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (771, 802, 898, 100, 6, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (772, 476, 132, 100, 63, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (773, 43, 243, 100, 97, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (774, 439, 637, 100, 97, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (775, 238, 555, 100, 93, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (776, 557, 981, 100, 75, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (777, 147, 947, 100, 95, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (778, 958, 46, 100, 50, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (779, 157, 563, 100, 66, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (780, 271, 48, 100, 79, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (781, 721, 552, 100, 95, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (782, 62, 448, 100, 42, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (783, 149, 39, 100, 22, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (784, 999, 396, 100, 86, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (785, 543, 618, 100, 65, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (786, 11, 834, 100, 66, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (787, 828, 467, 100, 33, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (788, 227, 89, 100, 70, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (789, 20, 349, 100, 49, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (790, 43, 284, 100, 34, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (791, 585, 325, 100, 25, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (792, 638, 151, 100, 22, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (793, 589, 835, 100, 35, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (794, 60, 848, 100, 7, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (795, 544, 985, 100, 33, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (796, 24, 759, 100, 41, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (797, 32, 694, 100, 8, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (798, 146, 337, 100, 16, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (799, 35, 206, 100, 58, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (800, 136, 895, 100, 40, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (801, 610, 86, 100, 26, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (802, 837, 650, 100, 10, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (803, 316, 365, 100, 72, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (804, 744, 196, 100, 29, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (805, 733, 39, 100, 28, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (806, 354, 160, 100, 55, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (807, 681, 88, 100, 89, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (808, 437, 706, 100, 83, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (809, 597, 574, 100, 46, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (810, 927, 303, 100, 79, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (811, 713, 305, 100, 60, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (812, 113, 89, 100, 63, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (813, 136, 489, 100, 35, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (814, 394, 484, 100, 87, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (815, 711, 856, 100, 28, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (816, 519, 967, 100, 81, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (817, 620, 697, 100, 20, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (818, 648, 873, 100, 59, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (819, 301, 849, 100, 34, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (820, 683, 654, 100, 94, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (821, 688, 443, 100, 65, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (822, 141, 269, 100, 44, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (823, 346, 486, 100, 27, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (824, 797, 23, 100, 3, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (825, 667, 272, 100, 33, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (826, 875, 177, 100, 55, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (827, 941, 649, 100, 79, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (828, 769, 456, 100, 27, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (829, 193, 742, 100, 99, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (830, 632, 689, 100, 13, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (831, 110, 492, 100, 67, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (832, 64, 390, 100, 99, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (833, 443, 686, 100, 93, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (834, 79, 572, 100, 67, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (835, 205, 949, 100, 56, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (836, 306, 263, 100, 78, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (837, 419, 899, 100, 23, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (838, 796, 799, 100, 82, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (839, 396, 302, 100, 42, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (840, 900, 618, 100, 61, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (841, 953, 658, 100, 79, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (842, 39, 827, 100, 13, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (843, 139, 958, 100, 29, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (844, 130, 114, 100, 4, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (845, 889, 288, 100, 33, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (846, 283, 315, 100, 53, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (847, 629, 17, 100, 66, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (848, 625, 415, 100, 70, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (849, 332, 789, 100, 52, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (850, 683, 58, 100, 53, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (851, 782, 990, 100, 6, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (852, 536, 113, 100, 71, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (853, 8, 379, 100, 38, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (854, 694, 420, 100, 75, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (855, 494, 436, 100, 62, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (856, 492, 370, 100, 86, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (857, 166, 667, 100, 41, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (858, 135, 151, 100, 51, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (859, 578, 824, 100, 29, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (860, 40, 142, 100, 92, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (861, 211, 819, 100, 74, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (862, 831, 96, 100, 95, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (863, 856, 938, 100, 52, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (864, 538, 393, 100, 74, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (865, 515, 927, 100, 13, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (866, 63, 124, 100, 46, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (867, 717, 754, 100, 89, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (868, 793, 399, 100, 7, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (869, 941, 953, 100, 68, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (870, 613, 265, 100, 20, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (871, 152, 815, 100, 95, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (872, 638, 197, 100, 16, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (873, 206, 709, 100, 92, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (874, 433, 948, 100, 16, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (875, 632, 777, 100, 2, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (876, 473, 765, 100, 63, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (877, 87, 102, 100, 7, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (878, 709, 155, 100, 47, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (879, 317, 687, 100, 13, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (880, 712, 87, 100, 25, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (881, 331, 939, 100, 86, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (882, 119, 72, 100, 55, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (883, 40, 308, 100, 18, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (884, 915, 727, 100, 26, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (885, 442, 134, 100, 76, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (886, 196, 101, 100, 1, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (887, 804, 596, 100, 75, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (888, 344, 496, 100, 75, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (889, 518, 700, 100, 49, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (890, 717, 89, 100, 18, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (891, 904, 591, 100, 44, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (892, 699, 456, 100, 64, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (893, 520, 609, 100, 91, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (894, 340, 308, 100, 60, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (895, 994, 602, 100, 30, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (896, 918, 285, 100, 70, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (897, 771, 553, 100, 58, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (898, 645, 417, 100, 80, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (899, 132, 853, 100, 26, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (900, 79, 135, 100, 91, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (901, 185, 438, 100, 77, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (902, 791, 548, 100, 10, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (903, 12, 180, 100, 22, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (904, 938, 620, 100, 79, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (905, 683, 843, 100, 31, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (906, 96, 727, 100, 15, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (907, 470, 365, 100, 82, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (908, 489, 151, 100, 65, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (909, 130, 646, 100, 80, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (910, 590, 847, 100, 6, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (911, 206, 761, 100, 89, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (912, 245, 285, 100, 25, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (913, 802, 44, 100, 59, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (914, 99, 744, 100, 22, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (915, 272, 611, 100, 33, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (916, 488, 213, 100, 97, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (917, 842, 437, 100, 86, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (918, 619, 622, 100, 39, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (919, 362, 850, 100, 37, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (920, 465, 7, 100, 69, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (921, 729, 816, 100, 34, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (922, 890, 314, 100, 61, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (923, 388, 621, 100, 3, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (924, 556, 649, 100, 35, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (925, 89, 162, 100, 63, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (926, 454, 687, 100, 12, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (927, 444, 205, 100, 71, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (928, 109, 18, 100, 21, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (929, 279, 768, 100, 88, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (930, 482, 105, 100, 80, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (931, 284, 379, 100, 36, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (932, 91, 781, 100, 39, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (933, 30, 576, 100, 85, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (934, 713, 276, 100, 11, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (935, 781, 571, 100, 97, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (936, 591, 592, 100, 54, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (937, 721, 109, 100, 77, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (938, 876, 216, 100, 24, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (939, 260, 807, 100, 88, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (940, 777, 284, 100, 69, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (941, 680, 896, 100, 79, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (942, 682, 402, 100, 91, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (943, 959, 40, 100, 17, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (944, 794, 414, 100, 13, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (945, 101, 988, 100, 12, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (946, 491, 329, 100, 24, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (947, 626, 558, 100, 81, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (948, 932, 261, 100, 36, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (949, 708, 222, 100, 36, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (950, 892, 82, 100, 70, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (951, 342, 925, 100, 42, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (952, 472, 942, 100, 1, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (953, 135, 495, 100, 78, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (954, 792, 826, 100, 90, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (955, 508, 243, 100, 13, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (956, 492, 659, 100, 97, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (957, 239, 151, 100, 46, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (958, 426, 671, 100, 40, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (959, 201, 503, 100, 60, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (960, 789, 519, 100, 80, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (961, 524, 584, 100, 22, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (962, 620, 5, 100, 71, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (963, 268, 811, 100, 87, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (964, 821, 871, 100, 22, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (965, 334, 339, 100, 50, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (966, 479, 928, 100, 85, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (967, 925, 751, 100, 75, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (968, 557, 487, 100, 21, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (969, 554, 603, 100, 79, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (970, 585, 304, 100, 32, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (971, 660, 379, 100, 24, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (972, 694, 850, 100, 21, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (973, 988, 680, 100, 35, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (974, 9, 404, 100, 11, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (975, 212, 401, 100, 52, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (976, 120, 704, 100, 28, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (977, 114, 343, 100, 81, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (978, 828, 707, 100, 22, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (979, 7, 470, 100, 66, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (980, 331, 31, 100, 63, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (981, 677, 91, 100, 19, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (982, 108, 304, 100, 6, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (983, 704, 115, 100, 74, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (984, 926, 717, 100, 53, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (985, 770, 612, 100, 41, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (986, 20, 553, 100, 45, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (987, 93, 75, 100, 5, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (988, 76, 247, 100, 89, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (989, 265, 125, 100, 28, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (990, 414, 63, 100, 74, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (991, 63, 369, 100, 87, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (992, 55, 971, 100, 11, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (993, 432, 708, 100, 95, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (994, 644, 990, 100, 44, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (995, 396, 583, 100, 33, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (996, 609, 768, 100, 34, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (997, 195, 167, 100, 72, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (998, 140, 169, 100, 58, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (999, 455, 343, 100, 74, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (1000, 325, 269, 100, 97, 1, '2023-04-19 02:47:45', '2023-04-19 02:47:45', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (2000000, 1, 101, 1, 98, 1, '2023-04-20 07:35:11', '2023-04-20 07:35:11', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (2000001, 101, 1, 242368, -242269, 1, '2023-04-20 13:13:15', '2023-04-20 13:13:15', 0);
INSERT INTO `t_user_interface_info_entity` VALUES (2000002, 101, 1030, 2, 98, 1, '2023-04-21 07:02:45', '2023-04-21 07:02:45', 0);

-- ----------------------------
-- Table structure for t_white_list
-- ----------------------------
DROP TABLE IF EXISTS `t_white_list`;
CREATE TABLE `t_white_list`  (
                                 `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                 `user_id` bigint(20) NOT NULL COMMENT '用户',
                                 `host` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ip',
                                 `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                                 `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                 `is_delete` tinyint(4) NOT NULL DEFAULT 0 COMMENT '逻辑删除(1删除)',
                                 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_white_list
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
