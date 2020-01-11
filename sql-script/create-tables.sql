CREATE DATABASE  IF NOT EXISTS `pages`;
USE `pages`;

--
-- Create table `language`
--

DROP TABLE IF EXISTS `language`;

CREATE TABLE `language` (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `acronym` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;


--
-- Create table `page_type`
--

DROP TABLE IF EXISTS `page_type`;

CREATE TABLE `page_type` (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `metadata` varchar(45) DEFAULT NULL,
  `lang_id` int UNSIGNED,
  PRIMARY KEY (`id`),
  FOREIGN KEY (lang_id)
  REFERENCES language(id)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;


--
-- Create table `page`
--

DROP TABLE IF EXISTS `page`;

CREATE TABLE `page` (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `page_type_id` int UNSIGNED,
  `lang_id` int UNSIGNED,
  `level_id` int NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `theme` varchar(45) DEFAULT NULL,
  `to_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  FOREIGN KEY (lang_id)
  REFERENCES language(id),
  FOREIGN KEY (page_type_id)
  REFERENCES page_type(id)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

INSERT INTO language(name, acronym) values('English', 'en');

INSERT INTO page_type(name, metadata, lang_id) values('Content', 'html', 6);

INSERT INTO page(page_type_id, lang_id, level_id, name, theme) values(6, 6, 1, "Math content", "Math");
