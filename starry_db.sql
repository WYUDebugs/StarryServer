/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.5.51 : Database - starry_db
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`starry_db` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `starry_db`;

/*Table structure for table `friend_tab` */

DROP TABLE IF EXISTS `friend_tab`;

CREATE TABLE `friend_tab` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '好友表id',
  `user_id` int(11) NOT NULL,
  `friend_id` int(11) NOT NULL,
  `friend_name` varchar(15) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `user_id` (`user_id`),
  KEY `friend_id` (`friend_id`),
  CONSTRAINT `friend_tab_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_tab` (`id`),
  CONSTRAINT `friend_tab_ibfk_2` FOREIGN KEY (`friend_id`) REFERENCES `user_tab` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `friend_tab` */

/*Table structure for table `interaction_tab` */

DROP TABLE IF EXISTS `interaction_tab`;

CREATE TABLE `interaction_tab` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '互动记录表id',
  `my_id` int(11) NOT NULL COMMENT '我的id',
  `his_id` int(11) NOT NULL COMMENT '好友id',
  `first_add_time` datetime DEFAULT NULL COMMENT '加好友时间',
  `first_chat_time` datetime DEFAULT NULL,
  `first_good_time` datetime DEFAULT NULL,
  `first_comment_time` datetime DEFAULT NULL,
  `lately_chat_time` datetime DEFAULT NULL,
  `lately_good_time` datetime DEFAULT NULL,
  `lately_comment_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `interaction_tab` */

/*Table structure for table `memory_book_tab` */

DROP TABLE IF EXISTS `memory_book_tab`;

CREATE TABLE `memory_book_tab` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '纪念册表id',
  `owner` int(11) NOT NULL COMMENT '纪念册创建者id',
  `title` varchar(15) DEFAULT NULL COMMENT '标题',
  `cover` varchar(100) DEFAULT NULL COMMENT '封面',
  `creat_time` datetime DEFAULT NULL COMMENT '创建时间',
  `friend_count` int(11) DEFAULT '0' COMMENT '共同编辑好友数',
  `moment_count` int(11) DEFAULT '0' COMMENT '片段数目',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `owner` (`owner`),
  CONSTRAINT `memory_book_tab_ibfk_1` FOREIGN KEY (`owner`) REFERENCES `user_tab` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `memory_book_tab` */

/*Table structure for table `memory_cover_tab` */

DROP TABLE IF EXISTS `memory_cover_tab`;

CREATE TABLE `memory_cover_tab` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '纪念册默认封面表id',
  `path` varchar(100) DEFAULT NULL COMMENT '默认封面路径',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `memory_cover_tab` */

/*Table structure for table `memory_friend_tab` */

DROP TABLE IF EXISTS `memory_friend_tab`;

CREATE TABLE `memory_friend_tab` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '纪念册好友编辑表id',
  `friend_id` int(11) NOT NULL COMMENT '好友id',
  `memory_book_id` int(11) NOT NULL COMMENT '纪念册id',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `friend_id` (`friend_id`),
  KEY `memory_book_id` (`memory_book_id`),
  CONSTRAINT `memory_friend_tab_ibfk_1` FOREIGN KEY (`friend_id`) REFERENCES `user_tab` (`id`),
  CONSTRAINT `memory_friend_tab_ibfk_2` FOREIGN KEY (`memory_book_id`) REFERENCES `memory_book_tab` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `memory_friend_tab` */

/*Table structure for table `moment_comment_tab` */

DROP TABLE IF EXISTS `moment_comment_tab`;

CREATE TABLE `moment_comment_tab` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '片段评论表id',
  `commentator` int(11) NOT NULL COMMENT '评论者id',
  `moment_id` int(11) NOT NULL COMMENT '片段表id',
  `comment_time` datetime DEFAULT NULL COMMENT '评论时间',
  `comment_content` text COMMENT '评论内容',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `commentator` (`commentator`),
  KEY `moment_id` (`moment_id`),
  CONSTRAINT `moment_comment_tab_ibfk_2` FOREIGN KEY (`moment_id`) REFERENCES `moment_tab` (`id`),
  CONSTRAINT `moment_comment_tab_ibfk_1` FOREIGN KEY (`commentator`) REFERENCES `user_tab` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `moment_comment_tab` */

/*Table structure for table `moment_image_tab` */

DROP TABLE IF EXISTS `moment_image_tab`;

CREATE TABLE `moment_image_tab` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '片段图片表id',
  `moment_id` int(11) NOT NULL COMMENT '片段表id',
  `path` varchar(100) DEFAULT NULL COMMENT '图片路径',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `moment_id` (`moment_id`),
  CONSTRAINT `moment_image_tab_ibfk_1` FOREIGN KEY (`moment_id`) REFERENCES `moment_tab` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `moment_image_tab` */

/*Table structure for table `moment_tab` */

DROP TABLE IF EXISTS `moment_tab`;

CREATE TABLE `moment_tab` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '纪念册片段表id',
  `sender` int(11) NOT NULL COMMENT '发布者id',
  `send_time` datetime DEFAULT NULL COMMENT '发布时间',
  `location_time` datetime DEFAULT NULL COMMENT '事件定位时间',
  `content` text COMMENT '内容',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `sender` (`sender`),
  CONSTRAINT `moment_tab_ibfk_1` FOREIGN KEY (`sender`) REFERENCES `user_tab` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `moment_tab` */

/*Table structure for table `public_comment_tab` */

DROP TABLE IF EXISTS `public_comment_tab`;

CREATE TABLE `public_comment_tab` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '动态评论表id',
  `commentator` int(11) NOT NULL COMMENT '评论者id',
  `public_id` int(11) NOT NULL COMMENT '动态表id',
  `comment_time` datetime DEFAULT NULL COMMENT '评论时间',
  `comment_content` text COMMENT '评论内容',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `commentator` (`commentator`),
  KEY `public_id` (`public_id`),
  CONSTRAINT `public_comment_tab_ibfk_1` FOREIGN KEY (`commentator`) REFERENCES `user_tab` (`id`),
  CONSTRAINT `public_comment_tab_ibfk_2` FOREIGN KEY (`public_id`) REFERENCES `public_tab` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `public_comment_tab` */

/*Table structure for table `public_concern_tab` */

DROP TABLE IF EXISTS `public_concern_tab`;

CREATE TABLE `public_concern_tab` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '动态关注表id',
  `concern_people` int(11) NOT NULL COMMENT '关注收藏着id',
  `concern_public` int(11) NOT NULL COMMENT '动态表id',
  `concern_time` datetime DEFAULT NULL COMMENT '收藏时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `concern_people` (`concern_people`),
  KEY `concern_public` (`concern_public`),
  CONSTRAINT `public_concern_tab_ibfk_1` FOREIGN KEY (`concern_people`) REFERENCES `user_tab` (`id`),
  CONSTRAINT `public_concern_tab_ibfk_2` FOREIGN KEY (`concern_public`) REFERENCES `public_tab` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `public_concern_tab` */

/*Table structure for table `public_good_tab` */

DROP TABLE IF EXISTS `public_good_tab`;

CREATE TABLE `public_good_tab` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '动态点赞表id',
  `man_of_praise` int(11) NOT NULL,
  `time_of_praise` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `man_of_praise` (`man_of_praise`),
  CONSTRAINT `public_good_tab_ibfk_1` FOREIGN KEY (`man_of_praise`) REFERENCES `user_tab` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `public_good_tab` */

/*Table structure for table `public_image_tab` */

DROP TABLE IF EXISTS `public_image_tab`;

CREATE TABLE `public_image_tab` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '动态图片列表id',
  `public_id` int(11) NOT NULL COMMENT '动态表id',
  `path` varchar(100) DEFAULT NULL COMMENT '图片路径',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `public_id` (`public_id`),
  CONSTRAINT `public_image_tab_ibfk_1` FOREIGN KEY (`public_id`) REFERENCES `public_tab` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `public_image_tab` */

/*Table structure for table `public_tab` */

DROP TABLE IF EXISTS `public_tab`;

CREATE TABLE `public_tab` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '动态表id',
  `publisher` int(11) NOT NULL COMMENT '动态发布者id',
  `time` datetime DEFAULT NULL COMMENT '发布时间',
  `content` text COMMENT '内容',
  `times_of_browse` int(11) DEFAULT NULL COMMENT '浏览数',
  `times_of_praise` int(11) DEFAULT NULL COMMENT '点赞数',
  `number_of_comments` int(11) DEFAULT NULL COMMENT '评论数',
  `collection_number` int(11) DEFAULT NULL COMMENT '关注收藏数',
  `address` varchar(100) DEFAULT NULL COMMENT '定位地址',
  `type` int(11) DEFAULT '0' COMMENT '动态类型，默认为0发布的动态，1分享过来的动态',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `publisher` (`publisher`),
  CONSTRAINT `public_tab_ibfk_1` FOREIGN KEY (`publisher`) REFERENCES `user_tab` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `public_tab` */

/*Table structure for table `user_tab` */

DROP TABLE IF EXISTS `user_tab`;

CREATE TABLE `user_tab` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `phone` varchar(15) NOT NULL,
  `password` varchar(70) NOT NULL,
  `name` varchar(15) DEFAULT NULL,
  `age` int(11) DEFAULT '0',
  `gender` int(11) DEFAULT '0',
  `birthday` date DEFAULT NULL,
  `signature` text,
  `headimage` varchar(100) DEFAULT NULL,
  `typelabel` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `phoneId` varchar(100) DEFAULT NULL,
  `register_time` datetime DEFAULT NULL COMMENT '注册时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `user_tab` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
