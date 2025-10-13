-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 13, 2025 at 11:01 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 7.4.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tolpawiring`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_car_model`
--

CREATE TABLE `tbl_car_model` (
  `car_id` int(11) NOT NULL,
  `car_model` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `date_uploaded` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_car_model`
--

INSERT INTO `tbl_car_model` (`car_id`, `car_model`, `status`, `date_uploaded`) VALUES
(1, 'Toyota Vios', 'AVAILABLE', '2025-10-12'),
(2, 'Honda City', 'AVAILABLE', '2025-10-13'),
(3, 'Toyota Wigo', 'AVAILABLE', '2025-10-13'),
(4, 'Mitsubishi Mirage', 'AVAILABLE', '2025-10-13'),
(5, 'Toyota Innova', 'AVAILABLE', '2025-10-13'),
(6, 'Mitsubishi Xpander', 'AVAILABLE', '2025-10-13'),
(7, 'Suzuki Ertiga', 'AVAILABLE', '2025-10-13'),
(8, 'Toyota Fortuner', 'AVAILABLE', '2025-10-13'),
(9, 'Mitsubishi Montero Sport', 'AVAILABLE', '2025-10-13'),
(10, 'Ford Everest', 'AVAILABLE', '2025-10-13'),
(11, 'Toyota Hilux', 'AVAILABLE', '2025-10-13');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_reservation`
--

CREATE TABLE `tbl_reservation` (
  `reservation_id` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  `flname` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `service_type` varchar(255) NOT NULL,
  `car_model` varchar(255) NOT NULL,
  `schedule` datetime DEFAULT NULL,
  `status` varchar(255) NOT NULL,
  `date_place` date NOT NULL,
  `date_updated` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_reservation`
--

INSERT INTO `tbl_reservation` (`reservation_id`, `id`, `flname`, `email`, `service_type`, `car_model`, `schedule`, `status`, `date_place`, `date_updated`) VALUES
(1, 7, 'Baral', 'baral.com', 'Change Oil', 'Toyota Vios', '2025-10-14 13:00:00', 'Cancelled', '2025-10-13', '2025-10-13 16:56:57'),
(2, 10, 'CJ UNARSE', 'kalbs@gmail.com', 'Change Oil', 'Toyota Vios', '2025-10-18 13:30:00', 'Confirmed', '2025-10-13', '2025-10-13 16:57:03'),
(3, 10, 'CJ UNARSE', 'kalbs@gmail.com', 'Change Oil', 'Ford Everest', '2025-10-31 16:30:00', 'Pending', '2025-10-13', '2025-10-13 16:54:36'),
(4, 7, 'Baral', 'baral.com', 'Change Oil', 'Toyota Wigo', '2025-10-20 13:30:00', 'Pending', '2025-10-13', '2025-10-13 16:55:21'),
(5, 7, 'Baral', 'baral.com', 'Tire Check', 'Toyota Wigo', '2025-10-14 12:30:00', 'Pending', '2025-10-13', '2025-10-13 16:55:38');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_service`
--

CREATE TABLE `tbl_service` (
  `service_id` int(11) NOT NULL,
  `service_name` varchar(255) NOT NULL,
  `service_description` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `date_uploaded` date DEFAULT curdate()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_service`
--

INSERT INTO `tbl_service` (`service_id`, `service_name`, `service_description`, `status`, `date_uploaded`) VALUES
(6, 'Change Oil', 'Changing Oil', 'Active', '2025-10-13'),
(7, 'Car Wash', 'Make your vehicle clean and shiny', 'Active', '2025-10-13'),
(8, 'Tire Check', 'Check you tire if there are some holes, damage, or wear out', 'Active', '2025-10-13');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_users`
--

CREATE TABLE `tbl_users` (
  `id` int(11) NOT NULL,
  `flname` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `role` varchar(20) DEFAULT 'user',
  `account_created` date NOT NULL DEFAULT curdate()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_users`
--

INSERT INTO `tbl_users` (`id`, `flname`, `email`, `username`, `password`, `role`, `account_created`) VALUES
(1, 'Emmanuel Malbas', '69.digong@gmail.com', 'shifter', '240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9', 'admin', '2025-10-09'),
(2, 'Kelly Domingo', '69.digong@gmail.com', 'toji', '240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9', 'admin', '2025-10-09'),
(3, 'John Doe', '69.digong@gmail.com', 'johndoe', 'e606e38b0d8c19b24cf0ee3808183162ea7cd63ff7912dbb22b5e803286b4446', 'user', '2025-10-09'),
(4, 'Michael Jordan', 'mj@gmail.com', 'michael', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'user', '2025-10-09'),
(7, 'Baral', 'baral.com', 'baral', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'user', '2025-10-09'),
(8, 'Admin', 'admin@gmail.com', 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'admin', '2025-10-11'),
(9, 'Karim Hamsar', 'kar@gmail.com', 'Karim', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'admin', '2025-10-12'),
(10, 'CJ UNARSE', 'kalbs@gmail.com', 'CJ', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'user', '2025-10-12');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_car_model`
--
ALTER TABLE `tbl_car_model`
  ADD PRIMARY KEY (`car_id`);

--
-- Indexes for table `tbl_reservation`
--
ALTER TABLE `tbl_reservation`
  ADD PRIMARY KEY (`reservation_id`),
  ADD KEY `fk_user_reservation` (`id`);

--
-- Indexes for table `tbl_service`
--
ALTER TABLE `tbl_service`
  ADD PRIMARY KEY (`service_id`);

--
-- Indexes for table `tbl_users`
--
ALTER TABLE `tbl_users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_car_model`
--
ALTER TABLE `tbl_car_model`
  MODIFY `car_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `tbl_reservation`
--
ALTER TABLE `tbl_reservation`
  MODIFY `reservation_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `tbl_service`
--
ALTER TABLE `tbl_service`
  MODIFY `service_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `tbl_users`
--
ALTER TABLE `tbl_users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tbl_reservation`
--
ALTER TABLE `tbl_reservation`
  ADD CONSTRAINT `fk_user_reservation` FOREIGN KEY (`id`) REFERENCES `tbl_users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
