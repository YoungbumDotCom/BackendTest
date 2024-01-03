-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: young
-- ------------------------------------------------------
-- Server version	8.0.34

use kmove;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments`
(
    `id`       int NOT NULL auto_increment,
    `author`   int NOT NULL,
    `comment`  varchar(255) DEFAULT NULL,
    `reg_date` datetime(6)  DEFAULT NULL,
    `post_id`  int NOT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_table1_member_idx` (`author`),
    KEY `fk_table1_post1_idx` (`post_id`),
    CONSTRAINT `fk_table1_member` FOREIGN KEY (`author`) REFERENCES `member` (`id`),
    CONSTRAINT `fk_table1_post1` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `follow`
--

DROP TABLE IF EXISTS `follow`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `follow`
(
    `id`           int NOT NULL auto_increment,
    `follower_id`  int NOT NULL,
    `following_id` int NOT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_follow_member1_idx` (`follower_id`),
    KEY `fk_follow_member2_idx` (`following_id`),
    CONSTRAINT `fk_follow_member1` FOREIGN KEY (`follower_id`) REFERENCES `member` (`id`),
    CONSTRAINT `fk_follow_member2` FOREIGN KEY (`following_id`) REFERENCES `member` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member`
(
    `id`           int          NOT NULL auto_increment,
    `birth`        varchar(255) NOT NULL,
    `created_date` datetime(6)  DEFAULT NULL,
    `email`        varchar(255) DEFAULT NULL unique,
    `modified`     varchar(255) DEFAULT NULL,
    `name`         varchar(255) NOT NULL,
    `nickname`     varchar(255) NOT NULL unique,
    `password`     varchar(255) NOT NULL,
    `phone`        varchar(100) DEFAULT NULL,
    `text`         varchar(255) DEFAULT NULL,
    `verify`       varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='회원가입';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post`
(
    `id`        int NOT NULL auto_increment unique,
    `member_id` int NOT NULL,
    `content`   varchar(255) DEFAULT NULL,
    `image`     varchar(255) DEFAULT NULL,
    `reg_date`  datetime(6)  DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_post_member1_idx` (`member_id`),
    CONSTRAINT `fk_post_member1` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2024-01-03 10:12:37
