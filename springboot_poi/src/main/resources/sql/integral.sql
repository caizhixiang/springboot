/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : springboot

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2017-10-16 15:21:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for integral
-- ----------------------------
DROP TABLE IF EXISTS `integral`;
CREATE TABLE `integral` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `serialNum` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `orderNo` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `integral` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `orderTime` datetime DEFAULT NULL,
  `orderStatus` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `payStatus` varchar(255) DEFAULT NULL,
  `sendStatus` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `getAccount` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `faceValue` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `goodsName` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `calcPrice` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `quantity` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of integral
-- ----------------------------
INSERT INTO `integral` VALUES ('1', '1', '1070387363093847547', '29900', '2016-06-04 00:51:13', '下单成功', '1', '发货成功', '155000000', '20', '话费充值20元', '21.34', '1');
INSERT INTO `integral` VALUES ('2', '2', '1070460789770744247', '300', '2017-09-12 11:57:33', '下单成功', '1', '发货成功', '155000001', '50', '话费充值50元', '51.34', '1');
INSERT INTO `integral` VALUES ('3', '3', '1070782399354679923', '29900', '2018-06-04 00:51:13', '下单成功', '1', '发货成功', '155000002', '100', '话费充值100元', '101.34', '1');
INSERT INTO `integral` VALUES ('4', '1', '1070387363093847547', '29900', '2016-06-04 00:51:13', '下单成功', '1', '发货成功', '155000000', '20', '话费充值20元', '21.34', '1');
INSERT INTO `integral` VALUES ('5', '2', '1070460789770744247', '300', '2017-09-12 11:57:33', '下单成功', '1', '发货成功', '155000001', '50', '话费充值50元', '51.34', '1');
INSERT INTO `integral` VALUES ('6', '3', '1070782399354679923', '29900', '2018-06-04 00:51:13', '下单成功', '1', '发货成功', '155000002', '100', '话费充值100元', '101.34', '1');
INSERT INTO `integral` VALUES ('7', '1', '1070387363093847547', '29900', '2016-06-04 00:51:13', '下单成功', '1', '发货成功', '155000000', '20', '话费充值20元', '21.34', '1');
INSERT INTO `integral` VALUES ('8', '2', '1070460789770744247', '300', '2017-09-12 11:57:33', '下单成功', '1', '发货成功', '155000001', '50', '话费充值50元', '51.34', '1');
INSERT INTO `integral` VALUES ('9', '3', '1070782399354679923', '29900', '2018-06-04 00:51:13', '下单成功', '1', '发货成功', '155000002', '100', '话费充值100元', '101.34', '1');
INSERT INTO `integral` VALUES ('10', '1', '1070387363093847547', '29900', '2016-06-04 00:51:13', '下单成功', '1', '发货成功', '155000000', '20', '话费充值20元', '21.34', '1');
INSERT INTO `integral` VALUES ('11', '2', '1070460789770744247', '300', '2017-09-12 11:57:33', '下单成功', '1', '发货成功', '155000001', '50', '话费充值50元', '51.34', '1');
INSERT INTO `integral` VALUES ('12', '3', '1070782399354679923', '29900', '2018-06-04 00:51:13', '下单成功', '1', '发货成功', '155000002', '100', '话费充值100元', '101.34', '1');
INSERT INTO `integral` VALUES ('13', '1', '1070387363093847547', '29900', '2016-06-04 00:51:13', '下单成功', '1', '发货成功', '155000000', '20', '话费充值20元', '21.34', '1');
INSERT INTO `integral` VALUES ('14', '2', '1070460789770744247', '300', '2017-09-12 11:57:33', '下单成功', '1', '发货成功', '155000001', '50', '话费充值50元', '51.34', '1');
INSERT INTO `integral` VALUES ('15', '3', '1070782399354679923', '29900', '2018-06-04 00:51:13', '下单成功', '1', '发货成功', '155000002', '100', '话费充值100元', '101.34', '1');
