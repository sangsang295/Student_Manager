CREATE DATABASE  IF NOT EXISTS `student` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `student`;
-- MySQL dump 10.13  Distrib 8.0.33, for macos13 (arm64)
--
-- Host: 10.211.55.4    Database: student
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
-- Table structure for table `cla2sub`
--

DROP TABLE IF EXISTS `cla2sub`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cla2sub` (
  `cla2sub_id` int NOT NULL AUTO_INCREMENT,
  `cla_id` int DEFAULT NULL,
  `sub_id` int DEFAULT NULL,
  `tec_id` int DEFAULT NULL,
  PRIMARY KEY (`cla2sub_id`),
  UNIQUE KEY `uni_cla_sub` (`cla_id`,`sub_id`),
  KEY `fk_cla2sub_sub` (`sub_id`),
  KEY `fk_cla2sub_cla` (`cla_id`),
  KEY `tec_id` (`tec_id`),
  CONSTRAINT `cla2sub_ibfk_1` FOREIGN KEY (`tec_id`) REFERENCES `teacher` (`tec_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_cla2sub_cla` FOREIGN KEY (`cla_id`) REFERENCES `classes` (`cla_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_cla2sub_sub` FOREIGN KEY (`sub_id`) REFERENCES `subject` (`sub_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cla2sub`
--

LOCK TABLES `cla2sub` WRITE;
/*!40000 ALTER TABLE `cla2sub` DISABLE KEYS */;
INSERT INTO `cla2sub` VALUES (1,1,1,1),(2,1,2,2),(3,2,1,1),(4,2,3,2),(5,2,4,2),(7,3,2,3),(8,4,4,3),(9,4,5,4),(12,5,4,4),(13,7,1,1),(14,7,4,7),(15,7,5,7),(16,5,3,7),(17,5,5,7);
/*!40000 ALTER TABLE `cla2sub` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classes`
--

DROP TABLE IF EXISTS `classes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `classes` (
  `cla_id` int NOT NULL AUTO_INCREMENT,
  `cla_name` varchar(22) DEFAULT NULL,
  `maj_id` int DEFAULT NULL,
  `cla_tec` varchar(22) DEFAULT NULL,
  PRIMARY KEY (`cla_id`),
  UNIQUE KEY `uni_name` (`cla_name`),
  KEY `fk_cla_maj` (`maj_id`),
  CONSTRAINT `fk_cla_maj` FOREIGN KEY (`maj_id`) REFERENCES `major` (`maj_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classes`
--

LOCK TABLES `classes` WRITE;
/*!40000 ALTER TABLE `classes` DISABLE KEYS */;
INSERT INTO `classes` VALUES (1,'12网编1班',1,'李红'),(2,'12ERP1班',2,'陈鑫'),(3,'12UI1班',3,'王伟'),(4,'12智能楼宇1班',4,'钟宁涛'),(5,'12网络1班',5,'陶月敏'),(7,'12游软1班',6,'刘海');
/*!40000 ALTER TABLE `classes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feedback` (
  `fb_id` varchar(36) NOT NULL,
  `sco_id` int DEFAULT NULL,
  `fb_le` int NOT NULL,
  `fb_cd` int NOT NULL,
  `fb_ca` int NOT NULL,
  `fb_he` int NOT NULL,
  `fb_cm` int NOT NULL,
  PRIMARY KEY (`fb_id`),
  UNIQUE KEY `sco_id_UNIQUE` (`sco_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` VALUES ('0ed5194c-7875-495d-a524-e7d8a808158d',33,4,3,2,1,1),('6899be35-892e-45ad-8de7-ac22c95031ca',10,3,0,1,4,1),('7001c72e-5f24-4240-8113-a43d3f39d189',6,3,3,4,1,1),('768db6d8-c4ee-4764-a583-186230a89056',32,4,3,1,3,2),('7936ec31-d7b8-4b06-9669-68eb71c27b23',43,1,4,2,2,2),('83b1649c-fba1-4c78-b0bf-640dbde697ca',41,1,4,0,3,3);
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `major`
--

DROP TABLE IF EXISTS `major`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `major` (
  `maj_id` int NOT NULL AUTO_INCREMENT,
  `maj_name` varchar(22) DEFAULT NULL,
  `maj_prin` varchar(22) DEFAULT NULL,
  `maj_link` varchar(22) DEFAULT NULL,
  `maj_phone` varchar(22) DEFAULT NULL,
  PRIMARY KEY (`maj_id`),
  UNIQUE KEY `uni_name` (`maj_name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `major`
--

LOCK TABLES `major` WRITE;
/*!40000 ALTER TABLE `major` DISABLE KEYS */;
INSERT INTO `major` VALUES (1,'软件','李红','李红','123456'),(2,'信管','刘海','刘海','1234567'),(3,'游美','钱五','钱五','1234567'),(4,'蓝盾','陶月敏','陶月敏','1234567'),(5,'网络','陈鑫','陈鑫','1234567'),(6,'游软','王伟','王伟','1234567');
/*!40000 ALTER TABLE `major` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operator`
--

DROP TABLE IF EXISTS `operator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `operator` (
  `ope_id` int NOT NULL AUTO_INCREMENT,
  `ope_name` varchar(22) DEFAULT NULL,
  `ope_pwd` varchar(22) DEFAULT NULL,
  `rol_id` int DEFAULT NULL,
  PRIMARY KEY (`ope_id`),
  UNIQUE KEY `uni_name` (`ope_name`),
  KEY `fk_ope_rol` (`rol_id`),
  CONSTRAINT `fk_ope_rol` FOREIGN KEY (`rol_id`) REFERENCES `role` (`rol_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operator`
--

LOCK TABLES `operator` WRITE;
/*!40000 ALTER TABLE `operator` DISABLE KEYS */;
INSERT INTO `operator` VALUES (1,'admin','admin',1),(2,'tec1','tec1',2),(3,'tec2','tec2',2),(4,'tec3','tec3',2),(5,'tec4','tec4',2),(6,'tec5','tec5',2),(7,'tec6','tec6',2),(8,'stu01','stu01',3),(9,'stu02','stu02',3),(10,'stu03','stu03',3),(11,'stu04','stu04',3),(12,'stu05','stu05',3),(13,'stu06','stu06',3),(14,'stu07','stu07',3),(36,'stu08','stu08',3),(37,'tec7','tec7',2),(38,'stu09','stu09',3),(39,'stu10','stu10',3),(40,'tec8','tec8',2),(41,'stu11','000000',3),(42,'stu12','000000',3),(43,'stu13','000000',3),(44,'stu14','000000',3),(45,'stu15','000000',3),(46,'stu16','000000',3),(47,'stu17','000000',3),(48,'stu18','000000',3),(49,'stu19','000000',3),(50,'stu00','000000',3);
/*!40000 ALTER TABLE `operator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `privilege`
--

DROP TABLE IF EXISTS `privilege`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `privilege` (
  `pri_id` int NOT NULL AUTO_INCREMENT,
  `pri_name` varchar(22) DEFAULT NULL,
  `pri_url` varchar(55) DEFAULT NULL,
  `menu_name` varchar(22) DEFAULT NULL,
  `rol_id` int DEFAULT NULL,
  PRIMARY KEY (`pri_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `privilege`
--

LOCK TABLES `privilege` WRITE;
/*!40000 ALTER TABLE `privilege` DISABLE KEYS */;
INSERT INTO `privilege` VALUES (1,'我的资料','/Student/InfoStudentServlet','学生管理',3),(2,'查询成绩','/Student/pages/search_score.jsp','成绩管理',3),(3,'查询课程','/Student/pages/search_subject.jsp','课程管理',3),(4,'同班同学','/Student/SearchClassmatesServlet','班级管理',3),(5,'我的资料','/Student/InfoTeacherServlet','教师管理',2),(6,'查找学生','/Student/pages/search_student.jsp','学生管理',2),(7,'学生成绩','/Student/pages/search_score.jsp','成绩管理',2),(8,'我的班级','/Student/SearchTeacherClassServlet','班级管理',2),(9,'我的课程','/Student/pages/search_subject.jsp','课程管理',2),(10,'添加学生','/Student/PlanAddStudentServlet','学生管理',1),(11,'查询学生','/Student/pages/search_student.jsp','学生管理',1),(12,'添加班级','/Student/PlanAddClassesServlet','班级管理',1),(13,'查询班级','/Student/pages/search_classes.jsp','班级管理',1),(14,'添加班级课程','/Student/PlanAddCla2SubSevlet','班级管理',1),(15,'查询班级课程','/Student/pages/search_classes_subject.jsp','班级管理',1),(16,'添加教师','/Student/pages/add_teacher.jsp','教师管理',1),(17,'查询教师','/Student/pages/search_teacher.jsp','教师管理',1),(18,'查询成绩','/Student/pages/search_score.jsp','成绩管理',1),(19,'添加课程','/Student/pages/add_subject.jsp','课程管理',1),(20,'查询课程','/Student/pages/search_subject.jsp','课程管理',1),(21,'添加专业','/Student/pages/add_major.jsp','专业管理',1),(22,'查询专业','/Student/pages/search_major.jsp','专业管理',1),(23,'班级课程表','/Student/pages/export_classes_subject.jsp','班级管理',1),(24,'班级课程表','/Student/pages/export_classes_subject.jsp','班级管理',2),(25,'班级课程表','/Student/pages/export_classes_subject.jsp','班级管理',3),(26,'导出班级成绩','/Student/ReportScoreServlet','班级管理',2),(27,'成绩统计','/Student/pages/statistics_score.html','成绩管理',1),(28,'反馈结果','/Student/pages/statistics_feedback.html','课程管理',1);
/*!40000 ALTER TABLE `privilege` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `rol_id` int NOT NULL AUTO_INCREMENT,
  `rol_name` varchar(22) DEFAULT NULL,
  PRIMARY KEY (`rol_id`),
  UNIQUE KEY `uni_name` (`rol_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (3,'学生'),(2,'教师'),(1,'管理员');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `score`
--

DROP TABLE IF EXISTS `score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `score` (
  `sco_id` int NOT NULL AUTO_INCREMENT,
  `sco_daily` float DEFAULT '0',
  `sco_exam` float DEFAULT '0',
  `sco_count` float DEFAULT '0',
  `stu_id` int DEFAULT NULL,
  `sub_id` int DEFAULT NULL,
  `cla2sub_id` int NOT NULL,
  `cla_id` int NOT NULL,
  PRIMARY KEY (`sco_id`),
  UNIQUE KEY `uni_stu_sub` (`stu_id`,`sub_id`,`cla2sub_id`),
  KEY `fk_sco_sub` (`sub_id`),
  KEY `fk_sco_stu` (`stu_id`),
  KEY `fk_sco_cla` (`cla2sub_id`),
  KEY `cla_id` (`cla_id`),
  CONSTRAINT `score_ibfk_1` FOREIGN KEY (`stu_id`) REFERENCES `student` (`stu_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `score_ibfk_2` FOREIGN KEY (`sub_id`) REFERENCES `subject` (`sub_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `score_ibfk_3` FOREIGN KEY (`cla2sub_id`) REFERENCES `cla2sub` (`cla2sub_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `score_ibfk_4` FOREIGN KEY (`cla_id`) REFERENCES `classes` (`cla_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `score`
--

LOCK TABLES `score` WRITE;
/*!40000 ALTER TABLE `score` DISABLE KEYS */;
INSERT INTO `score` VALUES (1,66,30,96,1,1,1,1),(2,20,72,92,1,2,2,1),(3,33,64,97,2,1,1,1),(4,28,62,90,2,2,2,1),(5,18,61,79,3,1,3,2),(6,31,66,97,3,3,4,2),(7,28,60,88,3,4,5,2),(8,33,56,89,4,1,3,2),(9,21,45,66,4,3,4,2),(10,25,65,90,4,4,5,2),(12,0,0,0,5,2,7,3),(14,30,66,96,6,2,7,3),(15,0,0,0,7,4,8,4),(16,20,60,80,7,5,9,4),(25,0,0,0,8,4,8,4),(26,14,55,69,8,5,9,4),(27,0,0,0,9,3,16,5),(28,0,0,0,10,3,16,5),(29,12,50,62,9,5,17,5),(30,20,73,93,10,5,17,5),(31,30,65,95,11,2,2,1),(32,11,34,45,12,1,1,1),(33,13,43,56,13,1,1,1),(34,22,48,70,14,1,1,1),(35,22,48,70,15,1,1,1),(36,23,48,71,16,1,1,1),(37,25,46,71,17,1,1,1),(38,24,50,74,18,1,1,1),(39,26,47,73,19,1,1,1),(40,27,47,75,20,1,1,1),(41,22,44,66,14,2,2,1),(42,21,40,61,15,2,2,1),(43,19,45,64,16,2,2,1),(44,25,46,71,17,2,2,1),(45,20,45,65,18,2,2,1),(46,28,53,81,19,2,2,1),(47,22,47,69,20,2,2,1);
/*!40000 ALTER TABLE `score` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `stu_id` int NOT NULL AUTO_INCREMENT,
  `ope_id` int DEFAULT NULL,
  `stu_no` varchar(22) DEFAULT NULL,
  `stu_name` varchar(22) DEFAULT NULL,
  `stu_sex` enum('男','女') DEFAULT '男',
  `stu_birth` date DEFAULT NULL,
  `stu_pic` varchar(22) DEFAULT NULL,
  `cla_id` int DEFAULT NULL,
  PRIMARY KEY (`stu_id`),
  UNIQUE KEY `uni_no` (`stu_no`),
  UNIQUE KEY `uni_ope` (`ope_id`),
  KEY `fk_stu_cla` (`cla_id`),
  KEY `fk_stu_ope` (`ope_id`),
  CONSTRAINT `fk_stu_cla` FOREIGN KEY (`cla_id`) REFERENCES `classes` (`cla_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_stu_ope` FOREIGN KEY (`ope_id`) REFERENCES `operator` (`ope_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,8,'01','陈美丽','女','2013-02-26','../images/person.png',1),(2,9,'02','王伟强','男','2013-11-23','../images/person.png',1),(3,10,'03','蔡佳金','女','2013-11-19','../images/person.png',2),(4,11,'04','何凤','男','2013-11-05','../images/person.png',2),(5,12,'05','李言春','女','2013-11-19','../images/person.png',3),(6,13,'06','董明','男','2013-11-03','../images/person.png',3),(7,14,'07','吴小娟','女','2013-11-03','../images/person.png',4),(8,36,'08','周建国','男','2013-09-06','../images/person.png',4),(9,38,'09','魏坤','女','2013-11-19','../images/person.png',5),(10,39,'10','管华山','男','2013-11-21','../images/person.png',5),(11,50,'11','慕枕山','男','2013-11-21','../images/person.png',2),(12,41,'12','白朝恩','男','2013-09-11','../images/person.png',1),(13,42,'13','余佳期','女','2013-07-02','../images/person.png',1),(14,43,'14','白璐','女','2013-08-11','../images/person.png',1),(15,44,'15','杨虎','男','2013-08-19','../images/person.png',1),(16,45,'16','孙柏','男','2013-11-11','../images/person.png',1),(17,46,'17','苏月','女','2013-01-06','../images/person.png',1),(18,47,'18','晋安','男','2013-07-22','../images/person.png',1),(19,48,'19','吴华','女','2013-02-21','../images/person.png',1),(20,49,'20','南宫清河','男','2013-04-06','../images/person.png',1);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subject` (
  `sub_id` int NOT NULL AUTO_INCREMENT,
  `sub_name` varchar(22) DEFAULT NULL,
  `sub_type` varchar(22) DEFAULT NULL,
  `sub_times` int DEFAULT NULL,
  PRIMARY KEY (`sub_id`),
  UNIQUE KEY `uni_name` (`sub_name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES (1,'J2SE','必修',108),(2,'C语言','必修',108),(3,'PhotoShop','选修',56),(4,'DIV+CSS','必修',96),(5,'矢量图','选修',56);
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teacher` (
  `tec_id` int NOT NULL AUTO_INCREMENT,
  `ope_id` int DEFAULT NULL,
  `tec_name` varchar(22) DEFAULT NULL,
  `tec_birth` date DEFAULT NULL,
  `tec_sex` enum('男','女') DEFAULT '男',
  `tec_major` varchar(22) DEFAULT NULL,
  `tec_phone` varchar(22) DEFAULT NULL,
  PRIMARY KEY (`tec_id`),
  UNIQUE KEY `uni_ope` (`ope_id`),
  UNIQUE KEY `fk_tec_ope` (`ope_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES (1,2,'李红','1988-04-14','女','J2SE','1234567'),(2,3,'陈鑫','1990-07-21','女','C语言','1234567'),(3,4,'王伟','2013-10-07','男','DIV+CSS','1234567'),(4,5,'钟宁涛','2013-10-24','女','PhotoShop','1234567'),(5,6,'陶月敏','1990-08-06','男','矢量图','1234567'),(6,7,'刘海','2013-11-19','女','J2SE','1234567'),(7,37,'钱五','1987-11-11','男','PhotoShop','1234567'),(8,40,'田静','2013-10-29','女','PHP','1234576');
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-08 11:18:16
