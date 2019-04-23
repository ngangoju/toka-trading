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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branch`
--

LOCK TABLES `branch` WRITE;
/*!40000 ALTER TABLE `branch` DISABLE KEYS */;
INSERT INTO `branch` VALUES (1,'Junior','2019-03-31 15:22:09','active',NULL,NULL,NULL,'Kicukiro Branch',3),(2,'FredRibakare','2019-04-20 22:03:56','active',NULL,NULL,NULL,'Isange branch',5),(3,'Fred  Ribakare','2019-04-20 22:06:07','active',NULL,NULL,NULL,'Come again branch',6);
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact`
--

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
INSERT INTO `contact` VALUES (1,NULL,NULL,'active',NULL,NULL,NULL,NULL,'sibo2540@gmail.com','0782234029',NULL,4);
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `district`
--

LOCK TABLES `district` WRITE;
/*!40000 ALTER TABLE `district` DISABLE KEYS */;
INSERT INTO `district` VALUES (1,'NYSTR01','Nyarugenge','Nyarugenge','Nyarugenge',1),(2,'GSBSTR02','Gasabo','Gasabo','Gasabo',1),(3,'KCkSTR03','Kicukiro','Kicukiro','Kicukiro',1),(4,'RBVSTR04','Rubavu','Rubavu','Rubavu',5),(5,NULL,'Nyarugenge ',NULL,NULL,NULL),(6,NULL,'Kimironko',NULL,NULL,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `documents`
--

LOCK TABLES `documents` WRITE;
/*!40000 ALTER TABLE `documents` DISABLE KEYS */;
INSERT INTO `documents` VALUES (1,NULL,'2019-03-31 15:46:36',NULL,NULL,NULL,NULL,'E:\\Tressdaressources\\TresProject\\VFPProject_Dev_Workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\toka-trading\\Vfp_Document\\\\',49264,'Agashya-passion-squash-300x300.png','c9a65c08-1164-4c49-8485-9f6c48c7c9a7.Agashya-passion-squash-300x300.png','ProductImage'),(2,NULL,'2019-04-03 17:46:28',NULL,NULL,NULL,NULL,'E:\\Tressdaressources\\TresProject\\VFPProject_Dev_Workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\toka-trading\\Vfp_Document\\\\',29355,'step2.png','287787f1-2cfc-4119-80af-a6ba3041c05e.step2.png','ProductImage'),(3,NULL,'2019-04-03 17:47:10',NULL,NULL,NULL,NULL,'E:\\Tressdaressources\\TresProject\\VFPProject_Dev_Workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\toka-trading\\Vfp_Document\\\\',21160,'Akandi-natural-mineral-water-5L-150x150.png','970c42a5-e4da-468f-9f65-f5869b249f17.Akandi-natural-mineral-water-5L-150x150.png','ProductImage'),(4,NULL,'2019-04-03 18:50:58',NULL,NULL,NULL,NULL,'E:\\Tressdaressources\\TresProject\\VFPProject_Dev_Workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\toka-trading\\Vfp_Document\\\\',448300,'Beverages.jpg','bd97c2d8-ff26-49dc-85d8-516a4c4c5d08.Beverages.jpg','ProductCategoryImage'),(5,NULL,'2019-04-04 10:34:30',NULL,NULL,NULL,NULL,'E:\\Tressdaressources\\TresProject\\VFPProject_Dev_Workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\toka-trading\\Vfp_Document\\\\',49264,'Agashya-passion-squash-300x300.png','eb43fce9-1a6d-484f-aa46-54e30f6a0ec8.Agashya-passion-squash-300x300.png','ProductImage'),(6,NULL,'2019-04-04 10:37:10',NULL,NULL,NULL,NULL,'E:\\Tressdaressources\\TresProject\\VFPProject_Dev_Workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\toka-trading\\Vfp_Document\\\\',21160,'Akandi-natural-mineral-water-5L-150x150.png','98156211-8fd3-4148-8f80-5a097acbc769.Akandi-natural-mineral-water-5L-150x150.png','ProductImage'),(7,NULL,'2019-04-04 14:41:58',NULL,NULL,NULL,NULL,'E:\\Tressdaressources\\TresProject\\VFPProject_Dev_Workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\toka-trading\\Vfp_Document\\\\',49264,'Agashya-passion-squash-300x300.png','665a699f-037f-4f67-bc88-35966058e7bc.Agashya-passion-squash-300x300.png','ProductImage'),(8,NULL,'2019-04-04 15:00:28',NULL,NULL,NULL,NULL,'E:\\Tressdaressources\\TresProject\\VFPProject_Dev_Workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\toka-trading\\Vfp_Document\\\\',21160,'Akandi-natural-mineral-water-5L-150x150.png','d06b0ffb-6d53-4eab-b5c1-2abb333f8543.Akandi-natural-mineral-water-5L-150x150.png','ProductImage'),(9,NULL,'2019-04-04 17:45:26',NULL,NULL,NULL,NULL,'E:\\Tressdaressources\\TresProject\\VFPProject_Dev_Workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\toka-trading\\Vfp_Document\\\\',14370,'Agashya-pineapple-squash-150x150.png','c8f25b95-7bb4-4c81-a00d-1d9a52c9b82a.Agashya-pineapple-squash-150x150.png','ProductImage'),(10,NULL,'2019-04-05 08:54:03',NULL,NULL,NULL,NULL,'E:\\Tressdaressources\\TresProject\\VFPProject_Dev_Workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\toka-trading\\Vfp_Document\\\\',87653,'Sorghum-Flour-300x300.png','562aad22-2e60-4e08-b83b-0be930ac0590.Sorghum-Flour-300x300.png','ProductImage'),(11,NULL,'2019-04-05 08:55:45',NULL,NULL,NULL,NULL,'E:\\Tressdaressources\\TresProject\\VFPProject_Dev_Workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\toka-trading\\Vfp_Document\\\\',95427,'Composite-Flour-300x300.png','15d14c3e-fecd-4902-b009-6315e52b06a4.Composite-Flour-300x300.png','ProductImage'),(12,NULL,'2019-04-05 08:59:00',NULL,NULL,NULL,NULL,'E:\\Tressdaressources\\TresProject\\VFPProject_Dev_Workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\toka-trading\\Vfp_Document\\\\',21160,'Akandi-natural-mineral-water-5L-150x150.png','34a6778c-bc6b-4ac9-8824-75839638963e.Akandi-natural-mineral-water-5L-150x150.png','ProductImage'),(13,NULL,'2019-04-05 08:59:41',NULL,NULL,NULL,NULL,'E:\\Tressdaressources\\TresProject\\VFPProject_Dev_Workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\toka-trading\\Vfp_Document\\\\',49264,'Agashya-passion-squash-300x300.png','99ce8867-b4e3-4cd0-a9ee-e77e7814a4a5.Agashya-passion-squash-300x300.png','ProductImage'),(14,NULL,'2019-04-05 12:50:39',NULL,NULL,NULL,NULL,'E:\\Tressdaressources\\TresProject\\VFPProject_Dev_Workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\toka-trading\\Vfp_Document\\\\',505429,'Spices-category.jpg','7b7a18ab-43f5-4e29-a375-fe09b3521e7d.Spices-category.jpg','ProductCategoryImage'),(15,NULL,'2019-04-05 16:05:43',NULL,NULL,NULL,NULL,'E:\\Tressdaressources\\TresProject\\VFPProject_Dev_Workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\toka-trading\\Vfp_Document\\\\',448300,'Beverages.jpg','90c6823e-8bc6-452e-bd62-925996a32128.Beverages.jpg','ProductCategoryImage'),(16,NULL,'2019-04-05 16:12:53',NULL,NULL,NULL,NULL,'E:\\Tressdaressources\\TresProject\\VFPProject_Dev_Workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\toka-trading\\Vfp_Document\\\\',23903,'Flours-category-267x300.jpg','4bbf3409-5772-49c5-8df7-e0181423e48b.Flours-category-267x300.jpg','ProductCategoryImage'),(17,NULL,'2019-04-05 17:02:21',NULL,NULL,NULL,NULL,'E:\\Tressdaressources\\TresProject\\VFPProject_Dev_Workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\toka-trading\\Vfp_Document\\\\',21160,'Akandi-natural-mineral-water-5L-150x150.png','7db57a97-b2ec-417c-84be-5be95bf1c78c.Akandi-natural-mineral-water-5L-150x150.png','ProductImage'),(18,NULL,'2019-04-07 20:39:10',NULL,NULL,NULL,NULL,'E:\\Tressdaressources\\TresProject\\VFPProject_Dev_Workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\toka-trading\\Vfp_Document\\\\',14370,'Agashya-pineapple-squash-150x150.png','1d474fbf-4f48-4104-b929-048b7f236591.Agashya-pineapple-squash-150x150.png','ProductImage'),(19,NULL,'2019-04-07 20:40:39',NULL,NULL,NULL,NULL,'E:\\Tressdaressources\\TresProject\\VFPProject_Dev_Workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\toka-trading\\Vfp_Document\\\\',95427,'Composite-Flour-300x300.png','6e478948-dd53-424e-ace4-ff11d394b1f7.Composite-Flour-300x300.png','ProductImage'),(21,NULL,'2019-04-20 20:55:55',NULL,NULL,NULL,NULL,'E:\\Tressdaressources\\TresProject\\VFPProject_Dev_Workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\toka-trading\\Vfp_Document\\\\',95427,'Composite-Flour-300x300.png','4729ecaa-de27-4ff5-bb56-3e25142c913a.Composite-Flour-300x300.png','ProductImage'),(22,NULL,'2019-04-20 21:06:22',NULL,NULL,NULL,NULL,'E:\\Tressdaressources\\TresProject\\VFPProject_Dev_Workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\toka-trading\\Vfp_Document\\\\',49264,'Agashya-passion-squash-300x300.png','f39b6617-a8e3-453f-8919-4450c10c2cc5.Agashya-passion-squash-300x300.png','ProductImage'),(23,NULL,'2019-04-20 21:19:07',NULL,NULL,NULL,NULL,'E:\\Tressdaressources\\TresProject\\VFPProject_Dev_Workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\toka-trading\\Vfp_Document\\\\',459664,'Bakery-category.jpg','ab649f34-7815-4595-b66a-f6382cae73cf.Bakery-category.jpg','ProductCategoryImage');
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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `listofmenu`
--

LOCK TABLES `listofmenu` WRITE;
/*!40000 ALTER TABLE `listofmenu` DISABLE KEYS */;
INSERT INTO `listofmenu` VALUES (1,'admin','2019-03-15 12:50:20','active','2019-03-25 03:09:06','2019-03-25 03:09:06','admin','2019-03-14 00:00:00','Users management','glyphicon glyphicon-user','gray2','Url01','/menu/ViewUsersDetails.xhtml',NULL,1),(2,'admin','2019-03-15 12:50:21','active','2019-03-14 02:04:00','2019-03-14 02:04:00','admin','2019-03-14 02:04:00','Profile management','glyphicon glyphicon-user','blue2','Url02','/menu/ViewProfile.xhtml',NULL,1),(3,'admin','2018-10-10 21:32:29','active','2018-10-10 21:32:29','2018-10-10 21:32:29','admin','2018-10-10 21:32:29','Manage MenuGroup','glyphicon glyphicon-asterisk','gray2','Url03','/menu/ViewMenuGroup.xhtml',NULL,1),(4,'admin','2018-10-10 21:49:39','active','2018-10-10 21:49:39','2018-10-10 21:49:39','admin','2018-10-10 21:49:39','List of Menu','glyphicon glyphicon-minus','blue2','Url04','/menu/ViewListOfMenu.xhtml',NULL,1),(5,'admin','2018-10-10 21:51:04','active','2018-10-10 21:51:04','2018-10-10 21:51:04','admin','2018-10-10 21:51:04','Menu Assignment','glyphicon glyphicon-asterisk','gray2','Url05','/menu/menuAssignmentForm.xhtml',NULL,1),(8,'admin','2019-03-24 18:38:11','active','2019-03-06 00:00:00','2019-03-06 00:00:00','admin','2019-03-06 00:00:00','Category management','glyphicon glyphicon-tasks','blue2','Url08','/menu/ProductCategory.xhtml',NULL,1),(9,'admin','2019-03-24 18:44:53','active','2019-03-06 04:12:15','2019-03-06 04:12:15','admin','2019-03-25 00:00:00','Order management','glyphicon glyphicon-shopping-cart','blue2','Url09','/menu/OrderProduct.xhtml',NULL,7),(10,'admin','2019-03-24 18:49:47','active','2019-03-14 02:04:00','2019-03-14 02:04:00','admin','2019-03-14 02:04:00','Sales management','glyphicon glyphicon-usd','blue2','Url10','/menu/SoldProduct.xhtml',NULL,11),(12,'admin','2019-03-28 18:11:31','active','2019-03-28 18:11:31','2019-03-28 18:11:31','admin','2019-03-28 18:11:31','Branch management','glyphicon glyphicon-map-marker','purple2','Url12','/menu/Branch.xhtml',NULL,1),(17,NULL,'2019-03-31 15:16:53','active',NULL,NULL,NULL,NULL,'Product management','glyphicon glyphicon-floppy-save','purple2 ','Url13','/menu/NewProduct.xhtml',NULL,1),(18,NULL,'2019-04-03 19:01:59','active',NULL,NULL,NULL,NULL,'Available Branches','glyphicon glyphicon-list-alt','blue2','Url18','/menu/BranchList.xhtml',NULL,7),(19,NULL,NULL,'active',NULL,NULL,NULL,NULL,'Distributed product',' glyphicon glyphicon-tasks','orange2','Url20','/menu/ProductAssigned.xhtml',NULL,2),(20,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Product sold',' glyphicon glyphicon-usd','dark-gray2','Url21','/menu/SalesProduct.xhtml',NULL,2);
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
) ENGINE=InnoDB AUTO_INCREMENT=302 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loginhistoric`
--

LOCK TABLES `loginhistoric` WRITE;
/*!40000 ALTER TABLE `loginhistoric` DISABLE KEYS */;
INSERT INTO `loginhistoric` VALUES (1,'Fred Ribakare',NULL,NULL,NULL,'2019-03-15 12:45:55',NULL,'NGANGO/192.168.1.117',NULL,'2019-03-15 12:50:36',1),(2,'Fred Ribakare',NULL,NULL,NULL,'2019-03-15 14:53:50',NULL,'NGANGO/192.168.1.117',NULL,'2019-03-15 14:54:17',1),(3,'Cedrick Barafinda',NULL,NULL,NULL,'2019-03-15 16:41:15',NULL,'NGANGO/192.168.1.117',NULL,'2019-03-15 16:51:35',2),(4,'Fred Ribakare',NULL,NULL,NULL,'2019-03-15 17:00:59',NULL,'NGANGO/192.168.1.117',NULL,'2019-03-15 17:01:51',1),(5,'Fred Ribakare',NULL,NULL,NULL,'2019-03-16 12:39:04',NULL,'NGANGO/127.0.0.1',NULL,'2019-03-16 12:39:44',1),(6,'Fred Ribakare',NULL,NULL,NULL,'2019-03-16 12:39:04',NULL,'NGANGO/127.0.0.1','2019-03-16 12:56:17',NULL,1),(7,'Fred Ribakare',NULL,NULL,NULL,'2019-03-16 12:56:19',NULL,'NGANGO/127.0.0.1',NULL,'2019-03-16 13:00:06',1),(8,'Fred Ribakare',NULL,NULL,NULL,'2019-03-16 13:07:06',NULL,'NGANGO/127.0.0.1',NULL,'2019-03-16 13:07:20',1),(9,'Fred Ribakare',NULL,NULL,NULL,'2019-03-16 13:07:06',NULL,'NGANGO/127.0.0.1','2019-03-16 13:08:30',NULL,1),(10,'Fred Ribakare',NULL,NULL,NULL,'2019-03-16 13:08:32',NULL,'NGANGO/127.0.0.1',NULL,'2019-03-16 13:08:50',1),(11,'Fred Ribakare',NULL,NULL,NULL,'2019-03-16 13:08:32',NULL,'NGANGO/127.0.0.1','2019-03-16 13:09:01',NULL,1),(12,'Junior Ngango',NULL,NULL,NULL,'2019-03-16 13:09:03',NULL,'NGANGO/127.0.0.1',NULL,'2019-03-16 13:09:12',3),(13,'Cedrick Barafinda',NULL,NULL,NULL,'2019-03-16 18:50:02',NULL,'NGANGO/127.0.0.1',NULL,'2019-03-16 18:52:00',2),(14,'Cedrick Barafinda',NULL,NULL,NULL,'2019-03-16 18:50:02',NULL,'NGANGO/127.0.0.1','2019-03-16 18:53:27',NULL,2),(15,'Fred Ribakare',NULL,NULL,NULL,'2019-03-16 18:53:29',NULL,'NGANGO/127.0.0.1',NULL,'2019-03-16 18:53:39',1),(16,'Fred Ribakare',NULL,NULL,NULL,'2019-03-18 05:54:19',NULL,'NGANGO/127.0.0.1',NULL,'2019-03-18 05:55:58',1),(17,'Fred Ribakare',NULL,NULL,NULL,'2019-03-18 06:02:17',NULL,'NGANGO/127.0.0.1',NULL,'2019-03-18 06:02:51',1),(18,'Fred Ribakare',NULL,NULL,NULL,'2019-03-18 08:12:40',NULL,'NGANGO/192.168.109.1',NULL,'2019-03-18 08:13:34',1),(19,'Cedrick Barafinda',NULL,NULL,NULL,'2019-03-18 15:12:00',NULL,'NGANGO/192.168.109.1',NULL,'2019-03-18 15:21:40',2),(20,'Fred Ribakare',NULL,NULL,NULL,'2019-03-18 15:50:25',NULL,'NGANGO/192.168.109.1',NULL,'2019-03-18 15:51:14',1),(21,'Fred Ribakare',NULL,NULL,NULL,'2019-03-18 16:11:01',NULL,'NGANGO/192.168.109.1',NULL,'2019-03-18 16:16:22',1),(22,'Fred Ribakare',NULL,NULL,NULL,'2019-03-18 17:32:29',NULL,'NGANGO/192.168.109.1',NULL,'2019-03-18 17:34:38',1),(23,'Fred Ribakare',NULL,NULL,NULL,'2019-03-18 17:32:29',NULL,'NGANGO/192.168.109.1','2019-03-18 17:54:59',NULL,1),(24,'Fred Ribakare',NULL,NULL,NULL,'2019-03-18 17:55:01',NULL,'NGANGO/192.168.109.1',NULL,'2019-03-18 18:00:43',1),(25,'Fred Ribakare',NULL,NULL,NULL,'2019-03-24 14:03:02',NULL,'NGANGO/192.168.1.114',NULL,'2019-03-24 14:03:40',1),(26,'Fred Ribakare',NULL,NULL,NULL,'2019-03-24 14:03:02',NULL,'NGANGO/192.168.1.114','2019-03-24 14:19:14',NULL,1),(27,'Fred Ribakare',NULL,NULL,NULL,'2019-03-24 14:19:15',NULL,'NGANGO/192.168.1.114',NULL,'2019-03-24 14:19:23',1),(28,'Fred Ribakare',NULL,NULL,NULL,'2019-03-24 14:43:18',NULL,'NGANGO/192.168.1.114',NULL,'2019-03-24 14:43:32',1),(29,'Fred Ribakare',NULL,NULL,NULL,'2019-03-24 14:46:01',NULL,'NGANGO/192.168.1.114',NULL,'2019-03-24 14:46:09',1),(30,'Fred Ribakare',NULL,NULL,NULL,'2019-03-24 14:50:43',NULL,'NGANGO/192.168.1.114',NULL,'2019-03-24 15:00:57',1),(31,'Fred Ribakare',NULL,NULL,NULL,'2019-03-28 17:24:27',NULL,'NGANGO/127.0.0.1',NULL,'2019-03-28 17:26:41',1),(32,'Junior Ngango',NULL,NULL,NULL,'2019-03-28 17:35:55',NULL,'NGANGO/127.0.0.1',NULL,'2019-03-28 17:36:38',3),(33,'Fred Ribakare',NULL,NULL,NULL,'2019-03-28 21:46:33',NULL,'NGANGO/127.0.0.1',NULL,'2019-03-28 21:47:25',1),(34,'Fred Ribakare',NULL,NULL,NULL,'2019-03-28 21:46:33',NULL,'NGANGO/127.0.0.1','2019-03-28 22:05:10',NULL,1),(35,'Junior Ngango',NULL,NULL,NULL,'2019-03-28 22:05:11',NULL,'NGANGO/127.0.0.1',NULL,'2019-03-28 22:30:25',3),(36,'Fred Ribakare',NULL,NULL,NULL,'2019-03-29 08:51:56',NULL,'NGANGO/192.168.1.130',NULL,'2019-03-29 08:53:41',1),(37,'Fred Ribakare',NULL,NULL,NULL,'2019-03-29 09:04:21',NULL,'NGANGO/192.168.1.130',NULL,'2019-03-29 09:13:56',1),(38,'Fred Ribakare',NULL,NULL,NULL,'2019-03-29 09:25:09',NULL,'NGANGO/192.168.1.130',NULL,'2019-03-29 09:25:29',1),(39,'Fred Ribakare',NULL,NULL,NULL,'2019-03-29 09:27:45',NULL,'NGANGO/127.0.0.1',NULL,'2019-03-29 09:27:54',1),(40,'Fred Ribakare',NULL,NULL,NULL,'2019-03-29 09:31:45',NULL,'NGANGO/127.0.0.1',NULL,'2019-03-29 09:32:11',1),(41,'Fred Ribakare',NULL,NULL,NULL,'2019-03-29 10:00:15',NULL,'NGANGO/192.168.1.130',NULL,'2019-03-29 10:00:27',1),(42,'Fred Ribakare',NULL,NULL,NULL,'2019-03-30 20:40:17',NULL,'NGANGO/192.168.1.130',NULL,'2019-03-30 20:40:48',1),(43,'Fred Ribakare',NULL,NULL,NULL,'2019-03-30 20:54:28',NULL,'NGANGO/192.168.1.130',NULL,'2019-03-30 20:54:38',1),(44,'Fred Ribakare',NULL,NULL,NULL,'2019-03-30 20:54:28',NULL,'NGANGO/192.168.1.130','2019-03-30 21:42:21',NULL,1),(45,'Junior Ngango',NULL,NULL,NULL,'2019-03-30 21:42:22',NULL,'NGANGO/192.168.1.130',NULL,'2019-03-30 21:42:32',3),(46,'Junior Ngango',NULL,NULL,NULL,'2019-03-30 21:42:22',NULL,'NGANGO/192.168.1.130','2019-03-30 21:49:03',NULL,3),(47,'Fred Ribakare',NULL,NULL,NULL,'2019-03-30 21:49:05',NULL,'NGANGO/192.168.1.130',NULL,'2019-03-30 21:49:12',1),(48,'Junior Ngango',NULL,NULL,NULL,'2019-03-31 14:32:36',NULL,'DESKTOP-BCD89LB/192.168.1.129','2019-03-31 14:39:30',NULL,3),(49,'Junior Ngango',NULL,NULL,NULL,'2019-03-31 14:39:31',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-03-31 14:39:39',3),(50,'Junior Ngango',NULL,NULL,NULL,'2019-03-31 14:39:31',NULL,'DESKTOP-BCD89LB/192.168.1.129','2019-03-31 14:40:38',NULL,3),(51,'Cedrick Barafinda',NULL,NULL,NULL,'2019-03-31 14:40:39',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-03-31 14:40:50',2),(52,'Cedrick Barafinda',NULL,NULL,NULL,'2019-03-31 14:40:39',NULL,'DESKTOP-BCD89LB/192.168.1.129','2019-03-31 14:54:53',NULL,2),(53,'Cedrick Barafinda',NULL,NULL,NULL,'2019-03-31 14:40:39',NULL,'DESKTOP-BCD89LB/192.168.1.129','2019-03-31 14:54:54',NULL,2),(54,'Junior Ngango',NULL,NULL,NULL,'2019-03-31 14:54:55',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-03-31 14:55:01',3),(55,'Junior Ngango',NULL,NULL,NULL,'2019-03-31 14:54:55',NULL,'DESKTOP-BCD89LB/192.168.1.129','2019-03-31 14:56:00',NULL,3),(56,'Fred Ribakare',NULL,NULL,NULL,'2019-03-31 14:56:01',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-03-31 14:56:09',1),(57,'Fred Ribakare',NULL,NULL,NULL,'2019-03-31 14:56:01',NULL,'DESKTOP-BCD89LB/192.168.1.129','2019-03-31 14:56:25',NULL,1),(58,'Cedrick Barafinda',NULL,NULL,NULL,'2019-03-31 14:56:26',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-03-31 14:56:34',2),(59,'Cedrick Barafinda',NULL,NULL,NULL,'2019-03-31 14:56:26',NULL,'DESKTOP-BCD89LB/192.168.1.129','2019-03-31 14:57:00',NULL,2),(60,'Emmi Sibo',NULL,NULL,NULL,'2019-03-31 14:57:01',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-03-31 14:57:13',4),(61,'Junior Ngango',NULL,NULL,NULL,'2019-03-31 15:06:28',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-03-31 15:07:52',3),(62,'Junior Ngango',NULL,NULL,NULL,'2019-03-31 15:20:04',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-03-31 15:24:16',3),(63,'Junior Ngango',NULL,NULL,NULL,'2019-03-31 15:20:04',NULL,'DESKTOP-BCD89LB/192.168.1.129','2019-03-31 15:47:00',NULL,3),(64,'Cedrick Barafinda',NULL,NULL,NULL,'2019-03-31 15:47:01',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-03-31 15:47:09',2),(65,'Cedrick Barafinda',NULL,NULL,NULL,'2019-03-31 15:47:01',NULL,'DESKTOP-BCD89LB/192.168.1.129','2019-03-31 15:47:46',NULL,2),(66,'Junior Ngango',NULL,NULL,NULL,'2019-03-31 15:47:46',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-03-31 15:47:57',3),(67,'Junior Ngango',NULL,NULL,NULL,'2019-04-03 17:42:37',NULL,'DESKTOP-BCD89LB/192.168.1.106',NULL,'2019-04-03 17:45:28',3),(68,'Junior Ngango',NULL,NULL,NULL,'2019-04-03 18:00:03',NULL,'DESKTOP-BCD89LB/192.168.1.106',NULL,'2019-04-03 18:01:01',3),(69,'Junior Ngango',NULL,NULL,NULL,'2019-04-03 18:48:11',NULL,'DESKTOP-BCD89LB/192.168.1.106',NULL,'2019-04-03 18:48:46',3),(70,'Junior Ngango',NULL,NULL,NULL,'2019-04-03 19:07:55',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-04-03 19:08:34',3),(71,'Junior Ngango',NULL,NULL,NULL,'2019-04-03 19:12:44',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-04-03 19:13:11',3),(72,'Junior Ngango',NULL,NULL,NULL,'2019-04-03 19:22:49',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-04-03 19:23:34',3),(73,'Junior Ngango',NULL,NULL,NULL,'2019-04-03 19:27:48',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-04-03 19:28:14',3),(74,'Junior Ngango',NULL,NULL,NULL,'2019-04-04 10:04:51',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-04-04 10:09:52',3),(75,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-04 10:15:20',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-04-04 10:21:31',2),(76,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-04 10:15:20',NULL,'DESKTOP-BCD89LB/192.168.1.129','2019-04-04 10:23:02',NULL,2),(77,'Fred Ribakare',NULL,NULL,NULL,'2019-04-04 10:23:03',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-04-04 10:23:13',1),(78,'Fred Ribakare',NULL,NULL,NULL,'2019-04-04 10:23:03',NULL,'DESKTOP-BCD89LB/192.168.1.129','2019-04-04 10:32:24',NULL,1),(79,'Junior Ngango',NULL,NULL,NULL,'2019-04-04 10:32:26',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-04-04 10:32:36',3),(80,'Junior Ngango',NULL,NULL,NULL,'2019-04-04 10:54:35',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-04-04 10:55:13',3),(81,'Junior Ngango',NULL,NULL,NULL,'2019-04-04 11:14:15',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-04-04 11:16:40',3),(82,'Junior Ngango',NULL,NULL,NULL,'2019-04-04 11:20:28',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-04-04 11:24:16',3),(83,'Junior Ngango',NULL,NULL,NULL,'2019-04-04 14:38:16',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-04-04 14:40:02',3),(84,'Junior Ngango',NULL,NULL,NULL,'2019-04-04 14:53:27',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-04-04 14:54:51',3),(85,'Junior Ngango',NULL,NULL,NULL,'2019-04-04 14:59:27',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-04-04 14:59:46',3),(86,'Junior Ngango',NULL,NULL,NULL,'2019-04-04 14:59:27',NULL,'DESKTOP-BCD89LB/192.168.1.129','2019-04-04 15:00:48',NULL,3),(87,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-04 15:00:48',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-04-04 15:00:59',2),(88,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-04 15:00:48',NULL,'DESKTOP-BCD89LB/192.168.1.129','2019-04-04 15:01:32',NULL,2),(89,'Junior Ngango',NULL,NULL,NULL,'2019-04-04 15:01:33',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-04-04 15:02:55',3),(90,'Junior Ngango',NULL,NULL,NULL,'2019-04-04 15:21:46',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-04-04 15:22:22',3),(91,'Junior Ngango',NULL,NULL,NULL,'2019-04-04 15:21:46',NULL,'DESKTOP-BCD89LB/192.168.1.129','2019-04-04 15:24:35',NULL,3),(92,'Junior Ngango',NULL,NULL,NULL,'2019-04-04 15:24:36',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-04-04 15:24:43',3),(93,'Junior Ngango',NULL,NULL,NULL,'2019-04-04 15:24:36',NULL,'DESKTOP-BCD89LB/192.168.1.129','2019-04-04 15:24:58',NULL,3),(94,'Emmi Sibo',NULL,NULL,NULL,'2019-04-04 15:25:00',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-04-04 15:25:07',4),(95,'Emmi Sibo',NULL,NULL,NULL,'2019-04-04 15:25:00',NULL,'DESKTOP-BCD89LB/192.168.1.129','2019-04-04 15:25:31',NULL,4),(96,'Junior Ngango',NULL,NULL,NULL,'2019-04-04 15:25:31',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-04-04 15:25:38',3),(97,'Junior Ngango',NULL,NULL,NULL,'2019-04-04 16:11:57',NULL,'DESKTOP-BCD89LB/192.168.43.244',NULL,'2019-04-04 16:12:54',3),(98,'Junior Ngango',NULL,NULL,NULL,'2019-04-04 16:21:27',NULL,'DESKTOP-BCD89LB/192.168.43.244',NULL,'2019-04-04 16:24:09',3),(99,'Junior Ngango',NULL,NULL,NULL,'2019-04-04 16:36:25',NULL,'DESKTOP-BCD89LB/192.168.43.244',NULL,'2019-04-04 16:37:23',3),(100,'Junior Ngango',NULL,NULL,NULL,'2019-04-04 16:47:30',NULL,'DESKTOP-BCD89LB/192.168.43.244',NULL,'2019-04-04 16:59:35',3),(101,'Junior Ngango',NULL,NULL,NULL,'2019-04-04 17:10:11',NULL,'DESKTOP-BCD89LB/192.168.43.244',NULL,'2019-04-04 17:16:01',3),(102,'Junior Ngango',NULL,NULL,NULL,'2019-04-04 17:27:02',NULL,'DESKTOP-BCD89LB/192.168.43.244',NULL,'2019-04-04 17:30:58',3),(103,'Junior Ngango',NULL,NULL,NULL,'2019-04-04 17:34:14',NULL,'DESKTOP-BCD89LB/192.168.43.244',NULL,'2019-04-04 17:34:33',3),(104,'Junior Ngango',NULL,NULL,NULL,'2019-04-04 17:41:56',NULL,'DESKTOP-BCD89LB/192.168.43.244',NULL,'2019-04-04 17:42:19',3),(105,'Junior Ngango',NULL,NULL,NULL,'2019-04-04 17:55:52',NULL,'DESKTOP-BCD89LB/192.168.43.244',NULL,'2019-04-04 17:56:13',3),(106,'Junior Ngango',NULL,NULL,NULL,'2019-04-04 18:10:10',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-04 18:10:32',3),(107,'Junior Ngango',NULL,NULL,NULL,'2019-04-05 08:47:16',NULL,'DESKTOP-BCD89LB/192.168.1.137',NULL,'2019-04-05 08:47:49',3),(108,'Junior Ngango',NULL,NULL,NULL,'2019-04-05 08:47:16',NULL,'DESKTOP-BCD89LB/192.168.1.137','2019-04-05 09:00:14',NULL,3),(109,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 09:00:14',NULL,'DESKTOP-BCD89LB/192.168.1.137',NULL,'2019-04-05 09:00:27',2),(110,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 09:00:14',NULL,'DESKTOP-BCD89LB/192.168.1.137','2019-04-05 09:01:16',NULL,2),(111,'Junior Ngango',NULL,NULL,NULL,'2019-04-05 09:01:17',NULL,'DESKTOP-BCD89LB/192.168.1.137',NULL,'2019-04-05 09:01:23',3),(112,'Junior Ngango',NULL,NULL,NULL,'2019-04-05 09:11:24',NULL,'DESKTOP-BCD89LB/192.168.1.137',NULL,'2019-04-05 09:11:58',3),(113,'Junior Ngango',NULL,NULL,NULL,'2019-04-05 09:11:24',NULL,'DESKTOP-BCD89LB/192.168.1.137','2019-04-05 09:16:46',NULL,3),(114,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 09:16:46',NULL,'DESKTOP-BCD89LB/192.168.1.137',NULL,'2019-04-05 09:16:59',2),(115,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 09:16:46',NULL,'DESKTOP-BCD89LB/192.168.1.137','2019-04-05 09:18:35',NULL,2),(116,'Junior Ngango',NULL,NULL,NULL,'2019-04-05 09:18:35',NULL,'DESKTOP-BCD89LB/192.168.1.137',NULL,'2019-04-05 09:18:51',3),(117,'Junior Ngango',NULL,NULL,NULL,'2019-04-05 09:18:35',NULL,'DESKTOP-BCD89LB/192.168.1.137','2019-04-05 09:26:38',NULL,3),(118,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 09:26:39',NULL,'DESKTOP-BCD89LB/192.168.1.137',NULL,'2019-04-05 09:27:09',2),(119,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 09:26:39',NULL,'DESKTOP-BCD89LB/192.168.1.137','2019-04-05 09:27:42',NULL,2),(120,'Junior Ngango',NULL,NULL,NULL,'2019-04-05 09:27:42',NULL,'DESKTOP-BCD89LB/192.168.1.137',NULL,'2019-04-05 09:27:49',3),(121,'Junior Ngango',NULL,NULL,NULL,'2019-04-05 09:27:42',NULL,'DESKTOP-BCD89LB/192.168.1.137','2019-04-05 09:33:01',NULL,3),(122,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 09:33:02',NULL,'DESKTOP-BCD89LB/192.168.1.137',NULL,'2019-04-05 09:33:10',2),(123,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 09:33:02',NULL,'DESKTOP-BCD89LB/192.168.1.137','2019-04-05 09:35:54',NULL,2),(124,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 09:35:54',NULL,'DESKTOP-BCD89LB/192.168.1.137',NULL,'2019-04-05 09:36:02',2),(125,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 09:35:54',NULL,'DESKTOP-BCD89LB/192.168.1.137','2019-04-05 09:38:16',NULL,2),(126,'Junior Ngango',NULL,NULL,NULL,'2019-04-05 09:38:17',NULL,'DESKTOP-BCD89LB/192.168.1.137',NULL,'2019-04-05 09:38:30',3),(127,'Junior Ngango',NULL,NULL,NULL,'2019-04-05 09:40:02',NULL,'DESKTOP-BCD89LB/192.168.1.137',NULL,'2019-04-05 09:40:09',3),(128,'Junior Ngango',NULL,NULL,NULL,'2019-04-05 09:40:02',NULL,'DESKTOP-BCD89LB/192.168.1.137','2019-04-05 09:41:48',NULL,3),(129,'Junior Ngango',NULL,NULL,NULL,'2019-04-05 09:41:48',NULL,'DESKTOP-BCD89LB/192.168.1.137',NULL,'2019-04-05 09:42:01',3),(130,'Junior Ngango',NULL,NULL,NULL,'2019-04-05 09:41:48',NULL,'DESKTOP-BCD89LB/192.168.1.137','2019-04-05 09:42:17',NULL,3),(131,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 09:42:17',NULL,'DESKTOP-BCD89LB/192.168.1.137',NULL,'2019-04-05 09:42:26',2),(132,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 09:42:17',NULL,'DESKTOP-BCD89LB/192.168.1.137','2019-04-05 09:46:50',NULL,2),(133,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 09:46:51',NULL,'DESKTOP-BCD89LB/192.168.1.137',NULL,'2019-04-05 09:47:21',2),(134,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 09:46:51',NULL,'DESKTOP-BCD89LB/192.168.1.137','2019-04-05 09:48:32',NULL,2),(135,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 09:48:32',NULL,'DESKTOP-BCD89LB/192.168.1.137',NULL,'2019-04-05 09:48:43',2),(136,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 10:07:04',NULL,'DESKTOP-BCD89LB/169.254.18.50',NULL,'2019-04-05 10:13:24',2),(137,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 10:55:50',NULL,'DESKTOP-BCD89LB/192.168.109.54',NULL,'2019-04-05 10:56:52',2),(138,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 11:05:07',NULL,'DESKTOP-BCD89LB/192.168.1.109',NULL,'2019-04-05 11:10:34',2),(139,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 11:40:51',NULL,'DESKTOP-BCD89LB/192.168.1.109',NULL,'2019-04-05 11:44:33',2),(140,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 12:26:38',NULL,'DESKTOP-BCD89LB/192.168.1.109',NULL,'2019-04-05 12:27:21',2),(141,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 12:26:38',NULL,'DESKTOP-BCD89LB/192.168.1.109','2019-04-05 12:51:20',NULL,2),(142,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 12:51:21',NULL,'DESKTOP-BCD89LB/192.168.1.109',NULL,'2019-04-05 12:51:31',2),(143,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 12:51:21',NULL,'DESKTOP-BCD89LB/192.168.1.109','2019-04-05 12:51:36',NULL,2),(144,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 12:51:36',NULL,'DESKTOP-BCD89LB/192.168.1.109',NULL,'2019-04-05 12:51:42',2),(145,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 14:59:18',NULL,'DESKTOP-BCD89LB/192.168.1.109',NULL,'2019-04-05 15:09:41',2),(146,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 15:13:42',NULL,'DESKTOP-BCD89LB/192.168.1.109',NULL,'2019-04-05 15:13:58',2),(147,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 15:20:18',NULL,'DESKTOP-BCD89LB/192.168.1.109',NULL,'2019-04-05 15:20:38',2),(148,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 15:20:18',NULL,'DESKTOP-BCD89LB/192.168.1.109','2019-04-05 15:21:37',NULL,2),(149,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 15:21:38',NULL,'DESKTOP-BCD89LB/192.168.1.109',NULL,'2019-04-05 15:21:46',2),(150,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 15:31:21',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-05 15:32:02',2),(151,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 15:31:21',NULL,'DESKTOP-BCD89LB/127.0.0.1','2019-04-05 15:33:08',NULL,2),(152,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 15:33:41',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-05 15:33:48',2),(153,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 15:56:51',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-05 15:57:29',2),(154,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 15:56:51',NULL,'DESKTOP-BCD89LB/127.0.0.1','2019-04-05 16:11:03',NULL,2),(155,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 16:11:04',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-05 16:11:28',2),(156,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 16:11:04',NULL,'DESKTOP-BCD89LB/127.0.0.1','2019-04-05 16:13:56',NULL,2),(157,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 16:13:57',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-05 16:14:50',2),(158,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 16:21:46',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-05 16:22:21',2),(159,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 16:42:08',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-05 16:42:26',2),(160,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 16:47:04',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-05 16:47:28',2),(161,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 16:57:47',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-05 16:58:13',2),(162,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 17:01:07',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-05 17:01:24',2),(163,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-07 20:12:15',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-07 20:14:10',2),(164,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-07 20:18:54',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-07 20:19:55',2),(165,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-07 20:36:36',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-07 20:37:09',2),(166,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-07 21:08:36',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-07 21:09:01',2),(167,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-07 21:14:36',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-07 21:15:00',2),(168,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-07 21:21:35',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-07 21:22:52',2),(169,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-07 21:28:30',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-07 21:28:48',2),(170,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-07 21:37:28',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-07 21:37:46',2),(171,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-07 21:37:28',NULL,'DESKTOP-BCD89LB/127.0.0.1','2019-04-07 21:41:07',NULL,2),(172,'Junior Ngango',NULL,NULL,NULL,'2019-04-07 21:41:08',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-07 21:41:16',3),(173,'Junior Ngango',NULL,NULL,NULL,'2019-04-07 22:58:57',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-07 22:59:24',3),(174,'Junior Ngango',NULL,NULL,NULL,'2019-04-08 09:14:01',NULL,'DESKTOP-BCD89LB/192.168.1.113',NULL,'2019-04-08 09:14:46',3),(175,'Junior Ngango',NULL,NULL,NULL,'2019-04-08 09:47:14',NULL,'DESKTOP-BCD89LB/192.168.1.113',NULL,'2019-04-08 09:47:48',3),(176,'Junior Ngango',NULL,NULL,NULL,'2019-04-08 09:58:24',NULL,'DESKTOP-BCD89LB/192.168.1.113',NULL,'2019-04-08 09:58:55',3),(177,'Junior Ngango',NULL,NULL,NULL,'2019-04-08 10:37:43',NULL,'DESKTOP-BCD89LB/192.168.1.113',NULL,'2019-04-08 10:38:07',3),(178,'Junior Ngango',NULL,NULL,NULL,'2019-04-08 10:46:46',NULL,'DESKTOP-BCD89LB/192.168.1.113',NULL,'2019-04-08 10:47:14',3),(179,'Junior Ngango',NULL,NULL,NULL,'2019-04-08 11:57:25',NULL,'DESKTOP-BCD89LB/192.168.1.113',NULL,'2019-04-08 11:58:16',3),(180,'Junior Ngango',NULL,NULL,NULL,'2019-04-08 12:08:05',NULL,'DESKTOP-BCD89LB/192.168.43.244',NULL,'2019-04-08 12:08:27',3),(181,'Junior Ngango',NULL,NULL,NULL,'2019-04-08 15:06:43',NULL,'DESKTOP-BCD89LB/192.168.1.101',NULL,'2019-04-08 15:07:57',3),(182,'Junior Ngango',NULL,NULL,NULL,'2019-04-08 15:19:54',NULL,'DESKTOP-BCD89LB/192.168.1.101',NULL,'2019-04-08 15:20:55',3),(183,'Junior Ngango',NULL,NULL,NULL,'2019-04-08 15:23:43',NULL,'DESKTOP-BCD89LB/192.168.1.101',NULL,'2019-04-08 15:24:09',3),(184,'Junior Ngango',NULL,NULL,NULL,'2019-04-08 15:38:47',NULL,'DESKTOP-BCD89LB/192.168.1.101',NULL,'2019-04-08 15:39:39',3),(185,'Junior Ngango',NULL,NULL,NULL,'2019-04-08 15:58:08',NULL,'DESKTOP-BCD89LB/192.168.1.101',NULL,'2019-04-08 15:58:40',3),(186,'Junior Ngango',NULL,NULL,NULL,'2019-04-08 16:02:17',NULL,'DESKTOP-BCD89LB/192.168.1.101',NULL,'2019-04-08 16:02:51',3),(187,'Junior Ngango',NULL,NULL,NULL,'2019-04-08 16:11:21',NULL,'DESKTOP-BCD89LB/192.168.1.101',NULL,'2019-04-08 16:11:44',3),(188,'Junior Ngango',NULL,NULL,NULL,'2019-04-08 16:25:00',NULL,'DESKTOP-BCD89LB/192.168.1.101',NULL,'2019-04-08 16:25:22',3),(189,'Junior Ngango',NULL,NULL,NULL,'2019-04-08 16:48:13',NULL,'DESKTOP-BCD89LB/192.168.1.101',NULL,'2019-04-08 16:48:43',3),(190,'Junior Ngango',NULL,NULL,NULL,'2019-04-08 17:08:59',NULL,'DESKTOP-BCD89LB/192.168.1.101',NULL,'2019-04-08 17:09:42',3),(191,'Junior Ngango',NULL,NULL,NULL,'2019-04-08 17:11:40',NULL,'DESKTOP-BCD89LB/192.168.1.101',NULL,'2019-04-08 17:12:08',3),(192,'Junior Ngango',NULL,NULL,NULL,'2019-04-08 17:30:32',NULL,'DESKTOP-BCD89LB/192.168.1.101',NULL,'2019-04-08 17:30:51',3),(193,'Junior Ngango',NULL,NULL,NULL,'2019-04-08 17:34:57',NULL,'DESKTOP-BCD89LB/192.168.1.101',NULL,'2019-04-08 17:35:15',3),(194,'Junior Ngango',NULL,NULL,NULL,'2019-04-08 17:37:35',NULL,'DESKTOP-BCD89LB/192.168.1.101',NULL,'2019-04-08 17:37:50',3),(195,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-08 18:20:49',NULL,'DESKTOP-BCD89LB/192.168.1.101',NULL,'2019-04-08 18:22:14',2),(196,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-08 18:20:49',NULL,'DESKTOP-BCD89LB/192.168.1.101','2019-04-08 18:22:56',NULL,2),(197,'Junior Ngango',NULL,NULL,NULL,'2019-04-08 18:22:56',NULL,'DESKTOP-BCD89LB/192.168.1.101',NULL,'2019-04-08 18:23:06',3),(198,'Junior Ngango',NULL,NULL,NULL,'2019-04-08 18:22:56',NULL,'DESKTOP-BCD89LB/192.168.1.101','2019-04-08 18:24:13',NULL,3),(199,'Fred Ribakare',NULL,NULL,NULL,'2019-04-08 18:24:14',NULL,'DESKTOP-BCD89LB/192.168.1.101',NULL,'2019-04-08 18:24:23',1),(200,'Fred Ribakare',NULL,NULL,NULL,'2019-04-08 18:24:14',NULL,'DESKTOP-BCD89LB/192.168.1.101','2019-04-08 18:26:34',NULL,1),(201,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-08 18:26:35',NULL,'DESKTOP-BCD89LB/192.168.1.101',NULL,'2019-04-08 18:27:24',2),(202,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-08 18:26:35',NULL,'DESKTOP-BCD89LB/192.168.1.101','2019-04-08 18:27:29',NULL,2),(203,'Emmi Sibo',NULL,NULL,NULL,'2019-04-08 18:27:30',NULL,'DESKTOP-BCD89LB/192.168.1.101',NULL,'2019-04-08 18:27:37',4),(204,'Emmi Sibo',NULL,NULL,NULL,'2019-04-08 18:27:30',NULL,'DESKTOP-BCD89LB/192.168.1.101','2019-04-08 18:30:13',NULL,4),(205,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-08 18:30:13',NULL,'DESKTOP-BCD89LB/192.168.1.101',NULL,'2019-04-08 18:30:33',2),(206,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-08 18:30:13',NULL,'DESKTOP-BCD89LB/192.168.1.101','2019-04-08 18:31:38',NULL,2),(207,'Emmi Sibo',NULL,NULL,NULL,'2019-04-08 18:31:39',NULL,'DESKTOP-BCD89LB/192.168.1.101',NULL,'2019-04-08 18:31:48',4),(208,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-19 12:03:26',NULL,'DESKTOP-BCD89LB/192.168.1.123',NULL,'2019-04-19 12:04:06',2),(209,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-19 12:09:28',NULL,'DESKTOP-BCD89LB/192.168.1.123',NULL,'2019-04-19 12:09:48',2),(210,'Junior Ngango',NULL,NULL,NULL,'2019-04-19 12:15:50',NULL,'DESKTOP-BCD89LB/192.168.1.123',NULL,'2019-04-19 12:16:10',3),(211,'Junior Ngango',NULL,NULL,NULL,'2019-04-19 15:42:37',NULL,'DESKTOP-BCD89LB/192.168.1.123',NULL,'2019-04-19 15:42:59',3),(212,'Junior Ngango',NULL,NULL,NULL,'2019-04-19 15:42:37',NULL,'DESKTOP-BCD89LB/192.168.1.123','2019-04-19 15:45:43',NULL,3),(213,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-19 15:45:44',NULL,'DESKTOP-BCD89LB/192.168.1.123',NULL,'2019-04-19 15:45:55',2),(214,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-19 15:45:44',NULL,'DESKTOP-BCD89LB/192.168.1.123','2019-04-19 16:01:29',NULL,2),(215,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-19 16:01:30',NULL,'DESKTOP-BCD89LB/192.168.1.123',NULL,'2019-04-19 16:01:39',2),(216,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-19 16:01:30',NULL,'DESKTOP-BCD89LB/192.168.1.123','2019-04-19 16:02:41',NULL,2),(217,'Fred Ribakare',NULL,NULL,NULL,'2019-04-19 16:02:42',NULL,'DESKTOP-BCD89LB/192.168.1.123',NULL,'2019-04-19 16:02:47',1),(218,'Fred Ribakare',NULL,NULL,NULL,'2019-04-19 16:02:42',NULL,'DESKTOP-BCD89LB/192.168.1.123','2019-04-19 16:09:43',NULL,1),(219,'Fred Ribakare',NULL,NULL,NULL,'2019-04-19 16:09:44',NULL,'DESKTOP-BCD89LB/192.168.1.123',NULL,'2019-04-19 16:09:53',1),(220,'Fred Ribakare',NULL,NULL,NULL,'2019-04-19 16:09:44',NULL,'DESKTOP-BCD89LB/192.168.1.123','2019-04-19 16:10:16',NULL,1),(221,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-19 16:10:17',NULL,'DESKTOP-BCD89LB/192.168.1.123',NULL,'2019-04-19 16:10:24',2),(222,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-19 16:10:17',NULL,'DESKTOP-BCD89LB/192.168.1.123','2019-04-19 16:12:51',NULL,2),(223,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-19 16:12:51',NULL,'DESKTOP-BCD89LB/192.168.1.123',NULL,'2019-04-19 16:12:57',2),(224,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-19 16:12:51',NULL,'DESKTOP-BCD89LB/192.168.1.123','2019-04-19 16:13:08',NULL,2),(225,'Junior Ngango',NULL,NULL,NULL,'2019-04-19 16:13:08',NULL,'DESKTOP-BCD89LB/192.168.1.123',NULL,'2019-04-19 16:13:23',3),(226,'Junior Ngango',NULL,NULL,NULL,'2019-04-19 16:13:08',NULL,'DESKTOP-BCD89LB/192.168.1.123','2019-04-19 16:13:30',NULL,3),(227,'Emmi Sibo',NULL,NULL,NULL,'2019-04-19 16:13:31',NULL,'DESKTOP-BCD89LB/192.168.1.123',NULL,'2019-04-19 16:14:10',4),(228,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-19 16:34:58',NULL,'DESKTOP-BCD89LB/192.168.1.123',NULL,'2019-04-19 16:35:18',2),(229,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-19 16:34:58',NULL,'DESKTOP-BCD89LB/192.168.1.123','2019-04-19 16:36:23',NULL,2),(230,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-19 16:50:00',NULL,'DESKTOP-BCD89LB/192.168.1.123',NULL,'2019-04-19 16:50:18',2),(231,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-19 16:53:01',NULL,'DESKTOP-BCD89LB/192.168.1.123',NULL,'2019-04-19 16:53:29',2),(232,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-19 16:53:01',NULL,'DESKTOP-BCD89LB/192.168.1.123','2019-04-19 16:53:46',NULL,2),(233,'Fred Ribakare',NULL,NULL,NULL,'2019-04-19 16:53:48',NULL,'DESKTOP-BCD89LB/192.168.1.123',NULL,'2019-04-19 16:53:56',1),(234,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-20 19:29:03',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-20 19:30:08',2),(235,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-20 19:32:38',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-20 19:32:57',2),(236,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-20 19:32:38',NULL,'DESKTOP-BCD89LB/127.0.0.1','2019-04-20 19:34:03',NULL,2),(237,'Fred Ribakare',NULL,NULL,NULL,'2019-04-20 19:34:04',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-20 19:34:14',1),(238,'Fred Ribakare',NULL,NULL,NULL,'2019-04-20 20:15:42',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-20 20:16:01',1),(239,'Fred Ribakare',NULL,NULL,NULL,'2019-04-20 20:18:24',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-20 20:18:38',1),(240,'Fred Ribakare',NULL,NULL,NULL,'2019-04-20 20:54:03',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-20 20:54:20',1),(241,'Fred Ribakare',NULL,NULL,NULL,'2019-04-20 21:04:22',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-20 21:04:47',1),(242,'Fred Ribakare',NULL,NULL,NULL,'2019-04-20 22:01:13',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-20 22:01:28',1),(243,'Fred Ribakare',NULL,NULL,NULL,'2019-04-20 22:05:34',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-20 22:05:55',1),(244,'Fred Ribakare',NULL,NULL,NULL,'2019-04-20 22:52:25',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-20 22:52:43',1),(245,'Fred Ribakare',NULL,NULL,NULL,'2019-04-20 23:00:23',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-20 23:00:37',1),(246,'Fred Ribakare',NULL,NULL,NULL,'2019-04-20 23:00:23',NULL,'DESKTOP-BCD89LB/127.0.0.1','2019-04-20 23:13:27',NULL,1),(247,'Fred Ribakare',NULL,NULL,NULL,'2019-04-20 23:13:28',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-20 23:13:34',1),(248,'Fred Ribakare',NULL,NULL,NULL,'2019-04-20 23:58:28',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-20 23:58:45',1),(249,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 00:03:27',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-21 00:03:41',1),(250,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 00:28:33',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-21 00:28:50',1),(251,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 10:33:40',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-21 10:34:11',1),(252,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 10:46:21',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-21 10:46:28',1),(253,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 10:52:37',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-21 10:53:00',1),(254,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 10:57:04',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-21 10:57:11',1),(255,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 11:02:38',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-21 11:02:52',1),(256,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 11:23:05',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-21 11:23:24',1),(257,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 11:23:05',NULL,'DESKTOP-BCD89LB/192.168.1.124','2019-04-21 11:26:10',NULL,1),(258,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 11:26:11',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-21 11:26:17',1),(259,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 11:31:27',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-21 11:31:40',1),(260,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 12:08:22',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-21 12:08:39',1),(261,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 12:13:29',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-21 12:13:45',1),(262,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 12:17:56',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-21 12:18:12',1),(263,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 12:21:33',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-21 12:22:08',1),(264,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 12:24:49',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-21 12:25:05',1),(265,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 12:34:57',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-21 12:35:17',1),(266,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 12:44:40',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-21 12:49:12',1),(267,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 12:52:00',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-21 12:52:17',1),(268,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 13:14:28',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-21 13:15:04',1),(269,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 13:21:35',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-21 13:23:31',1),(270,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 13:31:50',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-21 13:32:07',1),(271,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 13:35:09',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-21 13:35:37',1),(272,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 13:35:09',NULL,'DESKTOP-BCD89LB/127.0.0.1','2019-04-21 13:47:59',NULL,1),(273,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 13:48:02',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-21 13:49:39',1),(274,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 14:08:23',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-21 14:09:21',1),(275,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 14:36:40',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-21 14:37:18',1),(276,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 14:50:22',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-21 14:50:40',1),(277,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 15:06:29',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-21 15:06:58',1),(278,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 15:06:29',NULL,'DESKTOP-BCD89LB/192.168.1.124','2019-04-21 15:13:43',NULL,1),(279,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 15:13:44',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-21 15:13:51',1),(280,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 15:13:44',NULL,'DESKTOP-BCD89LB/192.168.1.124','2019-04-21 15:13:57',NULL,1),(281,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-21 15:13:57',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-21 15:14:04',2),(282,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-21 15:13:57',NULL,'DESKTOP-BCD89LB/192.168.1.124','2019-04-21 15:14:37',NULL,2),(283,'Emmi Sibo',NULL,NULL,NULL,'2019-04-21 15:14:38',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-21 15:14:44',4),(284,'Fred Ribakare',NULL,NULL,NULL,'2019-04-22 08:31:35',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-22 08:34:28',1),(285,'Fred Ribakare',NULL,NULL,NULL,'2019-04-22 09:06:07',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-22 09:06:14',1),(286,'Fred Ribakare',NULL,NULL,NULL,'2019-04-22 09:36:38',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-22 09:36:55',1),(287,'Fred Ribakare',NULL,NULL,NULL,'2019-04-22 12:32:05',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-22 12:32:41',1),(288,'Fred Ribakare',NULL,NULL,NULL,'2019-04-22 12:41:21',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-22 12:41:35',1),(289,'Fred Ribakare',NULL,NULL,NULL,'2019-04-22 12:41:21',NULL,'DESKTOP-BCD89LB/192.168.1.124','2019-04-22 13:45:37',NULL,1),(290,'Fred Ribakare',NULL,NULL,NULL,'2019-04-22 13:45:38',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-22 13:45:50',1),(291,'Fred Ribakare',NULL,NULL,NULL,'2019-04-22 13:48:02',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-22 13:48:19',1),(292,'Fred Ribakare',NULL,NULL,NULL,'2019-04-22 13:48:02',NULL,'DESKTOP-BCD89LB/192.168.1.124','2019-04-22 14:06:08',NULL,1),(293,'Fred Ribakare',NULL,NULL,NULL,'2019-04-22 14:06:09',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-22 14:10:26',1),(294,'Fred Ribakare',NULL,NULL,NULL,'2019-04-22 14:16:09',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-22 14:18:33',1),(295,'Fred Ribakare',NULL,NULL,NULL,'2019-04-22 14:25:07',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-22 14:25:23',1),(296,'Fred Ribakare',NULL,NULL,NULL,'2019-04-22 14:47:20',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-22 14:47:47',1),(297,'Fred Ribakare',NULL,NULL,NULL,'2019-04-22 14:57:36',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-22 14:57:51',1),(298,'Fred Ribakare',NULL,NULL,NULL,'2019-04-22 15:41:32',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-22 15:42:19',1),(299,'Fred Ribakare',NULL,NULL,NULL,'2019-04-23 09:21:52',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-23 09:22:17',1),(300,'Fred Ribakare',NULL,NULL,NULL,'2019-04-23 09:21:52',NULL,'DESKTOP-BCD89LB/192.168.1.124','2019-04-23 09:23:21',NULL,1),(301,'Fred Ribakare',NULL,NULL,NULL,'2019-04-23 09:53:59',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-23 09:54:16',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menuassignment`
--

LOCK TABLES `menuassignment` WRITE;
/*!40000 ALTER TABLE `menuassignment` DISABLE KEYS */;
INSERT INTO `menuassignment` VALUES (1,NULL,'2019-03-15 12:50:21','active',NULL,NULL,NULL,NULL,1,1),(2,NULL,'2019-03-15 12:50:21','active',NULL,NULL,NULL,NULL,2,1),(6,'admin','2018-10-10 21:32:47','active','2018-10-10 21:32:47','2018-10-10 21:32:47','admin',NULL,3,1),(7,'admin','2018-10-10 21:52:16','active','2018-10-10 21:52:16','2018-10-10 21:52:16','admin',NULL,4,1),(8,'admin','2018-10-10 21:52:55','active','2018-10-10 21:52:55','2018-10-10 21:52:55','admin',NULL,5,1),(9,NULL,'2019-03-24 18:41:11','active',NULL,NULL,NULL,NULL,8,1),(10,NULL,'2019-03-24 18:47:54','active',NULL,NULL,NULL,NULL,9,4),(11,NULL,'2019-03-28 07:34:21','active',NULL,NULL,NULL,NULL,10,2),(13,NULL,'2019-03-28 21:13:09','active',NULL,NULL,NULL,NULL,12,1),(15,NULL,'2019-03-31 15:17:21','active',NULL,NULL,NULL,NULL,17,1),(16,NULL,'2019-04-03 19:03:02','active',NULL,NULL,NULL,NULL,18,4),(17,NULL,NULL,'active',NULL,NULL,NULL,NULL,19,1),(18,NULL,NULL,'active',NULL,NULL,NULL,NULL,20,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menugroup`
--

LOCK TABLES `menugroup` WRITE;
/*!40000 ALTER TABLE `menugroup` DISABLE KEYS */;
INSERT INTO `menugroup` VALUES (1,'Fred','2019-03-15 12:50:07','active',NULL,NULL,NULL,NULL,'My Tasks','M001',NULL,NULL,'My Task',1),(2,'Fred','2019-03-15 12:50:07','active',NULL,NULL,NULL,NULL,'Reports','M002',NULL,NULL,'Reports',1),(7,'admin','2019-03-15 16:43:24','active','2019-03-15 16:43:24','2019-03-15 16:43:24','admin','2019-03-15 16:43:24','My Tasks','M007',NULL,NULL,'My Task',4),(11,'admin','2019-03-15 16:44:07','active','2019-03-15 16:44:07','2019-03-15 16:44:07','admin','2019-03-15 16:44:07','My Tasks','M011',NULL,NULL,'My Task',2),(13,'admin','2019-03-15 16:44:07','active','2019-03-15 16:44:07','2019-03-15 16:44:07','admin','2019-03-15 16:44:07','My Tasks','M013',NULL,NULL,'My Task',3),(14,'ceo','2019-04-04 10:24:11','active',NULL,'2019-04-04 10:24:11','ceo','2019-04-04 10:24:11','Report','M004',NULL,NULL,'Reports',3);
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderproduct`
--

LOCK TABLES `orderproduct` WRITE;
/*!40000 ALTER TABLE `orderproduct` DISABLE KEYS */;
INSERT INTO `orderproduct` VALUES (1,NULL,NULL,'active',NULL,NULL,NULL,'2019-04-07 21:46:13','5',4,8),(2,NULL,NULL,'active',NULL,NULL,NULL,'2019-04-07 22:09:37','2',4,7);
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
  `quantity` int(11) DEFAULT NULL,
  `productAssign` int(11) DEFAULT NULL,
  PRIMARY KEY (`perishedId`),
  KEY `FK1395720F3D984F2D` (`productAssign`),
  CONSTRAINT `FK1395720F3D984F2D` FOREIGN KEY (`productAssign`) REFERENCES `productassignment` (`prodAssId`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perishedproduct`
--

LOCK TABLES `perishedproduct` WRITE;
/*!40000 ALTER TABLE `perishedproduct` DISABLE KEYS */;
INSERT INTO `perishedproduct` VALUES (6,'manager','2019-04-07 21:31:24','active',NULL,'2019-04-07 21:31:24',NULL,'2019-04-07 02:00:00',5,NULL),(7,'manager','2019-04-07 21:38:48','active',NULL,'2019-04-07 21:38:48',NULL,'2020-07-14 02:00:00',2,NULL),(8,'manager','2019-04-07 21:40:02','active',NULL,'2019-04-07 21:40:02',NULL,'2019-08-13 02:00:00',5,NULL),(9,'ceo','2019-04-20 20:59:29','active',NULL,'2019-04-20 20:59:29',NULL,'2019-04-20 02:00:00',2,NULL);
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
  `productCategory` int(11) DEFAULT NULL,
  `productImage` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`productId`),
  UNIQUE KEY `productName` (`productName`),
  KEY `FK50C664CF10F49A2D` (`productCategory`),
  CONSTRAINT `FK50C664CF10F49A2D` FOREIGN KEY (`productCategory`) REFERENCES `productcategory` (`productCatid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (2,'staff','2019-04-03 18:55:00','active',NULL,'2019-04-07 21:38:15','manager','2019-12-31 02:00:00','2019-04-03 02:00:00','Agashya','2500.0',8,'3000.0',3,'Agashya-pineapple-squash-150x150.png'),(3,'staff','2019-04-04 14:55:56','active',NULL,'2019-04-07 21:39:37','manager','2019-12-31 02:00:00','2019-04-04 02:00:00','Akandi','600.0',5,'800.0',3,'Akandi-natural-mineral-water-5L-150x150.png'),(7,'manager','2019-04-05 16:13:05','desactive',NULL,'2019-04-07 20:39:44','manager','2020-04-30 02:00:00','2019-04-05 02:00:00','composite Flour','1500.0',0,'2000.0',5,'Composite-Flour-300x300.png'),(8,'manager','2019-04-07 21:28:55','active',NULL,'2019-04-20 20:56:09','ceo','2020-04-07 02:00:00','2019-04-07 02:00:00','Akanozo','1800.0',15,'3000.0',5,'Composite-Flour-300x300.png'),(9,'ceo','2019-04-20 21:01:30','active',NULL,'2019-04-20 21:05:09','ceo','2020-04-30 02:00:00','2019-04-20 02:00:00','Akabanga','1000.0',45,'1500.0',3,'Agashya-passion-squash-300x300.png');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productassignment`
--

DROP TABLE IF EXISTS `productassignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productassignment` (
  `prodAssId` int(11) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `crtdDtTime` datetime DEFAULT NULL,
  `genericStatus` varchar(255) DEFAULT NULL,
  `optLock` datetime DEFAULT NULL,
  `upDtTime` datetime DEFAULT NULL,
  `updatedBy` varchar(255) DEFAULT NULL,
  `assignDate` datetime DEFAULT NULL,
  `quantity` int(11) NOT NULL,
  `product` int(11) DEFAULT NULL,
  `branch` int(11) DEFAULT NULL,
  PRIMARY KEY (`prodAssId`),
  KEY `FK350FCE8365D11` (`product`),
  KEY `FK350FC8514D5B1` (`branch`),
  CONSTRAINT `FK350FC8514D5B1` FOREIGN KEY (`branch`) REFERENCES `branch` (`branchId`),
  CONSTRAINT `FK350FCE8365D11` FOREIGN KEY (`product`) REFERENCES `product` (`productId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productassignment`
--

LOCK TABLES `productassignment` WRITE;
/*!40000 ALTER TABLE `productassignment` DISABLE KEYS */;
INSERT INTO `productassignment` VALUES (1,'Fred Ribakare','2019-04-21 00:28:58','active',NULL,NULL,NULL,'2019-04-21 00:28:58',3,8,1),(2,'Fred Ribakare','2019-04-21 00:32:01','active',NULL,NULL,NULL,'2019-04-21 00:32:01',5,8,2),(3,'Fred Ribakare','2019-04-22 15:42:41','active',NULL,NULL,NULL,'2019-04-22 15:42:41',30,7,3),(4,'Fred Ribakare','2019-04-23 09:54:28','active',NULL,NULL,NULL,'2019-04-23 09:54:28',15,3,3);
/*!40000 ALTER TABLE `productassignment` ENABLE KEYS */;
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
  `categoryImage` varchar(255) DEFAULT NULL,
  `branch` int(11) DEFAULT NULL,
  PRIMARY KEY (`productCatid`),
  UNIQUE KEY `productCatName` (`productCatName`),
  KEY `FKD05546ED8514D5B1` (`branch`),
  CONSTRAINT `FKD05546ED8514D5B1` FOREIGN KEY (`branch`) REFERENCES `branch` (`branchId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productcategory`
--

LOCK TABLES `productcategory` WRITE;
/*!40000 ALTER TABLE `productcategory` DISABLE KEYS */;
INSERT INTO `productcategory` VALUES (3,'manager','2019-04-05 12:47:02','active',NULL,'2019-04-20 19:34:33','ceo','Spices','Spices-category.jpg',1),(4,'manager','2019-04-05 16:05:04','active',NULL,'2019-04-05 16:05:04',NULL,'Beverages','Beverages.jpg',1),(5,'manager','2019-04-05 16:12:23','active',NULL,'2019-04-05 16:12:23',NULL,'Flour','Flours-category-267x300.jpg',1),(6,'ceo','2019-04-20 21:18:01','active',NULL,'2019-04-20 21:18:01',NULL,'Bakery','Bakery-category.jpg',NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `soldproduct`
--

LOCK TABLES `soldproduct` WRITE;
/*!40000 ALTER TABLE `soldproduct` DISABLE KEYS */;
INSERT INTO `soldproduct` VALUES (1,NULL,NULL,'active',NULL,NULL,NULL,'2','2019-04-22 09:32:55',4,8);
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
  `productCategory` int(11) DEFAULT NULL,
  PRIMARY KEY (`upLoadId`),
  KEY `FKAAB918D6A1F167E3` (`documents`),
  KEY `FKAAB918D6C6256DC6` (`user`),
  KEY `FKAAB918D6E8365D11` (`product`),
  KEY `FKAAB918D610F49A2D` (`productCategory`),
  CONSTRAINT `FKAAB918D610F49A2D` FOREIGN KEY (`productCategory`) REFERENCES `productcategory` (`productCatid`),
  CONSTRAINT `FKAAB918D6A1F167E3` FOREIGN KEY (`documents`) REFERENCES `documents` (`DocId`),
  CONSTRAINT `FKAAB918D6C6256DC6` FOREIGN KEY (`user`) REFERENCES `users` (`userId`),
  CONSTRAINT `FKAAB918D6E8365D11` FOREIGN KEY (`product`) REFERENCES `product` (`productId`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uploadingfiles`
--

LOCK TABLES `uploadingfiles` WRITE;
/*!40000 ALTER TABLE `uploadingfiles` DISABLE KEYS */;
INSERT INTO `uploadingfiles` VALUES (14,'manager','2019-04-05 12:50:24','active',NULL,NULL,NULL,14,2,NULL,3),(15,'manager','2019-04-05 16:05:18','active',NULL,NULL,NULL,15,2,NULL,4),(16,'manager','2019-04-05 16:12:34','active',NULL,NULL,NULL,16,2,NULL,5),(17,'manager','2019-04-05 17:02:08','active',NULL,NULL,NULL,17,2,3,NULL),(18,'manager','2019-04-07 20:38:39','active',NULL,NULL,NULL,18,2,2,NULL),(19,'manager','2019-04-07 20:40:22','active',NULL,NULL,NULL,19,2,7,NULL),(21,'ceo','2019-04-20 20:55:37','active',NULL,NULL,NULL,21,1,8,NULL),(22,'ceo','2019-04-20 21:05:44','active',NULL,NULL,NULL,22,1,9,NULL),(23,'ceo','2019-04-20 21:18:55','active',NULL,NULL,NULL,23,1,NULL,6);
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
INSERT INTO `users` VALUES (1,NULL,'2019-03-15 12:46:54','active',NULL,NULL,NULL,'1987-11-05 12:46:54','Kigali',NULL,'Fred','male','us.png','Ribakare','online','active','ceo','21232f297a57a5a743894a0e4a801fc3',NULL,1,NULL),(2,NULL,'2019-03-15 12:46:54','active',NULL,NULL,NULL,'1987-11-05 12:46:54','Kigali',NULL,'Cedrick','male','us.png','Barafinda','offline','active','manager','21232f297a57a5a743894a0e4a801fc3',1,2,NULL),(3,NULL,'2019-03-15 12:46:54','active',NULL,NULL,NULL,'1994-03-19 12:46:54','Kigali',NULL,'Junior','male','us.png','Ngango','offline','active','staff','21232f297a57a5a743894a0e4a801fc3',1,3,NULL),(4,NULL,'2019-03-15 12:46:55','active',NULL,NULL,NULL,'1991-03-15 12:46:54','Kigali',NULL,'Emmi','male','us.png','Sibo','online','active','super','21232f297a57a5a743894a0e4a801fc3',NULL,4,NULL);
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

-- Dump completed on 2019-04-23 10:03:33
