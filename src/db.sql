CREATE DATABASE  IF NOT EXISTS `ecommarsproject` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ecommarsproject`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: ecommarsproject
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `categorie`
--

DROP TABLE IF EXISTS `categorie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categorie` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorie`
--

LOCK TABLES `categorie` WRITE;
/*!40000 ALTER TABLE `categorie` DISABLE KEYS */;
INSERT INTO `categorie` VALUES (1,'robot cleaner','robot'),(2,'robot smart','robot smart');
/*!40000 ALTER TABLE `categorie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produit`
--

DROP TABLE IF EXISTS `produit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produit` (
  `id` int NOT NULL AUTO_INCREMENT,
  `designation` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `prix` float NOT NULL,
  `quantite` int NOT NULL,
  `sdr` int NOT NULL,
  `idCateg` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKf3ahj0iohpvyyi4d8oa37tii1` (`idCateg`),
  CONSTRAINT `FKf3ahj0iohpvyyi4d8oa37tii1` FOREIGN KEY (`idCateg`) REFERENCES `categorie` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produit`
--

LOCK TABLES `produit` WRITE;
/*!40000 ALTER TABLE `produit` DISABLE KEYS */;
INSERT INTO `produit` VALUES (1,'Robot VacuumCombo G20','0bc2dc34-08f4-47b8-9173-191ef6260f7b23-tuya.jpg',200,6,0,1),(2,'Robot Vacuum Combo K10','26ef32c9-2dcd-4f9a-8179-a59258da6450Xiaomi-Mi-Robot-Vacuum-Mop-P-smart-home-cleaner.jpeg',150,1,0,1),(3,'Robot VacuumCombo G20','fa5f3f61-34cb-434f-bddd-6644d840986cH15ca54b67cd7464abff5400130e96aafM.jpg',300,16,0,1),(4,'Robot VacuumCombo T500','f30dd347-8ac6-4f65-882e-dd931693e71aH7dbfbc165abe48e7be4da8f110a89779V.jpg',450,8,0,1),(5,'Robot VacuumCombo T600','3b90b3fa-d480-4328-8685-ac1546b1c8d4fcf5ee8b-94a6-4f99-8024-ac0f95c25db0.jpeg',500,20,0,1),(6,'Robot Vacuum W12','8afea37e-4c7a-4edd-99a7-a19fcb47d0002022-Ecovacs-Deebot-X1-PRO-OMNI-5500pa-Vacuum-Cleaner-Sweeping-Robot-Sweeping-Mopping-Washing-Mop-Automatic.jpg',999,2,0,2),(7,'Robot Vacuum W15','0152dc47-825c-4b53-aab4-acbc3e389404360_robot_vacuum_cleaner_empowered_by_triple_eye_LiDAR.webp',699,3,0,2),(8,'Vacuum G10','a25dec77-bc01-41cd-b63b-e75f3f6318b5images (1).jpeg',199,3,0,2),(9,'Vacuum G11','80ebd65c-9f85-44c1-8e50-a7bba0c62a8fH74c7d30cd2a7454c917d670e86321785H.jpg',299,3,0,1),(10,'Vacuum G12','6843e6d3-0c9c-4731-883d-47779c2f3b9a91bd6975-90ff-43ab-b93c-64a903b5f6d7.jpeg',399,4,0,1),(11,'Vacuum H1010','bcde4c1b-a4b9-41ae-8778-1f7db4f3b39123-tuya.jpg',159,1,0,2),(12,'Vacuum H1011','b4cb04b8-4263-49f8-9306-980752fbc4c5OZMO-Mopping-01.jpg',189,10,0,2),(13,'Vacuum H1011','7798fefe-fe9e-4c82-81b9-1fa8858884b2image.jpg',189,10,0,2);
/*!40000 ALTER TABLE `produit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `mdp` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-02  2:13:07
