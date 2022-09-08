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
) ENGINE=InnoDB AUTO_INCREMENT=215 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
INSERT INTO `order_items` VALUES (181,3,1,32,61,NULL),(184,1,1,49,63,NULL),(185,2,1,49,63,NULL),(192,1,1,57,65,NULL),(193,2,1,57,65,NULL),(194,3,1,57,65,NULL),(195,1,1,49,64,NULL),(196,2,1,49,64,NULL),(197,3,1,49,64,NULL),(207,2,1,49,67,NULL),(208,3,1,49,67,NULL),(209,2,1,49,68,NULL),(210,5,1,49,68,NULL),(211,7,1,49,68,NULL),(212,2,1,58,66,NULL),(213,2,1,49,69,NULL);
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
  CONSTRAINT `fk_order_address1` FOREIGN KEY (`shipping_address_id`) REFERENCES `addresses` (`address_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_order_address2` FOREIGN KEY (`billing_address_id`) REFERENCES `addresses` (`address_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_order_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (61,32,'2022-08-30',60.00,1,1,'COMPLETED'),(62,32,'2022-08-30',1050.00,1,1,'In Progress'),(63,49,'2022-08-30',1760.00,1,1,'COMPLETED'),(64,49,'2022-08-30',4550.99,1,1,'COMPLETED'),(65,57,'2022-08-30',1820.00,1,1,'COMPLETED'),(66,58,'2022-08-31',2772.99,1,1,'COMPLETED'),(67,49,'2022-08-31',3421.99,1,1,'COMPLETED'),(68,49,'2022-08-31',6901.98,1,1,'COMPLETED'),(69,49,'2022-08-31',2772.99,1,1,'COMPLETED'),(70,49,'2022-08-31',1129.00,1,1,'In Progress');
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
INSERT INTO `products` VALUES (1,'iPhone 13 - 512 GB',1129.00,25,'https://res.cloudinary.com/dluvsyddd/image/upload/v1661872756/product1.png','A lightning‑fast chip. A leap in battery life. And all‑new photo and video capabilities.'),
	(2,'Macbook Pro 16\"',2772.99,18,'https://res.cloudinary.com/dluvsyddd/image/upload/v1661872751/product2.jpg','With the blazing-fast M1 Pro or M1 Max chip — the first Apple silicon designed for pros'),
    (3,'Sony Playstation 5',649.00,22,'https://res.cloudinary.com/dluvsyddd/image/upload/v1661872752/product3.jpg','Experience lightning-fast loading with an ultra-high speed SSD, deeper immersion with support for haptic feedback.'),
    (4,'Sony WF-1000XM4',229.99,39,'https://res.cloudinary.com/dluvsyddd/image/upload/v1661872753/product4.jpg','WF-1000XM4 earphones combine our most advanced noise cancelling with exceptional audio performance'),
    (5,'Samsung - 55\" Odyssey',3499.99,39,'https://res.cloudinary.com/dluvsyddd/image/upload/v1661872754/product5.jpg','55 inch, 1000R curved 4K UHD monitor with an exceptional picture quality via Quantum Mini-LED'),
    (6,'Samsung Galaxy Watch 5',449.99,46,'https://res.cloudinary.com/dluvsyddd/image/upload/v1661872755/product6.jpg','Worry less and do more and with a Sapphire Crystal Glass that’s more durable than before and a water resistant design that can take a splash⁴ or two. Go ahead, unleash the best you with Galaxy Watch5.'),
    (7,'Xbox Series X',629.00,48,'https://res.cloudinary.com/dluvsyddd/image/upload/v1661961072/product7.jpeg','Xbox Series X is the fastest, most powerful Xbox yet. Xbox Series S offers next-gen performance in the smallest Xbox ever.'),
    (8,'Nintendo Switch OLED',333.33,45,'https://res.cloudinary.com/dluvsyddd/image/upload/v1661961185/product8.jpeg','Nintendo Switch OLED');
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
INSERT INTO `user_roles` VALUES (1,32),(1,33),(2,32),(2,49),(2,50),(2,52),(2,57),(2,58),(2,59),(3,33);
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
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name_UNIQUE` (`user_name`),
  UNIQUE KEY `iduser_UNIQUE` (`user_id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (32,'admin','$2a$10$CAS6iCORONH12sWOyzeX9eolj.vv63lG18sZhgu/LCcSMo2En5PDO','Admin','Admin','admin@gmail.com','LOCAL'),(33,'boss','$2a$10$ERIORYKMjnrX8KEmabKVxuTmiK3k44oAP7saio8XAmrEKka7x49oi','Boss','Boss','boss@gmail.com','LOCAL'),(49,'user','$2a$10$h8kqjRSZ6rw/KF2RjU3lCOA7VY8nHFUzrvufJ1TplPSGvae8NNoDy','User','User','user@gmail.com','LOCAL'),(50,'tyler','$2a$10$/r5C9Ol5dRHolfjpJMlOTOJO/orVRjEAWZTFhv/MvnVLJl6r9OSly','Tyler','Tyhlker','werwer@gmail.com','LOCAL'),(52,'test222','$2a$10$oBUYEJnscbtjwDxXirDF5u8ZQql6Fm4azu8d2IP1abBdYCcmyUduC','Test','Test','test22@gmail.com','LOCAL'),(57,'alicetest@gmail.com','$2a$10$xbYv8bM201Sr86.HvMPDY.TxG7c/azN9sLWo4FfJLY.VYHth4pQ6e','Alice','Test','alicetest@gmail.com','OKTA'),(58,'huong.nguyen@hcl.com','$2a$10$UlCBexMRg.qW6LlV2fju0u/xq3JKNib4GX1IALqGJ1gln8jzcAG6u','Alice Huong','Nguyen','huong.nguyen@hcl.com','OKTA'),(59,'alicetestuser','$2a$10$MDLBWklZP26t0ACO42VZh.wgjMUcIqlT2kBYRnQPfBZF6Ga2LXgKS','Alice','Test','testalice@gmail.com','LOCAL');
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

-- Dump completed on 2022-08-31 14:39:21
