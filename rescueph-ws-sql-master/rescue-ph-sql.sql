show databases;

use mysql;

CREATE TABLE `registers` (
 `userid` int NOT NULL AUTO_INCREMENT,
 `email` varchar(30) NOT NULL,
 `fullname` VARCHAR(100) DEFAULT NULL,
 `password` varchar(15) NOT NULL,
 `isloggedin` boolean DEFAULT FALSE,
 `isverified` boolean DEFAULT FALSE,
 `contactnumber` varchar(20) DEFAULT NULL,
 PRIMARY KEY (`userid`)
);

CREATE TABLE `messages` (
 `messageid` int NOT NULL AUTO_INCREMENT,
 `userid` int NOT NULL,
 `fullname` VARCHAR(100) DEFAULT NULL,
 `message` varchar(50) DEFAULT NULL,
 `xcoord` long NOT NULL,
 `ycoord` long NOT NULL,
 `status` int DEFAULT 0,
 PRIMARY KEY (`messageid`)
);

