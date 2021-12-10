-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema elective_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema elective_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `elective_db` DEFAULT CHARACTER SET utf8 ;
USE `elective_db` ;

-- -----------------------------------------------------
-- Table `elective_db`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elective_db`.`role` ;

CREATE TABLE IF NOT EXISTS `elective_db`.`role` (
  `role_id` INT NOT NULL AUTO_INCREMENT,
  `role_name` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE INDEX `role_name_UNIQUE` (`role_name` ASC) VISIBLE);


-- -----------------------------------------------------
-- Table `elective_db`.`profile`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elective_db`.`profile` ;

CREATE TABLE IF NOT EXISTS `elective_db`.`profile` (
  `id_prifile` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(16) NOT NULL,
  `password` VARCHAR(32) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `telephone` VARCHAR(12) NOT NULL,
  `name` VARCHAR(45) NULL,
  `surname` VARCHAR(45) NULL,
  `role` VARCHAR(15) NOT NULL DEFAULT 'STUDENT',
  `block_status` TINYINT NULL DEFAULT 0,
  PRIMARY KEY (`id_prifile`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE,
  UNIQUE INDEX `telephone_UNIQUE` (`telephone` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  INDEX `fk_profile_1_idx` (`role` ASC) VISIBLE,
  CONSTRAINT `fk_profile_1`
    FOREIGN KEY (`role`)
    REFERENCES `elective_db`.`role` (`role_name`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table `elective_db`.`topic`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elective_db`.`topic` ;

CREATE TABLE IF NOT EXISTS `elective_db`.`topic` (
  `topic_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`topic_id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE);


-- -----------------------------------------------------
-- Table `elective_db`.`course`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elective_db`.`course` ;

CREATE TABLE IF NOT EXISTS `elective_db`.`course` (
  `id_course` INT NOT NULL AUTO_INCREMENT,
  `course_name` VARCHAR(30) NOT NULL,
  `course_topic` INT NOT NULL,
  `duration` INT NOT NULL,
  PRIMARY KEY (`id_course`),
  UNIQUE INDEX `course_name_UNIQUE` (`course_name` ASC) VISIBLE,
  INDEX `fk_course_topic1_idx` (`course_topic` ASC) VISIBLE,
  CONSTRAINT `fk_course_topic1`
    FOREIGN KEY (`course_topic`)
    REFERENCES `elective_db`.`topic` (`topic_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table `elective_db`.`course_description`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elective_db`.`course_description` ;

CREATE TABLE IF NOT EXISTS `elective_db`.`course_description` (
  `course_description_id_course` INT NULL,
  `course_info` LONGTEXT NULL,
  `course_image` BLOB NULL,
  UNIQUE INDEX `course_id_course_UNIQUE` (`course_description_id_course` ASC),
  CONSTRAINT `fk_course_description_course`
    FOREIGN KEY (`course_description_id_course`)
    REFERENCES `elective_db`.`course` (`id_course`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION);
    

-- -----------------------------------------------------
-- Table `elective_db`.`gradebook`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elective_db`.`gradebook` ;

CREATE TABLE IF NOT EXISTS `elective_db`.`gradebook` (
  `student_id_prifile` INT NOT NULL,
  `course_id_course` INT NOT NULL,
  `grade` INT NULL,
  INDEX `fk_profile_has_course_course1_idx` (`course_id_course` ASC) VISIBLE,
  CONSTRAINT `fk_profile_has_course_profile1`
    FOREIGN KEY (`student_id_prifile`)
    REFERENCES `elective_db`.`profile` (`id_prifile`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_profile_has_course_course1`
    FOREIGN KEY (`course_id_course`)
    REFERENCES `elective_db`.`course` (`id_course`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `elective_db`.`profile_course`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elective_db`.`profile_course` ;

CREATE TABLE IF NOT EXISTS `elective_db`.`profile_course` (
  `id_prifile` INT NULL,
  `id_course` INT NULL,
  `start_day_course` DATE NULL,
  INDEX `fk_profile_has_course_course2_idx` (`id_course` ASC) VISIBLE,
  INDEX `fk_profile_has_course_profile2_idx` (`id_prifile` ASC) VISIBLE,
  UNIQUE INDEX `id_prifile_UNIQUE` (`id_prifile` ASC) VISIBLE,
  UNIQUE INDEX `id_course_UNIQUE` (`id_course` ASC) VISIBLE,
  CONSTRAINT `fk_profile_has_course_profile2`
    FOREIGN KEY (`id_prifile`)
    REFERENCES `elective_db`.`profile` (`id_prifile`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_profile_has_course_course2`
    FOREIGN KEY (`id_course`)
    REFERENCES `elective_db`.`course` (`id_course`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
    
INSERT INTO role(role_id, role_name) VALUES (DEFAULT, 'ADMIN'), (DEFAULT, 'TEACHER'), (DEFAULT, 'STUDENT');

INSERT INTO topic(topic_id, name) VALUES (DEFAULT, 'JAVA'), (DEFAULT, 'C++'), (DEFAULT, '.NET'), (DEFAULT, 'JavaSkript');

SET @text = 'admin';
INSERT INTO profile (id_prifile, login, password, email, telephone, name, surname, role) VALUES(DEFAULT, @text, '21232f297a57a5a743894a0e4a801fc3', 'admin@gmail.com', 38050897564, 'Ivan', 'Petrov', 'ADMIN');

SET @text = 'teacher';
INSERT INTO profile (id_prifile, login, password, email, telephone, name, surname, role) VALUES(DEFAULT, @text, '8d788385431273d11e8b43bb78f3aa41', 'teacher@gmail.com', 380508885645, 'Dmytro', 'Kolesnicov', 'TEACHER');

SET @text = 'teacher1';
INSERT INTO profile (id_prifile, login, password, email, telephone, name, surname, role) VALUES(DEFAULT, @text, '41c8949aa55b8cb5dbec662f34b62df3', 'teacher01@gmail.com', 380508545645, 'Yuriy', 'Mischeryacov', 'TEACHER');

SET @text = 'teacher2';
INSERT INTO profile (id_prifile, login, password, email, telephone, name, surname, role) VALUES(DEFAULT, @text, 'ccffb0bb993eeb79059b31e1611ec353', 'teacher02@gmail.com', 380508733625, 'Maksim', 'Veres', 'TEACHER');

SET @text = 'teacher3';
INSERT INTO profile (id_prifile, login, password, email, telephone, name, surname, role) VALUES(DEFAULT, @text, '82470256ea4b80343b27afccbca1015b', 'teacher03@gmail.com', 380508744245, 'Petr', 'Petrov', 'TEACHER');

SET @text = 'teacher4';
INSERT INTO profile (id_prifile, login, password, email, telephone, name, surname, role) VALUES(DEFAULT, @text, '93dacda950b1dd917079440788af3321', 'teacher04@gmail.com', 380508755645, 'Ilya', 'Ilyin', 'TEACHER');

SET @text = 'teacher5';
INSERT INTO profile (id_prifile, login, password, email, telephone, name, surname, role) VALUES(DEFAULT, @text, 'ea105f0d381e790cdadc6a41eb611c77', 'teacher05@gmail.com', 380978744642, 'Konstantin', 'Kostin', 'TEACHER');

SET @text = 'teacher6';
INSERT INTO profile (id_prifile, login, password, email, telephone, name, surname, role) VALUES(DEFAULT, @text, 'ff1643afb67a6edb36ee3f6fea756323', 'teacher06@gmail.com', 380988725645, 'Artemiy', 'Artem`ev', 'TEACHER');

SET @text = 'teacher7';
INSERT INTO profile (id_prifile, login, password, email, telephone, name, surname, role) VALUES(DEFAULT, @text, '71e0f8d7d61b45e27b57c62eb8684583', 'teacher07@gmail.com', 380978752645, 'Anton', 'Antonov', 'TEACHER');

SET @text = 'teacher8';
INSERT INTO profile (id_prifile, login, password, email, telephone, name, surname, role) VALUES(DEFAULT, @text, 'ee1079e7de417c403b87932ea235dab7', 'teacher08@gmail.com', 380978722645, 'Valeriy', 'Mishun', 'TEACHER');



SET @text = 'student';
INSERT INTO profile (id_prifile, login, password, email, telephone, name, surname, role) VALUES(DEFAULT, @text, 'cd73502828457d15655bbd7a63fb0bc8', 'student@gmail.com', 380508665645, 'Max', 'Shelkovich', 'STUDENT');

SET @text = 'student1';
INSERT INTO profile (id_prifile, login, password, email, telephone, name, surname, role) VALUES(DEFAULT, @text, '5e5545d38a68148a2d5bd5ec9a89e327', 'student01@gmail.com', 380508555645, 'Vladimir', 'Petranovskiy', 'STUDENT');

SET @text = 'student2';
INSERT INTO profile (id_prifile, login, password, email, telephone, name, surname, role) VALUES(DEFAULT, @text, '213ee683360d88249109c2f92789dbc3', 'student02@gmail.com', 380508933645, 'Denis', 'Picus', 'STUDENT');

SET @text = 'student3';
INSERT INTO profile (id_prifile, login, password, email, telephone, name, surname, role) VALUES(DEFAULT, @text, '8e4947690532bc44a8e41e9fb365b76a', 'student03@gmail.com', 380678741645, 'Aleksandr', 'Konduhovich', 'STUDENT');

SET @text = 'student4';
INSERT INTO profile (id_prifile, login, password, email, telephone, name, surname, role) VALUES(DEFAULT, @text, '166a50c910e390d922db4696e4c7747b', 'student04@gmail.com', 380508795645, 'Denis', 'Golik', 'STUDENT');

SET @text = 'student5';
INSERT INTO profile (id_prifile, login, password, email, telephone, name, surname, role) VALUES(DEFAULT, @text, '9fd9280a7aa3578c8e853745a5fcc18a', 'student05@gmail.com', 380508675645, 'Il`ya', 'Shelk', 'STUDENT');

SET @text = 'student6';
INSERT INTO profile (id_prifile, login, password, email, telephone, name, surname, role) VALUES(DEFAULT, @text, '27e062bf3df59edebb5db9f89952c8b3', 'student06@gmail.com', 380508595645, 'Dmitriy', 'Proscura', 'STUDENT');

SET @text = 'student7';
INSERT INTO profile (id_prifile, login, password, email, telephone, name, surname, role) VALUES(DEFAULT, @text, '72e8744fc2faa17a83dec9bed06b8b65', 'student07@gmail.com', 380508913645, 'Pavel', 'Prats', 'STUDENT');

SET @text = 'student8';
INSERT INTO profile (id_prifile, login, password, email, telephone, name, surname, role) VALUES(DEFAULT, @text, '8aa7fb36a4efbbf019332b4677b528cf', 'student08@gmail.com', 380508741645, 'Aleksandr', 'Pryanic', 'STUDENT');

SET @text = 'student9';
INSERT INTO profile (id_prifile, login, password, email, telephone, name, surname, role) VALUES(DEFAULT, @text, '7c8cd5da17441ff04bf445736964dd16', 'student09@gmail.com', 380508715645, 'Oleg', 'Smytnuy', 'STUDENT');


INSERT INTO profile_course VALUES (2,1,'2021-01-12');
INSERT INTO profile_course VALUES (3,2,'2021-11-22');
INSERT INTO profile_course VALUES (4,3,'2022-01-15');
INSERT INTO profile_course VALUES (6,5,'2021-02-01');
INSERT INTO profile_course VALUES (7,6,'2021-11-30');
INSERT INTO profile_course VALUES (8,7,'2022-03-10');


INSERT INTO gradebook (student_id_prifile, course_id_course) VALUES(11,1);
INSERT INTO gradebook (student_id_prifile, course_id_course) VALUES(12,3);
INSERT INTO gradebook (student_id_prifile, course_id_course) VALUES(13,5);
INSERT INTO gradebook (student_id_prifile, course_id_course) VALUES(14,2);
INSERT INTO gradebook (student_id_prifile, course_id_course) VALUES(15,4);
INSERT INTO gradebook (student_id_prifile, course_id_course) VALUES(16,5);
INSERT INTO gradebook (student_id_prifile, course_id_course) VALUES(17,6);
INSERT INTO gradebook (student_id_prifile, course_id_course) VALUES(18,7);
INSERT INTO gradebook (student_id_prifile, course_id_course) VALUES(19,8);
INSERT INTO gradebook (student_id_prifile, course_id_course) VALUES(20,9);
INSERT INTO gradebook (student_id_prifile, course_id_course) VALUES(11,2);
INSERT INTO gradebook (student_id_prifile, course_id_course) VALUES(12,4);
INSERT INTO gradebook (student_id_prifile, course_id_course) VALUES(13,6);
INSERT INTO gradebook (student_id_prifile, course_id_course) VALUES(14,7);
INSERT INTO gradebook (student_id_prifile, course_id_course) VALUES(15,8);
INSERT INTO gradebook (student_id_prifile, course_id_course) VALUES(16,9);
INSERT INTO gradebook (student_id_prifile, course_id_course) VALUES(17,1);
INSERT INTO gradebook (student_id_prifile, course_id_course) VALUES(18,2);
INSERT INTO gradebook (student_id_prifile, course_id_course) VALUES(19,3);
INSERT INTO gradebook (student_id_prifile, course_id_course) VALUES(20,4);
INSERT INTO gradebook (student_id_prifile, course_id_course) VALUES(11,5);
INSERT INTO gradebook (student_id_prifile, course_id_course) VALUES(12,6);
INSERT INTO gradebook (student_id_prifile, course_id_course) VALUES(13,7);
INSERT INTO gradebook (student_id_prifile, course_id_course) VALUES(14,8);
INSERT INTO gradebook (student_id_prifile, course_id_course) VALUES(15,9);
INSERT INTO gradebook (student_id_prifile, course_id_course) VALUES(16,1);


INSERT INTO course VALUES (DEFAULT, 'JAVA BASIC',1, 12);
INSERT INTO course VALUES (DEFAULT, 'JAVA ADVANTAGE',1, 24);
INSERT INTO course VALUES (DEFAULT, 'C++ BASIC',2, 12);
INSERT INTO course VALUES (DEFAULT, 'C++ ADVANTAGE',2, 24);
INSERT INTO course VALUES (DEFAULT, '.NET BASIC',3, 12);
INSERT INTO course VALUES (DEFAULT, '.NET ADVANTAGE',3, 24);
INSERT INTO course VALUES (DEFAULT, 'JavaSkript Basic',4, 12);
INSERT INTO course VALUES (DEFAULT, 'JavaSkript ADVANTAGE',4, 24);
INSERT INTO course VALUES (DEFAULT, 'JavaSkript Intensive',4, 30);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

