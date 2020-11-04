-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 04, 2020 at 10:10 PM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cinema`
--

-- --------------------------------------------------------

--
-- Table structure for table `actores`
--

CREATE TABLE `actores` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `actores`
--

INSERT INTO `actores` (`id`, `nombre`) VALUES
(3, 'Blake beach'),
(4, 'Brian Rey'),
(10, 'Tom Cruise'),
(11, 'Jake Smith'),
(12, 'Matt Sanchez');

-- --------------------------------------------------------

--
-- Table structure for table `generos`
--

CREATE TABLE `generos` (
  `id` int(11) NOT NULL,
  `tipo` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `generos`
--

INSERT INTO `generos` (`id`, `tipo`) VALUES
(1, 'Terror'),
(4, 'Accion'),
(5, 'Musical'),
(6, 'Documental'),
(13, 'Comedia');

-- --------------------------------------------------------

--
-- Table structure for table `películas`
--

CREATE TABLE `películas` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `generoID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `películas`
--

INSERT INTO `películas` (`id`, `nombre`, `generoID`) VALUES
(1, 'Mision Imposible', 1),
(3, 'Zoom', 1),
(4, 'Viernes 13', 1),
(6, 'En la oscuridad', 1),
(8, 'High School Musical', 5),
(9, 'Steve Jobs', 6),
(22, 'El impostor', 4);

-- --------------------------------------------------------

--
-- Table structure for table `películas_actores`
--

CREATE TABLE `películas_actores` (
  `id` int(11) NOT NULL,
  `películaID` int(11) DEFAULT NULL,
  `actorID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `películas_actores`
--

INSERT INTO `películas_actores` (`id`, `películaID`, `actorID`) VALUES
(3, 1, 3),
(4, 1, 4),
(7, 3, 3),
(9, 6, 4),
(13, 4, 3),
(32, 9, 3),
(34, 3, 4),
(40, 6, 11),
(41, 1, 11),
(42, 1, 10),
(43, 8, 3),
(44, 22, 12),
(45, 22, 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `actores`
--
ALTER TABLE `actores`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `generos`
--
ALTER TABLE `generos`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `películas`
--
ALTER TABLE `películas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `generoID` (`generoID`);

--
-- Indexes for table `películas_actores`
--
ALTER TABLE `películas_actores`
  ADD PRIMARY KEY (`id`),
  ADD KEY `películaID` (`películaID`),
  ADD KEY `actorID` (`actorID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `actores`
--
ALTER TABLE `actores`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `generos`
--
ALTER TABLE `generos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `películas`
--
ALTER TABLE `películas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `películas_actores`
--
ALTER TABLE `películas_actores`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `películas`
--
ALTER TABLE `películas`
  ADD CONSTRAINT `películas_ibfk_1` FOREIGN KEY (`generoID`) REFERENCES `generos` (`id`);

--
-- Constraints for table `películas_actores`
--
ALTER TABLE `películas_actores`
  ADD CONSTRAINT `películas_actores_ibfk_1` FOREIGN KEY (`películaID`) REFERENCES `películas` (`id`),
  ADD CONSTRAINT `películas_actores_ibfk_2` FOREIGN KEY (`actorID`) REFERENCES `actores` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
