-- MySQL dump 10.13  Distrib 5.5.28, for Win32 (x86)
--
-- Host: localhost    Database: trading_db
-- ------------------------------------------------------
-- Server version	5.5.28

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
-- Table structure for table `branch`
--

DROP TABLE IF EXISTS `branch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `branch` (
  `branchId` int(11) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `crtdDtTime` datetime DEFAULT NULL,
  `genericStatus` varchar(255) DEFAULT NULL,
  `optLock` datetime DEFAULT NULL,
  `upDtTime` datetime DEFAULT NULL,
  `updatedBy` varchar(255) DEFAULT NULL,
  `branchName` varchar(255) DEFAULT NULL,
  `districtLocation` int(11) DEFAULT NULL,
  PRIMARY KEY (`branchId`),
  KEY `FK771411C2C1123BFE` (`districtLocation`),
  CONSTRAINT `FK771411C2C1123BFE` FOREIGN KEY (`districtLocation`) REFERENCES `district` (`districtId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branch`
--

LOCK TABLES `branch` WRITE;
/*!40000 ALTER TABLE `branch` DISABLE KEYS */;
INSERT INTO `branch` VALUES (1,'Junior','2019-03-31 15:22:09','active',NULL,NULL,NULL,'Kicukiro Branch',3);
/*!40000 ALTER TABLE `branch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact`
--

DROP TABLE IF EXISTS `contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact` (
  `contactId` int(11) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `crtdDtTime` datetime DEFAULT NULL,
  `genericStatus` varchar(255) DEFAULT NULL,
  `optLock` datetime DEFAULT NULL,
  `upDtTime` datetime DEFAULT NULL,
  `updatedBy` varchar(255) DEFAULT NULL,
  `contactDetails` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `pobox` varchar(255) DEFAULT NULL,
  `user` int(11) DEFAULT NULL,
  PRIMARY KEY (`contactId`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `phone` (`phone`),
  UNIQUE KEY `pobox` (`pobox`),
  KEY `FK9BEFBC00C6256DC6` (`user`),
  CONSTRAINT `FK9BEFBC00C6256DC6` FOREIGN KEY (`user`) REFERENCES `users` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact`
--

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `country` (
  `countryId` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `countryName_en` varchar(255) DEFAULT NULL,
  `countryName_fr` varchar(255) DEFAULT NULL,
  `countryName_rw` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`countryId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (1,'AFR01','RWANDA','RWANDA','RWANDA'),(2,'AFR02','UGANDA','UGANDA','UGANDA'),(3,'AFR03','TANZANIA','TANZANIE','TANZANIYA');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `district`
--

DROP TABLE IF EXISTS `district`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `district` (
  `districtId` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `districtName_en` varchar(255) DEFAULT NULL,
  `districtName_fr` varchar(255) DEFAULT NULL,
  `districtName_rw` varchar(255) DEFAULT NULL,
  `province` int(11) DEFAULT NULL,
  PRIMARY KEY (`districtId`),
  KEY `FK151397AE2085F98D` (`province`),
  CONSTRAINT `FK151397AE2085F98D` FOREIGN KEY (`province`) REFERENCES `province` (`provenceId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `district`
--

LOCK TABLES `district` WRITE;
/*!40000 ALTER TABLE `district` DISABLE KEYS */;
INSERT INTO `district` VALUES (1,'NYSTR01','Nyarugenge','Nyarugenge','Nyarugenge',1),(2,'GSBSTR02','Gasabo','Gasabo','Gasabo',1),(3,'KCkSTR03','Kicukiro','Kicukiro','Kicukiro',1),(4,'RBVSTR04','Rubavu','Rubavu','Rubavu',5);
/*!40000 ALTER TABLE `district` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `documents`
--

DROP TABLE IF EXISTS `documents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `documents` (
  `DocId` int(11) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `crtdDtTime` datetime DEFAULT NULL,
  `genericStatus` varchar(255) DEFAULT NULL,
  `optLock` datetime DEFAULT NULL,
  `upDtTime` datetime DEFAULT NULL,
  `updatedBy` varchar(255) DEFAULT NULL,
  `documentLoc` varchar(255) DEFAULT NULL,
  `fileSize` bigint(20) DEFAULT NULL,
  `originalFileName` varchar(255) DEFAULT NULL,
  `sysFilename` varchar(255) DEFAULT NULL,
  `validDocCode` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`DocId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `documents`
--

LOCK TABLES `documents` WRITE;
/*!40000 ALTER TABLE `documents` DISABLE KEYS */;
/*!40000 ALTER TABLE `documents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `listofmenu`
--

DROP TABLE IF EXISTS `listofmenu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `listofmenu` (
  `menuId` int(11) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `crtdDtTime` datetime DEFAULT NULL,
  `genericStatus` varchar(255) DEFAULT NULL,
  `optLock` datetime DEFAULT NULL,
  `upDtTime` datetime DEFAULT NULL,
  `updatedBy` varchar(255) DEFAULT NULL,
  `creationDate` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `iconImage` varchar(255) DEFAULT NULL,
  `menuColor` varchar(255) DEFAULT NULL,
  `urlCode` varchar(255) DEFAULT NULL,
  `urlName` varchar(255) DEFAULT NULL,
  `listOfMenu` int(11) DEFAULT NULL,
  `menuGroup` int(11) DEFAULT NULL,
  PRIMARY KEY (`menuId`),
  UNIQUE KEY `urlCode` (`urlCode`),
  UNIQUE KEY `urlName` (`urlName`),
  KEY `FK84E9DE34503B2FD5` (`listOfMenu`),
  KEY `FK84E9DE347A929B53` (`menuGroup`),
  CONSTRAINT `FK84E9DE34503B2FD5` FOREIGN KEY (`listOfMenu`) REFERENCES `listofmenu` (`menuId`),
  CONSTRAINT `FK84E9DE347A929B53` FOREIGN KEY (`menuGroup`) REFERENCES `menugroup` (`menuGroupId`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `listofmenu`
--

LOCK TABLES `listofmenu` WRITE;
/*!40000 ALTER TABLE `listofmenu` DISABLE KEYS */;
INSERT INTO `listofmenu` VALUES (1,'admin','2019-03-15 12:50:20','active','2019-03-25 03:09:06','2019-03-25 03:09:06','admin','2019-03-14 00:00:00','Users management','glyphicon glyphicon-user','gray2','Url01','/menu/ViewUsersDetails.xhtml',NULL,1),(2,'admin','2019-03-15 12:50:21','active','2019-03-14 02:04:00','2019-03-14 02:04:00','admin','2019-03-14 02:04:00','Profile management','glyphicon glyphicon-user','blue2','Url02','/menu/ViewProfile.xhtml',NULL,1),(3,'admin','2018-10-10 21:32:29','active','2018-10-10 21:32:29','2018-10-10 21:32:29','admin','2018-10-10 21:32:29','Manage MenuGroup','glyphicon glyphicon-asterisk','gray2','Url03','/menu/ViewMenuGroup.xhtml',NULL,1),(4,'admin','2018-10-10 21:49:39','active','2018-10-10 21:49:39','2018-10-10 21:49:39','admin','2018-10-10 21:49:39','List of Menu','glyphicon glyphicon-minus','blue2','Url04','/menu/ViewListOfMenu.xhtml',NULL,1),(5,'admin','2018-10-10 21:51:04','active','2018-10-10 21:51:04','2018-10-10 21:51:04','admin','2018-10-10 21:51:04','Menu Assignment','glyphicon glyphicon-asterisk','gray2','Url05','/menu/menuAssignmentForm.xhtml',NULL,1),(8,'admin','2019-03-24 18:38:11','active','2019-03-06 00:00:00','2019-03-06 00:00:00','admin','2019-03-06 00:00:00','Category management','glyphicon glyphicon-tasks','blue2','Url08','/menu/ProductCategory.xhtml',NULL,11),(9,'admin','2019-03-24 18:44:53','active','2019-03-06 04:12:15','2019-03-06 04:12:15','admin','2019-03-25 00:00:00','Order management','glyphicon glyphicon-shopping-cart','blue2','Url09','/menu/OrderProduct.xhtml',NULL,7),(10,'admin','2019-03-24 18:49:47','active','2019-03-14 02:04:00','2019-03-14 02:04:00','admin','2019-03-14 02:04:00','Sales management','glyphicon glyphicon-usd','blue2','Url10','/menu/SoldProduct.xhtml',NULL,11),(11,'admin','2019-03-28 07:36:55','active','2019-03-28 07:36:55','2019-03-28 07:36:55','admin','2019-03-28 07:36:55','Product management','glyphicon glyphicon-check','blue2','Url11','/menu/ProductPricing.xhtml',NULL,13),(12,'admin','2019-03-28 18:11:31','active','2019-03-28 18:11:31','2019-03-28 18:11:31','admin','2019-03-28 18:11:31','Branch management','glyphicon glyphicon-map-marker','purple2','Url12','/menu/Branch.xhtml',NULL,1),(17,NULL,'2019-03-31 15:16:53','active',NULL,NULL,NULL,NULL,'Product management','glyphicon glyphicon-floppy-save','purple2 ','Url13','/menu/NewProduct.xhtml',NULL,11);
/*!40000 ALTER TABLE `listofmenu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loginhistoric`
--

DROP TABLE IF EXISTS `loginhistoric`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `loginhistoric` (
  `historicId` int(11) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `crtdDtTime` datetime DEFAULT NULL,
  `genericStatus` varchar(255) DEFAULT NULL,
  `optLock` datetime DEFAULT NULL,
  `upDtTime` datetime DEFAULT NULL,
  `updatedBy` varchar(255) DEFAULT NULL,
  `IpAddress` varchar(255) DEFAULT NULL,
  `logOutTime` datetime DEFAULT NULL,
  `loginTimeIn` datetime DEFAULT NULL,
  `users` int(11) DEFAULT NULL,
  PRIMARY KEY (`historicId`),
  KEY `FKA90EB6C8CC951003` (`users`),
  CONSTRAINT `FKA90EB6C8CC951003` FOREIGN KEY (`users`) REFERENCES `users` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loginhistoric`
--

LOCK TABLES `loginhistoric` WRITE;
/*!40000 ALTER TABLE `loginhistoric` DISABLE KEYS */;
INSERT INTO `loginhistoric` VALUES (1,'Fred Ribakare',NULL,NULL,NULL,'2019-03-15 12:45:55',NULL,'NGANGO/192.168.1.117',NULL,'2019-03-15 12:50:36',1),(2,'Fred Ribakare',NULL,NULL,NULL,'2019-03-15 14:53:50',NULL,'NGANGO/192.168.1.117',NULL,'2019-03-15 14:54:17',1),(3,'Cedrick Barafinda',NULL,NULL,NULL,'2019-03-15 16:41:15',NULL,'NGANGO/192.168.1.117',NULL,'2019-03-15 16:51:35',2),(4,'Fred Ribakare',NULL,NULL,NULL,'2019-03-15 17:00:59',NULL,'NGANGO/192.168.1.117',NULL,'2019-03-15 17:01:51',1),(5,'Fred Ribakare',NULL,NULL,NULL,'2019-03-16 12:39:04',NULL,'NGANGO/127.0.0.1',NULL,'2019-03-16 12:39:44',1),(6,'Fred Ribakare',NULL,NULL,NULL,'2019-03-16 12:39:04',NULL,'NGANGO/127.0.0.1','2019-03-16 12:56:17',NULL,1),(7,'Fred Ribakare',NULL,NULL,NULL,'2019-03-16 12:56:19',NULL,'NGANGO/127.0.0.1',NULL,'2019-03-16 13:00:06',1),(8,'Fred Ribakare',NULL,NULL,NULL,'2019-03-16 13:07:06',NULL,'NGANGO/127.0.0.1',NULL,'2019-03-16 13:07:20',1),(9,'Fred Ribakare',NULL,NULL,NULL,'2019-03-16 13:07:06',NULL,'NGANGO/127.0.0.1','2019-03-16 13:08:30',NULL,1),(10,'Fred Ribakare',NULL,NULL,NULL,'2019-03-16 13:08:32',NULL,'NGANGO/127.0.0.1',NULL,'2019-03-16 13:08:50',1),(11,'Fred Ribakare',NULL,NULL,NULL,'2019-03-16 13:08:32',NULL,'NGANGO/127.0.0.1','2019-03-16 13:09:01',NULL,1),(12,'Junior Ngango',NULL,NULL,NULL,'2019-03-16 13:09:03',NULL,'NGANGO/127.0.0.1',NULL,'2019-03-16 13:09:12',3),(13,'Cedrick Barafinda',NULL,NULL,NULL,'2019-03-16 18:50:02',NULL,'NGANGO/127.0.0.1',NULL,'2019-03-16 18:52:00',2),(14,'Cedrick Barafinda',NULL,NULL,NULL,'2019-03-16 18:50:02',NULL,'NGANGO/127.0.0.1','2019-03-16 18:53:27',NULL,2),(15,'Fred Ribakare',NULL,NULL,NULL,'2019-03-16 18:53:29',NULL,'NGANGO/127.0.0.1',NULL,'2019-03-16 18:53:39',1),(16,'Fred Ribakare',NULL,NULL,NULL,'2019-03-18 05:54:19',NULL,'NGANGO/127.0.0.1',NULL,'2019-03-18 05:55:58',1),(17,'Fred Ribakare',NULL,NULL,NULL,'2019-03-18 06:02:17',NULL,'NGANGO/127.0.0.1',NULL,'2019-03-18 06:02:51',1),(18,'Fred Ribakare',NULL,NULL,NULL,'2019-03-18 08:12:40',NULL,'NGANGO/192.168.109.1',NULL,'2019-03-18 08:13:34',1),(19,'Cedrick Barafinda',NULL,NULL,NULL,'2019-03-18 15:12:00',NULL,'NGANGO/192.168.109.1',NULL,'2019-03-18 15:21:40',2),(20,'Fred Ribakare',NULL,NULL,NULL,'2019-03-18 15:50:25',NULL,'NGANGO/192.168.109.1',NULL,'2019-03-18 15:51:14',1),(21,'Fred Ribakare',NULL,NULL,NULL,'2019-03-18 16:11:01',NULL,'NGANGO/192.168.109.1',NULL,'2019-03-18 16:16:22',1),(22,'Fred Ribakare',NULL,NULL,NULL,'2019-03-18 17:32:29',NULL,'NGANGO/192.168.109.1',NULL,'2019-03-18 17:34:38',1),(23,'Fred Ribakare',NULL,NULL,NULL,'2019-03-18 17:32:29',NULL,'NGANGO/192.168.109.1','2019-03-18 17:54:59',NULL,1),(24,'Fred Ribakare',NULL,NULL,NULL,'2019-03-18 17:55:01',NULL,'NGANGO/192.168.109.1',NULL,'2019-03-18 18:00:43',1),(25,'Fred Ribakare',NULL,NULL,NULL,'2019-03-24 14:03:02',NULL,'NGANGO/192.168.1.114',NULL,'2019-03-24 14:03:40',1),(26,'Fred Ribakare',NULL,NULL,NULL,'2019-03-24 14:03:02',NULL,'NGANGO/192.168.1.114','2019-03-24 14:19:14',NULL,1),(27,'Fred Ribakare',NULL,NULL,NULL,'2019-03-24 14:19:15',NULL,'NGANGO/192.168.1.114',NULL,'2019-03-24 14:19:23',1),(28,'Fred Ribakare',NULL,NULL,NULL,'2019-03-24 14:43:18',NULL,'NGANGO/192.168.1.114',NULL,'2019-03-24 14:43:32',1),(29,'Fred Ribakare',NULL,NULL,NULL,'2019-03-24 14:46:01',NULL,'NGANGO/192.168.1.114',NULL,'2019-03-24 14:46:09',1),(30,'Fred Ribakare',NULL,NULL,NULL,'2019-03-24 14:50:43',NULL,'NGANGO/192.168.1.114',NULL,'2019-03-24 15:00:57',1),(31,'Fred Ribakare',NULL,NULL,NULL,'2019-03-28 17:24:27',NULL,'NGANGO/127.0.0.1',NULL,'2019-03-28 17:26:41',1),(32,'Junior Ngango',NULL,NULL,NULL,'2019-03-28 17:35:55',NULL,'NGANGO/127.0.0.1',NULL,'2019-03-28 17:36:38',3),(33,'Fred Ribakare',NULL,NULL,NULL,'2019-03-28 21:46:33',NULL,'NGANGO/127.0.0.1',NULL,'2019-03-28 21:47:25',1),(34,'Fred Ribakare',NULL,NULL,NULL,'2019-03-28 21:46:33',NULL,'NGANGO/127.0.0.1','2019-03-28 22:05:10',NULL,1),(35,'Junior Ngango',NULL,NULL,NULL,'2019-03-28 22:05:11',NULL,'NGANGO/127.0.0.1',NULL,'2019-03-28 22:30:25',3),(36,'Fred Ribakare',NULL,NULL,NULL,'2019-03-29 08:51:56',NULL,'NGANGO/192.168.1.130',NULL,'2019-03-29 08:53:41',1),(37,'Fred Ribakare',NULL,NULL,NULL,'2019-03-29 09:04:21',NULL,'NGANGO/192.168.1.130',NULL,'2019-03-29 09:13:56',1),(38,'Fred Ribakare',NULL,NULL,NULL,'2019-03-29 09:25:09',NULL,'NGANGO/192.168.1.130',NULL,'2019-03-29 09:25:29',1),(39,'Fred Ribakare',NULL,NULL,NULL,'2019-03-29 09:27:45',NULL,'NGANGO/127.0.0.1',NULL,'2019-03-29 09:27:54',1),(40,'Fred Ribakare',NULL,NULL,NULL,'2019-03-29 09:31:45',NULL,'NGANGO/127.0.0.1',NULL,'2019-03-29 09:32:11',1),(41,'Fred Ribakare',NULL,NULL,NULL,'2019-03-29 10:00:15',NULL,'NGANGO/192.168.1.130',NULL,'2019-03-29 10:00:27',1),(42,'Fred Ribakare',NULL,NULL,NULL,'2019-03-30 20:40:17',NULL,'NGANGO/192.168.1.130',NULL,'2019-03-30 20:40:48',1),(43,'Fred Ribakare',NULL,NULL,NULL,'2019-03-30 20:54:28',NULL,'NGANGO/192.168.1.130',NULL,'2019-03-30 20:54:38',1),(44,'Fred Ribakare',NULL,NULL,NULL,'2019-03-30 20:54:28',NULL,'NGANGO/192.168.1.130','2019-03-30 21:42:21',NULL,1),(45,'Junior Ngango',NULL,NULL,NULL,'2019-03-30 21:42:22',NULL,'NGANGO/192.168.1.130',NULL,'2019-03-30 21:42:32',3),(46,'Junior Ngango',NULL,NULL,NULL,'2019-03-30 21:42:22',NULL,'NGANGO/192.168.1.130','2019-03-30 21:49:03',NULL,3),(47,'Fred Ribakare',NULL,NULL,NULL,'2019-03-30 21:49:05',NULL,'NGANGO/192.168.1.130',NULL,'2019-03-30 21:49:12',1),(48,'Junior Ngango',NULL,NULL,NULL,'2019-03-31 14:32:36',NULL,'DESKTOP-BCD89LB/192.168.1.129','2019-03-31 14:39:30',NULL,3),(49,'Junior Ngango',NULL,NULL,NULL,'2019-03-31 14:39:31',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-03-31 14:39:39',3),(50,'Junior Ngango',NULL,NULL,NULL,'2019-03-31 14:39:31',NULL,'DESKTOP-BCD89LB/192.168.1.129','2019-03-31 14:40:38',NULL,3),(51,'Cedrick Barafinda',NULL,NULL,NULL,'2019-03-31 14:40:39',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-03-31 14:40:50',2),(52,'Cedrick Barafinda',NULL,NULL,NULL,'2019-03-31 14:40:39',NULL,'DESKTOP-BCD89LB/192.168.1.129','2019-03-31 14:54:53',NULL,2),(53,'Cedrick Barafinda',NULL,NULL,NULL,'2019-03-31 14:40:39',NULL,'DESKTOP-BCD89LB/192.168.1.129','2019-03-31 14:54:54',NULL,2),(54,'Junior Ngango',NULL,NULL,NULL,'2019-03-31 14:54:55',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-03-31 14:55:01',3),(55,'Junior Ngango',NULL,NULL,NULL,'2019-03-31 14:54:55',NULL,'DESKTOP-BCD89LB/192.168.1.129','2019-03-31 14:56:00',NULL,3),(56,'Fred Ribakare',NULL,NULL,NULL,'2019-03-31 14:56:01',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-03-31 14:56:09',1),(57,'Fred Ribakare',NULL,NULL,NULL,'2019-03-31 14:56:01',NULL,'DESKTOP-BCD89LB/192.168.1.129','2019-03-31 14:56:25',NULL,1),(58,'Cedrick Barafinda',NULL,NULL,NULL,'2019-03-31 14:56:26',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-03-31 14:56:34',2),(59,'Cedrick Barafinda',NULL,NULL,NULL,'2019-03-31 14:56:26',NULL,'DESKTOP-BCD89LB/192.168.1.129','2019-03-31 14:57:00',NULL,2),(60,'Emmi Sibo',NULL,NULL,NULL,'2019-03-31 14:57:01',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-03-31 14:57:13',4),(61,'Junior Ngango',NULL,NULL,NULL,'2019-03-31 15:06:28',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-03-31 15:07:52',3),(62,'Junior Ngango',NULL,NULL,NULL,'2019-03-31 15:20:04',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-03-31 15:24:16',3);
/*!40000 ALTER TABLE `loginhistoric` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menuassignment`
--

DROP TABLE IF EXISTS `menuassignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menuassignment` (
  `menuAssgnId` int(11) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `crtdDtTime` datetime DEFAULT NULL,
  `genericStatus` varchar(255) DEFAULT NULL,
  `optLock` datetime DEFAULT NULL,
  `upDtTime` datetime DEFAULT NULL,
  `updatedBy` varchar(255) DEFAULT NULL,
  `defaultMenuUrl` int(11) DEFAULT NULL,
  `listOfMenu` int(11) DEFAULT NULL,
  `userCategory` int(11) DEFAULT NULL,
  PRIMARY KEY (`menuAssgnId`),
  UNIQUE KEY `listOfMenu` (`listOfMenu`,`userCategory`),
  UNIQUE KEY `defaultMenuUrl` (`defaultMenuUrl`,`userCategory`),
  KEY `FK183F59AC503B2FD5` (`listOfMenu`),
  KEY `FK183F59ACD1CE045F` (`userCategory`),
  KEY `FK183F59AC585B1C50` (`defaultMenuUrl`),
  CONSTRAINT `FK183F59AC503B2FD5` FOREIGN KEY (`listOfMenu`) REFERENCES `listofmenu` (`menuId`),
  CONSTRAINT `FK183F59AC585B1C50` FOREIGN KEY (`defaultMenuUrl`) REFERENCES `listofmenu` (`menuId`),
  CONSTRAINT `FK183F59ACD1CE045F` FOREIGN KEY (`userCategory`) REFERENCES `usercategory` (`userCatid`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menuassignment`
--

LOCK TABLES `menuassignment` WRITE;
/*!40000 ALTER TABLE `menuassignment` DISABLE KEYS */;
INSERT INTO `menuassignment` VALUES (1,NULL,'2019-03-15 12:50:21','active',NULL,NULL,NULL,NULL,1,1),(2,NULL,'2019-03-15 12:50:21','active',NULL,NULL,NULL,NULL,2,1),(6,'admin','2018-10-10 21:32:47','active','2018-10-10 21:32:47','2018-10-10 21:32:47','admin',NULL,3,1),(7,'admin','2018-10-10 21:52:16','active','2018-10-10 21:52:16','2018-10-10 21:52:16','admin',NULL,4,1),(8,'admin','2018-10-10 21:52:55','active','2018-10-10 21:52:55','2018-10-10 21:52:55','admin',NULL,5,1),(9,NULL,'2019-03-24 18:41:11','active',NULL,NULL,NULL,NULL,8,3),(10,NULL,'2019-03-24 18:47:54','active',NULL,NULL,NULL,NULL,9,4),(11,NULL,'2019-03-28 07:34:21','active',NULL,NULL,NULL,NULL,10,3),(12,NULL,'2019-03-28 07:38:22','active',NULL,NULL,NULL,NULL,11,2),(13,NULL,'2019-03-28 21:13:09','active',NULL,NULL,NULL,NULL,12,1),(15,NULL,'2019-03-31 15:17:21','active',NULL,NULL,NULL,NULL,17,3);
/*!40000 ALTER TABLE `menuassignment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menugroup`
--

DROP TABLE IF EXISTS `menugroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menugroup` (
  `menuGroupId` int(11) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `crtdDtTime` datetime DEFAULT NULL,
  `genericStatus` varchar(255) DEFAULT NULL,
  `optLock` datetime DEFAULT NULL,
  `upDtTime` datetime DEFAULT NULL,
  `updatedBy` varchar(255) DEFAULT NULL,
  `creationDate` datetime DEFAULT NULL,
  `defaulGrouptMenu` varchar(255) DEFAULT NULL,
  `groupMenuCode` varchar(255) DEFAULT NULL,
  `iconImage` varchar(255) DEFAULT NULL,
  `menuColor` varchar(255) DEFAULT NULL,
  `menuGroupName` varchar(255) DEFAULT NULL,
  `userCategory` int(11) DEFAULT NULL,
  PRIMARY KEY (`menuGroupId`),
  UNIQUE KEY `groupMenuCode` (`groupMenuCode`),
  KEY `FK1B1E9440D1CE045F` (`userCategory`),
  CONSTRAINT `FK1B1E9440D1CE045F` FOREIGN KEY (`userCategory`) REFERENCES `usercategory` (`userCatid`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menugroup`
--

LOCK TABLES `menugroup` WRITE;
/*!40000 ALTER TABLE `menugroup` DISABLE KEYS */;
INSERT INTO `menugroup` VALUES (1,'Fred','2019-03-15 12:50:07','active',NULL,NULL,NULL,NULL,'My Tasks','M001',NULL,NULL,'My Task',1),(2,'Fred','2019-03-15 12:50:07','active',NULL,NULL,NULL,NULL,'Reports','M002',NULL,NULL,'Reports',1),(7,'admin','2019-03-15 16:43:24','active','2019-03-15 16:43:24','2019-03-15 16:43:24','admin','2019-03-15 16:43:24','My Tasks','M007',NULL,NULL,'My Task',4),(11,'admin','2019-03-15 16:44:07','active','2019-03-15 16:44:07','2019-03-15 16:44:07','admin','2019-03-15 16:44:07','My Tasks','M011',NULL,NULL,'My Task',3),(13,'admin','2019-03-15 16:44:07','active','2019-03-15 16:44:07','2019-03-15 16:44:07','admin','2019-03-15 16:44:07','My Tasks','M013',NULL,NULL,'My Task',2);
/*!40000 ALTER TABLE `menugroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderproduct`
--

DROP TABLE IF EXISTS `orderproduct`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderproduct` (
  `orderProductId` int(11) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `crtdDtTime` datetime DEFAULT NULL,
  `genericStatus` varchar(255) DEFAULT NULL,
  `optLock` datetime DEFAULT NULL,
  `upDtTime` datetime DEFAULT NULL,
  `updatedBy` varchar(255) DEFAULT NULL,
  `orderDate` datetime DEFAULT NULL,
  `quantity` varchar(255) DEFAULT NULL,
  `customer` int(11) DEFAULT NULL,
  `product` int(11) DEFAULT NULL,
  PRIMARY KEY (`orderProductId`),
  KEY `FK2C65FDE1E8365D11` (`product`),
  KEY `FK2C65FDE1EA1001D9` (`customer`),
  CONSTRAINT `FK2C65FDE1E8365D11` FOREIGN KEY (`product`) REFERENCES `product` (`productId`),
  CONSTRAINT `FK2C65FDE1EA1001D9` FOREIGN KEY (`customer`) REFERENCES `users` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderproduct`
--

LOCK TABLES `orderproduct` WRITE;
/*!40000 ALTER TABLE `orderproduct` DISABLE KEYS */;
/*!40000 ALTER TABLE `orderproduct` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perishedproduct`
--

DROP TABLE IF EXISTS `perishedproduct`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `perishedproduct` (
  `perishedId` int(11) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `crtdDtTime` datetime DEFAULT NULL,
  `genericStatus` varchar(255) DEFAULT NULL,
  `optLock` datetime DEFAULT NULL,
  `upDtTime` datetime DEFAULT NULL,
  `updatedBy` varchar(255) DEFAULT NULL,
  `perichedDate` datetime DEFAULT NULL,
  `quantity` varchar(255) DEFAULT NULL,
  `product` int(11) DEFAULT NULL,
  PRIMARY KEY (`perishedId`),
  KEY `FK1395720FE8365D11` (`product`),
  CONSTRAINT `FK1395720FE8365D11` FOREIGN KEY (`product`) REFERENCES `product` (`productId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perishedproduct`
--

LOCK TABLES `perishedproduct` WRITE;
/*!40000 ALTER TABLE `perishedproduct` DISABLE KEYS */;
/*!40000 ALTER TABLE `perishedproduct` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `productId` int(11) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `crtdDtTime` datetime DEFAULT NULL,
  `genericStatus` varchar(255) DEFAULT NULL,
  `optLock` datetime DEFAULT NULL,
  `upDtTime` datetime DEFAULT NULL,
  `updatedBy` varchar(255) DEFAULT NULL,
  `ExpireDate` datetime DEFAULT NULL,
  `ManufactDate` datetime DEFAULT NULL,
  `productName` varchar(255) DEFAULT NULL,
  `purchaseUnitPrice` varchar(255) DEFAULT NULL,
  `quantity` double DEFAULT NULL,
  `sellingUnitPrice` varchar(255) DEFAULT NULL,
  `branch` int(11) DEFAULT NULL,
  `productCategory` int(11) DEFAULT NULL,
  `productImage` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`productId`),
  UNIQUE KEY `productName` (`productName`),
  KEY `FK50C664CF10F49A2D` (`productCategory`),
  KEY `FK50C664CF8514D5B1` (`branch`),
  CONSTRAINT `FK50C664CF10F49A2D` FOREIGN KEY (`productCategory`) REFERENCES `productcategory` (`productCatid`),
  CONSTRAINT `FK50C664CF8514D5B1` FOREIGN KEY (`branch`) REFERENCES `branch` (`branchId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productcategory`
--

DROP TABLE IF EXISTS `productcategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productcategory` (
  `productCatid` int(11) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `crtdDtTime` datetime DEFAULT NULL,
  `genericStatus` varchar(255) DEFAULT NULL,
  `optLock` datetime DEFAULT NULL,
  `upDtTime` datetime DEFAULT NULL,
  `updatedBy` varchar(255) DEFAULT NULL,
  `productCatName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`productCatid`),
  UNIQUE KEY `productCatName` (`productCatName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productcategory`
--

LOCK TABLES `productcategory` WRITE;
/*!40000 ALTER TABLE `productcategory` DISABLE KEYS */;
/*!40000 ALTER TABLE `productcategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `province`
--

DROP TABLE IF EXISTS `province`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `province` (
  `provenceId` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `provenceName_en` varchar(255) DEFAULT NULL,
  `provenceName_fr` varchar(255) DEFAULT NULL,
  `provenceName_rw` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`provenceId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `province`
--

LOCK TABLES `province` WRITE;
/*!40000 ALTER TABLE `province` DISABLE KEYS */;
INSERT INTO `province` VALUES (1,'KGL001','KIGALI CITY','VILLE DE KIGALI','UMUJYI WA KIGALI'),(2,'STH001 ','SOUTHERN','SUD','AMAJYEPFO'),(3,'NTH001 ','NORTHERN','NORD','AMAJYARUGURU'),(4,'EST001 ','EASTERN','EST','UBURASIRAZUBA'),(5,'WST001 ','WESTERN','OUEST','UBURENGERAZUBA');
/*!40000 ALTER TABLE `province` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `soldproduct`
--

DROP TABLE IF EXISTS `soldproduct`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `soldproduct` (
  `soldId` int(11) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `crtdDtTime` datetime DEFAULT NULL,
  `genericStatus` varchar(255) DEFAULT NULL,
  `optLock` datetime DEFAULT NULL,
  `upDtTime` datetime DEFAULT NULL,
  `updatedBy` varchar(255) DEFAULT NULL,
  `quantity` varchar(255) DEFAULT NULL,
  `soldDate` datetime DEFAULT NULL,
  `customer` int(11) DEFAULT NULL,
  `product` int(11) DEFAULT NULL,
  PRIMARY KEY (`soldId`),
  KEY `FKA398C15BE8365D11` (`product`),
  KEY `FKA398C15BEA1001D9` (`customer`),
  CONSTRAINT `FKA398C15BE8365D11` FOREIGN KEY (`product`) REFERENCES `product` (`productId`),
  CONSTRAINT `FKA398C15BEA1001D9` FOREIGN KEY (`customer`) REFERENCES `users` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `soldproduct`
--

LOCK TABLES `soldproduct` WRITE;
/*!40000 ALTER TABLE `soldproduct` DISABLE KEYS */;
/*!40000 ALTER TABLE `soldproduct` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uploadingfiles`
--

DROP TABLE IF EXISTS `uploadingfiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uploadingfiles` (
  `upLoadId` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `crtdDtTime` datetime DEFAULT NULL,
  `genericStatus` varchar(255) DEFAULT NULL,
  `optLock` datetime DEFAULT NULL,
  `upDtTime` datetime DEFAULT NULL,
  `updatedBy` varchar(255) DEFAULT NULL,
  `documents` int(11) DEFAULT NULL,
  `user` int(11) DEFAULT NULL,
  `product` int(11) DEFAULT NULL,
  PRIMARY KEY (`upLoadId`),
  KEY `FKAAB918D6A1F167E3` (`documents`),
  KEY `FKAAB918D6C6256DC6` (`user`),
  KEY `FKAAB918D6E8365D11` (`product`),
  CONSTRAINT `FKAAB918D6E8365D11` FOREIGN KEY (`product`) REFERENCES `product` (`productId`),
  CONSTRAINT `FKAAB918D6A1F167E3` FOREIGN KEY (`documents`) REFERENCES `documents` (`DocId`),
  CONSTRAINT `FKAAB918D6C6256DC6` FOREIGN KEY (`user`) REFERENCES `users` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uploadingfiles`
--

LOCK TABLES `uploadingfiles` WRITE;
/*!40000 ALTER TABLE `uploadingfiles` DISABLE KEYS */;
/*!40000 ALTER TABLE `uploadingfiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usercategory`
--

DROP TABLE IF EXISTS `usercategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usercategory` (
  `userCatid` int(11) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `crtdDtTime` datetime DEFAULT NULL,
  `genericStatus` varchar(255) DEFAULT NULL,
  `optLock` datetime DEFAULT NULL,
  `upDtTime` datetime DEFAULT NULL,
  `updatedBy` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `usercategoryName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userCatid`),
  UNIQUE KEY `usercategoryName` (`usercategoryName`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usercategory`
--

LOCK TABLES `usercategory` WRITE;
/*!40000 ALTER TABLE `usercategory` DISABLE KEYS */;
INSERT INTO `usercategory` VALUES (1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'CEO'),(2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Branch Manager'),(3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Staff'),(4,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Customer');
/*!40000 ALTER TABLE `usercategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `crtdDtTime` datetime DEFAULT NULL,
  `genericStatus` varchar(255) DEFAULT NULL,
  `optLock` datetime DEFAULT NULL,
  `upDtTime` datetime DEFAULT NULL,
  `updatedBy` varchar(255) DEFAULT NULL,
  `DateOfBirth` datetime DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `fname` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `lname` varchar(255) DEFAULT NULL,
  `loginStatus` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `viewId` varchar(255) DEFAULT NULL,
  `viewName` varchar(255) DEFAULT NULL,
  `branch` int(11) DEFAULT NULL,
  `userCategory` int(11) DEFAULT NULL,
  `district` int(11) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `viewId` (`viewId`),
  KEY `FK4E39DE8D1CE045F` (`userCategory`),
  KEY `FK4E39DE88514D5B1` (`branch`),
  KEY `FK4E39DE8B8B00A49` (`district`),
  CONSTRAINT `FK4E39DE88514D5B1` FOREIGN KEY (`branch`) REFERENCES `branch` (`branchId`),
  CONSTRAINT `FK4E39DE8B8B00A49` FOREIGN KEY (`district`) REFERENCES `district` (`districtId`),
  CONSTRAINT `FK4E39DE8D1CE045F` FOREIGN KEY (`userCategory`) REFERENCES `usercategory` (`userCatid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,NULL,'2019-03-15 12:46:54','active',NULL,NULL,NULL,'1987-11-05 12:46:54','Kigali',NULL,'Fred','male','us.png','Ribakare','offline','active','ceo','21232f297a57a5a743894a0e4a801fc3',NULL,1,NULL),(2,NULL,'2019-03-15 12:46:54','active',NULL,NULL,NULL,'1987-11-05 12:46:54','Kigali',NULL,'Cedrick','male','us.png','Barafinda','offline','active','manager','21232f297a57a5a743894a0e4a801fc3',1,2,NULL),(3,NULL,'2019-03-15 12:46:54','active',NULL,NULL,NULL,'1994-03-19 12:46:54','Kigali',NULL,'Junior','male','us.png','Ngango','online','active','staff','21232f297a57a5a743894a0e4a801fc3',1,3,NULL),(4,NULL,'2019-03-15 12:46:55','active',NULL,NULL,NULL,'1991-03-15 12:46:54','Kigali',NULL,'Emmi','male','us.png','Sibo','online','active','super','21232f297a57a5a743894a0e4a801fc3',NULL,4,NULL);
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

-- Dump completed on 2019-03-31 15:27:11
