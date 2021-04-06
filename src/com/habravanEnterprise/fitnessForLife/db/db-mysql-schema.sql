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

DROP TABLE IF EXISTS `client_gym_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client_gym_details` (
  `Client_ID` int(11) NOT NULL,
  `Gym_ID` int(11) NOT NULL,
  KEY `Client_ID_idx` (`Client_ID`),
  KEY `Gym_ID_idx` (`Gym_ID`),
  CONSTRAINT `Client_Nr` FOREIGN KEY (`Client_ID`) REFERENCES `clients` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Gym_ID` FOREIGN KEY (`Gym_ID`) REFERENCES `gym_details` (`ID_Gym`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--


--
-- Table structure for table `client_membership`
--

DROP TABLE IF EXISTS `client_membership`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client_membership` (
  `Client_ID` int(11) NOT NULL,
  `Membership_ID` int(11) NOT NULL,
  KEY `Client_ID_idx` (`Client_ID`),
  KEY `Membership_ID_idx` (`Membership_ID`),
  CONSTRAINT `Client_ID` FOREIGN KEY (`Client_ID`) REFERENCES `clients` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Membership_ID` FOREIGN KEY (`Membership_ID`) REFERENCES `membership` (`ID_Membership`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--


--
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clients` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `User_ID` int(11) NOT NULL,
  `FullName` varchar(155) DEFAULT NULL,
  `Location` varchar(155) DEFAULT NULL,
  `Phone_Number` int(11) DEFAULT NULL,
  `Email_Address` varchar(155) DEFAULT NULL,
  `Date_of_Birth` date DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `FullName_UNIQUE` (`FullName`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--


--
-- Table structure for table `gym_details`
--

DROP TABLE IF EXISTS `gym_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gym_details` (
  `ID_Gym` int(11) NOT NULL AUTO_INCREMENT,
  `Gym_Name` varchar(155) NOT NULL,
  `Gym_Location` varchar(155) DEFAULT NULL,
  `Gym_Contact_Number`  int(11) NOT NULL,
  PRIMARY KEY (`ID_Gym`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--

--
-- Table structure for table `membership`
--

DROP TABLE IF EXISTS `membership`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `membership` (
  `ID_Membership` int(11) NOT NULL AUTO_INCREMENT,
  `Membership_Type` varchar(155) DEFAULT NULL,
  `Membership_Period` varchar(155) DEFAULT NULL,
  `Instructor_Name` varchar(155) DEFAULT NULL,
  PRIMARY KEY (`ID_Membership`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--


--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `ID` int(11) NOT NULL,
  `User_Name` varchar(155) DEFAULT NULL,
  `User_Password` varchar(155) DEFAULT NULL,
  `Connection_Date` date DEFAULT NULL,
  KEY `ID_idx` (`ID`),
 CONSTRAINT `ID` FOREIGN KEY (`ID`) REFERENCES `clients` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`

--

