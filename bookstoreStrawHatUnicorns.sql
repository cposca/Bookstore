CREATE DATABASE  IF NOT EXISTS `bookstore2` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bookstore2`;
-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: bookstore2
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `address` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `street` varchar(100) NOT NULL,
  `province` varchar(20) NOT NULL,
  `country` varchar(20) NOT NULL,
  `zip` varchar(20) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'123 Yonge St','ON','Canada','K1E 6T5','647-123-4567'),(2,'445 Avenue rd','ON','Canada','M1C 6K5','416-123-8569'),(3,'789 Keele St.','ON','Canada','K3C 9T5','416-123-9568'),(10,'qwe','ab','CA','123','12312'),(11,'zxc','ab','CA','zxc','123'),(12,'qwe','ab','CA','123','12312');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `book` (
  `bid` varchar(20) NOT NULL,
  `title` varchar(60) NOT NULL,
  `price` int(11) NOT NULL,
  `category` enum('Science','Fiction','Engineering') NOT NULL,
  PRIMARY KEY (`bid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES ('b001','Little Prince',20,'Fiction'),('b002','Physics',201,'Science'),('b003','Mechanics',100,'Engineering'),('b007','test',4,'Engineering');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commerceevent`
--

DROP TABLE IF EXISTS `commerceevent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `commerceevent` (
  `bid` varchar(20) NOT NULL,
  `timestamp` varchar(20) NOT NULL,
  `eventType` varchar(20) NOT NULL,
  PRIMARY KEY (`timestamp`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commerceevent`
--

LOCK TABLES `commerceevent` WRITE;
/*!40000 ALTER TABLE `commerceevent` DISABLE KEYS */;
INSERT INTO `commerceevent` VALUES ('0','1555343379368','cart'),('0','1555343379400','cart'),('0','1555343379446','cart'),('0','1555343379464','cart'),('0','1555343386762','cart'),('0','1555343395783','cart'),('0','1555343395806','cart'),('0','1555343395822','cart'),('0','1555343548173','cart'),('0','1555343548201','cart'),('0','1555343548242','cart'),('0','1555343548262','cart'),('0','1555343553342','cart'),('0','1555343553359','cart'),('0','1555343553395','cart'),('0','1555343553419','cart'),('0','1555343560147','cart'),('0','1555343560167','cart'),('0','1555343560206','cart'),('0','1555343560223','cart'),('0','1555343595595','cart'),('0','1555343595615','cart'),('0','1555343595654','cart'),('0','1555343595670','cart'),('0','1555343599353','cart'),('0','1555343599370','cart'),('0','1555343599391','cart'),('0','1555343599407','cart'),('0','1555343603086','cart'),('0','1555343603104','cart'),('0','1555343603122','cart'),('0','1555343603141','cart'),('0','1555343748502','cart'),('0','1555343748566','cart'),('0','1555343748601','cart'),('0','1555343748623','cart'),('0','1555343769652','cart'),('0','1555343769672','cart'),('0','1555343769727','cart'),('0','1555343769744','cart'),('b003','1555343778413','view'),('b003','1555343778428','view'),('b003','1555343790317','view'),('b003','1555343790338','view'),('0','1555343795869','cart'),('0','1555343795886','cart'),('0','1555343795912','cart'),('0','1555343795928','cart'),('0','1555343826549','purchase'),('0','1555343831519','purchase'),('0','1555343838121','purchase'),('0','1555343841357','purchase'),('0','1555343844518','purchase'),('0','1555343848469','purchase'),('0','1555343859162','cart'),('0','1555343859176','cart'),('0','1555343859215','cart'),('0','1555343859229','cart'),('0','1555343864081','cart'),('0','1555343864099','cart'),('0','1555343864115','cart'),('0','1555343864127','cart'),('0','1555343877740','purchase'),('0','1555343882526','purchase'),('0','1555343882964','purchase');
/*!40000 ALTER TABLE `commerceevent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `login` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(256) DEFAULT NULL,
  `salt` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES (10,'qwe','854c09fb915146d0fc977eaef940df0ccc8d614ba8bb54e8b8115e8ef2598205','8ECaPjr1+urW2jKllB7bnXn6Bzr+5iui522vtPU3NlyYwTgK8kzo9G07yVRwB8bs03CWNSTdHk/MkVOom2aeLg=='),(11,'asd','8c890cfff8f53b0c72125d1d6258ee275080e220b77cc6e5e0915b9b697504d6','KaAIkbwQSaeEGYaskw4uqZmSoaBwir5BLmouklwhuW8ybiQgsiBky/c7hHKiQlICDuZtyhK/ubdKKudxiXqd2w=='),(12,'xcv','0979a617b44ed684ee1e2035404419a2c92e622b77585efd0c3907d81c4b309e','pCAoYpbHDv5bUXRTqkDCSRIfiZQkIyprnho3IXy5+Uyxqvjb8fokiuKJ3bZADJh3sIxe8Gqafa3P5gylHQ+i9w==');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `po`
--

DROP TABLE IF EXISTS `po`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `po` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `lname` varchar(20) NOT NULL,
  `fname` varchar(20) NOT NULL,
  `status` enum('ORDERED','PROCESSED','DENIED') NOT NULL,
  `address` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `address` (`address`),
  CONSTRAINT `po_ibfk_1` FOREIGN KEY (`address`) REFERENCES `address` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `po`
--

LOCK TABLES `po` WRITE;
/*!40000 ALTER TABLE `po` DISABLE KEYS */;
INSERT INTO `po` VALUES (1,'John','White','PROCESSED',1),(2,'Peter','Black','DENIED',2),(3,'Andy','Green','ORDERED',3),(4,'zxc','zxc','ORDERED',11),(5,'zxc','zxc','ORDERED',11),(6,'zxc','zxc','DENIED',11),(7,'zxc','zxc','ORDERED',11),(8,'zxc','zxc','ORDERED',11),(9,'zxc','zxc','ORDERED',11),(10,'zxc','zxc','ORDERED',11),(11,'zxc','zxc','ORDERED',11),(12,'zxc','zxc','DENIED',11),(13,'zxc','zxc','ORDERED',11),(14,'zxc','zxc','ORDERED',11),(15,'zxc','zxc','ORDERED',11),(16,'zxc','zxc','ORDERED',11),(17,'zxc','zxc','DENIED',11),(18,'zxc','zxc','ORDERED',11),(19,'zxc','zxc','ORDERED',11),(20,'zxc','zxc','DENIED',11),(21,'zxc','zxc','ORDERED',11),(22,'qwe','qwe','ORDERED',12),(23,'qwe','qwe','DENIED',12),(24,'qwe','qwe','ORDERED',12),(25,'qwe','qwe','ORDERED',12),(26,'qwe','qwe','DENIED',12),(27,'qwe','qwe','ORDERED',12),(28,'qwe','qwe','DENIED',12),(29,'qwe','qwe','ORDERED',12),(30,'qwe','qwe','ORDERED',12);
/*!40000 ALTER TABLE `po` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `poitem`
--

DROP TABLE IF EXISTS `poitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `poitem` (
  `id` int(10) unsigned NOT NULL,
  `bid` varchar(20) NOT NULL,
  `price` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`,`bid`),
  KEY `id` (`id`),
  KEY `bid` (`bid`),
  CONSTRAINT `poitem_ibfk_1` FOREIGN KEY (`id`) REFERENCES `po` (`id`) ON DELETE CASCADE,
  CONSTRAINT `poitem_ibfk_2` FOREIGN KEY (`bid`) REFERENCES `book` (`bid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `poitem`
--

LOCK TABLES `poitem` WRITE;
/*!40000 ALTER TABLE `poitem` DISABLE KEYS */;
INSERT INTO `poitem` VALUES (1,'b001',20),(2,'b002',201),(3,'b003',100),(4,'b001',20),(5,'b001',20),(6,'b001',20),(7,'b001',20),(8,'b001',20),(9,'b001',20),(10,'b001',20),(11,'b001',20),(12,'b001',20),(13,'b001',20),(14,'b001',20),(15,'b001',20),(16,'b001',20),(18,'b001',20),(19,'b001',20),(21,'b001',20),(22,'b001',20),(22,'b002',201),(22,'b003',100),(24,'b001',20),(24,'b002',201),(24,'b003',100),(25,'b001',20),(25,'b002',201),(25,'b003',100),(27,'b001',20),(27,'b002',201),(27,'b003',100),(29,'b001',20),(29,'b002',201),(29,'b003',100),(30,'b001',20),(30,'b002',201),(30,'b003',100);
/*!40000 ALTER TABLE `poitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviews`
--

DROP TABLE IF EXISTS `reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `reviews` (
  `reviewId` int(11) NOT NULL AUTO_INCREMENT,
  `bid` varchar(20) NOT NULL,
  `review` varchar(500) NOT NULL,
  `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`reviewId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviews`
--

LOCK TABLES `reviews` WRITE;
/*!40000 ALTER TABLE `reviews` DISABLE KEYS */;
INSERT INTO `reviews` VALUES (1,'b007','Enter your review\r\n		','2019-04-13 20:57:25','aaa'),(2,'b007','NOOOO!\r\n		','2019-04-13 21:01:59','bbbb'),(3,'b007','test\r\n		','2019-04-13 21:04:08',''),(4,'b007','Enter your review\r\n		','2019-04-13 21:04:08',''),(5,'b007','Enter your review\r\n		','2019-04-13 21:04:15','r'),(6,'b007','d','2019-04-13 21:04:25',''),(7,'b003','erwer','2019-04-13 21:09:43','wer'),(8,'b001','sdf\r\n		','2019-04-14 01:19:08','sdf'),(9,'b002','nice!\r\n		','2019-04-14 14:51:12','artem'),(10,'b003','asdasd','2019-04-15 11:56:30','asd');
/*!40000 ALTER TABLE `reviews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fname` varchar(20) DEFAULT NULL,
  `lname` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (11,'zxc','zxc'),(12,'qwe','qwe');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `visitevent`
--

DROP TABLE IF EXISTS `visitevent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `visitevent` (
  `username` varchar(20) NOT NULL,
  `timestamp` varchar(20) NOT NULL,
  `status` varchar(20) NOT NULL,
  `token` varchar(256) NOT NULL,
  `bid` varchar(20) NOT NULL,
  PRIMARY KEY (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visitevent`
--

LOCK TABLES `visitevent` WRITE;
/*!40000 ALTER TABLE `visitevent` DISABLE KEYS */;
INSERT INTO `visitevent` VALUES ('xcv','1555343760916','active','664adbe341732cd179d99ada667dcb1d8fbd2a074b47e4c2153a34ac622c8255','b003');
/*!40000 ALTER TABLE `visitevent` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-15 11:58:50
