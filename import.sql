-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Gegenereerd op: 05 dec 2018 om 11:51
-- Serverversie: 5.7.23
-- PHP-versie: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `travelmore`
--

--
-- Gegevens worden geëxporteerd voor tabel `betaalmethode`
--

INSERT INTO `betaalmethode` (`id`, `naam`) VALUES
(1, 'Visa/MasterCard'),
(2, 'Paypal'),
(3, 'Bancontact'),
(4, 'Dogecoin'),
(5, 'iDEAL');

--
-- Gegevens worden geëxporteerd voor tabel `land`
--

INSERT INTO `land` (`id`, `code`, `naam`) VALUES
(13, 'BEL', 'Belgie'),
(1, 'ABW', 'Aruba'),
(2, 'AFG', 'Afghanistan'),
(3, 'ALB', 'Albania'),
(4, 'BRA', 'Brazil'),
(5, 'CHE', 'Switzerland'),
(6, 'CHN', 'China'),
(7, 'DNK', 'Denmark'),
(8, 'ESP', 'Spain'),
(9, 'HKG', 'Hong Kong'),
(10, 'JPN', 'Japan'),
(11, 'USA', 'United States of America'),
(12, 'NLD', 'Netherlands');

-- Gegevens worden geëxporteerd voor tabel `transportmiddel`
--

INSERT INTO `transportmiddel` (`id`, `naam`) VALUES
(1, 'Trein'),
(2, 'Vliegtuig'),
(3, 'Boot'),
(4, 'Bus');

--
-- Gegevens worden geëxporteerd voor tabel `locatie`
--

INSERT INTO `locatie` (`id`, `naam`, `land_id`) VALUES
(1, 'Brussel', 13),
(2, 'Antwerpen', 13),
(3, 'Geel', 13),
(4, 'Oostende', 13),
(5, 'Amsterdam', 12),
(6, 'Utrecht', 12),
(7, 'California', 11),
(8, 'Florida', 11),
(9, 'Las Vegas', 11),
(10, 'New York', 11),
(11, 'Tokio', 10),
(12, 'Osaka', 10),
(13, 'Kioto', 10),
(14, 'Yokohama', 10),
(15, 'Madrid', 8),
(16, 'Barcelona', 8),
(17, 'Granada', 8),
(18, 'Kopenhagen', 7),
(19, 'Aarhus', 7);

--
-- Gegevens worden geëxporteerd voor tabel `reis`
--

INSERT INTO `reis` (`id`, `aankomstDatum`, `aantalPlaatsen`, `prijs`, `vertrekDatum`, `aankomstLocatie_id`, `transportmiddel_id`, `vertrekLocatie_id`) VALUES
(1, '2018-10-30 12:00:00', 10, 200, '2018-11-01 14:00:00', 1, 3, 2),
(2, '2019-01-24 05:09:00', 60, 20, '2019-01-23 00:00:00', 12, 1, 15),
(3, '2018-10-24 05:09:00', 50, 250, '2018-10-23 00:00:00', 3, 3, 1),
(4, '2018-11-14 05:09:00', 20, 250, '2018-11-13 00:00:00', 7, 1, 14);

--
--
-- Gegevens worden geëxporteerd voor tabel `boeking`
--



COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
