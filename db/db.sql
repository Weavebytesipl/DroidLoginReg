-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 18, 2016 at 07:35 PM
-- Server version: 5.5.44-0ubuntu0.14.04.1
-- PHP Version: 5.5.9-1ubuntu4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `LoginDemo`
--

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(64) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) NOT NULL,
  `password` varchar(64) NOT NULL,
  `email` varchar(128) NOT NULL,
  `dob` date NOT NULL,
  `gender` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=21 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `email`, `dob`, `gender`) VALUES
(4, 'shiv', 'cfcd208495d565ef66e7dff9f98764da', 'ss@gmail.com', '2016-05-19', 'male'),
(5, 'root', '202cb962ac59075b964b07152d234b70', '', '0000-00-00', ''),
(6, 'shiv', '202cb962ac59075b964b07152d234b70', 'shiv@gmail.com', '2016-05-26', 'male'),
(7, 'shiv', '202cb962ac59075b964b07152d234b70', '', '0000-00-00', ''),
(8, 'shiv', '202cb962ac59075b964b07152d234b70', '', '0000-00-00', ''),
(9, 'rootfhfg', '202cb962ac59075b964b07152d234b70', '', '0000-00-00', ''),
(10, 'rootfsfsd', '202cb962ac59075b964b07152d234b70', '', '0000-00-00', ''),
(11, 'root', '202cb962ac59075b964b07152d234b70', '', '0000-00-00', ''),
(12, 'cp', '202cb962ac59075b964b07152d234b70', 'cp@gmail.com', '2016-05-09', 'male'),
(13, 'niraj', 'cfcd208495d565ef66e7dff9f98764da', 'ni@gmail.com', '2016-05-28', 'male'),
(14, 'weave', '8d5e957f297893487bd98fa830fa6413', 'weave@gmail.com', '2016-05-27', 'male'),
(15, 'kk', 'cfcd208495d565ef66e7dff9f98764da', 'kk@gmail.com', '0000-00-00', 'female'),
(16, 'kelly', '140f6969d5213fd0ece03148e62e461e', 'kelly@gmail.com', '2016-05-04', 'female'),
(17, 'shyam', '202cb962ac59075b964b07152d234b70', 'ss@gmail.com', '2016-05-11', 'male'),
(18, 'ss', '3a07eb24aaf954a56ebf40221eeae2a1', 'ss@gmail.com', '2016-05-11', 'male'),
(19, 'ram', 'af93b8d2335640f8bc181ef71f3bd505', '', '0000-00-00', 'male'),
(20, 'ok', 'b73fdaa1fb7669da760b49600c45d9be', '', '0000-00-00', '');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
