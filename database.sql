-- MySQL dump 10.13  Distrib 8.0.26, for macos11 (x86_64)
--
-- Host: localhost    Database: ecommerce
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `addresses`
--

DROP TABLE IF EXISTS `addresses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `addresses` (
  `address_id` bigint NOT NULL AUTO_INCREMENT,
  `street` varchar(255) NOT NULL,
  `unit` varchar(255) DEFAULT NULL,
  `city` varchar(255) NOT NULL,
  `state` varchar(2) NOT NULL,
  `country` varchar(45) NOT NULL,
  `zipcode` varchar(255) NOT NULL,
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `addresses`
--

LOCK TABLES `addresses` WRITE;
/*!40000 ALTER TABLE `addresses` DISABLE KEYS */;
INSERT INTO `addresses` VALUES (1,'123 Fake St',NULL,'Frisco','TX','US','75080');
/*!40000 ALTER TABLE `addresses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_items`
--

DROP TABLE IF EXISTS `order_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_items` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `product_id` bigint NOT NULL,
  `quantity` int NOT NULL,
  `user_id` bigint NOT NULL,
  `order_id` bigint NOT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_order_item_product1_idx` (`product_id`),
  KEY `fl_order_item_user1_idx` (`user_id`),
  KEY `fk_order_item_order1_idx` (`order_id`),
  CONSTRAINT `fk_order_item_order1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_order_item_product1` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_order_item_user1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=120 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
INSERT INTO `order_items` VALUES (11,1,3,1,12,NULL),(12,2,3,1,12,NULL),(13,1,3,1,14,NULL),(14,2,3,1,14,NULL),(15,2,3,1,15,NULL),(16,1,4,1,15,NULL),(17,1,4,1,16,NULL),(18,2,5,1,16,NULL),(19,1,8,1,17,NULL),(20,2,4,1,17,NULL),(21,1,1,1,18,NULL),(22,2,1,1,18,NULL),(23,1,1,1,19,NULL),(24,2,1,1,19,NULL),(25,1,2,1,20,NULL),(26,2,2,1,20,NULL),(27,1,1,1,21,NULL),(28,2,1,1,21,NULL),(29,2,3,1,22,NULL),(30,1,1,1,22,NULL),(31,1,2,1,23,NULL),(32,2,1,1,23,NULL),(33,1,1,1,24,NULL),(34,2,1,1,24,NULL),(35,1,2,1,25,NULL),(36,2,1,1,25,NULL),(37,1,1,1,26,NULL),(38,1,3,1,27,NULL),(39,2,2,1,28,NULL),(40,1,2,1,28,NULL),(41,2,1,1,29,NULL),(42,1,1,1,29,NULL),(43,2,1,1,30,NULL),(44,1,0,1,30,NULL),(45,2,1,1,31,NULL),(46,3,1,1,31,NULL),(47,1,1,1,31,NULL),(48,4,1,1,31,NULL),(49,1,1,1,32,NULL),(50,2,1,1,32,NULL),(51,3,1,1,32,NULL),(52,4,1,1,32,NULL),(53,5,1,1,32,NULL),(54,6,1,1,32,NULL),(55,7,1,1,32,NULL),(56,3,3,1,33,NULL),(57,4,3,1,33,NULL),(58,1,1,1,34,NULL),(59,5,1,1,34,NULL),(60,2,1,1,34,NULL),(61,3,1,1,34,NULL),(62,2,1,1,35,NULL),(63,1,1,1,36,NULL),(64,1,1,1,37,NULL),(65,2,1,1,37,NULL),(66,3,1,1,37,NULL),(67,3,4,1,38,NULL),(68,2,5,1,38,NULL),(69,2,3,1,39,NULL),(70,3,4,1,39,NULL),(71,1,1,1,40,NULL),(72,2,1,1,40,NULL),(73,3,1,1,40,NULL),(74,5,1,1,40,NULL),(75,3,1,1,41,NULL),(76,1,1,1,41,NULL),(77,2,2,1,41,NULL),(94,1,1,1,42,NULL),(95,2,1,1,42,NULL),(96,3,1,1,42,NULL),(105,2,1,1,43,NULL),(106,6,1,1,43,NULL),(107,5,1,1,43,NULL),(108,2,2,1,44,NULL),(109,3,4,1,44,NULL),(110,2,1,1,45,NULL),(111,3,1,1,45,NULL),(112,4,1,1,45,NULL),(115,3,1,1,46,NULL),(116,2,1,1,46,NULL),(117,2,1,1,47,NULL),(118,2,2,1,48,NULL),(119,3,4,1,48,NULL);
/*!40000 ALTER TABLE `order_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `order_date` date NOT NULL,
  `order_total` decimal(9,2) NOT NULL,
  `shipping_address_id` bigint NOT NULL,
  `billing_address_id` bigint NOT NULL,
  `order_status` varchar(45) NOT NULL,
  PRIMARY KEY (`order_id`,`user_id`),
  UNIQUE KEY `order_id_UNIQUE` (`order_id`),
  KEY `fk_order_user_idx` (`user_id`),
  KEY `fk_order_address1_idx` (`shipping_address_id`),
  KEY `fk_order_address2_idx` (`billing_address_id`),
  CONSTRAINT `fk_order_address1` FOREIGN KEY (`shipping_address_id`) REFERENCES `addresses` (`address_id`),
  CONSTRAINT `fk_order_address2` FOREIGN KEY (`billing_address_id`) REFERENCES `addresses` (`address_id`),
  CONSTRAINT `fk_order_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (5,1,'2022-08-22',130.00,1,1,'COMPLETED'),(12,1,'2022-08-22',90.00,1,1,'COMPLETED'),(14,1,'2022-08-22',90.00,1,1,'COMPLETED'),(15,1,'2022-08-22',100.00,1,1,'COMPLETED'),(16,1,'2022-08-23',140.00,1,1,'COMPLETED'),(17,1,'2022-08-23',160.00,1,1,'COMPLETED'),(18,1,'2022-08-23',30.00,1,1,'COMPLETED'),(19,1,'2022-08-23',30.00,1,1,'COMPLETED'),(20,1,'2022-08-23',60.00,1,1,'COMPLETED'),(21,1,'2022-08-23',30.00,1,1,'COMPLETED'),(22,1,'2022-08-23',70.00,1,1,'COMPLETED'),(23,1,'2022-08-23',40.00,1,1,'COMPLETED'),(24,1,'2022-08-23',30.00,1,1,'COMPLETED'),(25,1,'2022-08-23',40.00,1,1,'COMPLETED'),(26,1,'2022-08-23',10.00,1,1,'COMPLETED'),(27,1,'2022-08-23',30.00,1,1,'COMPLETED'),(28,1,'2022-08-23',60.00,1,1,'COMPLETED'),(29,1,'2022-08-23',30.00,1,1,'COMPLETED'),(30,1,'2022-08-23',20.00,1,1,'COMPLETED'),(31,1,'2022-08-23',160.00,1,1,'COMPLETED'),(32,1,'2022-08-23',2585.00,1,1,'COMPLETED'),(33,1,'2022-08-23',390.00,1,1,'COMPLETED'),(34,1,'2022-08-23',1981.00,1,1,'COMPLETED'),(35,1,'2022-08-23',990.00,1,1,'COMPLETED'),(36,1,'2022-08-23',770.00,1,1,'COMPLETED'),(37,1,'2022-08-24',1820.00,1,1,'COMPLETED'),(38,1,'2022-08-24',5190.00,1,1,'COMPLETED'),(39,1,'2022-08-24',3210.00,1,1,'COMPLETED'),(40,1,'2022-08-24',1981.00,1,1,'COMPLETED'),(41,1,'2022-08-24',2810.00,1,1,'COMPLETED'),(42,1,'2022-08-24',1820.00,1,1,'COMPLETED'),(43,1,'2022-08-24',1301.00,1,1,'COMPLETED'),(44,1,'2022-08-24',2220.00,1,1,'COMPLETED'),(45,1,'2022-08-24',1120.00,1,1,'COMPLETED'),(46,1,'2022-08-24',1050.00,1,1,'COMPLETED'),(47,1,'2022-08-24',990.00,1,1,'COMPLETED'),(48,1,'2022-08-24',2220.00,1,1,'COMPLETED');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders_cart_items`
--

DROP TABLE IF EXISTS `orders_cart_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders_cart_items` (
  `order_order_id` bigint NOT NULL,
  `cart_items_id` bigint NOT NULL,
  UNIQUE KEY `UK_6b1c8smsiwmstkr6gisj0o4np` (`cart_items_id`),
  KEY `FKs8m1h7ff8t2l37t0x6d33x3pf` (`order_order_id`),
  CONSTRAINT `FKm6lhs8klm10wrdbrl01uxws9` FOREIGN KEY (`cart_items_id`) REFERENCES `order_items` (`id`),
  CONSTRAINT `FKs8m1h7ff8t2l37t0x6d33x3pf` FOREIGN KEY (`order_order_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders_cart_items`
--

LOCK TABLES `orders_cart_items` WRITE;
/*!40000 ALTER TABLE `orders_cart_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders_cart_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `product_id` bigint NOT NULL AUTO_INCREMENT,
  `product_name` varchar(255) NOT NULL,
  `unit_price` decimal(9,2) NOT NULL,
  `product_stock` int NOT NULL DEFAULT '0',
  `product_image` varchar(255) DEFAULT NULL,
  `product_description` varchar(255) NOT NULL,
  PRIMARY KEY (`product_id`),
  UNIQUE KEY `product_id_UNIQUE` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Men\'s Ace embroidered sneaker',770.00,42,'gucciMen.jpg','The Ace low-top sneaker in leather'),(2,'Gucci Logo Women',990.00,25,'Guccilogo.jpg','Women\'s Rhyton Gucci logo leather sneaker'),(3,'Yeezy Boost 350',60.00,22,'yeezy.jpg','Yeeze Boost 350 sneakers'),(4,'Nike Air Jordan 1',70.00,44,'nike.jpg','Nike Air Jordan 1 sneaker'),(5,'Nike Air Max 270',161.00,46,'AirMax270.jpg','Nike Air Max 270\nNike Men\'s Shoes'),(6,'NMD_R1 PRIMEBLUE SHOES',150.00,48,'addidas.jpg','PROGRESSIVE SNEAKERS ROOTED IN RUNNING.'),(7,'Adidas Ivy Park x Ultraboost OG',384.00,49,'addidaspink.jpg',' Ivy Park x Ultraboost OG \'Ivy Heart\' Mens Sneakers');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `role_id` bigint NOT NULL,
  `role_name` varchar(45) NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `roles_id_UNIQUE` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ADMIN'),(2,'USER'),(3,'CUSTOMER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
  `role_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`role_id`,`user_id`),
  KEY `fk_user_roles_roles1_idx` (`role_id`),
  KEY `fk_user_roles_user1_idx` (`user_id`),
  CONSTRAINT `fk_user_roles_roles1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user_roles_user1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (1,1),(1,32),(1,33),(2,17),(2,18),(2,26),(2,33),(3,33);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name_UNIQUE` (`user_name`),
  UNIQUE KEY `iduser_UNIQUE` (`user_id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'tes2','12345','Alice','Nguyen','alice.nguyen@gmail.com'),(2,'test','12345','Test','TestUser','test@gmail.com'),(3,'killian','12345','Killian','Nguyen','killian.nguyen@gmail.com'),(4,'alice123','12345','Alice','Nguyen','alice.nguyen2529@gmail.com'),(5,'test12345','12345','Test','Nguyen','test.nguyen@gmail.com'),(6,'testhello','12345','Hello','World','hello@gmail.com'),(7,'qwe','12345','123','123','123@gmail.com'),(8,'test123','12345','TestNew','Hi','test123@gmail.com'),(9,'alice12345','12345','Alice','Nguyen','test234@gmail.com'),(11,'alice0001','$2a$10$uiCBrGKd8kr9JuNtSGjBjuU1btNt1ffpLL1/tPthRDyZKpc7zzjuW','Alice','Nguyen','alice.nguyen01@gmail.com'),(17,'alice00003','$2a$10$BxkpbRUYhnmMwkvsPZrsIe/BcRT5OAsfts9aahXapgCHnJ7xT/dYi','Alice','Nguyen','alice.nguyen23@gmail.com'),(18,'test13','$2a$10$oQX3YfI05YIb8R8zYdCdGuPBpl/vUcYAY4NHOA1I.jDR32vQugFkG','Test','Hi','test2@gmail.com'),(19,'Alice','12345','Alice','Nguyen','alicenguyen00000@gmaail.com'),(20,'huong2529','12345','huong','nguyen','huong12345@gmail.com'),(21,'Alice12','123456','Huong','Nguyen','huong.nguyen123@gmail.com'),(24,'Alice123456','1234567','Huong','Nguyen','huong.nguyen357@gmail.com'),(26,'user','$2a$10$1.Fq8ssoRXnVED/5GPKpou.6QUALdJjA/5SZgxvGJneEBZcpJTvOW','Killian','Nguyen','user@gmail.com'),(27,'killian02','$2a$10$hciOV5EQ1NggISfYOmOLwum5SObSk4OOzk/x7X1YJPYSjL72kNr5q','Killian2','Nguyyen','killian2@gmail.com'),(32,'admin','$2a$10$CAS6iCORONH12sWOyzeX9eolj.vv63lG18sZhgu/LCcSMo2En5PDO','Admin','Admin','admin@gmail.com'),(33,'boss','$2a$10$ERIORYKMjnrX8KEmabKVxuTmiK3k44oAP7saio8XAmrEKka7x49oi','Boss','Boss','customer@gmail.com');
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

-- Dump completed on 2022-08-24 14:18:11
