DELETE FROM flowers_auction_bd.user;
DELETE FROM flowers_auction_bd.user_role;
DELETE FROM flowers_auction_bd.user_cards;
DELETE FROM flowers_auction_bd.user_shipping_address;
DELETE FROM flowers_auction_bd.flower_lot;
DELETE FROM flowers_auction_bd.bets_m2m;

-- user_role initizalization
INSERT INTO `flowers_auction_bd`.`user_role` (`role_id`, `user_role`) VALUES (1, 'admin');
INSERT INTO `flowers_auction_bd`.`user_role` (`role_id`, `user_role`) VALUES (2, 'user');

-- users initialization
INSERT INTO `flowers_auction_bd`.`user` (`user_id`, `role_id`, `u_login`, `u_password`, `u_email`)
VALUES (1, '1', 'admin', MD5('Qwe123123'), 'admin@mail.ru');
INSERT INTO `flowers_auction_bd`.`user` (`user_id`, `role_id`, `u_login`, `u_password`, `u_email`)
VALUES (2, '2', 'dima', MD5('Qwe123123'), 'dima@mail.ru');
INSERT INTO `flowers_auction_bd`.`user` (`user_id`, `role_id`, `u_login`, `u_password`, `u_email`)
VALUES (3, '2', 'senia', MD5('Qwe123123'), 'senia@mail.ru');
INSERT INTO `flowers_auction_bd`.`user` (`user_id`, `role_id`, `u_login`, `u_password`, `u_email`)
VALUES (4, '2', 'stephan', MD5('Qwe123123'), 'stephan@mail.ru');
INSERT INTO `flowers_auction_bd`.`user` (`user_id`, `role_id`, `u_login`, `u_password`, `u_email`)
VALUES (5, '2', 'kolia', MD5('Qwe123123'), 'kolia@mail.ru');
INSERT INTO `flowers_auction_bd`.`user` (`user_id`, `role_id`, `u_login`, `u_password`, `u_email`)
VALUES (6, '2', 'stas11', MD5('Qwe123123'), 'stas11@mail.ru');
INSERT INTO `flowers_auction_bd`.`user` (`user_id`, `role_id`, `u_login`, `u_password`, `u_email`)
VALUES (7, '2', 'Zarina', MD5('Qwe123123'), 'zarina@mail.ru');
INSERT INTO `flowers_auction_bd`.`user` (`user_id`, `role_id`, `u_login`, `u_password`, `u_email`)
VALUES (8, '2', 'Ksenia', MD5('Qwe123123'), 'kesenia@mail.ru');
INSERT INTO `flowers_auction_bd`.`user` (`user_id`, `role_id`, `u_login`, `u_password`, `u_email`)
VALUES (9, '2', 'Vika', MD5('Qwe123123'), 'vika@mail.ru');
INSERT INTO `flowers_auction_bd`.`user` (`user_id`, `role_id`, `u_login`, `u_password`, `u_email`)
VALUES (10, '2', 'Lida', MD5('Qwe123123'), 'lida@mail.ru');

-- users cards initialization 
INSERT INTO flowers_auction_bd.user_cards (card_id, user_id, card_number, card_name)
VALUES (1, 1, 3412642797438642, 'Карточка для оплат');
INSERT INTO flowers_auction_bd.user_cards (card_id, user_id, card_number, card_name)
VALUES (2, 2, 5785645622344342, 'Моя карта');
INSERT INTO flowers_auction_bd.user_cards (card_id, user_id, card_number) VALUES (3, 2, 6575675643553453);
INSERT INTO flowers_auction_bd.user_cards (card_id, user_id, card_number, card_name)
VALUES (4, 3, 1323553434233223, 'Карта 1');
INSERT INTO flowers_auction_bd.user_cards (card_id, user_id, card_number) VALUES (5, 4, 5445674564243243);
INSERT INTO flowers_auction_bd.user_cards (card_id, user_id, card_number, card_name)
VALUES (6, 4, 1231231231256666, 'Master card');
INSERT INTO flowers_auction_bd.user_cards (card_id, user_id, card_number, card_name)
VALUES (7, 5, 6478827386785568, 'Visa');
INSERT INTO flowers_auction_bd.user_cards (card_id, user_id, card_number, card_name)
VALUES (8, 5, 3256123566743578, 'Master card');
INSERT INTO flowers_auction_bd.user_cards (card_id, user_id, card_number, card_name)
VALUES (9, 5, 7856334278754533, 'VISA');
INSERT INTO flowers_auction_bd.user_cards (card_id, user_id, card_number, card_name)
VALUES (10, 6, 3562347856544345, 'CARD');
INSERT INTO flowers_auction_bd.user_cards (card_id, user_id, card_number, card_name)
VALUES (11, 7, 2322676342387856, 'EXPRESS');
INSERT INTO flowers_auction_bd.user_cards (card_id, user_id, card_number, card_name)
VALUES (12, 8, 3231346789675546, 'MASTER');
INSERT INTO flowers_auction_bd.user_cards (card_id, user_id, card_number, card_name)
VALUES (13, 9, 3324568567646435, 'VISA ELECTRON');
INSERT INTO flowers_auction_bd.user_cards (card_id, user_id, card_number, card_name)
VALUES (14, 2, 5435374575443223, 'MASTER card');
INSERT INTO flowers_auction_bd.user_cards (card_id, user_id, card_number, card_name)
VALUES (15, 3, 5467223432121233, 'Main card');
INSERT INTO flowers_auction_bd.user_cards (card_id, user_id, card_number, card_name)
VALUES (16, 7, 2235533466745755, 'visa visa');
INSERT INTO flowers_auction_bd.user_cards (card_id, user_id, card_number) VALUES (17, 7, 3432326457656557);

-- user_shipping_address initialization
INSERT INTO flowers_auction_bd.user_shipping_address (ship_addr_id, user_id, sa_first_name, sa_second_name, sa_last_name,
                                                      sa_country, sa_city, sa_street, sa_phone, sa_postal_code)
VALUES
  (1, 1, 'Андрей', 'Владимирович', 'Петрушин', 'Belarus', 'Zhodino', 'Lenina 17\1 kv 39', 375295559297, '220015');
INSERT INTO flowers_auction_bd.user_shipping_address (ship_addr_id, user_id, sa_first_name, sa_second_name, sa_last_name,
                                                      sa_country, sa_city, sa_street, sa_phone, sa_postal_code)
VALUES (2, 2, 'Дмитрий', 'Иванович', 'Батура', 'Belarus', 'Minsk', 'Molodezhnaya d 20', 375298855451, '220015');
INSERT INTO flowers_auction_bd.user_shipping_address (ship_addr_id, user_id, sa_first_name, sa_second_name, sa_last_name,
                                                      sa_country, sa_city, sa_street, sa_phone, sa_postal_code)
VALUES (3, 3, 'Сеня', 'Викторович', 'Белякович', 'Russia', 'Moscow', 'Lenina 11 kv 2', 375296429296, '101000');
INSERT INTO flowers_auction_bd.user_shipping_address (ship_addr_id, user_id, sa_first_name, sa_second_name, sa_last_name,
                                                      sa_country, sa_city, sa_street, sa_phone, sa_postal_code)
VALUES
  (4, 4, 'Степан', 'Сергеевич', 'Иванов', 'Russia', 'Dzerzhinsk', '50 let oktiabria d 20 kv 15', 375296684296, '102355');
INSERT INTO flowers_auction_bd.user_shipping_address (ship_addr_id, user_id, sa_first_name, sa_second_name, sa_last_name,
                                                      sa_country, sa_city, sa_street, sa_phone, sa_postal_code)
VALUES
  (5, 5, 'Николай', 'Дмитрьевич', 'Саренков', 'Belarus', 'Zhodino', 'Solnechnaya 20 kv 10', 375296661236, '222160');
INSERT INTO flowers_auction_bd.user_shipping_address (ship_addr_id, user_id, sa_first_name, sa_second_name, sa_last_name,
                                                      sa_country, sa_city, sa_street, sa_phone, sa_postal_code)
VALUES
  (6, 6, 'Станислва', 'Аркадьевна', 'Жмых', 'Belarus', 'Zhodino', 'per. Raduzhnii d 20', 375297419296, '222161');
INSERT INTO flowers_auction_bd.user_shipping_address (ship_addr_id, user_id, sa_first_name, sa_second_name, sa_last_name,
                                                      sa_country, sa_city, sa_street, sa_phone, sa_postal_code)
VALUES
  (7, 7, 'Дмитрий', 'Иванович', 'Батура', 'Belarus', 'Smolevichi', 'Sovetskaia 20 kb 16', 375296674396, '222201');
INSERT INTO flowers_auction_bd.user_shipping_address (ship_addr_id, user_id, sa_first_name, sa_second_name, sa_last_name,
                                                      sa_country, sa_city, sa_street, sa_phone, sa_postal_code)
VALUES (8, 8, 'Зиноида', '', '', 'Belarus', 'Zhodino', 'Sovetsaia 11 kv 13', 375296123296, '222120');
INSERT INTO flowers_auction_bd.user_shipping_address (ship_addr_id, user_id, sa_first_name, sa_second_name, sa_last_name,
                                                      sa_country, sa_city, sa_street, sa_phone, sa_postal_code)
VALUES (9, 9, 'Ксения', 'Викторовна', 'Жук', 'Belarus', 'Minsk', 'Rocosovskogo d20 kv 5', 375297431296, '222120');
INSERT INTO flowers_auction_bd.user_shipping_address (ship_addr_id, user_id, sa_first_name, sa_second_name, sa_last_name,
                                                      sa_country, sa_city, sa_street, sa_phone, sa_postal_code)
VALUES (10, 10, 'Виктония', 'Владимировна', 'Клюева', 'Belarus', 'Smolevich', '8 Марта д.12', 375296631696, '222201');

-- flower lots initialization
INSERT INTO flowers_auction_bd.flower_lot (fl_id, user_id, fl_type, fl_name, fl_description, fl_start_price, fl_state)
VALUES (1, 2, 'луговые', 'Ирис', 'Отдаю в хорошие руки', 20.15, 'new');
INSERT INTO flowers_auction_bd.flower_lot (fl_id, user_id, fl_type, fl_name, fl_description, fl_start_price, fl_state)
VALUES (2, 2, 'луговые', 'Алтей', 'Отдаю в хорошие руки', 11.15, 'trading');
INSERT INTO flowers_auction_bd.flower_lot (fl_id, user_id, fl_type, fl_name, fl_description, fl_start_price, fl_state)
VALUES (3, 3, 'луговые', 'Горец Птичий', 'Отдаю в хорошие руки', 50000, 'rejected');
INSERT INTO flowers_auction_bd.flower_lot (fl_id, user_id, fl_type, fl_name, fl_description, fl_start_price, fl_state)
VALUES (4, 3, 'луговые', 'Донник', 'Отдаю в хорошие руки', 5, 'sold');
INSERT INTO flowers_auction_bd.flower_lot (fl_id, user_id, fl_type, fl_name, fl_description, fl_start_price, fl_state)
VALUES (5, 4, 'комнатные', 'Агава', 'Отдаю в хорошие руки', 16, 'new');
INSERT INTO flowers_auction_bd.flower_lot (fl_id, user_id, fl_type, fl_name, fl_description, fl_start_price, fl_state)
VALUES (6, 4, 'комнатные', 'Ардизия', 'Отдаю в хорошие руки', 200, 'trading');
INSERT INTO flowers_auction_bd.flower_lot (fl_id, user_id, fl_type, fl_name, fl_description, fl_start_price, fl_state)
VALUES (7, 4, 'комнатные', 'Алоэ', 'Отдаю в хорошие руки', 25, 'trading');
INSERT INTO flowers_auction_bd.flower_lot (fl_id, user_id, fl_type, fl_name, fl_description, fl_start_price, fl_state)
VALUES (8, 5, 'лесные', 'Вербейник', 'Отдаю в хорошие руки', 20.43, 'trading');
INSERT INTO flowers_auction_bd.flower_lot (fl_id, user_id, fl_type, fl_name, fl_description, fl_start_price, fl_state)
VALUES (9, 5, 'лесные', 'Адонис', 'Отдаю в хорошие руки', 19.99, 'trading');
INSERT INTO flowers_auction_bd.flower_lot (fl_id, user_id, fl_type, fl_name, fl_description, fl_start_price, fl_state)
VALUES (10, 6, 'лесные', 'Голубика', 'Отдаю в хорошие руки', 18, 'trading');
INSERT INTO flowers_auction_bd.flower_lot (fl_id, user_id, fl_type, fl_name, fl_description, fl_start_price, fl_state)
VALUES (11, 6, 'садовые', 'Аспаргус', 'Отдаю в хорошие руки', 20, 'sold');
INSERT INTO flowers_auction_bd.flower_lot (fl_id, user_id, fl_type, fl_name, fl_description, fl_start_price, fl_state)
VALUES (12, 7, 'садовые', 'Астра', 'Отдаю в хорошие руки', 3, 'sold');
INSERT INTO flowers_auction_bd.flower_lot (fl_id, user_id, fl_type, fl_name, fl_description, fl_start_price, fl_state)
VALUES (13, 8, 'садовые', 'Космея', 'Отдаю в хорошие руки', 10002, 'rejected');
INSERT INTO flowers_auction_bd.flower_lot (fl_id, user_id, fl_type, fl_name, fl_description, fl_start_price, fl_state)
VALUES (14, 9, 'кактусы', 'Астрофитум', 'Отдаю в хорошие руки', 15, 'trading');
INSERT INTO flowers_auction_bd.flower_lot (fl_id, user_id, fl_type, fl_name, fl_description, fl_start_price, fl_state)
VALUES (15, 10, 'кактусы', 'Эспотоя', 'Отдаю в хорошие руки', 14, 'trading');

-- User bets !
INSERT INTO flowers_auction_bd.bets_m2m (bet_id, fl_id, user_id, user_bet) VALUES (1, 2, 2, 16);
INSERT INTO flowers_auction_bd.bets_m2m (bet_id, fl_id, user_id, user_bet) VALUES (2, 2, 6, 20);
INSERT INTO flowers_auction_bd.bets_m2m (bet_id, fl_id, user_id, user_bet) VALUES (3, 4, 2, 50);
INSERT INTO flowers_auction_bd.bets_m2m (bet_id, fl_id, user_id, user_bet) VALUES (4, 4, 3, 70);
INSERT INTO flowers_auction_bd.bets_m2m (bet_id, fl_id, user_id, user_bet) VALUES (5, 6, 4, 100);
INSERT INTO flowers_auction_bd.bets_m2m (bet_id, fl_id, user_id, user_bet) VALUES (6, 6, 5, 120);
INSERT INTO flowers_auction_bd.bets_m2m (bet_id, fl_id, user_id, user_bet) VALUES (7, 6, 3, 85);
INSERT INTO flowers_auction_bd.bets_m2m (bet_id, fl_id, user_id, user_bet) VALUES (8, 7, 6, 70);
INSERT INTO flowers_auction_bd.bets_m2m (bet_id, fl_id, user_id, user_bet) VALUES (9, 7, 9, 80);
INSERT INTO flowers_auction_bd.bets_m2m (bet_id, fl_id, user_id, user_bet) VALUES (10, 7, 10, 85);
INSERT INTO flowers_auction_bd.bets_m2m (bet_id, fl_id, user_id, user_bet) VALUES (11, 8, 3, 15);
INSERT INTO flowers_auction_bd.bets_m2m (bet_id, fl_id, user_id, user_bet) VALUES (12, 8, 4, 18);
INSERT INTO flowers_auction_bd.bets_m2m (bet_id, fl_id, user_id, user_bet) VALUES (13, 8, 5, 16.44);
INSERT INTO flowers_auction_bd.bets_m2m (bet_id, fl_id, user_id, user_bet) VALUES (14, 9, 1, 23.5);
INSERT INTO flowers_auction_bd.bets_m2m (bet_id, fl_id, user_id, user_bet) VALUES (15, 10, 8, 25);
INSERT INTO flowers_auction_bd.bets_m2m (bet_id, fl_id, user_id, user_bet) VALUES (16, 10, 4, 28);
INSERT INTO flowers_auction_bd.bets_m2m (bet_id, fl_id, user_id, user_bet) VALUES (17, 10, 5, 29);
INSERT INTO flowers_auction_bd.bets_m2m (bet_id, fl_id, user_id, user_bet) VALUES (18, 10, 4, 30);
INSERT INTO flowers_auction_bd.bets_m2m (bet_id, fl_id, user_id, user_bet) VALUES (19, 11, 3, 31);
INSERT INTO flowers_auction_bd.bets_m2m (bet_id, fl_id, user_id, user_bet) VALUES (20, 11, 5, 32);
INSERT INTO flowers_auction_bd.bets_m2m (bet_id, fl_id, user_id, user_bet) VALUES (21, 12, 4, 33);
INSERT INTO flowers_auction_bd.bets_m2m (bet_id, fl_id, user_id, user_bet) VALUES (22, 12, 8, 34);
INSERT INTO flowers_auction_bd.bets_m2m (bet_id, fl_id, user_id, user_bet) VALUES (23, 14, 7, 32);
INSERT INTO flowers_auction_bd.bets_m2m (bet_id, fl_id, user_id, user_bet) VALUES (24, 14, 6, 33);
INSERT INTO flowers_auction_bd.bets_m2m (bet_id, fl_id, user_id, user_bet) VALUES (25, 15, 5, 35);

