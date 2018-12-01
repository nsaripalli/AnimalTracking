-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`species`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`species` ;

CREATE TABLE IF NOT EXISTS `mydb`.`species` (
  `species_id` INT NOT NULL AUTO_INCREMENT,
  `taxon` VARCHAR(100) NOT NULL,
  `common_name` VARCHAR(250) NULL,
  `scientific_name` VARCHAR(250) NOT NULL,
  PRIMARY KEY (`species_id`),
  UNIQUE INDEX `species_id_UNIQUE` (`species_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`observer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`observer` ;

CREATE TABLE IF NOT EXISTS `mydb`.`observer` (
  `observer_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(150) NOT NULL,
  `email` VARCHAR(150) NOT NULL,
  `phone` VARCHAR(20) NULL,
  `scientist` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`observer_id`),
  UNIQUE INDEX `observer_id_UNIQUE` (`observer_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`watch`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`watch` ;

CREATE TABLE IF NOT EXISTS `mydb`.`watch` (
  `watch_id` INT NOT NULL AUTO_INCREMENT,
  `latitude` DOUBLE NOT NULL,
  `longitude` DOUBLE NOT NULL,
  `radius` DOUBLE NOT NULL DEFAULT 1,
  `start_date` DATETIME NOT NULL DEFAULT NOW(),
  `end_date` DATETIME NULL,
  `species_id` INT NOT NULL,
  `scientist_id` INT NOT NULL,
  PRIMARY KEY (`watch_id`),
  UNIQUE INDEX `watch_id_UNIQUE` (`watch_id` ASC) VISIBLE,
  INDEX `fk_watch_species_idx` (`species_id` ASC) VISIBLE,
  INDEX `fk_watch_observer1_idx` (`scientist_id` ASC) VISIBLE,
  CONSTRAINT `fk_watch_species`
    FOREIGN KEY (`species_id`)
    REFERENCES `mydb`.`species` (`species_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_watch_observer1`
    FOREIGN KEY (`scientist_id`)
    REFERENCES `mydb`.`observer` (`observer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`sighting`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`sighting` ;

CREATE TABLE IF NOT EXISTS `mydb`.`sighting` (
  `sighting_id` INT NOT NULL AUTO_INCREMENT,
  `quantity` INT NOT NULL DEFAULT 1,
  `latitude` DOUBLE NOT NULL,
  `longitude` DOUBLE NOT NULL,
  `notes` VARCHAR(500) NULL,
  `species_id` INT NOT NULL,
  `observer_id` INT NOT NULL,
  `photo` BLOB NULL,
  `watch_id` INT NULL,
  PRIMARY KEY (`sighting_id`),
  UNIQUE INDEX `sighting_id_UNIQUE` (`sighting_id` ASC) VISIBLE,
  INDEX `fk_sighting_species1_idx` (`species_id` ASC) VISIBLE,
  INDEX `fk_sighting_observer1_idx` (`observer_id` ASC) VISIBLE,
  INDEX `fk_sighting_watch1_idx` (`watch_id` ASC) VISIBLE,
  CONSTRAINT `fk_sighting_species1`
    FOREIGN KEY (`species_id`)
    REFERENCES `mydb`.`species` (`species_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sighting_observer1`
    FOREIGN KEY (`observer_id`)
    REFERENCES `mydb`.`observer` (`observer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sighting_watch1`
    FOREIGN KEY (`watch_id`)
    REFERENCES `mydb`.`watch` (`watch_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
