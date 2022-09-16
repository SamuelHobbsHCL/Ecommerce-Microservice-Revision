-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: ecommerce
-- ------------------------------------------------------
-- Server version	8.0.30

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
  `state` varchar(20) NOT NULL,
  `country` varchar(45) NOT NULL,
  `zipcode` varchar(255) NOT NULL,
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `addresses`
--

LOCK TABLES `addresses` WRITE;
/*!40000 ALTER TABLE `addresses` DISABLE KEYS */;
INSERT INTO `addresses` VALUES (1,'Default Address',NULL,'City','State','Country','75048'),(2,'1234 Hello blvd',NULL,'Sachse','Texas','United States','75048'),(3,'100 Rock St',NULL,'Fort Worth','Texas','United States','76123'),(4,'1212 Heritage Blvd',NULL,'Allen','Texas','United States','75081'),(5,'1010 Summer St','Apt 102','McKinney','Texas','United States','75023'),(6,'123 Fake St',NULL,'Garland','Texas','United States','75042'),(7,'1234 Test Test St',NULL,'Sachse','Texas','United States','75048'),(8,'1234 New Test Test St',NULL,'Sachse','Texas','United States','75048'),(9,'1234 New Test Test St','Apt 232','Sachse','Texas','United States','75048'),(10,'1234 Hello St',NULL,'Sachse','Texas','United States','75048'),(11,'12345 Hello St',NULL,'Sachse','Texas','United States','75048');
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
) ENGINE=InnoDB AUTO_INCREMENT=337 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
INSERT INTO `order_items` VALUES (181,3,1,32,61,NULL),(184,1,1,49,63,NULL),(185,2,1,49,63,NULL),(192,1,1,57,65,NULL),(193,2,1,57,65,NULL),(194,3,1,57,65,NULL),(195,1,1,49,64,NULL),(196,2,1,49,64,NULL),(197,3,1,49,64,NULL),(207,2,1,49,67,NULL),(208,3,1,49,67,NULL),(209,2,1,49,68,NULL),(210,5,1,49,68,NULL),(211,7,1,49,68,NULL),(212,2,1,58,66,NULL),(213,2,1,49,69,NULL),(217,2,1,49,70,NULL),(218,1,1,32,62,NULL),(219,1,2,49,71,NULL),(221,3,1,49,71,NULL),(222,2,1,49,72,NULL),(223,3,1,49,72,NULL),(224,1,1,58,73,NULL),(225,2,1,58,73,NULL),(233,4,1,49,74,NULL),(234,1,1,49,74,NULL),(235,1,1,58,75,NULL),(236,1,1,58,76,NULL),(237,2,1,58,76,NULL),(238,1,1,49,77,NULL),(239,2,1,49,77,NULL),(247,1,2,58,79,NULL),(248,2,2,58,79,NULL),(249,2,1,49,78,NULL),(250,3,2,49,78,NULL),(252,6,2,49,80,NULL),(253,4,1,49,80,NULL),(254,8,1,49,80,NULL),(256,1,2,49,82,NULL),(257,3,3,49,82,NULL),(260,2,1,49,83,NULL),(261,3,1,49,83,NULL),(264,3,1,49,86,NULL),(265,2,1,49,86,NULL),(266,2,2,49,87,NULL),(267,3,2,49,87,NULL),(268,5,1,49,87,NULL),(269,3,2,49,88,NULL),(270,2,1,49,88,NULL),(271,4,1,49,88,NULL),(277,2,2,49,89,NULL),(278,5,1,49,89,NULL),(279,6,1,49,89,NULL),(280,2,1,49,90,NULL),(281,8,1,49,90,NULL),(282,7,1,49,90,NULL),(283,4,1,49,90,NULL),(284,2,0,32,91,NULL),(285,8,1,32,91,NULL),(287,4,1,32,91,NULL),(288,2,0,32,92,NULL),(289,5,1,32,92,NULL),(290,7,1,32,92,NULL),(291,2,0,32,93,NULL),(292,1,1,32,94,NULL),(293,2,0,32,94,NULL),(294,3,1,32,94,NULL),(295,2,0,32,95,NULL),(296,2,2,32,96,NULL),(297,3,1,32,96,NULL),(318,2,1,49,98,NULL),(319,3,1,49,98,NULL),(324,2,2,49,99,NULL),(327,2,1,64,101,NULL),(328,1,1,64,101,NULL),(329,1,1,64,102,NULL),(330,2,1,64,102,NULL),(331,3,1,64,102,NULL),(332,2,1,64,103,NULL),(336,2,2,64,104,NULL);
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
  `shipping_address_id` bigint DEFAULT NULL,
  `billing_address_id` bigint DEFAULT NULL,
  `order_status` varchar(45) NOT NULL,
  PRIMARY KEY (`order_id`,`user_id`),
  UNIQUE KEY `order_id_UNIQUE` (`order_id`),
  KEY `fk_order_user_idx` (`user_id`),
  KEY `fk_order_address1_idx` (`shipping_address_id`),
  KEY `fk_order_address2_idx` (`billing_address_id`),
  CONSTRAINT `fk_order_address1` FOREIGN KEY (`shipping_address_id`) REFERENCES `addresses` (`address_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_order_address2` FOREIGN KEY (`billing_address_id`) REFERENCES `addresses` (`address_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_order_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (61,32,'2022-08-30',60.00,1,1,'COMPLETED'),(62,32,'2022-08-31',1129.00,1,1,'COMPLETED'),(63,49,'2022-08-30',1760.00,1,1,'COMPLETED'),(64,49,'2022-08-30',4550.99,1,1,'COMPLETED'),(65,57,'2022-08-30',1820.00,1,1,'COMPLETED'),(66,58,'2022-08-31',2772.99,1,1,'SHIPPED'),(67,49,'2022-08-31',3421.99,1,1,'COMPLETED'),(68,49,'2022-08-31',6901.98,1,1,'COMPLETED'),(69,49,'2022-08-31',2772.99,1,1,'COMPLETED'),(70,49,'2022-08-31',2772.99,1,1,'COMPLETED'),(71,49,'2022-08-31',2907.00,1,1,'COMPLETED'),(72,49,'2022-09-01',3421.99,1,1,'COMPLETED'),(73,58,'2022-09-01',3901.99,1,1,'COMPLETED'),(74,49,'2022-09-01',1358.99,1,1,'COMPLETED'),(75,58,'2022-09-01',1129.00,1,1,'DELIVERED'),(76,58,'2022-09-01',3901.99,1,1,'DELIVERED'),(77,49,'2022-09-01',3901.99,1,1,'COMPLETED'),(78,49,'2022-09-02',4070.99,1,1,'COMPLETED'),(79,58,'2022-09-01',7803.98,1,1,'DELIVERED'),(80,49,'2022-09-02',1463.30,1,1,'COMPLETED'),(82,49,'2022-09-06',4205.00,1,1,'COMPLETED'),(83,49,'2022-09-06',3421.99,1,1,'COMPLETED'),(85,58,'2022-09-06',2772.99,1,1,'In Progress'),(86,49,'2022-09-07',3421.99,1,1,'COMPLETED'),(87,49,'2022-09-08',10343.97,1,1,'COMPLETED'),(88,49,'2022-09-08',4300.98,1,1,'COMPLETED'),(89,49,'2022-09-08',9495.96,1,1,'COMPLETED'),(90,49,'2022-09-08',3965.31,1,1,'COMPLETED'),(91,32,'2022-09-08',563.32,1,1,'COMPLETED'),(92,32,'2022-09-08',4128.99,1,1,'COMPLETED'),(93,32,'2022-09-08',0.00,1,1,'COMPLETED'),(94,32,'2022-09-08',1778.00,1,1,'COMPLETED'),(95,32,'2022-09-08',0.00,1,1,'COMPLETED'),(96,32,'2022-09-08',6194.98,1,1,'COMPLETED'),(97,32,'2022-09-08',0.00,1,1,'In Progress'),(98,49,'2022-09-08',3421.99,1,1,'COMPLETED'),(99,49,'2022-09-12',5545.98,1,1,'COMPLETED'),(100,49,'2022-09-13',2907.00,1,1,'In Progress'),(101,64,'2022-09-13',3901.99,1,1,'DELIVERED'),(102,64,'2022-09-14',4550.99,1,1,'DELIVERED'),(103,64,'2022-09-14',2772.99,1,1,'DELIVERED'),(104,64,'2022-09-16',5545.98,1,1,'COMPLETED');
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'iPhone 13 - 512 GB',1129.00,20,'https://res.cloudinary.com/dluvsyddd/image/upload/v1661872756/product1.png','A lightning‑fast chip. A leap in battery life. And all‑new photo and video capabilities.'),(2,'Macbook Pro 16\"',2772.99,40,'https://res.cloudinary.com/dluvsyddd/image/upload/v1661872751/product2.jpg','With the blazing-fast M1 Pro or M1 Max chip — the first Apple silicon designed for pros'),(3,'Sony Playstation 5',649.00,65,'https://res.cloudinary.com/dluvsyddd/image/upload/v1661872752/product3.jpg','Experience lightning-fast loading with an ultra-high speed SSD, deeper immersion with support for haptic feedback.'),(4,'Sony WF-1000XM4',229.99,34,'https://res.cloudinary.com/dluvsyddd/image/upload/v1661872753/product4.jpg','WF-1000XM4 earphones combine our most advanced noise cancelling with exceptional audio performance'),(5,'Samsung - 55\" Odyssey',3499.99,76,'https://res.cloudinary.com/dluvsyddd/image/upload/v1661872754/product5.jpg','55 inch, 1000R curved 4K UHD monitor with an exceptional picture quality via Quantum Mini-LED'),(6,'Samsung Galaxy Watch 5',449.99,43,'https://res.cloudinary.com/dluvsyddd/image/upload/v1661872755/product6.jpg','Worry less and do more and with a Sapphire Crystal Glass that’s more durable than before and a water resistant design that can take a splash⁴ or two. Go ahead, unleash the best you with Galaxy Watch5.'),(7,'Xbox Series X',629.00,46,'https://res.cloudinary.com/dluvsyddd/image/upload/v1661961072/product7.jpg','Xbox Series X is the fastest, most powerful Xbox yet. Xbox Series S offers next-gen performance in the smallest Xbox ever.'),(8,'Nintendo Switch OLED',333.33,42,'https://res.cloudinary.com/dluvsyddd/image/upload/v1661961185/product8.jpg','Nintendo Switch OLED');
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
INSERT INTO `user_roles` VALUES (1,32),(1,33),(2,32),(2,49),(2,50),(2,52),(2,57),(2,58),(2,60),(2,61),(2,64),(2,71),(3,33);
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
  `auth_provider` varchar(15) DEFAULT NULL,
  `address_id` bigint DEFAULT NULL,
  `profile_image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name_UNIQUE` (`user_name`),
  UNIQUE KEY `iduser_UNIQUE` (`user_id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `address_fk_idx` (`address_id`),
  CONSTRAINT `address_fk` FOREIGN KEY (`address_id`) REFERENCES `addresses` (`address_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (32,'admin','$2a$10$CAS6iCORONH12sWOyzeX9eolj.vv63lG18sZhgu/LCcSMo2En5PDO','Admin','Admin','admin@gmail.com','LOCAL',2,NULL),(33,'boss','$2a$10$ERIORYKMjnrX8KEmabKVxuTmiK3k44oAP7saio8XAmrEKka7x49oi','Boss','Boss','boss@gmail.com','LOCAL',1,NULL),(49,'alice2529','$2a$10$ufxvUV.tZcLzfTjwk6iUP.vzckWdHKuKRK3YzJOlT.0rlA7GWIIku','Alice','Nguyen','user2@gmail.com','LOCAL',5,NULL),(50,'tyler','$2a$10$/r5C9Ol5dRHolfjpJMlOTOJO/orVRjEAWZTFhv/MvnVLJl6r9OSly','Tyler','Tyhlker','werwer@gmail.com','LOCAL',3,NULL),(52,'test222','$2a$10$.hm9mBlEasU8ZiLjRAOueuGlFCwwb5qTTOi.klJmoc0f8G57GRB26','Hello','Test','test22@gmail.com','LOCAL',4,NULL),(57,'alicetest@gmail.com','$2a$10$xbYv8bM201Sr86.HvMPDY.TxG7c/azN9sLWo4FfJLY.VYHth4pQ6e','Alice','Test','alicetest@gmail.com','OKTA',2,NULL),(58,'huong.nguyen@hcl.com','$2a$10$JxC.OQFodzNjGkxgKkZeo.El0Jq.UAiUTVQvJoOar4up8L/c9ohyy','Alice','Huong Nguyen','huong.nguyen@hcl.com','LOCAL',1,NULL),(60,'testnewuser','$2a$10$MW9al5e9sb9iwio9gZ2YjOhZMgnLyps8mKrbywFq7r8qkdBOTQeOy','Alice','Hello','test@gmail.com','LOCAL',2,NULL),(61,'d','$2a$10$dyf8E6yLRkB7Kr2hf1v.Oe2HqDIY3rzhqfJnabDk3ogmZP4Tb4212','d','d','d@gmail.com','LOCAL',3,NULL),(64,'alicenguyen2529','$2a$10$0pMtAMohnQjoywh0Pa6xXOjkfXyqAinMZDRtj4JPMjfYnmH2X0q5i','Alice','Nguyen','user@gmail.com','LOCAL',11,'http://res.cloudinary.com/dwnb2nqcu/image/upload/v1663310367/pkbqtamihrvibzynct2o.jpg');
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

-- Dump completed on 2022-09-16  5:29:24