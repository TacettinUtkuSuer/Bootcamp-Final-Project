-- Adminer 4.8.1 MySQL 8.0.28 dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `id` bigint NOT NULL,
  `country` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `district` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `full_address` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `advert_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5g9tb2oqq92qofurbrm2dmn3f` (`advert_id`),
  CONSTRAINT `FK5g9tb2oqq92qofurbrm2dmn3f` FOREIGN KEY (`advert_id`) REFERENCES `advert` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_turkish_ci;

INSERT INTO `address` (`id`, `country`, `district`, `full_address`, `advert_id`) VALUES
(4,	'ANKARA',	'Çankaya',	'Bahçeli 11/43',	3),
(6,	'ANKARA',	'Çankaya',	'Mustafa Kemal 16/8',	5),
(8,	'ANKARA',	'Çankaya',	'Çayyolu 14/23',	7),
(10,	'ANKARA',	'Yenimahalle',	'Çukurambar 4/67',	9),
(12,	'ANKARA',	'Çankaya',	'Mustafa Kemal 89/34',	11),
(17,	'ANKARA',	'Yenimahalle',	'Batıkent 10/3',	16),
(19,	'ANKARA',	'Çankaya',	'Yaşamkent 18/2',	18),
(24,	'ANKARA',	'Çankaya',	'Tunalı 5/34',	23),
(26,	'ANKARA',	'Yenimahelle',	'Çiğdem 16/8',	25),
(28,	'ANKARA',	'Çankaya',	'Esat 14/23',	27);

DROP TABLE IF EXISTS `advert`;
CREATE TABLE `advert` (
  `id` bigint NOT NULL,
  `advert_status` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `advert_type` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `definition` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `title` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `address_id` bigint DEFAULT NULL,
  `advert_product_package_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8l6ed9wjykm2komk6qlq0wlb1` (`address_id`),
  KEY `FKfjvl7f53bm5fx78mgcy4d9lvu` (`advert_product_package_id`),
  CONSTRAINT `FK8l6ed9wjykm2komk6qlq0wlb1` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`),
  CONSTRAINT `FKfjvl7f53bm5fx78mgcy4d9lvu` FOREIGN KEY (`advert_product_package_id`) REFERENCES `advert_product_package` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_turkish_ci;

INSERT INTO `advert` (`id`, `advert_status`, `advert_type`, `definition`, `title`, `address_id`, `advert_product_package_id`) VALUES
(3,	'ACTIVE',	'FORSALE',	'3+1 Kombili 5.Kat',	'Acil satılık sahibinden ev!!!',	4,	2),
(5,	'PASSIVE',	'FORRENT',	'4+1 Merkezi Isıtma Giriş Katı',	'Otobüs ve metro durağına yakın merkezi ısıtma kiralık ev.',	6,	2),
(7,	'ACTIVE',	'FORSALE',	'2+1 PayÖlçerli Teras',	'Şehrin en merkezi yerinde satılık ev.',	8,	2),
(9,	'ACTIVE',	'DAILYRENT',	'1+1',	'Residance\'ta günlük kiralık',	10,	2),
(11,	'PASSIVE',	'FORSALE',	'2+1 Merkezi Şömineli',	'Yenilenmiş, bakımlı, temiz ev',	12,	2),
(16,	'ACTIVE',	'FORSALE',	'5+1 Yerden Isıtma 4.Kat',	'Acil kiralık ev!!!',	17,	15),
(18,	'PASSIVE',	'FORRENT',	'2+1 Havuzlu Sitede',	'Ankara\'nın göbeğinde...',	19,	15),
(23,	'ACTIVE',	'FORSALE',	'3+1 Merkezi 4.Kat',	'Yeni ev alacağımdan acil!!!',	24,	22),
(25,	'ACTIVE',	'FORRENT',	'3+1 Merkezi Isıtma Kotta',	'Öğrenciye bekara ev.',	26,	22),
(27,	'ACTIVE',	'FORSALE',	'2+1 Pay Ölçerli Çatı Katı Şömineli',	'Sıfır ev',	28,	22);

DROP TABLE IF EXISTS `advert_product_package`;
CREATE TABLE `advert_product_package` (
  `id` bigint NOT NULL,
  `package_expiration_date` date DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrmn2y2eejs9bmgd5au54t3kbp` (`user_id`),
  CONSTRAINT `FKrmn2y2eejs9bmgd5au54t3kbp` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_turkish_ci;

INSERT INTO `advert_product_package` (`id`, `package_expiration_date`, `user_id`) VALUES
(2,	'2022-05-09',	1),
(15,	'2022-05-13',	14),
(22,	'2022-04-20',	21);

DROP TABLE IF EXISTS `credit_card`;
CREATE TABLE `credit_card` (
  `id` bigint NOT NULL,
  `cvvnumber` int NOT NULL,
  `credit_card_number` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `name_on_credit_card` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `short_format_expiration_year` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh4wi9724muee2kp2c4ku1yia2` (`user_id`),
  CONSTRAINT `FKh4wi9724muee2kp2c4ku1yia2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_turkish_ci;

INSERT INTO `credit_card` (`id`, `cvvnumber`, `credit_card_number`, `name_on_credit_card`, `short_format_expiration_year`, `user_id`) VALUES
(13,	123,	'1111 2222 3333 4567',	'Tacettin Utku Suer',	'25',	1),
(20,	234,	'1111 2222 3333 7693',	'Cem Özer',	'26',	14),
(29,	456,	'1111 2222 3333 3847',	'Berna Erker',	'24',	21);

DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_turkish_ci;

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(33);

DROP TABLE IF EXISTS `info_table`;
CREATE TABLE `info_table` (
  `id` bigint NOT NULL,
  `key_column` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `value_column` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_turkish_ci;

INSERT INTO `info_table` (`id`, `key_column`, `value_column`) VALUES
(30,	'Price',	'95.50');

DROP TABLE IF EXISTS `payment_list`;
CREATE TABLE `payment_list` (
  `id` bigint NOT NULL,
  `credit_card_number` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `name_on_credit_card` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_turkish_ci;

INSERT INTO `payment_list` (`id`, `credit_card_number`, `name_on_credit_card`, `price`) VALUES
(31,	'**** **** ****  3847',	'Berna Erker',	95.50),
(32,	'**** **** ****  4567',	'Tacettin Utku Suer',	95.50);

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint NOT NULL,
  `email` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `surname` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `advert_product_package_id` bigint DEFAULT NULL,
  `credit_card_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8spt6lm4s9u6lhb1nlv218ibv` (`advert_product_package_id`),
  KEY `FKgcjy88ysvwgqo3umk010mjuxv` (`credit_card_id`),
  CONSTRAINT `FK8spt6lm4s9u6lhb1nlv218ibv` FOREIGN KEY (`advert_product_package_id`) REFERENCES `advert_product_package` (`id`),
  CONSTRAINT `FKgcjy88ysvwgqo3umk010mjuxv` FOREIGN KEY (`credit_card_id`) REFERENCES `credit_card` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_turkish_ci;

INSERT INTO `user` (`id`, `email`, `name`, `password`, `phone`, `surname`, `advert_product_package_id`, `credit_card_id`) VALUES
(1,	'tacettinutkusuer@gmail.com',	'Tacettin Utku',	'1993',	'+90 555 444 33 22',	'Suer',	2,	13),
(14,	'cemozer@gmail.com',	'Cem',	'1991',	'+90 555 444 33 22',	'Özer',	15,	20),
(21,	'bernaerker@gmail.com',	'Berna',	'1997',	'+90 555 444 33 22',	'Erker',	22,	29);

-- 2022-03-22 20:39:03
