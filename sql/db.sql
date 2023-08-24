/*
 Navicat Premium Data Transfer

 Source Server         : centsos-192.168.159.134
 Source Server Type    : MySQL
 Source Server Version : 50740
 Source Host           : 192.168.159.134:3306
 Source Schema         : api-platform

 Target Server Type    : MySQL
 Target Server Version : 50740
 File Encoding         : 65001

 Date: 24/08/2023 11:20:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_call_result
-- ----------------------------
DROP TABLE IF EXISTS `t_call_result`;
CREATE TABLE `t_call_result`  (
                                  `user_id` bigint(20) NOT NULL COMMENT '用户id',
                                  `interface_id` bigint(20) NOT NULL COMMENT '接口id',
                                  `succeed` tinyint(4) NOT NULL DEFAULT 1 COMMENT '是否调用成功',
                                  `call_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '调用时间',
                                  `id` bigint(20) NOT NULL COMMENT '主键',
                                  PRIMARY KEY (`id`, `user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_interface
-- ----------------------------
DROP TABLE IF EXISTS `t_interface`;
CREATE TABLE `t_interface`  (
                                `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                `name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '接口名称',
                                `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '接口描述',
                                `is_free` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否免费',
                                `url` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '接口地址',
                                `method` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '请求方式',
                                `request_param` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '{}' COMMENT '请求参数',
                                `request_header` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '{}' COMMENT '请求头',
                                `response_header` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '{}' COMMENT '响应头',
                                `status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '接口状态(0-关闭 ,1-开启)',
                                `user_id` int(11) NOT NULL COMMENT '创建人',
                                `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                                `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                `is_delete` tinyint(4) NOT NULL DEFAULT 0 COMMENT '逻辑删除(1删除)',
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_interface_example
-- ----------------------------
DROP TABLE IF EXISTS `t_interface_example`;
CREATE TABLE `t_interface_example`  (
                                        `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                        `interface_id` bigint(20) NOT NULL COMMENT '接口id',
                                        `call_example` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调用示例',
                                        `return_example` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '返回示例',
                                        `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                                        `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                        `is_delete` tinyint(4) NOT NULL DEFAULT 0 COMMENT '逻辑删除(1删除)',
                                        PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
                           `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                           `user_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '昵称',
                           `user_account` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账号',
                           `avatar_url` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'https://blog.dhx.icu/img/avater.png' COMMENT '头像',
                           `gender` tinyint(4) NOT NULL DEFAULT 1 COMMENT '性别(1男0女)',
                           `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
                           `phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话',
                           `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
                           `access_key` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ak',
                           `secret_key` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'sk',
                           `user_role` tinyint(4) NOT NULL DEFAULT 0 COMMENT '0-用户 1-管理员',
                           `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                           `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                           `is_delete` tinyint(4) NOT NULL DEFAULT 0 COMMENT '逻辑删除(1删除)',
                           PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_user_interface_info
-- ----------------------------
DROP TABLE IF EXISTS `t_user_interface_info`;
CREATE TABLE `t_user_interface_info`  (
                                          `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                          `user_id` bigint(20) NOT NULL COMMENT '用户id',
                                          `total_num` int(11) NOT NULL DEFAULT 0 COMMENT '总次数',
                                          `left_num` int(11) NOT NULL DEFAULT 0 COMMENT '剩余次数',
                                          `status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '领取状态',
                                          `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                                          `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                          `is_delete` tinyint(4) NOT NULL DEFAULT 0 COMMENT '逻辑删除(1删除)',
                                          PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

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

SET FOREIGN_KEY_CHECKS = 1;
