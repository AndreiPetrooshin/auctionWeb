CREATE SCHEMA IF NOT EXISTS `flowers_auction_bd_andreico` DEFAULT CHARACTER SET utf8 ;
USE `flowers_auction_bd` ;

-- Table `flowers_auction_bd`.`user_role`
CREATE TABLE IF NOT EXISTS `flowers_auction_bd_andreico`.`user_role` (
  `role_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_role` ENUM('admin', 'user') NOT NULL,
  PRIMARY KEY (`role_id`))
  ENGINE = InnoDB;


-- Table `flowers_auction_bd`.`user`
CREATE TABLE IF NOT EXISTS `flowers_auction_bd_andreico`.`user` (
  `user_id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `role_id` INT UNSIGNED NOT NULL,
  `u_login` VARCHAR(45) NOT NULL,
  `u_password` CHAR(32) NOT NULL,
  `u_email` VARCHAR(80) NOT NULL,
  PRIMARY KEY (`user_id`),
  INDEX `fk_users_roles_idx` (`role_id` ASC),
  UNIQUE INDEX `u_login_UNIQUE` (`u_login` ASC),
  UNIQUE INDEX `u_email_UNIQUE` (`u_email` ASC),
  CONSTRAINT `fk_users_roles`
  FOREIGN KEY (`role_id`)
  REFERENCES `flowers_auction_bd_andreico`.`user_role` (`role_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
  ENGINE = InnoDB;


-- Table `flowers_auction_bd`.`user_cards`
CREATE TABLE IF NOT EXISTS `flowers_auction_bd_andreico`.`user_cards` (
  `card_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` INT UNSIGNED NOT NULL,
  `card_number` VARCHAR(16) NOT NULL,
  `card_name` VARCHAR(50) NULL DEFAULT 'My card',
  PRIMARY KEY (`card_id`),
  INDEX `fk_user_cards_users1_idx` (`user_id` ASC),
  UNIQUE INDEX `uc_card_number_UNIQUE` (`card_number` ASC),
  CONSTRAINT `fk_user_cards_users1`
  FOREIGN KEY (`user_id`)
  REFERENCES `flowers_auction_bd_andreico`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
  ENGINE = InnoDB;


-- Table `flowers_auction_bd`.`user_shipping_address`
CREATE TABLE IF NOT EXISTS `flowers_auction_bd_andreico`.`user_shipping_address` (
  `ship_addr_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` INT UNSIGNED NOT NULL,
  `sa_first_name` VARCHAR(30) NOT NULL,
  `sa_second_name` VARCHAR(35) NOT NULL,
  `sa_last_name` VARCHAR(35) NOT NULL,
  `sa_country` VARCHAR(30) NOT NULL,
  `sa_city` VARCHAR(30) NOT NULL,
  `sa_street` VARCHAR(50) NOT NULL,
  `sa_phone` VARCHAR(16) NOT NULL,
  `sa_postal_code` VARCHAR(9) NULL,
  PRIMARY KEY (`ship_addr_id`),
  INDEX `fk_user_shipping_address_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_shipping_address_user1`
  FOREIGN KEY (`user_id`)
  REFERENCES `flowers_auction_bd_andreico`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
  ENGINE = InnoDB;


-- Table `flowers_auction_bd`.`flower_lot`
CREATE TABLE IF NOT EXISTS `flowers_auction_bd_andreico`.`flower_lot` (
  `fl_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` INT UNSIGNED NOT NULL,
  `fl_type` ENUM('meadow', 'indoor', 'forest ', 'garden', 'cactus') NULL DEFAULT 'indoor',
  `fl_name` VARCHAR(50) NOT NULL,
  `fl_description` VARCHAR(500) NULL,
  `fl_start_price` DECIMAL(10,2) NOT NULL,
  `fl_state` ENUM('new', 'trading', 'rejected', 'sold') NULL DEFAULT 'new',
  PRIMARY KEY (`fl_id`),
  INDEX `fk_flower_lot_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user`
  FOREIGN KEY (`user_id`)
  REFERENCES `flowers_auction_bd_andreico`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
  ENGINE = InnoDB;


-- Table `flowers_auction_bd`.`bets_m2m`
CREATE TABLE IF NOT EXISTS `flowers_auction_bd_andreico`.`bets_m2m` (
  `bet_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `fl_id` INT UNSIGNED NOT NULL,
  `user_id` INT UNSIGNED NOT NULL,
  `user_bet` DECIMAL(10,2) UNSIGNED NOT NULL,
  INDEX `fk_flower_lot_has_user_user1_idx` (`user_id` ASC),
  INDEX `fk_flower_lot_has_user_flower_lot1_idx` (`fl_id` ASC),
  PRIMARY KEY (`bet_id`),
  CONSTRAINT `fk_flower_lot_has_user_flower_lot1`
  FOREIGN KEY (`fl_id`)
  REFERENCES `flowers_auction_bd_andreico`.`flower_lot` (`fl_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_flower_lot_has_user_user1`
  FOREIGN KEY (`user_id`)
  REFERENCES `flowers_auction_bd_andreico`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
  ENGINE = InnoDB;


-- Table `flowers_auction_bd`.`payments`
CREATE TABLE IF NOT EXISTS `flowers_auction_bd_andreico`.`payments` (
  `payment_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `fl_id` INT UNSIGNED NOT NULL,
  `user_id` INT UNSIGNED NOT NULL,
  `price` DECIMAL(10,2) UNSIGNED NULL,
  `is_paid` BIT(1) NULL DEFAULT 0,
  PRIMARY KEY (`payment_id`),
  INDEX `fk_payments_fl1_idx` (`fl_id` ASC),
  INDEX `fk_payments_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_payments_flower_lot1`
  FOREIGN KEY (`fl_id`)
  REFERENCES `flowers_auction_bd_andreico`.`flower_lot` (`fl_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_payments_user1`
  FOREIGN KEY (`user_id`)
  REFERENCES `flowers_auction_bd_andreico`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;

