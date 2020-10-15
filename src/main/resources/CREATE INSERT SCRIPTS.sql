-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.1.70-community-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             10.3.0.5771
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for muzic_store
CREATE DATABASE IF NOT EXISTS `tutor` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `muzic_store`;

-- Dumping structure for table muzic_store.authority
CREATE TABLE IF NOT EXISTS `authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `active` bit(1) DEFAULT b'1',
  `created_by` varchar(255) DEFAULT NULL,
  `created_dtm` datetime DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_dtm` datetime DEFAULT NULL,
  `valid_from` datetime DEFAULT NULL,
  `valid_to` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `name` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQUE_ROLENAME` (`name`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Dumping data for table muzic_store.authority: 0 rows
/*!40000 ALTER TABLE `authority` DISABLE KEYS */;
INSERT INTO `authority` (`id`, `active`, `created_by`, `created_dtm`, `updated_by`, `updated_dtm`, `valid_from`, `valid_to`, `version`, `name`) VALUES
	(1, b'1', NULL, CURRENT_TIMESTAMP, NULL, NULL, CURRENT_TIMESTAMP, '8888-12-31 23:59:59', 1, 'ROLE_SUPERADMIN'),
	(2, b'1', NULL, CURRENT_TIMESTAMP, NULL, NULL, CURRENT_TIMESTAMP, '8888-12-31 23:59:59', 1, 'ROLE_ADMIN'),
	(3, b'1', NULL, CURRENT_TIMESTAMP, NULL, NULL, CURRENT_TIMESTAMP, '8888-12-31 23:59:59', 1, 'ROLE_USER');
/*!40000 ALTER TABLE `authority` ENABLE KEYS */;

-- Dumping structure for table muzic_store.user_authority
CREATE TABLE IF NOT EXISTS `user_authority` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKiwgsyem2maacfmh11eknji0se` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Dumping data for table muzic_store.user_authority: 0 rows
/*!40000 ALTER TABLE `user_authority` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_authority` ENABLE KEYS */;

-- Dumping structure for table muzic_store.user_profile
CREATE TABLE IF NOT EXISTS `user_profile` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `active` bit(1) DEFAULT b'1',
  `created_by` varchar(255) DEFAULT NULL,
  `created_dtm` datetime DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_dtm` datetime DEFAULT NULL,
  `valid_from` datetime DEFAULT NULL,
  `valid_to` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_nm` varchar(255) DEFAULT NULL,
  `last_nm` varchar(255) DEFAULT NULL,
  `mobile` varchar(10) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `prfl_pic` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQUE_USERNAME` (`username`),
  UNIQUE KEY `UNIQUE_EMAIL` (`email`),
  UNIQUE KEY `UNIQUE_MOBILE` (`mobile`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Dumping data for table muzic_store.user_profile: 0 rows
/*!40000 ALTER TABLE `user_profile` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_profile` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
