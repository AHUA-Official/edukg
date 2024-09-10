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

 Date: 07/08/2024 21:22:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for triple
-- ----------------------------
DROP TABLE IF EXISTS `triple`;
CREATE TABLE `triple`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `Subject` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '一个主语' COMMENT '主语',
  `edge` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '一个关系' COMMENT '关系',
  `target` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '一个宾语' COMMENT '宾语',
  `Subjectid` int NULL DEFAULT 1 COMMENT '主语id',
  `targetid` int NULL DEFAULT 1 COMMENT '宾语id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '三元组表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of triple
-- ----------------------------
INSERT INTO `triple` VALUES (1, '我', '爱', '你', 2, 3);

SET FOREIGN_KEY_CHECKS = 1;
