/*
 Navicat Premium Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 80039 (8.0.39)
 Source Host           : localhost:3306
 Source Schema         : springboot

 Target Server Type    : MySQL
 Target Server Version : 80039 (8.0.39)
 File Encoding         : 65001

 Date: 07/08/2024 21:20:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for entity
-- ----------------------------
DROP TABLE IF EXISTS `entity`;
CREATE TABLE `entity`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `entity` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '没有用的实体' COMMENT '实体名称',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '需要归类的类型' COMMENT '实体类型',
  `tag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT 'fghj' COMMENT 'll',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '摘要',
  `source` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '网络资源' COMMENT '来源',
  `score` int NULL DEFAULT 0 COMMENT '分数',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT 'alive' COMMENT '状态',
  `parent_id` int NULL DEFAULT 1 COMMENT '层级关系',
  `alias` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '别名' COMMENT '外号',
  `apeartime` int NULL DEFAULT 1 COMMENT '出现次数',
  `verification` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT 'no' COMMENT '有没有被确认验证过',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '知识实体表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of entity
-- ----------------------------
INSERT INTO `entity` VALUES (1, '根', '根', '根', '根', '网络资源', 100, 'alive', 1, '别名', 1, 'yes');
INSERT INTO `entity` VALUES (2, '我', '需要归类的类型', 'fghj', NULL, '网络资源', 0, 'alive', 1, '别名', 1, 'no');
INSERT INTO `entity` VALUES (3, '你', 'DEFAULT_TYPE', 'DEFAULT_TAG', 'DEFAULT_DESCRIPTION', 'DEFAULT_SOURCE', 0, 'DEFAULT_STATUS', 0, 'DEFAULT_ALIAS', 0, 'UNVERIFIED');

SET FOREIGN_KEY_CHECKS = 1;
