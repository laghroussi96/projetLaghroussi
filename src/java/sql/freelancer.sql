-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Lun 30 Avril 2018 à 00:45
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `freelancer`
--

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

CREATE TABLE IF NOT EXISTS `admin` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  `PRENOM` varchar(255) DEFAULT NULL,
  `PAYS_ID` bigint(20) DEFAULT NULL,
  `USER_LOGIN` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_ADMIN_PAYS_ID` (`PAYS_ID`),
  KEY `FK_ADMIN_USER_LOGIN` (`USER_LOGIN`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `admin`
--

INSERT INTO `admin` (`ID`, `NOM`, `PRENOM`, `PAYS_ID`, `USER_LOGIN`) VALUES
(1, 'aa', 'aa', 1, NULL),
(51, 'cc', 'cc', 4, NULL),
(151, 'qq', 'qq', 3, 'qq'),
(152, 'rr', 'rr', 3, 'qq'),
(153, 'ss', 'ss', 2, 'qq'),
(201, 'ww', 'ww', 4, 'ww');

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

CREATE TABLE IF NOT EXISTS `categorie` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `categorie`
--

INSERT INTO `categorie` (`ID`, `NOM`) VALUES
(7, 'Application Descktop'),
(9, 'Application Mobile'),
(10, 'Site statique'),
(11, 'Site dynamique');

-- --------------------------------------------------------

--
-- Structure de la table `commentaire`
--

CREATE TABLE IF NOT EXISTS `commentaire` (
  `ID` bigint(20) NOT NULL,
  `TEXT` varchar(255) DEFAULT NULL,
  `FREELANCE_ID` bigint(20) DEFAULT NULL,
  `MISSION_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_COMMENTAIRE_FREELANCE_ID` (`FREELANCE_ID`),
  KEY `FK_COMMENTAIRE_MISSION_ID` (`MISSION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `compte`
--

CREATE TABLE IF NOT EXISTS `compte` (
  `ID` bigint(20) NOT NULL,
  `SOLDE` double DEFAULT NULL,
  `USER_LOGIN` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_COMPTE_USER_LOGIN` (`USER_LOGIN`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `device`
--

CREATE TABLE IF NOT EXISTS `device` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `BROWSER` varchar(255) DEFAULT NULL,
  `DEVICECATEGORIE` varchar(255) DEFAULT NULL,
  `OPERATINGSYSTEM` varchar(255) DEFAULT NULL,
  `USER_LOGIN` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_DEVICE_USER_LOGIN` (`USER_LOGIN`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `devise`
--

CREATE TABLE IF NOT EXISTS `devise` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `devise`
--

INSERT INTO `devise` (`ID`, `NOM`) VALUES
(12, 'Dirham\r\n'),
(13, 'Dollar'),
(14, 'Euro');

-- --------------------------------------------------------

--
-- Structure de la table `diplome`
--

CREATE TABLE IF NOT EXISTS `diplome` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `diplome`
--

INSERT INTO `diplome` (`ID`, `NOM`) VALUES
(15, 'Licence'),
(16, 'Master'),
(17, 'Bac+2');

-- --------------------------------------------------------

--
-- Structure de la table `freelance`
--

CREATE TABLE IF NOT EXISTS `freelance` (
  `ID` bigint(20) NOT NULL,
  `DATEINSCRIPTION` date DEFAULT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  `PRENOM` varchar(255) DEFAULT NULL,
  `TARIF` double DEFAULT NULL,
  `TEL` double DEFAULT NULL,
  `ADMIN_ID` bigint(20) DEFAULT NULL,
  `DEVISE_ID` bigint(20) DEFAULT NULL,
  `DIPLOME_ID` bigint(20) DEFAULT NULL,
  `PAYS_ID` bigint(20) DEFAULT NULL,
  `USER_LOGIN` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_FREELANCE_DEVISE_ID` (`DEVISE_ID`),
  KEY `FK_FREELANCE_DIPLOME_ID` (`DIPLOME_ID`),
  KEY `FK_FREELANCE_ADMIN_ID` (`ADMIN_ID`),
  KEY `FK_FREELANCE_USER_LOGIN` (`USER_LOGIN`),
  KEY `FK_FREELANCE_PAYS_ID` (`PAYS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `freelance`
--

INSERT INTO `freelance` (`ID`, `DATEINSCRIPTION`, `NOM`, `PRENOM`, `TARIF`, `TEL`, `ADMIN_ID`, `DEVISE_ID`, `DIPLOME_ID`, `PAYS_ID`, `USER_LOGIN`) VALUES
(101, '2018-01-16', 'laghrousi', 'fatima', 200, 0.666754378, 1, 12, 17, 4, NULL),
(102, '2018-04-16', 'aderrab', 'samira', 30, 0.643546756, 1, 14, 15, 5, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `image`
--

CREATE TABLE IF NOT EXISTS `image` (
  `ID` bigint(20) NOT NULL,
  `TYPE` varchar(255) DEFAULT NULL,
  `CHEMIN` varchar(255) DEFAULT NULL,
  `MISSION` tinyint(1) DEFAULT '0',
  `USER` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `langue`
--

CREATE TABLE IF NOT EXISTS `langue` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `langue`
--

INSERT INTO `langue` (`ID`, `NOM`) VALUES
(18, 'Français'),
(19, 'Anglais');

-- --------------------------------------------------------

--
-- Structure de la table `langueskill`
--

CREATE TABLE IF NOT EXISTS `langueskill` (
  `ID` bigint(20) NOT NULL,
  `NIVEAU` varchar(255) DEFAULT NULL,
  `FREELANCE_ID` bigint(20) DEFAULT NULL,
  `LANGUE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_LANGUESKILL_LANGUE_ID` (`LANGUE_ID`),
  KEY `FK_LANGUESKILL_FREELANCE_ID` (`FREELANCE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `langueskill`
--

INSERT INTO `langueskill` (`ID`, `NIVEAU`, `FREELANCE_ID`, `LANGUE_ID`) VALUES
(21, 'B1', NULL, 18),
(22, 'B2', NULL, 18),
(23, 'C2', NULL, 18),
(24, 'C1', NULL, 18),
(25, 'B1', NULL, 19),
(26, 'B2', NULL, 19),
(27, 'C1', NULL, 19),
(28, 'C1', NULL, 19);

-- --------------------------------------------------------

--
-- Structure de la table `mission`
--

CREATE TABLE IF NOT EXISTS `mission` (
  `ID` bigint(20) NOT NULL,
  `AVANCEMENT` varchar(255) DEFAULT NULL,
  `COMMENTAIRE` varchar(255) DEFAULT NULL,
  `DATEACCEPTATION` date DEFAULT NULL,
  `DATELIMITE` date DEFAULT NULL,
  `DATEPUBLICATION` date DEFAULT NULL,
  `ISACCEPTED` tinyint(1) DEFAULT '0',
  `MAXBUDGET` double DEFAULT NULL,
  `MINBUDGET` double DEFAULT NULL,
  `TITRE` varchar(255) DEFAULT NULL,
  `ADMIN_ID` bigint(20) DEFAULT NULL,
  `CATEGORIE_ID` bigint(20) DEFAULT NULL,
  `DEVISE_ID` bigint(20) DEFAULT NULL,
  `DIPLOME_ID` bigint(20) DEFAULT NULL,
  `FREELANCE_ID` bigint(20) DEFAULT NULL,
  `LANGUE_ID` bigint(20) DEFAULT NULL,
  `RECRUTEUR_ID` bigint(20) DEFAULT NULL,
  `IMAGE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_MISSION_IMAGE_ID` (`IMAGE_ID`),
  KEY `FK_MISSION_CATEGORIE_ID` (`CATEGORIE_ID`),
  KEY `FK_MISSION_RECRUTEUR_ID` (`RECRUTEUR_ID`),
  KEY `FK_MISSION_DEVISE_ID` (`DEVISE_ID`),
  KEY `FK_MISSION_FREELANCE_ID` (`FREELANCE_ID`),
  KEY `FK_MISSION_DIPLOME_ID` (`DIPLOME_ID`),
  KEY `FK_MISSION_ADMIN_ID` (`ADMIN_ID`),
  KEY `FK_MISSION_LANGUE_ID` (`LANGUE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `mission`
--

INSERT INTO `mission` (`ID`, `AVANCEMENT`, `COMMENTAIRE`, `DATEACCEPTATION`, `DATELIMITE`, `DATEPUBLICATION`, `ISACCEPTED`, `MAXBUDGET`, `MINBUDGET`, `TITRE`, `ADMIN_ID`, `CATEGORIE_ID`, `DEVISE_ID`, `DIPLOME_ID`, `FREELANCE_ID`, `LANGUE_ID`, `RECRUTEUR_ID`, `IMAGE_ID`) VALUES
(37, 'en cour', '', '2018-03-29', '2018-04-29', '2018-04-29', 1, 6000, NULL, 'projet mobile', NULL, 9, 12, 16, 101, 18, 1, NULL),
(38, 'aboutis', '', '2018-04-29', '2018-03-29', '2018-04-29', 1, 700, NULL, 'projet java', NULL, 7, 13, 15, 101, 19, 1, NULL),
(39, 'non aboutis', '', '2018-04-27', '2018-02-27', '2018-04-27', 1, 700, NULL, 'site web', NULL, 11, 14, 17, 102, 18, 2, NULL),
(89, 'en cour', NULL, '2018-03-13', '2018-03-13', '2018-03-13', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(100, 'en cour', NULL, '2018-04-18', '2018-04-18', '2018-04-18', 1, 4000, NULL, 'site web dynamique', 51, 11, 12, 16, 102, 18, 1, NULL),
(101, 'aboutis', NULL, '2018-02-09', '2018-05-17', '2018-04-09', 2, 900, NULL, 'jeu pour enfant', 1, 7, 14, 15, 102, 19, 2, NULL),
(102, 'non aboutis', NULL, '2018-03-14', '2018-06-28', '2018-06-14', 1, 10000, NULL, 'app de gestion', 1, 9, 12, 16, 102, 18, 1, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `notification`
--

CREATE TABLE IF NOT EXISTS `notification` (
  `ID` bigint(20) NOT NULL,
  `DATENOTIFICATION` date DEFAULT NULL,
  `DATEVUE` date DEFAULT NULL,
  `TEXT` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `notificationcategorie`
--

CREATE TABLE IF NOT EXISTS `notificationcategorie` (
  `ID` bigint(20) NOT NULL,
  `TYPE` varchar(255) DEFAULT NULL,
  `ADMIN_ID` bigint(20) DEFAULT NULL,
  `FREELANCE_ID` bigint(20) DEFAULT NULL,
  `NOTIFICATION_ID` bigint(20) DEFAULT NULL,
  `RECRUTEUR_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_NOTIFICATIONCATEGORIE_RECRUTEUR_ID` (`RECRUTEUR_ID`),
  KEY `FK_NOTIFICATIONCATEGORIE_NOTIFICATION_ID` (`NOTIFICATION_ID`),
  KEY `FK_NOTIFICATIONCATEGORIE_ADMIN_ID` (`ADMIN_ID`),
  KEY `FK_NOTIFICATIONCATEGORIE_FREELANCE_ID` (`FREELANCE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `operation`
--

CREATE TABLE IF NOT EXISTS `operation` (
  `ID` bigint(20) NOT NULL,
  `DATEOPERATION` date DEFAULT NULL,
  `MONTANT` double DEFAULT NULL,
  `ADMIN_ID` bigint(20) DEFAULT NULL,
  `COMPTE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_OPERATION_ADMIN_ID` (`ADMIN_ID`),
  KEY `FK_OPERATION_COMPTE_ID` (`COMPTE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `paiement`
--

CREATE TABLE IF NOT EXISTS `paiement` (
  `ID` bigint(20) NOT NULL,
  `MONTANT` double DEFAULT NULL,
  `ADMIN_ID` bigint(20) DEFAULT NULL,
  `COMPTE_ID` bigint(20) DEFAULT NULL,
  `FREELANCE_ID` bigint(20) DEFAULT NULL,
  `RECRUTEUR_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_PAIEMENT_FREELANCE_ID` (`FREELANCE_ID`),
  KEY `FK_PAIEMENT_COMPTE_ID` (`COMPTE_ID`),
  KEY `FK_PAIEMENT_RECRUTEUR_ID` (`RECRUTEUR_ID`),
  KEY `FK_PAIEMENT_ADMIN_ID` (`ADMIN_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `pays`
--

CREATE TABLE IF NOT EXISTS `pays` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `pays`
--

INSERT INTO `pays` (`ID`, `NOM`) VALUES
(1, 'Maroc'),
(2, 'France'),
(3, 'Algérie'),
(4, 'Espagne'),
(5, 'Belgique'),
(6, 'Suisse');

-- --------------------------------------------------------

--
-- Structure de la table `recruteur`
--

CREATE TABLE IF NOT EXISTS `recruteur` (
  `ID` bigint(20) NOT NULL,
  `DATEINSCRIPTION` date DEFAULT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  `PRENOM` varchar(255) DEFAULT NULL,
  `TEL` double DEFAULT NULL,
  `ADMIN_ID` bigint(20) DEFAULT NULL,
  `PAYS_ID` bigint(20) DEFAULT NULL,
  `USER_LOGIN` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_RECRUTEUR_PAYS_ID` (`PAYS_ID`),
  KEY `FK_RECRUTEUR_ADMIN_ID` (`ADMIN_ID`),
  KEY `FK_RECRUTEUR_USER_LOGIN` (`USER_LOGIN`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `recruteur`
--

INSERT INTO `recruteur` (`ID`, `DATEINSCRIPTION`, `NOM`, `PRENOM`, `TEL`, `ADMIN_ID`, `PAYS_ID`, `USER_LOGIN`) VALUES
(1, '2018-07-12', 'hafid', 'fatima', 0.0627224777, 1, 2, NULL),
(2, '2018-02-21', 'bennajim', 'salma ', 0.645675432, 1, 1, NULL),
(7798, '2018-04-10', 'hanani', 'mehdi', 0.654348765, 1, 3, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `review`
--

CREATE TABLE IF NOT EXISTS `review` (
  `ID` bigint(20) NOT NULL,
  `COMMENTAIRE` varchar(255) DEFAULT NULL,
  `SCORE` int(11) DEFAULT NULL,
  `FREELANCE_ID` bigint(20) DEFAULT NULL,
  `MISSION_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_REVIEW_MISSION_ID` (`MISSION_ID`),
  KEY `FK_REVIEW_FREELANCE_ID` (`FREELANCE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `sequence`
--

CREATE TABLE IF NOT EXISTS `sequence` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL,
  PRIMARY KEY (`SEQ_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `sequence`
--

INSERT INTO `sequence` (`SEQ_NAME`, `SEQ_COUNT`) VALUES
('SEQ_GEN', '250');

-- --------------------------------------------------------

--
-- Structure de la table `technologie`
--

CREATE TABLE IF NOT EXISTS `technologie` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  `CATEGORIE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_TECHNOLOGIE_CATEGORIE_ID` (`CATEGORIE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `technologie`
--

INSERT INTO `technologie` (`ID`, `NOM`, `CATEGORIE_ID`) VALUES
(28, 'JavaFx', 7),
(29, 'Swing', 7),
(30, 'Ionic', 9),
(31, 'Android', 9),
(32, 'React native', 9);

-- --------------------------------------------------------

--
-- Structure de la table `technologiemission`
--

CREATE TABLE IF NOT EXISTS `technologiemission` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  `MISSION_ID` bigint(20) DEFAULT NULL,
  `TECHNOLOGIE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_TECHNOLOGIEMISSION_TECHNOLOGIE_ID` (`TECHNOLOGIE_ID`),
  KEY `FK_TECHNOLOGIEMISSION_MISSION_ID` (`MISSION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `technologieskill`
--

CREATE TABLE IF NOT EXISTS `technologieskill` (
  `ID` bigint(20) NOT NULL,
  `NBRANNEEXPER` int(11) DEFAULT NULL,
  `NIVEAU` varchar(255) DEFAULT NULL,
  `FREELANCE_ID` bigint(20) DEFAULT NULL,
  `TECHNOLOGIE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_TECHNOLOGIESKILL_FREELANCE_ID` (`FREELANCE_ID`),
  KEY `FK_TECHNOLOGIESKILL_TECHNOLOGIE_ID` (`TECHNOLOGIE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `technologieskill`
--

INSERT INTO `technologieskill` (`ID`, `NBRANNEEXPER`, `NIVEAU`, `FREELANCE_ID`, `TECHNOLOGIE_ID`) VALUES
(35, NULL, 'debutant', NULL, NULL),
(36, NULL, 'amateur', NULL, NULL),
(37, NULL, 'expert', NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `LOGIN` varchar(255) NOT NULL,
  `BLOCKED` int(11) DEFAULT NULL,
  `MDPCHANGED` tinyint(1) DEFAULT '0',
  `NBRCNX` int(11) DEFAULT NULL,
  `PASSEWORD` varchar(255) DEFAULT NULL,
  `TYPE` int(11) DEFAULT NULL,
  `IMAGE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`LOGIN`),
  KEY `FK_USER_IMAGE_ID` (`IMAGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`LOGIN`, `BLOCKED`, `MDPCHANGED`, `NBRCNX`, `PASSEWORD`, `TYPE`, `IMAGE_ID`) VALUES
('qq', 0, 0, 0, 'qq', 0, NULL),
('ww', 0, 0, 0, 'ww', 0, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `user_device`
--

CREATE TABLE IF NOT EXISTS `user_device` (
  `User_LOGIN` varchar(255) NOT NULL,
  `devices_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`User_LOGIN`,`devices_ID`),
  KEY `FK_USER_DEVICE_devices_ID` (`devices_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `FK_ADMIN_USER_LOGIN` FOREIGN KEY (`USER_LOGIN`) REFERENCES `user` (`LOGIN`),
  ADD CONSTRAINT `FK_ADMIN_PAYS_ID` FOREIGN KEY (`PAYS_ID`) REFERENCES `pays` (`ID`);

--
-- Contraintes pour la table `commentaire`
--
ALTER TABLE `commentaire`
  ADD CONSTRAINT `FK_COMMENTAIRE_MISSION_ID` FOREIGN KEY (`MISSION_ID`) REFERENCES `mission` (`ID`),
  ADD CONSTRAINT `FK_COMMENTAIRE_FREELANCE_ID` FOREIGN KEY (`FREELANCE_ID`) REFERENCES `freelance` (`ID`);

--
-- Contraintes pour la table `compte`
--
ALTER TABLE `compte`
  ADD CONSTRAINT `FK_COMPTE_USER_LOGIN` FOREIGN KEY (`USER_LOGIN`) REFERENCES `user` (`LOGIN`);

--
-- Contraintes pour la table `device`
--
ALTER TABLE `device`
  ADD CONSTRAINT `FK_DEVICE_USER_LOGIN` FOREIGN KEY (`USER_LOGIN`) REFERENCES `user` (`LOGIN`);

--
-- Contraintes pour la table `freelance`
--
ALTER TABLE `freelance`
  ADD CONSTRAINT `FK_FREELANCE_PAYS_ID` FOREIGN KEY (`PAYS_ID`) REFERENCES `pays` (`ID`),
  ADD CONSTRAINT `FK_FREELANCE_ADMIN_ID` FOREIGN KEY (`ADMIN_ID`) REFERENCES `admin` (`ID`),
  ADD CONSTRAINT `FK_FREELANCE_DEVISE_ID` FOREIGN KEY (`DEVISE_ID`) REFERENCES `devise` (`ID`),
  ADD CONSTRAINT `FK_FREELANCE_DIPLOME_ID` FOREIGN KEY (`DIPLOME_ID`) REFERENCES `diplome` (`ID`),
  ADD CONSTRAINT `FK_FREELANCE_USER_LOGIN` FOREIGN KEY (`USER_LOGIN`) REFERENCES `user` (`LOGIN`);

--
-- Contraintes pour la table `langueskill`
--
ALTER TABLE `langueskill`
  ADD CONSTRAINT `FK_LANGUESKILL_FREELANCE_ID` FOREIGN KEY (`FREELANCE_ID`) REFERENCES `freelance` (`ID`),
  ADD CONSTRAINT `FK_LANGUESKILL_LANGUE_ID` FOREIGN KEY (`LANGUE_ID`) REFERENCES `langue` (`ID`);

--
-- Contraintes pour la table `mission`
--
ALTER TABLE `mission`
  ADD CONSTRAINT `FK_MISSION_LANGUE_ID` FOREIGN KEY (`LANGUE_ID`) REFERENCES `langue` (`ID`),
  ADD CONSTRAINT `FK_MISSION_ADMIN_ID` FOREIGN KEY (`ADMIN_ID`) REFERENCES `admin` (`ID`),
  ADD CONSTRAINT `FK_MISSION_CATEGORIE_ID` FOREIGN KEY (`CATEGORIE_ID`) REFERENCES `categorie` (`ID`),
  ADD CONSTRAINT `FK_MISSION_DEVISE_ID` FOREIGN KEY (`DEVISE_ID`) REFERENCES `devise` (`ID`),
  ADD CONSTRAINT `FK_MISSION_DIPLOME_ID` FOREIGN KEY (`DIPLOME_ID`) REFERENCES `diplome` (`ID`),
  ADD CONSTRAINT `FK_MISSION_FREELANCE_ID` FOREIGN KEY (`FREELANCE_ID`) REFERENCES `freelance` (`ID`),
  ADD CONSTRAINT `FK_MISSION_IMAGE_ID` FOREIGN KEY (`IMAGE_ID`) REFERENCES `image` (`ID`),
  ADD CONSTRAINT `FK_MISSION_RECRUTEUR_ID` FOREIGN KEY (`RECRUTEUR_ID`) REFERENCES `recruteur` (`ID`);

--
-- Contraintes pour la table `notificationcategorie`
--
ALTER TABLE `notificationcategorie`
  ADD CONSTRAINT `FK_NOTIFICATIONCATEGORIE_FREELANCE_ID` FOREIGN KEY (`FREELANCE_ID`) REFERENCES `freelance` (`ID`),
  ADD CONSTRAINT `FK_NOTIFICATIONCATEGORIE_ADMIN_ID` FOREIGN KEY (`ADMIN_ID`) REFERENCES `admin` (`ID`),
  ADD CONSTRAINT `FK_NOTIFICATIONCATEGORIE_NOTIFICATION_ID` FOREIGN KEY (`NOTIFICATION_ID`) REFERENCES `notification` (`ID`),
  ADD CONSTRAINT `FK_NOTIFICATIONCATEGORIE_RECRUTEUR_ID` FOREIGN KEY (`RECRUTEUR_ID`) REFERENCES `recruteur` (`ID`);

--
-- Contraintes pour la table `operation`
--
ALTER TABLE `operation`
  ADD CONSTRAINT `FK_OPERATION_COMPTE_ID` FOREIGN KEY (`COMPTE_ID`) REFERENCES `compte` (`ID`),
  ADD CONSTRAINT `FK_OPERATION_ADMIN_ID` FOREIGN KEY (`ADMIN_ID`) REFERENCES `admin` (`ID`);

--
-- Contraintes pour la table `paiement`
--
ALTER TABLE `paiement`
  ADD CONSTRAINT `FK_PAIEMENT_ADMIN_ID` FOREIGN KEY (`ADMIN_ID`) REFERENCES `admin` (`ID`),
  ADD CONSTRAINT `FK_PAIEMENT_COMPTE_ID` FOREIGN KEY (`COMPTE_ID`) REFERENCES `compte` (`ID`),
  ADD CONSTRAINT `FK_PAIEMENT_FREELANCE_ID` FOREIGN KEY (`FREELANCE_ID`) REFERENCES `freelance` (`ID`),
  ADD CONSTRAINT `FK_PAIEMENT_RECRUTEUR_ID` FOREIGN KEY (`RECRUTEUR_ID`) REFERENCES `recruteur` (`ID`);

--
-- Contraintes pour la table `recruteur`
--
ALTER TABLE `recruteur`
  ADD CONSTRAINT `FK_RECRUTEUR_USER_LOGIN` FOREIGN KEY (`USER_LOGIN`) REFERENCES `user` (`LOGIN`),
  ADD CONSTRAINT `FK_RECRUTEUR_ADMIN_ID` FOREIGN KEY (`ADMIN_ID`) REFERENCES `admin` (`ID`),
  ADD CONSTRAINT `FK_RECRUTEUR_PAYS_ID` FOREIGN KEY (`PAYS_ID`) REFERENCES `pays` (`ID`);

--
-- Contraintes pour la table `review`
--
ALTER TABLE `review`
  ADD CONSTRAINT `FK_REVIEW_FREELANCE_ID` FOREIGN KEY (`FREELANCE_ID`) REFERENCES `freelance` (`ID`),
  ADD CONSTRAINT `FK_REVIEW_MISSION_ID` FOREIGN KEY (`MISSION_ID`) REFERENCES `mission` (`ID`);

--
-- Contraintes pour la table `technologie`
--
ALTER TABLE `technologie`
  ADD CONSTRAINT `FK_TECHNOLOGIE_CATEGORIE_ID` FOREIGN KEY (`CATEGORIE_ID`) REFERENCES `categorie` (`ID`);

--
-- Contraintes pour la table `technologiemission`
--
ALTER TABLE `technologiemission`
  ADD CONSTRAINT `FK_TECHNOLOGIEMISSION_MISSION_ID` FOREIGN KEY (`MISSION_ID`) REFERENCES `mission` (`ID`),
  ADD CONSTRAINT `FK_TECHNOLOGIEMISSION_TECHNOLOGIE_ID` FOREIGN KEY (`TECHNOLOGIE_ID`) REFERENCES `technologie` (`ID`);

--
-- Contraintes pour la table `technologieskill`
--
ALTER TABLE `technologieskill`
  ADD CONSTRAINT `FK_TECHNOLOGIESKILL_TECHNOLOGIE_ID` FOREIGN KEY (`TECHNOLOGIE_ID`) REFERENCES `technologie` (`ID`),
  ADD CONSTRAINT `FK_TECHNOLOGIESKILL_FREELANCE_ID` FOREIGN KEY (`FREELANCE_ID`) REFERENCES `freelance` (`ID`);

--
-- Contraintes pour la table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FK_USER_IMAGE_ID` FOREIGN KEY (`IMAGE_ID`) REFERENCES `image` (`ID`);

--
-- Contraintes pour la table `user_device`
--
ALTER TABLE `user_device`
  ADD CONSTRAINT `FK_USER_DEVICE_devices_ID` FOREIGN KEY (`devices_ID`) REFERENCES `device` (`ID`),
  ADD CONSTRAINT `FK_USER_DEVICE_User_LOGIN` FOREIGN KEY (`User_LOGIN`) REFERENCES `user` (`LOGIN`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
