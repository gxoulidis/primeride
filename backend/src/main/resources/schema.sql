DROP TABLE IF EXISTS booking;
DROP TABLE IF EXISTS car;
DROP TABLE IF EXISTS citizen;
DROP TABLE IF EXISTS dealership;


CREATE TABLE `citizen` (
    `afm` CHAR(9) NOT NULL PRIMARY KEY,
    `first_name` VARCHAR(255) NOT NULL,
    `last_name` VARCHAR(255) NOT NULL,
    `email` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL
);

CREATE TABLE `dealership` (
     `afm` CHAR(9) NOT NULL PRIMARY KEY,
     `email` VARCHAR(255) NOT NULL,
     `dealership_name` VARCHAR(255) NOT NULL,
     `owner_name` VARCHAR(255) NOT NULL,
     `password` VARCHAR(255) NOT NULL
);


CREATE TABLE `car` (
     `car_id` BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
     `brand` VARCHAR(255) NOT NULL,
     `model` VARCHAR(255) NOT NULL,
     `fuel_type` ENUM('DIESEL', 'PETROL', 'ELECTRIC', 'HYBRID') NOT NULL,
     `engine_type` ENUM('INLINE4', 'INLINE6', 'V6', 'V8', 'ELECTRIC', 'HYBRID') NOT NULL,
     `seats` INT NOT NULL,
     `price` DOUBLE NOT NULL,
     `additional_info` VARCHAR(255),
     `number_of_cars` INT NOT NULL,
     `dealership_afm` CHAR(9) NOT NULL,
     FOREIGN KEY (`dealership_afm`) REFERENCES `dealership`(`afm`)
);


CREATE TABLE `booking`(
    `booking_id` BIGINT  NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `date` DATE NOT NULL,
    `days_of_booking` INT NOT NULL,
    `citizen_afm` CHAR(9) NOT NULL,
    `car_id` BIGINT NOT NULL,
    FOREIGN KEY (`citizen_afm`) REFERENCES `citizen`(`afm`) ON DELETE CASCADE,
    FOREIGN KEY (`car_id`) REFERENCES `car`(`car_id`) ON DELETE CASCADE
);
