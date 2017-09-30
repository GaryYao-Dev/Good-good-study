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
) ENGINE=InnoDB AUTO_INCREMENT=667 DEFAULT CHARSET=latin1;
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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
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
) ENGINE=InnoDB AUTO_INCREMENT=23313 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `likelist`
--

LOCK TABLES `likelist` WRITE;
/*!40000 ALTER TABLE `likelist` DISABLE KEYS */;
INSERT INTO `likelist` VALUES (23265,23432473,1),(23271,23432476,1),(23272,23432475,1),(23277,23432478,1),(23278,23432481,1),(23279,23432491,2),(23280,23432490,2),(23281,23432488,2),(23282,23432487,2),(23283,23432485,2),(23284,23432484,2),(23286,23432492,1),(23288,23432487,1),(23290,23432489,1),(23291,23432486,1),(23292,23432485,1),(23293,23432484,1),(23296,23432488,1),(23301,23432493,1),(23311,23432499,1),(23312,23432496,1);
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
  `activity` varchar(45) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`logID`),
  UNIQUE KEY `logID_UNIQUE` (`logID`),
  KEY `userID_idx` (`userID`),
  CONSTRAINT `userID_log` FOREIGN KEY (`userID`) REFERENCES `users` (`userid`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1049 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log`
--

LOCK TABLES `log` WRITE;
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
INSERT INTO `log` VALUES (1045,1,'Banned','2017-09-29 23:20:01'),(1046,2,'unbanned','2017-09-29 23:20:05'),(1047,3,'unbanned','2017-09-29 23:21:11'),(1048,1,'unbanned','2017-09-29 23:21:13');
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
  PRIMARY KEY (`p_id`),
  UNIQUE KEY `post_p_id_uindex` (`p_id`),
  KEY `post_users_userid_fk` (`p_userid`),
  CONSTRAINT `post_users_userid_fk` FOREIGN KEY (`p_userid`) REFERENCES `users` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=23432501 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (23432353,' james','d29e4882-84fd-4302-864d-f82247559592_hqdefault.jpg','2017-09-27 08:39:27',4,1),(23432473,'dsfasdfdsaf','','2017-09-27 23:03:48',1,0),(23432474,'dsfsadf','','2017-09-27 23:05:02',1,0),(23432475,'222222','','2017-09-27 23:09:19',2,1),(23432476,'leoleoleo','','2017-09-27 23:09:27',2,1),(23432477,'zc cvzx csa xCZZX xz','','2017-09-28 00:13:16',1,0),(23432478,'dszvsxZvXZ','','2017-09-28 00:13:22',1,0),(23432479,'advZvXC','','2017-09-28 00:13:25',1,0),(23432480,'zxVZX','','2017-09-28 00:13:31',1,0),(23432481,'adfdsfsadf','','2017-09-28 00:13:34',1,1),(23432482,'213324','','2017-09-28 14:17:06',2,1),(23432483,'sdafdsaf','','2017-09-28 14:17:08',2,1),(23432484,'asdfsadf','','2017-09-28 14:17:10',2,1),(23432485,'sadfsadf','','2017-09-28 14:17:12',2,1),(23432486,'sdafasdf','','2017-09-28 14:17:13',2,1),(23432487,'sdfsadf','','2017-09-28 14:17:17',2,1),(23432488,'sadfasdfadfasdfas','','2017-09-28 14:17:20',2,1),(23432489,'121eqwerfadsf','','2017-09-28 14:17:23',2,1),(23432490,'qqqqq','','2017-09-28 14:17:27',2,1),(23432491,'wwwwww','','2017-09-28 14:17:30',2,1),(23432492,'eeeeee','','2017-09-28 14:17:32',2,1),(23432493,'dfsasdfadsf','e9fb5640-5300-4007-8bf5-32f848641ab2_hqdefault.jpg','2017-09-28 15:07:08',1,0),(23432494,'xcvszcgvsad','9b22fa95-8503-40b8-8dc2-a532e50b37f9_hqdefault.jpg','2017-09-28 15:07:16',1,1),(23432495,'sdfdasf','','2017-09-28 15:18:09',1,1),(23432496,'dsffdsgdfsg','','2017-09-28 15:22:49',1,1),(23432497,'Uå¸hiåå®¶','','2017-09-28 17:22:56',1,1),(23432498,'å¦è¢å¤©ç½¡ä¼å','','2017-09-28 17:23:05',1,1),(23432499,'happy','','2017-09-28 17:23:15',1,1),(23432500,'happy','','2017-09-28 17:23:31',1,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Tom',NULL,'tom','0','',0,1,'',NULL,NULL,NULL),(2,'Leo',NULL,'leo','0','',0,1,'',NULL,NULL,NULL),(3,'Kevin',NULL,'kevin','0','',0,1,'',NULL,NULL,NULL),(4,'James',NULL,'james','0','',0,1,'',NULL,NULL,NULL),(5,'Wolf',NULL,'wolf','0','',0,1,'',NULL,NULL,NULL),(6,'Sherly',NULL,'sherly','0','',0,1,'',NULL,NULL,NULL),(7,'vigoss','C:UsersGaryDesktopunswUNSWbook	argetUNSWbook-1.0-SNAPSHOTWEB-INF/headphoto/headphoto7.jpeg ','ztr807308796','ab123456','wshhrm@163.com',1,1,'M',1,1960,1),(8,'afeaf','headphoto8.jpeg ','ztrvioss','ab1234567','wshhrm@163.com',1,1,'F',1,1960,1);
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

-- Dump completed on 2017-09-30 13:58:37
