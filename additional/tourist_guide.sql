-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 11, 2024 at 06:14 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tourist_guide`
--

-- --------------------------------------------------------

--
-- Table structure for table `activity`
--

CREATE TABLE `activity` (
  `activity_id` int(11) NOT NULL,
  `tag` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `activity`
--

INSERT INTO `activity` (`activity_id`, `tag`) VALUES
(1, 'Sailing'),
(2, 'Skiing'),
(3, 'Cycling'),
(4, 'Skydiving'),
(5, 'Diving');

-- --------------------------------------------------------

--
-- Table structure for table `article`
--

CREATE TABLE `article` (
  `article_id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `text` text NOT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `visit_count` int(11) NOT NULL DEFAULT 0,
  `autor_id` int(11) NOT NULL,
  `destination_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `article`
--

INSERT INTO `article` (`article_id`, `title`, `text`, `created_at`, `visit_count`, `autor_id`, `destination_id`) VALUES
(9, 'A Gaudí Wonderland', 'Barcelona, a vibrant city on the Mediterranean coast, is famed for its stunning architecture by Antoni Gaudí, such as the Sagrada Familia and Park Güell. Visitors can stroll down La Rambla, enjoy tapas at local eateries, and relax on the city\'s beautiful beaches. The city\'s rich history and lively culture make it a must-visit destination.', '2024-06-10 21:59:38', 2, 13, 16),
(10, 'The Pearl of the Adriatic', 'Dubrovnik, known as the \"Pearl of the Adriatic,\" boasts stunning medieval architecture and crystal-clear waters. Visitors can walk along the ancient city walls, explore the historic Old Town, and enjoy the serene beaches. The city\'s captivating history and scenic beauty make it a top travel spot.', '2024-06-10 21:59:38', 109, 14, 17),
(11, 'The City of Canals', 'Venice, the city of canals, offers a unique and enchanting experience with its gondola rides and historic architecture. Tourists can visit iconic landmarks like St. Mark\'s Basilica and the Doge\'s Palace. The romantic ambiance and rich cultural heritage draw millions of visitors each year.', '2024-06-10 21:59:38', 0, 15, 18),
(13, 'Island of Sunsets', 'Santorini, with its iconic white-washed buildings and stunning sunsets, is a picturesque island in the Aegean Sea. Visitors can explore the ancient ruins of Akrotiri, relax on unique volcanic beaches, and enjoy local wines. The island\'s breathtaking views and rich history attract travelers from around the globe.', '2024-06-10 21:59:38', 1, 16, 20),
(14, 'Down Under Delights', 'Sydney offers a blend of natural beauty and vibrant urban life, featuring landmarks like the Sydney Opera House and Harbour Bridge. Tourists can relax on famous beaches like Bondi and Manly or explore the nearby Blue Mountains. The city\'s diverse attractions and sunny climate make it a popular destination.', '2024-06-10 21:59:38', 0, 13, 21),
(15, 'Scenic Beauty', 'Cape Town is a dynamic city with stunning natural landscapes, including Table Mountain and pristine beaches. Visitors can explore the vibrant V&A Waterfront, tour historic Robben Island, and enjoy local wines in nearby vineyards. The city\'s rich culture and scenic beauty offer a unique travel experience.', '2024-06-10 21:59:38', 0, 16, 22),
(16, 'Historical Charm', 'Dubrovnik offers a unique blend of history and seaside charm, with its well-preserved medieval walls and stunning Adriatic views. Visitors can take a cable car ride for panoramic views, explore ancient fortresses, and swim in crystal-clear waters. The city\'s historical significance and natural beauty make it a top destination.', '2024-06-10 21:59:38', 0, 15, 17),
(17, 'City of Seven Hills', 'Lisbon, the hilly capital of Portugal, is known for its historic neighborhoods, vibrant nightlife, and scenic waterfront. Visitors can explore the Alfama district, ride the iconic Tram 28, and enjoy views from São Jorge Castle. The city\'s mix of old-world charm and modern attractions captivates tourists.', '2024-06-10 21:59:38', 3, 12, 27),
(18, 'Beach and Culture Hub', 'Miami is known for its lively nightlife, beautiful beaches, and cultural diversity. Tourists can explore the Art Deco architecture in South Beach, visit Little Havana for a taste of Cuban culture, and enjoy watersports in Biscayne Bay. The city\'s energetic vibe and tropical climate attract millions of visitors.', '2024-06-10 21:59:38', 0, 13, 23),
(23, 'Timeless Romance', 'Venice captivates visitors with its romantic canals, historic palaces, and vibrant piazzas. Tourists can enjoy a gondola ride through the Grand Canal, visit the Rialto Market, and explore the art at the Peggy Guggenheim Collection. The city\'s unique charm and cultural richness make it a top destination for romantics and art lovers.', '2024-06-10 22:04:10', 0, 13, 18),
(24, 'Urban Adventure', 'Sydney is a bustling metropolis offering iconic landmarks like the Opera House and Harbour Bridge alongside beautiful beaches. Visitors can surf at Bondi Beach, take a ferry to Manly, and explore the diverse dining scene in the city. The blend of urban attractions and natural beauty makes Sydney a must-visit.', '2024-06-10 22:04:10', 16, 15, 21),
(25, 'Mediterranean Elegance', 'Nice, with its azure waters and historic Old Town, is a jewel on the French Riviera. Visitors can walk along the Promenade des Anglais, visit the Matisse Museum, and enjoy the vibrant Cours Saleya market. The city\'s blend of French elegance and Mediterranean charm makes it a favorite seaside escape.', '2024-06-10 22:04:10', 1, 16, 24),
(26, 'Carnival Capital', 'Rio de Janeiro is famous for its stunning beaches, vibrant festivals, and iconic landmarks like Christ the Redeemer. Visitors can sunbathe on Copacabana, take in the views from Sugarloaf Mountain, and experience the energy of the Rio Carnival. The city\'s lively spirit and beautiful scenery make it an unforgettable destination.', '2024-06-10 22:04:10', 30, 12, 25);

-- --------------------------------------------------------

--
-- Table structure for table `article_activity`
--

CREATE TABLE `article_activity` (
  `article_id` int(11) NOT NULL,
  `activity_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `article_activity`
--

INSERT INTO `article_activity` (`article_id`, `activity_id`) VALUES
(9, 3),
(10, 1),
(13, 1),
(13, 5),
(14, 3),
(14, 4),
(17, 4),
(18, 1),
(18, 5),
(24, 3),
(24, 4),
(25, 1),
(25, 3);

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE `comment` (
  `comment_id` int(11) NOT NULL,
  `author_name` varchar(100) NOT NULL,
  `text` text NOT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `article_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`comment_id`, `author_name`, `text`, `created_at`, `article_id`) VALUES
(17, 'Danilo Joncic', 'I would really like to go to Rio', '2024-06-10 23:24:44', 26),
(18, 'Pera Prle', 'Me aswell', '2024-06-10 23:29:51', 26);

-- --------------------------------------------------------

--
-- Table structure for table `destination`
--

CREATE TABLE `destination` (
  `destination_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `destination`
--

INSERT INTO `destination` (`destination_id`, `name`, `description`) VALUES
(15, 'Cairo', 'Egypt'),
(16, 'Barcelona', 'Spain'),
(17, 'Dubrovnik', 'Croatia'),
(18, 'Venice', 'Italy'),
(19, 'Cancun', 'Mexico'),
(20, 'Santorini', 'Greece'),
(21, 'Sydney', 'Australia'),
(22, 'Cape Town', 'South Africa'),
(23, 'Miami', 'USA'),
(24, 'Nice', 'France'),
(25, 'Rio De Janeiro', 'Brazil'),
(26, 'Bali', 'Indonesia'),
(27, 'Lisabon', 'Portugal');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `tip` enum('editor','admin') NOT NULL,
  `status` enum('active','blocked') NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `email`, `firstname`, `lastname`, `tip`, `status`, `password`) VALUES
(11, 'admin@admin.com', 'Admin', 'Admin', 'admin', 'active', 'jGl25bVBBBW96Qi9Te4V37Fnqchz/Eu4qB9vKrRIqRg='),
(12, 'writer@writer.com', 'General', 'Melchett', 'editor', 'active', 'uTAGd0y91LKZOJoDrD2Iw6drRg1Th5W8EnGAEakJ+6U='),
(13, 'edmund@tv.com', 'Edmund', 'Blackadder', 'editor', 'active', 'o7U1odYfNOK+s8zoUnsByqGXGv6HvXz3GLy3rVdc124='),
(14, 'baldrick@tv.com', 'Baldrick', 'Sodoff', 'editor', 'active', 'A44sc5RGzrKxrkVrFVFaBpw2upg/qabRYEmasIQGRtg='),
(15, 'house@md.com', 'George', 'Barleigh', 'editor', 'active', 'ZiJbcoT6TG5swBw350Vc4b0vvARpjvcGw/MK/o1bPRQ='),
(16, 'captain@orders.com', 'Captain', 'Darling', 'editor', 'active', 'iVOfB4v//m3Yzixbk1cgKSgGkc5OmXWQKOUOmeKetZE='),
(20, 'noviMejl@zmail.com', 'Daca', 'Jončić', 'editor', 'active', 'Yb5VqOL2tOFyM4vd8YTW2+4pyYhT4KBIXs7n8nua8LQ=');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `activity`
--
ALTER TABLE `activity`
  ADD PRIMARY KEY (`activity_id`);

--
-- Indexes for table `article`
--
ALTER TABLE `article`
  ADD PRIMARY KEY (`article_id`),
  ADD KEY `destination_id` (`destination_id`),
  ADD KEY `article_ibfk_1` (`autor_id`);

--
-- Indexes for table `article_activity`
--
ALTER TABLE `article_activity`
  ADD PRIMARY KEY (`article_id`,`activity_id`),
  ADD KEY `activity_id` (`activity_id`);

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`comment_id`),
  ADD KEY `article_id` (`article_id`);

--
-- Indexes for table `destination`
--
ALTER TABLE `destination`
  ADD PRIMARY KEY (`destination_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `activity`
--
ALTER TABLE `activity`
  MODIFY `activity_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `article`
--
ALTER TABLE `article`
  MODIFY `article_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
  MODIFY `comment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `destination`
--
ALTER TABLE `destination`
  MODIFY `destination_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `article`
--
ALTER TABLE `article`
  ADD CONSTRAINT `article_ibfk_1` FOREIGN KEY (`autor_id`) REFERENCES `user` (`user_id`) ON UPDATE NO ACTION,
  ADD CONSTRAINT `article_ibfk_2` FOREIGN KEY (`destination_id`) REFERENCES `destination` (`destination_id`);

--
-- Constraints for table `article_activity`
--
ALTER TABLE `article_activity`
  ADD CONSTRAINT `article_activity_ibfk_1` FOREIGN KEY (`article_id`) REFERENCES `article` (`article_id`),
  ADD CONSTRAINT `article_activity_ibfk_2` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`activity_id`);

--
-- Constraints for table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`article_id`) REFERENCES `article` (`article_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
