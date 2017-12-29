-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema flowers_auction_bd
-- -----------------------------------------------------
-- Аукцион цветов где юзеры предлагают свои цветы (лоты) на торги, остальные юзеры делают ставки. Администратор контролирует процесс хода торгов и выставляет лоты юзеров.

-- -----------------------------------------------------
-- Schema flowers_auction_bd
--
-- Аукцион цветов где юзеры предлагают свои цветы (лоты) на торги, остальные юзеры делают ставки. Администратор контролирует процесс хода торгов и выставляет лоты юзеров.
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS flowers_auction_bd;
CREATE SCHEMA IF NOT EXISTS `flowers_auction_bd` DEFAULT CHARACTER SET utf8 ;
USE `flowers_auction_bd` ;

-- -----------------------------------------------------
-- Table `flowers_auction_bd`.`user_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `flowers_auction_bd`.`user_role` (
  `role_id` INT NOT NULL AUTO_INCREMENT COMMENT 'Идентификатор роли пользователя',
  `user_role` ENUM('admin', 'user') NOT NULL COMMENT 'Роль пользователя',
  PRIMARY KEY (`role_id`))
ENGINE = InnoDB
COMMENT = 'Описывает роли ( права доступа ) в приложении';


-- -----------------------------------------------------
-- Table `flowers_auction_bd`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `flowers_auction_bd`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT COMMENT 'Идентификатор юзера',
  `role_id` INT NOT NULL COMMENT 'Идентификатор прав юзера',
  `u_login` VARCHAR(45) NOT NULL COMMENT 'Логин юзера, не может быть null и должен быть уникальным',
  `u_password` CHAR(32) NOT NULL COMMENT 'Пароль юзера, не может быть null',
  `u_email` VARCHAR(80) NOT NULL COMMENT 'Адресс электронной почты, является уникальным, не может быть null',
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
COMMENT = 'Информация о пользователе';


-- -----------------------------------------------------
-- Table `flowers_auction_bd`.`user_cards`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `flowers_auction_bd`.`user_cards` (
  `card_id` INT NOT NULL AUTO_INCREMENT COMMENT 'Идентификатор способа оплаты юзера',
  `user_id` INT NOT NULL COMMENT 'Идентификатор юзера',
  `card_number` VARCHAR(16) NOT NULL COMMENT 'Номер карточки пользователя, что бы другие юзеры могли переводить деньги на этот счет',
  `card_name` VARCHAR(50) NULL DEFAULT 'My card' COMMENT 'Имя карточки (произвольное) которое может задовать юзер для удобства',
  PRIMARY KEY (`card_id`, `user_id`),
  INDEX `fk_user_cards_users1_idx` (`user_id` ASC),
  UNIQUE INDEX `uc_card_number_UNIQUE` (`card_number` ASC),
  CONSTRAINT `fk_user_cards_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `flowers_auction_bd`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
COMMENT = 'Карточки пользователя, связь идентифицирующая, так как не может существовать карточек без пользователя. Таблица не хранит инфорамацию для оплаты, в целях безопасности.';


-- -----------------------------------------------------
-- Table `flowers_auction_bd`.`user_shipping_address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `flowers_auction_bd`.`user_shipping_address` (
  `ship_addr_id` INT NOT NULL AUTO_INCREMENT COMMENT 'Идентификатор пользователя',
  `user_id` INT NOT NULL,
  `sa_first_name` VARCHAR(30) NOT NULL COMMENT 'Имя закрепленное за адресом',
  `sa_second_name` VARCHAR(35) NOT NULL COMMENT 'Отчество закрепленное за адресом',
  `sa_last_name` VARCHAR(35) NOT NULL COMMENT 'Фамилия закрепленное за адресом',
  `sa_country` VARCHAR(30) NOT NULL COMMENT 'Страна пользователя',
  `sa_city` VARCHAR(30) NOT NULL COMMENT 'Город пользователя',
  `sa_street` VARCHAR(50) NOT NULL COMMENT 'Адресс улицы и дома',
  `sa_phone` VARCHAR(16) NOT NULL COMMENT 'Номер телефона',
  `sa_postal_code` VARCHAR(9) NULL COMMENT 'Почтовый код, поле может хранить null, так как не все адреса имеют почтовый код',
  `sa_is_active` BIT(1) NULL DEFAULT 1 COMMENT 'Отображает, является ли данный адресс активным на данный момент или нет.',
  PRIMARY KEY (`ship_addr_id`),
  INDEX `fk_user_shipping_address_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_shipping_address_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `flowers_auction_bd`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
COMMENT = 'Таблица описывающая возможные адресса ( карточки ) пользователя, куда можно доставить товар';


-- -----------------------------------------------------
-- Table `flowers_auction_bd`.`flower_lot`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `flowers_auction_bd`.`flower_lot` (
  `fl_id` INT NOT NULL AUTO_INCREMENT COMMENT 'Идентификатор лота цветка',
  `user_id` INT NOT NULL COMMENT 'Пользователь, владелец лота',
  `fl_type` ENUM('луговые', 'комнатные', 'лесные ', 'садовые', 'кактусы') NULL DEFAULT 'комнатные' COMMENT 'Типы цветов, стандартное значение - Комнатные т.к. чаще будут они',
  `fl_name` VARCHAR(50) NOT NULL COMMENT 'Название растения ',
  `fl_description` VARCHAR(500) NULL COMMENT 'Описание лота (растения)',
  `fl_start_price` DECIMAL(10,2) NOT NULL COMMENT 'Начальная цена лота ( растения ) ',
  `fl_state` ENUM('new', 'trading', 'rejected', 'sold') NULL DEFAULT 'new' COMMENT 'Састояние лота, которое устанавливает администратор, первоначальное состояние всегда new! ',
  PRIMARY KEY (`fl_id`),
  INDEX `fk_flower_lot_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `flowers_auction_bd`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
COMMENT = 'Лот ( цветок) выставленный юзером, в которой хранится информация о нем, а также первоначальная стоимость и состояние';


-- -----------------------------------------------------
-- Table `flowers_auction_bd`.`bets_m2m`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `flowers_auction_bd`.`bets_m2m` (
  `bet_id` INT NOT NULL AUTO_INCREMENT COMMENT 'Идентификатор ставки',
  `fl_id` INT NOT NULL COMMENT 'Идентификатор лота\n',
  `user_id` INT NOT NULL COMMENT 'Идентификатор юзера',
  `user_bet` DECIMAL(10,2) NOT NULL COMMENT 'Ставки которые делают юзеры',
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
COMMENT = 'Таблица со ставками юзеров';


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
