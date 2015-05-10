-- phpMyAdmin SQL Dump
-- version 3.2.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Aug 19, 2014 at 02:58 PM
-- Server version: 5.1.41
-- PHP Version: 5.3.1

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `urindo`
--

-- --------------------------------------------------------

--
-- Table structure for table `khs`
--

CREATE TABLE IF NOT EXISTS `khs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `npm` varchar(100) NOT NULL,
  `kodemk` varchar(100) NOT NULL,
  `smt` int(11) NOT NULL,
  `nilai` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=296 ;

--
-- Dumping data for table `khs`
--

INSERT INTO `khs` (`id`, `npm`, `kodemk`, `smt`, `nilai`) VALUES
(278, '2', 'S1.02.15.3.2.T', 2, 0),
(277, '2', 'S1.02.14.3.2.T', 2, 0),
(276, '2', 'S1.02.13.3.2.T', 2, 0),
(275, '2', 'S1.02.12.2.2.T', 2, 0),
(274, '2', 'S1.02.11.2.2.T', 2, 0),
(273, '2', 'UR.GA.05.2.2.T', 2, 0),
(272, '2', 'UR.GA.04.2.2.T', 2, 0),
(271, '2', 'S1.02.10.3.1.T', 1, 0),
(270, '2', 'S1.02.09.2.1.T', 1, 4),
(269, '2', 'S1.02.08.3.1.T	', 1, 4),
(268, '2', 'S1.02.07.3.1.T', 1, 4),
(267, '2', 'S1.02.06.3.1.T', 1, 4),
(266, '2', 'UR.GA.03.2.1.T', 1, 4),
(265, '2', 'UR.GA.02.2.1.T', 1, 4),
(264, '2', 'UR.GA.01.2.1.T', 1, 4),
(279, '2', 'S1.02.16.3.2.T', 2, 0),
(280, '2', 'S1.02.17.2.1.T', 3, 4),
(281, '2', 'S1.02.18.2.1.T', 3, 4),
(282, '2', 'S1.02.19.3.1.T', 3, 4),
(283, '2', 'S1.02.20.3.1.T', 3, 4),
(284, '2', 'S1.02.21.3.1.T', 3, 4),
(285, '2', 'S1.02.22.3.1.T', 3, 4),
(286, '2', 'S1.02.23.3.1.T', 3, 4),
(287, '2', 'S1.02.10.3.1.T', 3, 0),
(288, '201043500070', 'UR.GA.01.2.1.T', 1, 4),
(289, '201043500070', 'UR.GA.02.2.1.T', 1, 3),
(290, '201043500070', 'UR.GA.03.2.1.T', 1, 3),
(291, '201043500070', 'S1.02.06.3.1.T', 1, 3),
(292, '201043500070', 'S1.02.07.3.1.T', 1, 3),
(293, '201043500070', 'S1.02.08.3.1.T	', 1, 3),
(294, '201043500070', 'S1.02.09.2.1.T', 1, 3),
(295, '201043500070', 'S1.02.10.3.1.T', 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `krs`
--

CREATE TABLE IF NOT EXISTS `krs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `npm` varchar(100) NOT NULL,
  `kodemk` varchar(100) NOT NULL,
  `smt` int(11) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=355 ;

--
-- Dumping data for table `krs`
--

INSERT INTO `krs` (`id`, `npm`, `kodemk`, `smt`, `status`) VALUES
(346, '201043500070', 'S1.02.09.2.1.T', 1, 2),
(345, '201043500070', 'S1.02.08.3.1.T	', 1, 2),
(343, '201043500070', 'S1.02.06.3.1.T', 1, 2),
(341, '201043500070', 'UR.GA.02.2.1.T', 1, 2),
(344, '201043500070', 'S1.02.07.3.1.T', 1, 2),
(342, '201043500070', 'UR.GA.03.2.1.T', 1, 2),
(335, '2', 'S1.02.20.3.1.T', 3, 2),
(336, '2', 'S1.02.21.3.1.T', 3, 2),
(340, '201043500070', 'UR.GA.01.2.1.T', 1, 2),
(339, '2', 'S1.02.10.3.1.T', 3, 2),
(338, '2', 'S1.02.23.3.1.T', 3, 2),
(337, '2', 'S1.02.22.3.1.T', 3, 2),
(334, '2', 'S1.02.19.3.1.T', 3, 2),
(333, '2', 'S1.02.18.2.1.T', 3, 2),
(332, '2', 'S1.02.17.2.1.T', 3, 2),
(326, '2', 'S1.02.11.2.2.T', 2, 2),
(328, '2', 'S1.02.13.3.2.T', 2, 2),
(329, '2', 'S1.02.14.3.2.T', 2, 2),
(330, '2', 'S1.02.15.3.2.T', 2, 2),
(324, '2', 'UR.GA.04.2.2.T', 2, 2),
(331, '2', 'S1.02.16.3.2.T', 2, 2),
(327, '2', 'S1.02.12.2.2.T', 2, 2),
(325, '2', 'UR.GA.05.2.2.T', 2, 2),
(317, '2', 'UR.GA.02.2.1.T', 1, 2),
(318, '2', 'UR.GA.03.2.1.T', 1, 2),
(319, '2', 'S1.02.06.3.1.T', 1, 2),
(320, '2', 'S1.02.07.3.1.T', 1, 2),
(321, '2', 'S1.02.08.3.1.T	', 1, 2),
(322, '2', 'S1.02.09.2.1.T', 1, 2),
(323, '2', 'S1.02.10.3.1.T', 1, 2),
(316, '2', 'UR.GA.01.2.1.T', 1, 2),
(347, '201043500070', 'S1.02.10.3.1.T', 1, 2),
(348, '2', 'S1.02.24.2.2.T', 4, 1),
(349, '2', 'S1.02.25.2.2.T', 4, 1),
(350, '2', 'S1.02.26.3.2.T', 4, 1),
(351, '2', 'S1.02.27.3.2.T', 4, 1),
(352, '2', 'S1.02.28.3.2.T', 4, 1),
(353, '2', 'S1.02.29.23.2.T', 4, 1),
(354, '2', 'S1.02.30.3.2.T', 4, 1);

-- --------------------------------------------------------

--
-- Table structure for table `mahasiswa`
--

CREATE TABLE IF NOT EXISTS `mahasiswa` (
  `npm` bigint(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `smt` int(11) NOT NULL DEFAULT '1',
  `krs` int(11) NOT NULL DEFAULT '0',
  `totalkrs` int(11) NOT NULL DEFAULT '0',
  `akses` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`npm`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mahasiswa`
--

INSERT INTO `mahasiswa` (`npm`, `nama`, `password`, `smt`, `krs`, `totalkrs`, `akses`) VALUES
(3, 'admin', '3', 1, 0, 0, 1),
(2, 'Asal asalan doang', '2', 4, 2, 19, 0),
(201043500695, 'Taufik Widhiarto', '1', 1, 0, 0, 0),
(201043500070, 'Novi Yanti', '1', 2, 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `matkul`
--

CREATE TABLE IF NOT EXISTS `matkul` (
  `idm` int(11) NOT NULL AUTO_INCREMENT,
  `kode_mk` varchar(50) NOT NULL,
  `mk` varchar(100) NOT NULL,
  `sks` int(11) NOT NULL,
  `smt` int(11) NOT NULL,
  PRIMARY KEY (`idm`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=56 ;

--
-- Dumping data for table `matkul`
--

INSERT INTO `matkul` (`idm`, `kode_mk`, `mk`, `sks`, `smt`) VALUES
(1, 'UR.GA.01.2.1.T', 'Pendidikan Agama', 2, 1),
(2, 'UR.GA.02.2.1.T', 'Bahasa Indonesia', 2, 1),
(3, 'UR.GA.03.2.1.T', 'Pendidikan Pancasila & Kewarganegaraan', 3, 1),
(4, 'S1.02.06.3.1.T', 'Pengantar Bisnis', 3, 1),
(5, 'S1.02.07.3.1.T', 'Peng. Ekonomi Mikro', 3, 1),
(6, 'S1.02.08.3.1.T	', 'Peng. Akutansi 1', 3, 1),
(7, 'S1.02.09.2.1.T', 'Statistik 1', 2, 1),
(8, 'S1.02.10.3.1.T', 'Matematika Ekonomi', 3, 1),
(9, 'UR.GA.04.2.2.T', 'Ilmu Sosial Budaya Dasar', 2, 2),
(10, 'UR.GA.05.2.2.T', 'Ilmu Alamiah Dasar', 2, 2),
(11, 'S1.02.11.2.2.T', 'Bahasa Inggris I', 2, 2),
(12, 'S1.02.12.2.2.T', 'Teori & Aplikasi Komputer', 2, 2),
(13, 'S1.02.13.3.2.T', 'Pengantar Manajemen', 3, 2),
(14, 'S1.02.14.3.2.T', 'Pengantar Akutansi II', 3, 2),
(15, 'S1.02.15.3.2.T', 'Peng. Ekonomi Makro', 3, 2),
(16, 'S1.02.16.3.2.T', 'Statistik II', 3, 2),
(17, 'S1.02.17.2.1.T', 'Bahasa Inggris II', 2, 3),
(18, 'S1.02.18.2.1.T', 'Ekonomi Mikro', 2, 3),
(19, 'S1.02.19.3.1.T', 'Akuntansi Biaya', 3, 3),
(20, 'S1.02.20.3.1.T', 'Manajemen Keuangan I', 3, 3),
(21, 'S1.02.21.3.1.T', 'Manajemen SDM 1', 3, 3),
(22, 'S1.02.22.3.1.T', 'Manajemen Pemasaran I', 3, 3),
(23, 'S1.02.23.3.1.T', 'Sistem Informasi Manajemen', 3, 3),
(24, 'S1.02.24.2.2.T', 'Aspek Hukum Dlm Bisnis', 2, 4),
(25, 'S1.02.25.2.2.T', 'Ekonomi Makro', 2, 4),
(26, 'S1.02.26.3.2.T', 'Manajemen Keuangan II', 3, 4),
(27, 'S1.02.27.3.2.T', 'Manajemen SDM II', 3, 4),
(28, 'S1.02.28.3.2.T', 'Manajemen Pemasaran II', 3, 4),
(29, 'S1.02.29.23.2.T', 'Manajemen Operasional I', 3, 4),
(30, 'S1.02.30.3.2.T', 'Perpajakan', 3, 4),
(31, 'S1.02.31.2.1.T', 'Ekonomi Pembangunan', 2, 5),
(32, 'S1.02.32.3.1.T', 'Ekonomi Internasional', 3, 5),
(33, 'S1.02.33.3.1.T', 'Manajemen Operasional II', 3, 5),
(34, 'S1.02.34.3.1.T', 'Manajemen Koperasi', 3, 5),
(35, 'S1.02.35.3.1.T', 'Kewirausahaan', 3, 5),
(36, 'S1.02.36.3.1.T', 'Komunikasi Bisnis', 3, 5),
(37, 'S1.02.37.3.1.T', 'Ekonomi Manajerial', 3, 5),
(38, 'S1.02.38.3.2.T', 'Ekonomi Moneter', 3, 6),
(39, 'S1.02.39.3.2.T', 'Anggaran Perusahaan', 3, 6),
(40, 'S1.02.40.3.2.T', 'Metodologi Penelitian', 3, 6),
(41, 'S1.02.41.3.2.T', 'Studi Kelayakan Bisnis', 3, 6),
(42, 'S1.02.42.3.2.T', 'Manajemen Strategik', 3, 6),
(43, 'S1.02.43.3.2.T', 'Manajemen Investasi *', 3, 6),
(44, 'S1.02.44.3.2.T', 'Keuangan Internasional *', 3, 6),
(45, 'S1.02.45.2.1.T', 'Perilaku Organisasi', 2, 7),
(46, 'S1.02.46.3.1.T', 'Riset Operasi', 3, 7),
(47, 'S1.02.47.2.1.T', 'Manajemen Resiko', 2, 7),
(48, 'S1.02.48.3.1.T', 'BLKL', 3, 7),
(49, 'S1.02.49.3.1.T', 'Manajemen Akuntansi', 3, 7),
(50, 'S1.02.50.3.1.T', 'Manajemen Kinerja *', 3, 7),
(51, 'S1.02.51.3.1.T', 'Teori. Peng. Keputusan *', 3, 7),
(52, 'S1.02.52.3.2.T', 'Manajemen Audit', 3, 8),
(53, 'S1.02.53.3.2.T', 'Pemasaran Internasional *', 3, 8),
(54, 'S1.02.55.3.2.T', 'Teknik Proyeksi Bisnis *', 3, 8),
(55, 'S1.02.55.6.2.L', 'Skripsi', 6, 8);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
