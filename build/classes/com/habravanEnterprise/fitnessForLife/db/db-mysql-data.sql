-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: gym_memberdb
-- ------------------------------------------------------
-- Server version	5.6.49-log

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
-- Table structure for table `client_gym_details
`
--
USE gym_memberdb_test;


--
-- Dumping data for table `client_gym_details`
--

LOCK TABLES `client_gym_details` WRITE;
/*!40000 ALTER TABLE `client_gym_details` DISABLE KEYS */;
INSERT INTO `client_gym_details` VALUES (1,1),(2,2),(3,3);
/*!40000 ALTER TABLE `client_gym_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client_membership`
--


--
-- Dumping data for table `client_membership`
--

LOCK TABLES `client_membership` WRITE;
/*!40000 ALTER TABLE `client_membership` DISABLE KEYS */;
INSERT INTO `client_membership` VALUES (1,1),(2,2);
/*!40000 ALTER TABLE `client_membership` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clients`
--


--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` VALUES (1,1,'Tom Hilfigher','Manchester',68754055,'tom_b@gmail.com','1986-02-02'),(2,2,'Laura Mercier','London',69883325,'laura@gmail.com','1999-02-11'),(3,3,'Giorgio Armani','Birmingham',69772255,'armanig@gmail.com','1989-10-10'),(4,4,'Janni Versace','Liverpool',67889922,'janniers@gmail.com','2000-09-07');
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gym_details`
--



--
-- Dumping data for table `gym_details`
--

LOCK TABLES `gym_details` WRITE;
/*!40000 ALTER TABLE `gym_details` DISABLE KEYS */;
INSERT INTO `gym_details` VALUES (1,'PureGym','Manchester',77883399),(2,'EnergyFitness','London',74996655),(3,'FitnessClass','Liverpool',71223366);
/*!40000 ALTER TABLE `gym_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `membership`
--



--
-- Dumping data for table `membership`
--

LOCK TABLES `membership` WRITE;
/*!40000 ALTER TABLE `membership` DISABLE KEYS */;
INSERT INTO `membership` VALUES (1,'OFF-PEAK','Monthly','Johnatan Gregor'),(2,'PLUS','Monthly','George Jameson');
/*!40000 ALTER TABLE `membership` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--



--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Tom','1234','2010-02-20');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;


