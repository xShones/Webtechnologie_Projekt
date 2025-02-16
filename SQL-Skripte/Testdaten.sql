

#Testdaten User
INSERT INTO `user` (`id`, `email`, `password`, `username`) VALUES (1, 'test@web.de', 'passwort', 'testuser');

#Testdaten Kategorie
INSERT INTO `categories` (`id`, `name`) VALUES (1, 'Gehalt');
INSERT INTO `categories` (`id`, `name`) VALUES (2, 'Lebensmittel');
INSERT INTO `categories` (`id`, `name`) VALUES (4, 'Miete');
INSERT INTO `categories` (`id`, `name`) VALUES (3, 'Technik');


#Testdaten Transaktion
INSERT INTO `transaction` (`id`, `amount`, `date`, `description`, `type`, `category_id`, `user_id`) VALUES (1, 1344.8, '2025-01-16', 'Gehalt Januar', 'Einnahme', 1, 1);
INSERT INTO `transaction` (`id`, `amount`, `date`, `description`, `type`, `category_id`, `user_id`) VALUES (3, 1205.34, '2025-02-16', 'Neuer Fernseher', 'Ausgabe', 3, 1);
INSERT INTO `transaction` (`id`, `amount`, `date`, `description`, `type`, `category_id`, `user_id`) VALUES (2, 550, '2025-01-01', 'Miete Januar', 'Ausgabe', 4, 1);
