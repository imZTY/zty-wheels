/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50539
Source Host           : 127.0.0.1:3306
Source Database       : zty_wheel

Target Server Type    : MYSQL
Target Server Version : 50539
File Encoding         : 65001

Date: 2020-05-13 23:51:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for file_info
-- ----------------------------
DROP TABLE IF EXISTS `file_info`;
CREATE TABLE `file_info` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) COLLATE utf8_unicode_ci NOT NULL COMMENT '图片原名称',
  `file_kind` smallint(6) DEFAULT NULL COMMENT '文件类别',
  `public_url` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `private_url` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `download_time` int(12) DEFAULT '0',
  `like_time` int(12) DEFAULT '0',
  `create_by` int(12) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for role_info
-- ----------------------------
DROP TABLE IF EXISTS `role_info`;
CREATE TABLE `role_info` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `rights` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_by` int(12) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_user_create` (`create_by`) USING BTREE COMMENT '快速查获某用户创建的角色'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '账号昵称',
  `phone` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `head_pic` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '头像图片url',
  `sex` tinyint(4) DEFAULT '0' COMMENT '性别，0=未知，1=男性，2=女性',
  `email` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Email',
  `province` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户省份',
  `city` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户城市',
  `country` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '微信国家代号，如中国为CN',
  `account_kind` tinyint(4) NOT NULL DEFAULT '1' COMMENT '账号类别（1=平台账号，2=微信账号）',
  `openid` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '微信用户唯一标识',
  `role_id` int(8) DEFAULT NULL COMMENT '角色id，权限信息由角色进行区分',
  `age` float(4,0) DEFAULT NULL COMMENT '用户年龄',
  `job` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户职业',
  `organization` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户所在公司/组织',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNI_account` (`phone`,`openid`) USING BTREE,
  KEY `IDX_phone_exist` (`phone`) USING BTREE COMMENT '快速检查手机号是否已存在',
  KEY `IDX_login_check` (`phone`,`password`) USING BTREE COMMENT '快速登录校验',
  KEY `IDX_wxid` (`openid`) USING BTREE COMMENT '根据微信openid快速查找用户'
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
