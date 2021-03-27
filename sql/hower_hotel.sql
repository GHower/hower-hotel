/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : hower_hotel

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 27/03/2021 17:46:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for customer_info
-- ----------------------------
DROP TABLE IF EXISTS `customer_info`;
CREATE TABLE `customer_info`  (
  `id` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '会员ID，也是身份证',
  `v_id` int(11) NULL DEFAULT NULL COMMENT '客户会员身份',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '客户姓名',
  `contact` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '联系方式',
  `sex` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '性别',
  `status` smallint(1) UNSIGNED ZEROFILL NOT NULL COMMENT '状态,0-可用，1-黑名单',
  `create_uid` int(11) NULL DEFAULT NULL COMMENT '创建者ID',
  `update_uid` int(11) NULL DEFAULT NULL COMMENT '修改者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '客户创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '客户信息更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_customer_info_customer_vip_1`(`v_id`) USING BTREE,
  CONSTRAINT `fk_customer_info_customer_vip_1` FOREIGN KEY (`v_id`) REFERENCES `customer_vip` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_info
-- ----------------------------
INSERT INTO `customer_info` VALUES ('441222200003150909', 5, '亚索', '13612345679', '1', 1, 1, 1, '2021-03-21 18:10:22', '2021-03-21 18:10:24');
INSERT INTO `customer_info` VALUES ('441222200003150911', 1, '布兰德', '13612345679', '1', 1, 1, 1, '2021-03-21 18:10:22', '2021-03-21 18:10:24');
INSERT INTO `customer_info` VALUES ('441222200003150912', 1, '卡尔玛', '13612345679', '0', 1, 1, 1, '2021-03-21 18:10:22', '2021-03-21 18:10:24');
INSERT INTO `customer_info` VALUES ('441222200003150913', 2, '希维尔', '13612345679', '0', 1, 1, 1, '2021-03-21 18:10:22', '2021-03-21 18:10:24');
INSERT INTO `customer_info` VALUES ('441222200003150914', 1, '厄运晓姐', '13612345679', '0', 1, 1, 1, '2021-03-21 18:10:22', '2021-03-21 18:10:24');
INSERT INTO `customer_info` VALUES ('441222200003150919', 1, '提莫', '13612345679', '1', 1, 1, 1, '2021-03-21 18:10:22', '2021-03-21 18:10:24');
INSERT INTO `customer_info` VALUES ('441222200003150921', 3, '普朗克 ', '13612345679', '1', 1, 1, 1, '2021-03-21 18:10:22', '2021-03-21 18:10:24');
INSERT INTO `customer_info` VALUES ('441222200003150922', 4, '安妮', '13612345679', '0', 1, 1, 1, '2021-03-21 18:10:22', '2021-03-21 18:10:24');
INSERT INTO `customer_info` VALUES ('441222200003150923', 5, '艾瑞莉娅', '13612345679', '0', 1, 1, 1, '2021-03-21 18:10:22', '2021-03-21 18:10:24');
INSERT INTO `customer_info` VALUES ('441222200003150929', 1, '波比', '13612345679', '0', 1, 1, 1, '2021-03-21 18:10:22', '2021-03-21 18:10:24');
INSERT INTO `customer_info` VALUES ('441222200003150939', 1, '潘森', '13612345679', '1', 1, 1, 1, '2021-03-21 18:10:22', '2021-03-21 18:10:24');

-- ----------------------------
-- Table structure for customer_vip
-- ----------------------------
DROP TABLE IF EXISTS `customer_vip`;
CREATE TABLE `customer_vip`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '会员类型ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '会员名称',
  `discount` decimal(5, 2) NULL DEFAULT NULL COMMENT '折扣力度',
  `create_uid` int(11) NULL DEFAULT NULL COMMENT '创建者ID',
  `update_uid` int(11) NULL DEFAULT NULL COMMENT '修改者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `status` smallint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_vip
-- ----------------------------
INSERT INTO `customer_vip` VALUES (1, '一星会员', 1.00, 1, 1, '2021-03-21 12:14:20', '2021-03-21 12:14:52', 1);
INSERT INTO `customer_vip` VALUES (2, '二星会员', 0.95, 1, 1, '2021-03-21 12:14:20', '2021-03-21 12:14:52', 1);
INSERT INTO `customer_vip` VALUES (3, '三星会员', 0.88, 1, 1, '2021-03-23 14:59:20', '2021-03-23 14:59:22', 1);
INSERT INTO `customer_vip` VALUES (4, '四星会员', 0.80, 1, 1, '2021-03-23 14:59:20', '2021-03-23 14:59:22', 1);
INSERT INTO `customer_vip` VALUES (5, '五星会员', 0.70, 1, 1, '2021-03-23 14:59:20', '2021-03-23 14:59:22', 1);

-- ----------------------------
-- Table structure for department_info
-- ----------------------------
DROP TABLE IF EXISTS `department_info`;
CREATE TABLE `department_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门ID',
  `p_id` int(11) NULL DEFAULT NULL COMMENT '父级部门',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  `status` smallint(1) NULL DEFAULT NULL COMMENT '部门状态',
  `create_uid` int(11) NULL DEFAULT NULL COMMENT '创建者ID',
  `update_uid` int(11) NULL DEFAULT NULL COMMENT '修改者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_department_info_department_info_1`(`p_id`) USING BTREE,
  CONSTRAINT `fk_department_info_department_info_1` FOREIGN KEY (`p_id`) REFERENCES `department_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department_info
-- ----------------------------
INSERT INTO `department_info` VALUES (1, 1, '豪尔精品酒店', 1, 1, 1, '2021-03-22 15:43:43', '2021-03-22 15:43:47');
INSERT INTO `department_info` VALUES (2, 1, '行政部', 1, 1, 1, '2021-03-22 15:43:43', '2021-03-22 15:43:47');
INSERT INTO `department_info` VALUES (3, 1, '财务部', 1, 1, 1, '2021-03-22 15:47:01', '2021-03-22 15:47:03');
INSERT INTO `department_info` VALUES (4, 1, '工程部', 1, 1, 1, '2021-03-22 15:47:01', '2021-03-22 15:47:03');
INSERT INTO `department_info` VALUES (5, 1, '客房部', 1, 1, 1, '2021-03-22 15:47:01', '2021-03-22 15:47:03');
INSERT INTO `department_info` VALUES (6, 2, '后勤保障', 1, 1, 1, '2021-03-22 15:48:47', '2021-03-22 15:48:49');
INSERT INTO `department_info` VALUES (7, 2, '涉外经营', 1, 1, 1, '2021-03-22 15:48:47', '2021-03-22 15:48:49');
INSERT INTO `department_info` VALUES (8, 2, '项目承包', 1, 1, 1, '2021-03-22 15:48:47', '2021-03-22 15:48:49');
INSERT INTO `department_info` VALUES (9, 2, '报批及复验', 1, 1, 1, '2021-03-22 15:48:47', '2021-03-22 15:48:49');
INSERT INTO `department_info` VALUES (10, 3, '财务计划、监督', 1, 1, 1, '2021-03-22 15:48:47', '2021-03-22 15:48:49');
INSERT INTO `department_info` VALUES (11, 3, '资金规划', 1, 1, 1, '2021-03-22 15:48:47', '2021-03-22 15:48:49');
INSERT INTO `department_info` VALUES (12, 4, '设备监管', 1, 1, 1, '2021-03-22 15:48:47', '2021-03-22 15:48:49');
INSERT INTO `department_info` VALUES (13, 5, '客房销售', 1, 1, 1, '2021-03-22 15:48:47', '2021-03-22 15:48:49');
INSERT INTO `department_info` VALUES (14, 5, '客房保洁', 1, 1, 1, '2021-03-22 15:48:47', '2021-03-22 15:48:49');
INSERT INTO `department_info` VALUES (15, 9, 'test', 0, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info`  (
  `id` int(16) NOT NULL COMMENT '订单号',
  `r_id` int(11) NOT NULL COMMENT '房间ID',
  `c_id` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '客户ID',
  `guest_id` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单下的住客信息',
  `in_time` datetime(0) NULL DEFAULT NULL COMMENT '入住时间',
  `out_time` datetime(0) NULL DEFAULT NULL COMMENT '离店时间',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单状态，1-有效，2-租住中，3-已取消',
  `source` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单来源',
  `remarcks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单备注',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '订单创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '订单修改时间',
  `pay_fact` decimal(8, 2) NULL DEFAULT NULL COMMENT '需要付款的金额，实付款',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_order_info_customer_info_1`(`c_id`) USING BTREE,
  INDEX `fk_order_info_room_info_1`(`r_id`) USING BTREE,
  CONSTRAINT `fk_order_info_customer_info_1` FOREIGN KEY (`c_id`) REFERENCES `customer_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_order_info_room_info_1` FOREIGN KEY (`r_id`) REFERENCES `room_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_info
-- ----------------------------
INSERT INTO `order_info` VALUES (2020065334, 5, '441222200003150922', '441222200003150922', '2021-03-27 16:41:05', '2021-03-31 16:41:08', '1', '美团', NULL, '2021-03-27 16:41:33', '2021-03-27 16:41:36', NULL);
INSERT INTO `order_info` VALUES (2020065356, 1, '441222200003150939', '441222200003150939', '2021-03-27 16:41:05', '2021-03-31 16:41:08', '3', '美团', NULL, '2021-03-27 16:41:33', '2021-03-27 16:41:36', NULL);
INSERT INTO `order_info` VALUES (2021050512, 1, '441222200003150914', '441222200003150913,441222200003150912', '2021-03-23 12:17:36', '2021-03-25 08:17:50', '2', '美团', NULL, '2021-03-25 08:18:13', '2021-03-25 08:18:16', 128.00);
INSERT INTO `order_info` VALUES (2021050612, 2, '441222200003150923', '441222200003150923,441222200003150922', '2021-03-25 12:17:36', '2021-03-31 08:17:50', '2', '美团', NULL, '2021-03-25 08:18:13', '2021-03-25 08:18:16', 148.84);
INSERT INTO `order_info` VALUES (2021065332, 3, '441222200003150921', '441222200003150921,441222200003150939', '2021-03-27 12:17:36', '2021-03-28 08:17:50', '2', '美团', NULL, '2021-03-25 08:18:13', '2021-03-25 08:18:16', 159.60);

-- ----------------------------
-- Table structure for order_pay_info
-- ----------------------------
DROP TABLE IF EXISTS `order_pay_info`;
CREATE TABLE `order_pay_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `s_id` int(11) NULL DEFAULT NULL COMMENT '操作员ID',
  `o_id` int(11) NULL DEFAULT NULL COMMENT '订单ID',
  `pay_way` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '付款方式,[现金，微信，支付宝，刷某行卡]',
  `money` decimal(10, 2) NULL DEFAULT NULL COMMENT '付款金额',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '流水账创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_pay_info_staff_info_id`(`s_id`) USING BTREE,
  CONSTRAINT `fk_pay_info_staff_info_id` FOREIGN KEY (`s_id`) REFERENCES `staff_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_pay_info
-- ----------------------------
INSERT INTO `order_pay_info` VALUES (1, 10000, 2021050512, '微信', 128.00, '2021-03-26 10:10:21');
INSERT INTO `order_pay_info` VALUES (2, 10000, 2021050612, '现金', 100.00, '2021-03-26 10:10:24');
INSERT INTO `order_pay_info` VALUES (3, 10000, 2021050612, '微信', 48.84, '2021-03-26 10:10:26');
INSERT INTO `order_pay_info` VALUES (4, 10000, 2021065332, '支付宝', 159.60, '2021-03-26 10:10:29');

-- ----------------------------
-- Table structure for room_info
-- ----------------------------
DROP TABLE IF EXISTS `room_info`;
CREATE TABLE `room_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '房间id',
  `type_id` int(11) NOT NULL COMMENT '房间类型，->房间类型表.id',
  `status` smallint(6) NULL DEFAULT NULL COMMENT '房间状态',
  `number` int(11) NULL DEFAULT NULL COMMENT '房间号码',
  `create_uid` int(11) NULL DEFAULT NULL COMMENT '创建者ID',
  `update_uid` int(11) NULL DEFAULT NULL COMMENT '修改者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_room_info_room_type_1`(`type_id`) USING BTREE,
  CONSTRAINT `fk_room_info_room_type_1` FOREIGN KEY (`type_id`) REFERENCES `room_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of room_info
-- ----------------------------
INSERT INTO `room_info` VALUES (1, 1, 3, 8201, 1, 1, '2021-03-23 22:53:19', '2021-03-23 22:53:21');
INSERT INTO `room_info` VALUES (2, 2, 3, 8202, 1, 1, '2021-03-23 22:53:19', '2021-03-23 22:53:21');
INSERT INTO `room_info` VALUES (3, 3, 3, 8203, 1, 1, '2021-03-23 22:53:19', '2021-03-23 22:53:21');
INSERT INTO `room_info` VALUES (4, 4, 1, 8204, 1, 1, '2021-03-23 22:53:19', '2021-03-23 22:53:21');
INSERT INTO `room_info` VALUES (5, 5, 2, 8205, 1, 1, '2021-03-23 22:53:19', '2021-03-23 22:53:21');
INSERT INTO `room_info` VALUES (6, 6, 4, 8206, 1, 1, '2021-03-23 22:55:06', '2021-03-23 22:55:10');
INSERT INTO `room_info` VALUES (7, 7, 1, 8207, 1, 1, '2021-03-23 22:53:19', '2021-03-23 22:53:21');
INSERT INTO `room_info` VALUES (8, 2, 2, 8208, 1, 1, '2021-03-23 22:53:19', '2021-03-23 22:53:21');
INSERT INTO `room_info` VALUES (9, 2, 1, 8301, 1, 1, '2021-03-23 22:53:19', '2021-03-23 22:53:21');
INSERT INTO `room_info` VALUES (10, 3, 2, 8302, 1, 1, '2021-03-23 22:53:19', '2021-03-23 22:53:21');
INSERT INTO `room_info` VALUES (11, 2, 2, 8303, 1, 1, '2021-03-23 22:53:19', '2021-03-23 22:53:21');
INSERT INTO `room_info` VALUES (12, 4, 1, 8304, 1, 1, '2021-03-23 22:53:19', '2021-03-23 22:53:21');
INSERT INTO `room_info` VALUES (13, 5, 1, 8305, 1, 1, '2021-03-23 22:53:19', '2021-03-23 22:53:21');
INSERT INTO `room_info` VALUES (14, 1, 2, 8306, 1, 1, '2021-03-23 22:53:19', '2021-03-23 22:53:21');
INSERT INTO `room_info` VALUES (15, 2, 1, 8307, 1, 1, '2021-03-23 22:53:19', '2021-03-23 22:53:21');
INSERT INTO `room_info` VALUES (16, 1, 1, 8308, 1, 1, '2021-03-23 22:53:19', '2021-03-23 22:53:21');
INSERT INTO `room_info` VALUES (17, 6, 1, 8401, 1, 1, '2021-03-23 22:53:19', '2021-03-23 22:53:21');
INSERT INTO `room_info` VALUES (18, 7, 2, 8402, 1, 1, '2021-03-23 22:53:19', '2021-03-23 22:53:21');
INSERT INTO `room_info` VALUES (19, 5, 2, 8403, 1, 1, '2021-03-23 22:53:19', '2021-03-23 22:53:21');
INSERT INTO `room_info` VALUES (20, 6, 1, 8404, 1, 1, '2021-03-23 22:53:19', '2021-03-23 22:53:21');
INSERT INTO `room_info` VALUES (21, 2, 1, 8405, 1, 1, '2021-03-23 22:53:19', '2021-03-23 22:53:21');
INSERT INTO `room_info` VALUES (22, 2, 1, 8406, 1, 1, '2021-03-23 22:53:19', '2021-03-23 22:53:21');
INSERT INTO `room_info` VALUES (23, 1, 4, 8407, 1, 1, '2021-03-23 22:53:19', '2021-03-23 22:53:21');
INSERT INTO `room_info` VALUES (24, 3, 1, 8408, 1, 1, '2021-03-23 22:53:19', '2021-03-23 22:53:21');
INSERT INTO `room_info` VALUES (25, 4, 1, 8501, 1, 1, '2021-03-23 22:53:19', '2021-03-23 22:53:21');
INSERT INTO `room_info` VALUES (26, 5, 2, 8502, 1, 1, '2021-03-23 22:53:19', '2021-03-23 22:53:21');
INSERT INTO `room_info` VALUES (27, 1, 1, 8503, 1, 1, '2021-03-23 22:53:19', '2021-03-23 22:53:21');
INSERT INTO `room_info` VALUES (28, 1, 1, 8504, 1, 1, '2021-03-23 22:53:19', '2021-03-23 22:53:21');
INSERT INTO `room_info` VALUES (29, 2, 1, 8505, 1, 1, '2021-03-23 22:53:19', '2021-03-23 22:53:21');
INSERT INTO `room_info` VALUES (30, 3, 1, 8506, 1, 1, '2021-03-23 22:53:19', '2021-03-23 22:53:21');
INSERT INTO `room_info` VALUES (31, 1, 2, 8507, 1, 1, '2021-03-23 22:53:19', '2021-03-23 22:53:21');
INSERT INTO `room_info` VALUES (32, 1, 1, 8508, 1, 1, '2021-03-23 22:53:19', '2021-03-23 22:53:21');

-- ----------------------------
-- Table structure for room_type
-- ----------------------------
DROP TABLE IF EXISTS `room_type`;
CREATE TABLE `room_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '房间类型ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '房间类型名称，如豪华大床房',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '该类型房间的基础定价',
  `create_uid` int(11) NULL DEFAULT NULL COMMENT '创建者ID',
  `update_uid` int(11) NULL DEFAULT NULL COMMENT '修改者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of room_type
-- ----------------------------
INSERT INTO `room_type` VALUES (1, '豪华大床房', 128.00, 1, 1, '2021-03-23 22:46:54', '2021-03-23 22:46:57');
INSERT INTO `room_type` VALUES (2, '豪华双床房', 168.00, 1, 1, '2021-03-23 22:46:54', '2021-03-23 22:46:57');
INSERT INTO `room_type` VALUES (3, '舒适三人房', 228.00, 1, 1, '2021-03-23 22:46:54', '2021-03-23 22:46:57');
INSERT INTO `room_type` VALUES (4, '豪华圆床房', 136.00, 1, 1, '2021-03-23 22:46:54', '2021-03-23 22:46:57');
INSERT INTO `room_type` VALUES (5, '浪漫情侣房', 188.00, 1, 1, '2021-03-23 22:46:54', '2021-03-23 22:46:57');
INSERT INTO `room_type` VALUES (6, '豪华海景房', 268.00, 1, 1, '2021-03-23 22:46:54', '2021-03-23 22:46:57');
INSERT INTO `room_type` VALUES (7, '温馨家庭房', 183.00, 1, 1, '2021-03-23 22:46:54', '2021-03-23 22:46:57');

-- ----------------------------
-- Table structure for satff_salary
-- ----------------------------
DROP TABLE IF EXISTS `satff_salary`;
CREATE TABLE `satff_salary`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `s_id` int(11) NULL DEFAULT NULL COMMENT '员工ID',
  `salary_basic` decimal(10, 2) NULL DEFAULT NULL COMMENT '基本工资',
  `bonus` decimal(10, 2) NULL DEFAULT NULL COMMENT '全勤奖金',
  `fi_f` decimal(10, 2) NULL DEFAULT NULL COMMENT '五险一金(Five insurances and one fund),',
  `income_tax` decimal(10, 2) NULL DEFAULT NULL COMMENT '所得税',
  `salary_fact` decimal(10, 2) NULL DEFAULT NULL COMMENT '实发薪酬',
  `create_time` date NULL DEFAULT NULL COMMENT '薪酬数据，每月15号自动生成',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_salare_sid`(`s_id`) USING BTREE,
  CONSTRAINT `fk_salare_sid` FOREIGN KEY (`s_id`) REFERENCES `staff_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of satff_salary
-- ----------------------------

-- ----------------------------
-- Table structure for staff_info
-- ----------------------------
DROP TABLE IF EXISTS `staff_info`;
CREATE TABLE `staff_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '员工ID',
  `dep_id` int(11) NOT NULL COMMENT '部门ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '员工姓名',
  `identity` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '员工证件号码',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '员工邮箱，可不设置',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '员工登录密码',
  `contact` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '员工联系方式',
  `sex` smallint(1) NULL DEFAULT NULL COMMENT '性别',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态',
  `shift` smallint(1) NULL DEFAULT NULL COMMENT '上班的班次,0-白班,1-夜班',
  `create_uid` int(11) NULL DEFAULT NULL COMMENT '创建者ID',
  `update_uid` int(11) NULL DEFAULT NULL COMMENT '修改者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_staff_info_department_info_1`(`dep_id`) USING BTREE,
  CONSTRAINT `fk_staff_info_department_info_1` FOREIGN KEY (`dep_id`) REFERENCES `department_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10013 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of staff_info
-- ----------------------------
INSERT INTO `staff_info` VALUES (10000, 5, '邑大', '445100200001011000', '123456789@qq.com', '123456', '13612345678', 1, '1', 1, 1, 1, '2021-03-26 10:20:36', '2021-03-26 10:20:39');
INSERT INTO `staff_info` VALUES (10001, 2, '张一', '445100200001011231', '12345678900@qq.com', '123456', '13612345678', 0, '1', 0, 1, 1, '2021-03-24 18:48:20', '2021-03-24 18:48:22');
INSERT INTO `staff_info` VALUES (10002, 3, '张二2', '445100200001011232', '12345678900@qq.com', '123456', '13612345678', 1, '1', 1, 1, 1, '2021-03-24 18:48:20', '2021-03-24 18:48:22');
INSERT INTO `staff_info` VALUES (10003, 3, '张二', '445100200001011232', '12345678900@qq.com', '123456', '13612345678', 0, '1', 1, 1, 1, '2021-03-24 18:48:20', '2021-03-24 18:48:22');
INSERT INTO `staff_info` VALUES (10004, 4, '张四', '445100200001011234', '12345678900@qq.com', '123456', '13612345678', 1, '1', 0, 1, 1, '2021-03-24 18:48:20', '2021-03-24 18:48:22');
INSERT INTO `staff_info` VALUES (10005, 3, '张二', '445100200001011232', '12345678900@qq.com', '123456', '13612345678', 1, '1', 1, 1, 1, '2021-03-24 18:48:20', '2021-03-24 18:48:22');
INSERT INTO `staff_info` VALUES (10007, 4, '张六', '445100200001011236', '12345678900@qq.com', '123456', '13612345678', 1, '1', 0, 1, 1, '2021-03-24 18:48:20', '2021-03-24 18:48:22');
INSERT INTO `staff_info` VALUES (10008, 3, '张七', '445100200001011237', '12345678900@qq.com', '123456', '13612345678', 1, '1', 0, 1, 1, '2021-03-24 18:48:20', '2021-03-24 18:48:22');
INSERT INTO `staff_info` VALUES (10009, 2, '张八', '445100200001011238', '12345678900@qq.com', '123456', '13612345678', 1, '1', 1, 1, 1, '2021-03-24 18:48:20', '2021-03-24 18:48:22');
INSERT INTO `staff_info` VALUES (10010, 5, '张九', '445100200001011239', '12345678900@qq.com', '123456', '13612345678', 1, '1', 1, 1, 1, '2021-03-24 18:48:20', '2021-03-24 18:48:22');
INSERT INTO `staff_info` VALUES (10011, 2, '张十', '445100200001011240', '12345678900@qq.com', '123456', '13612345678', 1, '1', 1, 1, 1, '2021-03-24 18:48:20', '2021-03-24 18:48:22');
INSERT INTO `staff_info` VALUES (10012, 5, '张十一', '445100200001011241', '12345678900@qq.com', '123456', '13612345678', 0, '1', 0, 1, 1, '2021-03-24 18:48:20', '2021-03-24 18:48:22');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `permission` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, '修改', 'Update');
INSERT INTO `sys_permission` VALUES (2, '查询', 'Retrieve');
INSERT INTO `sys_permission` VALUES (3, '新增', 'Create');
INSERT INTO `sys_permission` VALUES (4, '删除', 'Delete');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `create_uid` int(11) NOT NULL COMMENT '创建者ID',
  `update_uid` int(11) NULL DEFAULT NULL COMMENT '修改者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `status` smallint(1) NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'super_admin', 1, 1, '2021-03-21 09:16:50', '2021-03-21 09:16:54', '超级管理员', 1);
INSERT INTO `sys_role` VALUES (2, 'admin', 1, 1, '2021-03-21 09:17:09', '2021-03-21 09:17:11', '普通管理员', 1);
INSERT INTO `sys_role` VALUES (3, 'guest', 1, 1, '2021-03-21 09:17:27', '2021-03-21 09:17:30', '用户', 1);

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_id` int(11) NULL DEFAULT NULL,
  `role_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES (1, 1, 1);
INSERT INTO `sys_role_permission` VALUES (2, 2, 1);
INSERT INTO `sys_role_permission` VALUES (3, 3, 1);
INSERT INTO `sys_role_permission` VALUES (4, 4, 1);
INSERT INTO `sys_role_permission` VALUES (5, 2, 2);
INSERT INTO `sys_role_permission` VALUES (6, 3, 2);
INSERT INTO `sys_role_permission` VALUES (7, 2, 3);

-- ----------------------------
-- Table structure for sys_staff_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_staff_role`;
CREATE TABLE `sys_staff_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NULL DEFAULT NULL COMMENT '员工ID',
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_staff`(`uid`) USING BTREE,
  INDEX `fk_role`(`role_id`) USING BTREE,
  CONSTRAINT `fk_role` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_staff` FOREIGN KEY (`uid`) REFERENCES `staff_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_staff_role
-- ----------------------------
INSERT INTO `sys_staff_role` VALUES (1, 10000, 1);
INSERT INTO `sys_staff_role` VALUES (2, 10001, 2);
INSERT INTO `sys_staff_role` VALUES (3, 10002, 3);
INSERT INTO `sys_staff_role` VALUES (4, 10000, 2);

-- ----------------------------
-- Table structure for sys_token
-- ----------------------------
DROP TABLE IF EXISTS `sys_token`;
CREATE TABLE `sys_token`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'token',
  `s_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `expires_time` datetime(0) NULL DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `staff_token`(`s_id`) USING BTREE,
  CONSTRAINT `staff_token` FOREIGN KEY (`s_id`) REFERENCES `staff_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_token
-- ----------------------------
INSERT INTO `sys_token` VALUES (9, '5648b5c728d92442857b933c2cf48f72', 10000, '2021-03-27 11:49:20', '2021-03-28 11:49:20');
INSERT INTO `sys_token` VALUES (10, '59a941d505c36e646a4dd3c515e0da0a', 10001, '2021-03-27 09:47:11', '2021-03-28 09:47:11');

SET FOREIGN_KEY_CHECKS = 1;
