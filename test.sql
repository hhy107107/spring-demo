/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-11-26 21:51:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `file`
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `path` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '文件路径',
  `name` varchar(100) CHARACTER SET utf8 NOT NULL COMMENT '文件名',
  `suffix` varchar(20) NOT NULL COMMENT '文件后缀',
  `description` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '文件描述',
  `upload_time` datetime NOT NULL COMMENT '上传时间',
  `last_time` datetime NOT NULL COMMENT '文件最后一次使用时间',
  `use_count` int(11) NOT NULL COMMENT '文件请求次数',
  `authority` bigint(20) NOT NULL COMMENT '文件所有者（0-公共  >0 私有[私有者编号]）',
  PRIMARY KEY (`id`),
  KEY `authorityid` (`authority`),
  CONSTRAINT `authorityid` FOREIGN KEY (`authority`) REFERENCES `user_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='文件';

-- ----------------------------
-- Records of file
-- ----------------------------

-- ----------------------------
-- Table structure for `message`
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` bigint(20) NOT NULL,
  `user_to` bigint(20) NOT NULL,
  `user_from` bigint(20) NOT NULL,
  `type` tinyint(4) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `send_time` datetime NOT NULL,
  `status` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------

-- ----------------------------
-- Table structure for `note`
-- ----------------------------
DROP TABLE IF EXISTS `note`;
CREATE TABLE `note` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `type_id` bigint(20) unsigned NOT NULL COMMENT '类型id',
  `author` varchar(255) NOT NULL COMMENT '作者名称',
  `user_id` bigint(20) NOT NULL COMMENT '作者id',
  `content` varchar(255) DEFAULT NULL COMMENT '内容',
  `title` varchar(255) NOT NULL COMMENT '标题',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `heart` int(11) DEFAULT NULL COMMENT '收获了多少喜欢',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态 1-正常 2-草稿 3-回收站',
  PRIMARY KEY (`id`),
  KEY `fk_note_type` (`type_id`),
  KEY `fk_note_uid` (`user_id`),
  CONSTRAINT `fk_note_type` FOREIGN KEY (`type_id`) REFERENCES `note_type` (`id`),
  CONSTRAINT `fk_note_uid` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='笔记';

-- ----------------------------
-- Records of note
-- ----------------------------

-- ----------------------------
-- Table structure for `note_type`
-- ----------------------------
DROP TABLE IF EXISTS `note_type`;
CREATE TABLE `note_type` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `name` varchar(255) NOT NULL COMMENT '类型名称',
  `description` varchar(255) DEFAULT NULL COMMENT '类型描述',
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_notetype_uid` (`user_id`),
  CONSTRAINT `fk_notetype_uid` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='笔记类型';

-- ----------------------------
-- Records of note_type
-- ----------------------------
INSERT INTO `note_type` VALUES ('1', 'ds', null, '3');
INSERT INTO `note_type` VALUES ('2', '', null, '3');
INSERT INTO `note_type` VALUES ('3', '小黄笔记', null, '3');

-- ----------------------------
-- Table structure for `user_info`
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `name` varchar(255) NOT NULL COMMENT '用户名字',
  `usertype` varchar(2) DEFAULT NULL COMMENT '用户类型',
  `enabled` int(2) DEFAULT NULL COMMENT '是否可用',
  `realname` varchar(32) DEFAULT NULL COMMENT '真实姓名',
  `qq` varchar(14) DEFAULT NULL COMMENT 'QQ',
  `email` varchar(100) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL COMMENT '联系电话',
  PRIMARY KEY (`id`),
  KEY `id` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('0', 'service', 'aaaa', '', '1', null, null, null, null, null);
INSERT INTO `user_info` VALUES ('3', 'test3', 'bbbb', '', '1', null, null, null, null, null);
INSERT INTO `user_info` VALUES ('4', 'test4', 'cccc', '', '2', null, null, null, null, null);
INSERT INTO `user_info` VALUES ('5', 'test5', 'dddd', '', '1', null, null, null, null, null);
