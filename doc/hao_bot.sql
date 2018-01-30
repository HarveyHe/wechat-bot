-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.21 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.1.0.4867
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 hao_bot 的数据库结构
DROP DATABASE IF EXISTS `hao_bot`;
CREATE DATABASE IF NOT EXISTS `hao_bot` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `hao_bot`;


-- 导出  表 hao_bot.bot_extract 结构
DROP TABLE IF EXISTS `bot_extract`;
CREATE TABLE IF NOT EXISTS `bot_extract` (
  `extract_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `creator` int(11) DEFAULT NULL COMMENT '创建人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modifier` int(11) DEFAULT NULL COMMENT '修改人',
  `points` decimal(10,4) DEFAULT NULL COMMENT '数量',
  `to_user_name` varchar(200) DEFAULT NULL COMMENT '微信会员',
  `user_name` varchar(200) DEFAULT NULL COMMENT '微信会员名称',
  `status` int(11) DEFAULT NULL COMMENT '审核状态：0待审核，1审核通过，-1审核不通过',
  PRIMARY KEY (`extract_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='提取记录';

-- 正在导出表  hao_bot.bot_extract 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `bot_extract` DISABLE KEYS */;
/*!40000 ALTER TABLE `bot_extract` ENABLE KEYS */;


-- 导出  表 hao_bot.bot_integral 结构
DROP TABLE IF EXISTS `bot_integral`;
CREATE TABLE IF NOT EXISTS `bot_integral` (
  `uintegral_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `to_user_name` varchar(200) DEFAULT NULL COMMENT '微信会员id',
  `user_name` varchar(200) DEFAULT NULL COMMENT '会员名称',
  `remaining_points` decimal(10,4) DEFAULT NULL COMMENT '剩余积分',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `creator` int(11) DEFAULT NULL COMMENT '创建人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modifier` int(11) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`uintegral_id`),
  UNIQUE KEY `to_user_name` (`to_user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='积分';

-- 正在导出表  hao_bot.bot_integral 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `bot_integral` DISABLE KEYS */;
/*!40000 ALTER TABLE `bot_integral` ENABLE KEYS */;


-- 导出  表 hao_bot.bot_order 结构
DROP TABLE IF EXISTS `bot_order`;
CREATE TABLE IF NOT EXISTS `bot_order` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `creator` int(11) DEFAULT NULL COMMENT '创建人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modifier` int(11) DEFAULT NULL COMMENT '修改人',
  `points` decimal(10,4) DEFAULT NULL COMMENT '下注数量',
  `to_user_name` varchar(200) DEFAULT NULL COMMENT '微信会员',
  `user_name` varchar(200) DEFAULT NULL COMMENT '微信会员名称',
  `status` int(11) DEFAULT NULL COMMENT '审核状态：0下单，1已结算，-1取消',
  `record` int(11) DEFAULT NULL COMMENT '下注（共十注）',
  `playing_no` varchar(50) DEFAULT NULL COMMENT '下注期数',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='下注记录';

-- 正在导出表  hao_bot.bot_order 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `bot_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `bot_order` ENABLE KEYS */;


-- 导出  表 hao_bot.bot_recharge 结构
DROP TABLE IF EXISTS `bot_recharge`;
CREATE TABLE IF NOT EXISTS `bot_recharge` (
  `recharge_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `points` decimal(10,4) DEFAULT NULL COMMENT '充值数量',
  `status` int(11) DEFAULT NULL COMMENT '审核状态：0待审核，1审核通过，-1审核不通过',
  `to_user_name` varchar(200) DEFAULT NULL COMMENT '微信会员id',
  `user_name` varchar(200) DEFAULT NULL COMMENT '微信会员名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `creator` int(11) DEFAULT NULL COMMENT '创建人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modifier` int(11) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`recharge_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='充分记录';

-- 正在导出表  hao_bot.bot_recharge 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `bot_recharge` DISABLE KEYS */;
/*!40000 ALTER TABLE `bot_recharge` ENABLE KEYS */;


-- 导出  表 hao_bot.bot_user 结构
DROP TABLE IF EXISTS `bot_user`;
CREATE TABLE IF NOT EXISTS `bot_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户标识',
  `user_account` varchar(100) NOT NULL COMMENT '登录账号',
  `user_name` varchar(100) DEFAULT NULL COMMENT '用户名称',
  `password` varchar(255) NOT NULL COMMENT '登录密码',
  `user_type` tinyint(4) NOT NULL COMMENT '用户类型,0 系统管理用户',
  `user_status` tinyint(4) NOT NULL COMMENT '用户状态 0正常,-1已删除,1禁用,只有0的状态才能正常登录',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `creator` int(11) DEFAULT NULL COMMENT '创建人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modifier` int(11) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `idx_user_account_user_type` (`user_account`,`user_type`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='管理用户表';

-- 正在导出表  hao_bot.bot_user 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `bot_user` DISABLE KEYS */;
INSERT INTO `bot_user` (`user_id`, `user_account`, `user_name`, `password`, `user_type`, `user_status`, `create_time`, `creator`, `modify_time`, `modifier`) VALUES
	(1, 'admin', 'admin', 'ce1692154e6093e8aecb095029d5d37f348655a34a283b13a709a0a118b9a90e727662aee18f263c', 1, 1, '2018-01-30 10:21:43', NULL, '2018-01-30 10:21:43', NULL);
/*!40000 ALTER TABLE `bot_user` ENABLE KEYS */;


-- 导出  表 hao_bot.playing_records 结构
DROP TABLE IF EXISTS `playing_records`;
CREATE TABLE IF NOT EXISTS `playing_records` (
  `playing_records_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `creator` int(11) DEFAULT NULL COMMENT '创建人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modifier` int(11) DEFAULT NULL COMMENT '修改人',
  `playing_no` varchar(200) DEFAULT NULL COMMENT '牌局期数',
  `record1` int(11) DEFAULT NULL COMMENT '注1',
  `record2` int(11) DEFAULT NULL COMMENT '注2',
  `record3` int(11) DEFAULT NULL COMMENT '注3',
  `record4` int(11) DEFAULT NULL COMMENT '注4',
  `record5` int(11) DEFAULT NULL COMMENT '注5',
  `record6` int(11) DEFAULT NULL COMMENT '注6',
  `record7` int(11) DEFAULT NULL COMMENT '注7',
  `record8` int(11) DEFAULT NULL COMMENT '注8',
  `record9` int(11) DEFAULT NULL COMMENT '注9',
  `record10` int(11) DEFAULT NULL COMMENT '注10',
  PRIMARY KEY (`playing_records_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='牌局记录';

-- 正在导出表  hao_bot.playing_records 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `playing_records` DISABLE KEYS */;
/*!40000 ALTER TABLE `playing_records` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
