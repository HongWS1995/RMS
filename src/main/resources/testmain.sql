/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.5.48 : Database - test
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`test` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `test`;

/*Table structure for table `alert` */

DROP TABLE IF EXISTS `alert`;

CREATE TABLE `alert` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `user` varchar(20) DEFAULT NULL,
  `message` varchar(1000) DEFAULT NULL,
  `state` int(10) DEFAULT '0',
  `date` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `alert` */

insert  into `alert`(`id`,`user`,`message`,`state`,`date`) values (1,'admin','远古',10,'2017-02-11 11:09:04'),(2,'admin','消息1',0,'2018-02-12 12:36:15'),(3,'admin','消息1',5,'2018-02-12 12:36:18'),(4,'admin','消息2',5,'2018-02-12 12:36:22'),(5,'admin','消息3',10,'2018-02-12 12:36:27'),(6,'admin','发送消息',5,'2018-02-12 12:45:17'),(7,'admin','奉送',0,'2018-02-12 03:27:25'),(8,'admin','哈哈',0,'2018-02-12 03:53:36'),(9,'admin','哈哈',0,'2018-02-12 03:53:49'),(10,'admin','哈哈',5,'2018-02-12 03:53:54'),(11,'admin','外网',0,'2018-02-12 03:55:06'),(12,'admin','uusee',0,'2018-02-12 04:39:43');

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Data for the table `category` */

insert  into `category`(`id`,`name`) values (1,'粤菜');

/*Table structure for table `communicate` */

DROP TABLE IF EXISTS `communicate`;

CREATE TABLE `communicate` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `fromUser` varchar(50) DEFAULT NULL,
  `toUser` varchar(50) DEFAULT NULL,
  `message` varchar(500) DEFAULT NULL,
  `sendTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Data for the table `communicate` */

insert  into `communicate`(`id`,`fromUser`,`toUser`,`message`,`sendTime`) values (1,NULL,NULL,'消息',NULL);

/*Table structure for table `order` */

DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
  `id` int(15) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `seat_id` int(15) DEFAULT NULL COMMENT '下单座位id',
  `total` int(15) DEFAULT NULL COMMENT '订单总额',
  `status` varchar(20) DEFAULT NULL COMMENT '订单状态',
  PRIMARY KEY (`id`),
  KEY `FK_order` (`seat_id`),
  CONSTRAINT `FK_order` FOREIGN KEY (`seat_id`) REFERENCES `seat` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `order` */

/*Table structure for table `orderdetail` */

DROP TABLE IF EXISTS `orderdetail`;

CREATE TABLE `orderdetail` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `order_id` int(15) DEFAULT NULL,
  `product_id` int(15) DEFAULT NULL,
  `count` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_orderdetail` (`order_id`),
  CONSTRAINT `FK_orderdetail` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Data for the table `orderdetail` */

/*Table structure for table `product` */

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Data for the table `product` */

/*Table structure for table `seat` */

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

/*Data for the table `seat` */

/*Table structure for table `table_r` */

DROP TABLE IF EXISTS `table_r`;

CREATE TABLE `table_r` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `table_name` varchar(30) DEFAULT NULL,
  `table_capacity` int(3) DEFAULT '4',
  `status` char(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `table_r` */

insert  into `table_r`(`id`,`table_name`,`table_capacity`,`status`) values (1,'A',4,NULL);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) DEFAULT NULL,
  `isLogin` varchar(2) DEFAULT NULL COMMENT '是否登录',
  `login_times` int(10) DEFAULT NULL COMMENT '登录次数',
  `lastest_login_time` varchar(30) DEFAULT NULL COMMENT '最近登录时间',
  `isStaff` char(4) DEFAULT NULL COMMENT '是否员工',
  `Ptype_id` int(15) DEFAULT NULL COMMENT '人员类型',
  `canLogin` int(4) DEFAULT NULL COMMENT '是否离职',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 MIN_ROWS=1 MAX_ROWS=4294967295 AVG_ROW_LENGTH=1 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`isLogin`,`login_times`,`lastest_login_time`,`isStaff`,`Ptype_id`,`canLogin`) values (1,'zhangsan','123456','Y',4,'2018-03-30 15:49:34','N',3,1),(2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(4,'用户21','123456','Y',1,'2018-03-18 15:24:00','N',3,1),(5,'用户22','123456',NULL,1,'2018-03-29 00:37:06','N',3,1),(6,'用户23','123456',NULL,1,'2018-03-29 00:38:09','N',3,1),(7,'用户null','123456',NULL,1,'2018-03-30 15:49:34','N',3,1),(8,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(9,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(10,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(11,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(12,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(13,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(14,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `user_log` */

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

/*Data for the table `user_log` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
