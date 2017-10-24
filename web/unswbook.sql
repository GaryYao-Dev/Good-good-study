CREATE DATABASE  IF NOT EXISTS `unswbook` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `unswbook`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: unswbook
-- ------------------------------------------------------
-- Server version	5.7.19-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `adminID` int(11) NOT NULL AUTO_INCREMENT,
  `adminName` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`adminID`),
  UNIQUE KEY `adminID_UNIQUE` (`adminID`),
  UNIQUE KEY `adminName_UNIQUE` (`adminName`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'gary','123');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friendship`
--

DROP TABLE IF EXISTS `friendship`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `friendship` (
  `f_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `friend_id` int(11) NOT NULL,
  `confirm` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`f_id`),
  UNIQUE KEY `friendship_f_id_uindex` (`f_id`),
  KEY `friendship_users_userid_fk` (`user_id`),
  KEY `friendship_users_userid_fk2` (`friend_id`),
  CONSTRAINT `friendship_users_userid_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`userid`),
  CONSTRAINT `friendship_users_userid_fk2` FOREIGN KEY (`friend_id`) REFERENCES `users` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friendship`
--

LOCK TABLES `friendship` WRITE;
/*!40000 ALTER TABLE `friendship` DISABLE KEYS */;
INSERT INTO `friendship` VALUES (9,1,2,1),(11,3,2,1),(12,1,5,1),(13,3,6,1);
/*!40000 ALTER TABLE `friendship` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `likelist`
--

DROP TABLE IF EXISTS `likelist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `likelist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `p_id` int(11) NOT NULL,
  `likeUser_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `likelist_post_p_id_fk` (`p_id`),
  CONSTRAINT `likelist_post_p_id_fk` FOREIGN KEY (`p_id`) REFERENCES `post` (`p_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23314 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `likelist`
--

LOCK TABLES `likelist` WRITE;
/*!40000 ALTER TABLE `likelist` DISABLE KEYS */;
INSERT INTO `likelist` VALUES (23265,23432473,1),(23271,23432476,1),(23272,23432475,1),(23277,23432478,1),(23278,23432481,1),(23279,23432491,2),(23280,23432490,2),(23281,23432488,2),(23282,23432487,2),(23283,23432485,2),(23284,23432484,2),(23286,23432492,1),(23288,23432487,1),(23290,23432489,1),(23291,23432486,1),(23292,23432485,1),(23293,23432484,1),(23296,23432488,1),(23301,23432493,1),(23311,23432499,1),(23312,23432496,1),(23313,23432506,9);
/*!40000 ALTER TABLE `likelist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log`
--

DROP TABLE IF EXISTS `log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log` (
  `logID` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) NOT NULL,
  `activity` varchar(300) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`logID`),
  UNIQUE KEY `logID_UNIQUE` (`logID`),
  KEY `userID_idx` (`userID`),
  CONSTRAINT `userID_log` FOREIGN KEY (`userID`) REFERENCES `users` (`userid`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1220 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log`
--

LOCK TABLES `log` WRITE;
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
INSERT INTO `log` VALUES (1045,1,'Banned','2017-09-29 23:20:01'),(1046,2,'unbanned','2017-09-29 23:20:05'),(1047,3,'unbanned','2017-09-29 23:21:11'),(1048,1,'unbanned','2017-09-29 23:21:13'),(1049,1,'Banned','2017-09-30 08:06:03'),(1050,1,'Banned','2017-09-30 08:06:08'),(1051,2,'Banned','2017-09-30 08:06:41'),(1052,2,'Unbanned','2017-09-30 08:06:43'),(1053,2,'Unbanned','2017-09-30 08:06:45'),(1054,2,'Banned','2017-09-30 08:06:47'),(1055,2,'Unbanned','2017-09-30 08:06:48'),(1056,2,'Banned','2017-09-30 08:06:50'),(1057,2,'Unbanned','2017-09-30 08:06:51'),(1058,2,'Banned','2017-09-30 08:08:05'),(1059,2,'Unbanned','2017-09-30 08:08:07'),(1060,2,'Banned','2017-09-30 08:09:05'),(1061,2,'Unbanned','2017-09-30 08:09:06'),(1062,2,'Banned','2017-09-30 08:09:08'),(1063,2,'Unbanned','2017-09-30 08:09:10'),(1064,3,'Banned','2017-09-30 08:09:36'),(1065,3,'Unbanned','2017-09-30 08:10:10'),(1066,3,'Banned','2017-09-30 08:10:12'),(1067,3,'Unbanned','2017-09-30 08:10:14'),(1068,2,'Banned','2017-09-30 08:10:16'),(1069,2,'Unbanned','2017-09-30 08:10:18'),(1070,2,'Banned','2017-09-30 08:13:25'),(1071,2,'Banned','2017-09-30 08:13:27'),(1072,2,'Unbanned','2017-09-30 08:13:29'),(1073,2,'Banned','2017-09-30 08:13:31'),(1074,2,'Unbanned','2017-09-30 08:17:07'),(1075,2,'Banned','2017-09-30 08:18:23'),(1076,2,'Unbanned','2017-09-30 08:18:24'),(1077,5,'Banned','2017-09-30 08:18:34'),(1078,5,'Unbanned','2017-09-30 08:18:37'),(1079,5,'Banned','2017-09-30 08:18:40'),(1080,5,'Unbanned','2017-09-30 08:18:42'),(1081,2,'Banned','2017-09-30 08:18:44'),(1082,2,'Unbanned','2017-09-30 08:18:46'),(1083,3,'Banned','2017-09-30 08:18:48'),(1084,3,'Unbanned','2017-09-30 08:18:49'),(1085,4,'Banned','2017-09-30 11:05:17'),(1086,2,'Banned','2017-09-30 11:05:25'),(1087,1,'Unbanned','2017-09-30 11:05:43'),(1088,1,'Banned','2017-09-30 11:05:47'),(1089,5,'Banned','2017-09-30 11:26:59'),(1090,7,'Banned','2017-09-30 11:56:58'),(1091,5,'Unbanned','2017-09-30 11:57:00'),(1092,5,'Banned','2017-09-30 11:57:03'),(1093,5,'Unbanned','2017-09-30 11:57:04'),(1094,2,'Unbanned','2017-09-30 12:08:35'),(1095,4,'Unbanned','2017-09-30 12:08:39'),(1096,2,'Banned','2017-09-30 12:08:42'),(1097,2,'Unbanned','2017-09-30 12:13:42'),(1098,1,'Unbanned','2017-09-30 12:13:49'),(1099,8,'Banned','2017-09-30 12:42:49'),(1100,8,'Banned','2017-09-30 12:42:54'),(1101,7,'Unbanned','2017-09-30 12:43:11'),(1102,7,'Banned','2017-09-30 12:43:15'),(1103,8,'Unbanned','2017-09-30 12:43:17'),(1104,3,'Banned','2017-09-30 12:43:18'),(1105,3,'Unbanned','2017-09-30 12:43:21'),(1106,3,'Banned','2017-09-30 13:31:46'),(1107,7,'Unbanned','2017-09-30 13:32:03'),(1108,2,'Banned','2017-09-30 13:32:07'),(1109,4,'Banned','2017-09-30 21:55:30'),(1110,4,'Banned','2017-09-30 21:55:43'),(1111,2,'Unbanned','2017-09-30 22:36:34'),(1112,5,'Banned','2017-10-01 05:10:41'),(1113,5,'Unbanned','2017-10-01 05:10:54'),(1114,2,'Banned','2017-10-01 05:10:56'),(1115,2,'Unbanned','2017-10-01 05:10:57'),(1140,2,'Banned','2017-10-03 10:53:12'),(1141,3,'Banned','2017-10-03 10:53:23'),(1142,3,'Unbanned','2017-10-03 10:53:26'),(1143,4,'Banned','2017-10-03 10:53:31'),(1144,4,'Unbanned','2017-10-03 10:53:32'),(1152,5,'Banned','2017-10-04 06:23:46'),(1153,5,'Unbanned','2017-10-04 06:23:50'),(1154,4,'Banned','2017-10-04 06:23:51'),(1155,2,'Unbanned','2017-10-04 06:23:53'),(1164,1,'Banned','2017-10-04 06:33:21'),(1165,4,'Unbanned','2017-10-04 06:33:24'),(1166,4,'Banned','2017-10-04 06:33:26'),(1171,2,'Login','2017-10-04 11:49:24'),(1202,1,'Bully word detected:cold','2017-10-18 06:52:04'),(1206,9,'Register','2017-10-23 22:26:58'),(1207,9,'Login','2017-10-23 22:27:11'),(1208,9,'Post:hello','2017-10-23 22:27:30'),(1209,1,'Bully word detected:cold, bully','2017-10-23 22:47:35'),(1210,1,'Bully word detected:cold, bully','2017-10-23 22:52:46'),(1211,9,'Login','2017-10-23 23:51:33'),(1212,9,'Bully word detected:cold, bully','2017-10-23 23:51:55'),(1213,9,'Post:This is a message with bully word cold sending from Sdyney, by Gary, University of New South Wales','2017-10-23 23:52:06'),(1214,9,'Login','2017-10-23 23:54:00'),(1215,9,'Bully word detected:cold, bully','2017-10-23 23:54:13'),(1216,9,'Post:This is a message with bully word cold sending from Sdyney, by Gary, University of New South Wales','2017-10-23 23:54:24'),(1217,9,'Login','2017-10-24 00:00:39'),(1218,9,'Bully word detected:cold, bully','2017-10-24 00:01:06'),(1219,9,'Post:This is a message with bully word cold sending from Sdyney, New York, by Gary, University of New South Wales','2017-10-24 00:01:16');
/*!40000 ALTER TABLE `log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notification` (
  `notificationID` int(11) NOT NULL AUTO_INCREMENT,
  `n_content` varchar(255) NOT NULL,
  `senderID` int(11) NOT NULL,
  `receiverID` int(11) NOT NULL,
  `n_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `readornot` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`notificationID`),
  UNIQUE KEY `notification_notificationID_uindex` (`notificationID`),
  KEY `notification_users_userid_fk` (`receiverID`),
  CONSTRAINT `notification_users_userid_fk` FOREIGN KEY (`receiverID`) REFERENCES `users` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` VALUES (1,'sdfasdfdsafadsf',1,2,'2017-09-27 12:26:31',0),(2,'sdfasdfdsafadsf',3,2,'2017-09-27 12:43:04',0),(3,'sdfasdfdsafadsf',4,2,'2017-09-27 12:43:14',0),(10,'hello',1,2,'2017-09-27 12:26:31',0),(11,'1 like your post',1,2,'2017-09-27 13:16:52',0),(12,'hello',1,2,'2017-09-27 12:26:31',0),(13,'1 like your post',1,2,'2017-09-27 13:27:08',0),(14,'1 like your post',1,2,'2017-09-27 13:27:17',0),(15,'1 like your post',1,2,'2017-09-27 13:27:18',0),(16,'1 like your post',1,2,'2017-09-27 13:27:18',0),(17,'1 like your post',1,2,'2017-09-27 13:28:15',0),(18,'1 like your post',1,2,'2017-09-27 13:28:15',0),(19,'1 like your post',1,2,'2017-09-27 13:28:16',0),(20,'1 like your post',1,2,'2017-09-27 13:28:16',0),(21,'tom like your post',1,2,'2017-09-27 13:28:54',0),(22,'tom like your post',1,2,'2017-09-27 13:28:55',0),(23,'tom like your post',1,2,'2017-09-27 14:12:59',0),(24,'tom like your post',1,2,'2017-09-27 14:13:00',0),(25,'tom like your post',1,2,'2017-09-28 03:58:24',0),(26,'tom like your post',1,2,'2017-09-28 03:58:25',0),(27,'tom like your post',1,2,'2017-09-28 04:18:38',0),(28,'tom like your post',1,2,'2017-09-28 04:18:40',0),(29,'tom like your post',1,2,'2017-09-28 04:18:41',0),(30,'tom like your post',1,2,'2017-09-28 04:18:43',0),(31,'tom like your post',1,2,'2017-09-28 04:18:44',0),(32,'tom like your post',1,2,'2017-09-28 04:18:45',0),(33,'tom like your post',1,2,'2017-09-28 04:18:46',0),(34,'tom like your post',1,2,'2017-09-28 04:18:47',0),(35,'tom like your post',1,2,'2017-09-28 04:18:48',0),(36,'tom like your post',1,2,'2017-09-28 04:51:59',0),(37,'tom like your post',1,2,'2017-09-28 04:52:00',0),(38,'tom like your post',1,2,'2017-09-28 04:52:04',0),(39,'tom like your post',1,2,'2017-09-28 05:10:38',0),(40,'tom like your post',1,2,'2017-09-28 05:10:38',0),(41,'tom like your post',1,2,'2017-09-28 05:10:39',0),(42,'tom like your post',1,2,'2017-09-28 05:10:39',0);
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post` (
  `p_id` int(11) NOT NULL AUTO_INCREMENT,
  `p_content` varchar(255) NOT NULL,
  `p_image` varchar(255) DEFAULT NULL,
  `p_time` datetime NOT NULL,
  `p_userid` int(11) NOT NULL,
  `isVaild` tinyint(1) NOT NULL DEFAULT '1',
  `p_location` varchar(255) DEFAULT NULL,
  `p_person` varchar(255) DEFAULT NULL,
  `p_organization` varchar(255) DEFAULT NULL,
  `p_keyword` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`p_id`),
  UNIQUE KEY `post_p_id_uindex` (`p_id`),
  KEY `post_users_userid_fk` (`p_userid`),
  CONSTRAINT `post_users_userid_fk` FOREIGN KEY (`p_userid`) REFERENCES `users` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=23432510 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (23432353,' james','d29e4882-84fd-4302-864d-f82247559592_hqdefault.jpg','2017-09-27 08:39:27',4,1,NULL,NULL,NULL,NULL),(23432473,'dsfasdfdsaf','','2017-09-27 23:03:48',1,0,NULL,NULL,NULL,NULL),(23432474,'dsfsadf','','2017-09-27 23:05:02',1,0,NULL,NULL,NULL,NULL),(23432475,'222222','','2017-09-27 23:09:19',2,1,NULL,NULL,NULL,NULL),(23432476,'leoleoleo','','2017-09-27 23:09:27',2,1,NULL,NULL,NULL,NULL),(23432477,'zc cvzx csa xCZZX xz','','2017-09-28 00:13:16',1,0,NULL,NULL,NULL,NULL),(23432478,'dszvsxZvXZ','','2017-09-28 00:13:22',1,0,NULL,NULL,NULL,NULL),(23432479,'advZvXC','','2017-09-28 00:13:25',1,0,NULL,NULL,NULL,NULL),(23432480,'zxVZX','','2017-09-28 00:13:31',1,0,NULL,NULL,NULL,NULL),(23432481,'adfdsfsadf','','2017-09-28 00:13:34',1,1,NULL,NULL,NULL,NULL),(23432482,'213324','','2017-09-28 14:17:06',2,1,NULL,NULL,NULL,NULL),(23432483,'sdafdsaf','','2017-09-28 14:17:08',2,1,NULL,NULL,NULL,NULL),(23432484,'asdfsadf','','2017-09-28 14:17:10',2,1,NULL,NULL,NULL,NULL),(23432485,'sadfsadf','','2017-09-28 14:17:12',2,1,NULL,NULL,NULL,NULL),(23432486,'sdafasdf','','2017-09-28 14:17:13',2,1,NULL,NULL,NULL,NULL),(23432487,'sdfsadf','','2017-09-28 14:17:17',2,1,NULL,NULL,NULL,NULL),(23432488,'sadfasdfadfasdfas','','2017-09-28 14:17:20',2,1,NULL,NULL,NULL,NULL),(23432489,'121eqwerfadsf','','2017-09-28 14:17:23',2,1,NULL,NULL,NULL,NULL),(23432490,'qqqqq','','2017-09-28 14:17:27',2,1,NULL,NULL,NULL,NULL),(23432491,'wwwwww','','2017-09-28 14:17:30',2,1,NULL,NULL,NULL,NULL),(23432492,'eeeeee','','2017-09-28 14:17:32',2,1,NULL,NULL,NULL,NULL),(23432493,'dfsasdfadsf','e9fb5640-5300-4007-8bf5-32f848641ab2_hqdefault.jpg','2017-09-28 15:07:08',1,0,NULL,NULL,NULL,NULL),(23432494,'xcvszcgvsad','9b22fa95-8503-40b8-8dc2-a532e50b37f9_hqdefault.jpg','2017-09-28 15:07:16',1,1,NULL,NULL,NULL,NULL),(23432495,'sdfdasf','','2017-09-28 15:18:09',1,1,NULL,NULL,NULL,NULL),(23432496,'dsffdsgdfsg','','2017-09-28 15:22:49',1,1,NULL,NULL,NULL,NULL),(23432497,'Uå¸hiåå®¶','','2017-09-28 17:22:56',1,1,NULL,NULL,NULL,NULL),(23432498,'å¦è¢å¤©ç½¡ä¼å','','2017-09-28 17:23:05',1,1,NULL,NULL,NULL,NULL),(23432499,'happy','','2017-09-28 17:23:15',1,1,NULL,NULL,NULL,NULL),(23432500,'happy','','2017-09-28 17:23:31',1,1,NULL,NULL,NULL,NULL),(23432506,'hello','','2017-10-24 09:27:30',9,1,NULL,NULL,NULL,NULL),(23432507,'This is a message with bully word cold sending from Sdyney, by Gary, University of New South Wales','','2017-10-24 10:51:49',9,1,'Sdyney  ','Gary  ','University of New South Wales  ','Gary New University Sdyney Wales South sending cold message bully word '),(23432508,'This is a message with bully word cold sending from Sdyney, by Gary, University of New South Wales','','2017-10-24 10:54:08',9,1,'Sdyney , ','Gary , ','University of New South Wales , ','Gary, New, University, Sdyney, Wales, South, sending, cold, message, bully, word, '),(23432509,'This is a message with bully word cold sending from Sdyney, New York, by Gary, University of New South Wales','','2017-10-24 11:01:01',9,1,'Sdyney, New York, ','Gary, ','University of New South Wales, ','Gary, New, University, York, Sdyney, Wales, South, sending, cold, message, bully, word, ');
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) NOT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `userName` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `confirm_email` tinyint(1) DEFAULT '0',
  `confirm` tinyint(1) NOT NULL DEFAULT '1',
  `gender` varchar(10) NOT NULL,
  `u_day` int(11) DEFAULT NULL,
  `u_year` int(11) DEFAULT NULL,
  `u_month` int(11) DEFAULT NULL,
  PRIMARY KEY (`userid`),
  UNIQUE KEY `users_userid_uindex` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Tom',NULL,'tom','0','',1,0,'',NULL,NULL,NULL),(2,'Leo',NULL,'leo','0','',1,1,'',NULL,NULL,NULL),(3,'Kevin',NULL,'kevin','0','',1,1,'',NULL,NULL,NULL),(4,'James',NULL,'james','0','',1,0,'',NULL,NULL,NULL),(5,'Wolf',NULL,'wolf','0','',1,1,'',NULL,NULL,NULL),(6,'Sherly',NULL,'sherly','0','',1,1,'',NULL,NULL,NULL),(7,'vigoss','C:UsersGaryDesktopunswUNSWbook	argetUNSWbook-1.0-SNAPSHOTWEB-INF/headphoto/headphoto7.jpeg ','ztr807308796','ab123456','wshhrm@163.com',1,1,'M',1,1960,1),(8,'afeaf','headphoto8.jpeg ','ztrvioss','ab1234567','wshhrm@163.com',1,1,'F',1,1960,1),(9,'gary','headphoto9.jpeg ','g y','123','ygy3389@gmail.com',1,1,'M',1,1960,1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-10-24 12:09:26
