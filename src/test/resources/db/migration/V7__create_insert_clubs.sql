CREATE TABLE IF NOT EXISTS `club` (
  `club_id` int(11) NOT NULL AUTO_INCREMENT,
  `club_name` varchar(32) NOT NULL,
  PRIMARY KEY (`club_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

INSERT INTO `club` (`club_id`, `club_name`) VALUES
(1, 'CULTURAL'),
(2, 'CSR'),
(3, 'CAFETERIA'),
(4, 'SPORTS');

CREATE TABLE IF NOT EXISTS `employee_clubs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `employee_id` int(11) NOT NULL,
  `club_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `employee_clubs` (`id`, `employee_id`, `club_id`) VALUES
(1, 3, 1),
(2, 4, 1),
(3, 7, 2),
(4, 10, 2),
(5, 11, 2),
(6, 12, 3),
(7, 20, 4),
(8, 21, 3),
(9, 24, 1),
(10, 26, 3),
(11, 27, 2),
(12, 28, 2),
(13, 30, 4),
(14, 31, 4),
(15, 38, 4),
(16, 40, 2),
(17, 41, 4),
(18, 46, 1),
(19, 48, 4),
(20, 50, 1),
(21, 51, 2),
(22, 54, 1),
(23, 60, 4),
(24, 61, 1),
(25, 63, 3),
(26, 70, 4),
(27, 71, 1),
(28, 73, 3),
(29, 75, 1),
(30, 76, 3),
(31, 80, 3),
(32, 81, 2),
(33, 85, 1),
(34, 86, 3),
(35, 90, 1),
(36, 91, 4),
(37, 92, 1),
(38, 95, 4),
(39, 96, 4);
