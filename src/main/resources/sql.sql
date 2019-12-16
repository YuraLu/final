-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema tutor_system
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema tutor_system
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `tutor_system` DEFAULT CHARACTER SET utf8 ;
USE `tutor_system` ;

-- -----------------------------------------------------
-- Table `tutor_system`.`questions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tutor_system`.`questions` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `text` VARCHAR(100) NOT NULL,
  `testId` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tutor_system`.`subjects`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tutor_system`.`subjects` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) CHARACTER SET 'utf8' NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tutor_system`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tutor_system`.`roles` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tutor_system`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tutor_system`.`users` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `roleId` INT(11) NOT NULL,
  `banned` TINYINT(1) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_users_roles1_idx` (`roleId` ASC) VISIBLE,
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `password_UNIQUE` (`password` ASC) VISIBLE,
  CONSTRAINT `fk_users_roles1`
    FOREIGN KEY (`roleId`)
    REFERENCES `tutor_system`.`roles` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tutor_system`.`tests`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tutor_system`.`tests` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) CHARACTER SET 'utf8' NOT NULL,
  `description` VARCHAR(110) NOT NULL,
  `subjectId` INT(11) NULL,
  `authorId` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tests_subjects_idx` (`subjectId` ASC) VISIBLE,
  UNIQUE INDEX `title_UNIQUE` (`title` ASC) VISIBLE,
  INDEX `fk_tests_users1_idx` (`authorId` ASC) VISIBLE,
  CONSTRAINT `fk_tests_subjects`
    FOREIGN KEY (`subjectId`)
    REFERENCES `tutor_system`.`subjects` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tests_users1`
    FOREIGN KEY (`authorId`)
    REFERENCES `tutor_system`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tutor_system`.`answers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tutor_system`.`answers` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `text` VARCHAR(45) NOT NULL,
  `correct` TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tutor_system`.`answerGroups`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tutor_system`.`answerGroups` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `answerId` INT(11) NOT NULL,
  `questionId` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_answerGroup_answer1_idx` (`answerId` ASC) VISIBLE,
  INDEX `fk_answerGroup_questions1_idx` (`questionId` ASC) VISIBLE,
  CONSTRAINT `fk_answerGroup_answer1`
    FOREIGN KEY (`answerId`)
    REFERENCES `tutor_system`.`answers` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_answerGroup_questions1`
    FOREIGN KEY (`questionId`)
    REFERENCES `tutor_system`.`questions` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tutor_system`.`testQuestions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tutor_system`.`testQuestions` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `questionId` INT(11) NOT NULL,
  `testId` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_testQuestions_questions1_idx` (`questionId` ASC) VISIBLE,
  INDEX `fk_testQuestions_tests1_idx` (`testId` ASC) VISIBLE,
  CONSTRAINT `fk_testQuestions_questions1`
    FOREIGN KEY (`questionId`)
    REFERENCES `tutor_system`.`questions` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_testQuestions_tests1`
    FOREIGN KEY (`testId`)
    REFERENCES `tutor_system`.`tests` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tutor_system`.`assignments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tutor_system`.`assignments` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `score` INT NULL,
  `date` TIMESTAMP NULL,
  `testId` INT(11) NOT NULL,
  `studentId` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_assignments_tests1_idx` (`testId` ASC) VISIBLE,
  INDEX `fk_assignments_users1_idx` (`studentId` ASC) VISIBLE,
  CONSTRAINT `fk_assignments_tests1`
    FOREIGN KEY (`testId`)
    REFERENCES `tutor_system`.`tests` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_assignments_users1`
    FOREIGN KEY (`studentId`)
    REFERENCES `tutor_system`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tutor_system`.`replies`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tutor_system`.`replies` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `answerId` INT(11) NOT NULL,
  `questionId` INT(11) NOT NULL,
  `assignmentId` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_replies_answers1_idx` (`answerId` ASC) VISIBLE,
  INDEX `fk_replies_questions1_idx` (`questionId` ASC) VISIBLE,
  INDEX `fk_replies_assignments1_idx` (`assignmentId` ASC) VISIBLE,
  CONSTRAINT `fk_replies_answers1`
    FOREIGN KEY (`answerId`)
    REFERENCES `tutor_system`.`answers` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_replies_questions1`
    FOREIGN KEY (`questionId`)
    REFERENCES `tutor_system`.`questions` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_replies_assignments1`
    FOREIGN KEY (`assignmentId`)
    REFERENCES `tutor_system`.`assignments` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
