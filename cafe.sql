-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 14, 2021 at 10:27 AM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cafe`
--

-- --------------------------------------------------------

--
-- Table structure for table `computer`
--

CREATE TABLE `computer` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `comNpNo` varchar(100) NOT NULL,
  `amount` varchar(100) NOT NULL,
  `date` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `computer`
--

INSERT INTO `computer` (`id`, `name`, `comNpNo`, `amount`, `date`) VALUES
(4, 'lab5', '234', '434', '2021-07-08');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `prodName` varchar(100) NOT NULL,
  `cartegory` varchar(100) NOT NULL,
  `date` varchar(100) NOT NULL,
  `amount` varchar(100) NOT NULL,
  `productNo` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `prodName`, `cartegory`, `date`, `amount`, `productNo`) VALUES
(2, 'Printer', 'Electronics', '2021-07-09', '4543', 'tyrtr45');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `phone` varchar(100) NOT NULL,
  `time` varchar(100) NOT NULL,
  `amount` varchar(100) NOT NULL,
  `date` varchar(100) NOT NULL,
  `compNo` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `phone`, `time`, `amount`, `date`, `compNo`) VALUES
(2, 'dsfsdf', '434', '45 min', '344', '2021-07-02', '3423'),
(3, 'ewqeqw', '21323', '1 hour', '5000', '2021-07-09', '23'),
(4, 'dfdf', 'sdfds', '45 min', '30000', '2021-07-16', 'dfds'),
(5, 'rttret', 'ret', '15 min', '30000', '2021-07-01', 'ret');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `computer`
--
ALTER TABLE `computer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `computer`
--
ALTER TABLE `computer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
