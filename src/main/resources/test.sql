/*
Navicat MySQL Data Transfer

Source Server         : my
Source Server Version : 50548
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50548
File Encoding         : 65001

Date: 2018-05-08 18:15:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for alert
-- ----------------------------
DROP TABLE IF EXISTS `alert`;
CREATE TABLE `alert` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `user` varchar(20) DEFAULT NULL,
  `message` varchar(1000) DEFAULT NULL,
  `state` int(10) DEFAULT '0',
  `date` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of alert
-- ----------------------------
INSERT INTO `alert` VALUES ('1', 'admin', '远古', '10', '2017-02-11 11:09:04');
INSERT INTO `alert` VALUES ('2', 'admin', '消息1', '0', '2018-02-12 12:36:15');
INSERT INTO `alert` VALUES ('3', 'admin', '消息1', '5', '2018-02-12 12:36:18');
INSERT INTO `alert` VALUES ('4', 'admin', '消息2', '5', '2018-02-12 12:36:22');
INSERT INTO `alert` VALUES ('5', 'admin', '消息3', '10', '2018-02-12 12:36:27');
INSERT INTO `alert` VALUES ('6', 'admin', '发送消息', '5', '2018-02-12 12:45:17');
INSERT INTO `alert` VALUES ('7', 'admin', '奉送', '0', '2018-02-12 03:27:25');
INSERT INTO `alert` VALUES ('8', 'admin', '哈哈', '0', '2018-02-12 03:53:36');
INSERT INTO `alert` VALUES ('9', 'admin', '哈哈', '0', '2018-02-12 03:53:49');
INSERT INTO `alert` VALUES ('10', 'admin', '哈哈', '5', '2018-02-12 03:53:54');
INSERT INTO `alert` VALUES ('11', 'admin', '外网', '0', '2018-02-12 03:55:06');
INSERT INTO `alert` VALUES ('29', 'admin', '123', '0', '2018-05-08 17:26:38');
INSERT INTO `alert` VALUES ('30', 'admin', '123', '0', '2018-05-08 17:26:39');
INSERT INTO `alert` VALUES ('31', 'admin', '123', '0', '2018-05-08 17:26:39');
INSERT INTO `alert` VALUES ('32', 'admin', '123', '0', '2018-05-08 17:26:40');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10015 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('10001', '粤菜');
INSERT INTO `category` VALUES ('10002', '湘菜');
INSERT INTO `category` VALUES ('10003', '鲁菜');
INSERT INTO `category` VALUES ('10004', '川菜');
INSERT INTO `category` VALUES ('10005', '苏菜');
INSERT INTO `category` VALUES ('10006', '闽菜');
INSERT INTO `category` VALUES ('10007', '徽菜');
INSERT INTO `category` VALUES ('10008', '浙菜');
INSERT INTO `category` VALUES ('10009', '汤');
INSERT INTO `category` VALUES ('10010', '小吃');
INSERT INTO `category` VALUES ('10011', '饮料');
INSERT INTO `category` VALUES ('10012', '配料');
INSERT INTO `category` VALUES ('10013', '海鲜');
INSERT INTO `category` VALUES ('10014', '粥');

-- ----------------------------
-- Table structure for communicate
-- ----------------------------
DROP TABLE IF EXISTS `communicate`;
CREATE TABLE `communicate` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `fromUser` varchar(50) DEFAULT NULL,
  `toUser` varchar(50) DEFAULT NULL,
  `message` varchar(500) DEFAULT NULL,
  `sendTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of communicate
-- ----------------------------
INSERT INTO `communicate` VALUES ('1', null, null, '消息', null);

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int(15) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `table_id` int(15) DEFAULT NULL COMMENT '下单座位id',
  `total` int(15) DEFAULT NULL COMMENT '订单总额',
  `status` varchar(20) DEFAULT NULL COMMENT '订单状态',
  `remark` varchar(200) DEFAULT NULL,
  `createTime` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_order` (`table_id`),
  CONSTRAINT `FK_order` FOREIGN KEY (`table_id`) REFERENCES `table_r` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=1000081 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('1000017', '10', '344', '已退款', '不要加辣椒', '2018-04-07 22:46:07');
INSERT INTO `order` VALUES ('1000019', '10', '3128', '已完成', '辣椒加多点', '2018-04-07 23:39:38');
INSERT INTO `order` VALUES ('1000020', null, '636', '已完成', '', '2018-04-07 23:40:40');
INSERT INTO `order` VALUES ('1000021', '12', '68', '已完成', '', '2018-04-07 23:43:12');
INSERT INTO `order` VALUES ('1000022', '7', '156', '已完成', '', '2018-04-07 23:49:45');
INSERT INTO `order` VALUES ('1000024', '1', '68', '已完成', '', '2018-04-08 21:57:10');
INSERT INTO `order` VALUES ('1000030', null, '480', '已完成', '', '2018-04-09 21:56:53');
INSERT INTO `order` VALUES ('1000031', '7', '306', '已完成', '', '2018-04-09 22:00:03');
INSERT INTO `order` VALUES ('1000033', '7', '240', '已完成', '', '2018-04-10 20:34:48');
INSERT INTO `order` VALUES ('1000035', null, '156', '已完成', '', '2018-04-10 22:17:00');
INSERT INTO `order` VALUES ('1000036', '8', '446', '已完成', '', '2018-04-10 23:11:13');
INSERT INTO `order` VALUES ('1000037', '7', '1054', '已完成', '', '2018-04-10 23:30:50');
INSERT INTO `order` VALUES ('1000038', null, '432', '已取消', '', '2018-04-21 15:17:11');
INSERT INTO `order` VALUES ('1000039', '6', '608', '已提交', '', '2018-04-21 20:23:36');
INSERT INTO `order` VALUES ('1000040', '1', '46', '已提交', '', '2018-04-30 15:34:10');
INSERT INTO `order` VALUES ('1000041', '2', '68', '已提交', '', '2018-04-30 15:56:32');
INSERT INTO `order` VALUES ('1000042', '2', '68', '已提交', '', '2018-04-30 15:56:38');
INSERT INTO `order` VALUES ('1000043', null, '268', '已完成', '', '2018-04-30 15:59:08');
INSERT INTO `order` VALUES ('1000044', null, '568', '已完成', '油不要放太多', '2018-04-30 16:11:31');
INSERT INTO `order` VALUES ('1000045', '2', '344', '已提交', '', '2018-04-30 16:16:43');
INSERT INTO `order` VALUES ('1000046', '2', '904', '已提交', '', '2018-04-30 16:17:03');
INSERT INTO `order` VALUES ('1000047', '6', '672', '已提交', '', '2018-04-30 16:18:28');
INSERT INTO `order` VALUES ('1000048', '2', '224', '已收费', '', '2018-04-30 16:22:45');
INSERT INTO `order` VALUES ('1000049', null, '136', '已收费', '', '2018-04-30 18:48:15');
INSERT INTO `order` VALUES ('1000050', null, '312', '已完成', '', '2018-05-01 21:31:56');
INSERT INTO `order` VALUES ('1000051', null, '156', '已提交', '', '2018-05-01 21:33:51');
INSERT INTO `order` VALUES ('1000052', null, '344', '已提交', '', '2018-05-01 21:42:24');
INSERT INTO `order` VALUES ('1000053', null, '204', '已收费', '', '2018-05-01 21:43:47');
INSERT INTO `order` VALUES ('1000054', '1', '156', '已完成', '', '2018-05-04 23:16:06');
INSERT INTO `order` VALUES ('1000055', '1', '156', '已收费', '', '2018-05-04 23:26:39');
INSERT INTO `order` VALUES ('1000056', '1', '156', '已收费', '', '2018-05-04 23:28:57');
INSERT INTO `order` VALUES ('1000057', '1', '532', '已完成', '', '2018-05-05 14:21:46');
INSERT INTO `order` VALUES ('1000058', '8', '92', '已提交', '', '2018-05-06 00:23:54');
INSERT INTO `order` VALUES ('1000059', '6', '324', '已提交', '尽快，赶时间', '2018-05-06 09:35:06');
INSERT INTO `order` VALUES ('1000060', '7', '68', '已提交', '', '2018-05-06 19:24:35');
INSERT INTO `order` VALUES ('1000061', '6', '96', '已提交', '', '2018-05-06 23:40:19');
INSERT INTO `order` VALUES ('1000062', '1', '344', '已提交', '', '2018-05-06 23:58:23');
INSERT INTO `order` VALUES ('1000063', '1', '156', '已提交', '', '2018-05-08 00:04:26');
INSERT INTO `order` VALUES ('1000064', '1', '156', '已提交', '', '2018-05-08 11:04:50');
INSERT INTO `order` VALUES ('1000065', '1', '68', '已提交', '', '2018-05-08 11:07:46');
INSERT INTO `order` VALUES ('1000066', '2', '156', '已提交', '', '2018-05-08 11:26:59');
INSERT INTO `order` VALUES ('1000067', '1', '156', '已提交', '', '2018-05-08 11:34:10');
INSERT INTO `order` VALUES ('1000068', '6', '68', '已提交', '', '2018-05-08 12:40:41');
INSERT INTO `order` VALUES ('1000069', '7', '68', '已提交', '', '2018-05-08 12:54:52');
INSERT INTO `order` VALUES ('1000070', '8', '68', '已提交', '', '2018-05-08 12:55:32');
INSERT INTO `order` VALUES ('1000071', '14', '156', '已提交', '', '2018-05-08 12:55:53');
INSERT INTO `order` VALUES ('1000072', '1', '68', '已提交', '', '2018-05-08 13:32:19');
INSERT INTO `order` VALUES ('1000073', '6', '68', '已提交', '', '2018-05-08 14:17:28');
INSERT INTO `order` VALUES ('1000074', '6', '294', '已提交', '', '2018-05-08 14:19:29');
INSERT INTO `order` VALUES ('1000075', '8', '68', '已提交', '', '2018-05-08 14:19:46');
INSERT INTO `order` VALUES ('1000076', '2', '68', '已提交', '', '2018-05-08 14:21:32');
INSERT INTO `order` VALUES ('1000077', '6', '46', '已提交', '', '2018-05-08 14:57:55');
INSERT INTO `order` VALUES ('1000078', '6', '156', '已提交', '', '2018-05-08 15:14:59');
INSERT INTO `order` VALUES ('1000079', '2', '68', '已提交', '', '2018-05-08 16:34:01');
INSERT INTO `order` VALUES ('1000080', '7', '68', '已提交', '', '2018-05-08 18:12:32');

-- ----------------------------
-- Table structure for orderdetail
-- ----------------------------
DROP TABLE IF EXISTS `orderdetail`;
CREATE TABLE `orderdetail` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `order_id` int(15) DEFAULT NULL,
  `product_id` int(15) DEFAULT NULL,
  `count` int(4) DEFAULT NULL,
  `flag` varchar(4) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_orderdetail` (`order_id`),
  CONSTRAINT `FK_orderdetail` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=174 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of orderdetail
-- ----------------------------
INSERT INTO `orderdetail` VALUES ('9', '1000017', '1000', '1', '1');
INSERT INTO `orderdetail` VALUES ('10', '1000017', '1001', '1', '1');
INSERT INTO `orderdetail` VALUES ('11', '1000017', '1002', '1', '1');
INSERT INTO `orderdetail` VALUES ('18', '1000019', '1000', '3', '1');
INSERT INTO `orderdetail` VALUES ('19', '1000019', '1001', '2', '0');
INSERT INTO `orderdetail` VALUES ('20', '1000019', '1002', '10', '1');
INSERT INTO `orderdetail` VALUES ('21', '1000019', '1003', '5', '0');
INSERT INTO `orderdetail` VALUES ('22', '1000019', '1004', '5', '1');
INSERT INTO `orderdetail` VALUES ('23', '1000019', '1020', '1', '1');
INSERT INTO `orderdetail` VALUES ('24', '1000019', '1021', '1', '1');
INSERT INTO `orderdetail` VALUES ('25', '1000019', '1022', '1', '0');
INSERT INTO `orderdetail` VALUES ('26', '1000020', '1000', '1', '0');
INSERT INTO `orderdetail` VALUES ('27', '1000020', '1001', '1', '0');
INSERT INTO `orderdetail` VALUES ('28', '1000020', '1002', '1', '0');
INSERT INTO `orderdetail` VALUES ('29', '1000020', '1003', '1', '0');
INSERT INTO `orderdetail` VALUES ('30', '1000020', '1004', '1', '0');
INSERT INTO `orderdetail` VALUES ('31', '1000020', '1013', '1', '0');
INSERT INTO `orderdetail` VALUES ('32', '1000020', '1014', '1', '0');
INSERT INTO `orderdetail` VALUES ('33', '1000020', '1008', '1', '0');
INSERT INTO `orderdetail` VALUES ('34', '1000020', '1009', '1', '0');
INSERT INTO `orderdetail` VALUES ('35', '1000020', '1010', '1', '0');
INSERT INTO `orderdetail` VALUES ('36', '1000020', '1023', '1', '0');
INSERT INTO `orderdetail` VALUES ('37', '1000021', '1000', '1', '0');
INSERT INTO `orderdetail` VALUES ('38', '1000022', '1000', '1', '0');
INSERT INTO `orderdetail` VALUES ('39', '1000022', '1001', '1', '0');
INSERT INTO `orderdetail` VALUES ('40', '1000024', '1000', '1', '0');
INSERT INTO `orderdetail` VALUES ('45', '1000030', '1000', '1', '0');
INSERT INTO `orderdetail` VALUES ('46', '1000030', '1001', '1', '0');
INSERT INTO `orderdetail` VALUES ('47', '1000030', '1002', '1', '0');
INSERT INTO `orderdetail` VALUES ('48', '1000030', '1003', '1', '0');
INSERT INTO `orderdetail` VALUES ('49', '1000030', '1004', '1', '0');
INSERT INTO `orderdetail` VALUES ('50', '1000031', '1000', '1', '0');
INSERT INTO `orderdetail` VALUES ('51', '1000031', '1001', '1', '0');
INSERT INTO `orderdetail` VALUES ('52', '1000031', '1004', '1', '0');
INSERT INTO `orderdetail` VALUES ('53', '1000031', '1021', '1', '0');
INSERT INTO `orderdetail` VALUES ('54', '1000031', '1022', '1', '0');
INSERT INTO `orderdetail` VALUES ('61', '1000033', '1010', '5', '0');
INSERT INTO `orderdetail` VALUES ('62', '1000033', '1023', '5', '0');
INSERT INTO `orderdetail` VALUES ('69', '1000035', '1000', '1', '0');
INSERT INTO `orderdetail` VALUES ('70', '1000035', '1001', '1', '0');
INSERT INTO `orderdetail` VALUES ('71', '1000036', '1000', '1', '0');
INSERT INTO `orderdetail` VALUES ('72', '1000036', '1001', '1', '0');
INSERT INTO `orderdetail` VALUES ('73', '1000036', '1002', '1', '0');
INSERT INTO `orderdetail` VALUES ('74', '1000036', '1007', '1', '0');
INSERT INTO `orderdetail` VALUES ('75', '1000036', '1008', '1', '0');
INSERT INTO `orderdetail` VALUES ('76', '1000036', '1009', '1', '0');
INSERT INTO `orderdetail` VALUES ('77', '1000036', '1010', '1', '0');
INSERT INTO `orderdetail` VALUES ('78', '1000037', '1000', '1', '0');
INSERT INTO `orderdetail` VALUES ('79', '1000037', '1001', '1', '0');
INSERT INTO `orderdetail` VALUES ('80', '1000037', '1002', '2', '0');
INSERT INTO `orderdetail` VALUES ('81', '1000037', '1003', '2', '0');
INSERT INTO `orderdetail` VALUES ('82', '1000037', '1004', '2', '0');
INSERT INTO `orderdetail` VALUES ('83', '1000037', '1016', '1', '0');
INSERT INTO `orderdetail` VALUES ('84', '1000037', '1017', '1', '0');
INSERT INTO `orderdetail` VALUES ('85', '1000037', '1013', '1', '0');
INSERT INTO `orderdetail` VALUES ('86', '1000037', '1008', '2', '0');
INSERT INTO `orderdetail` VALUES ('87', '1000037', '1009', '1', '0');
INSERT INTO `orderdetail` VALUES ('88', '1000037', '1010', '1', '0');
INSERT INTO `orderdetail` VALUES ('89', '1000038', '1000', '1', '0');
INSERT INTO `orderdetail` VALUES ('90', '1000038', '1001', '1', '0');
INSERT INTO `orderdetail` VALUES ('91', '1000038', '1002', '1', '0');
INSERT INTO `orderdetail` VALUES ('92', '1000038', '1003', '1', '0');
INSERT INTO `orderdetail` VALUES ('93', '1000039', '1000', '1', '0');
INSERT INTO `orderdetail` VALUES ('94', '1000039', '1001', '1', '0');
INSERT INTO `orderdetail` VALUES ('95', '1000039', '1002', '1', '0');
INSERT INTO `orderdetail` VALUES ('96', '1000039', '1003', '3', '0');
INSERT INTO `orderdetail` VALUES ('97', '1000040', '1015', '1', '0');
INSERT INTO `orderdetail` VALUES ('98', '1000041', '1000', '1', '0');
INSERT INTO `orderdetail` VALUES ('99', '1000042', '1000', '1', '0');
INSERT INTO `orderdetail` VALUES ('100', '1000043', '1004', '2', '0');
INSERT INTO `orderdetail` VALUES ('101', '1000043', '1020', '2', '0');
INSERT INTO `orderdetail` VALUES ('102', '1000044', '1000', '3', '0');
INSERT INTO `orderdetail` VALUES ('103', '1000044', '1001', '1', '0');
INSERT INTO `orderdetail` VALUES ('104', '1000044', '1002', '1', '0');
INSERT INTO `orderdetail` VALUES ('105', '1000044', '1003', '1', '0');
INSERT INTO `orderdetail` VALUES ('106', '1000045', '1000', '1', '0');
INSERT INTO `orderdetail` VALUES ('107', '1000045', '1001', '1', '0');
INSERT INTO `orderdetail` VALUES ('108', '1000045', '1002', '1', '0');
INSERT INTO `orderdetail` VALUES ('109', '1000046', '1000', '5', '0');
INSERT INTO `orderdetail` VALUES ('110', '1000046', '1002', '3', '0');
INSERT INTO `orderdetail` VALUES ('111', '1000047', '1000', '3', '0');
INSERT INTO `orderdetail` VALUES ('112', '1000047', '1001', '3', '0');
INSERT INTO `orderdetail` VALUES ('113', '1000047', '1016', '2', '0');
INSERT INTO `orderdetail` VALUES ('114', '1000047', '1017', '2', '0');
INSERT INTO `orderdetail` VALUES ('115', '1000048', '1000', '2', '0');
INSERT INTO `orderdetail` VALUES ('116', '1000048', '1001', '1', '0');
INSERT INTO `orderdetail` VALUES ('117', '1000049', '1000', '2', '0');
INSERT INTO `orderdetail` VALUES ('118', '1000050', '1000', '2', '0');
INSERT INTO `orderdetail` VALUES ('119', '1000050', '1001', '1', '0');
INSERT INTO `orderdetail` VALUES ('120', '1000050', '1003', '1', '0');
INSERT INTO `orderdetail` VALUES ('121', '1000051', '1000', '1', '0');
INSERT INTO `orderdetail` VALUES ('122', '1000051', '1001', '1', '0');
INSERT INTO `orderdetail` VALUES ('123', '1000052', '1000', '1', '0');
INSERT INTO `orderdetail` VALUES ('124', '1000052', '1001', '1', '0');
INSERT INTO `orderdetail` VALUES ('125', '1000052', '1002', '1', '0');
INSERT INTO `orderdetail` VALUES ('126', '1000053', '1000', '3', '0');
INSERT INTO `orderdetail` VALUES ('127', '1000054', '1000', '1', '0');
INSERT INTO `orderdetail` VALUES ('128', '1000054', '1001', '1', '0');
INSERT INTO `orderdetail` VALUES ('129', '1000055', '1000', '1', '0');
INSERT INTO `orderdetail` VALUES ('130', '1000055', '1001', '1', '0');
INSERT INTO `orderdetail` VALUES ('131', '1000056', '1000', '1', '1');
INSERT INTO `orderdetail` VALUES ('132', '1000056', '1001', '1', '1');
INSERT INTO `orderdetail` VALUES ('133', '1000057', '1000', '1', '0');
INSERT INTO `orderdetail` VALUES ('134', '1000057', '1001', '1', '0');
INSERT INTO `orderdetail` VALUES ('135', '1000057', '1002', '2', '0');
INSERT INTO `orderdetail` VALUES ('136', '1000058', '1015', '1', '0');
INSERT INTO `orderdetail` VALUES ('137', '1000058', '1016', '1', '0');
INSERT INTO `orderdetail` VALUES ('138', '1000059', '1002', '1', '0');
INSERT INTO `orderdetail` VALUES ('139', '1000059', '1003', '1', '0');
INSERT INTO `orderdetail` VALUES ('140', '1000059', '1004', '1', '0');
INSERT INTO `orderdetail` VALUES ('141', '1000060', '1000', '1', '0');
INSERT INTO `orderdetail` VALUES ('142', '1000061', '1004', '2', '0');
INSERT INTO `orderdetail` VALUES ('143', '1000062', '1000', '1', '0');
INSERT INTO `orderdetail` VALUES ('144', '1000062', '1001', '1', '0');
INSERT INTO `orderdetail` VALUES ('145', '1000062', '1002', '1', '0');
INSERT INTO `orderdetail` VALUES ('146', '1000063', '1000', '1', '0');
INSERT INTO `orderdetail` VALUES ('147', '1000063', '1001', '1', '0');
INSERT INTO `orderdetail` VALUES ('148', '1000064', '1000', '1', '0');
INSERT INTO `orderdetail` VALUES ('149', '1000064', '1001', '1', '0');
INSERT INTO `orderdetail` VALUES ('150', '1000065', '1000', '1', '0');
INSERT INTO `orderdetail` VALUES ('151', '1000066', '1000', '1', '0');
INSERT INTO `orderdetail` VALUES ('152', '1000066', '1001', '1', '0');
INSERT INTO `orderdetail` VALUES ('153', '1000067', '1000', '1', '0');
INSERT INTO `orderdetail` VALUES ('154', '1000067', '1001', '1', '0');
INSERT INTO `orderdetail` VALUES ('155', '1000068', '1000', '1', '0');
INSERT INTO `orderdetail` VALUES ('156', '1000069', '1000', '1', '0');
INSERT INTO `orderdetail` VALUES ('157', '1000070', '1000', '1', '0');
INSERT INTO `orderdetail` VALUES ('158', '1000071', '1000', '1', '0');
INSERT INTO `orderdetail` VALUES ('159', '1000071', '1001', '1', '0');
INSERT INTO `orderdetail` VALUES ('160', '1000072', '1000', '1', '0');
INSERT INTO `orderdetail` VALUES ('161', '1000073', '1000', '1', '0');
INSERT INTO `orderdetail` VALUES ('162', '1000074', '1000', '2', '0');
INSERT INTO `orderdetail` VALUES ('163', '1000074', '1020', '1', '0');
INSERT INTO `orderdetail` VALUES ('164', '1000074', '1021', '1', '0');
INSERT INTO `orderdetail` VALUES ('165', '1000074', '1016', '1', '0');
INSERT INTO `orderdetail` VALUES ('166', '1000075', '1000', '1', '0');
INSERT INTO `orderdetail` VALUES ('167', '1000076', '1000', '1', '0');
INSERT INTO `orderdetail` VALUES ('168', '1000077', '1007', '1', '0');
INSERT INTO `orderdetail` VALUES ('169', '1000077', '1008', '1', '0');
INSERT INTO `orderdetail` VALUES ('170', '1000078', '1000', '1', '0');
INSERT INTO `orderdetail` VALUES ('171', '1000078', '1001', '1', '0');
INSERT INTO `orderdetail` VALUES ('172', '1000079', '1000', '1', '0');
INSERT INTO `orderdetail` VALUES ('173', '1000080', '1000', '1', '0');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `cid` int(20) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `imagePath` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_product` (`cid`),
  CONSTRAINT `FK_product` FOREIGN KEY (`cid`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1024 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1000', '广州文昌鸡', '10001', '68', 'image/1000.jpg');
INSERT INTO `product` VALUES ('1001', '明炉烤乳猪', '10001', '88', 'image/1001.jpg');
INSERT INTO `product` VALUES ('1002', '新龙皇夜宴', '10001', '188', 'image/1002.jpg');
INSERT INTO `product` VALUES ('1003', '清蒸东星斑', '10001', '88', 'image/1003.jpg');
INSERT INTO `product` VALUES ('1004', '潮汕卤味', '10001', '48', 'image/1004.jpg');
INSERT INTO `product` VALUES ('1007', '山药豆腐汤', '10009', '28', 'image/1007.jpg');
INSERT INTO `product` VALUES ('1008', '冬瓜金针菇汤', '10009', '18', 'image/1008.jpg');
INSERT INTO `product` VALUES ('1009', '五彩酸辣汤', '10009', '18', 'image/1009.jpg');
INSERT INTO `product` VALUES ('1010', '莲藕排骨汤', '10009', '38', 'image/1010.jpg');
INSERT INTO `product` VALUES ('1012', '蟹子豆腐', '10005', '66', 'image/1012.jpg');
INSERT INTO `product` VALUES ('1013', '炖鳝酥', '10005', '56', 'image/1013.jpg');
INSERT INTO `product` VALUES ('1014', '扬州炒饭', '10005', '16', 'image/1014.jpg');
INSERT INTO `product` VALUES ('1015', '水煮肉', '10004', '46', 'image/1015.jpg');
INSERT INTO `product` VALUES ('1016', '鱼香肉丝', '10004', '46', 'image/1016.jpg');
INSERT INTO `product` VALUES ('1017', '酸菜鱼', '10004', '56', 'image/1017.jpg');
INSERT INTO `product` VALUES ('1018', '麻婆豆腐', '10004', '66', 'image/1018.jpg');
INSERT INTO `product` VALUES ('1019', '辣鱼片', '10004', '66', 'image/1019.jpg');
INSERT INTO `product` VALUES ('1020', '剁椒鱼头', '10002', '86', 'image/1020.jpg');
INSERT INTO `product` VALUES ('1021', '家常豆腐', '10002', '26', 'image/1021.jpg');
INSERT INTO `product` VALUES ('1022', '魔都湘味鸡', '10002', '76', 'image/1022.jpg');
INSERT INTO `product` VALUES ('1023', '清汤', '10009', '10', 'image/1023.jpg');

-- ----------------------------
-- Table structure for ptype
-- ----------------------------
DROP TABLE IF EXISTS `ptype`;
CREATE TABLE `ptype` (
  `id` int(15) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 MIN_ROWS=1 MAX_ROWS=9999 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of ptype
-- ----------------------------
INSERT INTO `ptype` VALUES ('3', '服务员');
INSERT INTO `ptype` VALUES ('5', '厨师');
INSERT INTO `ptype` VALUES ('6', '系统管理员');

-- ----------------------------
-- Table structure for seat
-- ----------------------------
DROP TABLE IF EXISTS `seat`;
CREATE TABLE `seat` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `table_id` int(11) NOT NULL,
  `seat_name` varchar(20) NOT NULL,
  `charge_state` varchar(20) DEFAULT '未收费',
  PRIMARY KEY (`id`),
  KEY `seat_table` (`table_id`),
  CONSTRAINT `seat_table` FOREIGN KEY (`table_id`) REFERENCES `table_r` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seat
-- ----------------------------

-- ----------------------------
-- Table structure for table_r
-- ----------------------------
DROP TABLE IF EXISTS `table_r`;
CREATE TABLE `table_r` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `table_name` varchar(30) DEFAULT NULL,
  `table_capacity` int(3) DEFAULT '4',
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of table_r
-- ----------------------------
INSERT INTO `table_r` VALUES ('1', '1号', '4', '用餐中');
INSERT INTO `table_r` VALUES ('2', '2号', '4', '用餐中');
INSERT INTO `table_r` VALUES ('6', '3号', '12', '用餐中');
INSERT INTO `table_r` VALUES ('7', '4号', '12', '空闲');
INSERT INTO `table_r` VALUES ('8', '5号', '12', '空闲');
INSERT INTO `table_r` VALUES ('9', '6号', '6', '空闲');
INSERT INTO `table_r` VALUES ('10', '7号', '6', '空闲');
INSERT INTO `table_r` VALUES ('11', '8号', '6', '空闲');
INSERT INTO `table_r` VALUES ('12', '9号', '6', '空闲');
INSERT INTO `table_r` VALUES ('14', '10号', '12', '已预定');
INSERT INTO `table_r` VALUES ('15', '11号', '12', '已预定');
INSERT INTO `table_r` VALUES ('16', '12号', '12', '已预定');
INSERT INTO `table_r` VALUES ('17', '13号', '12', '已预定');
INSERT INTO `table_r` VALUES ('18', '14号', '12', '已预定');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `tel` varchar(50) DEFAULT NULL COMMENT '电话',
  `isLogin` varchar(2) DEFAULT NULL COMMENT '是否登录',
  `login_times` int(10) DEFAULT NULL COMMENT '登录次数',
  `lastest_login_time` varchar(30) DEFAULT NULL COMMENT '最近登录时间',
  `isStaff` char(4) DEFAULT NULL COMMENT '是否员工',
  `Ptype_id` int(15) DEFAULT NULL COMMENT '人员类型',
  `canLogin` int(4) DEFAULT NULL COMMENT '是否离职',
  PRIMARY KEY (`id`),
  KEY `fk_ptype` (`Ptype_id`),
  CONSTRAINT `fk_ptype` FOREIGN KEY (`Ptype_id`) REFERENCES `ptype` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10011 DEFAULT CHARSET=utf8 MIN_ROWS=1 MAX_ROWS=4294967295 AVG_ROW_LENGTH=1 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('10001', 'admin', '123', '广东省梅州市梅江区嘉应学院', '18814383273', 'Y', '182', '2018-05-08 18:09:18', 'Y', '6', '1');
INSERT INTO `user` VALUES ('10010', '测试人员', '123', '天河', '18814383273', 'Y', '2', '2018-05-06 15:54:40', null, '5', '0');

-- ----------------------------
-- Table structure for user_log
-- ----------------------------
DROP TABLE IF EXISTS `user_log`;
CREATE TABLE `user_log` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(20) NOT NULL,
  `remote_ip` varchar(30) DEFAULT NULL,
  `alter_data` varchar(200) DEFAULT NULL COMMENT '数据修改',
  PRIMARY KEY (`id`),
  KEY `user_FK` (`user_id`),
  CONSTRAINT `user_FK` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_log
-- ----------------------------
