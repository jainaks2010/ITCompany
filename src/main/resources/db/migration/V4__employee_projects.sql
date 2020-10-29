CREATE TABLE  `employee_project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `employee_id` int(11) NOT NULL,
  `project_id` int(11) NOT NULL,
  `is_current` tinyint(1) NOT NULL,
  `start_date` date NOT NULL DEFAULT current_timestamp(),
  `end_date` date NOT NULL,
  `employee_rating` int(11) NOT NULL DEFAULT 5,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE  `project` (
  `project_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `domain` varchar(100) NOT NULL,
  PRIMARY KEY (`project_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

INSERT INTO `project` (`project_id`, `name`, `domain`) VALUES
(1, 'GREAT HEALTH Inc', 'Health Care'),
(2, 'MAG ONE', 'Retail'),
(3, 'Reliable Life Insurance', 'Insurance'),
(4, 'Strong Tyres', 'Manufacturing'),
(5, 'COMFORT HOTELs', 'Retail'),
(6, 'HIGH STEEL', 'Manufacturing');
