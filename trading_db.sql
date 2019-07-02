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
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `commentId` int(11) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `crtdDtTime` datetime DEFAULT NULL,
  `genericStatus` varchar(255) DEFAULT NULL,
  `optLock` datetime DEFAULT NULL,
  `upDtTime` datetime DEFAULT NULL,
  `updatedBy` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`commentId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact`
--

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
INSERT INTO `contact` VALUES (1,NULL,NULL,'active',NULL,NULL,NULL,NULL,'sibo2540@gmail.com','0782234029',NULL,4),(2,'admin','2019-05-19 13:04:00','active',NULL,NULL,'admin',NULL,'gihaudrey@gmail.com','0783027388',NULL,5),(3,'admin','2019-05-27 21:16:33','active',NULL,NULL,'admin',NULL,'ngangoju@gmail.com','0394894982',NULL,6),(4,'admin','2019-06-01 10:37:13','active',NULL,NULL,'admin',NULL,'nsenga@gael.com','985353635',NULL,7),(5,'admin','2019-06-11 19:07:19','active',NULL,NULL,'admin',NULL,'kagororam@gmail.com','0783847382',NULL,8),(6,'admin','2019-06-11 19:38:18','active',NULL,NULL,'admin',NULL,'hhh@ddd.vv','4567777',NULL,9);
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
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `documents`
--

LOCK TABLES `documents` WRITE;
/*!40000 ALTER TABLE `documents` DISABLE KEYS */;
INSERT INTO `documents` VALUES (1,NULL,'2019-03-31 15:46:36',NULL,NULL,NULL,NULL,'E:\\Tressdaressources\\TresProject\\VFPProject_Dev_Workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\toka-trading\\Vfp_Document\\\\',49264,'Agashya-passion-squash-300x300.png','c9a65c08-1164-4c49-8485-9f6c48c7c9a7.Agashya-passion-squash-300x300.png','ProductImage'),(2,NULL,'2019-04-03 17:46:28',NULL,NULL,NULL,NULL,'E:\\Tressdaressources\\TresProject\\VFPProject_Dev_Workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\toka-trading\\Vfp_Document\\\\',29355,'step2.png','287787f1-2cfc-4119-80af-a6ba3041c05e.step2.png','ProductImage'),(3,NULL,'2019-04-03 17:47:10',NULL,NULL,NULL,NULL,'E:\\Tressdaressources\\TresProject\\VFPProject_Dev_Workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\toka-trading\\Vfp_Document\\\\',21160,'Akandi-natural-mineral-water-5L-150x150.png','970c42a5-e4da-468f-9f65-f5869b249f17.Akandi-natural-mineral-water-5L-150x150.png','ProductImage'),(4,NULL,'2019-04-03 18:50:58',NULL,NULL,NULL,NULL,'E:\\Tressdaressources\\TresProject\\VFPProject_Dev_Workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\toka-trading\\Vfp_Document\\\\',448300,'Beverages.jpg','bd97c2d8-ff26-49dc-85d8-516a4c4c5d08.Beverages.jpg','ProductCategoryImage'),(5,NULL,'2019-04-04 10:34:30',NULL,NULL,NULL,NULL,'E:\\Tressdaressources\\TresProject\\VFPProject_Dev_Workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\toka-trading\\Vfp_Document\\\\',49264,'Agashya-passion-squash-300x300.png','eb43fce9-1a6d-484f-aa46-54e30f6a0ec8.Agashya-passion-squash-300x300.png','ProductImage'),(6,NULL,'2019-04-04 10:37:10',NULL,NULL,NULL,NULL,'E:\\Tressdaressources\\TresProject\\VFPProject_Dev_Workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\toka-trading\\Vfp_Document\\\\',21160,'Akandi-natural-mineral-water-5L-150x150.png','98156211-8fd3-4148-8f80-5a097acbc769.Akandi-natural-mineral-water-5L-150x150.png','ProductImage'),(7,NULL,'2019-04-04 14:41:58',NULL,NULL,NULL,NULL,'E:\\Tressdaressources\\TresProject\\VFPProject_Dev_Workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\toka-trading\\Vfp_Document\\\\',49264,'Agashya-passion-squash-300x300.png','665a699f-037f-4f67-bc88-35966058e7bc.Agashya-passion-squash-300x300.png','ProductImage'),(8,NULL,'2019-04-04 15:00:28',NULL,NULL,NULL,NULL,'E:\\Tressdaressources\\TresProject\\VFPProject_Dev_Workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\toka-trading\\Vfp_Document\\\\',21160,'Akandi-natural-mineral-water-5L-150x150.png','d06b0ffb-6d53-4eab-b5c1-2abb333f8543.Akandi-natural-mineral-water-5L-150x150.png','ProductImage'),(9,NULL,'2019-04-04 17:45:26',NULL,NULL,NULL,NULL,'E:\\Tressdaressources\\TresProject\\VFPProject_Dev_Workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\toka-trading\\Vfp_Document\\\\',14370,'Agashya-pineapple-squash-150x150.png','c8f25b95-7bb4-4c81-a00d-1d9a52c9b82a.Agashya-pineapple-squash-150x150.png','ProductImage'),(10,NULL,'2019-04-05 08:54:03',NULL,NULL,NULL,NULL,'E:\\Tressdaressources\\TresProject\\VFPProject_Dev_Workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\toka-trading\\Vfp_Document\\\\',87653,'Sorghum-Flour-300x300.png','562aad22-2e60-4e08-b83b-0be930ac0590.Sorghum-Flour-300x300.png','ProductImage'),(11,NULL,'2019-04-05 08:55:45',NULL,NULL,NULL,NULL,'E:\\Tressdaressources\\TresProject\\VFPProject_Dev_Workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\toka-trading\\Vfp_Document\\\\',95427,'Composite-Flour-300x300.png','15d14c3e-fecd-4902-b009-6315e52b06a4.Composite-Flour-300x300.png','ProductImage'),(12,NULL,'2019-04-05 08:59:00',NULL,NULL,NULL,NULL,'E:\\Tressdaressources\\TresProject\\VFPProject_Dev_Workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\toka-trading\\Vfp_Document\\\\',21160,'Akandi-natural-mineral-water-5L-150x150.png','34a6778c-bc6b-4ac9-8824-75839638963e.Akandi-natural-mineral-water-5L-150x150.png','ProductImage'),(13,NULL,'2019-04-05 08:59:41',NULL,NULL,NULL,NULL,'E:\\Tressdaressources\\TresProject\\VFPProject_Dev_Workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\toka-trading\\Vfp_Document\\\\',49264,'Agashya-passion-squash-300x300.png','99ce8867-b4e3-4cd0-a9ee-e77e7814a4a5.Agashya-passion-squash-300x300.png','ProductImage'),(14,NULL,'2019-04-05 12:50:39',NULL,NULL,NULL,NULL,'E:\\Tressdaressources\\TresProject\\VFPProject_Dev_Workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\toka-trading\\Vfp_Document\\\\',505429,'Spices-category.jpg','7b7a18ab-43f5-4e29-a375-fe09b3521e7d.Spices-category.jpg','ProductCategoryImage'),(15,NULL,'2019-04-05 16:05:43',NULL,NULL,NULL,NULL,'E:\\Tressdaressources\\TresProject\\VFPProject_Dev_Workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\toka-trading\\Vfp_Document\\\\',448300,'Beverages.jpg','90c6823e-8bc6-452e-bd62-925996a32128.Beverages.jpg','ProductCategoryImage'),(16,NULL,'2019-04-05 16:12:53',NULL,NULL,NULL,NULL,'E:\\Tressdaressources\\TresProject\\VFPProject_Dev_Workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\toka-trading\\Vfp_Document\\\\',23903,'Flours-category-267x300.jpg','4bbf3409-5772-49c5-8df7-e0181423e48b.Flours-category-267x300.jpg','ProductCategoryImage'),(17,NULL,'2019-04-05 17:02:21',NULL,NULL,NULL,NULL,'E:\\Tressdaressources\\TresProject\\VFPProject_Dev_Workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\toka-trading\\Vfp_Document\\\\',21160,'Akandi-natural-mineral-water-5L-150x150.png','7db57a97-b2ec-417c-84be-5be95bf1c78c.Akandi-natural-mineral-water-5L-150x150.png','ProductImage'),(18,NULL,'2019-04-07 20:39:10',NULL,NULL,NULL,NULL,'E:\\Tressdaressources\\TresProject\\VFPProject_Dev_Workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\toka-trading\\Vfp_Document\\\\',14370,'Agashya-pineapple-squash-150x150.png','1d474fbf-4f48-4104-b929-048b7f236591.Agashya-pineapple-squash-150x150.png','ProductImage'),(19,NULL,'2019-04-07 20:40:39',NULL,NULL,NULL,NULL,'E:\\Tressdaressources\\TresProject\\VFPProject_Dev_Workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\toka-trading\\Vfp_Document\\\\',95427,'Composite-Flour-300x300.png','6e478948-dd53-424e-ace4-ff11d394b1f7.Composite-Flour-300x300.png','ProductImage'),(21,NULL,'2019-04-20 20:55:55',NULL,NULL,NULL,NULL,'E:\\Tressdaressources\\TresProject\\VFPProject_Dev_Workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\toka-trading\\Vfp_Document\\\\',95427,'Composite-Flour-300x300.png','4729ecaa-de27-4ff5-bb56-3e25142c913a.Composite-Flour-300x300.png','ProductImage'),(22,NULL,'2019-04-20 21:06:22',NULL,NULL,NULL,NULL,'E:\\Tressdaressources\\TresProject\\VFPProject_Dev_Workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\toka-trading\\Vfp_Document\\\\',49264,'Agashya-passion-squash-300x300.png','f39b6617-a8e3-453f-8919-4450c10c2cc5.Agashya-passion-squash-300x300.png','ProductImage'),(23,NULL,'2019-04-20 21:19:07',NULL,NULL,NULL,NULL,'E:\\Tressdaressources\\TresProject\\VFPProject_Dev_Workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\toka-trading\\Vfp_Document\\\\',459664,'Bakery-category.jpg','ab649f34-7815-4595-b66a-f6382cae73cf.Bakery-category.jpg','ProductCategoryImage'),(24,NULL,'2019-06-11 19:52:58',NULL,NULL,NULL,NULL,'D:\\Projects\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\toka-trading\\Vfp_Document\\\\',93854,'JohnWayneLifeLessons.jpg','c3a3a51f-aee8-4b8e-b7b6-ec134dcce3a9.JohnWayneLifeLessons.jpg','ProductCategoryImage'),(25,NULL,'2019-06-11 20:17:25',NULL,NULL,NULL,NULL,'D:\\Projects\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\toka-trading\\Vfp_Document\\\\',1094,'1.png','8919ddf0-dfcb-4b66-ac22-bbfa0bf729d1.1.png','ProductCategoryImage'),(26,NULL,'2019-06-12 12:48:26',NULL,NULL,NULL,NULL,'D:\\Projects\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\toka-trading\\Vfp_Document\\\\',6041,'WhatsApp Image 2019-06-11 at 10.19.04 AM.jpeg','b4e9830a-31b5-4ebd-a7e3-e6d3a7672bda.WhatsApp Image 2019-06-11 at 10.19.04 AM.jpeg','ProductImage');
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
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `listofmenu`
--

LOCK TABLES `listofmenu` WRITE;
/*!40000 ALTER TABLE `listofmenu` DISABLE KEYS */;
INSERT INTO `listofmenu` VALUES (1,'admin','2019-03-15 12:50:20','active','2019-03-25 03:09:06','2019-03-25 03:09:06','admin','2019-03-14 00:00:00','Users management','glyphicon glyphicon-user','gray2','Url01','/menu/ViewUsersDetails.xhtml',NULL,1),(2,'admin','2019-03-15 12:50:21','active','2019-03-14 02:04:00','2019-03-14 02:04:00','admin','2019-03-14 02:04:00','Profile management','glyphicon glyphicon-user','blue2','Url02','/menu/ViewProfile.xhtml',NULL,1),(3,'admin','2018-10-10 21:32:29','active','2018-10-10 21:32:29','2018-10-10 21:32:29','admin','2018-10-10 21:32:29','Manage MenuGroup','glyphicon glyphicon-asterisk','gray2','Url03','/menu/ViewMenuGroup.xhtml',NULL,1),(4,'admin','2018-10-10 21:49:39','active','2018-10-10 21:49:39','2018-10-10 21:49:39','admin','2018-10-10 21:49:39','List of Menu','glyphicon glyphicon-minus','blue2','Url04','/menu/ViewListOfMenu.xhtml',NULL,1),(5,'admin','2018-10-10 21:51:04','active','2018-10-10 21:51:04','2018-10-10 21:51:04','admin','2018-10-10 21:51:04','Menu Assignment','glyphicon glyphicon-asterisk','gray2','Url05','/menu/menuAssignmentForm.xhtml',NULL,1),(8,'admin','2019-03-24 18:38:11','active','2019-03-06 00:00:00','2019-03-06 00:00:00','admin','2019-03-06 00:00:00','Category management','glyphicon glyphicon-tasks','blue2','Url08','/menu/ProductCategory.xhtml',NULL,1),(9,'admin','2019-03-24 18:44:53','active','2019-03-06 04:12:15','2019-03-06 04:12:15','admin','2019-03-25 00:00:00','Order management','glyphicon glyphicon-shopping-cart','blue2','Url09','/menu/OrderProduct.xhtml',NULL,7),(10,'admin','2019-03-24 18:49:47','active','2019-03-14 02:04:00','2019-03-14 02:04:00','admin','2019-03-14 02:04:00','Sales management','glyphicon glyphicon-usd','blue2','Url10','/menu/SoldProduct.xhtml',NULL,11),(12,'admin','2019-03-28 18:11:31','active','2019-03-28 18:11:31','2019-03-28 18:11:31','admin','2019-03-28 18:11:31','Branch management','glyphicon glyphicon-map-marker','purple2','Url12','/menu/Branch.xhtml',NULL,1),(17,NULL,'2019-03-31 15:16:53','active',NULL,NULL,NULL,NULL,'Product management','glyphicon glyphicon-floppy-save','purple2 ','Url13','/menu/NewProduct.xhtml',NULL,1),(18,NULL,'2019-04-03 19:01:59','active',NULL,NULL,NULL,NULL,'Available Branches','glyphicon glyphicon-list-alt','blue2','Url18','/menu/BranchList.xhtml',NULL,7),(19,NULL,NULL,'active',NULL,NULL,NULL,NULL,'Distributed product',' glyphicon glyphicon-tasks','orange2','Url20','/menu/ProductAssigned.xhtml',NULL,2),(20,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Product sold',' glyphicon glyphicon-usd','dark-gray2','Url21','/menu/SalesProduct.xhtml',NULL,2),(24,NULL,'2019-04-23 17:17:23','active',NULL,NULL,NULL,NULL,'Assigned products','glyphicon glyphicon-list-alt','purple2 ','Url22','/menu/AssignedProduct.xhtml',NULL,11),(25,'ceo','2019-06-16 16:49:44','active',NULL,'2019-06-16 16:49:44','ceo','2019-06-16 16:49:44','Daily processed order','glyphicon glyphicon-euro','green2','url0029','/menu/ProcessedProducts.xhtml',NULL,2),(28,'ceo','2019-06-17 12:32:10','active',NULL,'2019-06-17 12:32:10','ceo','2019-06-17 12:32:10','Branch statistics','glyphicon glyphicon-th-list','purple2','url0450','/menu/ProcessedBranchProducts.xhtml',NULL,15);
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
) ENGINE=InnoDB AUTO_INCREMENT=654 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loginhistoric`
--

LOCK TABLES `loginhistoric` WRITE;
/*!40000 ALTER TABLE `loginhistoric` DISABLE KEYS */;
INSERT INTO `loginhistoric` VALUES (1,'Fred Ribakare',NULL,NULL,NULL,'2019-03-15 12:45:55',NULL,'NGANGO/192.168.1.117',NULL,'2019-03-15 12:50:36',1),(2,'Fred Ribakare',NULL,NULL,NULL,'2019-03-15 14:53:50',NULL,'NGANGO/192.168.1.117',NULL,'2019-03-15 14:54:17',1),(3,'Cedrick Barafinda',NULL,NULL,NULL,'2019-03-15 16:41:15',NULL,'NGANGO/192.168.1.117',NULL,'2019-03-15 16:51:35',2),(4,'Fred Ribakare',NULL,NULL,NULL,'2019-03-15 17:00:59',NULL,'NGANGO/192.168.1.117',NULL,'2019-03-15 17:01:51',1),(5,'Fred Ribakare',NULL,NULL,NULL,'2019-03-16 12:39:04',NULL,'NGANGO/127.0.0.1',NULL,'2019-03-16 12:39:44',1),(6,'Fred Ribakare',NULL,NULL,NULL,'2019-03-16 12:39:04',NULL,'NGANGO/127.0.0.1','2019-03-16 12:56:17',NULL,1),(7,'Fred Ribakare',NULL,NULL,NULL,'2019-03-16 12:56:19',NULL,'NGANGO/127.0.0.1',NULL,'2019-03-16 13:00:06',1),(8,'Fred Ribakare',NULL,NULL,NULL,'2019-03-16 13:07:06',NULL,'NGANGO/127.0.0.1',NULL,'2019-03-16 13:07:20',1),(9,'Fred Ribakare',NULL,NULL,NULL,'2019-03-16 13:07:06',NULL,'NGANGO/127.0.0.1','2019-03-16 13:08:30',NULL,1),(10,'Fred Ribakare',NULL,NULL,NULL,'2019-03-16 13:08:32',NULL,'NGANGO/127.0.0.1',NULL,'2019-03-16 13:08:50',1),(11,'Fred Ribakare',NULL,NULL,NULL,'2019-03-16 13:08:32',NULL,'NGANGO/127.0.0.1','2019-03-16 13:09:01',NULL,1),(12,'Junior Ngango',NULL,NULL,NULL,'2019-03-16 13:09:03',NULL,'NGANGO/127.0.0.1',NULL,'2019-03-16 13:09:12',3),(13,'Cedrick Barafinda',NULL,NULL,NULL,'2019-03-16 18:50:02',NULL,'NGANGO/127.0.0.1',NULL,'2019-03-16 18:52:00',2),(14,'Cedrick Barafinda',NULL,NULL,NULL,'2019-03-16 18:50:02',NULL,'NGANGO/127.0.0.1','2019-03-16 18:53:27',NULL,2),(15,'Fred Ribakare',NULL,NULL,NULL,'2019-03-16 18:53:29',NULL,'NGANGO/127.0.0.1',NULL,'2019-03-16 18:53:39',1),(16,'Fred Ribakare',NULL,NULL,NULL,'2019-03-18 05:54:19',NULL,'NGANGO/127.0.0.1',NULL,'2019-03-18 05:55:58',1),(17,'Fred Ribakare',NULL,NULL,NULL,'2019-03-18 06:02:17',NULL,'NGANGO/127.0.0.1',NULL,'2019-03-18 06:02:51',1),(18,'Fred Ribakare',NULL,NULL,NULL,'2019-03-18 08:12:40',NULL,'NGANGO/192.168.109.1',NULL,'2019-03-18 08:13:34',1),(19,'Cedrick Barafinda',NULL,NULL,NULL,'2019-03-18 15:12:00',NULL,'NGANGO/192.168.109.1',NULL,'2019-03-18 15:21:40',2),(20,'Fred Ribakare',NULL,NULL,NULL,'2019-03-18 15:50:25',NULL,'NGANGO/192.168.109.1',NULL,'2019-03-18 15:51:14',1),(21,'Fred Ribakare',NULL,NULL,NULL,'2019-03-18 16:11:01',NULL,'NGANGO/192.168.109.1',NULL,'2019-03-18 16:16:22',1),(22,'Fred Ribakare',NULL,NULL,NULL,'2019-03-18 17:32:29',NULL,'NGANGO/192.168.109.1',NULL,'2019-03-18 17:34:38',1),(23,'Fred Ribakare',NULL,NULL,NULL,'2019-03-18 17:32:29',NULL,'NGANGO/192.168.109.1','2019-03-18 17:54:59',NULL,1),(24,'Fred Ribakare',NULL,NULL,NULL,'2019-03-18 17:55:01',NULL,'NGANGO/192.168.109.1',NULL,'2019-03-18 18:00:43',1),(25,'Fred Ribakare',NULL,NULL,NULL,'2019-03-24 14:03:02',NULL,'NGANGO/192.168.1.114',NULL,'2019-03-24 14:03:40',1),(26,'Fred Ribakare',NULL,NULL,NULL,'2019-03-24 14:03:02',NULL,'NGANGO/192.168.1.114','2019-03-24 14:19:14',NULL,1),(27,'Fred Ribakare',NULL,NULL,NULL,'2019-03-24 14:19:15',NULL,'NGANGO/192.168.1.114',NULL,'2019-03-24 14:19:23',1),(28,'Fred Ribakare',NULL,NULL,NULL,'2019-03-24 14:43:18',NULL,'NGANGO/192.168.1.114',NULL,'2019-03-24 14:43:32',1),(29,'Fred Ribakare',NULL,NULL,NULL,'2019-03-24 14:46:01',NULL,'NGANGO/192.168.1.114',NULL,'2019-03-24 14:46:09',1),(30,'Fred Ribakare',NULL,NULL,NULL,'2019-03-24 14:50:43',NULL,'NGANGO/192.168.1.114',NULL,'2019-03-24 15:00:57',1),(31,'Fred Ribakare',NULL,NULL,NULL,'2019-03-28 17:24:27',NULL,'NGANGO/127.0.0.1',NULL,'2019-03-28 17:26:41',1),(32,'Junior Ngango',NULL,NULL,NULL,'2019-03-28 17:35:55',NULL,'NGANGO/127.0.0.1',NULL,'2019-03-28 17:36:38',3),(33,'Fred Ribakare',NULL,NULL,NULL,'2019-03-28 21:46:33',NULL,'NGANGO/127.0.0.1',NULL,'2019-03-28 21:47:25',1),(34,'Fred Ribakare',NULL,NULL,NULL,'2019-03-28 21:46:33',NULL,'NGANGO/127.0.0.1','2019-03-28 22:05:10',NULL,1),(35,'Junior Ngango',NULL,NULL,NULL,'2019-03-28 22:05:11',NULL,'NGANGO/127.0.0.1',NULL,'2019-03-28 22:30:25',3),(36,'Fred Ribakare',NULL,NULL,NULL,'2019-03-29 08:51:56',NULL,'NGANGO/192.168.1.130',NULL,'2019-03-29 08:53:41',1),(37,'Fred Ribakare',NULL,NULL,NULL,'2019-03-29 09:04:21',NULL,'NGANGO/192.168.1.130',NULL,'2019-03-29 09:13:56',1),(38,'Fred Ribakare',NULL,NULL,NULL,'2019-03-29 09:25:09',NULL,'NGANGO/192.168.1.130',NULL,'2019-03-29 09:25:29',1),(39,'Fred Ribakare',NULL,NULL,NULL,'2019-03-29 09:27:45',NULL,'NGANGO/127.0.0.1',NULL,'2019-03-29 09:27:54',1),(40,'Fred Ribakare',NULL,NULL,NULL,'2019-03-29 09:31:45',NULL,'NGANGO/127.0.0.1',NULL,'2019-03-29 09:32:11',1),(41,'Fred Ribakare',NULL,NULL,NULL,'2019-03-29 10:00:15',NULL,'NGANGO/192.168.1.130',NULL,'2019-03-29 10:00:27',1),(42,'Fred Ribakare',NULL,NULL,NULL,'2019-03-30 20:40:17',NULL,'NGANGO/192.168.1.130',NULL,'2019-03-30 20:40:48',1),(43,'Fred Ribakare',NULL,NULL,NULL,'2019-03-30 20:54:28',NULL,'NGANGO/192.168.1.130',NULL,'2019-03-30 20:54:38',1),(44,'Fred Ribakare',NULL,NULL,NULL,'2019-03-30 20:54:28',NULL,'NGANGO/192.168.1.130','2019-03-30 21:42:21',NULL,1),(45,'Junior Ngango',NULL,NULL,NULL,'2019-03-30 21:42:22',NULL,'NGANGO/192.168.1.130',NULL,'2019-03-30 21:42:32',3),(46,'Junior Ngango',NULL,NULL,NULL,'2019-03-30 21:42:22',NULL,'NGANGO/192.168.1.130','2019-03-30 21:49:03',NULL,3),(47,'Fred Ribakare',NULL,NULL,NULL,'2019-03-30 21:49:05',NULL,'NGANGO/192.168.1.130',NULL,'2019-03-30 21:49:12',1),(48,'Junior Ngango',NULL,NULL,NULL,'2019-03-31 14:32:36',NULL,'DESKTOP-BCD89LB/192.168.1.129','2019-03-31 14:39:30',NULL,3),(49,'Junior Ngango',NULL,NULL,NULL,'2019-03-31 14:39:31',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-03-31 14:39:39',3),(50,'Junior Ngango',NULL,NULL,NULL,'2019-03-31 14:39:31',NULL,'DESKTOP-BCD89LB/192.168.1.129','2019-03-31 14:40:38',NULL,3),(51,'Cedrick Barafinda',NULL,NULL,NULL,'2019-03-31 14:40:39',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-03-31 14:40:50',2),(52,'Cedrick Barafinda',NULL,NULL,NULL,'2019-03-31 14:40:39',NULL,'DESKTOP-BCD89LB/192.168.1.129','2019-03-31 14:54:53',NULL,2),(53,'Cedrick Barafinda',NULL,NULL,NULL,'2019-03-31 14:40:39',NULL,'DESKTOP-BCD89LB/192.168.1.129','2019-03-31 14:54:54',NULL,2),(54,'Junior Ngango',NULL,NULL,NULL,'2019-03-31 14:54:55',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-03-31 14:55:01',3),(55,'Junior Ngango',NULL,NULL,NULL,'2019-03-31 14:54:55',NULL,'DESKTOP-BCD89LB/192.168.1.129','2019-03-31 14:56:00',NULL,3),(56,'Fred Ribakare',NULL,NULL,NULL,'2019-03-31 14:56:01',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-03-31 14:56:09',1),(57,'Fred Ribakare',NULL,NULL,NULL,'2019-03-31 14:56:01',NULL,'DESKTOP-BCD89LB/192.168.1.129','2019-03-31 14:56:25',NULL,1),(58,'Cedrick Barafinda',NULL,NULL,NULL,'2019-03-31 14:56:26',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-03-31 14:56:34',2),(59,'Cedrick Barafinda',NULL,NULL,NULL,'2019-03-31 14:56:26',NULL,'DESKTOP-BCD89LB/192.168.1.129','2019-03-31 14:57:00',NULL,2),(60,'Emmi Sibo',NULL,NULL,NULL,'2019-03-31 14:57:01',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-03-31 14:57:13',4),(61,'Junior Ngango',NULL,NULL,NULL,'2019-03-31 15:06:28',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-03-31 15:07:52',3),(62,'Junior Ngango',NULL,NULL,NULL,'2019-03-31 15:20:04',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-03-31 15:24:16',3),(63,'Junior Ngango',NULL,NULL,NULL,'2019-03-31 15:20:04',NULL,'DESKTOP-BCD89LB/192.168.1.129','2019-03-31 15:47:00',NULL,3),(64,'Cedrick Barafinda',NULL,NULL,NULL,'2019-03-31 15:47:01',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-03-31 15:47:09',2),(65,'Cedrick Barafinda',NULL,NULL,NULL,'2019-03-31 15:47:01',NULL,'DESKTOP-BCD89LB/192.168.1.129','2019-03-31 15:47:46',NULL,2),(66,'Junior Ngango',NULL,NULL,NULL,'2019-03-31 15:47:46',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-03-31 15:47:57',3),(67,'Junior Ngango',NULL,NULL,NULL,'2019-04-03 17:42:37',NULL,'DESKTOP-BCD89LB/192.168.1.106',NULL,'2019-04-03 17:45:28',3),(68,'Junior Ngango',NULL,NULL,NULL,'2019-04-03 18:00:03',NULL,'DESKTOP-BCD89LB/192.168.1.106',NULL,'2019-04-03 18:01:01',3),(69,'Junior Ngango',NULL,NULL,NULL,'2019-04-03 18:48:11',NULL,'DESKTOP-BCD89LB/192.168.1.106',NULL,'2019-04-03 18:48:46',3),(70,'Junior Ngango',NULL,NULL,NULL,'2019-04-03 19:07:55',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-04-03 19:08:34',3),(71,'Junior Ngango',NULL,NULL,NULL,'2019-04-03 19:12:44',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-04-03 19:13:11',3),(72,'Junior Ngango',NULL,NULL,NULL,'2019-04-03 19:22:49',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-04-03 19:23:34',3),(73,'Junior Ngango',NULL,NULL,NULL,'2019-04-03 19:27:48',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-04-03 19:28:14',3),(74,'Junior Ngango',NULL,NULL,NULL,'2019-04-04 10:04:51',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-04-04 10:09:52',3),(75,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-04 10:15:20',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-04-04 10:21:31',2),(76,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-04 10:15:20',NULL,'DESKTOP-BCD89LB/192.168.1.129','2019-04-04 10:23:02',NULL,2),(77,'Fred Ribakare',NULL,NULL,NULL,'2019-04-04 10:23:03',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-04-04 10:23:13',1),(78,'Fred Ribakare',NULL,NULL,NULL,'2019-04-04 10:23:03',NULL,'DESKTOP-BCD89LB/192.168.1.129','2019-04-04 10:32:24',NULL,1),(79,'Junior Ngango',NULL,NULL,NULL,'2019-04-04 10:32:26',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-04-04 10:32:36',3),(80,'Junior Ngango',NULL,NULL,NULL,'2019-04-04 10:54:35',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-04-04 10:55:13',3),(81,'Junior Ngango',NULL,NULL,NULL,'2019-04-04 11:14:15',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-04-04 11:16:40',3),(82,'Junior Ngango',NULL,NULL,NULL,'2019-04-04 11:20:28',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-04-04 11:24:16',3),(83,'Junior Ngango',NULL,NULL,NULL,'2019-04-04 14:38:16',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-04-04 14:40:02',3),(84,'Junior Ngango',NULL,NULL,NULL,'2019-04-04 14:53:27',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-04-04 14:54:51',3),(85,'Junior Ngango',NULL,NULL,NULL,'2019-04-04 14:59:27',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-04-04 14:59:46',3),(86,'Junior Ngango',NULL,NULL,NULL,'2019-04-04 14:59:27',NULL,'DESKTOP-BCD89LB/192.168.1.129','2019-04-04 15:00:48',NULL,3),(87,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-04 15:00:48',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-04-04 15:00:59',2),(88,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-04 15:00:48',NULL,'DESKTOP-BCD89LB/192.168.1.129','2019-04-04 15:01:32',NULL,2),(89,'Junior Ngango',NULL,NULL,NULL,'2019-04-04 15:01:33',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-04-04 15:02:55',3),(90,'Junior Ngango',NULL,NULL,NULL,'2019-04-04 15:21:46',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-04-04 15:22:22',3),(91,'Junior Ngango',NULL,NULL,NULL,'2019-04-04 15:21:46',NULL,'DESKTOP-BCD89LB/192.168.1.129','2019-04-04 15:24:35',NULL,3),(92,'Junior Ngango',NULL,NULL,NULL,'2019-04-04 15:24:36',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-04-04 15:24:43',3),(93,'Junior Ngango',NULL,NULL,NULL,'2019-04-04 15:24:36',NULL,'DESKTOP-BCD89LB/192.168.1.129','2019-04-04 15:24:58',NULL,3),(94,'Emmi Sibo',NULL,NULL,NULL,'2019-04-04 15:25:00',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-04-04 15:25:07',4),(95,'Emmi Sibo',NULL,NULL,NULL,'2019-04-04 15:25:00',NULL,'DESKTOP-BCD89LB/192.168.1.129','2019-04-04 15:25:31',NULL,4),(96,'Junior Ngango',NULL,NULL,NULL,'2019-04-04 15:25:31',NULL,'DESKTOP-BCD89LB/192.168.1.129',NULL,'2019-04-04 15:25:38',3),(97,'Junior Ngango',NULL,NULL,NULL,'2019-04-04 16:11:57',NULL,'DESKTOP-BCD89LB/192.168.43.244',NULL,'2019-04-04 16:12:54',3),(98,'Junior Ngango',NULL,NULL,NULL,'2019-04-04 16:21:27',NULL,'DESKTOP-BCD89LB/192.168.43.244',NULL,'2019-04-04 16:24:09',3),(99,'Junior Ngango',NULL,NULL,NULL,'2019-04-04 16:36:25',NULL,'DESKTOP-BCD89LB/192.168.43.244',NULL,'2019-04-04 16:37:23',3),(100,'Junior Ngango',NULL,NULL,NULL,'2019-04-04 16:47:30',NULL,'DESKTOP-BCD89LB/192.168.43.244',NULL,'2019-04-04 16:59:35',3),(101,'Junior Ngango',NULL,NULL,NULL,'2019-04-04 17:10:11',NULL,'DESKTOP-BCD89LB/192.168.43.244',NULL,'2019-04-04 17:16:01',3),(102,'Junior Ngango',NULL,NULL,NULL,'2019-04-04 17:27:02',NULL,'DESKTOP-BCD89LB/192.168.43.244',NULL,'2019-04-04 17:30:58',3),(103,'Junior Ngango',NULL,NULL,NULL,'2019-04-04 17:34:14',NULL,'DESKTOP-BCD89LB/192.168.43.244',NULL,'2019-04-04 17:34:33',3),(104,'Junior Ngango',NULL,NULL,NULL,'2019-04-04 17:41:56',NULL,'DESKTOP-BCD89LB/192.168.43.244',NULL,'2019-04-04 17:42:19',3),(105,'Junior Ngango',NULL,NULL,NULL,'2019-04-04 17:55:52',NULL,'DESKTOP-BCD89LB/192.168.43.244',NULL,'2019-04-04 17:56:13',3),(106,'Junior Ngango',NULL,NULL,NULL,'2019-04-04 18:10:10',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-04 18:10:32',3),(107,'Junior Ngango',NULL,NULL,NULL,'2019-04-05 08:47:16',NULL,'DESKTOP-BCD89LB/192.168.1.137',NULL,'2019-04-05 08:47:49',3),(108,'Junior Ngango',NULL,NULL,NULL,'2019-04-05 08:47:16',NULL,'DESKTOP-BCD89LB/192.168.1.137','2019-04-05 09:00:14',NULL,3),(109,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 09:00:14',NULL,'DESKTOP-BCD89LB/192.168.1.137',NULL,'2019-04-05 09:00:27',2),(110,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 09:00:14',NULL,'DESKTOP-BCD89LB/192.168.1.137','2019-04-05 09:01:16',NULL,2),(111,'Junior Ngango',NULL,NULL,NULL,'2019-04-05 09:01:17',NULL,'DESKTOP-BCD89LB/192.168.1.137',NULL,'2019-04-05 09:01:23',3),(112,'Junior Ngango',NULL,NULL,NULL,'2019-04-05 09:11:24',NULL,'DESKTOP-BCD89LB/192.168.1.137',NULL,'2019-04-05 09:11:58',3),(113,'Junior Ngango',NULL,NULL,NULL,'2019-04-05 09:11:24',NULL,'DESKTOP-BCD89LB/192.168.1.137','2019-04-05 09:16:46',NULL,3),(114,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 09:16:46',NULL,'DESKTOP-BCD89LB/192.168.1.137',NULL,'2019-04-05 09:16:59',2),(115,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 09:16:46',NULL,'DESKTOP-BCD89LB/192.168.1.137','2019-04-05 09:18:35',NULL,2),(116,'Junior Ngango',NULL,NULL,NULL,'2019-04-05 09:18:35',NULL,'DESKTOP-BCD89LB/192.168.1.137',NULL,'2019-04-05 09:18:51',3),(117,'Junior Ngango',NULL,NULL,NULL,'2019-04-05 09:18:35',NULL,'DESKTOP-BCD89LB/192.168.1.137','2019-04-05 09:26:38',NULL,3),(118,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 09:26:39',NULL,'DESKTOP-BCD89LB/192.168.1.137',NULL,'2019-04-05 09:27:09',2),(119,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 09:26:39',NULL,'DESKTOP-BCD89LB/192.168.1.137','2019-04-05 09:27:42',NULL,2),(120,'Junior Ngango',NULL,NULL,NULL,'2019-04-05 09:27:42',NULL,'DESKTOP-BCD89LB/192.168.1.137',NULL,'2019-04-05 09:27:49',3),(121,'Junior Ngango',NULL,NULL,NULL,'2019-04-05 09:27:42',NULL,'DESKTOP-BCD89LB/192.168.1.137','2019-04-05 09:33:01',NULL,3),(122,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 09:33:02',NULL,'DESKTOP-BCD89LB/192.168.1.137',NULL,'2019-04-05 09:33:10',2),(123,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 09:33:02',NULL,'DESKTOP-BCD89LB/192.168.1.137','2019-04-05 09:35:54',NULL,2),(124,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 09:35:54',NULL,'DESKTOP-BCD89LB/192.168.1.137',NULL,'2019-04-05 09:36:02',2),(125,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 09:35:54',NULL,'DESKTOP-BCD89LB/192.168.1.137','2019-04-05 09:38:16',NULL,2),(126,'Junior Ngango',NULL,NULL,NULL,'2019-04-05 09:38:17',NULL,'DESKTOP-BCD89LB/192.168.1.137',NULL,'2019-04-05 09:38:30',3),(127,'Junior Ngango',NULL,NULL,NULL,'2019-04-05 09:40:02',NULL,'DESKTOP-BCD89LB/192.168.1.137',NULL,'2019-04-05 09:40:09',3),(128,'Junior Ngango',NULL,NULL,NULL,'2019-04-05 09:40:02',NULL,'DESKTOP-BCD89LB/192.168.1.137','2019-04-05 09:41:48',NULL,3),(129,'Junior Ngango',NULL,NULL,NULL,'2019-04-05 09:41:48',NULL,'DESKTOP-BCD89LB/192.168.1.137',NULL,'2019-04-05 09:42:01',3),(130,'Junior Ngango',NULL,NULL,NULL,'2019-04-05 09:41:48',NULL,'DESKTOP-BCD89LB/192.168.1.137','2019-04-05 09:42:17',NULL,3),(131,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 09:42:17',NULL,'DESKTOP-BCD89LB/192.168.1.137',NULL,'2019-04-05 09:42:26',2),(132,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 09:42:17',NULL,'DESKTOP-BCD89LB/192.168.1.137','2019-04-05 09:46:50',NULL,2),(133,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 09:46:51',NULL,'DESKTOP-BCD89LB/192.168.1.137',NULL,'2019-04-05 09:47:21',2),(134,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 09:46:51',NULL,'DESKTOP-BCD89LB/192.168.1.137','2019-04-05 09:48:32',NULL,2),(135,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 09:48:32',NULL,'DESKTOP-BCD89LB/192.168.1.137',NULL,'2019-04-05 09:48:43',2),(136,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 10:07:04',NULL,'DESKTOP-BCD89LB/169.254.18.50',NULL,'2019-04-05 10:13:24',2),(137,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 10:55:50',NULL,'DESKTOP-BCD89LB/192.168.109.54',NULL,'2019-04-05 10:56:52',2),(138,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 11:05:07',NULL,'DESKTOP-BCD89LB/192.168.1.109',NULL,'2019-04-05 11:10:34',2),(139,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 11:40:51',NULL,'DESKTOP-BCD89LB/192.168.1.109',NULL,'2019-04-05 11:44:33',2),(140,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 12:26:38',NULL,'DESKTOP-BCD89LB/192.168.1.109',NULL,'2019-04-05 12:27:21',2),(141,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 12:26:38',NULL,'DESKTOP-BCD89LB/192.168.1.109','2019-04-05 12:51:20',NULL,2),(142,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 12:51:21',NULL,'DESKTOP-BCD89LB/192.168.1.109',NULL,'2019-04-05 12:51:31',2),(143,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 12:51:21',NULL,'DESKTOP-BCD89LB/192.168.1.109','2019-04-05 12:51:36',NULL,2),(144,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 12:51:36',NULL,'DESKTOP-BCD89LB/192.168.1.109',NULL,'2019-04-05 12:51:42',2),(145,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 14:59:18',NULL,'DESKTOP-BCD89LB/192.168.1.109',NULL,'2019-04-05 15:09:41',2),(146,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 15:13:42',NULL,'DESKTOP-BCD89LB/192.168.1.109',NULL,'2019-04-05 15:13:58',2),(147,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 15:20:18',NULL,'DESKTOP-BCD89LB/192.168.1.109',NULL,'2019-04-05 15:20:38',2),(148,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 15:20:18',NULL,'DESKTOP-BCD89LB/192.168.1.109','2019-04-05 15:21:37',NULL,2),(149,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 15:21:38',NULL,'DESKTOP-BCD89LB/192.168.1.109',NULL,'2019-04-05 15:21:46',2),(150,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 15:31:21',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-05 15:32:02',2),(151,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 15:31:21',NULL,'DESKTOP-BCD89LB/127.0.0.1','2019-04-05 15:33:08',NULL,2),(152,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 15:33:41',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-05 15:33:48',2),(153,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 15:56:51',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-05 15:57:29',2),(154,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 15:56:51',NULL,'DESKTOP-BCD89LB/127.0.0.1','2019-04-05 16:11:03',NULL,2),(155,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 16:11:04',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-05 16:11:28',2),(156,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 16:11:04',NULL,'DESKTOP-BCD89LB/127.0.0.1','2019-04-05 16:13:56',NULL,2),(157,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 16:13:57',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-05 16:14:50',2),(158,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 16:21:46',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-05 16:22:21',2),(159,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 16:42:08',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-05 16:42:26',2),(160,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 16:47:04',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-05 16:47:28',2),(161,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 16:57:47',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-05 16:58:13',2),(162,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-05 17:01:07',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-05 17:01:24',2),(163,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-07 20:12:15',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-07 20:14:10',2),(164,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-07 20:18:54',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-07 20:19:55',2),(165,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-07 20:36:36',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-07 20:37:09',2),(166,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-07 21:08:36',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-07 21:09:01',2),(167,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-07 21:14:36',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-07 21:15:00',2),(168,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-07 21:21:35',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-07 21:22:52',2),(169,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-07 21:28:30',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-07 21:28:48',2),(170,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-07 21:37:28',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-07 21:37:46',2),(171,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-07 21:37:28',NULL,'DESKTOP-BCD89LB/127.0.0.1','2019-04-07 21:41:07',NULL,2),(172,'Junior Ngango',NULL,NULL,NULL,'2019-04-07 21:41:08',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-07 21:41:16',3),(173,'Junior Ngango',NULL,NULL,NULL,'2019-04-07 22:58:57',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-07 22:59:24',3),(174,'Junior Ngango',NULL,NULL,NULL,'2019-04-08 09:14:01',NULL,'DESKTOP-BCD89LB/192.168.1.113',NULL,'2019-04-08 09:14:46',3),(175,'Junior Ngango',NULL,NULL,NULL,'2019-04-08 09:47:14',NULL,'DESKTOP-BCD89LB/192.168.1.113',NULL,'2019-04-08 09:47:48',3),(176,'Junior Ngango',NULL,NULL,NULL,'2019-04-08 09:58:24',NULL,'DESKTOP-BCD89LB/192.168.1.113',NULL,'2019-04-08 09:58:55',3),(177,'Junior Ngango',NULL,NULL,NULL,'2019-04-08 10:37:43',NULL,'DESKTOP-BCD89LB/192.168.1.113',NULL,'2019-04-08 10:38:07',3),(178,'Junior Ngango',NULL,NULL,NULL,'2019-04-08 10:46:46',NULL,'DESKTOP-BCD89LB/192.168.1.113',NULL,'2019-04-08 10:47:14',3),(179,'Junior Ngango',NULL,NULL,NULL,'2019-04-08 11:57:25',NULL,'DESKTOP-BCD89LB/192.168.1.113',NULL,'2019-04-08 11:58:16',3),(180,'Junior Ngango',NULL,NULL,NULL,'2019-04-08 12:08:05',NULL,'DESKTOP-BCD89LB/192.168.43.244',NULL,'2019-04-08 12:08:27',3),(181,'Junior Ngango',NULL,NULL,NULL,'2019-04-08 15:06:43',NULL,'DESKTOP-BCD89LB/192.168.1.101',NULL,'2019-04-08 15:07:57',3),(182,'Junior Ngango',NULL,NULL,NULL,'2019-04-08 15:19:54',NULL,'DESKTOP-BCD89LB/192.168.1.101',NULL,'2019-04-08 15:20:55',3),(183,'Junior Ngango',NULL,NULL,NULL,'2019-04-08 15:23:43',NULL,'DESKTOP-BCD89LB/192.168.1.101',NULL,'2019-04-08 15:24:09',3),(184,'Junior Ngango',NULL,NULL,NULL,'2019-04-08 15:38:47',NULL,'DESKTOP-BCD89LB/192.168.1.101',NULL,'2019-04-08 15:39:39',3),(185,'Junior Ngango',NULL,NULL,NULL,'2019-04-08 15:58:08',NULL,'DESKTOP-BCD89LB/192.168.1.101',NULL,'2019-04-08 15:58:40',3),(186,'Junior Ngango',NULL,NULL,NULL,'2019-04-08 16:02:17',NULL,'DESKTOP-BCD89LB/192.168.1.101',NULL,'2019-04-08 16:02:51',3),(187,'Junior Ngango',NULL,NULL,NULL,'2019-04-08 16:11:21',NULL,'DESKTOP-BCD89LB/192.168.1.101',NULL,'2019-04-08 16:11:44',3),(188,'Junior Ngango',NULL,NULL,NULL,'2019-04-08 16:25:00',NULL,'DESKTOP-BCD89LB/192.168.1.101',NULL,'2019-04-08 16:25:22',3),(189,'Junior Ngango',NULL,NULL,NULL,'2019-04-08 16:48:13',NULL,'DESKTOP-BCD89LB/192.168.1.101',NULL,'2019-04-08 16:48:43',3),(190,'Junior Ngango',NULL,NULL,NULL,'2019-04-08 17:08:59',NULL,'DESKTOP-BCD89LB/192.168.1.101',NULL,'2019-04-08 17:09:42',3),(191,'Junior Ngango',NULL,NULL,NULL,'2019-04-08 17:11:40',NULL,'DESKTOP-BCD89LB/192.168.1.101',NULL,'2019-04-08 17:12:08',3),(192,'Junior Ngango',NULL,NULL,NULL,'2019-04-08 17:30:32',NULL,'DESKTOP-BCD89LB/192.168.1.101',NULL,'2019-04-08 17:30:51',3),(193,'Junior Ngango',NULL,NULL,NULL,'2019-04-08 17:34:57',NULL,'DESKTOP-BCD89LB/192.168.1.101',NULL,'2019-04-08 17:35:15',3),(194,'Junior Ngango',NULL,NULL,NULL,'2019-04-08 17:37:35',NULL,'DESKTOP-BCD89LB/192.168.1.101',NULL,'2019-04-08 17:37:50',3),(195,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-08 18:20:49',NULL,'DESKTOP-BCD89LB/192.168.1.101',NULL,'2019-04-08 18:22:14',2),(196,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-08 18:20:49',NULL,'DESKTOP-BCD89LB/192.168.1.101','2019-04-08 18:22:56',NULL,2),(197,'Junior Ngango',NULL,NULL,NULL,'2019-04-08 18:22:56',NULL,'DESKTOP-BCD89LB/192.168.1.101',NULL,'2019-04-08 18:23:06',3),(198,'Junior Ngango',NULL,NULL,NULL,'2019-04-08 18:22:56',NULL,'DESKTOP-BCD89LB/192.168.1.101','2019-04-08 18:24:13',NULL,3),(199,'Fred Ribakare',NULL,NULL,NULL,'2019-04-08 18:24:14',NULL,'DESKTOP-BCD89LB/192.168.1.101',NULL,'2019-04-08 18:24:23',1),(200,'Fred Ribakare',NULL,NULL,NULL,'2019-04-08 18:24:14',NULL,'DESKTOP-BCD89LB/192.168.1.101','2019-04-08 18:26:34',NULL,1),(201,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-08 18:26:35',NULL,'DESKTOP-BCD89LB/192.168.1.101',NULL,'2019-04-08 18:27:24',2),(202,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-08 18:26:35',NULL,'DESKTOP-BCD89LB/192.168.1.101','2019-04-08 18:27:29',NULL,2),(203,'Emmi Sibo',NULL,NULL,NULL,'2019-04-08 18:27:30',NULL,'DESKTOP-BCD89LB/192.168.1.101',NULL,'2019-04-08 18:27:37',4),(204,'Emmi Sibo',NULL,NULL,NULL,'2019-04-08 18:27:30',NULL,'DESKTOP-BCD89LB/192.168.1.101','2019-04-08 18:30:13',NULL,4),(205,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-08 18:30:13',NULL,'DESKTOP-BCD89LB/192.168.1.101',NULL,'2019-04-08 18:30:33',2),(206,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-08 18:30:13',NULL,'DESKTOP-BCD89LB/192.168.1.101','2019-04-08 18:31:38',NULL,2),(207,'Emmi Sibo',NULL,NULL,NULL,'2019-04-08 18:31:39',NULL,'DESKTOP-BCD89LB/192.168.1.101',NULL,'2019-04-08 18:31:48',4),(208,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-19 12:03:26',NULL,'DESKTOP-BCD89LB/192.168.1.123',NULL,'2019-04-19 12:04:06',2),(209,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-19 12:09:28',NULL,'DESKTOP-BCD89LB/192.168.1.123',NULL,'2019-04-19 12:09:48',2),(210,'Junior Ngango',NULL,NULL,NULL,'2019-04-19 12:15:50',NULL,'DESKTOP-BCD89LB/192.168.1.123',NULL,'2019-04-19 12:16:10',3),(211,'Junior Ngango',NULL,NULL,NULL,'2019-04-19 15:42:37',NULL,'DESKTOP-BCD89LB/192.168.1.123',NULL,'2019-04-19 15:42:59',3),(212,'Junior Ngango',NULL,NULL,NULL,'2019-04-19 15:42:37',NULL,'DESKTOP-BCD89LB/192.168.1.123','2019-04-19 15:45:43',NULL,3),(213,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-19 15:45:44',NULL,'DESKTOP-BCD89LB/192.168.1.123',NULL,'2019-04-19 15:45:55',2),(214,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-19 15:45:44',NULL,'DESKTOP-BCD89LB/192.168.1.123','2019-04-19 16:01:29',NULL,2),(215,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-19 16:01:30',NULL,'DESKTOP-BCD89LB/192.168.1.123',NULL,'2019-04-19 16:01:39',2),(216,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-19 16:01:30',NULL,'DESKTOP-BCD89LB/192.168.1.123','2019-04-19 16:02:41',NULL,2),(217,'Fred Ribakare',NULL,NULL,NULL,'2019-04-19 16:02:42',NULL,'DESKTOP-BCD89LB/192.168.1.123',NULL,'2019-04-19 16:02:47',1),(218,'Fred Ribakare',NULL,NULL,NULL,'2019-04-19 16:02:42',NULL,'DESKTOP-BCD89LB/192.168.1.123','2019-04-19 16:09:43',NULL,1),(219,'Fred Ribakare',NULL,NULL,NULL,'2019-04-19 16:09:44',NULL,'DESKTOP-BCD89LB/192.168.1.123',NULL,'2019-04-19 16:09:53',1),(220,'Fred Ribakare',NULL,NULL,NULL,'2019-04-19 16:09:44',NULL,'DESKTOP-BCD89LB/192.168.1.123','2019-04-19 16:10:16',NULL,1),(221,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-19 16:10:17',NULL,'DESKTOP-BCD89LB/192.168.1.123',NULL,'2019-04-19 16:10:24',2),(222,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-19 16:10:17',NULL,'DESKTOP-BCD89LB/192.168.1.123','2019-04-19 16:12:51',NULL,2),(223,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-19 16:12:51',NULL,'DESKTOP-BCD89LB/192.168.1.123',NULL,'2019-04-19 16:12:57',2),(224,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-19 16:12:51',NULL,'DESKTOP-BCD89LB/192.168.1.123','2019-04-19 16:13:08',NULL,2),(225,'Junior Ngango',NULL,NULL,NULL,'2019-04-19 16:13:08',NULL,'DESKTOP-BCD89LB/192.168.1.123',NULL,'2019-04-19 16:13:23',3),(226,'Junior Ngango',NULL,NULL,NULL,'2019-04-19 16:13:08',NULL,'DESKTOP-BCD89LB/192.168.1.123','2019-04-19 16:13:30',NULL,3),(227,'Emmi Sibo',NULL,NULL,NULL,'2019-04-19 16:13:31',NULL,'DESKTOP-BCD89LB/192.168.1.123',NULL,'2019-04-19 16:14:10',4),(228,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-19 16:34:58',NULL,'DESKTOP-BCD89LB/192.168.1.123',NULL,'2019-04-19 16:35:18',2),(229,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-19 16:34:58',NULL,'DESKTOP-BCD89LB/192.168.1.123','2019-04-19 16:36:23',NULL,2),(230,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-19 16:50:00',NULL,'DESKTOP-BCD89LB/192.168.1.123',NULL,'2019-04-19 16:50:18',2),(231,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-19 16:53:01',NULL,'DESKTOP-BCD89LB/192.168.1.123',NULL,'2019-04-19 16:53:29',2),(232,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-19 16:53:01',NULL,'DESKTOP-BCD89LB/192.168.1.123','2019-04-19 16:53:46',NULL,2),(233,'Fred Ribakare',NULL,NULL,NULL,'2019-04-19 16:53:48',NULL,'DESKTOP-BCD89LB/192.168.1.123',NULL,'2019-04-19 16:53:56',1),(234,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-20 19:29:03',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-20 19:30:08',2),(235,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-20 19:32:38',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-20 19:32:57',2),(236,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-20 19:32:38',NULL,'DESKTOP-BCD89LB/127.0.0.1','2019-04-20 19:34:03',NULL,2),(237,'Fred Ribakare',NULL,NULL,NULL,'2019-04-20 19:34:04',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-20 19:34:14',1),(238,'Fred Ribakare',NULL,NULL,NULL,'2019-04-20 20:15:42',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-20 20:16:01',1),(239,'Fred Ribakare',NULL,NULL,NULL,'2019-04-20 20:18:24',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-20 20:18:38',1),(240,'Fred Ribakare',NULL,NULL,NULL,'2019-04-20 20:54:03',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-20 20:54:20',1),(241,'Fred Ribakare',NULL,NULL,NULL,'2019-04-20 21:04:22',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-20 21:04:47',1),(242,'Fred Ribakare',NULL,NULL,NULL,'2019-04-20 22:01:13',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-20 22:01:28',1),(243,'Fred Ribakare',NULL,NULL,NULL,'2019-04-20 22:05:34',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-20 22:05:55',1),(244,'Fred Ribakare',NULL,NULL,NULL,'2019-04-20 22:52:25',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-20 22:52:43',1),(245,'Fred Ribakare',NULL,NULL,NULL,'2019-04-20 23:00:23',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-20 23:00:37',1),(246,'Fred Ribakare',NULL,NULL,NULL,'2019-04-20 23:00:23',NULL,'DESKTOP-BCD89LB/127.0.0.1','2019-04-20 23:13:27',NULL,1),(247,'Fred Ribakare',NULL,NULL,NULL,'2019-04-20 23:13:28',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-20 23:13:34',1),(248,'Fred Ribakare',NULL,NULL,NULL,'2019-04-20 23:58:28',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-20 23:58:45',1),(249,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 00:03:27',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-21 00:03:41',1),(250,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 00:28:33',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-21 00:28:50',1),(251,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 10:33:40',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-21 10:34:11',1),(252,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 10:46:21',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-21 10:46:28',1),(253,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 10:52:37',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-21 10:53:00',1),(254,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 10:57:04',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-21 10:57:11',1),(255,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 11:02:38',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-21 11:02:52',1),(256,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 11:23:05',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-21 11:23:24',1),(257,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 11:23:05',NULL,'DESKTOP-BCD89LB/192.168.1.124','2019-04-21 11:26:10',NULL,1),(258,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 11:26:11',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-21 11:26:17',1),(259,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 11:31:27',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-21 11:31:40',1),(260,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 12:08:22',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-21 12:08:39',1),(261,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 12:13:29',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-21 12:13:45',1),(262,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 12:17:56',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-21 12:18:12',1),(263,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 12:21:33',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-21 12:22:08',1),(264,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 12:24:49',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-21 12:25:05',1),(265,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 12:34:57',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-21 12:35:17',1),(266,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 12:44:40',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-21 12:49:12',1),(267,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 12:52:00',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-21 12:52:17',1),(268,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 13:14:28',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-21 13:15:04',1),(269,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 13:21:35',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-21 13:23:31',1),(270,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 13:31:50',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-21 13:32:07',1),(271,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 13:35:09',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-21 13:35:37',1),(272,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 13:35:09',NULL,'DESKTOP-BCD89LB/127.0.0.1','2019-04-21 13:47:59',NULL,1),(273,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 13:48:02',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-04-21 13:49:39',1),(274,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 14:08:23',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-21 14:09:21',1),(275,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 14:36:40',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-21 14:37:18',1),(276,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 14:50:22',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-21 14:50:40',1),(277,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 15:06:29',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-21 15:06:58',1),(278,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 15:06:29',NULL,'DESKTOP-BCD89LB/192.168.1.124','2019-04-21 15:13:43',NULL,1),(279,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 15:13:44',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-21 15:13:51',1),(280,'Fred Ribakare',NULL,NULL,NULL,'2019-04-21 15:13:44',NULL,'DESKTOP-BCD89LB/192.168.1.124','2019-04-21 15:13:57',NULL,1),(281,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-21 15:13:57',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-21 15:14:04',2),(282,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-21 15:13:57',NULL,'DESKTOP-BCD89LB/192.168.1.124','2019-04-21 15:14:37',NULL,2),(283,'Emmi Sibo',NULL,NULL,NULL,'2019-04-21 15:14:38',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-21 15:14:44',4),(284,'Fred Ribakare',NULL,NULL,NULL,'2019-04-22 08:31:35',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-22 08:34:28',1),(285,'Fred Ribakare',NULL,NULL,NULL,'2019-04-22 09:06:07',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-22 09:06:14',1),(286,'Fred Ribakare',NULL,NULL,NULL,'2019-04-22 09:36:38',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-22 09:36:55',1),(287,'Fred Ribakare',NULL,NULL,NULL,'2019-04-22 12:32:05',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-22 12:32:41',1),(288,'Fred Ribakare',NULL,NULL,NULL,'2019-04-22 12:41:21',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-22 12:41:35',1),(289,'Fred Ribakare',NULL,NULL,NULL,'2019-04-22 12:41:21',NULL,'DESKTOP-BCD89LB/192.168.1.124','2019-04-22 13:45:37',NULL,1),(290,'Fred Ribakare',NULL,NULL,NULL,'2019-04-22 13:45:38',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-22 13:45:50',1),(291,'Fred Ribakare',NULL,NULL,NULL,'2019-04-22 13:48:02',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-22 13:48:19',1),(292,'Fred Ribakare',NULL,NULL,NULL,'2019-04-22 13:48:02',NULL,'DESKTOP-BCD89LB/192.168.1.124','2019-04-22 14:06:08',NULL,1),(293,'Fred Ribakare',NULL,NULL,NULL,'2019-04-22 14:06:09',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-22 14:10:26',1),(294,'Fred Ribakare',NULL,NULL,NULL,'2019-04-22 14:16:09',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-22 14:18:33',1),(295,'Fred Ribakare',NULL,NULL,NULL,'2019-04-22 14:25:07',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-22 14:25:23',1),(296,'Fred Ribakare',NULL,NULL,NULL,'2019-04-22 14:47:20',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-22 14:47:47',1),(297,'Fred Ribakare',NULL,NULL,NULL,'2019-04-22 14:57:36',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-22 14:57:51',1),(298,'Fred Ribakare',NULL,NULL,NULL,'2019-04-22 15:41:32',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-22 15:42:19',1),(299,'Fred Ribakare',NULL,NULL,NULL,'2019-04-23 09:21:52',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-23 09:22:17',1),(300,'Fred Ribakare',NULL,NULL,NULL,'2019-04-23 09:21:52',NULL,'DESKTOP-BCD89LB/192.168.1.124','2019-04-23 09:23:21',NULL,1),(301,'Fred Ribakare',NULL,NULL,NULL,'2019-04-23 09:53:59',NULL,'DESKTOP-BCD89LB/192.168.1.124',NULL,'2019-04-23 09:54:16',1),(302,'Fred Ribakare',NULL,NULL,NULL,'2019-04-23 18:42:08',NULL,'NGANGO/192.168.1.137',NULL,'2019-04-23 18:42:51',1),(303,'Fred Ribakare',NULL,NULL,NULL,'2019-04-23 18:42:08',NULL,'NGANGO/192.168.1.137','2019-04-23 18:44:58',NULL,1),(304,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-23 18:44:59',NULL,'NGANGO/192.168.1.137',NULL,'2019-04-23 18:45:13',2),(305,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-23 18:44:59',NULL,'NGANGO/192.168.1.137','2019-04-23 18:47:12',NULL,2),(306,'Emmi Sibo',NULL,NULL,NULL,'2019-04-23 18:47:12',NULL,'NGANGO/192.168.1.137',NULL,'2019-04-23 18:47:32',4),(307,'Emmi Sibo',NULL,NULL,NULL,'2019-04-23 18:47:12',NULL,'NGANGO/192.168.1.137','2019-04-23 18:54:56',NULL,4),(308,'Emmi Sibo',NULL,NULL,NULL,'2019-04-23 18:54:57',NULL,'NGANGO/192.168.1.137',NULL,'2019-04-23 18:55:21',4),(309,'Emmi Sibo',NULL,NULL,NULL,'2019-04-23 18:54:57',NULL,'NGANGO/192.168.1.137','2019-04-23 18:55:41',NULL,4),(310,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-23 18:55:41',NULL,'NGANGO/192.168.1.137',NULL,'2019-04-23 18:55:51',2),(311,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-23 18:58:33',NULL,'NGANGO/192.168.1.137',NULL,'2019-04-23 18:58:59',2),(312,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-23 19:15:33',NULL,'NGANGO/192.168.1.137',NULL,'2019-04-23 19:16:05',2),(313,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-23 19:15:33',NULL,'NGANGO/192.168.1.137','2019-04-23 19:22:22',NULL,2),(314,'Emmi Sibo',NULL,NULL,NULL,'2019-04-23 19:22:23',NULL,'NGANGO/192.168.1.137',NULL,'2019-04-23 19:22:31',4),(315,'Emmi Sibo',NULL,NULL,NULL,'2019-04-23 19:38:09',NULL,'NGANGO/192.168.1.137',NULL,'2019-04-23 19:39:47',4),(316,'Emmi Sibo',NULL,NULL,NULL,'2019-04-23 19:55:54',NULL,'NGANGO/192.168.1.137',NULL,'2019-04-23 19:56:38',4),(317,'Emmi Sibo',NULL,NULL,NULL,'2019-04-23 20:22:02',NULL,'NGANGO/192.168.1.137',NULL,'2019-04-23 20:23:14',4),(318,'Emmi Sibo',NULL,NULL,NULL,'2019-04-23 20:22:02',NULL,'NGANGO/192.168.1.137','2019-04-23 20:32:23',NULL,4),(319,'Fred Ribakare',NULL,NULL,NULL,'2019-04-23 20:32:24',NULL,'NGANGO/192.168.1.137',NULL,'2019-04-23 20:32:33',1),(320,'Fred Ribakare',NULL,NULL,NULL,'2019-04-23 20:32:24',NULL,'NGANGO/192.168.1.137','2019-04-23 20:43:20',NULL,1),(321,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-23 20:43:20',NULL,'NGANGO/192.168.1.137',NULL,'2019-04-23 20:43:29',2),(322,'Cedrick Barafinda',NULL,NULL,NULL,'2019-04-23 20:43:20',NULL,'NGANGO/192.168.1.137','2019-04-23 20:44:21',NULL,2),(323,'Emmi Sibo',NULL,NULL,NULL,'2019-04-23 20:44:21',NULL,'NGANGO/192.168.1.137',NULL,'2019-04-23 20:44:30',4),(324,'Emmi Sibo',NULL,NULL,NULL,'2019-04-24 11:29:36',NULL,'NGANGO/192.168.1.137',NULL,'2019-04-24 11:30:32',4),(325,'Emmi Sibo',NULL,NULL,NULL,'2019-04-24 11:59:15',NULL,'NGANGO/192.168.1.137',NULL,'2019-04-24 12:01:03',4),(326,'Emmi Sibo',NULL,NULL,NULL,'2019-04-24 13:04:02',NULL,'NGANGO/192.168.1.137',NULL,'2019-04-24 13:07:41',4),(327,'Fred Ribakare',NULL,NULL,NULL,'2019-04-24 15:12:04',NULL,'NGANGO/192.168.109.1',NULL,'2019-04-24 15:23:24',1),(328,'Emmi Sibo',NULL,NULL,NULL,'2019-05-01 09:08:24',NULL,'NGANGO/127.0.0.1',NULL,'2019-05-01 09:32:12',4),(329,'Emmi Sibo',NULL,NULL,NULL,'2019-05-01 09:08:24',NULL,'NGANGO/127.0.0.1','2019-05-01 09:34:34',NULL,4),(330,'Cedrick Barafinda',NULL,NULL,NULL,'2019-05-01 09:34:34',NULL,'NGANGO/127.0.0.1',NULL,'2019-05-01 09:34:43',2),(331,'Cedrick Barafinda',NULL,NULL,NULL,'2019-05-01 09:34:34',NULL,'NGANGO/127.0.0.1','2019-05-01 09:36:05',NULL,2),(332,'Fred Ribakare',NULL,NULL,NULL,'2019-05-01 09:36:05',NULL,'NGANGO/127.0.0.1',NULL,'2019-05-01 09:36:46',1),(333,'Cedrick Barafinda',NULL,NULL,NULL,'2019-05-03 09:15:21',NULL,'NGANGO/192.168.1.123',NULL,'2019-05-03 09:18:23',2),(334,'Cedrick Barafinda',NULL,NULL,NULL,'2019-05-03 09:15:21',NULL,'NGANGO/192.168.1.123','2019-05-03 09:19:08',NULL,2),(335,'Fred Ribakare',NULL,NULL,NULL,'2019-05-03 09:19:10',NULL,'NGANGO/192.168.1.123',NULL,'2019-05-03 09:19:20',1),(336,'Fred Ribakare',NULL,NULL,NULL,'2019-05-03 09:19:10',NULL,'NGANGO/192.168.1.123','2019-05-03 09:31:40',NULL,1),(337,'Emmi Sibo',NULL,NULL,NULL,'2019-05-03 09:31:42',NULL,'NGANGO/192.168.1.123',NULL,'2019-05-03 09:32:05',4),(338,'Cedrick Barafinda',NULL,NULL,NULL,'2019-05-05 14:26:20',NULL,'NGANGO/192.168.1.123',NULL,'2019-05-05 14:26:34',2),(339,'Cedrick Barafinda',NULL,NULL,NULL,'2019-05-05 14:26:20',NULL,'NGANGO/192.168.1.123','2019-05-05 14:27:30',NULL,2),(340,'Emmi Sibo',NULL,NULL,NULL,'2019-05-05 14:27:30',NULL,'NGANGO/192.168.1.123',NULL,'2019-05-05 14:27:50',4),(341,'Emmi Sibo',NULL,NULL,NULL,'2019-05-05 17:13:05',NULL,'NGANGO/192.168.1.123',NULL,'2019-05-05 17:13:33',4),(342,'Fred Ribakare',NULL,NULL,NULL,'2019-05-05 18:06:10',NULL,'NGANGO/192.168.1.123',NULL,'2019-05-05 18:06:54',1),(343,'Fred Ribakare',NULL,NULL,NULL,'2019-05-05 18:06:10',NULL,'NGANGO/192.168.1.123','2019-05-05 18:15:30',NULL,1),(344,'Cedrick Barafinda',NULL,NULL,NULL,'2019-05-05 18:15:31',NULL,'NGANGO/192.168.1.123',NULL,'2019-05-05 18:15:39',2),(345,'Cedrick Barafinda',NULL,NULL,NULL,'2019-05-05 18:15:31',NULL,'NGANGO/192.168.1.123','2019-05-05 18:16:15',NULL,2),(346,'Emmi Sibo',NULL,NULL,NULL,'2019-05-05 18:16:15',NULL,'NGANGO/192.168.1.123',NULL,'2019-05-05 18:16:22',4),(347,'Emmi Sibo',NULL,NULL,NULL,'2019-05-06 10:02:16',NULL,'NGANGO/192.168.1.123',NULL,'2019-05-06 10:11:44',4),(348,'Emmi Sibo',NULL,NULL,NULL,'2019-05-06 10:53:29',NULL,'NGANGO/192.168.1.123',NULL,'2019-05-06 10:59:23',4),(349,'Emmi Sibo',NULL,NULL,NULL,'2019-05-06 10:53:29',NULL,'NGANGO/192.168.1.123','2019-05-06 11:08:10',NULL,4),(350,'Cedrick Barafinda',NULL,NULL,NULL,'2019-05-06 11:08:11',NULL,'NGANGO/192.168.1.123',NULL,'2019-05-06 11:08:19',2),(351,'Emmi Sibo',NULL,NULL,NULL,'2019-05-07 12:13:58',NULL,'NGANGO/192.168.1.123',NULL,'2019-05-07 12:30:40',4),(352,'Emmi Sibo',NULL,NULL,NULL,'2019-05-07 17:03:56',NULL,'NGANGO/192.168.1.123',NULL,'2019-05-07 17:05:34',4),(353,'Emmi Sibo',NULL,NULL,NULL,'2019-05-07 17:11:41',NULL,'NGANGO/192.168.1.123',NULL,'2019-05-07 17:24:29',4),(354,'Emmi Sibo',NULL,NULL,NULL,'2019-05-07 17:35:45',NULL,'NGANGO/192.168.1.123',NULL,'2019-05-07 17:37:34',4),(355,'Emmi Sibo',NULL,NULL,NULL,'2019-05-07 17:35:45',NULL,'NGANGO/192.168.1.123','2019-05-07 17:43:54',NULL,4),(356,'Audrey Gihozo',NULL,NULL,NULL,'2019-05-19 12:32:59',NULL,'NGANGO/127.0.0.1',NULL,'2019-05-19 13:06:30',5),(357,'Fred Ribakare',NULL,NULL,NULL,'2019-05-27 21:04:45',NULL,'NGANGO/192.168.1.128',NULL,'2019-05-27 21:05:20',1),(358,'Fred Ribakare',NULL,NULL,NULL,'2019-05-27 21:04:45',NULL,'NGANGO/192.168.1.128','2019-05-27 21:09:33',NULL,1),(359,'Cedrick Barafinda',NULL,NULL,NULL,'2019-05-27 21:09:33',NULL,'NGANGO/192.168.1.128',NULL,'2019-05-27 21:09:40',2),(360,'Cedrick Barafinda',NULL,NULL,NULL,'2019-05-27 21:09:33',NULL,'NGANGO/192.168.1.128','2019-05-27 21:11:54',NULL,2),(361,'Cedrick Barafinda',NULL,NULL,NULL,'2019-05-27 21:11:54',NULL,'NGANGO/192.168.1.128',NULL,'2019-05-27 21:12:06',2),(362,'Cedrick Barafinda',NULL,NULL,NULL,'2019-05-27 21:11:54',NULL,'NGANGO/192.168.1.128','2019-05-27 21:12:32',NULL,2),(363,'Emmi Sibo',NULL,NULL,NULL,'2019-05-27 21:12:33',NULL,'NGANGO/192.168.1.128',NULL,'2019-05-27 21:12:38',4),(364,'Emmi Sibo',NULL,NULL,NULL,'2019-05-27 21:12:33',NULL,'NGANGO/192.168.1.128','2019-05-27 21:16:26',NULL,4),(365,'Fred Muhizi',NULL,NULL,NULL,'2019-05-27 21:16:26',NULL,'NGANGO/192.168.1.128',NULL,'2019-05-27 21:21:20',6),(366,'Fred Muhizi',NULL,NULL,NULL,'2019-05-27 21:16:26',NULL,'NGANGO/192.168.1.128','2019-05-27 21:21:34',NULL,6),(367,'Fred Ribakare',NULL,NULL,NULL,'2019-05-27 21:21:35',NULL,'NGANGO/192.168.1.128',NULL,'2019-05-27 21:21:40',1),(368,'Fred Ribakare',NULL,NULL,NULL,'2019-05-27 21:21:35',NULL,'NGANGO/192.168.1.128','2019-05-27 21:22:11',NULL,1),(369,'Fred Muhizi',NULL,NULL,NULL,'2019-05-27 21:22:11',NULL,'NGANGO/192.168.1.128',NULL,'2019-05-27 21:22:17',6),(370,'Fred Muhizi',NULL,NULL,NULL,'2019-05-27 21:22:11',NULL,'NGANGO/192.168.1.128','2019-05-27 21:22:24',NULL,6),(371,'Emmi Sibo',NULL,NULL,NULL,'2019-05-27 21:22:25',NULL,'NGANGO/192.168.1.128',NULL,'2019-05-27 21:22:31',4),(372,'Emmi Sibo',NULL,NULL,NULL,'2019-05-27 21:22:25',NULL,'NGANGO/192.168.1.128','2019-05-27 21:24:00',NULL,4),(373,'Cedrick Barafinda',NULL,NULL,NULL,'2019-05-27 21:24:00',NULL,'NGANGO/192.168.1.128',NULL,'2019-05-27 21:24:07',2),(374,'Cedrick Barafinda',NULL,NULL,NULL,'2019-05-27 21:24:00',NULL,'NGANGO/192.168.1.128','2019-05-27 21:24:40',NULL,2),(375,'Emmi Sibo',NULL,NULL,NULL,'2019-05-27 21:24:40',NULL,'NGANGO/192.168.1.128',NULL,'2019-05-27 21:24:46',4),(376,'Emmi Sibo',NULL,NULL,NULL,'2019-05-27 21:24:40',NULL,'NGANGO/192.168.1.128','2019-05-27 21:25:47',NULL,4),(377,'Fred Ribakare',NULL,NULL,NULL,'2019-05-27 21:25:47',NULL,'NGANGO/192.168.1.128',NULL,'2019-05-27 21:25:58',1),(378,'Gael Nsenga',NULL,NULL,NULL,'2019-06-01 10:45:41',NULL,'NGANGO/127.0.0.1',NULL,'2019-06-01 10:45:55',7),(379,'Gael Nsenga',NULL,NULL,NULL,'2019-06-01 12:06:49',NULL,'NGANGO/127.0.0.1',NULL,'2019-06-01 12:07:27',7),(380,'Gael Nsenga',NULL,NULL,NULL,'2019-06-01 12:44:36',NULL,'NGANGO/127.0.0.1',NULL,'2019-06-01 12:47:05',7),(381,'Gael Nsenga',NULL,NULL,NULL,'2019-06-01 15:59:00',NULL,'NGANGO/192.168.43.109',NULL,'2019-06-01 15:59:27',7),(382,'Gael Nsenga',NULL,NULL,NULL,'2019-06-01 16:55:17',NULL,'NGANGO/192.168.43.109',NULL,'2019-06-01 16:55:49',7),(383,'Gael Nsenga',NULL,NULL,NULL,'2019-06-01 17:03:00',NULL,'NGANGO/192.168.43.109',NULL,'2019-06-01 17:03:32',7),(384,'Gael Nsenga',NULL,NULL,NULL,'2019-06-01 17:24:36',NULL,'NGANGO/127.0.0.1',NULL,'2019-06-01 17:25:54',7),(385,'Gael Nsenga',NULL,NULL,NULL,'2019-06-01 17:56:00',NULL,'NGANGO/127.0.0.1',NULL,'2019-06-01 17:56:32',7),(386,'Gael Nsenga',NULL,NULL,NULL,'2019-06-01 18:04:27',NULL,'NGANGO/127.0.0.1',NULL,'2019-06-01 18:04:51',7),(387,'Emmi Sibo',NULL,NULL,NULL,'2019-06-01 19:05:05',NULL,'NGANGO/127.0.0.1',NULL,'2019-06-01 19:06:52',4),(388,'Emmi Sibo',NULL,NULL,NULL,'2019-06-01 19:05:05',NULL,'NGANGO/127.0.0.1','2019-06-01 19:11:10',NULL,4),(389,'Gael Nsenga',NULL,NULL,NULL,'2019-06-01 19:11:11',NULL,'NGANGO/127.0.0.1',NULL,'2019-06-01 19:11:29',7),(390,'Gael Nsenga',NULL,NULL,NULL,'2019-06-01 19:17:03',NULL,'NGANGO/127.0.0.1',NULL,'2019-06-01 19:17:16',7),(391,'Gael Nsenga',NULL,NULL,NULL,'2019-06-01 19:17:03',NULL,'NGANGO/127.0.0.1','2019-06-01 19:17:56',NULL,7),(392,'Emmi Sibo',NULL,NULL,NULL,'2019-06-01 19:17:57',NULL,'NGANGO/127.0.0.1',NULL,'2019-06-01 19:18:05',4),(393,'Gael Nsenga',NULL,NULL,NULL,'2019-06-01 19:28:18',NULL,'NGANGO/127.0.0.1',NULL,'2019-06-01 19:28:31',7),(394,'Gael Nsenga',NULL,NULL,NULL,'2019-06-01 19:32:54',NULL,'NGANGO/127.0.0.1',NULL,'2019-06-01 19:33:08',7),(395,'Gael Nsenga',NULL,NULL,NULL,'2019-06-01 19:36:30',NULL,'NGANGO/127.0.0.1',NULL,'2019-06-01 19:36:40',7),(396,'Gael Nsenga',NULL,NULL,NULL,'2019-06-01 19:36:30',NULL,'NGANGO/127.0.0.1','2019-06-01 19:38:28',NULL,7),(397,'Fred Ribakare',NULL,NULL,NULL,'2019-06-01 19:38:29',NULL,'NGANGO/127.0.0.1',NULL,'2019-06-01 19:38:38',1),(398,'Fred Ribakare',NULL,NULL,NULL,'2019-06-01 19:38:29',NULL,'NGANGO/127.0.0.1','2019-06-01 19:45:48',NULL,1),(399,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-01 19:45:50',NULL,'NGANGO/127.0.0.1',NULL,'2019-06-01 19:46:03',2),(400,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-01 19:45:50',NULL,'NGANGO/127.0.0.1','2019-06-01 19:46:25',NULL,2),(401,'Fred Muhizi',NULL,NULL,NULL,'2019-06-01 19:46:27',NULL,'NGANGO/127.0.0.1',NULL,'2019-06-01 19:46:39',6),(402,'Fred Muhizi',NULL,NULL,NULL,'2019-06-01 19:46:27',NULL,'NGANGO/127.0.0.1','2019-06-01 19:47:12',NULL,6),(403,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-01 19:47:13',NULL,'NGANGO/127.0.0.1',NULL,'2019-06-01 19:47:21',2),(404,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-01 19:47:13',NULL,'NGANGO/127.0.0.1','2019-06-01 19:50:19',NULL,2),(405,'Emmi Sibo',NULL,NULL,NULL,'2019-06-01 19:50:20',NULL,'NGANGO/127.0.0.1',NULL,'2019-06-01 19:50:34',4),(406,'Emmi Sibo',NULL,NULL,NULL,'2019-06-01 19:50:20',NULL,'NGANGO/127.0.0.1','2019-06-01 19:53:05',NULL,4),(407,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-01 19:53:06',NULL,'NGANGO/127.0.0.1',NULL,'2019-06-01 19:53:13',2),(408,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-01 19:57:29',NULL,'NGANGO/127.0.0.1',NULL,'2019-06-01 19:58:24',2),(409,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-01 19:57:29',NULL,'NGANGO/127.0.0.1','2019-06-01 20:10:10',NULL,2),(410,'Gael Nsenga',NULL,NULL,NULL,'2019-06-01 20:10:11',NULL,'NGANGO/127.0.0.1',NULL,'2019-06-01 20:10:21',7),(411,'Gael Nsenga',NULL,NULL,NULL,'2019-06-01 20:10:11',NULL,'NGANGO/127.0.0.1','2019-06-01 20:10:55',NULL,7),(412,'Fred Muhizi',NULL,NULL,NULL,'2019-06-01 20:10:56',NULL,'NGANGO/127.0.0.1',NULL,'2019-06-01 20:11:03',6),(413,'Fred Muhizi',NULL,NULL,NULL,'2019-06-01 20:10:56',NULL,'NGANGO/127.0.0.1','2019-06-01 20:22:04',NULL,6),(414,'Fred Muhizi',NULL,NULL,NULL,'2019-06-01 22:14:14',NULL,'NGANGO/192.168.1.145',NULL,'2019-06-01 22:21:38',6),(415,'Gael Nsenga',NULL,NULL,NULL,'2019-06-04 17:05:25',NULL,'NGANGO/192.168.1.148',NULL,'2019-06-04 17:07:44',7),(416,'Gael Nsenga',NULL,NULL,NULL,'2019-06-04 17:05:25',NULL,'NGANGO/192.168.1.148','2019-06-04 17:10:40',NULL,7),(417,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-04 17:24:33',NULL,'NGANGO/192.168.1.148',NULL,'2019-06-04 17:31:10',2),(418,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-04 17:24:33',NULL,'NGANGO/192.168.1.148','2019-06-04 17:40:55',NULL,2),(419,'Fred Muhizi',NULL,NULL,NULL,'2019-06-04 17:40:55',NULL,'NGANGO/192.168.1.148',NULL,'2019-06-04 17:41:03',6),(420,'Fred Muhizi',NULL,NULL,NULL,'2019-06-04 17:40:55',NULL,'NGANGO/192.168.1.148','2019-06-04 17:41:47',NULL,6),(421,'Gael Nsenga',NULL,NULL,NULL,'2019-06-04 17:41:48',NULL,'NGANGO/192.168.1.148',NULL,'2019-06-04 17:41:54',7),(422,'Gael Nsenga',NULL,NULL,NULL,'2019-06-04 17:41:48',NULL,'NGANGO/192.168.1.148','2019-06-04 17:42:57',NULL,7),(423,'Fred Muhizi',NULL,NULL,NULL,'2019-06-04 17:42:59',NULL,'NGANGO/192.168.1.148',NULL,'2019-06-04 17:43:04',6),(424,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-04 18:05:36',NULL,'NGANGO/192.168.1.148',NULL,'2019-06-04 18:06:53',2),(425,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-04 18:14:52',NULL,'NGANGO/192.168.1.148',NULL,'2019-06-04 18:22:51',2),(426,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-04 18:14:52',NULL,'NGANGO/192.168.1.148','2019-06-04 18:30:07',NULL,2),(427,'Fred Muhizi',NULL,NULL,NULL,'2019-06-04 18:30:08',NULL,'NGANGO/192.168.1.148',NULL,'2019-06-04 18:30:19',6),(428,'Fred Muhizi',NULL,NULL,NULL,'2019-06-04 18:30:08',NULL,'NGANGO/192.168.1.148','2019-06-04 18:31:25',NULL,6),(429,'Gael Nsenga',NULL,NULL,NULL,'2019-06-04 18:31:25',NULL,'NGANGO/192.168.1.148',NULL,'2019-06-04 18:31:52',7),(430,'Gael Nsenga',NULL,NULL,NULL,'2019-06-04 18:45:02',NULL,'NGANGO/192.168.1.148',NULL,'2019-06-04 18:46:35',7),(431,'Emmi Sibo',NULL,NULL,NULL,'2019-06-04 19:31:25',NULL,'NGANGO/192.168.1.148',NULL,'2019-06-04 19:33:38',4),(432,'Gael Nsenga',NULL,NULL,NULL,'2019-06-04 19:40:00',NULL,'NGANGO/192.168.1.148',NULL,'2019-06-04 19:41:37',7),(433,'Emmi Sibo',NULL,NULL,NULL,'2019-06-04 19:45:22',NULL,'NGANGO/192.168.1.148',NULL,'2019-06-04 19:46:30',4),(434,'Emmi Sibo',NULL,NULL,NULL,'2019-06-04 19:45:22',NULL,'NGANGO/192.168.1.148','2019-06-04 19:52:09',NULL,4),(435,'Fred Ribakare',NULL,NULL,NULL,'2019-06-04 19:52:10',NULL,'NGANGO/192.168.1.148',NULL,'2019-06-04 19:52:17',1),(436,'Fred Ribakare',NULL,NULL,NULL,'2019-06-04 19:52:10',NULL,'NGANGO/192.168.1.148','2019-06-04 19:53:15',NULL,1),(437,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-04 19:53:15',NULL,'NGANGO/192.168.109.1',NULL,'2019-06-04 20:09:07',2),(438,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-04 20:10:54',NULL,'NGANGO/127.0.0.1',NULL,'2019-06-04 20:12:46',2),(439,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-04 20:10:54',NULL,'NGANGO/127.0.0.1','2019-06-04 20:13:24',NULL,2),(440,'Fred Muhizi',NULL,NULL,NULL,'2019-06-04 20:13:25',NULL,'NGANGO/127.0.0.1',NULL,'2019-06-04 20:13:31',6),(441,'Fred Muhizi',NULL,NULL,NULL,'2019-06-04 20:13:25',NULL,'NGANGO/127.0.0.1','2019-06-04 20:13:49',NULL,6),(442,'Gael Nsenga',NULL,NULL,NULL,'2019-06-04 20:13:50',NULL,'NGANGO/127.0.0.1',NULL,'2019-06-04 20:13:56',7),(443,'Gael Nsenga',NULL,NULL,NULL,'2019-06-04 20:13:50',NULL,'NGANGO/127.0.0.1','2019-06-04 20:15:53',NULL,7),(444,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-04 20:15:53',NULL,'NGANGO/127.0.0.1',NULL,'2019-06-04 20:15:59',2),(445,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-04 20:15:53',NULL,'NGANGO/127.0.0.1','2019-06-04 20:16:30',NULL,2),(446,'Fred Muhizi',NULL,NULL,NULL,'2019-06-04 20:16:31',NULL,'NGANGO/127.0.0.1',NULL,'2019-06-04 20:16:36',6),(447,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-04 21:46:51',NULL,'NGANGO/192.168.1.148',NULL,'2019-06-04 21:47:37',2),(448,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-04 21:46:51',NULL,'NGANGO/192.168.1.148','2019-06-04 21:48:19',NULL,2),(449,'Gael Nsenga',NULL,NULL,NULL,'2019-06-04 21:48:19',NULL,'NGANGO/192.168.1.148',NULL,'2019-06-04 21:48:29',7),(450,'Maxime Kagorora',NULL,NULL,NULL,'2019-06-11 19:06:52',NULL,'NGANGO/169.254.216.208',NULL,'2019-06-11 19:14:28',8),(451,'Maxime Kagorora',NULL,NULL,NULL,'2019-06-11 19:06:52',NULL,'NGANGO/169.254.216.208','2019-06-11 19:16:35',NULL,8),(452,'gggg hhhh',NULL,NULL,NULL,'2019-06-11 19:16:36',NULL,'NGANGO/192.168.25.193',NULL,'2019-06-11 19:39:21',9),(453,'gggg hhhh',NULL,NULL,NULL,'2019-06-11 19:16:36',NULL,'NGANGO/127.0.0.1','2019-06-11 19:50:38',NULL,9),(454,'Fred Ribakare',NULL,NULL,NULL,'2019-06-11 19:50:38',NULL,'NGANGO/127.0.0.1',NULL,'2019-06-11 19:50:53',1),(455,'Fred Ribakare',NULL,NULL,NULL,'2019-06-11 19:50:38',NULL,'NGANGO/127.0.0.1','2019-06-11 19:54:58',NULL,1),(456,'Fred Ribakare',NULL,NULL,NULL,'2019-06-11 19:54:59',NULL,'NGANGO/127.0.0.1',NULL,'2019-06-11 19:55:09',1),(457,'Fred Ribakare',NULL,NULL,NULL,'2019-06-11 19:54:59',NULL,'NGANGO/127.0.0.1','2019-06-11 19:56:34',NULL,1),(458,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-11 19:56:34',NULL,'NGANGO/127.0.0.1',NULL,'2019-06-11 19:56:44',2),(459,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-11 19:56:34',NULL,'NGANGO/127.0.0.1','2019-06-11 20:00:17',NULL,2),(460,'Fred Ribakare',NULL,NULL,NULL,'2019-06-11 20:00:18',NULL,'NGANGO/127.0.0.1',NULL,'2019-06-11 20:00:29',1),(461,'Fred Ribakare',NULL,NULL,NULL,'2019-06-11 20:16:00',NULL,'NGANGO/172.20.10.2',NULL,'2019-06-11 20:16:17',1),(462,'Fred Ribakare',NULL,NULL,NULL,'2019-06-12 12:38:53',NULL,'NGANGO/192.168.1.108',NULL,'2019-06-12 12:40:12',1),(463,'Fred Ribakare',NULL,NULL,NULL,'2019-06-12 15:42:11',NULL,'NGANGO/192.168.1.108',NULL,'2019-06-12 15:44:05',1),(464,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-15 10:34:22',NULL,'NGANGO/192.168.1.132',NULL,'2019-06-15 10:39:26',2),(465,'Fred Ribakare',NULL,NULL,NULL,'2019-06-15 11:16:32',NULL,'NGANGO/192.168.1.132',NULL,'2019-06-15 11:17:09',1),(466,'Fred Ribakare',NULL,NULL,NULL,'2019-06-15 11:16:32',NULL,'NGANGO/192.168.1.132','2019-06-15 11:17:45',NULL,1),(467,'Emmi Sibo',NULL,NULL,NULL,'2019-06-15 11:17:46',NULL,'NGANGO/192.168.1.132',NULL,'2019-06-15 11:18:01',4),(468,'Emmi Sibo',NULL,NULL,NULL,'2019-06-15 11:17:46',NULL,'NGANGO/192.168.1.132','2019-06-15 11:18:27',NULL,4),(469,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-15 11:18:27',NULL,'NGANGO/192.168.1.132',NULL,'2019-06-15 11:18:33',2),(470,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-16 11:03:23',NULL,'NGANGO/192.168.1.132',NULL,'2019-06-16 11:07:30',2),(471,'Fred Ribakare',NULL,NULL,NULL,'2019-06-16 14:02:07',NULL,'DESKTOP-BCD89LB/192.168.1.139','2019-06-16 14:07:37',NULL,1),(472,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-16 14:07:39',NULL,'DESKTOP-BCD89LB/192.168.1.139',NULL,'2019-06-16 14:07:54',2),(473,'Emmi Sibo',NULL,NULL,NULL,'2019-06-16 14:19:14',NULL,'DESKTOP-BCD89LB/192.168.1.139',NULL,'2019-06-16 14:19:56',4),(474,'Emmi Sibo',NULL,NULL,NULL,'2019-06-16 14:19:14',NULL,'DESKTOP-BCD89LB/192.168.1.139','2019-06-16 14:20:41',NULL,4),(475,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-16 14:20:41',NULL,'DESKTOP-BCD89LB/192.168.1.139',NULL,'2019-06-16 14:20:50',2),(476,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-16 14:20:41',NULL,'DESKTOP-BCD89LB/192.168.1.139','2019-06-16 14:21:23',NULL,2),(477,'Fred Muhizi',NULL,NULL,NULL,'2019-06-16 14:21:24',NULL,'DESKTOP-BCD89LB/192.168.1.139',NULL,'2019-06-16 14:21:32',6),(478,'Fred Muhizi',NULL,NULL,NULL,'2019-06-16 14:21:24',NULL,'DESKTOP-BCD89LB/192.168.1.139','2019-06-16 14:23:03',NULL,6),(479,'Fred Ribakare',NULL,NULL,NULL,'2019-06-16 14:23:03',NULL,'DESKTOP-BCD89LB/192.168.1.139',NULL,'2019-06-16 14:23:11',1),(480,'Fred Ribakare',NULL,NULL,NULL,'2019-06-16 14:23:03',NULL,'DESKTOP-BCD89LB/192.168.1.139','2019-06-16 14:25:52',NULL,1),(481,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-16 14:25:53',NULL,'DESKTOP-BCD89LB/192.168.1.139',NULL,'2019-06-16 14:26:02',2),(482,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-16 14:25:53',NULL,'DESKTOP-BCD89LB/192.168.1.139','2019-06-16 14:26:14',NULL,2),(483,'Fred Muhizi',NULL,NULL,NULL,'2019-06-16 14:26:14',NULL,'DESKTOP-BCD89LB/192.168.1.139',NULL,'2019-06-16 14:26:20',6),(484,'Fred Muhizi',NULL,NULL,NULL,'2019-06-16 14:26:14',NULL,'DESKTOP-BCD89LB/192.168.1.139','2019-06-16 14:32:33',NULL,6),(485,'Fred Ribakare',NULL,NULL,NULL,'2019-06-16 14:32:34',NULL,'DESKTOP-BCD89LB/192.168.1.139',NULL,'2019-06-16 14:32:41',1),(486,'Fred Muhizi',NULL,NULL,NULL,'2019-06-16 15:49:59',NULL,'DESKTOP-BCD89LB/192.168.1.139',NULL,'2019-06-16 15:50:34',6),(487,'Fred Muhizi',NULL,NULL,NULL,'2019-06-16 15:49:59',NULL,'DESKTOP-BCD89LB/192.168.1.139','2019-06-16 15:50:48',NULL,6),(488,'Fred Ribakare',NULL,NULL,NULL,'2019-06-16 15:50:49',NULL,'DESKTOP-BCD89LB/192.168.1.139',NULL,'2019-06-16 15:50:56',1),(489,'Fred Ribakare',NULL,NULL,NULL,'2019-06-16 16:37:17',NULL,'DESKTOP-BCD89LB/192.168.1.139',NULL,'2019-06-16 16:38:35',1),(490,'Fred Ribakare',NULL,NULL,NULL,'2019-06-16 16:42:59',NULL,'DESKTOP-BCD89LB/192.168.1.139',NULL,'2019-06-16 16:43:17',1),(491,'Fred Ribakare',NULL,NULL,NULL,'2019-06-16 16:46:58',NULL,'DESKTOP-BCD89LB/192.168.1.139',NULL,'2019-06-16 16:47:17',1),(492,'Fred Ribakare',NULL,NULL,NULL,'2019-06-16 16:46:58',NULL,'DESKTOP-BCD89LB/192.168.1.139','2019-06-16 17:12:22',NULL,1),(493,'Fred Ribakare',NULL,NULL,NULL,'2019-06-16 17:12:23',NULL,'DESKTOP-BCD89LB/192.168.1.139',NULL,'2019-06-16 17:12:28',1),(494,'Fred Ribakare',NULL,NULL,NULL,'2019-06-16 17:51:43',NULL,'DESKTOP-BCD89LB/192.168.1.139',NULL,'2019-06-16 17:52:18',1),(495,'Fred Ribakare',NULL,NULL,NULL,'2019-06-16 17:54:41',NULL,'DESKTOP-BCD89LB/192.168.1.139',NULL,'2019-06-16 17:55:00',1),(496,'Fred Ribakare',NULL,NULL,NULL,'2019-06-16 17:59:11',NULL,'DESKTOP-BCD89LB/192.168.1.139',NULL,'2019-06-16 17:59:28',1),(497,'Fred Ribakare',NULL,NULL,NULL,'2019-06-16 18:11:21',NULL,'DESKTOP-BCD89LB/192.168.1.139',NULL,'2019-06-16 18:11:43',1),(498,'Fred Ribakare',NULL,NULL,NULL,'2019-06-16 18:19:31',NULL,'DESKTOP-BCD89LB/192.168.1.139',NULL,'2019-06-16 18:19:49',1),(499,'Fred Ribakare',NULL,NULL,NULL,'2019-06-16 18:27:53',NULL,'DESKTOP-BCD89LB/192.168.1.139',NULL,'2019-06-16 18:28:07',1),(500,'Fred Ribakare',NULL,NULL,NULL,'2019-06-17 08:26:44',NULL,'DESKTOP-BCD89LB/192.168.1.139',NULL,'2019-06-17 08:27:00',1),(501,'Fred Ribakare',NULL,NULL,NULL,'2019-06-17 09:17:26',NULL,'DESKTOP-BCD89LB/192.168.1.139',NULL,'2019-06-17 09:17:46',1),(502,'Fred Ribakare',NULL,NULL,NULL,'2019-06-17 10:10:48',NULL,'DESKTOP-BCD89LB/192.168.1.139',NULL,'2019-06-17 10:11:15',1),(503,'Fred Ribakare',NULL,NULL,NULL,'2019-06-17 10:22:05',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-06-17 10:22:20',1),(504,'Fred Ribakare',NULL,NULL,NULL,'2019-06-17 10:51:27',NULL,'DESKTOP-BCD89LB/192.168.43.244',NULL,'2019-06-17 10:52:31',1),(505,'Fred Ribakare',NULL,NULL,NULL,'2019-06-17 11:17:33',NULL,'DESKTOP-BCD89LB/192.168.43.244',NULL,'2019-06-17 11:18:50',1),(506,'Fred Ribakare',NULL,NULL,NULL,'2019-06-17 11:20:50',NULL,'DESKTOP-BCD89LB/192.168.43.244',NULL,'2019-06-17 11:21:07',1),(507,'Fred Ribakare',NULL,NULL,NULL,'2019-06-17 11:25:34',NULL,'DESKTOP-BCD89LB/192.168.43.244',NULL,'2019-06-17 11:25:49',1),(508,'Fred Ribakare',NULL,NULL,NULL,'2019-06-17 11:33:40',NULL,'DESKTOP-BCD89LB/192.168.43.244',NULL,'2019-06-17 11:34:02',1),(509,'Fred Ribakare',NULL,NULL,NULL,'2019-06-17 11:38:46',NULL,'DESKTOP-BCD89LB/192.168.43.244',NULL,'2019-06-17 11:38:58',1),(510,'Fred Ribakare',NULL,NULL,NULL,'2019-06-17 11:45:37',NULL,'DESKTOP-BCD89LB/192.168.43.244',NULL,'2019-06-17 11:45:51',1),(511,'Fred Ribakare',NULL,NULL,NULL,'2019-06-17 11:45:37',NULL,'DESKTOP-BCD89LB/192.168.43.244','2019-06-17 11:50:56',NULL,1),(512,'Fred Ribakare',NULL,NULL,NULL,'2019-06-17 11:50:56',NULL,'DESKTOP-BCD89LB/192.168.43.244',NULL,'2019-06-17 11:51:01',1),(513,'Fred Ribakare',NULL,NULL,NULL,'2019-06-17 12:03:06',NULL,'DESKTOP-BCD89LB/192.168.43.244',NULL,'2019-06-17 12:03:19',1),(514,'Fred Ribakare',NULL,NULL,NULL,'2019-06-17 12:03:06',NULL,'DESKTOP-BCD89LB/192.168.43.244','2019-06-17 12:15:17',NULL,1),(515,'Fred Muhizi',NULL,NULL,NULL,'2019-06-17 12:15:18',NULL,'DESKTOP-BCD89LB/192.168.43.244',NULL,'2019-06-17 12:15:25',6),(516,'Fred Ribakare',NULL,NULL,NULL,'2019-06-17 12:19:44',NULL,'DESKTOP-BCD89LB/192.168.43.244',NULL,'2019-06-17 12:21:08',1),(517,'Fred Ribakare',NULL,NULL,NULL,'2019-06-17 12:19:44',NULL,'DESKTOP-BCD89LB/192.168.43.244','2019-06-17 12:25:20',NULL,1),(518,'Fred Muhizi',NULL,NULL,NULL,'2019-06-17 12:25:21',NULL,'DESKTOP-BCD89LB/192.168.43.244',NULL,'2019-06-17 12:25:28',6),(519,'Fred Muhizi',NULL,NULL,NULL,'2019-06-17 12:25:21',NULL,'DESKTOP-BCD89LB/192.168.43.244','2019-06-17 12:25:42',NULL,6),(520,'Fred Ribakare',NULL,NULL,NULL,'2019-06-17 12:25:43',NULL,'DESKTOP-BCD89LB/192.168.43.244',NULL,'2019-06-17 12:25:56',1),(521,'Fred Ribakare',NULL,NULL,NULL,'2019-06-17 12:25:43',NULL,'DESKTOP-BCD89LB/192.168.43.244','2019-06-17 12:33:15',NULL,1),(522,'Fred Muhizi',NULL,NULL,NULL,'2019-06-17 12:33:16',NULL,'DESKTOP-BCD89LB/192.168.43.244',NULL,'2019-06-17 12:33:24',6),(523,'Fred Muhizi',NULL,NULL,NULL,'2019-06-17 12:58:29',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-06-17 12:58:52',6),(524,'Fred Muhizi',NULL,NULL,NULL,'2019-06-17 13:02:23',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-06-17 13:03:56',6),(525,'Fred Muhizi',NULL,NULL,NULL,'2019-06-17 13:06:40',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-06-17 13:06:58',6),(526,'Fred Muhizi',NULL,NULL,NULL,'2019-06-17 13:11:11',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-06-17 13:11:28',6),(527,'Fred Muhizi',NULL,NULL,NULL,'2019-06-17 13:17:20',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-06-17 13:17:52',6),(528,'Fred Muhizi',NULL,NULL,NULL,'2019-06-17 13:19:34',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-06-17 13:20:17',6),(529,'Fred Muhizi',NULL,NULL,NULL,'2019-06-17 13:24:54',NULL,'DESKTOP-BCD89LB/127.0.0.1',NULL,'2019-06-17 13:25:09',6),(530,'Fred Muhizi',NULL,NULL,NULL,'2019-06-17 14:34:08',NULL,'DESKTOP-BCD89LB/192.168.1.142',NULL,'2019-06-17 14:34:31',6),(531,'Fred Muhizi',NULL,NULL,NULL,'2019-06-17 14:38:35',NULL,'DESKTOP-BCD89LB/192.168.1.142',NULL,'2019-06-17 14:38:58',6),(532,'Fred Muhizi',NULL,NULL,NULL,'2019-06-17 14:40:53',NULL,'DESKTOP-BCD89LB/192.168.1.142',NULL,'2019-06-17 14:41:15',6),(533,'Fred Muhizi',NULL,NULL,NULL,'2019-06-17 14:46:48',NULL,'DESKTOP-BCD89LB/192.168.1.142',NULL,'2019-06-17 14:47:13',6),(534,'Fred Muhizi',NULL,NULL,NULL,'2019-06-17 14:49:56',NULL,'DESKTOP-BCD89LB/192.168.1.142',NULL,'2019-06-17 14:54:00',6),(535,'Fred Muhizi',NULL,NULL,NULL,'2019-06-17 14:49:56',NULL,'DESKTOP-BCD89LB/192.168.1.142','2019-06-17 15:15:37',NULL,6),(536,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-17 15:15:38',NULL,'DESKTOP-BCD89LB/192.168.1.142',NULL,'2019-06-17 15:15:52',2),(537,'Fred Ribakare',NULL,NULL,NULL,'2019-06-17 15:59:28',NULL,'DESKTOP-BCD89LB/192.168.1.142',NULL,'2019-06-17 16:00:14',1),(538,'Fred Ribakare',NULL,NULL,NULL,'2019-06-18 07:42:02',NULL,'DESKTOP-BCD89LB/192.168.1.142',NULL,'2019-06-18 07:42:28',1),(539,'Fred Ribakare',NULL,NULL,NULL,'2019-06-18 07:49:45',NULL,'DESKTOP-BCD89LB/192.168.1.142',NULL,'2019-06-18 07:50:01',1),(540,'Fred Ribakare',NULL,NULL,NULL,'2019-06-18 07:53:11',NULL,'DESKTOP-BCD89LB/192.168.1.142',NULL,'2019-06-18 07:53:23',1),(541,'Fred Ribakare',NULL,NULL,NULL,'2019-06-18 15:26:59',NULL,'DESKTOP-BCD89LB/192.168.1.142',NULL,'2019-06-18 15:27:28',1),(542,'Fred Ribakare',NULL,NULL,NULL,'2019-06-18 15:38:02',NULL,'DESKTOP-BCD89LB/192.168.1.142',NULL,'2019-06-18 15:38:15',1),(543,'Fred Ribakare',NULL,NULL,NULL,'2019-06-18 16:18:08',NULL,'DESKTOP-BCD89LB/192.168.1.142',NULL,'2019-06-18 16:18:38',1),(544,'Fred Ribakare',NULL,NULL,NULL,'2019-06-18 16:33:07',NULL,'DESKTOP-BCD89LB/192.168.1.142',NULL,'2019-06-18 16:33:22',1),(545,'Fred Ribakare',NULL,NULL,NULL,'2019-06-18 17:12:52',NULL,'DESKTOP-BCD89LB/192.168.1.142',NULL,'2019-06-18 17:13:09',1),(546,'Fred Ribakare',NULL,NULL,NULL,'2019-06-18 17:16:14',NULL,'DESKTOP-BCD89LB/192.168.1.142',NULL,'2019-06-18 17:16:28',1),(547,'Fred Ribakare',NULL,NULL,NULL,'2019-06-18 17:24:08',NULL,'DESKTOP-BCD89LB/192.168.1.142',NULL,'2019-06-18 17:25:17',1),(548,'Fred Ribakare',NULL,NULL,NULL,'2019-06-18 17:33:12',NULL,'DESKTOP-BCD89LB/192.168.1.142',NULL,'2019-06-18 17:33:27',1),(549,'Fred Ribakare',NULL,NULL,NULL,'2019-06-18 17:36:59',NULL,'DESKTOP-BCD89LB/192.168.1.142',NULL,'2019-06-18 17:37:12',1),(550,'Fred Ribakare',NULL,NULL,NULL,'2019-06-18 17:41:27',NULL,'DESKTOP-BCD89LB/192.168.1.142',NULL,'2019-06-18 17:41:41',1),(551,'Fred Ribakare',NULL,NULL,NULL,'2019-06-18 17:52:45',NULL,'DESKTOP-BCD89LB/192.168.1.142',NULL,'2019-06-18 17:52:59',1),(552,'Fred Ribakare',NULL,NULL,NULL,'2019-06-18 18:05:30',NULL,'DESKTOP-BCD89LB/192.168.1.142',NULL,'2019-06-18 18:05:43',1),(553,'Fred Ribakare',NULL,NULL,NULL,'2019-06-18 18:05:30',NULL,'DESKTOP-BCD89LB/192.168.1.142','2019-06-18 18:07:16',NULL,1),(554,'Fred Muhizi',NULL,NULL,NULL,'2019-06-18 18:07:17',NULL,'DESKTOP-BCD89LB/192.168.1.142',NULL,'2019-06-18 18:07:22',6),(555,'Fred Muhizi',NULL,NULL,NULL,'2019-06-18 18:07:17',NULL,'DESKTOP-BCD89LB/192.168.1.142','2019-06-18 18:08:27',NULL,6),(556,'Fred Ribakare',NULL,NULL,NULL,'2019-06-18 18:08:27',NULL,'DESKTOP-BCD89LB/192.168.1.142',NULL,'2019-06-18 18:08:33',1),(557,'Fred Ribakare',NULL,NULL,NULL,'2019-06-18 18:23:53',NULL,'DESKTOP-BCD89LB/192.168.1.142',NULL,'2019-06-18 18:24:07',1),(558,'Fred Ribakare',NULL,NULL,NULL,'2019-06-18 18:35:49',NULL,'DESKTOP-BCD89LB/192.168.1.142',NULL,'2019-06-18 18:36:12',1),(559,'Fred Ribakare',NULL,NULL,NULL,'2019-06-18 18:35:49',NULL,'DESKTOP-BCD89LB/192.168.1.142','2019-06-18 18:38:06',NULL,1),(560,'Fred Ribakare',NULL,NULL,NULL,'2019-06-18 18:38:07',NULL,'DESKTOP-BCD89LB/192.168.1.142',NULL,'2019-06-18 18:38:12',1),(561,'Fred Ribakare',NULL,NULL,NULL,'2019-06-18 18:44:40',NULL,'DESKTOP-BCD89LB/192.168.1.142',NULL,'2019-06-18 18:44:53',1),(562,'Fred Ribakare',NULL,NULL,NULL,'2019-06-18 18:44:40',NULL,'DESKTOP-BCD89LB/192.168.1.142','2019-06-18 18:47:05',NULL,1),(563,'Fred Muhizi',NULL,NULL,NULL,'2019-06-18 18:47:05',NULL,'DESKTOP-BCD89LB/192.168.1.142',NULL,'2019-06-18 18:47:12',6),(564,'Fred Muhizi',NULL,NULL,NULL,'2019-06-18 18:47:05',NULL,'DESKTOP-BCD89LB/192.168.1.142','2019-06-18 18:50:54',NULL,6),(565,'Fred Muhizi',NULL,NULL,NULL,'2019-06-18 18:50:55',NULL,'DESKTOP-BCD89LB/192.168.1.142',NULL,'2019-06-18 18:51:20',6),(566,'Fred Muhizi',NULL,NULL,NULL,'2019-06-18 18:50:55',NULL,'DESKTOP-BCD89LB/192.168.1.142','2019-06-18 18:56:06',NULL,6),(567,'Fred Muhizi',NULL,NULL,NULL,'2019-06-18 18:56:07',NULL,'DESKTOP-BCD89LB/192.168.1.142',NULL,'2019-06-18 18:56:15',6),(568,'Fred Muhizi',NULL,NULL,NULL,'2019-06-18 18:56:07',NULL,'DESKTOP-BCD89LB/192.168.1.142','2019-06-18 19:01:54',NULL,6),(569,'Fred Ribakare',NULL,NULL,NULL,'2019-06-18 19:01:55',NULL,'DESKTOP-BCD89LB/192.168.1.142',NULL,'2019-06-18 19:02:06',1),(570,'Fred Ribakare',NULL,NULL,NULL,'2019-06-22 12:34:47',NULL,'NGANGO/192.168.42.99',NULL,'2019-06-22 12:38:02',1),(571,'Fred Ribakare',NULL,NULL,NULL,'2019-06-22 16:22:51',NULL,'NGANGO/192.168.42.99',NULL,'2019-06-22 16:23:40',1),(572,'Fred Ribakare',NULL,NULL,NULL,'2019-06-22 16:22:51',NULL,'NGANGO/127.0.0.1','2019-06-22 17:08:30',NULL,1),(573,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-22 17:08:35',NULL,'NGANGO/127.0.0.1',NULL,'2019-06-22 17:12:06',2),(574,'Fred Ribakare',NULL,NULL,NULL,'2019-06-23 18:55:53',NULL,'NGANGO/192.168.109.1',NULL,'2019-06-23 18:57:45',1),(575,'Fred Ribakare',NULL,NULL,NULL,'2019-06-23 19:36:49',NULL,'NGANGO/192.168.109.1',NULL,'2019-06-23 19:37:53',1),(576,'Fred Ribakare',NULL,NULL,NULL,'2019-06-23 19:36:49',NULL,'NGANGO/192.168.109.1','2019-06-23 19:39:27',NULL,1),(577,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-24 12:44:08',NULL,'NGANGO/192.168.1.132',NULL,'2019-06-24 12:44:24',2),(578,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-24 12:54:57',NULL,'NGANGO/192.168.1.132',NULL,'2019-06-24 12:55:14',2),(579,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-24 13:53:30',NULL,'NGANGO/192.168.1.132',NULL,'2019-06-24 13:53:59',2),(580,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-24 15:38:05',NULL,'NGANGO/192.168.1.132',NULL,'2019-06-24 15:38:22',2),(581,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-24 15:59:57',NULL,'NGANGO/192.168.1.132',NULL,'2019-06-24 16:07:42',2),(582,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-24 16:25:08',NULL,'NGANGO/192.168.1.132',NULL,'2019-06-24 16:25:59',2),(583,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-24 17:04:01',NULL,'NGANGO/192.168.1.132',NULL,'2019-06-24 17:09:11',2),(584,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-24 17:27:03',NULL,'NGANGO/192.168.1.132',NULL,'2019-06-24 17:28:30',2),(585,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-24 17:33:34',NULL,'NGANGO/192.168.1.132',NULL,'2019-06-24 17:34:57',2),(586,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-24 17:41:38',NULL,'NGANGO/192.168.1.132',NULL,'2019-06-24 17:42:48',2),(587,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-24 19:45:34',NULL,'NGANGO/192.168.1.132',NULL,'2019-06-24 19:46:10',2),(588,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-25 04:54:26',NULL,'NGANGO/127.0.0.1',NULL,'2019-06-25 04:54:58',2),(589,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-25 06:18:35',NULL,'NGANGO/127.0.0.1',NULL,'2019-06-25 06:18:56',2),(590,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-25 07:04:56',NULL,'NGANGO/127.0.0.1',NULL,'2019-06-25 07:05:49',2),(591,'Fred Muhizi',NULL,NULL,NULL,'2019-06-25 07:15:18',NULL,'NGANGO/127.0.0.1',NULL,'2019-06-25 07:15:32',6),(592,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-25 07:24:03',NULL,'NGANGO/127.0.0.1',NULL,'2019-06-25 07:24:18',2),(593,'Fred Ribakare',NULL,NULL,NULL,'2019-06-25 16:03:32',NULL,'NGANGO/192.168.1.132',NULL,'2019-06-25 16:04:07',1),(594,'Fred Ribakare',NULL,NULL,NULL,'2019-06-25 16:03:32',NULL,'NGANGO/192.168.1.132','2019-06-25 16:06:10',NULL,1),(595,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-25 16:06:11',NULL,'NGANGO/192.168.1.132',NULL,'2019-06-25 16:06:18',2),(596,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-25 16:06:11',NULL,'NGANGO/192.168.1.132','2019-06-25 16:08:11',NULL,2),(597,'Emmi Sibo',NULL,NULL,NULL,'2019-06-25 16:08:12',NULL,'NGANGO/192.168.1.132',NULL,'2019-06-25 16:08:36',4),(598,'Emmi Sibo',NULL,NULL,NULL,'2019-06-25 16:08:12',NULL,'NGANGO/192.168.1.132','2019-06-25 16:08:40',NULL,4),(599,'Fred Ribakare',NULL,NULL,NULL,'2019-06-25 16:08:40',NULL,'NGANGO/192.168.1.132',NULL,'2019-06-25 16:09:00',1),(600,'Fred Ribakare',NULL,NULL,NULL,'2019-06-25 16:08:40',NULL,'NGANGO/192.168.1.132','2019-06-25 16:11:03',NULL,1),(601,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-25 16:11:03',NULL,'NGANGO/192.168.1.132',NULL,'2019-06-25 16:11:10',2),(602,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-25 16:42:06',NULL,'NGANGO/192.168.1.132',NULL,'2019-06-25 16:42:41',2),(603,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-25 17:32:58',NULL,'NGANGO/192.168.1.132',NULL,'2019-06-25 17:35:03',2),(604,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-25 17:44:24',NULL,'NGANGO/192.168.1.132',NULL,'2019-06-25 17:50:17',2),(605,'Fred Muhizi',NULL,NULL,NULL,'2019-06-25 18:33:41',NULL,'NGANGO/192.168.1.132',NULL,'2019-06-25 18:34:38',6),(606,'Fred Muhizi',NULL,NULL,NULL,'2019-06-25 18:54:29',NULL,'NGANGO/192.168.1.132',NULL,'2019-06-25 18:55:25',6),(607,'Fred Ribakare',NULL,NULL,NULL,'2019-06-25 19:10:40',NULL,'NGANGO/127.0.0.1',NULL,'2019-06-25 19:10:57',1),(608,'Fred Ribakare',NULL,NULL,NULL,'2019-06-25 19:10:40',NULL,'NGANGO/127.0.0.1','2019-06-25 19:12:50',NULL,1),(609,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-25 19:12:50',NULL,'NGANGO/127.0.0.1',NULL,'2019-06-25 19:12:56',2),(610,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-25 19:12:50',NULL,'NGANGO/127.0.0.1','2019-06-25 19:13:43',NULL,2),(611,'Fred Muhizi',NULL,NULL,NULL,'2019-06-25 19:13:43',NULL,'NGANGO/127.0.0.1',NULL,'2019-06-25 19:13:48',6),(612,'Fred Muhizi',NULL,NULL,NULL,'2019-06-25 19:13:43',NULL,'NGANGO/127.0.0.1','2019-06-25 19:14:12',NULL,6),(613,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-25 19:14:13',NULL,'NGANGO/127.0.0.1',NULL,'2019-06-25 19:15:15',2),(614,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-25 19:14:13',NULL,'NGANGO/127.0.0.1','2019-06-25 19:21:09',NULL,2),(615,'Emmi Sibo',NULL,NULL,NULL,'2019-06-25 19:21:10',NULL,'NGANGO/127.0.0.1',NULL,'2019-06-25 19:21:18',4),(616,'Emmi Sibo',NULL,NULL,NULL,'2019-06-25 19:21:10',NULL,'NGANGO/127.0.0.1','2019-06-25 19:22:11',NULL,4),(617,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-25 19:22:11',NULL,'NGANGO/127.0.0.1',NULL,'2019-06-25 19:22:18',2),(618,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-25 19:22:11',NULL,'NGANGO/127.0.0.1','2019-06-25 19:25:51',NULL,2),(619,'Emmi Sibo',NULL,NULL,NULL,'2019-06-25 19:25:52',NULL,'NGANGO/127.0.0.1',NULL,'2019-06-25 19:26:02',4),(620,'Fred Ribakare',NULL,NULL,NULL,'2019-06-26 12:58:30',NULL,'NGANGO/192.168.1.132',NULL,'2019-06-26 12:59:31',1),(621,'Fred Ribakare',NULL,NULL,NULL,'2019-06-26 12:58:30',NULL,'NGANGO/192.168.1.132','2019-06-26 13:43:06',NULL,1),(622,'Cedrick Barafinda',NULL,NULL,NULL,'2019-06-26 13:43:07',NULL,'NGANGO/192.168.1.132',NULL,'2019-06-26 13:44:40',2),(623,'Fred Ribakare',NULL,NULL,NULL,'2019-06-28 10:25:41',NULL,'NGANGO/192.168.1.132',NULL,'2019-06-28 10:39:08',1),(624,'Fred Ribakare',NULL,NULL,NULL,'2019-06-29 08:02:14',NULL,'NGANGO/127.0.0.1',NULL,'2019-06-29 08:05:36',1),(625,'Fred Ribakare',NULL,NULL,NULL,'2019-06-29 10:44:12',NULL,'NGANGO/192.168.43.109',NULL,'2019-06-29 10:55:35',1),(626,'Fred Ribakare',NULL,NULL,NULL,'2019-07-01 07:48:52',NULL,'NGANGO/127.0.0.1',NULL,'2019-07-01 07:51:28',1),(627,'Fred Ribakare',NULL,NULL,NULL,'2019-07-01 08:13:00',NULL,'NGANGO/127.0.0.1',NULL,'2019-07-01 08:13:34',1),(628,'Fred Ribakare',NULL,NULL,NULL,'2019-07-01 08:47:04',NULL,'NGANGO/127.0.0.1',NULL,'2019-07-01 08:47:37',1),(629,'Fred Ribakare',NULL,NULL,NULL,'2019-07-01 09:05:34',NULL,'NGANGO/127.0.0.1',NULL,'2019-07-01 09:07:05',1),(630,'Fred Ribakare',NULL,NULL,NULL,'2019-07-01 10:17:49',NULL,'NGANGO/127.0.0.1',NULL,'2019-07-01 10:19:46',1),(631,'Fred Ribakare',NULL,NULL,NULL,'2019-07-01 10:22:54',NULL,'NGANGO/127.0.0.1',NULL,'2019-07-01 10:23:08',1),(632,'Fred Ribakare',NULL,NULL,NULL,'2019-07-01 10:35:40',NULL,'NGANGO/127.0.0.1',NULL,'2019-07-01 10:36:04',1),(633,'Fred Ribakare',NULL,NULL,NULL,'2019-07-01 10:51:37',NULL,'NGANGO/127.0.0.1',NULL,'2019-07-01 10:51:53',1),(634,'Fred Ribakare',NULL,NULL,NULL,'2019-07-01 10:51:37',NULL,'NGANGO/127.0.0.1','2019-07-01 11:19:15',NULL,1),(635,'Cedrick Barafinda',NULL,NULL,NULL,'2019-07-01 11:19:18',NULL,'NGANGO/127.0.0.1',NULL,'2019-07-01 11:19:29',2),(636,'Fred Ribakare',NULL,NULL,NULL,'2019-07-01 11:52:56',NULL,'NGANGO/127.0.0.1',NULL,'2019-07-01 11:53:16',1),(637,'Fred Ribakare',NULL,NULL,NULL,'2019-07-01 11:52:56',NULL,'NGANGO/127.0.0.1','2019-07-01 12:12:29',NULL,1),(638,'Fred Ribakare',NULL,NULL,NULL,'2019-07-01 12:12:30',NULL,'NGANGO/127.0.0.1',NULL,'2019-07-01 12:12:48',1),(639,'Fred Ribakare',NULL,NULL,NULL,'2019-07-01 12:12:30',NULL,'NGANGO/127.0.0.1','2019-07-01 12:40:34',NULL,1),(640,'Fred Ribakare',NULL,NULL,NULL,'2019-07-01 12:41:14',NULL,'NGANGO/127.0.0.1',NULL,'2019-07-01 12:42:39',1),(641,'Fred Ribakare',NULL,NULL,NULL,'2019-07-01 13:30:53',NULL,'NGANGO/192.168.43.109',NULL,'2019-07-01 13:33:37',1),(642,'Fred Ribakare',NULL,NULL,NULL,'2019-07-01 14:33:36',NULL,'NGANGO/192.168.43.109',NULL,'2019-07-01 14:45:49',1),(643,'Fred Ribakare',NULL,NULL,NULL,'2019-07-01 14:33:36',NULL,'NGANGO/192.168.43.109','2019-07-01 14:47:58',NULL,1),(644,'Cedrick Barafinda',NULL,NULL,NULL,'2019-07-01 14:47:58',NULL,'NGANGO/192.168.43.109',NULL,'2019-07-01 14:48:08',2),(645,'Cedrick Barafinda',NULL,NULL,NULL,'2019-07-01 15:36:58',NULL,'NGANGO/192.168.43.109',NULL,'2019-07-01 15:40:33',2),(646,'Cedrick Barafinda',NULL,NULL,NULL,'2019-07-01 16:13:28',NULL,'NGANGO/127.0.0.1',NULL,'2019-07-01 16:16:28',2),(647,'Cedrick Barafinda',NULL,NULL,NULL,'2019-07-01 16:13:28',NULL,'NGANGO/127.0.0.1','2019-07-01 16:19:37',NULL,2),(648,'Fred Ribakare',NULL,NULL,NULL,'2019-07-01 16:19:49',NULL,'NGANGO/127.0.0.1',NULL,'2019-07-01 16:20:37',1),(649,'Fred Ribakare',NULL,NULL,NULL,'2019-07-01 16:54:54',NULL,'NGANGO/127.0.0.1',NULL,'2019-07-01 16:58:08',1),(650,'Fred Ribakare',NULL,NULL,NULL,'2019-07-01 17:08:29',NULL,'NGANGO/127.0.0.1',NULL,'2019-07-01 17:09:08',1),(651,'Cedrick Barafinda',NULL,NULL,NULL,'2019-07-01 17:40:43',NULL,'NGANGO/127.0.0.1',NULL,'2019-07-01 17:41:07',2),(652,'Cedrick Barafinda',NULL,NULL,NULL,'2019-07-01 17:40:43',NULL,'NGANGO/127.0.0.1','2019-07-01 17:43:20',NULL,2),(653,'Fred Muhizi',NULL,NULL,NULL,'2019-07-01 17:43:20',NULL,'NGANGO/127.0.0.1',NULL,'2019-07-01 17:43:29',6);
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
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menuassignment`
--

LOCK TABLES `menuassignment` WRITE;
/*!40000 ALTER TABLE `menuassignment` DISABLE KEYS */;
INSERT INTO `menuassignment` VALUES (1,NULL,'2019-03-15 12:50:21','active',NULL,NULL,NULL,NULL,1,1),(2,NULL,'2019-03-15 12:50:21','active',NULL,NULL,NULL,NULL,2,1),(6,'admin','2018-10-10 21:32:47','active','2018-10-10 21:32:47','2018-10-10 21:32:47','admin',NULL,3,1),(7,'admin','2018-10-10 21:52:16','active','2018-10-10 21:52:16','2018-10-10 21:52:16','admin',NULL,4,1),(8,'admin','2018-10-10 21:52:55','active','2018-10-10 21:52:55','2018-10-10 21:52:55','admin',NULL,5,1),(9,NULL,'2019-03-24 18:41:11','active',NULL,NULL,NULL,NULL,8,1),(10,NULL,'2019-03-24 18:47:54','active',NULL,NULL,NULL,NULL,9,4),(11,NULL,'2019-03-28 07:34:21','active',NULL,NULL,NULL,NULL,10,2),(13,NULL,'2019-03-28 21:13:09','active',NULL,NULL,NULL,NULL,12,1),(15,NULL,'2019-03-31 15:17:21','active',NULL,NULL,NULL,NULL,17,1),(16,NULL,'2019-04-03 19:03:02','active',NULL,NULL,NULL,NULL,18,4),(17,NULL,NULL,'active',NULL,NULL,NULL,NULL,19,1),(18,NULL,NULL,'active',NULL,NULL,NULL,NULL,20,1),(19,NULL,'2019-04-23 17:18:38','active',NULL,NULL,NULL,NULL,24,2),(20,'ceo','2019-06-16 17:12:05','active',NULL,'2019-06-16 17:12:05','ceo',NULL,25,1),(21,'ceo','2019-06-17 12:33:01','active',NULL,'2019-06-17 12:33:01','ceo',NULL,28,2);
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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menugroup`
--

LOCK TABLES `menugroup` WRITE;
/*!40000 ALTER TABLE `menugroup` DISABLE KEYS */;
INSERT INTO `menugroup` VALUES (1,'Fred','2019-03-15 12:50:07','active',NULL,NULL,NULL,NULL,'My Tasks','M001',NULL,NULL,'My Task',1),(2,'Fred','2019-03-15 12:50:07','active',NULL,NULL,NULL,NULL,'Reports','M002',NULL,NULL,'Reports',1),(7,'admin','2019-03-15 16:43:24','active','2019-03-15 16:43:24','2019-03-15 16:43:24','admin','2019-03-15 16:43:24','My Tasks','M007',NULL,NULL,'My Task',4),(11,'admin','2019-03-15 16:44:07','active','2019-03-15 16:44:07','2019-03-15 16:44:07','admin','2019-03-15 16:44:07','My Tasks','M011',NULL,NULL,'My Task',2),(13,'admin','2019-03-15 16:44:07','active','2019-03-15 16:44:07','2019-03-15 16:44:07','admin','2019-03-15 16:44:07','My Tasks','M013',NULL,NULL,'My Task',3),(14,'ceo','2019-04-04 10:24:11','active',NULL,'2019-04-04 10:24:11','ceo','2019-04-04 10:24:11','Report','M004',NULL,NULL,'Reports',3),(15,'Fred','2019-06-17 12:25:01','active',NULL,NULL,NULL,NULL,'Reports','M003',NULL,NULL,'Reports',2);
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
  `productInfo` int(11) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`orderProductId`),
  KEY `FK2C65FDE1EA1001D9` (`customer`),
  KEY `FK2C65FDE18EB2D2AC` (`productInfo`),
  CONSTRAINT `FK2C65FDE18EB2D2AC` FOREIGN KEY (`productInfo`) REFERENCES `productassignment` (`prodAssId`),
  CONSTRAINT `FK2C65FDE1EA1001D9` FOREIGN KEY (`customer`) REFERENCES `users` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderproduct`
--

LOCK TABLES `orderproduct` WRITE;
/*!40000 ALTER TABLE `orderproduct` DISABLE KEYS */;
INSERT INTO `orderproduct` VALUES (6,'gael','2019-06-01 18:05:14','active',NULL,'2019-06-01 18:05:14',NULL,'2019-06-01 18:05:14','44',7,5,'pending'),(7,'super','2019-06-01 19:07:06','active',NULL,'2019-06-01 19:07:06',NULL,'2019-06-01 19:07:06','11',4,5,'pending'),(8,'gael','2019-06-01 19:28:46','active',NULL,'2019-06-01 19:28:46',NULL,'2019-06-01 19:28:46','5',7,3,'pending'),(9,'super','2019-06-24 17:09:17','active',NULL,'2019-06-24 17:09:17','manager','2019-06-01 19:50:50','49',4,9,'pending'),(10,'gael','2019-06-01 20:10:35','active',NULL,'2019-06-01 20:10:35',NULL,'2019-06-01 20:10:35','2',7,2,'pending'),(11,'gael','2019-06-04 17:42:30','active',NULL,'2019-06-04 17:42:30',NULL,'2019-06-04 17:42:30','9',7,7,'pending'),(12,'gael','2019-06-04 20:15:16','active',NULL,'2019-06-04 20:15:16',NULL,'2019-06-04 20:15:16','10',7,9,'pending'),(13,'gael','2019-06-04 20:15:39','active',NULL,'2019-06-04 20:15:39',NULL,'2019-06-04 20:15:39','2',7,2,'pending'),(14,'maxo','2019-06-11 19:16:04','active',NULL,'2019-06-11 19:16:04',NULL,'2019-06-11 19:16:04','20',8,9,'pending'),(15,'super','2019-06-15 11:18:07','active',NULL,'2019-06-15 11:18:07',NULL,'2019-06-15 11:18:07','16',4,9,'processed'),(16,'super','2019-06-16 14:20:15','active',NULL,'2019-06-16 14:20:15',NULL,'2019-06-16 14:20:15','8',4,7,'pending');
/*!40000 ALTER TABLE `orderproduct` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderproductcomment`
--

DROP TABLE IF EXISTS `orderproductcomment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderproductcomment` (
  `commentOrdId` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `crtdDtTime` datetime DEFAULT NULL,
  `genericStatus` varchar(255) DEFAULT NULL,
  `optLock` datetime DEFAULT NULL,
  `upDtTime` datetime DEFAULT NULL,
  `updatedBy` varchar(255) DEFAULT NULL,
  `comment` int(11) DEFAULT NULL,
  `orderProduct` int(11) DEFAULT NULL,
  PRIMARY KEY (`commentOrdId`),
  KEY `FK7A1A593ED2B004F` (`orderProduct`),
  KEY `FK7A1A593E7E669FF1` (`comment`),
  CONSTRAINT `FK7A1A593E7E669FF1` FOREIGN KEY (`comment`) REFERENCES `comment` (`commentId`),
  CONSTRAINT `FK7A1A593ED2B004F` FOREIGN KEY (`orderProduct`) REFERENCES `orderproduct` (`orderProductId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderproductcomment`
--

LOCK TABLES `orderproductcomment` WRITE;
/*!40000 ALTER TABLE `orderproductcomment` DISABLE KEYS */;
/*!40000 ALTER TABLE `orderproductcomment` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (2,'staff','2019-04-03 18:55:00','active',NULL,'2019-04-07 21:38:15','manager','2019-12-31 02:00:00','2019-04-03 02:00:00','Agashya','2500.0',230,'3000.0',3,'Agashya-pineapple-squash-150x150.png'),(3,'staff','2019-04-04 14:55:56','active',NULL,'2019-04-07 21:39:37','manager','2019-12-31 02:00:00','2019-04-04 02:00:00','Akandi','600.0',345,'800.0',3,'Akandi-natural-mineral-water-5L-150x150.png'),(7,'manager','2019-04-05 16:13:05','desactive',NULL,'2019-04-07 20:39:44','manager','2020-04-30 02:00:00','2019-04-05 02:00:00','composite Flour','1500.0',300,'2000.0',5,'Composite-Flour-300x300.png'),(8,'manager','2019-04-07 21:28:55','active',NULL,'2019-04-20 20:56:09','ceo','2020-04-07 02:00:00','2019-04-07 02:00:00','Akanozo','1800.0',90,'3000.0',5,'Composite-Flour-300x300.png'),(9,'ceo','2019-04-20 21:01:30','active',NULL,'2019-04-20 21:05:09','ceo','2020-04-30 02:00:00','2019-04-20 02:00:00','Akabanga','1000.0',20,'1500.0',3,'Agashya-passion-squash-300x300.png'),(20,'ceo','2019-06-11 19:53:12','active',NULL,'2019-06-12 12:45:18','ceo','2019-06-27 02:00:00','2019-06-05 02:00:00','rice','9500',350,'9950',3,'WhatsApp Image 2019-06-11 at 10.19.04 AM.jpeg'),(22,'ceo','2019-06-12 15:45:03','active',NULL,'2019-06-12 15:52:31','ceo','2022-06-21 02:00:00','2019-06-20 02:00:00','Nice Biscuit','1300.0',748,'1500.0',3,NULL),(23,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0.0',0,'0.0',NULL,NULL),(24,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0.0',0,'0.0',NULL,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productassignment`
--

LOCK TABLES `productassignment` WRITE;
/*!40000 ALTER TABLE `productassignment` DISABLE KEYS */;
INSERT INTO `productassignment` VALUES (1,'manager','2019-06-25 16:20:14','active',NULL,'2019-06-25 16:20:14','manager','2019-04-21 00:28:58',444,8,1),(2,'Fred Ribakare','2019-04-21 00:32:01','active',NULL,NULL,NULL,'2019-04-21 00:32:01',185,8,2),(3,'Fred Ribakare','2019-04-22 15:42:41','active',NULL,NULL,NULL,'2019-04-22 15:42:41',583,7,3),(4,'Fred Ribakare','2019-04-23 09:54:28','active',NULL,NULL,NULL,'2019-04-23 09:54:28',883,3,3),(5,'Fred Ribakare','2019-05-05 18:13:41','active',NULL,NULL,NULL,'2019-05-05 18:13:41',1283,9,1),(6,'Fred Ribakare','2019-05-27 21:08:10','active',NULL,NULL,NULL,'2019-05-27 21:08:10',773,9,1),(7,'Fred Ribakare','2019-05-27 21:21:44','active',NULL,NULL,NULL,'2019-05-27 21:21:44',200,3,2),(8,'Fred Ribakare','2019-06-01 19:39:16','active',NULL,NULL,NULL,'2019-06-01 19:39:16',1090,3,1),(9,'Fred Ribakare','2019-06-01 19:44:24','active',NULL,NULL,NULL,'2019-06-01 19:44:24',350,3,1),(10,'manager','2019-06-25 19:15:23','active',NULL,'2019-06-25 19:15:23','manager','2019-06-23 19:38:30',1455,22,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productcategory`
--

LOCK TABLES `productcategory` WRITE;
/*!40000 ALTER TABLE `productcategory` DISABLE KEYS */;
INSERT INTO `productcategory` VALUES (3,'manager','2019-04-05 12:47:02','active',NULL,'2019-04-20 19:34:33','ceo','Spices','Spices-category.jpg',1),(4,'manager','2019-04-05 16:05:04','active',NULL,'2019-04-05 16:05:04',NULL,'Beverages','Beverages.jpg',1),(5,'manager','2019-04-05 16:12:23','active',NULL,'2019-04-05 16:12:23',NULL,'Flour','Flours-category-267x300.jpg',1),(6,'ceo','2019-04-20 21:18:01','active',NULL,'2019-04-20 21:18:01',NULL,'Bakery','Bakery-category.jpg',1),(7,'ceo','2019-06-11 19:51:59','active',NULL,'2019-06-11 20:00:39','ceo','goo','JohnWayneLifeLessons.jpg',1),(8,'ceo','2019-06-11 20:16:32','active',NULL,'2019-06-11 20:16:32',NULL,'google','1.png',NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uploadingfiles`
--

LOCK TABLES `uploadingfiles` WRITE;
/*!40000 ALTER TABLE `uploadingfiles` DISABLE KEYS */;
INSERT INTO `uploadingfiles` VALUES (14,'manager','2019-04-05 12:50:24','active',NULL,NULL,NULL,14,2,NULL,3),(15,'manager','2019-04-05 16:05:18','active',NULL,NULL,NULL,15,2,NULL,4),(16,'manager','2019-04-05 16:12:34','active',NULL,NULL,NULL,16,2,NULL,5),(17,'manager','2019-04-05 17:02:08','active',NULL,NULL,NULL,17,2,3,NULL),(18,'manager','2019-04-07 20:38:39','active',NULL,NULL,NULL,18,2,2,NULL),(19,'manager','2019-04-07 20:40:22','active',NULL,NULL,NULL,19,2,7,NULL),(21,'ceo','2019-04-20 20:55:37','active',NULL,NULL,NULL,21,1,8,NULL),(22,'ceo','2019-04-20 21:05:44','active',NULL,NULL,NULL,22,1,9,NULL),(23,'ceo','2019-04-20 21:18:55','active',NULL,NULL,NULL,23,1,NULL,6),(24,'ceo','2019-06-11 19:52:11','active',NULL,NULL,NULL,24,1,NULL,7),(25,'ceo','2019-06-11 20:16:52','active',NULL,NULL,NULL,25,1,NULL,8),(26,'ceo','2019-06-12 12:46:27','active',NULL,NULL,NULL,26,1,20,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,NULL,'2019-03-15 12:46:54','active',NULL,NULL,NULL,'1987-11-05 12:46:54','Kigali',NULL,'Fred','male','us.png','Ribakare','online','active','ceo','21232f297a57a5a743894a0e4a801fc3',NULL,1,NULL),(2,NULL,'2019-03-15 12:46:54','active',NULL,NULL,NULL,'1987-11-05 12:46:54','Kigali',NULL,'Cedrick','male','us.png','Barafinda','offline','active','manager','21232f297a57a5a743894a0e4a801fc3',1,2,NULL),(3,NULL,'2019-03-15 12:46:54','active',NULL,NULL,NULL,'1994-03-19 12:46:54','Kigali',NULL,'Junior','male','us.png','Ngango','offline','active','staff','21232f297a57a5a743894a0e4a801fc3',1,3,NULL),(4,NULL,'2019-03-15 12:46:55','active',NULL,NULL,NULL,'1991-03-15 12:46:54','Kigali',NULL,'Emmi','male','us.png','Sibo','online','active','super','21232f297a57a5a743894a0e4a801fc3',NULL,4,NULL),(5,'admin','2019-05-19 13:04:00','active',NULL,NULL,'admin',NULL,NULL,'2019-05-19 13:04:00','Audrey','female','us.png','Gihozo','online','active','gihozo','64877e4b4afc68014695d68e54be8abd',NULL,4,NULL),(6,'admin','2019-05-27 21:16:33','active',NULL,NULL,'admin','1987-11-05 12:46:54','Kigali','2019-05-27 21:16:33','Fred','male','us.png','Muhizi','online','active','fred','21232f297a57a5a743894a0e4a801fc3',2,2,NULL),(7,'admin','2019-06-01 10:37:13','active',NULL,NULL,'admin',NULL,'Kigali','2019-06-01 10:37:13','Gael','male','us.png','Nsenga','online','active','gael','21232f297a57a5a743894a0e4a801fc3',NULL,4,NULL),(8,'admin','2019-06-11 19:07:19','active',NULL,NULL,'admin',NULL,'Kigali','2019-06-11 19:07:19','Maxime','male','us.png','Kagorora','offline','active','maxo','21232f297a57a5a743894a0e4a801fc3',NULL,4,NULL),(9,'admin','2019-06-11 19:38:18','active',NULL,NULL,'admin',NULL,'Kigali','2019-06-11 19:38:18','gggg','male','us.png','hhhh','offline','active','jim','21232f297a57a5a743894a0e4a801fc3',NULL,4,NULL);
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

-- Dump completed on 2019-07-02 12:54:09
