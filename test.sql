/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50625
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2017-12-02 19:27:23
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
  `is_mark_down` tinyint(4) NOT NULL COMMENT '1-morkdown 模式  2-普通模式',
  `zan_num` int(11) DEFAULT NULL COMMENT '赞的数量',
  PRIMARY KEY (`id`),
  KEY `fk_note_type` (`type_id`),
  KEY `fk_note_uid` (`user_id`),
  CONSTRAINT `fk_note_type` FOREIGN KEY (`type_id`) REFERENCES `note_type` (`id`),
  CONSTRAINT `fk_note_uid` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='笔记';

-- ----------------------------
-- Records of note
-- ----------------------------
INSERT INTO `note` VALUES ('1', '3', '用户3', '3', '内容啊', '标题', '2017-11-27 10:19:38', null, null, null, '1', null);
INSERT INTO `note` VALUES ('3', '5', '客服', '0', null, '标题', '2017-12-01 15:34:10', null, null, null, '1', null);
INSERT INTO `note` VALUES ('5', '5', '客服', '0', null, '12月的第一天', '2017-12-01 16:47:24', null, null, null, '1', null);
INSERT INTO `note` VALUES ('14', '4', '小黄人', '3', null, '标题fsdaef', '2017-12-01 17:14:54', null, null, null, '1', null);
INSERT INTO `note` VALUES ('15', '4', '小黄人', '3', null, '标题fsdaef', '2017-12-01 17:17:58', null, null, null, '1', null);
INSERT INTO `note` VALUES ('16', '4', '小黄人', '3', null, '标题', '2017-12-01 17:45:39', null, null, null, '1', null);
INSERT INTO `note` VALUES ('17', '4', '小黄人', '3', '### hello', '标题', '2017-12-01 17:47:07', null, null, null, '2', null);
INSERT INTO `note` VALUES ('18', '3', '小黄人', '3', '### hello', '标题', '2017-12-02 10:18:21', null, null, null, '1', null);

-- ----------------------------
-- Table structure for `note_grade`
-- ----------------------------
DROP TABLE IF EXISTS `note_grade`;
CREATE TABLE `note_grade` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '写作等级名称',
  `grade` tinyint(4) NOT NULL COMMENT '写作等级',
  `num` int(11) NOT NULL COMMENT '笔记数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='写作等级\r\n\r\n';

-- ----------------------------
-- Records of note_grade
-- ----------------------------
INSERT INTO `note_grade` VALUES ('1', '写作新手', '1', '1');
INSERT INTO `note_grade` VALUES ('2', '写作达人', '2', '20');
INSERT INTO `note_grade` VALUES ('3', '写作大师', '3', '200');
INSERT INTO `note_grade` VALUES ('4', '写作王者', '4', '500');

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='笔记类型';

-- ----------------------------
-- Records of note_type
-- ----------------------------
INSERT INTO `note_type` VALUES ('3', '小黄笔记', null, '3');
INSERT INTO `note_type` VALUES ('4', '随笔', null, '3');
INSERT INTO `note_type` VALUES ('5', '服务日志', null, '0');

-- ----------------------------
-- Table structure for `note_user_grade`
-- ----------------------------
DROP TABLE IF EXISTS `note_user_grade`;
CREATE TABLE `note_user_grade` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户id',
  `grade_id` bigint(20) unsigned NOT NULL COMMENT '等级id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户写笔记的等级';

-- ----------------------------
-- Records of note_user_grade
-- ----------------------------
INSERT INTO `note_user_grade` VALUES ('1', '3', '1');

-- ----------------------------
-- Table structure for `note_user_zan`
-- ----------------------------
DROP TABLE IF EXISTS `note_user_zan`;
CREATE TABLE `note_user_zan` (
  `id` bigint(20) NOT NULL COMMENT '自增id',
  `note_id` bigint(20) NOT NULL COMMENT '笔记id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `zan` tinyint(4) NOT NULL COMMENT '是否赞 1-赞 2-没赞'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='笔记用户赞的情况';

-- ----------------------------
-- Records of note_user_zan
-- ----------------------------

-- ----------------------------
-- Table structure for `user_info`
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `username` varchar(32) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `name` varchar(255) NOT NULL COMMENT '用户名字',
  `usertype` varchar(2) DEFAULT NULL COMMENT '用户类型 1-普通用户 2-管理员',
  `enabled` int(2) DEFAULT NULL COMMENT '是否可用',
  `qq` varchar(14) DEFAULT NULL COMMENT 'QQ',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `tel` varchar(255) DEFAULT NULL COMMENT '联系电话',
  `sex` tinyint(4) DEFAULT NULL COMMENT '性别 1-男 2-女',
  `signature` varchar(255) DEFAULT NULL COMMENT '个性签名',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `birthday` date DEFAULT NULL,
  `reason` tinyint(4) NOT NULL DEFAULT '1' COMMENT '来到小黄平台的原因 1-崇拜小黄 2-崇拜统子 3-想了解bjhj 4-被小黄无形之中的气质所吸引',
  `userface` varchar(255) DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`id`),
  KEY `id` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('0', 'service', 'aaaa', '客服', '1', null, null, null, null, null, null, null, null, '1', null);
INSERT INTO `user_info` VALUES ('3', 'test3', 'bbbb', '小黄人', '1', null, null, null, null, '1', '小黄是世界上最聪明的小黄，没有之一', '浙江杭州', '1995-01-07', '1', 'test\\2017-12-01\\3f2af929-0555-41bd-b4c6-2ca68f0ca390.jpg');
INSERT INTO `user_info` VALUES ('4', 'test4', 'cccc', '用户4', '2', null, null, null, null, null, null, null, null, '1', null);
INSERT INTO `user_info` VALUES ('5', 'test5', 'dddd', '用户5', '1', null, null, null, null, null, null, null, null, '1', null);
