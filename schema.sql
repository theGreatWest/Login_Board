-- MySQL dump 10.13  Distrib 8.0.40, for macos14 (arm64)
--
-- Host: 127.0.0.1    Database: board
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `board_tb`
--

DROP TABLE IF EXISTS `board_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `board_tb` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `post_id` bigint NOT NULL,
  `status` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board_tb`
--

LOCK TABLES `board_tb` WRITE;
/*!40000 ALTER TABLE `board_tb` DISABLE KEYS */;
INSERT INTO `board_tb` VALUES (1,1,'ACTIVE'),(2,2,'ACTIVE'),(3,3,'INACTIVE');
/*!40000 ALTER TABLE `board_tb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post_tb`
--

DROP TABLE IF EXISTS `post_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post_tb` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `content` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_date` datetime NOT NULL,
  `like` bigint DEFAULT '0',
  `dislike` bigint DEFAULT '0',
  `category` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `board_id` bigint NOT NULL,
  `user_unique_number` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_tb`
--

LOCK TABLES `post_tb` WRITE;
/*!40000 ALTER TABLE `post_tb` DISABLE KEYS */;
INSERT INTO `post_tb` VALUES (1,'나만 웃긴가','인싸도 아싸도 아닌 어정쩡한 사람을 그럴싸라고 부르자','2024-09-08 00:00:00',3,15,'humor',1,6),(2,'마트에서 들은 젊은 부부 대화','여: 여보 저 강아지 봐봐. 우와~ 만져보고 싶다. 여보 우리도 강아지 키우면 안 될까?\n    남: (딱히 내키지 않아 망설이더니) 음.. 저기.. 내가 좀 더 개처럼 살게\n    \n    둘 다 정말 사랑스러움ㅋㅋㅋㅋ','2024-09-09 00:00:00',10,13,'daily',2,6),(3,'우리집 앵무새 데리고 건강검진 갔다가 수치플...','애가 검진 받는데 의사쌤한테 욕해서 병원 뒤집어짐ㅋㅋㅋ 선생님은 완전 오열하듯 웃곸ㅋㅋ 기다리던 집사님들도 단체로 빵터지는데 나혼자 창피해서 죽을뻔ㅋ큐ㅠ','2025-03-13 00:00:00',5,1,'humor',3,1);
/*!40000 ALTER TABLE `post_tb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reply_tb`
--

DROP TABLE IF EXISTS `reply_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reply_tb` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_date` datetime NOT NULL,
  `like` bigint DEFAULT '0',
  `dislike` bigint DEFAULT '0',
  `post_id` bigint NOT NULL,
  `user_unique_number` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reply_tb`
--

LOCK TABLES `reply_tb` WRITE;
/*!40000 ALTER TABLE `reply_tb` DISABLE KEYS */;
INSERT INTO `reply_tb` VALUES (1,'ㅇㅈ','2024-09-08 00:00:00',3,10,1,1),(2,'노잼ㅇㅇ','2024-09-09 00:00:00',10,2,1,4),(3,'웨이터, 락스 한 잔','2025-03-13 00:00:00',10,1,2,1);
/*!40000 ALTER TABLE `reply_tb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_tb`
--

DROP TABLE IF EXISTS `user_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_tb` (
  `unique_number` bigint NOT NULL AUTO_INCREMENT,
  `id` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nickname` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `phone1` varchar(3) COLLATE utf8mb4_unicode_ci NOT NULL,
  `phone2` varchar(4) COLLATE utf8mb4_unicode_ci NOT NULL,
  `phone3` varchar(4) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `last_login_at` datetime NOT NULL,
  `status` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`unique_number`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_tb`
--

LOCK TABLES `user_tb` WRITE;
/*!40000 ALTER TABLE `user_tb` DISABLE KEYS */;
INSERT INTO `user_tb` VALUES (1,'kyjBME','0123456789*','쮸','010','7162','2085','kjuzoojuk@naver.com','2025-03-13 00:00:00','ACTIVE'),(2,'honghong05','012345~','hongSound','010','1111','2323','hong@gmail.com','2021-02-05 00:00:00','INACTIVE'),(3,'funnyCat','hihi0505**','재밌당','010','3453','2111','funnyCat@hanmail.com','2024-01-01 00:00:00','INACTIVE'),(4,'gaja','!!01234','gajaaa','010','3422','4536','gaaaajaaaa@gmail.com','2025-03-13 00:00:00','ACTIVE'),(5,'jaanii','^hi1004^','자니','010','9800','9807','jani@naver.com','2025-02-21 00:00:00','DELETED'),(6,'outside','0000!!','밖이야','010','0012','3212','outoutbaby@naver.com','2024-09-08 00:00:00','ACTIVE'),(7,'cash02','0101**','닛몰캐시','010','5671','1122','cash@gmail.com','2025-01-08 00:00:00','ACTIVE');
/*!40000 ALTER TABLE `user_tb` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-13 13:07:19
