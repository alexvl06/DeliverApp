CREATE DATABASE  IF NOT EXISTS `deliver_db` /*!40100 DEFAULT CHARACTER SET utf8mb3 COLLATE utf8_spanish_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `deliver_db`;
-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: deliver_db
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clients` (
  `idClient` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `phoneNumber` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL,
  `money` decimal(10,0) unsigned zerofill DEFAULT NULL,
  PRIMARY KEY (`idClient`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` VALUES (26,'Calle 76 sur # 46-22, Sabaneta, Antioquia','3053478433','alexisavila1991@gmail.com',0000940000),(27,'Calle 66a #55a-51, Medellín, Antioquia','3173856632','yinajuliana03@gmail.com',0000450000),(28,'Calle 10 # 20-27, Ciénaga, Magdalena','3052230574','alexis.avila@inter-telco.com',0001900000),(29,'Cra 35 # 15b-35, Medellín, Antioquia','6045200500','soporte@inter-telco.com',0320000000),(34,'Cra. 31 #68a, Medellín, Antioquia','6045200125','soporte@coke.com',1350000000);
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `legals`
--

DROP TABLE IF EXISTS `legals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `legals` (
  `NIT` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8_spanish_ci NOT NULL,
  `business name` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `idClient` int NOT NULL,
  PRIMARY KEY (`NIT`),
  KEY `fk_Legal_idx` (`idClient`),
  CONSTRAINT `fk_Legal` FOREIGN KEY (`idClient`) REFERENCES `clients` (`idClient`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `legals`
--

LOCK TABLES `legals` WRITE;
/*!40000 ALTER TABLE `legals` DISABLE KEYS */;
INSERT INTO `legals` VALUES ('1623485692','Coca-Cola Company',34),('9003825161','Inter-Telco S.A.S',29);
/*!40000 ALTER TABLE `legals` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `naturals`
--

DROP TABLE IF EXISTS `naturals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `naturals` (
  `CC` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8_spanish_ci NOT NULL,
  `firstname` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `secondname` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8_spanish_ci DEFAULT NULL,
  `first lastname` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL,
  `second lastname` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8_spanish_ci DEFAULT NULL,
  `idClient` int NOT NULL,
  PRIMARY KEY (`CC`),
  KEY `fk_Natural_idx` (`idClient`),
  CONSTRAINT `fk_Natural` FOREIGN KEY (`idClient`) REFERENCES `clients` (`idClient`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `naturals`
--

LOCK TABLES `naturals` WRITE;
/*!40000 ALTER TABLE `naturals` DISABLE KEYS */;
INSERT INTO `naturals` VALUES ('1017241138','Yina','Juliana','Micanquer','Caipe',27),('1083555169','Alexis','Rafael del Carmen','Ávila','Ortiz',26);
/*!40000 ALTER TABLE `naturals` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `code` int NOT NULL AUTO_INCREMENT,
  `description` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL,
  `brand` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL,
  `supplier` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL,
  `price` decimal(10,0) unsigned zerofill DEFAULT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (2,'Cuchilla de afeitar','Gillete','Almacenes Éxito',0000004500,560),(3,'Telefono móbil','Iphone 3 nano','Alkatronix',0002550000,12),(4,'Mancuernas para hacer ejercicio','Olympus','La tienda del Deportista',0000080800,67),(6,'Par de zapatos multi-usos','Brahma','Calzados Bucaramanga',0000140000,14);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `requests`
--

DROP TABLE IF EXISTS `requests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `requests` (
  `idRequest` int NOT NULL AUTO_INCREMENT,
  `creation_date` timestamp NOT NULL,
  `quantity` int unsigned NOT NULL,
  `status` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL DEFAULT '"untested"',
  `code` int NOT NULL,
  `idClient` int NOT NULL,
  PRIMARY KEY (`idRequest`),
  KEY `fk_client_idx` (`idClient`),
  KEY `fk_product_idx` (`code`),
  CONSTRAINT `fk_client` FOREIGN KEY (`idClient`) REFERENCES `clients` (`idClient`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_product` FOREIGN KEY (`code`) REFERENCES `products` (`code`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `requests`
--

LOCK TABLES `requests` WRITE;
/*!40000 ALTER TABLE `requests` DISABLE KEYS */;
INSERT INTO `requests` VALUES (12,'2022-05-23 06:17:12',1,'Pendiente',3,29),(13,'2022-05-23 06:17:13',1,'Pendiente',2,29),(14,'2022-05-23 06:17:20',1,'Pendiente',6,29);
/*!40000 ALTER TABLE `requests` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-23  4:11:24
