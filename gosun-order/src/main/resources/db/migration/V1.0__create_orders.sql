/*
 Navicat Premium Data Transfer

 Source Server         : cloud_test
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : 181.181.1.11:3306
 Source Schema         : orders

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 06/09/2019 14:49:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `order_num` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `item_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('B5C890ADFAA244BE937240F37AF154BE', 'F9F768B9A6194EC0BB7F1090449C2BA1', '2');

SET FOREIGN_KEY_CHECKS = 1;


CREATE TABLE `t_community_mac_count` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `community_id` bigint(20) DEFAULT NULL COMMENT '小区ID',
  `building_id` bigint(20) DEFAULT NULL COMMENT '小区楼栋ID',
  `building` varchar(64) DEFAULT NULL COMMENT '楼栋',
  `sum` bigint(20) DEFAULT NULL COMMENT '统计计数',
  `total_sum` bigint(20) DEFAULT NULL COMMENT '累计统计数',
  `diff_sum` bigint(20) DEFAULT NULL COMMENT '差值统计数',
  `remark` text DEFAULT NULL COMMENT '描述',
  `data_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据时间',
  `type` int(11) DEFAULT NULL COMMENT '类型 （1小区 2楼栋）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci AUTO_INCREMENT=210004 COMMENT='小区mac统计';

CREATE TABLE `t_community_mac_event` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `event_name` varchar(128) NOT NULL COMMENT '时间名称',
  `community_id` bigint(20) NOT NULL COMMENT '小区ID',
  `building_id` bigint(20) NOT NULL COMMENT '楼栋ID',
  `unit_id` bigint(20) NOT NULL COMMENT '单元ID',
  `type` int(2) DEFAULT NULL COMMENT '事件类型（1群租 2超出阈值报警）',
  `number` int(11) DEFAULT NULL COMMENT '群租人数',
  `remark` varchar(64) DEFAULT NULL COMMENT '事件描述',
  `event_time` timestamp NULL DEFAULT NULL COMMENT '事件时间',
  `building` varchar(50) DEFAULT NULL COMMENT '楼栋',
  `unit` varchar(50) DEFAULT NULL COMMENT '单元',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci AUTO_INCREMENT=120012 COMMENT='基于MAC采集事件记录表';
