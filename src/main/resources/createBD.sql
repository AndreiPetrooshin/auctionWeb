-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema flowers_auction_bd
-- -----------------------------------------------------
-- ������� ������ ��� ����� ���������� ���� ����� (����) �� �����, ��������� ����� ������ ������. ������������� ������������ ������� ���� ������ � ���������� ���� ������.

-- -----------------------------------------------------
-- Schema flowers_auction_bd
--
-- ������� ������ ��� ����� ���������� ���� ����� (����) �� �����, ��������� ����� ������ ������. ������������� ������������ ������� ���� ������ � ���������� ���� ������.
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS flowers_auction_bd;
CREATE SCHEMA IF NOT EXISTS `flowers_auction_bd` DEFAULT CHARACTER SET utf8 ;
USE `flowers_auction_bd` ;

-- -----------------------------------------------------
-- Table `flowers_auction_bd`.`user_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `flowers_auction_bd`.`user_role` (
  `role_id` INT NOT NULL AUTO_INCREMENT COMMENT '������������� ���� ������������',
  `user_role` ENUM('admin', 'user') NOT NULL COMMENT '���� ������������',
  PRIMARY KEY (`role_id`))
ENGINE = InnoDB
COMMENT = '��������� ���� ( ����� ������� ) � ����������';


-- -----------------------------------------------------
-- Table `flowers_auction_bd`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `flowers_auction_bd`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT COMMENT '������������� �����',
  `role_id` INT NOT NULL COMMENT '������������� ���� �����',
  `u_login` VARCHAR(45) NOT NULL COMMENT '����� �����, �� ����� ���� null � ������ ���� ����������',
  `u_password` CHAR(32) NOT NULL COMMENT '������ �����, �� ����� ���� null',
  `u_email` VARCHAR(80) NOT NULL COMMENT '������ ����������� �����, �������� ����������, �� ����� ���� null',
  PRIMARY KEY (`user_id`),
  INDEX `fk_users_roles_idx` (`role_id` ASC),
  UNIQUE INDEX `u_login_UNIQUE` (`u_login` ASC),
  UNIQUE INDEX `u_email_UNIQUE` (`u_email` ASC),
  CONSTRAINT `fk_users_roles`
    FOREIGN KEY (`role_id`)
    REFERENCES `flowers_auction_bd`.`user_role` (`role_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
COMMENT = '���������� � ������������';


-- -----------------------------------------------------
-- Table `flowers_auction_bd`.`user_cards`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `flowers_auction_bd`.`user_cards` (
  `card_id` INT NOT NULL AUTO_INCREMENT COMMENT '������������� ������� ������ �����',
  `user_id` INT NOT NULL COMMENT '������������� �����',
  `card_number` VARCHAR(16) NOT NULL COMMENT '����� �������� ������������, ��� �� ������ ����� ����� ���������� ������ �� ���� ����',
  `card_name` VARCHAR(50) NULL DEFAULT 'My card' COMMENT '��� �������� (������������) ������� ����� �������� ���� ��� ��������',
  PRIMARY KEY (`card_id`, `user_id`),
  INDEX `fk_user_cards_users1_idx` (`user_id` ASC),
  UNIQUE INDEX `uc_card_number_UNIQUE` (`card_number` ASC),
  CONSTRAINT `fk_user_cards_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `flowers_auction_bd`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
COMMENT = '�������� ������������, ����� ����������������, ��� ��� �� ����� ������������ �������� ��� ������������. ������� �� ������ ����������� ��� ������, � ����� ������������.';


-- -----------------------------------------------------
-- Table `flowers_auction_bd`.`user_shipping_address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `flowers_auction_bd`.`user_shipping_address` (
  `ship_addr_id` INT NOT NULL AUTO_INCREMENT COMMENT '������������� ������������',
  `user_id` INT NOT NULL,
  `sa_first_name` VARCHAR(30) NOT NULL COMMENT '��� ������������ �� �������',
  `sa_second_name` VARCHAR(35) NOT NULL COMMENT '�������� ������������ �� �������',
  `sa_last_name` VARCHAR(35) NOT NULL COMMENT '������� ������������ �� �������',
  `sa_country` VARCHAR(30) NOT NULL COMMENT '������ ������������',
  `sa_city` VARCHAR(30) NOT NULL COMMENT '����� ������������',
  `sa_street` VARCHAR(50) NOT NULL COMMENT '������ ����� � ����',
  `sa_phone` VARCHAR(16) NOT NULL COMMENT '����� ��������',
  `sa_postal_code` VARCHAR(9) NULL COMMENT '�������� ���, ���� ����� ������� null, ��� ��� �� ��� ������ ����� �������� ���',
  `sa_is_active` BIT(1) NULL DEFAULT 1 COMMENT '����������, �������� �� ������ ������ �������� �� ������ ������ ��� ���.',
  PRIMARY KEY (`ship_addr_id`),
  INDEX `fk_user_shipping_address_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_shipping_address_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `flowers_auction_bd`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
COMMENT = '������� ����������� ��������� ������� ( �������� ) ������������, ���� ����� ��������� �����';


-- -----------------------------------------------------
-- Table `flowers_auction_bd`.`flower_lot`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `flowers_auction_bd`.`flower_lot` (
  `fl_id` INT NOT NULL AUTO_INCREMENT COMMENT '������������� ���� ������',
  `user_id` INT NOT NULL COMMENT '������������, �������� ����',
  `fl_type` ENUM('�������', '���������', '������ ', '�������', '�������') NULL DEFAULT '���������' COMMENT '���� ������, ����������� �������� - ��������� �.�. ���� ����� ���',
  `fl_name` VARCHAR(50) NOT NULL COMMENT '�������� �������� ',
  `fl_description` VARCHAR(500) NULL COMMENT '�������� ���� (��������)',
  `fl_start_price` DECIMAL(10,2) NOT NULL COMMENT '��������� ���� ���� ( �������� ) ',
  `fl_state` ENUM('new', 'trading', 'rejected', 'sold') NULL DEFAULT 'new' COMMENT '��������� ����, ������� ������������� �������������, �������������� ��������� ������ new! ',
  PRIMARY KEY (`fl_id`),
  INDEX `fk_flower_lot_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `flowers_auction_bd`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
COMMENT = '��� ( ������) ������������ ������, � ������� �������� ���������� � ���, � ����� �������������� ��������� � ���������';


-- -----------------------------------------------------
-- Table `flowers_auction_bd`.`bets_m2m`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `flowers_auction_bd`.`bets_m2m` (
  `bet_id` INT NOT NULL AUTO_INCREMENT COMMENT '������������� ������',
  `fl_id` INT NOT NULL COMMENT '������������� ����\n',
  `user_id` INT NOT NULL COMMENT '������������� �����',
  `user_bet` DECIMAL(10,2) NOT NULL COMMENT '������ ������� ������ �����',
  PRIMARY KEY (`bet_id`),
  INDEX `fk_flower_lot_has_user_user1_idx` (`user_id` ASC),
  INDEX `fk_flower_lot_has_user_flower_lot1_idx` (`fl_id` ASC),
  CONSTRAINT `fk_flower_lot_has_user_flower_lot1`
    FOREIGN KEY (`fl_id`)
    REFERENCES `flowers_auction_bd`.`flower_lot` (`fl_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_flower_lot_has_user_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `flowers_auction_bd`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
COMMENT = '������� �� �������� ������';


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
